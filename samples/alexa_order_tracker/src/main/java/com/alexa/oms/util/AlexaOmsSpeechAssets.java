package com.alexa.oms.util;

import java.util.Arrays;
import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class AlexaOmsSpeechAssets
{
    public static final String ORDER_NUMBER_SLOT = "OrderNumber";
    public static final String ORDER_COUNT_SLOT = "OrderCount";
    public static final String ORDER_POSITION_SLOT = "Position";
    private static final String[] RECENT_SLOTS = new String[]{"top","first","recent","latest","head"};
    private static final String[] OLDEST_SLOTS = new String[]{"bottom","last","oldest","tail"};
     
    public static enum IntentType
    {
        HelloWorldIntent,
        OrderStatusIntent,
        OrdersIntent,
        OrderDetailIntent,
        AmazonHelpIntent("AMAZON.HelpIntent"),
        AmazonCancelIntent("AMAZON.CancelIntent"),
        AmazonStopIntent("AMAZON.StopIntent");
        
        private final String stringValue;
                      
        private IntentType()
        {
            
            this.stringValue = this.name();
        }
        
        private IntentType(final String stringValue)
        {
            this.stringValue = stringValue.intern();
        }
        
        /**
         * gets a String value of the current enum.
         * @return - a Sting object.
         */
        public String getName()
        {
            return this.stringValue;
        }
        
        
        public static IntentType getIntentType(String name)
        {

            for (IntentType it : values()) {
                if (it.getName().equalsIgnoreCase(name)) {
                    return it;
                }
            }
            return null;
        }
       
       
    }
    
   public static List<String> getRecentSlots()
   {              
       return Arrays.asList(RECENT_SLOTS);       
   }
   
   public static List<String> getOldestSlots()
   {              
       return Arrays.asList(OLDEST_SLOTS);       
   }
   
   /*public static void main(String args[])
   {
       IntentType it = IntentType.getIntentType("AMAZON.CancelIntent");
       
       System.out.println(it.toString());
       String inte = "asssa";
       
       System.out.println(" : " + AlexaOmsSpeechAssets.Intent.AmazonCancelIntent.getName() + " : " + AlexaOmsSpeechAssets.Intent.OrdersIntent.getName());
       
       BCryptPasswordEncoder b  = new BCryptPasswordEncoder();
       System.out.println(b.encode("sp123"));
       System.out.println(b.encode("kp123"));
       
   }*/
    
}
