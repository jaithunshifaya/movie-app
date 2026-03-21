package com.movieapp.service;

import com.movieapp.entity.SearchHistory;
import com.movieapp.entity.User;
import com.movieapp.repository.HistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SearchHistoryService {

    @Autowired
    private HistoryRepository repo;

    public void save(String query, User user) {
        SearchHistory history = new SearchHistory();
        history.setSearchQuery(query);
        history.setSearchedAt(LocalDateTime.now());
        history.setUser(user);

        repo.save(history);
    }

    public List<SearchHistory> getByUser(User user) {
        return repo.findByUser(user);
    }

    // ❌ DELETE ONE
    public void delete(Long id, User user) {
        SearchHistory h = repo.findById(id).orElseThrow();

        if (h.getUser().getId() != user.getId()) {
            throw new RuntimeException("Unauthorized");
        }

        repo.delete(h);
    }

    // ❌ CLEAR ALL
    public void clear(User user) {
        List<SearchHistory> list = repo.findByUser(user);
        repo.deleteAll(list);
    }

    public void deleteById(int id) {
        repo.deleteById((long) id);
    }

    public void clearUserHistory(User user) {
        List<SearchHistory> list = repo.findByUser(user);
        repo.deleteAll(list);
    }
}