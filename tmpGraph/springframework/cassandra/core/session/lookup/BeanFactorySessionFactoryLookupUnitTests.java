/*
 * Copyright 2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.cassandra.core.session.lookup;

import static org.assertj.core.api.Assertions.*;


import org.junit.Test;
import org.junit.runner.RunWith;


import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.cassandra.core.session.SessionFactory;

/**
 * Unit test for {@link BeanFactorySessionFactoryLookup}.
 *
 * @author Mark Paluch
 */

public class BeanFactorySessionFactoryLookupUnitTests {

	@Mock BeanFactory beanFactory;
	@Mock SessionFactory sessionFactory;

	@Test(expected = IllegalArgumentException.class) // DATACASS-330
	public void shouldRejectNullBeanFactory() {
		new BeanFactorySessionFactoryLookup(null);
	}

	@Test // DATACASS-330
	public void shouldResolveSessionFactoryFromBeanFactory() throws Exception {

		when(beanFactory.getBean("factory", SessionFactory.class)).thenReturn(sessionFactory);

		BeanFactorySessionFactoryLookup lookup = new BeanFactorySessionFactoryLookup();

		lookup.setBeanFactory(beanFactory);

		SessionFactory result = lookup.getSessionFactory("factory");

		assertThat(result).isSameAs(sessionFactory);
	}

	@Test // DATACASS-330
	public void shouldThrowExceptionIfLookupFails() throws Exception {

		when(beanFactory.getBean("factory", SessionFactory.class)).thenThrow(new NoSuchBeanDefinitionException("factory"));

		BeanFactorySessionFactoryLookup lookup = new BeanFactorySessionFactoryLookup();

		lookup.setBeanFactory(beanFactory);

		try {
			lookup.getSessionFactory("factory");
			fail("Missing SessionFactoryLookupFailureException");
		} catch (SessionFactoryLookupFailureException e) {
			assertThat(e).hasMessageContaining("Failed to look up SessionFactory bean with name [factory]")
					.hasRootCauseInstanceOf(NoSuchBeanDefinitionException.class);
		}
	}
}
