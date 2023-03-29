package com.application.frontend.details;

import com.application.backend.model.RestaurantItems;
import com.application.backend.model.Table;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import org.apache.tomcat.jni.Local;

import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RestaurantLayout extends Div {
    private final RestaurantItems restaurantItems;

    private final LocalDate date;

    private final String time;

    private Button button1 = new Button();
    private String typeButton1 = "free";
    boolean[] freeTables;
    boolean[] freeTables2;
    boolean[] freeTables3;
    private Button button2 = new Button();
    private String typeButton2 = "free";

    private Button button3 = new Button();
    private String typeButton3 = "free";

    private int index;

    public int getIndex() {
        return index;
    }

    public RestaurantLayout(RestaurantItems restaurant, LocalDate date, String time){
        this.restaurantItems = restaurant;
        this.date = date;
        this.time = time;
    }

    public VerticalLayout createLayout(RestaurantItems restaurant, List<LocalDate> date, String time) {
        ArrayList<Table> tables = restaurant.getTables();
        VerticalLayout totalLayout = new VerticalLayout();
        totalLayout.setPadding(true);
        totalLayout.setSpacing(true);
        totalLayout.setHeight("16rem");
        totalLayout.setWidth("16rem");

        int daysBetween = date.size();

        int type = 0;
        if (time.equals("Dinner"))
            type = 1;

        if (daysBetween == 0)
            index = type;
        else
            index = (daysBetween*2)+type;

        HorizontalLayout table1 = new HorizontalLayout();

        freeTables = restaurant.getTables().get(0).getBooked();
        boolean value1 = freeTables[index];

        if (!value1) {
            button1.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_SUCCESS);
            button1.setText("table 1 is free");
        }
        else {
            button1.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_ERROR);
            button1.setText("table 1 is booked");
            typeButton1 = "reserved";
        }
        table1.add(button1);
        table1.setAlignItems(FlexComponent.Alignment.START);

        HorizontalLayout table2 = new HorizontalLayout();

        freeTables2 = restaurant.getTables().get(1).getBooked();
        boolean value2 = freeTables2[index];

        if (!value2) {
            button2.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_SUCCESS);
            button2.setText("table 2 is free");
        }
        else {
            button2.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_ERROR);
            button2.setText("table 2 is booked");
            typeButton2 = "reserved";
        }
        table2.add(button2);
        table2.setAlignItems(FlexComponent.Alignment.END);

        HorizontalLayout table3 = new HorizontalLayout();

        freeTables3 = restaurant.getTables().get(1).getBooked();
        boolean value3 = freeTables3[index];

        if (!value3) {
            button3.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_SUCCESS);
            button3.setText("table 3 is free");
        }
        else {
            button3.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_ERROR);
            button3.setText("table 3 is booked");
            typeButton3 = "reserved";
        }
        table3.add(button3);
        table3.setAlignItems(FlexComponent.Alignment.START);

        totalLayout.add(table1, table2, table3);
        return totalLayout;
    }

    public Button getButton1() {
        return button1;
    }

    public Button getButton2() {
        return button2;
    }

    public Button getButton3() {
        return button3;
    }

    public String getTypeButton1() {
        return typeButton1;
    }

    public String getTypeButton2() {
        return typeButton2;
    }

    public String getTypeButton3() {
        return typeButton3;
    }

    public boolean[] getFreeTables() {
        return freeTables;
    }
}
