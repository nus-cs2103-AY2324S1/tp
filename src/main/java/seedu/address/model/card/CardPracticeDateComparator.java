package seedu.address.model.card;

import java.util.Comparator;

/**
 * Compares two cards by their ordering. Cards are ordered by next practice date.
 */
public class CardPracticeDateComparator implements Comparator<Card> {
    @Override
    public int compare(Card o1, Card o2) {
        return o1.compareTo(o2);
    }
}
