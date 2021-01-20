package com.company;

import java.time.DayOfWeek;

public class Booking {
    public String ID;
    public DayOfWeek date;
    public int startTime;
    public int endTime;

    public Booking(String id,DayOfWeek date, int startTime, int endTime) {
        this.ID= id;
        this.date = date;
        this.startTime=startTime;
        this.endTime = endTime;
    }
    public static void main(String[] args) throws Exception{
        for (DayOfWeek c : DayOfWeek.values())
            System.out.println(c);
    }
}
