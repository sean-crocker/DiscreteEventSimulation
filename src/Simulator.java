import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;

/**
 *	Class:			Simulator
 *	Author:			Sean Crocker
 *	Student Number:	3307768
 * 
 */
public class Simulator {
	private List<Storage<Item>> queues;			//List of Inter-Stage Storages
	private List<Stage<Item>> stages;			//List of Stages
	private PriorityQueue<Stage<Item>> events;	//Priority Queue of Stages
	private double currentTime;					//The current time of the simulation
	private double totalTime;					//The time limit of the simulation
	
	
	/**
	 * Constructor with parameters to initialise the class.
	 * @param mean sets average of all the times.
	 * @param range sets the range of all the times
	 * @param qMax sets the maximum length of the queues for the inter-stage storage
	 */
	public Simulator(int mean, int range, int qMax) {
		Random random = new Random();
		Comparator<Stage<Item>> compare = new Compare<Stage<Item>>();
		queues = new ArrayList<Storage<Item>>();
		stages = new ArrayList<Stage<Item>>();
		events = new PriorityQueue<Stage<Item>>(compare);
		
		Storage<Item> q1 = new Storage<Item>(1, "q1", qMax);
		Storage<Item> q2 = new Storage<Item>(2, "q2", qMax);
		Storage<Item> q3 = new Storage<Item>(3, "q3", qMax);
		Storage<Item> q4 = new Storage<Item>(4, "q4", qMax);
		Storage<Item> q5 = new Storage<Item>(5, "q5", qMax);
		
		queues.add(q1);
		queues.add(q2);
		queues.add(q3);
		queues.add(q4);
		queues.add(q5);
		
		Stage<Item> s0 = new FirstStage<Item>(0,"s0", q1);
		Stage<Item> s1 = new StandardStage<Item>(1,"s1", q1, q2);
		Stage<Item> s2 = new StandardStage<Item>(2,"s2", q2, q3);
		Stage<Item> s3 = new StandardStage<Item>(3,"s3", q3, q4);
		Stage<Item> s4 = new StandardStage<Item>(4,"s4", q4, q5);
		Stage<Item> s5 = new LastStage<Item>(5,"s5", q5);
		
		stages.add(s0);
		stages.add(s1);
		stages.add(s2);
		stages.add(s3);
		stages.add(s4);
		stages.add(s5);
		
		events.addAll(stages);
		
		currentTime = 0;
		totalTime = 10000000;
		
		for (int i = 0; i < 6; i++)
			stages.get(i).setProductionTime(mean, range, random);
	}

	/**
	 * Method start
	 * Used to begin the simulation by running the stage with the lowest time first.
	 * Precondition:	The simulation must exist giving a range, mean and maximum queue size.
	 * Postcondition:	The simulation has reached its time limit and the result can be displayed.
	 */
	public void start() {		
		while (currentTime < totalTime) {
			while (!events.isEmpty()) {
				Stage<Item> stage = events.remove();
				if (stage.getID() == 0) {
					Item item = new Item();
					stage.setData(item);
				}
				unblock(stage.getID());
				unstarve(stage.getID());
			}
			events.addAll(stages);
		}
	}
	
	/**
	 * Method unstarve
	 * Uses recursion to find the stage that is not starved. If it is not starved,
	 * that stage is used.
	 * Precondition:	All stages must be added to the array list.
	 * Postcondition:	Calls this method again if the stage is starved, else runs the procedure in the stage.
	 * @param pos the position of the stage in the array list. 
	 */
	public void unstarve(int pos) {
		if (stages.get(pos).isStarved() && pos > 0)
			unstarve(pos - 1);
		currentTime += stages.get(pos).produce(currentTime);
	}
	
	/**
	 * Method unblock
	 * Uses recursion to find which stage after the given is not blocked. If it is not blocked,
	 * that stage is used.
	 * Precondition:	All stages must be added to the array list.
	 * Postcondition:	Calls this method again if the stage is blocked, else runs the procedure in the stage.
	 * @param pos the position of the stage in the array list.
	 */
	public void unblock(int pos) {
		if (stages.get(pos).isBlocked() && pos < 6) {
			unblock(pos + 1);
		}
		currentTime += stages.get(pos).produce(currentTime);
	}
	
	/**
	 * Method toString
	 * Converts the simulator to a string and returns the results.
	 * Precondition:	The simulation must have started before results are gathered and returned.
	 * Postcondition:	The simulator is converted to a string and returned.
	 */
	public String toString() {
		String result = "";
		result = "Production Stations: \n"
				+"Stage: \t\t"+"Starve(t): \t\t"+"Blocked(t): \n";
		for (int i = 0; i < 6; i++)
			result += stages.get(i).toString() + "\n";
		
		result += "\n"+ "Storage Queues: \n"
				+"Store: \t\t"+"AvgTime(t):\t\t"+"AvgItems\n";
		for (int i = 0; i < 5; i++)
			result += queues.get(i).toString() + "\n";
		return result;
	}
}
