import java.io.*;
import java.util.*;

public class L2_SetOps
{
	public static void main( String args[] ) throws Exception
	{
		BufferedReader infile1 = new BufferedReader( new FileReader( args[0] ) );
		BufferedReader infile2 = new BufferedReader( new FileReader( args[1] ) );

		String[] set1 = loadSet( infile1 );
		Arrays.sort( set1 );
		String[] set2 = loadSet( infile2 );
		Arrays.sort( set2 );
		printSet( "set1: ",set1 );
		printSet( "set2: ",set2 );

		String[] union = union( set1, set2 );
		Arrays.sort( union );
		printSet( "\nunion: ", union );

		String[] intersection = intersection( set1, set2 );
		Arrays.sort( intersection );
		printSet( "\nintersection: ",intersection );

		String[] difference = difference( set1, set2 );
		Arrays.sort( difference );
		printSet( "\ndifference: ",difference );

		String[] xor = xor( set1, set2 );
		Arrays.sort( xor );
		printSet("\nxor: ", xor );

		System.out.println( "\nSets Echoed after operations.");

		printSet( "set1: ", set1 );
		printSet( "set2: ", set2 );

	}// END MAIN

	// USE AS GIVEN - DO NOT MODIFY
	// CAVEAT: This method will not work *correctly* until you write a working doubleLength() method.

	static String[] loadSet( BufferedReader infile ) throws Exception
	{
		final int INITIAL_LENGTH = 5;
		int count=0;
		String[] set = new String[INITIAL_LENGTH];
		while( infile.ready() )
		{
				if (count >= set.length){
					set = doubleLength( set );
				}
				set[ count++ ] = infile.readLine();	
		}
		infile.close();
		return trimArray( set, count );
	}

	// USE AS GIVEN - DO NOT MODIFY
	static void printSet( String caption, String [] set )
	{
		System.out.print( caption );
		for ( String s : set )
			System.out.print( s + " " );
		System.out.println();
	}


	/* ###############################################################
		For each of the following set operations you must execute the following steps:
		1) dimension an array that is just big enough to handle the largest possible set for that operation.
		2) add the appropriate elements to the array as the operation prescribes.
		3) before returning the array, resize it to the exact size as the number of elements in it.
	*/

	static boolean contains( String[] set, String elem ){
		for(int i=0; i<set.length; i++){
			//loop through and see if element in unionResult is also in set 2
			// if they are same return true
			if(set[i].equals(elem)){
				return true;
			}
		}
		return false; 
	}

	static String[] union( String[] set1, String[] set2 )
	{
		String[] unionResult = new String[set1.length + set2.length];
		int index=0;
		for(int i=0; i<set1.length; i++){
			unionResult[index++]=set1[i];
		}
		/*
		 * for loop-> loop through set2
		 * implement contains()
		 * if true, do not add to unionReturn
		 * if false, add to unionReturn
		 */
		for(int i=0; i<set2.length; i++){
			if(!contains(set1, set2[i])){
				unionResult[index++]=set2[i];
			}
		}
		unionResult = trimArray(unionResult, index);
		//System.out.println("unionResult: " + Arrays.toString(unionResult));
		return unionResult;
	}

	static String[] intersection( String[] set1, String[] set2 )
	{
		String[] intersectionResult;
		int index=0;
		if(set1.length<=set2.length){
		intersectionResult = new String[set1.length];
		for(int i=0; i<intersectionResult.length; i++){
			if(contains(set2, set1[i])){
				intersectionResult[index++]=set1[i];
			}
		}
		}else{
			intersectionResult = new String[set2.length];
			for(int i=0; i<intersectionResult.length; i++){
				if(contains(set1, set2[i])){
					intersectionResult[index++]=set2[i];
				}
			}
		}
		intersectionResult= trimArray(intersectionResult, index);
		return intersectionResult; 
	}

	static String[] difference(String[] set1, String[] set2) {
		String[] diffResult;
		int index = 0;
		diffResult = new String[set1.length];
		for (int i = 0; i < diffResult.length; i++) {
			if (!contains(set2, set1[i])) {
				diffResult[index++] = set1[i];
			}
		}
		diffResult = trimArray(diffResult, index);
		return diffResult;
	}

	//NO LOOPS FOR XOR
	static String[] xor( String[] set1, String[] set2 )
	{
		return union(difference(set1, set2), difference(set2, set1)); // change this to return a trimmed full array
	}

	// return an array of length 2x with all data from the old array stored in the new array
	static String[] doubleLength( String[] old )
	{
		//System.out.println("old.length= "+ old.length + "\n old values= "+ Arrays.toString(old) );
		String[] dblLength = new String[old.length * 2];
		for (int i=0; i<old.length; i++){
			dblLength[i] = old[i];
		}
		//System.out.println("dblLength.length= "+ dblLength.length + "\n dblLength values= "+ Arrays.toString(dblLength));
		return dblLength; // you change accordingly
	}

	// return an array of length==count with all data from the old array stored in the new array
	static String[] trimArray( String[] old, int count )
	{
		//System.out.println("old.length= "+ old.length + "\n old values= "+ Arrays.toString(old) );
		String[] trimArr = new String[count];
		for (int i=0; i<count; i++){
			trimArr[i] = old[i];
		}
		//System.out.println("trimArr.length= "+ trimArr.length + "\n trimArr values= "+ Arrays.toString(trimArr));
		return trimArr; // you change accordingly
	}

} // END CLASS