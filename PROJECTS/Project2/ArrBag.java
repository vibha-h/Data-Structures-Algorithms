//  2022 SPRING CS-445 STUDENT STARTER FILE FOR ARRBAG PROJECT #2

/*	ANY TIME YOU CAST ARRAY OF OBJECT TO ARRAY OF <T>
    YOU MUST SUPPESS THE WARNING. PUT SUPPRESSION STATEMENT RIGHT BEFORE THE METHOD
    DONT PLACE IT THE METHOD - EVEN THOUGH THE LINE THAT THROWS THE WARNING IS IN THE METHOD
    THE SUPRESSION BELONGS *BEFORE* THE METHOD. TIGHT ON IT, BEFORE IT. 
*/

import java.io.*;
import java.util.*;

public class ArrBag<T>
{
	final int NOT_FOUND = -1;
	final int INITIAL_CAPACITY = 1;
	private int count; // LOGICAL SIZE
	T[] theArray; 

	// DEFAULT C'TOR
	@SuppressWarnings("unchecked") // SUPRESSION GOES **HERE** BEFORE METHOD
	public ArrBag()
	{	theArray = (T[]) new Object[INITIAL_CAPACITY]; // SUPPRESSION DOES NOT BELONG INSIDE THE METHOD
		count = 0; // i.e. logical size, actual number of elems in the array
	}

	// SPECIFIC INITIAL LENGTH VERSION OF CONSTRUCTOR
	// only the union,intersect,diff call this version of constructor
	@SuppressWarnings("unchecked")
	public ArrBag( int optionalCapacity )
	{
		theArray = (T[]) new Object[optionalCapacity ]; // CASTING TO T[] (creates warning we have to suppress
		count = 0; // i.e. logical size, actual number of elems in the array
	}

	// C'TOR LOADS FROM FILE Of STRINGS
	@SuppressWarnings("unchecked")
	public ArrBag( String infileName ) throws Exception
	{
		count = 0; // i.e. logical size, actual number of elems in the array
		BufferedReader infile = new BufferedReader( new FileReader(  infileName ) );
		while ( infile.ready() )
			this.add( (T) infile.readLine() ); // Note the 'this.' is redundant !!
		infile.close();
	}

	// APPENDS NEW ELEM AT END OF LOGICAL ARRAY. UPSIZES FIRST IF NEEDED
	public boolean add( T element ) // THIS IS AN APPEND TO THE LOGICAL END OF THE ARRAY AT INDEX count
	{	if (element == null ) return false;
		if (size() == theArray.length) upSize(); // DOUBLES PHYSICAL CAPACITY
		theArray[ count++] = element; // ADD IS THE "setter"
		return true; // success. it was added
	}

	// INCR EVERY SUCESSFULL ADD, DECR EVERY SUCESSFUL REMOVE
	public int size()
	{
		return count;
	}

	// RETURNS TRUE IF THERE ARE NO ELEMENTS IN THE ARRAY, OTHERWISE FALSE
	public boolean isEmpty()
	{
		return false;
	}

	public T get( int index )
	{
		if ( index < 0 || index >=size() )
			die("FATAL ERROR IN .get() method. Index to uninitialized element or out of array bounds. Bogus index was: " + index + "\n" );
		return theArray[index];
	}

	// SEARCHES FOR THE KEY. TRUE IF FOUND, OTHERWISE FALSE
	public boolean contains(T key) {
		if (key == null)
			return false;
		for (int i = 0; i < size(); ++i) {			
			if (get(i).equals(key)) { // WE ARE MAKING AN ASSUMPTION ABOUT TYPE T... WHAT IS IT?
				//System.out.println("key= " + key + "\nget(i)= " + get(i));
				return true;
			}
		}
		return false;
	}

	void die( String errMsg )
	{
		System.out.println( errMsg );
		System.exit(0);
	}

	// --------------------------------------------------------------------------------------------
	// # # # # # # # # # # #   Y O U   W R I T E   T H E S E   M E T H O D S  # # # # # # # # # # #
	// --------------------------------------------------------------------------------------------

	// PERFORMS LOGICAL (SHALLOW) REMOVE OF ALL THE ELEMENTS IN THE ARRAY (SIMPLE 1 LINER!)
	public void clear()
	{
		count=0; // done!
	}

	// DOUBLE THE .length (capacity),OF UNDERLYING PHYSICAL ARRAY POINTED TO BY VAR theArray
	// we are not changing the size() i.e. count of actual elems in the array. JUST THE CAPACITY	

	@SuppressWarnings("unchecked")
	private void upSize()
	{
		// STEP1: copy in the line of the first constructor that dimensions [INITAL_CAPACITY]
		// then change the INITAL CAPACITY to 2 * the .length of this array
		T[] newArr = (T[]) new Object[theArray.length*2];
		// STEP2: copy every elemen from theArray (the full one) into the 2xBigger array
		for(int i=0; i<theArray.length; i++){
			newArr[i]=theArray[i];
		}
		// STEP3: overwrite  theArray with the ref value of the2xBigger array 
		theArray = newArr;
	}

	public String toString()
	{
		String toString  = ""; // YES YOU ARE ALLOWED TO NAME VAR SAME AS METHOD IT's IN
		// LOOP OVER THE ARRAY USING THE GET METHOD TO GET EACH ELEM
		// TACK THAT ELEM ONTO THE STRING TO BE RETURNED
		for(int i=0; i<this.size(); i++){
			toString+= new String((String)this.get(i)+" ");
		}
		return toString;
	}


	// RETURNS A THIRD ARRBAG CONTAINING ONLY ONE COPY (NO DUPES) OF ALL THE ELEMs FROM BOTH BAGS
	// DOES -NOT- MODIFY THIS OR OTHER BAG
	public ArrBag<T> union( ArrBag<T> other )
	{
		// IM GIVING THE FIRST LINE OF UNION AS FREEBIE. DO THE SAME FOR OTHER METHODS EXCEPT
		// THE INTIAL CAPACITY MAY BE DIFFERENT. MAKE INIT CAP AS SMALL AS POSSIBLE BUT JUST BIG
		// ENUF THAT U R SURE NO UPSZING COULD POSSIBLY BE NEEDED.

		ArrBag<T> unionResult = new ArrBag<T>( this.size() + other.size() ); // YES U CAN HAVE VAR NAMED SAME AS METHOD

		// YOUR CODE HERE TO LOOP ACROSS THIS & OTHER SET - AND PUT THE UNION OF THE
		// 2 SETS INTO THE RESULT SET TO BE RETURNED
		for(int i=0; i<this.size(); i++){
			unionResult.add(this.get(i));
		}
		for(int i=0; i<other.size(); ++i){
			if(!contains(other.get(i))){
			unionResult.add(other.get(i));
			}
		}
		return unionResult;
	}

	// RETURNS A THIRD ARRBAG CONTAIING ONLY ONE COPY (NO DUPES) OF ALL THE ElEMENTS IN COMMON
	// DOES -NOT- MODIFY THIS OR OTHER
	public ArrBag<T> intersection( ArrBag<T> other )
	{
		ArrBag<T> intersectionResult;
		ArrBag<T> loopArr = other;
		// YOUR LOOPS HERE
		if(this.size()<=other.size()){
			intersectionResult = new ArrBag<T>(this.size());			
		}else{
			intersectionResult = new ArrBag<T>(other.size());			
		}
		for(int i=0; i< loopArr.size(); i++){
			if(contains(loopArr.get(i))){
				intersectionResult.add(loopArr.get(i));
			}
		}

		return intersectionResult;
	}

	// RETURNS A THIRD ARRBAG CONTAINING ONLY ONE COPY (NO DUPES) OF ALL THE ELEMENTS
	// REMAINING AFTER THIS BAG - OTHER BAG
	// DOES -NOT- MODIFY THIS OR OTHER
	public ArrBag<T> difference( ArrBag<T> other )
	{
		ArrBag<T> diffResult = new ArrBag<T>(this.size()); 

		// YOUR LOOPS HERE
		for(int i=0; i<this.size(); i++){
			if(!other.contains(this.get(i))){ 
				diffResult.add(this.get(i));
			}
		}

		return diffResult;
	}

	// RETURNS A THIRD ARRBAG CONTAIING ONLY ONE COPY (NO DUPES) OF ALL THE ElEMENTS
	// CONTAINED IN THE UNION OF THIS AND OTHER - INTERSECTION OF THIS AND OTHER
	// DOES -NOT- MODIFY THE THIS OR OTHER
	public ArrBag<T> xor( ArrBag<T> other )
	{
		return this.union(other).difference(this.intersection(other)); 
	}
} // END ARRBAG CLASS