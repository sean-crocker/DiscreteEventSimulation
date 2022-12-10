import java.util.Comparator;

/**
 * Class:			Compare
 * Author:			Sean Crocker
 * Student Number:	3307768
 *
 */
public class Compare<T extends Stage<Item>> implements Comparator<T>  {
	
	/**
	 * Method compare
	 * Used to order the priority queue by determining which item comes before another.
	 * Precondition:	A comparator and priority queue collection must have been created and filled.
	 * Postcondition:	The priority queue is sorted.
	 * @param value1 the first value being compared.
	 * @param value2 the second value being compared.
	 */
	@Override
	public int compare(T value1, T value2) {
		if (value1.getProductionTime() < value2.getProductionTime())
			return -1;
		else if (value1.getProductionTime() > value2.getProductionTime())
			return 1;
		else
			return 0;
	}
}