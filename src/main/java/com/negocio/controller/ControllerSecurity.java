/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.negocio.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author Usuario
 */
@Controller
public class ControllerSecurity {
    
    
    @GetMapping("/")
    public String home(){
     return "/home";
    }
    
    @GetMapping("/user")
    public String user(){
     return "/user";
    }
    
    @GetMapping("/error")
    public String error(){
     return "/error";
    }
    
    @GetMapping("/login")
    public String login(){
     return "/login";
    }
}
