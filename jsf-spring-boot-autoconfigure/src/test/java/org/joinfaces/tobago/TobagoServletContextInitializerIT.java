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

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.apache.myfaces.tobago.config.TobagoConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = TobagoSpringBootAutoConfiguration.class, webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class TobagoServletContextInitializerIT {

   @Autowired
   private TobagoProperties tobagoProperties;

   @Test
   public void testOnStartup() throws ServletException {
      TobagoServletContextInitializer tobagoServletContextInitializer
         = new TobagoServletContextInitializer(this.tobagoProperties);

      ServletContext servletContext = new MockServletContext();

      tobagoServletContextInitializer.onStartup(servletContext);

      TobagoConfig tobagoConfig = (TobagoConfig) servletContext.getAttribute(TobagoConfig.TOBAGO_CONFIG);
      assertThat(tobagoConfig).isNotNull();
      assertThat(tobagoConfig.isPreventFrameAttacks()).isTrue();
   };
}
