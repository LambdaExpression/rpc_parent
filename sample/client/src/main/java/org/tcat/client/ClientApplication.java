package org.tcat.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tcat.api.user.service.UserServiceRemote;

/**
 * Created by Lin on 2017/4/27.
 */
@ServletComponentScan
@SpringBootApplication
@EnableScheduling
@RestController
public class ClientApplication extends SpringBootServletInitializer {

    @Autowired
    private UserServiceRemote userService;

    @GetMapping("/")
    public String index() {
//        return DateFormatUtils.format(System.currentTimeMillis(), "yyyy-MM-dd HH:mm:ss.SSS");
        return userService.test();
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(ClientApplication.class, args);
    }

}
