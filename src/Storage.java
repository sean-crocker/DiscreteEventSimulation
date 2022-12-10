import java.util.ArrayList;
import java.util.List;

/**
 * Class:			Storage
 * Author:			Sean Crocker
 * Student Number:	3307768
 */
public class Storage<T extends Item> implements Queue<T>{
	private int ID;			//The unique identification number of the storage
	private String name;	//The name of the storage
	private int maxSize;	//The length of the queue
	private Node<T> front;	//The node at the front of the queue
	private Node<T> rear;	//The node at the rear of the queue
	private int count;		//The amount of items in the queue
	private int numOfItems;	//The total amount of items that pass through the queue
	private List<T> times;	//The list of items that pass through the queue
		
	/**
	 * Constructor with parameters to set all the variables with the values given
	 * @param ID the identification number of the storage
	 * @param name the name of the storage
	 * @param size the length of the queue
	 */
	public Storage(int ID, String name, int size) {
		maxSize = size + 1;
		front = null;
		rear = null;
		count = 0;
		numOfItems = 0;
		times = new ArrayList<T>();
		this.ID = ID;
		this.setName(name);
	}
	
	/**
	 * Method enqueue
	 * Used to add an item to the rear of the queue.
	 * Precondition:	The queue must not be full.
	 * Postcondition:	The item is added and set as the new rear of the queue.
	 * @param value the data being entered into the queue
	 * @param time the time the data entered the queue
	 */
	public void enqueue(T value, double time) {
		if(isFull())
			System.err.println("Cannot insert into full queue.");
		else {
			value.addEnterTime(ID, time);
			Node<T> temp = rear;
			rear = new Node<T>();
			rear.setData(value);
			rear.setNext(null);
			if (isEmpty()) {
				front = rear;
			}
			else {
				temp.setNext(rear);
			}
			numOfItems++;
			count++;
		}
	}
	
	/**
	 * Method dequeue
	 * Used to remove an item from the front of the queue and return it.
	 * Precondition:	The queue must not be empty.
	 * Postcondition:	The front of the queue is equal to its next node and returns 
	 * 					the value while decrementing the count.
	 * @param time sets the time the item leaves the queue
	 */
	public T dequeue(double time) {
		if (isEmpty()) {
			System.err.println("Cannot remove from empty queue.");
			return null;
		}
		else {
			T value = front.getData();
			value.addLeaveTime(ID, time);
			times.add(value);
			front = front.getNext();
			count--;
			if (isEmpty())
				rear = null;
			return value;
		}
	}
	
	/**
	 * Method getAverageTime
	 * Used to get the average time of all the items being idle in the queue.
	 * Precondition:	Items must have been enqueued and dequeued and the simulation
	 * 					must have ended.
	 * Postcondition:	Returns the average time all items have been sitting in this queue.
	 * Return:			@return the average time items have been idling in the queue
	 */
	public double getAverageTime() {
		double result = 0;
		for (int i = 0; i < times.size(); i++) {
			double time = times.get(i).getLeaveTime(ID) - times.get(i).getEnterTime(ID);
			result += time;
		}
		return result / times.size();
	}
	
	/**
	 * Method getAverageItems (NOT YET IMPLEMENTED)
	 * Used to get the average amount of items that have been in this queue.
	 * Precondition:	Items must have been enqueued and dequeued and the simulation
	 * 					must have ended.
	 * Postcondition:	Returns the average amount of items that have been sitting in the queue.
	 * Return:			@return the average amount of items
	 */
	public double getAverageItems() {
		return 0;
	}
	
	/**
	 * Method peek
	 * Used the return the value at the front of the queue.
	 * Precondition:	The queue must not be empty.
	 * Postcondition:	Returns the data at the front of the queue.
	 * Return:			@return front of queue.
	 */
	public T peek() {
		return front.getData();
	}
	
	/**
	 * Method isEmpty
	 * Used to determine if the queue is empty.
	 * Postcondition:	Returns true is the amount of items in the queue is equal to zero.
	 * 					Else, returns false.
	 * Return:			@return true if count equals zero
	 */
	public boolean isEmpty() {
		return count == 0;
	}
	
	/**
	 * Method isFull
	 * Used to determine if the queue is full.
	 * Postcondition:	Returns true if the amount of items in the queue is equal to
	 * 					the size of the queue. Else, returns false
	 * Return:			@return true if count equals maxSize
	 */
	public boolean isFull() {
		return count == maxSize;
	}

	/**
	 * Method getID
	 * Used the get the ID of the storage.
	 * Precondition:	the ID of the storage must be set
	 * Postcondition:	Returns the ID of the storage
	 * Return:			@return the ID
	 */
	public int getID() {
		return ID;
	}

	/**
	 * Method setID
	 * Used to set the ID of the storage.
	 * Postcondition:	The ID is set.
	 * @param the ID to set
	 */
	public void setID(int ID) {
		this.ID = ID;
	}
	
	/**
	 * Method getName
	 * Used to return the name of the storage.
	 * Precondition:	The name must have been set.
	 * Postcondition:	Returns the name of the storage.
	 * Return:			@return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Method setName
	 * Used to set the name of the storage.
	 * Postcondition:	Sets the name of the storage.
	 * @param the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Method toString
	 * Used to convert the collection to a string and return the result.
	 * Precondition:	Items must have been enqueued and dequeued and the
	 * 					simulation must have ended.
	 * Postcondition:	Returns the ID, average time, and average amount of items in the queue as a string.
	 * Return:			@return returns to collection as a string.
	 */
	public String toString() {
		String time = String.format("%5.2f", getAverageTime());
		String item = String.format("%5.2f", getAverageItems());
		return getID()+ "\t\t"+ time+ "\t\t"+ item;
	}
}