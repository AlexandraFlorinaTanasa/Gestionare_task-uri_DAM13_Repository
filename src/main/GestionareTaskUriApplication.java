package org.gestionare_taskuri;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.logging.Logger;

@SpringBootApplication
public class GestionareTaskUriApplication {
private static Logger logger=Logger.getLogger(GestionareTaskUriApplication.class.getName());
    public static void main(String[] args) {
        logger.info("Loading...SpringBoot-GestionareTaskUriApplication Default Settings...");
        SpringApplication.run(GestionareTaskUriApplication.class, args);
    }

}
