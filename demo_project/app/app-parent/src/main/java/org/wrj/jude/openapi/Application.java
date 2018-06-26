package org.wrj.jude.openapi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wangrenjun on 2017/2/9.
 */
@SpringBootApplication
@RestController
public class Application {

    private static Logger logger = LoggerFactory.getLogger(Application.class);

    @RequestMapping("/")
    String home() {
        logger.info("Application.home run............");
        return "hello";
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);

    }
}
