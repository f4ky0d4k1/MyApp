package com.example.myapp3;

import java.util.List;

public class Item {

    private String day;
    private List<InternalItem> internalItems;

    public Item(String day, List<InternalItem> internalItems) {
        this.day = day;
        this.internalItems = internalItems;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public List<InternalItem> getInternalItems() {
        return internalItems;
    }

    public void setInternalItems(List<InternalItem> internalItems) {
        this.internalItems = internalItems;
    }
}
