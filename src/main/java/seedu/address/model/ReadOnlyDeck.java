package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.card.Card;

/**
 * Unmodifiable view of an card
 */
public interface ReadOnlyDeck {
    /**
     * Returns an unmodifiable view of the cards list.
     * This list will not contain any duplicate cards.
     */
    ObservableList<Card> getCardList();

}
