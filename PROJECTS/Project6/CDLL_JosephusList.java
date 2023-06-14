import java.io.*;
import java.util.*;

public class CDLL_JosephusList<T>
{
	private CDLL_Node<T> head;  // pointer to the front (first) element of the list
	private int count=0;
	// private Scanner kbd = new Scanner(System.in); // FOR DEBUGGING. See executeRitual() method 
	public CDLL_JosephusList()
	{
		head = null; // compiler does this anyway. just for emphasis
	}

	// LOAD LINKED LIST FORM INCOMING FILE
	
	public CDLL_JosephusList( String infileName ) throws Exception
	{
		BufferedReader infile = new BufferedReader( new FileReader( infileName ) );	
		while ( infile.ready() )
		{	@SuppressWarnings("unchecked") 
			T data = (T) infile.readLine(); // CAST CUASES WARNING (WHICH WE CONVENIENTLY SUPPRESS)
			insertAtTail( data ); 
		}
		infile.close();
	}
	

	
	// ########################## Y O U   W R I T E / F I L L   I N   T H E S E   M E T H O D S ########################
	
	// TACK ON NEW NODE AT END OF LIST
	public void insertAtTail(T data)
	{
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
			head=n;
			head = head.next;		
		}
		//System.out.println("Head.prev: "+ head.prev +" Head: "+ head +" Head.next: "+head.next);
	}

	
	public int size()
	{	
		count=0;
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
	
	// RETURN REF TO THE FIRST NODE CONTAINING  KEY. ELSE RETURN NULL
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
	
	void removeNode( CDLL_Node<T> deadNode )
	{
		CDLL_Node<T> tempPrev = deadNode.prev;
		CDLL_Node<T> tempNext = deadNode.next;
		//if skipCount is negative: need to link deadNode.prev to deadNode.next
		//System.out.println("tempPrev: "+ tempPrev.data + " tempNext: " + tempNext.data + " Head: " + head.data);
		if(head.next==deadNode){
			head.next=tempNext;
			tempNext.prev=head;
		//else skipCount is positive: 
		}else{
			//head.prev=tempPrev;
			//tempPrev.next=head;
			tempNext.prev = tempPrev;
			tempPrev.next = tempNext;
		}
			
	}
	
	public void executeRitual( T first2Bdeleted, int skipCount )
	{
		if (size() <= 1 ) return;
		CDLL_Node<T> curr = search( first2Bdeleted );
		if ( curr==null ) return;
		//System.out.println("CURR:" + curr.data);
		
		// OK THERE ARE AT LEAST 2 NODES AND CURR IS SITING ON first2Bdeleted
		do
		{
			CDLL_Node<T> deadNode = curr; 
			T deadName = deadNode.data; 
			CDLL_Node<T> tempPrev = curr.prev;
			CDLL_Node<T> tempNext = curr.next;
			//System.out.println("tempPrev.data= "+ tempPrev.data+" tempNext.data= "+ tempNext.data);
			
			// ** println( "stopping on curr.data to delete curr.data");
			System.out.println("stopping on "+ curr.data + " to delete "+ curr.data);
			
			// BEFORE DOING ACTUAL DELETE DO THESE TWO THINGS 

			// 1: you gotta move that curr off of the deadNode. 
			//    if skipCount poitive do curr=curr.next  esle do  curr=curr.prev
			if(skipCount>0){
				curr=tempNext;
			}else{
				curr=tempPrev;
			}
			// 2: check to see if HEAD is pointing to the deadnode. 
			//    If so make head=curr 
			if(head==deadNode){
				head=curr;
			}
			
			// NOW DELETE THE DEADNODE
			//System.out.println("------------deadNode= "+deadName +"\n head= "+ head.data);
			removeNode(deadNode);
			// println("deleted. list now: + toString() ); // toString prints the
			System.out.println("deleted. list now: "+ toString());
			
			// if the list size has reached 1 return YOU ARE DONE.  RETURN RIGHT HERE

			// ** println("resuming at curr.data, skipping curr.data + skipCount-1 nodes CLOCKWISE/COUNTERWISE after");
			if(curr!=curr.next){
			if(skipCount>0){
				System.out.println("resuming at "+ curr.data+ ", skipping "+ curr.data +" + "+ (skipCount-1)+" nodes CLOCKWISE after");
			}else{
				System.out.println("resuming at "+ curr.data+ ", skipping "+ curr.data +" + "+ (Math.abs(skipCount)-1)+" nodes COUNTER_CLOCKWISE after");
			}
		}
			
			// write loop that advances curr pointer skipCount times (be sure of CLOCKWISE or COUNTER)
			if(skipCount>0){
				for(int i=0; i<skipCount; i++){
					curr=curr.next;
				}
				}else if(skipCount<0){
					for(int i=0; i>skipCount; i--){
						curr=curr.prev;
					}
				}
			}

			// OPTIONAL HERE FOR DEBUGGING TO MAKE IT STOP AT BOTTOM OF LOOP
			// Scanner kbd = new Scanner( System.in ); String junk = kbd.nextLine();   
		while (size() > 1 );  // ACTUALLY COULD BE WHILE (TRUE) SINCE WE RETURN AS SOON AS SIZE READES 1

	}

 class CDLL_Node<T>
{
  private T data;
  private CDLL_Node<T> prev, next; // EACH CDLL_Node PTS TO ITS PREV  & NEXT

   CDLL_Node()
  {
    this( null, null, null );  // 3 FIELDS TO INIT
  }

   CDLL_Node(T data)
  {
    this( data, null, null);
  }
   CDLL_Node(T data, CDLL_Node<T> prev, CDLL_Node<T> next)
  {
	this.data=data; 
	this.prev=prev; 
	this.next=next;
  }
  public String toString()
  {
	  return ""+data;
  } 
	 
} //EOF
	
} // END CDLL_LIST CLASS

// COPY THE NODE CLASS INTO HERE THEN DELETE YOUR NODE.JAVA file 
// REMOVE ALL PUBLIC AND REMOVE SETTERS GETTERS
// LEAVE PUBLIC ONLY ON TOSTRING