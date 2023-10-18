package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TestData.INDEX_FIRST_CONTACT;
import static seedu.address.testutil.TestData.INVALID_EMAIL;
import static seedu.address.testutil.TestData.INVALID_NAME;
import static seedu.address.testutil.TestData.INVALID_PHONE;
import static seedu.address.testutil.TestData.VALID_EMAIL_AMY;
import static seedu.address.testutil.TestData.VALID_NAME_AMY;
import static seedu.address.testutil.TestData.VALID_NOTE_BOB;
import static seedu.address.testutil.TestData.VALID_PHONE_AMY;
import static seedu.address.testutil.TestData.WHITESPACE;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.contact.Email;
import seedu.address.model.contact.Name;
import seedu.address.model.contact.Note;
import seedu.address.model.contact.Phone;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.TestData;


public class ParserUtilTest {

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
        assertEquals(INDEX_FIRST_CONTACT, ParserUtil.parseIndex("1"));

        // Leading and trailing whitespaces
        assertEquals(INDEX_FIRST_CONTACT, ParserUtil.parseIndex("  1  "));
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
        Name expectedName = new Name(VALID_NAME_AMY);
        assertEquals(expectedName, ParserUtil.parseName(VALID_NAME_AMY));
    }

    @Test
    public void parseName_validValueWithWhitespace_returnsTrimmedName() throws Exception {
        String nameWithWhitespace = WHITESPACE + VALID_NAME_AMY + WHITESPACE;
        Name expectedName = new Name(VALID_NAME_AMY);
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
        Phone expectedPhone = new Phone(VALID_PHONE_AMY);
        assertEquals(expectedPhone, ParserUtil.parsePhone(VALID_PHONE_AMY));
    }

    @Test
    public void parsePhone_validValueWithWhitespace_returnsTrimmedPhone() throws Exception {
        String phoneWithWhitespace = WHITESPACE + VALID_PHONE_AMY + WHITESPACE;
        Phone expectedPhone = new Phone(VALID_PHONE_AMY);
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
        Email expectedEmail = new Email(VALID_EMAIL_AMY);
        assertEquals(expectedEmail, ParserUtil.parseEmail(VALID_EMAIL_AMY));
    }

    @Test
    public void parseEmail_validValueWithWhitespace_returnsTrimmedEmail() throws Exception {
        String emailWithWhitespace = WHITESPACE + VALID_EMAIL_AMY + WHITESPACE;
        Email expectedEmail = new Email(VALID_EMAIL_AMY);
        assertEquals(expectedEmail, ParserUtil.parseEmail(emailWithWhitespace));
    }

    @Test
    public void parseNote_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseNote((String) null));
    }

    @Test
    public void parseNote_validValueWithoutWhitespace_returnsNote() throws Exception {
        Note expectedAddress = new Note(VALID_NOTE_BOB);
        assertEquals(expectedAddress, ParserUtil.parseNote(VALID_NOTE_BOB));
    }

    @Test
    public void parseNote_validValueWithWhitespace_returnsTrimmedNote() throws Exception {
        String addressWithWhitespace = WHITESPACE + VALID_NOTE_BOB + WHITESPACE;
        Note expectedAddress = new Note(VALID_NOTE_BOB);
        assertEquals(expectedAddress, ParserUtil.parseNote(addressWithWhitespace));
    }

    @Test
    public void parseTag_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTag(null));
    }

    @Test
    public void parseTag_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTag(TestData.Invalid.Tag.UNDERSCORE_DASH));
    }

    @Test
    public void parseTag_validValueWithoutWhitespace_returnsTag() throws Exception {
        assertEquals(
            new Tag(TestData.Valid.Tag.ALPHANUMERIC),
            ParserUtil.parseTag(TestData.Valid.Tag.ALPHANUMERIC)
        );
    }

    @Test
    public void parseTag_validValueWithWhitespace_returnsTrimmedTag() throws Exception {
        String tagWithWhitespace = WHITESPACE + TestData.Valid.Tag.ALPHANUMERIC + WHITESPACE;
        Tag expectedTag = new Tag(TestData.Valid.Tag.ALPHANUMERIC);
        assertEquals(expectedTag, ParserUtil.parseTag(tagWithWhitespace));
    }

    @Test
    public void parseTags_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTags(null));
    }

    @Test
    public void parseTags_collectionWithInvalidTags_throwsParseException() {
        assertThrows(
            ParseException.class,
            () -> {
                ParserUtil.parseTags(
                    Arrays.asList(TestData.Valid.Tag.ALPHANUMERIC, TestData.Invalid.Tag.HASHTAG)
                );
            }
        );
    }

    @Test
    public void parseTags_emptyCollection_returnsEmptySet() throws Exception {
        assertTrue(ParserUtil.parseTags(Collections.emptyList()).isEmpty());
    }

    @Test
    public void parseTags_collectionWithValidTags_returnsTagSet() throws Exception {
        Set<Tag> actualTagSet = ParserUtil.parseTags(
            Arrays.asList(TestData.Valid.Tag.ALPHANUMERIC, TestData.Valid.Tag.ALPHANUMERIC_SPACES)
        );
        Set<Tag> expectedTagSet = new HashSet<Tag>(
            Arrays.asList(
                new Tag(TestData.Valid.Tag.ALPHANUMERIC),
                new Tag(TestData.Valid.Tag.ALPHANUMERIC_SPACES)
            )
        );

        assertEquals(expectedTagSet, actualTagSet);
    }
}
