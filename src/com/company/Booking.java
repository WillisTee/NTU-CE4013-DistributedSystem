package com.company;

import java.time.DayOfWeek;

public class Booking {
    public String ID;
    public DayOfWeek date;
    public int startTime;
    public int endTime;
    public String username;

    public Booking(String id,DayOfWeek date, int startTime, int endTime,String username) {
        this.ID= id;
        this.date = date;
        this.startTime=startTime;
        this.endTime = endTime;
        this.username = username;
    }
}
