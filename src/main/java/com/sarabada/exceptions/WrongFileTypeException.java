package com.sarabada.exceptions;

public class WrongFileTypeException extends Exception {
    public WrongFileTypeException(String required, String found) {
        super ("File type not expected. Required: "
                +required+", found: "+found+".");
    }
}
