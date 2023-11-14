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
import seedu.address.model.person.Description;
import seedu.address.model.person.Email;
import seedu.address.model.person.Major;
import seedu.address.model.person.Name;
import seedu.address.model.person.Year;

public class JsonAdaptedPersonTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_MAJOR = "Computer Games";
    private static final String INVALID_YEAR = "1.5";
    private static final String INVALID_DESCRIPTION = "";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TUTORIAL = "1";
    private static final String INVALID_SM = "#www.invalid.com";

    private static final String INVALID_NATIONALITY = "singaporean";
    private static final String INVALID_GENDER = "male";
    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_MAJOR = BENSON.getMajor().toString();
    private static final String VALID_YEAR = BENSON.getYear().toString();
    private static final String VALID_EMAIL = BENSON.getEmail().toString();
    private static final String VALID_DESCRIPTION = BENSON.getDescription().toString();
    private static final String VALID_NATIONALITY = BENSON.getNationality().toString();
    private static final String VALID_GENDER = BENSON.getGender().toString();
    private static final List<JsonAdaptedTutorial> VALID_TUTORIALS = BENSON.getTutorials().stream()
            .map(JsonAdaptedTutorial::new)
            .collect(Collectors.toList());
    private static final List<JsonAdaptedSocialMedia> VALID_SM = BENSON.getSocialMediaLinks().stream()
            .map(JsonAdaptedSocialMedia::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validPersonDetails_returnsPerson() throws Exception {
        JsonAdaptedPerson person = new JsonAdaptedPerson(BENSON);
        assertEquals(BENSON, person.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(INVALID_NAME, VALID_MAJOR, VALID_YEAR, VALID_EMAIL, VALID_DESCRIPTION,
                            VALID_TUTORIALS, VALID_SM, VALID_NATIONALITY, VALID_GENDER);

        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(null, VALID_MAJOR, VALID_YEAR, VALID_EMAIL, VALID_DESCRIPTION,
                            VALID_TUTORIALS, VALID_SM, VALID_NATIONALITY, VALID_GENDER);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidMajor_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, INVALID_MAJOR, VALID_YEAR, VALID_EMAIL, VALID_DESCRIPTION,
                            VALID_TUTORIALS, VALID_SM, VALID_NATIONALITY, VALID_GENDER);
        String expectedMessage = Major.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullMajor_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, null, VALID_YEAR, VALID_EMAIL, VALID_DESCRIPTION, VALID_TUTORIALS,
                            VALID_SM, VALID_NATIONALITY, VALID_GENDER);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Major.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidYear_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_MAJOR, INVALID_YEAR, VALID_EMAIL, VALID_DESCRIPTION,
                            VALID_TUTORIALS, VALID_SM, VALID_NATIONALITY, VALID_GENDER);
        String expectedMessage = Year.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullYear_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_MAJOR, null, VALID_EMAIL, VALID_DESCRIPTION,
                            VALID_TUTORIALS, VALID_SM, VALID_NATIONALITY, VALID_GENDER);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Year.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_MAJOR, VALID_YEAR, INVALID_EMAIL, VALID_DESCRIPTION,
                            VALID_TUTORIALS, VALID_SM, VALID_NATIONALITY, VALID_GENDER);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_MAJOR, VALID_YEAR, null, VALID_DESCRIPTION,
                            VALID_TUTORIALS, VALID_SM, VALID_NATIONALITY, VALID_GENDER);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidDescription_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_MAJOR, VALID_YEAR, VALID_EMAIL, INVALID_DESCRIPTION,
                            VALID_TUTORIALS, VALID_SM, VALID_NATIONALITY, VALID_GENDER);

        String expectedMessage = Description.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullDescription_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_MAJOR, VALID_YEAR, VALID_EMAIL, null,
                            VALID_TUTORIALS, VALID_SM, VALID_NATIONALITY, VALID_GENDER);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Description.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidTutorials_throwsIllegalValueException() {
        List<JsonAdaptedTutorial> invalidTutorials = new ArrayList<>(VALID_TUTORIALS);
        invalidTutorials.add(new JsonAdaptedTutorial(INVALID_TUTORIAL));
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_MAJOR, VALID_YEAR, VALID_EMAIL, VALID_DESCRIPTION,
                    invalidTutorials, VALID_SM, VALID_NATIONALITY, VALID_GENDER);
        assertThrows(IllegalValueException.class, person::toModelType);
    }

    @Test
    public void toModelType_invalidSocialMediaLinks_throwsIllegalValueException() {
        List<JsonAdaptedSocialMedia> invalidSocialMediaLinks = new ArrayList<>(VALID_SM);
        invalidSocialMediaLinks.add(new JsonAdaptedSocialMedia(INVALID_SM));
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_MAJOR, VALID_YEAR, VALID_EMAIL, VALID_DESCRIPTION,
                            VALID_TUTORIALS, invalidSocialMediaLinks, VALID_NATIONALITY, VALID_GENDER);
        assertThrows(IllegalValueException.class, person::toModelType);
    }

}
