# Restaurant Menu Application

A modern, browser-based restaurant menu management system with **real-time editing** and **CSV import/export**. No server or database required — runs entirely in your browser!

## ✨ Features

- 🌐 **Browser-Based**: Runs entirely in the browser, no installation needed
- 💾 **Auto-Save**: Changes automatically saved to browser storage
- 📥 **CSV Import/Export**: Import existing menus or export for backup
- ✏️ **Full CRUD**: Create, Read, Update, and Delete menu items
- 🏷️ **Category Organization**: Browse items by Appetizers, Main Courses, Desserts, Beverages
- 🔍 **Real-Time Search**: Instant search by name or description
- 📱 **Responsive Design**: Works on desktop, tablet, and mobile
- 🎨 **Modern UI**: Clean, intuitive interface with smooth animations

## 🚀 Quick Start

### Option 1: Open in Browser

Simply open `index.html` in any modern web browser:

```bash
# Using Python (if installed)
python -m http.server 8000

# Using Node.js (if installed)
npx serve

# Or just double-click index.html
```

Then navigate to `http://localhost:8000` or the file directly.

### Option 2: Live Preview

If you're using VS Code:
1. Install the "Live Server" extension
2. Right-click `index.html`
3. Select "Open with Live Server"

## 📖 How to Use

### Managing Menu Items

1. **View All Items**: See your complete menu at a glance
2. **Browse by Category**: Filter items by type (Appetizers, Main Course, etc.)
3. **Search**: Type to instantly find items by name or description
4. **Add New Item**: Click "Add Item" and fill out the form
5. **Edit Item**: Click the ✏️ edit button on any menu card
6. **Delete Item**: Click the 🗑️ delete button (with confirmation)

### Import/Export CSV

#### Export Your Menu
1. Click "📤 Export CSV" button
2. Save the `menu_items.csv` file
3. Edit in Excel, Google Sheets, or any text editor

#### Import a Menu
1. Click "📥 Import CSV" button
2. Select your CSV file
3. Menu items will be loaded instantly

### CSV File Format

Your CSV file should have this structure:

```csv
name,description,price,category
Spring Rolls,Crispy vegetable spring rolls with sweet chili sauce,6.99,Appetizer
Grilled Salmon,Fresh Atlantic salmon with lemon butter,18.99,Main Course
Chocolate Cake,Rich chocolate layer cake,6.50,Dessert
Coffee,Freshly brewed coffee,2.99,Beverage
```

**Column Requirements:**
- `name` - Item name (text)
- `description` - Item description (text)
- `price` - Price (number, e.g., 12.99)
- `category` - One of: Appetizer, Main Course, Dessert, Beverage

**Tips:**
- If descriptions contain commas, wrap in quotes: `"Pasta, with sauce"`
- Keep the header row: `name,description,price,category`
- Use decimal format for prices: `12.99` not `$12.99`

## 🏗️ Project Structure

```
RestaurantMenu/
├── index.html          # Main HTML structure
├── styles.css          # Modern, responsive styling
├── app.js              # Application logic (MenuManager class)
├── menu_items.csv      # Original sample data (reference)
└── README.md           # This file
```

## 💾 Data Storage

- **Browser Storage**: Menu items are saved to `localStorage`
- **Persistent**: Data survives page refreshes
- **Per-Browser**: Each browser has its own storage
- **Export Backup**: Use CSV export to backup your menu

## 🎨 Features in Detail

### Real-Time Updates
All changes are immediately reflected in the UI and saved to browser storage.

### Category System
Four built-in categories with emoji icons:
- 🥟 Appetizers
- 🍖 Main Courses
- 🍰 Desserts
- ☕ Beverages

### Search Functionality
- Searches both item names and descriptions
- Updates results as you type (300ms debounce)
- Case-insensitive matching

### Responsive Design
- Desktop: Multi-column grid layout
- Tablet: Adaptive grid
- Mobile: Single-column layout
- Touch-friendly buttons and interactions

## 🔧 Technical Details

### Technologies Used
- **HTML5**: Semantic structure
- **CSS3**: Modern styling with CSS Grid and Flexbox
- **Vanilla JavaScript**: No frameworks or dependencies
- **localStorage API**: Client-side data persistence
- **FileReader API**: CSV import functionality

### Browser Compatibility
Works in all modern browsers:
- ✅ Chrome/Edge (v90+)
- ✅ Firefox (v88+)
- ✅ Safari (v14+)
- ✅ Opera (v76+)

### No Dependencies
This application has **zero external dependencies**. Everything runs with native browser APIs.

## 🔄 Migration from Java Version

This web version replaces the original Java console application with:

- ✅ Modern browser-based UI instead of terminal interface
- ✅ Point-and-click instead of numbered menus
- ✅ localStorage instead of CSV file I/O
- ✅ CSV import/export for compatibility
- ✅ Real-time search and filtering
- ✅ Responsive design for all devices

All original features are preserved:
- View full menu ✓
- View by category ✓
- Add new items ✓
- Update existing items ✓
- Delete items ✓
- Search by name ✓
- Load/save menu data ✓

## 📝 Example Usage

### Adding a New Item

1. Click "➕ Add Item"
2. Fill in the form:
   - Name: "Caesar Salad"
   - Description: "Romaine lettuce with parmesan and croutons"
   - Price: 8.99
   - Category: Appetizer
3. Click "Add Item"
4. Item appears immediately in the menu

### Editing an Item

1. Find the item in any view
2. Click the ✏️ edit button
3. Modify the fields
4. Click "Update Item"

### Importing a Menu

1. Prepare your CSV file with the correct format
2. Click "📥 Import CSV"
3. Select your file
4. All items are loaded and saved

## 🎯 Future Enhancements

Possible additions:
- 🖼️ Image upload for menu items
- 💰 Currency selection
- 🌍 Multi-language support
- 📊 Price analytics
- 🎨 Theme customization
- 📤 Export to PDF
- 🔗 Share menu via link

## 📄 License

This project is open source and available for educational purposes.

## 🤝 Contributing

Feel free to fork, modify, and submit pull requests!

---

**Note**: This is a client-side application. All data is stored in your browser's localStorage. To backup your menu, use the CSV export feature regularly.