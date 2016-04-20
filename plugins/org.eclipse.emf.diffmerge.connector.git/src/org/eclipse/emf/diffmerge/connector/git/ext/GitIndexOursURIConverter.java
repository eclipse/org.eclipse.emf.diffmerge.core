/*******************************************************************************
 * Copyright (c) 2015-2016 Intel Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Stephane Bouchet (Intel Corporation) - initial API and implementation
 *    Olivier Constant (Thales Global Services) - tight integration
 *******************************************************************************/
package org.eclipse.emf.diffmerge.connector.git.ext;

import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.ContentHandler;
import org.eclipse.emf.ecore.resource.URIHandler;
import org.eclipse.jgit.dircache.DirCacheEntry;
import org.eclipse.jgit.lib.Repository;


/**
 * A URI Converter for file revisions in the Git index (staging area), holding role "Ours"
 * in the case of conflicts.
 */
public class GitIndexOursURIConverter extends AbstractGitConflictURIConverter {
  
  /**
   * Constructor
   * @param repository_p a non-null Git repository
   */
  public GitIndexOursURIConverter(Repository repository_p) {
    super(repository_p, DirCacheEntry.STAGE_2); // "Ours" in conflict resolution
  }
  
  /**
   * Constructor
   * @param uriHandlers_p a non-null list
   * @param contentHandlers_p a non-null list
   * @param repository_p a non-null Git repository
   */
  public GitIndexOursURIConverter(List<URIHandler> uriHandlers_p, List<ContentHandler> contentHandlers_p, Repository repository_p) {
    super(uriHandlers_p, contentHandlers_p, repository_p, DirCacheEntry.STAGE_2); // "Ours" in conflict resolution
  }

  /**
   * @see org.eclipse.emf.diffmerge.connector.git.ext.AbstractGitURIConverter#getURIPathRepresentation(org.eclipse.emf.common.util.URI)
   */
  @Override
  protected String getURIPathRepresentation(URI uri_p) {
    return uri_p.toPlatformString(true);
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.connector.git.ext.AbstractGitURIConverter#isSupportedURI(org.eclipse.emf.common.util.URI)
   */
  @Override
  protected boolean isSupportedURI(URI uri_p) {
    return uri_p.isPlatformResource();
  }
  
}