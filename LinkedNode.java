/**
 * 
 * @author Adam Lewandowski
 *
 * @param <T> - a generic
 * 
 * Class that creates nodes of book objects to be linked
 * by the LinkedBookshelf class
 */
public class LinkedNode<T> {
  
  private T data;
  private LinkedNode<T> next;
  
  /**
   * 
   * @param data - the data object being used to create
   * this node
   * 
   * Sets the data of this node to given data and data
   * of next node to null
   */
  public LinkedNode(T data) {
    this.data = data;
    this.next = null;
  }

  /**
   * 
   * @param data - data of this node
   * @param next - data of the next node
   * 
   * Constructs a node with data and sets the next node
   * to that of the next parameter
   */
  public LinkedNode(T data, LinkedNode<T> next) {
    this.data = data;
    this.next = next;
  }
  
  /**
   * 
   * @return - The value held in next
   */
  public LinkedNode<T> getNext() {
    return this.next;
  }
  
  /**
   * 
   * @return - The value held in data
   */
  public T getData() {
    return this.data;
  }
  
  /**
   * 
   * @return - The value held in data in the
   * form of a String
   */
  public String toString() {
    return data.toString();
  }
  
  /**
   * 
   * @param next - the value of the node next
   * 
   * Sets the next of this node to parameter next
   */
  public void setNext(LinkedNode<T> next) {
    this.next = next;
  }
}
