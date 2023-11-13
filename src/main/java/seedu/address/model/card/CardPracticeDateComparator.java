package seedu.address.model.card;

import java.util.Comparator;

/**
 * Compares two cards by their ordering. Cards are ordered by next practice date.
 */
public class CardPracticeDateComparator implements Comparator<Card> {
    @Override
    public int compare(Card card, Card otherCard) {
        return card.compareTo(otherCard);
    }
}
