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
import seedu.address.model.person.Name;

public class JsonAdaptedPersonTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_ROLE = "Teacher";
    private static final String INVALID_CONTACT = " ";
    private static final String INVALID_COURSE = "@@@@";
    private static final String INVALID_TUTORIAL = "cs2100/ f";

    private static final String VALID_NAME = BENSON.getName().toString();
    private static final List<JsonAdaptedRole> VALID_ROLES = BENSON.getRoles().stream()
            .map(JsonAdaptedRole::new)
            .collect(Collectors.toList());
    private static final List<JsonAdaptedContact> VALID_CONTACTS = BENSON.getContacts().stream()
            .map(JsonAdaptedContact::new)
            .collect(Collectors.toList());
    private static final List<JsonAdaptedCourse> VALID_COURSES = BENSON.getCourses().stream()
            .map(JsonAdaptedCourse::new)
            .collect(Collectors.toList());
    private static final List<JsonAdaptedTutorial> VALID_TUTORIALS = BENSON.getTutorials().stream()
            .map(JsonAdaptedTutorial::new)
            .collect(Collectors.toList());

    private static final boolean VALID_FAVOURITE = false;

    @Test
    public void toModelType_validPersonDetails_returnsPerson() throws Exception {
        JsonAdaptedPerson person = new JsonAdaptedPerson(BENSON);
        assertEquals(BENSON, person.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(INVALID_NAME, VALID_ROLES, VALID_CONTACTS, VALID_COURSES, VALID_TUTORIALS,
                        VALID_FAVOURITE);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(null, VALID_ROLES, VALID_CONTACTS, VALID_COURSES,
                VALID_TUTORIALS, VALID_FAVOURITE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidRoles_throwsIllegalValueException() {
        List<JsonAdaptedRole> invalidRoles = new ArrayList<>(VALID_ROLES);
        invalidRoles.add(new JsonAdaptedRole(INVALID_ROLE));
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, invalidRoles, VALID_CONTACTS, VALID_COURSES, VALID_TUTORIALS,
                        VALID_FAVOURITE);
        assertThrows(IllegalValueException.class, person::toModelType);
    }

    @Test
    public void toModelType_invalidContacts_throwsIllegalValueException() {
        List<JsonAdaptedContact> invalidContacts = new ArrayList<>(VALID_CONTACTS);
        invalidContacts.add(new JsonAdaptedContact(INVALID_CONTACT));
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_ROLES, invalidContacts, VALID_COURSES, VALID_TUTORIALS,
                        VALID_FAVOURITE);
        assertThrows(IllegalValueException.class, person::toModelType);
    }

    @Test
    public void toModelType_invalidCourses_throwsIllegalValueException() {
        List<JsonAdaptedCourse> invalidCourses = new ArrayList<>(VALID_COURSES);
        invalidCourses.add(new JsonAdaptedCourse(INVALID_COURSE));
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_ROLES, VALID_CONTACTS, invalidCourses, VALID_TUTORIALS,
                        VALID_FAVOURITE);
        assertThrows(IllegalValueException.class, person::toModelType);
    }


    @Test
    public void toModelType_invalidTutorials_throwsIllegalValueException() {
        List<JsonAdaptedTutorial> invalidTutorials = new ArrayList<>(VALID_TUTORIALS);
        invalidTutorials.add(new JsonAdaptedTutorial(INVALID_TUTORIAL));
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_ROLES, VALID_CONTACTS, VALID_COURSES, invalidTutorials,
                        VALID_FAVOURITE);
        assertThrows(IllegalValueException.class, person::toModelType);
    }

}
