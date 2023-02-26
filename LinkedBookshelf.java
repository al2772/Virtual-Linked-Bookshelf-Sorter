/**
 * 
 * @author Adam Lewandowski
 *
 * Generates a linked list of book nodes that create
 * a bookshelf object
 */
public class LinkedBookshelf {

  private LinkedNode<Book> front;
  private LinkedNode<Book> back;
  private int size;
  private Attribute sortedBy;

/**
 * 
 * Initializes a bookshelf to be empty with the first and last
 * nodes null, and size 0
 */
  public LinkedBookshelf() {
    this.front = null;
    this.back = null;
    this.sortedBy = Attribute.ID;
    this.size = 0;

  }

  /**
   * 
   * @return - The size of the current bookshelf object
   */
  public int size() {
    return this.size;
  }

  /**
   * 
   * @return - true if the bookshelf size is zero and both front and 
   * back nodes are null
   */
  public boolean isEmpty() {
    if (this.size == 0 && this.front == null && this.back == null) {
      return true;
    }
    return false;
  }

 
  
  /**
   * 
   * @returns - The 
   */
  @Override
  public String toString() {
    String result = "";
    
    // add the attribute to be sorted by
    result += this.sortedBy.toString();

    LinkedNode<Book> linkedNode = this.front;

    // Look through the list and get each node
    while (linkedNode.getNext() != null) {
      result += "\n";
      result += linkedNode.getData().toString();
      linkedNode = linkedNode.getNext();
    }
    // add last node
    result += "\n";
    result += linkedNode.getData().toString();

    return result;
  }

  /**
   * 
   * @param index - The index of the list to be looked at
   * @return - The prober node that exists in the list 
   * at the given index
   */
  public LinkedNode<Book> getNode(int index) {
    if (index < 0 | index > this.size - 1) {
      throw new IndexOutOfBoundsException("index out of range");
    }

    LinkedNode<Book> newNode = this.front;

    for (int i = 0; i < index; ++i) {
      newNode = newNode.getNext();
    }

    return newNode;
  }

/**
 * 
 * @param index - The index of the list to be looked at
 * @return - The proper book object that exists at the 
 * node given by the index
 */
  public Book get(int index) {
    if (index < 0 | index > this.size - 1) {
      throw new IndexOutOfBoundsException("index out of range");
    }

    LinkedNode<Book> newNode = this.front;

    for (int i = 0; i < index; ++i) {
      newNode = newNode.getNext();
    }

    return newNode.getData();
  }

  /**
   * 
   * @return - The Book item at the first node, or head,
   * of the list
   */
  public Book getFirst() {
    return this.front.getData();
  }

  /**
   * 
   * @return - The Book item at the last node, or tail,
   * of the list
   */
  public Book getLast() {
    return this.back.getData();
  }

  /**
   * Clears the list to make an empty one again, 
   * setting the back and front nodes to null and
   * size to zero
   */
  public void clear() {
    this.back = null;
    this.front = null;
    this.size = 0;
  }

  /**
   * 
   * @param toAdd - The Book object being added to the list
   * 
   * Adds the given book object to the end of the list
   * as a node
   */
  public void appendBook(Book toAdd) {
    
    LinkedNode<Book> newNode = new LinkedNode<Book>(toAdd);

    if (this.front == null) {
      this.size++;
      this.front = newNode;
      this.back = newNode;
    }

    else {
      this.back.setNext(newNode);
      this.back = newNode;
      this.size++;

    }
  }

  /**
   * 
   * @param toAdd - The book to be inserted into the list
   * 
   * Inserts a book anywhere in the list by using the compareTo
   * method given in the Book class to determine which book is
   * "greater" than the other
   */
  public void insertBook(Book toAdd) {
    LinkedNode<Book> newNode = new LinkedNode<Book>(toAdd);
    LinkedNode<Book> currentNode = this.front;
    LinkedNode<Book> otherNode = null;

    // When this list is empty
    if (this.front == null) {
      this.size++;
      this.front = newNode;
      this.back = newNode;
    }


    // what to do if a larger book is found
    else {
      while (toAdd.compareTo(currentNode.getData(), this.sortedBy) > 0 & currentNode.getNext() != null) {
        otherNode = currentNode;
        currentNode = currentNode.getNext();
      }

      if (otherNode == null) {
        if (toAdd.compareTo(this.front.getData(), this.sortedBy) > 0) {
          // newNode is now the tail and the size increases
          this.size++;
          this.back = newNode;
          currentNode.setNext(newNode);
        } else {
          // newNode is now the head and size increases
          this.size++;
          this.front = newNode;
          newNode.setNext(currentNode);
          this.back = currentNode;
        }
      }

      else {
        if (toAdd.compareTo(currentNode.getData(), this.sortedBy) > 0) {
          this.size++;
          currentNode.setNext(newNode);
          otherNode.setNext(currentNode);
          this.back = currentNode;
        } else {
          this.size++;
          otherNode.setNext(newNode);
          newNode.setNext(currentNode);
          this.back = currentNode;
        }

      }

    }
  }


/**
 * 
 * @param b - The LinkedBookshelf to be sorted
 * @param sortedBy - The parameter to be used to sort the books by
 * 
 * Creates a new LinkedBookshelf object that cycles through b and
 * uses insertBook to add the books in proper order to the new list. 
 * Next, this is done again to put the elements back into b, but now
 * in the correct order
 */
  public static void sort(LinkedBookshelf b, Attribute sortedBy) {
    if (b.size == 1 || b.size == 0) {
      return;
    }
    
    LinkedBookshelf temp = new LinkedBookshelf();
    temp.sortedBy = sortedBy;
    b.sortedBy = sortedBy;
    LinkedNode<Book> currentNode = b.front;
    temp.appendBook(currentNode.getData());
    currentNode = currentNode.getNext();

     while(currentNode.getNext() != null) {
       temp.insertBook(currentNode.getData());
       currentNode = currentNode.getNext();
     }
    temp.insertBook(currentNode.getData());
    b.clear();
    
    LinkedNode<Book> tempCurrentNode = temp.front;
    
    while(tempCurrentNode.getNext() != null) {
      b.appendBook(tempCurrentNode.getData());
      tempCurrentNode = tempCurrentNode.getNext();
    }

    b.appendBook(tempCurrentNode.getData());
  }

}
