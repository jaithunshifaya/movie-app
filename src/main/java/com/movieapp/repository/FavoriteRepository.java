package com.movieapp.repository;

import com.movieapp.entity.Favorite;
import com.movieapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavoriteRepository extends JpaRepository<Favorite, Integer> {

    List<Favorite> findByUser(User user);

    void deleteByIdAndUser(int id, User user);
}