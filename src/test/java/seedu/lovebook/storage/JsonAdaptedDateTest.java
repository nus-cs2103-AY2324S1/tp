package seedu.lovebook.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.lovebook.storage.JsonAdaptedDate.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.lovebook.testutil.Assert.assertThrows;
import static seedu.lovebook.testutil.TypicalPersons.BENSON;

import org.junit.jupiter.api.Test;

import seedu.lovebook.commons.exceptions.IllegalValueException;
import seedu.lovebook.model.person.Age;
import seedu.lovebook.model.person.Gender;
import seedu.lovebook.model.person.Height;
import seedu.lovebook.model.person.Name;
import seedu.lovebook.model.person.horoscope.Horoscope;

public class JsonAdaptedDateTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_AGE = "+651234";
    private static final String INVALID_HEIGHT = " ";
    private static final String INVALID_GENDER = "example.com";
    private static final String INVALID_INCOME = " ";
    private static final String INVALID_HOROSCOPE = "TIGER";

    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_AGE = BENSON.getAge().toString();
    private static final String VALID_GENDER = BENSON.getGender().toString();
    private static final String VALID_HEIGHT = BENSON.getHeight().toString();
    private static final String VALID_INCOME = BENSON.getIncome().toString();
    private static final String VALID_HOROSCOPE = BENSON.getHoroscope().toString();

    @Test
    public void toModelType_validPersonDetails_returnsPerson() throws Exception {
        JsonAdaptedDate person = new JsonAdaptedDate(BENSON);
        assertEquals(BENSON, person.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedDate person =
                new JsonAdaptedDate(INVALID_NAME, VALID_AGE, VALID_GENDER, VALID_HEIGHT, VALID_INCOME,
                        VALID_HOROSCOPE);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedDate person = new JsonAdaptedDate(null, VALID_AGE, VALID_GENDER, VALID_HEIGHT,
                VALID_INCOME, VALID_HOROSCOPE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidAge_throwsIllegalValueException() {
        JsonAdaptedDate person =
                new JsonAdaptedDate(VALID_NAME, INVALID_AGE, VALID_GENDER, VALID_HEIGHT, VALID_INCOME,
                        VALID_HOROSCOPE);
        String expectedMessage = Age.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullAge_throwsIllegalValueException() {
        JsonAdaptedDate person = new JsonAdaptedDate(VALID_NAME, null, VALID_GENDER, VALID_HEIGHT, VALID_INCOME,
                VALID_HOROSCOPE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Age.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidGender_throwsIllegalValueException() {
        JsonAdaptedDate person =
                new JsonAdaptedDate(VALID_NAME, VALID_AGE, INVALID_GENDER, VALID_HEIGHT, VALID_INCOME, VALID_HOROSCOPE);
        String expectedMessage = Gender.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullGender_throwsIllegalValueException() {
        JsonAdaptedDate person = new JsonAdaptedDate(VALID_NAME, VALID_AGE, null, VALID_HEIGHT, VALID_INCOME,
                VALID_HOROSCOPE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Gender.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
        JsonAdaptedDate person =
                new JsonAdaptedDate(VALID_NAME, VALID_AGE, VALID_GENDER, INVALID_HEIGHT, VALID_INCOME, VALID_HOROSCOPE);
        String expectedMessage = Height.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        JsonAdaptedDate person = new JsonAdaptedDate(VALID_NAME, VALID_AGE, VALID_GENDER, null, VALID_INCOME,
                VALID_HOROSCOPE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Height.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidHoroscope_throwsIllegalValueException() {
        JsonAdaptedDate person =
                new JsonAdaptedDate(VALID_NAME, VALID_AGE, VALID_GENDER, VALID_HEIGHT, VALID_INCOME,
                        INVALID_HOROSCOPE);
        String expectedMessage = Horoscope.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullHoroscope_throwsIllegalValueException() {
        JsonAdaptedDate person = new JsonAdaptedDate(VALID_NAME, VALID_AGE, VALID_GENDER, VALID_HEIGHT,
                VALID_INCOME, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Horoscope.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }
}
