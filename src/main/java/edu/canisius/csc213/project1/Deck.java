package edu.canisius.csc213.project1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Represents a deck of playing cards with a configurable size.
 */
public class Deck {
	
	/**
	 * All the cards that are currently in the deck.
	 */
	private final List<Card> cards = new ArrayList<>();

	/**
	 * Creates a deck with a given size. The size must be a multiple of 4 and at most 52.
	 *
	 * @param size The number of cards in the deck.
	 * @throws IllegalArgumentException if size is invalid.
	 */
	public Deck(int size) {
		// Validate size (must be a multiple of 4 and at most 52).
        if (size % 4 != 0 || size > 52 || size <= 0){
            throw new IllegalArgumentException("Illegal deck size.");
        }

		// Saves the enum values to variables (so I need to write less later)
		Card.Rank[] rankList = Card.Rank.values();
		Card.Suit[] suitList = Card.Suit.values();

		// Initialize the deck with the correct cards.
		var numRanks = size / 4;

		// Build the deck starting with Aces.
		for (Card.Suit suit : suitList) {
			for (var i = rankList.length - 1; i >= rankList.length - numRanks; i--){
				cards.add(new Card(suit, rankList[i]));
			}
		}
	}
	
	/**
	 * Shuffles the deck.
	 */
	public void shuffle() {
		// Implement shuffle logic.
		Collections.shuffle(cards);
	}
	
	/**
	 * Draws the top card from the deck.
	 *
	 * @return The drawn card.
	 * @throws NoSuchElementException if the deck is empty.
	 */
	public Card draw() {
		// Implement draw logic.
		if (cards.isEmpty()){
			throw new NoSuchElementException("Deck is empty.");
		}
		return cards.removeLast();
	}
	
	/**
	 * Gets the number of remaining cards in the deck.
	 *
	 * @return The number of cards left.
	 */
	public int size() {
		// Implement size method.
		return cards.size();
	}
}