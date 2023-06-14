/*
	Deck class (for TopCardPlacer class of project #1
*/

import java.util.*;
import java.io.*;

public class Deck
{
	private int[] deck;
	private int[] topHalf;
	private int[] bottomHalf;
	private int[] binArr;
	private final int MAX_DECK_SIZE = 30;
	public Deck( int numCards )
	{	
		if ( numCards%2 != 0 || numCards > MAX_DECK_SIZE ) 
		{
			System.out.format("\nINVALID DECK SIZE: (" + numCards + "). Must be an small even number <= %d\n", MAX_DECK_SIZE);
			System.exit(0);
		}
		
		deck = new int[numCards];
		for ( int i=0 ; i<numCards ; i++ ){
			deck[i] = i;
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

	public String toBitString( int n ) 
	{
		int logN = (int)(Math.log(n)/ Math.log(2));
		this.binArr = new int[(logN + 1)];
		int[] newArr = new int[binArr.length];
		for (int index=0; index<binArr.length; index++){
			binArr[index] = 0;
			newArr[index] = 0;
		}
		
		while (n>0){
			if(logN>=0){
				newArr[logN]=1;
			}
			int p= (int)Math.pow(2, logN);
			n = n - p;
			logN= (int)(Math.log(n)/ Math.log(2));
		}

		int a = 0;
		for (int j = newArr.length-1; j >= 0; j--){
			binArr[a] = newArr[j];
			a++;
		}

		String bitString= new String();
		for (int i=0; i<binArr.length; i++){
			bitString += String.valueOf(binArr[i]);
		}
		return bitString;
	}
}	// END DECK CLASS