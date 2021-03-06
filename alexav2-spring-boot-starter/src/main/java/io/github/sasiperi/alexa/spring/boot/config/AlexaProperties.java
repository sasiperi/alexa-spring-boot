package io.github.sasiperi.alexa.spring.boot.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

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
 * @author sasiperi (mailto:pvssasikala@gmail.com)
 */

@ConfigurationProperties(prefix = "alexa")
public class AlexaProperties
{
    /*
     * The application id that alexa(dev) provides amzn1.ask.skill.xxxxxxx
     */
    private String applicationId;
    /*
     * card title that you want to go on the account alexa.amazon and in the appstore
     */
    private String cardTitle;
    
    /*
     * Various pre-configured intent names
     */
    private Intent intent;
    
    /*
     * Various pre-configured responses for standards intents, with default values, that can be overridden.     *
     */
    private Response response; 
    /*
     * Comma separated list of speechlet URI mappings, which will be invoked for intent(s)
     */
    private String[] speechletUriMappings;

    /*
     * The application id that alexa(dev) provides amzn1.ask.skill.xxxxxxx
     */
    public String getApplicationId()
    {
        return applicationId;
    }

    public void setApplicationId(String applicationId)
    {
        this.applicationId = applicationId;
    }
    
    public String getCardTitle()
    {
        return cardTitle;
    }

    public void setCardTitle(String cardTitle)
    {
        this.cardTitle = cardTitle;
    }
    
    public Intent getIntent()
    {
        return intent;
    }

    public void setIntent(Intent intent)
    {
        this.intent = intent;
    }
    
    public Response getResponse()
    {
        return response;
    }

    public void setResponse(Response response)
    {
        this.response = response;
    }

    public String[] getSpeechletUriMappings()
    {
        return speechletUriMappings;
    }

    public void setSpeechletUriMappings(String[] speechletUriMappings)
    {
        this.speechletUriMappings = speechletUriMappings;
    }
    
    
    public static class Intent
    {
        /*
         * Help Intent Name.
         * defaultValue": "AMAZON.HelpIntent" 
         */
        private String helpIntent = "AMAZON.HelpIntent";
       
        /*
         * STOP Intent Name.
         * defaultValue": "AMAZON.StopIntent" 
         */
        private String stopIntent = "AMAZON.StopIntent";
        
        /*
         * Cancel Intent Name.
         * defaultValue": "AMAZON.CancelIntent" 
         */
        private String cancelIntent = "AMAZON.CancelIntent";
        
        
        /*
         * Falleback Intent Name.
         * defaultValue": "AMAZON.FallbackIntent" 
         */
        private String fallbackIntent = "AMAZON.FallbackIntent" ;

        public String getHelpIntent()
        {
            return helpIntent;
        }

        public void setHelpIntent(String helpIntent)
        {
            this.helpIntent = helpIntent;
        }

        public String getStopIntent()
        {
            return stopIntent;
        }

        public void setStopIntent(String stopIntent)
        {
            this.stopIntent = stopIntent;
        }
        
        public String getCancelIntent()
        {
            return cancelIntent;
        }

        public void setCancelIntent(String cancelIntent)
        {
            this.cancelIntent = cancelIntent;
        }
        
        public String getFallbackIntent()
        {
            return fallbackIntent;
        }

        public void setFallbackIntent(String fallbackIntent)
        {
            this.fallbackIntent = fallbackIntent;
        }
     
    }
    
    public static class Response
    {
        /*
         * Response for Welcome Intent.
         * defaultValue": "Welcome" 
         */
        private String welcome = "welcome test";
        /*
         * Response for Good Bye Intent
         * defaultValue: Good Bye
         */
        private String goodBye;
        /*
         * Response for Hello Intent
         * defaultValue: Hello
         */
        private String helloIntent;
        /*
         * Response for Help Intent.
         * defaultValue: Help
         */
        private String helpIntent;
        /*
         * Response for reprompting help.
         * defaultValue: I'm sorry I didn't understand that. Could you please repeat Or you can say help to learn the valid actions
         */
        private String repromptHelpIntent;
        /*
         * Generic Repormpt Intent.
         * defaultValue: Would you like me to help with anything else ? If not you can say good bye
         */
        private String repromptGenericIntent;
        
        /*
         * Response to fallback intent.
         * defaultValue: Sorry, I don't know that. You can say try saying help!
         */
        private String fallbackIntent;

        public String getWelcome()
        {
            return welcome;
        }

        public void setWelcome(String welcome)
        {
            this.welcome = welcome;
        }

        public String getGoodBye()
        {
            return goodBye;
        }

        public void setGoodBye(String goodBye)
        {
            this.goodBye = goodBye;
        }

        public String getHelloIntent()
        {
            return helloIntent;
        }

        public void setHelloIntent(String helloIntent)
        {
            this.helloIntent = helloIntent;
        }

        public String getHelpIntent()
        {
            return helpIntent;
        }

        public void setHelpIntent(String helpIntent)
        {
            this.helpIntent = helpIntent;
        }

        public String getRepromptHelpIntent()
        {
            return repromptHelpIntent;
        }

        public void setRepromptHelpIntent(String repromptHelpIntent)
        {
            this.repromptHelpIntent = repromptHelpIntent;
        }

        public String getRepromptGenericIntent()
        {
            return repromptGenericIntent;
        }

        public void setRepromptGenericIntent(String repromptGenericIntent)
        {
            this.repromptGenericIntent = repromptGenericIntent;
        }

        public String getFallbackIntent()
        {
            return fallbackIntent;
        }

        public void setFallbackIntent(String fallbackIntent)
        {
            this.fallbackIntent = fallbackIntent;
        }

    }

}
