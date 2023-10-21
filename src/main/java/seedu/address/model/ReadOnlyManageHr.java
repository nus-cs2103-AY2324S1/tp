package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.person.Employee;

/**
 * Unmodifiable view of ManageHR
 */
public interface ReadOnlyManageHr {

    /**
     * Returns an unmodifiable view of the employees list.
     * This list will not contain any duplicate employees.
     */
    ObservableList<Employee> getEmployeeList();

}
