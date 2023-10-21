package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DEPARTMENT_LOGISTIC;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalEmployees.ALICE;
import static seedu.address.testutil.TypicalEmployees.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.person.Employee;
import seedu.address.model.person.exceptions.DuplicateEmployeeException;
import seedu.address.testutil.EmployeeBuilder;

public class ManageHrTest {

    private final ManageHr manageHr = new ManageHr();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), manageHr.getEmployeeList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> manageHr.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        ManageHr newData = getTypicalAddressBook();
        manageHr.resetData(newData);
        assertEquals(newData, manageHr);
    }

    @Test
    public void resetData_withDuplicateEmployees_throwsDuplicateEmployeeException() {
        // Two people with the same identity fields
        Employee editedAlice = new EmployeeBuilder(ALICE).withAddress(VALID_ADDRESS_BOB)
                .withDepartments(VALID_DEPARTMENT_LOGISTIC)
                .build();
        List<Employee> newPeople = Arrays.asList(ALICE, editedAlice);
        ManageHrStub newData = new ManageHrStub(newPeople);

        assertThrows(DuplicateEmployeeException.class, () -> manageHr.resetData(newData));
    }

    @Test
    public void hasEmployee_nullEmployee_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> manageHr.hasEmployee(null));
    }

    @Test
    public void hasEmployee_employeeNotInAddressBook_returnsFalse() {
        assertFalse(manageHr.hasEmployee(ALICE));
    }

    @Test
    public void hasEmployee_employeeInAddressBook_returnsTrue() {
        manageHr.addEmployee(ALICE);
        assertTrue(manageHr.hasEmployee(ALICE));
    }

    @Test
    public void hasEmployee_employeeWithSameIdentityFieldsInAddressBook_returnsTrue() {
        manageHr.addEmployee(ALICE);
        Employee editedAlice = new EmployeeBuilder(ALICE).withAddress(VALID_ADDRESS_BOB)
                .withDepartments(VALID_DEPARTMENT_LOGISTIC)
                .build();
        assertTrue(manageHr.hasEmployee(editedAlice));
    }

    @Test
    public void getEmployeeList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> manageHr.getEmployeeList().remove(0));
    }

    @Test
    public void toStringMethod() {
        String expected = ManageHr.class.getCanonicalName() + "{employees=" + manageHr.getEmployeeList() + "}";
        assertEquals(expected, manageHr.toString());
    }

    /**
     * A stub ReadOnlyAddressBook whose people list can violate interface constraints.
     */
    private static class ManageHrStub implements ReadOnlyManageHr {
        private final ObservableList<Employee> people = FXCollections.observableArrayList();

        ManageHrStub(Collection<Employee> people) {
            this.people.setAll(people);
        }

        @Override
        public ObservableList<Employee> getEmployeeList() {
            return people;
        }
    }

}
