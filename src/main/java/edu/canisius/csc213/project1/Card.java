package edu.canisius.csc213.project1;

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
