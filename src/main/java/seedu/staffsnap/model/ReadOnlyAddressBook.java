package seedu.staffsnap.model;

import javafx.collections.ObservableList;
import seedu.staffsnap.model.employee.Employee;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyAddressBook {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Employee> getEmployeeList();

}
