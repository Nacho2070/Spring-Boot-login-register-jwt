package com.proyect_Ignacio.Proyect.Exception;

public class UserNotFoundException  extends RuntimeException{
    public UserNotFoundException(){

        super("User doesn't exists");

    }
}
