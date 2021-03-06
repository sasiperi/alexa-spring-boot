package com.alexa.oms.config;

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
