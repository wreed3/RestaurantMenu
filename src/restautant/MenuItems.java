package restautant;

public class MenuItems {
    private String name;
    private String description;
    private double price;
    private String category;
    
    // Constructor
    public MenuItems(String name, String description, double price, String category) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
    }
    
    // Parse from CSV line
    public static MenuItems fromCSV(String csvLine) {
        String[] parts = csvLine.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)"); // Handle commas in quotes
        if (parts.length < 4) {
            throw new IllegalArgumentException("Invalid CSV line: " + csvLine);
        }
        
        String name = parts[0].trim().replace("\"", "");
        String description = parts[1].trim().replace("\"", "");
        double price = Double.parseDouble(parts[2].trim());
        String category = parts[3].trim().replace("\"", "");
        
        return new MenuItems(name, description, price, category);
    }
    
    // Convert to CSV line
    public String toCSV() {
        // Escape fields that contain commas
        String escapedName = name.contains(",") ? "\"" + name + "\"" : name;
        String escapedDesc = description.contains(",") ? "\"" + description + "\"" : description;
        String escapedCategory = category.contains(",") ? "\"" + category + "\"" : category;
        
        return String.format("%s,%s,%.2f,%s", escapedName, escapedDesc, price, escapedCategory);
    }
    
    // Getters
    public String getName() {
        return name;
    }
    
    public String getDescription() {
        return description;
    }
    
    public double getPrice() {
        return price;
    }
    
    public String getCategory() {
        return category;
    }
    
    // Setters
    public void setName(String name) {
        this.name = name;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public void setPrice(double price) {
        this.price = price;
    }
    
    public void setCategory(String category) {
        this.category = category;
    }
    
    @Override
    public String toString() {
        return String.format("%-20s %-50s $%.2f [%s]", name, description, price, category);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        MenuItems other = (MenuItems) obj;
        return name.equalsIgnoreCase(other.name);
    }
    
    @Override
    public int hashCode() {
        return name.toLowerCase().hashCode();
    }
}