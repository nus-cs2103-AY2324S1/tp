package seedu.address.model;

import javafx.collections.ObservableMap;
import seedu.address.model.person.assignment.Assignment;
import seedu.address.model.person.assignment.AssignmentName;

/**
 * Unmodifiable view of an assignment map.
 */
public interface ReadOnlyAssignmentMap {

    /**
     * Returns an unmodifiable view of the assignment map.
     */
    ObservableMap<AssignmentName, Assignment> getAssignmentMap();
}
