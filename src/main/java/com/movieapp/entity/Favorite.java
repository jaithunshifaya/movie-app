package com.movieapp.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "favorites")
public class Favorite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String imdbID;
    private String title;
    private String poster;
    private String year;

    // ✅ ADD THIS
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // getters & setters
    public int getId() { return id; }

    public String getImdbID() { return imdbID; }

    public String getTitle() { return title; }

    public String getPoster() { return poster; }

    public String getYear() { return year; }

    public User getUser() { return user; }

    public void setId(int id) { this.id = id; }

    public void setImdbID(String imdbID) { this.imdbID = imdbID; }

    public void setTitle(String title) { this.title = title; }

    public void setPoster(String poster) { this.poster = poster; }

    public void setYear(String year) { this.year = year; }

    public void setUser(User user) { this.user = user; }
}