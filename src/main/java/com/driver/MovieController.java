package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    MovieService movieService;
    @PostMapping("/add-movie")
    public ResponseEntity addMovie(@RequestBody Movie movie){

        try{
            boolean added = movieService.addMovie(movie);
            return new ResponseEntity("added Successfully", HttpStatus.OK);
        } catch (MovieAlreadyExist e){
            return new ResponseEntity("added Successfully", HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping("/add-director")
    public ResponseEntity addDirector(@RequestBody Director director){

        try{
            boolean added = movieService.addDirector(director);
            return new ResponseEntity("added Successfully", HttpStatus.OK);
        } catch (DirectorAlreadyExist e){
            return new ResponseEntity("added Successfully", HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping("/add-movie-director-pair")
    public ResponseEntity addMovieDirectorPair(@RequestParam String movieName, @RequestParam String directorName){

        try{
            boolean added = movieService.addMovieDirectorPair(movieName,directorName);
            return new ResponseEntity("added Successfully",HttpStatus.OK);
        } catch (MovieNotPresent e){
            return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
        } catch (DirectorNotPresent e){
            return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
        } catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/get-movie-by-name/{name}")
    public ResponseEntity getMovieByName(@PathVariable String name){
        try{
            Optional<Movie> movieOpt = movieService.getMovieByName(name);
            return new ResponseEntity(movieOpt.get(),HttpStatus.FOUND);
        } catch (MovieNotPresent ex){
            return new ResponseEntity(ex.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/get-director-by-name/{name}")
    public ResponseEntity getDirectorByName(@PathVariable String name){
        try{
            Optional<Director> directorOpt = movieService.getDirectorByName(name);
            return new ResponseEntity(directorOpt.get(),HttpStatus.FOUND);
        } catch (DirectorNotPresent e){
            return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/get-movies-by-director-name/{director}")
    public ResponseEntity getMoviesByDirectorName(@PathVariable String director){

        try{
            List<Movie> ans = movieService.getMoviesByDirectorName(director);
            return new ResponseEntity(ans,HttpStatus.OK);
        } catch(DirectorNotPresent e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/get-all-movies")
    public ResponseEntity findAllMovies(){

        List<Movie> ans = movieService.findAllMovies();
        return new ResponseEntity(ans,HttpStatus.OK);
    }

    @DeleteMapping("/delete-director-by-name")
    public ResponseEntity deleteDirectorByName(@RequestParam String name){

        try {
            boolean deleted = movieService.deleteDirectorByName(name);
            return new ResponseEntity("Deleted Successfully", HttpStatus.OK);
        } catch(DirectorNotPresent e){
            return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
        }

    }

    @DeleteMapping("/delete-all-directors")
    public ResponseEntity deleteAllDirectors(){

        boolean deleted = movieService.deleteAllDirectors();
        return new ResponseEntity("Deleted Successfully", HttpStatus.OK);
    }




}
