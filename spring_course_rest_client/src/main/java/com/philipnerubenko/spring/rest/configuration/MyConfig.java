package com.philipnerubenko.spring.rest.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Spring configuration class for setting up application context and defining beans.
 * Scans the "com.philipnerubenko.spring.rest" package for components and provides
 * a RestTemplate bean for HTTP communication.
 */
@Configuration
@ComponentScan("com.philipnerubenko.spring.rest")
public class MyConfig {
  /**
   * Creates and registers a RestTemplate bean for making HTTP requests.
   * <p>
   * RestTemplate is used to interact with RESTful web services.
   *
   * @return a new instance of RestTemplate
   */
  @Bean
  public RestTemplate restTemplate() {
    return new RestTemplate();
  }
}
