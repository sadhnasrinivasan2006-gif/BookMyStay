import java.util.*;
public class BookMyStayApp {

    static class Service {
        private String serviceName;
        private double cost;

        public Service(String serviceName, double cost) {
            this.serviceName = serviceName;
            this.cost = cost;
        }

        public double getCost() {
            return cost;
        }
    }

    static class AddOnServiceManager {
        private Map<String, List<Service>> servicesByReservation;

        public AddOnServiceManager() {
            servicesByReservation = new HashMap<>();
        }

        public void addService(String reservationId, Service service) {
            servicesByReservation
                    .computeIfAbsent(reservationId, k -> new ArrayList<>())
                    .add(service);
        }

        public double calculateTotalServiceCost(String reservationId) {
            List<Service> services = servicesByReservation.get(reservationId);

            if (services == null) return 0;

            double total = 0;
            for (Service s : services) {
                total += s.getCost();
            }
            return total;
        }
    }

    public static void main(String[] args) {

        System.out.println("Add-On Service Selection");

        AddOnServiceManager manager = new AddOnServiceManager();

        String reservationId = "Single-1";

        manager.addService(reservationId, new Service("Spa", 1500));

        System.out.println("Reservation ID: " + reservationId);

        double totalCost = manager.calculateTotalServiceCost(reservationId);

        System.out.println("Total Add-On Cost: " + totalCost);
    }
}