package com.philipnerubenko.spring.security.configuration;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

/**
 * Initializes Spring Security configuration for the web application.
 * <p>
 * Extends {@link AbstractSecurityWebApplicationInitializer} to automatically register
 * the Spring Security filter chain and apply security configurations defined in
 * {@code MySecurityConfig}. This class requires no additional implementation as it
 * inherits the default security initialization behavior from its parent class.
 */
public class MySecurityInitializer extends AbstractSecurityWebApplicationInitializer {}
