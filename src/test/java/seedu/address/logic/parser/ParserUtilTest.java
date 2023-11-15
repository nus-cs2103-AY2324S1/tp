package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.Assert.assertWarns;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.CommandWarnings;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.displayable.Address;
import seedu.address.model.displayable.Email;
import seedu.address.model.displayable.HouseInfo;
import seedu.address.model.displayable.Name;
import seedu.address.model.displayable.Phone;
import seedu.address.model.displayable.Priority;
import seedu.address.model.displayable.SortOrder;
import seedu.address.model.tag.Tag;

public class ParserUtilTest {
    private static final String INVALID_NAME = "  ";
    private static final String INVALID_PHONE = "+kokp;llk;j";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TAG = "  ";
    private static final String INVALID_INFO = "  ";
    private static final String INVALID_PRIORITY = "fijsgfkl";
    private static final String INVALID_SORT_ORDER = "b";
    private static final String UNRECOMMENDED_NAME = "R@chel";
    private static final String UNRECOMMENDED_PHONE = "(HP) 2934383, (OFF) 2930211";
    private static final String UNRECOMMENDED_EMAIL = "ffdoklf@f";
    private static final String UNRECOMMENDED_TAG = "#friend";
    private static final String UNRECOMMENDED_PRIORITY = "hiiiiiii";
    private static final String VALID_NAME = "Rachel Walker";
    private static final String VALID_PHONE = "123456";
    private static final String VALID_ADDRESS = "123 Main Street #0505";
    private static final String VALID_EMAIL = "rachel@example.com";
    private static final String VALID_INFO = "4 room flat";
    private static final String VALID_TAG_1 = "friend";
    private static final String VALID_TAG_2 = "neighbour";
    private static final String VALID_PRIORITY = "high";
    private static final String VALID_SORT_ORDER = "a";
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
        assertThrows(NullPointerException.class, () -> ParserUtil.parseName(new CommandWarnings(), (String) null));
    }

    @Test
    public void parseName_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseName(new CommandWarnings(), INVALID_NAME));
    }

    @Test
    public void parseName_validValueWithoutWhitespace_returnsName() throws Exception {
        Name expectedName = new Name(VALID_NAME);
        CommandWarnings commandWarnings = new CommandWarnings();
        assertEquals(expectedName, ParserUtil.parseName(commandWarnings, VALID_NAME));
        assertFalse(commandWarnings.containsWarnings());
    }

    @Test
    public void parseName_validValueWithWhitespace_returnsTrimmedName() throws Exception {
        String nameWithWhitespace = WHITESPACE + VALID_NAME + WHITESPACE;
        Name expectedName = new Name(VALID_NAME);
        CommandWarnings commandWarnings = new CommandWarnings();
        assertEquals(expectedName, ParserUtil.parseName(commandWarnings, nameWithWhitespace));
        assertFalse(commandWarnings.containsWarnings());
    }

    @Test
    public void parsePhone_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parsePhone(new CommandWarnings(), (String) null));
    }

    @Test
    public void parsePhone_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parsePhone(new CommandWarnings(), INVALID_PHONE));
    }

    @Test
    public void parsePhone_validValueWithoutWhitespace_returnsPhone() throws Exception {
        Phone expectedPhone = new Phone(VALID_PHONE);
        CommandWarnings commandWarnings = new CommandWarnings();
        assertEquals(expectedPhone, ParserUtil.parsePhone(commandWarnings, VALID_PHONE));
        assertFalse(commandWarnings.containsWarnings());
    }

    @Test
    public void parsePhone_validValueWithWhitespace_returnsTrimmedPhone() throws Exception {
        String phoneWithWhitespace = WHITESPACE + VALID_PHONE + WHITESPACE;
        Phone expectedPhone = new Phone(VALID_PHONE);
        CommandWarnings commandWarnings = new CommandWarnings();
        assertEquals(expectedPhone, ParserUtil.parsePhone(commandWarnings, phoneWithWhitespace));
        assertFalse(commandWarnings.containsWarnings());
    }

    @Test
    public void parseAddress_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseAddress(new CommandWarnings(), (String) null));
    }

    @Test
    public void parseAddress_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseAddress(new CommandWarnings(), INVALID_ADDRESS));
    }

    @Test
    public void parseAddress_validValueWithoutWhitespace_returnsAddress() throws Exception {
        Address expectedAddress = new Address(VALID_ADDRESS);
        CommandWarnings commandWarnings = new CommandWarnings();
        assertEquals(expectedAddress, ParserUtil.parseAddress(commandWarnings, VALID_ADDRESS));
        assertFalse(commandWarnings.containsWarnings());
    }

    @Test
    public void parseAddress_validValueWithWhitespace_returnsTrimmedAddress() throws Exception {
        String addressWithWhitespace = WHITESPACE + VALID_ADDRESS + WHITESPACE;
        Address expectedAddress = new Address(VALID_ADDRESS);
        CommandWarnings commandWarnings = new CommandWarnings();
        assertEquals(expectedAddress, ParserUtil.parseAddress(commandWarnings, addressWithWhitespace));
        assertFalse(commandWarnings.containsWarnings());
    }
    @Test
    public void parseInfo_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseHouseInfo(new CommandWarnings(), (String) null));
    }

    @Test
    public void parseInfo_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseHouseInfo(new CommandWarnings(), INVALID_INFO));
    }

    @Test
    public void parseInfo_validValueWithoutWhitespace_returnsInfo() throws Exception {
        HouseInfo expectedInfo = new HouseInfo(VALID_INFO);
        CommandWarnings commandWarnings = new CommandWarnings();
        assertEquals(expectedInfo, ParserUtil.parseHouseInfo(commandWarnings, VALID_INFO));
        assertFalse(commandWarnings.containsWarnings());
    }

    @Test
    public void parseInfo_validValueWithWhitespace_returnsTrimmedInfo() throws Exception {
        String infoWithWhitespace = WHITESPACE + VALID_INFO + WHITESPACE;
        HouseInfo expectedInfo = new HouseInfo(VALID_INFO);
        CommandWarnings commandWarnings = new CommandWarnings();
        assertEquals(expectedInfo, ParserUtil.parseHouseInfo(commandWarnings, infoWithWhitespace));
        assertFalse(commandWarnings.containsWarnings());
    }
    @Test
    public void parseEmail_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseEmail(new CommandWarnings(), (String) null));
    }

    @Test
    public void parseEmail_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseEmail(new CommandWarnings(), INVALID_EMAIL));
    }

    @Test
    public void parseEmail_validValueWithoutWhitespace_returnsEmail() throws Exception {
        Email expectedEmail = new Email(VALID_EMAIL);
        CommandWarnings commandWarnings = new CommandWarnings();
        assertEquals(expectedEmail, ParserUtil.parseEmail(commandWarnings, VALID_EMAIL));
        assertFalse(commandWarnings.containsWarnings());
    }

    @Test
    public void parseEmail_validValueWithWhitespace_returnsTrimmedEmail() throws Exception {
        String emailWithWhitespace = WHITESPACE + VALID_EMAIL + WHITESPACE;
        Email expectedEmail = new Email(VALID_EMAIL);
        CommandWarnings commandWarnings = new CommandWarnings();
        assertEquals(expectedEmail, ParserUtil.parseEmail(commandWarnings, emailWithWhitespace));
        assertFalse(commandWarnings.containsWarnings());
    }
    @Test
    public void parsePriority_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parsePriority(new CommandWarnings(), (String) null));
    }

    @Test
    public void parsePriority_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parsePriority(new CommandWarnings(), INVALID_PRIORITY));
    }

    @Test
    public void parsePriority_validValueWithoutWhitespace_returnsPriority() throws Exception {
        Priority expectedPriority = new Priority(VALID_PRIORITY);
        CommandWarnings commandWarnings = new CommandWarnings();
        assertEquals(expectedPriority, ParserUtil.parsePriority(commandWarnings, VALID_PRIORITY));
        assertFalse(commandWarnings.containsWarnings());
    }

    @Test
    public void parsePriority_validValueWithWhitespace_returnsTrimmedPriority() throws Exception {
        String priorityWithWhitespace = WHITESPACE + VALID_PRIORITY + WHITESPACE;
        Priority expectedPriority = new Priority(VALID_PRIORITY);
        CommandWarnings commandWarnings = new CommandWarnings();
        assertEquals(expectedPriority, ParserUtil.parsePriority(commandWarnings, priorityWithWhitespace));
        assertFalse(commandWarnings.containsWarnings());
    }

    @Test
    public void parseTag_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTag(new CommandWarnings(), null));
    }

    @Test
    public void parseTag_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTag(new CommandWarnings(), INVALID_TAG));
    }

    @Test
    public void parseTag_validValueWithoutWhitespace_returnsTag() throws Exception {
        Tag expectedTag = new Tag(VALID_TAG_1);
        CommandWarnings commandWarnings = new CommandWarnings();
        assertEquals(expectedTag, ParserUtil.parseTag(commandWarnings, VALID_TAG_1));
        assertFalse(commandWarnings.containsWarnings());
    }

    @Test
    public void parseTag_validValueWithWhitespace_returnsTrimmedTag() throws Exception {
        String tagWithWhitespace = WHITESPACE + VALID_TAG_1 + WHITESPACE;
        Tag expectedTag = new Tag(VALID_TAG_1);
        CommandWarnings commandWarnings = new CommandWarnings();
        assertEquals(expectedTag, ParserUtil.parseTag(commandWarnings, tagWithWhitespace));
        assertFalse(commandWarnings.containsWarnings());
    }

    @Test
    public void parseTags_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTags(new CommandWarnings(), null));
    }

    @Test
    public void parseTags_collectionWithInvalidTags_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTags(new CommandWarnings(),
                Arrays.asList(VALID_TAG_1, INVALID_TAG)));
    }

    @Test
    public void parseTags_emptyCollection_returnsEmptySet() throws Exception {
        CommandWarnings commandWarnings = new CommandWarnings();
        assertTrue(ParserUtil.parseTags(commandWarnings, Collections.emptyList()).isEmpty());
        assertFalse(commandWarnings.containsWarnings());
    }

    @Test
    public void parseTags_collectionWithValidTags_returnsTagSet() throws Exception {
        CommandWarnings commandWarnings = new CommandWarnings();
        Set<Tag> actualTagSet = ParserUtil.parseTags(commandWarnings, Arrays.asList(VALID_TAG_1, VALID_TAG_2));
        assertFalse(commandWarnings.containsWarnings());
        Set<Tag> expectedTagSet = new HashSet<Tag>(Arrays.asList(new Tag(VALID_TAG_1), new Tag(VALID_TAG_2)));

        assertEquals(expectedTagSet, actualTagSet);
    }

    @Test
    public void parseUnrecommendedName_flagsWarning() {
        assertWarns((CommandWarnings commandWarnings) -> ParserUtil.parseName(commandWarnings, UNRECOMMENDED_NAME),
                Name.MESSAGE_RECOMMENDATIONS);
    }

    @Test
    public void parseUnrecommendedPhone_flagsWarning() {
        assertWarns((CommandWarnings commandWarnings) -> ParserUtil.parsePhone(commandWarnings, UNRECOMMENDED_PHONE),
                Phone.MESSAGE_RECOMMENDATIONS);
    }

    @Test
    public void parseUnrecommendedEmail_flagsWarning() {
        assertWarns((CommandWarnings commandWarnings) -> ParserUtil.parseEmail(commandWarnings, UNRECOMMENDED_EMAIL),
                Email.MESSAGE_RECOMMENDATIONS);
    }

    @Test
    public void parseUnrecommendedTag_flagsWarning() {
        assertWarns((CommandWarnings commandWarnings) -> ParserUtil.parseTag(commandWarnings, UNRECOMMENDED_TAG),
                Tag.MESSAGE_RECOMMENDATIONS);
    }

    @Test
    public void parseUnrecommendedPriority_flagsWarning() {
        assertWarns((CommandWarnings commandWarnings) ->
                        ParserUtil.parsePriority(commandWarnings, UNRECOMMENDED_PRIORITY),
                Priority.MESSAGE_RECOMMENDATIONS);
    }

    @Test
    public void parseSortOrder_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseSortOrder(new CommandWarnings(), null));
    }

    @Test
    public void parseSortOrder_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseSortOrder(new CommandWarnings(), INVALID_SORT_ORDER));
    }

    @Test
    public void parseSortOrder_validValueWithoutWhitespace_returnsSortOrder() throws Exception {
        SortOrder expectedSortOrder = new SortOrder(VALID_SORT_ORDER);
        assertEquals(expectedSortOrder, ParserUtil.parseSortOrder(new CommandWarnings(), VALID_SORT_ORDER));
    }

    @Test
    public void parseSortOrder_validValueWithWhitespace_returnsSortOrder() throws Exception {
        String sortOrderWithWhitespace = WHITESPACE + VALID_SORT_ORDER + WHITESPACE;
        SortOrder expectedSortOrder = new SortOrder(VALID_SORT_ORDER);
        assertEquals(expectedSortOrder, ParserUtil.parseSortOrder(new CommandWarnings(), sortOrderWithWhitespace));
    }
}
