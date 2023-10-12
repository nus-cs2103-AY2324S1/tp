package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.flashcard.FlashCard;

/**
 * Unmodifiable view of a flashlingo
 */
public interface ReadOnlyFlashlingo {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<FlashCard> getFlashCardList();

}
