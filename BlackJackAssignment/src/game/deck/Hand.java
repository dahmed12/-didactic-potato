package game.deck;

import java.util.ArrayList;

public class Hand {
	private String nameOfPlayer;
	private ArrayList<Card> handList;
	public int totalCardValue;

	public Hand(String player) {
		nameOfPlayer = player;
		handList = new ArrayList<Card>();
		totalCardValue = 0;
	}

	public void display() {
		System.out.println(nameOfPlayer + ":");
		for (Card card : handList) {
			card.display();
		}
		System.out.println(nameOfPlayer + "'s total: " + totalCardValue);
	}

	public void hiddenDisplay() {
		System.out.println(nameOfPlayer + ":");
		System.out.println("<Hidden Card>");
		for (int i = 1; i < handList.size(); i++) {
			handList.get(i).display();
		}
	}

	public void recieveCard(Card dealtCard, boolean show) {
		handList.add(dealtCard);
		getCardValue(dealtCard);
		if (show) {
			// System.out.println();
			// System.out.println(nameOfPlayer + " takes a card:");
			dealtCard.display();
		}
	}

	public void getCardValue(Card dealtCard) {

		if (dealtCard.rank.equals("A")) {
			if (totalCardValue <= 10) {
				totalCardValue += 11;
			} else {
				totalCardValue += 1;
			}
		} else {
			totalCardValue += dealtCard.value;
		}

	}
}