package com.application.backend.API;


import com.application.backend.model.RestaurantItems;
import com.application.backend.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class RestaurantController {

    @Autowired
    ItemRepository restaurantItemRepo;

    @GetMapping("/api/all")
    public List<RestaurantItems> all() {
        return restaurantItemRepo.findAll().stream().toList();
    }

    @GetMapping("/api/name/{name}")
    public List<RestaurantItems> findName(@PathVariable String name) {
        return restaurantItemRepo.findItemByName(name);
    }

    @GetMapping("/api/{id}")
    public List<RestaurantItems> findId(@PathVariable String id) {
        return restaurantItemRepo.findById(id).stream().toList();
    }

}