package io.github.sasiperi.alexa.spring.boot.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.LaunchRequest;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.Session;

import io.github.sasiperi.alexa.spring.boot.config.AlexaProperties;

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
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.stereotype.Component;

import static com.amazon.ask.request.Predicates.requestType;

/**
 *@author sasiperi (mailto:pvssasikala@gmail.com)
 *
 */
@Component(value="launchRequestHandler")
@ConditionalOnMissingBean(type = "LaunchRequestHandler")
public class LaunchRequestHandler implements RequestHandler
{
    private static final Logger LOG = LoggerFactory.getLogger(LaunchRequestHandler.class);
    
    @Autowired
    private AlexaProperties alexaProps;

    @Override
    public boolean canHandle(HandlerInput input)
    {
        return input.matches(requestType(LaunchRequest.class));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) 
    {
        Session session = input.getRequestEnvelope().getSession();
        String jwtToken = session.getUser().getAccessToken();
        LOG.info("onSessionStarted requestId={}, sessionId={}, access_token={}, userid={}", input.getRequest().getRequestId(), session.getSessionId(), jwtToken, session.getUser().getUserId());
        if (jwtToken == null)
        {
            LOG.warn(" This skill is not authenticated and authorized, you may want to secure the skill !");
            
        }
        
        
        return input.getResponseBuilder().
                withSpeech(alexaProps.getResponse().getHelloIntent()).
                withSimpleCard(alexaProps.getCardTitle(), alexaProps.getResponse().getHelloIntent()).
                build();
    }

}