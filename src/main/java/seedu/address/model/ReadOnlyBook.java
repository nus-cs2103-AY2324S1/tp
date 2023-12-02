package seedu.address.model;

import java.util.Optional;

import javafx.collections.ObservableList;

/**
 * Unmodifiable view of an book
 */
public interface ReadOnlyBook<T> {

    /**
     * Returns an unmodifiable view of the T list.
     * This list will not contain any duplicate T.
     */
    ObservableList<T> getList();

    /**
     * Returns the item with the specified id if it exists
     * @param id the id of the item
     * @return the item specified by the id
     */
    Optional<T> getById(int id);

}
