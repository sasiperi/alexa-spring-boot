package com.alexa.oms.util;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;

import com.alexa.oms.jsonpojo.Claims;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil
{
    private static final Logger LOG = LoggerFactory.getLogger(JsonUtil.class);

    public static String getClaimsJsonFromJWT(String jwtToken)
    {

        Jwt jwtTk = JwtHelper.decode(jwtToken);
        String claims = jwtTk.getClaims();
        LOG.debug("JSON CLAIMS => " + claims);

        return claims;
    }

    public static Claims getClaimsFromClaimsJson(String claims) throws IOException
    {

        /*JsonParser jp = new JsonFactory().createParser(claims);
        return jp.readValueAs(Claims.class);
        */
        ObjectMapper objectMapper = new ObjectMapper();        
        return objectMapper.readValue(claims, Claims.class);

    }
    
    
    public static Claims getClaimsFromJWT(String jwtToken) throws IOException
    {

        return getClaimsFromClaimsJson(getClaimsJsonFromJWT(jwtToken));

    }
    

   /* public static void main(String args[]) throws IOException
    {
        String jwt = "{\"exp\":1491960909,\"user_name\":\"a@b.com\",\"authorities\":[\"ROLE_USER\"],\"jti\":\"cbc2540a-0957-4d0f-94ae-582d29acd7d1\",\"client_id\":\"alexa_oms\",\"scope\":[\"read\",\"write\"]}";
       
        System.out.println(getClaimsFromClaimsJson(jwt).toString());
    }*/
}
