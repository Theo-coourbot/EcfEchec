package com.echec.demo.exeption;

public class UserNotExistException extends Exception{
    public UserNotExistException() {
        super("User(s) Not Exist");
    }
}
