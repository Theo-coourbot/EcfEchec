package com.echec.demo.exeption;

public class NotAdminException extends Exception{
    public NotAdminException() {
        super("Not Admin");
    }
}
