package com.application.backend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.vaadin.addon.googlemap.GoogleMapMarker;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

// TODO den Document Input zurückändern

@Document("finalRestaurantItems") //restaurantItems
public class RestaurantItems {

    @Id
    private String id;

    @Field("name")
    private String name;
    private String website;
    private String address;

    private String cuisineType;

    private String phoneNumber;

    private String imageURL;

    private double rating;
    private ArrayList<Table> tables;

    private int priceCategory;
    private OpeningHours openingHours;

    private GoogleMapMarker googleMapMarker;

    public RestaurantItems(String id, String name, String website, String address, String cuisineType, String phoneNumber, String imageURL, double rating, ArrayList<Table> tables, int priceCategory, OpeningHours openingHours) {
        this.id = id;
        this.name = name;
        this.website = website;
        this.address = address;
        this.cuisineType = cuisineType;
        this.phoneNumber = phoneNumber;
        this.imageURL = imageURL;
        this.rating = rating;
        this.tables = tables;
        this.priceCategory = priceCategory;
        this.openingHours = openingHours;
    }

    public OpeningHours getOpeningHours() {
        return openingHours;
    }

    public void setOpeningHours(OpeningHours openingHours) {
        this.openingHours = openingHours;
    }

    public ArrayList<Table> getTables() {
        return tables;
    }

    public void setTables(ArrayList<Table> tables) {
        this.tables = tables;
    }

    public String getImageURL() {
        return imageURL;
    }

    public String getCuisineType() {
        return cuisineType;
    }

    public void setCuisineType(String cuisineType) {
        this.cuisineType = cuisineType;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getPriceCategory() {
        return priceCategory;
    }

    public void setPriceCategory(int priceCategory) {
        this.priceCategory = priceCategory;
    }

    public void setImageSrc(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setGoogleMapMarker(GoogleMapMarker googleMapMarker) {
        this.googleMapMarker = googleMapMarker;
    }

    public GoogleMapMarker getGoogleMapMarker() {
        return googleMapMarker;
    }
}
