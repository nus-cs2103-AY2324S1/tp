package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.GEORGE;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Specialty;


public class JsonAdaptedSpecialistTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TAG = "#friend";
    private static final String INVALID_SPECIALTY = "";

    private static final String VALID_NAME = GEORGE.getName().toString();
    private static final String VALID_PHONE = GEORGE.getPhone().toString();
    private static final String VALID_EMAIL = GEORGE.getEmail().toString();
    private static final String VALID_ADDRESS = GEORGE.getAddress().toString();
    private static final String VALID_SPECIALTY = GEORGE.getSpecialty().toString();
    private static final List<JsonAdaptedTag> VALID_TAGS = GEORGE.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());
    @Test
    public void toModelType_validSpecialistDetails_returnsSpecialist() throws Exception {
        JsonAdaptedSpecialist specialist = new JsonAdaptedSpecialist(GEORGE);
        assertEquals(GEORGE, specialist.toModelType());
    }

    @Test
    public void toModelType_invalidSpecialty_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedSpecialist(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_TAGS,
                        INVALID_SPECIALTY);
        String expectedMessage = Specialty.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }
}
