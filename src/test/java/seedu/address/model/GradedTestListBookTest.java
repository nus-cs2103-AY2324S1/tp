package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalGradedTest.GT1;
import static seedu.address.testutil.TypicalGradedTest.GT2;

import org.junit.jupiter.api.Test;

import seedu.address.model.gradedtest.GradedTest;
import seedu.address.model.gradedtest.exceptions.DuplicateGradedTestException;
import seedu.address.model.gradedtest.exceptions.GradedTestNotFoundException;
import seedu.address.testutil.GradedTestBuilder;

public class GradedTestListBookTest {
    @Test
    public void addGradedTest_validGradedTest_success() {
        GradedTestListBook gradedTestListBook = new GradedTestListBook();
        GradedTest gradedTest = new GradedTestBuilder().build();

        gradedTestListBook.addGradedTest(gradedTest);

        assertTrue(gradedTestListBook.hasGradedTest(gradedTest));
    }

    @Test
    public void addGradedTest_duplicateGradedTest_throwsException() {
        GradedTestListBook gradedTestListBook = new GradedTestListBook();
        GradedTest gradedTest = new GradedTestBuilder().build();

        gradedTestListBook.addGradedTest(gradedTest);

        assertThrows(DuplicateGradedTestException.class, () -> gradedTestListBook.addGradedTest(gradedTest));
    }

    @Test
    public void removeGradedTest_validGradedTest_success() {
        GradedTestListBook gradedTestListBook = new GradedTestListBook();
        GradedTest gradedTest = new GradedTestBuilder().build();

        gradedTestListBook.addGradedTest(gradedTest);
        gradedTestListBook.removeGradedTest(gradedTest);

        assertFalse(gradedTestListBook.hasGradedTest(gradedTest));
    }

    @Test
    public void removeGradedTest_nonExistentGradedTest_throwsException() {
        GradedTestListBook gradedTestListBook = new GradedTestListBook();
        GradedTest gradedTest = new GradedTestBuilder().build();

        assertThrows(GradedTestNotFoundException.class, () -> gradedTestListBook.removeGradedTest(gradedTest));
    }

    @Test
    public void setGradedTest_validGradedTest_success() {
        GradedTestListBook gradedTestListBook = new GradedTestListBook();
        GradedTest gradedTest = GT1;

        gradedTestListBook.addGradedTest(gradedTest);

        GradedTest editedGradedTest = GT2;

        gradedTestListBook.setGradedTests(gradedTest, editedGradedTest);

        assertFalse(gradedTestListBook.hasGradedTest(gradedTest));
        assertTrue(gradedTestListBook.hasGradedTest(editedGradedTest));
    }

    @Test
    public void setGradedTest_nonExistentGradedTest_throwsException() {
        GradedTestListBook gradedTestListBook = new GradedTestListBook();
        GradedTest gradedTest = new GradedTestBuilder().build();
        GradedTest editedGradedTest = new GradedTestBuilder().build();

        assertThrows(GradedTestNotFoundException.class, () ->
                gradedTestListBook.setGradedTests(gradedTest, editedGradedTest));
    }

    @Test
    public void setGradedTest_duplicateGradedTest_throwsException() {
        GradedTestListBook gradedTestListBook = new GradedTestListBook();
        GradedTest gt1 = new GradedTestBuilder().withReadingAssessment1("1").build();
        GradedTest gt2 = new GradedTestBuilder().withReadingAssessment1("2").build();

        gradedTestListBook.addGradedTest(gt1);
        gradedTestListBook.addGradedTest(gt2);

        // Attempt to set gt1 to be a duplicate of gt2
        assertThrows(DuplicateGradedTestException.class, () -> gradedTestListBook.setGradedTests(gt1, gt2));
    }

    @Test
    public void getGradedTest_validIndex_success() {
        GradedTestListBook gradedTestListBook = new GradedTestListBook();
        GradedTest gradedTest = new GradedTestBuilder().build();

        gradedTestListBook.addGradedTest(gradedTest);

        GradedTest retrievedGradedTest = gradedTestListBook.getGradedTest(0);

        assertTrue(gradedTest.equals(retrievedGradedTest));
    }

    @Test
    public void hasGradedTest_validGradedTest_returnsTrue() {
        GradedTestListBook gradedTestListBook = new GradedTestListBook();
        GradedTest gradedTest = new GradedTestBuilder().build();

        gradedTestListBook.addGradedTest(gradedTest);

        assertTrue(gradedTestListBook.hasGradedTest(gradedTest));
    }

    @Test
    public void hasGradedTest_nonExistentGradedTest_returnsFalse() {
        GradedTestListBook gradedTestListBook = new GradedTestListBook();
        GradedTest gradedTest = new GradedTestBuilder().build();

        assertFalse(gradedTestListBook.hasGradedTest(gradedTest));
    }

}
