/**
 * Class:			StandardStage
 * Author:			Sean Crocker
 * Student Number:	3307768
 */
public class StandardStage<T extends Item> extends Stage<T> {
	private Storage<T> previous;	//The previous inter-storage queue
	private Storage<T> next;		//The successor inter-storage queue
	
	/**
	 * Default constructor to initialise all variables.
	 */
	public StandardStage() {
		super();
		previous = null;
		next = null;
	}
	
	/**
	 * Constructor with parameters to initialise all variables with the given values.
	 * @param ID the identification number of the stage
	 * @param name the name of the stage
	 * @param prevStorage the previous inter-storage queue
	 * @param nextStorage the next inter-storage queue
	 */
	public StandardStage(int ID, String name, Storage<T> prevStorage, Storage<T> nextStorage) {
		super(ID, name);
		this.previous = prevStorage;
		this.next = nextStorage;
	}
	
	/**
	 * Method produce
	 * Used to get an item from the previous queue and enqueue it into the next with the stage
	 * is not starved or blocked. Returns the production time of this stage.
	 * Postcondition:	Returns zero if an item exists in the stage or the stage is starved. Else, returns
	 * 					the production time. Adds to the total starved or blocked time if necessary.
	 * Return:			@return zero if starved or item exists, else the production time.
	 * @param currentTime the current time of the simulation
	 */
	@Override
	public double produce(double currentTime) {
		if (getData() != null)	{								//If stage already contains data, enqueue it
			resume(currentTime);								//and remove it
			return 0;
		}
		else if (isStarved()) {									//If Starved, increment total starveTime
			starveTime += getProductionTime();
			return 0;
		}
		else {
			if (isBlocked())									//If Blocked, increment total blocked time.
				blockedTime += getProductionTime();
			else {												//If not blocked,
				setData(previous.dequeue(currentTime));			//Get Item from queue								//Set the current item in the stage.
				currentTime += getProductionTime();				//Add production time to current time,
				next.enqueue(getData(), currentTime);			//Place item in next queue,
				setData(null);									//Remove item from stage.
			}
			return getProductionTime();
		}
	}

	/**
	 * Method isStarved
	 * Used to determine if the stage is starved by seeing if the previous storage is empty.
	 * Precondition:	The storage collections must have been created.
	 * Postcondition:	Returns true if the previous storage is empty. Else, returns false.
	 * Return:			@return true if previous storage is empty
	 */
	@Override
	public boolean isStarved() {
		return previous.isEmpty();
	}

	/**
	 * Method isBlocked
	 * Used to determine if the stage is blocked by seeing if he next storage is full.
	 * Precondition:	The storage collections must have been created.
	 * Postcondition:	Returns true if the next storage is full. Else, returns false.
	 * Return:			@return true if next storage is full
	 */
	@Override
	public boolean isBlocked() {
		return next.isFull() && !previous.isEmpty();
	}

	/**
	 * Method resume
	 * Used to resume the final parts of the production at this stage.
	 * Precondition:	The method produce must call this procedure.
	 * Postcondition:	Enqueue the item into the next storage and removes the item from the stage.
	 * @param time taken to finish this stage.
	 */
	@Override
	public void resume(double time) {
		if (getData() != null && !isBlocked()) {
			next.enqueue(getData(), time);
			setData(null);
		}
	}
}