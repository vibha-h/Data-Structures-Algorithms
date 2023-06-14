import java.io.*;
import java.util.*;

public class Tower<T>
{
	private Disk<T> base;  // pointer to first disk at BASE of the tower (i.e. the old head pointer)
	private Disk<T> top;   // pointer to last disk at TOP of the tower   (i.e. the old tail pointer)

	public Tower()
	{	base = null; // compiler does this anyway. just for emphasis
	}

	public boolean empty()
	{
		return (base==null);
	}

	// i.e. the old insertAtTail or now insertAtTop we call it a PUSH
	public void push(T label)
	{
		if (this.empty()) {
			base = new Disk<T>(label, base);
			return;
		} else {
			Disk<T> cur = base;
			while (cur.next != null) {
				cur = cur.next;
			}
			cur.next = new Disk<T>(label, null);
		}
	}

	// i.e. the old removeAtTail or now removeAtTop we call it a POP
	public Disk<T> pop() throws Exception
	{
		// YOU WRITE THIS METHOD
		// MUST THROW FATAL EXCEPTION IF TOWER IS EMPTY
		
		Disk<T> temp = base;
		//if(!this.empty()){
			//System.out.println(base.label +"-----------");
			if(temp == null || temp.next==null){
				//top=null;
				base = null;
				return base;
			}
			while(temp.next!=null){
				top = temp;
				//System.out.println(temp.label + " : " + top.label + "-----------");
				temp = temp.next;
			}
			//System.out.println(top.label+"-----------");
			top.next=null;//set pointer to previous node
			//System.out.println(top.label);
		//}
		return top; // JUST TO MAKE IT COMPILE. REPLACE WITH YOUR CODE
	}

	// public boolean remove(T key)
	// {
	// 	if(head==null){
	// 		return false;
	// 	} 
	// 	Node<T> temp = head;
	// 	Node<T> prev = null;
	// 	while(temp!=null){
	// 		if(temp.data.equals(key)){
	// 			if(prev==null){ //first node in list matches key
	// 				removeAtFront();
	// 			}else{ //if a match is found anywhere else in the list,
	// 				prev.next=temp.next; //skips the matching node and sets that to next node in list (unlink the matching node from linkedlist)
	// 				return true;
	// 			}
	// 		}
	// 	prev=temp;//set pointer to previous node
	// 	temp=temp.next; //move pointer to next node
	// 	}
	// 	return false;
	// }

	// public boolean removeAtTail()	// RETURNS TRUE IF THERE WAS NODE TO REMOVE
	// {
	// 	Node<T> temp=head; 
	// 	Node<T> prev=null;
	// 	if(head==null){
	// 		return false;
	// 	}
	// 	while(temp!=null){
	// 		prev=temp; 
	// 		temp=temp.next;
	// 	}
	// 	return remove(prev.data);
		
	// }

	// prints the tower base to top, left to right,  respectively //
	public String toString()
	{	if (base == null ) 	return "EMPTY\t";
		String toString = "";
		for ( Disk<T> curr = base; curr != null ; curr=curr.next )
			toString += curr.label + " ";

		return toString;
	}
} // END TOWER CLASS

/*###############################################################################*/

class Disk<T>
{
	T label;
	Disk<T> next;

	Disk(T data)
	{	this( data, null );
	}

	Disk(T label, Disk<T> next)
	{	this.label = label;
		this.next = next;
	}

} // END DISK CLASS