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
package org.peri.alexa.spring.boot.service;

import org.peri.alexa.spring.boot.config.AlexaProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazon.speech.slu.Intent;
import com.amazon.speech.speechlet.IntentRequest;
import com.amazon.speech.speechlet.LaunchRequest;
import com.amazon.speech.speechlet.Session;
import com.amazon.speech.speechlet.SessionEndedRequest;
import com.amazon.speech.speechlet.SessionStartedRequest;
import com.amazon.speech.speechlet.Speechlet;
import com.amazon.speech.speechlet.SpeechletException;
import com.amazon.speech.speechlet.SpeechletResponse;
import com.amazon.speech.ui.OutputSpeech;
import com.amazon.speech.ui.PlainTextOutputSpeech;
import com.amazon.speech.ui.Reprompt;
import com.amazon.speech.ui.SimpleCard;
import com.amazon.speech.ui.SsmlOutputSpeech;

/**
 * This is the speechlet that hanles are Order Tracking related intent requests
 * for the Skill Alexa Order Tracking Service
 * @author sasi.peri
 * @version1.0
 */

/**
 * Provides a convenient base class for creating a {@link Speechlet}
 * instance. The implementation allows customization for a specific skill by overriding methods, for custom/specific intents (by overriding onIntent method for example)
 *
 * <p>
 *  To do this, you must create a class that extends SkillSpeechletDefaultImpl and override onIntent method
 * </p>
 *
 * 
 *
 * @author @sasiperi
 * @version1.0
 */
@Service
public class SkillSpeechletDefaultImpl implements Speechlet
{
    private static final Logger LOG = LoggerFactory.getLogger(SkillSpeechletDefaultImpl.class);
    
    @Autowired
    private AlexaProperties  alexaProps;
    
    public static final String CUSTOMER_ID = "CustomerId";
    
    
    @Override
    public void onSessionStarted(final SessionStartedRequest request, final Session session) throws SpeechletException
    {
        String jwtToken = session.getUser().getAccessToken();
        LOG.info("onSessionStarted requestId={}, sessionId={}, access_token={}, userid={}", request.getRequestId(), session.getSessionId(), jwtToken, session.getUser().getUserId());
        if (jwtToken == null)
        {
            LOG.warn(" This skill is not authenticated and authorized, you may want to secure the skill !");
            
        }
        
        int customerId = (Integer)session.getAttribute(CUSTOMER_ID);
        LOG.debug(" Customer Id is: " + customerId);
        
        
    }

    @Override
    public SpeechletResponse onLaunch(final LaunchRequest request, final Session session) throws SpeechletException
    {
        LOG.debug("onLaunch requestId={}, sessionId={}, access_token={}, userid={}", request.getRequestId(), session.getSessionId(), session.getUser().getAccessToken(), session.getUser().getUserId());
        return getResponse(alexaProps.getResponse().getWelcome());
    }

    @Override
    public void onSessionEnded(final SessionEndedRequest request, final Session session) throws SpeechletException 
    {
        LOG.debug("onSessionEnded requestId={}, sessionId={}", request.getRequestId(), session.getSessionId());        
        
        // any cleanup LOGic goes here
    }

    
    /* (non-Javadoc)
     * @see com.amazon.speech.speechlet.Speechlet#onIntent(com.amazon.speech.speechlet.IntentRequest, com.amazon.speech.speechlet.Session)
     * This is the method expected to be 
     */
    @Override
    public SpeechletResponse onIntent(final IntentRequest request, final Session session) throws SpeechletException
    {        
       
        try
        {
            
            if (!session.getApplication().getApplicationId().equalsIgnoreCase(alexaProps.getApplicationId()))
            {
                //TODO
                //throw new HttpServerErrorException(HttpStatus.UNAUTHORIZED,RESPONSE_NOT_AUTHORIZED);
                
            }

            Intent intent = request.getIntent();                        
            
            if (intent != null)
            {
                LOG.debug("Intent ==> " + intent.getName() + " Slot Keys :  " + intent.getSlots().keySet() +  "Slot Keys :" + intent.getSlots().values());
                
               
            }
            
            
            
        }catch(Exception e)
        {
            LOG.error("",e);
            throw new SpeechletException(e.getMessage());
        }
       
        //TODO
       // Implement what needs to happen for each intent here.
        // And return actual response
        return null;
                
    }

    /**
     * Creates a {@code SpeechletResponse} for the help intent.
     *
     * @return SpeechletResponse spoken and visual response for the given intent
     */
    public SpeechletResponse getResponse(String response)
    {
        return getResponse(response, response,false);
    }
    
    public SpeechletResponse getResponse(String response, boolean shouldEndSession)
    {
        return getResponse(response, response,shouldEndSession);
    }
    
    public SpeechletResponse getResponse(String response, String repromptReponse)
    {
        return getResponse(response, response,false);
    }
    
    public SpeechletResponse getResponse(String response, String repromptReponse, boolean shouldEndSession)
    {
        // Create the Simple card content.
        SimpleCard card = new SimpleCard();
        card.setTitle(alexaProps.getCardTitle());
        card.setContent(response);

        // Create the plain text output.
        PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
        speech.setText(response);
        
        PlainTextOutputSpeech repromptSpeech = new PlainTextOutputSpeech();
        repromptSpeech.setText(repromptReponse);

        // Create reprompt
        Reprompt reprompt = new Reprompt();        
        reprompt.setOutputSpeech(repromptSpeech);

        SpeechletResponse speechletResponse = SpeechletResponse.newAskResponse(speech, reprompt, card);
        speechletResponse.setNullableShouldEndSession(shouldEndSession);
        return speechletResponse;
    }
  

   
    /**
     * Wrapper for creating the Ask response from the input strings.
     * 
     * @param stringOutput
     *            the output to be spoken
     * @param isOutputSsml
     *            whether the output text is of type SSML
     * @param repromptText
     *            the reprompt for if the user doesn't reply or is misunderstood.
     * @param isRepromptSsml
     *            whether the reprompt text is of type SSML
     * @return SpeechletResponse the speechlet response
     */
    public SpeechletResponse newAskResponse(String stringOutput, boolean isOutputSsml, String repromptText, boolean isRepromptSsml)
    {
        OutputSpeech outputSpeech, repromptOutputSpeech;
        if (isOutputSsml)
        {
            outputSpeech = new SsmlOutputSpeech();
            ((SsmlOutputSpeech) outputSpeech).setSsml(stringOutput);
        } else
        {
            outputSpeech = new PlainTextOutputSpeech();
            ((PlainTextOutputSpeech) outputSpeech).setText(stringOutput);
        }

        if (isRepromptSsml)
        {
            repromptOutputSpeech = new SsmlOutputSpeech();
            ((SsmlOutputSpeech) repromptOutputSpeech).setSsml(repromptText);
        } else
        {
            repromptOutputSpeech = new PlainTextOutputSpeech();
            ((PlainTextOutputSpeech) repromptOutputSpeech).setText(repromptText);
        }
        Reprompt reprompt = new Reprompt();
        reprompt.setOutputSpeech(repromptOutputSpeech);
        
        SimpleCard card = new SimpleCard();
        card.setTitle(alexaProps.getCardTitle());
        card.setContent(stringOutput);

        return SpeechletResponse.newAskResponse(outputSpeech, reprompt, card);
    }

   
}
