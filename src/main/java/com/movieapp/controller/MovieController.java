package com.movieapp.controller;

import com.movieapp.entity.SearchHistory;
import com.movieapp.entity.User;
import com.movieapp.repository.UserRepository;
import com.movieapp.service.MovieService;
import com.movieapp.service.SearchHistoryService;
import com.movieapp.config.JwtUtil;
import com.movieapp.dto.MovieResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
@CrossOrigin("*")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @Autowired
    private SearchHistoryService historyService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;

    // 🔍 SEARCH MOVIES + SAVE HISTORY
    @GetMapping
    public MovieResponse searchMovies(@RequestParam String name,
                                      @RequestHeader(value = "Authorization", required = false) String token) {

        try {
            if (token == null || !token.startsWith("Bearer ")) {
                throw new RuntimeException("Invalid or Missing Token");
            }

            token = token.substring(7);

            String email = jwtUtil.extractUsername(token);
            User user = userRepository.findByEmail(email);

            if (user == null) {
                throw new RuntimeException("User not found");
            }

            historyService.save(name, user);

            return movieService.searchMovies(name);

        } catch (Exception e) {
            e.printStackTrace();
            return new MovieResponse();
        }
    }
    // 🎬 MOVIE DETAILS
    @GetMapping("/details")
    public Object getMovieDetails(@RequestParam String id) {
        return movieService.getMovieDetails(id);
    }

    // 🕘 GET USER SEARCH HISTORY
    @GetMapping("/history")
    public List<SearchHistory> getHistory(@RequestHeader("Authorization") String token) {

        token = token.substring(7);

        String email = jwtUtil.extractUsername(token);
        User user = userRepository.findByEmail(email);

        return historyService.getByUser(user);
    }

    @DeleteMapping("/history/{id}")
    public void deleteHistory(@PathVariable Long id,
                              @RequestHeader("Authorization") String token) {

        token = token.substring(7);

        String email = jwtUtil.extractUsername(token);
        User user = userRepository.findByEmail(email);

        historyService.delete(id, user);
    }

    // 🧹 CLEAR ALL HISTORY
    @DeleteMapping("/history/clear")
    public void clearHistory(@RequestHeader("Authorization") String token) {

        token = token.substring(7);

        String email = jwtUtil.extractUsername(token);
        User user = userRepository.findByEmail(email);

        historyService.clearUserHistory(user);
    }
}