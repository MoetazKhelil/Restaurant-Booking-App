package com.application.frontend.details;

import com.application.backend.API.RestaurantService;
import com.application.backend.model.OpeningHours;
import com.application.backend.model.RestaurantItems;
import com.application.backend.model.Table;
import com.application.frontend.MainLayout;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datetimepicker.DateTimePicker;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.messages.MessageInput;
import com.vaadin.flow.component.messages.MessageList;
import com.vaadin.flow.component.messages.MessageListItem;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@PageTitle("Details")
@Route(value = "details", layout = MainLayout.class)
public class DetailedView extends VerticalLayout implements HasUrlParameter<String> {

    private String idTest = "";
    private RestaurantService restaurantService = new RestaurantService();

    public DetailedView() {
        //String router = UI.getCurrent().getInternals().getRouter().toString();

        setSpacing(true);
        addClassName("detail-view");
        setJustifyContentMode(JustifyContentMode.CENTER);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        getStyle().set("text-align", "center");

    }

    public void renderRestaurant(){
        RestaurantService restaurantService = new RestaurantService();
        add(createDetails(restaurantService.getById(idTest)));
    }


    private VerticalLayout createDetails(RestaurantItems restaurantItems) {
        VerticalLayout details = new VerticalLayout();
        details.addClassName("details");
        details.setAlignItems(Alignment.CENTER);
        details.setSpacing(false);
        details.getThemeList().add("spacing-s");

        //name of restaurant
        Span name = new Span(restaurantItems.getName());
        name.addClassName("name");

        //PriceCategory Layout
        int priceCategory = restaurantItems.getPriceCategory();
        HorizontalLayout priceLayout = new HorizontalLayout();
        priceLayout.setClassName("priceLayout");

        //Price Category
        if (priceCategory == 1) {
            Icon priceIcon = new Icon(VaadinIcon.EURO);
            priceLayout.add(priceIcon);
        } else if (priceCategory == 2) {
            Icon priceIcon1 = new Icon(VaadinIcon.EURO);
            Icon priceIcon2 = new Icon(VaadinIcon.EURO);
            priceLayout.add(priceIcon1, priceIcon2);
        } else if (priceCategory == 3) {
            Icon priceIcon1 = new Icon(VaadinIcon.EURO);
            Icon priceIcon2 = new Icon(VaadinIcon.EURO);
            Icon priceIcon3 = new Icon(VaadinIcon.EURO);
            priceLayout.add(priceIcon1, priceIcon2, priceIcon3);
        } else {
            Icon priceIcon1 = new Icon(VaadinIcon.EURO);
            Icon priceIcon2 = new Icon(VaadinIcon.EURO);
            Icon priceIcon3 = new Icon(VaadinIcon.EURO);
            Icon priceIcon4 = new Icon(VaadinIcon.EURO);
            priceLayout.add(priceIcon1, priceIcon2, priceIcon3, priceIcon4);
        }
        priceLayout.setAlignItems(Alignment.START);
        priceLayout.setHeight("8%");
        priceLayout.setWidth("8%");


        //ratings of Restaurant
        // ratingLayout
        double ratings = restaurantItems.getRating();
        HorizontalLayout ratingsLayout = new HorizontalLayout();
        ratingsLayout.setClassName("ratingsLayout");

        for (int i = 1; i < 6; i++) {
            boolean cond = false;
            if (i <= ratings) {
                Icon ratingsIcon = new Icon(VaadinIcon.STAR);
                ratingsLayout.add(ratingsIcon);
                cond = true;
            }
            if (!cond) {
                Icon ratingsIcon = new Icon(VaadinIcon.STAR_O);
                ratingsLayout.add(ratingsIcon);
            }
        }
        VerticalLayout header = new VerticalLayout();
        header.addClassName("header");
        header.setSpacing(false);
        header.getThemeList().add("spacing-s");
        header.add(name, ratingsLayout, priceLayout);

        //image
        Image image = new Image();
        image.setSrc(restaurantItems.getImageURL());
        image.setHeight("28rem");
        image.setWidth("33rem");

        //AddressLayout
        Span address = new Span(restaurantItems.getAddress());
        address.setClassName("address");
        Icon addressIcon = new Icon(VaadinIcon.MAP_MARKER);
        HorizontalLayout addressLayout = new HorizontalLayout(addressIcon, address);
        addressLayout.setClassName("addressLayout");

        //WebsiteLayout
        //Span website = new Span(restaurantItems.getWebsite());
        Anchor website = new Anchor(restaurantItems.getWebsite(), restaurantItems.getWebsite());
        website.setClassName("website");
        Icon websiteIcon = new Icon(VaadinIcon.GLOBE_WIRE);
        website.setSizeFull();
        HorizontalLayout websiteLayout = new HorizontalLayout(websiteIcon, website);
        websiteLayout.setClassName("websiteLayout");

        //cuisineLayout
        Span cuisineType = new Span(restaurantItems.getCuisineType());
        Icon cuisineIcon = new Icon(VaadinIcon.CROSS_CUTLERY);
        HorizontalLayout cuisineLayout = new HorizontalLayout(cuisineIcon, cuisineType);
        cuisineLayout.setClassName("cuisineLayout");

        //PhoneNumberLayout
        Span phoneNumber = new Span(restaurantItems.getPhoneNumber());
        Icon phoneIcon = new Icon(VaadinIcon.PHONE);
        HorizontalLayout phoneLayout = new HorizontalLayout(phoneIcon, phoneNumber);
        phoneLayout.setClassName("phoneLayout");

        //OpeningHours
        Span mon = new Span(restaurantItems.getOpeningHours().getMon());
        Span tue = new Span(restaurantItems.getOpeningHours().getTue());
        Span wed = new Span(restaurantItems.getOpeningHours().getWed());
        Span thu = new Span(restaurantItems.getOpeningHours().getThu());
        Span fri = new Span(restaurantItems.getOpeningHours().getFri());
        Span sat = new Span(restaurantItems.getOpeningHours().getSat());
        Span sun = new Span(restaurantItems.getOpeningHours().getSun());
        VerticalLayout daysLayout = new VerticalLayout(mon, tue, wed, thu, fri, sat, sun);
        Icon hoursIcon = new Icon(VaadinIcon.CLOCK);
        HorizontalLayout hoursLayout = new HorizontalLayout(hoursIcon, daysLayout);
        hoursLayout.setClassName("hoursLayout");

        DateTimePicker dateTimePicker = new DateTimePicker("Start date and time");
        dateTimePicker.setMin(LocalDateTime.now());
        dateTimePicker.setStep(Duration.ofHours(1));
        dateTimePicker.setHelperText("Please select 13:00 for lunch and 19:00 for dinner! \n " +
                "Reservations are only possible a week in advance!");
        dateTimePicker.setMax(LocalDateTime.now().plusDays(6));
        add(dateTimePicker);


        dateTimePicker.addValueChangeListener(event -> {
            if (dateTimePicker.getValue() != null) {
                boolean validWeekDay = isOpen(restaurantItems.getOpeningHours());
                boolean validDayTime = dateTimePicker.getValue().getHour() == 13
                        || dateTimePicker.getValue().getHour() == 19;
                if (!validDayTime || !validWeekDay) {
                    dateTimePicker.setReadOnly(true);
                    Notification invalidTimeSelection = new Notification();
                    invalidTimeSelection.addThemeVariants(NotificationVariant.LUMO_ERROR);

                    Div statusText = new Div(new Text("Please choose a time according to instructions below and in line with our operating hours!"));

                    Button retryButton = new Button("Retry");
                    retryButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY_INLINE);
                    retryButton.getElement().getStyle().set("margin-left", "var(--lumo-space-xl)");
                    retryButton.addClickListener(retry -> {
                        invalidTimeSelection.close();
                        dateTimePicker.setReadOnly(false);
                    });

                    Button closeButton = new Button(new Icon("lumo", "cross"));
                    closeButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY_INLINE);
                    closeButton.getElement().setAttribute("aria-label", "Close");
                    closeButton.addClickListener(close -> {
                        invalidTimeSelection.close();
                        dateTimePicker.setReadOnly(false);
                    });

                    HorizontalLayout layout = new HorizontalLayout(statusText, retryButton, closeButton);
                    layout.setAlignItems(Alignment.CENTER);

                    invalidTimeSelection.add(layout);
                    invalidTimeSelection.setPosition(Notification.Position.MIDDLE);
                    invalidTimeSelection.open();

                } else {

                    Dialog dialog = new Dialog();

                    dialog.setHeaderTitle("Reservation details");

                    //VerticalLayout dialogLayout = createDialogLayout();
                    //dialog.add(dialogLayout);

                    Button saveButton = createSaveButton(dialog);

                    Button cancelButton = new Button("Cancel", e -> {
                        dialog.close();
                        dateTimePicker.clear();
                    });
                    dialog.getFooter().add(cancelButton);
                    dialog.getFooter().add(saveButton);

                    add(dialog);

                    dialog.open();
                    String mealType = "Lunch";
                    if (dateTimePicker.getValue().getHour() == 19)
                        mealType = "Dinner";

                    RestaurantLayout layout = new RestaurantLayout(getCurrentRestaurant(), dateTimePicker.getValue().toLocalDate(), mealType);

                    VerticalLayout dialogdesign = new VerticalLayout();
                    dialogdesign.setPadding(true);
                    dialog.setDraggable(true);
                    dialog.setResizable(true);
                    dialogdesign.setAlignItems(Alignment.STRETCH);
                    dialogdesign.getStyle().set("width", "45rem").set("max-width", "100%");
                    TextField appointment = new TextField();
                    appointment.setReadOnly(true);


                    String t1available = layout.getTypeButton1();
                    String t2available = layout.getTypeButton2();
                    String t3available = layout.getTypeButton3();

                    int availableTables = 3;
                    if (t1available.equals("reserved"))
                        availableTables--;
                    if (t2available.equals("reserved"))
                        availableTables--;
                    if (t3available.equals("reserved"))
                        availableTables--;

                    String date = dateTimePicker.getValue().getDayOfWeek().toString().toLowerCase() + " " + dateTimePicker.getValue().getDayOfMonth() + "."
                            + dateTimePicker.getValue().getMonthValue() + "." + dateTimePicker.getValue().getYear();

                    appointment.setValue("We have " + availableTables + " available tables for " + mealType + " on " + date + "!");
                    //appointment.setWidth("40rem");

                    Select<String> numberOfPeople = new Select<>();
                    numberOfPeople.setLabel("Number of guests");
                    numberOfPeople.setValue("1");
                    numberOfPeople.setItems("1", "2", "3", "4", "5", "6", "7", "8");

                    TextField requestUsername = new TextField();
                    requestUsername.setValue("Please enter a Username below and confirm your reservation by clicking the button!");
                    requestUsername.setReadOnly(true);
                    requestUsername.setSizeFull();

                    TextField requestTableNumber = new TextField();
                    requestTableNumber.setValue("Please also choose one of the available tables on the right or enter the table number.");
                    requestTableNumber.setReadOnly(true);
                    requestTableNumber.setSizeFull();

                    TextField userName = new TextField("Username");
                    userName.setRequired(true);
                    userName.setPlaceholder("maria97");

                    TextField tableNumber = new TextField("Table number");
                    userName.setRequired(true);

                    FormLayout formLayout = new FormLayout();
                    formLayout.add(userName);
                    HorizontalLayout input = new HorizontalLayout();
                    input.setPadding(true);

                    VerticalLayout tableLayout = layout.createLayout(getCurrentRestaurant(), dateTimePicker.getValue().
                            toLocalDate().datesUntil(dateTimePicker.getValue().toLocalDate()).toList(), mealType);

                    Button b1 = layout.getButton1();
                    Button b2 = layout.getButton2();
                    Button b3 = layout.getButton3();

                    b1.addClickListener(event1 -> {
                        if (t1available.equals("free"))
                            tableNumber.setValue("1");
                        else
                            Notification.show("Your choice is not available!");
                    });
                    b2.addClickListener(event2 -> {
                        if (t2available.equals("free"))
                            tableNumber.setValue("2");
                        else
                            Notification.show("Your choice is not available!");
                    });
                    b3.addClickListener(event3 -> {
                        if (t3available.equals("free"))
                            tableNumber.setValue("3");
                        else
                            Notification.show("Your choice is not available!");
                    });

                    VerticalLayout numberOfPeopleAndTable = new VerticalLayout();
                    numberOfPeopleAndTable.add(numberOfPeople, tableNumber);

                    input.add(numberOfPeopleAndTable, userName, tableLayout);
                    input.setAlignItems(Alignment.BASELINE);
                    dialogdesign.add(appointment, requestUsername, requestTableNumber, input);
                    dialog.add(dialogdesign);
                    saveButton.addClickListener(event1 -> {
                        boolean check = checkInputs(tableNumber.getValue(), userName.getValue(), numberOfPeople.getValue());
                        if (check) {
                            dialog.close();
                            dateTimePicker.clear();

                            RestaurantItems newRestaurant = getCurrentRestaurant();
                            newRestaurant.getTables().get(Integer.parseInt(tableNumber.getValue())).getBooked()[layout.getIndex()] = true;
                            restaurantService.putById(newRestaurant);

                            Notification.show("successful reservation").setPosition(Notification.Position.MIDDLE);
                        } else {
                            Notification.show("Please check your input again before proceeding!").setPosition(Notification.Position.MIDDLE);
                        }
                    });
                }
            }
        });

        // add all informations in a layout
        VerticalLayout informationsLayout = new VerticalLayout(cuisineLayout, addressLayout, websiteLayout,
                phoneLayout, hoursLayout, dateTimePicker);
        informationsLayout.setClassName("informationsLayout");
        informationsLayout.setSpacing(true);
        informationsLayout.setPadding(true);


        //Comments
        MessageList list = new MessageList();
        MessageInput input = new MessageInput();
        input.addSubmitListener(submitEvent -> {
            MessageListItem newMessage = new MessageListItem(
                    submitEvent.getValue(), null, "Anonymous");
            newMessage.setUserColorIndex(3);
            List<MessageListItem> items = new ArrayList<>(list.getItems());
            items.add(newMessage);
            list.setItems(items);
        });

        Avatar alex = new Avatar("Alex");
        alex.setImage("https://cdn.pixabay.com/photo/2016/08/20/05/38/avatar-1606916__340.png");
        Avatar julia = new Avatar("Julia");
        julia.setImage("https://cdn.pixabay.com/photo/2014/03/24/17/19/teacher-295387__340.png");

        MessageListItem message1 = new MessageListItem(
                "Lovely and authentic restaurant with very friendly service.", null, "Alex", alex.getImage());
        message1.setUserColorIndex(1);
        MessageListItem message2 = new MessageListItem("Although we arrived late, the staff were super friendly" +
                " with us. We'll be back! Thank you for everything",
                null, "Julia", julia.getImage());
        message2.setUserColorIndex(2);
        list.setItems(message1, message2);

        VerticalLayout chatLayout = new VerticalLayout(list, input);
        chatLayout.setHeight("500px");
        chatLayout.setWidth("400px");
        chatLayout.expand(list);
        chatLayout.setAlignItems(Alignment.END);


        HorizontalLayout infoAndCommentLayout = new HorizontalLayout(informationsLayout, chatLayout);
        infoAndCommentLayout.setSpacing(true);
        infoAndCommentLayout.setWidth("75%");
        infoAndCommentLayout.setClassName("infoAndCommentLayout");
        details.add(header, image, infoAndCommentLayout);

        return details;
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

    private static VerticalLayout createDialogLayout() {

        TextField firstNameField = new TextField("First name");
        TextField lastNameField = new TextField("Last name");

        VerticalLayout dialogLayout = new VerticalLayout(firstNameField,
                lastNameField);
        dialogLayout.setPadding(false);
        dialogLayout.setSpacing(false);
        dialogLayout.setAlignItems(FlexComponent.Alignment.STRETCH);
        dialogLayout.getStyle().set("width", "18rem").set("max-width", "100%");

        return dialogLayout;
    }

    private static Button createSaveButton(Dialog dialog) {
        Button saveButton = new Button("confirm reservation");
        saveButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        return saveButton;
    }


    private RestaurantItems getCurrentRestaurant() {
        RestaurantService service = new RestaurantService();
        return service.getById(idTest);
    }

    private boolean checkInputs(String table, String username, String guests) {
        if (guests==null)
            return false;
        if (table.equals("1") || table.equals("2") || table.equals("3")) {
            if (username.equals("")||guests.equals(""))
                return false;
            else
                return true;
        } else
            return false;
    }

    @Override
    public void setParameter(BeforeEvent event, String parameter) {
        setIdTest(String.format("%s", parameter));
        renderRestaurant();
    }

    public String getIdTest() {
        return idTest;
    }

    public void setIdTest(String idTest) {
        this.idTest = idTest;
    }
}

