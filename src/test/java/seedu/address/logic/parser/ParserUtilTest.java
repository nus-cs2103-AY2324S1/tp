package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.time.MonthDay;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Balance;
import seedu.address.model.person.Birthday;
import seedu.address.model.person.Email;
import seedu.address.model.person.Linkedin;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Telegram;
import seedu.address.model.tag.Tag;

public class ParserUtilTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TAG = "#friend";
    private static final String INVALID_BALANCE = "-2.50";

    private static final String VALID_NAME = "Rachel Walker";
    private static final String VALID_PHONE = "123456";
    private static final String VALID_ADDRESS = "123 Main Street #0505";
    private static final String VALID_EMAIL = "rachel@example.com";
    private static final String VALID_TAG_1 = "friend";
    private static final String VALID_TAG_2 = "neighbour";
    private static final String VALID_BALANCE = "2.50";

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
    public void parseBalance_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseBalance(null));
    }

    @Test
    public void parseBalance_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseBalance(INVALID_BALANCE));
    }

    @Test
    public void parseBalance_validValueWithoutWhitespace_returnsBalance() throws Exception {
        Balance expectedBalance = new Balance(250);
        assertEquals(expectedBalance, ParserUtil.parseBalance(VALID_BALANCE));
    }

    @Test
    public void parseBalance_validValueWithWhitespace_returnsTrimmedBalance() throws Exception {
        String balanceWithWhitespace = WHITESPACE + VALID_BALANCE + WHITESPACE;
        Balance expectedBalance = new Balance(250);
        assertEquals(expectedBalance, ParserUtil.parseBalance(balanceWithWhitespace));
    }

    @Test
    public void parseBalance_largeAmount_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseBalance("123456.78"));
    }

    @Test
    public void parseBalance_noDollarSign_validBalance() throws Exception {
        Balance expectedBalance = new Balance(250);
        assertEquals(expectedBalance, ParserUtil.parseBalance("2.50"));
    }

    @Test
    public void parseBalance_noCents_returnsCorrectBalance() throws Exception {
        Balance expectedBalance = new Balance(20000); // 200 dollars in cents
        assertEquals(expectedBalance, ParserUtil.parseBalance("$200"));
    }

    @Test
    public void parseBalance_singleDecimalPlace_returnsCorrectBalance() throws Exception {
        Balance expectedBalance = new Balance(210); // 2.10 dollars in cents
        assertEquals(expectedBalance, ParserUtil.parseBalance("$2.1"));
    }

    @Test
    public void parseBalance_zeroBalance_returnsCorrectBalance() throws Exception {
        Balance expectedBalance = new Balance(0);
        assertEquals(expectedBalance, ParserUtil.parseBalance("$0"));
    }

    @Test
    public void parseBalance_nonNumericValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseBalance("abcd.ef"));
    }

    @Test
    public void parseBalance_tooManyDecimalPoints_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseBalance("12.34.56"));
    }

    @Test
    public void parseBalance_tooManyCentsDigits_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseBalance("12.345"));
    }

    @Test
    public void parseBalance_onlyCents_throwsParseException() throws Exception {
        assertThrows(ParseException.class, () -> ParserUtil.parseBalance(".50"));
    }

    @Test
    public void parseBalance_leadingZeroes_returnsCorrectBalance() throws Exception {
        Balance expectedBalance = new Balance(250); // 2.50 dollars in cents
        assertEquals(expectedBalance, ParserUtil.parseBalance("002.50"));

        expectedBalance = new Balance(500); // 5 dollars in cents
        assertEquals(expectedBalance, ParserUtil.parseBalance("00000000000005"));

        expectedBalance = new Balance(5); // 5 cents
        assertEquals(expectedBalance, ParserUtil.parseBalance("000000000000.05"));
    }

    @Test
    public void parseBalance_trailingZeroesAfterDecimal_returnsCorrectBalance() throws Exception {
        Balance expectedBalance = new Balance(200); // 2.00 dollars in cents
        assertEquals(expectedBalance, ParserUtil.parseBalance("2.00"));
    }

    @Test
    public void parseBalance_justDollarSign_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseBalance("$"));
    }

    @Test
    public void parseBalance_exceedTransactionLimit_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseBalance("$20000.01"));
        assertThrows(ParseException.class, () -> ParserUtil.parseBalance("$20001"));
    }

    @Test
    public void parseBalance_atTransactionLimit_returnsBalance() throws Exception {
        Balance expectedBalance = new Balance(2000000);
        assertEquals(expectedBalance, ParserUtil.parseBalance("$20000"));
        assertEquals(expectedBalance, ParserUtil.parseBalance("$20000.00"));
    }

    @Test
    public void parseBalance_negativeAmount_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseBalance("-$1"));
        assertThrows(ParseException.class, () -> ParserUtil.parseBalance("-1"));
    }

    @Test
    public void parseBalance_exceedDollarPartLimit_throwsParseException() {
        // Check for dollar part being 5 digits
        assertThrows(ParseException.class, () -> ParserUtil.parseBalance("$100000"));
        assertThrows(ParseException.class, () -> ParserUtil.parseBalance("$100000.00"));

        // EP: Leading zeroes in dollar part, but after stripping still >5 digits
        assertThrows(ParseException.class, () -> ParserUtil.parseBalance("$00000100000"));
        assertThrows(ParseException.class, () -> ParserUtil.parseBalance("$00000100000.00"));

        // EP: Without dollar sign
        assertThrows(ParseException.class, () -> ParserUtil.parseBalance("100000"));
        assertThrows(ParseException.class, () -> ParserUtil.parseBalance("100000.00"));
    }

    @Test
    public void parseBalance_badCharacters_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseBalance("$20,000")); // comma
        assertThrows(ParseException.class, () -> ParserUtil.parseBalance("$20.000")); // period in wrong place
        assertThrows(ParseException.class, () -> ParserUtil.parseBalance("$20k")); // letter
        assertThrows(ParseException.class, () -> ParserUtil.parseBalance("$20@00")); // special character
    }

    @Test
    public void parseBalance_multipleDollarSigns_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseBalance("$$20000"));
        assertThrows(ParseException.class, () -> ParserUtil.parseBalance("$200$00"));
        assertThrows(ParseException.class, () -> ParserUtil.parseBalance("$20000$"));
    }

    @Test
    public void parseBalance_emptyString_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseBalance(""));
    }

    @Test
    public void parseBalance_onlyWhitespace_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseBalance("    "));
    }

    @Test
    public void parseBalance_dollarSignNotAtStart_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseBalance("200$"));
    }

    @Test
    public void parseBalance_decimalAtStart_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseBalance(".200"));
    }

    @Test
    public void parseBalance_decimalAtEnd_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseBalance("200."));
    }

    @Test
    public void parseBalance_multipleDecimals_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseBalance("20.00.00"));
    }

    @Test
    public void parseBalance_moreThanTwoDecimalPlaces_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseBalance("200.001"));
    }

    @Test
    public void parseTelegram() throws Exception {
        // EP: Valid
        Telegram expectedTelegram = new Telegram("@telegram");
        assertEquals(expectedTelegram, ParserUtil.parseTelegram("@telegram"));

        // EP: Invalid
        // Blank
        assertThrows(ParseException.class, () -> ParserUtil.parseTelegram("1 tg/"));
        // Not starting with @
        assertThrows(ParseException.class, () -> ParserUtil.parseTelegram("1 tg/telegram"));
        // Not meeting 5 character requirement
        assertThrows(ParseException.class, () -> ParserUtil.parseTelegram("1 tg/@tel"));
        // Using characters other than alphanumeric and underscores
        assertThrows(ParseException.class, () -> ParserUtil.parseTelegram("1 tg/@tel?!"));
    }

    @Test
    public void parseBirthday() throws Exception {
        // EP: Valid
        Birthday expectedBirthday = new Birthday(MonthDay.of(6, 9));
        assertEquals(expectedBirthday, ParserUtil.parseBirthday("09/06"));

        // EP: Invalid
        // Blank
        assertThrows(ParseException.class, () -> ParserUtil.parseBirthday("1 b/"));
        // Not following format
        assertThrows(ParseException.class, () -> ParserUtil.parseBirthday("1 b/24-01-24"));
        // Invalid date
        assertThrows(ParseException.class, () -> ParserUtil.parseBirthday("1 b/./."));
        // Date that doesn't exist
        assertThrows(ParseException.class, () -> ParserUtil.parseBirthday("1 b/31/02"));
    }

    @Test
    public void parseLinkedin() throws Exception {
        // EP: Valid
        Linkedin expectedLinkedin = new Linkedin("linkedin");
        assertEquals(expectedLinkedin, ParserUtil.parseLinkedin("linkedin"));

        // EP: Invalid
        // Blank
        assertThrows(ParseException.class, () -> ParserUtil.parseTelegram("1 li/"));
        // Has special characters
        assertThrows(ParseException.class, () -> ParserUtil.parseLinkedin("1 li/?"));
        // Has whitespace
        assertThrows(ParseException.class, () -> ParserUtil.parseLinkedin("1 li/linked in"));
    }
}
