package com.example.LinguaLens;

//Useful utilities go here.

public class Utilities {
    public void print(String message) {
        System.out.println(message);
    }

    public void criticalError(String message)
    {
        UI.showDialogue(message, "OK", 0);
        System.exit(1);
    }

    public void criticalError()
    {
        UI.showDialogue("A critical error has occurred. Please restart the program and try again.", "OK", 0);
        System.exit(1);
    }
}