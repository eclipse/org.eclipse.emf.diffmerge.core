/**
 * <copyright>
 * 
 * Copyright (c) 2006-2017  Thales Global Services S.A.S.
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
package org.eclipse.emf.diffmerge.sirius;

import java.util.Set;

import org.eclipse.emf.diffmerge.api.scopes.IFeaturedModelScope;
import org.eclipse.emf.diffmerge.gmf.GMFMergePolicy;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.ECrossReferenceAdapter;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.DView;
import org.eclipse.sirius.viewpoint.ViewpointPackage;


/**
 * A merge policy for Sirius elements.
 */
public class SiriusMergePolicy extends GMFMergePolicy {
  
  /**
   * Extend the given addition group for the given element within the given scope
   * based on Sirius peculiarities
   * @param group_p a non-null, modifiable collection
   * @param element_p a non-null element
   * @param scope_p a non-null scope
   */
  protected void extendSiriusAdditionGroup(Set<EObject> group_p, EObject element_p,
      IFeaturedModelScope scope_p) {
    // Semantic element -> DSemanticDecorators
    if (isGraphicalFromSemantic()) {
      ECrossReferenceAdapter crAdapter = ECrossReferenceAdapter.getCrossReferenceAdapter(element_p);
      if (crAdapter != null) {
        for (EStructuralFeature.Setting setting : crAdapter.getNonNavigableInverseReferences(element_p, false)) {
          if (setting.getEStructuralFeature() == ViewpointPackage.eINSTANCE.getDSemanticDecorator_Target())
            group_p.add(setting.getEObject());
        }
      }
    }
    // Sirius 4.1: Retrieve the diagram while merging the descriptor
    if (element_p instanceof DRepresentationDescriptor) {
      group_p.add(((DRepresentationDescriptor) element_p).getRepresentation());
    }
    // Sirius 4.1: Retrieve the descriptor while merging the diagram
    if (element_p instanceof DRepresentation) {
      EObject container = scope_p.getContainer(element_p);
      if (container instanceof DView) {
        for (EObject descriptor : scope_p.get(container,
            ViewpointPackage.Literals.DVIEW__OWNED_REPRESENTATION_DESCRIPTORS)) {
          if (descriptor instanceof DRepresentationDescriptor) {
            if (element_p.equals(((DRepresentationDescriptor) descriptor).getRepresentation())) {
              group_p.add(descriptor);
            }
          }
        }
      } else if (container == null) {
        DRepresentationDescriptor descriptor = getDescriptor((DRepresentation)element_p, scope_p);
        if (descriptor != null)
          group_p.add(descriptor);
      }
    }
    // Sirius/GMF consistency: GMF driven by Sirius
    if (element_p instanceof DDiagramElement)
      extendGMFAdditionGroupSemanticTarget(group_p, element_p, scope_p);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.gmf.GMFMergePolicy#getAdditionGroup(org.eclipse.emf.ecore.EObject, org.eclipse.emf.diffmerge.api.scopes.IFeaturedModelScope)
   */
  @Override
  public Set<EObject> getAdditionGroup(EObject element_p, IFeaturedModelScope scope_p) {
    Set<EObject> result = super.getAdditionGroup(element_p, scope_p);
    extendSiriusAdditionGroup(result, element_p, scope_p);
    return result;
  }
  
  /**
   * Return the descriptor for the given representation within the given scope, if any
   * @param representation_p a non-null representation
   * @param scope_p a non-null scope
   * @return a potentially null descriptor
   */
  protected DRepresentationDescriptor getDescriptor(
      DRepresentation representation_p, IFeaturedModelScope scope_p) {
    for (EObject root : scope_p.getContents()) {
      if (root instanceof DAnalysis) {
        for (DView view : ((DAnalysis)root).getOwnedViews()) {
          for (DRepresentationDescriptor descriptor : view.getOwnedRepresentationDescriptors()) {
            if (descriptor.getRepresentation() == representation_p)
              return descriptor;
          }
        }
      }
    }
    return null;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.gmf.GMFMergePolicy#isSingleMandatory(org.eclipse.emf.ecore.EReference)
   */
  @Override
  protected boolean isSingleMandatory(EReference reference_p) {
    return super.isSingleMandatory(reference_p) ||
        reference_p == ViewpointPackage.eINSTANCE.getDSemanticDecorator_Target();
  }
  
}
