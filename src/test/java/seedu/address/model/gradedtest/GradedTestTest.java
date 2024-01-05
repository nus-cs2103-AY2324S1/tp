package seedu.address.model.gradedtest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GRADED_TEST_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GRADED_TEST_2;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalGradedTest.GT1;
import static seedu.address.testutil.TypicalGradedTest.GT3;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.testutil.GradedTestBuilder;


public class GradedTestTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new GradedTest(null));
    }

    @Test
    public void constructor_invalidGradedTestName_throwsIllegalArgumentException() {
        String invalidGradedTestName = "%$#%#%";
        assertThrows(IllegalArgumentException.class, () -> new GradedTest(invalidGradedTestName));
    }

    @Test
    public void constructor_invalidGradedTestNameDefault_throwsIllegalArgumentException() {
        String validGradedTestName = "default";
        assertTrue(() -> GradedTest.isValidGradeTestNameDefault(validGradedTestName));
        assertFalse(() -> GradedTest.isValidGradeTestName(validGradedTestName));
    }

    @Test
    public void constructor_isValidGradedTestName_throwsNullPointerException() {
        // null gradedTest name
        assertThrows(NullPointerException.class, () -> GradedTest.isValidGradeTestName(null));
    }

    @Test
    public void constructor_isValidGradedTestNameDefault_throwsNullPointerException() {
        // null gradedTest name
        assertThrows(NullPointerException.class, () -> GradedTest.isValidGradeTestNameDefault(null));
    }

    @Test
    public void constructor_validGradedTestNameSmallLetters_pass() {
        String testName = "default";
        assertTrue(GradedTest.isValidGradeTestNameDefault(testName));
    }

    @Test
    public void constructor_validGradedTestNameBlockLetters_pass() {
        String testName = "DEFAULT";
        assertTrue(GradedTest.isValidGradeTestNameDefault(testName));
    }

    @Test
    public void constructor_validGradedTestNameMixCase_pass() {
        String testName = "dEfAUlT";
        assertTrue(GradedTest.isValidGradeTestNameDefault(testName));
    }

    @Test
    public void constructor_gradedTest_pass() {
        String testName = "dEfAUlT";
        String expected = "-";
        GradedTest gradedTest = new GradedTest(testName);
        assertEquals(gradedTest.getRA1().toString(), expected);
        assertEquals(gradedTest.getRA2().toString(), expected);
        assertEquals(gradedTest.getMidTerms().toString(), expected);
        assertEquals(gradedTest.getFinals().toString(), expected);
        assertEquals(gradedTest.getPracticalExam().toString(), expected);
    }


    @Test
    public void testValidateFieldValidRA1_valid() {
        GradedTest gradedTest = new GradedTest(VALID_GRADED_TEST_1);
        String result = gradedTest.validateField("RA1", "-");
        assertEquals(null, result);
    }

    @Test
    public void testValidateFieldInvalidRA1_invalid() {
        GradedTest gradedTest = new GradedTest(VALID_GRADED_TEST_1);
        String result = gradedTest.validateField("RA1", "-100");
        assertEquals(ReadingAssessment1.MESSAGE_CONSTRAINTS, result);
    }

    @Test
    public void testValidateFieldValidRA2_valid() {
        GradedTest gradedTest = new GradedTest(VALID_GRADED_TEST_1);
        String result = gradedTest.validateField("RA2", "4564132");
        assertEquals(null, result);
    }

    @Test
    public void testValidateFieldInvalidRA2_invalid() {
        GradedTest gradedTest = new GradedTest(VALID_GRADED_TEST_1);
        String result = gradedTest.validateField("RA2", "^#*&");
        assertEquals(ReadingAssessment2.MESSAGE_CONSTRAINTS, result);
    }

    @Test
    public void testValidateFieldValidMidTerms_valid() {
        GradedTest gradedTest = new GradedTest(VALID_GRADED_TEST_1);
        String result = gradedTest.validateField("MidTerms", "123.456");
        assertEquals(null, result);
    }

    @Test
    public void testValidateFieldInvalidMidTerms_invalid() {
        GradedTest gradedTest = new GradedTest(VALID_GRADED_TEST_1);
        String result = gradedTest.validateField("MidTerms", "onetwothree");
        assertEquals(MidTerms.MESSAGE_CONSTRAINTS, result);
    }

    @Test
    public void testValidateFieldValidFinals_valid() {
        GradedTest gradedTest = new GradedTest(VALID_GRADED_TEST_2);
        String result = gradedTest.validateField("Finals", "4");
        assertEquals(null, result);
    }

    @Test
    public void testValidateFieldValidFinals_invalid() {
        GradedTest gradedTest = new GradedTest(VALID_GRADED_TEST_2);
        String result = gradedTest.validateField("Finals", "--");
        assertEquals(Finals.MESSAGE_CONSTRAINTS, result);
    }

    @Test
    public void testValidateFieldInvalidPE_valid() {
        GradedTest gradedTest = new GradedTest(VALID_GRADED_TEST_2);
        String result = gradedTest.validateField("PE", "-"); // 1 - is ok
        assertEquals(null, result);
    }
    @Test
    public void testValidateFieldInvalidPE_invalid() {
        GradedTest gradedTest = new GradedTest(VALID_GRADED_TEST_2);
        String result = gradedTest.validateField("PE", "-----------"); // Cant have so many -
        assertEquals(PracticalExam.MESSAGE_CONSTRAINTS, result);
    }

    @Test
    public void gradedTest_invalidInput1_throwsParseException() {
        String invalidInput = "RA1:90 RA2:85 MidTerms:75 Finals:80 PE:95"; // not separated by |
        assertThrows(IllegalArgumentException.class, () -> new GradedTest(invalidInput));
    }

    @Test
    public void gradedTest_invalidInput2_throwsParseException() {
        // No spacing fails for parseGradedTest
        String invalidInput = "RA1:90|RA2:85|MidTerms:75|Finals:80|PE:95"; // no spacing between |
        assertThrows(IllegalArgumentException.class, () -> new GradedTest(invalidInput));
    }

    @Test
    public void parseGradedTest_validInput1_throwsParseException() {
        String validInput = "RA1:90 | RA2:85 | MidTerms:75 | Finals:80 | PE:95";
        assertDoesNotThrow(()-> GradedTest.parseGradedTest(validInput));
    }

    @Test
    public void parseGradedTest_validInput2_throwsParseException() {
        String validInput = "RA1:- | RA2:- | MidTerms:75 | Finals:80 | PE:-";
        assertDoesNotThrow(()-> GradedTest.parseGradedTest(validInput));
    }

    @Test
    public void parseGradedTest_validInput3_throwsParseException() {
        // No spacing passes for parseGradedTest
        String validInput = "RA1:-|RA2:-|MidTerms:75|Finals:80|PE:-";
        assertDoesNotThrow(()-> GradedTest.parseGradedTest(validInput));
    }

    @Test
    public void parseGradedTest_invalidInput1_throwsParseException() {
        String validInput = "RA1:90 | RA2:85 |";
        assertThrows(ParseException.class, ()-> GradedTest.parseGradedTest(validInput));
    }

    @Test
    public void parseGradedTest_invalidInput2_throwsParseException() {
        String validInput = "RA1=- | RA2=- | MidTerms=75 | Finals=80 | PE=-";
        assertThrows(ParseException.class, ()-> GradedTest.parseGradedTest(validInput));
    }

    @Test
    public void parseGradedTest_invalidInput3_throwsParseException() {
        String validInput = "RA1:- | RA2:- | MidTerms:75 | Finals:80 | PE:- | INVALIDFIELD:-";
        assertThrows(ParseException.class, ()-> GradedTest.parseGradedTest(validInput));
    }


    @Test
    public void isSameGradedTest() {
        // same object -> returns true
        assertTrue(GT1.isSameGradedTest(GT1));

        // null -> returns false
        assertFalse(GT1.isSameGradedTest(null));

        // same test -> returns true
        GradedTest editedGradedTest1 = new GradedTestBuilder(GT1).build();
        assertTrue(GT1.isSameGradedTest(editedGradedTest1));

        // different finals -> returns false
        editedGradedTest1 = new GradedTestBuilder(GT1).withFinals("100").build();
        assertFalse(GT1.isSameGradedTest(editedGradedTest1));

        // different Pe -> returns false
        editedGradedTest1 = new GradedTestBuilder(GT1).withPe("100").build();
        assertFalse(GT1.isSameGradedTest(editedGradedTest1));

        // different Pe,RA1, RA2 -> returns false
        editedGradedTest1 = new GradedTestBuilder(GT1).withPe("100")
                .withReadingAssessment1("1").withReadingAssessment2("2")
                .build();
        assertFalse(GT1.isSameGradedTest(editedGradedTest1));

    }

    @Test
    public void equals() {
        // same values -> returns true
        GradedTest gradedTestCopy = new GradedTestBuilder(GT1).build();
        assertTrue(GT1.equals(gradedTestCopy));

        // same object -> returns true
        assertTrue(GT1.equals(GT1));

        // null -> returns false
        assertFalse(GT1.equals(null));

        // different type -> returns false
        assertFalse(GT1.equals(5));

        // different gradedTest -> returns false
        assertFalse(GT1.equals(GT3));

        // different pe value -> return false
        GradedTest editedGradedTest2 = new GradedTestBuilder(GT1)
                .withPe(GT3.getPracticalExam().value).build();
        assertFalse(GT1.equals(editedGradedTest2));

        // different finals value -> return false
        editedGradedTest2 = new GradedTestBuilder(GT1).withFinals(GT3
                .getFinals().value).build();
        assertFalse(GT1.equals(editedGradedTest2));

    }

    @Test
    public void toStringMethod() {
        String expected = String.format("%s{RA1:%s, RA2:%s, MidTerms:%s, Finals:%s, PE:%s}",
                GradedTest.class.getCanonicalName(), GT1.getRA1(), GT1.getRA2(),
                GT1.getMidTerms(), GT1.getFinals(), GT1.getPracticalExam());
        assertEquals(expected, expected);
    }
}
