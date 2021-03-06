/*********************************************************************
 * Copyright (c) 2019 Thales Global Services S.A.S.
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Thales Global Services S.A.S. - initial API and implementation
 **********************************************************************/
package org.eclipse.emf.diffmerge.pojo.jdiffdata.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.diffmerge.generic.api.diff.IAttributeValuePresence;
import org.eclipse.emf.diffmerge.generic.api.diff.IReferenceValuePresence;
import org.eclipse.emf.diffmerge.generic.gdiffdata.impl.GMatchImpl;
import org.eclipse.emf.diffmerge.pojo.jdiffdata.JComparison;
import org.eclipse.emf.diffmerge.pojo.jdiffdata.JMapping;
import org.eclipse.emf.diffmerge.pojo.jdiffdata.JMatch;
import org.eclipse.emf.diffmerge.pojo.jdiffdata.JdiffdataPackage;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>GMatch</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.emf.diffmerge.pojo.jdiffdata.impl.JMatchImpl#getAncestor <em>Ancestor</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.pojo.jdiffdata.impl.JMatchImpl#getReference <em>Reference</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.pojo.jdiffdata.impl.JMatchImpl#getTarget <em>Target</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.pojo.jdiffdata.impl.JMatchImpl#getModifiableAttributeMap <em>Modifiable Attribute Map</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.pojo.jdiffdata.impl.JMatchImpl#getModifiableReferenceMap <em>Modifiable Reference Map</em>}</li>
 *   <li>{@link org.eclipse.emf.diffmerge.pojo.jdiffdata.impl.JMatchImpl#getModifiableOrderReferenceMap <em>Modifiable Order Reference Map</em>}</li>
 * </ul>
 *
 * @generated
 */
public class JMatchImpl<E extends Object> extends GMatchImpl<E, Object, Object>
    implements JMatch<E> {
  /**
   * The cached value of the '{@link #getAncestor() <em>Ancestor</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAncestor()
   * @generated
   * @ordered
   */
  protected E ancestor;

  /**
   * The cached value of the '{@link #getReference() <em>Reference</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getReference()
   * @generated
   * @ordered
   */
  protected E reference;

  /**
   * The cached value of the '{@link #getTarget() <em>Target</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTarget()
   * @generated
   * @ordered
   */
  protected E target;

  /**
   * The cached value of the '{@link #getModifiableAttributeMap() <em>Modifiable Attribute Map</em>}' map.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getModifiableAttributeMap()
   * @generated
   * @ordered
   */
  protected EMap<Object, EList<IAttributeValuePresence<?>>> modifiableAttributeMap;

  /**
   * The cached value of the '{@link #getModifiableReferenceMap() <em>Modifiable Reference Map</em>}' map.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getModifiableReferenceMap()
   * @generated
   * @ordered
   */
  protected EMap<Object, EMap<Object, IReferenceValuePresence<?>>> modifiableReferenceMap;

  /**
   * The cached value of the '{@link #getModifiableOrderReferenceMap() <em>Modifiable Order Reference Map</em>}' map.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getModifiableOrderReferenceMap()
   * @generated
   * @ordered
   */
  protected EMap<Object, EList<IReferenceValuePresence<?>>> modifiableOrderReferenceMap;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected JMatchImpl() {
    super();
  }

  /**
   * Constructor
   * Precondition: at least one of the given elements is not null.
   * @param target_p the optional element on the TARGET side
   * @param reference_p the optional element on the REFERENCE side
   * @param ancestor_p the optional element on the ANCESTOR side
   * @generated NOT
   */
  public JMatchImpl(E target_p, E reference_p, E ancestor_p) {
    super(target_p, reference_p, ancestor_p);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass() {
    return JdiffdataPackage.Literals.JMATCH;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EMap<Object, EList<IAttributeValuePresence<?>>> getModifiableAttributeMap() {
    if (modifiableAttributeMap == null) {
      modifiableAttributeMap = new EcoreEMap<Object, EList<IAttributeValuePresence<?>>>(
          JdiffdataPackage.Literals.ATTRIBUTE_TO_DIFFERENCE_ENTRY,
          AttributeToDifferenceEntryImpl.class, this,
          JdiffdataPackage.JMATCH__MODIFIABLE_ATTRIBUTE_MAP);
    }
    return modifiableAttributeMap;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EMap<Object, EMap<Object, IReferenceValuePresence<?>>> getModifiableReferenceMap() {
    if (modifiableReferenceMap == null) {
      modifiableReferenceMap = new EcoreEMap<Object, EMap<Object, IReferenceValuePresence<?>>>(
          JdiffdataPackage.Literals.REFERENCE_TO_ELEMENT_TO_DIFFERENCE_ENTRY,
          ReferenceToElementToDifferenceEntryImpl.class, this,
          JdiffdataPackage.JMATCH__MODIFIABLE_REFERENCE_MAP);
    }
    return modifiableReferenceMap;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EMap<Object, EList<IReferenceValuePresence<?>>> getModifiableOrderReferenceMap() {
    if (modifiableOrderReferenceMap == null) {
      modifiableOrderReferenceMap = new EcoreEMap<Object, EList<IReferenceValuePresence<?>>>(
          JdiffdataPackage.Literals.REFERENCE_TO_ORDER_DIFFERENCE_ENTRY,
          ReferenceToOrderDifferenceEntryImpl.class, this,
          JdiffdataPackage.JMATCH__MODIFIABLE_ORDER_REFERENCE_MAP);
    }
    return modifiableOrderReferenceMap;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public E getAncestor() {
    return ancestor;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void setAncestor(E newAncestor) {
    E oldAncestor = ancestor;
    ancestor = newAncestor;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET,
          JdiffdataPackage.JMATCH__ANCESTOR, oldAncestor, ancestor));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public E getReference() {
    return reference;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void setReference(E newReference) {
    E oldReference = reference;
    reference = newReference;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET,
          JdiffdataPackage.JMATCH__REFERENCE, oldReference, reference));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public E getTarget() {
    return target;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void setTarget(E newTarget) {
    E oldTarget = target;
    target = newTarget;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET,
          JdiffdataPackage.JMATCH__TARGET, oldTarget, target));
  }

  /**
   * @generated NOT
   */
  @Override
  public JMapping<E> getMapping() {
    return (JMapping<E>) super.getMapping();
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.GMatchImpl#getComparison()
   * @generated NOT
   */
  @Override
  public JComparison<E> getComparison() {
    return (JComparison<E>) super.getComparison();
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
    case JdiffdataPackage.JMATCH__MODIFIABLE_ATTRIBUTE_MAP:
      return ((InternalEList<?>) getModifiableAttributeMap())
          .basicRemove(otherEnd, msgs);
    case JdiffdataPackage.JMATCH__MODIFIABLE_REFERENCE_MAP:
      return ((InternalEList<?>) getModifiableReferenceMap())
          .basicRemove(otherEnd, msgs);
    case JdiffdataPackage.JMATCH__MODIFIABLE_ORDER_REFERENCE_MAP:
      return ((InternalEList<?>) getModifiableOrderReferenceMap())
          .basicRemove(otherEnd, msgs);
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
    case JdiffdataPackage.JMATCH__ANCESTOR:
      return getAncestor();
    case JdiffdataPackage.JMATCH__REFERENCE:
      return getReference();
    case JdiffdataPackage.JMATCH__TARGET:
      return getTarget();
    case JdiffdataPackage.JMATCH__MODIFIABLE_ATTRIBUTE_MAP:
      if (coreType)
        return getModifiableAttributeMap();
      else
        return getModifiableAttributeMap().map();
    case JdiffdataPackage.JMATCH__MODIFIABLE_REFERENCE_MAP:
      if (coreType)
        return getModifiableReferenceMap();
      else
        return getModifiableReferenceMap().map();
    case JdiffdataPackage.JMATCH__MODIFIABLE_ORDER_REFERENCE_MAP:
      if (coreType)
        return getModifiableOrderReferenceMap();
      else
        return getModifiableOrderReferenceMap().map();
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
    case JdiffdataPackage.JMATCH__ANCESTOR:
      setAncestor((E) newValue);
      return;
    case JdiffdataPackage.JMATCH__REFERENCE:
      setReference((E) newValue);
      return;
    case JdiffdataPackage.JMATCH__TARGET:
      setTarget((E) newValue);
      return;
    case JdiffdataPackage.JMATCH__MODIFIABLE_ATTRIBUTE_MAP:
      ((EStructuralFeature.Setting) getModifiableAttributeMap()).set(newValue);
      return;
    case JdiffdataPackage.JMATCH__MODIFIABLE_REFERENCE_MAP:
      ((EStructuralFeature.Setting) getModifiableReferenceMap()).set(newValue);
      return;
    case JdiffdataPackage.JMATCH__MODIFIABLE_ORDER_REFERENCE_MAP:
      ((EStructuralFeature.Setting) getModifiableOrderReferenceMap())
          .set(newValue);
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
    case JdiffdataPackage.JMATCH__ANCESTOR:
      setAncestor((E) null);
      return;
    case JdiffdataPackage.JMATCH__REFERENCE:
      setReference((E) null);
      return;
    case JdiffdataPackage.JMATCH__TARGET:
      setTarget((E) null);
      return;
    case JdiffdataPackage.JMATCH__MODIFIABLE_ATTRIBUTE_MAP:
      getModifiableAttributeMap().clear();
      return;
    case JdiffdataPackage.JMATCH__MODIFIABLE_REFERENCE_MAP:
      getModifiableReferenceMap().clear();
      return;
    case JdiffdataPackage.JMATCH__MODIFIABLE_ORDER_REFERENCE_MAP:
      getModifiableOrderReferenceMap().clear();
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
    case JdiffdataPackage.JMATCH__ANCESTOR:
      return ancestor != null;
    case JdiffdataPackage.JMATCH__REFERENCE:
      return reference != null;
    case JdiffdataPackage.JMATCH__TARGET:
      return target != null;
    case JdiffdataPackage.JMATCH__MODIFIABLE_ATTRIBUTE_MAP:
      return modifiableAttributeMap != null
          && !modifiableAttributeMap.isEmpty();
    case JdiffdataPackage.JMATCH__MODIFIABLE_REFERENCE_MAP:
      return modifiableReferenceMap != null
          && !modifiableReferenceMap.isEmpty();
    case JdiffdataPackage.JMATCH__MODIFIABLE_ORDER_REFERENCE_MAP:
      return modifiableOrderReferenceMap != null
          && !modifiableOrderReferenceMap.isEmpty();
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
    result.append(" (ancestor: "); //$NON-NLS-1$
    result.append(ancestor);
    result.append(", reference: "); //$NON-NLS-1$
    result.append(reference);
    result.append(", target: "); //$NON-NLS-1$
    result.append(target);
    result.append(')');
    return result.toString();
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.GMatchImpl#getModifiableAttributeMap(boolean)
   * @generated NOT
   */
  @Override
  protected EMap<Object, EList<IAttributeValuePresence<?>>> getModifiableAttributeMap(
      boolean create_p) {
    return create_p ? getModifiableAttributeMap() : modifiableAttributeMap;
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.GMatchImpl#getModifiableOrderReferenceMap(boolean)
   * @generated NOT
   */
  @Override
  protected EMap<Object, EList<IReferenceValuePresence<?>>> getModifiableOrderReferenceMap(
      boolean create_p) {
    return create_p ? getModifiableOrderReferenceMap()
        : modifiableOrderReferenceMap;
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.GMatchImpl#getModifiableReferenceMap(boolean)
   * @generated NOT
   */
  @Override
  protected EMap<Object, EMap<Object, IReferenceValuePresence<?>>> getModifiableReferenceMap(
      boolean create_p) {
    return create_p ? getModifiableReferenceMap() : modifiableReferenceMap;
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.GMatchImpl#newAttributeValuePresenceList(java.lang.Object)
   * @generated NOT
   */
  @Override
  @SuppressWarnings({ "rawtypes", "unchecked" })
  protected EList<IAttributeValuePresence<E>> newAttributeValuePresenceList(
      Object attribute_p) {
    AttributeToDifferenceEntryImpl entry = new AttributeToDifferenceEntryImpl();
    entry.setKey(attribute_p);
    getModifiableAttributeMap(true).add(entry);
    return (EList) entry.getValue();
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.GMatchImpl#newReferenceOrderDifferenceList(java.lang.Object)
   * @generated NOT
   */
  @Override
  @SuppressWarnings({ "rawtypes", "unchecked" })
  protected EList<IReferenceValuePresence<E>> newReferenceOrderDifferenceList(
      Object reference_p) {
    ReferenceToOrderDifferenceEntryImpl entry = new ReferenceToOrderDifferenceEntryImpl();
    entry.setKey(reference_p);
    getModifiableOrderReferenceMap(true).add(entry);
    return (EList) entry.getValue();
  }

  /**
   * @see org.eclipse.emf.diffmerge.generic.gdiffdata.impl.GMatchImpl#newReferenceValueToPresenceMap(java.lang.Object)
   * @generated NOT
   */
  @Override
  @SuppressWarnings({ "rawtypes", "unchecked" })
  protected EMap<E, IReferenceValuePresence<E>> newReferenceValueToPresenceMap(
      Object reference_p) {
    ReferenceToElementToDifferenceEntryImpl entry = new ReferenceToElementToDifferenceEntryImpl();
    entry.setKey(reference_p);
    getModifiableReferenceMap(true).add(entry);
    return (EMap) entry.getValue();
  }

} //GMatchImpl
