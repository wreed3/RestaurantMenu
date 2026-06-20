package restautant;

import java.util.List;
import java.util.Scanner;

public class Restaurant {
    private String name;
    private Menu menu;
    private Scanner scanner;
    
    public Restaurant(String name) {
        this.name = name;
        this.menu = new Menu("menu_items.csv"); // Load from file
        this.scanner = new Scanner(System.in);
    }
    
    public void run() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("          Welcome to " + name + "!");
        System.out.println("=".repeat(60));
        
        while (true) {
            displayMainMenu();
            
            int choice = getIntInput("Choose option: ");
            System.out.println();
            
            switch (choice) {
                case 1:
                    viewFullMenu();
                    break;
                case 2:
                    viewByCategory();
                    break;
                case 3:
                    addNewItem();
                    break;
                case 4:
                    removeItem();
                    break;
                case 5:
                    updateItem();
                    break;
                case 6:
                    searchItem();
                    break;
                case 7:
                    reloadMenu();
                    break;
                case 8:
                    System.out.println("Thank you for visiting " + name + "!");
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("⚠ Invalid option. Please try again.");
            }
            
            System.out.println("\nPress Enter to continue...");
            scanner.nextLine();
        }
    }
    
    private void displayMainMenu() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("                    MENU OPTIONS");
        System.out.println("=".repeat(60));
        System.out.println("  1. View full menu");
        System.out.println("  2. View by category");
        System.out.println("  3. Add new item");
        System.out.println("  4. Remove item");
        System.out.println("  5. Update item");
        System.out.println("  6. Search for item");
        System.out.println("  7. Reload menu from file");
        System.out.println("  8. Exit");
        System.out.println("=".repeat(60));
    }
    
    private void viewFullMenu() {
        menu.displayMenu();
    }
    
    private void viewByCategory() {
        List<String> categories = menu.getCategories();
        
        if (categories.isEmpty()) {
            System.out.println("No categories available.");
            return;
        }
        
        System.out.println("Available categories:");
        for (int i = 0; i < categories.size(); i++) {
            System.out.println("  " + (i + 1) + ". " + categories.get(i));
        }
        
        int choice = getIntInput("Select category (1-" + categories.size() + "): ");
        
        if (choice >= 1 && choice <= categories.size()) {
            menu.displayByCategory(categories.get(choice - 1));
        } else {
            System.out.println("⚠ Invalid category selection.");
        }
    }
    
    private void addNewItem() {
        System.out.println("=== Add New Menu Item ===");
        
        System.out.print("Name: ");
        String name = scanner.nextLine();
        
        System.out.print("Description: ");
        String description = scanner.nextLine();
        
        double price = getDoubleInput("Price: $");
        
        System.out.print("Category: ");
        String category = scanner.nextLine();
        
        MenuItems newItem = new MenuItems(name, description, price, category);
        menu.addItem(newItem);
    }
    
    private void removeItem() {
        System.out.print("Enter name of item to remove: ");
        String name = scanner.nextLine();
        menu.removeItem(name);
    }
    
    private void updateItem() {
        System.out.print("Enter name of item to update: ");
        String oldName = scanner.nextLine();
        
        MenuItems existingItem = menu.searchItem(oldName);
        if (existingItem == null) {
            System.out.println("⚠ Item not found: " + oldName);
            return;
        }
        
        System.out.println("Current item: " + existingItem);
        System.out.println("\nEnter new details (press Enter to keep current value):");
        
        System.out.print("Name [" + existingItem.getName() + "]: ");
        String name = scanner.nextLine();
        if (name.trim().isEmpty()) name = existingItem.getName();
        
        System.out.print("Description [" + existingItem.getDescription() + "]: ");
        String description = scanner.nextLine();
        if (description.trim().isEmpty()) description = existingItem.getDescription();
        
        System.out.print("Price [" + existingItem.getPrice() + "]: $");
        String priceStr = scanner.nextLine();
        double price = priceStr.trim().isEmpty() ? existingItem.getPrice() : Double.parseDouble(priceStr);
        
        System.out.print("Category [" + existingItem.getCategory() + "]: ");
        String category = scanner.nextLine();
        if (category.trim().isEmpty()) category = existingItem.getCategory();
        
        MenuItems updatedItem = new MenuItems(name, description, price, category);
        menu.updateItem(oldName, updatedItem);
    }
    
    private void searchItem() {
        System.out.print("Enter item name to search: ");
        String name = scanner.nextLine();
        
        MenuItems item = menu.searchItem(name);
        if (item != null) {
            System.out.println("\n✓ Found:");
            System.out.println(item);
        } else {
            System.out.println("⚠ Item not found: " + name);
        }
    }
    
    private void reloadMenu() {
        System.out.println("Reloading menu from file...");
        menu.loadFromFile();
        System.out.println("✓ Menu reloaded! Total items: " + menu.getItemCount());
    }
    
    private int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                int value = Integer.parseInt(scanner.nextLine());
                return value;
            } catch (NumberFormatException e) {
                System.out.println("⚠ Please enter a valid number.");
            }
        }
    }
    
    private double getDoubleInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                double value = Double.parseDouble(scanner.nextLine());
                return value;
            } catch (NumberFormatException e) {
                System.out.println("⚠ Please enter a valid number.");
            }
        }
    }
    
    public static void main(String[] args) {
        Restaurant restaurant = new Restaurant("The Grand Bistro");
        restaurant.run();
    }
}