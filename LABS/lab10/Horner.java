
import java.util.*;
import java.io.*;

public class Horner // USER MUST ENTER STRING ON CMD LINE AS KEY TO HASH
{
	static int arrayLength = 0;
	static int base = 0;

	public static void main( String[] args )
	{
		if ( args.length < 3 )
		{	System.out.println("\n usage: $ java Horner arrayLength base key (i.e. 10000 256 foobar)\n" );
			System.exit(0);
		}
		arrayLength = Integer.parseInt( args[0] );
		base = Integer.parseInt( args[1] );
		String key = args[2];

		System.out.format("\n   your array length is: %d  your base is: %d your key is: '%s'\n\n",arrayLength, base, key );

		int hashCode = horner( key );
		System.out.format("\n   horner converted key: '%s' to index [%d] in range [0..%d]\n",key,hashCode,arrayLength-1);
	}

	static int horner( String key ) // FYI: HORNER IS BASIS FOR PARSEINT FUNCTION
	{
		int total = 0;
		for( int i=0 ; i<key.length() ; ++i )
		{
			// '\0' is  null char its asciiValue is zero. Thus (char - null) = the asciiValue of that char
			int	asciiValue = key.charAt(i) - '\0'; // '\0' is  null char its asciiValue is zero.
			int weightedValue = asciiValue * (int)Math.pow( 26, key.length()-(1+i) ); // base 26 assumes only always lower case a..z
			//String wv = String.format( "
			total = total + weightedValue;
			System.out.format( "   '%c' = %3d * (%d^%d) = %-12d  total = %d",
			                   key.charAt(i),asciiValue, base, key.length()-(1+i), weightedValue, total );
			if ( i<key.length()-1 )
				System.out.println("\n+");
			else
				System.out.println();
		}

		return Math.abs(total) % arrayLength;

	} // END HORNER

} // EMD MAIN

