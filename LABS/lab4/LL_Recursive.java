// 2021 FALL CS 445 LAB #4  STUDENT STARTER FILE

import java.io.*;
import java.util.*;

public class LL_Recursive<T>
{
	private Node<T> head;  // pointer to the front (first) element of the list
	String toString = "";

	public LL_Recursive()
	{
		head = null; // compiler does this anyway. just for emphasis
	}

	// INSERTS NEW NODE AT FRONT PUSHING EXISTING NODES BACK ONE PLACE
	public void insertAtFront(T data)
	{
		head = new Node<T>(data,head);
	}

 	
	// #####  W R I T E  (O R  R E-W R I T E)  T H E S E   M E T H O D S   R E C U R S I V E L Y ####


 	// MUST USE (CALL) search() METHOD IN THIS CODE TO DETERMINE THE RETURN VALUE
 	// NO LOOPS ALLOWED NO RECURSION ALLOWED.  THE SEARCH WILL BE RECURSIVE THOUGH
 	public boolean contains( T key )
 	{
		return search(key)!=null; //returns true if search(key) is found otherwise returns false
	}

	// YOU MUST WRITE THIS USING RECURSION
	// YOU WILL HAVE TO WRITE A HELPER METHOD. THE HELPER WILL BE RECURSIVE
	public void insertAtTail(T data)
	{
		if (this.size() == 0) {
			insertAtFront(data);
			return;
		} else {
			insertAtTailHelper(this.head, data);
		}
	}

	public void insertAtTailHelper(Node<T> cur, T data)
	{
		if(cur.next == null){
			cur.next= new Node<T>(data, null);
		}else{
			insertAtTailHelper(cur.next, data);
		}
	}

	// WE will illustrate this in class today 2/6/2022
	public int size()
	{
		return sizeHelper( head ); 
	}
	private int sizeHelper( Node<T> head )
	{
		if (head==null) {
			return 0;
		}
		return 1 + sizeHelper(head.next);
	}
	
	// USE THE TOSTRING AS OUR PRINT.  ***MUST RE-WRITE USING RECURSION***
	// YOU WILL HAVE TO WRITE A HELPER METHOD. THE HELPER WILL BE RECURSIVE
	public String toString()
	{
		return toStringHelper(head);
		// String toString = "";

		// for (Node<T> curr = head; curr != null; curr = curr.next)
		// {
		// 	toString += curr.data;		// WE ASSUME OUR T TYPE HAS toString() DEFINED
		// 	if (curr.next != null)
		// 		toString += " -> ";
		// }

		// return toString + "\n";
	}

	public String toStringHelper(Node<T> cur)
    {
		//cur=head;
		if(cur==null){
			return toString;
		}else{
			toString += cur.data;	
			if(cur.next != null){
				toString += " -> ";		
			}			
		}
		return toStringHelper(cur.next);
	}

	// MUST BE RECURSIVE. YOU WILL HAVE TO WRITE A HELPER METHOD. THE HELPER WILL BE RECURSIVE
	public Node<T> search( T key )
	{
		return searchHelper(head, key); //JUST TO MAKE IT COMPILE. REPLACE WITH YOUR CODE
	}

	public Node<T> searchHelper(Node<T> cur, T key)
	{
		if(cur==null){
			return null;
		}else if( cur.toString().equals(key)){
			return cur;
		}
		return searchHelper(cur.next, key);
	}
} //END OF LL_Recursive CLASS


///////////////////////////////////////////////////////////////////////////////////////////////////

class Node<T>
{ T data;
  Node<T> next;
  Node() { this( null, null ); }
  Node(T data){this( data, null ); }
  Node(T data, Node<T> next) { this.data=data; this.next=next; }
  public String toString() { return ""+data; }
} //END OF NODE CLASS