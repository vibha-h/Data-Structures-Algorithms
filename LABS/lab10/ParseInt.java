import java.util.*;
import java.io.*;

public class ParseInt // USER MUST ENTER STRING ON CMD LINE AS KEY TO HASH
{
	public static void main( String[] args )
	{
		System.out.format("horner converts '%s' -> %d\n", args[0], horner(args[0]) );
	}

	static int horner( String s ) // FYI: HORNER IS BASIS FOR PARSEINT FUNCTION
	{
		int total = 0;
		for( int i=0 ; i<s.length() ; ++i )
		{
			total *= 10;
			int asciiVal = s.charAt(i) - '0'; // converts char: '7' to int: 7
			total += asciiVal;
		}
		return total;
	} // END HORNER
} // EMD MAIN

