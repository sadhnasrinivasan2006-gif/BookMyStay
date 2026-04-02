import java.util.HashMap;

public class BookMyStay {

    private HashMap<String, Integer> inventory;
    private HashMap<String, Double> roomPrices;

    public BookMyStay() {
        inventory = new HashMap<>();
        roomPrices = new HashMap<>();
    }

    public void addRoomType(String type, int count, double price) {
        inventory.put(type, count);
        roomPrices.put(type, price);
    }

    public void searchRooms() {
        System.out.println("Available Rooms:\n");

        for (String type : inventory.keySet()) {
            int available = inventory.get(type);

            if (available > 0) {
                double price = roomPrices.get(type);

                System.out.println("Room Type: " + type);
                System.out.println("Price: ₹" + price);
                System.out.println("Available: " + available);
                System.out.println();
            }
        }
    }

    public static void main(String[] args) {

        BookMyStay app = new BookMyStay();

        app.addRoomType("Single", 5, 2000);
        app.addRoomType("Double", 0, 3500);
        app.addRoomType("Suite", 2, 5000);

        app.searchRooms();
    }
}