/**
 * Interface:			Queue
 * Author:				Sean Crocker
 * Student Number:		3307768
 */
public interface Queue<T> {

	public void setID(int value);				//Sets the ID of the queue
	
	public int getID();							//Returns the ID of the queue
	
	public void setName(String name);			//Sets the name of the queue
	
	public String getName();					//Returns the name of the queue
	
	public void enqueue(T value, double time);	//Adds data to the queue and sets the time entered
	
	public T dequeue(double time);				//Removes data from the queue, returns it, and sets the time left
	
	public boolean isEmpty();					//Returns true if size equals zero
	
	public T peek();							//Returns the data at the front of the queue
	
	public boolean isFull();					//Returns true if size equals the length
	
	public String toString();					//Converts the collection to a string and returns it
	
	public double getAverageTime();				//Returns the average time an item is in the queue
	
	public double getAverageItems();			//Returns the average amount of items in the queue
}
