/*
	Deck class
*/

import java.util.*;
import java.io.*;
import java.lang.reflect.Array;

public class Deck
{
	private int[] deck;
	private int[] topHalf;
	private int[] bottomHalf;
	private final int MAX_DECK_SIZE = 20;
	public Deck( int numCards )
	{	if ( numCards%2 != 0 || numCards > MAX_DECK_SIZE ) 
		{
			System.out.format("\nINVALID DECK SIZE: (" + numCards + "). Must be an small even number <= %d\n", MAX_DECK_SIZE);
			System.exit(0);
		}
		// YOU DO THIS => int deck to be exactly numCards long
		deck = new int[numCards];
		// YOU DO THIS => fill deck with with 0 1 2 3 ... numCards-1 in order
		for (int i=0; i<numCards; i++){
			deck[i]=i;
		}
		topHalf= new int[numCards/2];
		bottomHalf= new int[numCards/2]; 
	}
	
	public String toString()
	{
		String deckStr = "";
		for ( int i=0 ; i < deck.length ; ++i )
			deckStr += deck[i] + " ";
		return deckStr;
	}

	/*
	 * divide deck in half
	 * putting the values into two seperate arrays (topHalf[] and bottomHalf[])
	 */
	public void splitDeck(){
		int j=0;
		for (int i=0; i<deck.length;i++){
			if (i<(deck.length/2)){
				topHalf[i]=deck[i];
			} else{
				bottomHalf[j]=deck[i];
				j++;
			}
			
		}
	}
	// ONLY WORKS ON DECK WITH EVEN NUMBER OF CARDS
	// MODIFIES THE MEMBER ARRAY DECK
	/*
	 * take value of first index of the bottom half and place it in first index of
	 * new array
	 * take value of first index of top half and place it in second index of new
	 * array
	 * take value of second index of bottom half and place it in third index of new
	 * array
	 * take value of second index of first half and place it in fourth index of new
	 * array
	 * and on and on for n number of cards
	 */
	public void inShuffle()
	{
		splitDeck();
		int l=0;
		for (int i=0; i<(deck.length/2); i++){
			deck[l]=bottomHalf[i];
			l++;
			deck[l]=topHalf[i];
			l++;
		}
	}

	// ONLY WORKS ON DECK WITH EVEN NUMBER OF CARDS
	// MODIFIES THE MEMBER ARRAY DECK
	/*
	 * take value of first index of the top half and place it in first index of new
	 * array
	 * take value of first index of bottom half and place it in second index of new
	 * array
	 * take value of second index of top half and place it in third index of new
	 * array
	 * take value of second index of bottom half and place it in fourth index of new
	 * array
	 * and on and on for n number of cards
	 */
	public void outShuffle()
	{	
		splitDeck();
		int l=0;
		for (int i=0; i<(deck.length/2); i++){
			deck[l]=topHalf[i];
			l++;
			deck[l]=bottomHalf[i];
			l++;
		}

	}
	
	// RETURNS TRUE IF DECK IN ORIGINAL SORTED:  0 1 2 3 ...
	public boolean inSortedOrder()
	{
		//loop until new array matches original array


		boolean compare= true;
		for (int i=0; i<deck.length; i++){
			if(deck[i]!=i){
				compare= false;
				break;
			}
		}
		return compare; // JUST HERE TO COMPILE
	}
}	// END DECK CLASS