/*********************************************************************
 * Copyright (c) 2010-2018 Thales Global Services S.A.S.
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Thales Global Services S.A.S. - initial API and implementation
 **********************************************************************/
package org.eclipse.emf.diffmerge.generic.impl.helpers;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.diffmerge.generic.api.IComparison;
import org.eclipse.emf.diffmerge.generic.api.IDiffPolicy;
import org.eclipse.emf.diffmerge.generic.api.IMapping;
import org.eclipse.emf.diffmerge.generic.api.IMatch;
import org.eclipse.emf.diffmerge.generic.api.IMergePolicy;
import org.eclipse.emf.diffmerge.generic.api.IScopePolicy;
import org.eclipse.emf.diffmerge.generic.api.Role;
import org.eclipse.emf.diffmerge.generic.api.scopes.IEditableTreeDataScope;
import org.eclipse.emf.diffmerge.generic.api.scopes.ITreeDataScope;


/**
 * A unidirectional copier from a given scope to another one.
 * The state as defined in the superclass is never modified, only behavior is reused.
 * 
 * @param <E> The type of the elements of the data scope.
 * @param <A> The type of the attributes of the data scope.
 * @param <R> The type of the references of the data scope.
 * 
 * @author Olivier Constant
 */
public class UnidirectionalComparisonCopier<E, A, R> {
  
  /** The non-null role of the source for this copier */
  protected final Role _sourceRole;
  
  /** The initially null mapping this copier relies upon */
  protected IMapping.Editable<E, A, R> _mapping;
  
  /** The initially null source scope for this copier */
  protected ITreeDataScope<E, A, R> _sourceScope;
  
  /** The initially null target scope for this copier */
  protected IEditableTreeDataScope<E, A, R> _destinationScope;
  
  /** The potentially null diff policy */
  protected IDiffPolicy<E, A, R> _diffPolicy;
  
  /** The potentially null merge policy to apply */
  protected IMergePolicy<E, A, R> _mergePolicy;
  
  /** Whether out of scope values must be copied or ignored */
  protected boolean _copyOutOfScopeValues;
  
  
  /**
   * Constructor
   * @param sourceRole_p the non-null role being the source role of this copier
   *        w.r.t. the comparison
   */
  public UnidirectionalComparisonCopier(Role sourceRole_p) {
    _sourceRole = sourceRole_p;
    _mapping = null;
    _sourceScope = null;
    _destinationScope = null;
    _copyOutOfScopeValues = false;
  }
  
  /**
   * Complete the given partial match by copying its unmatched element and
   * updating this mapping accordingly.
   * The references of the element are not completed.
   * Postcondition: !partialMatch_p.isPartial()
   * @param partialMatch_p a non-null partial match
   * @param comparison_p a non-null comparison
   * @return a non-null element which is a clone of the element in partialMatch_p
   */
  public E completeMatch(IMatch<E, A, R> partialMatch_p, IComparison.Editable<E, A, R> comparison_p) {
    setComparison(comparison_p);
    assert partialMatch_p.getUncoveredRole() == _sourceRole.opposite() &&
        !getCompletedMatches().contains(partialMatch_p);
    E element = partialMatch_p.get(_sourceRole);
    E result = copy(element);
    assert result != null;
    _mapping.mapIncrementally(
        element, _sourceRole, result, _sourceRole.opposite());
    getCompletedMatches().add(_mapping.getMatchFor(element, _sourceRole));
    return result;
  }
  
  /**
   * Complete the references between all completed elements
   * @param comparison_p a non-null comparison defining a behavioral context
   */
  public void completeReferences(IComparison.Editable<E, A, R> comparison_p) {
    setComparison(comparison_p);
    copyReferences();
  }
  
  /**
   * Return a (shallow) copy of the given element.
   * Precondition: this method has never been called on the same element before,
   *   except if clear() was called in the meantime.
   * @param element_p an element belonging to the source scope
   * @return a non-null copy
   */
  protected E copy(E element_p) {
    assert _mergePolicy != null;
    E result = _mergePolicy.baseCopy(element_p);
    for (A attribute : _sourceScope.getAttributes(element_p)) {
      if (coverAttribute(attribute)) {
        copyAttribute(attribute, element_p, result);
      }
    }
    // No call to method put, so the state never changes
    return result;
  }
  
  /**
   * Copy the attribute values of the given element to the given copy
   * @param attribute_p a non-null attribute
   * @param element_p a non-null element
   * @param copy_p a non-null copy
   */
  protected void copyAttribute(A attribute_p, E element_p, E copy_p) {
    for (Object value : _sourceScope.getAttributeValues(element_p, attribute_p)) {
      _destinationScope.addAttributeValue(copy_p, attribute_p, value);
    }
  }
  
  /**
   * Copy reference values between the source elements copied to the resulting copies
   */
  protected void copyReferences() {
    for (IMatch<E, A, R> updatedMatch : getCompletedMatches()) {
      copyReferences(updatedMatch);
    }
    // Update of containments may have changed physical storage, which may have an impact on IDs
    if (_mergePolicy != null) {
      for (IMatch<E, A, R> updatedMatch : getCompletedMatches()) {
        E source = updatedMatch.get(_sourceRole);
        E target = updatedMatch.get(_sourceRole.opposite());
        _mergePolicy.setID(source, _sourceScope, target, _destinationScope);
      }
    }
  }
  
  /**
   * Copy the cross-references of the destination element of the given match
   * @param match_p a non-null, non-partial match
   */
  protected void copyReferences(IMatch<E, A, R> match_p) {
    E source = match_p.get(_sourceRole);
    E destination = match_p.get(_sourceRole.opposite());
    assert source != null && destination != null;
    for (R reference : _sourceScope.getReferences(source)) {
      if (!_sourceScope.getScopePolicy().isContainerReference(reference) &&
          coverReference(reference)) {
        copyReference(reference, source, destination);
      }
    }
  }
  
  /**
   * Copy the reference values of the given element to the given copy
   * @param reference_p a non-null reference
   * @param element_p a non-null element
   * @param copy_p a non-null copy
   */
  protected void copyReference(R reference_p, E element_p, E copy_p) {
    // This implementation assumes that values need only be added
    List<E> sourceValues = _sourceScope.getReferenceValues(element_p, reference_p);
    IScopePolicy<E, A, R> scopePolicy = _sourceScope.getScopePolicy();
    R opposite = scopePolicy.getOppositeReference(reference_p);
    for (E sourceValue : sourceValues) {
      IMatch<E, A, R> valueMatch = _mapping.getMatchFor(sourceValue, _sourceRole);
      if (valueMatch != null) {
        // Value in scope
        // If value is in copier or ref is unidirectional, it is not handled
        // by a ref presence diff so it must be copied
        boolean mustCopy = getCompletedMatches().contains(valueMatch) ||
          // Being a containment means there is an implicit opposite
          (opposite == null && !_sourceScope.isContainment(reference_p));
        if (!mustCopy) {
          // Otherwise, check if it is actually handled by a ref presence diff
          // (it may not be because the opposite ref may not be covered by the diff policy)
          IMatch<E, A, R> holderMatch = _mapping.getMatchFor(element_p, _sourceRole);
          if (holderMatch != null) {
            mustCopy =
                holderMatch.getReferenceValueDifference(reference_p, sourceValue) == null;
          }
        }
        if (mustCopy) {
          E destinationValue = valueMatch.get(_sourceRole.opposite());
          if (destinationValue != null) {
            _destinationScope.addReferenceValue(copy_p, reference_p, destinationValue);
          }
        } // Else handled by a ref presence diff
      } else {
        // Value out of scope: keep as is if no side effect due to bidirectionality or containment
        if (_copyOutOfScopeValues && opposite == null &&
            !_sourceScope.isContainment(reference_p) && !scopePolicy.isContainerReference(reference_p) ||
            _diffPolicy != null && _diffPolicy.coverOutOfScopeValue(sourceValue, reference_p)) {
          _destinationScope.addReferenceValue(copy_p, reference_p, sourceValue);
        }
      }
    }
  }
  
  /**
   * Return whether the given attribute must be copied
   * @param attribute_p a non-null attribute
   */
  protected boolean coverAttribute(A attribute_p) {
    return _mergePolicy != null && _mergePolicy.copyAttribute(attribute_p, _destinationScope);
  }
  
  /**
   * Return whether the given reference must be copied
   * @param reference_p a non-null reference
   */
  protected boolean coverReference(R reference_p) {
    return _mergePolicy != null && _mergePolicy.copyReference(reference_p, _destinationScope);
  }
  
  /**
   * Return the copy of the given element, if any
   * @param element_p a non-null element
   * @return a potentially null element
   */
  public E get(E element_p) {
    return get(element_p, true);
  }
  
  /**
   * Return the match of the given element, if any
   * @param element_p a non-null element, which for relevance should belong to the source scope
   * @param copyOnly_p whether the scope should be restricted to the matches
   *        updated by this copier, i.e., the result may only be a copy
   * @return a potentially null element
   */
  public E get(E element_p, boolean copyOnly_p) {
    E result = null;
    IMatch<E, A, R> match = _mapping.getMatchFor(element_p, _sourceRole);
    if (match != null && (!copyOnly_p || getCompletedMatches().contains(match))) {
      result = match.get(_sourceRole.opposite());
    }
    return result;
  }
  
  /**
   * Return the modifiable set of completed matches from the source role
   * to its opposite
   * @return a non-null, modifiable collection
   */
  protected Collection<IMatch<E, A, R>> getCompletedMatches() {
    return _mapping.getModifiableCompletedMatches(_sourceRole.opposite());
  }
  
  /**
   * Set the comparison which defines the behavioral context of this copier
   * @param comparison_p a non-null comparison
   */
  protected void setComparison(IComparison.Editable<E, A, R> comparison_p) {
    _mapping = comparison_p.getMapping();
    _sourceScope = comparison_p.getScope(_sourceRole);
    _destinationScope = comparison_p.getScope(_sourceRole.opposite());
    _diffPolicy = comparison_p.getLastDiffPolicy();
    _mergePolicy = comparison_p.getLastMergePolicy();
    if (_mergePolicy != null) {
      _copyOutOfScopeValues = _mergePolicy.copyOutOfScopeCrossReferences(
          _sourceScope, _destinationScope);
    }
  }
  
}