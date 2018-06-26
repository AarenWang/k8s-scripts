package org.wrj.jude.openapi;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wangrenjun on 2017/2/14.
 */
@RestController
public class HomeController {

    @RequestMapping("/home")
    String home() {
        return "Hi  This is Spring Boot First Application";
    }
}
