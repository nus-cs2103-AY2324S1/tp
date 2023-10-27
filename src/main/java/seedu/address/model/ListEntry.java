package seedu.address.model;

import seedu.address.model.person.Name;

/**
 * Represents a ListEntry in the application.
 */
public abstract class ListEntry {
    //public static ListEntry getDefault${className);
    public abstract ListEntry clone();
    public abstract Name getName();
}
