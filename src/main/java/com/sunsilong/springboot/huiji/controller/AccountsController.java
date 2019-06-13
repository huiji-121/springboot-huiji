package com.sunsilong.springboot.huiji.controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author SSL
 */
@Configuration
public class AccountsController {
    @GetMapping("/")
    public String index() {
        return "redirect:/index.html";
    }

}
