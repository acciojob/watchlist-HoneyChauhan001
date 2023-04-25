package com.driver;

public class DirectorNotPresent extends RuntimeException {
    public DirectorNotPresent(String directorName) {
        super("Director: "+directorName+" does not exist in data");
    }
}
