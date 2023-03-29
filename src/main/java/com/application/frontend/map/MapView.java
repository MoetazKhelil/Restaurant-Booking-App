package com.application.frontend.map;

import com.application.backend.API.RestaurantService;
import com.application.backend.model.RestaurantItems;
import com.application.frontend.MainLayout;
import com.vaadin.flow.component.accordion.Accordion;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.CheckboxGroup;
import com.vaadin.flow.component.checkbox.CheckboxGroupVariant;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.radiobutton.RadioGroupVariant;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import org.vaadin.addon.googlemap.GoogleMap;
import org.vaadin.addon.googlemap.GoogleMapMarker;

import java.util.ArrayList;
import java.util.List;

import java.util.Locale;
import java.util.stream.Collectors;


@PageTitle("Map view")
@Route(value = "mapview", layout = MainLayout.class)


public class MapView extends FlexLayout {

    RestaurantService restaurantService = new RestaurantService();
    GoogleMap googleMap = new GoogleMap("AIzaSyCYFo2MV3fPY7sqWBCrxyt3GKMqzPptPmU");
    public MapView() {

        //MEL HOME VIEW
        VerticalLayout finalLayout = new VerticalLayout();
        TextField search = new TextField();
        search.setPlaceholder("Search");
        search.setPrefixComponent(VaadinIcon.SEARCH.create());
        search.addClassName("search");
        search.setClearButtonVisible(true);
        add(search);


        Accordion accordion = new Accordion();

        HorizontalLayout filter = new HorizontalLayout();
        filter.setSpacing(true);
        filter.setPadding(true);
        CheckboxGroup<String> restaurantType = new CheckboxGroup<>();
        restaurantType.setLabel("Restaurant Type");
        restaurantType.setItems("Italienisch", "Mexikanisch", "Indisch", "Asiatisch", "Fast Food", "Regional");
        restaurantType.addThemeVariants(CheckboxGroupVariant.LUMO_VERTICAL);


        CheckboxGroup<String> priceCategory = new CheckboxGroup<>();
        priceCategory.setLabel("Price Category");
        priceCategory.setItems("€€€€", "€€€", "€€", "€");
        priceCategory.addThemeVariants(CheckboxGroupVariant.LUMO_VERTICAL);

        RadioButtonGroup<String> averageRating = new RadioButtonGroup<>();
        averageRating.addThemeVariants(RadioGroupVariant.LUMO_VERTICAL);
        averageRating.setLabel("Average Rating");
        averageRating.setItems("5", "min. 4", "min. 3", "min. 2", "min. 1");


        VerticalLayout buttons = new VerticalLayout();
        buttons.setSpacing(true);
        buttons.setPadding(false);
        buttons.setAlignItems(FlexComponent.Alignment.END);

        Button apply = new Button("apply filters");
        apply.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_SUCCESS);
        //filter




        Button clear = new Button("clear filters");
        clear.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_ERROR);



        buttons.add(apply, clear);

        filter.add(restaurantType, priceCategory, averageRating, buttons);
        accordion.add("expand filters", filter);
        accordion.close();
        //add(accordion);

        FlexLayout div = new FlexLayout();
        div.getStyle().set("flex-direction", "column");
        div.setWidth("40em");
        div.setHeight("40em");
        //div.add(new Label("View Restaurants on Google Maps"));



        GoogleMapMarker marker1 =
                new GoogleMapMarker.GoogleMapMarkerBuilder(48.149860, 11.581350)
                        .withAnimation(GoogleMapMarker.Animation.DROP)
                        .withContentText("Cadu, Cafe an der Uni")
                        .withDraggable(true)
                        .build();


        GoogleMapMarker marker2 =
                new GoogleMapMarker.GoogleMapMarkerBuilder(48.149269, 11.562700)
                        .withAnimation(GoogleMapMarker.Animation.DROP)
                        .withContentText("Wirtshaus Maxvorstadt")
                        .withDraggable(true)
                        .build();


        GoogleMapMarker marker3 =
                new GoogleMapMarker.GoogleMapMarkerBuilder(48.149130, 11.563210)
                        .withAnimation(GoogleMapMarker.Animation.DROP)
                        .withContentText("Mentor's Holzofenpizza Restaurant Bar")
                        .withDraggable(true)
                        .build();

        GoogleMapMarker marker4 =
                new GoogleMapMarker.GoogleMapMarkerBuilder(48.124610, 11.580410)
                        .withAnimation(GoogleMapMarker.Animation.DROP)
                        .withContentText("Mai Garten Chinesisches Restaurant")
                        .withDraggable(true)
                        .build();

        GoogleMapMarker marker5 =
                new GoogleMapMarker.GoogleMapMarkerBuilder(48.135910, 11.584320)
                        .withAnimation(GoogleMapMarker.Animation.DROP)
                        .withContentText("Jin")
                        .withDraggable(true)
                        .build();
        GoogleMapMarker marker6 =
                new GoogleMapMarker.GoogleMapMarkerBuilder(48.1280435, 11.6048212)
                        .withAnimation(GoogleMapMarker.Animation.DROP)
                        .withContentText("Zirbel Stuben")
                        .withDraggable(true)
                        .build();
        GoogleMapMarker marker7 =
                new GoogleMapMarker.GoogleMapMarkerBuilder(48.1037229, 11.5436149)
                        .withAnimation(GoogleMapMarker.Animation.DROP)
                        .withContentText("A Casa Tua")
                        .withDraggable(true)
                        .build();
        GoogleMapMarker marker8 =
                new GoogleMapMarker.GoogleMapMarkerBuilder(48.1331567, 11.5930777)
                        .withAnimation(GoogleMapMarker.Animation.DROP)
                        .withContentText("MUN Restaurant")
                        .withDraggable(true)
                        .build();
        GoogleMapMarker marker9 =
                new GoogleMapMarker.GoogleMapMarkerBuilder(48.1433682, 11.5755136)
                        .withAnimation(GoogleMapMarker.Animation.DROP)
                        .withContentText("KOI Restaurant")
                        .withDraggable(true)
                        .build();
        GoogleMapMarker marker10 =
                new GoogleMapMarker.GoogleMapMarkerBuilder(48.0669783, 11.5397424)
                        .withAnimation(GoogleMapMarker.Animation.DROP)
                        .withContentText("Waldwirtschaft Wawi - Biergarten und Restaurant")
                        .withDraggable(true)
                        .build();
        GoogleMapMarker marker11 =
                new GoogleMapMarker.GoogleMapMarkerBuilder(48.1432037, 11.5589965)
                        .withAnimation(GoogleMapMarker.Animation.DROP)
                        .withContentText("Ristorante VI VADI Cucina Italiana")
                        .withDraggable(true)
                        .build();
        GoogleMapMarker marker12 =
                new GoogleMapMarker.GoogleMapMarkerBuilder(48.1379089, 11.5608691)
                        .withAnimation(GoogleMapMarker.Animation.DROP)
                        .withContentText("Asia Restaurant Schillerstraße")
                        .withDraggable(true)
                        .build();
        GoogleMapMarker marker13 =
                new GoogleMapMarker.GoogleMapMarkerBuilder(48.1358782, 11.5788632)
                        .withAnimation(GoogleMapMarker.Animation.DROP)
                        .withContentText("SAUSALITOS")
                        .withDraggable(true)
                        .build();
        GoogleMapMarker marker14 =
                new GoogleMapMarker.GoogleMapMarkerBuilder(48.1314385, 11.5891455)
                        .withAnimation(GoogleMapMarker.Animation.DROP)
                        .withContentText("Chandani Chowk Indisches Restaurant")
                        .withDraggable(true)
                        .build();
        GoogleMapMarker marker15 =
                new GoogleMapMarker.GoogleMapMarkerBuilder(48.1200492, 11.5734607)
                        .withAnimation(GoogleMapMarker.Animation.DROP)
                        .withContentText("TÜRKITCH Köfte & Kebap 1")
                        .withDraggable(true)
                        .build();
        GoogleMapMarker marker16 =
                new GoogleMapMarker.GoogleMapMarkerBuilder(48.1372357, 11.5635515)
                        .withAnimation(GoogleMapMarker.Animation.DROP)
                        .withContentText("Subway")
                        .withDraggable(true)
                        .build();
        GoogleMapMarker marker17 =
                new GoogleMapMarker.GoogleMapMarkerBuilder(48.1495072, 11.5725262)
                        .withAnimation(GoogleMapMarker.Animation.DROP)
                        .withContentText("Indisches Fastfood")
                        .withDraggable(true)
                        .build();
        GoogleMapMarker marker18 =
                new GoogleMapMarker.GoogleMapMarkerBuilder(48.1486272, 11.5736546)
                        .withAnimation(GoogleMapMarker.Animation.DROP)
                        .withContentText("Döner Öz Urfa")
                        .withDraggable(true)
                        .build();


        GoogleMapMarker marker19 =
                new GoogleMapMarker.GoogleMapMarkerBuilder(48.1538188, 11.5539963)
                        .withAnimation(GoogleMapMarker.Animation.DROP)
                        .withContentText("Amigos Restaurante Mexicano")
                        .withDraggable(true)
                        .build();

        GoogleMapMarker marker20 =
                new GoogleMapMarker.GoogleMapMarkerBuilder(48.1490663, 11.5524348)
                        .withAnimation(GoogleMapMarker.Animation.DROP)
                        .withContentText("Restaurant Nymphenburger Hof")
                        .withDraggable(true)
                        .build();

        GoogleMapMarker marker21 =
                new GoogleMapMarker.GoogleMapMarkerBuilder(48.1531557, 11.5409438)
                        .withAnimation(GoogleMapMarker.Animation.DROP)
                        .withContentText("Restaurant Zauberberg")
                        .withDraggable(true)
                        .build();


        GoogleMapMarker marker22 =
                new GoogleMapMarker.GoogleMapMarkerBuilder(48.1391347, 11.5632439)
                        .withAnimation(GoogleMapMarker.Animation.DROP)
                        .withContentText("Kentucky Fried Chicken")
                        .withDraggable(true)
                        .build();


        GoogleMapMarker marker23 =
                new GoogleMapMarker.GoogleMapMarkerBuilder(48.1340265, 11.5669572)
                        .withAnimation(GoogleMapMarker.Animation.DROP)
                        .withContentText("Kennedy's Bar & Restaurant")
                        .withDraggable(true)
                        .build();

        GoogleMapMarker marker24 =
                new GoogleMapMarker.GoogleMapMarkerBuilder(48.1327969, 11.5892187)
                        .withAnimation(GoogleMapMarker.Animation.DROP)
                        .withContentText("Biergarten am Muffatwerk")
                        .withDraggable(true)
                        .build();

        GoogleMapMarker marker25 =
                new GoogleMapMarker.GoogleMapMarkerBuilder(48.1388745, 11.5663615)
                        .withAnimation(GoogleMapMarker.Animation.DROP)
                        .withContentText("McDonald's")
                        .withDraggable(true)
                        .build();


        GoogleMapMarker marker26 =
                new GoogleMapMarker.GoogleMapMarkerBuilder(48.130070, 11.569760)
                        .withAnimation(GoogleMapMarker.Animation.DROP)
                        .withContentText("Palast der Winde")
                        .withDraggable(true)
                        .build();


        GoogleMapMarker marker27 =
                new GoogleMapMarker.GoogleMapMarkerBuilder(48.148260, 11.535760)
                        .withAnimation(GoogleMapMarker.Animation.DROP)
                        .withContentText("ZAPATA")
                        .withDraggable(true)
                        .build();


        GoogleMapMarker marker28 =
                new GoogleMapMarker.GoogleMapMarkerBuilder(48.157000, 11.584950)
                        .withAnimation(GoogleMapMarker.Animation.DROP)
                        .withContentText("Don Luca")
                        .withDraggable(true)
                        .build();


        GoogleMapMarker marker29 =
                new GoogleMapMarker.GoogleMapMarkerBuilder(48.137779, 11.564470)
                        .withAnimation(GoogleMapMarker.Animation.DROP)
                        .withContentText("Burger King München")
                        .withDraggable(true)
                        .build();

        GoogleMapMarker marker30 =
                new GoogleMapMarker.GoogleMapMarkerBuilder(48.139889, 11.606240)
                        .withAnimation(GoogleMapMarker.Animation.DROP)
                        .withContentText("Swagat - Indisches Restaurant München")
                        .withDraggable(true)
                        .build();

        List<GoogleMapMarker> listMarkers = List.of(marker1,marker2,marker3,marker4,marker5,
                marker6,marker7,marker8,marker9,marker10,
                marker11,marker12,marker13,marker14,marker15,marker16,marker17,
                marker18,marker19,marker20,marker21,marker22,
                marker23,marker24,marker25,marker26,marker27,
                marker28,marker29,marker30);

        setMarkersToRestaurants(restaurantService.getAll(),listMarkers);
        googleMap.setMarkers(listMarkers.toArray(new GoogleMapMarker[listMarkers.size()]));

        //search
        search.addValueChangeListener(event -> {
            String input = search.getValue();
            updateMapAfterSearch(input, restaurantService.getAll(),listMarkers);
        });

        //clear filter
        clear.addClickListener(event -> {
            restaurantType.deselectAll();
            priceCategory.deselectAll();
            averageRating.clear();
            List<String> selectedTypes = restaurantType.getSelectedItems().stream().toList();
            List<String> selectedPrices = priceCategory.getSelectedItems().stream().toList();
            String selectedRating = averageRating.getValue();
            updateMapAfterFilter(restaurantService.getAll(),selectedTypes, selectedPrices, selectedRating,listMarkers);
        });


        //apply filter
        apply.addClickListener(event -> {
            List<String> selectedTypes = restaurantType.getSelectedItems().stream().toList();
            List<String> selectedPrices = priceCategory.getSelectedItems().stream().toList();
            String selectedRating = averageRating.getValue();
            updateMapAfterFilter(restaurantService.getAll(),selectedTypes, selectedPrices, selectedRating,listMarkers);
        });

        googleMap.setZoom(8);
        googleMap.setFitToMarkers(true);
        div.add(accordion, googleMap);
        finalLayout.add(div);
        add(finalLayout);

    }


    // mapping markers to restaurants
    private void setMarkersToRestaurants(List<RestaurantItems> restaurantItems, List<GoogleMapMarker> markerList) {
        for (RestaurantItems restaurantItems1 : restaurantItems) {
            for (GoogleMapMarker googleMapMarker : markerList) {
                if (restaurantItems1.getName().equals(googleMapMarker.getContentText())) {
                    restaurantItems1.setGoogleMapMarker(googleMapMarker);
                }
            }
        }
    }
    public void updateMapAfterFilter(List<RestaurantItems> restaurantServiceAll,List<String> types, List<String> prices,
                                     String rating, List<GoogleMapMarker> googleMapMarkers) {

        setMarkersToRestaurants(restaurantServiceAll,googleMapMarkers);
        if (rating != null){
            int ratingAsInt = Integer.parseInt(rating.substring(rating.length() - 1));
            restaurantServiceAll = restaurantServiceAll.stream().filter(r -> r.getRating() >= ratingAsInt).collect(Collectors.toList());
        }
        if (types.size()!=0){
            restaurantServiceAll = restaurantServiceAll.stream().filter(r -> types.contains(r.getCuisineType())).collect(Collectors.toList());
        }

        List<Integer> pricesAsInt = new ArrayList<>();
        for (String category : prices) {
            if (category.equals("€"))
                pricesAsInt.add(1);
            if (category.equals("€€"))
                pricesAsInt.add(2);
            if (category.equals("€€€"))
                pricesAsInt.add(3);
            if (category.equals("€€€€"))
                pricesAsInt.add(4);
        }
        if (pricesAsInt.size()!=0){
            restaurantServiceAll = restaurantServiceAll.stream().filter(r -> pricesAsInt.contains(r.getPriceCategory())).collect(Collectors.toList());
        }
        List<GoogleMapMarker> list = restaurantServiceAll.stream().map(r-> r.getGoogleMapMarker()).toList();
        googleMap.setMarkers(list.toArray(new GoogleMapMarker[list.size()]));
        googleMap.updateMarkers();
    }

    public void updateMapAfterSearch(String searchValue, List<RestaurantItems> originalList, List<GoogleMapMarker> markerList) {

        setMarkersToRestaurants(originalList,markerList);

        List<RestaurantItems> result = new ArrayList<>();
        for (RestaurantItems restaurantItems : originalList) {
            if (restaurantItems.getName().toUpperCase().startsWith(searchValue.toUpperCase(Locale.ROOT)))
                result.add(restaurantItems);
        }
        List<GoogleMapMarker> list = result.stream().map(r-> r.getGoogleMapMarker()).toList();
        googleMap.setMarkers(list.toArray(new GoogleMapMarker[list.size()]));
        googleMap.updateMarkers();
    }
}
