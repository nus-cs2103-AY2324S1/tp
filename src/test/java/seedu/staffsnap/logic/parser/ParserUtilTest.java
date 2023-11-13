package seedu.staffsnap.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.staffsnap.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.staffsnap.testutil.Assert.assertThrows;
import static seedu.staffsnap.testutil.TypicalIndexes.INDEX_FIRST_APPLICANT;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.staffsnap.logic.parser.exceptions.ParseException;
import seedu.staffsnap.model.applicant.Descriptor;
import seedu.staffsnap.model.applicant.Email;
import seedu.staffsnap.model.applicant.Name;
import seedu.staffsnap.model.applicant.Phone;
import seedu.staffsnap.model.applicant.Position;
import seedu.staffsnap.model.interview.Interview;
import seedu.staffsnap.model.interview.Rating;

public class ParserUtilTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_POSITION = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TYPE = "";
    private static final String INVALID_DESCRIPTOR = "nam";
    private static final String INVALID_RATING = "15.0";

    private static final String VALID_NAME = "Rachel Walker";
    private static final String VALID_PHONE = "123456";
    private static final String VALID_POSITION = "Software Engineer";
    private static final String VALID_EMAIL = "rachel@example.com";
    private static final String VALID_TYPE_1 = "behavioral";
    private static final String VALID_TYPE_2 = "technical";
    private static final String VALID_DESCRIPTOR = "name";
    private static final String VALID_RATING = "5.0";

    private static final String CAPITALIZED_NAME = "RACHEL WALKER";

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
        assertEquals(INDEX_FIRST_APPLICANT, ParserUtil.parseIndex("1"));

        // Leading and trailing whitespaces
        assertEquals(INDEX_FIRST_APPLICANT, ParserUtil.parseIndex("  1  "));
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
    public void parsePosition_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parsePosition((String) null));
    }

    @Test
    public void parsePosition_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parsePosition(INVALID_POSITION));
    }

    @Test
    public void parsePosition_validValueWithoutWhitespace_returnsPosition() throws Exception {
        Position expectedPosition = new Position(VALID_POSITION);
        assertEquals(expectedPosition, ParserUtil.parsePosition(VALID_POSITION));
    }

    @Test
    public void parsePosition_validValueWithWhitespace_returnsTrimmedPosition() throws Exception {
        String positionWithWhitespace = WHITESPACE + VALID_POSITION + WHITESPACE;
        Position expectedPosition = new Position(VALID_POSITION);
        assertEquals(expectedPosition, ParserUtil.parsePosition(positionWithWhitespace));
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
    public void parseInterview_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseInterview(null, null));
    }

    @Test
    public void parseInterview_invalidType_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseInterview(INVALID_TYPE, VALID_RATING));
    }

    @Test
    public void parseInterview_invalidRating_throwsParseException() {
        assertThrows(IllegalArgumentException.class, () -> ParserUtil.parseInterview(VALID_TYPE_1, INVALID_RATING));
    }

    @Test
    public void parseInterview_validValueWithoutWhitespace_returnsInterview() throws Exception {
        Interview expectedInterview = new Interview(VALID_TYPE_1, new Rating(VALID_RATING));
        assertEquals(expectedInterview, ParserUtil.parseInterview(VALID_TYPE_1, VALID_RATING));
    }

    @Test
    public void parseInterview_validValuesWithWhitespace_returnsTrimmedInterview() throws Exception {
        String typeWithWhitespace = WHITESPACE + VALID_TYPE_1 + WHITESPACE;
        String ratingWithWhitespace = WHITESPACE + VALID_RATING + WHITESPACE;
        Interview expectedInterview = new Interview(VALID_TYPE_1, new Rating(VALID_RATING));
        assertEquals(expectedInterview, ParserUtil.parseInterview(typeWithWhitespace, ratingWithWhitespace));
    }

    @Test
    public void parseInterviews_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseInterviews(null));
    }

    @Test
    public void parseInterviews_collectionWithInvalidInterviews_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil
                .parseInterviews(Arrays.asList(VALID_TYPE_1, INVALID_TYPE)));
    }

    @Test
    public void parseInterviews_emptyCollection_returnsEmptySet() throws Exception {
        assertTrue(ParserUtil.parseInterviews(Collections.emptyList()).isEmpty());
    }

    @Test
    public void parseInterviews_collectionWithValidInterviews_returnsInterviewList() throws Exception {
        List<Interview> actualInterviewList = ParserUtil
                .parseInterviews(Arrays.asList(VALID_TYPE_1, VALID_TYPE_2));
        List<Interview> expectedInterviewList = new ArrayList<>(
                Arrays.asList(new Interview(VALID_TYPE_1, new Rating("-")),
                        new Interview(VALID_TYPE_2, new Rating("-"))));

        assertEquals(expectedInterviewList, actualInterviewList);
    }

    @Test
    public void parseDescriptor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseDescriptor((String) null));
    }

    @Test
    public void parseDescriptor_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseDescriptor(INVALID_DESCRIPTOR));
    }

    @Test
    public void parseDescriptor_validValueWithoutWhitespace_returnsName() throws Exception {
        Descriptor expectedDescriptor = Descriptor.NAME;
        assertEquals(expectedDescriptor, ParserUtil.parseDescriptor(VALID_DESCRIPTOR));
    }

    @Test
    public void parseDescriptor_validValueWithWhitespace_returnsTrimmedName() throws Exception {
        String descriptorWithWhitespace = WHITESPACE + VALID_DESCRIPTOR + WHITESPACE;
        Descriptor expectedDescriptor = Descriptor.NAME;
        assertEquals(expectedDescriptor, ParserUtil.parseDescriptor(descriptorWithWhitespace));
    }

    @Test
    public void standardizeCapitalization_validValueWithCapitalization_returnsCapitalizedString() {
        assertEquals(VALID_NAME, ParserUtil.standardizeCapitalization(CAPITALIZED_NAME));
    }

    @Test
    public void parseType_validValuesWithUppercaseType_returnsLowercaseType() throws Exception {
        String typeWithUppercase = VALID_TYPE_1.toUpperCase();
        assertEquals(VALID_TYPE_1, ParserUtil.parseType(typeWithUppercase));
    }

    @Test
    public void parseType_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseType((String) null));
    }

    @Test
    public void parseType_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseType(INVALID_TYPE));
    }

    @Test
    public void parseType_validValueWithoutWhitespace_returnsType() throws Exception {
        String typeWithoutWhitespace = VALID_TYPE_1;
        assertEquals(VALID_TYPE_1, ParserUtil.parseType(typeWithoutWhitespace));
    }

    @Test
    public void parseType_validValueWithWhitespace_returnsTrimmedType() throws Exception {
        String typeWithWhitespace = WHITESPACE + VALID_TYPE_1 + WHITESPACE;
        assertEquals(VALID_TYPE_1, ParserUtil.parseType(typeWithWhitespace));
    }

    @Test
    public void parseRating_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseRating((String) null));
    }

    @Test
    public void parseRating_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseRating(INVALID_RATING));
    }

    @Test
    public void parseRating_validValueWithoutWhitespace_returnsRating() throws Exception {
        Rating expectedRating = new Rating(VALID_RATING);
        assertEquals(expectedRating, ParserUtil.parseRating(VALID_RATING));
    }

    @Test
    public void parseRating_validValueWithWhitespace_returnsTrimmedRating() throws Exception {
        String ratingWithWhitespace = WHITESPACE + VALID_RATING + WHITESPACE;
        Rating expectedRating = new Rating(VALID_RATING);
        assertEquals(expectedRating, ParserUtil.parseRating(ratingWithWhitespace));
    }
}
