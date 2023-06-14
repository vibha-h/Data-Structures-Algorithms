import java.io.*;
import java.util.*;

public class CDLL_List<T>
{
	private CDLL_Node<T> head;  // pointer to the front (first) element of the list
	private int count=0;

	public CDLL_List()
	{
		head = null; // compiler does this anyway. just for emphasis
	}

	// LOAD LINKED LIST FORM INCOMING FILE

	public CDLL_List( String fileName, String insertionMode ) throws Exception
	{
			BufferedReader infile = new BufferedReader( new FileReader( fileName ) );
			while ( infile.ready() )
			{	@SuppressWarnings("unchecked")
				T data = (T) infile.readLine(); // CAST CUASES WARNING (WHICH WE CONVENIENTLY SUPPRESS)
				if ( insertionMode.equals("atFront") )
					insertAtFront( data );
				else if ( insertionMode.equals( "atTail" ) )
					insertAtTail( data );
				else
					die( "FATAL ERROR: Unrecognized insertion mode <" + insertionMode + ">. Aborting program" );
			}
			infile.close();
	}

	private void die( String errMsg )
	{
		System.out.println( errMsg );
		System.exit(0);
	}

	// ########################## Y O U   W R I T E / F I L L   I N   T H E S E   M E T H O D S ########################

	// OF COURSE MORE EFFICIENT TO KEEP INTERNAL COUNTER BUT YOU COMPUTE IT DYNAMICALLY WITH A TRAVERSAL LOOP
	@SuppressWarnings("unchecked")
	public int size()
	{	
		CDLL_Node<T> cur = head;
		String startNodeData = (String)cur.data;
		while (cur != null) {			
			cur = cur.next;			
			count++;
			if(cur.data == startNodeData){
				break;
			}
		}
		return count;
	}


	// TACK A NEW NODE ONTO THE FRONT OF THE LIST
	
	public void insertAtFront(T data) {
		// you could call insertAtTail here
		// then "adjust" the head pointer
		if (head == null) {
			head = new CDLL_Node<T>();
			head.data = data;
			head.next = head.prev = head;
		}else{			
			CDLL_Node<T> last = head.prev;
			CDLL_Node<T> n = new CDLL_Node<T>();
			n.data = data;
			// setting up previous and next of new node
			n.next = head;
			n.prev = last;

			// Update next and previous pointers of head and last.
			last.next = head.prev = n;
			// Update head pointer
			head = n;		
		}
	}

	// TACK ON NEW NODE AT END OF LIST
	@SuppressWarnings("unchecked")
	public void insertAtTail(T data)
	{
		// you could call insertAtFront here
		// then "adjust" the head pointer
		insertAtFront(data);
		head=head.next;
	}

	// RETURN TRUE/FALSE THIS LIST CONTAINS A NODE WITH DATA EQUALS KEY
	public boolean contains( T key )
	{
		if(search(key)!=null){
			return true;
		}
		return false;
	}

	// RETURN REF TO THE FIRST NODE (SEARCH CLOCKWISE FOLLOWING next) THAT CONTAINS THIS KEY. DO -NOT- RETURN REF TO KEY ISIDE NODE
	// RETURN NULL IF NOT FOUND
	/*
	 * loop through the nodes 
	 * compare each node.data to key
	 * if key is a match, return that node
	 */
	public CDLL_Node<T> search( T key )
	{
		CDLL_Node<T> cur = head;
		String startNodeData = (String)cur.data;
		while(cur!=null){
			cur=cur.next;
			if(cur.data.equals(key)){
				return cur;
			}
			if(cur.data == startNodeData){
				break;
			}
		}
		return null;
	}

	// RETURNS CONATENATION OF CLOCKWISE TRAVERSAL
	@SuppressWarnings("unchecked")
	public String toString()
	{
		String toString = "";
		CDLL_Node<T> cur = head;
		String startNodeData = (String)cur.data;
		while (cur != null) {
			if(toString != ""){
				toString += "<=>";
			}
			toString += cur;			
			cur = cur.next;			
			if(cur.data == startNodeData){
				break;
			}
		}
		return toString;
	}

} // END CDLL_LIST CLASS

// PRIVATE TO CODE OUTSIDE FILE. BUT PUBLIC TO CODE INSIDE
class CDLL_Node<T>
{
  T data; // DONT DEFINE MEMBERS AS PUBLIC OR PRIVATE
  CDLL_Node<T> prev, next; //
  CDLL_Node() 		{ this( null, null, null ); }
  CDLL_Node(T data) { this( data, null, null);  }
  CDLL_Node(T data, CDLL_Node<T> prev, CDLL_Node<T> next)
  {	this.data=data; this.prev=prev; this.next=next;
  }
  public String toString() // TOSTRING MUST BE PUBLIC
  {	return ""+data;
  }
} //END NODE CLASS

