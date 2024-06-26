public class Reservation {
    private int reservationID;
    private String customerName;
    private String roomType;

    public Reservation(int reservationID, String roomType, String customerName) {
        this.reservationID = reservationID;
        this.roomType = roomType;
        this.customerName = customerName;
    }

    public int getReservationID() {
        return reservationID;
    }

    public String getRoomType() {
        return roomType;
    }

    public String getCustomerName() {
        return customerName;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "reservationID=" + reservationID +
                ", customerName='" + customerName + '\'' +
                ", roomType='" + roomType + '\'' +
                '}';
    }
}
