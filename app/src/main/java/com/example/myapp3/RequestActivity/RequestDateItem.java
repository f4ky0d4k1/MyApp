package com.example.myapp3.RequestActivity;

import java.util.List;

public class RequestDateItem {

    private String month;
    private List<RequestDateInternalItem> internalItems;

    public RequestDateItem(String month, List<RequestDateInternalItem> internalItems) {
        this.month = month;
        this.internalItems = internalItems;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public List<RequestDateInternalItem> getInternalItems() {
        return internalItems;
    }

    public void setInternalItems(List<RequestDateInternalItem> internalItems) {
        this.internalItems = internalItems;
    }
}
