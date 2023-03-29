package com.application;

import com.vaadin.flow.component.dependency.NpmPackage;
import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.theme.Theme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * The entry point of the Spring Boot application.
 *
 * Use the @PWA annotation make the application installable on phones, tablets
 * and some desktop browsers.
 *
 */
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
@Theme(value = "reservationsystem")
@PWA(name = "Reservation System", shortName = "Reservation System", offlineResources = {})
@NpmPackage(value = "line-awesome", version = "1.3.0")
@EnableMongoRepositories
public class Application extends SpringBootServletInitializer implements AppShellConfigurator {


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);

    }


}
