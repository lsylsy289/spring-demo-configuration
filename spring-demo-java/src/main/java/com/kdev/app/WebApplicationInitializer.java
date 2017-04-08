package com.kdev.app;

import javax.servlet.Filter;
import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.kdev.app.context.RootContextConfig;
import com.kdev.app.context.ServletContextConfig;

/**
 * AbstractAnnotationConfigDispatcherServletInitializer
 * 
 */
public class WebApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        // TODO Auto-generated method stub
        return new Class<?>[]{RootContextConfig.class};
    }
 
    @Override
    protected void registerDispatcherServlet(ServletContext servletContext) {
        // TODO Auto-generated method stub
          WebApplicationContext servletAppContext = createServletApplicationContext();
          DispatcherServlet ds = new DispatcherServlet(servletAppContext);
          ServletRegistration.Dynamic appServlet = servletContext.addServlet("appServlet", ds);
          appServlet.setLoadOnStartup(1);
          appServlet.addMapping(getServletMappings());
    }
    
    @Override
    protected String[] getServletMappings() {
        // TODO Auto-generated method stub
        return new String[] { "/" };
    }
    
    @Override
    protected Filter[] getServletFilters() {
        // TODO Auto-generated method stub
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        return new Filter[]{characterEncodingFilter, new HiddenHttpMethodFilter()};
    }
 
 
    @Override
    protected Class<?>[] getServletConfigClasses() {
        // TODO Auto-generated method stub
        return new Class<?>[]{ServletContextConfig.class};
    }
}