import java.util.Random;

/**
 * Interface:			StageInterface
 * Author:				Sean Crocker
 * Student Number:		3307768
 */
public interface StageInterface<T> {

	public void setProductionTime(int mean, int range, Random random);	//Sets the production time
	
	public double getProductionTime();									//Returns the production time
	
	public void setID(int value);										//Sets the ID
	
	public int getID();													//Returns the ID
			
	public void setName(String name);									//Sets the name
	
	public String getName();											//Returns the name
	
	public void setData(T value);										//Sets the data
	
	public T getData();													//Returns the data
	
	public String toString();											//Converts Stage to string and returns results
}