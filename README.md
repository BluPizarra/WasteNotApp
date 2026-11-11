# Waste Not: Food Inventory & Expiry Tracker

[![Java](https://img.shields.io/badge/Java-8%20%7C%2011%20%7C%2017%20%7C%2021-404258?style=for-the-badge&logo=openjdk&logoColor=white)](https://www.oracle.com/java/)
[![Application](https://img.shields.io/badge/App-Console%20CLI-474E68?style=for-the-badge)](https://github.com/BluPizarra/WasteNotApp)
[![License: MIT](https://img.shields.io/badge/License-MIT-50577A?style=for-the-badge)](LICENSE)
[![Academic](https://img.shields.io/badge/APC-SOCIT%20INPROLA-6B728E?style=for-the-badge)](https://www.apc.edu.ph/)

**Waste Not** is a console-based Java application built to manage household food inventories, keep track of expiration dates, organize food storage, and prevent unnecessary food waste.

---

## Key Features

- **Inventory Management**: Add new food items with name, food category, quantity, expiration date, and storage location.
- **Expiry Tracking & Alerts**:
  - Automatically flags items expiring within **3 days** with an `[EXPIRING SOON]` tag.
  - Automatically flags items that have passed their expiration date with an `[EXPIRED]` tag.
  - Dedicated menu options to list only expiring-soon or expired items.
- **Storage Location Filtering**: Categorize and search items by location (e.g., `Fridge`, `Pantry`, `Freezer`).
- **Category Search**: Group and find items by category (`Produce`, `Dairy`, `Meat`, `Grains`, `Bakery`, `Other`).
- **Sorting**: Instantly sort inventory by expiration date in ascending order (earliest expiring first).
- **Real-time Dashboard**: Displays total tracked items, items expiring soon, and expired counts upon launch and in summary views.
- **Modify & Remove**: Easily adjust quantities or remove consumed food items.

---

## Project Structure

```text
WasteNotApp/
├── .gitignore
├── README.md
└── src/
    └── socit/
        └── inprola/
            └── finals/
                └── WasteNotApp.java
```

---

## Getting Started

### Prerequisites
- **Java Development Kit (JDK)**: JDK 8 or higher (JDK 17 or JDK 21 recommended).

### How to Compile and Run

1. **Clone the repository:**
   ```bash
   git clone https://github.com/BluPizarra/WasteNotApp.git
   cd WasteNotApp
   ```

2. **Compile the source code:**
   ```bash
   javac -d bin src/socit/inprola/finals/WasteNotApp.java
   ```

3. **Run the application:**
   ```bash
   java -cp bin socit.inprola.finals.WasteNotApp
   ```

*Alternative quick run directly from `src`:*
```bash
javac src/socit/inprola/finals/WasteNotApp.java
java -cp src socit.inprola.finals.WasteNotApp
```

---

## Usage & Menu Options

Upon running the app, you will be presented with the interactive console menu:

```text
********************************************************************************
              WASTE NOT: FOOD INVENTORY & EXPIRY TRACKER
********************************************************************************

--------------------------------------------------------------------------------
INVENTORY SUMMARY:
Total Items: 7
Items Expiring Soon: 1
Expired Items: 1
--------------------------------------------------------------------------------

================================================================================
                                 MAIN MENU
================================================================================
1. Add New Food Item
2. Remove Food Item
3. Update Item Quantity
4. View All Items
5. View Items Expiring Soon (≤ 3 days)
6. View Expired Items
7. Search by Category
8. Search by Location
9. Sort by Expiry Date
10. Show Summary Stats
0. Exit
================================================================================
```

---

## Author

- **Bludylan Cyrus A. Pizarra** ([@BluPizarra](https://github.com/BluPizarra))
- **Course**: SOCIT - INPROLA (Introduction to Programming Languages) Finals Project