import java.util.*;

class ListNode {
	int val;
	char charVal;
	ListNode next;
	ListNode prev;

	public ListNode(int val) {
		this.val = val;
		this.next = null;
		this.prev = null;
	}

	public ListNode(char val) {
		this.charVal = val;
	}

	public List<Integer> scan() {
		List<Integer> a = new ArrayList<Integer>();
		ListNode curr = this;
		while (curr != null) {
			System.out.println(curr.val);
			a.add(curr.val);
			curr = curr.next;
		}
		return a;
	}

	public ListNode appendHead(int val) {
		ListNode node = new ListNode(val);
		node.next = this;
		return node;
	}

	public ListNode appendTail(int val) {
		ListNode node = new ListNode(val);
		// if (this == null) {
		// 	return node;
		// }
		ListNode curr = this;
		while (curr.next != null) {
			curr = curr.next;
		}
		curr.next = node;
		return this;
	}

	public static int length(ListNode head) {
		int l = 0;
		while (head != null) {
			l++;
			head = head.next;
		}
		return l;
	}

	public static Integer get(ListNode head, int i) {
		int l = 0;
		while (head != null) {
			if (i == l) {
				return head.val;
			}
			l++;
			head = head.next;
		}
		return null;
	}

	public static ListNode build(int[] vals) {
		ListNode dummy = new ListNode(-1);

		ListNode curr = dummy;
		for (int i = 0; i < vals.length; i++) {
			curr.next = new ListNode(vals[i]);
			curr = curr.next;
		}

		return dummy.next;
	}

	public static ListNode buildStringNodes(char[] vals) {
		ListNode dummy = new ListNode(-1);

		ListNode curr = dummy;
		for (int i = 0; i < vals.length; i++) {
			curr.next = new ListNode(vals[i]);
			curr = curr.next;
		}

		return dummy.next;
	}
}

// class ListNode {
// 	public int val;
// 	public ListNode next;
// 	public ListNode prev;

// 	public ListNode(int val) {
// 		this.val = val;
// 		this.next = null;
// 		this.prev = null;
// 	}
// }
  
  
class MyLinkedList {
	private ListNode head;
	private ListNode tail;
	private int size;

	/** Initialize your data structure here. */
	public MyLinkedList() {
	}

	public List<Integer> scan() {
		List<Integer> a = new ArrayList<Integer>();
		ListNode curr = head;
		while (curr != null) {
			System.out.println(curr.val);
			a.add(curr.val);
			curr = curr.next;
		}
		return a;
	}

	public ListNode getNode(int index) {
		ListNode curr = head;

		while (curr != null && index > 0) {
			index--;
			curr = curr.next;
		}
		return curr;
	}
	
	/** Get the value of the index-th node in the linked list. If the index is invalid, return -1. */
	public int get(int index) {
		ListNode node = getNode(index);
		return node == null ? -1 : node.val;
	}
	
	/** Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list. */
	public void addAtHead(int val) {
		ListNode newHead = new ListNode(val);
		newHead.next = head;
		if (head != null) {
			head.prev = newHead;
		}
		
		head = newHead;
		size++;
		if (tail == null) {
			tail = head;
		}
	}
	
	/** Append a node of value val to the last element of the linked list. */
	public void addAtTail(int val) {
		ListNode newTail = new ListNode(val);
		if (tail == null) {
			tail = newTail;
          	head = newTail;
		}
		else {
			tail.next = newTail;
			newTail.prev = tail;
			tail = tail.next;
		}
		size++;
	}
	
	/** Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, 
	 the node will not be inserted. */
	public void addAtIndex(int index, int val) {
		ListNode curr = null;

		if (size == index) {
			addAtTail(val);
		}
		else if (index > size) {
			return;
		}
		else {
			curr = getNode(index);
			if (curr.prev == null) {
				addAtHead(val);
			}
			else {
				ListNode newNode = new ListNode(val);
				curr.prev.next = newNode;
				newNode.prev = curr.prev;
				newNode.next = curr;
				curr.prev = newNode;
				size++;
			}
		}
	}
	
	/** Delete the index-th node in the linked list, if the index is valid. */
	public void deleteAtIndex(int index) {
		if (index > size - 1) {
			return;
		}

		ListNode curr = getNode(index);
		if (curr.prev == null) {
			// delete head;
			head = curr.next;
			head.prev = null;
		}
		else {
			ListNode next = curr.next;
			curr.prev.next = next;
			next.prev = curr.prev;
			curr.next= null;
		}
		size--;
	}
}
  
  /**
   * Your MyLinkedList object will be instantiated and called as such:
   * MyLinkedList obj = new MyLinkedList();
   * int param_1 = obj.get(index);
   * obj.addAtHead(val);
   * obj.addAtTail(val);
   * obj.addAtIndex(index,val);
   * obj.deleteAtIndex(index);
   */




// public class LinkListPalindrome {
// 	public boolean isPalindrome(ListNode head) {
// 		// Write your solution here
// 		if (head == null || head.next == null) {
// 		  return true;
// 		}
	
// 		// find the mid point 
// 		// always use the mid.next, so we don't care about odd or even
// 		ListNode mid = findMid(head);
// 		// reverse it
// 		ListNode second = reverse(mid.next);
// 		// compare 
// 		while (second != null) {
// 			if (head.value != second.value) {
// 				return false;
// 			}
// 			else {
// 				head = head.next;
// 				second = second.next;
// 			}
// 		}
// 		return true;
// 	  }
	
// 	  private ListNode reverse(ListNode head) {
// 		ListNode prev = null;
	
// 		while (head != null) {
// 		  ListNode next = head.next;
// 		  head.next = prev;
// 		  prev = head;
// 		  head = next;
// 		}
	
// 		return prev;
// 	  }
	
// 	  private ListNode findMid(ListNode head) {
// 		ListNode slow = head;
// 		ListNode fast = head;
	
// 		while (fast.next != null && fast.next.next != null) {
// 		  slow = slow.next;
// 		  fast = fast.next.next;
// 		}
	
// 		return slow;
// 	  }

// 	  public ListNode removeElements(ListNode head, int val) {
// 		// Write your solution here
// 		if (head == null) {
// 		  return head;
// 		}
// 		if (head.next == null) {
// 		  if (head.value == val) {
// 			return null;
// 		  }
// 		  else {
// 			return head;
// 		  }
// 		}
// 		ListNode curr = head;
// 		ListNode prev = null;
// 		while (curr != null) {
// 		  ListNode next = curr.next;
// 		  if (curr.value == val) {
// 			if (prev == null) {
// 			  head = next;
// 			}
// 			else {
// 			  prev.next = next;
// 			}
// 		  }
// 		  else {
// 			prev = curr;
// 		  }
// 		  curr = next;
// 		}
	
// 		return head;
// 	  }

// 	  public void printMe(int a) {
// 		  String b = Integer.toString(a);

// 		  for (int i = 0; i < b.length(); i++) {
// 			System.out.println(b.charAt(i));
// 		  }
// 		  int res = (int) Math.pow(10, 2);
// 		  System.out.println(res);
// 	  }
// }