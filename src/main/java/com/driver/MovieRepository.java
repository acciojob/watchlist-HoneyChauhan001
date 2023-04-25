package com.driver;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MovieRepository {

    Map<String,Movie> movieMap = new HashMap<>();
    Map<String,Director> directorMap = new HashMap<>();
    Map<Director, List<Movie>> directorMovieMap = new HashMap<>();

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

    public boolean addMovieDirectorPair(Movie movie, Director director) {
        if(directorMovieMap.containsKey(director)){
            List<Movie> oldlist = directorMovieMap.get(director);
            oldlist.add(movie);
            directorMovieMap.put(director,oldlist);
        }
        else {
            List<Movie> newList = new ArrayList<>();
            newList.add(movie);
            directorMovieMap.put(director,newList);
        }
        return true;
    }

    public List<Movie> getMoviesByDirectorName(Director director) {
        if(directorMovieMap.containsKey(director)){
            return directorMovieMap.get(director);
        } else return new ArrayList<>();
    }

    public List<Movie> findAllMovies() {
        List<Movie> list = new ArrayList<Movie>(movieMap.values());
        return list;
    }

    public boolean deleteDirectorByName(Director director) {
        if(directorMovieMap.containsKey(director)){
            List<Movie> list = directorMovieMap.get(director);
            for(Movie movie : list){
                String name = movie.getName();
                movieMap.remove(name);
            }
            directorMovieMap.remove(director);
            directorMap.remove(director.getName());
        }
        else {
            directorMap.remove(director.getName());
        }
        return true;
    }

    public List<Director> findAllDirectors() {
        List<Director> list = new ArrayList<Director>(directorMap.values());
        return list;
    }
}
