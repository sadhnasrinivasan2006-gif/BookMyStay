import java.io.*;
import java.util.*;
public class BookMyStayApp {

    static class RoomInventory {
        private Map<String, Integer> roomAvailability;

        public RoomInventory() {
            roomAvailability = new HashMap<>();
            roomAvailability.put("Single", 5);
            roomAvailability.put("Double", 3);
            roomAvailability.put("Suite", 2);
        }

        public Map<String, Integer> getRoomAvailability() {
            return roomAvailability;
        }

        public void setRoomAvailability(Map<String, Integer> map) {
            this.roomAvailability = map;
        }
    }

    static class FilePersistenceService {

        public void saveInventory(RoomInventory inventory, String filePath) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {

                for (Map.Entry<String, Integer> entry :
                        inventory.getRoomAvailability().entrySet()) {

                    writer.write(entry.getKey() + "=" + entry.getValue());
                    writer.newLine();
                }

                System.out.println("Inventory saved successfully.");

            } catch (IOException e) {
                System.out.println("Error saving inventory.");
            }
        }

        public void loadInventory(RoomInventory inventory, String filePath) {

            File file = new File(filePath);

            if (!file.exists()) {
                System.out.println("No valid inventory data found. Starting fresh.");
                return;
            }

            Map<String, Integer> map = new HashMap<>();

            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

                String line;

                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split("=");
                    map.put(parts[0], Integer.parseInt(parts[1]));
                }

                inventory.setRoomAvailability(map);

            } catch (Exception e) {
                System.out.println("Error loading inventory. Starting fresh.");
            }
        }
    }

    public static void main(String[] args) {

        System.out.println("System Recovery");

        String filePath = "inventory.txt";

        RoomInventory inventory = new RoomInventory();
        FilePersistenceService service = new FilePersistenceService();

        service.loadInventory(inventory, filePath);

        System.out.println("\nCurrent Inventory:");

        for (String type : inventory.getRoomAvailability().keySet()) {
            System.out.println(type + ": " +
                    inventory.getRoomAvailability().get(type));
        }

        service.saveInventory(inventory, filePath);
    }
}