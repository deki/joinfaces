/*
 * Copyright 2016-2016 the original author or authors.
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

package org.joinfaces.richfaces;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RichfacesSpringBootAutoConfiguration.class, webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class RichfacesServletContextInitializerIT {

	@Autowired
	private RichfacesProperties richfacesProperties;

	@Test
	public void testOnStartup() throws ServletException {
		RichfacesServletContextInitializer richfacesServletContextInitializer
			= new RichfacesServletContextInitializer(this.richfacesProperties);

		ServletContext servletContext = new MockServletContext();

		richfacesServletContextInitializer.onStartup(servletContext);

		assertThat(servletContext.getInitParameter("org.richfaces.skin"))
			.isEqualTo("This is a skin");
	}

}
