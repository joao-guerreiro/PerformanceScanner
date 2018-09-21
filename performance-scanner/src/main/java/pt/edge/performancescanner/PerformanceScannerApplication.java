package pt.edge.performancescanner;


import java.util.EnumSet;

import javax.faces.webapp.FacesServlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.DispatcherType;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import pt.edge.performancescanner.domain.User;
import pt.edge.performancescanner.repository.UserRepository;


@SpringBootApplication
@ComponentScan(basePackages = "pt.edge.performancescanner")
public class PerformanceScannerApplication extends SpringBootServletInitializer {

	private static final Logger log = LoggerFactory.getLogger(PerformanceScannerApplication.class);


	public static void main(String[] args) {
		SpringApplication.run(PerformanceScannerApplication.class, args);

	}

}
