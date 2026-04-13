package edu.canisius.csc213.project1;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

/**
 * UniqueHands class to analyze how long it takes to see every possible hand for different deck sizes and hand sizes.
 */
public class UniqueHands {

	public static void main(String[] args) {
		int[] deckSizes = {24, 28}; // Deck sizes to test.
		int[] handSizes = {6, 7}; // Hand sizes to test.
		int trials = 5; // Number of trials per deck-hand combination.
		// Name of the CSV file.
		final String fileName = "uniqueHandsData.csv";

		System.out.println("🃏 Deck Simulation: How long to see every possible hand?");
		System.out.println("-".repeat(100));

		// Start the CSV file with a try with resources statement.
		try (var writer = new PrintWriter(new FileOutputStream(fileName))) {
			writer.println("Deck Size,Hand Size,Trial,Attempts,Time (s)");
		}
		catch (IOException e) { throw new RuntimeException(e); }


		// Outer loop: Iterates through deck sizes (24, 28).
		for (var i : deckSizes){
			// Inner loop: Iterates through hand sizes (6, 7).
			for (var j : handSizes){
				// Inside inner loop: Run 5 trials, track time and attempts, and compute averages.
				var avgAttempts = 0;
				var avgTime = 0.0;
				// Run all 5 trials.
				for (var k = 0; k <trials; k++){
					var startTime = System.nanoTime();
					var attempts = countAttemptsToSeeAllHands(i, j);
					var elapsedTime = (1.0 / 1_000_000_000.0) * (System.nanoTime() - startTime) ;
					avgTime += elapsedTime;
					avgAttempts += attempts;
					System.out.printf("Deck Size: %d | Hand Size: %d | Trial %d | Attempts: %d | Time: %.3fs%n", i, j, k + 1, attempts, elapsedTime);
					// Print information about the simulation to a csv file using a try with resources statement.
					// It prints: Deck size,Hand size,Trial #,Attempts,time.
					try (var writer = new PrintWriter(new FileOutputStream(fileName, true))) {
						writer.printf("%d,%d,%d,%d,%.3fs%n", i, j, k + 1, attempts, elapsedTime);
					}
					catch (IOException e) { throw new RuntimeException(e); }
				}
				// Calculate averages.
				avgTime /= trials;
				avgAttempts /= trials;

				// Print final stats.
				System.out.printf("✅ Deck Size: %d | Hand Size: %d | Avg Attempts: %d | Avg Time: %.3fs%n", i, j, avgAttempts, avgTime);
				System.out.println("-".repeat(100));

			}
		}


	}

	/**
	 * Simulates drawing a random handSize amount of cards from a deck of size deckSize, and counts how long it takes to see every possible hand.
	 *
	 * @param deckSize Number of cards in a deck.
	 * @param handSize Number of cards in a hand.
	 * @return The number of attempts it took to see all hands.
	 */
    public static int countAttemptsToSeeAllHands(int deckSize, int handSize){
		HashSet<List<Card>> handsList = new HashSet<>(handSize);
		var percentCover = 0.0;
		var handsSeen = 0;
		var handsPossible = (calculateTotalUniqueHands(deckSize, handSize)).longValue();
		var handsLeft = (calculateTotalUniqueHands(deckSize, handSize)).longValue();
		var attempts = 0;

		// Repeats until every possible hand is in the HashSet.
		while (handsList.size() < handsPossible){
			// List that will contain the new hand that is viewed.
			List<Card> cards = new ArrayList<>();
			// Makes a new deck with the proper deck size and shuffles it.
			Deck deck = new Deck(deckSize);
			deck.shuffle();
			// Adds cards to the hand until the handSize is reached.
			for (var i = 0; i < handSize; i++){
				cards.add(deck.draw());
			}
			// Sorts the hand so that hands with the same cards in a different order will count as the same.
			// Learned this from stack overflow :)
			cards.sort(Comparator.comparing(Card::suit).thenComparing(Card::rank));

			// Adds the hand to the list of seen hands and updates the status variables.
			handsList.add(cards);
			handsSeen = handsList.size();
			handsLeft = handsPossible - handsSeen;
			percentCover = ((double) handsSeen / handsPossible) * 100;
			attempts++;
			// Prints progress every 100,000 attempts.
			if (attempts % 100_000 == 0){
				System.out.printf("%.3f%% coverage reached after %d attempts (Unique Hands: %d / %d | Needed: %d)%n", percentCover, attempts, handsSeen, handsPossible, handsLeft);
			}
		}
		System.out.printf("✅ %.3f%% coverage reached after %d attempts (Unique Hands: %d / %d | Needed: %d)%n", percentCover, attempts, handsSeen, handsPossible, handsLeft);
        return attempts;
    }

	/**
	 * Calculates how many possible unique hand combinations there are for a certain deck and hand size.
	 *
	 * @param deckSize Number of cards in the deck.
	 * @param handSize Number of cards in a hand.
	 * @return A BigInteger value of the number of unique hand combinations
	 */
    public static BigInteger calculateTotalUniqueHands(int deckSize, int handSize){
        return (factorial(deckSize).divide(factorial(handSize).multiply(factorial(deckSize - handSize))));
    }

	/**
	 * Finds the factorial of the input number.
	 *
	 * @param num The number that the function finds the factorial of.
	 * @return The factorial of the input number.
	 */
	public static BigInteger factorial(int num){
		// Test for negative number.
		if (num < 0) {
			throw new IllegalArgumentException("Input must not be a negative.");
		}
		// 0! = 0.
		BigInteger result = new BigInteger("1");
		// Starts at the input and multiplies it with result, then continues subtracting by 1 and repeating until it reaches 1.
		for (var i = num; i > 1; i--){
			result = result.multiply(BigInteger.valueOf(i));
		}
		return result;
	}
}