import java.io.*;
import java.util.*;
// DO NOT IMPORT ANYTHING ELSE
// NO PACKAGE STATMENTS 
// NO OVERRIDE STATMENTS 

public class ComplexSwamp
{
	static int[][] swamp;  // NOW YOU DON'T HAVE PASS THE REF IN/OUT METHODS

 	public static void main(String[] args) throws Exception
	{
		int[] dropInPt = new int[2]; // row and col will be on the 2nd line of input file;
		swamp = loadSwamp( args[0], dropInPt );
		int row=dropInPt[0], col = dropInPt[1];
		String path = ""; // with each step grows to => "[2,3][3,4][3,5][4,6]" etc
		dfs( row, col, path );
		//printSwamp(path, swamp);
	} // END MAIN

 	// JUST FOR YOUR DEBUGGING - DELETE THIS METHOD AND ITS CALL BEFORE HANDIN
	// ----------------------------------------------------------------
	/*private static void printSwamp(String label, int[][] swamp )
	{
		System.out.println( label );
		System.out.print("   ");
		for(int c = 0; c < swamp.length; c++)
			System.out.print( c + " " ) ;
		System.out.print( "\n   ");
		for(int c = 0; c < swamp.length; c++)
			System.out.print("- ");
		System.out.print( "\n");

		for(int r = 0; r < swamp.length; r++)
		{	System.out.print( r + "| ");
			for(int c = 0; c < swamp[r].length; c++)
				System.out.print( swamp[r][c] + " ");
			System.out.println("|");
		}
		System.out.print( "   ");
		for(int c = 0; c < swamp.length; c++)
			System.out.print("- ");
		System.out.print( "\n");
	}*/
	// --YOU-- WRITE THIS METHOD 
	// (you may copy from YOURSELF from YOUR lab7 not someone else's)
   	// ----------------------------------------------------------------
	private static int[][] loadSwamp( String infileName, int[] dropInPt  ) throws Exception
	{
		// open infile with Scanner
		Scanner infile = new Scanner(new BufferedReader( new FileReader( infileName )) );
		int dimension = infile.nextInt();
		swamp = new int[dimension][dimension];
		// read in the next two numbers with two calls to .nextInt()
		// store into origin[0] and origin[1]
		dropInPt[0] = infile.nextInt();
		dropInPt[1] = infile.nextInt();
		// System.out.println("dimension= "+ dimension);
		// System.out.println("origin[0]= "+ origin[0]);
		// System.out.println("origin[1]= "+ origin[1]);
		// now use a nested loop outer = row from 0 to dimension-1
		//	 inner loop = col from 0 to dimension-1
		for(int row=0; row<(dimension); row++){
			//System.out.println("SWAMP: row:" + row);
			for(int col=0; col<(dimension); col++){
	//		 store each .nextInt() into theSwamp[row][col]
				swamp[row][col]= infile.nextInt();
				//System.out.println("col:" + col + ": Value: " + theSwamp[row][col]);
			}
		}
		//printSwamp(infileName,swamp);
		return swamp;
	}

	static boolean onEdge( int r, int c ) // RET TRUE IF ON EDGE OF SWAMP
	{
		return r==0 || r==swamp.length-1 || c ==0 || c==swamp.length-1;
	}

	static void dfs( int row, int col, String path ) // dfs = DEPTH FIRST SEARCH
	{
		// IMPLEMENT THE DFS ALGORITHM IN HERE
		path += "[" + row + "," + col + "] ";
		// System.out.println(path + " ROW: " + row +" COL: "+ col);
		// printSwamp(path, swamp);
		if (onEdge(row, col)) {
			System.out.println(path);
			return;
		}
		if (swamp[row - 1][col] == 1) // TRY NORTH
		{
			swamp[row][col] = -1; // mark the cell im ON RIGHT NOW as "been here already dont wanna come back"
			// System.out.println("set ROW: " + row +" COL: "+ col + " to -1");
			dfs(row - 1, col, path);
			// swamp[row-1][col] = -1;
			swamp[row][col] = 1;
			// System.out.println("unmarking ROW: " + row +" COL: "+ col + " to 1");
		}
		if (swamp[row - 1][col + 1] == 1)// else test NE cell
		{
			swamp[row][col] = -1; // mark the cell im ON RIGHT NOW as "been here already dont wanna come back"
			// System.out.println("set ROW: " + row +" COL: "+ col + " to -1");
			dfs(row - 1, col + 1, path);
			// swamp[row-1][col+1] = -1;
			swamp[row][col] = 1; // unmark
			// System.out.println("unmarking ROW: " + row +" COL: "+ col + " to 1");
		}
		if (swamp[row][col + 1] == 1) // else test E cell
		{
			swamp[row][col] = -1; // mark the cell im ON RIGHT NOW as "been here already dont wanna come back"
			// System.out.println("set ROW: " + row +" COL: "+ col + " to -1");
			dfs(row, col + 1, path);
			// swamp[row][col+1] = -1;
			swamp[row][col] = 1; // unmark
			// System.out.println("unmarking ROW: " + row +" COL: "+ col + " to 1");
		}
		if (swamp[row + 1][col + 1] == 1)// else test SE cell
		{
			swamp[row][col] = -1; // mark the cell im ON RIGHT NOW as "been here already dont wanna come back"
			// System.out.println("set ROW: " + row +" COL: "+ col + " to -1");
			dfs(row + 1, col + 1, path);
			// swamp[row+1][col+1] = -1;
			swamp[row][col] = 1; // unmark
			// System.out.println("unmarking ROW: " + row +" COL: "+ col + " to 1");
		}
		if (swamp[row + 1][col] == 1)// else test S cell
		{
			swamp[row][col] = -1; // mark the cell im ON RIGHT NOW as "been here already dont wanna come back"
			// System.out.println("set ROW: " + row +" COL: "+ col + " to -1");
			dfs(row + 1, col, path);
			// swamp[row+1][col] = -1;
			swamp[row][col] = 1; // unmark
			// System.out.println("unmarking ROW: " + row +" COL: "+ col + " to 1");
		}
		if (swamp[row + 1][col - 1] == 1)// else test SW cell
		{
			swamp[row][col] = -1;
			// System.out.println("set ROW: " + row +" COL: "+ col + " to -1");
			dfs(row + 1, col - 1, path);
			// swamp[row+1][col-1] = -1;
			swamp[row][col] = 1; // unmark
			// System.out.println("unmarking ROW: " + row +" COL: "+ col + " to 1");
		}
		if (swamp[row][col - 1] == 1)// else test W cell
		{
			swamp[row][col] = -1;
			// System.out.println("set ROW: " + row +" COL: "+ col + " to -1");
			dfs(row, col - 1, path);
			// swamp[row][col-1] = -1;
			swamp[row][col] = 1; // unmark
			// System.out.println("unmarking ROW: " + row +" COL: "+ col + " to 1");
		}
		if (swamp[row - 1][col - 1] == 1)// else test NW cell
		{
			swamp[row][col] = -1;
			// System.out.println("set ROW: " + row +" COL: "+ col + " to -1");
			dfs(row - 1, col - 1, path);
			// swamp[row-1][col-1] = -1;
			swamp[row][col] = 1; // unmark
			// System.out.println("unmarking ROW: " + row +" COL: "+ col + " to 1");
		}
	}
}


