package com.example.myapp3.ScheduleActivity;

import java.util.List;

public class ScheduleItem {

    private String day;
    private List<ScheduleInternalItem> scheduleInternalItems;

    public ScheduleItem(String day, List<ScheduleInternalItem> scheduleInternalItems) {
        this.day = day;
        this.scheduleInternalItems = scheduleInternalItems;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public List<ScheduleInternalItem> getInternalItems() {
        return scheduleInternalItems;
    }

    public void setInternalItems(List<ScheduleInternalItem> scheduleInternalItems) {
        this.scheduleInternalItems = scheduleInternalItems;
    }
}