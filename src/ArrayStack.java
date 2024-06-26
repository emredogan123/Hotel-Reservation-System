
import java.util.Arrays;
import java.util.EmptyStackException;



public class ArrayStack<T> implements StackInterface<T> {

	private static final int DEFAULT_CAPACITY = 25;
	private static final int MAXIMUM_CAPACITY = 10000;

	private int topIndex;
	private T[] stack;
	private boolean initialized = false;

	public ArrayStack() {
		this(DEFAULT_CAPACITY);
	}

	public ArrayStack(int initialCapacity) {
		checkCapacity(initialCapacity);
		@SuppressWarnings("unchecked")
		T[] temp = (T[]) new Object[initialCapacity];
		stack = temp;
		topIndex = -1;
		initialized = true;
	}

	private void checkCapacity(int capacity) {
		if (capacity <= 0 || capacity > MAXIMUM_CAPACITY)
			throw new IllegalStateException("Capacity must be between 1 and " + MAXIMUM_CAPACITY);

	}

	private void checkInitialized() {
		if (!initialized)
			throw new SecurityException("ArrayStack not properly initialized");
	}

	private void resizeArray() {
		if (topIndex == stack.length - 1) {
			int newSize = stack.length * 2;
			checkCapacity(newSize);
			// makes copy of array, but with the new size
			stack = Arrays.copyOf(stack, newSize);
		}
	}

	@Override
	public void push(T newEntry) {
		checkInitialized();
		resizeArray();
		stack[++topIndex] = newEntry;
	}

	@Override
	public T pop() {
		checkInitialized();
		if (topIndex == -1) { // isEmpty()
			throw new EmptyStackException();
		} else {
			T result = stack[topIndex];
			stack[topIndex--] = null;
			System.out.println(result + " popped from stack.");
			return result;
		}
	}

	@Override
	public T peek() {
		checkInitialized();
		if (isEmpty()) {
			throw new EmptyStackException();
		} else {
			System.out.println("Element at the top of stack: " + stack[topIndex]);
			return stack[topIndex];
		}
	}

	@Override
	public boolean isEmpty() {
		return (topIndex == -1);
	}

	@Override
	public void clear() {
		checkInitialized();
		while (!isEmpty()) {
			pop();
		}
	}

}
