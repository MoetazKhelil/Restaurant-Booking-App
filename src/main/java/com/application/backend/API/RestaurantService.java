package com.application.backend.API;


import com.application.backend.model.RestaurantItems;
import com.google.gson.Gson;
import org.json.JSONArray;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class RestaurantService {
    RestTemplate restTemplate = new RestTemplate();

    private final String ApiURL = "http://localhost:8080/api/";


    public List<RestaurantItems> getAll() {
        String rawJson = restTemplate.getForObject(ApiURL + "all", String.class);
        return parseJson(rawJson);
    }


    public RestaurantItems getById(String id) {
        String rawJson = restTemplate.getForObject(ApiURL + id, String.class);
        return parseJson(rawJson).get(0);
    }

    public void putById(RestaurantItems restaurantUpdated) {
        String json = parseObject(restaurantUpdated);
        restTemplate.put(ApiURL + restaurantUpdated.getId(),json,RestaurantItems.class);
    }
    private String parseObject(RestaurantItems restaurantItem) {
        Gson g = new Gson();
        String json = g.toJson(restaurantItem);
        return json;
    }

    private List<RestaurantItems> parseJson(String rawJson) {
        List<RestaurantItems> list = new ArrayList<>();
        JSONArray restaurants = new JSONArray(rawJson);
        Gson g = new Gson();

        for (int i = 0; i < restaurants.length(); i++) {
            String restaurantObject = restaurants.getJSONObject(i).toString();
            RestaurantItems restaurantItem = g.fromJson(restaurantObject, RestaurantItems.class);

            list.add(restaurantItem);
        }
        return list;
    }

    public String getURL(HttpServletRequest request) {
        return request.getRequestURI();
    }
}


