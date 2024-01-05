package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.session.Session;

/**
 * Unmodifiable view of a session list
 */
public interface ReadOnlySessionList {
    /**
     * Returns an unmodifiable view of the session list.
     * This list will not contain any duplicate session.
     */
    ObservableList<Session> getSessionList();
}


