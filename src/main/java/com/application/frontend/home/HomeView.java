package com.application.frontend.home;

import com.application.backend.API.RestaurantService;
import com.application.backend.model.OpeningHours;
import com.application.backend.model.RestaurantItems;
import com.application.frontend.MainLayout;
import com.application.frontend.details.DetailedView;
import com.vaadin.flow.component.accordion.Accordion;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.CheckboxGroup;
import com.vaadin.flow.component.checkbox.CheckboxGroupVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.radiobutton.RadioGroupVariant;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@PageTitle("Home")
@Route(value = "home", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
public class HomeView extends Div implements AfterNavigationObserver {

    Grid<RestaurantItems> grid = new Grid<>();
    RestaurantService restaurantService = new RestaurantService();

    public HomeView() {

        addClassName("home-view");

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
        apply.addClickListener(event -> {
            List<String> selectedTypes = restaurantType.getSelectedItems().stream().toList();
            List<String> selectedPrices = priceCategory.getSelectedItems().stream().toList();
            String selectedRating = averageRating.getValue();
            updateGridAfterFilter(selectedTypes, selectedPrices, selectedRating);
        });

        Button clear = new Button("clear filters");
        clear.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_ERROR);
        clear.addClickListener(event -> {
            restaurantType.deselectAll();
            priceCategory.deselectAll();
            averageRating.clear();
            List<String> selectedTypes = restaurantType.getSelectedItems().stream().toList();
            List<String> selectedPrices = priceCategory.getSelectedItems().stream().toList();
            String selectedRating = averageRating.getValue();
            updateGridAfterFilter(selectedTypes, selectedPrices, selectedRating);
        });

        buttons.add(apply, clear);

        filter.add(restaurantType, priceCategory, averageRating, buttons);
        accordion.add("expand filters", filter);
        accordion.close();
        add(accordion);

        grid.setHeight("100%");
        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER, GridVariant.LUMO_NO_ROW_BORDERS);
        search.addValueChangeListener(event -> {
            String input = search.getValue();
            updateGridAfterSearch(input, restaurantService.getAll());
        });
        grid.addComponentColumn(this::createCard);

        add(grid);
    }

    private HorizontalLayout createCard(RestaurantItems restaurantItems) {
        HorizontalLayout card = new HorizontalLayout();
        card.setAlignItems(FlexComponent.Alignment.CENTER);
        card.addClassName("card");
        card.setSpacing(false);
        card.getThemeList().add("spacing-s");

        //image
        Image image = new Image();
        image.setSrc(restaurantItems.getImageURL());
        image.setHeight("8rem");
        image.setWidth("8rem");
        VerticalLayout description = new VerticalLayout();
        description.addClassName("description");
        description.setSpacing(false);
        description.setPadding(false);

        //first row of card
        HorizontalLayout header = new HorizontalLayout();
        header.addClassName("header");
        header.setSpacing(false);
        header.getThemeList().add("spacing-s");

        Span name = new Span(restaurantItems.getName());
        name.addClassName("name");

        Span rating = new Span(restaurantItems.getRating() + "/5");
        rating.addClassName("rating");

        Span price = new Span(priceIntToString(restaurantItems.getPriceCategory()));
        price.addClassName("price");

        header.add(name, rating, price);

        //second row
        Span cuisine = new Span(restaurantItems.getCuisineType());

        HorizontalLayout info = new HorizontalLayout();
        info.addClassName("info");
        info.setSpacing(false);
        info.getThemeList().add("spacing-s");

        //third row including button
        HorizontalLayout infoAndButton = new HorizontalLayout();
        infoAndButton.addClassName("info");
        infoAndButton.setSpacing(false);
        infoAndButton.setPadding(true);

        infoAndButton.setJustifyContentMode(FlexComponent.JustifyContentMode.EVENLY);

        VerticalLayout adress = new VerticalLayout();
        adress.setSpacing(false);
        adress.setPadding(false);
        Span adress1 = new Span(restaurantItems.getAddress().split(",")[0]);
        Span adress2 = new Span(restaurantItems.getAddress().substring(restaurantItems.getAddress().indexOf(',') + 1));
        adress2.addClassName("adress");

        adress.add(adress1, adress2);
        Span openingHours;
        if (isOpen(restaurantItems.getOpeningHours()))
            openingHours = new Span("We are open until " + openToday(restaurantItems.getOpeningHours()) + " today!");
        else
            openingHours = new Span("Unfortunately we are closed today!");

        Button successButton = new Button("Book Table");
        successButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_SUCCESS);

        VerticalLayout button = new VerticalLayout();
        button.setSpacing(false);
        button.setPadding(true);
        button.add(successButton);
        button.setAlignItems(FlexComponent.Alignment.END);

        successButton.addClickListener(clickevent -> {
            String id = restaurantItems.getId();
            //testId = id;
            String route = RouteConfiguration.forSessionScope()
                    .getUrl(DetailedView.class, id);
            successButton.getUI().ifPresent(ui ->
                    ui.navigate(route));
        });
        successButton.addClassName("Button");

        info.add(adress, openingHours);

        infoAndButton.add(info, button);

        description.add(header, cuisine, infoAndButton);
        card.add(image, description);
        return card;
    }

    @Override
    public void afterNavigation(AfterNavigationEvent event) {

        List<RestaurantItems> restaurants = restaurantService.getAll();

        grid.setItems(restaurants);
    }

    public void updateGridAfterSearch(String searchValue, List<RestaurantItems> originalList) {
        List<RestaurantItems> result = new ArrayList<>();
        for (RestaurantItems restaurantItems : originalList) {
            if (restaurantItems.getName().toUpperCase().startsWith(searchValue.toUpperCase(Locale.ROOT)))
                result.add(restaurantItems);
        }
        grid.setItems(result);
    }

    public void updateGridAfterFilter(List<String> types, List<String> prices, String rating) {
        List<RestaurantItems> restaurantServiceAll = restaurantService.getAll();
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

        grid.setItems(restaurantServiceAll);
    }

    public String priceIntToString(int price) {
        return "€".repeat(Math.max(0, price));
    }

    public String openToday(OpeningHours openingHours) {
        if (openingHours.getMon().equals(""))
            return "";
        String date = LocalDate.now().getDayOfWeek().toString();
        String open;

        switch (date) {
            case "MONDAY" -> open = openingHours.getMon();
            case "TUESDAY" -> open = openingHours.getTue();
            case "WEDNESDAY" -> open = openingHours.getWed();
            case "THURSDAY" -> open = openingHours.getThu();
            case "FRIDAY" -> open = openingHours.getFri();
            case "SATURDAY" -> open = openingHours.getSat();
            default -> open = openingHours.getSun();
        }
        return open.substring(open.length() - 5);
    }

    public boolean isOpen(OpeningHours openingHours) {
        return !openToday(openingHours).equals("losed") && !openToday(openingHours).equals("ossen");
    }
}
