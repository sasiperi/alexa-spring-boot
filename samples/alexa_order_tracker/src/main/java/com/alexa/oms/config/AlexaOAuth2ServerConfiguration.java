package com.alexa.oms.config;

import static com.alexa.oms.util.AlexaOmsMiscConstants.ROLE_USER;

import java.security.KeyPair;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

@Configuration
public class AlexaOAuth2ServerConfiguration
{
    
    @Configuration
    @EnableAuthorizationServer
    protected static class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter
    {

        @Autowired
        private DataSource dataSource;

        @Bean
        public TokenStore tokenStore()
        {
            return new JdbcTokenStore(dataSource);
        }

        @Autowired
        @Qualifier("authenticationManagerBean")
        private AuthenticationManager authenticationManager;

        @Override
        public void configure(AuthorizationServerEndpointsConfigurer endpoints)
                throws Exception
        {

            endpoints.tokenStore(tokenStore())
                    .authenticationManager(authenticationManager)
                    .accessTokenConverter(jwtAccessTokenConverter())
                    .allowedTokenEndpointRequestMethods(HttpMethod.GET,
                            HttpMethod.POST);
        }
        
        @Bean
        public JwtAccessTokenConverter jwtAccessTokenConverter()
        {
            JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
            converter.setSigningKey("ALEXA_OMS_SUPER_SECRET_SIGNINGKEY");
            KeyPair keyPair = new KeyStoreKeyFactory(new ClassPathResource("keystore.jks"), "foobar".toCharArray()).getKeyPair("test");
            converter.setKeyPair(keyPair);
            return converter;
        }

        @Override
        public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception
        {
            oauthServer.allowFormAuthenticationForClients();
        }

        @Override
        public void configure(ClientDetailsServiceConfigurer clients) throws Exception
        {
            clients
                .inMemory()
                .withClient("alexa_oms")
                .scopes("read", "write")
                .authorities(ROLE_USER)
                .authorizedGrantTypes("client_credentials", "password", "refresh_token", "authorization_code", "implicit")
                //.authorizedGrantTypes("password","implicit","authorization_code")
                .secret("alexa_OMS_c001_s3crEt")
                .redirectUris("https://layla.amazon.com/api/skill/link/MMD6TSJZ97HYT","https://pitangui.amazon.com/api/skill/link/MMD6TSJZ97HYT","/success")
                .accessTokenValiditySeconds(1800);
           
        }
    }
    
    
    /*protected static class CustomTokenEnhancer implements TokenEnhancer
    {
        @Override
        public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication)
        {
            Map<String, Object> additionalInfo = new HashMap<>();
            additionalInfo.put("alexa_oms_id", authentication.getPrincipal().toString());
            ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
            return accessToken;
        }
    }*/
}
