/**
 * 
 */
package org.peri.alexa.spring.boot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazon.speech.speechlet.Speechlet;
import com.amazon.speech.speechlet.servlet.SpeechletServlet;

/*
 * #%L
 * alexa-spring-boot-starter
 * %%
 * Copyright (C) 2019- @sasiperi
 * %%
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
 * #L%
 */


/**
 *@author @sasiperi (mailto:pvssasikala@gmail.com)
 *
 */
@Configuration
@ConditionalOnClass(Speechlet.class)
@EnableConfigurationProperties(AlexaProperties.class)
public class AlexaAutoConfiguration
{
    @Autowired
    private AlexaProperties  alexaProps;

    @Autowired
    private Speechlet speechlet;
    
    @Bean
    public ServletRegistrationBean<SpeechletServlet> registerServlet() 
    {
        SpeechletServlet speechletServlet = new SpeechletServlet();
        speechletServlet.setSpeechlet(this.speechlet);

        return new ServletRegistrationBean<SpeechletServlet>(speechletServlet, alexaProps.getSpeechletUriMappings());
    }
}
