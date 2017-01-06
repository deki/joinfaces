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

import java.util.List;
import java.util.Properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.Setter;

/**
 * Configuration properties of Tobago.
 * Taken from org.apache.myfaces.tobago.internal.config.TobagoConfigImpl
 * and http://myfaces.apache.org/tobago/tobago-config-3.0.xsd
 * @author Dennis Kieselhorst
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "jsf.tobago")
public class TobagoProperties {
   private boolean createSessionSecret;
   private boolean checkSessionSecret;
   private boolean preventFrameAttacks;
   private boolean checkSecurityAnnotations;
   private boolean setNosniffHeader;

   private List<String> supportedThemeNames;
   private String defaultThemeName;

   private ContentSecurityPolicy contentSecurityPolicy;

   private Sanitizer sanitizer;

   private List<RendererConfig> renderConfigs;

   // https://github.com/spring-projects/spring-boot/wiki/Spring-Boot-Configuration-Binding

   @Getter
   @Setter
   public static class ContentSecurityPolicy {
      private String mode;
      private List<String> directives;
   }

   @Getter
   @Setter
   public static class Sanitizer {
      private String sanitizerClass;
      private Properties sanitizerProperties;
   }

   @Getter
   @Setter
   public static class RendererConfig {
      private String name;
      private List<String> supportedMarkups;
   }
}
