package com.integrador1.cafekamila;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CafekamilaApplication {

    private static final Logger logger =
            LoggerFactory.getLogger(CafekamilaApplication.class);

    public static void main(String[] args) {

        SpringApplication.run(
                CafekamilaApplication.class,
                args
        );

        logger.info("=================================");
        logger.info("APLICACION CAFEKAMILA INICIADA");
        logger.info("=================================");
    }

}