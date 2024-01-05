package seedu.address.model.gradedtest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalGradedTest.GT1;
import static seedu.address.testutil.TypicalGradedTest.GT2;
import static seedu.address.testutil.TypicalGradedTest.GT3;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.gradedtest.exceptions.DuplicateGradedTestException;
import seedu.address.model.gradedtest.exceptions.GradedTestNotFoundException;


public class GradedTestListTest {
    private final GradedTestList gradedTestList = new GradedTestList();

    @Test
    public void contains_nullGradedTest_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> gradedTestList.contains(null));
    }

    @Test
    public void contains_gradedTestNotInList_returnsFalse() {
        assertFalse(gradedTestList.contains(GT1));
    }

    @Test
    public void contains_gradedTestInList_returnsTrue() {
        gradedTestList.add(GT1);
        assertTrue(gradedTestList.contains(GT1));
    }

    @Test
    public void add_nullGradedTest_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> gradedTestList.add(null));
    }

    @Test
    public void add_duplicateGradedTest_throwsDuplicateGradedTestException() {
        gradedTestList.add(GT1);
        assertThrows(DuplicateGradedTestException.class, () -> gradedTestList.add(GT1));
    }

    @Test
    public void editGradedTest_nullTargetGradedTest_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> gradedTestList.editGradedTest(null, GT1));
    }

    @Test
    public void editGradedTest_nullEditedGradedTest_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> gradedTestList.editGradedTest(GT1, null));
    }

    @Test
    public void editGradedTest_throwsExceptionsForNullArguments() {
        gradedTestList.add(GT1);
        GradedTest target = GT2;
        GradedTest editedGradedTest = GT3;
        gradedTestList.add(target);

        // Test for null arguments
        assertThrows(NullPointerException.class, () -> {
            gradedTestList.editGradedTest(target, null);
        });

        assertThrows(NullPointerException.class, () -> {
            gradedTestList.editGradedTest(null, editedGradedTest);
        });
    }

    @Test
    public void editGradedTest_targetGradedTestNotInList_throwsGradedTestNotFoundException() {
        assertThrows(GradedTestNotFoundException.class, () -> gradedTestList.editGradedTest(GT1, GT1));
    }

    @Test
    public void setGradedTest_editedGradedTestIsSameGradedTest_success() {
        gradedTestList.add(GT1);
        gradedTestList.editGradedTest(GT1, GT1);
        GradedTestList expectedGradedTestList = new GradedTestList();
        expectedGradedTestList.add(GT1);
        assertTrue(expectedGradedTestList.equals(gradedTestList));
    }

    @Test
    public void setGradedTest_editedGradedeTestHasDifferentIdentity_success() {
        gradedTestList.add(GT1);
        gradedTestList.editGradedTest(GT1, GT2);
        GradedTestList expectedGradedTestList = new GradedTestList();
        expectedGradedTestList.add(GT2);
        assertTrue(expectedGradedTestList.equals(gradedTestList));
    }

    @Test
    public void editGradedTest_throwsGradedTestNotFoundException1() {
        gradedTestList.add(GT1);
        GradedTest target = GT2;
        GradedTest editedGradedTest = GT3;

        // Test the GradedTestNotFoundException
        assertThrows(GradedTestNotFoundException.class, () -> {
            gradedTestList.editGradedTest(target, editedGradedTest);
        });
    }

    @Test
    public void editGradedTest_throwsGradedTestNotFoundException2() {
        // Create a GradedTestList instance and add some initial data
        gradedTestList.add(GT1);
        gradedTestList.add(GT2);
        GradedTest target = GT3;
        GradedTest editedGradedTest = GT2;

        // Test the GradedTestNotFoundException
        // target instance not added into gradedTestList
        assertThrows(GradedTestNotFoundException.class, () -> {
            gradedTestList.editGradedTest(target, editedGradedTest);
        });
    }

    @Test
    public void editGradedTest_throwsDuplicateGradedTestException1() {
        // Create a GradedTestList instance and add some initial data
        gradedTestList.add(GT1);
        GradedTest target = GT2;
        GradedTest editedGradedTest = GT1;

        // Add the target to the list and also add the editedGradedTest
        gradedTestList.add(target);

        // Test the DuplicateGradedTestException
        assertThrows(DuplicateGradedTestException.class, () -> {
            gradedTestList.editGradedTest(target, editedGradedTest);
        });
    }

    @Test
    public void editGradedTest_throwsDuplicateGradedTestException2() {
        // Create a GradedTestList instance and add some initial data
        gradedTestList.add(GT1);
        gradedTestList.add(GT2);
        GradedTest target = GT3;
        gradedTestList.add(target);
        GradedTest editedGradedTest = GT2;

        // Test the DuplicateGradedTestException
        assertThrows(DuplicateGradedTestException.class, () -> {
            gradedTestList.editGradedTest(target, editedGradedTest);
        });
    }

    @Test
    public void setGradedTest_editedGradedTestHasNonUniqueIdentity_throwsDuplicateGradedTestException() {
        gradedTestList.add(GT1);
        gradedTestList.add(GT2);
        assertThrows(DuplicateGradedTestException.class, () -> gradedTestList.editGradedTest(GT1, GT2));
    }

    @Test
    public void editGradedTest_duplicateGradedTest_throwsDuplicateGradedTestException() {
        gradedTestList.add(GT1);
        gradedTestList.add(GT2);
        gradedTestList.add(GT3);
        assertThrows(DuplicateGradedTestException.class, () -> gradedTestList.editGradedTest(GT1, GT3));
    }

    @Test
    public void editGradedTest_success() {
        gradedTestList.add(GT1);
        gradedTestList.editGradedTest(GT1, GT2);
        GradedTestList expectedGradedTestList = new GradedTestList();
        expectedGradedTestList.add(GT2);
        assertEquals(expectedGradedTestList, gradedTestList);
    }

    @Test
    public void remove_nullGradedTest_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> gradedTestList.remove(null));
    }

    @Test
    public void remove_gradedTestDoesNotExist_throwsGradedTestNotFoundException() {
        assertThrows(GradedTestNotFoundException.class, () -> gradedTestList.remove(GT1));
    }

    @Test
    public void remove_existingGradedTest_removesGradedTest() {
        gradedTestList.add(GT1);
        gradedTestList.remove(GT1);
        GradedTestList expectedGradedTestList = new GradedTestList();
        assertEquals(expectedGradedTestList, gradedTestList);
    }

    @Test
    public void setGradedTest_nullUniqueGradedTestList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> gradedTestList.setGradedTests((GradedTestList) null));
    }

    @Test
    public void setGradedTest_uniqueGradedTestList_replacesOwnListWithProvidedUniqueGradedTestList() {
        gradedTestList.add(GT1);
        GradedTestList expectedGradedTestList = new GradedTestList();
        expectedGradedTestList.add(GT2);
        gradedTestList.setGradedTests(expectedGradedTestList);
        assertEquals(expectedGradedTestList, gradedTestList);
    }

    @Test
    public void setGradedTest_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> gradedTestList.setGradedTests((List<GradedTest>) null));
    }

    @Test
    public void setGradedTest_list_replacesOwnListWithProvidedList() {
        gradedTestList.add(GT1);
        List<GradedTest> otherGradedTestList = Collections.singletonList(GT1);
        gradedTestList.setGradedTests(otherGradedTestList);
        GradedTestList expectedGradedTestList = new GradedTestList();
        expectedGradedTestList.add(GT1);
        assertEquals(expectedGradedTestList, gradedTestList);
    }

    @Test
    public void setGradedTest_listWithDuplicateGradedTest_throwsDuplicateGradedTestException() {
        List<GradedTest> listWithDuplicateGradedTest = Arrays.asList(GT1, GT1);
        assertThrows(DuplicateGradedTestException.class, () ->
                gradedTestList.setGradedTests(listWithDuplicateGradedTest));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
                -> gradedTestList.asUnmodifiableObservableList().remove(0));
    }

    @Test
    public void getGradedTest_indexWithinBounds_success() {
        gradedTestList.add(GT1);
        assertEquals(GT1, gradedTestList.getGradedTest(0));
    }

    @Test
    public void getGradedTest_indexOutsideBounds_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> gradedTestList.getGradedTest(0));
    }

    @Test
    public void toStringMethod() {
        assertEquals(gradedTestList.asUnmodifiableObservableList().toString(), gradedTestList.toString());
    }

    @Test
    public void equalsTest() {
        GradedTestList gradedTestList1 = new GradedTestList();
        GradedTestList gradedTestList2 = new GradedTestList();

        // Test when both lists are empty
        assertTrue(gradedTestList1.equals(gradedTestList2));

        // Add a graded test to one list and test for inequality
        gradedTestList1.add(GT1);
        assertFalse(gradedTestList1.equals(gradedTestList2));

        // Add the same graded test to the other list and test for equality
        gradedTestList2.add(GT1);
        assertTrue(gradedTestList1.equals(gradedTestList2));
    }

    @Test
    public void hashCodeTest() {
        GradedTestList gradedTestList1 = new GradedTestList();
        GradedTestList gradedTestList2 = new GradedTestList();

        // Test when both lists are empty
        assertEquals(gradedTestList1.hashCode(), gradedTestList2.hashCode());

        // Add a graded test to one list and test for inequality
        gradedTestList1.add(GT1);
        assertNotEquals(gradedTestList1.hashCode(), gradedTestList2.hashCode());

        // Add the same graded test to the other list and test for equality
        gradedTestList2.add(GT1);
        assertEquals(gradedTestList1.hashCode(), gradedTestList2.hashCode());
    }

    @Test
    public void addAndRemoveGradedTests() {
        GradedTestList gradedTestList = new GradedTestList();

        // Add a graded test
        gradedTestList.add(GT1);
        assertTrue(gradedTestList.contains(GT1));

        // Remove the added graded test
        gradedTestList.remove(GT1);
        assertFalse(gradedTestList.contains(GT1));

        // Try to remove a non-existent graded test (should throw exception)
        assertThrows(GradedTestNotFoundException.class, () -> gradedTestList.remove(GT1));
    }

    @Test
    public void setGradedTestListWithDuplicateGradedTests() {
        GradedTestList gradedTestList = new GradedTestList();
        gradedTestList.add(GT2);

        // Create a list with duplicate graded tests
        List<GradedTest> duplicateGradedTestList = Arrays.asList(GT2, GT2);

        // Setting the list should throw DuplicateGradedTestException
        assertThrows(DuplicateGradedTestException.class, () -> gradedTestList.setGradedTests(duplicateGradedTestList));
    }

    @Test
    public void getGradedTestWithInvalidIndex() {
        GradedTestList gradedTestList = new GradedTestList();
        gradedTestList.add(GT2);

        assertThrows(IllegalArgumentException.class, () -> gradedTestList.getGradedTest(1));
    }

    @Test
    public void setGradedTestsWithNullList() {
        GradedTestList gradedTestList = new GradedTestList();
        List<GradedTest> nullList = null;

        assertThrows(NullPointerException.class, () -> gradedTestList.setGradedTests(nullList));
    }

    @Test
    public void unmodifiableListModification() {
        GradedTestList gradedTestList = new GradedTestList();
        gradedTestList.add(GT2);

        assertThrows(UnsupportedOperationException.class, () ->
                gradedTestList.asUnmodifiableObservableList().remove(0));
    }
}
