package blackjack;

import java.util.Scanner;

import game.deck.Deck;
import game.deck.Hand;

public class BlackJack {

	static Deck theDeck = new Deck();

	static int playerChips = 100;
	static int playerBet;
	static int roundCount;
	static Hand dealerHand;
	static Hand playerHand;
	static boolean playAgain = false;
	static String nameOfPlayer;
	static Scanner input = new Scanner(System.in);

	public static void main(String[] args) {
		System.out.println("Welcome to BlackJack! What is your name?");
		nameOfPlayer = input.nextLine();
		while (true) {
			System.out.println();
			System.out.println(nameOfPlayer + ", please enter number 1 to play Black Jack OR choose a number from the options below.....");
			System.out.println("[1]: Play BlackJack");
			System.out.println("[2]: Shuffle Cards");
			System.out.println("[3]: Display All Cards in Deck");
			System.out.println("[4]: New Deck");
			System.out.println("[5]: Reset");
			System.out.println("[6]: Quit");
			String line = input.nextLine();
			if (line.equals("6")) {
				return;
			} else {
				try {
					menu(line);
				} catch (IndexOutOfBoundsException e) {
					System.out.println("Exception thrown  :" + e);
					System.out.println("No more cards to deal... Go back to menu for new deck.");
				}
			}
		}
	}

	private static void menu(String line) {
		if (line.contains("4")) {
			theDeck = new Deck();
			System.out.println("You are starting a new deck");
		} else if (line.equals("2")) {
			System.out.println("Shuffling Deck.....");
			theDeck.shuffle();

			System.out.println("Deck has been Shuffled.");
		} else if (line.equals("3")) {
			System.out.println();
			theDeck.displayDeck();
		} else if (line.equals("1")) {
			while (playerChips > 0) {
				theDeck.shuffle();
				startGame();
				playAgain = false;
				System.out.println("You have " + Integer.toString(playerChips) + " chips.");
				System.out.println("Do you want to play again? (Y/N)");
				String choice = input.nextLine();
				if (!choice.toUpperCase().equals("Y")) {
					return;
				}
			}
			System.out.println("No more chips to bet, plase quit or Reset the game for chips");
		} else if (line.equals("5")) {
			theDeck = new Deck();
			playerChips = 100;
			System.out.println("Reseted , you have 100 chips......");
		} else {
			System.out.println("Please enter number....................");
		}

	}

	private static void startGame() {
		dealerHand = new Hand("Dealer");
		playerHand = new Hand(nameOfPlayer);
		roundCount = 1;
		askBet();
		while (playerBet > playerChips) {
			System.out.println("Your bet exceeds the amount you have.."+ "/n" + "try again");
			askBet();
		}
		theDeck.deal(dealerHand, false);
		theDeck.deal(playerHand, false);
		System.out.println("....................");
		theDeck.deal(dealerHand, false);
		theDeck.deal(playerHand, false);
		reveal();
		while (!playAgain) {
			hitOrStand();
		}
	}

	private static void askBet() {
		System.out.println("You have " + Integer.toString(playerChips) + " Chips. How much would you like to bet?");
		String bet = input.nextLine();
		try {
			playerBet = Integer.valueOf(bet).intValue();
		} catch (NumberFormatException e) {
			System.out.println("Exception thrown  :" + e);
			System.out.println("Please put in an number.");
		}
	}

	private static void hitOrStand() {
		//System.out.println();
		System.out.println("\n"+ "Would you like to HIT? (Y/N)");
		String choice = input.nextLine();
		if (choice.equalsIgnoreCase("Y")) {
			roundCount += 1;
			theDeck.deal(playerHand, true);
			if (playerHand.totalCardValue > 21) {
				System.out.println("You BUST");
				dealerWins();
			} else {
				reveal();
			}
		} else if (choice.equalsIgnoreCase("N")) {
			while (!playAgain) {
				dealerDraws();
			}
		} else {
			System.out.println("ENTER Y or N");
		}
	}

	private static void dealerDraws() {

		if (dealerHand.totalCardValue > 21) {
			System.out.println("Dealer BUST");
			playerWins();
		} else if (dealerHand.totalCardValue <= 16) {
			theDeck.deal(dealerHand, true);
			System.out.println("\n"+"Dealer chooses to HIT");
		} else {
			System.out.println("\n"+"Dealer chooses to STAND.");
			if (playerHand.totalCardValue == 21) {
				System.out.println("\n"+"BLACKJACK!!!");
				playerWins();
			} else if (playerHand.totalCardValue < dealerHand.totalCardValue) {
				dealerWins();
			} else if (playerHand.totalCardValue > dealerHand.totalCardValue) {
				playerWins();
			} else {
				System.out.println("-----DRAW-----");
				dealerWins();
				System.out.println("House wins on a draw by default");
			}
		}
	}

	private static void reveal() {
		System.out.println();
		System.out.println("ROUND " + Integer.toString(roundCount) + ":");
		dealerHand.hiddenDisplay();
		playerHand.display();
	}

	private static void showCards() {
		

		System.out.println("Dealars Cards :");
		dealerHand.display();
		System.out.println("-----------------------------");
		System.out.println("Your Cards :");
		playerHand.display();
	}

	private static void dealerWins() {
		showCards();
		System.out.println();
		System.out.println("You lose, house wins ");
		playerChips -= playerBet;
		playAgain = true;
	}

	private static void playerWins() {
		showCards();
		System.out.println();
		System.out.println(nameOfPlayer + ", YOU WIN!");
		playerChips += playerBet;
		playAgain = true;
	}

}