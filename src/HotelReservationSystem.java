import java.util.*;

public class HotelReservationSystem {
    private Stack<Room> singleRoomStack;
    private Stack<Room> doubleRoomStack;
    private Stack<Room> suiteRoomStack;
    private Stack<Room> deluxeRoomStack;

    private Queue<Reservation> singleRoomReservations;
    private Queue<Reservation> doubleRoomReservations;
    private Queue<Reservation> suiteRoomReservations;
    private Queue<Reservation> deluxeRoomReservations;

    private List<Room> unavailableRooms;
    private List<Room> availableRooms;

    public HotelReservationSystem() {
        singleRoomStack = new Stack<>();
        doubleRoomStack = new Stack<>();
        suiteRoomStack = new Stack<>();
        deluxeRoomStack = new Stack<>();

        singleRoomReservations = new LinkedList<>();
        doubleRoomReservations = new LinkedList<>();
        suiteRoomReservations = new LinkedList<>();
        deluxeRoomReservations = new LinkedList<>();

        unavailableRooms = new ArrayList<>();
        availableRooms = new ArrayList<>();

        initializeRooms();
    }

    private void initializeRooms() {
        for (int i = 5; i >= 1; i--) {
            singleRoomStack.push(new Room(i, "Single", true));
        }
        for (int i = 10; i >= 6; i--) {
            doubleRoomStack.push(new Room(i, "Double", true));
        }
        for (int i = 15; i >= 11; i--) {
            suiteRoomStack.push(new Room(i, "Suite", true));
        }
        for (int i = 20; i >= 16; i--) {
            deluxeRoomStack.push(new Room(i, "Deluxe", true));
        }
        updateAvailableRooms();
    }

    void updateAvailableRooms() {
        availableRooms.clear();
        addAvailableRooms(singleRoomStack);
        addAvailableRooms(doubleRoomStack);
        addAvailableRooms(suiteRoomStack);
        addAvailableRooms(deluxeRoomStack);
    }

    private void addAvailableRooms(Stack<Room> roomStack) {
        for (Room room : roomStack) {
            if (room.isAvailable()) {
                availableRooms.add(room);
            }
        }
    }

    void updateUnavailableRooms() {
        unavailableRooms.clear();
        addUnavailableRooms(singleRoomStack);
        addUnavailableRooms(doubleRoomStack);
        addUnavailableRooms(suiteRoomStack);
        addUnavailableRooms(deluxeRoomStack);
    }

    private void addUnavailableRooms(Stack<Room> roomStack) {
        for (Room room : roomStack) {
            if (!room.isAvailable()) {
                unavailableRooms.add(room);
            }
        }
    }

    public void processReservations(String filePath) {
        Queue<Reservation> reservations = FileIO.readReservations(filePath);

        while (!reservations.isEmpty()) {
            Reservation reservation = reservations.poll();
            String roomType = reservation.getRoomType().toLowerCase();
            processSingleReservation(roomType, reservation);
        }

        processWaitingReservations();
        updateAvailableRooms();
        //updateUnavailableRooms();
    }
    
    private void processSingleReservation(String roomType, Reservation reservation) {
        Stack<Room> roomStack = getRoomStack(roomType);
        Queue<Reservation> roomQueue = getRoomQueue(roomType);

        if (!roomStack.isEmpty() && roomStack.peek().isAvailable()) {
            Room room = roomStack.pop();
            room.setAvailable(false);
            unavailableRooms.add(room);
            availableRooms.remove(room);
        } else {
            roomQueue.add(reservation);
        }
    }

    private Stack<Room> getRoomStack(String roomType) {
        switch (roomType) {
            case "single":
                return singleRoomStack;
            case "double":
                return doubleRoomStack;
            case "suite":
                return suiteRoomStack;
            case "deluxe":
                return deluxeRoomStack;
            default:
                throw new IllegalArgumentException("Invalid room type: " + roomType);
        }
    }

    private Queue<Reservation> getRoomQueue(String roomType) {
        switch (roomType) {
            case "single":
                return singleRoomReservations;
            case "double":
                return doubleRoomReservations;
            case "suite":
                return suiteRoomReservations;
            case "deluxe":
                return deluxeRoomReservations;
            default:
                throw new IllegalArgumentException("Invalid room type: " + roomType);
        }
    }

    void makeOddRoomsAvailable() {
        for (Room room : unavailableRooms) {
            if (room.getRoomNumber() % 2 != 0) {
                room.setAvailable(true);
                availableRooms.add(room);
            }
        }
        unavailableRooms.removeIf(room -> room.getRoomNumber() % 2 != 0);
    }

    public void processWaitingReservations() {
        processRoomQueue(singleRoomReservations, singleRoomStack);
        processRoomQueue(doubleRoomReservations, doubleRoomStack);
        processRoomQueue(suiteRoomReservations, suiteRoomStack);
        processRoomQueue(deluxeRoomReservations, deluxeRoomStack);
    }

    private void processRoomQueue(Queue<Reservation> roomQueue, Stack<Room> roomStack) {
        for (Reservation reservation : roomQueue) {
            if (!roomStack.isEmpty() && roomStack.peek().isAvailable()) {
                Room room = roomStack.pop();
                room.setAvailable(false);
                unavailableRooms.add(room);
                roomQueue.poll();
            } else {
                break;
            }
        }
    }


    public void displayInitialRoomSituation() {
        System.out.println("Initial Room Situation:");
        displayAvailableRooms();
        System.out.println();
    }

    public void displayReservationSituationAfterReading() {
        System.out.println("Reservation Situation After Reading Reservations.txt:");
        displayRoomQueues();
        System.out.println();
    }

    public void displayRoomSituationAfterProcessingReservations() {
        System.out.println("Room Situation After Processing Reservations:");
        displayAvailableRooms();
        displayUnavailableRooms();
        System.out.println();
    }

    public void displayRoomSituationAfterMakingOddRoomsAvailable() {
        System.out.println("Room Situation After Odd Rooms Available:");
        displayAvailableRooms();
        displayUnavailableRooms();
        System.out.println();
    }

    public void displayFinalRoomSituationAfterProcessingWaitingReservations() {
        System.out.println("Room Situation After Processing Waiting Reservations:");
        displayAvailableRooms();
        displayUnavailableRooms();
        System.out.println();
    }

    public void displayAvailableRooms() {
        System.out.println("Available Rooms:");
        for (Room room : availableRooms) {
            System.out.println(room);
        }
    }

    public void displayUnavailableRooms() {
        System.out.println("Unavailable Rooms:");
        for (Room room : unavailableRooms) {
            System.out.println(room);
        }
    }

    public void displayRoomStacks() {
        System.out.println("Single Rooms Stack: " + singleRoomStack);
        System.out.println("Double Rooms Stack: " + doubleRoomStack);
        System.out.println("Suite Rooms Stack: " + suiteRoomStack);
        System.out.println("Deluxe Rooms Stack: " + deluxeRoomStack);
    }

    public void displayRoomQueues() {
        System.out.println("Single Room Reservations Queue: " + singleRoomReservations);
        System.out.println("Double Room Reservations Queue: " + doubleRoomReservations);
        System.out.println("Suite Room Reservations Queue: " + suiteRoomReservations);
        System.out.println("Deluxe Room Reservations Queue: " + deluxeRoomReservations);
    }
}