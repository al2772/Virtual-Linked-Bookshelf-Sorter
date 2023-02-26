/**
 * 
 * @author Adam Lewandowski
 *
 */
public class LinkedBookshelfTester {

  /**
   * 
   * @return - True if LinkedNode properly creates a new node
   */
  public static boolean testLinkedNode() {
    Book.resetGenerator();

    Book testData = new Book("Test Book", 345);
    Book nextBook = new Book("Test Book 2", 430);
    LinkedNode<Book> testNext = new LinkedNode<Book>(nextBook);

    LinkedNode<Book> testNode = new LinkedNode<>(testData, testNext);

    if (testNode.getData() == testData && testNode.getNext() == testNext) {
      return true;
    }

    return false;
  }

  /**
   * 
   * @return - True if the toString method generates a string of the books in the list (does not
   *         have to be in order given by attribute).
   */
  public static boolean testToString() {
    Book.resetGenerator();

    Book test1 = new Book("Test Book", 345, "Lewandowski", "Adam");
    Book test2 = new Book("Test Book2", 32, "Lock", "John");

    LinkedBookshelf testShelf = new LinkedBookshelf();

    testShelf.appendBook(test1);
    testShelf.appendBook(test2);

    if (testShelf.toString().equals(
        "ID\n0: \"Test Book\", Lewandowski, Adam (345)\n1: \"Test Book2\", Lock, John (32)")) {
      return true;
    }

    return false;
  }

  /**
   * 
   * @return - True if the clear method accurately makes the list empty by setting size to zero and
   *         both next and data to null
   */
  public static boolean testClear() {
    Book.resetGenerator();

    Book testData = new Book("Test Book", 345, "Lewandowski", "Adam");
    LinkedBookshelf testShelf = new LinkedBookshelf();
    testShelf.appendBook(testData);

    testShelf.clear();

    if (testShelf.isEmpty() != true | testShelf.size() != 0) {
      return false;
    }

    try {
      if (testShelf.getFirst() != null | testShelf.getLast() != null) {
        return false;
      } else {
        return true;
      }

    } catch (NullPointerException e) {
      return true;
    }
  }

  /**
   * 
   * @return - True if the appendBook method properly places new books at the end of the list every
   *         time it is called
   */
  public static boolean testAddBooks() {
    Book.resetGenerator();

    Boolean result = false;

    LinkedBookshelf testShelf = new LinkedBookshelf();
    Book test1 = new Book("Test1", 276, "Everett", "Tom");
    Book test2 = new Book("Test2", 456, "Ham", "Timmy");
    Book test3 = new Book("Test3", 321, "Turner", "Bob");
    testShelf.appendBook(test1);
    testShelf.appendBook(test2);

    LinkedNode<Book> node = new LinkedNode<Book>(test2);

    if (testShelf.getLast().toString().equals(node.toString())) {
      result = true;
    }

    LinkedBookshelf testShelf2 = new LinkedBookshelf();
    int initialShelfSize = testShelf2.size();
    testShelf2.appendBook(test3);

    if (initialShelfSize + 1 != testShelf2.size()) {
      result = false;
    }

    return result;
  }

  /**
   * 
   * @return - True if the returned String form of the list is accurately sorted by the sortedBy
   *         attribute given
   */
  public static boolean testSortBooks() {
    Book.resetGenerator();
    
    Boolean result = false;

    Book test1 = new Book("Test Book1", 345, "Lewandowski", "Adam");
    Book test2 = new Book("Test Book2", 32, "Lock", "John");
    Book test3 = new Book("Test Book3", 657, "Anderson", "Tim");
    Book test4 = new Book("Test Book4", 875, "Johnson", "Jack");
    
    Book test5 = new Book("Test Book1", 345, "Lewandowski", "Adam");
    Book test6 = new Book("Test Book2", 32, "Lock", "John");
    Book test7 = new Book("Test Book3", 657, "Anderson", "Tim");
    Book test8 = new Book("Test Book4", 875, "Johnson", "Mack");

    LinkedBookshelf testShelf = new LinkedBookshelf();
    LinkedBookshelf testShelf2 = new LinkedBookshelf();

    testShelf.insertBook(test1);
    testShelf.insertBook(test2);
    testShelf.insertBook(test3);
    testShelf.insertBook(test4);
    
    testShelf2.insertBook(test5);
    testShelf2.insertBook(test6);
    testShelf2.insertBook(test7);
    testShelf2.insertBook(test8);

    LinkedBookshelf.sort(testShelf, Attribute.PAGECOUNT);
    LinkedBookshelf.sort(testShelf2, Attribute.PAGECOUNT);


    String actual = testShelf.toString().trim();
    String actual2 = testShelf2.toString().trim();
    
    String expected = "PAGECOUNT\n1: \"Test Book2\", Lock, John (32)\n0: \"Test Book1\", Lewandowski, Adam (345)\n2: "
        + "\"Test Book3\", Anderson, Tim (657)\n3: \"Test Book4\", Johnson, Jack (875)";
    String expected2 = "AUTHOR\n0: \"Test Book1\", Lewandowski, Adam, (345)\n1: \"Test Book2\", Lock, John, (32)\n"
        + "3: \"Test Book4\", Johnson, Mack, (875)\n2: \"Test Book3\", Anderson, Tim (657";

    if (actual.equals(expected.trim())) {
      result = true;
    }
    if (actual2.equals(expected2.trim())){
      System.out.println(actual2);
      result = true;
    }
    return result;
  }

  /**
   * 
   * @return - True if and only if all of the tests run and return a boolean value of true
   */
  public static boolean runAllTests() {
    if (testLinkedNode()) {
      System.out.println("Linked node test passed");
      if (testClear()) {
        System.out.println("Clear test passed");
        if (testAddBooks()) {
          System.out.println("Add book test passed");
          if (testToString()) {
            System.out.println("To string test passed");
            if (testSortBooks()) {
              System.out.println("Sort books test passed");
              return true;
            }
          }
        }
      }

    }
    return false;
  }

  /**
   * 
   * @param args
   * 
   *             Runs the runAllTests method, which tests all methods to see if they return true
   */
  public static void main(String[] args) {
    runAllTests();
  }

}
