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
package org.eclipse.emf.diffmerge.pojo.pojodiffdata.util;

import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;

import org.eclipse.emf.diffmerge.generic.api.IComparison.Editable;

import org.eclipse.emf.diffmerge.generic.api.diff.IAttributeValuePresence;
import org.eclipse.emf.diffmerge.generic.api.diff.IElementPresence;
import org.eclipse.emf.diffmerge.generic.api.diff.IElementRelativePresence;
import org.eclipse.emf.diffmerge.generic.api.diff.IReferenceValuePresence;
import org.eclipse.emf.diffmerge.generic.api.diff.IValuePresence;

import org.eclipse.emf.diffmerge.generic.gdiffdata.EIdentified;

import org.eclipse.emf.diffmerge.pojo.pojodiffdata.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see org.eclipse.emf.diffmerge.pojo.pojodiffdata.PojodiffdataPackage
 * @generated
 */
public class PojodiffdataSwitch<T> {
  /**
   * The cached model package
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected static PojodiffdataPackage modelPackage;

  /**
   * Creates an instance of the switch.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public PojodiffdataSwitch() {
    if (modelPackage == null) {
      modelPackage = PojodiffdataPackage.eINSTANCE;
    }
  }

  /**
   * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the first non-null result returned by a <code>caseXXX</code> call.
   * @generated
   */
  public T doSwitch(EObject theEObject) {
    return doSwitch(theEObject.eClass(), theEObject);
  }

  /**
   * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the first non-null result returned by a <code>caseXXX</code> call.
   * @generated
   */
  protected T doSwitch(EClass theEClass, EObject theEObject) {
    if (theEClass.eContainer() == modelPackage) {
      return doSwitch(theEClass.getClassifierID(), theEObject);
    } else {
      List<EClass> eSuperTypes = theEClass.getESuperTypes();
      return eSuperTypes.isEmpty() ? defaultCase(theEObject)
          : doSwitch(eSuperTypes.get(0), theEObject);
    }
  }

  /**
   * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the first non-null result returned by a <code>caseXXX</code> call.
   * @generated
   */
  protected T doSwitch(int classifierID, EObject theEObject) {
    switch (classifierID) {
    case PojodiffdataPackage.ECOMPARISON: {
      EComparison eComparison = (EComparison) theEObject;
      T result = caseEComparison(eComparison);
      if (result == null)
        result = caseGdiffdata_EComparison(eComparison);
      if (result == null)
        result = caseEIdentified(eComparison);
      if (result == null)
        result = caseIEditableComparison(eComparison);
      if (result == null)
        result = defaultCase(theEObject);
      return result;
    }
    case PojodiffdataPackage.ECOMPARISON_ELEMENT: {
      EComparisonElement eComparisonElement = (EComparisonElement) theEObject;
      T result = caseEComparisonElement(eComparisonElement);
      if (result == null)
        result = caseGdiffdata_EComparisonElement(eComparisonElement);
      if (result == null)
        result = defaultCase(theEObject);
      return result;
    }
    case PojodiffdataPackage.EMAPPING: {
      EMapping eMapping = (EMapping) theEObject;
      T result = caseEMapping(eMapping);
      if (result == null)
        result = caseGdiffdata_EMapping(eMapping);
      if (result == null)
        result = caseEComparisonElement(eMapping);
      if (result == null)
        result = caseEIdentified(eMapping);
      if (result == null)
        result = caseGdiffdata_EComparisonElement(eMapping);
      if (result == null)
        result = caseIEditableMapping(eMapping);
      if (result == null)
        result = defaultCase(theEObject);
      return result;
    }
    case PojodiffdataPackage.EMATCH: {
      EMatch eMatch = (EMatch) theEObject;
      T result = caseEMatch(eMatch);
      if (result == null)
        result = caseGdiffdata_EMatch(eMatch);
      if (result == null)
        result = caseEComparisonElement(eMatch);
      if (result == null)
        result = caseEIdentified(eMatch);
      if (result == null)
        result = caseGdiffdata_EComparisonElement(eMatch);
      if (result == null)
        result = caseIEditableMatch(eMatch);
      if (result == null)
        result = defaultCase(theEObject);
      return result;
    }
    case PojodiffdataPackage.EMERGEABLE_DIFFERENCE: {
      EMergeableDifference eMergeableDifference = (EMergeableDifference) theEObject;
      T result = caseEMergeableDifference(eMergeableDifference);
      if (result == null)
        result = caseGdiffdata_EMergeableDifference(eMergeableDifference);
      if (result == null)
        result = caseEComparisonElement(eMergeableDifference);
      if (result == null)
        result = caseEIdentified(eMergeableDifference);
      if (result == null)
        result = caseGdiffdata_EComparisonElement(eMergeableDifference);
      if (result == null)
        result = caseIEditableMergeableDifference(eMergeableDifference);
      if (result == null)
        result = defaultCase(theEObject);
      return result;
    }
    case PojodiffdataPackage.EELEMENT_RELATIVE_PRESENCE: {
      EElementRelativePresence eElementRelativePresence = (EElementRelativePresence) theEObject;
      T result = caseEElementRelativePresence(eElementRelativePresence);
      if (result == null)
        result = caseGdiffdata_EElementRelativePresence(
            eElementRelativePresence);
      if (result == null)
        result = caseEMergeableDifference(eElementRelativePresence);
      if (result == null)
        result = caseGdiffdata_EMergeableDifference(eElementRelativePresence);
      if (result == null)
        result = caseIElementRelativePresence(eElementRelativePresence);
      if (result == null)
        result = caseEComparisonElement(eElementRelativePresence);
      if (result == null)
        result = caseEIdentified(eElementRelativePresence);
      if (result == null)
        result = caseGdiffdata_EComparisonElement(eElementRelativePresence);
      if (result == null)
        result = caseIEditableMergeableDifference(eElementRelativePresence);
      if (result == null)
        result = defaultCase(theEObject);
      return result;
    }
    case PojodiffdataPackage.EELEMENT_PRESENCE: {
      EElementPresence eElementPresence = (EElementPresence) theEObject;
      T result = caseEElementPresence(eElementPresence);
      if (result == null)
        result = caseGdiffdata_EElementPresence(eElementPresence);
      if (result == null)
        result = caseEElementRelativePresence(eElementPresence);
      if (result == null)
        result = caseGdiffdata_EElementRelativePresence(eElementPresence);
      if (result == null)
        result = caseIElementPresence(eElementPresence);
      if (result == null)
        result = caseEMergeableDifference(eElementPresence);
      if (result == null)
        result = caseGdiffdata_EMergeableDifference(eElementPresence);
      if (result == null)
        result = caseIElementRelativePresence(eElementPresence);
      if (result == null)
        result = caseEComparisonElement(eElementPresence);
      if (result == null)
        result = caseEIdentified(eElementPresence);
      if (result == null)
        result = caseGdiffdata_EComparisonElement(eElementPresence);
      if (result == null)
        result = caseIEditableMergeableDifference(eElementPresence);
      if (result == null)
        result = defaultCase(theEObject);
      return result;
    }
    case PojodiffdataPackage.EVALUE_PRESENCE: {
      EValuePresence eValuePresence = (EValuePresence) theEObject;
      T result = caseEValuePresence(eValuePresence);
      if (result == null)
        result = caseGdiffdata_EValuePresence(eValuePresence);
      if (result == null)
        result = caseEElementRelativePresence(eValuePresence);
      if (result == null)
        result = caseGdiffdata_EElementRelativePresence(eValuePresence);
      if (result == null)
        result = caseIValuePresence(eValuePresence);
      if (result == null)
        result = caseEMergeableDifference(eValuePresence);
      if (result == null)
        result = caseGdiffdata_EMergeableDifference(eValuePresence);
      if (result == null)
        result = caseIElementRelativePresence(eValuePresence);
      if (result == null)
        result = caseEComparisonElement(eValuePresence);
      if (result == null)
        result = caseEIdentified(eValuePresence);
      if (result == null)
        result = caseGdiffdata_EComparisonElement(eValuePresence);
      if (result == null)
        result = caseIEditableMergeableDifference(eValuePresence);
      if (result == null)
        result = defaultCase(theEObject);
      return result;
    }
    case PojodiffdataPackage.EATTRIBUTE_VALUE_PRESENCE: {
      EAttributeValuePresence eAttributeValuePresence = (EAttributeValuePresence) theEObject;
      T result = caseEAttributeValuePresence(eAttributeValuePresence);
      if (result == null)
        result = caseGdiffdata_EAttributeValuePresence(eAttributeValuePresence);
      if (result == null)
        result = caseEValuePresence(eAttributeValuePresence);
      if (result == null)
        result = caseGdiffdata_EValuePresence(eAttributeValuePresence);
      if (result == null)
        result = caseIAttributeValuePresence(eAttributeValuePresence);
      if (result == null)
        result = caseEElementRelativePresence(eAttributeValuePresence);
      if (result == null)
        result = caseGdiffdata_EElementRelativePresence(
            eAttributeValuePresence);
      if (result == null)
        result = caseIValuePresence(eAttributeValuePresence);
      if (result == null)
        result = caseEMergeableDifference(eAttributeValuePresence);
      if (result == null)
        result = caseGdiffdata_EMergeableDifference(eAttributeValuePresence);
      if (result == null)
        result = caseIElementRelativePresence(eAttributeValuePresence);
      if (result == null)
        result = caseEComparisonElement(eAttributeValuePresence);
      if (result == null)
        result = caseEIdentified(eAttributeValuePresence);
      if (result == null)
        result = caseGdiffdata_EComparisonElement(eAttributeValuePresence);
      if (result == null)
        result = caseIEditableMergeableDifference(eAttributeValuePresence);
      if (result == null)
        result = defaultCase(theEObject);
      return result;
    }
    case PojodiffdataPackage.EREFERENCE_VALUE_PRESENCE: {
      EReferenceValuePresence eReferenceValuePresence = (EReferenceValuePresence) theEObject;
      T result = caseEReferenceValuePresence(eReferenceValuePresence);
      if (result == null)
        result = caseGdiffdata_EReferenceValuePresence(eReferenceValuePresence);
      if (result == null)
        result = caseEValuePresence(eReferenceValuePresence);
      if (result == null)
        result = caseGdiffdata_EValuePresence(eReferenceValuePresence);
      if (result == null)
        result = caseIReferenceValuePresence(eReferenceValuePresence);
      if (result == null)
        result = caseEElementRelativePresence(eReferenceValuePresence);
      if (result == null)
        result = caseGdiffdata_EElementRelativePresence(
            eReferenceValuePresence);
      if (result == null)
        result = caseIValuePresence(eReferenceValuePresence);
      if (result == null)
        result = caseEMergeableDifference(eReferenceValuePresence);
      if (result == null)
        result = caseGdiffdata_EMergeableDifference(eReferenceValuePresence);
      if (result == null)
        result = caseIElementRelativePresence(eReferenceValuePresence);
      if (result == null)
        result = caseEComparisonElement(eReferenceValuePresence);
      if (result == null)
        result = caseEIdentified(eReferenceValuePresence);
      if (result == null)
        result = caseGdiffdata_EComparisonElement(eReferenceValuePresence);
      if (result == null)
        result = caseIEditableMergeableDifference(eReferenceValuePresence);
      if (result == null)
        result = defaultCase(theEObject);
      return result;
    }
    case PojodiffdataPackage.ATTRIBUTE_TO_DIFFERENCE_ENTRY: {
      @SuppressWarnings("unchecked")
      Map.Entry<Object, EList<IAttributeValuePresence<Object>>> attributeToDifferenceEntry = (Map.Entry<Object, EList<IAttributeValuePresence<Object>>>) theEObject;
      T result = caseAttributeToDifferenceEntry(attributeToDifferenceEntry);
      if (result == null)
        result = defaultCase(theEObject);
      return result;
    }
    case PojodiffdataPackage.REFERENCE_TO_ELEMENT_TO_DIFFERENCE_ENTRY: {
      @SuppressWarnings("unchecked")
      Map.Entry<Object, EMap<Object, IReferenceValuePresence<Object>>> referenceToElementToDifferenceEntry = (Map.Entry<Object, EMap<Object, IReferenceValuePresence<Object>>>) theEObject;
      T result = caseReferenceToElementToDifferenceEntry(
          referenceToElementToDifferenceEntry);
      if (result == null)
        result = defaultCase(theEObject);
      return result;
    }
    case PojodiffdataPackage.REFERENCE_TO_ORDER_DIFFERENCE_ENTRY: {
      @SuppressWarnings("unchecked")
      Map.Entry<Object, EList<IReferenceValuePresence<Object>>> referenceToOrderDifferenceEntry = (Map.Entry<Object, EList<IReferenceValuePresence<Object>>>) theEObject;
      T result = caseReferenceToOrderDifferenceEntry(
          referenceToOrderDifferenceEntry);
      if (result == null)
        result = defaultCase(theEObject);
      return result;
    }
    case PojodiffdataPackage.ELEMENT_TO_DIFFERENCE_ENTRY: {
      @SuppressWarnings("unchecked")
      Map.Entry<Object, IReferenceValuePresence<Object>> elementToDifferenceEntry = (Map.Entry<Object, IReferenceValuePresence<Object>>) theEObject;
      T result = caseElementToDifferenceEntry(elementToDifferenceEntry);
      if (result == null)
        result = defaultCase(theEObject);
      return result;
    }
    case PojodiffdataPackage.ELEMENT_TO_MATCH_ENTRY: {
      @SuppressWarnings("unchecked")
      Map.Entry<Object, EMatch> elementToMatchEntry = (Map.Entry<Object, EMatch>) theEObject;
      T result = caseElementToMatchEntry(elementToMatchEntry);
      if (result == null)
        result = defaultCase(theEObject);
      return result;
    }
    default:
      return defaultCase(theEObject);
    }
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>EComparison</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>EComparison</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseEComparison(EComparison object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>EComparison Element</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>EComparison Element</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseEComparisonElement(EComparisonElement object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>EMapping</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>EMapping</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseEMapping(EMapping object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>EMatch</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>EMatch</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseEMatch(EMatch object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>EMergeable Difference</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>EMergeable Difference</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseEMergeableDifference(EMergeableDifference object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>EElement Relative Presence</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>EElement Relative Presence</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseEElementRelativePresence(EElementRelativePresence object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>EElement Presence</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>EElement Presence</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseEElementPresence(EElementPresence object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>EValue Presence</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>EValue Presence</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseEValuePresence(EValuePresence object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>EAttribute Value Presence</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>EAttribute Value Presence</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseEAttributeValuePresence(EAttributeValuePresence object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>EReference Value Presence</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>EReference Value Presence</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseEReferenceValuePresence(EReferenceValuePresence object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Attribute To Difference Entry</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Attribute To Difference Entry</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseAttributeToDifferenceEntry(
      Map.Entry<Object, EList<IAttributeValuePresence<Object>>> object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Reference To Element To Difference Entry</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Reference To Element To Difference Entry</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseReferenceToElementToDifferenceEntry(
      Map.Entry<Object, EMap<Object, IReferenceValuePresence<Object>>> object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Reference To Order Difference Entry</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Reference To Order Difference Entry</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseReferenceToOrderDifferenceEntry(
      Map.Entry<Object, EList<IReferenceValuePresence<Object>>> object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Element To Difference Entry</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Element To Difference Entry</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseElementToDifferenceEntry(
      Map.Entry<Object, IReferenceValuePresence<Object>> object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Element To Match Entry</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Element To Match Entry</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseElementToMatchEntry(Map.Entry<Object, EMatch> object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>EIdentified</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>EIdentified</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseEIdentified(EIdentified object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>IEditable Comparison</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>IEditable Comparison</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public <E> T caseIEditableComparison(Editable<E> object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>EComparison</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>EComparison</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public <E, A, R> T caseGdiffdata_EComparison(
      org.eclipse.emf.diffmerge.generic.gdiffdata.EComparison<E, A, R> object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>EComparison Element</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>EComparison Element</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public <E, A, R> T caseGdiffdata_EComparisonElement(
      org.eclipse.emf.diffmerge.generic.gdiffdata.EComparisonElement<E, A, R> object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>IEditable Mapping</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>IEditable Mapping</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public <E> T caseIEditableMapping(
      org.eclipse.emf.diffmerge.generic.api.IMapping.Editable<E> object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>EMapping</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>EMapping</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public <E, A, R> T caseGdiffdata_EMapping(
      org.eclipse.emf.diffmerge.generic.gdiffdata.EMapping<E, A, R> object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>IEditable Match</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>IEditable Match</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public <E> T caseIEditableMatch(
      org.eclipse.emf.diffmerge.generic.api.IMatch.Editable<E> object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>EMatch</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>EMatch</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public <E, A, R> T caseGdiffdata_EMatch(
      org.eclipse.emf.diffmerge.generic.gdiffdata.EMatch<E, A, R> object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>IEditable Mergeable Difference</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>IEditable Mergeable Difference</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public <E> T caseIEditableMergeableDifference(
      org.eclipse.emf.diffmerge.generic.api.diff.IMergeableDifference.Editable<E> object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>EMergeable Difference</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>EMergeable Difference</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public <E, A, R> T caseGdiffdata_EMergeableDifference(
      org.eclipse.emf.diffmerge.generic.gdiffdata.EMergeableDifference<E, A, R> object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>IElement Relative Presence</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>IElement Relative Presence</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public <E> T caseIElementRelativePresence(
      IElementRelativePresence<E> object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>EElement Relative Presence</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>EElement Relative Presence</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public <E, A, R> T caseGdiffdata_EElementRelativePresence(
      org.eclipse.emf.diffmerge.generic.gdiffdata.EElementRelativePresence<E, A, R> object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>IElement Presence</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>IElement Presence</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public <E> T caseIElementPresence(IElementPresence<E> object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>EElement Presence</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>EElement Presence</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public <E, A, R> T caseGdiffdata_EElementPresence(
      org.eclipse.emf.diffmerge.generic.gdiffdata.EElementPresence<E, A, R> object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>IValue Presence</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>IValue Presence</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public <E> T caseIValuePresence(IValuePresence<E> object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>EValue Presence</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>EValue Presence</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public <E, A, R> T caseGdiffdata_EValuePresence(
      org.eclipse.emf.diffmerge.generic.gdiffdata.EValuePresence<E, A, R> object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>IAttribute Value Presence</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>IAttribute Value Presence</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public <E> T caseIAttributeValuePresence(IAttributeValuePresence<E> object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>EAttribute Value Presence</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>EAttribute Value Presence</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public <E, A, R> T caseGdiffdata_EAttributeValuePresence(
      org.eclipse.emf.diffmerge.generic.gdiffdata.EAttributeValuePresence<E, A, R> object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>IReference Value Presence</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>IReference Value Presence</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public <E> T caseIReferenceValuePresence(IReferenceValuePresence<E> object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>EReference Value Presence</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>EReference Value Presence</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public <E, A, R> T caseGdiffdata_EReferenceValuePresence(
      org.eclipse.emf.diffmerge.generic.gdiffdata.EReferenceValuePresence<E, A, R> object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch, but this is the last case anyway.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject)
   * @generated
   */
  public T defaultCase(EObject object) {
    return null;
  }

} //PojodiffdataSwitch