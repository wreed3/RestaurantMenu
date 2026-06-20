package restautant;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Menu {
    private List<MenuItems> items;
    private String dataFilePath;
    
    // Constructor with file path
    public Menu(String dataFilePath) {
        this.items = new ArrayList<>();
        this.dataFilePath = dataFilePath;
        loadFromFile();
    }
    
    // Default constructor (uses default file path)
    public Menu() {
        this("menu_items.csv");
    }
    
    // Load menu items from CSV file
    public void loadFromFile() {
        items.clear();
        File file = new File(dataFilePath);
        
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = reader.readLine(); // Skip header
            int lineNumber = 1;
            
            while ((line = reader.readLine()) != null) {
                lineNumber++;
                if (!line.trim().isEmpty()) {
                    try {
                        items.add(MenuItems.fromCSV(line));
                    } catch (Exception e) {
                        System.err.println("Error parsing line " + lineNumber + ": " + e.getMessage());
                    }
                }
            }
            System.out.println("✓ Loaded " + items.size() + " items from " + dataFilePath);
        } catch (FileNotFoundException e) {
            System.out.println("Menu file not found at: " + dataFilePath);
            System.out.println("Creating default menu file...");
            createDefaultFile();
        } catch (IOException e) {
            System.err.println("Error loading menu: " + e.getMessage());
        }
    }
    
    // Save menu items to CSV file
    public void saveToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(dataFilePath))) {
            writer.println("name,description,price,category");
            for (MenuItems item : items) {
                writer.println(item.toCSV());
            }
            System.out.println("✓ Menu saved successfully to " + dataFilePath);
        } catch (IOException e) {
            System.err.println("Error saving menu: " + e.getMessage());
        }
    }
    
    // Create default file with sample data if missing
    private void createDefaultFile() {
        items.add(new MenuItems("Spring Rolls", "Crispy vegetable spring rolls with sweet chili sauce", 6.99, "Appetizer"));
        items.add(new MenuItems("Bruschetta", "Toasted bread with tomatoes and basil", 7.50, "Appetizer"));
        items.add(new MenuItems("Grilled Salmon", "Fresh Atlantic salmon with lemon butter", 18.99, "Main Course"));
        items.add(new MenuItems("Beef Burger", "Angus beef burger with fries", 12.99, "Main Course"));
        items.add(new MenuItems("Chocolate Cake", "Rich chocolate layer cake", 6.50, "Dessert"));
        items.add(new MenuItems("Ice Cream", "Vanilla ice cream with toppings", 4.99, "Dessert"));
        items.add(new MenuItems("Coffee", "Freshly brewed coffee", 2.99, "Beverage"));
        items.add(new MenuItems("Soda", "Assorted soft drinks", 2.50, "Beverage"));
        
        saveToFile();
        System.out.println("✓ Default menu created");
    }
    
    // Add a new menu item
    public void addItem(MenuItems item) {
        if (items.contains(item)) {
            System.out.println("⚠ Item with name '" + item.getName() + "' already exists!");
            return;
        }
        items.add(item);
        saveToFile(); // Auto-save after modification
        System.out.println("✓ Added: " + item.getName());
    }
    
    // Remove a menu item by name
    public boolean removeItem(String name) {
        boolean removed = items.removeIf(item -> item.getName().equalsIgnoreCase(name));
        if (removed) {
            saveToFile(); // Auto-save after modification
            System.out.println("✓ Removed: " + name);
        } else {
            System.out.println("⚠ Item not found: " + name);
        }
        return removed;
    }
    
    // Update an existing menu item
    public boolean updateItem(String name, MenuItems newItem) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getName().equalsIgnoreCase(name)) {
                items.set(i, newItem);
                saveToFile(); // Auto-save after modification
                System.out.println("✓ Updated: " + name);
                return true;
            }
        }
        System.out.println("⚠ Item not found: " + name);
        return false;
    }
    
    // Display the full menu
    public void displayMenu() {
        if (items.isEmpty()) {
            System.out.println("The menu is empty.");
            return;
        }
        
        System.out.println("\n" + "=".repeat(80));
        System.out.println("                           RESTAURANT MENU");
        System.out.println("=".repeat(80));
        
        String currentCategory = "";
        for (MenuItems item : items) {
            if (!item.getCategory().equals(currentCategory)) {
                currentCategory = item.getCategory();
                System.out.println("\n--- " + currentCategory.toUpperCase() + " ---");
            }
            System.out.println(item);
        }
        System.out.println("=".repeat(80));
    }
    
    // Search for an item by name
    public MenuItems searchItem(String name) {
        for (MenuItems item : items) {
            if (item.getName().equalsIgnoreCase(name)) {
                return item;
            }
        }
        return null;
    }
    
    // Get all items in a specific category
    public List<MenuItems> getItemsByCategory(String category) {
        return items.stream()
                .filter(item -> item.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList());
    }
    
    // Get all unique categories
    public List<String> getCategories() {
        return items.stream()
                .map(MenuItems::getCategory)
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }
    
    // Display items by category
    public void displayByCategory(String category) {
        List<MenuItems> categoryItems = getItemsByCategory(category);
        
        if (categoryItems.isEmpty()) {
            System.out.println("No items found in category: " + category);
            return;
        }
        
        System.out.println("\n=== " + category.toUpperCase() + " ===");
        for (MenuItems item : categoryItems) {
            System.out.println(item);
        }
    }
    
    // Get all items
    public List<MenuItems> getAllItems() {
        return new ArrayList<>(items);
    }
    
    // Get item count
    public int getItemCount() {
        return items.size();
    }
}