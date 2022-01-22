package restautant;

import java.util.ArrayList;

public class MenuItems {
    private String itemName;
    private double price;
    private String description;
    private String category;
    private Boolean isNew;


    public MenuItems(String itemName, double price, String description, String category, Boolean isNew) {
        this.itemName = itemName;
        this.price = price;
        this.description = description;
        this.category = category;
        this.isNew = isNew;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Boolean getNew() {
        return isNew;
    }

    public void setNew(Boolean aNew) {
        isNew = aNew;
    }
}


