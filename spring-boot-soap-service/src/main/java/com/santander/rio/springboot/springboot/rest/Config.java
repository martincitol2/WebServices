package com.santander.rio.springboot.springboot.rest;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
public class Config extends WsConfigurerAdapter {
    @Bean
    public ServletRegistrationBean messageDispatcherServlet(ApplicationContext applicationContext) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean(servlet, "/service/*");
    }

    @Bean(name = "clientDetailsWsdl")
    public DefaultWsdl11Definition defaultWsdl11Definitions(XsdSchema clientSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("ClientDetailsPort");
        wsdl11Definition.setLocationUri("/service/client-details");
        wsdl11Definition.setTargetNamespace("http://www.santander.com.ar");
        wsdl11Definition.setSchema(clientSchema);
        return wsdl11Definition;
    }

    @Bean
    public XsdSchema clientSchema() {
        return new SimpleXsdSchema(new ClassPathResource("client.xsd"));
    }
}