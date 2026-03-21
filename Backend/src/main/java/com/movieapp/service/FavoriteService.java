package com.movieapp.service;

import com.movieapp.entity.Favorite;
import com.movieapp.entity.User;
import com.movieapp.repository.FavoriteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoriteService {

    @Autowired
    private FavoriteRepository repo;

    public Favorite save(Favorite fav) {
        return repo.save(fav);
    }

    public List<Favorite> getByUser(User user) {
        return repo.findByUser(user);
    }

    public void delete(int id, User user) {
        Favorite fav = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Favorite not found"));

        if (fav.getUser().getId() != user.getId()) {
            throw new RuntimeException("Unauthorized");
        }

        repo.delete(fav);
    }

    public Favorite update(int id, Favorite updatedFav, User user) {
        Favorite fav = repo.findById(id).orElseThrow();

        if (fav.getUser().getId() != user.getId()) {
            throw new RuntimeException("Unauthorized");
        }

        fav.setTitle(updatedFav.getTitle());
        fav.setPoster(updatedFav.getPoster());
        fav.setYear(updatedFav.getYear());

        return repo.save(fav);
    }
}