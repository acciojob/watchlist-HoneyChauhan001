package com.driver;

public class MovieNotPresent extends RuntimeException {
    public MovieNotPresent(String movieName) {
        super("Movie: " + movieName + " does not exist in data");
    }
}
