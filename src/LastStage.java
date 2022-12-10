/**
 * Class:			LastStage
 * Author:			Sean Crocker
 * Student Number:	3307768
 */
public class LastStage<T extends Item> extends Stage<T> {
	private Storage<T> previous;	//The inter-stage storage queue before this stage.
	
	/**
	 * Default constructor to initialise all variables.
	 */
	public LastStage() {
		super();
		previous = null;
	}
	
	/**
	 * Constructor with parameters to intialise all variables with the values given.
	 * @param ID the identification number of the stage
	 * @param name the name of the stage
	 * @param prevStore the storage queue before this stage
	 */
	public LastStage(int ID, String name, Storage<T> prevStore) {
		super(ID, name);
		this.previous = prevStore;
	}
	
	/**
	 * Method produce
	 * Used to add an item to the final stage of production and return the production time.
	 * Precondition:	An item must not already be in this stage.
	 * Postcondition:	Prints an error if the stage is blocked or if an item is in it. Else,
	 * 					removes an item from the storage before it, adds it to the stage and calls
	 * 					the method resume to remove it.
	 * Return:			@return productionTime the time taken at this stage.
	 * @param currentTime the current time of the simulation.
	 */
	@Override
	public double produce(double currentTime) {
		if (isStarved()) {								//If Starved, increment total starved time
			starveTime += getProductionTime();			//and set the production time to zero.
			return 0;
		}
		if (getData() != null) {								//If an item already exists in stage,									//Set production time to zero, display an error message.
			System.err.println("Error, item currently in final stage.");
			return 0;
		}
		T item = previous.dequeue(currentTime);			//Get Item from queue
		setData(item);									//Set the current item in the stage.
		if (isBlocked()) {								//If Blocked, increment total blocked time and throw error.
			blockedTime += getProductionTime();
			System.err.println("Error, final stage is blocked.");
		}
		else											//If not blocked, remove item from stage.
			resume(currentTime += getProductionTime());
		return getProductionTime();
	}
	
	/**
	 * Method isStarved
	 * Used to determine if the stage is starved by seeing if the previous storage queue is empty.
	 * Precondition:	The queue collection must have been created and set.
	 * Postcondition:	Returns true if the storage is empty.
	 * Return:			@return true if the storage is empty, else false.
	 */
	@Override
	public boolean isStarved() {
		return previous.isEmpty();
	}

	/**
	 * Method isBlocked
	 * Used to determine if the stage is blocked by seeing if the successor storage queue is full.
	 * Postcondition:	Returns false due to being final stage.
	 * Return:			@return false final stage.
	 */
	@Override
	public boolean isBlocked() {
		return false;
	}
	
	/**
	 * Method resume
	 * Used to resume the final part of the stage by removing the item from the stage.
	 * Precondition:	The method, produce, called the procedure.
	 * Postcondition:	The item is removed from the stage.
	 * @param time the time taken to produce the item in this stage.
	 */
	@Override
	public void resume(double time) {
		if (getData() != null && !isBlocked())
			setData(null);
	}
}