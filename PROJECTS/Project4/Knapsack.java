import java.io.*;

public class Knapsack
{
	public static void main( String[] args ) throws Exception 
	{
		BufferedReader infile1 = new BufferedReader(new FileReader(args[0])); // takes input-n.txt as argument
		int[] set = new int[16]; // hardcode size to 16 bc every set has length 16
		String input = infile1.readLine(); //reads first line of file
		int sum = Integer.parseInt(infile1.readLine()); // reads and parses as int the second line of file which is the sum which each subset should add to
		String[] set1 = input.split(" "); // splits the first line wherever there is a space and puts each value into string array
									
		for (int i = 0; i < set1.length; i++) { // loops through string array and parses each index to int and puts it into int array set
												
			set[i] = Integer.parseInt(set1[i]);

		}
		infile1.close();
		int calcSum = 0;
		int[] subset = null;

		for (int i = 0; i < 65536; ++i) // i < (2^16) = i < 65536
		{
			String bitmap = toBitString(i, set.length);
			if (calcSum == sum) { // if sum of values in the subset is same as desired sum, subset will be printed
				for (int a = 0; a < subset.length; ++a) { // prints out only non-zero values of subset
					if (subset[a] != 0) {
						System.out.print(subset[a] + " ");
					}
				}
				System.out.println("\r");
			}
			calcSum = 0;
			subset = new int[set.length];
			int j = 0;
			for (int bindx = 0; bindx < set.length; ++bindx) {// generates all possible subsets
				if (bitmap.charAt(bindx) == '1') {
					subset[j] = set[bindx];
					j++;
					calcSum += set[bindx];
				} else {
				}
			}
		}
	} // END MAIN

	// i.e number 31 converted to a width of 5 bits = "11111"
	//     nuumber 7 converted to a width of 5 bits = "00111"
	static String toBitString( int number, int width )
	{
		String bitstring = "";
		while (number > 0)
		{	if (number % 2 == 0)
				bitstring = "0" + bitstring;
			else
				bitstring = "1" + bitstring;
			number /= 2 ;
		}
		while ( bitstring.length() < width )
				bitstring = "0" + bitstring;
		return bitstring;
	}
} // END CLASS