package seedu.address.model.department;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.DepartmentBuilder.DEPARTMENT_INVESTMENT;
import static seedu.address.testutil.DepartmentBuilder.DEPARTMENT_LOGISTICS;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.department.exceptions.DepartmentNotFoundException;
import seedu.address.model.department.exceptions.DuplicateDepartmentException;

public class UniqueDepartmentListTest {

    private final UniqueDepartmentList uniqueDepartmentList = new UniqueDepartmentList();

    @Test
    public void contains_nullDepartment_throwsNullPointerException() {
        Department department = null;
        assertThrows(NullPointerException.class, () -> uniqueDepartmentList.contains(department));
    }

    @Test
    public void contains_departmentNotInList_returnsFalse() {
        assertFalse(uniqueDepartmentList.contains(DEPARTMENT_INVESTMENT));
    }

    @Test
    public void contains_departmentInList_returnsTrue() {
        uniqueDepartmentList.add(DEPARTMENT_INVESTMENT);
        assertTrue(uniqueDepartmentList.contains(DEPARTMENT_INVESTMENT));
    }

    @Test
    public void add_nullDepartment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueDepartmentList.add(null));
    }

    @Test
    public void add_duplicateDepartment_throwsDuplicateDepartmentException() {
        uniqueDepartmentList.add(DEPARTMENT_INVESTMENT);
        assertThrows(DuplicateDepartmentException.class, () -> uniqueDepartmentList.add(DEPARTMENT_INVESTMENT));
    }

    @Test
    public void setDepartment_nullTargetDepartment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueDepartmentList.setDepartment(
                null, DEPARTMENT_INVESTMENT));
    }

    @Test
    public void setDepartment_nullEditedDepartment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueDepartmentList.setDepartment(
                DEPARTMENT_INVESTMENT, null));
    }

    @Test
    public void setDepartment_targetDepartmentNotInList_throwsDepartmentNotFoundException() {
        assertThrows(DepartmentNotFoundException.class, () -> uniqueDepartmentList.setDepartment(
                DEPARTMENT_INVESTMENT, DEPARTMENT_INVESTMENT));
    }

    @Test
    public void setDepartment_editedDepartmentIsSameDepartment_success() {
        uniqueDepartmentList.add(DEPARTMENT_INVESTMENT);
        uniqueDepartmentList.setDepartment(DEPARTMENT_INVESTMENT, DEPARTMENT_INVESTMENT);
        UniqueDepartmentList expectedUniqueDepartmentList = new UniqueDepartmentList();
        expectedUniqueDepartmentList.add(DEPARTMENT_INVESTMENT);
        assertEquals(expectedUniqueDepartmentList, uniqueDepartmentList);
    }

    @Test
    public void setDepartment_editedDepartmentHasDifferentIdentity_success() {
        uniqueDepartmentList.add(DEPARTMENT_INVESTMENT);
        uniqueDepartmentList.setDepartment(DEPARTMENT_INVESTMENT, DEPARTMENT_LOGISTICS);
        UniqueDepartmentList expectedUniqueDepartmentList = new UniqueDepartmentList();
        expectedUniqueDepartmentList.add(DEPARTMENT_LOGISTICS);
        assertEquals(expectedUniqueDepartmentList, uniqueDepartmentList);
    }

    @Test
    public void setDepartment_editedDepartmentHasNonUniqueIdentity_throwsDuplicateDepartmentException() {
        uniqueDepartmentList.add(DEPARTMENT_INVESTMENT);
        uniqueDepartmentList.add(DEPARTMENT_LOGISTICS);
        assertThrows(DuplicateDepartmentException.class, () -> uniqueDepartmentList.setDepartment(
                DEPARTMENT_INVESTMENT, DEPARTMENT_LOGISTICS));
    }

    @Test
    public void remove_nullDepartment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueDepartmentList.remove(null));
    }

    @Test
    public void remove_departmentDoesNotExist_throwsDepartmentNotFoundException() {
        assertThrows(DepartmentNotFoundException.class, () -> uniqueDepartmentList.remove(DEPARTMENT_LOGISTICS));
    }

    @Test
    public void remove_existingDepartment_removesDepartment() {
        uniqueDepartmentList.add(DEPARTMENT_INVESTMENT);
        uniqueDepartmentList.remove(DEPARTMENT_INVESTMENT);
        UniqueDepartmentList expectedUniqueDepartmentList = new UniqueDepartmentList();
        assertEquals(expectedUniqueDepartmentList, uniqueDepartmentList);
    }

    @Test
    public void setDepartments_nullUniqueDepartmentList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueDepartmentList.setDepartments(
                (UniqueDepartmentList) null));
    }

    @Test
    public void setDepartments_uniqueDepartmentList_replacesOwnListWithProvidedUniqueDepartmentList() {
        uniqueDepartmentList.add(DEPARTMENT_INVESTMENT);
        UniqueDepartmentList expectedUniqueDepartmentList = new UniqueDepartmentList();
        expectedUniqueDepartmentList.add(DEPARTMENT_LOGISTICS);
        uniqueDepartmentList.setDepartments(expectedUniqueDepartmentList);
        assertEquals(expectedUniqueDepartmentList, uniqueDepartmentList);
    }

    @Test
    public void setDepartments_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueDepartmentList.setDepartments((List<Department>) null));
    }

    @Test
    public void setDepartments_list_replacesOwnListWithProvidedList() {
        uniqueDepartmentList.add(DEPARTMENT_INVESTMENT);
        List<Department> departmentList = Collections.singletonList(DEPARTMENT_LOGISTICS);
        uniqueDepartmentList.setDepartments(departmentList);
        UniqueDepartmentList expectedUniqueDepartmentList = new UniqueDepartmentList();
        expectedUniqueDepartmentList.add(DEPARTMENT_LOGISTICS);
        assertEquals(expectedUniqueDepartmentList, uniqueDepartmentList);
    }

    @Test
    public void setDepartments_listWithDuplicateDepartments_throwsDuplicateDepartmentException() {
        List<Department> listWithDuplicatePeople = Arrays.asList(DEPARTMENT_INVESTMENT, DEPARTMENT_INVESTMENT);
        assertThrows(DuplicateDepartmentException.class, () -> uniqueDepartmentList
                .setDepartments(listWithDuplicatePeople));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
                -> uniqueDepartmentList.asUnmodifiableObservableList().remove(0));
    }

    @Test
    public void equals() {
        // different type -> returns false
        assertFalse(uniqueDepartmentList.equals(new Object()));

        // same values -> returns true
        UniqueDepartmentList other = new UniqueDepartmentList();
        assertTrue(uniqueDepartmentList.equals(other));

        // different values -> returns false
        other.add(DEPARTMENT_INVESTMENT);
        assertFalse(uniqueDepartmentList.equals(other));

        // same object -> returns true
        assertTrue(uniqueDepartmentList.equals(uniqueDepartmentList));

        // null -> returns false
        assertFalse(uniqueDepartmentList.equals(null));
    }

    @Test
    public void toStringMethod() {
        assertEquals(uniqueDepartmentList.asUnmodifiableObservableList().toString(), uniqueDepartmentList.toString());
    }
}
