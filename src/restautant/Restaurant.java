package restautant;

import java.util.*;
import java.util.ArrayList;

import static restautant.Menu.printMenuItem;
import static restautant.Menu.printMenuItems;
public class Restaurant {


    public static void main(String[] args) {
        // write your code here
        Date lastUpdated = new Date();

        Boolean printEntireMenu = true;
        Scanner input = new Scanner(System.in);

        MenuItems menuItem1 = new MenuItems("Pepperoni Pizza", 20.00, "The best pepperoni pizza that you have ever tasted!", "Pizza", false );
        MenuItems menuItem2 = new MenuItems("Veggie Pizza", 15.00, "The best veggie pizza that you have ever tasted!", "Pizza", false);
        MenuItems menuItem3 = new MenuItems("Supreme Pizza", 25.00, "The Best supreme of all supreme pizzas!", "Pizza", false);
        MenuItems menuItem4 = new MenuItems("The Real Deal", 30.00, "The Godfather of all Pizzas!", "Pizza", true);
        MenuItems menuItem5 = new MenuItems("Apple Pizza Pie", 15.00, "When you're crazing something sweet there is only one solution", "Dessert",true);
        MenuItems menuItem6 = new MenuItems("Sizzling Wings", 12.50, "Wings that will give you wings!", "Sides", true);
        MenuItems menuItem7 = new MenuItems ("Romaine insane salad", 10.00, "The best Romaine salad you have ever tasted", "Sides", true);
        MenuItems menuItem8 = new MenuItems("Cheesey Bread",5.0,"Who said bread couldn't be cheesy!", "Sides",false);
        MenuItems menuItem9 = new MenuItems("Cinnamon sticks", 5.0, "Satisfy your sweet tooth!", "Dessert", true);

        ArrayList<MenuItems> menuItem = new ArrayList<>();
        Menu basicMenu = new Menu(lastUpdated,menuItem);

        basicMenu.addMenuItem(menuItem1);
        basicMenu.addMenuItem(menuItem2);
        basicMenu.addMenuItem(menuItem3);
        basicMenu.addMenuItem(menuItem4);
        basicMenu.addMenuItem(menuItem5);
        basicMenu.addMenuItem(menuItem6);
        basicMenu.addMenuItem(menuItem7);
        basicMenu.addMenuItem(menuItem8);
        basicMenu.addMenuItem(menuItem9);

//        System.out.println("\n\n");
        System.out.print("*** WELCOME to the PIZZA DEPOT ***");
        System.out.print("\nWhere everything everything that we\n" + "have will be the best you ever tasted!\n\n");
        System.out.println("Do you want to view the entire menu? \nType: \n( yes ) - To see the entire menu \n( no ) - To search for a menu item");

        String response = input.nextLine();
        response = response.toLowerCase();

        if(response.equals("yes")){
            System.out.println("\n---------MENU----------");
            System.out.println(lastUpdated);
            System.out.println("\n");
            System.out.println("---PIZZA---");
            printMenuItems(menuItem, "Pizza");
//
            System.out.println("\n\n---SIDES---");
            printMenuItems(menuItem, "Sides");
            System.out.println("\n\n---DESSERTS---");
            printMenuItems(menuItem, "Dessert");
        }else {
            System.out.println("What menu item would you like to search: ");
            String response2 = input.nextLine();
            System.out.println("---------MENU----------");
            System.out.println(lastUpdated);
            printMenuItem(menuItem, response2);

        }

        System.out.println("The menu is getting too long. Please enter in a menu item to remove");
        String response3 = input.nextLine();
        basicMenu.removeMenuItem(menuItem, response3);
        System.out.println("Here is the menu");
        System.out.println("\n---------MENU----------");
        System.out.println(lastUpdated);
        System.out.println("\n");
        System.out.println("---PIZZA---");
        printMenuItems(menuItem, "Pizza");
//
        System.out.println("\n\n---SIDES---");
        printMenuItems(menuItem, "Sides");
        System.out.println("\n\n---DESSERTS---");
        printMenuItems(menuItem, "Dessert");
    }



}





