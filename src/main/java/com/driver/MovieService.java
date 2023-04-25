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
    
    public boolean addMovie(Movie movie) throws MovieAlreadyExist {
        Optional<Movie> movieOpt = movieRepository.getMovieByName(movie.getName());
        if(movieOpt.isEmpty()){
            movieRepository.addMovie(movie);
            return true;
        } else {
            throw new MovieAlreadyExist(movie.getName());
        }
    }
    public boolean addDirector(Director director) throws DirectorAlreadyExist{
        Optional<Director> directorOpt = movieRepository.getDirectorByName(director.getName());
        if(directorOpt.isEmpty()){
            movieRepository.addDirector(director);
            return true;
        } else {
            throw new DirectorAlreadyExist(director.getName());
        }
    }

    public boolean addMovieDirectorPair(String movieName, String directorName) throws MovieNotPresent,DirectorNotPresent {
        this.getMovieByName(movieName);
        Optional<Director> directorOpt = this.getDirectorByName(directorName);

        movieRepository.addMovieDirectorPair(movieName,directorName);
        int numOfMovie = directorOpt.get().getNumberOfMovies() + 1;
        directorOpt.get().setNumberOfMovies(numOfMovie);
        return true;
    }

    public List<String> getMoviesByDirectorName(String director) throws DirectorNotPresent{
        this.getDirectorByName(director);
        List<String> movieList = movieRepository.getMoviesByDirectorName(director);
        return movieList;
    }

    public List<String> findAllMovies() {
        List<String> ans = movieRepository.findAllMovies();
        return ans;
    }

    public boolean deleteDirectorByName(String name) throws DirectorNotPresent {

        List<String> movieList = movieRepository.getMoviesByDirectorName(name);
        for(String movie : movieList){
            movieRepository.deleteMovie(movie);
        }
        movieRepository.deleteDirector(name);
        return true;
    }

    public boolean deleteAllDirectors() {

        List<String> directorList = movieRepository.findAllDirectors();

        for(String director : directorList){
            this.deleteDirectorByName(director);
        }
        return true;
    }
}
