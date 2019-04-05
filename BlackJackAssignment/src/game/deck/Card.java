package game.deck;

public class Card {
	private String suit;
	public String rank;
	public int value;

	public Card(String inputSuit, String inputRank) {
		suit = inputSuit;
		rank = inputRank;
		if (rank.equals("A")) {
			value = 1;
		} else if (rank.equals("K") || rank.equals("J") || rank.equals("Q")) {
			value = 10;
		} else {
			value = Integer.parseInt(rank); 
		}

	}

	public void display() {
		String fullSuit;
		String fullRank;

		if (suit.equals("C")) {
			fullSuit = "CLUBS";
		} else if (suit.equals("D")) {
			fullSuit = "DIAMONDS";
		} else if (suit.equals("H")) {
			fullSuit = "HEARTS";
		} else {
			fullSuit = "SPADES";
		}

		if (rank.equals("A")) {
			fullRank = "ACE";
		} else if (rank.equals("K")) {
			fullRank = "KING";
		} else if (rank.equals("J")) {
			fullRank = "JACK";
		} else if (rank.equals("Q")) {
			fullRank = "QUEEN";
		} else {
			fullRank = rank;
		}

		System.out.println(fullRank + " of " + fullSuit);
	}

}