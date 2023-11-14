package seedu.lovebook.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.lovebook.storage.JsonAdaptedDate.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.lovebook.testutil.Assert.assertThrows;
import static seedu.lovebook.testutil.TypicalDates.BENSON;

import org.junit.jupiter.api.Test;

import seedu.lovebook.commons.exceptions.IllegalValueException;
import seedu.lovebook.model.DatePrefs;
import seedu.lovebook.model.date.Age;
import seedu.lovebook.model.date.Height;
import seedu.lovebook.model.date.Income;
import seedu.lovebook.model.date.horoscope.Horoscope;

public class JsonAdaptedDatePrefsTest {

    private static final String INVALID_AGE = " ";
    private static final String INVALID_HEIGHT = " ";
    private static final String INVALID_INCOME = " ";
    private static final String INVALID_HOROSCOPE = "TIGER";

    private static final Age VALID_AGE = BENSON.getAge();
    private static final Height VALID_HEIGHT = BENSON.getHeight();
    private static final Income VALID_INCOME = BENSON.getIncome();
    private static final Horoscope VALID_HOROSCOPE = BENSON.getHoroscope();
    private static final DatePrefs VALID_PREFS = new DatePrefs(VALID_AGE, VALID_HEIGHT, VALID_INCOME, VALID_HOROSCOPE);

    @Test
    public void toModelType_validDatePrefs_returnsPrefs() throws Exception {
        JsonAdaptedDatePrefs prefs = new JsonAdaptedDatePrefs(VALID_PREFS);
        assertEquals(VALID_PREFS, prefs.toModelType());
    }

    @Test
    public void toModelType_invalidAge_throwsIllegalValueException() {
        JsonAdaptedDatePrefs prefs = new JsonAdaptedDatePrefs(INVALID_AGE, VALID_HEIGHT.toString(),
                VALID_INCOME.toString(), VALID_HOROSCOPE.toString());
        String expectedMessage = Age.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, prefs::toModelType);
    }

    @Test
    public void toModelType_nullAge_throwsIllegalValueException() {
        JsonAdaptedDatePrefs prefs = new JsonAdaptedDatePrefs(null, VALID_HEIGHT.toString(),
                VALID_INCOME.toString(), VALID_HOROSCOPE.toString());
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Age.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, prefs::toModelType);
    }

    @Test
    public void toModelType_invalidHeight_throwsIllegalValueException() {
        JsonAdaptedDatePrefs prefs =
                new JsonAdaptedDatePrefs(VALID_AGE.toString(), INVALID_HEIGHT, VALID_INCOME.toString(),
                        VALID_HOROSCOPE.toString());
        String expectedMessage = Height.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, prefs::toModelType);
    }

    @Test
    public void toModelType_nullHeight_throwsIllegalValueException() {
        JsonAdaptedDatePrefs prefs = new JsonAdaptedDatePrefs(VALID_AGE.toString(), null, VALID_INCOME.toString(),
                VALID_HOROSCOPE.toString());
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Height.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, prefs::toModelType);
    }

    @Test
    public void toModelType_invalidHoroscope_throwsIllegalValueException() {
        JsonAdaptedDatePrefs prefs =
                new JsonAdaptedDatePrefs(VALID_AGE.toString(), VALID_HEIGHT.toString(), VALID_INCOME.toString(),
                        INVALID_HOROSCOPE);
        String expectedMessage = Horoscope.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, prefs::toModelType);
    }

    @Test
    public void toModelType_nullHoroscope_throwsIllegalValueException() {
        JsonAdaptedDatePrefs prefs = new JsonAdaptedDatePrefs(VALID_AGE.toString(), VALID_HEIGHT.toString(),
                VALID_INCOME.toString(), null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Horoscope.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, prefs::toModelType);
    }

    @Test
    public void toModelType_invalidIncome_throwsIllegalValueException() {
        JsonAdaptedDatePrefs prefs =
                new JsonAdaptedDatePrefs(VALID_AGE.toString(), VALID_HEIGHT.toString(), INVALID_INCOME,
                        VALID_HOROSCOPE.toString());
        String expectedMessage = Income.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, prefs::toModelType);
    }

    @Test
    public void toModelType_nullIncome_throwsIllegalValueException() {
        JsonAdaptedDatePrefs prefs = new JsonAdaptedDatePrefs(VALID_AGE.toString(), VALID_HEIGHT.toString(),
                null, VALID_HOROSCOPE.toString());
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Income.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, prefs::toModelType);
    }

}
