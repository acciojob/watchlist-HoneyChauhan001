package com.driver;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MovieRepository {

    Map<String,Movie> movieMap = new HashMap<>();
    Map<String,Director> directorMap = new HashMap<>();
    Map<String, List<String>> directorMovieMap = new HashMap<>();

    public Optional<Movie> getMovieByName(String name) {
        if(movieMap.containsKey(name)){
            return Optional.of(movieMap.get(name));
        }
        else return Optional.empty();
    }

    public boolean addMovie(Movie movie) {
        movieMap.put(movie.getName(),movie);
        return true;
    }

    public Optional<Director> getDirectorByName(String name) {
        if(directorMap.containsKey(name)){
            return Optional.of(directorMap.get(name));
        }
        else return Optional.empty();
    }

    public boolean addDirector(Director director) {
        directorMap.put(director.getName(),director);
        return true;
    }

    public boolean addMovieDirectorPair(String movie, String director) {
        if(directorMovieMap.containsKey(director)){
            List<String> oldlist = directorMovieMap.get(director);
            oldlist.add(movie);
            directorMovieMap.put(director,oldlist);
        }
        else {
            List<String> newList = new ArrayList<>();
            newList.add(movie);
            directorMovieMap.put(director,newList);
        }
        return true;
    }

    public List<String> getMoviesByDirectorName(String director) {
        if(directorMovieMap.containsKey(director)){
            return directorMovieMap.get(director);
        } else return new ArrayList<>();
    }

    public List<String> findAllMovies() {
        List<String> list = new ArrayList<String>(movieMap.keySet());
        return list;
    }
    public List<String> findAllDirectors() {
        List<String> list = new ArrayList<String>(directorMap.keySet());
        return list;
    }

    public void deleteMovie(String movie) {
        movieMap.remove(movie);
    }

    public void deleteDirector(String name) {
        directorMovieMap.remove(name);
        directorMap.remove(name);
    }
}
