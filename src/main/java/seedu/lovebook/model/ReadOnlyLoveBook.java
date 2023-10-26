package seedu.lovebook.model;

import javafx.collections.ObservableList;
import seedu.lovebook.model.date.Date;

/**
 * Unmodifiable view of the LoveBook.
 */
public interface ReadOnlyLoveBook {

    /**
     * Returns an unmodifiable view of the dates list.
     * This list will not contain any duplicate dates.
     */
    ObservableList<Date> getPersonList();

}
