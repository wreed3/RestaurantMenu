package restautant;
import java.util.*;
import java.util.ArrayList;

public class Menu {
    private Date lastUpdated;
    private ArrayList<MenuItems> menuItem;

    public Menu(Date lastUpdated, ArrayList<MenuItems> menuItem) {
        this.lastUpdated = lastUpdated;
        this.menuItem = menuItem;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public ArrayList<MenuItems> getMenuItem() {
        return menuItem;
    }

    public void setMenuItem(ArrayList<MenuItems> menuItem) {
        this.menuItem = menuItem;
    }


    public static void printMenuItems(ArrayList<MenuItems> menuItems, String category) {
        for (MenuItems menuItem : menuItems) {
            if (menuItem.getCategory().equals(category)) {
                System.out.println("----------------------");
                System.out.println(menuItem.getItemName());
                System.out.println(menuItem.getDescription());
                System.out.println(menuItem.getPrice());
                if (menuItem.getNew()) {
                    System.out.println("*New Item*");
                }
            }
        }
    }

    public ArrayList<MenuItems> addMenuItem(MenuItems itemToAdd) {
        for(int i = 0; i<menuItem.size(); i++){
            if(menuItem.get(i).equals(itemToAdd)){
                System.out.println("This item is already on the menu");
                return this.menuItem;
            }
        }
        this.menuItem.add(itemToAdd);
        return this.menuItem;
    }

    public ArrayList<MenuItems> removeMenuItem(ArrayList<MenuItems> menuItems, String item) {

        menuItems.removeIf(menuItem -> menuItem.getItemName().toLowerCase().contains(item));
        System.out.println("THIS ITEM IS NOT ON THE MENU.\n NOTHING HAS CHANGED ON THE MENU");
        return menuItems;
    }
    public static void printMenuItem (ArrayList < MenuItems > menuItems, String item){
        for (MenuItems menuItem : menuItems) {
            if (menuItem.getItemName().toLowerCase().contains(item)) {
                System.out.println("----------------------");
                System.out.println(menuItem.getItemName());
                System.out.println(menuItem.getDescription());
                System.out.println(menuItem.getPrice());
                if (menuItem.getNew()) {
                    System.out.println("*New Item*");
                }
            }
        }
    }

}