package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    @Autowired
    MovieRepository movieRepository;

    public Optional<Movie> getMovieByName(String name) throws MovieNotPresent{
        Optional<Movie> movieOpt = movieRepository.getMovieByName(name);
        if(movieOpt.isEmpty()){
            throw new MovieNotPresent(name);
        } else {
            return movieOpt;
        }
    }
    public Optional<Director> getDirectorByName(String name) throws DirectorNotPresent {
        Optional<Director> directorOpt = movieRepository.getDirectorByName(name);
        if(directorOpt.isEmpty()){
            throw new DirectorNotPresent(name);
        } else {
            return directorOpt;
        }
    }
    public void addMovie(Movie movie) {
        movieRepository.addMovie(movie);
    }
    public void addDirector(Director director){
        movieRepository.addDirector(director);

    }

    public void addMovieDirectorPair(String movieName, String directorName) throws MovieNotPresent,DirectorNotPresent {
        Optional<Movie> movieOpt = this.getMovieByName(movieName);
        Optional<Director> directorOpt = this.getDirectorByName(directorName);

        if(movieOpt.isPresent() && directorOpt.isPresent()){
            movieRepository.addMovieDirectorPair(movieName,directorName);
        }
    }

    public List<String> getMoviesByDirectorName(String director) {
        List<String> movieList = movieRepository.getMoviesByDirectorName(director);
        return movieList;
    }

    public List<String> findAllMovies() {
        List<String> ans = movieRepository.findAllMovies();
        return ans;
    }

    public void deleteDirectorByName(String name) throws DirectorNotPresent {

        List<String> movieList = this.getMoviesByDirectorName(name);
        for(String movie : movieList){
            movieRepository.deleteMovie(movie);
        }
        movieRepository.deleteDirector(name);
        movieRepository.deleteDirectorByName(name);
    }

    public void deleteAllDirectors() {
        List<String> directorList = movieRepository.findAllDirectors();

        for(String director : directorList){
            movieRepository.deleteDirectorByName(director);
        }
    }
}
