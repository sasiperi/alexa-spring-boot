package io.github.sasiperi.alexa.spring.boot.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
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
import com.amazon.ask.model.Response;

import io.github.sasiperi.alexa.spring.boot.config.AlexaProperties;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.stereotype.Component;

import static com.amazon.ask.request.Predicates.intentName;

/**
 *@author sasiperi (mailto:pvssasikala@gmail.com)
 *
 */
@Component(value="helpIntentHandler")
@ConditionalOnMissingBean(type = "HelpIntentHandler")
public class HelpIntentHandler implements RequestHandler
{
    
    @Autowired
    private AlexaProperties alexaProps;

    @Override
    public boolean canHandle(HandlerInput input)
    {
        return input.matches(intentName(alexaProps.getIntent().getHelpIntent()));
    }

    @Override
    public Optional<Response> handle(HandlerInput input)
    {
        return input.getResponseBuilder().
                withSpeech(alexaProps.getResponse().getHelpIntent()).
                withSimpleCard(alexaProps.getCardTitle(), alexaProps.getResponse().getHelpIntent()).
                build();
    }
}