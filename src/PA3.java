/**
 * Class:			PA3
 * Author:			Sean Crocker
 * Student Number:	3307768
 *
 */
public class PA3 {

	/**
	 * Method main
	 * Main method that runs when the program begins.
	 * Postcondition:	Runs the method readArgs to read the arguments.
	 * @param args the arguments entered in the command window.
	 */
	public static void main(String[] args) {
		PA3 main = new PA3();
		main.readArgs(args);
	}

	/**
	 * Method readArgs
	 * Used to read the arguments entered to gather the mean, range and the maximum length of the queues.
	 * Precondition:	User must have entered arguments when running the program.
	 * Postcondition:	Creates a new simulation object, starts the simulation and prints the results.
	 * @param args arguments entered by the user defining the mean, range and queue length.
	 */
	private void readArgs(String[] args) {
		if (args[0] != null && args[1] != null && args[2] != null) {
			int mean = Integer.parseInt(args[0]);
			int range = Integer.parseInt(args[1]);
			int queueMax = Integer.parseInt(args[2]);
			Simulator sim = new Simulator(mean, range, queueMax);
			sim.start();
			System.out.println(sim.toString());
		}
		else
			System.err.println("Error, arguments were not found. Please specify the mean, range, and the queue max.");
	}
}