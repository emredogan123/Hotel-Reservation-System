public class Main {
	public static void main(String[] args) {
        HotelReservationSystem hotelReservationSystem = new HotelReservationSystem();
        
        // Display initial room situation
        hotelReservationSystem.displayInitialRoomSituation();
        
        // Process reservations from a file (mock the file read with some reservations)
        hotelReservationSystem.processReservations("src//reservations.txt");
        
        // Display reservation situation after reading
        hotelReservationSystem.displayReservationSituationAfterReading();
        
        // Display room situation after processing reservations
        hotelReservationSystem.displayRoomSituationAfterProcessingReservations();
        
        // Make odd numbered rooms available
        hotelReservationSystem.makeOddRoomsAvailable();
        
        // Display room situation after making odd rooms available
        hotelReservationSystem.displayRoomSituationAfterMakingOddRoomsAvailable();
        
        // Process waiting reservations
        hotelReservationSystem.processWaitingReservations();
        
        // Display final room situation after processing waiting reservations
        hotelReservationSystem.displayFinalRoomSituationAfterProcessingWaitingReservations();
        
    }

}
