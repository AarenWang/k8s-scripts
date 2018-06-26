package org.wrj.k8s.app.send;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wangrenjun on 2017/2/14.
 */
@RestController
public class K8sSendHomeController {

    @RequestMapping("/home")
    String home() {
        return "Hi  This is K8sSendHomeController Application";
    }
}
