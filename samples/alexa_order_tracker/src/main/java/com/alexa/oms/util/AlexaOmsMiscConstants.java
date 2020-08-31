package com.alexa.oms.util;

public class AlexaOmsMiscConstants {
	
	// *************  ERROR RESPONSES ***********************
	public static final String RESPONSE_INVALID_INTENT             =   "Invalid command";
	public static final String RESPONSE_INVALID_NUMBER             =   "Invalid number";
	public static final String RESPONSE_UNKNOWN_CUSTOMER           =   "Unknown customer";
	public static final String RESPONSE_NOT_AUTHORIZED             =   "You are not authorized to use this skill";
	
	// *****        ORDERS INTENT *****************************
	public static final String RESPONSE_REPROMPT_INVALID_COUNT     =   "I am not sure the order count, please try again";
	public static final String RESPONSE_EMPTY_COUNT                =   "I'm not sure how many orders, for example you can say find the top 2 orders";
	public static final String RESPONSE_OUTOFRANGE_COUNT           =   "I am sorry ! Valid number of orders I can get you should between 1 and 5. You asked me for invalid number ";
	
	public static final String RESPONSE_REPROMPT_INVALID_POSITION     =   "I am not sure the position, please try again";
    public static final String RESPONSE_EMPTY_POSITION                =   "I'm not sure if you want the latest or oldest orders, for example you can say find the top 2 orders";
    public static final String RESPONSE_INVALID_POSITION              =   "Invalid position";
    
    
    // *****        ORDERS DETAIL/STATUS INTENT *****************************
    //public static final String RESPONSE_REPROMPT_BLANK_ORDERNUMBER              =   "I'm not sure what the order number is, please try again. For example say get order status for the order 123";
    //public static final String RESPONSE_BLANK_ORDERNUMBER                       =   "Invalid position";
	
	// *********       AMAZON STANDARD INTENTS **********************
    public static final String RESPONSE_WELCOME             =   "Welcome Cardinal Edge Park Supplies, this is Alexa, I can help tracking your orders";
    public static final String RESPONSE_HELLO_INTENT        =   "Hello there. Welcome to Alexa Edge Park Order Tracking Skill. You can say help to find what I can do for you";
    public static final String RESPONSE_HELP_INTENT         =   "<speak> Using Edge Park Order Tracking skill you can find your orer status, order details and get first or last few orders, <break time=\"0.2s\" /> maximum up to 5. " 
                                                                + "<break time=\"0.2s\" /> For example, you can say. <break time=\"0.2s\" />"
                                                                + "Find status of the order 123 <break time=\"0.2s\" /> " 
                                                                + "OR Find details of the order 123 <break time=\"0.2s\" /> "
                                                                + "OR get me top 2 orders <break time=\"0.2s\" /> "
                                                                + "Now, what can I help you with?</speak>";
    
	public static final String RESPONSE_REPROMPT_HELP_INTENT   =   "I'm sorry I didn't understand that. You can ask things like status, details, last or first few orders"; 	                                                            
	public static final String RESPONSE_GOODBYE                =   "GoodBye";
	public static final String RESPONSE_REPROMPT_GENERIC       =   "Would you like me to help with any thing else ? if not you can say good bye.";
	
	
	//SESSION
	public static final String CUSTOMER_ID = "CustomerId";
	
//******* Other constants *************
    
    public static final String ROLE_USER = "ROLE_USER";
    
}
