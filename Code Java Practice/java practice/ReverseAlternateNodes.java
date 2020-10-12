import java.util.HashSet;

public class ReverseAlternateNodes {
	public ListNode reverseAlternate(ListNode head) {
		// Write your solution here
		if (head == null || head.next == null) {
		  return head;
		}
	
		ListNode prev = null;
		ListNode old = head;
		ListNode newSubHead = null;
			ListNode newTail = head;
	
		int index = 0;
	
		while (old != null) {
		  ListNode next = old.next;
		  if (index % 2 != 0) {
			old.next = newSubHead;
			newSubHead = old;
			prev.next = next;
			if (next != null) {
			  newTail = next;
			}
			else {
			  newTail = prev;          
			}
			
		  }
		  index++;
		  prev = old;
		  old = next;
		}
	
		newTail.next = newSubHead;
		return head;
	}


	public ListNode removeDup(ListNode head) {
		// Write your solution here
	
		HashSet<Integer> memo = new HashSet<>();
		HashSet<Integer> duplicates = new HashSet<>();
	
		ListNode curr = head;
	
		while (curr != null) {
		  if (memo.contains(curr.val)) {
			duplicates.add(curr.val);
		  }
		  else {
			memo.add(curr.val);
		  }
		  curr = curr.next;
		}
	
		curr = head;
		ListNode prev = null;
	
		while (curr != null) {
		  ListNode next = curr.next;
		  if (duplicates.contains(curr.val)) {
			if (prev == null) {
			  head = head.next;
			}
			else {
			  prev.next = curr.next;
			}
		  }
		  curr = next;
		}
	
		return head;
	  }
}
