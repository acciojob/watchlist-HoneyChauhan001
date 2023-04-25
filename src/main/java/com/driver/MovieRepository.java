package com.driver;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MovieRepository {

    private Map<String,Movie> movieMap;
    private Map<String,Director> directorMap;
    private Map<String, List<String>> directorMovieMap;

    public MovieRepository() {
        this.movieMap = new HashMap<>();
        this.directorMap = new HashMap<>();
        this.directorMovieMap = new HashMap<>();
    }

    public Optional<Movie> getMovieByName(String name) {
        if(movieMap.containsKey(name)){
            return Optional.of(movieMap.get(name));
        }
        else return Optional.empty();
    }

    public void addMovie(Movie movie) {
        movieMap.put(movie.getName(),movie);
    }

    public Optional<Director> getDirectorByName(String name) {
        if(directorMap.containsKey(name)){
            return Optional.of(directorMap.get(name));
        }
        else return Optional.empty();
    }

    public void addDirector(Director director) {
        directorMap.put(director.getName(),director);
    }

    public void addMovieDirectorPair(String  movie, String director) {
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

    public void deleteDirectorByName(String name) {
        if(directorMovieMap.containsKey(name)){
            List<String> list = directorMovieMap.get(name);
            for(String movie : list){
                movieMap.remove(movie);
            }
            directorMovieMap.remove(name);
            directorMap.remove(name);
        }
        else {
            directorMap.remove(name);
        }
    }

    public List<String> findAllDirectors() {
        List<String> list = new ArrayList<String>(directorMap.keySet());
        return list;
    }
}
