package seedu.address.logic.parser;

import static java.lang.Integer.parseInt;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.ParserUtil.CourseOperation;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Email;
import seedu.address.model.person.ID;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;
import seedu.address.model.week.Week;

public class ParserUtilTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_ID = "E12345678";
    private static final String INVALID_TAG = "#friend";
    private static final String INVALID_COURSE_OPERATION = "update";
    private static final String INVALID_WEEK = "25";
    private static final String INVALID_WEEK_UNPARSEABLE = ":";

    private static final String VALID_NAME = "Rachel Walker";
    private static final String VALID_PHONE = "123456";
    private static final String VALID_EMAIL = "rachel@example.com";
    private static final String VALID_ID = "A1234567H";
    private static final String VALID_TAG_1 = "friend";
    private static final String VALID_TAG_2 = "neighbour";
    private static final String VALID_CREATE_OPERATION = "create";
    private static final String VALID_DELETE_OPERATION = "delete";
    private static final String VALID_SWITCH_OPERATION = "switch";
    private static final String VALID_EDIT_OPERATION = "edit";
    private static final String VALID_WEEK = "2";

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
    public void parseId_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseId((String) null));
    }

    @Test
    public void parseId_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseId(INVALID_ID));
    }

    @Test
    public void parseId_validValueWithoutWhitespace_returnsId() throws Exception {
        ID expectedId = new ID(VALID_ID);
        assertEquals(expectedId, ParserUtil.parseId(VALID_ID));
    }

    @Test
    public void parseId_validValueWithWhitespace_returnsTrimmedId() throws Exception {
        String idWithWhitespace = WHITESPACE + VALID_ID + WHITESPACE;
        ID expectedId = new ID(VALID_ID);
        assertEquals(expectedId, ParserUtil.parseId(idWithWhitespace));
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
    public void parseWeek_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseWeek(null));
    }

    @Test
    public void parseWeek_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseWeek(INVALID_WEEK));
        assertThrows(ParseException.class, () -> ParserUtil.parseWeek(INVALID_WEEK_UNPARSEABLE));
    }

    @Test
    public void parseWeek_validValueWithoutWhitespace_returnsWeek() throws Exception {
        Week expectedWeek = new Week(parseInt(VALID_WEEK));
        assertEquals(expectedWeek, ParserUtil.parseWeek(VALID_WEEK));
    }

    @Test
    public void parseWeek_validValueWithWhitespace_returnsTrimmedWeek() throws Exception {
        String weekWithWhitespace = WHITESPACE + VALID_WEEK + WHITESPACE;
        Week expectedWeek = new Week(parseInt(VALID_WEEK));
        assertEquals(expectedWeek, ParserUtil.parseWeek(weekWithWhitespace));
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
    public void parseCourseOperation_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseCourseOperation((String) null));
    }

    @Test
    public void parseCourseOperation_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseCourseOperation(INVALID_COURSE_OPERATION));
    }


    @Test
    public void parseCourseOperation_validValueWithputWhiteSpace_returnsCourseOperation() throws Exception {
        assertEquals(CourseOperation.CREATE, ParserUtil.parseCourseOperation(VALID_CREATE_OPERATION));
        assertEquals(CourseOperation.DELETE, ParserUtil.parseCourseOperation(VALID_DELETE_OPERATION));
        assertEquals(CourseOperation.SWITCH, ParserUtil.parseCourseOperation(VALID_SWITCH_OPERATION));
        assertEquals(CourseOperation.EDIT, ParserUtil.parseCourseOperation(VALID_EDIT_OPERATION));
    }

    @Test
    public void parseCourseOperation_validValueWithWhiteSpace_returnsCourseOperation() throws Exception {
        String createOperationWithWhitespace = WHITESPACE + VALID_CREATE_OPERATION + WHITESPACE;
        assertEquals(CourseOperation.CREATE, ParserUtil.parseCourseOperation(createOperationWithWhitespace));

        String deleteOperationWithWhitespace = WHITESPACE + VALID_DELETE_OPERATION + WHITESPACE;
        assertEquals(CourseOperation.DELETE, ParserUtil.parseCourseOperation(deleteOperationWithWhitespace));

        String switchOperationWithWhitespace = WHITESPACE + VALID_SWITCH_OPERATION + WHITESPACE;
        assertEquals(CourseOperation.SWITCH, ParserUtil.parseCourseOperation(switchOperationWithWhitespace));

        String editOperationWithWhitespace = WHITESPACE + VALID_EDIT_OPERATION + WHITESPACE;
        assertEquals(CourseOperation.EDIT, ParserUtil.parseCourseOperation(editOperationWithWhitespace));
    }
}

