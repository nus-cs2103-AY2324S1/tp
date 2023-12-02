package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.Messages.MESSAGE_NON_POSITIVE_INTEGER_INDEX;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_COMPANY;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.company.Email;
import seedu.address.model.company.Name;
import seedu.address.model.company.Phone;
import seedu.address.model.company.Priority;

public class ParserUtilTest {
    private static final String INVALID_COMPANY_NAME = "G@ogle";
    private static final String INVALID_PHONE_NON_NUMERICAL_DIGITS = "+65&1234";
    private static final String INVALID_PHONE_LESS_THAN_3_DIGITS = "   1   1 ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_PRIORITY = "0";

    private static final String VALID_COMPANY_NAME = "Google";
    private static final String VALID_PHONE_WITHOUT_SPACES = "98765432";
    private static final String VALID_PHONE_IN_BETWEEN_WHITESPACES = "9  87 6 54 3 2";
    private static final String VALID_EMAIL = "rachel@example.com";
    private static final String VALID_PRIORITY = "NONE";

    private static final String WHITESPACE = " \t\r\n";

    @Test
    public void parseIndex_invalidInput_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseIndex("10 a"));
    }

    @Test
    public void parseIndex_outOfRangeInput_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_NON_POSITIVE_INTEGER_INDEX, ()
            -> ParserUtil.parseIndex(Long.toString(Integer.MAX_VALUE + 1)));
    }

    @Test
    public void parseIndex_validInput_success() throws Exception {
        // No whitespaces
        assertEquals(INDEX_FIRST_COMPANY, ParserUtil.parseIndex("1"));

        // Leading and trailing whitespaces
        assertEquals(INDEX_FIRST_COMPANY, ParserUtil.parseIndex("  1  "));
    }

    @Test
    public void parseName_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseName(null));
    }

    @Test
    public void parseName_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseName(INVALID_COMPANY_NAME));
    }

    @Test
    public void parseName_emptyValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseName(""));
    }

    @Test
    public void parseName_validValueWithoutWhitespace_returnsName() throws Exception {
        Name expectedName = new Name(VALID_COMPANY_NAME);
        assertEquals(expectedName, ParserUtil.parseName(VALID_COMPANY_NAME));
    }

    @Test
    public void parseName_validValueWithWhitespace_returnsTrimmedName() throws Exception {
        String nameWithWhitespace = WHITESPACE + VALID_COMPANY_NAME + WHITESPACE;
        Name expectedName = new Name(VALID_COMPANY_NAME);
        assertEquals(expectedName, ParserUtil.parseName(nameWithWhitespace));
    }

    @Test
    public void parsePhone_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parsePhone(null));
    }

    @Test
    public void parsePhone_invalidValueWithNonNumerical_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parsePhone(INVALID_PHONE_NON_NUMERICAL_DIGITS));
    }

    @Test
    public void parsePhone_invalidValueWithLessThan3Digits_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parsePhone(INVALID_PHONE_LESS_THAN_3_DIGITS));
    }

    @Test
    public void parsePhone_emptyValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parsePhone(""));
    }

    @Test
    public void parsePhone_validValueWithInBetweenWhiteSpace_returnsPhone() throws Exception {
        Phone expectedPhone = new Phone(VALID_PHONE_WITHOUT_SPACES);
        assertEquals(expectedPhone, ParserUtil.parsePhone(VALID_PHONE_IN_BETWEEN_WHITESPACES));
    }

    @Test
    public void parsePhone_validValueWithoutWhitespace_returnsPhone() throws Exception {
        Phone expectedPhone = new Phone(VALID_PHONE_WITHOUT_SPACES);
        assertEquals(expectedPhone, ParserUtil.parsePhone(VALID_PHONE_WITHOUT_SPACES));
    }

    @Test
    public void parsePhone_validValueWithTrailingWhitespace_returnsTrimmedPhone() throws Exception {
        String phoneWithWhitespace = WHITESPACE + VALID_PHONE_WITHOUT_SPACES + WHITESPACE;
        Phone expectedPhone = new Phone(VALID_PHONE_WITHOUT_SPACES);
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
    public void parseEmail_emptyValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseEmail(""));
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
    public void parsePriority_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parsePriority((String) null));
    }

    @Test
    public void parsePriority_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parsePriority(INVALID_PRIORITY));
    }

    @Test
    public void parsePriority_emptyValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parsePriority(""));
    }

    @Test
    public void parsePriority_validValueWithoutWhitespace_returnsPriority() throws Exception {
        Priority expectedPriority = new Priority(VALID_PRIORITY);
        assertEquals(expectedPriority, ParserUtil.parsePriority(VALID_PRIORITY));
    }

    @Test
    public void parsePriority_validValueWithWhitespace_returnsTrimmedPriority() throws Exception {
        String priorityWithWhitespace = WHITESPACE + VALID_PRIORITY + WHITESPACE;
        Priority expectedPriority = new Priority(VALID_PRIORITY);
        assertEquals(expectedPriority, ParserUtil.parsePriority(priorityWithWhitespace));
    }
}
