/* This class was borrowed and modified as needed with permission from it's original author
   Mark Stelhik ( http:///www.cs.cmu.edu/~mjs ).  You can find Mark's original presentation of
   this material in the links to his S-01 15111,  and F-01 15113 courses on his home page.
*/

import java.io.*;
import java.util.*;

public class Graph 
{
	private final int NO_EDGE = -1; // all real edges are positive
	private int G[][];              // will point to a 2D array to hold our graph data

	private int numEdges;
	public Graph( String graphFileName ) throws Exception  // since readFild doesn't handle them either
	{
		loadGraphFile( graphFileName );
		// System.out.println("OUT-->"+outDegree(2));//<--TO TEST OUTDEGREE()
		// System.out.println("IN-->"+inDegree(2));//<--TO TEST INDEGREE()
		// System.out.println("DEGREE-->"+degree(2));//<--TO TEST DEGREE()
		
//		T E M P O R A R Y    C O D E    T O    V E R I F Y    P R I V A T E 
// 		M E T H O D S    W E    C A N T    C A L L    F R O M   T E S T E R 
//		      ........R E M O V E   A F T E R   T E S T I N G .......
/*
		for (int node = 0 ; node < G.length ; ++node )
		{
			System.out.format( "DEBUG:: in (%d)=%d  ",node,inDegree(node) );
			System.out.format( "out(%d)=%d  ",node,outDegree(node) );
			System.out.format( "deg(%d)=%d\n",node,degree(node) );
		}
*/
	}

	///////////////////////////////////// LOAD GRAPH FILE //////////////////////////////////////////
	//
	// FIRST NUMBER IN GRAPH FILE IS THE SQUARE DIMENSION OF OUR 2D ARRAY
	// THE REST OF THE LINES EACH CONTAIN A TRIPLET <ROW,COL,WEIGHT> REPRESENTING AN EDGE IN THE GRAPH

	private void loadGraphFile( String graphFileName ) throws Exception
	{
		Scanner graphFile = new Scanner( new File( graphFileName ) );

		int dimension = graphFile.nextInt();   	// THE OF OUR N x N GRAPH
		G = new int[dimension][dimension]; 		// N x N ARRAY OF ZEROS
		numEdges=0;
		

		// WRITE A LOOP THAT PUTS NO_EDGE VALUE(-1) EVERYWHERE EXCPT ON THE DIAGONAL(0)
		for (int row = 0; row < dimension; row++) {
			for (int col = 0; col < dimension; col++) {
				if (row == col) {
					G[row][col] = 0;
				} else {
					G[row][col] = -1;
				}
			}
		}

		// NOW LOOP THRU THE FILE READING EACH TRIPLET row col weight FROM THE LINE
		// USE row & col AS WHERE TO STORE THE weight
		// i.e. g[row][col] = w;

		while ( graphFile.hasNextInt() )
		{
			// read in the row,col,weight // that eat us this line
			// call add edge
		int r = graphFile.nextInt();
		int c = graphFile.nextInt();
		int w = graphFile.nextInt();
			G[r][c] = w;
			addEdge(r, c, w);
		}
	} // END readGraphFile

	private void addEdge( int r, int c, int w )
	{
		G[r][c] = w;
		++numEdges; // only this method adds edges so we do increment counter here only
	}
	
  private boolean hasEdge(int fromNode, int toNode)
  {
    return false; 
  }

	// IN DEGREE IS NUMBER OF ROADS INTO THIS CITY
	// NODE IS THE COL#. IN DEGREE IS HOW MANY POSITIVE NUMBERS IN THAT COL
	private int inDegree(int node)
	{
		int in=0;
		for(int i=0; i<G.length; i++){
			if(G[i][node]>0){
				in++;
			}
		}
		return in;	
	}

	// OUT DEGREE IS NUMBER OF ROADS OUT OF THIS CITY
	// NODE IS THE ROW #. OUT DEGREE IS HOW MANY POSITIVE NUMBERS IN THAT ROW
	private int outDegree(int node)
	{
		int out=0;
		for(int i=0; i<G.length; i++){
			if(G[node][i]>0){
				out++;
			}
		}
		return out;	
	}

	// DEGREE IS TOTAL NUMBER OF ROAD BOTH IN AND OUT OF THE CITY 
	private int degree(int node)
	{
		int degree = inDegree(node)+outDegree(node);
		return degree;
	}

	// PUBLIC METHODS 
	
	public int maxOutDegree()
	{
		int maxOut=0;
		for(int i=0; i<G.length; i++){
			if( outDegree(i)>maxOut){
				maxOut = outDegree(i);
			}
			
		}
		return maxOut;
	}

	public int maxInDegree()
	{
		int maxIn=0;
		for(int i=0; i<G.length; i++){
			if( inDegree(i)>maxIn){
				maxIn = inDegree(i);
			}
			
		}
		return maxIn;
	}

	public int minOutDegree()
	{
		int minOut=maxOutDegree();
		for(int i=0; i<G.length; i++){
			if( outDegree(i)<minOut){
				minOut = outDegree(i);
			}
			
		}
		return minOut;
	}
	public int minInDegree()
	{
		int minIn=maxInDegree();
		for(int i=0; i<G.length; i++){
			if( inDegree(i)<minIn){
				minIn = inDegree(i);
			}
			
		}
		return minIn;
	}	
	
	public int maxDegree()
	{
		int maxDeg= 0;
		for(int i=0; i<G.length;i++){
			if(degree(i)>maxDeg){
				maxDeg=degree(i);
			}
		}
		return maxDeg;
	}

	public int minDegree()
	{
		int minDeg= maxDegree();
		for(int i=0; i<G.length;i++){
			if(degree(i)<minDeg){
				minDeg=degree(i);
			}
		}
		return minDeg;
	}
	
	public void removeEdge(int fromNode, int toNode)
	{
		/* if caller passes in a row col pair that 
		   out of bound or has no edge there, you must 
		   throw and catch an exception. See my output.
		
		   if the edge is there then remove it by writing 
		   in a NO_EDGE value at that coordinate in the grid	
		*/
		try{
			G[fromNode][toNode]=-1;
		}catch(Exception e){
			System.out.println("java.lang.Exception: Non Existent Edge Exception: removeEdge("+fromNode+","+toNode+")");
			System.exit(0);
		}
	}
	
	// TOSTRING
	public String toString()
	{	String the2String = "";
		for (int r=0 ; r < G.length ;++r )
		{
			for ( int c=0 ; c < G[r].length ; ++c )
				the2String += String.format("%3s",G[r][c] + " ");
			the2String += "\n";
		}
		return the2String;
	} // END TOSTRING

} //EOF

class Edge
{
	int dest, weight;
	Edge next;
	Edge( int dest, int weight, Edge next )
	{
		this.dest = dest;
		this.weight = weight;
		this.next = next;
	}
}


