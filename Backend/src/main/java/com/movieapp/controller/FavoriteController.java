package com.movieapp.controller;

import com.movieapp.entity.Favorite;
import com.movieapp.entity.User;
import com.movieapp.repository.UserRepository;
import com.movieapp.service.FavoriteService;
import com.movieapp.config.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/favorites")
@CrossOrigin("*")
public class FavoriteController {

    @Autowired
    private FavoriteService service;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;

    
    @PostMapping
    public Favorite save(@RequestBody Favorite fav,
                         @RequestHeader("Authorization") String token) {

        String email = jwtUtil.extractUsername(token);
        User user = userRepository.findByEmail(email);

        fav.setUser(user);
        return service.save(fav);
    }

    
    @GetMapping
    public List<Favorite> getAll(@RequestHeader("Authorization") String token) {

        String email = jwtUtil.extractUsername(token);
        User user = userRepository.findByEmail(email);

        return service.getByUser(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id,
                                    @RequestHeader("Authorization") String token) {
        try {
            String email = jwtUtil.extractUsername(token);
            User user = userRepository.findByEmail(email);

            service.delete(id, user);

            return ResponseEntity.ok("Deleted successfully");

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Delete failed");
        }
    }
    
    @PutMapping("/{id}")
    public Favorite update(@PathVariable int id,
                           @RequestBody Favorite fav,
                           @RequestHeader("Authorization") String token) {

        String email = jwtUtil.extractUsername(token);
        User user = userRepository.findByEmail(email);

        return service.update(id, fav, user);
    }
}