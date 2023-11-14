package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalEmails.EMAIL_FIRST_PERSON;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Description;
import seedu.address.model.person.Email;
import seedu.address.model.person.Gender;
import seedu.address.model.person.Major;
import seedu.address.model.person.Name;
import seedu.address.model.person.Nationality;
import seedu.address.model.person.Year;
import seedu.address.model.socialmedialink.SocialMediaLink;
import seedu.address.model.tutorial.Tutorial;

public class ParserUtilTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_MAJOR = "Computer Games";
    private static final String INVALID_YEAR = "1.5";
    private static final String INVALID_DESCRIPTION = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TUTORIAL = "1";
    private static final String INVALID_SM = "#www.invalid.com";
    private static final String INVALID_GENDER = "a";
    private static final String INVALID_NATIONALITY = "abc";
    private static final String INVALID_GROUP_NUMBER = "a";
    private static final String INVALID_TASK_INDEX_ZERO = "0";
    private static final String INVALID_TASK_INDEX_NEGATIVE = "-1";
    private static final String INVALID_TASK_INDEX_NON_NUMERIC = "abc";
    private static final String VALID_NAME = "Rachel Walker";
    private static final String VALID_MAJOR = "Computer Science";
    private static final String VALID_YEAR = "2";
    private static final String VALID_DESCRIPTION = "Web Developer";
    private static final String VALID_EMAIL = "rachel@u.nus.edu";
    private static final String VALID_TUTORIAL_FIRST = "01";
    private static final String VALID_TUTORIAL_SECOND = "02";
    private static final String VALID_SM_LINKEDIN = "https://www.linkedin.com/in/rachel";
    private static final String VALID_SM_GITHUB = "https://github.com/rachel";
    private static final String VALID_GENDER = "f";
    private static final String VALID_NATIONALITY = "local";
    private static final String VALID_GROUP_NUMBER = "1";
    private static final String VALID_TASK_INDEX = "5";
    private static final String WHITESPACE = " \t\r\n";

    @Test
    public void parseEmail_invalidInput_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseEmail(INVALID_EMAIL));
    }

    @Test
    public void parseEmail_validInput_success() throws Exception {
        // No whitespaces
        assertEquals(EMAIL_FIRST_PERSON, ParserUtil.parseEmail("first@u.nus.edu"));

        // Leading and trailing whitespaces
        assertEquals(EMAIL_FIRST_PERSON, ParserUtil.parseEmail("  first@u.nus.edu  "));
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
    public void parseMajor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseMajor((String) null));
    }

    @Test
    public void parseMajor_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseMajor(INVALID_MAJOR));
    }

    @Test
    public void parseMajor_validValueWithoutWhitespace_returnsMajor() throws Exception {
        Major expectedMajor = new Major(VALID_MAJOR);
        assertEquals(expectedMajor, ParserUtil.parseMajor(VALID_MAJOR));
    }

    @Test
    public void parseMajor_validValueWithWhitespace_returnsTrimmedMajor() throws Exception {
        String majorWithWhitespace = WHITESPACE + VALID_MAJOR + WHITESPACE;
        Major expectedMajor = new Major(VALID_MAJOR);
        assertEquals(expectedMajor, ParserUtil.parseMajor(majorWithWhitespace));
    }

    @Test
    public void parseYear_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseYear((String) null));
    }

    @Test
    public void parseYear_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseYear(INVALID_YEAR));
    }

    @Test
    public void parseYear_validValueWithoutWhitespace_returnsYear() throws Exception {
        Year expectedYear = new Year(VALID_YEAR);
        assertEquals(expectedYear, ParserUtil.parseYear(VALID_YEAR));
    }

    @Test
    public void parseYear_validValueWithWhitespace_returnsTrimmedYear() throws Exception {
        String yearWithWhitespace = WHITESPACE + VALID_YEAR + WHITESPACE;
        Year expectedYear = new Year(VALID_YEAR);
        assertEquals(expectedYear, ParserUtil.parseYear(yearWithWhitespace));
    }

    @Test
    public void parseDescription_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseDescription((String) null));
    }

    @Test
    public void parseDescription_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseDescription(INVALID_DESCRIPTION));
    }

    @Test
    public void parseDescription_validValueWithoutWhitespace_returnsDescription() throws Exception {
        Description expectedDescription = new Description(VALID_DESCRIPTION);
        assertEquals(expectedDescription, ParserUtil.parseDescription(VALID_DESCRIPTION));
    }

    @Test
    public void parseDescription_validValueWithWhitespace_returnsTrimmedDescription() throws Exception {
        String descriptionWithWhitespace = WHITESPACE + VALID_DESCRIPTION + WHITESPACE;
        Description expectedDescription = new Description(VALID_DESCRIPTION);
        assertEquals(expectedDescription, ParserUtil.parseDescription(descriptionWithWhitespace));
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
    public void parseTutorial_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTutorial(null));
    }

    @Test
    public void parseTutorial_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTutorial(INVALID_TUTORIAL));
    }

    @Test
    public void parseTutorial_validValueWithoutWhitespace_returnsTutorial() throws Exception {
        Tutorial expectedTutorial = new Tutorial(VALID_TUTORIAL_FIRST);
        assertEquals(expectedTutorial, ParserUtil.parseTutorial(VALID_TUTORIAL_FIRST));
    }

    @Test
    public void parseTutorial_validValueWithWhitespace_returnsTrimmedTutorial() throws Exception {
        String tutorialWithWhitespace = WHITESPACE + VALID_TUTORIAL_FIRST + WHITESPACE;
        Tutorial expectedTutorial = new Tutorial(VALID_TUTORIAL_FIRST);
        assertEquals(expectedTutorial, ParserUtil.parseTutorial(tutorialWithWhitespace));
    }

    @Test
    public void parseTutorials_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTutorials(null));
    }

    @Test
    public void parseTutorials_collectionWithInvalidTutorials_throwsParseException() {
        assertThrows(ParseException.class, () ->
                ParserUtil.parseTutorials(Arrays.asList(VALID_TUTORIAL_FIRST, INVALID_TUTORIAL)));
    }

    @Test
    public void parseTutorials_emptyCollection_returnsEmptySet() throws Exception {
        assertTrue(ParserUtil.parseTutorials(Collections.emptyList()).isEmpty());
    }

    @Test
    public void parseTutorials_collectionWithValidTutorials_returnsTutorialSet() throws Exception {
        Set<Tutorial> actualTutorialSet =
                ParserUtil.parseTutorials(Arrays.asList(VALID_TUTORIAL_FIRST, VALID_TUTORIAL_SECOND));
        Set<Tutorial> expectedTutorialSet =
                new HashSet<Tutorial>(Arrays.asList(new Tutorial(VALID_TUTORIAL_FIRST),
                        new Tutorial(VALID_TUTORIAL_SECOND)));

        assertEquals(expectedTutorialSet, actualTutorialSet);
    }

    @Test
    public void parseSocialMediaLink_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseSocialMediaLink(null));
    }

    @Test
    public void parseSocialMediaLink_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseSocialMediaLink(INVALID_SM));
    }

    @Test
    public void parseSocialMediaLink_validValueWithoutWhitespace_returnsSocialMediaLink() throws Exception {
        SocialMediaLink expectedSocialMediaLink = new SocialMediaLink(VALID_SM_LINKEDIN);
        assertEquals(expectedSocialMediaLink, ParserUtil.parseSocialMediaLink(VALID_SM_LINKEDIN));
    }

    @Test
    public void parseSocialMediaLink_validValueWithWhitespace_returnsTrimmedSocialMediaLink() throws Exception {
        String socialMediaLinkWithWhitespace = WHITESPACE + VALID_SM_LINKEDIN + WHITESPACE;
        SocialMediaLink expectedSocialMediaLink = new SocialMediaLink(VALID_SM_LINKEDIN);
        assertEquals(expectedSocialMediaLink, ParserUtil.parseSocialMediaLink(socialMediaLinkWithWhitespace));
    }

    @Test
    public void parseSocialMediaLinks_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseSocialMediaLinks(null));
    }

    @Test
    public void parseSocialMediaLinks_collectionWithInvalidSocialMediaLinks_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseSocialMediaLinks(
                Arrays.asList(VALID_SM_LINKEDIN, INVALID_SM)));
    }

    @Test
    public void parseSocialMediaLinks_emptyCollection_returnsEmptySet() throws Exception {
        assertTrue(ParserUtil.parseSocialMediaLinks(Collections.emptyList()).isEmpty());
    }

    @Test
    public void parseSocialMediaLinks_collectionWithValidSocialMediaLinks_returnsSocialMediaLinkSet() throws Exception {
        Set<SocialMediaLink> actualSocialMediaLinkSet = ParserUtil.parseSocialMediaLinks(
                Arrays.asList(VALID_SM_LINKEDIN, VALID_SM_GITHUB));
        Set<SocialMediaLink> expectedSocialMediaLinkSet = new HashSet<SocialMediaLink>(
                Arrays.asList(new SocialMediaLink(VALID_SM_LINKEDIN), new SocialMediaLink(VALID_SM_GITHUB)));

        assertEquals(expectedSocialMediaLinkSet, actualSocialMediaLinkSet);
    }

    @Test
    public void parseTutorials_validInput_success() throws Exception {
        Set<String> validTutorials = new HashSet<>(Arrays.asList("02", "05"));
        Set<Tutorial> expectedTutorialSet = new HashSet<>(Arrays.asList(new Tutorial("02"), new Tutorial("05")));

        Set<Tutorial> actualTutorialSet = ParserUtil.parseTutorials(validTutorials);

        assertEquals(expectedTutorialSet, actualTutorialSet);
    }

    @Test
    public void parseTutorials_invalidInput_throwsParseException() {
        List<String> invalidTutorials = Arrays.asList("25", "T02");
        for (String invalidTutorial : invalidTutorials) {
            assertThrows(ParseException.class, () ->
                ParserUtil.parseTutorials(Collections.singletonList(invalidTutorial)));
        }
    }

    @Test
    public void parseTutorials_duplicateInput_singleInstanceStored() throws Exception {
        List<String> duplicateTutorials = Arrays.asList("01", "01", "02", "02");
        Set<Tutorial> expectedTutorials = new HashSet<>(Arrays.asList(new Tutorial("01"), new Tutorial("02")));

        Set<Tutorial> actualTutorials = ParserUtil.parseTutorials(duplicateTutorials);

        assertEquals(expectedTutorials, actualTutorials);
    }

    @Test
    public void parseTaskIndex_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTaskIndex((String) null));
    }

    @Test
    public void parseTaskIndex_invalidValueZero_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTaskIndex(INVALID_TASK_INDEX_ZERO));
    }

    @Test
    public void parseTaskIndex_invalidValueNegative_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTaskIndex(INVALID_TASK_INDEX_NEGATIVE));
    }

    @Test
    public void parseTaskIndex_invalidValueNonNumeric_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTaskIndex(INVALID_TASK_INDEX_NON_NUMERIC));
    }

    @Test
    public void parseTaskIndex_validValueWithoutWhitespace_returnsTaskIndex() throws Exception {
        assertEquals(5, ParserUtil.parseTaskIndex(VALID_TASK_INDEX));
    }

    @Test
    public void parseTaskIndex_validValueWithWhitespace_returnsTrimmedTaskIndex() throws Exception {
        String taskIndexWithWhitespace = WHITESPACE + VALID_TASK_INDEX + WHITESPACE;
        assertEquals(5, ParserUtil.parseTaskIndex(taskIndexWithWhitespace));
    }

    @Test
    public void parseNationality_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseNationality((String) null));
    }

    @Test
    public void parseNationality_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseNationality(INVALID_NATIONALITY));
    }

    @Test
    public void parseNationality_validValueWithoutWhitespace_returnsNationality() throws Exception {
        Nationality expectedNationality = new Nationality(VALID_NATIONALITY);
        assertEquals(expectedNationality, ParserUtil.parseNationality(VALID_NATIONALITY));
    }

    @Test
    public void parseNationality_validValueWithWhitespace_returnsTrimmedNationality() throws Exception {
        String nationalityWithWhitespace = WHITESPACE + VALID_NATIONALITY + WHITESPACE;
        Nationality expectedNationality = new Nationality(VALID_NATIONALITY);
        assertEquals(expectedNationality, ParserUtil.parseNationality(nationalityWithWhitespace));
    }

    @Test
    public void parseGender_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseGender((String) null));
    }

    @Test
    public void parseGender_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseGender(INVALID_GENDER));
    }

    @Test
    public void parseGender_validValueWithoutWhitespace_returnsGender() throws Exception {
        Gender expectedGender = new Gender(VALID_GENDER);
        assertEquals(expectedGender, ParserUtil.parseGender(VALID_GENDER));
    }

    @Test
    public void parseGender_validValueWithWhitespace_returnsTrimmedGender() throws Exception {
        String genderWithWhitespace = WHITESPACE + VALID_GENDER + WHITESPACE;
        Gender expectedGender = new Gender(VALID_GENDER);
        assertEquals(expectedGender, ParserUtil.parseGender(genderWithWhitespace));
    }

    @Test
    public void parseGroupNumber_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseGroupNumber((String) null));
    }

    @Test
    public void parseGroupNumber_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseGroupNumber(INVALID_GROUP_NUMBER));
    }

    @Test
    public void parseGroupNumber_validValueWithoutWhitespace_returnsGroupNumber() throws Exception {
        assertEquals(1, ParserUtil.parseGroupNumber(VALID_GROUP_NUMBER));
    }

    @Test
    public void parseGroupNumber_validValueWithWhitespace_returnsTrimmedGroupNumber() throws Exception {
        String groupNumberWithWhitespace = WHITESPACE + VALID_GROUP_NUMBER + WHITESPACE;
        assertEquals(1, ParserUtil.parseGroupNumber(groupNumberWithWhitespace));
    }
}
