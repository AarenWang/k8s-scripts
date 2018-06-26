package org.wrj.k8s.app.receive;

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
public class K8sReceiveApplication {

    private static Logger logger = LoggerFactory.getLogger(K8sReceiveApplication.class);

    @RequestMapping("/")
    String home() {
        logger.info("K8sReceiveApplication.home run............");
        return "hello";
    }

    public static void main(String[] args) {
        SpringApplication.run(K8sReceiveApplication.class, args);

    }
}
