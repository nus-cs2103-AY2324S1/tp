package seedu.address.model.card;

import java.util.Comparator;

public class CardPracticeDateComparator implements Comparator<Card> {
    @Override
    public int compare(Card o1, Card o2) {
        return o1.compareTo(o2);
    }
}
