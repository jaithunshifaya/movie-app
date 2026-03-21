package com.movieapp.service;

import com.movieapp.dto.MovieResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MovieService {

    @Value("${omdb.api.key}")
    private String apiKey;

    @Autowired
    private RestTemplate restTemplate;

    // 🔍 SEARCH MOVIES (ONLY API CALL)
    public MovieResponse searchMovies(String name) {

        String url = "http://www.omdbapi.com/?s=" + name + "&apikey=" + apiKey;
        return restTemplate.getForObject(url, MovieResponse.class);
    }

    // 🎬 MOVIE DETAILS
    public Object getMovieDetails(String imdbId) {
        String url = "http://www.omdbapi.com/?i=" + imdbId + "&apikey=" + apiKey;
        return restTemplate.getForObject(url, Object.class);
    }
}