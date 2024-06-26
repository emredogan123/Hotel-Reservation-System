import java.util.NoSuchElementException;

public class ArrayQueue<T> implements QueueInterface<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] queueArray;
    private int front;
    private int rear;
    private int size;

    // Constructor to initialize the queue with default capacity
    public ArrayQueue() {
        this(DEFAULT_CAPACITY);
    }

    // Constructor to initialize the queue with a specified capacity
    public ArrayQueue(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be positive.");
        }
        this.queueArray = (T[]) new Object[capacity];
        this.front = 0;
        this.rear = -1;
        this.size = 0;
    }

    // Method to add an element to the queue
    @Override
    public void enqueue(T newEntry) {
        if (size == queueArray.length) {
            expandCapacity();
        }
        rear = (rear + 1) % queueArray.length;
        queueArray[rear] = newEntry;
        size++;
    }

    // Method to remove an element from the queue
    @Override
    public T dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty. Cannot dequeue.");
        }
        T removedItem = queueArray[front];
        queueArray[front] = null; // Help with garbage collection
        front = (front + 1) % queueArray.length;
        size--;
        return removedItem;
    }

    // Method to get the front element of the queue
    @Override
    public T getFront() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty. No front element.");
        }
        return queueArray[front];
    }

    // Method to check if the queue is empty
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    // Method to clear the queue
    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            queueArray[(front + i) % queueArray.length] = null;
        }
        front = 0;
        rear = -1;
        size = 0;
    }

    // Helper method to expand the capacity of the queue
    private void expandCapacity() {
        @SuppressWarnings("unchecked")
		T[] newQueue = (T[]) new Object[queueArray.length * 2];
        for (int i = 0; i < size; i++) {
            newQueue[i] = queueArray[(front + i) % queueArray.length];
        }
        queueArray = newQueue;
        front = 0;
        rear = size - 1;
    }
}
