package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedPerson.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.BENSON;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Email;
import seedu.address.model.person.Gpa;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.StudentNumber;

public class JsonAdaptedPersonTest {
    private static final String INVALID_STUDENT_NUMBER = "A023C";
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final double INVALID_GPA = -1.0;
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_STUDENT_NUMBER = BENSON.getStudentNumber().toString();
    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_PHONE = BENSON.getPhone().toString();
    private static final String VALID_EMAIL = BENSON.getEmail().toString();
    private static final double VALID_GPA = BENSON.getGpa().value;
    private static final String VALID_PREVIOUS_GRADE = BENSON.getPreviousGrade().toString();
    private static final double VALID_INTERVIEW_SCORE = BENSON.getInterviewScore().get().value;
    private static final String VALID_COMMENT = BENSON.getComment().get().comment;
    private static final List<JsonAdaptedTag> VALID_TAGS = BENSON.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());
    private static final boolean VALID_IS_HIDDEN = BENSON.getIsHidden().value;
    private static final List<JsonAdaptedAttachment> VALID_ATTACHMENTS = BENSON.getAttachments().stream()
            .map(JsonAdaptedAttachment::new)
            .collect(Collectors.toList());
    private static final boolean VALID_IS_BOOKMARKED = BENSON.getIsBookmarked().value;

    @Test
    public void toModelType_validPersonDetails_returnsPerson() throws Exception {
        JsonAdaptedPerson person = new JsonAdaptedPerson(BENSON);
        assertEquals(BENSON, person.toModelType());
    }

    @Test
    public void toModelType_invalidStudentNumber_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(
            INVALID_STUDENT_NUMBER,
            VALID_NAME,
            VALID_PHONE,
            VALID_EMAIL,
            VALID_GPA,
            VALID_PREVIOUS_GRADE,
            VALID_INTERVIEW_SCORE,
            VALID_COMMENT,
            VALID_TAGS,
            VALID_ATTACHMENTS,
            VALID_IS_HIDDEN,
            VALID_IS_BOOKMARKED
        );
        String expectedMessage = StudentNumber.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullStudentNumber_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(
            null,
            VALID_NAME,
            VALID_PHONE,
            VALID_EMAIL,
            VALID_GPA,
            VALID_PREVIOUS_GRADE,
            VALID_INTERVIEW_SCORE,
            VALID_COMMENT,
            VALID_TAGS,
            VALID_ATTACHMENTS,
            VALID_IS_HIDDEN,
            VALID_IS_BOOKMARKED
        );
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, StudentNumber.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }


    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(
            VALID_STUDENT_NUMBER,
            INVALID_NAME,
            VALID_PHONE,
            VALID_EMAIL,
            VALID_GPA,
            VALID_PREVIOUS_GRADE,
            VALID_INTERVIEW_SCORE,
            VALID_COMMENT,
            VALID_TAGS,
            VALID_ATTACHMENTS,
            VALID_IS_HIDDEN,
            VALID_IS_BOOKMARKED
        );
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(
            VALID_STUDENT_NUMBER,
            null,
            VALID_PHONE,
            VALID_EMAIL,
            VALID_GPA,
            VALID_PREVIOUS_GRADE,
            VALID_INTERVIEW_SCORE,
            VALID_COMMENT,
            VALID_TAGS,
            VALID_ATTACHMENTS,
            VALID_IS_HIDDEN,
            VALID_IS_BOOKMARKED
        );
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(
            VALID_STUDENT_NUMBER,
            VALID_NAME,
            INVALID_PHONE,
            VALID_EMAIL,
            VALID_GPA,
            VALID_PREVIOUS_GRADE,
            VALID_INTERVIEW_SCORE,
            VALID_COMMENT,
            VALID_TAGS,
            VALID_ATTACHMENTS,
            VALID_IS_HIDDEN,
            VALID_IS_BOOKMARKED
        );
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(
            VALID_STUDENT_NUMBER,
            VALID_NAME,
            null,
            VALID_EMAIL,
            VALID_GPA,
            VALID_PREVIOUS_GRADE,
            VALID_INTERVIEW_SCORE,
            VALID_COMMENT,
            VALID_TAGS,
            VALID_ATTACHMENTS,
            VALID_IS_HIDDEN,
            VALID_IS_BOOKMARKED
        );
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(
            VALID_STUDENT_NUMBER,
            VALID_NAME,
            VALID_PHONE,
            INVALID_EMAIL,
            VALID_GPA,
            VALID_PREVIOUS_GRADE,
            VALID_INTERVIEW_SCORE,
            VALID_COMMENT,
            VALID_TAGS,
            VALID_ATTACHMENTS,
            VALID_IS_HIDDEN,
            VALID_IS_BOOKMARKED
        );
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(
            VALID_STUDENT_NUMBER,
            VALID_NAME,
            VALID_PHONE,
            null,
            VALID_GPA,
            VALID_PREVIOUS_GRADE,
            VALID_INTERVIEW_SCORE,
            VALID_COMMENT,
            VALID_TAGS,
            VALID_ATTACHMENTS,
            VALID_IS_HIDDEN,
            VALID_IS_BOOKMARKED
        );
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidGpa_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(
            VALID_STUDENT_NUMBER,
            VALID_NAME,
            VALID_PHONE,
            VALID_EMAIL,
            INVALID_GPA,
            VALID_PREVIOUS_GRADE,
            VALID_INTERVIEW_SCORE,
            VALID_COMMENT,
            VALID_TAGS,
            VALID_ATTACHMENTS,
            VALID_IS_HIDDEN,
            VALID_IS_BOOKMARKED
        );
        String expectedMessage = Gpa.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullGpa_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(
            VALID_STUDENT_NUMBER,
            VALID_NAME,
            VALID_PHONE,
            VALID_EMAIL,
            null,
            VALID_PREVIOUS_GRADE,
            VALID_INTERVIEW_SCORE,
            VALID_COMMENT,
            VALID_TAGS,
            VALID_ATTACHMENTS,
            VALID_IS_HIDDEN,
            VALID_IS_BOOKMARKED
        );
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Gpa.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedPerson person = new JsonAdaptedPerson(
            VALID_STUDENT_NUMBER,
            VALID_NAME,
            VALID_PHONE,
            VALID_EMAIL,
            VALID_GPA,
            VALID_PREVIOUS_GRADE,
            VALID_INTERVIEW_SCORE,
            VALID_COMMENT,
            invalidTags,
            VALID_ATTACHMENTS,
            VALID_IS_HIDDEN,
            VALID_IS_BOOKMARKED
        );
        assertThrows(IllegalValueException.class, person::toModelType);
    }

}
