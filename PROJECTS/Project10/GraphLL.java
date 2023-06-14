import java.io.*;
import java.util.*;

public class GraphLL
{
	private final int NO_EDGE = -1; // all real edges are positive
	private  Edge[] G;              // every G[i] is the head of a linked list, i.e ref to an Edge 

	private int numEdges;
	public GraphLL( String graphFileName ) throws Exception  // since readFild doesn't handle them either
	{
		loadGraphFile( graphFileName );
	}

	///////////////////////////////////// LOAD GRAPH FILE //////////////////////////////////////////

	private void loadGraphFile( String graphFileName ) throws Exception
	{
		Scanner graphFile = new Scanner( new File( graphFileName ) );
		int numNodes = graphFile.nextInt();   
		G = new Edge[numNodes];
		numEdges=0;

		// NOW LOOP THRU THE FILE READING EACH TRIPLET row col weight FROM THE LINE
		// DO an insertAtFront using g[SRC] as the head 

		while ( graphFile.hasNextInt() )
		{	// read in the src, dest, weight
			// call addEdge
		int src = graphFile.nextInt();
		int dest = graphFile.nextInt();
		int weight = graphFile.nextInt();
		//System.out.println("src: "+ src +" dest: " +dest+" weight: "+weight);
			addEdge(src, dest, weight);
		}
	} // END readGraphFile

	// GO TO G[src] AND DO INSERTATFRONT ON THAT 'head' POINTER I.E. G[src]
	private void addEdge( int src, int dest, int weight )
	{
		//System.out.println("G.length "+G.length);		
		if(G[src]==null){
			G[src] = new Edge(dest, weight, null);			
		}else{ 
			Edge next = G[src];
			G[src] = new Edge(dest, weight,next);
		}
	}
	
	private boolean hasEdge(int src, int dest)
	{
		return false; // CHANGE/REMOVE/MOVE AS NEEDED
		// GOTO G[src] AND WALK THAT LINKED LIST LOOKING FOR A NODE (EDGE) WITH DEST
	}

	private int inDegree(int dest) // # of roads(edges) entering this city (node)
	{	// THE HARDER ONE
		int inDeg = 0;
		// WALK ALL THE LISTS COUNTING THE NODE HAVING THIS DEST
		for (int i = 0; i < G.length; i++)
		{
			Edge e = G[i];
			while( e != null){
				if(e.dest == dest){
					inDeg++;
				}
				e = e.next;
			}
		}
		return inDeg;
	}

	private int outDegree(int src) // # of roads(edges) leaving this city (src node #)
	{	// THE EASIER ONE
		int outDeg=0;
		// JUST RETURN THE LENGTH OF THIS LIST AT G[src]
		Edge e = G[src];
			while( e != null){
				e = e.next;
				outDeg++;
			}
		return outDeg;	
	}

	private int degree(int node) // indegree + outdegree of this node #
	{
		int degree = inDegree(node)+outDegree(node);
		return degree;
	}

	// PUBLIC METHODS. THIS CODE COPIED FROM THE GRAPH2D LAB AND USED AS IS. SINCE IT IS NOT
	// DEPENDENT ON UNDERLYING DATA STRUCTURE AND RELIES ONLY ON CALLING THE 3 DEGREE FUNCTIONS
	
	public int maxOutDegree()
	{
		int maxOutDegree = 0; // ASSUM 1STNODE HAS LARGEST OUTDEG
		// LOOP OVER ALL NODES CALLING THE OUTDEG OF THAT NODE- RMEMBER THE LARGEST OUTDEG
		for(int i=0; i<G.length; i++){
			int out= outDegree(i);
			if(out>maxOutDegree){
				maxOutDegree=out;
			}
		}
		return maxOutDegree;
	}

	public int maxInDegree()
	{
		int maxInDegree = 0; // ASSUM 1STNODE HAS LARGEST OUTDEG
		// SAME AS ABOVE BUT CALLING ALL INS AND REMEMBERING  LARGEST INDEG
		for(int i=0; i<G.length; i++){
			int in= inDegree(i);
			if(in>maxInDegree){
				maxInDegree=in;
			}
		}
		return maxInDegree;
	}

	public int minOutDegree()
	{
		int minOutDegree = maxOutDegree(); // ASSUM 1STNODE HAS SMALLES OUTDEG
		// SAME PATTERN AS ABOVE 
		for(int i=0; i<G.length; i++){
			int out= outDegree(i);
			if(out< minOutDegree){
				minOutDegree=out;
			}
		}
		return minOutDegree;
	}

	public int minInDegree()
	{
		int minInDegree = maxInDegree(); // ASSUM 1STNODE HAS LARGEST OUTDEG
		// SAME PATTERN 
		for(int i=0; i<G.length; i++){
			int in= inDegree(i);
			if(in<minInDegree){
				minInDegree=in;
			}
		}
	
		return minInDegree;
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
	
	public void removeEdge(int src, int dest) { 
		// ITS AN OLD FASHIONED FIND & REMOVE NODE ON A 1 WAY LINKED LIST
		// IF THE LIST AT G[src] IS NULL -OR- SRC OR DEST OUT OF BOUNDS
		// THROW AND CATCH AN EXCEPTION - SEE OUTPUT

		// USE A BASE CASE TEST FOR 1ST NODE BEGIN THE ONE
		// WALK A CURR UP TO THE PRED OF THE DEADNODE
		// REMOVE THE NODE (IF ITS THERE)
		try {

			if (G[src].next != null) {

				Edge prev = null;

				Edge n = G[src];

				while (n != null) {
					if (n.dest == dest) {
						if (prev == null) {
							n = n.next;
						} else {
							prev.next = n.next;
						}
					}
					prev = n;
					n = prev.next;
				}
				if (n == null) {
					prev.next = null;
				}
			} else if (G[src].dest == dest) {
				G[src] = null;
			}
		} catch (Exception e) {
			System.out
					.println("java.lang.Exception: Non Existent Edge Exception: removeEdge(" + src + "," + dest + ")");
		}
		// ITS NOT THERE THROW AND CATCH AN EXCEPTION (SEE OUTPUT)

	} // E N D R E M O V E D G E

	// TOSTRING
	public String toString()
	{
		String the2String = "";	
		// SEE OUTPUT
		for (int i = 0; i < G.length; i++)
		{
			Edge e = G[i];
			the2String += i+":";	
			while( e != null){
				the2String += " -> ["+e.dest + "," + e.weight + "]";
				e = e.next;
			}
			the2String+="\n";
		}
		return the2String;
	} // END TOSTRING
} // E N D    G R A P H L L    C L A S S

// - - - - Y O U   M U S T   F I L L   I N   T H E   E D G E  (think 'Node')  C L A S S  - - - -
// NOTHING PUBLIC. LET IF DEFAULT TO PACKAGE/PRIVATE LIKE WE DID IN OTHER LL ASSIGNMENTS
// SO THAT YOU DONT HAVE TO CALL SETTERS AND GETTERS IN YOUR GRAPHLL CODE.

class Edge 
{
	// DEFINE dest,weight,nextInt
	// DEFINE FULL CONSTRUCTOR
	int dest, weight;
	Edge next;
	Edge( int dest, int weight, Edge next )
	{
		this.dest = dest;
		this.weight = weight;
		this.next = next;
	}
}


