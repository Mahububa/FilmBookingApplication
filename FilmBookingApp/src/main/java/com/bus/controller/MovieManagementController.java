package com.bus.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bus.beans.MovieDetails;
import com.bus.service.MovieRepo;



@Controller
public class MovieManagementController {

    @Autowired
    private MovieRepo movieRepo;

    @GetMapping("/movie_management")
    public String movieManagement(Model model) {
        List<MovieDetails> movies = movieRepo.findAll(); // Retrieve all movies from the database
        model.addAttribute("movies", movies); // Add movies to the model
        return "movie_management";
    }
    
    @GetMapping("/add_movie")
    public String showAddMovieForm(Model model) {
        model.addAttribute("movieDetails", new MovieDetails());
        return "addmovie"; // This will direct to the Add New Movie form
    }

    @RequestMapping("/add_movie")
    public String addMovie(@ModelAttribute MovieDetails description, Model model) {
        try {
            // Save the new movie details to the database
            movieRepo.save(description); // Update parameter name
            model.addAttribute("message", "Movie added successfully!");
        } catch (Exception e) {
            model.addAttribute("error", "Failed to add movie. Please try again.");
        }
        return "redirect:/movie_management";
    }
    
    @GetMapping("/edit_movie/{movieId}")
    public String showEditMovieForm(@PathVariable("movieId") long movieId, Model model) {
        Optional<MovieDetails> optionalMovieDetails = movieRepo.findById(movieId);
        
        if (optionalMovieDetails.isPresent()) {
            MovieDetails movieDetails = optionalMovieDetails.get();
            model.addAttribute("movieDetails", movieDetails);
            return "edit";
        } else {
            return "redirect:/movie_management"; // Redirect to movie management if movie is not found
        }
    }

    @PostMapping("/edit_movie")
    public String editMovie(@ModelAttribute MovieDetails description, Model model) {
        try {
            // Update the movie details in the database
            movieRepo.save(description);
            model.addAttribute("message", "Movie details updated successfully!");
        } catch (Exception e) {
            model.addAttribute("error", "Failed to update movie details. Please try again.");
        }
        return "redirect:/movie_management";
    }
    @GetMapping("/delete_movie/{movieId}")
    public String deleteMovie(@PathVariable Long movieId) {
        try {
            movieRepo.deleteById(movieId);
        } catch (Exception e) {
            // Handle exceptions if needed
            e.printStackTrace(); // Example: print the exception to the console
        }
        return "redirect:/movie_management";
    }

    
}