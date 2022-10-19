package thrones.game;

import ch.aplu.jcardgame.Card;
import ch.aplu.jcardgame.Hand;

import java.util.stream.Collectors;

public final class SuitAndRank {
    enum GoTSuit { CHARACTER, DEFENCE, ATTACK, MAGIC }
    public enum Suit {
        SPADES(GoTSuit.DEFENCE),
        HEARTS(GoTSuit.CHARACTER),
        DIAMONDS(GoTSuit.MAGIC),
        CLUBS(GoTSuit.ATTACK);
        Suit(GoTSuit gotsuit) {
            this.gotsuit = gotsuit;
        }
        private final GoTSuit gotsuit;

        public boolean isDefence(){ return gotsuit == GoTSuit.DEFENCE; }

        public boolean isAttack(){ return gotsuit == GoTSuit.ATTACK; }

        public boolean isCharacter(){ return gotsuit == GoTSuit.CHARACTER; }

        public boolean isMagic(){ return gotsuit == GoTSuit.MAGIC; }
    }

    public enum Rank {
        // Reverse order of rank importance (see rankGreater() below)
        // Order of cards is tied to card images
        ACE(1), KING(10), QUEEN(10), JACK(10), TEN(10), NINE(9), EIGHT(8), SEVEN(7), SIX(6), FIVE(5), FOUR(4), THREE(3), TWO(2);
        Rank(int rankValue) {
            this.rankValue = rankValue;
        }
        private final int rankValue;
        public int getRankValue() {
            return rankValue;
        }
    }

    /*
    Canonical String representations of Suit, Rank, Card, and Hand
    */
    public static String canonical(Suit s) { return s.toString().substring(0, 1); }

    public static String canonical(Rank r) {
        return switch (r) {
            case ACE, KING, QUEEN, JACK, TEN -> r.toString().substring(0, 1);
            default -> String.valueOf(r.getRankValue());
        };
    }

    public static String canonical(Card c) { return canonical((Rank) c.getRank()) + canonical((Suit) c.getSuit()); }

    public static String canonical(Hand h) {
        return "[" + h.getCardList().stream().map(SuitAndRank::canonical).collect(Collectors.joining(",")) + "]";
    }
}
