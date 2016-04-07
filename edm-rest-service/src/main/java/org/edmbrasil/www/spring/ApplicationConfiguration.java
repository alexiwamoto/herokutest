package org.edmbrasil.www.spring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan(basePackages = "org.edmbrasil.www")
@EnableJpaRepositories(basePackages = "org.edmbrasil.www.repository")
public class ApplicationConfiguration {
}
