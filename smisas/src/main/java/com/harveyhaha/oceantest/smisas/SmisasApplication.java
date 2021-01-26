package com.harveyhaha.oceantest.smisas;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SmisasApplication {
    private static final Logger logger = LoggerFactory.getLogger(SmisasApplication.class);

    public static void main(String[] args) {
        logger.debug("启动 Smiasa服务2");
        SpringApplication.run(SmisasApplication.class, args);
    }

}
