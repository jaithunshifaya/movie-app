package com.movieapp.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "search_history")
public class SearchHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

   
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String searchQuery;
    private LocalDateTime searchedAt;

    
    public int getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public String getSearchQuery() {
        return searchQuery;
    }

    public LocalDateTime getSearchedAt() {
        return searchedAt;
    }

   
    public void setId(int id) {
        this.id = id;
    }

    public void setUser(User user) {   
        this.user = user;
    }

    public void setSearchQuery(String searchQuery) {
        this.searchQuery = searchQuery;
    }

    public void setSearchedAt(LocalDateTime searchedAt) {
        this.searchedAt = searchedAt;
    }
}