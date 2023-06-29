package com.echec.demo.exeption;

public class UserExistException extends Exception{
    public UserExistException() {
        super("pseudo Exist");
    }
}
