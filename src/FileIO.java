import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

public class FileIO {
    public static Queue<Reservation> readReservations(String filePath) {
        Queue<Reservation> reservations = new LinkedList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            // Skip the header line
            if ((line = br.readLine()) != null) {
                if (line.contains("Reservation ID")) {
                    line = br.readLine();
                }
            }
            while (line != null) {
                String[] parts = line.split(",");
                int reservationID = Integer.parseInt(parts[0].trim());
                String roomType = parts[2].trim();
                String customerName = parts[1].trim();
                reservations.add(new Reservation(reservationID, roomType, customerName));
                line = br.readLine();
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
        return reservations;
    }
}
