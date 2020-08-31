/**
 * 
 */
package io.github.sasiperi.alexa.spring.boot.config;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.amazon.ask.Skill;
import com.amazon.ask.Skills;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.Response;
import com.amazon.ask.request.handler.GenericRequestHandler;
import com.amazon.ask.servlet.SkillServlet;

/*
 * #%L
 * alexav2-spring-boot-starter
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
 * @author sasiperi (mailto:pvssasikala@gmail.com)
 *
 */
@Configuration
@ConditionalOnClass(SkillServlet.class)
@EnableConfigurationProperties(AlexaProperties.class)
@ComponentScan(basePackages={"io.github.sasiperi.alexa.spring.boot.handlers"})
public class AlexaAutoConfiguration
{
    @Autowired
    private AlexaProperties  alexaProps;   
   
    private List<GenericRequestHandler<HandlerInput,Optional<Response>>> requestHandlers;
      
    
    @Autowired
    public AlexaAutoConfiguration(List<GenericRequestHandler<HandlerInput,Optional<Response>>> requestHandlers)
    {
        this.requestHandlers = requestHandlers;
    }
   
    
    @Bean
    public ServletRegistrationBean<SkillServlet> registerServlet(Skill skillInstance) {
        SkillServlet skillServlet = new SkillServlet(skillInstance);
        return new ServletRegistrationBean<>(skillServlet, alexaProps.getSpeechletUriMappings());
    }

    
    @Bean
    public Skill getSkill()    
    {
        return Skills.standard().
                addRequestHandlers(requestHandlers).
                withSkillId(alexaProps.getApplicationId()).
                build();
    }
     
    
    
}
