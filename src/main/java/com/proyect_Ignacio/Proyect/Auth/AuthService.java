package com.proyect_Ignacio.Proyect.Auth;


import java.security.Principal;
import java.util.Optional;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.proyect_Ignacio.Proyect.Exception.UserAlreadyExistException;
import com.proyect_Ignacio.Proyect.Exception.UserNotFoundException;
import com.proyect_Ignacio.Proyect.Requests.LoginRequest;
import com.proyect_Ignacio.Proyect.Requests.RegisterRequest;
import com.proyect_Ignacio.Proyect.Requests.changePasswordRequest;
import com.proyect_Ignacio.Proyect.User.User;
import com.proyect_Ignacio.Proyect.User.UserRepository;
import com.proyect_Ignacio.Proyect.User.UserRole;
import com.proyect_Ignacio.Proyect.jwt.JwtService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor 
public class AuthService {

    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;

    public AuthResponse Login(LoginRequest request) {

        Optional<User> value = userRepository.findByEmail(request.getEmail());

        if(value.isEmpty()){
            throw new UserNotFoundException();
        }
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        
        UserDetails userDetails = userRepository.findByEmail(request.getEmail()).orElseThrow();  
        //Generate the token 
        String token = jwtService.getToken(userDetails);
        
        return AuthResponse.builder()
               .token(token)
               .build();
               
    }

    public String Register( RegisterRequest request ){
        
          Optional<User> value = userRepository.findByEmail(request.getEmail());

        if(value.isPresent()){
            throw new UserAlreadyExistException();
        }

           User user = new User();
           user.setEmail(request.getEmail());
           user.setFirstname(request.getFirstname()); 
           user.setPassword(passwordEncoder.encode(request.getPassword()));
           user.setRole(UserRole.USER);

          userRepository.save(user);
          return "User successfully registered";
    }


    public String changePassword(changePasswordRequest request, Principal connectedUser) {
       
        var user = (User) ((UsernamePasswordAuthenticationToken)connectedUser).getPrincipal();
        
       //check if the current password is correct respected from database
       if(!passwordEncoder.matches(request.getCurrentPassword(),user.getPassword())){

        throw new IllegalStateException("Wrong password");

       }

       //check if the new password isn´t equals to the config password
       if (!request.getNewPassword().equals( request.getValidatePassword() )) {
        throw new IllegalStateException("Password are not the same");
       }
       user.setPassword(passwordEncoder.encode(request.getNewPassword()));
       
       userRepository.save(user);
       return "Password has been changed";

    }

}  


