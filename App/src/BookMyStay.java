import java.util.HashMap;

public class BookMyStay {

    private HashMap<String, Integer> inventory;

    public BookMyStay() {
        inventory = new HashMap<>();
    }

    public void addRoomType(String roomType, int count) {
        inventory.put(roomType, count);
    }

    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    public void updateAvailability(String roomType, int newCount) {
        if (inventory.containsKey(roomType)) {
            inventory.put(roomType, newCount);
        } else {
            System.out.println("Room type not found!");
        }
    }

    public void displayInventory() {
        System.out.println("Current Room Inventory:");
        for (String roomType : inventory.keySet()) {
            System.out.println(roomType + " : " + inventory.get(roomType));
        }
    }

    public static void main(String[] args) {

        BookMyStay app = new BookMyStay();

        app.addRoomType("Single", 10);
        app.addRoomType("Double", 5);
        app.addRoomType("Suite", 2);

        app.displayInventory();

        System.out.println("\nAvailable Single Rooms: "
                + app.getAvailability("Single"));

        app.updateAvailability("Single", 8);

        System.out.println("\nAfter Update:");
        app.displayInventory();
    }
}