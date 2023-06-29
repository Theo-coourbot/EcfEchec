package com.echec.demo.exeption;

public class NotSignInException extends Exception{
    public NotSignInException() {
        super("Not Logged");
    }
}
