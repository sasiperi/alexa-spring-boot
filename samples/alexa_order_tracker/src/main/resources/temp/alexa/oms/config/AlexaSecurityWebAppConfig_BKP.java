package com.alexa.oms.config;

import static com.alexa.oms.util.AlexaOmsMiscConstants.ROLE_USER;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;

import com.alexa.oms.service.Http401UnauthorizedEntryPoint;
import com.alexa.oms.service.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class AlexaSecurityWebAppConfig extends WebSecurityConfigurerAdapter
{

    @Autowired
    UserDetailsServiceImpl userDetailsServiceImpl;
    
    @Autowired
    private Http401UnauthorizedEntryPoint authenticationEntryPoint;

    @Value("${spring.security.oauth2.resourceserver.jwt.jwk-set-uri}")
    String jwkSetUri;
    // @formatter:off
    private static final String[] AUTH_WHITELIST = {
                        // -- swagger ui
                        "/resources/**", 
                        "/favicon.ico", 
                        "/alexaOms", 
                        "/configuration/ui",
                        "/configuration/security",
                        "/webjars/**",
                        "/.well-known/jwks.json",
                        "/oauth/**"
                        // other public endpoints of your API may be appended to this array
    };
    // @formatter:on

    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth)
            throws Exception
    {
        auth.userDetailsService(userDetailsServiceImpl)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception
    {
        return super.authenticationManagerBean();
    }

    /*
     * @Override
     * 
     * @Bean public AuthenticationManager authenticationManagerBean() throws
     * Exception { return super.authenticationManagerBean(); }
     */

    /*
     * @Override public void configure(WebSecurity web) throws Exception {
     * web.ignoring() .antMatchers(HttpMethod.OPTIONS, "/**")
     * .antMatchers("/login") .antMatchers("/index")
     * .antMatchers("/favicon.ico");
     * 
     * }
     */

    
    
    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        // @formatter:off
        http
            .httpBasic().realmName("alexa")
            .and()
            	.exceptionHandling()
            	.authenticationEntryPoint(authenticationEntryPoint)
            .and()
                .csrf().disable()
                .headers().frameOptions().disable()
                .and()
                	.sessionManagement()
                	.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            	.authorizeRequests(	authorizeRequests ->
        				{
							try {
								authorizeRequests
									.antMatchers(AUTH_WHITELIST).permitAll()									
									.anyRequest().fullyAuthenticated()
									//.anyRequest().hasAnyAuthority(ROLE_USER)
									.and()
									   .formLogin().loginPage("/login").permitAll()
									   .defaultSuccessUrl("/success")
									   .failureUrl("/login-error")
									 .and()
									    .logout().permitAll();
							} catch (Exception e) 
							{							
								e.printStackTrace();
							}
						}
                			
           	)
            .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);
        
    	// @formatter:on
    }

    @Bean
    JwtDecoder jwtDecoder()
    {
        return NimbusJwtDecoder.withJwkSetUri(this.jwkSetUri).build();
    }

}