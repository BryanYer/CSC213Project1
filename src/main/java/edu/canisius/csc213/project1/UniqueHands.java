package edu.canisius.csc213.project1;

import java.math.BigInteger;

/**
 * UniqueHands class to analyze how long it takes to see every possible hand for different deck sizes and hand sizes.
 */
public class UniqueHands {
	
	static void main(String[] args) {
		int[] deckSizes = {24, 28}; // Deck sizes to test
		int[] handSizes = {6, 7}; // Hand sizes to test
		int trials = 5; // Number of trials per deck-hand combination
		
		System.out.println("🃏 Deck Simulation: How long to see every possible hand?");
		System.out.println("-".repeat(100));
		

		// Outer loop: Iterates through deck sizes (24, 28).
		for (var i : deckSizes){
			// Inner loop: Iterates through hand sizes (6, 7)
			for (var j : handSizes){
				// TODO: Inside inner loop: Run 5 trials, track time and attempts, and compute averages.
				var startTime = System.nanoTime();


				var elapsedTime = System.nanoTime() - startTime;

			}
		}

		
	}
	
	// TODO: Implement countAttemptsToSeeAllHands()
    public static int countAttemptsToSeeAllHands(int deckSize, int handSize){
        return 0;
    }

	/**
	 * Calculates how many possible unique hand combinations there are for a certain deck and hand size.
	 * @param deckSize Number of cards in the deck.
	 * @param handSize Number of cards in a hand.
	 * @return A BigInteger value of the number of unique hand combinations
	 */
    public static BigInteger calculateTotalUniqueHands(int deckSize, int handSize){
        return (factorial(deckSize).divide(factorial(handSize).multiply(factorial(deckSize - handSize))));
    }

	/**
	 * Finds the factorial of the input number.
	 * @param num The number that the function finds the factorial of.
	 * @return The factorial of the input number.
	 */
	public static BigInteger factorial(int num){
		BigInteger result = new BigInteger("1");
		if (num == 0){
			return result;
		}
		for (var i = num; i > 0; i--){
			result = result.multiply(new BigInteger(String.valueOf(i)));
		}
		return result;
	}
}