public class MyCircularDeque {

  private int[] array;
  private int head; // will always be empty
  private int tail;
  private int size;
  /** Initialize your data structure here. Set the size of the deque to be k. */
  public MyCircularDeque(int k) {
    array = new int[k+1];
    head = 0;
    tail = 1;
  }
  
  /** Adds an item at the front of Deque. Return true if the operation is successful. */
  public boolean insertFront(int value) {
    if (isFull()) {
      return false;
    }
    array[head] = value;
 
    head = computeIndex(head);
    size++;
    return true;
  }
  
  /** Adds an item at the rear of Deque. Return true if the operation is successful. */
  public boolean insertLast(int value) {
    if (isFull()) {
      return false;
    }
    array[tail] = value;

    tail = (tail + 1) % array.length;
    size++;
    return true;
  }
  
  /** Deletes an item from the front of Deque. Return true if the operation is successful. */
  public boolean deleteFront() {
    if (isEmpty()) {
      return false;
    }
    head = (head + 1) % array.length;
      size--;
    return true;
  }
  
  /** Deletes an item from the rear of Deque. Return true if the operation is successful. */
  public boolean deleteLast() {
    if (isEmpty()) {
      return false;
    }
    array[tail] = -1;
    tail = computeIndex(tail);
      size--;
    return true;
  }
  
  /** Get the front item from the deque. */
  public int getFront() {
    if (isEmpty()) {
      return -1;
    }
    return array[(head + 1) % array.length];
  }
  
  /** Get the last item from the deque. */
  public int getRear() {
    if (isEmpty()) {
      return -1;
    }
    return array[computeIndex(tail)];
  }
  
  /** Checks whether the circular deque is empty or not. */
  public boolean isEmpty() {
    return size == 0;
  }
  
  /** Checks whether the circular deque is full or not. */
  public boolean isFull() {
    return size == array.length;
}

  private int computeIndex(int i) {
    return (array.length + i - 1) % array.length;
  }
}

//   h   t
// 0,1,2,3,4