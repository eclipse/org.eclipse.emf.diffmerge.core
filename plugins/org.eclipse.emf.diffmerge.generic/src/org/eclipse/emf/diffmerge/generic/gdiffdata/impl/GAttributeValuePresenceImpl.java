/*********************************************************************
 * Copyright (c) 2010-2019 Thales Global Services S.A.S.
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
import org.eclipse.emf.diffmerge.generic.api.IDiffPolicy;
import org.eclipse.emf.diffmerge.generic.api.IMatch;
import org.eclipse.emf.diffmerge.generic.api.Role;
import org.eclipse.emf.diffmerge.generic.api.diff.IAttributeValuePresence;
import org.eclipse.emf.diffmerge.generic.api.scopes.IEditableTreeDataScope;
import org.eclipse.emf.diffmerge.generic.gdiffdata.GAttributeValuePresence;
import org.eclipse.emf.diffmerge.generic.gdiffdata.GMatch;
import org.eclipse.emf.diffmerge.generic.gdiffdata.GdiffdataPackage;
import org.eclipse.emf.ecore.EClass;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>EAttribute Value Presence</b></em>'.
 * <!-- end-user-doc -->
 *
 * @generated
 */
public abstract class GAttributeValuePresenceImpl<E, A, R> extends
    GValuePresenceImpl<E, A, R> implements GAttributeValuePresence<E, A, R> {
  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected GAttributeValuePresenceImpl() {
    super();
  }

  /**
   * Constructor
   * @param elementMatch_p the non-null match for the element holding the value
   * @param attribute_p the non-null attribute holding the value
   * @param value_p the non-null value held
   * @param presenceRole_p the role in which the value is held: TARGET or REFERENCE
   * @param isOrder_p whether the value presence is solely due to ordering
   * @generated NOT
   */
  public GAttributeValuePresenceImpl(GMatch<E, A, R> elementMatch_p,
      A attribute_p, Object value_p, Role presenceRole_p, boolean isOrder_p) {
    super(elementMatch_p, presenceRole_p, isOrder_p);
    setAttribute(attribute_p);
    setValue(value_p);
    ((IMatch.Editable<E>) elementMatch).addRelatedDifference(this);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass() {
    return GdiffdataPackage.Literals.GATTRIBUTE_VALUE_PRESENCE;
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.GAttributeValuePresence#setValue(java.lang.Object)
   * @generated NOT
   */
  public abstract void setValue(Object value);

  /**
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.GValuePresenceImpl#getFeature()
   * @generated NOT
   */
  @Override
  public abstract A getFeature();

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public abstract void setAttribute(A attribute);

  /**
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.GValuePresenceImpl#getSymmetrical()
   * @generated NOT
   */
  @Override
  public IAttributeValuePresence<E> getSymmetrical() {
    IAttributeValuePresence<E> result = null;
    if (!isManyFeature()) {
      Collection<IAttributeValuePresence<E>> candidates = getElementMatch()
          .getAttributeDifferences(getFeature());
      assert candidates.size() <= 2; // Because !isMany()
      for (IAttributeValuePresence<E> candidate : candidates) {
        if (candidate.getPresenceRole() == getAbsenceRole()) {
          result = candidate;
          break;
        }
      }
    } else if (isOrder()) {
      result = getElementMatch().getAttributeOrderDifference(getFeature(),
          getAbsenceRole());
    }
    return result;
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.api.diff.IValuePresence#isChangeableFeature()
   * @generated NOT
   */
  public boolean isChangeableFeature() {
    return getPresenceScope().mIsChangeableAttribute(getFeature());
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.GValuePresenceImpl#isManyFeature()
   * @generated NOT
   */
  @Override
  public boolean isManyFeature() {
    return getPresenceScope().mIsManyAttribute(getFeature());
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.api.diff.IElementRelativeDifference#isUnrelatedToContainmentTree()
   * @generated NOT
   */
  public boolean isUnrelatedToContainmentTree() {
    return true;
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.GValuePresenceImpl#mergeOrder()
   * @generated NOT
   */
  @Override
  protected void mergeOrder() {
    // TODO Implement
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.GValuePresenceImpl#mergeValueAddition()
   * @generated NOT
   */
  @Override
  protected void mergeValueAddition() {
    IEditableTreeDataScope<E> absenceScope = getAbsenceScope();
    E holderMatch = getMatchOfHolder();
    assert holderMatch != null; // Must be guaranteed by diff dependency handling
    absenceScope.addAttributeValue(holderMatch, getFeature(), getValue());
    IDiffPolicy<E> diffPolicy = getComparison().getLastDiffPolicy();
    if (diffPolicy != null && diffPolicy.considerOrderedAttribute(getFeature(),
        getPresenceScope())) {
      // TODO Implement
    }
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.GValuePresenceImpl#mergeValueRemoval()
   * @generated NOT
   */
  @Override
  public void mergeValueRemoval() {
    IEditableTreeDataScope<E> presenceScope = getPresenceScope();
    presenceScope.removeAttributeValue(getHolder(), getFeature(), getValue());
  }

} //GAttributeValuePresenceImpl
