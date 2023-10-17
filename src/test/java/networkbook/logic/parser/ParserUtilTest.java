package networkbook.logic.parser;

import static networkbook.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import networkbook.logic.parser.exceptions.ParseException;
import networkbook.model.person.Email;
import networkbook.model.person.Link;
import networkbook.model.person.Name;
import networkbook.model.person.Phone;
import networkbook.model.person.Priority;
import networkbook.model.tag.Tag;
import networkbook.model.util.UniqueList;
import networkbook.testutil.TypicalIndexes;

public class ParserUtilTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_LINK = "facebookcom";
    private static final String INVALID_GRADUATING_YEAR = "123a";
    private static final String INVALID_COURSE = "";
    private static final String INVALID_SPECIALISATION = "";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TAG = "#friend";
    private static final String INVALID_PRIORITY = "hi";

    private static final String VALID_NAME = "Rachel Walker";
    private static final String VALID_PHONE = "123456";
    private static final String VALID_LINK = "www.facebook.com/alice";
    private static final String VALID_LINK_2 = "https://www.google.com/?q=haha";
    private static final String VALID_GRADUATING_YEAR = "2000";
    private static final String VALID_COURSE = "Computer Science";
    private static final String VALID_SPECIALISATION = "Game Development";
    private static final String VALID_EMAIL = "rachel@example.com";
    private static final String VALID_EMAIL_2 = "nkn@what.com";
    private static final String VALID_TAG_1 = "friend";
    private static final String VALID_TAG_2 = "neighbour";
    private static final String VALID_PRIORITY = "meDIuM";

    private static final String WHITESPACE = " \t\r\n";

    @Test
    public void parseIndex_invalidInput_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseIndex("10 a"));
    }

    @Test
    public void parseIndex_outOfRangeInput_throwsParseException() {
        assertThrows(ParseException.class, ParserUtil.MESSAGE_INVALID_INDEX, ()
            -> ParserUtil.parseIndex(Long.toString(Integer.MAX_VALUE + 1)));
    }

    @Test
    public void parseIndex_validInput_success() throws Exception {
        // No whitespaces
        assertEquals(TypicalIndexes.INDEX_FIRST_PERSON, ParserUtil.parseIndex("1"));

        // Leading and trailing whitespaces
        assertEquals(TypicalIndexes.INDEX_FIRST_PERSON, ParserUtil.parseIndex("  1  "));
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
    public void parseLink_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseLink((String) null));
    }

    @Test
    public void parseLink_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseLink(INVALID_LINK));
    }

    @Test
    public void parseLinks_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseLinks(null));
    }

    @Test
    public void parseLinks_collectionWithInvalidLinks_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseLinks(Arrays.asList(VALID_LINK, INVALID_LINK)));
    }

    @Test
    public void parseLinks_collectionWithDuplicates_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseLinks(Arrays.asList(VALID_LINK, VALID_LINK)));
    }

    @Test
    public void parseLinks_emptyCollection_returnsEmptyListOfEmails() throws Exception {
        assertEquals(new UniqueList<Link>(), ParserUtil.parseLinks(Arrays.asList()));
    }

    @Test
    public void parseLinks_collectionWithValidLinks_returnsLinkList() throws Exception {
        UniqueList<Link> expectedList = new UniqueList<Link>()
                .setItems(Arrays.asList(new Link(VALID_LINK), new Link(VALID_LINK_2)));
        UniqueList<Link> actualList = ParserUtil.parseLinks(Arrays.asList(VALID_LINK, VALID_LINK_2));
        assertEquals(expectedList, actualList);
    }

    @Test
    public void parseGraduatingYear_null_throwsParseException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseGraduatingYear((String) null));
    }

    @Test
    public void parseGraduatingYear_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseGraduatingYear(INVALID_GRADUATING_YEAR));
    }

    @Test
    public void parseCourse_null_throwsParseException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseCourse((String) null));
    }

    @Test
    public void parseCourse_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseCourse(INVALID_COURSE));
    }

    @Test
    public void parseSpecialisation_null_throwsParseException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseSpecialisation((String) null));
    }

    @Test
    public void parseSpecialisation_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseSpecialisation(INVALID_COURSE));
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
    public void parseEmails_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseEmails(null));
    }

    @Test
    public void parseEmails_collectionWithInvalidEmails_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseEmails(Arrays.asList(VALID_EMAIL, INVALID_EMAIL)));
    }

    @Test
    public void parseEmails_collectionWithDuplicates_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseEmails(Arrays.asList(VALID_EMAIL, VALID_EMAIL)));
    }

    @Test
    public void parseEmails_emptyCollection_returnsEmptyListOfEmails() throws Exception {
        assertEquals(new UniqueList<Email>(), ParserUtil.parseEmails(Arrays.asList()));
    }

    @Test
    public void parseEmails_collectionWithValidEmails_returnsEmailList() throws Exception {
        UniqueList<Email> expectedList = new UniqueList<Email>()
                .setItems(Arrays.asList(new Email(VALID_EMAIL), new Email(VALID_EMAIL_2)));
        UniqueList<Email> actualList = ParserUtil.parseEmails(Arrays.asList(VALID_EMAIL, VALID_EMAIL_2));
        assertEquals(expectedList, actualList);
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
    public void parsePriority_validValue_success() throws Exception {
        Priority expectedPriority = new Priority(VALID_PRIORITY);
        assertEquals(expectedPriority, ParserUtil.parsePriority(VALID_PRIORITY));
    }

    @Test
    public void parsePriority_validValueWithWhitespace_success() throws Exception {
        Priority expectedPriority = new Priority(VALID_PRIORITY);
        assertEquals(expectedPriority, ParserUtil.parsePriority(WHITESPACE + VALID_PRIORITY + WHITESPACE));
    }

    @Test
    public void parsePriority_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parsePriority(INVALID_PRIORITY));
    }
}
