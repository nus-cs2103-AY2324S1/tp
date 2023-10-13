package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.schedule.EndTime;
import seedu.address.model.schedule.StartTime;

public class ParserUtilTest {

    /* Tutor related */
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_EMAIL = "example.com";

    private static final String VALID_NAME = "Rachel Walker";
    private static final String VALID_PHONE = "123456";
    private static final String VALID_EMAIL = "rachel@example.com";

    /* Schedule related */
    private static final String INVALID_TUTOR_INDEX_STRING_1 = "-1";
    private static final String INVALID_TUTOR_INDEX_STRING_2 = "0";
    private static final String INVALID_TIME_STRING_1 = "2023-12-12T44:44";
    private static final String INVALID_TIME_STRING_2 = "2023-12-12 10:00";
    private static final String INVALID_TIME_STRING_3 = "2023-15-12T10:00";

    private static final String VALID_TUTOR_INDEX_STRING = "1";
    private static final Integer VALID_TUTOR_INDEX_VALUE = 1;
    private static final String VALID_TIME_STRING = "2023-09-15T11:00:00";
    private static final LocalDateTime VALID_TIME_VALUE = LocalDateTime.of(2023, 9, 15, 11, 0, 0);

    /* Others */
    private static final String WHITESPACE = " \t\r\n";

    @Test
    public void parseIndex_invalidInput_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseIndex("10 a"));
    }

    @Test
    public void parseIndex_outOfRangeInput_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_INVALID_INDEX, ()
                -> ParserUtil.parseIndex(Long.toString(Integer.MAX_VALUE + 1)));
    }

    @Test
    public void parseIndex_validInput_success() throws Exception {
        // No whitespaces
        assertEquals(INDEX_FIRST_PERSON, ParserUtil.parseIndex("1"));

        // Leading and trailing whitespaces
        assertEquals(INDEX_FIRST_PERSON, ParserUtil.parseIndex("  1  "));
    }

    @Test
    public void parseName_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseName((String) null));
    }

    @Test
    public void parseName_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseName(INVALID_NAME));
    }

    @Test
    public void parseName_validValueWithoutWhitespace_returnsName() throws Exception {
        Name expectedName = new Name(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseName(VALID_NAME));
    }

    @Test
    public void parseName_validValueWithWhitespace_returnsTrimmedName() throws Exception {
        String nameWithWhitespace = WHITESPACE + VALID_NAME + WHITESPACE;
        Name expectedName = new Name(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseName(nameWithWhitespace));
    }

    @Test
    public void parsePhone_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parsePhone((String) null));
    }

    @Test
    public void parsePhone_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parsePhone(INVALID_PHONE));
    }

    @Test
    public void parsePhone_validValueWithoutWhitespace_returnsPhone() throws Exception {
        Phone expectedPhone = new Phone(VALID_PHONE);
        assertEquals(expectedPhone, ParserUtil.parsePhone(VALID_PHONE));
    }

    @Test
    public void parsePhone_validValueWithWhitespace_returnsTrimmedPhone() throws Exception {
        String phoneWithWhitespace = WHITESPACE + VALID_PHONE + WHITESPACE;
        Phone expectedPhone = new Phone(VALID_PHONE);
        assertEquals(expectedPhone, ParserUtil.parsePhone(phoneWithWhitespace));
    }

    @Test
    public void parseEmail_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseEmail((String) null));
    }

    @Test
    public void parseEmail_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseEmail(INVALID_EMAIL));
    }

    @Test
    public void parseEmail_validValueWithoutWhitespace_returnsEmail() throws Exception {
        Email expectedEmail = new Email(VALID_EMAIL);
        assertEquals(expectedEmail, ParserUtil.parseEmail(VALID_EMAIL));
    }

    @Test
    public void parseEmail_validValueWithWhitespace_returnsTrimmedEmail() throws Exception {
        String emailWithWhitespace = WHITESPACE + VALID_EMAIL + WHITESPACE;
        Email expectedEmail = new Email(VALID_EMAIL);
        assertEquals(expectedEmail, ParserUtil.parseEmail(emailWithWhitespace));
    }

    @Test
    public void parserUtil_instantiation_success() {
        ParserUtil parserUtil = new ParserUtil();
        assertNotNull(parserUtil);
    }

    @Test
    public void parseStartTime_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseStartTime(null));
    }

    @Test
    public void parseStartTime_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseStartTime(INVALID_TIME_STRING_1));
        assertThrows(ParseException.class, () -> ParserUtil.parseStartTime(INVALID_TIME_STRING_2));
        assertThrows(ParseException.class, () -> ParserUtil.parseStartTime(INVALID_TIME_STRING_3));
    }

    @Test
    public void parseStartTime_validValueWithoutWhitespace_returnsStartTime() throws Exception {
        StartTime expectedStartTime = new StartTime(VALID_TIME_VALUE);
        assertEquals(expectedStartTime, ParserUtil.parseStartTime(VALID_TIME_STRING));
    }

    @Test
    public void parseStartTime_validValueWithWhitespace_returnsTrimmedName() throws Exception {
        String startTimeWithWhitespace = WHITESPACE + VALID_TIME_STRING + WHITESPACE;
        StartTime expectedStartTime = new StartTime(VALID_TIME_VALUE);
        assertEquals(expectedStartTime, ParserUtil.parseStartTime(startTimeWithWhitespace));
    }

    @Test
    public void parseEndTime_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseEmail(null));
    }

    @Test
    public void parseEndTime_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseEndTime(INVALID_TIME_STRING_1));
        assertThrows(ParseException.class, () -> ParserUtil.parseEndTime(INVALID_TIME_STRING_2));
        assertThrows(ParseException.class, () -> ParserUtil.parseEndTime(INVALID_TIME_STRING_3));
    }

    @Test
    public void parseEndTime_validValueWithoutWhitespace_returnsEndTime() throws Exception {
        EndTime expectedEndTime = new EndTime(VALID_TIME_VALUE);
        assertEquals(expectedEndTime, ParserUtil.parseEndTime(VALID_TIME_STRING));
    }

    @Test
    public void parseEndTime_validValueWithWhitespace_returnsTrimmedName() throws Exception {
        String endTimeWithWhitespace = WHITESPACE + VALID_TIME_STRING + WHITESPACE;
        EndTime expectedEndTime = new EndTime(VALID_TIME_VALUE);
        assertEquals(expectedEndTime, ParserUtil.parseEndTime(endTimeWithWhitespace));
    }
}
