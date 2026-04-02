public class BookMyStayApp {
    static abstract class Room{
        protected int numberofbeds;
        protected int squareFeet;
        protected double pricepernight;

        public Room(int numberofbeds,int squareFeet,double pricepernight){
            this.numberofbeds=numberofbeds;
            this.squareFeet=squareFeet;
            this.pricepernight=pricepernight;
        }
        public void displayroomdetails(){
            System.out.println("Beds:"+numberofbeds);
            System.out.println("Size:"+squareFeet+"sqft");
            System.out.println("Price:"+pricepernight);
        }

    }
    static class singleroom extends Room{
        public singleroom(){
            super(1,250,1500);
        }
    }
    static class doubleroom extends Room{
        public doubleroom(){
            super(2,400,2500);
        }
    }
    static class suiteroom extends Room{
        public suiteroom(){
            super(3,750,5000);
        }
    }
    public static void main(String[] args){
        System.out.println("Hotel Room Initialization");

        int Singleroomavailability=5;
        int Doubleroomavailability=3;
        int Suiteroomavailability=2;

        Room single=new singleroom();
        Room doubleRoom=new doubleroom();
        Room suite=new suiteroom();

        System.out.println("Single room:");
        single.displayroomdetails();
        System.out.println("Availability:"+Singleroomavailability);

        System.out.println("\n");

        System.out.println("Double room:");
        doubleRoom.displayroomdetails();
        System.out.println("Availability:"+Doubleroomavailability);
        System.out.println("\n");

        System.out.println("Suite room:");
        suite.displayroomdetails();
        System.out.println("Availability:"+Suiteroomavailability);

    }
}
