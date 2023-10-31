package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.event.EventID;
import seedu.address.model.event.EventInformation;
import seedu.address.model.event.EventLocation;
import seedu.address.model.event.EventName;
import seedu.address.model.event.EventTime;
import seedu.address.model.note.NoteContent;
import seedu.address.model.note.NoteID;
import seedu.address.model.note.NoteTitle;
import seedu.address.model.person.Address;
import seedu.address.model.person.ContactID;
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
    private static final String INVALID_CONTACT_ID_1 = "abc";
    private static final String INVALID_CONTACT_ID_2 = "1.23";
    private static final String INVALID_NOTE_ID_1 = "abc";
    private static final String INVALID_NOTE_ID_2 = "1.23";

    private static final String VALID_NAME = "Rachel Walker";
    private static final String VALID_PHONE = "123456";
    private static final String VALID_ADDRESS = "123 Main Street #0505";
    private static final String VALID_EMAIL = "rachel@example.com";
    private static final String VALID_TAG_1 = "friend";
    private static final String VALID_TAG_2 = "neighbour";
    private static final String VALID_CONTACT_ID = "1";
    private static final String VALID_NOTE_ID = "1";
    private static final String VALID_NOTE_CONTENT = "Content 1";
    private static final String VALID_NOTE_TITLE = "Title 1";

    private static final String WHITESPACE = " \t\r\n";
    private static final String EMPTY_STRING = "";

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
    public void parseContactID_validValue_returnsContactID() throws Exception {
        assertEquals(VALID_CONTACT_ID, ParserUtil.parseContactID(VALID_CONTACT_ID).toString());
    }

    @Test
    public void parseContactID_invalidValueEmptyString_throwsParseException() {
        assertThrows(ParseException.class, ContactID.MESSAGE_NON_EMPTY, () -> ParserUtil.parseContactID("").toString());
    }

    @Test
    public void parseContactID_invalidInteger_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseContactID(INVALID_CONTACT_ID_1));
        assertThrows(ParseException.class, () -> ParserUtil.parseContactID(INVALID_CONTACT_ID_2));
    }

    @Test
    public void parseNoteID_validValue_returnsNoteID() throws Exception {
        assertEquals(VALID_NOTE_ID, ParserUtil.parseNoteID(VALID_NOTE_ID).toString());
    }

    @Test
    public void parseNoteID_invalidValueEmptyString_throwsParseException() {
        assertThrows(ParseException.class,
                NoteID.MESSAGE_NON_EMPTY, () -> ParserUtil.parseNoteID(EMPTY_STRING).toString());
    }

    @Test
    public void parseNoteID_invalidValueWhitespace_throwsParseException() {
        assertThrows(ParseException.class,
                NoteID.MESSAGE_NON_EMPTY, () -> ParserUtil.parseNoteID(WHITESPACE).toString());
    }

    @Test
    public void parseNoteID_invalidValueNotInteger_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseNoteID(INVALID_NOTE_ID_1).toString());
        assertThrows(ParseException.class, () -> ParserUtil.parseNoteID(INVALID_NOTE_ID_2).toString());
    }

    @Test
    public void parseNoteContent_validValueWithoutWhitespace_returnsNoteContent() throws Exception {
        assertEquals(VALID_NOTE_CONTENT, ParserUtil.parseNoteContent(VALID_NOTE_CONTENT).toString());
    }

    @Test
    public void parseNoteContent_validValueWithWhitespace_returnsTrimmedNoteContent() throws Exception {
        String noteContentWithWhitespace = WHITESPACE + VALID_NOTE_CONTENT + WHITESPACE;
        assertEquals(VALID_NOTE_CONTENT, ParserUtil.parseNoteContent(noteContentWithWhitespace).toString());
    }

    @Test
    public void parseNoteContent_invalidValueEmptyString_throwsParseException() throws Exception {
        assertThrows(ParseException.class, NoteContent.MESSAGE_CONSTRAINTS, () ->
                ParserUtil.parseNoteContent(EMPTY_STRING).toString());
    }

    @Test
    public void parseNoteContent_invalidValueWhitespace_throwsParseException() throws Exception {
        assertThrows(ParseException.class, NoteContent.MESSAGE_CONSTRAINTS, () ->
                ParserUtil.parseNoteContent(WHITESPACE).toString());
    }

    @Test
    public void parseNoteTitle_validValueWithoutWhitespace_returnsNoteTitle() throws Exception {
        assertEquals(VALID_NOTE_TITLE, ParserUtil.parseNoteContent(VALID_NOTE_TITLE).toString());
    }

    @Test
    public void parseNoteTitle_validValueWithWhitespace_returnsTrimmedNoteTitle() throws Exception {
        String noteTitleWithWhitespace = WHITESPACE + VALID_NOTE_TITLE + WHITESPACE;
        assertEquals(VALID_NOTE_TITLE, ParserUtil.parseNoteTitle(noteTitleWithWhitespace).toString());
    }

    @Test
    public void parseNoteTitle_invalidValueEmptyString_throwsParseException() throws Exception {
        assertThrows(ParseException.class, NoteTitle.MESSAGE_CONSTRAINTS, () ->
                ParserUtil.parseNoteTitle(EMPTY_STRING).toString());
    }

    @Test
    public void parseNoteTitle_invalidValueWhitespace_throwsParseException() throws Exception {
        assertThrows(ParseException.class, NoteTitle.MESSAGE_CONSTRAINTS, () ->
                ParserUtil.parseNoteTitle(WHITESPACE).toString());
    }

    @Test
    public void parseEventID_success() throws Exception {
        assertEquals("1", ParserUtil.parseEventID("1").toString());
    }

    @Test
    public void parseEventID_fail_emptyString() {
        assertThrows(ParseException.class, EventID.MESSAGE_NON_EMPTY, () -> ParserUtil.parseEventID("").toString());
    }

    @Test
    public void parseEventID_fail_invalidInteger() {
        assertThrows(ParseException.class, String.format(Messages.MESSAGE_INVALID_INTEGER_ARGUMENT,
                "For input string: \"abc\""), () -> ParserUtil.parseEventID("abc").toString());
        assertThrows(ParseException.class, String.format(Messages.MESSAGE_INVALID_INTEGER_ARGUMENT,
                "For input string: \"1.23\""), () -> ParserUtil.parseEventID("1.23").toString());
    }

    @Test
    public void parseEventName_success() throws Exception {
        assertEquals("SomeName", ParserUtil.parseEventName("SomeName").toString());
    }

    @Test
    public void parseEventName_fail_emptyString() throws Exception {
        assertThrows(ParseException.class, EventName.MESSAGE_CONSTRAINTS, () ->
                ParserUtil.parseEventName("").toString());
    }

    @Test
    public void parseEventTime_success() throws Exception {
        assertEquals(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + " 12:00:00",
                ParserUtil.parseEventTime("12:00").toString());
        assertEquals("2023-12-01 00:00:00",
                ParserUtil.parseEventTime("2023-12-01").toString());
        assertEquals("2023-12-01 10:02:03",
                ParserUtil.parseEventTime("2023-12-01 10:02:03").toString());
        assertEquals("",
                ParserUtil.parseEventTime(null).toString());
    }

    @Test
    public void parseEventTime_fail_emptyString() {
        assertThrows(ParseException.class, EventTime.MESSAGE_NON_EMPTY, () -> ParserUtil.parseEventTime(""));
    }

    @Test
    public void parseEventTime_fail_wrongFormat() {
        assertThrows(ParseException.class, EventTime.MESSAGE_NON_EMPTY, () -> ParserUtil.parseEventTime(""));
        String dateNow = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        assertThrows(ParseException.class,
                EventTime.MESSAGE_INVALID_DATETIME_FORMAT + "Text '" + dateNow
                        + " 1' could not be parsed at index 11", () ->
                        ParserUtil.parseEventTime("1"));
        assertThrows(ParseException.class,
                EventTime.MESSAGE_INVALID_DATETIME_FORMAT + "Text '" + dateNow
                        + " 1,2,3,4' could not be parsed at index 11", () ->
                        ParserUtil.parseEventTime("1,2,3,4"));
    }

    @Test
    public void parseEventLocation_success() throws Exception {
        assertEquals("SomeLocation", ParserUtil.parseEventLocation("SomeLocation").toString());
    }

    @Test
    public void parseEventLocation_fail_emptyString() throws Exception {
        assertThrows(ParseException.class, EventLocation.MESSAGE_CONSTRAINTS, () ->
                ParserUtil.parseEventLocation("").toString());
    }

    @Test
    public void parseEventInformation_success() throws Exception {
        assertEquals("SomeInformation", ParserUtil.parseEventInformation("SomeInformation").toString());
    }

    @Test
    public void parseEventInformation_fail_emptyString() throws Exception {
        assertThrows(ParseException.class, EventInformation.MESSAGE_CONSTRAINTS, () ->
                ParserUtil.parseEventInformation("").toString());
    }
}
