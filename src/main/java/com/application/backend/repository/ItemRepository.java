package com.application.backend.repository;


import com.application.backend.model.RestaurantItems;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ItemRepository extends MongoRepository<RestaurantItems, String> {

    List<RestaurantItems> findItemByName(String name);

    //List<RestaurantItems> findItemByCategory(String category);


}