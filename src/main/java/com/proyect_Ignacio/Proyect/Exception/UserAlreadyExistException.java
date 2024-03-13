package com.proyect_Ignacio.Proyect.Exception;

public class UserAlreadyExistException extends RuntimeException {

   public UserAlreadyExistException(){
      super("user already exist");
    }

}
