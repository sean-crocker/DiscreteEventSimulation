/**
 * Class:			Item
 * Author:			Sean Crocker
 * Student Number:	3307768
 */
public class Item {
	private double[] enterTime;		//An array of the times entered each queue.
	private double[] leaveTime;		//An array of the times gathered when leaving each queue.
	private double productionTime;	//The total time to produce this item.
	
	/**
	 * Default constructor to initialise all variables.
	 */
	public Item() {
		enterTime = new double[6];
		leaveTime = new double[6];
		setProductionTime(0);
	}
	
	/**
	 * Constructor with parameters to initialise all variables with values given.
	 * @param enter the times the item entered each queue.
	 * @param leave the times the item left each queue.
	 * @param finalTime the final production time of the item.
	 */
	public Item(double[] enter, double[] leave, double finalTime) {
		this.setEnterTime(enter);
		this.setLeaveTime(leave);
		this.setProductionTime(finalTime);
	}

	/**
	 * Method getEnterTime
	 * Used to get the time the item entered a particular queue.
	 * Precondition:	The queues must have been created.
	 * Postcondition:	Returns the time the item entered the particular queue.
	 * @param pos the queue the item entered.
	 * @return the time the item entered the queue.
	 */
	public double getEnterTime(int pos) {
		return enterTime[pos];
	}

	/**
	 * Method setEnterTime
	 * Used to set the times that the item entered each queue.
	 * Precondition:	All queues must have been created.
	 * Postcondition:	All enter times are set.
	 * @param enterTime the array of times to set for entering queues.
	 */
	public void setEnterTime(double[] enterTime) {
		this.enterTime = enterTime;
	}

	/**
	 * Method getLeaveTime
	 * Used to get the time the item leaves a particular queue.
	 * Precondition:	The queues must have been created.
	 * Postcondition:	Returns the time the item left a queue.
	 * @param pos the queue the item left.
	 * @return the time the item left the queue.
	 */
	public double getLeaveTime(int pos) {
		return leaveTime[pos];
	}

	/**
	 * Method setLeaveTime
	 * Used to set the times that the item left each queue.
	 * Precondition:	All queues must have been created.
	 * Postcondition:	All leave times are set.
	 * @param leaveTime the array of times to set for leaving queues.
	 */
	public void setLeaveTime(double[] leaveTime) {
		this.leaveTime = leaveTime;
	}

	/**
	 * Method getProductionTime
	 * Used to get the time taken to produce this item.
	 * Precondition:	The item must have finished it's production.
	 * Postcondition:	The time taken to produce this item is returned.
	 * @return the production time.
	 */
	public double getProductionTime() {
		return productionTime;
	}

	/**
	 * Method setProductionTime
	 * Used to set the time taken to produce this item.
	 * Precondition:	The item must have finished it's production.
	 * Postcondition:	The time taken to produce this item is set.
	 * @param productionTime the production time to set.
	 */
	public void setProductionTime(double productionTime) {
		this.productionTime = productionTime;
	}
	
	/**
	 * Method addEnterTime
	 * Used to add a time that this item entered a particular queue.
	 * Precondition:	The queue must exist and the time must be given.
	 * Postcondition:	The time taken to enter a queue is set.
	 * @param pos the queue which the item entered.
	 * @param time the time which the item entered.
	 */
	public void addEnterTime(int pos, double time) {
		enterTime[pos] = time;
	}
	
	/**
	 * Method addLeaveTime
	 * Used to add a time that this item left a particular queue.
	 * Precondition:	The queue must exist and the time must be given.
	 * Postcondition:	The time taken to leave a queue is set.
	 * @param pos the queue which the item left.
	 * @param time the time which the item left.
	 */
	public void addLeaveTime(int pos, double time) {
		leaveTime[pos] = time;
	}
}