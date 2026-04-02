import java.util.*;
public class BookMyStayApp {

    static class Reservation {
        private String guestName;
        private String roomType;

        public Reservation(String guestName, String roomType) {
            this.guestName = guestName;
            this.roomType = roomType;
        }

        public String getGuestName() {
            return guestName;
        }

        public String getRoomType() {
            return roomType;
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

        public void updateAvailability(String roomType, int count) {
            roomAvailability.put(roomType, count);
        }
    }

    static class BookingRequestQueue {
        private Queue<Reservation> requestQueue;

        public BookingRequestQueue() {
            requestQueue = new LinkedList<>();
        }

        public void addRequest(Reservation reservation) {
            requestQueue.offer(reservation);
        }

        public Reservation getNextRequest() {
            return requestQueue.poll();
        }

        public boolean hasPendingRequests() {
            return !requestQueue.isEmpty();
        }
    }

    static class RoomAllocationService {
        private Set<String> allocatedRoomIds;
        private Map<String, Set<String>> assignedRoomsByType;
        private Map<String, Integer> roomCounters;

        public RoomAllocationService() {
            allocatedRoomIds = new HashSet<>();
            assignedRoomsByType = new HashMap<>();
            roomCounters = new HashMap<>();
        }

        public void allocateRoom(Reservation reservation, RoomInventory inventory) {

            String roomType = reservation.getRoomType();
            roomType = roomType.substring(0,1).toUpperCase() + roomType.substring(1).toLowerCase();

            Map<String, Integer> availability = inventory.getRoomAvailability();

            if (!availability.containsKey(roomType) || availability.get(roomType) <= 0) {
                System.out.println("No rooms available for " + reservation.getGuestName());
                return;
            }

            String roomId = generateRoomId(roomType);

            allocatedRoomIds.add(roomId);

            assignedRoomsByType
                    .computeIfAbsent(roomType, k -> new HashSet<>())
                    .add(roomId);

            inventory.updateAvailability(roomType, availability.get(roomType) - 1);

            System.out.println("Booking confirmed for Guest: " +
                    reservation.getGuestName() +
                    ", Room ID: " + roomId);
        }

        private String generateRoomId(String roomType) {

            int count = roomCounters.getOrDefault(roomType, 0) + 1;
            roomCounters.put(roomType, count);

            String roomId = roomType + "-" + count;

            while (allocatedRoomIds.contains(roomId)) {
                count++;
                roomId = roomType + "-" + count;
            }

            return roomId;
        }
    }

    public static void main(String[] args) {

        System.out.println("Room Allocation Processing\n");

        RoomInventory inventory = new RoomInventory();
        BookingRequestQueue queue = new BookingRequestQueue();
        RoomAllocationService allocationService = new RoomAllocationService();

        queue.addRequest(new Reservation("Abhi", "Single"));
        queue.addRequest(new Reservation("Subha", "Single"));
        queue.addRequest(new Reservation("Vanmathi", "Suite"));

        while (queue.hasPendingRequests()) {
            Reservation request = queue.getNextRequest();
            allocationService.allocateRoom(request, inventory);
        }
    }
}