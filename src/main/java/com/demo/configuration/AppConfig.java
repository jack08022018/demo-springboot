package com.demo.configuration;

import com.demo.configuration.exceptionHandler.RestTemplateResponseErrorHandler;
import com.demo.configuration.filters.MainFilter;
import com.demo.configuration.filters.SubFilter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableScheduling
public class AppConfig {

    @Autowired
    private Environment env;

    @Bean
    public FilterRegistrationBean<MainFilter> mainFilter() {
        FilterRegistrationBean<MainFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new MainFilter());
        registrationBean.addUrlPatterns("/api/*");
        registrationBean.setOrder(1);
        return registrationBean;
    }

//    @Bean
//    public FilterRegistrationBean<SubFilter> subFilter() {
//        FilterRegistrationBean<SubFilter> registrationBean = new FilterRegistrationBean<>();
//        registrationBean.setFilter(new SubFilter());
//        registrationBean.addUrlPatterns("/api/*");
//        registrationBean.setOrder(2);
//        return registrationBean;
//    }

    @Bean(name = "customRestTemplate")
    public RestTemplate getRestTemplate() {
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setReadTimeout(10000);
        requestFactory.setConnectTimeout(10000);
        RestTemplate restTemplate = new RestTemplate(requestFactory);
        restTemplate.setErrorHandler(new RestTemplateResponseErrorHandler());
        return restTemplate;
    }

    @Bean(name = "customObjectMapper")
    public ObjectMapper getObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        return mapper;
    }
}
