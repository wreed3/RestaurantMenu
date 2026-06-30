// Restaurant Menu Manager - Client-Side Application

class MenuManager {
    constructor() {
        this.menuItems = [];
        this.currentView = 'all';
        this.editingIndex = null;
        this.init();
    }

    init() {
        this.loadFromStorage();
        this.setupEventListeners();
        this.renderAllItems();
        this.updateItemCount();
    }

    // Data Management
    loadFromStorage() {
        const stored = localStorage.getItem('restaurantMenu');
        if (stored) {
            this.menuItems = JSON.parse(stored);
        } else {
            // Initialize with sample data from CSV
            this.menuItems = [
                {
                    name: "Spring Rolls",
                    description: "Crispy vegetable spring rolls with sweet chili sauce",
                    price: 6.99,
                    category: "Appetizer"
                },
                {
                    name: "Bruschetta",
                    description: "Toasted bread with tomatoes basil and garlic",
                    price: 7.50,
                    category: "Appetizer"
                },
                {
                    name: "Grilled Salmon",
                    description: "Fresh Atlantic salmon with lemon butter",
                    price: 18.99,
                    category: "Main Course"
                },
                {
                    name: "Beef Burger",
                    description: "Angus beef burger with fries",
                    price: 12.99,
                    category: "Main Course"
                },
                {
                    name: "Chicken Pasta",
                    description: "Creamy Alfredo pasta with grilled chicken",
                    price: 14.99,
                    category: "Main Course"
                },
                {
                    name: "Chocolate Cake",
                    description: "Rich chocolate layer cake",
                    price: 6.50,
                    category: "Dessert"
                },
                {
                    name: "Tiramisu",
                    description: "Classic Italian coffee-flavored dessert",
                    price: 7.00,
                    category: "Dessert"
                },
                {
                    name: "Coffee",
                    description: "Freshly brewed coffee",
                    price: 2.99,
                    category: "Beverage"
                },
                {
                    name: "Fresh Juice",
                    description: "Orange apple or mixed berry",
                    price: 3.99,
                    category: "Beverage"
                }
            ];
            this.saveToStorage();
        }
    }

    saveToStorage() {
        localStorage.setItem('restaurantMenu', JSON.stringify(this.menuItems));
    }

    // CRUD Operations
    addItem(item) {
        this.menuItems.push(item);
        this.saveToStorage();
        this.renderAllItems();
        this.updateItemCount();
    }

    updateItem(index, item) {
        this.menuItems[index] = item;
        this.saveToStorage();
        this.renderCurrentView();
        this.updateItemCount();
    }

    deleteItem(index) {
        this.menuItems.splice(index, 1);
        this.saveToStorage();
        this.renderCurrentView();
        this.updateItemCount();
    }

    clearAll() {
        this.menuItems = [];
        this.saveToStorage();
        this.renderCurrentView();
        this.updateItemCount();
    }

    // Rendering
    renderAllItems() {
        const container = document.getElementById('menuItemsContainer');
        if (this.menuItems.length === 0) {
            container.innerHTML = this.getEmptyState('No menu items yet', 'Add your first item to get started!');
            return;
        }

        container.innerHTML = this.menuItems
            .map((item, index) => this.createMenuItemCard(item, index))
            .join('');
    }

    renderCategoryItems() {
        const category = document.getElementById('categorySelect').value;
        const container = document.getElementById('categoryItemsContainer');

        if (!category) {
            container.innerHTML = this.getEmptyState('Select a category', 'Choose a category from the dropdown above');
            return;
        }

        const filtered = this.menuItems.filter(item => item.category === category);

        if (filtered.length === 0) {
            container.innerHTML = this.getEmptyState('No items in this category', 'Try selecting a different category');
            return;
        }

        container.innerHTML = filtered
            .map(item => {
                const index = this.menuItems.indexOf(item);
                return this.createMenuItemCard(item, index);
            })
            .join('');
    }

    renderSearchResults() {
        const query = document.getElementById('searchInput').value.toLowerCase();
        const container = document.getElementById('searchResultsContainer');

        if (!query) {
            container.innerHTML = this.getEmptyState('Start typing to search', 'Search by item name or description');
            return;
        }

        const filtered = this.menuItems.filter(item =>
            item.name.toLowerCase().includes(query) ||
            item.description.toLowerCase().includes(query)
        );

        if (filtered.length === 0) {
            container.innerHTML = this.getEmptyState('No results found', `No items match "${query}"`);
            return;
        }

        container.innerHTML = filtered
            .map(item => {
                const index = this.menuItems.indexOf(item);
                return this.createMenuItemCard(item, index);
            })
            .join('');
    }

    createMenuItemCard(item, index) {
        const categoryEmoji = {
            'Appetizer': '🥟',
            'Main Course': '🍖',
            'Dessert': '🍰',
            'Beverage': '☕'
        };

        return `
            <div class="menu-item">
                <div class="menu-item-header">
                    <div>
                        <div class="menu-item-name">${this.escapeHtml(item.name)}</div>
                        <span class="menu-item-category">${categoryEmoji[item.category] || ''} ${item.category}</span>
                    </div>
                </div>
                <p class="menu-item-description">${this.escapeHtml(item.description)}</p>
                <div class="menu-item-footer">
                    <span class="menu-item-price">$${item.price.toFixed(2)}</span>
                    <div class="menu-item-actions">
                        <button class="icon-btn edit" onclick="menuManager.editItem(${index})" title="Edit">✏️</button>
                        <button class="icon-btn delete" onclick="menuManager.confirmDelete(${index})" title="Delete">🗑️</button>
                    </div>
                </div>
            </div>
        `;
    }

    getEmptyState(title, message) {
        return `
            <div class="empty-state">
                <div class="empty-state-icon">📭</div>
                <div class="empty-state-text">${title}</div>
                <div>${message}</div>
            </div>
        `;
    }

    // View Management
    switchView(viewName) {
        // Update navigation
        document.querySelectorAll('.nav-btn').forEach(btn => {
            btn.classList.toggle('active', btn.dataset.view === viewName);
        });

        // Update views
        document.querySelectorAll('.view').forEach(view => {
            view.classList.remove('active');
        });

        const viewMap = {
            'all': 'allItemsView',
            'category': 'categoryView',
            'search': 'searchView',
            'add': 'addItemView'
        };

        document.getElementById(viewMap[viewName]).classList.add('active');
        this.currentView = viewName;

        // Render appropriate content
        if (viewName === 'all') {
            this.renderAllItems();
        } else if (viewName === 'category') {
            this.renderCategoryItems();
        } else if (viewName === 'search') {
            this.renderSearchResults();
        } else if (viewName === 'add') {
            this.resetForm();
        }
    }

    renderCurrentView() {
        if (this.currentView === 'all') {
            this.renderAllItems();
        } else if (this.currentView === 'category') {
            this.renderCategoryItems();
        } else if (this.currentView === 'search') {
            this.renderSearchResults();
        }
    }

    // Form Management
    resetForm() {
        document.getElementById('itemForm').reset();
        document.getElementById('editIndex').value = '';
        document.getElementById('formTitle').textContent = 'Add New Item';
        document.getElementById('submitBtn').textContent = 'Add Item';
        this.editingIndex = null;
    }

    editItem(index) {
        const item = this.menuItems[index];
        this.editingIndex = index;

        document.getElementById('editIndex').value = index;
        document.getElementById('itemName').value = item.name;
        document.getElementById('itemDescription').value = item.description;
        document.getElementById('itemPrice').value = item.price;
        document.getElementById('itemCategory').value = item.category;

        document.getElementById('formTitle').textContent = 'Edit Item';
        document.getElementById('submitBtn').textContent = 'Update Item';

        this.switchView('add');
    }

    handleFormSubmit(e) {
        e.preventDefault();

        const item = {
            name: document.getElementById('itemName').value.trim(),
            description: document.getElementById('itemDescription').value.trim(),
            price: parseFloat(document.getElementById('itemPrice').value),
            category: document.getElementById('itemCategory').value
        };

        if (this.editingIndex !== null) {
            this.updateItem(this.editingIndex, item);
            this.showNotification('Item updated successfully!');
        } else {
            this.addItem(item);
            this.showNotification('Item added successfully!');
        }

        this.switchView('all');
    }

    // CSV Import/Export
    exportToCsv() {
        const headers = ['name', 'description', 'price', 'category'];
        const csvContent = [
            headers.join(','),
            ...this.menuItems.map(item =>
                headers.map(header => {
                    const value = item[header].toString();
                    // Escape commas and quotes
                    if (value.includes(',') || value.includes('"') || value.includes('\n')) {
                        return `"${value.replace(/"/g, '""')}"`;
                    }
                    return value;
                }).join(',')
            )
        ].join('\n');

        const blob = new Blob([csvContent], { type: 'text/csv' });
        const url = URL.createObjectURL(blob);
        const a = document.createElement('a');
        a.href = url;
        a.download = 'menu_items.csv';
        a.click();
        URL.revokeObjectURL(url);

        this.showNotification('Menu exported successfully!');
    }

    importFromCsv(file) {
        const reader = new FileReader();
        reader.onload = (e) => {
            try {
                const text = e.target.result;
                const lines = text.split('\n').filter(line => line.trim());
                const headers = lines[0].split(',').map(h => h.trim());

                const items = [];
                for (let i = 1; i < lines.length; i++) {
                    const values = this.parseCSVLine(lines[i]);
                    if (values.length === 4) {
                        items.push({
                            name: values[0],
                            description: values[1],
                            price: parseFloat(values[2]),
                            category: values[3]
                        });
                    }
                }

                if (items.length > 0) {
                    this.menuItems = items;
                    this.saveToStorage();
                    this.renderCurrentView();
                    this.updateItemCount();
                    this.showNotification(`Imported ${items.length} items successfully!`);
                } else {
                    this.showNotification('No valid items found in CSV', true);
                }
            } catch (error) {
                console.error('Import error:', error);
                this.showNotification('Error importing CSV file', true);
            }
        };
        reader.readAsText(file);
    }

    parseCSVLine(line) {
        const result = [];
        let current = '';
        let inQuotes = false;

        for (let i = 0; i < line.length; i++) {
            const char = line[i];
            const nextChar = line[i + 1];

            if (char === '"') {
                if (inQuotes && nextChar === '"') {
                    current += '"';
                    i++;
                } else {
                    inQuotes = !inQuotes;
                }
            } else if (char === ',' && !inQuotes) {
                result.push(current.trim());
                current = '';
            } else {
                current += char;
            }
        }
        result.push(current.trim());

        return result;
    }

    // Modal and Confirmations
    confirmDelete(index) {
        this.showModal(
            'Delete Item',
            `Are you sure you want to delete "${this.menuItems[index].name}"?`,
            () => this.deleteItem(index)
        );
    }

    confirmClearAll() {
        this.showModal(
            'Clear All Items',
            'Are you sure you want to delete ALL menu items? This cannot be undone.',
            () => this.clearAll()
        );
    }

    showModal(title, message, onConfirm) {
        const modal = document.getElementById('modal');
        document.getElementById('modalTitle').textContent = title;
        document.getElementById('modalMessage').textContent = message;

        modal.classList.add('active');

        const confirmBtn = document.getElementById('modalConfirm');
        const cancelBtn = document.getElementById('modalCancel');

        const closeModal = () => {
            modal.classList.remove('active');
            confirmBtn.replaceWith(confirmBtn.cloneNode(true));
            cancelBtn.replaceWith(cancelBtn.cloneNode(true));
        };

        document.getElementById('modalConfirm').onclick = () => {
            onConfirm();
            closeModal();
        };

        document.getElementById('modalCancel').onclick = closeModal;
    }

    // Utilities
    updateItemCount() {
        document.getElementById('totalCount').textContent = `${this.menuItems.length} items`;
    }

    showNotification(message, isError = false) {
        // Simple notification (you could enhance this with a toast library)
        const notification = document.createElement('div');
        notification.textContent = message;
        notification.style.cssText = `
            position: fixed;
            top: 20px;
            right: 20px;
            padding: 1rem 1.5rem;
            background: ${isError ? '#dc2626' : '#16a34a'};
            color: white;
            border-radius: 8px;
            box-shadow: 0 4px 12px rgba(0,0,0,0.15);
            z-index: 2000;
            animation: slideIn 0.3s ease;
        `;

        document.body.appendChild(notification);

        setTimeout(() => {
            notification.style.animation = 'slideOut 0.3s ease';
            setTimeout(() => notification.remove(), 300);
        }, 3000);
    }

    escapeHtml(text) {
        const div = document.createElement('div');
        div.textContent = text;
        return div.innerHTML;
    }

    // Event Listeners Setup
    setupEventListeners() {
        // Navigation
        document.querySelectorAll('.nav-btn').forEach(btn => {
            btn.addEventListener('click', () => this.switchView(btn.dataset.view));
        });

        // Form
        document.getElementById('itemForm').addEventListener('submit', (e) => this.handleFormSubmit(e));
        document.getElementById('cancelBtn').addEventListener('click', () => {
            this.resetForm();
            this.switchView('all');
        });

        // Category select
        document.getElementById('categorySelect').addEventListener('change', () => this.renderCategoryItems());

        // Search input
        let searchTimeout;
        document.getElementById('searchInput').addEventListener('input', () => {
            clearTimeout(searchTimeout);
            searchTimeout = setTimeout(() => this.renderSearchResults(), 300);
        });

        // CSV Import/Export
        document.getElementById('exportCsvBtn').addEventListener('click', () => this.exportToCsv());
        document.getElementById('importCsvBtn').addEventListener('click', () => {
            document.getElementById('csvFileInput').click();
        });
        document.getElementById('csvFileInput').addEventListener('change', (e) => {
            if (e.target.files[0]) {
                this.importFromCsv(e.target.files[0]);
                e.target.value = ''; // Reset input
            }
        });

        // Clear all
        document.getElementById('clearAllBtn').addEventListener('click', () => this.confirmClearAll());
    }
}

// Initialize the application
const menuManager = new MenuManager();

// Add CSS animation keyframes
const style = document.createElement('style');
style.textContent = `
    @keyframes slideIn {
        from { transform: translateX(100%); opacity: 0; }
        to { transform: translateX(0); opacity: 1; }
    }
    @keyframes slideOut {
        from { transform: translateX(0); opacity: 1; }
        to { transform: translateX(100%); opacity: 0; }
    }
`;
document.head.appendChild(style);