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
package io.github.sasiperi.alexa.spring.boot.examples.service;

import io.github.sasiperi.alexa.spring.boot.config.AlexaProperties;
import io.github.sasiperi.alexa.spring.boot.service.SkillSpeechletDefaultImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazon.speech.slu.Intent;
import com.amazon.speech.speechlet.IntentRequest;
import com.amazon.speech.speechlet.Session;
import com.amazon.speech.speechlet.SpeechletException;
import com.amazon.speech.speechlet.SpeechletResponse;

/**
 * This sample shows how to create a simple speechlet for handling speechlet
 * requests.
 * It overrides on Intent method of Default implementation from alexa-spring-boot-starter
 */

@Service
public class HelloWorldSpeechlet extends SkillSpeechletDefaultImpl
{
    private static final Logger log = LoggerFactory.getLogger(HelloWorldSpeechlet.class);

    @Autowired
    private AlexaProperties alexaProps;

    @Override
    public SpeechletResponse onIntent(final IntentRequest request, final Session session) throws SpeechletException
    {
        log.info("onIntent requestId={}, sessionId={}", request.getRequestId(), session.getSessionId());

        Intent intent = request.getIntent();
        String intentName = (intent != null) ? intent.getName() : null;

        if ("HelloWorldIntent".equals(intentName))
        {
            return getResponse(alexaProps.getResponse().getHelloIntent());
        } else if ("AMAZON.HelpIntent".equals(intentName))
        {
            return getResponse(alexaProps.getResponse().getWelcome());
        } else
        {
            throw new SpeechletException("Invalid Intent");
        }
    }

}
