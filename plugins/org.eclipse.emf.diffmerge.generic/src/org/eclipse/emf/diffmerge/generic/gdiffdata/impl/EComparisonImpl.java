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
package org.eclipse.emf.diffmerge.generic.gdiffdata.impl;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.AbstractTreeIterator;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.diffmerge.generic.Messages;
import org.eclipse.emf.diffmerge.generic.api.IComparison;
import org.eclipse.emf.diffmerge.generic.api.IDiffPolicy;
import org.eclipse.emf.diffmerge.generic.api.IMapping;
import org.eclipse.emf.diffmerge.generic.api.IMatch;
import org.eclipse.emf.diffmerge.generic.api.IMatchPolicy;
import org.eclipse.emf.diffmerge.generic.api.IMergePolicy;
import org.eclipse.emf.diffmerge.generic.api.IMergeSelector;
import org.eclipse.emf.diffmerge.generic.api.Role;
import org.eclipse.emf.diffmerge.generic.api.diff.IDifference;
import org.eclipse.emf.diffmerge.generic.api.scopes.IEditableTreeDataScope;
import org.eclipse.emf.diffmerge.generic.api.scopes.ITreeDataScope;
import org.eclipse.emf.diffmerge.generic.gdiffdata.EComparison;
import org.eclipse.emf.diffmerge.generic.gdiffdata.EMapping;
import org.eclipse.emf.diffmerge.generic.gdiffdata.GdiffdataPackage;
import org.eclipse.emf.diffmerge.generic.impl.helpers.DiffOperation;
import org.eclipse.emf.diffmerge.generic.impl.helpers.MatchOperation;
import org.eclipse.emf.diffmerge.generic.impl.helpers.MergeOperation;
import org.eclipse.emf.diffmerge.generic.util.IExpensiveOperation;
import org.eclipse.emf.diffmerge.structures.IEqualityTester;
import org.eclipse.emf.diffmerge.structures.common.FArrayList;
import org.eclipse.emf.diffmerge.structures.common.FHashSet;
import org.eclipse.emf.diffmerge.structures.common.FOrderedSet;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>EComparison</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.emf.diffmerge.generic.gdiffdata.impl.EComparisonImpl#getAncestorScope <em>Ancestor Scope</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.generic.gdiffdata.impl.EComparisonImpl#getReferenceScope <em>Reference Scope</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.generic.gdiffdata.impl.EComparisonImpl#getTargetScope <em>Target Scope</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.generic.gdiffdata.impl.EComparisonImpl#getLastMatchPolicy <em>Last Match Policy</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.generic.gdiffdata.impl.EComparisonImpl#getLastDiffPolicy <em>Last Diff Policy</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.generic.gdiffdata.impl.EComparisonImpl#getLastMergePolicy <em>Last Merge Policy</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.generic.gdiffdata.impl.EComparisonImpl#getMapping <em>Mapping</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class EComparisonImpl<E, A, R> extends EIdentifiedImpl
    implements EComparison<E, A, R> {
  /**
   * The cached value of the '{@link #getAncestorScope() <em>Ancestor Scope</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAncestorScope()
   * @generated
   * @ordered
   */
  protected IEditableTreeDataScope<E, A, R> ancestorScope;

  /**
   * The cached value of the '{@link #getReferenceScope() <em>Reference Scope</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getReferenceScope()
   * @generated
   * @ordered
   */
  protected IEditableTreeDataScope<E, A, R> referenceScope;

  /**
   * The cached value of the '{@link #getTargetScope() <em>Target Scope</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTargetScope()
   * @generated
   * @ordered
   */
  protected IEditableTreeDataScope<E, A, R> targetScope;

  /**
   * The cached value of the '{@link #getLastMatchPolicy() <em>Last Match Policy</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getLastMatchPolicy()
   * @generated
   * @ordered
   */
  protected IMatchPolicy<E, A, R> lastMatchPolicy;

  /**
   * The cached value of the '{@link #getLastDiffPolicy() <em>Last Diff Policy</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getLastDiffPolicy()
   * @generated
   * @ordered
   */
  protected IDiffPolicy<E, A, R> lastDiffPolicy;

  /**
   * The cached value of the '{@link #getLastMergePolicy() <em>Last Merge Policy</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getLastMergePolicy()
   * @generated
   * @ordered
   */
  protected IMergePolicy<E, A, R> lastMergePolicy;

  /**
   * The cached value of the '{@link #getMapping() <em>Mapping</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getMapping()
   * @generated
   * @ordered
   */
  protected EMapping<E, A, R> mapping;

  /**
   * The non-null sets of duplicate match IDs per role
   * @generated NOT
   */
  private final Map<Role, Set<Object>> _duplicateIDs;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  protected EComparisonImpl() {
    super();
    Map<Role, Set<Object>> duplicateIDs = new HashMap<Role, Set<Object>>(3);
    for (Role role : Role.values()) {
      duplicateIDs.put(role, new HashSet<Object>(0));
    }
    _duplicateIDs = Collections.unmodifiableMap(duplicateIDs);
  }

  /**
   * Simplified constructor
   * @param targetScope_p the non-null model scope playing the TARGET comparison role
   * @param referenceScope_p the non-null model scope playing the REFERENCE comparison role
   * @generated NOT
   */
  public EComparisonImpl(IEditableTreeDataScope<E, A, R> targetScope_p,
      IEditableTreeDataScope<E, A, R> referenceScope_p) {
    this(targetScope_p, referenceScope_p, null);
  }

  /**
   * Full constructor
   * @param targetScope_p the non-null model scope playing the TARGET comparison role
   * @param referenceScope_p the non-null model scope playing the REFERENCE comparison role
   * @param ancestorScope_p the optional model scope playing the ANCESTOR comparison role
   * @generated NOT
   */
  public EComparisonImpl(IEditableTreeDataScope<E, A, R> targetScope_p,
      IEditableTreeDataScope<E, A, R> referenceScope_p,
      IEditableTreeDataScope<E, A, R> ancestorScope_p) {
    this();
    setTargetScope(targetScope_p);
    setReferenceScope(referenceScope_p);
    setAncestorScope(ancestorScope_p);
    setMapping(newMapping());
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass() {
    return GdiffdataPackage.Literals.ECOMPARISON;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public IEditableTreeDataScope<E, A, R> getAncestorScope() {
    return ancestorScope;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setAncestorScope(
      IEditableTreeDataScope<E, A, R> newAncestorScope) {
    IEditableTreeDataScope<E, A, R> oldAncestorScope = ancestorScope;
    ancestorScope = newAncestorScope;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET,
          GdiffdataPackage.ECOMPARISON__ANCESTOR_SCOPE, oldAncestorScope,
          ancestorScope));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public IEditableTreeDataScope<E, A, R> getReferenceScope() {
    return referenceScope;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setReferenceScope(
      IEditableTreeDataScope<E, A, R> newReferenceScope) {
    IEditableTreeDataScope<E, A, R> oldReferenceScope = referenceScope;
    referenceScope = newReferenceScope;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET,
          GdiffdataPackage.ECOMPARISON__REFERENCE_SCOPE, oldReferenceScope,
          referenceScope));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public IEditableTreeDataScope<E, A, R> getTargetScope() {
    return targetScope;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setTargetScope(IEditableTreeDataScope<E, A, R> newTargetScope) {
    IEditableTreeDataScope<E, A, R> oldTargetScope = targetScope;
    targetScope = newTargetScope;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET,
          GdiffdataPackage.ECOMPARISON__TARGET_SCOPE, oldTargetScope,
          targetScope));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public IMatchPolicy<E, A, R> getLastMatchPolicy() {
    return lastMatchPolicy;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setLastMatchPolicy(IMatchPolicy<E, A, R> newLastMatchPolicy) {
    IMatchPolicy<E, A, R> oldLastMatchPolicy = lastMatchPolicy;
    lastMatchPolicy = newLastMatchPolicy;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET,
          GdiffdataPackage.ECOMPARISON__LAST_MATCH_POLICY, oldLastMatchPolicy,
          lastMatchPolicy));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public IDiffPolicy<E, A, R> getLastDiffPolicy() {
    return lastDiffPolicy;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setLastDiffPolicy(IDiffPolicy<E, A, R> newLastDiffPolicy) {
    IDiffPolicy<E, A, R> oldLastDiffPolicy = lastDiffPolicy;
    lastDiffPolicy = newLastDiffPolicy;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET,
          GdiffdataPackage.ECOMPARISON__LAST_DIFF_POLICY, oldLastDiffPolicy,
          lastDiffPolicy));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public IMergePolicy<E, A, R> getLastMergePolicy() {
    return lastMergePolicy;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setLastMergePolicy(IMergePolicy<E, A, R> newLastMergePolicy) {
    IMergePolicy<E, A, R> oldLastMergePolicy = lastMergePolicy;
    lastMergePolicy = newLastMergePolicy;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET,
          GdiffdataPackage.ECOMPARISON__LAST_MERGE_POLICY, oldLastMergePolicy,
          lastMergePolicy));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EMapping<E, A, R> getMapping() {
    return mapping;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public NotificationChain basicSetMapping(EMapping<E, A, R> newMapping,
      NotificationChain msgs) {
    NotificationChain result = msgs;
    EMapping<E, A, R> oldMapping = mapping;
    mapping = newMapping;
    if (eNotificationRequired()) {
      ENotificationImpl notification = new ENotificationImpl(this,
          Notification.SET, GdiffdataPackage.ECOMPARISON__MAPPING, oldMapping,
          newMapping);
      if (result == null) {
        result = notification;
      } else {
        result.add(notification);
      }
    }
    return result;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setMapping(EMapping<E, A, R> newMapping) {
    if (newMapping != mapping) {
      NotificationChain msgs = null;
      if (mapping != null)
        msgs = ((InternalEObject) mapping).eInverseRemove(this,
            EOPPOSITE_FEATURE_BASE - GdiffdataPackage.ECOMPARISON__MAPPING,
            null, msgs);
      if (newMapping != null)
        msgs = ((InternalEObject) newMapping).eInverseAdd(this,
            EOPPOSITE_FEATURE_BASE - GdiffdataPackage.ECOMPARISON__MAPPING,
            null, msgs);
      msgs = basicSetMapping(newMapping, msgs);
      if (msgs != null)
        msgs.dispatch();
    } else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET,
          GdiffdataPackage.ECOMPARISON__MAPPING, newMapping, newMapping));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public NotificationChain eInverseRemove(InternalEObject otherEnd,
      int featureID, NotificationChain msgs) {
    switch (featureID) {
    case GdiffdataPackage.ECOMPARISON__MAPPING:
      return basicSetMapping(null, msgs);
    }
    return super.eInverseRemove(otherEnd, featureID, msgs);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType) {
    switch (featureID) {
    case GdiffdataPackage.ECOMPARISON__ANCESTOR_SCOPE:
      return getAncestorScope();
    case GdiffdataPackage.ECOMPARISON__REFERENCE_SCOPE:
      return getReferenceScope();
    case GdiffdataPackage.ECOMPARISON__TARGET_SCOPE:
      return getTargetScope();
    case GdiffdataPackage.ECOMPARISON__LAST_MATCH_POLICY:
      return getLastMatchPolicy();
    case GdiffdataPackage.ECOMPARISON__LAST_DIFF_POLICY:
      return getLastDiffPolicy();
    case GdiffdataPackage.ECOMPARISON__LAST_MERGE_POLICY:
      return getLastMergePolicy();
    case GdiffdataPackage.ECOMPARISON__MAPPING:
      return getMapping();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @SuppressWarnings("unchecked")
  @Override
  public void eSet(int featureID, Object newValue) {
    switch (featureID) {
    case GdiffdataPackage.ECOMPARISON__ANCESTOR_SCOPE:
      setAncestorScope((IEditableTreeDataScope<E, A, R>) newValue);
      return;
    case GdiffdataPackage.ECOMPARISON__REFERENCE_SCOPE:
      setReferenceScope((IEditableTreeDataScope<E, A, R>) newValue);
      return;
    case GdiffdataPackage.ECOMPARISON__TARGET_SCOPE:
      setTargetScope((IEditableTreeDataScope<E, A, R>) newValue);
      return;
    case GdiffdataPackage.ECOMPARISON__LAST_MATCH_POLICY:
      setLastMatchPolicy((IMatchPolicy<E, A, R>) newValue);
      return;
    case GdiffdataPackage.ECOMPARISON__LAST_DIFF_POLICY:
      setLastDiffPolicy((IDiffPolicy<E, A, R>) newValue);
      return;
    case GdiffdataPackage.ECOMPARISON__LAST_MERGE_POLICY:
      setLastMergePolicy((IMergePolicy<E, A, R>) newValue);
      return;
    case GdiffdataPackage.ECOMPARISON__MAPPING:
      setMapping((EMapping<E, A, R>) newValue);
      return;
    }
    super.eSet(featureID, newValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eUnset(int featureID) {
    switch (featureID) {
    case GdiffdataPackage.ECOMPARISON__ANCESTOR_SCOPE:
      setAncestorScope((IEditableTreeDataScope<E, A, R>) null);
      return;
    case GdiffdataPackage.ECOMPARISON__REFERENCE_SCOPE:
      setReferenceScope((IEditableTreeDataScope<E, A, R>) null);
      return;
    case GdiffdataPackage.ECOMPARISON__TARGET_SCOPE:
      setTargetScope((IEditableTreeDataScope<E, A, R>) null);
      return;
    case GdiffdataPackage.ECOMPARISON__LAST_MATCH_POLICY:
      setLastMatchPolicy((IMatchPolicy<E, A, R>) null);
      return;
    case GdiffdataPackage.ECOMPARISON__LAST_DIFF_POLICY:
      setLastDiffPolicy((IDiffPolicy<E, A, R>) null);
      return;
    case GdiffdataPackage.ECOMPARISON__LAST_MERGE_POLICY:
      setLastMergePolicy((IMergePolicy<E, A, R>) null);
      return;
    case GdiffdataPackage.ECOMPARISON__MAPPING:
      setMapping((EMapping<E, A, R>) null);
      return;
    }
    super.eUnset(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public boolean eIsSet(int featureID) {
    switch (featureID) {
    case GdiffdataPackage.ECOMPARISON__ANCESTOR_SCOPE:
      return ancestorScope != null;
    case GdiffdataPackage.ECOMPARISON__REFERENCE_SCOPE:
      return referenceScope != null;
    case GdiffdataPackage.ECOMPARISON__TARGET_SCOPE:
      return targetScope != null;
    case GdiffdataPackage.ECOMPARISON__LAST_MATCH_POLICY:
      return lastMatchPolicy != null;
    case GdiffdataPackage.ECOMPARISON__LAST_DIFF_POLICY:
      return lastDiffPolicy != null;
    case GdiffdataPackage.ECOMPARISON__LAST_MERGE_POLICY:
      return lastMergePolicy != null;
    case GdiffdataPackage.ECOMPARISON__MAPPING:
      return mapping != null;
    }
    return super.eIsSet(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String toString() {
    if (eIsProxy())
      return super.toString();

    StringBuilder result = new StringBuilder(super.toString());
    result.append(" (ancestorScope: "); //$NON-NLS-1$
    result.append(ancestorScope);
    result.append(", referenceScope: "); //$NON-NLS-1$
    result.append(referenceScope);
    result.append(", targetScope: "); //$NON-NLS-1$
    result.append(targetScope);
    result.append(", lastMatchPolicy: "); //$NON-NLS-1$
    result.append(lastMatchPolicy);
    result.append(", lastDiffPolicy: "); //$NON-NLS-1$
    result.append(lastDiffPolicy);
    result.append(", lastMergePolicy: "); //$NON-NLS-1$
    result.append(lastMergePolicy);
    result.append(')');
    return result.toString();
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IComparison#clear()
   * @generated NOT
   */
  public void clear() {
    ((IMapping.Editable<E, A, R>) getMapping()).clear();
    setLastMatchPolicy(null);
    setLastDiffPolicy(null);
    setLastMergePolicy(null);
    for (Role role : Role.values()) {
      _duplicateIDs.get(role).clear();
    }
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IComparison#compute(org.eclipse.emf.diffmerge.generic.api.IMatchPolicy, org.eclipse.emf.diffmerge.generic.api.IDiffPolicy, org.eclipse.emf.diffmerge.generic.api.IMergePolicy, org.eclipse.core.runtime.IProgressMonitor)
   * @generated NOT
   */
  public IStatus compute(IMatchPolicy<E, A, R> matchPolicy_p,
      IDiffPolicy<E, A, R> diffPolicy_p, IMergePolicy<E, A, R> mergePolicy_p,
      IProgressMonitor monitor_p) {
    // Monitor
    IProgressMonitor nonNullMonitor = monitor_p != null ? monitor_p
        : new NullProgressMonitor();
    SubMonitor subMonitor = SubMonitor.convert(nonNullMonitor,
        Messages.Comparison_Task_Main, 2);
    // Policies
    setLastMatchPolicy(
        matchPolicy_p != null ? matchPolicy_p : getDefaultMatchPolicy());
    setLastDiffPolicy(
        diffPolicy_p != null ? diffPolicy_p : getDefaultDiffPolicy());
    setLastMergePolicy(
        mergePolicy_p != null ? mergePolicy_p : getDefaultMergePolicy());
    // Behavior
    IStatus result = computeMatch(getLastMatchPolicy(), subMonitor.newChild(1));
    if (result.isOK()) {
      result = computeDiff(getLastDiffPolicy(), getLastMergePolicy(),
          subMonitor.newChild(1));
    }
    return result;
  }

  /**
   * Execute the Match phase of the comparison process
   * @param matchPolicy_p a non-null match policy
   * @param monitor_p a non-null progress monitor
   * @return a non-null status of the execution
   * @generated NOT
   */
  protected IStatus computeMatch(IMatchPolicy<E, A, R> matchPolicy_p,
      IProgressMonitor monitor_p) {
    IExpensiveOperation matchOperation = getMatchOperation(matchPolicy_p,
        _duplicateIDs);
    IStatus result = matchOperation.run(monitor_p);
    return result;
  }

  /**
   * Execute the Diff phase of the comparison process
   * @param diffPolicy_p a non-null diff policy
   * @param mergePolicy_p a non-null merge policy
   * @param monitor_p a non-null progress monitor
   * @return a non-null status of the execution
   * @generated NOT
   */
  protected IStatus computeDiff(IDiffPolicy<E, A, R> diffPolicy_p,
      IMergePolicy<E, A, R> mergePolicy_p, IProgressMonitor monitor_p) {
    IExpensiveOperation diffOperation = getDiffOperation(diffPolicy_p,
        mergePolicy_p);
    IStatus result = diffOperation.run(monitor_p);
    return result;
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IComparison#getAllContents(org.eclipse.emf.diffmerge.generic.api.Role)
   * @generated NOT
   */
  @SuppressWarnings("serial")
  public TreeIterator<IMatch<E, A, R>> getAllContents(final Role role_p) {
    return new AbstractTreeIterator<IMatch<E, A, R>>(this, false) {
      /**
       * @see org.eclipse.emf.common.util.AbstractTreeIterator#getChildren(Object)
       */
      @SuppressWarnings({ "rawtypes", "unchecked" })
      @Override
      protected Iterator<? extends IMatch<E, A, R>> getChildren(
          Object object_p) {
        Iterator<? extends IMatch<E, A, R>> result;
        if (object_p instanceof IComparison) {
          result = ((IComparison) object_p).getContents(role_p).iterator();
        } else {
          result = getContentsOf((IMatch) object_p, role_p).iterator();
        }
        return result;
      }
    };
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IComparison#getContainerOf(org.eclipse.emf.diffmerge.generic.api.IMatch, org.eclipse.emf.diffmerge.generic.api.Role)
   * @generated NOT
   */
  public IMatch<E, A, R> getContainerOf(IMatch<E, A, R> match_p, Role role_p) {
    IMatch<E, A, R> result = null;
    E child = match_p.get(role_p);
    if (child != null) {
      ITreeDataScope<E, A, R> scope = getScope(role_p);
      E container = scope.getContainer(child);
      if (container != null) {
        result = getMapping().getMatchFor(container, role_p);
      }
    }
    return result;
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IComparison#getContents()
   * @generated NOT
   */
  public List<IMatch<E, A, R>> getContents() {
    List<IMatch<E, A, R>> targetMatches = getContents(Role.TARGET);
    List<IMatch<E, A, R>> referenceMatches = getContents(Role.REFERENCE);
    List<IMatch<E, A, R>> result = new FArrayList<IMatch<E, A, R>>(
        referenceMatches, null);
    for (IMatch<E, A, R> targetMatch : targetMatches) {
      if (!result.contains(targetMatch))
        result.add(targetMatch);
    }
    return Collections.unmodifiableList(result);
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IComparison#getContents(org.eclipse.emf.diffmerge.generic.api.Role)
   * @generated NOT
   */
  public List<IMatch<E, A, R>> getContents(Role role_p) {
    List<IMatch<E, A, R>> result = new FArrayList<IMatch<E, A, R>>();
    // Defining 'contents': list of elements whose matches must be retrieved
    Iterable<E> contents = getScope(role_p).getRoots();
    // Retrieving matches
    for (E child : contents) {
      IMatch<E, A, R> childMatch = getMapping().getMatchFor(child, role_p);
      if (childMatch != null)
        result.add(childMatch);
    }
    return Collections.unmodifiableList(result);
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IComparison#getContentsOf(org.eclipse.emf.diffmerge.generic.api.IMatch)
   * @generated NOT
   */
  public List<IMatch<E, A, R>> getContentsOf(IMatch<E, A, R> match_p) {
    List<IMatch<E, A, R>> targetMatches = getContentsOf(match_p, Role.TARGET);
    List<IMatch<E, A, R>> referenceMatches = getContentsOf(match_p,
        Role.REFERENCE);
    List<IMatch<E, A, R>> result = new FOrderedSet<IMatch<E, A, R>>(
        referenceMatches, null);
    for (IMatch<E, A, R> targetMatch : targetMatches) {
      result.add(targetMatch);
    }
    return Collections.unmodifiableList(result);
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IComparison#getContentsOf(org.eclipse.emf.diffmerge.generic.api.IMatch, org.eclipse.emf.diffmerge.generic.api.Role)
   * @generated NOT
   */
  public List<IMatch<E, A, R>> getContentsOf(IMatch<E, A, R> match_p,
      Role role_p) {
    List<IMatch<E, A, R>> result = new FArrayList<IMatch<E, A, R>>();
    // Defining 'contents': list of elements whose matches must be retrieved
    Iterable<E> contents = null;
    E container = match_p.get(role_p);
    if (container != null) {
      ITreeDataScope<E, A, R> scope = getScope(role_p);
      contents = scope.getContents(container);
    }
    // Retrieving matches
    if (contents != null) {
      for (E child : contents) {
        IMatch<E, A, R> childMatch = getMapping().getMatchFor(child, role_p);
        if (childMatch != null)
          result.add(childMatch);
      }
    }
    return Collections.unmodifiableList(result);
  }

  /**
   * Return a default diff policy
   * @return a non-null object
   * @generated NOT
   */
  protected abstract IDiffPolicy<E, A, R> getDefaultDiffPolicy();

  /**
   * Return a default match policy
   * @return a non-null object
   * @generated NOT
   */
  protected abstract IMatchPolicy<E, A, R> getDefaultMatchPolicy();

  /**
   * Return a default merge policy
   * @return a non-null object
   * @generated NOT
   */
  protected abstract IMergePolicy<E, A, R> getDefaultMergePolicy();

  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IComparison#getDifferences(org.eclipse.emf.diffmerge.generic.api.Role)
   * @generated NOT
   */
  public List<IDifference<E, A, R>> getDifferences(Role role_p) {
    List<IDifference<E, A, R>> result = new FArrayList<IDifference<E, A, R>>();
    Iterator<IMatch<E, A, R>> it = getAllContents(role_p);
    while (it.hasNext()) {
      IMatch<E, A, R> current = it.next();
      result.addAll(current.getPresenceDifferencesIn(role_p));
    }
    return result;
  }

  /**
   * Return an operation for executing the Diff phase
   * @param diffPolicy_p an optional diff policy
   * @param mergePolicy_p an optional merge policy
   * @return a non-null operation which is configured to be applied on the given comparison data
   * @generated NOT
   */
  protected IExpensiveOperation getDiffOperation(
      IDiffPolicy<E, A, R> diffPolicy_p, IMergePolicy<E, A, R> mergePolicy_p) {
    return new DiffOperation<E, A, R>(this, diffPolicy_p, mergePolicy_p);
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IComparison#getDuplicateMatchIDs(org.eclipse.emf.diffmerge.generic.api.Role)
   * @generated NOT
   */
  public Collection<Object> getDuplicateMatchIDs(Role role_p) {
    return _duplicateIDs.get(role_p);
  }

  /**
   * Return an operation for executing the Match phase
   * @param policy_p an optional match policy
   * @param duplicateIDs_p an optional map that associates each role with an empty, modifiable set of duplicate match IDs 
   * @return a non-null operation which is configured to be applied on the given comparison data
   * @generated NOT
   */
  protected IExpensiveOperation getMatchOperation(
      IMatchPolicy<E, A, R> policy_p, Map<Role, Set<Object>> duplicateIDs_p) {
    return new MatchOperation<E, A, R>(this, policy_p, duplicateIDs_p);
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IComparison#getNbDifferences()
   * @generated NOT
   */
  public int getNbDifferences() {
    int result = 0;
    for (IMatch<E, A, R> match : getMapping().getContents()) {
      result += match.getRelatedDifferences().size();
    }
    return result;
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IComparison#getNbNoContainmentDifferences()
   * @generated NOT
   */
  public int getNbNoContainmentDifferences() {
    int result = 0;
    for (IMatch<E, A, R> match : getMapping().getContents()) {
      result += match.getNbNoContainmentDifferences();
    }
    return result;
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IComparison#getRemainingDifferences()
   * @generated NOT
   */
  public Collection<IDifference<E, A, R>> getRemainingDifferences() {
    Collection<IDifference<E, A, R>> result = new FHashSet<IDifference<E, A, R>>(
        IEqualityTester.BY_EQUALS);
    for (IMatch<E, A, R> match : getMapping().getContents()) {
      for (IDifference<E, A, R> difference : match.getAllDifferences()) {
        if (!difference.isMerged())
          result.add(difference);
      }
    }
    return Collections.unmodifiableCollection(result);
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IComparison.Editable#getScope(org.eclipse.emf.diffmerge.generic.api.Role)
   * @generated NOT
   */
  public IEditableTreeDataScope<E, A, R> getScope(Role role_p) {
    IEditableTreeDataScope<E, A, R> result;
    switch (role_p) {
    case TARGET:
      result = getTargetScope();
      break;
    case REFERENCE:
      result = getReferenceScope();
      break;
    default:
      result = getAncestorScope();
    }
    return result;
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IComparison#hasRemainingDifferences()
   * @generated NOT
   */
  public boolean hasRemainingDifferences() {
    for (IMatch<E, A, R> match : getMapping().getContents()) {
      for (IDifference<E, A, R> difference : match.getAllDifferences()) {
        if (!difference.isMerged())
          return true;
      }
    }
    return false;
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IComparison#isConsistent()
   * @generated NOT
   */
  public boolean isConsistent() {
    for (Role role : Role.values()) {
      if (!getDuplicateMatchIDs(role).isEmpty())
        return false;
    }
    return true;
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IComparison#isThreeWay()
   * @generated NOT
   */
  public boolean isThreeWay() {
    return getAncestorScope() != null;
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IComparison#merge(org.eclipse.emf.diffmerge.generic.api.Role, boolean, org.eclipse.core.runtime.IProgressMonitor)
   * @generated not
   */
  public Collection<IDifference<E, A, R>> merge(final Role destination_p,
      boolean updateReferences_p, IProgressMonitor monitor_p) {
    Collection<IDifference<E, A, R>> result = merge(new IMergeSelector() {
      /**
       * @see org.eclipse.emf.diffmerge.generic.api.IMergeSelector#getMergeDirection(org.eclipse.emf.diffmerge.generic.api.diff.IDifference)
       */
      public Role getMergeDirection(IDifference<?, ?, ?> difference_p) {
        return destination_p;
      }
    }, updateReferences_p, monitor_p);
    return result;
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IComparison#merge(java.util.Collection, org.eclipse.emf.diffmerge.generic.api.Role, boolean, org.eclipse.core.runtime.IProgressMonitor)
   * @generated NOT
   */
  public Collection<IDifference<E, A, R>> merge(
      Collection<? extends IDifference<E, A, R>> differences_p,
      Role destination_p, boolean updateReferences_p,
      IProgressMonitor monitor_p) {
    MergeOperation<E, A, R> operation = new MergeOperation<E, A, R>(this,
        differences_p, destination_p, updateReferences_p);
    operation.run(monitor_p);
    return operation.getOutput();
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IComparison#merge(org.eclipse.emf.diffmerge.generic.api.IMergeSelector, boolean, org.eclipse.core.runtime.IProgressMonitor)
   * @generated NOT
   */
  public Collection<IDifference<E, A, R>> merge(IMergeSelector merger_p,
      boolean updateReferences_p, IProgressMonitor monitor_p) {
    MergeOperation<E, A, R> operation = new MergeOperation<E, A, R>(this,
        merger_p, updateReferences_p);
    operation.run(monitor_p);
    return operation.getOutput();
  }

  /**
   * Create and return a mapping for this comparison
   * @return a non-null mapping
   * @generated NOT
   */
  protected abstract EMapping<E, A, R> newMapping();

  /**
   * @see org.eclipse.emf.diffmerge.generic.api.IComparison.Editable#swapScopes()
   * @generated NOT
   */
  public boolean swapScopes() {
    boolean isEmpty = getMapping().isEmpty();
    if (isEmpty) {
      IEditableTreeDataScope<E, A, R> formerTarget = targetScope;
      targetScope = referenceScope;
      referenceScope = formerTarget;
    }
    return isEmpty;
  }

} //EComparisonImpl