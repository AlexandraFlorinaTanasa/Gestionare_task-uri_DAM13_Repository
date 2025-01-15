package org.gestionare_taskuri.config;

import org.springframework.web.servlet.config.annotation.CorsRegistry;

public interface RepositoryRestConfigurer {
    void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors);
}
