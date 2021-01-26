package com.harveyhaha.oceantest.agency;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AgencyApplication {
    private static final Logger logger = LoggerFactory.getLogger(AgencyApplication.class);

    public static void main(String[] args) {
        logger.debug("启动 http代理服务器q");
        SpringApplication.run(AgencyApplication.class, args);
    }
}
