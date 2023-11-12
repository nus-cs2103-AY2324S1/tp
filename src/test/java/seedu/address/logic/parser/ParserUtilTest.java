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

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Address;
import seedu.address.model.person.BloodType;
import seedu.address.model.person.Condition;
import seedu.address.model.person.Email;
import seedu.address.model.person.Ic;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

public class ParserUtilTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_IC1 = "Y9834876G";
    private static final String INVALID_IC2 = "T934865H";
    private static final String INVALID_PATIENT_TAG = "priority: HIGHEST";
    private static final String INVALID_DOCTOR_TAG1 = "NURSE";
    private static final String INVALID_BLOOD_TYPE1 = "o+";
    private static final String INVALID_BLOOD_TYPE2 = "O";
    private static final String INVALID_CONDITION = " ";

    private static final String VALID_NAME = "Rachel Walker";
    private static final String VALID_PHONE = "123456";
    private static final String VALID_ADDRESS = "123 Main Street #0505";
    private static final String VALID_EMAIL = "rachel@example.com";
    private static final String VALID_IC1 = "T1234567H";
    private static final String VALID_IC2 = "t1234567h";
    private static final String VALID_TAG1 = "FRIENDS";
    private static final String VALID_TAG2 = "STUDENT";
    private static final String VALID_PATIENT_TAG1 = "priority: LOW";
    private static final String VALID_PATIENT_TAG2 = "priority: HIGH";
    private static final String VALID_DOCTOR_TAG1 = "SURGEON";
    private static final String VALID_DOCTOR_TAG2 = "CARDIOLOGIST";
    private static final String VALID_BLOOD_TYPE = "O+";
    private static final String VALID_CONDITION = "appendicitis";

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
    public void parseIc_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseIc((String) null));
    }

    @Test
    public void parseIc_invalidStartingCharacter_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseIc(INVALID_IC1));
    }

    @Test
    public void parseIc_invalidNumbers_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseIc(INVALID_IC2));
    }

    @Test
    public void parseIc_validIcWithCaps_returnsIc() throws Exception {
        Ic expectedIc = new Ic(VALID_IC1);
        assertEquals(expectedIc, ParserUtil.parseIc(VALID_IC1));
    }

    @Test
    public void parseIc_validIcWithoutCaps_returnsIc() throws Exception {
        String inputIc = VALID_IC2.toUpperCase();
        Ic expectedIc = new Ic(inputIc);
        assertEquals(expectedIc, ParserUtil.parseIc(VALID_IC2));
    }

    @Test
    public void parseTag_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTag(null));
    }

    @Test
    public void parsePatientTag_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parsePatientTag(INVALID_PATIENT_TAG));
    }

    @Test
    public void parseDoctorTag_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseDoctorTag(INVALID_DOCTOR_TAG1));
    }

    @Test
    public void parseTag_validValueWithoutWhitespace_returnsTag() {
        Tag expectedTag = new Tag(VALID_TAG1);
        assertEquals(expectedTag, ParserUtil.parseTag(VALID_TAG1));
    }

    @Test
    public void parseTag_validValueWithWhitespace_returnsTrimmedTag() {
        String tagWithWhitespace = WHITESPACE + VALID_TAG1 + WHITESPACE;
        Tag expectedTag = new Tag(VALID_TAG1);
        assertEquals(expectedTag, ParserUtil.parseTag(tagWithWhitespace));
    }

    @Test
    public void parseTags_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTags(null));
    }

    @Test
    public void parsePatientTags_collectionWithMultipleTags_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parsePatientTags(Arrays.asList(VALID_PATIENT_TAG1,
                VALID_PATIENT_TAG2)));
    }

    @Test
    public void parseDoctorTags_collectionWithInvalidTags_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseDoctorTags(Arrays.asList(VALID_DOCTOR_TAG1,
                INVALID_DOCTOR_TAG1)));
    }

    @Test
    public void parseTags_emptyCollection_returnsEmptySet() throws Exception {
        assertTrue(ParserUtil.parseTags(Collections.emptyList()).isEmpty());
    }

    @Test
    public void parseTags_collectionWithValidTags_returnsTagSet() throws Exception {
        Set<Tag> actualTagSet = ParserUtil.parseTags(Arrays.asList(VALID_TAG1, VALID_TAG2));
        Set<Tag> expectedTagSet = new HashSet<Tag>(Arrays.asList(new Tag(VALID_TAG1),
                new Tag(VALID_TAG2)));

        assertEquals(expectedTagSet, actualTagSet);
    }

    @Test
    public void parseTags_collectionWithValidDoctorTags_returnsTagSet() throws Exception {
        Set<Tag> actualTagSet = ParserUtil.parseDoctorTags(Arrays.asList(VALID_DOCTOR_TAG1, VALID_DOCTOR_TAG2));
        Set<Tag> expectedTagSet = new HashSet<Tag>(Arrays.asList(new Tag(VALID_DOCTOR_TAG1),
                new Tag(VALID_DOCTOR_TAG2)));

        assertEquals(expectedTagSet, actualTagSet);
    }

    @Test
    public void parseBloodType_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseBloodType((String) null));
    }

    @Test
    public void parseBloodType_BloodTypeWithoutCaps_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseBloodType(INVALID_BLOOD_TYPE1));
    }

    @Test
    public void parseBloodType_invalidBloodType_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseBloodType(INVALID_BLOOD_TYPE2));
    }

    @Test
    public void parseBloodType_validBloodType_returnsBloodType() throws Exception {
        BloodType expectedBloodType = new BloodType(VALID_BLOOD_TYPE);
        assertEquals(expectedBloodType, ParserUtil.parseBloodType(VALID_BLOOD_TYPE));
    }

    @Test
    public void parseCondition_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseCondition((String) null));
    }

    @Test
    public void parseCondition_invalidCondition_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseCondition(INVALID_CONDITION));
    }

    @Test
    public void parseCondition_validCondition_returnsCondition() throws Exception {
        Condition expectedCondition = new Condition(VALID_CONDITION);
        assertEquals(expectedCondition, ParserUtil.parseCondition(VALID_CONDITION));
    }
}
