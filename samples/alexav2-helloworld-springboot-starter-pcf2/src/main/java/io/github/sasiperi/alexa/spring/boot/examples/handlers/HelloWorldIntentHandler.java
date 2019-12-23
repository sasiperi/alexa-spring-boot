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
package io.github.sasiperi.alexa.spring.boot.examples.handlers;

import static com.amazon.ask.request.Predicates.intentName;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;

import io.github.sasiperi.alexa.spring.boot.config.AlexaProperties;

/**
 * @author sasiperi (mailto:pvssasikala@gmail.com)
 *
 */
@Component
public class HelloWorldIntentHandler implements RequestHandler
{
    private static final Logger LOG = LoggerFactory.getLogger(HelloWorldIntentHandler.class);
    
    @Autowired
    private AlexaProperties alexaProps;

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.amazon.ask.request.handler.GenericRequestHandler#canHandle(java.lang.
     * Object)
     */

    @Override
    public boolean canHandle(HandlerInput input)
    {
        return input.matches(intentName("HelloWorldIntent"));
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.amazon.ask.request.handler.GenericRequestHandler#handle(java.lang.
     * Object)
     */
    @Override
    public Optional<Response> handle(HandlerInput input)
    {
        
        LOG.info("onIntent requestId={}, sessionId={}", input.getRequest().getRequestId(), input.getRequestEnvelope().getSession().getSessionId());
        return input.getResponseBuilder().
                withSpeech(alexaProps.getResponse().getGoodBye()).
                withSimpleCard(alexaProps.getCardTitle(), alexaProps.getResponse().getWelcome()).
                build();
    }

}
