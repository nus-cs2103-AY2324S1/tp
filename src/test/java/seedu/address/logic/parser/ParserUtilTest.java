package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_INVALID_FIELDS;
import static seedu.address.logic.Messages.MESSAGE_INVALID_INDEX_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_MISSING_INDEX;
import static seedu.address.logic.Messages.MESSAGE_TOO_MANY_INDEXES;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_PERSON;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

public class ParserUtilTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_NAME = "Rachel Walker";
    private static final String VALID_PHONE = "123456";
    private static final String VALID_EMAIL = "rachel@example.com";
    private static final String VALID_TAG_1 = "friend";
    private static final String VALID_TAG_2 = "neighbour";
    private static final String WHITESPACE = " \t\r\n";
    private static final String VALID_TIME = "02.10.2023 1000";
    private static final String INVALID_TIME_1 = "02.10.2023  1000";
    private static final String INVALID_TIME_2 = "02.10.29999 1000";
    private static final String INVALID_TIME_3 = "02.10.2023 10000";
    private static final String INVALID_TIME_4 = "02.15.2023 1000";
    private static final String INVALID_TIME_5 = "78.10.2023 1000";
    private static final String INVALID_TIME_6 = "02.10.2023 5555";

    @Test
    public void verifyNoArgs_argsPresent_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_INVALID_FIELDS, () -> ParserUtil.verifyNoArgs("10 a"));
    }

    @Test
    public void verifyNoArgs_noArgs_success() {
        assertDoesNotThrow(() -> ParserUtil.verifyNoArgs(""));
    }

    @Test
    public void parseIndex_invalidInput_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_INVALID_INDEX_FORMAT, () -> ParserUtil.parseIndex("10 a"));
    }

    @Test
    public void parseIndex_outOfRangeInput_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_INVALID_INDEX_FORMAT, (
                ) -> ParserUtil.parseIndex(Long.toString(Integer.MAX_VALUE + 1)));
    }

    @Test
    public void parseIndex_emptyInput_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_MISSING_INDEX, () -> ParserUtil.parseIndex(""));
    }

    @Test
    public void parseIndex_validInput_success() throws Exception {
        // No whitespaces
        assertEquals(INDEX_FIRST_PERSON, ParserUtil.parseIndex("1"));

        // Leading and trailing whitespaces
        assertEquals(INDEX_FIRST_PERSON, ParserUtil.parseIndex("  1  "));
    }

    @Test
    public void parseIndexes_invalidInput_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_INVALID_INDEX_FORMAT, () -> ParserUtil.parseIndexes("10 10 w", 3));
    }

    @Test
    public void parseIndexes_outOfRangeInput_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_INVALID_INDEX_FORMAT, () -> ParserUtil.parseIndexes("1 0 4", 3));
    }

    @Test
    public void parseIndexes_tooFewArgs_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_MISSING_INDEX, () -> ParserUtil.parseIndexes("10 10", 3));
    }

    @Test
    public void parseIndexes_tooManyNumericArgs_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_TOO_MANY_INDEXES, () -> ParserUtil.parseIndexes("10 10", 1));
    }

    @Test
    public void parseIndexes_tooManyNonNumericArgs_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_INVALID_FIELDS, () -> ParserUtil.parseIndexes("10 w", 1));
    }

    @Test
    public void parseIndexes_nonPositiveExpectedIndexes_throwsAssertionError() {
        assertThrows(AssertionError.class, () -> ParserUtil.parseIndexes("", 0));
    }

    @Test
    public void parseIndexes_validInput_success() throws Exception {
        // Single index
        List<Index> singleIndexList = List.of(INDEX_FIRST_PERSON);
        assertEquals(singleIndexList, ParserUtil.parseIndexes("1", 1));

        // Multiple indexes
        List<Index> multipleIndexList = List.of(INDEX_FIRST_PERSON, INDEX_SECOND_PERSON, INDEX_THIRD_PERSON);
        assertEquals(multipleIndexList, ParserUtil.parseIndexes("1 2 3", 3));

        // Different whitespaces
        assertEquals(multipleIndexList, ParserUtil.parseIndexes(" 1  2      3   ", 3));
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
    public void parseTag_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTag(null));
    }

    @Test
    public void parseTag_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTag(INVALID_TAG));
    }

    @Test
    public void parseTag_validValueWithoutWhitespace_returnsTag() throws Exception {
        Tag expectedTag = Tag.of(VALID_TAG_1);
        assertEquals(expectedTag, ParserUtil.parseTag(VALID_TAG_1));
    }

    @Test
    public void parseTag_validValueWithWhitespace_returnsTrimmedTag() throws Exception {
        String tagWithWhitespace = WHITESPACE + VALID_TAG_1 + WHITESPACE;
        Tag expectedTag = Tag.of(VALID_TAG_1);
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
        Set<Tag> expectedTagSet = new HashSet<Tag>(Arrays.asList(Tag.of(VALID_TAG_1), Tag.of(VALID_TAG_2)));

        assertEquals(expectedTagSet, actualTagSet);
    }

    @Test
    public void parseTime_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseName((String) null));
    }

    @Test
    public void parseTime_invalidValue0_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseContactTime("29.02.2023 1000"));
        assertThrows(ParseException.class, () -> ParserUtil.parseMeetingTime("29.02.2023 1000"));
    }

    @Test
    public void parseTime_invalidValue1_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseContactTime("30.02.2023 1000"));
        assertThrows(ParseException.class, () -> ParserUtil.parseMeetingTime("30.02.2023 1000"));
    }

    @Test
    public void parseTime_invalidValue2_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseContactTime("31.02.2023 1000"));
        assertThrows(ParseException.class, () -> ParserUtil.parseMeetingTime("31.02.2023 1000"));
    }

    @Test
    public void parseTime_invalidValue3_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseContactTime("31.04.2023 1000"));
        assertThrows(ParseException.class, () -> ParserUtil.parseMeetingTime("31.04.2023 1000"));
    }

    @Test
    public void parseTime_invalidValue4_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseContactTime(INVALID_TIME_1));
        assertThrows(ParseException.class, () -> ParserUtil.parseMeetingTime(INVALID_TIME_2));
        assertThrows(ParseException.class, () -> ParserUtil.parseContactTime(INVALID_TIME_3));
        assertThrows(ParseException.class, () -> ParserUtil.parseMeetingTime(INVALID_TIME_4));
        assertThrows(ParseException.class, () -> ParserUtil.parseContactTime(INVALID_TIME_5));
        assertThrows(ParseException.class, () -> ParserUtil.parseMeetingTime(INVALID_TIME_6));
    }

    @Test
    public void parseTime_validValueWithoutWhitespace_returnsName() throws Exception {
        LocalDateTime expectedTime = LocalDateTime.of(2023, 10, 2, 10, 00);
        assertEquals(expectedTime, ParserUtil.parseContactTime(VALID_TIME));
        assertEquals(expectedTime, ParserUtil.parseMeetingTime(VALID_TIME));
    }

}
