package socit.inprola.finals;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Comparator;

// FoodItem class representing individual food items
class FoodItem {
    // Fields
    private String name;
    private String category;
    private LocalDate expiryDate;
    private int quantity;
    private String location; // e.g., "Fridge", "Pantry", "Freezer"
    
    // Constructor
    public FoodItem(String name, String category, LocalDate expiryDate, int quantity, String location) {
        this.name = name;
        this.category = category;
        this.expiryDate = expiryDate;
        this.quantity = quantity;
        this.location = location;
    }
    
    // Getters and Setters
    public String getName() { return name; }
    public String getCategory() { return category; }
    public LocalDate getExpiryDate() { return expiryDate; }
    public int getQuantity() { return quantity; }
    public String getLocation() { return location; }
    
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public void setExpiryDate(LocalDate expiryDate) { this.expiryDate = expiryDate; }
    
    // Method to check if item is expiring soon (within 3 days)
    public boolean isExpiringSoon() {
        LocalDate today = LocalDate.now();
        LocalDate threeDaysFromNow = today.plusDays(3);
        return !expiryDate.isAfter(threeDaysFromNow) && !expiryDate.isBefore(today);
    }
    
    // Method to check if item is expired
    public boolean isExpired() {
        return expiryDate.isBefore(LocalDate.now());
    }
    
    // Method to get days until expiry
    public long getDaysUntilExpiry() {
        return java.time.temporal.ChronoUnit.DAYS.between(LocalDate.now(), expiryDate);
    }
    
    // Override toString for display
    @Override
    public String toString() {
        String status = "";
        if (isExpired()) {
            status = " [EXPIRED]";
        } else if (isExpiringSoon()) {
            status = " [EXPIRING SOON]";
        }
        
        return String.format("%-15s | %-10s | %-12s | Qty: %-3d | %s%s", 
            name, category, expiryDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), 
            quantity, location, status);
    }
}

// InventoryManager class to handle the list of food items
class InventoryManager {
    // Using ArrayList to store food items
    private ArrayList<FoodItem> inventory;
    
    // Constructor
    public InventoryManager() {
        inventory = new ArrayList<>();
        initializeSampleData(); // Add some sample data for testing
    }
    
    // Initialize with some sample data
    private void initializeSampleData() {
        LocalDate today = LocalDate.now();
        inventory.add(new FoodItem("Milk", "Dairy", today.plusDays(2), 1, "Fridge"));
        inventory.add(new FoodItem("Eggs", "Dairy", today.plusDays(14), 12, "Fridge"));
        inventory.add(new FoodItem("Rice", "Grains", today.plusDays(180), 1, "Pantry"));
        inventory.add(new FoodItem("Tomatoes", "Produce", today.minusDays(1), 3, "Fridge"));
        inventory.add(new FoodItem("Chicken", "Meat", today.plusDays(5), 2, "Freezer"));
        inventory.add(new FoodItem("Bread", "Bakery", today.plusDays(7), 1, "Pantry"));
        inventory.add(new FoodItem("Apples", "Produce", today.plusDays(10), 6, "Fridge"));
    }
    
    // Method to add a new food item
    public void addFoodItem(FoodItem item) {
        inventory.add(item);
        System.out.println("✓ " + item.getName() + " added to inventory.");
    }
    
    // Method to remove a food item by name
    public boolean removeFoodItem(String itemName) {
        for (int i = 0; i < inventory.size(); i++) {
            if (inventory.get(i).getName().equalsIgnoreCase(itemName)) {
                FoodItem removed = inventory.remove(i);
                System.out.println("✓ " + removed.getName() + " removed from inventory.");
                return true;
            }
        }
        System.out.println("✗ Item not found: " + itemName);
        return false;
    }
    
    // Method to update quantity of a food item
    public boolean updateQuantity(String itemName, int newQuantity) {
        for (FoodItem item : inventory) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                item.setQuantity(newQuantity);
                System.out.println("✓ " + itemName + " quantity updated to " + newQuantity);
                return true;
            }
        }
        System.out.println("✗ Item not found: " + itemName);
        return false;
    }
    
    // Method to view all items
    public void viewAllItems() {
        if (inventory.isEmpty()) {
            System.out.println("Inventory is empty.");
            return;
        }
        
        System.out.println("\n" + "=".repeat(80));
        System.out.println("                          FOOD INVENTORY");
        System.out.println("=".repeat(80));
        System.out.printf("%-15s | %-10s | %-12s | %-8s | %-10s%n", 
            "Name", "Category", "Expiry Date", "Quantity", "Location");
        System.out.println("-".repeat(80));
        
        for (FoodItem item : inventory) {
            System.out.println(item);
        }
        System.out.println("=".repeat(80));
    }
    
    // Method to view items expiring soon (within 3 days)
    public void viewExpiringSoon() {
        System.out.println("\n" + "=".repeat(80));
        System.out.println("                    ITEMS EXPIRING WITHIN 3 DAYS");
        System.out.println("=".repeat(80));
        
        boolean found = false;
        for (FoodItem item : inventory) {
            if (item.isExpiringSoon() && !item.isExpired()) {
                System.out.println(item);
                found = true;
            }
        }
        
        if (!found) {
            System.out.println("No items expiring within 3 days.");
        }
        System.out.println("=".repeat(80));
    }
    
    // Method to view expired items
    public void viewExpiredItems() {
        System.out.println("\n" + "=".repeat(80));
        System.out.println("                         EXPIRED ITEMS");
        System.out.println("=".repeat(80));
        
        boolean found = false;
        for (FoodItem item : inventory) {
            if (item.isExpired()) {
                System.out.println(item);
                found = true;
            }
        }
        
        if (!found) {
            System.out.println("No expired items.");
        }
        System.out.println("=".repeat(80));
    }
    
    // Method to search items by category
    public void searchByCategory(String category) {
        System.out.println("\n" + "=".repeat(80));
        System.out.println("                   ITEMS IN CATEGORY: " + category.toUpperCase());
        System.out.println("=".repeat(80));
        
        boolean found = false;
        for (FoodItem item : inventory) {
            if (item.getCategory().equalsIgnoreCase(category)) {
                System.out.println(item);
                found = true;
            }
        }
        
        if (!found) {
            System.out.println("No items found in category: " + category);
        }
        System.out.println("=".repeat(80));
    }
    
    // Method to search items by location
    public void searchByLocation(String location) {
        System.out.println("\n" + "=".repeat(80));
        System.out.println("                   ITEMS IN LOCATION: " + location.toUpperCase());
        System.out.println("=".repeat(80));
        
        boolean found = false;
        for (FoodItem item : inventory) {
            if (item.getLocation().equalsIgnoreCase(location)) {
                System.out.println(item);
                found = true;
            }
        }
        
        if (!found) {
            System.out.println("No items found in location: " + location);
        }
        System.out.println("=".repeat(80));
    }
    
    // Method to sort items by expiry date (ascending)
    public void sortByExpiryDate() {
        inventory.sort(Comparator.comparing(FoodItem::getExpiryDate));
        System.out.println("✓ Inventory sorted by expiry date (earliest first).");
    }
    
    // Method to get total items count
    public int getTotalItemCount() {
        return inventory.size();
    }
    
    // Method to get count of expired items
    public int getExpiredItemCount() {
        int count = 0;
        for (FoodItem item : inventory) {
            if (item.isExpired()) {
                count++;
            }
        }
        return count;
    }
    
    // Method to get count of items expiring soon
    public int getExpiringSoonCount() {
        int count = 0;
        for (FoodItem item : inventory) {
            if (item.isExpiringSoon() && !item.isExpired()) {
                count++;
            }
        }
        return count;
    }
}

// Main class to run the application
public class WasteNotApp {
    private static Scanner scanner = new Scanner(System.in);
    private static InventoryManager inventoryManager = new InventoryManager();
    private static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    
    public static void main(String[] args) {
        System.out.println("\n" + "*".repeat(80));
        System.out.println("              WASTE NOT: FOOD INVENTORY & EXPIRY TRACKER");
        System.out.println("*".repeat(80));
        
        displayWelcomeStats();
        
        boolean running = true;
        
        while (running) {
            displayMainMenu();
            System.out.print("\nEnter your choice (1-10): ");
            String choice = scanner.nextLine();
            
            switch (choice) {
                case "1":
                    addNewItem();
                    break;
                case "2":
                    removeItem();
                    break;
                case "3":
                    updateQuantity();
                    break;
                case "4":
                    inventoryManager.viewAllItems();
                    break;
                case "5":
                    inventoryManager.viewExpiringSoon();
                    break;
                case "6":
                    inventoryManager.viewExpiredItems();
                    break;
                case "7":
                    searchByCategory();
                    break;
                case "8":
                    searchByLocation();
                    break;
                case "9":
                    inventoryManager.sortByExpiryDate();
                    inventoryManager.viewAllItems();
                    break;
                case "10":
                    displayWelcomeStats();
                    break;
                case "0":
                    System.out.println("\nThank you for using Waste Not!");
                    System.out.println("Remember: Reducing food waste helps both your wallet and our planet!");
                    running = false;
                    break;
                default:
                    System.out.println("\n✗ Invalid choice. Please enter a number between 0 and 10.");
            }
            
            if (running) {
                System.out.print("\nPress Enter to continue...");
                scanner.nextLine();
            }
        }
        
        scanner.close();
    }
    
    private static void displayWelcomeStats() {
        System.out.println("\n" + "-".repeat(80));
        System.out.println("INVENTORY SUMMARY:");
        System.out.println("Total Items: " + inventoryManager.getTotalItemCount());
        System.out.println("Items Expiring Soon: " + inventoryManager.getExpiringSoonCount());
        System.out.println("Expired Items: " + inventoryManager.getExpiredItemCount());
        System.out.println("-".repeat(80));
    }
    
    private static void displayMainMenu() {
        System.out.println("\n" + "=".repeat(80));
        System.out.println("                                 MAIN MENU");
        System.out.println("=".repeat(80));
        System.out.println("1. Add New Food Item");
        System.out.println("2. Remove Food Item");
        System.out.println("3. Update Item Quantity");
        System.out.println("4. View All Items");
        System.out.println("5. View Items Expiring Soon (≤ 3 days)");
        System.out.println("6. View Expired Items");
        System.out.println("7. Search by Category");
        System.out.println("8. Search by Location");
        System.out.println("9. Sort by Expiry Date");
        System.out.println("10. Show Summary Stats");
        System.out.println("0. Exit");
        System.out.println("=".repeat(80));
    }
    
    private static void addNewItem() {
        System.out.println("\n" + "-".repeat(80));
        System.out.println("                      ADD NEW FOOD ITEM");
        System.out.println("-".repeat(80));
        
        try {
            System.out.print("Enter item name: ");
            String name = scanner.nextLine();
            
            System.out.print("Enter category (Produce/Dairy/Meat/Grains/Bakery/Other): ");
            String category = scanner.nextLine();
            
            System.out.print("Enter expiry date (YYYY-MM-DD): ");
            String dateInput = scanner.nextLine();
            LocalDate expiryDate = LocalDate.parse(dateInput, dateFormatter);
            
            if (expiryDate.isBefore(LocalDate.now())) {
                System.out.println("⚠ Warning: This expiry date is in the past!");
            }
            
            System.out.print("Enter quantity: ");
            int quantity = Integer.parseInt(scanner.nextLine());
            
            System.out.print("Enter location (Fridge/Pantry/Freezer/Other): ");
            String location = scanner.nextLine();
            
            FoodItem newItem = new FoodItem(name, category, expiryDate, quantity, location);
            inventoryManager.addFoodItem(newItem);
            
        } catch (DateTimeParseException e) {
            System.out.println("✗ Invalid date format. Please use YYYY-MM-DD.");
        } catch (NumberFormatException e) {
            System.out.println("✗ Invalid quantity. Please enter a number.");
        }
    }
    
    private static void removeItem() {
        System.out.println("\n" + "-".repeat(80));
        System.out.println("                      REMOVE FOOD ITEM");
        System.out.println("-".repeat(80));
        
        System.out.print("Enter the name of the item to remove: ");
        String itemName = scanner.nextLine();
        inventoryManager.removeFoodItem(itemName);
    }
    
    private static void updateQuantity() {
        System.out.println("\n" + "-".repeat(80));
        System.out.println("                     UPDATE ITEM QUANTITY");
        System.out.println("-".repeat(80));
        
        System.out.print("Enter the name of the item: ");
        String itemName = scanner.nextLine();
        
        try {
            System.out.print("Enter new quantity: ");
            int newQuantity = Integer.parseInt(scanner.nextLine());
            
            if (newQuantity < 0) {
                System.out.println("✗ Quantity cannot be negative.");
                return;
            }
            
            inventoryManager.updateQuantity(itemName, newQuantity);
        } catch (NumberFormatException e) {
            System.out.println("✗ Invalid quantity. Please enter a number.");
        }
    }
    
    private static void searchByCategory() {
        System.out.println("\n" + "-".repeat(80));
        System.out.println("                     SEARCH BY CATEGORY");
        System.out.println("-".repeat(80));
        
        System.out.print("Enter category to search (Produce/Dairy/Meat/Grains/Bakery/Other): ");
        String category = scanner.nextLine();
        inventoryManager.searchByCategory(category);
    }
    
    private static void searchByLocation() {
        System.out.println("\n" + "-".repeat(80));
        System.out.println("                     SEARCH BY LOCATION");
        System.out.println("-".repeat(80));
        
        System.out.print("Enter location to search (Fridge/Pantry/Freezer/Other): ");
        String location = scanner.nextLine();
        inventoryManager.searchByLocation(location);
    }
}