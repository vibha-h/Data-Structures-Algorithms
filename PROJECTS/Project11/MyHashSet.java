import java.io.*;
import java.util.*;

public class MyHashSet implements HS_Interface
{	private int numBuckets; // changes over life of the hashset due to resizing the array
	private Node[] bucketArray;
	private int size; // total # keys stored in set right now

	// THIS IS A TYPICAL AVERAGE BUCKET SIZE. IF YOU GET A LOT BIGGER THEN YOU ARE MOVING AWAY FROM (1)
	private final int MAX_ACCEPTABLE_AVE_BUCKET_SIZE = 20;  // **DO NOT CHANGE THIS NUMBER**

	public MyHashSet( int numBuckets )
	{	size=0;
		this.numBuckets = numBuckets;
		bucketArray = new Node[numBuckets]; // array of linked lists
		System.out.format("IN CONSTRUCTOR: INITIAL TABLE LENGTH=%d RESIZE WILL OCCUR EVERY TIME AVE BUCKET LENGTH EXCEEDS %d\n", numBuckets, MAX_ACCEPTABLE_AVE_BUCKET_SIZE );
	}

	private int hash( String key){
		int total = 0;
		int sum=0;
        int length = key.length();
        for(int i = 0; i < length; i++)
        {
            char ch = key.charAt(i);
            int ascii = (int) ch;
            sum = sum*17 + ascii;
            sum%=numBuckets;
        }
        return sum;
		// char[] arr = key.toCharArray();
        // int hash = 0;
        // for ( int i = 0; i < arr.length; i++ )
        //     hash = arr[i] + ((hash << 5) - hash);
        // return Math.abs(hash) % numBuckets;
	}

	private void insertAtFront(String key, Node curr){
		Node prev = curr;
		curr = new Node(key, prev);
	}

	public boolean add( String key )
	{
		// your code here to add the key to the table and ++ your size variable
		//hash and add to array
		//if size is too big then call upsize

		//hash key first
		if ( !contains(key) )
        {
            int hash = hash(key);
            Node curr = bucketArray[hash];
            Node prev = null;
            bucketArray[hash] = new Node(key, curr);
			//System.out.println("OLD:" + newNode.data + " next= " +newNode.next);
            // if ( curr == null )
            //     bucketArray[hash] = newNode;
            // else
            // {
            //     // while ( curr != null )
            //     // {
            //     //     prev = curr;
            //     //     curr = curr.next;
            //     // }
            //     // prev.next = newNode;

			// 	insertAtFront(key, curr);
			// 	//System.out.println("NEW:" + newNode.data+ " next= " + prev.data);
            // }
            size++;
            if ( size > MAX_ACCEPTABLE_AVE_BUCKET_SIZE * this.numBuckets )
			upSize_ReHash_AllKeys();
            return true;
        }
        return false;
	}
	public boolean contains( String key )
	{	
		int hash = hash(key);
        Node curr = bucketArray[hash];
        while ( curr != null )
        {
            if ( curr.data.equals(key) )
            {
                return true;
            }
            curr = curr.next;
        }
        return false; // just to make it compile.
		// You hash this key. goto that list. look for this key in that list
		
	}

	private void upSize_ReHash_AllKeys()
	{	System.out.format("KEYS HASHED=%10d UPSIZING TABLE FROM %8d to %8d REHASHING ALL KEYS\n",
						   size, bucketArray.length, bucketArray.length*2  );
		//size=0;
		Node[] biggerArray = new Node[ bucketArray.length * 2 ];
		this.numBuckets = biggerArray.length;
		//Node[] old = bucketArray;
		

/*		FOR EACH LIST IN THE ARRAY
			FOR EACH NODE IN THAT LIST
				HASH THAT KEY INTO THE BIGGER TABLE
				BE SURE TO USE THE BIGGER .LENGTH AS THE MODULUS
*/
	//System.out.println("SIZE BEFORE: " + size);

	for (int i = 0; i < bucketArray.length; i++) {
		Node curr = bucketArray[i];
		
		// biggerArray[hash] = curr;
		while (curr != null) {
			int hash = hash(curr.data);
			biggerArray[hash] = new Node(curr.data,biggerArray[hash]);//insertAtFront(curr.data, biggerArray[hash]);//(curr.data);
			curr = curr.next;
		}
	}
	bucketArray = biggerArray;
	//System.out.println("SIZE AFTER: " + size);
	} // END OF UPSIZE & REHASH

	public boolean remove( String key )
	{
		//check does it contain key 
		//if contains, remove and return true
		int hash = hash(key);
        Node curr = bucketArray[hash];
        Node prev = null;
        while ( curr != null )
        {
            if ( curr.data.equals(key) )
            {
                if ( prev == null )
                    bucketArray[hash] = curr.next;
                else
                    prev.next = curr.next;
                size--;
                return true;
            }
            prev = curr;
            curr = curr.next;
        }
        return false;
	}
	public boolean isEmpty()
	{
		return size==0;
	}
	public void clear()
	{
		for ( int i = 0; i < bucketArray.length; i++ )
		bucketArray[i] = null;
		size = 0;
	}
	public int size()
	{
		return size;
	}	
	
} //END MyHashSet CLASS

class Node
{	String data;
	Node next;
	public Node ( String data, Node next )
	{ 	this.data = data;
		this.next = next;
	}
}



