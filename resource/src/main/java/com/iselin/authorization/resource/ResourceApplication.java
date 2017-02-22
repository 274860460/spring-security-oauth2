package com.iselin.authorization.resource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@SpringBootApplication
public class ResourceApplication {

    @RequestMapping("/a")
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Object a() {
        return "a";
    }

//    @PreAuthorize("hasRole('ROLE_CREDIT')")
    @RequestMapping("/c")
    public Object c() {
        return "c";
    }


    public static void main(String[] args) {
        SpringApplication.run(ResourceApplication.class, args);
    }
}
