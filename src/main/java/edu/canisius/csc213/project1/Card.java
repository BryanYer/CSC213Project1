package edu.canisius.csc213.project1;

/**
 * a record containing two enums, one for Suit and one for Rank, and an override toString method that returns the format: RANK of SUIT
 * @param suit an enum containing the possible suits of cards (HEARTS, DIAMONDS, etc.)
 * @param rank an enum containing the possible ranks of cars (TWO, THREE, etc.)
 */
public record Card(Suit suit, Rank rank) {
    public enum Suit {HEARTS, DIAMONDS, CLUBS, SPADES}

    public enum Rank {TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING, ACE}

    /**
     * @return a string in the format: RANK of SUIT.
     */
    @Override
    public String toString(){
        return "%s of %s".formatted(rank, suit);
    }
}
