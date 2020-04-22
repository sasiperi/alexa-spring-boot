package com.alexa.oms.jsonpojo;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author sasi.peri
 *
 */
@Configuration
@EnableJpaRepositories(basePackages={"com.alexa.oms.repository"})
public class JpaConfiguration {
	
	

}
