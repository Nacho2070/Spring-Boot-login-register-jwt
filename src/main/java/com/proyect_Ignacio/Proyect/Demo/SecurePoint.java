package com.proyect_Ignacio.Proyect.Demo;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;



/**
 * DemoEndPoint
 */
@RestController
@RequestMapping("demo/")
public class SecurePoint {


   @GetMapping("endpoint")  
   public String  SecurityEndpoint(){
    return "Welcome to secure Endpoint";
   }

    
}