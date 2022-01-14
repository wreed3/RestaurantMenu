package restautant;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;


public class Menu {
    public static final String RESTAURANT = "";
    private String categories;
    private HashMap<String, Arrays> menuItem;
    private Date lastUpdated;

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public HashMap<String, Arrays> getMenuItem() {
        return menuItem;
    }

    public void setMenuItem(HashMap<String, Arrays> menuItem) {
        this.menuItem = menuItem;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}
