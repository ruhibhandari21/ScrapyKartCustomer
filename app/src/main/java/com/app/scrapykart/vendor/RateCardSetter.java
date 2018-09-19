package com.app.scrapykart.vendor;

/**
 * Created by shadaf on 7/1/18.
 */

public class RateCardSetter {

    String itemName = "Item", otherDetails = "", unit = "kg", price = "10";
    boolean selected =false;
    int quantity = 1;

    public RateCardSetter(String itemName, String otherDetails, String unit, String price) {
        this.itemName = itemName;
        this.otherDetails = otherDetails;
        this.unit = unit;
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getOtherDetails() {
        return otherDetails;
    }

    public void setOtherDetails(String otherDetails) {
        this.otherDetails = otherDetails;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
