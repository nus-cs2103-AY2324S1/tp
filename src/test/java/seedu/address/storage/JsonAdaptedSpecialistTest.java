package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedPerson.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.GEORGE;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Location;
import seedu.address.model.person.Specialty;


public class JsonAdaptedSpecialistTest {
    private static final String INVALID_LOCATION = " ";
    private static final String INVALID_SPECIALTY = "";

    private static final String VALID_NAME = GEORGE.getName().toString();
    private static final String VALID_PHONE = GEORGE.getPhone().toString();
    private static final String VALID_EMAIL = GEORGE.getEmail().toString();
    private static final String VALID_LOCATION = GEORGE.getLocation().toString();
    private static final String VALID_SPECIALTY = GEORGE.getSpecialty().toString();
    private static final List<JsonAdaptedTag> VALID_TAGS = GEORGE.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());


    @Test
    public void toModelType_invalidLocation_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedSpecialist(VALID_NAME, VALID_PHONE, VALID_EMAIL, INVALID_LOCATION, VALID_TAGS,
                        VALID_SPECIALTY);
        String expectedMessage = Location.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullLocation_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedSpecialist(VALID_NAME, VALID_PHONE, VALID_EMAIL, null, VALID_TAGS,
                        VALID_SPECIALTY);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT,
                Location.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_validSpecialistDetails_returnsSpecialist() throws Exception {
        JsonAdaptedSpecialist specialist = new JsonAdaptedSpecialist(GEORGE);
        assertEquals(GEORGE, specialist.toModelType());
    }

    @Test
    public void toModelType_invalidSpecialty_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedSpecialist(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_LOCATION, VALID_TAGS,
                        INVALID_SPECIALTY);
        String expectedMessage = Specialty.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullSpeciality_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedSpecialist(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_LOCATION,
                VALID_TAGS, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Specialty.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }
}
