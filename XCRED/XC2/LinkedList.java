// 2021 FALL CS 445 LAB #3  STUDENT STARTER FILE

import java.io.*;
import java.util.*;

public class LinkedList<T extends Comparable<T>>
{
	private Node<T> head;  // pointer to the front (first) element of the list
	private Node<T> tail;  // pointer to the last elem of the list ( caboose or tail node)

	public LinkedList()
	{
		head = null; // compiler does this anyway. just for emphasis
		tail = head;
	}

	// USE THE TOSTRING AS OUR PRINT
	public String toString()
	{
		String toString = "";

		for (Node<T> curr = head; curr != null; curr = curr.next)
		{
			toString += curr.data;		// WE ASSUME OUR T TYPE HAS toString() DEFINED
			if (curr.next != null)
				toString += " -> ";
		}

		return (String) (toString + " ");
	}
	
	// ########################## Y O U   W R I T E   T H E S E    M E T H O D S  
	// . . .AND ANY SUPPORTING METHODS YOU NEED FOR THEM 
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

	public void insertAtFront(T data)
	{
		head = new Node<T>(data,head);
	}

	// THIS VERSION JUST LOADS THE LISTS FROM THE FILE BEFORE THEY ARE MERGED
	public void insertAtTail( T data )
	{
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

		/*
		 * 1. iterate through each list while comparing each node
		 * add the nodes to new list in lexical order
		 * must be done in one iteration of both lists together
		 * create 2 pointers and advance in the same loop?
		*/
		public LinkedList<T> merge( LinkedList<T> other )  // think 'sorted union' but only 1 pass allowed
	{
			
		LinkedList<T> union = new LinkedList<T>();
		LinkedList<T> loopLL = null;
		LinkedList<T> secLL = null;
		if(this.size() > other.size()){
			loopLL = this;
			secLL = other;
		}else{
			loopLL = other;
			secLL = this;
		}
		Node<T> list1Node = loopLL.head;
		Node<T> list2Node = secLL.head;
		Node<T> temp = union.head;
		Node<T> cur=null;
		
		while(list1Node!=null){
			if(list2Node != null){
				if (list1Node.data.compareTo(list2Node.data) < 0) {
					cur = new Node<T>(list1Node.data, null);
					list1Node = list1Node.next;
				} else {
					cur = new Node<T>(list2Node.data, null);
					list2Node = list2Node.next;
				}				
			}else{
				cur = new Node<T>(list1Node.data, null);
				list1Node = list1Node.next;				
			}	
			if(temp == null){
				union.head = new Node<T>(cur.data, union.head);
				temp =union.head;
			}else{		
            	temp.next= new Node<T>(cur.data, null);
				temp =temp.next;
			}
		}
		return union;
	} 

} //END OF LINKEDLIST CLASS DEFINITION

// NODE CLASS
 class Node<T>
{
  T data;
  Node<T> next;

  Node(T data)
  {
    this( data, null );
  }
  Node(T data, Node<T> next)
  {
    this.data = data; 
    this.next = next; 
  }

  public String toString()
  {
	  return "" + data; // stringify the data
  } 
	 
} //EOF