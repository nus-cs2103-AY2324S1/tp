package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAssignments.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.assignment.Assignment;
import seedu.address.model.internship.role.InternshipRole;
import seedu.address.model.internship.task.InternshipTask;

public class AddressBookTest {

    private final AddressBook addressBook = new AddressBook();


    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        AddressBook newData = getTypicalAddressBook();
        addressBook.resetData(newData);
        assertEquals(newData, addressBook);
    }

    /**
     * A stub ReadOnlyAddressBook whose persons list can violate interface constraints.
     */
    private static class AddressBookStub implements ReadOnlyAddressBook {
        private final ObservableList<Assignment> assignments = FXCollections.observableArrayList();
        private final ObservableList<InternshipRole> roles = FXCollections.observableArrayList();
        private final ObservableList<InternshipTask> internshipTasks = FXCollections.observableArrayList();

        @Override
        public ObservableList<InternshipTask> getInternshipTaskList() {
            return internshipTasks;
        }

        @Override
        public ObservableList<InternshipRole> getInternshipRoleList() {
            return roles;
        }

        @Override
        public ObservableList<Assignment> getAssignmentList() {
            return assignments;
        }

    }

}
