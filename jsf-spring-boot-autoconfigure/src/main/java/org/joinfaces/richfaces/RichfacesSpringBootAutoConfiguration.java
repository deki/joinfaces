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

import org.joinfaces.javaxfaces.JavaxFacesSpringBootAutoConfiguration;
import org.richfaces.webapp.ResourceServlet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Spring Boot Auto Configuration of RichFaces.
 * @author Marcelo Fernandes
 * @author Jamillo Santos
 * @author Renato Soares
 */
@Configuration
@EnableConfigurationProperties(RichfacesProperties.class)
@ConditionalOnClass(name = "org.richfaces.application.CoreConfiguration")
@AutoConfigureBefore(JavaxFacesSpringBootAutoConfiguration.class)
@ConditionalOnWebApplication
public class RichfacesSpringBootAutoConfiguration {

	@Autowired
	private RichfacesProperties richfacesProperties;

	@Bean
	public ServletContextInitializer richfacesServletContextInitializer() {
		return new RichfacesServletContextInitializer(this.richfacesProperties);
	}

	@Bean
	public ServletRegistrationBean richfacesResourcesServlet() {
		ServletRegistrationBean result = new ServletRegistrationBean();
		result.setServlet(new ResourceServlet());
		result.setLoadOnStartup(1);
		result.addUrlMappings("/org.richfaces.resources/*");
		return result;
	}
}
