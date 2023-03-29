package com.application.backend.model;

public class Table {
    private final int nr;
    private String bookedBy;
    private boolean booked[];

    public int getNr() {
        return nr;
    }

    public String getBookedBy() {
        return bookedBy;
    }

    public boolean[] getBooked() {
        return booked;
    }

    public Table(int nr, String bookedBy, boolean booked[]) {
        this.nr = nr;
        this.bookedBy = bookedBy;
        this.booked = booked;
    }





    public void setBookedBy(String bookedBy) {
        this.bookedBy = bookedBy;
    }

    public void setBooked(boolean[] booked) {
        this.booked = booked;
    }
}
