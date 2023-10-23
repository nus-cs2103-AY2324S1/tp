package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.card.Card;

/**
 * Unmodifiable view of an Deck
 */
public interface ReadOnlyDeck {
    /**
     * Returns an unmodifiable view of the Cards list.
     * This list will not contain any duplicate Cards.
     */
    ObservableList<Card> getCardList();
    void sort();


}
