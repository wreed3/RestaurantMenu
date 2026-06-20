# Restaurant Menu Application

A Java-based restaurant menu management system with **editable external file storage**. Menu items are stored in a CSV file that can be edited without touching the code!

## Features

- ✅ **Easy Editing**: Edit menu items in Excel, Google Sheets, or any text editor
- ✅ **Auto-save**: Changes made through the app are automatically saved
- ✅ **Hot Reload**: Reload menu from file without restarting the application
- ✅ **Full CRUD**: Create, Read, Update, and Delete menu items
- ✅ **Category Organization**: Browse items by category (Appetizers, Main Course, Desserts, Beverages)
- ✅ **Search Functionality**: Find items by name
- ✅ **No Database Required**: Simple CSV file storage

## Quick Start

### 1. Compile and Run

```bash
# Compile
javac src/restautant/*.java

# Run
java -cp src restautant.Restaurant
```

### 2. Edit Menu Items

The menu is stored in `menu_items.csv` in the project root. You can edit this file in:

- **Excel/Google Sheets**: Open as spreadsheet
- **Text Editor**: Edit as plain text (VS Code, Notepad++, etc.)
- **Command Line**: Use any CSV editor

**File Format:**
```csv
name,description,price,category
Spring Rolls,Crispy vegetable spring rolls with sweet chili sauce,6.99,Appetizer
Grilled Salmon,Fresh Atlantic salmon with lemon butter,18.99,Main Course
```

### 3. Reload Changes

After editing the CSV file:

1. In the application, select option **7** (Reload menu from file)
2. Your changes will be loaded immediately!

## Menu File Structure

### CSV Format

The `menu_items.csv` file has four columns:

| Column | Description | Example |
|--------|-------------|---------|
| name | Item name | "Grilled Salmon" |
| description | Item description | "Fresh Atlantic salmon with lemon butter" |
| price | Price (number) | 18.99 |
| category | Category name | "Main Course" |

### Example File

```csv
name,description,price,category
Spring Rolls,Crispy vegetable spring rolls with sweet chili sauce,6.99,Appetizer
Bruschetta,Toasted bread with tomatoes and basil,7.50,Appetizer
Grilled Salmon,Fresh Atlantic salmon with lemon butter,18.99,Main Course
Beef Burger,Angus beef burger with fries,12.99,Main Course
Chocolate Cake,Rich chocolate layer cake,6.50,Dessert
Coffee,Freshly brewed coffee,2.99,Beverage
```

## Editing Guidelines

### ✅ Do's

- Keep the header row: `name,description,price,category`
- Use decimal format for prices: `12.99` not `$12.99`
- Use standard categories: Appetizer, Main Course, Dessert, Beverage
- Save as CSV format (not XLSX)

### ❌ Don'ts

- Don't use commas in descriptions (or wrap in quotes: `"Pasta, with sauce"`)
- Don't delete the header row
- Don't use special characters in prices
- Don't leave blank rows (except at the end)

### Handling Commas in Text

If your description contains commas, wrap it in quotes:

```csv
name,description,price,category
"Surf & Turf","Lobster, steak, and vegetables",29.99,Main Course
```

## Application Features

### Main Menu Options

1. **View full menu** - Display all items organized by category
2. **View by category** - Filter items by specific category
3. **Add new item** - Add a new menu item (auto-saves to file)
4. **Remove item** - Delete an item by name (auto-saves to file)
5. **Update item** - Modify an existing item (auto-saves to file)
6. **Search for item** - Find an item by name
7. **Reload menu from file** - Load changes from CSV file
8. **Exit** - Close the application

### Auto-Save

Any changes made through the application (add, update, remove) are **automatically saved** to the CSV file. No manual save required!

## Project Structure

```
RestaurantMenu/
├── src/
│   └── restautant/
│       ├── Restaurant.java    # Main application
│       ├── Menu.java          # Menu management with file I/O
│       └── MenuItems.java     # Menu item model
├── menu_items.csv             # Editable menu data (THIS FILE!)
└── README.md                  # This file
```

## Technical Details

### File I/O

- **Loading**: Menu is loaded from `menu_items.csv` on startup
- **Saving**: Changes are written to file after each modification
- **Error Handling**: Invalid lines are skipped with error messages
- **Default Data**: If file is missing, a default menu is created

### CSV Parsing

The application handles:
- Quoted fields (for commas in text)
- Empty lines (skipped)
- Invalid data (logged as errors)
- Missing file (creates default)

## Troubleshooting

### Menu Not Loading

**Problem**: Menu shows as empty or doesn't load

**Solution**:
1. Check that `menu_items.csv` exists in the project root
2. Verify the file has the correct header row
3. Check for syntax errors in the CSV
4. Look for error messages when the app starts

### Changes Not Appearing

**Problem**: Edited the CSV but changes don't show in app

**Solution**:
1. Save the CSV file
2. In the app, select option **7** (Reload menu)
3. Or restart the application

### File Corruption

**Problem**: CSV file is corrupted or has errors

**Solution**:
1. Delete `menu_items.csv`
2. Restart the application
3. A new default file will be created

### Special Characters

**Problem**: Descriptions with commas break the layout

**Solution**:
Wrap the description in quotes:
```csv
name,description,price,category
Combo Meal,"Burger, fries, and drink",15.99,Main Course
```

## Advanced Usage

### Bulk Editing

1. Open `menu_items.csv` in Excel or Google Sheets
2. Make multiple changes at once
3. Save as CSV
4. Reload in the application (option 7)

### Backup

Create a backup before making major changes:

```bash
cp menu_items.csv menu_items.backup.csv
```

### Version Control

The CSV format is git-friendly:

```bash
git add menu_items.csv
git commit -m "Updated menu prices"
```

### Different Restaurants

Create multiple CSV files for different restaurants:

```bash
menu_italian.csv
menu_chinese.csv
menu_mexican.csv
```

Modify `Restaurant.java` to load the desired file:

```java
this.menu = new Menu("menu_italian.csv");
```

## Future Enhancements

Potential improvements:
- [ ] JSON format support (better structure)
- [ ] Image URLs for menu items
- [ ] Multi-language support
- [ ] Price history tracking
- [ ] Nutritional information
- [ ] Allergen warnings
- [ ] Inventory management
- [ ] Daily specials

## License

This is a sample educational project. Feel free to use and modify as needed.

## Support

For issues or questions:
1. Check this README
2. Verify CSV file format
3. Look at console error messages
4. Review the example `menu_items.csv`

---

**Happy Editing!** 🍽️

Remember: You can edit the menu anytime by opening `menu_items.csv` in your favorite editor!