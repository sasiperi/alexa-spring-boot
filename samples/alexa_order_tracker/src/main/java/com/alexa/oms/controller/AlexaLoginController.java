package com.alexa.oms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
//import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@EnableAutoConfiguration
@ComponentScan
public class AlexaLoginController
{
    
    private static final String REDIRECT_URL_CART = "redirect:" + "TODO";
    
    @Autowired
    private ObjectMapper objectMapper;
       
    //@RequestMapping(value={"/login","/"}, method = RequestMethod.GET)
    @RequestMapping(value={"/login","/"})
    public String validateUser(final Model model, final RedirectAttributes redirectModel)
    {
        
        return "index";
    }
    
    @RequestMapping(value={"/success"}, method = RequestMethod.GET)       
    //public String redirectToAmazon(final Model model, final RedirectAttributes redirectModel, OAuth2Authentication auth) throws Exception
    public String redirectToAmazon(final Model model, final RedirectAttributes redirectModel) throws Exception
    {
        /*if(auth !=  null)
        {
            System.out.println(auth.getAuthorities());
            
            System.out.println(auth.getDetails().toString());
            
            model.addAttribute("principal",auth.getPrincipal().toString());
            model.addAttribute("jwt",toPrettyJsonString(auth));
            model.addAttribute("p_name",auth.getName());
        }
        */
        
        return "greetingView";
        //return REDIRECT_URL_CART;
    }
    
    @RequestMapping("/login-error")
    public String loginError(Model model, final RedirectAttributes redirectModel)
    {
        model.addAttribute("loginError", true);
        redirectModel.addFlashAttribute("loginError", true);
        return "redirect:login";
    }

    
    private String toPrettyJsonString(Object object) throws Exception {
        return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
    }
}

    
