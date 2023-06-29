package com.echec.demo.exeption;

public class TournamentNotExistException extends Exception{
    public TournamentNotExistException() {
        super("tournament not Exist");
    }
}
