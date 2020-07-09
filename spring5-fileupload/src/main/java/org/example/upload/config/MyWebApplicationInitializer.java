package org.example.upload.config;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration;

public class MyWebApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] { ApplicationConfig.class };
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] { WebConfig.class };
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }

    @Override
    protected Filter[] getServletFilters() {
        return new Filter[] { new CharacterEncodingFilter("UTF-8", true) };
    }

    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
        // MultipartConfigElement(String location, long maxFileSize, long maxRequestSize, int fileSizeThreshold)
        registration.setMultipartConfig(new MultipartConfigElement("/tmp"));
    }
}