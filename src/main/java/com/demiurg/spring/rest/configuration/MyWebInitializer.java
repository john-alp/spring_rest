package com.demiurg.spring.rest.configuration;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

// web.xml
public class MyWebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
       // return new Class[0];
        return null;
    }

    @Override   //  <param-value>/WEB-INF/applicationContext.xml</param-value>
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{MyConfig.class};
    }

    @Override   //   <url-pattern>/</url-pattern>
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
