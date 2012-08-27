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
package org.eclipse.emf.diffmerge.diffdata.impl;

import java.util.Collection;

import org.eclipse.emf.diffmerge.api.IDiffPolicy;
import org.eclipse.emf.diffmerge.api.Role;
import org.eclipse.emf.diffmerge.api.diff.IAttributeValuePresence;
import org.eclipse.emf.diffmerge.api.scopes.IFeaturedModelScope;
import org.eclipse.emf.diffmerge.diffdata.DiffdataPackage;
import org.eclipse.emf.diffmerge.diffdata.EAttributeValuePresence;
import org.eclipse.emf.diffmerge.diffdata.EComparison;
import org.eclipse.emf.diffmerge.diffdata.EMatch;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>EAttribute Value Presence</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public class EAttributeValuePresenceImpl extends EValuePresenceImpl implements
		EAttributeValuePresence {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EAttributeValuePresenceImpl() {
		super();
	}

	/**
	 * Constructor
	 * @param comparison_p the non-null comparison to which this difference belongs
	 * @param elementMatch_p the non-null match for the element holding the value
	 * @param attribute_p the non-null attribute holding the value
	 * @param value_p the non-null value held
	 * @param presenceRole_p the role in which the value is held: TARGET or REFERENCE
	 * @param isOrder_p whether the value presence is solely due to ordering
	 * @generated NOT
	 */
	public EAttributeValuePresenceImpl(EComparison comparison_p,
			EMatch elementMatch_p, EAttribute attribute_p, Object value_p,
			Role presenceRole_p, boolean isOrder_p) {
		super(comparison_p, elementMatch_p, attribute_p, value_p,
				presenceRole_p, isOrder_p);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DiffdataPackage.Literals.EATTRIBUTE_VALUE_PRESENCE;
	}

	/**
	 * @see org.eclipse.emf.diffmerge.impl.base.AbstractValuePresence#getFeature()
	 * @generated NOT
	 */
	@Override
	public EAttribute getFeature() {
		return (EAttribute) super.getFeature();
	}

	/**
	 * @see org.eclipse.emf.diffmerge.diffdata.impl.EValuePresenceImpl#getSymmetrical()
	 * @generated NOT
	 */
	@Override
	public IAttributeValuePresence getSymmetrical() {
		IAttributeValuePresence result = null;
		if (!getFeature().isMany()) {
			Collection<IAttributeValuePresence> candidates = getElementMatch()
					.getAttributeDifferences(getFeature());
			assert candidates.size() <= 2; // Because !isMany()
			for (IAttributeValuePresence candidate : candidates) {
				if (candidate.getPresenceRole() == getAbsenceRole()) {
					result = candidate;
					break;
				}
			}
		} else if (isOrder()) {
			result = getElementMatch().getAttributeValueDifference(
					getFeature(), null);
		}
		return result;
	}

	/**
	 * @see org.eclipse.emf.diffmerge.api.diff.IElementRelativeDifference#isProperToElement()
	 * @generated NOT
	 */
	public boolean isProperToElement() {
		return true;
	}

	/**
	 * @see org.eclipse.emf.diffmerge.diffdata.impl.EValuePresenceImpl#mergeOrder()
	 * @generated NOT
	 */
	@Override
	protected void mergeOrder() {
		// TODO Implement
	}

	/**
	 * @see org.eclipse.emf.diffmerge.diffdata.impl.EValuePresenceImpl#mergeValueAddition()
	 * @generated NOT
	 */
	@Override
	protected void mergeValueAddition() {
		IFeaturedModelScope absenceScope = getAbsenceScope();
		EObject holderMatch = getMatchOfHolder();
		assert holderMatch != null; // Must be guaranteed by diff dependency handling
		absenceScope.add(holderMatch, getFeature(), getValue());
		IDiffPolicy diffPolicy = getComparison().getLastDiffPolicy();
		if (diffPolicy != null && diffPolicy.considerOrdered(getFeature())) {
			// TODO Implement
		}
	}

	/**
	 * @see org.eclipse.emf.diffmerge.diffdata.impl.EValuePresenceImpl#mergeValueRemoval()
	 * @generated NOT
	 */
	@Override
	public void mergeValueRemoval() {
		IFeaturedModelScope presenceScope = getPresenceScope();
		presenceScope.remove(getHolder(), getFeature(), getValue());
	}

} //EAttributeValuePresenceImpl