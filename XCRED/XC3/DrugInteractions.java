import java.util.*;
import java.io.*;

public class DrugInteractions
{
	//static TreeSet<String> val = new TreeSet<String>();
	public static void main( String[] args ) throws Exception
	{
		BufferedReader foodDrug2CategoryFile = new BufferedReader( new FileReader( "foodDrug2Category.txt" ) );
		TreeMap<String, TreeSet<String>> fdMap = loadMap(foodDrug2CategoryFile);
		printString(fdMap);
		BufferedReader patient2FoodDrugFile = new BufferedReader( new FileReader( "patient2FoodDrug.txt") );
		TreeMap<String, TreeSet<String>> pdMap = loadMap(patient2FoodDrugFile);
		System.out.println();
		printString(pdMap);
		System.out.println();
		BufferedReader dontMixFile = new BufferedReader( new FileReader( "dontMix.txt" ) );
		TreeSet<String> dontmix = new TreeSet<String>();

		while( dontMixFile.ready()){
		 	dontmix.add(dontMixFile.readLine());
		 }	
		step3(pdMap, fdMap, dontmix);
	} // END MAIN

	private static boolean checkIfContains(TreeSet<String> patValues, TreeSet<String> fdValues){
		boolean print = false;
		Iterator<String> vIterator = patValues.iterator();
		     while(vIterator.hasNext()){
				String value = vIterator.next();
				if(fdValues.contains(value)){
					print = true;
				}
			}
			return print;
	}

	public static TreeMap< String, TreeSet<String>> loadMap(BufferedReader file) throws IOException{ 
	TreeMap<String, TreeSet<String>> map = new TreeMap<String, TreeSet<String>>(); // THE MAP THAT GETS PRINTED	
	while(file.ready()){
		String[] keyval = file.readLine().split(","); //STATINS,lipitor,crestor,zocor,lipex
		String key = keyval[0]; 
		TreeSet<String> val = new TreeSet<String>();
		for(int i=1; i<keyval.length; i++){
		
		 val.add(keyval[i] +" ");
	}
	map.put(key, val);
	}
	return map;
}

	//PRINT MAP
	public static void printString(TreeMap<String, TreeSet<String>> map){
	// Set<Map.Entry<String, TreeSet<String>>> printMap =  map.entrySet();
	// for(Map.Entry<String, TreeSet<String>> entry : printMap){
	// 	System.out.println(entry.getKey()+ entry.getValue());
	// }

	for(String entry : map.keySet()){
		System.out.print(entry + " ");		
		for ( String s : map.get(entry) ){
			System.out.print( s);
		} 
		System.out.println();
	}
}

public static void step3(TreeMap<String, TreeSet<String>> pdMap , TreeMap<String, TreeSet<String>> fdMap, TreeSet<String> dontmix ){
	
		//compare values from pdmap to values from fdmap
		//if the values match with two keys, compare the keys to dontMix.txt
		//if they both match, print key from pdmap
		//for each key in the pdMap
	for(String entry : pdMap.keySet()){
		boolean print1 = false;
		boolean print2 = false;
		//get the food drug values
		TreeSet<String> vals = pdMap.get(entry);
		//System.out.println("Patient: " + entry + " Values: " + pdMap.get(entry));
		//For each entry in the dmMap
		Iterator<String> dmIterator = dontmix.iterator();
		 while(dmIterator.hasNext()){
			print1 = false;
			print2 = false;
			//Get the values from the dontmix treeset
			String[] keys = dmIterator.next().split(",");
				//get the values for the matching keys from fdMap
			TreeSet<String> fd1vals= fdMap.get(keys[0]);
			//System.out.println("fdMap Key: " + keys[0] + " Values: " +  fd1vals);
			TreeSet<String> fd2vals= fdMap.get(keys[1]);
			//System.out.println("fdMap Key: " + keys[1] + " Values: " +  fd2vals);
			 print1 = checkIfContains(vals, fd1vals);
			 print2 = checkIfContains(vals, fd2vals);

			//System.out.println("PRINT 1: " + print1 + " print 2: " + print2);
			if(print1 && print2){
				System.out.println(entry);
				break;
			}
		}
		
	}
}

	
} // END CLASS