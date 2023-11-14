package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.EmptyAddress;
import seedu.address.model.person.Name;
import seedu.address.model.person.NonEmptyAddress;
import seedu.address.model.person.Person;
import seedu.address.model.person.PersonTestUtil;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Tag;
import seedu.address.model.person.predicate.AddressContainsKeywordsPredicate;
import seedu.address.model.person.predicate.EmailContainsKeywordsPredicate;
import seedu.address.model.person.predicate.InsuranceContainsKeywordsPredicate;
import seedu.address.model.person.predicate.NameContainsKeywordsPredicate;
import seedu.address.model.person.predicate.PhoneContainsKeywordsPredicate;
import seedu.address.model.person.predicate.PriorityContainsKeywordsPredicate;
import seedu.address.model.person.predicate.RemarkContainsKeywordsPredicate;
import seedu.address.model.person.predicate.TagContainsKeywordsPredicate;

public class ParserUtilTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TAG = "#friend";
    private static final String INVALID_ADDRESS = PersonTestUtil.generateStringOfLength(101);

    private static final String VALID_NAME = "Rachel Walker";
    private static final String VALID_PHONE = "12345678";
    private static final String VALID_ADDRESS = "123 Main Street #0505";
    private static final String VALID_EMPTY_ADDRESS = " ";
    private static final String VALID_EMAIL = "rachel@example.com";
    private static final String VALID_TAG_FRIEND = "friend";
    private static final String VALID_TAG_NEIGHBOUR = "neighbour";
    private static final String VALID_NAME_KEYWORDS_ONE = "Alice";
    private static final String VALID_NAME_KEYWORDS_TWO = "Main";
    private static final String VALID_NAME_KEYWORDS_THREE = "12345678";

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
    public void parseAddress_emptyAddress_returnsEmptyAddress() throws Exception {
        Address expectedAddress = EmptyAddress.getEmptyAddress();
        assertEquals(expectedAddress, ParserUtil.parseAddress(VALID_EMPTY_ADDRESS));
    }

    @Test
    public void parseAddress_validValueWithoutWhitespace_returnsAddress() throws Exception {
        Address expectedAddress = new NonEmptyAddress(VALID_ADDRESS);
        assertEquals(expectedAddress, ParserUtil.parseAddress(VALID_ADDRESS));
    }

    @Test
    public void parseAddress_validValueWithWhitespace_returnsTrimmedAddress() throws Exception {
        String addressWithWhitespace = WHITESPACE + VALID_ADDRESS + WHITESPACE;
        Address expectedAddress = new NonEmptyAddress(VALID_ADDRESS);
        assertEquals(expectedAddress, ParserUtil.parseAddress(addressWithWhitespace));
    }

    @Test
    public void parseAddress_nullValue_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseAddress(null));
    }

    @Test
    public void parseAddress_invalidAddress_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseAddress(INVALID_ADDRESS));
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
        Tag expectedTag = new Tag(VALID_TAG_FRIEND);
        assertEquals(expectedTag, ParserUtil.parseTag(VALID_TAG_FRIEND));
    }

    @Test
    public void parseTag_validValueWithWhitespace_returnsTrimmedTag() throws Exception {
        String tagWithWhitespace = WHITESPACE + VALID_TAG_FRIEND + WHITESPACE;
        Tag expectedTag = new Tag(VALID_TAG_FRIEND);
        assertEquals(expectedTag, ParserUtil.parseTag(tagWithWhitespace));
    }

    @Test
    public void parseTags_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTags(null));
    }

    @Test
    public void parseTags_collectionWithInvalidTags_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTags(Arrays.asList(VALID_TAG_FRIEND, INVALID_TAG)));
    }

    @Test
    public void parseTags_emptyCollection_returnsEmptySet() throws Exception {
        assertTrue(ParserUtil.parseTags(Collections.emptyList()).isEmpty());
    }

    @Test
    public void parseTags_collectionWithValidTags_returnsTagSet() throws Exception {
        Set<Tag> actualTagSet = ParserUtil.parseTags(Arrays.asList(VALID_TAG_FRIEND, VALID_TAG_NEIGHBOUR));
        Set<Tag> expectedTagSet = new HashSet<>(Arrays.asList(new Tag(VALID_TAG_FRIEND), new Tag(VALID_TAG_NEIGHBOUR)));
        assertEquals(expectedTagSet, actualTagSet);
    }

    @Test
    public void parseNameKeywords_validKeywords_returnsNameContainsKeywordsPredicate() throws Exception {
        Predicate<Person> actualPredicate = ParserUtil.parseNameKeywords(VALID_NAME_KEYWORDS_ONE
                + " " + VALID_NAME_KEYWORDS_TWO);
        Predicate<Person> expectedPredicate = new NameContainsKeywordsPredicate(Arrays.asList(
                VALID_NAME_KEYWORDS_ONE, VALID_NAME_KEYWORDS_TWO));

        assertEquals(expectedPredicate, actualPredicate);
    }

    @Test
    public void parseAddressKeywords_validKeywords_returnsAddressContainsKeywordsPredicate() throws Exception {
        Predicate<Person> actualPredicate = ParserUtil.parseAddressKeywords(VALID_NAME_KEYWORDS_ONE
                + " " + VALID_NAME_KEYWORDS_THREE);
        Predicate<Person> expectedPredicate = new AddressContainsKeywordsPredicate(Arrays.asList(
                VALID_NAME_KEYWORDS_ONE, VALID_NAME_KEYWORDS_THREE));

        assertEquals(expectedPredicate, actualPredicate);
    }

    @Test
    public void parseEmailKeywords_validKeywords_returnsEmailContainsKeywordsPredicate() throws Exception {
        Predicate<Person> actualPredicate = ParserUtil.parseEmailKeywords(VALID_NAME_KEYWORDS_THREE
                + " " + VALID_NAME_KEYWORDS_TWO);
        Predicate<Person> expectedPredicate = new EmailContainsKeywordsPredicate(Arrays.asList(
                VALID_NAME_KEYWORDS_THREE, VALID_NAME_KEYWORDS_TWO));

        assertEquals(expectedPredicate, actualPredicate);
    }

    @Test
    public void parseInsuranceKeywords_validKeywords_returnsInsuranceContainsKeywordsPredicate() throws Exception {
        Predicate<Person> actualPredicate = ParserUtil.parseInsuranceKeywords(VALID_NAME_KEYWORDS_ONE
                + " " + VALID_NAME_KEYWORDS_TWO);
        Predicate<Person> expectedPredicate = new InsuranceContainsKeywordsPredicate(Arrays.asList(
                VALID_NAME_KEYWORDS_ONE, VALID_NAME_KEYWORDS_TWO));

        assertEquals(expectedPredicate, actualPredicate);
    }

    @Test
    public void parsePhoneKeywords_validKeywords_returnPhoneContainsKeywordsPredicate() throws Exception {
        Predicate<Person> actualPredicate = ParserUtil.parsePhoneKeywords(VALID_NAME_KEYWORDS_ONE
                + " " + VALID_NAME_KEYWORDS_THREE);
        Predicate<Person> expectedPredicate = new PhoneContainsKeywordsPredicate(Arrays.asList(
                VALID_NAME_KEYWORDS_ONE, VALID_NAME_KEYWORDS_THREE));

        assertEquals(expectedPredicate, actualPredicate);
    }

    @Test
    public void parseTagKeywords_validKeywords_returnsPriorityContainsKeywordsPredicate() throws Exception {
        Predicate<Person> actualPredicate = ParserUtil.parsePriorityKeywords(VALID_NAME_KEYWORDS_THREE
                + " " + VALID_NAME_KEYWORDS_TWO);
        Predicate<Person> expectedPredicate = new PriorityContainsKeywordsPredicate(Arrays.asList(
                VALID_NAME_KEYWORDS_THREE, VALID_NAME_KEYWORDS_TWO));

        assertEquals(expectedPredicate, actualPredicate);
    }

    @Test
    public void parseRemarkKeywords_validKeywords_returnsRemarkContainsKeywordsPredicate() throws Exception {
        Predicate<Person> actualPredicate = ParserUtil.parseRemarkKeywords(VALID_NAME_KEYWORDS_THREE
                + " " + VALID_NAME_KEYWORDS_TWO);
        Predicate<Person> expectedPredicate = new RemarkContainsKeywordsPredicate(Arrays.asList(
                VALID_NAME_KEYWORDS_THREE, VALID_NAME_KEYWORDS_TWO));

        assertEquals(expectedPredicate, actualPredicate);
    }
    @Test
    public void parsePriorityKeywords_validKeywords_returnsTagContainsKeywordsPredicate() throws Exception {
        Predicate<Person> actualPredicate = ParserUtil.parseTagKeywords(VALID_NAME_KEYWORDS_ONE
                + " " + VALID_NAME_KEYWORDS_TWO);
        Predicate<Person> expectedPredicate = new TagContainsKeywordsPredicate(Arrays.asList(
                VALID_NAME_KEYWORDS_ONE, VALID_NAME_KEYWORDS_TWO));

        assertEquals(expectedPredicate, actualPredicate);
    }

}
