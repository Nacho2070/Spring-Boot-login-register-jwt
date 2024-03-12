package com.proyect_Ignacio.Proyect.Auth;

import java.io.UnsupportedEncodingException;
import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyect_Ignacio.Proyect.Requests.*;

import lombok.RequiredArgsConstructor;



@RequestMapping("/auth")
@RestController
@RequiredArgsConstructor
public class AuthController {
    
     @Autowired
     private AuthService authService;

     @PatchMapping("/changePassword") 
     public ResponseEntity<?> changePassword (@RequestBody changePasswordRequest request,Principal conecterUser){

        return  ResponseEntity.ok(authService.changePassword(request,conecterUser));
        
     }

     @PostMapping("/login") 
     public ResponseEntity<AuthResponse> Login (@RequestBody LoginRequest request){

        return ResponseEntity.ok(authService.Login(request));

     }

    @PostMapping("/register")
     public ResponseEntity<?> Register (@RequestBody RegisterRequest request) throws UnsupportedEncodingException{
            
         return ResponseEntity.ok(authService.Register(request));   
          
     } 
}
