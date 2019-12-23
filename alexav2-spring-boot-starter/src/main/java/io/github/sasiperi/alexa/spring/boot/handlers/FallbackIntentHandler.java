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

package io.github.sasiperi.alexa.spring.boot.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;

import io.github.sasiperi.alexa.spring.boot.config.AlexaProperties;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.stereotype.Component;

import static com.amazon.ask.request.Predicates.intentName;

// Per Amazon Alexa documentation (samples)
// AMAZON.FallackIntent is only currently available in en-US locale.
// This handler will not be triggered except in that locale, so it can be
// safely deployed for any locale.

/**
 * @author sasiperi (mailto:pvssasikala@gmail.com) *
 */
@Component(value="fallbackIntentHandler")
@ConditionalOnMissingBean(type = "FallbackIntentHandler")
public class FallbackIntentHandler implements RequestHandler
{

    @Autowired
    private AlexaProperties alexaProps;
    
    @Override
    public boolean canHandle(HandlerInput input)
    {
        return input.matches(intentName(alexaProps.getIntent().getFallbackIntent()));
    }

    @Override
    public Optional<Response> handle(HandlerInput input)
    {
        return input.getResponseBuilder().
                withSpeech(alexaProps.getResponse().getFallbackIntent()).
                withSimpleCard(alexaProps.getCardTitle(), alexaProps.getResponse().getFallbackIntent()).
                build();
    }
}
