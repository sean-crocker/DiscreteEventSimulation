/**
 * Class:			Node
 * Author:			Sean Crocker
 * Student Number:	3307768
 */
public class Node<T> {
	private Node<T> next;		//The next node in the queue
	private T data;				//The data stored in the node.
	
	/**
	 * Default constructor to initialise all variables.
	 */
	public Node() {
		setNext(null);
		setData(null);
	}

	/**
	 * Method getNext
	 * Used to return the next node.
	 * Precondition:	The next node must have been set.
	 * Postcondition:	The next node is returned.
	 * Return:			@return the next node
	 */
	public Node<T> getNext() {
		return next;
	}

	/**
	 * Method setNext
	 * Used to set the next node.
	 * Postcondition:	Sets the next node.
	 * @param the next to set
	 */
	public void setNext(Node<T> next) {
		this.next = next;
	}

	/**
	 * Method getData
	 * Used to get the data in the node.
	 * Precondition:	The data must have been set.
	 * Postcondition:	Returns the data in the node.
	 * Return:			@return the data
	 */
	public T getData() {
		return data;
	}

	/**
	 * Method setData
	 * Used to set the data in the node.
	 * Postcondition:	Sets the data for the node.
	 * @param the data to set
	 */
	public void setData(T data) {
		this.data = data;
	}
}