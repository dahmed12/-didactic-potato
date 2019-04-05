package game.deck;

import java.util.ArrayList;
import java.util.Random;

public class Deck {
	private ArrayList<Card> deckList;
	private String[] Suits = {"C", "D", "H", "S"}; 
	private String[] Ranks = {"A","2","3","4","5","6","7","8","9","10","J","Q","K"};
	public Deck() {
		deckList = new ArrayList<Card>();
		for (String suit : Suits) {
			for (String rank : Ranks) {
				deckList.add(new Card(suit,rank));
			}		
		}
		deckList.size();
	}
	
	public void shuffle() {
		ArrayList<Card> newDeckContents = new ArrayList<Card>();
		Random rand = new Random();
		int count = deckList.size();
		int track = 0;
		for (int i=0; i < count; i++){
			track = count - i;
			int index = rand.nextInt(track);
			Card a = deckList.get(index);
			deckList.remove(index);
			newDeckContents.add(i, a);
		}
		deckList = newDeckContents;
	}

	public void deal(Hand player, boolean show) {
		Card dealtCard = deckList.get(0);
		deckList.remove(0);
		player.recieveCard(dealtCard, show);
		deckList.size();
	}



	public void displayDeck() {
		for (Card card: deckList ){
			card.display();
		}
	}


}