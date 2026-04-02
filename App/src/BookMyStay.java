import java.util.*;
public class BookMyStayApp {

    static class InvalidBookingException extends Exception {
        public InvalidBookingException(String message) {
            super(message);
        }
    }

    static class RoomInventory {
        private Map<String, Integer> roomAvailability;

        public RoomInventory() {
            roomAvailability = new HashMap<>();
            roomAvailability.put("Single", 2);
            roomAvailability.put("Double", 1);
            roomAvailability.put("Suite", 1);
        }

        public Map<String, Integer> getRoomAvailability() {
            return roomAvailability;
        }
    }

    static class ReservationValidator {

        public void validate(String guestName, String roomType, RoomInventory inventory)
                throws InvalidBookingException {

            if (guestName == null || guestName.trim().isEmpty()) {
                throw new InvalidBookingException("Guest name cannot be empty.");
            }

            String formattedRoomType =
                    roomType.substring(0, 1).toUpperCase() +
                            roomType.substring(1).toLowerCase();

            if (!inventory.getRoomAvailability().containsKey(formattedRoomType)) {
                throw new InvalidBookingException("Invalid room type selected.");
            }

            if (inventory.getRoomAvailability().get(formattedRoomType) <= 0) {
                throw new InvalidBookingException("No rooms available for selected type.");
            }
        }
    }

    static class BookingRequestQueue {
        private Queue<String> queue;

        public BookingRequestQueue() {
            queue = new LinkedList<>();
        }

        public void addRequest(String request) {
            queue.offer(request);
        }
    }

    public static void main(String[] args) {

        System.out.println("Booking Validation");

        Scanner scanner = new Scanner(System.in);

        RoomInventory inventory = new RoomInventory();
        ReservationValidator validator = new ReservationValidator();
        BookingRequestQueue bookingQueue = new BookingRequestQueue();

        try {
            System.out.print("Enter guest name: ");
            String guestName = scanner.nextLine();

            System.out.print("Enter room type (Single/Double/Suite): ");
            String roomType = scanner.nextLine();

            validator.validate(guestName, roomType, inventory);

            bookingQueue.addRequest(guestName + " - " + roomType);

            System.out.println("Booking request accepted.");

        } catch (InvalidBookingException e) {
            System.out.println("Booking failed: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}