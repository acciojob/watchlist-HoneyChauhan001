package com.driver;

public class MovieAlreadyExist extends RuntimeException{
    public MovieAlreadyExist(String name) {
        super("Movie: " + name + " already exists in data");
    }
}
