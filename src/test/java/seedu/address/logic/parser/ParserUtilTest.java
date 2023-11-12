package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.ParserUtil.*;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.*;
import seedu.address.model.tag.Tag;
import seedu.address.model.tag.UniqueTagList;

public class ParserUtilTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TAG = "#friend";

    private static final String INVALID_STATUS = "invalidStatus";


    private static final String VALID_NAME = "Rachel Walker";
    private static final String VALID_PHONE = "123456";
    private static final String VALID_ADDRESS = "123 Main Street #0505";
    private static final String VALID_EMAIL = "rachel@example.com";
    private static final String VALID_TAG_1 = "intern";
    private static final String VALID_TAG_2 = "tech";

    private static final String VALID_STATUS_INTERVIEWED = "interviewed";
    private static final String VALID_STATUS_REJECTED = "rejected";
    private static final String VALID_STATUS_OFFERED = "offered";
    private static final String VALID_STATUS_PRELIMINARY = "preliminary";

    private static final String WHITESPACE = " \t\r\n";

    private static UniqueTagList uniqueTagList = new UniqueTagList();

    @BeforeEach
    public void clearTags() {
        if (uniqueTagList.contains(new Tag(VALID_TAG_1, "employment"))) {
            uniqueTagList.remove(new Tag(VALID_TAG_1, "employment"));
        }
        if (uniqueTagList.contains(new Tag(VALID_TAG_2, "dept"))) {
            uniqueTagList.remove(new Tag(VALID_TAG_2, "dept"));
        }
    }

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
    public void parseStatusType_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseStatusType(null));
    }

    @Test
    public void parseStatusType_invalidStatusType_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseStatusType(INVALID_STATUS));
    }

    @Test
    public void parseStatusType_validStatusType_returnsStatusType() throws ParseException {
        // interviewed
        assertEquals(StatusTypes.INTERVIEWED, ParserUtil.parseStatusType(VALID_STATUS_INTERVIEWED));

        // offered
        assertEquals(StatusTypes.OFFERED, ParserUtil.parseStatusType(VALID_STATUS_OFFERED));

        // rejected
        assertEquals(StatusTypes.REJECTED, ParserUtil.parseStatusType(VALID_STATUS_REJECTED));

        //preliminary
        assertEquals(StatusTypes.PRELIMINARY, ParserUtil.parseStatusType(VALID_STATUS_PRELIMINARY));
    }


    @Test
    public void parseTag_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTag(null, null));
    }

    @Test
    public void parseTag_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTag(INVALID_TAG, ""));
    }

    @Test
    public void parseTag_validValueWithoutWhitespace_returnsTag() throws Exception {
        Tag expectedTag = new Tag(VALID_TAG_1, "uncategorised");
        assertEquals(expectedTag, ParserUtil.parseTag(VALID_TAG_1, ""));
    }

    @Test
    public void parseTag_validValueWithWhitespace_returnsTrimmedTag() throws Exception {
        String tagWithWhitespace = WHITESPACE + VALID_TAG_1 + WHITESPACE;
        Tag expectedTag = new Tag(VALID_TAG_1, "uncategorised");
        assertEquals(expectedTag, ParserUtil.parseTag(tagWithWhitespace, ""));
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
    public void parseTags_collectionWithValidTagsCategoryNotSpecified_returnsTagSet() throws Exception {
        Set<Tag> actualTagSet = ParserUtil.parseTags(Arrays.asList(VALID_TAG_1, VALID_TAG_2));
        Set<Tag> expectedTagSet = new HashSet<Tag>(Arrays.asList(new Tag(VALID_TAG_1, "uncategorised"),
                new Tag(VALID_TAG_2, "uncategorised")));

        assertEquals(expectedTagSet, actualTagSet);
    }

    @Test
    public void parseTags_collectionWithValidTagsCategorySpecified_returnsTagSet() throws Exception {
        uniqueTagList.add(new Tag(VALID_TAG_1, "employment"));
        uniqueTagList.add(new Tag(VALID_TAG_2, "dept"));
        Set<Tag> actualTagSet = ParserUtil.parseTags(Arrays.asList("employment " + VALID_TAG_1, "dept " + VALID_TAG_2));
        Set<Tag> expectedTagSet = new HashSet<Tag>(Arrays.asList(new Tag(VALID_TAG_1, "employment"),
                new Tag(VALID_TAG_2, "dept")));

        assertEquals(expectedTagSet, actualTagSet);
    }

    @Test
    public void parseSinglePrefixTags_collectionWithNonExistingTags_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseSinglePrefixTags(Arrays.asList(VALID_TAG_1 + VALID_TAG_2)));
    }

    @Test
    public void parseTags_collectionWIthInvalidTagsCategorySpecified_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTags(Arrays.asList("employment " + VALID_TAG_1, "dept " + VALID_TAG_2)));
    }

    @Test
    public void parseTagCategories_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTagCategories(null));
    }

    @Test
    public void parseTagCategories_collectionWithInvalidTagName_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTagCategories(Arrays.asList("role " + INVALID_TAG)));
    }

    @Test
    public void parseTagCategories_collectionWithIncompleteTag_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTagCategories(Arrays.asList(VALID_TAG_2)));
    }

    @Test
    public void parseTagCategories_collectionWithIncompleteAndCompleteTags_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTagCategories(Arrays.asList(VALID_TAG_2, "employment" + VALID_TAG_1)));
    }


    @Test
    public void parseScore_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseScore(null));
    }

    @Test
    public void parseScore_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseScore("a"));
    }

    @Test
    public void parseScore_validValueWithoutWhitespace_returnsScore() throws Exception {
        assertEquals(ParserUtil.parseScore("1"), new Score(1));
    }

    @Test
    public void parseScore_negativeValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseScore("-1"));
    }

    @Test
    public void parseTagScore_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTagScore(null));
    }

    @Test
    public void parseTagScore_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTagScore("a"));
    }

    @Test
    public void parseTagScore_invalidValueTwoTabs_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTagScore("Interview 100 awadaw"));
    }
}
