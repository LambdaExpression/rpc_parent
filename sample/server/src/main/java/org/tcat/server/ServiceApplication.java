package org.tcat.server;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Lin on 2017/4/27.
 */
@ServletComponentScan
@SpringBootApplication
@EnableScheduling
@RestController
public class ServiceApplication extends SpringBootServletInitializer {

    @GetMapping("/")
    public String index() {
        return DateFormatUtils.format(System.currentTimeMillis(), "yyyy-MM-dd HH:mm:ss.SSS");
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(ServiceApplication.class, args);
    }

}
