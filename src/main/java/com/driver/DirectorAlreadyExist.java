package com.driver;

public class DirectorAlreadyExist extends RuntimeException {
    public DirectorAlreadyExist(String name) {
        super("Director: "+name+" already exists");
    }
}
