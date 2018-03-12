package com.github.wolfclub.oneblog.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

/**
 * @author liuchan
 * @date 11:00 2018/2/7
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter{

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("**").allowedOrigins("*")
                .allowedMethods("*").allowedHeaders("*").maxAge(3600);
    }


    public StringHttpMessageConverter stringHttpMessageConverter(){
        StringHttpMessageConverter stringMessageConverter = new StringHttpMessageConverter();
        stringMessageConverter.setSupportedMediaTypes(Arrays.asList(MediaType.TEXT_HTML, MediaType.APPLICATION_JSON_UTF8));
        return stringMessageConverter;
    }

    @Bean
    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
        MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ssZ"));
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        jsonConverter.setObjectMapper(objectMapper);
        return jsonConverter;
    }


    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {

        converters.add(stringHttpMessageConverter());
        converters.add(mappingJackson2HttpMessageConverter());
        super.configureMessageConverters(converters);
    }
}
