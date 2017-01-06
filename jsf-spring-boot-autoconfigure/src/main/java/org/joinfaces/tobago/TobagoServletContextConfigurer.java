/*
 * Copyright 2016-2017 the original author or authors.
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

package org.joinfaces.tobago;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.myfaces.tobago.config.TobagoConfig;
import org.apache.myfaces.tobago.internal.config.TobagoConfigBuilder;
import org.apache.myfaces.tobago.internal.config.TobagoConfigFragment;
import org.apache.myfaces.tobago.internal.config.TobagoConfigImpl;
import org.apache.myfaces.tobago.internal.config.TobagoConfigSorter;
import org.joinfaces.ServletContextConfigurer;
import org.xml.sax.SAXException;

import lombok.Builder;

/**
 * Servlet context configurer of Tobago.
 * @author Dennis Kieselhorst
 */
public class TobagoServletContextConfigurer extends ServletContextConfigurer {
   private final TobagoProperties tobagoProperties;
   private final ServletContext servletContext;

   @Builder
   public TobagoServletContextConfigurer(TobagoProperties tobagoProperties, ServletContext servletContext) {
      super(servletContext, "");
      this.tobagoProperties = tobagoProperties;
      this.servletContext = servletContext;
   }

   @Override
   public void configure() {
      TobagoConfigFragment tobagoConfigFragment = new TobagoConfigFragment();
      tobagoConfigFragment.setName("spring-boot-autoconfigured");
      tobagoConfigFragment.setCreateSessionSecret(Boolean.toString(tobagoProperties.isCreateSessionSecret()));
      tobagoConfigFragment.setCheckSessionSecret(Boolean.toString(tobagoProperties.isCheckSessionSecret()));
      tobagoConfigFragment.setCheckSecurityAnnotations(tobagoProperties.isCheckSecurityAnnotations());
      tobagoConfigFragment.setSetNosniffHeader(tobagoProperties.isSetNosniffHeader());
      tobagoConfigFragment.setPreventFrameAttacks(tobagoProperties.isPreventFrameAttacks());
      tobagoConfigFragment.setDefaultThemeName(tobagoConfigFragment.getDefaultThemeName());

      // TODO mapping of other properties

      TobagoConfigBuilder tobagoConfigBuilder =
         new TobagoConfigBuilder(servletContext, Collections.singletonList(tobagoConfigFragment));
      try {
         tobagoConfigBuilder.build();
      } catch (URISyntaxException e) {
         servletContext.log("error during Tobago configuration", e);
      } catch (SAXException e) {
         servletContext.log("error during Tobago configuration", e);
      } catch (ParserConfigurationException e) {
         servletContext.log("error during Tobago configuration", e);
      } catch (ServletException e) {
         servletContext.log("error during Tobago configuration", e);
      } catch (IOException e) {
         servletContext.log("error during Tobago configuration", e);
      }
   }
}
