import java.util.Random;

/**
 * Class:			Stage
 * Author:			Sean Crocker
 * Student Number:	3307768
 */
public abstract class Stage<T> implements StageInterface<T> {
	protected double starveTime;		//The total amount of time spent starved
	protected double blockedTime;		//The total amount of time spent blocked
	private double productionTime;		//The time taken to finish this stage of production
	private T data;						//The data currently in the stage
	private int ID;						//The unique identification number of the stage 
	private String name;				//The name of the stage
	
	/**
	 * Default constructor to initialise all variables.
	 */
	public Stage() {
		starveTime = 0;
		blockedTime = 0;
		setID(0);
	}
	
	/**
	 * Constructor with variables to initialise all variables with the values given.
	 * @param ID the identification number of the stage
	 * @param name the name of the stage
	 */
	public Stage(int ID, String name) {
		this.starveTime = 0;
		this.blockedTime = 0;
		this.ID = ID;
		this.name = name;
		this.productionTime = 0;
	}
	
	/**
	 * Method produce
	 * Abstract method used to produce an item at this stage.
	 * Postcondition:	The current time of the simulation is incremented if the stage is not starved.
	 * @param currentTime the current time of the simulation
	 * Return:			@return production time if the stage is not starved, else zero.
	 */
	public abstract double produce(double currentTime);
	
	/**
	 * Method isStarved
	 * Abstract method used to determine if the stage is starved.
	 * Postcondition:	Returns true if previous queue is empty. Else if it's the beginning stage or queue
	 * 					is not empty, returns false.
	 * Return:			@return true if previous queue is empty.
	 */
	public abstract boolean isStarved();
	
	/**
	 * Method isBlocked
	 * Abstract Method used to determine if the stage is blocked.
	 * Postcondition:	Returns true if the next queue is full. Else if it's the final stage or the queue
	 * 					is not full, returns false.
	 * Return:			@return true if next queue is full.
	 */
	public abstract boolean isBlocked();
	
	/**
	 * Method resume
	 * Abstract method to end the final parts of the stage of production.
	 * Postcondition:	Removes the item from the stage and enqueues it to the next queue if possible.
	 * @param time the time the item enters the next queue
	 */
	public abstract void resume(double time);
	
	/**
	 * Method getProductionTime
	 * Used to return the time it takes at this stage.
	 * Precondition:	The production time must have been set.
	 * Postcondition:	Returns the production time.
	 * Return:			@return the production time
	 */
	public double getProductionTime() {
		return productionTime;
	}
	
	/**
	 * Method setProductionTime
	 * Used to set the time taken at this stage with the random number generator, the mean and the range.
	 * Precondition:	The mean and range must be given by the user. A simulator must have been created.
	 * Postcondition:	Sets the production time at this stage.
	 * @param m the average of all the stages' production time
	 * @param n the range of all the stages' production time
	 * @param random the random number generator used the produce the time taken
	 */
	public void setProductionTime(int m, int n, Random random) {
		this.productionTime = m + n * (random.nextDouble() - 0.5);
	}
		
	/**
	 * Method getID
	 * Used to return the identification number of the stage.
	 * Precondition:	The ID must have been set.
	 * Postcondition:	Returns the ID number.
	 * Return:			@return the ID
	 */
	public int getID() {
		return ID;
	}

	/**
	 * Method setID
	 * Used to set the identification number of the stage.
	 * Postcondition:	Sets the ID number.
	 * @param ID the ID number to set
	 */
	public void setID(int ID) {
		this.ID = ID;
	}
	
	/**
	 * Method toString
	 * Used to convert the Stage object into a string and return the result.
	 * Precondition:	The simulation must have finished to gather results.
	 * Postcondition:	Returns the results in the form of a string.
	 * Return:			@return the ID, total starve time, and total blocked time of this stage.
	 */
	public String toString() {
		String starve = String.format("%5.2f", starveTime);
		String block = String.format("%5.2f", blockedTime);
		return  getID() + "\t\t"+ starve +"\t\t"+ block;
	}

	/**
	 * Method setData
	 * Used to set the data stored in the stage.
	 * Postcondition:	Sets the data.
	 * @param value the data to set
	 */
	public void setData(T value) {
		this.data = value;
	}
	
	/**
	 * Method getData
	 * Used to return the data stored in the stage.
	 * Precondition:	The data must have been set.
	 * Postcondition:	Returns the data.
	 * Return:			@return the data stored in the stage
	 */
	public T getData() {
		return data;
	}
	
	/**
	 * Method setName
	 * Used to set the name of the stage.
	 * Postcondition:	Sets the name of the stage.
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Method getName
	 * Used to get the name of the stage.
	 * Precondition:	The name must have been set.
	 * Postcondition:	Returns the name.
	 * Return:			@return the name of the stage
	 */
	public String getName() {
		return name;
	}
}