package swe.context.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static swe.context.testutil.Assert.assertThrows;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import swe.context.commons.core.index.Index;
import swe.context.logic.parser.exceptions.ParseException;
import swe.context.model.alternate.AlternateContact;
import swe.context.model.contact.Email;
import swe.context.model.contact.Name;
import swe.context.model.contact.Note;
import swe.context.model.contact.Phone;
import swe.context.model.tag.Tag;
import swe.context.testutil.TestData;



public class ParserUtilTest {

    @Test
    public void parseIndex_invalidInput_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseIndex("10 a"));
    }

    @Test
    public void parseIndex_outOfRangeInput_throwsParseException() {
        assertThrows(ParseException.class, ParserUtil.MESSAGE_INDEX_NOT_POSITIVE, ()
            -> ParserUtil.parseIndex(Long.toString(Integer.MAX_VALUE + 1)));
    }

    @Test
    public void parseIndex_validInput_success() throws Exception {
        // No whitespaces
        assertEquals(TestData.IndexContact.FIRST_CONTACT, ParserUtil.parseIndex("1"));

        // Leading and trailing whitespaces
        assertEquals(TestData.IndexContact.FIRST_CONTACT, ParserUtil.parseIndex("  1  "));
    }

    @Test
    public void parseName_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseName((String) null));
    }

    @Test
    public void parseName_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseName(TestData.Invalid.NAME));
    }

    @Test
    public void parseName_validValueWithoutWhitespace_returnsName() throws Exception {
        Name expectedName = new Name(TestData.Valid.NAME_AMY);
        assertEquals(expectedName, ParserUtil.parseName(TestData.Valid.NAME_AMY));
    }

    @Test
    public void parseName_validValueWithWhitespace_returnsTrimmedName() throws Exception {
        String nameWithWhitespace = TestData.WHITESPACE + TestData.Valid.NAME_AMY + TestData.WHITESPACE;
        Name expectedName = new Name(TestData.Valid.NAME_AMY);
        assertEquals(expectedName, ParserUtil.parseName(nameWithWhitespace));
    }

    @Test
    public void parsePhone_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parsePhone((String) null));
    }

    @Test
    public void parsePhone_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parsePhone(TestData.Invalid.PHONE));
    }

    @Test
    public void parsePhone_validValueWithoutWhitespace_returnsPhone() throws Exception {
        Phone expectedPhone = new Phone(TestData.Valid.PHONE_AMY);
        assertEquals(expectedPhone, ParserUtil.parsePhone(TestData.Valid.PHONE_AMY));
    }

    @Test
    public void parsePhone_validValueWithWhitespace_returnsTrimmedPhone() throws Exception {
        String phoneWithWhitespace = TestData.WHITESPACE + TestData.Valid.PHONE_AMY + TestData.WHITESPACE;
        Phone expectedPhone = new Phone(TestData.Valid.PHONE_AMY);
        assertEquals(expectedPhone, ParserUtil.parsePhone(phoneWithWhitespace));
    }

    @Test
    public void parseEmail_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseEmail((String) null));
    }

    @Test
    public void parseEmail_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseEmail(TestData.Invalid.EMAIL));
    }

    @Test
    public void parseEmail_validValueWithoutWhitespace_returnsEmail() throws Exception {
        Email expectedEmail = new Email(TestData.Valid.EMAIL_AMY);
        assertEquals(expectedEmail, ParserUtil.parseEmail(TestData.Valid.EMAIL_AMY));
    }

    @Test
    public void parseEmail_validValueWithWhitespace_returnsTrimmedEmail() throws Exception {
        String emailWithWhitespace = TestData.WHITESPACE + TestData.Valid.EMAIL_AMY + TestData.WHITESPACE;
        Email expectedEmail = new Email(TestData.Valid.EMAIL_AMY);
        assertEquals(expectedEmail, ParserUtil.parseEmail(emailWithWhitespace));
    }

    @Test
    public void parseNote_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseNote((String) null));
    }

    @Test
    public void parseNote_validValueWithoutWhitespace_returnsNote() throws Exception {
        Note expectedAddress = new Note(TestData.Valid.NOTE_BOB);
        assertEquals(expectedAddress, ParserUtil.parseNote(TestData.Valid.NOTE_BOB));
    }

    @Test
    public void parseNote_validValueWithWhitespace_returnsTrimmedNote() throws Exception {
        String addressWithWhitespace = TestData.WHITESPACE + TestData.Valid.NOTE_BOB + TestData.WHITESPACE;
        Note expectedAddress = new Note(TestData.Valid.NOTE_BOB);
        assertEquals(expectedAddress, ParserUtil.parseNote(addressWithWhitespace));
    }

    @Test
    public void parseTag_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTag(null));
    }

    @Test
    public void parseTag_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTag(TestData.Invalid.Tag.UNDERSCORE_DASH));
    }

    @Test
    public void parseTag_validValueWithoutWhitespace_returnsTag() throws Exception {
        assertEquals(
            new Tag(TestData.Valid.Tag.ALPHANUMERIC),
            ParserUtil.parseTag(TestData.Valid.Tag.ALPHANUMERIC)
        );
    }

    @Test
    public void parseTag_validValueWithWhitespace_returnsTrimmedTag() throws Exception {
        String tagWithWhitespace = TestData.WHITESPACE + TestData.Valid.Tag.ALPHANUMERIC + TestData.WHITESPACE;
        Tag expectedTag = new Tag(TestData.Valid.Tag.ALPHANUMERIC);
        assertEquals(expectedTag, ParserUtil.parseTag(tagWithWhitespace));
    }

    @Test
    public void parseTags_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTags(null));
    }

    @Test
    public void parseTags_collectionWithInvalidTags_throwsParseException() {
        assertThrows(
            ParseException.class,
            () -> {
                ParserUtil.parseTags(
                    Arrays.asList(TestData.Valid.Tag.ALPHANUMERIC, TestData.Invalid.Tag.HASHTAG)
                );
            }
        );
    }

    @Test
    public void parseTags_emptyCollection_returnsEmptySet() throws Exception {
        assertTrue(ParserUtil.parseTags(Collections.emptyList()).isEmpty());
    }

    @Test
    public void parseTags_collectionWithValidTags_returnsTagSet() throws Exception {
        Set<Tag> actualTagSet = ParserUtil.parseTags(
            Arrays.asList(TestData.Valid.Tag.ALPHANUMERIC, TestData.Valid.Tag.ALPHANUMERIC_SPACES)
        );
        Set<Tag> expectedTagSet = new HashSet<Tag>(
            Arrays.asList(
                new Tag(TestData.Valid.Tag.ALPHANUMERIC),
                new Tag(TestData.Valid.Tag.ALPHANUMERIC_SPACES)
            )
        );

        assertEquals(expectedTagSet, actualTagSet);
    }

    @Test
    public void parseAlternate_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseAlternate(null));
    }

    @Test
    public void parseAlternate_validValueWithoutUnderscore_returnsAlternateContact() throws Exception {
        assertEquals(new AlternateContact(TestData.Valid.AlternateContact.ALPHANUMERIC),
                ParserUtil.parseAlternate(TestData.Valid.AlternateContact.ALPHANUMERIC));
    }

    @Test
    public void parseAlternate_validValueWithUnderscore_returnsAlternateContact() throws Exception {
        assertEquals(new AlternateContact(TestData.Valid.AlternateContact.ALPHANUMERIC_UNDERSCORE),
                ParserUtil.parseAlternate(TestData.Valid.AlternateContact.ALPHANUMERIC_UNDERSCORE));
    }

    @Test
    public void parseAlternates_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseAlternates(null));
    }

    @Test
    public void parseAlternates_collectionWithInvalidAlternateContacts_throwsParseException() {
        assertThrows(
                ParseException.class,
                () -> {
                    ParserUtil.parseTags(
                            Arrays.asList(TestData.Valid.AlternateContact.ALPHANUMERIC,
                                    TestData.Invalid.AlternateContact.MISSING_SYMBOL)
                    );
                }
        );
    }

    @Test
    public void parseAlternates_emptyCollection_returnsEmptySet() throws Exception {
        assertTrue(ParserUtil.parseAlternates(Collections.emptyList()).isEmpty());
    }

    @Test
    public void parseAlternates_collectionWithValidAlternateContacts_returnsAlternateContactSet() throws Exception {
        Set<AlternateContact> actualAlternateContactSet = ParserUtil.parseAlternates(
                Arrays.asList(TestData.Valid.AlternateContact.ALPHANUMERIC,
                        TestData.Valid.AlternateContact.ALPHANUMERIC_UNDERSCORE)
        );
        Set<AlternateContact> expectedAlternateContactSet = new HashSet<AlternateContact>(
                Arrays.asList(
                        new AlternateContact(TestData.Valid.AlternateContact.ALPHANUMERIC),
                        new AlternateContact(TestData.Valid.AlternateContact.ALPHANUMERIC_UNDERSCORE)
                )
        );

        assertEquals(expectedAlternateContactSet, actualAlternateContactSet);
    }

    @Test
    public void parseIndex_minValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseIndex("0"));
    }

    @Test
    public void parseIndex_maxValue_success() throws Exception {
        assertEquals(Index.fromOneBased(Integer.MAX_VALUE),
                ParserUtil.parseIndex(String.valueOf(Integer.MAX_VALUE)));
    }

    @Test
    public void parseName_specialCharacters_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseName("Amy*"));
    }

    @Test
    public void parsePhone_specialCharacters_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parsePhone("+65-1234"));
    }

    @Test
    public void parseEmail_specialCharacters_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseEmail("amy#email.com"));
    }

    @Test
    public void parseTags_mixedValidAndInvalidTags_throwsParseException() {
        assertThrows(ParseException.class,
                () -> ParserUtil.parseTags(Arrays.asList("validTag", "#InvalidTag")));
    }

    @Test
    public void parseAlternates_mixedValidAndInvalidAlternateContacts_throwsParseException() {
        assertThrows(ParseException.class,
                () -> ParserUtil.parseAlternates(Arrays.asList("Valid: Contact", "Invalid Contact")));
    }

}
