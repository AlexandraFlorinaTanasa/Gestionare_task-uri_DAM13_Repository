package org.gestionare_taskuri.config;

import jakarta.persistence.EntityManager;
import jakarta.persistence.metamodel.Type;

import org.springframework.context.annotation.Configuration;

import org.springframework.web.servlet.config.annotation.CorsRegistry;



@Configuration
public class RestRepositoryConfig implements RepositoryRestConfigurer {


    public EntityManager entityManager;

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
        config.exposeIdsFor(
                (Integer[]) entityManager.getMetamodel().getEntities().stream()
                        .map(Type::getJavaType)
                        .toArray());
    }
}