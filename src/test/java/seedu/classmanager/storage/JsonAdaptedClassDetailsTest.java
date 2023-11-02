package seedu.classmanager.storage;

import static java.lang.Boolean.FALSE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.classmanager.storage.JsonAdaptedStudent.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.classmanager.testutil.Assert.assertThrows;
import static seedu.classmanager.testutil.TypicalStudents.BENSON;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.classmanager.commons.exceptions.IllegalValueException;
import seedu.classmanager.model.student.ClassDetails;
import seedu.classmanager.model.student.information.Assignment;
import seedu.classmanager.model.student.information.AssignmentTracker;
import seedu.classmanager.model.student.information.AttendanceTracker;
import seedu.classmanager.model.student.information.ClassParticipationTracker;

public class JsonAdaptedClassDetailsTest {
    private final String validClassNumber = BENSON.getClassDetails().toString();
    private final Boolean[] tutorialArray = new Boolean[ClassDetails.getTutorialCount()];
    private final Integer[] assignmentArray = new Integer[ClassDetails.getAssignmentCount()];
    private final List<Boolean> validAttendanceTracker = Arrays.asList(tutorialArray);
    private final List<Integer> validAssignmentTracker = Arrays.asList(assignmentArray);
    private final List<Boolean> validClassParticipationTracker = Arrays.asList(tutorialArray);

    @BeforeEach
    public void setUp() {
        Arrays.fill(tutorialArray, FALSE);
        Arrays.fill(assignmentArray, 0);
    }

    @Test
    public void toModelType_validClassDetails_returnsClassDetails() throws Exception {
        JsonAdaptedClassDetails classDetails = new JsonAdaptedClassDetails(validClassNumber,
                validAttendanceTracker,
                validAssignmentTracker,
                validClassParticipationTracker);
        ClassDetails expectedClassDetails = new ClassDetails(validClassNumber,
                new AttendanceTracker(validAttendanceTracker),
                new AssignmentTracker(validAssignmentTracker),
                new ClassParticipationTracker(validClassParticipationTracker));
        assertEquals(expectedClassDetails, classDetails.toModelType());
    }

    @Test
    public void toModelType_invalidClassNumber_throwsIllegalValueException() {
        String invalidClassNumber = "11";
        JsonAdaptedClassDetails classDetails =
                new JsonAdaptedClassDetails(invalidClassNumber,
                        validAttendanceTracker,
                        validAssignmentTracker,
                        validClassParticipationTracker);
        String expectedMessage = ClassDetails.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, classDetails::toModelType);
    }

    @Test
    public void toModelType_nullClassNumber_throwsIllegalValueException() {
        JsonAdaptedClassDetails classDetails = new JsonAdaptedClassDetails(null,
                validAttendanceTracker,
                validAssignmentTracker,
                validClassParticipationTracker);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT,
                ClassDetails.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, classDetails::toModelType);
    }

    @Test
    public void toModelType_invalidAssignmentTracker_throwsIllegalValueException() {
        List<Integer> invalidAssignmentTracker = new ArrayList<Integer>(validAssignmentTracker);
        invalidAssignmentTracker.set(0, -1);
        JsonAdaptedClassDetails classDetails =
                new JsonAdaptedClassDetails(validClassNumber,
                        validAttendanceTracker,
                        invalidAssignmentTracker,
                        validClassParticipationTracker);
        String expectedMessage = Assignment.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, classDetails::toModelType);
    }

    @Test
    public void toModelType_unequalLength_throwsIllegalValueException() {
        JsonAdaptedClassDetails classDetails = new JsonAdaptedClassDetails(validClassNumber,
                Arrays.asList(true, false),
                validAssignmentTracker,
                Arrays.asList(false, true, false));
        String expectedMessage = ClassDetails.MESSAGE_UNEQUAL_LENGTH;
        assertThrows(IllegalValueException.class, expectedMessage, classDetails::toModelType);
    }

}
