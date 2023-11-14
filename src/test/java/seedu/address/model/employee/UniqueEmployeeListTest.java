package seedu.address.model.employee;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DEPARTMENT_LOGISTIC;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalEmployees.ALICE;
import static seedu.address.testutil.TypicalEmployees.AMY;
import static seedu.address.testutil.TypicalEmployees.BENSON;
import static seedu.address.testutil.TypicalEmployees.BOB;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.employee.exceptions.DuplicateEmployeeException;
import seedu.address.model.employee.exceptions.EmployeeNotFoundException;
import seedu.address.model.employee.exceptions.SubordinatePresentException;
import seedu.address.model.employee.exceptions.SupervisorNotFoundException;
import seedu.address.testutil.EmployeeBuilder;

public class UniqueEmployeeListTest {

    private final UniqueEmployeeList uniqueEmployeeList = new UniqueEmployeeList();

    @Test
    public void contains_nullEmployee_throwsNullPointerException() {
        Employee employee = null;
        assertThrows(NullPointerException.class, () -> uniqueEmployeeList.contains(employee));
    }

    @Test
    public void contains_employeeNotInList_returnsFalse() {
        assertFalse(uniqueEmployeeList.contains(ALICE));
    }

    @Test
    public void contains_employeeInList_returnsTrue() {
        uniqueEmployeeList.add(ALICE);
        assertTrue(uniqueEmployeeList.contains(ALICE));
    }

    @Test
    public void contains_employeeWithSameIdentityFieldsInList_returnsTrue() {
        uniqueEmployeeList.add(ALICE);
        Employee editedAlice = new EmployeeBuilder(ALICE).withAddress(VALID_ADDRESS_BOB)
                .withDepartments(VALID_DEPARTMENT_LOGISTIC)
                .build();
        assertTrue(uniqueEmployeeList.contains(editedAlice));
    }

    @Test
    public void add_nullEmployee_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueEmployeeList.add(null));
    }

    @Test
    public void add_duplicateEmployee_throwsDuplicateEmployeeException() {
        uniqueEmployeeList.add(ALICE);
        assertThrows(DuplicateEmployeeException.class, () -> uniqueEmployeeList.add(ALICE));
    }

    @Test
    public void add_employeeWithSupervisorsNotFoundInList_throwsSupervisorNotFoundException() {
        assertThrows(SupervisorNotFoundException.class, () -> uniqueEmployeeList.add(BOB));
    }

    @Test
    public void setEmployee_nullTargetEmployee_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueEmployeeList.setEmployee(null, ALICE));
    }

    @Test
    public void setEmployee_nullEditedEmployee_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueEmployeeList.setEmployee(ALICE, null));
    }

    @Test
    public void setEmployee_targetEmployeeNotInList_throwsEmployeeNotFoundException() {
        assertThrows(EmployeeNotFoundException.class, () -> uniqueEmployeeList.setEmployee(ALICE, ALICE));
    }

    @Test
    public void setEmployee_targetEmployeeWhoIsSupervisorToOthers_throwsSubordinatePresentException() {
        uniqueEmployeeList.add(AMY);
        uniqueEmployeeList.add(BOB);
        assertThrows(SubordinatePresentException.class, () -> uniqueEmployeeList.setEmployee(AMY, ALICE));
    }

    @Test
    public void setEmployee_targetEmployeeIsSupervisorOfEditedEmployee_throwsSupervisorNotFoundException() {
        uniqueEmployeeList.add(ALICE);
        uniqueEmployeeList.add(AMY);
        assertThrows(SupervisorNotFoundException.class, () -> uniqueEmployeeList.setEmployee(AMY, BOB));
    }

    @Test
    public void setEmployee_editedEmployeeWithSupervisorsNotFoundInList_throwsSupervisorNotFoundException() {
        uniqueEmployeeList.add(ALICE);
        assertThrows(SupervisorNotFoundException.class, () -> uniqueEmployeeList.setEmployee(ALICE, BOB));
    }

    @Test
    public void setEmployee_editedEmployeeIsSameEmployee_success() {
        uniqueEmployeeList.add(ALICE);
        uniqueEmployeeList.setEmployee(ALICE, ALICE);
        UniqueEmployeeList expectedUniqueEmployeeList = new UniqueEmployeeList();
        expectedUniqueEmployeeList.add(ALICE);
        assertEquals(expectedUniqueEmployeeList, uniqueEmployeeList);
    }

    @Test
    public void setEmployee_editedEmployeeHasSameIdentity_success() {
        uniqueEmployeeList.add(ALICE);
        Employee editedAlice = new EmployeeBuilder(ALICE).withAddress(VALID_ADDRESS_BOB)
                .withDepartments(VALID_DEPARTMENT_LOGISTIC)
                .build();
        uniqueEmployeeList.setEmployee(ALICE, editedAlice);
        UniqueEmployeeList expectedUniqueEmployeeList = new UniqueEmployeeList();
        expectedUniqueEmployeeList.add(editedAlice);
        assertEquals(expectedUniqueEmployeeList, uniqueEmployeeList);
    }

    @Test
    public void setEmployee_editedEmployeeHasDifferentIdentity_success() {
        uniqueEmployeeList.add(ALICE);
        uniqueEmployeeList.setEmployee(ALICE, BENSON);
        UniqueEmployeeList expectedUniqueEmployeeList = new UniqueEmployeeList();
        expectedUniqueEmployeeList.add(BENSON);
        assertEquals(expectedUniqueEmployeeList, uniqueEmployeeList);
    }

    @Test
    public void setEmployee_editedEmployeeHasNonUniqueIdentity_throwsDuplicateEmployeeException() {
        uniqueEmployeeList.add(AMY);
        uniqueEmployeeList.add(BOB);
        assertThrows(DuplicateEmployeeException.class, () -> uniqueEmployeeList.setEmployee(BOB, AMY));
    }

    @Test
    public void remove_nullEmployee_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueEmployeeList.remove(null));
    }

    @Test
    public void remove_employeeDoesNotExist_throwsEmployeeNotFoundException() {
        assertThrows(EmployeeNotFoundException.class, () -> uniqueEmployeeList.remove(ALICE));
    }

    @Test
    public void remove_existingEmployee_removesEmployee() {
        uniqueEmployeeList.add(ALICE);
        uniqueEmployeeList.remove(ALICE);
        UniqueEmployeeList expectedUniqueEmployeeList = new UniqueEmployeeList();
        assertEquals(expectedUniqueEmployeeList, uniqueEmployeeList);
    }

    @Test
    public void remove_existingEmployeeHasSubordinate_throwsSubordinatePresentException() {
        uniqueEmployeeList.add(AMY);
        uniqueEmployeeList.add(BOB);
        assertThrows(SubordinatePresentException.class, () -> uniqueEmployeeList.remove(AMY));
    }

    @Test
    public void setEmployees_nullUniqueEmployeeList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueEmployeeList.setEmployees((UniqueEmployeeList) null));
    }

    @Test
    public void setEmployees_uniqueEmployeeList_replacesOwnListWithProvidedUniqueEmployeeList() {
        uniqueEmployeeList.add(ALICE);
        UniqueEmployeeList expectedUniqueEmployeeList = new UniqueEmployeeList();
        expectedUniqueEmployeeList.add(BENSON);
        uniqueEmployeeList.setEmployees(expectedUniqueEmployeeList);
        assertEquals(expectedUniqueEmployeeList, uniqueEmployeeList);
    }

    @Test
    public void setEmployees_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueEmployeeList.setEmployees((List<Employee>) null));
    }

    @Test
    public void setEmployees_list_replacesOwnListWithProvidedList() {
        uniqueEmployeeList.add(ALICE);
        List<Employee> employeeList = Collections.singletonList(AMY);
        uniqueEmployeeList.setEmployees(employeeList);
        UniqueEmployeeList expectedUniqueEmployeeList = new UniqueEmployeeList();
        expectedUniqueEmployeeList.add(AMY);
        assertEquals(expectedUniqueEmployeeList, uniqueEmployeeList);
    }

    @Test
    public void setEmployees_listWithDuplicateEmployees_throwsDuplicateEmployeeException() {
        List<Employee> listWithDuplicatePeople = Arrays.asList(ALICE, ALICE);
        assertThrows(DuplicateEmployeeException.class, () -> uniqueEmployeeList
                .setEmployees(listWithDuplicatePeople));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueEmployeeList.asUnmodifiableObservableList().remove(0));
    }

    @Test
    public void iterator_iteratorReturnsExpectedElementsInOrder_returnsTrue() {
        uniqueEmployeeList.add(ALICE);
        uniqueEmployeeList.add(AMY);
        Iterator<Employee> iterator = uniqueEmployeeList.iterator();
        assertTrue(iterator.hasNext());
        assertEquals(ALICE, iterator.next());
        assertEquals(AMY, iterator.next());
        assertFalse(iterator.hasNext());
    }

    @Test
    public void equals() {
        // different type -> returns false
        assertFalse(uniqueEmployeeList.equals(new Object()));

        // same values -> returns true
        UniqueEmployeeList other = new UniqueEmployeeList();
        assertTrue(uniqueEmployeeList.equals(other));

        // different values -> returns false
        other.add(ALICE);
        assertFalse(uniqueEmployeeList.equals(other));

        // same object -> returns true
        assertTrue(uniqueEmployeeList.equals(uniqueEmployeeList));

        // null -> returns false
        assertFalse(uniqueEmployeeList.equals(null));
    }

    @Test
    public void toStringMethod() {
        assertEquals(uniqueEmployeeList.asUnmodifiableObservableList().toString(), uniqueEmployeeList.toString());
    }
}
