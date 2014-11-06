/*
 * Licensed to Jasig under one or more contributor license
 * agreements. See the NOTICE file distributed with this work
 * for additional information regarding copyright ownership.
 * Jasig licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License.  You may obtain a
 * copy of the License at the following location:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.jasig.cas.authentication.principal;

import org.junit.Test;

import java.util.Collections;

import static org.junit.Assert.*;

/**
 * Handles tests for {@link DefaultPrincipalFactory}.
 * @author Misagh Moayyed
 * @since 4.1
 */
public class DefaultPrincipalFactoryTests {
    @Test
    public void testCreatingSimplePrincipal() {
        final PrincipalFactory f = new DefaultPrincipalFactory();
        final Principal p = f.createPrincipal("uid");
        assertEquals(p.getId(), "uid");
        assertEquals(p.getAttributes().size(), 0);
    }

    @Test
    public void testCreatingSimplePrincipalWithAttributes() {
        final PrincipalFactory f = new DefaultPrincipalFactory();
        final Principal p = f.createPrincipal("uid", Collections.<String, Object>singletonMap("mail", "final@example.com"));
        assertEquals(p.getId(), "uid");
        assertEquals(p.getAttributes().size(), 1);
        assertTrue(p.getAttributes().containsKey("mail"));
    }

    @Test
    public void testCreatingSimplePrincipalWithDefaultRepository() {
        final PrincipalFactory f = new DefaultPrincipalFactory(new DefaultPrincipalAttributesRepository());
        final Principal p = f.createPrincipal("uid");
        assertEquals(p.getId(), "uid");
        assertEquals(p.getAttributes().size(), 0);
    }

    @Test
    public void testCreatingSimplePrincipalWithDefaultRepositoryInitialAttributes() {
        final PrincipalFactory f = new DefaultPrincipalFactory(
                new DefaultPrincipalAttributesRepository(Collections.<String, Object>singletonMap("mail", "final@example.com")));
        final Principal p = f.createPrincipal("uid");
        assertEquals(p.getId(), "uid");
        assertEquals(p.getAttributes().size(), 1);
        assertTrue(p.getAttributes().containsKey("mail"));
    }
}
