package tn.esprit.spring;

import java.util.EnumSet;

import javax.faces.webapp.FacesServlet;
import javax.servlet.DispatcherType;

import org.ocpsoft.rewrite.servlet.RewriteFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import tn.esprit.spring.config.WebSocketConfig;
import tn.esprit.spring.control.MainController;
import tn.esprit.spring.control.WebSocketController;
import tn.esprit.spring.interceptor.HttpHandshakeInterceptor;
import tn.esprit.spring.listener.WebSocketEventListener;
import tn.esprit.spring.model.ChatMessage;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackageClasses=WebSocketController.class)
@ComponentScan(basePackageClasses=MainController.class)
@ComponentScan(basePackageClasses=WebSocketConfig.class)
@ComponentScan(basePackageClasses=HttpHandshakeInterceptor.class)
@ComponentScan(basePackageClasses=ChatMessage.class)
@ComponentScan(basePackageClasses=WebSocketEventListener.class)
public class ConsomithamzaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConsomithamzaApplication.class, args);
	}
	@Bean
	public ServletRegistrationBean servletRegistrationBean() {
	FacesServlet servlet= new FacesServlet();
	return new ServletRegistrationBean(servlet, "*.jsf"); }
	@Bean
	public FilterRegistrationBean rewriteFilter() {
	FilterRegistrationBean rwFilter= new FilterRegistrationBean(new RewriteFilter());
	rwFilter.setDispatcherTypes(EnumSet.of(DispatcherType.FORWARD, DispatcherType.REQUEST, DispatcherType.ASYNC, DispatcherType.ERROR));
		rwFilter.addUrlPatterns("/*");
		return rwFilter;
		}
}
//@ComponentScan(basePackages = {"tn.esprit.spring.service", "tn.esprit.spring.config","tn.esprit.spring.entity","tn.esprit.spring.control"})
