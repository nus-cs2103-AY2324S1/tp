package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static seedu.address.commons.core.tab.Tab.CONTACT_PARAMETER;
import static seedu.address.commons.core.tab.Tab.EVENTS_PARAMETER;
import static seedu.address.commons.core.tab.Tab.FINANCE_PARAMETER;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.tab.Tab;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.event.EventDescription;
import seedu.address.model.event.EventName;
import seedu.address.model.event.Location;
import seedu.address.model.finance.Amount;
import seedu.address.model.finance.Description;
import seedu.address.model.person.Address;
import seedu.address.model.person.Company;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.TelegramName;


public class ParserUtilTest {
    private static final String INVALID_NAME = createMoreThanAllowedString();
    private static final String INVALID_PHONE = "+65";
    private static final String INVALID_ADDRESS = createMoreThanAllowedString();
    private static final String INVALID_COMPANY = createMoreThanAllowedString();
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TELEGRAM_NAME = "R@chel_!.";
    private static final String INVALID_AMOUNT = "-900";
    private static final String INVALID_CLIENT = createMoreThanAllowedString();
    private static final String INVALID_DESCRIPTION = createMoreThanAllowedString();
    private static final String VALID_NAME = "Rachel Walker";
    private static final String VALID_PHONE = "123456";
    private static final String VALID_ADDRESS = "123 Main Street #0505";
    private static final String VALID_EMAIL = "rachel@example.com";
    private static final String VALID_COMPANY = "Google";
    private static final String VALID_TELEGRAM_NAME = "@rachel_walker";
    private static final String VALID_EVENT_NAME = "Meeting with Alice";
    private static final String VALID_LOCATION = "Starbucks@Utown";
    private static final String INVALID_LOCATION = createMoreThanAllowedString();
    private static final String VALID_AMOUNT = "900";
    private static final String VALID_CLIENT = "Rachel Walker";
    private static final String VALID_DESCRIPTION = "Test Description";
    private static final String WHITESPACE = " \t\r\n";

    @Test
    public void constructor_valid_success() {
        ParserUtil pu = new ParserUtil();
        assertNotNull(pu);
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
        assertEquals(INDEX_FIRST, ParserUtil.parseIndex("1"));

        // Leading and trailing whitespaces
        assertEquals(INDEX_FIRST, ParserUtil.parseIndex("  1  "));
    }

    @Test
    public void parseTab_invalidInput_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTab(null));
    }

    @Test
    public void parseTab_validInput_success() throws ParseException {
        assertEquals(Tab.fromParameter(CONTACT_PARAMETER), ParserUtil.parseTab(CONTACT_PARAMETER));
        assertEquals(Tab.fromParameter(FINANCE_PARAMETER), ParserUtil.parseTab(FINANCE_PARAMETER));
        assertEquals(Tab.fromParameter(EVENTS_PARAMETER), ParserUtil.parseTab(EVENTS_PARAMETER));
    }

    @Test
    public void parseTab_validInputWithWhitespace_success() throws ParseException {
        assertEquals(Tab.fromParameter(CONTACT_PARAMETER), ParserUtil.parseTab("  " + CONTACT_PARAMETER + "  "));
        assertEquals(Tab.fromParameter(FINANCE_PARAMETER), ParserUtil.parseTab("  " + FINANCE_PARAMETER + "  "));
        assertEquals(Tab.fromParameter(EVENTS_PARAMETER), ParserUtil.parseTab("  " + EVENTS_PARAMETER + "  "));
    }

    @Test
    public void parseTab_invalidInput_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTab("invalidTab"));
        assertThrows(ParseException.class, () -> ParserUtil.parseTab(""));
        assertThrows(ParseException.class, () -> ParserUtil.parseTab("  "));
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
    public void parseTelegramName_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTelegramName(INVALID_TELEGRAM_NAME));
    }

    @Test
    public void parseTelegramName_validValueWithoutWhitespace_returnsTelegramName() throws Exception {
        TelegramName expectedTelegramName = new TelegramName(VALID_TELEGRAM_NAME);
        assertEquals(expectedTelegramName, ParserUtil.parseTelegramName(VALID_TELEGRAM_NAME));
    }

    @Test
    public void parseCompany_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseCompany(INVALID_COMPANY));
    }

    @Test
    public void parseCompany_validValueWithoutWhitespace_returnsCompany() throws Exception {
        Company expectedCompany = new Company(VALID_COMPANY);
        assertEquals(expectedCompany, ParserUtil.parseCompany(VALID_COMPANY));
    }

    @Test
    public void parseEventName_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseEventName((String) null));
    }

    @Test
    public void parseEventName_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseEventName(INVALID_NAME));
    }

    @Test
    public void parseEventName_validValueWithoutWhitespace_returnsName() throws Exception {
        EventName expectedName = new EventName(VALID_EVENT_NAME);
        assertEquals(expectedName, ParserUtil.parseEventName(VALID_EVENT_NAME));
    }

    @Test
    public void parseEventName_validValueWithWhitespace_returnsTrimmedName() throws Exception {
        String nameWithWhitespace = WHITESPACE + VALID_NAME + WHITESPACE;
        EventName expectedName = new EventName(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseEventName(nameWithWhitespace));
    }

    @Test
    public void parseLocation_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseLocation((String) null));
    }

    @Test
    public void parseLocation_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseLocation(INVALID_LOCATION));
    }

    @Test
    public void parseLocation_validValueWithoutWhitespace_returnsName() throws Exception {
        Location expectedName = new Location(VALID_LOCATION);
        assertEquals(expectedName, ParserUtil.parseLocation(VALID_LOCATION));
    }

    @Test
    public void parseLocation_validValueWithWhitespace_returnsTrimmedName() throws Exception {
        String nameWithWhitespace = WHITESPACE + VALID_LOCATION + WHITESPACE;
        Location expectedName = new Location(VALID_LOCATION);
        assertEquals(expectedName, ParserUtil.parseLocation(nameWithWhitespace));
    }

    @Test
    public void parseEventDescription_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseEventDescription((String) null));
    }

    @Test
    public void parseEventDescription_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseEventDescription(INVALID_DESCRIPTION));
    }

    @Test
    public void parseEventDescription_validValueWithoutWhitespace_returnsName() throws Exception {
        EventDescription expectedName = new EventDescription(VALID_DESCRIPTION);
        assertEquals(expectedName, ParserUtil.parseEventDescription(VALID_DESCRIPTION));
    }

    @Test
    public void parseDescription_validValueWithWhitespace_returnsTrimmedName() throws Exception {
        String nameWithWhitespace = WHITESPACE + VALID_DESCRIPTION + WHITESPACE;
        EventDescription expectedName = new EventDescription(VALID_DESCRIPTION);
        assertEquals(expectedName, ParserUtil.parseEventDescription(nameWithWhitespace));
    }

    @Test
    public void parseClient_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseClient(INVALID_NAME));
    }

    @Test
    public void parseClient_validValueWithoutWhitespace_returnsName() throws Exception {
        Person expectedPerson = new Person(new Name(VALID_NAME), new Phone("00000"), new Email("filler@email.com"),
                new Address(""), new Company(""), new TelegramName(""));
        assertEquals(expectedPerson, ParserUtil.parseClient(VALID_NAME));
    }

    @Test
    public void parseClients_invalidValue_throwsParseException() {
        Collection<String> invalidNames = new ArrayList<>();
        invalidNames.add(INVALID_NAME);
        assertThrows(ParseException.class, () -> ParserUtil.parseClients(invalidNames));
    }

    @Test
    public void parseClients_validValueWithoutWhitespace_returnsName() throws Exception {
        Person expectedPerson = new Person(new Name(VALID_NAME), new Phone("00000"), new Email("filler@email.com"),
                new Address(""), new Company(""), new TelegramName(""));
        Set<Person> expectedSet = new HashSet<>();
        expectedSet.add(expectedPerson);
        ArrayList<String> collectionOfValidName = new ArrayList<>();
        collectionOfValidName.add(VALID_NAME);
        assertEquals(expectedSet, ParserUtil.parseClients(collectionOfValidName));
    }

    @Test
    void parseAmount_withInvalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseAmount(INVALID_AMOUNT));
    }
    @Test
    public void parseAmount_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseAmount((String) null));
    }
    @Test
    public void parseAmount_validValueWithoutWhitespace_returnsAmount() throws Exception {
        Amount expectedAmount = new Amount(VALID_AMOUNT);
        assertEquals(expectedAmount, ParserUtil.parseAmount(VALID_AMOUNT));
    }
    @Test
    void parseDescription_withInvalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseDescription(INVALID_DESCRIPTION));
    }
    @Test
    public void parseDescription_validValueWithoutWhitespace_returnsDescription() throws Exception {
        Description expectedDescription = new Description(VALID_DESCRIPTION);
        assertEquals(expectedDescription, ParserUtil.parseDescription(VALID_DESCRIPTION));
    }

    public static String createMoreThanAllowedString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 257; i++) {
            sb.append("a");
        }
        return sb.toString();
    }
}

