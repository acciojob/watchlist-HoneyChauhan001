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
            boolean added= movieRepository.addMovie(movie);
            return added;
        } else {
            throw new MovieAlreadyExist(movie.getName());
        }
    }
    public boolean addDirector(Director director) throws DirectorAlreadyExist{
        Optional<Director> directorOpt = movieRepository.getDirectorByName(director.getName());
        if(directorOpt.isEmpty()){
            boolean added= movieRepository.addDirector(director);
            return added;
        } else {
            throw new DirectorAlreadyExist(director.getName());
        }
    }

    public boolean addMovieDirectorPair(String movieName, String directorName) throws MovieNotPresent,DirectorNotPresent {
        Optional<Movie> movieOpt = this.getMovieByName(movieName);
        Optional<Director> directorOpt = this.getDirectorByName(directorName);

        boolean added = movieRepository.addMovieDirectorPair(movieOpt.get(),directorOpt.get());
        int numOfMovie = directorOpt.get().getNumberOfMovies() + 1;
        directorOpt.get().setNumberOfMovies(numOfMovie);
        return added;
    }

    public List<Movie> getMoviesByDirectorName(String director) throws DirectorNotPresent{
        Optional<Director> directorOpt = this.getDirectorByName(director);

        List<Movie> movieList = movieRepository.getMoviesByDirectorName(directorOpt.get());
        return movieList;
    }

    public List<Movie> findAllMovies() {
        List<Movie> ans = movieRepository.findAllMovies();
        return ans;
    }

    public boolean deleteDirectorByName(String name) throws DirectorNotPresent {
        Optional<Director> directorOpt = this.getDirectorByName(name);
        boolean deleted = movieRepository.deleteDirectorByName(directorOpt.get());
        return true;
    }

    public boolean deleteAllDirectors() {
        List<Director> directorList = movieRepository.findAllDirectors();

        for(Director director : directorList){
            movieRepository.deleteDirectorByName((director));
        }
        return true;
    }
}
