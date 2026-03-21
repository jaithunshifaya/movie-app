package com.movieapp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class MovieResponse {

    @JsonProperty("Search")
    private List<Movie> search;

    @JsonProperty("totalResults")
    private String totalResults;

    @JsonProperty("Response")
    private String response;

    // INNER CLASS
    public static class Movie {

        @JsonProperty("Title")
        private String title;

        @JsonProperty("Year")
        private String year;

        @JsonProperty("imdbID")
        private String imdbID;

        @JsonProperty("Type")
        private String type;

        @JsonProperty("Poster")
        private String poster;

        // GETTERS
        public String getTitle() { return title; }
        public String getYear() { return year; }
        public String getImdbID() { return imdbID; }
        public String getType() { return type; }
        public String getPoster() { return poster; }

        // SETTERS
        public void setTitle(String title) { this.title = title; }
        public void setYear(String year) { this.year = year; }
        public void setImdbID(String imdbID) { this.imdbID = imdbID; }
        public void setType(String type) { this.type = type; }
        public void setPoster(String poster) { this.poster = poster; }
    }

    // GETTERS
    public List<Movie> getSearch() { return search; }
    public String getTotalResults() { return totalResults; }
    public String getResponse() { return response; }

    // SETTERS
    public void setSearch(List<Movie> search) { this.search = search; }
    public void setTotalResults(String totalResults) { this.totalResults = totalResults; }
    public void setResponse(String response) { this.response = response; }
}