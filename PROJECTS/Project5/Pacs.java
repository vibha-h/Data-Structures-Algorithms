import java.io.*;
import java.util.*;

public class Pacs
{	public static void main( String args[] ) throws Exception
	{	BufferedReader memberToPacsFile = new BufferedReader(new FileReader( "member2Pacs.txt"));
		BufferedReader AllPacsFile= new BufferedReader(new FileReader("allPacs.txt"));
		TreeSet<String> allPacs= new TreeSet<String>();
		while( AllPacsFile.ready()){
			allPacs.add(AllPacsFile.readLine());
		}			
		
		TreeMap<String, TreeSet<String>> pacToMembers = new TreeMap<String, TreeSet<String>>(); // THE MAP THAT GETS PRINTED	
		//POPULATE THE TREE MAP ABOVE
			/*
			 * make the pac the keys of the map, the members shoukd be the values
			 */
		while(memberToPacsFile.ready()){
			String[] tokens = memberToPacsFile.readLine().split("\\s+"); //bgates BFPR AFCTC
			String member = tokens[0]; 
			
			for(int i=1; i<tokens.length; i++){ 
				String pac = tokens[i];
				if(! pacToMembers.containsKey(pac)){
					TreeSet<String> pacMem = new TreeSet<String>();
					pacMem.add(member);
					pacToMembers.put(pac, pacMem);
				}else{
					TreeSet<String> pacMem = pacToMembers.get(pac);
					pacMem.add(member);
					pacToMembers.put(pac, pacMem);
					
				}
			}
		}
		//NOw PRINT THAT MAP (see output)
		for(String pac : allPacs){
			System.out.print(pac + "K ");
			if(pacToMembers.containsKey(pac)){
			for ( String s : pacToMembers.get(pac) ){
				System.out.print( s + "V ");
			} 
		}
		System.out.println("\r");
		}
	} // END MAIN
} // CLASS
