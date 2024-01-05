package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.gradedtest.Finals;
import seedu.address.model.gradedtest.GradedTest;
import seedu.address.model.gradedtest.MidTerms;
import seedu.address.model.gradedtest.PracticalExam;
import seedu.address.model.gradedtest.ReadingAssessment1;
import seedu.address.model.gradedtest.ReadingAssessment2;
import seedu.address.testutil.TypicalGradedTest;

public class JsonAdaptedGradedTestTest {
    @Test
    public void toModelType_validJsonAdaptedGradedTestWithDefaults_success() throws IllegalValueException {
        JsonAdaptedGradedTest jsonAdaptedGradedTest = new JsonAdaptedGradedTest(
                "80", "90", "-", "85", "-");

        GradedTest gradedTest = jsonAdaptedGradedTest.toModelType();
        ReadingAssessment1 ra1 = new ReadingAssessment1("80");
        ReadingAssessment2 ra2 = new ReadingAssessment2("90");
        MidTerms midTerms = new MidTerms("-");
        Finals finals = new Finals("85");
        PracticalExam pe = new PracticalExam("-");
        // Verify the converted GradedTest fields, including the defaults
        assertTrue(ra1.equals(gradedTest.getRA1()));
        assertTrue(ra2.equals(gradedTest.getRA2()));
        assertTrue(midTerms.equals(gradedTest.getMidTerms()));
        assertTrue(finals.equals(gradedTest.getFinals()));
        assertTrue(pe.equals(gradedTest.getPracticalExam()));
    }

    @Test
    public void toModelType_invalidJsonAdaptedGradedTest_throwsIllegalValueException1() {
        JsonAdaptedGradedTest jsonAdaptedGradedTest = new JsonAdaptedGradedTest(
                "80", "90", "abc", "85", "95");

        assertThrows(IllegalValueException.class, jsonAdaptedGradedTest::toModelType);
    }

    @Test
    public void toModelType_invalidJsonAdaptedGradedTest_throwsIllegalValueException2() {
        JsonAdaptedGradedTest jsonAdaptedGradedTest = new JsonAdaptedGradedTest(
                "-1", "90", "23", "85", "95");

        assertThrows(IllegalValueException.class, jsonAdaptedGradedTest::toModelType);
    }

    @Test
    public void toModelType_invalidJsonAdaptedGradedTest_throwsIllegalValueException3() {
        JsonAdaptedGradedTest jsonAdaptedGradedTest = new JsonAdaptedGradedTest(
                "23", "-90", "23", "85", "95");

        assertThrows(IllegalValueException.class, jsonAdaptedGradedTest::toModelType);
    }

    @Test
    public void toModelType_missingFields_throwsIllegalValueException() {
        JsonAdaptedGradedTest jsonAdaptedGradedTest = new JsonAdaptedGradedTest(null, "90", "75", "85", "95");
        assertThrows(IllegalValueException.class, jsonAdaptedGradedTest::toModelType);
    }

    @Test
    public void toModelType_validGradedTestDetails_returnsConsultation1() throws Exception {
        JsonAdaptedGradedTest gradedTest = new JsonAdaptedGradedTest(TypicalGradedTest.GT1);
        assertEquals(TypicalGradedTest.GT1, gradedTest.toModelType());
    }

    @Test
    public void toModelType_validGradedTestDetails_returnsConsultation2() throws Exception {
        JsonAdaptedGradedTest gradedTest = new JsonAdaptedGradedTest(TypicalGradedTest.GT2);
        assertEquals(TypicalGradedTest.GT2, gradedTest.toModelType());
    }

    @Test
    public void toModelType_validGradedTestDetails_returnsConsultation3() throws Exception {
        JsonAdaptedGradedTest gradedTest = new JsonAdaptedGradedTest(TypicalGradedTest.GT3);
        assertEquals(TypicalGradedTest.GT3, gradedTest.toModelType());
    }

    @Test
    public void toModelType_validGradedTestDetails_returnsConsultation4() throws Exception {
        JsonAdaptedGradedTest gradedTest = new JsonAdaptedGradedTest(TypicalGradedTest.GT4);
        assertEquals(TypicalGradedTest.GT4, gradedTest.toModelType());
    }
}
