package com.ps.exercise.eip.configuration;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.apache.camel.component.servlet.CamelHttpTransportServlet;
import org.springframework.context.annotation.Configuration;

/**
 * Class to define all spring beans
 */
@Configuration
public class Config {

    private static final String CAMEL_SERVLET_NAME = "CamelServlet";
    private static final String CAMEL_URL_MAPPING = "/services/rest/api/*";

    @Bean
    public ServletRegistrationBean camelServletRegistrationBean() {
        ServletRegistrationBean registration = new ServletRegistrationBean(new CamelHttpTransportServlet(), CAMEL_URL_MAPPING);
        registration.setName(CAMEL_SERVLET_NAME);
        return registration;
    }

}
