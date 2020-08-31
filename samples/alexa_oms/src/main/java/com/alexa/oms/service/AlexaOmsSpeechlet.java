/**
    Copyright 2014-2015 Amazon.com, Inc. or its affiliates. All Rights Reserved.

    Licensed under the Apache License, Version 2.0 (the "License"). You may not use this file except in compliance with the License. A copy of the License is located at

        http://aws.amazon.com/apache2.0/

    or in the "license" file accompanying this file. This file is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */
package com.alexa.oms.service;

import static com.alexa.oms.util.AlexaOmsMiscConstants.CUSTOMER_ID;
import static com.alexa.oms.util.AlexaOmsMiscConstants.RESPONSE_EMPTY_COUNT;
import static com.alexa.oms.util.AlexaOmsMiscConstants.RESPONSE_GOODBYE;
import static com.alexa.oms.util.AlexaOmsMiscConstants.RESPONSE_HELLO_INTENT;
import static com.alexa.oms.util.AlexaOmsMiscConstants.RESPONSE_HELP_INTENT;
import static com.alexa.oms.util.AlexaOmsMiscConstants.RESPONSE_INVALID_INTENT;
import static com.alexa.oms.util.AlexaOmsMiscConstants.RESPONSE_INVALID_NUMBER;
import static com.alexa.oms.util.AlexaOmsMiscConstants.RESPONSE_INVALID_POSITION;
import static com.alexa.oms.util.AlexaOmsMiscConstants.RESPONSE_NOT_AUTHORIZED;
import static com.alexa.oms.util.AlexaOmsMiscConstants.RESPONSE_OUTOFRANGE_COUNT;
import static com.alexa.oms.util.AlexaOmsMiscConstants.RESPONSE_REPROMPT_GENERIC;
import static com.alexa.oms.util.AlexaOmsMiscConstants.RESPONSE_REPROMPT_HELP_INTENT;
import static com.alexa.oms.util.AlexaOmsMiscConstants.RESPONSE_REPROMPT_INVALID_COUNT;
import static com.alexa.oms.util.AlexaOmsMiscConstants.RESPONSE_UNKNOWN_CUSTOMER;
import static com.alexa.oms.util.AlexaOmsMiscConstants.RESPONSE_WELCOME;
import static com.alexa.oms.util.AlexaOmsSpeechAssets.ORDER_COUNT_SLOT;
import static com.alexa.oms.util.AlexaOmsSpeechAssets.ORDER_NUMBER_SLOT;
import static com.alexa.oms.util.AlexaOmsSpeechAssets.ORDER_POSITION_SLOT;
import static com.alexa.oms.util.AlexaOmsSpeechAssets.getOldestSlots;
import static com.alexa.oms.util.AlexaOmsSpeechAssets.getRecentSlots;
import static com.alexa.oms.util.AlexaOmsSpeechAssets.IntentType.getIntentType;
import static com.alexa.oms.util.JsonUtil.getClaimsFromJWT;
import static org.apache.commons.lang3.StringUtils.isBlank;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;

import com.alexa.oms.jsonpojo.Claims;
import com.alexa.oms.model.Customer;
import com.alexa.oms.repository.CustomerRepository;
import com.alexa.oms.util.AlexaOmsSpeechAssets.IntentType;
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

@Service
public class AlexaOmsSpeechlet implements Speechlet
{
    private static final Logger LOG = LoggerFactory.getLogger(AlexaOmsSpeechlet.class);
    private static final String CARD_TITLE = "Alexa Order Tracking Skill";

    @Value("${application.id}")
    private String applicationId;
    
    @Autowired(required = true)
    AlexaOrderService alexaOrderService;
    
    @Autowired(required = true)
    CustomerRepository customerRepository;

    @Override
    public void onSessionStarted(final SessionStartedRequest request, final Session session) throws SpeechletException
    {
        String jwtToken = session.getUser().getAccessToken();
        LOG.info("onSessionStarted requestId={}, sessionId={}, access_token={}, userid={}", request.getRequestId(), session.getSessionId(), jwtToken, session.getUser().getUserId());
        if (jwtToken == null)
        {
            //return getResponse(RESPONSE_NOT_AUTHORIZED);            
            throw new HttpServerErrorException(HttpStatus.UNAUTHORIZED,RESPONSE_NOT_AUTHORIZED);
            
        }
        
        try
        {
         
              Claims claims = getClaimsFromJWT(jwtToken);
              String userId = claims.getUser_name();    
              
              LOG.info("User Id ==> " + userId);
              
              Customer customer = customerRepository.findByEmail(userId);
              
              LOG.info("CustomerId ==> " + customer.getCustomerId());
              
              if(StringUtils.isBlank(userId) || customer == null)
              {
                  
                  throw new HttpServerErrorException(HttpStatus.UNAUTHORIZED,RESPONSE_NOT_AUTHORIZED);
              }
              
              session.setAttribute(CUSTOMER_ID, customer.getCustomerId());          
            
        }catch (Exception e)
        {
            LOG.error("erro during cusomer identification" + e);
            throw new HttpServerErrorException(HttpStatus.UNAUTHORIZED,RESPONSE_NOT_AUTHORIZED);
        }
        
    }

    @Override
    public SpeechletResponse onLaunch(final LaunchRequest request, final Session session) throws SpeechletException
    {
        LOG.info("onLaunch requestId={}, sessionId={}, access_token={}, userid={}", request.getRequestId(), session.getSessionId(), session.getUser().getAccessToken(), session.getUser().getUserId());
        return getResponse(RESPONSE_WELCOME);
    }

    @Override
    public void onSessionEnded(final SessionEndedRequest request, final Session session) throws SpeechletException 
    {
        LOG.info("onSessionEnded requestId={}, sessionId={}", request.getRequestId(), session.getSessionId());        
        session.removeAttribute(CUSTOMER_ID);
        // any cleanup LOGic goes here
    }

    
    @Override
    public SpeechletResponse onIntent(final IntentRequest request, final Session session) throws SpeechletException, HttpServerErrorException
    {
        //LOG.debug("onIntent requestId={}, sessionId={}, access_token={}, userid={}", request.getRequestId(), session.getSessionId(), session.getUser().getAccessToken(), session.getUser().getUserId());
       
        
        int customerId;
        try
        {
            customerId = (Integer)session.getAttribute(CUSTOMER_ID);
            if (!session.getApplication().getApplicationId().equalsIgnoreCase(applicationId))
            {
                //return getResponse(RESPONSE_NOT_AUTHORIZED);
                
                throw new HttpServerErrorException(HttpStatus.UNAUTHORIZED,RESPONSE_NOT_AUTHORIZED);
                
            }

            Intent intent = request.getIntent();
            String intentName = (intent != null) ? intent.getName() : null;
                        
            IntentType reqIntent = getIntentType(intentName);
            
            
            if (reqIntent != null)
            {
                LOG.debug("Intent ==> " + reqIntent + " Slot Keys :  " + intent.getSlots().keySet() +  "Slot Keys :" + intent.getSlots().values());
                
                switch (reqIntent)
                {
                    case HelloWorldIntent:
                        return getResponse(RESPONSE_HELLO_INTENT);
        
                    case OrderStatusIntent:
                        return getOrderStatusIntentResponse(intent, customerId, session);
        
                    case OrdersIntent:
                        return getOrdersIntentResponse(intent, customerId, session);
        
                    case OrderDetailIntent:
                        return getOrderDetailIntentResponse(intent, customerId, session);
        
                    case AmazonHelpIntent:
                        return newAskResponse(RESPONSE_HELP_INTENT, true, RESPONSE_REPROMPT_HELP_INTENT, false);
        
                    case AmazonCancelIntent:
                        return getResponse(RESPONSE_GOODBYE,true);
        
                    case AmazonStopIntent:
                        return getResponse(RESPONSE_GOODBYE,true);
        
                    default:
                        throw new SpeechletException(RESPONSE_INVALID_INTENT);

                }
            }else
            {
                throw new SpeechletException(RESPONSE_INVALID_INTENT);
            }
            
            
        }catch(NumberFormatException e)
        {
            LOG.error("",e);
            throw new SpeechletException(RESPONSE_UNKNOWN_CUSTOMER);
            
        }catch(Exception e)
        {
            LOG.error("",e);
            throw new SpeechletException(e.getMessage());
        }
                
    }

    private SpeechletResponse getOrdersIntentResponse(Intent intent, int customerId, Session session) throws SpeechletException
    {
        String numberOfOrdersStr = intent.getSlot(ORDER_COUNT_SLOT).getValue();
        String position = intent.getSlot(ORDER_POSITION_SLOT).getValue();
        try
        {
            if(isBlank(numberOfOrdersStr))
            {   
                return getResponse(RESPONSE_EMPTY_COUNT,RESPONSE_REPROMPT_INVALID_COUNT);
            }
                
             int numberOfOrders = Integer.parseInt(numberOfOrdersStr);
             if(numberOfOrders < 0 || numberOfOrders > 5)
             {
                 return getResponse(RESPONSE_OUTOFRANGE_COUNT+numberOfOrders, RESPONSE_REPROMPT_INVALID_COUNT);
             }
             
             if(isBlank(position))
             {
                 return getResponse(RESPONSE_EMPTY_COUNT,RESPONSE_REPROMPT_INVALID_COUNT);
                 
             }
             //if user want to get recent orders
             else if(getRecentSlots().contains(position))
             {
              
                 String response = alexaOrderService.getTopOrders(customerId,numberOfOrders);  
                 return newAskResponse(response, true, RESPONSE_REPROMPT_GENERIC, false);
             }
             //if user wants to get oldest orders
             else if(getOldestSlots().contains(position))
             {
                 String response = alexaOrderService.getBottomOrders(customerId,numberOfOrders);
                 return newAskResponse(response, true, RESPONSE_REPROMPT_GENERIC, false);
             }
             // don't know how to order by? (top/bottom ?)
             // so throw exception
             else
             {
                 throw new SpeechletException(RESPONSE_INVALID_POSITION);
             }

        }
        catch(NumberFormatException e)
        {
            throw new SpeechletException(RESPONSE_INVALID_NUMBER);
            
        }
        
    }

    private SpeechletResponse getOrderDetailIntentResponse(Intent intent, int customerId, Session session) throws SpeechletException
    {
        String orderNumStr = intent.getSlot(ORDER_NUMBER_SLOT).getValue();
        if(isBlank(orderNumStr))
        {
         
            // The category didn't match one of our predefined categories. Reprompt the user.            
            String speechOutput =
                    "I'm not sure what the order number is, for example you can say find the order details for" +
                    "1 <break time=\"0.1s\" /> " +
                    "2 <break time=\"0.1s\" /> " +                    
                    "3.";
            String repromptText  = "I'm not sure what the order number is, please try again. For example say get order detail for the order 123";
            
            return newAskResponse("<speak>" + speechOutput  + "</speak>", true, repromptText, false);
        }else
        {
            
            try
            {
                int orderNum = Integer.parseInt(orderNumStr);
                String response = alexaOrderService.getOrderDetailsString(customerId,orderNum);               
                return newAskResponse(response, true, RESPONSE_REPROMPT_GENERIC, false);
                
            }catch(NumberFormatException ne)
            {
                throw new SpeechletException(RESPONSE_INVALID_NUMBER);
            }
            
        }
    }

    private SpeechletResponse getOrderStatusIntentResponse(Intent intent, int customerId, Session session) throws SpeechletException
    {
        String orderNumStr = intent.getSlot(ORDER_NUMBER_SLOT).getValue();
        if(isBlank(orderNumStr))
        {
         
            // The category didn't match one of our predefined categories. Reprompt the user.            
            String speechOutput =
                    "I'm not sure what the order number is, for example you can say find the order status for" +
                    "1 <break time=\"0.1s\" /> " +
                    "2 <break time=\"0.1s\" /> " +                    
                    "3.";
            String repromptText  = "I'm not sure what the order number is, please try again. For example say get order status for the order 123";
            
            return newAskResponse("<speak>" + speechOutput  + "</speak>", true, repromptText, false);
        }else
        {
            
            try
            {
                int orderNum = Integer.parseInt(orderNumStr);
                String response = alexaOrderService.getOrderStatusString(customerId,orderNum);               
                return newAskResponse(response, true, RESPONSE_REPROMPT_GENERIC, false);
                
            }catch(NumberFormatException ne)
            {
                throw new SpeechletException(RESPONSE_INVALID_NUMBER);
            }
            
        }
        
    }

    /**
     * Creates a {@code SpeechletResponse} for the help intent.
     *
     * @return SpeechletResponse spoken and visual response for the given intent
     */
    private SpeechletResponse getResponse(String response)
    {
        return getResponse(response, response,false);
    }
    
    private SpeechletResponse getResponse(String response, boolean shouldEndSession)
    {
        return getResponse(response, response,shouldEndSession);
    }
    
    private SpeechletResponse getResponse(String response, String repromptReponse)
    {
        return getResponse(response, response,false);
    }
    
    private SpeechletResponse getResponse(String response, String repromptReponse, boolean shouldEndSession)
    {
        // Create the Simple card content.
        SimpleCard card = new SimpleCard();
        card.setTitle(CARD_TITLE);
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
        speechletResponse.setShouldEndSession(shouldEndSession);
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
    private SpeechletResponse newAskResponse(String stringOutput, boolean isOutputSsml, String repromptText, boolean isRepromptSsml)
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
        card.setTitle(CARD_TITLE);
        card.setContent(stringOutput);

        return SpeechletResponse.newAskResponse(outputSpeech, reprompt, card);
    }
}
