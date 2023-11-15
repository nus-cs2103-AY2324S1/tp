package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndices.ONEBASED_ONE_TO_THREE;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Address;
import seedu.address.model.person.AnimalType;
import seedu.address.model.person.Availability;
import seedu.address.model.person.Email;
import seedu.address.model.person.Housing;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

public class ParserUtilTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TAG = "#friend";
    private static final String INVALID_AVAILABILITY = "notavailable";
    private static final String INVALID_ANIMAL_TYPE = "dog";
    private static final String INVALID_HOUSING = "hdb";

    private static final String VALID_NAME = "Rachel Walker";
    private static final String VALID_PHONE = "123456";
    private static final String VALID_ADDRESS = "123 Main Street #0505";
    private static final String VALID_EMAIL = "rachel@example.com";
    private static final String VALID_TAG_1 = "friend";
    private static final String VALID_TAG_2 = "neighbour";
    private static final String VALID_AVAILABILITY1 = "Available";
    private static final String VALID_AVAILABILITY2 = "NotAvailable";
    private static final String VALID_AVAILABILITY3 = "nil";
    private static final String VALID_ANIMAL_TYPE = "able.Dog";
    private static final String VALID_HOUSING = "HDB";

    private static final String WHITESPACE = " \t\r\n";

    @Test
    public void parseIndex_invalidInput_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseIndex("10 a"));
        assertThrows(ParseException.class, () -> ParserUtil.parseIndex("-9"));
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
    public void parseIndices_invalidInput_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseIndex("1 2 a b 3 c"));
        assertThrows(ParseException.class, () -> ParserUtil.parseIndex("-1 2 -4 6 3 -9"));

    }

    @Test
    public void parseIndices_outOfRangeInput_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_INVALID_INDEX, ()
                -> ParserUtil.parseIndices(Long.toString(Integer.MAX_VALUE + 1) + "2 3 1"));
    }

    @Test
    public void parseIndices_validInput_success() throws Exception {

        // Leading and trailing whitespaces
        assertEquals(ONEBASED_ONE_TO_THREE, ParserUtil.parseIndices("  1  2  3  "));

        // duplicates
        assertEquals(ONEBASED_ONE_TO_THREE, ParserUtil.parseIndices("1 2 2 3 3 1 3 2 1 3"));

        // white spaces and duplicates
        assertEquals(ONEBASED_ONE_TO_THREE, ParserUtil.parseIndices("   1 2   3  2 3    1    3   1  2 "));
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
    public void parseAvailability_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseAvailability((String) null));
    }

    @Test
    public void parseAvailability_invalidInput_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseAvailability(INVALID_AVAILABILITY));
    }

    @Test
    public void parseAvailability_validValueWithoutWhitespace_returnsName() throws Exception {
        Availability expectedAvailability = new Availability(VALID_AVAILABILITY2);
        assertEquals(expectedAvailability, ParserUtil.parseAvailability(VALID_AVAILABILITY2));
    }

    @Test
    public void parseAvailability_validValueWithWhitespace_returnsTrimmedName() throws Exception {
        String availabilityWithWhitespace = WHITESPACE + VALID_AVAILABILITY1 + WHITESPACE;
        Availability expectedAvailability = new Availability(VALID_AVAILABILITY1);
        assertEquals(expectedAvailability, ParserUtil.parseAvailability(availabilityWithWhitespace));
    }

    @Test
    public void parseAnimalType_null_throwsNullPointerException() {
        Availability validAvailability = new Availability(VALID_AVAILABILITY1);
        assertThrows(NullPointerException.class, () -> ParserUtil.parseAnimalType(((String) null),
                validAvailability));
    }

    @Test
    public void parseAnimalType_invalidInputWhenAvailable_throwsParseException() {
        Availability availableAvailability = new Availability(VALID_AVAILABILITY1);
        assertThrows(ParseException.class, () -> ParserUtil.parseAnimalType(INVALID_ANIMAL_TYPE,
               availableAvailability));
    }

    @Test
    public void parseAnimalType_invalidInputWhenNotAvailable_throwsParseException() {
        Availability notAvailableAvailability = new Availability(VALID_AVAILABILITY2);
        assertThrows(ParseException.class, () -> ParserUtil.parseAnimalType(INVALID_ANIMAL_TYPE,
                notAvailableAvailability));
    }

    @Test
    public void parseAnimalType_invalidInputWhenNilAvailable_throwsParseException() {
        Availability nilAvailability = new Availability(VALID_AVAILABILITY3);
        assertThrows(ParseException.class, () -> ParserUtil.parseAnimalType(INVALID_ANIMAL_TYPE,
                nilAvailability));
    }

    @Test
    public void parseAnimalType_validValueWithoutWhitespace_returnsName() throws Exception {
        Availability availableAvailability = new Availability(VALID_AVAILABILITY1);
        AnimalType expectedAnimalType = new AnimalType(VALID_ANIMAL_TYPE, availableAvailability);
        assertEquals(expectedAnimalType, ParserUtil.parseAnimalType(VALID_ANIMAL_TYPE,
                availableAvailability));
    }

    @Test
    public void parseAnimalType_validValueWithWhitespace_returnsTrimmedName() throws Exception {
        String animalTypeWithWhitespace = WHITESPACE + VALID_ANIMAL_TYPE + WHITESPACE;
        Availability availableAvailability = new Availability(VALID_AVAILABILITY1);
        AnimalType expectedAnimalType = new AnimalType(VALID_ANIMAL_TYPE, availableAvailability);
        assertEquals(expectedAnimalType, ParserUtil.parseAnimalType(animalTypeWithWhitespace,
                availableAvailability));
    }

    @Test
    public void parseHousing_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseHousing((String) null));
    }

    @Test
    public void parseHousing_invalidInput_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseHousing(INVALID_HOUSING));
    }

    @Test
    public void parseHousing_validValueWithoutWhitespace_returnsName() throws Exception {
        Housing expectedHousing = new Housing(VALID_HOUSING);
        assertEquals(expectedHousing, ParserUtil.parseHousing(VALID_HOUSING));
    }

    @Test
    public void parseHousing_validValueWithWhitespace_returnsTrimmedName() throws Exception {
        String housingWithWhitespace = WHITESPACE + VALID_HOUSING + WHITESPACE;
        Housing expectedHousing = new Housing(VALID_HOUSING);
        assertEquals(expectedHousing, ParserUtil.parseHousing(housingWithWhitespace));
    }

}
