/**
 * <copyright>
 * 
 * Copyright (c) 2010-2012 Thales Global Services S.A.S.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Thales Global Services S.A.S. - initial API and implementation
 * 
 * </copyright>
 */
package org.eclipse.emf.diffmerge.ui.viewers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.compare.structuremergeviewer.DiffNode;
import org.eclipse.compare.structuremergeviewer.Differencer;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.diffmerge.api.IComparison;
import org.eclipse.emf.diffmerge.api.IMatch;
import org.eclipse.emf.diffmerge.api.Role;
import org.eclipse.emf.diffmerge.api.diff.IDifference;
import org.eclipse.emf.diffmerge.api.diff.IElementPresence;
import org.eclipse.emf.diffmerge.api.diff.IElementRelativeDifference;
import org.eclipse.emf.diffmerge.api.diff.IElementRelativePresence;
import org.eclipse.emf.diffmerge.api.diff.IPresenceDifference;
import org.eclipse.emf.diffmerge.api.diff.IReferenceValuePresence;
import org.eclipse.emf.diffmerge.api.diff.IValuePresence;
import org.eclipse.emf.diffmerge.diffdata.EComparison;
import org.eclipse.emf.diffmerge.diffdata.EMatch;
import org.eclipse.emf.diffmerge.ui.EMFDiffMergeUIPlugin;
import org.eclipse.emf.diffmerge.ui.actions.EMFDiffMergeEditorInput.ScopeTypedElementWrapper;
import org.eclipse.emf.diffmerge.ui.diffuidata.MatchAndFeature;
import org.eclipse.emf.diffmerge.ui.diffuidata.UIComparison;
import org.eclipse.emf.diffmerge.ui.util.DifferenceKind;
import org.eclipse.emf.diffmerge.util.structures.FHashMap;
import org.eclipse.emf.diffmerge.util.structures.FOrderedSet;
import org.eclipse.emf.diffmerge.util.structures.IEqualityTester;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.ui.services.IDisposable;



/**
 * An ICompareInput that wraps a model comparison
 * @author Olivier Constant
 */
public class ModelComparisonDiffNode extends DiffNode implements IDisposable {
  
  /**
   * The kinds of user-level differences
   */
  public static enum UserDifferenceKind { PRESENCE_LEFT, PRESENCE_RIGHT, MOVE, PROPER }
  
  /** Whether to use custom icons for differences by default */
  public static final boolean DEFAULT_USE_CUSTOM_ICONS = true;
  
  /** Whether to use custom labels for differences by default */
  public static final boolean DEFAULT_USE_CUSTOM_LABELS = false;
  
  
  /** The resource manager */
  private final ComparisonResourceManager _resourceManager;
  
  /** The non-null comparison-related contents */
  private final UIComparison _contents;
  
  /** The non-null editing domain */
  private final EditingDomain _editingDomain;
  
  /** The role that drives the representation of the comparison */
  private Role _drivingRole;
  
  /** The set of difference kinds which are counted */
  private final Set<UserDifferenceKind> _countedKinds;
  
  /** The map from matches to difference numbers */
  private final EMap<EMatch, Integer> _matchToNb;
  
  /** Whether to use custom icons for differences */
  private boolean _useCustomIcons;
  
  /** Whether to use custom labels for differences */
  private boolean _useCustomLabels;
  
  
  /**
   * Constructor
   * @param uiComparison_p a non-null UI comparison
   * @param domain_p a non-null editing domain
   */
  public ModelComparisonDiffNode(UIComparison uiComparison_p, EditingDomain domain_p) {
    super(
        Differencer.CHANGE,
        uiComparison_p.getActualComparison().isThreeWay()? new ScopeTypedElementWrapper(
            uiComparison_p.getActualComparison().getScope(Role.ANCESTOR)): null,
        new ScopeTypedElementWrapper(uiComparison_p.getActualComparison().getScope(Role.TARGET)),
        new ScopeTypedElementWrapper(uiComparison_p.getActualComparison().getScope(Role.REFERENCE)));
    _resourceManager = new ComparisonResourceManager();
    _contents = uiComparison_p;
    _editingDomain = domain_p;
    _drivingRole = Role.TARGET;
    _useCustomIcons = DEFAULT_USE_CUSTOM_ICONS;
    _useCustomLabels = DEFAULT_USE_CUSTOM_LABELS;
    _matchToNb = new FHashMap<EMatch, Integer>(IEqualityTester.BY_EQUALS);
    _countedKinds = new HashSet<UserDifferenceKind>(
        Arrays.asList(UserDifferenceKind.values()));
  }
  
  /**
   * Count and return the number of significant differences on the given match
   * @param match_p a non-null match
   * @return a positive int
   */
  protected int countDifferenceNumber(IMatch match_p) {
    int result = 0;
    if (counts(UserDifferenceKind.PROPER))
     result += countProperDifferenceNumber(match_p);
    if (counts(UserDifferenceKind.MOVE) && isAMove(match_p, false, false))
      result++;
    IElementPresence presence = match_p.getElementPresenceDifference();
    if (presence != null && !shouldBeIgnored(presence)) {
      boolean isAddition = presence.getPresenceRole() == getDrivingRole();
      boolean countPresence = isAddition? counts(UserDifferenceKind.PRESENCE_LEFT):
        counts(UserDifferenceKind.PRESENCE_RIGHT);
      if (countPresence)
        result++;
    }
    return result;
  }
  
  /**
   * Return the number of proper differences at a user level on the given match
   * @param match_p a non-null match
   * @return a positive int or 0
   */
  protected int countProperDifferenceNumber(IMatch match_p) {
    int result = 0;
    if (!match_p.isPartial()) {
      Set<EStructuralFeature> uniFeatures = new HashSet<EStructuralFeature>();
      for (IDifference difference : match_p.getRelatedDifferences()) {
        if (difference instanceof IElementRelativeDifference && !shouldBeIgnored(difference)) {
          IElementRelativeDifference eltDiff = (IElementRelativeDifference)difference;
          if (eltDiff.isProperToElement()) {
            if (eltDiff instanceof IValuePresence) {
              EStructuralFeature feature = ((IValuePresence)eltDiff).getFeature();
              if (feature != null &&
                  (!feature.isMany() || ((IValuePresence)eltDiff).isOrder())) {
                // A value presence on a non-many feature
                if (!uniFeatures.contains(feature)) {
                  // Not counted yet
                  result++;
                  uniFeatures.add(feature);
                }
              } else {
                // A value presence on a feature such that isMany()
                result++;
              }
            } else {
              // Not a value presence
              result++;
            }
          }
        }
      }
    }
    return result;
  }
  
  /**
   * Return whether the given difference kind is being counted
   * @param kind_p a non-null difference kind
   */
  public boolean counts(UserDifferenceKind kind_p) {
    return _countedKinds.contains(kind_p);
  }
  
  /**
   * @see org.eclipse.ui.services.IDisposable#dispose()
   */
  public void dispose() {
    _resourceManager.dispose();
  }
  
  /**
   * Return the model comparison of this node
   * @return a non-null comparison
   */
  public EComparison getActualComparison() {
    return getUIComparison().getActualComparison();
  }
  
  /**
   * Return the container of the given match from a GUI perspective
   * @param match_p a non-null match
   * @return a potentially null match
   */
  protected EMatch getContainerOf(EMatch match_p) {
    Role containerSide;
    if (match_p.getUncoveredRole() == getDrivingRole())
      containerSide = getDrivingRole().opposite();
    else
      containerSide = getDrivingRole();
    EMatch result = (EMatch)getActualComparison().getContainerOf(match_p, containerSide);
    return result;
  }
  
  /**
   * Return the difference kind of the given match with filtering
   * @param match_p a non-null match
   * @return FROM_LEFT*, FROM_RIGHT*, CONFLICT, MODIFIED, COUNTED or NONE
   */
  public DifferenceKind getDifferenceKind(IMatch match_p) {
    DifferenceKind result = DifferenceKind.NONE;
    IElementPresence presence = match_p.getElementPresenceDifference();
    if (presence != null) {
      result = getDifferenceKind(presence);
    } else {
      result = getModificationKind(match_p);
      result = result.with(getOwnershipDifferenceKind(match_p), isThreeWay());
      result = result.keepOnlyDirection(isThreeWay());
    }
    if (result == DifferenceKind.NONE && getDifferenceNumber(match_p) > 0)
      result = DifferenceKind.COUNTED;
    return result;
  }
  
  /**
   * Return the difference kind of the given match and feature with filtering
   * @param maf_p a non-null match and feature
   * @return FROM_LEFT*, FROM_RIGHT*, CONFLICT, MODIFIED, COUNTED or NONE
   */
  @SuppressWarnings({ "unchecked", "rawtypes" })
  public DifferenceKind getDifferenceKind(MatchAndFeature maf_p) {
    DifferenceKind result = DifferenceKind.NONE;
    EStructuralFeature feature = maf_p.getFeature();
    if (feature == EMFDiffMergeUIPlugin.getDefault().getOwnershipFeature()) {
      // Ownership feature (move)
      result = getOwnershipDifferenceKind(maf_p.getMatch());
    } else {
      // Standard feature
      Collection<? extends IValuePresence> presences;
      if (feature instanceof EReference) {
        if (((EReference)feature).isContainment()) {
          // Containment on feature which is not ownership:
          // consider order only
          presences = new ArrayList<IValuePresence>();
          IValuePresence orderDiff = maf_p.getMatch().getOrderDifference(feature, getDrivingRole());
          if (orderDiff != null)
            ((List)presences).add(orderDiff);
          orderDiff = maf_p.getMatch().getOrderDifference(feature, getDrivingRole().opposite());
          if (orderDiff != null)
            ((List)presences).add(orderDiff);
        } else {
          presences = maf_p.getMatch().getReferenceDifferences((EReference)feature);
        }
      } else {
        presences = maf_p.getMatch().getAttributeDifferences((EAttribute)feature);
      }
      Iterator<? extends IValuePresence> it = presences.iterator();
      while (it.hasNext() && result != DifferenceKind.CONFLICT &&
          result != DifferenceKind.FROM_BOTH && result != DifferenceKind.MODIFIED) {
        DifferenceKind current = getDifferenceKind(it.next());
        result = result.with(current, true);
      }
    }
    return result;
  }
  
  /**
   * Return the kind of the given difference with filtering
   * @param difference_p a non-null difference
   * @return FROM_LEFT_*, FROM_RIGHT_*, CONFLICT, MODIFIED or NONE
   */
  public DifferenceKind getDifferenceKind(IDifference difference_p) {
    DifferenceKind result = DifferenceKind.NONE;
    if (!shouldBeIgnored(difference_p)) {
      if (difference_p.isConflicting()) {
        result = DifferenceKind.CONFLICT;
      } else if (difference_p instanceof IPresenceDifference) {
        IPresenceDifference presence = (IPresenceDifference)difference_p;
        boolean isMany = isMany(presence);
        if (presence.getPresenceRole() == getDrivingRole()) {
          // Present on the driving side
          if (isThreeWay() && presence.isAlignedWithAncestor())
            result = DifferenceKind.FROM_RIGHT_DEL;
          else
            if (isMany || isThreeWay())
              result = DifferenceKind.FROM_LEFT_ADD;
            else
              result = DifferenceKind.MODIFIED;
        } else {
          // Present on the non-driving side
          if (isThreeWay() && presence.isAlignedWithAncestor())
            result = DifferenceKind.FROM_LEFT_DEL;
          else
            if (isMany || isThreeWay())
              result = DifferenceKind.FROM_RIGHT_ADD;
            else
              result = DifferenceKind.MODIFIED;
        }
      }
    }
    return result;
  }
  
  /**
   * Return the number of differences associated to the given match
   * @param match_p a non-null match
   * @return a positive int or 0
   */
  public int getDifferenceNumber(IMatch match_p) {
    Integer currentNb = getMatchToNb().get(match_p);
    if (currentNb == null)
      currentNb = Integer.valueOf(0);
    return currentNb.intValue();
  }
  
  /**
   * Return the role that drives the representation of the model comparison
   * @return a non-null role which is TARGET or REFERENCE
   */
  public Role getDrivingRole() {
    return _drivingRole;
  }
  
  /**
   * Return the list of visible children for merge of the given match
   * @param match_p a non-null match
   * @return a non-null, potentially empty, unmodifiable list
   */
  public List<IMatch> getChildrenForMerge(IMatch match_p) {
    List<IMatch> candidates = getActualComparison().getContentsOf(match_p);
    List<IMatch> result = new FOrderedSet<IMatch>();
    IComparison comparison = match_p.getMapping().getComparison();
    for (IMatch candidate : candidates) {
      if (isAMove(candidate, false, false) &&
          comparison.getContainerOf(candidate, getDrivingRole().opposite()) == match_p)
        continue; // Move origin
      if (getDifferenceNumber(candidate) > 0)
        result.add(candidate);
    }
    return Collections.unmodifiableList(result);
  }
  
  /**
   * Return the editing domain of this node
   * @return a non-null editing domain
   */
  public EditingDomain getEditingDomain() {
    return _editingDomain;
  }
  
  /**
   * Return the map from matches to differences numbers
   * @return a non-null, modifiable map
   */
  protected EMap<EMatch, Integer> getMatchToNb() {
    return _matchToNb;
  }
  
  /**
   * Return the modification status of the given match with filtering
   * @param match_p a non-null match
   * @return a non-null kind
   */
  protected DifferenceKind getModificationKind(IMatch match_p) {
    DifferenceKind result = DifferenceKind.NONE;
    if (counts(UserDifferenceKind.PROPER) &&
        match_p.getElementPresenceDifference() == null) {
      for (IDifference diff : match_p.getRelatedDifferences()) {
        if (diff instanceof IElementRelativeDifference &&
            ((IElementRelativeDifference)diff).isProperToElement()) {
          DifferenceKind diffKind = getDifferenceKind(diff);
          result = result.with(diffKind, isThreeWay());
          if (result == DifferenceKind.CONFLICT)
            break;
        }
      }
    }
    return result;
  }
  
  /**
   * Return the subset of the given set of differences which should not be ignored
   * given the current representation configuration
   * @param differences_p a non-null set
   * @return a non-null, potentially empty, unmodifiable list
   */
  public List<IDifference> getNonIgnoredDifferences(Iterable<? extends IDifference> differences_p) {
    List<IDifference> result = new ArrayList<IDifference>();
    for (IDifference difference : differences_p) {
      if (!shouldBeIgnored(difference))
        result.add(difference);
    }
    return Collections.unmodifiableList(result);
  }
  
  /**
   * Return the difference kind for the ownership of the given match
   * @param match_p a non-null match
   * @return a non-null difference kind
   */
  protected DifferenceKind getOwnershipDifferenceKind(IMatch match_p) {
    DifferenceKind result = DifferenceKind.NONE;
    if (representAsMove(match_p)) {
      result = DifferenceKind.MODIFIED;
      if (isThreeWay()) {
        IReferenceValuePresence onLeft = match_p.getOwnershipDifference(getDrivingRole());
        boolean fromLeft = onLeft != null && !onLeft.isAlignedWithAncestor();
        IReferenceValuePresence onRight = match_p.getOwnershipDifference(getDrivingRole().opposite());
        boolean fromRight = onRight != null && !onRight.isAlignedWithAncestor();
        if (fromLeft && fromRight)
          result = DifferenceKind.CONFLICT;
        else if (fromLeft)
          result = DifferenceKind.FROM_LEFT;
        else if (fromRight)
          result = DifferenceKind.FROM_RIGHT;
      }
    }
    return result;
  }
  
  /**
   * Return the resource manager for this node
   * @return a non-null resource manager
   */
  protected ComparisonResourceManager getResourceManager() {
    return _resourceManager;
  }
  
  /**
   * Return the UI comparison of this node
   * @return a non-null UI comparison
   */
  public UIComparison getUIComparison() {
    return _contents;
  }
  
  /**
   * Return the number of differences associated to the given match from a
   * UI perspective
   * @param match_p a non-null match
   * @return a positive int or 0
   */
  public int getUIDifferenceNumber(EMatch match_p) {
    int result = getDifferenceNumber(match_p);
    if (match_p.getUncoveredRole() == getDrivingRole() &&
          counts(UserDifferenceKind.PRESENCE_RIGHT) ||
        match_p.getUncoveredRole() == getDrivingRole().opposite()
          && counts(UserDifferenceKind.PRESENCE_LEFT))
      result--;
    return result;
  }
  
  /**
   * @see org.eclipse.compare.structuremergeviewer.DiffContainer#hasChildren()
   */
  @Override
  public boolean hasChildren() {
    // Is there content?
    return getUIComparison().getActualComparison().hasRemainingDifferences();
  }
  
  /**
   * Return whether the given match has visible children for merge
   * @param match_p a non-null match
   */
  public boolean hasChildrenForMerge(IMatch match_p) {
    List<IMatch> candidates = getActualComparison().getContentsOf(match_p);
    IComparison comparison = match_p.getMapping().getComparison();
    for (IMatch candidate : candidates) {
      if (isAMove(candidate, false, false) &&
          comparison.getContainerOf(candidate, getDrivingRole().opposite()) == match_p)
        continue; // Move origin
      if (getDifferenceNumber(candidate) > 0)
        return true;
    }
    return false;
  }
  
  /**
   * Increment the number of differences by the given increment for the given match
   * @param match_p a non-null match
   * @param increment_p a positive int
   */
  protected void incrementDifferenceNumbers(EMatch match_p, int increment_p) {
    int currentNb = getDifferenceNumber(match_p);
    Integer newNb = Integer.valueOf(increment_p + currentNb);
    getMatchToNb().put(match_p, newNb);
  }
  
  /**
   * Increment the number of differences by the given increment for the given match
   * and its parents according to the driving role
   * @param match_p a non-null match
   * @param increment_p a positive int
   */
  protected void incrementDifferenceNumbersInHierarchy(EMatch match_p, int increment_p) {
    if (increment_p > 0) {
      incrementDifferenceNumbers(match_p, increment_p);
      EMatch current = getContainerOf(match_p);
//      Role uncovered = match_p.getUncoveredRole();
//      if (current != null && (uncovered == null || current.getUncoveredRole() != uncovered)) {
        while (current != null) {
          incrementDifferenceNumbers(current, increment_p);
          current = getContainerOf(current);
        }
//      }
    }
  }
  
  /**
   * Return whether the given match corresponds to a moved element
   * @param match_p a non-null match
   * @param considerMerged_p whether even a merged move must be considered
   * @param considerIgnored_p whether even an ignored move must be considered
   */
  public boolean isAMove(IMatch match_p, boolean considerMerged_p, boolean considerIgnored_p) {
    boolean result = false;
    if (!match_p.isPartial()) {
      IReferenceValuePresence onTarget = match_p.getOwnershipDifference(Role.TARGET);
      IReferenceValuePresence onReference = match_p.getOwnershipDifference(Role.REFERENCE);
      result =
        (onTarget != null && (considerMerged_p || !onTarget.isMerged()) &&
            (considerIgnored_p || !getUIComparison().getDifferencesToIgnore().contains(onTarget))) ||
        (onReference != null && (considerMerged_p || !onReference.isMerged()) &&
            (considerIgnored_p || !getUIComparison().getDifferencesToIgnore().contains(onReference)));
    }
    return result;
  }
  
  /**
   * Return whether the are still differences that the user has to handle
   */
  public boolean isEmpty() {
    IComparison comparison = getActualComparison();
    if (comparison != null) {
      for (IMatch match : comparison.getMapping().getContents()) {
        for (IDifference difference : match.getAllDifferences()) {
          if (!shouldBeIgnored(difference))
            return false;
        }
      }
    }
    return true;
  }
  
  /**
   * Return whether differences are being filtered
   */
  public boolean isFiltering() {
    return UserDifferenceKind.values().length != _countedKinds.size();
  }
  
  /**
   * Return whether the given difference must be considered as a presence with no
   * multiplicity constraint
   * @param presence_p a non-null difference
   */
  public boolean isMany(IPresenceDifference presence_p) {
    boolean result = true;
    if (presence_p instanceof IValuePresence) {
      IValuePresence valuePresence = (IValuePresence)presence_p;
      EStructuralFeature feature = valuePresence.getFeature();
      result = (feature == null || feature.isMany()) && !valuePresence.isOrder();
    }
    return result;
  }
  
  /**
   * Return whether the given path corresponds to a moved element on the side of
   * the source of the move
   * @param path_p a non-null path
   */
  public boolean isMoveOrigin(TreePath path_p) {
    boolean result = false;
    IMatch end = (IMatch)path_p.getLastSegment();
    if (end != null && isAMove(end, false, true)) {
      TreePath parentPath = path_p.getParentPath();
      IMatch father =
        parentPath == null? null: (IMatch)parentPath.getLastSegment();
      IComparison comparison = end.getMapping().getComparison();
      result = comparison.getContainerOf(end, getDrivingRole().opposite()) == father;
    }
    return result;
  }
  
  /**
   * Return whether the given difference is conceptually the opposite to ownership
   * @param difference_p a non-null difference
   */
  public boolean isOwnershipOpposite(IDifference difference_p) {
    boolean result = false;
    if (difference_p instanceof IReferenceValuePresence) {
      IReferenceValuePresence valuePresence = (IReferenceValuePresence)difference_p;
      EReference ref = valuePresence.getFeature();
      result = ref == null || ref.isContainment();
    }
    return result;
  }
  
  /**
   * Return whether this comparison is 3-way
   */
  public boolean isThreeWay() {
    return getActualComparison().isThreeWay();
  }
  
  /**
   * Return whether the given match is modified in the comparison
   * @param match_p a non-null match
   */
  public boolean representAsModification(IMatch match_p) {
    if (!counts(UserDifferenceKind.PROPER) || match_p.getElementPresenceDifference() != null)
      return false;
    for (IDifference diff : match_p.getRelatedDifferences()) {
      if (representAsModificationDifference(diff))
        return true;
    }
    return false;
  }
  
  /**
   * Return whether the given difference can be considered as a modification
   * of the corresponding element
   * @param diff_p a non-null difference
   */
  protected boolean representAsModificationDifference(IDifference diff_p) {
    boolean result = false;
    if (diff_p instanceof IElementRelativePresence) {
      IElementRelativePresence presence = (IElementRelativePresence)diff_p;
      return presence.isProperToElement() && !shouldBeIgnored(diff_p);
    }
    return result;
  }
  
  /**
   * Return whether the given match is represented as a move in the comparison
   * @param match_p a non-null match
   */
  public boolean representAsMove(IMatch match_p) {
    boolean result = false;
    if (counts(UserDifferenceKind.MOVE) && !match_p.isPartial()) {
      IReferenceValuePresence drivingOwnership = match_p.getOwnershipDifference(
          getDrivingRole());
      IReferenceValuePresence nonDrivingOwnership = match_p.getOwnershipDifference(
          getDrivingRole().opposite());
      result = drivingOwnership != null && !shouldBeIgnored(drivingOwnership) ||
        nonDrivingOwnership != null && !shouldBeIgnored(nonDrivingOwnership);
    }
    return result;
  }
  
  /**
   * Return whether the given path is represented as a moved element on the side of
   * the source of the move
   * @param path_p a non-null path
   */
  public boolean representAsMoveOrigin(TreePath path_p) {
    IMatch end = (IMatch)path_p.getLastSegment();
    return representAsMove(end) && isMoveOrigin(path_p);
  }
  
  /**
   * Return whether the given match contains differences to represent from the user's point of view
   * @param match_p a non-null match
   */
  public boolean representAsUserDifference(IMatch match_p) {
    DifferenceKind kind = getDifferenceKind(match_p);
    boolean result = kind != DifferenceKind.NONE &&
      kind != DifferenceKind.COUNTED;
    return result;
  }
  
  /**
   * Return whether the given path contains differences to represent from the user's point of view
   * @param path_p a non-null path
   */
  public boolean representAsUserDifference(TreePath path_p) {
    boolean result = false;
    IMatch end = (IMatch)path_p.getLastSegment();
    if (end != null)
      result = representAsUserDifference(end) && !representAsMoveOrigin(path_p);
    return result;
  }
  
  /**
   * Specify whether the given difference kind should be counted
   * @param kind_p a non-null difference kind
   * @param count_p whether kind_p should be counted
   */
  public void setCount(UserDifferenceKind kind_p, boolean count_p) {
    if (count_p)
      _countedKinds.add(kind_p);
    else
      _countedKinds.remove(kind_p);
  }
  
  /**
   * Set whether this viewer should use custom icons to represent differences
   */
  public void setUseCustomIcons(boolean useCustom_p) {
    _useCustomIcons = useCustom_p;
  }
  
  /**
   * Return whether the given difference should be ignored
   * @param difference_p a non-null difference
   */
  public boolean shouldBeIgnored(IDifference difference_p) {
    return
      difference_p.isMerged() ||
      getUIComparison().getDifferencesToIgnore().contains(difference_p) ||
      (difference_p instanceof IValuePresence &&
        ((IValuePresence)difference_p).isProperToElement() && !counts(UserDifferenceKind.PROPER)) ||
      difference_p instanceof IElementPresence &&
        (((IElementPresence)difference_p).getPresenceRole() == getDrivingRole() &&
            !counts(UserDifferenceKind.PRESENCE_LEFT) ||
         ((IElementPresence)difference_p).getPresenceRole() == getDrivingRole().opposite() &&
            !counts(UserDifferenceKind.PRESENCE_RIGHT));
  }
  
  /**
   * Re-compute the number of differences per match
   */
  public void updateDifferenceNumbers() {
    getMatchToNb().clear();
    for (IMatch match : getActualComparison().getMapping().getContents()) {
      int nb = countDifferenceNumber(match);
      incrementDifferenceNumbersInHierarchy((EMatch)match, nb);
    }
  }
  
  /**
   * Set whether this viewer should use custom labels to represent differences
   */
  public void setUseCustomLabels(boolean useCustom_p) {
    _useCustomLabels = useCustom_p;
  }
  
  /**
   * Return whether this viewer uses custom icons to represent differences
   */
  public boolean usesCustomIcons() {
    return _useCustomIcons;
  }
  
  /**
   * Return whether this viewer uses custom labels to represent differences
   */
  public boolean usesCustomLabels() {
    return _useCustomLabels;
  }
  
}