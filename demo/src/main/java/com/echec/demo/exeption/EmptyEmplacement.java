package com.echec.demo.exeption;

public class EmptyEmplacement extends Exception{
    public EmptyEmplacement() {
        super("remplir tout les champs et assigner 2 joueurs");
    }
}
