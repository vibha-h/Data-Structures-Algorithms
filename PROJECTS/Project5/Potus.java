import java.util.*;
import java.io.*;

public class Potus
{
	public static void main( String[] args )  throws Exception
	{
		BufferedReader state2PresidentsFile = new BufferedReader( new FileReader("state2Presidents.txt") );
		TreeMap<String,TreeSet<String>> state2Presidents= new TreeMap<String,TreeSet<String>>();
		loadMap(state2Presidents, state2PresidentsFile);

		BufferedReader allPresidentsFile = new BufferedReader( new FileReader("allPresidents.txt") );
		TreeSet<String> allPresidents = new TreeSet<String>();
		loadSet(allPresidents, allPresidentsFile);

		BufferedReader allStatesFile = new BufferedReader( new FileReader("allStates.txt") );
		TreeSet<String> allStates = new TreeSet<String>();
		loadSet(allStates, allStatesFile);

		System.out.println( "THESE STATES HAD THESE POTUS BORN IN THEM:\n");
		//System.out.println(state2Presidents.toString()); //call printSet here instead
		printSet(state2Presidents);

		System.out.println( "\nLIST OF POTUS AND STATE THEY WERE BORN IN:\n");
		TreeMap<String, TreeSet<String>> president2State = new TreeMap<String, TreeSet<String>>();
		loadPres2StateMap(state2Presidents, president2State);
		printSet(president2State);
	
		System.out.println( "\nTHESE POTUS BORN BEFORE STATES WERE FORMED:\n");
		printSet(step34(president2State, allPresidents));

		System.out.println( "\nTHESE STATES HAD NO POTUS BORN IN THEM:\n");	
		printSet(step34(state2Presidents, allStates));
	} // END MAIN

	//       - - - - - - - - - - -  H E L P E R    M E T H O D S - - - - - - - -
	static TreeMap<String, TreeSet<String>> loadMap(TreeMap<String, TreeSet<String>> map, BufferedReader infile ) throws Exception
	{
		while( infile.ready() ){
		String input = infile.readLine();
		String[] a = input.split(" ");
		String key = a[0];
		TreeSet<String>s = new TreeSet<String>();
		for (int i=1; i< a.length; i++){
			s.add(a[i]);
		}
		map.put(key, s);
		}
      infile.close();
      return map;
	}

	static TreeSet<String> loadSet( TreeSet<String> s ,BufferedReader infile ) throws Exception
	{
		while( infile.ready() ){
        s.add(infile.readLine());
		}
      infile.close();
      return s;
	}

	static void printSet(TreeMap<String, TreeSet<String>> set )
	{		
		Iterator i = set.keySet().iterator();
		while(i.hasNext()){
			String key = (String)i.next();
			System.out.print( key + " " );
			for ( String s : set.get(key) ){
				System.out.print( s + " " );
			}
			System.out.println( "\r" );
		}
	}

	static void printSet(TreeSet<String> set){
		for ( String s : set ){
			System.out.println( s + "\r" );
		}
	}

	static void loadPres2StateMap(TreeMap<String, TreeSet<String>> state, TreeMap<String, TreeSet<String>> pres){
		Iterator i = state.keySet().iterator();
		while(i.hasNext()){
			String key = (String)i.next();
			for ( String s : state.get(key) ){
				TreeSet<String> keySet = new TreeSet<String>();
				keySet.add(key);
				pres.put(s, keySet);
			}
		}
	}
	
	static TreeSet<String> step34(TreeMap<String, TreeSet<String>> pres2state, TreeSet<String> pres){
		/*
		 * loop through the TreeSet and for each entry, check if matching key exists in TreeMap
		 * if key doesnt exist, print value from treeset
		 */
		Iterator i = pres.iterator();
		TreeSet<String> newPres = new TreeSet<String>();
		while(i.hasNext()){
		String val = (String)i.next();
		if(! pres2state.containsKey(val)){
			newPres.add(val);
			}
		}
		return newPres;
	}
	
	
}	// END CLASS