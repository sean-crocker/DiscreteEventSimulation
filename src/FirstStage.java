/**
 * Class:			FirstStage
 * Author:			Sean Crocker
 * Student Number:	3307768
 *
 */
public class FirstStage<T extends Item> extends Stage<T> {
	private Storage<T> next;		//The successor inter-storage queue.
	
	/**
	 * Default constructor that initialises all values for the beginning stage of the simulation.
	 */
	public FirstStage() {
		super();
		next = null;
	}
	
	/**
	 * Constructor with parameters to intialise all values including the ones stated in the parameters.
	 * @param ID the identification number of the storage.
	 * @param name the name of the storage
	 * @param nextStorage the successor inter-storage queue.
	 */
	public FirstStage(int ID, String name, Storage<T> nextStorage) {
		super(ID, name);
		this.next = nextStorage;
	}
	
	/**
	 * Method produce
	 * Checks if the stage is starved or blocked and adds the production time to the total times.
	 * If the stage is not blocked or starved, runs the procedure resume to add the item to the queue
	 * and remove it from the stage.
	 * Precondition:	A new item was created and set.
	 * Postcondition:	Returns the production time so that may be incremented to the current time, removes the
	 * 					item from the stage, and enqueues the item onto the successor queue.
	 * @param currentTime the current time of the simulation.
	 */
	@Override
	public double produce(double currentTime) {		
		if (isStarved()) {										//If Starved, increment total starved time,
			starveTime += getProductionTime();					//set production time to zero, and display an error.
			System.err.println("Error, first stage is starved.");
			return 0;
		}
		if (isBlocked())										//If Blocked, increment total blocked time.
			blockedTime += getProductionTime();
		else {													//If not blocked,
			currentTime += getProductionTime();					//Add production time to current time,
			resume(currentTime);
		}
		return getProductionTime();
	}
	
	/**
	 * Method isStarved
	 * Used to determine if the stage is starved by seeing if the previous inter-stage storage queue is empty.
	 * Postcondition:	Returns false due to being the beginning stage.
	 * Return:			@return false, due to being the beginning stage.	
	 */
	@Override
	public boolean isStarved() {
		return false;
	}
	
	/**
	 * Method isBlocked
	 * Used to determine if the stage is blocked by seeing if the successor inter-stage storage queue is full.
	 * Postcondition:	Returns true if the next storage queue is full.
	 * Return:			@return true if the next storage queue is full, false if it is not.
	 */
	@Override
	public boolean isBlocked() {
		return next.isFull();
	}
	/**
	 * Method resume
	 * Used to resume the stage of production by adding the item to the next queue and removing the item from
	 * the stage if the next queue is not full and there is an item in the stage.
	 * Precondition:	The method, produce, must have been called, adding the current time to the simulation time.
	 * Postcondition:	The item is moved to the next queue and the item is removed from the stage only under
	 * 					the correct conditions.
	 */
	@Override
	public void resume(double time) {
		if (getData() != null && !isBlocked()) {
			next.enqueue(getData(), time);
			setData(null);
		}
	}
}