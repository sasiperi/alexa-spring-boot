package com.alexa.oms.config;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alexa.oms.service.AlexaOmsSpeechlet;
import com.amazon.speech.speechlet.servlet.SpeechletServlet;




@Configuration
public class AlexaConfig
{


	@Autowired
	private AlexaOmsSpeechlet speechlet;
	
    @Bean
    public ServletRegistrationBean registerServlet() 
    {
        SpeechletServlet speechletServlet = new SpeechletServlet();
        speechletServlet.setSpeechlet(this.speechlet);

        return new ServletRegistrationBean(speechletServlet, "/alexaOms");
    }

    
}
