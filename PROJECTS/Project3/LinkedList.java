import java.io.*;
import java.util.*;

// NOTICE THE "<T extends Comparable<T>>"
// using <T extends Comparable<T>> in here means compiler wont let the code in main send in any T type
// that does not implement Comparable.  Now we do not have to cast the incoming key to a Comparable
// in our insertInOrder() method. Compiler now lets us call .compareTo off the dot on the incoming key
// without throwing an error.

public class LinkedList<T extends Comparable<T>> 
{
	private Node<T> head;  // pointer to the front (first) element of the list

	public LinkedList()
	{
		head = null; // compiler does this anyway. just for emphasis
	}

	// LOAD LINKED LIST FROM INCOMING FILE
	@SuppressWarnings("unchecked")
	public LinkedList( String fileName, boolean orderedFlag )
	{	head = null;
		try
		{
			BufferedReader infile = new BufferedReader( new FileReader( fileName ) );
			while ( infile.ready() )
			{
				if (orderedFlag)
					insertInOrder( (T)infile.readLine() );  // WILL INSERT EACH ELEM INTO IT'S SORTED POSITION
				else
					insertAtTail( (T)infile.readLine() );  // TACK EVERY NEWELEM ONTO END OF LIST. ORIGINAL ORDER PRESERVED
			}
			infile.close();
		}
		catch( Exception e )
		{
			System.out.println( "FATAL ERROR CAUGHT IN C'TOR: " + e );
			System.exit(0);
		}
	}

	//-------------------------------------------------------------

	// inserts new elem at front of list - pushing old elements back one place

	public void insertAtFront(T data)
	{
		head = new Node<T>(data,head);
	}

	// we use toString as our print

	public String toString()
	{
		String toString = "";

		for (Node<T> curr = head; curr != null; curr = curr.next )
		{
			toString += curr.data;		// WE ASSUME OUR T TYPE HAS toString() DEFINED
			if (curr.next != null)
				toString += " ";
		}

		return toString;
	}

	// ########################## Y O U   W R I T E    T H E S E    M E T H O D S ########################



	public int size() // OF COURSE MORE EFFICIENT to KEEP COUNTER BUT YOU  MUST WRITE LOOP
	{
		/*
		 * initialize to 0
		 * create new Node<T> because??? cant we just use head?
		 * loop through existing nodes and increment size
		 */
		int size = 0;
		Node<T> cur = head;
		while (cur != null) {
			cur = cur.next;
			size++;
		}
		return size;
	}

	public boolean empty()
	{
		return size() == 0;	//returns true if size()==0 , otherwise returns false
	}

	public boolean contains( T key )
	{
		return search(key)!=null; //returns true if search(key) is found otherwise returns false
	}

	public Node<T> search( T key )
	{
		/*
		 * while the node is not null, loop through and if 
		 * the value of the node matches the key, return the node
		 * exit if statement and increment to next node
		 * keep looping until the next node (cur.next) is null
		 */
		Node<T> cur = head;
		while (cur != null) {
			if (cur != null && cur.toString().equals(key)) {
				return cur;
			}
			cur = cur.next;
		}
		return null;
	}

	// TACK A NEW NODE (CABOOSE) ONTO THE END OF THE LIST
	public void insertAtTail(T data)
	{
		/*
		 * if the size of the list is 0, insertAtFront() to create a new Node and place data into node, and exit
		 * else create new node cur and set equal to head
		 * while cur.next exists (isnt null), increment to cur.next.
		 * exit while loop when cur.next becomes null
		 * set cur.next to be a new Node<T> that takes data 
		 */
		if (this.size() == 0) {
			insertAtFront(data);
			return;
		} else {
			Node<T> cur = head;
			while (cur.next != null) {
				cur = cur.next;
			}
			cur.next= new Node<T>(data, null);
		}
	}

	// IF YOU DEFINE <T> at the top of this class as <T implements Comparable>
	// YOU DO NOT HAVE TO CAST TO COMPARABLE AND YOU DO NOT NEED TO SUPPRESS 
	public void insertInOrder(T  data)
	{
		Node<T> temp= head;
		Node<T> prev=null;
		if (head==null || data.compareTo(head.data)<0){
			//if the linklist is empty or the incoming data is lexically less than existing data, add to front
		insertAtFront(data);
		}else{
			Node<T> n =new Node<T>(data,null);
			while(temp!=null && data.compareTo(temp.data)>0){
				//if the incoming data is lexically more than the existing data, keep looping
				//maintain a pointer to previous node in prev variable
				prev=temp;
				temp=temp.next;
			}
			//now at position to insert new node, set new node to next position
			prev.next=n;
			// now continue to next nodes from list
			n.next=temp;				
		}
		return;

	}

	public boolean remove(T key)
	{
		if(head==null){
			return false;
		} 
		Node<T> temp = head;
		Node<T> prev = null;
		while(temp!=null){
			if(temp.data.equals(key)){
				if(prev==null){ //first node in list matches key
					removeAtFront();
				}else{ //if a match is found anywhere else in the list,
					prev.next=temp.next; //skips the matching node and sets that to next node in list (unlink the matching node from linkedlist)
					return true;
				}
			}
		prev=temp;//set pointer to previous node
		temp=temp.next; //move pointer to next node
		}
		return false;
	}

	public boolean removeAtTail()	// RETURNS TRUE IF THERE WAS NODE TO REMOVE
	{
		Node<T> temp=head; 
		Node<T> prev=null;
		if(head==null){
			return false;
		}
		while(temp!=null){
			prev=temp; 
			temp=temp.next;
		}
		return remove(prev.data);
		
	}

	public boolean removeAtFront() // RETURNS TRUE IF THERE WAS NODE TO REMOVE
	{
	if (head==null) {
		return false;
	}
	//sets head to the next node
	  head = head.next; // THIS IS THE OP
	  return true;
	}

	public LinkedList<T> union( LinkedList<T> other )
	{
		LinkedList<T> union = new LinkedList<T>();
		Node<T> temp = head;
		Node<T> otemp = other.head;	
		if(temp == null && otemp ==null){ //if both lists are empty
			return union; 
		}
		while(temp != null){
			union.insertAtTail(temp.data);
			temp = temp.next;
		}
		while(otemp != null){
			if(!union.contains(otemp.data)){ //if union doesnt alr contain the data in otemp,
				union.insertInOrder(otemp.data);
			}
			otemp= otemp.next; 
		}
		return union;
	}

	public LinkedList<T> inter( LinkedList<T> other )
	{
		LinkedList<T> inter = new LinkedList<T>();
		Node<T> temp = head;
		Node<T> otemp = other.head;	
		if(temp == null || otemp==null){ //if either list is empty, new list should be empty bc there are no same values
			return inter;
		}
		while(temp != null){
			if(other.contains(temp.data)){
				inter.insertInOrder(temp.data);
			}
			temp = temp.next;
		}
		

		return inter;
	}
	public LinkedList<T> diff( LinkedList<T> other ) //arg1 - arg2
	{
		LinkedList<T> diff = new LinkedList<T>();
		Node<T> temp = head;
		if(temp == null){ 
			return diff;
		}
		while(temp != null){
			if(!other.contains(temp.data)){
				diff.insertInOrder(temp.data);
			}
			temp=temp.next;
		}
		return diff;
	}
	public LinkedList<T> xor( LinkedList<T> other ) //union -inter
	{
		return this.union(other).diff(this.inter(other)); 

	}


} //END LINKEDLIST CLASS 

// A D D   N O D E   C L A S S  D O W N   H E R E 
// R E M O V E  A L L  P U B L I C  &  P R I V A T E (except toString)
// R E M O V E  S E T T E R S  &  G E T T E R S 
// M A K E  T O  S T R I N G  P U B L I C
class Node<T extends Comparable<T>> // tells compiler our incoming T type implements Comparable

{
T data;
Node<T> next;

Node()
  {
    this( null, null );
  }

Node(T data)
  {
    this( data, null );
  }

Node(T data, Node<T> next)
  {
    this.data=data;
    this.next=next;
  }

public String toString(){ 
	return ""+this.data;
}


} //EOF