import java.util.LinkedList;
import java.util.Queue;

public class reservationsQueue<T> {
    private Queue<T> queue;

    public reservationsQueue() {
        queue = new LinkedList<>();
    }

    public void enqueue(T item) {
        queue.add(item);
    }

    public T dequeue() {
        return queue.poll();
    }

    public T peek() {
        return queue.peek();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    public int size() {
        return queue.size();
    }
    reservationsQueue<Reservation> singleReservations = new reservationsQueue<>();
    reservationsQueue<Reservation> doubleReservations = new reservationsQueue<>();
    reservationsQueue<Reservation> suiteReservations = new reservationsQueue<>();
    reservationsQueue<Reservation> deluxeReservations = new reservationsQueue<>();
}