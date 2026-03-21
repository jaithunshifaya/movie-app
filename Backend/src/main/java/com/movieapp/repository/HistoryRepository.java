package com.movieapp.repository;

import com.movieapp.entity.SearchHistory;
import com.movieapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HistoryRepository extends JpaRepository<SearchHistory, Long> {

    List<SearchHistory> findByUser(User user);
}