package wedlog.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static wedlog.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static wedlog.address.testutil.Assert.assertThrows;
import static wedlog.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import wedlog.address.logic.parser.exceptions.ParseException;
import wedlog.address.model.person.Address;
import wedlog.address.model.person.Email;
import wedlog.address.model.person.Name;
import wedlog.address.model.person.Phone;
import wedlog.address.model.person.RsvpStatus;
import wedlog.address.model.person.TableNumber;
import wedlog.address.model.tag.DietaryRequirement;
import wedlog.address.model.tag.Tag;

public class ParserUtilTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_RSVP = "gibberish";
    private static final String INVALID_DIETARY_REQUIREMENT = "no milk, cheese";
    private static final String INVALID_TABLE_NUMBER = "-1.0";
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_NAME = "Rachel Walker";
    private static final String VALID_PHONE = "123456";
    private static final String VALID_ADDRESS = "123 Main Street #0505";
    private static final String VALID_EMAIL = "rachel@example.com";
    private static final String VALID_RSVP_YES = "yes";
    private static final String VALID_RSVP_NO = "no";
    private static final String VALID_RSVP_UNKNOWN = "unknown";
    private static final String VALID_DIETARY_REQUIREMENT_1 = "no beef";
    private static final String VALID_DIETARY_REQUIREMENT_2 = "vegan";
    private static final String VALID_TABLE_NUMBER = "13";
    private static final String VALID_TAG_1 = "friend";
    private static final String VALID_TAG_2 = "neighbour";

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
    public void parseRsvp_null_returnsRsvpUnknown() throws Exception {
        RsvpStatus expectedRsvp = RsvpStatus.unknown();
        assertEquals(expectedRsvp, ParserUtil.parseRsvp((String) null));
    }

    @Test
    public void parseRsvp_emptyString_returnsRsvpUnknown() throws Exception {
        RsvpStatus expectedRsvp = RsvpStatus.unknown();
        assertEquals(expectedRsvp, ParserUtil.parseRsvp(""));
    }

    @Test
    public void parseRsvp_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseRsvp(INVALID_RSVP));
    }

    @Test
    public void parseRsvp_validValueWithWhitespace_returnsTrimmedRsvp() throws Exception {
        String rsvpYesWithWhitespace = WHITESPACE + VALID_RSVP_YES + WHITESPACE;
        String rsvpNoWithWhitespace = WHITESPACE + VALID_RSVP_NO + WHITESPACE;
        String rsvpUnknownWithWhitespace = WHITESPACE + VALID_RSVP_UNKNOWN + WHITESPACE;
        RsvpStatus expectedYesRsvp = new RsvpStatus(VALID_RSVP_YES);
        RsvpStatus expectedNoRsvp = new RsvpStatus(VALID_RSVP_NO);
        RsvpStatus expectedUnknownRsvp = new RsvpStatus(VALID_RSVP_UNKNOWN);
        assertEquals(expectedYesRsvp, ParserUtil.parseRsvp(rsvpYesWithWhitespace));
        assertEquals(expectedNoRsvp, ParserUtil.parseRsvp(rsvpNoWithWhitespace));
        assertEquals(expectedUnknownRsvp, ParserUtil.parseRsvp(rsvpUnknownWithWhitespace));
    }

    @Test
    public void parseRsvp_validValueWithoutWhitespace_returnsTrimmedRsvp() throws Exception {
        RsvpStatus expectedYesRsvp = new RsvpStatus(VALID_RSVP_YES);
        RsvpStatus expectedNoRsvp = new RsvpStatus(VALID_RSVP_NO);
        RsvpStatus expectedUnknownRsvp = new RsvpStatus(VALID_RSVP_UNKNOWN);
        assertEquals(expectedYesRsvp, ParserUtil.parseRsvp(VALID_RSVP_YES));
        assertEquals(expectedNoRsvp, ParserUtil.parseRsvp(VALID_RSVP_NO));
        assertEquals(expectedUnknownRsvp, ParserUtil.parseRsvp(VALID_RSVP_UNKNOWN));
    }

    @Test
    public void parseDietaryRequirement_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseDietaryRequirement(null));
    }

    @Test
    public void parseDietaryRequirement_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseDietaryRequirement(INVALID_DIETARY_REQUIREMENT));
    }

    @Test
    public void parseDietaryRequirement_validValueWithoutWhiteSpace_returnsDietaryRequirement() throws Exception {
        DietaryRequirement expectedDietaryRequirement = new DietaryRequirement(VALID_DIETARY_REQUIREMENT_1);
        assertEquals(expectedDietaryRequirement, ParserUtil.parseDietaryRequirement(VALID_DIETARY_REQUIREMENT_1));
    }

    @Test
    public void parseDietaryRequirement_validValueWithWhiteSpace_returnsTrimmedDietaryRequirement() throws Exception {
        String dietaryRequirementWithWhitespace = WHITESPACE + VALID_DIETARY_REQUIREMENT_1 + WHITESPACE;
        DietaryRequirement expectedDietaryRequirement = new DietaryRequirement(VALID_DIETARY_REQUIREMENT_1);
        assertEquals(expectedDietaryRequirement, ParserUtil.parseDietaryRequirement(dietaryRequirementWithWhitespace));
    }

    @Test
    public void parseDietaryRequirements_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseDietaryRequirements(null));
    }

    @Test
    public void parseDietaryRequirements_emptyCollection_returnsEmptySet() throws Exception {
        assertTrue(ParserUtil.parseDietaryRequirements(Collections.emptyList()).isEmpty());
    }

    @Test
    public void parseDietaryRequirements_nonEmptyCollection_returnsDietaryRequirementSet() throws Exception {
        Set<DietaryRequirement> actualDietaryRequirementSet = ParserUtil.parseDietaryRequirements(
                Arrays.asList(VALID_DIETARY_REQUIREMENT_1, VALID_DIETARY_REQUIREMENT_2));
        Set<DietaryRequirement> expectedDietaryRequirementSet = new HashSet<>(
                Arrays.asList(new DietaryRequirement(VALID_DIETARY_REQUIREMENT_1),
                        new DietaryRequirement(VALID_DIETARY_REQUIREMENT_2)));

        assertEquals(expectedDietaryRequirementSet, actualDietaryRequirementSet);
    }

    @Test
    public void parseTable_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTable(null));
    }

    @Test
    public void parseTable_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseEmail(INVALID_TABLE_NUMBER));
    }

    @Test
    public void parseTable_validValueWithoutWhitespace_returnsTableNumber() throws Exception {
        TableNumber expectedTableNumber = new TableNumber(VALID_TABLE_NUMBER);
        assertEquals(expectedTableNumber, ParserUtil.parseTable(VALID_TABLE_NUMBER));
    }

    @Test
    public void parseTable_validValueWithWhitespace_returnsTrimmedTableNumber() throws Exception {
        String tableNumberWithWhiteSpace = WHITESPACE + VALID_TABLE_NUMBER + WHITESPACE;
        TableNumber expectedTableNumber = new TableNumber(VALID_TABLE_NUMBER);
        assertEquals(expectedTableNumber, ParserUtil.parseTable(tableNumberWithWhiteSpace));
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
}
