package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.event.EventDescription;
import seedu.address.model.event.EventPeriod;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

public class ParserUtilTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TAG = "#friend";

    private static final String INVALID_DESCRIPTION = "";
    private static final String INVALID_DATE = "2023-13-35 16:80";

    private static final String VALID_NAME = "Rachel Walker";
    private static final String VALID_PHONE = "123456";
    private static final String VALID_ADDRESS = "123 Main Street #0505";
    private static final String VALID_EMAIL = "rachel@example.com";
    private static final String VALID_TAG_1 = "friend";
    private static final String VALID_TAG_2 = "neighbour";
    private static final String VALID_DESCRIPTION = "sleep";
    private static final String VALID_START_DATE = "2023-01-01 08:00";
    private static final String VALID_END_DATE = "2023-01-01 09:00";

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
    public void parseDualIndex_invalidInput_throwsParseException() throws Exception {
        assertThrows(ParseException.class, () -> ParserUtil.parseDualIndexes("10 a"));
    }

    @Test
    public void parseDualIndex_negativeValueInput_throwsParseException() throws Exception {
        assertThrows(ParseException.class, MESSAGE_INVALID_INDEX, ()
                -> ParserUtil.parseDualIndexes("-1 1"));
    }

    @Test
    public void parseDualIndexes_validInput_success() throws Exception {
        ArrayList<Index> expected = new ArrayList<>();
        Index expectedFirstItem = Index.fromOneBased(1);
        Index expectedSecondItem = Index.fromOneBased(2);
        expected.add(expectedFirstItem);
        expected.add(expectedSecondItem);

        ArrayList<Index> actual = ParserUtil.parseDualIndexes("1 2");
        assertEquals(expected, actual);
    }

    @Test
    public void parseDualIndexes_validInput_success2() throws Exception {
        ArrayList<Index> expected = new ArrayList<>();
        Index expectedFirstItem = Index.fromOneBased(1);
        Index expectedSecondItem = Index.fromOneBased(4);
        expected.add(expectedFirstItem);
        expected.add(expectedSecondItem);

        ArrayList<Index> actual = ParserUtil.parseDualIndexes("1 4  ");
        assertEquals(expected, actual);
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
    public void parseAddress_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseAddress((String) null));
    }

    @Test
    public void parseAddress_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseAddress(INVALID_ADDRESS));
    }

    @Test
    public void parseAddress_validValueWithoutWhitespace_returnsAddress() throws Exception {
        Address expectedAddress = new Address(VALID_ADDRESS);
        assertEquals(expectedAddress, ParserUtil.parseAddress(VALID_ADDRESS));
    }

    @Test
    public void parseAddress_validValueWithWhitespace_returnsTrimmedAddress() throws Exception {
        String addressWithWhitespace = WHITESPACE + VALID_ADDRESS + WHITESPACE;
        Address expectedAddress = new Address(VALID_ADDRESS);
        assertEquals(expectedAddress, ParserUtil.parseAddress(addressWithWhitespace));
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
    public void parseTag_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTag(null));
    }

    @Test
    public void parseTag_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTag(INVALID_TAG));
    }

    @Test
    public void parseTag_validValueWithoutWhitespace_returnsTag() throws Exception {
        Tag expectedTag = new Tag(VALID_TAG_1);
        assertEquals(expectedTag, ParserUtil.parseTag(VALID_TAG_1));
    }

    @Test
    public void parseTag_validValueWithWhitespace_returnsTrimmedTag() throws Exception {
        String tagWithWhitespace = WHITESPACE + VALID_TAG_1 + WHITESPACE;
        Tag expectedTag = new Tag(VALID_TAG_1);
        assertEquals(expectedTag, ParserUtil.parseTag(tagWithWhitespace));
    }

    @Test
    public void parseTags_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTags(null));
    }

    @Test
    public void parseTags_collectionWithInvalidTags_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTags(Arrays.asList(VALID_TAG_1, INVALID_TAG)));
    }

    @Test
    public void parseTags_emptyCollection_returnsEmptySet() throws Exception {
        assertTrue(ParserUtil.parseTags(Collections.emptyList()).isEmpty());
    }

    @Test
    public void parseTags_collectionWithValidTags_returnsTagSet() throws Exception {
        Set<Tag> actualTagSet = ParserUtil.parseTags(Arrays.asList(VALID_TAG_1, VALID_TAG_2));
        Set<Tag> expectedTagSet = new HashSet<Tag>(Arrays.asList(new Tag(VALID_TAG_1), new Tag(VALID_TAG_2)));

        assertEquals(expectedTagSet, actualTagSet);
    }

    @Test
    public void parseEventDescription_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseEventDescription(null));
    }

    @Test
    public void parseEventDescription_invalidDescription_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseEventDescription(INVALID_DESCRIPTION));
    }

    @Test
    public void parseEventDescription_validDescription_returnsDescription() throws Exception {
        EventDescription actual = ParserUtil.parseEventDescription(VALID_DESCRIPTION);
        EventDescription expected = new EventDescription(VALID_DESCRIPTION);

        assertEquals(actual, expected);
    }

    @Test
    public void parseEventPeriod_nullStart_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseEventPeriod(null, VALID_END_DATE));
    }

    @Test
    public void parseEventPeriod_nullEnd_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseEventPeriod(VALID_START_DATE, null));
    }

    @Test
    public void parseEventPeriod_nullStartAndEnd_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseEventPeriod(null, null));
    }

    @Test
    public void parseEventPeriod_invalidStart_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseEventPeriod(INVALID_DATE, VALID_END_DATE));
    }

    @Test
    public void parseEventPeriod_invalidEnd_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseEventPeriod(VALID_START_DATE, INVALID_DATE));
    }

    @Test
    public void parseEventPeriod_invalidStartAndEnd_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseEventPeriod(INVALID_DATE, INVALID_DATE));
    }

    @Test
    public void parseEventPeriod_endBeforeStart_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseEventPeriod(VALID_END_DATE, VALID_START_DATE));
    }

    @Test
    public void parseEventPeriod_validStartAndEnd_returnsValidEventPeriod() throws Exception {
        EventPeriod actual = ParserUtil.parseEventPeriod(VALID_START_DATE, VALID_END_DATE);
        EventPeriod expected = new EventPeriod(VALID_START_DATE, VALID_END_DATE);

        assertEquals(actual, expected);
    }
}
