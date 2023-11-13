package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.address.logic.parser.ParserUtil.parseDeadline;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TASK;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.IllegalArgumentException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.member.Member;
import seedu.address.model.task.Deadline;
import seedu.address.model.task.Description;
import seedu.address.model.task.Note;
import seedu.address.model.task.Priority;

public class ParserUtilTest {
    private static final String INVALID_DESCRIPTION = "Do Use/r Guide.";
    private static final String INVALID_MEMBER = "";

    private static final String VALID_DESCRIPTION = "Do User Guide";
    private static final String VALID_NOTE = "This is a valid note";
    private static final String INVALID_NOTE = "Do TaskWise v1/2"; // contains "/"
    private static final String VALID_MEMBER_1 = "George";
    private static final String VALID_MEMBER_2 = "Harry";

    private static final String WHITESPACE = " \t\r\n";
    private static final String VALID_DEADLINE_WITH_TIME_COLON = "25-10-2023 16:00";
    private static final String VALID_DEADLINE_WITH_TIME_DASH = "25-10-2023 16-00";
    private static final String VALID_DEADLINE_WITH_TIME_NO_COLON_DASH = "25-10-2023 1600";
    private static final String VALID_DEADLINE_SLASH_WITH_TIME = "25/10/2023 16:00";
    private static final String VALID_DEADLINE_MIX_WITH_TIME = "25/10-2023 16:00";
    private static final String VALID_DEADLINE_WITHOUT_TIME = "25-10-2023";
    private static final String VALID_DEADLINE_LEAP_DAY_WITHOUT_TIME = "29-02-2024";
    private static final String DEADLINE_INVALID_INPUT = "9-10-202316:00";
    private static final String DEADLINE_INVALID_DAY_INPUT = "9-10-2023 16:00";
    private static final String DEADLINE_INVALID_MONTH_INPUT = "19-9-2023 16:00";
    private static final String DEADLINE_INVALID_DAY_DEADLINE = "32-10-2023 16:00";
    private static final String DEADLINE_INVALID_LEAP_DAY_DEADLINE = "29-02-2023 16:00";
    private static final String DEADLINE_INVALID_MONTH_DEADLINE = "25-13-2023 16:00";
    private static final String DEADLINE_INVALID_YEAR_DEADLINE = "32-10-10000 16:00";

    private static final String DEADLINE_INVALID_DAY_INPUT_NO_TIME = "9-10-2023";
    private static final String DEADLINE_INVALID_MONTH_INPUT_NO_TIME = "19-9-2023";
    private static final String DEADLINE_INVALID_DAY_DEADLINE_NO_TIME = "32-10-2023";
    private static final String DEADLINE_INVALID_LEAP_DAY_DEADLINE_NO_TIME = "29-02-2023";
    private static final String DEADLINE_INVALID_MONTH_DEADLINE_NO_TIME = "25-13-2023";
    private static final String DEADLINE_INVALID_YEAR_DEADLINE_NO_TIME = "32-10-10000";
    private static final String VALID_PRIORITY = "high";
    private static final String INVALID_PRIORITY = "hiiii";

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
    public void parseIndex_outOfRangeNegativeInput_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_INVALID_INDEX, ()
                -> ParserUtil.parseIndex(Long.toString(Integer.MIN_VALUE)));
    }

    @Test
    public void parseIndex_validInput_success() throws Exception {
        // No whitespaces
        assertEquals(INDEX_FIRST_TASK, ParserUtil.parseIndex("1"));

        // Leading and trailing whitespaces
        assertEquals(INDEX_FIRST_TASK, ParserUtil.parseIndex("  1  "));
    }

    @Test
    public void parseName_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseDescription((String) null));
    }

    @Test
    public void parseName_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseDescription(INVALID_DESCRIPTION));
    }

    @Test
    public void parseName_normalValid_throwsParseException() throws Exception {
        Description expectedName = new Description("this is valid");
        assertEquals(ParserUtil.parseDescription("this is valid"), expectedName);
    }

    @Test
    public void parseName_validValueWithoutWhitespace_returnsName() throws Exception {
        Description expectedName = new Description(VALID_DESCRIPTION);
        assertEquals(expectedName, ParserUtil.parseDescription(VALID_DESCRIPTION));
    }

    @Test
    public void parseName_validValueWithWhitespace_returnsTrimmedName() throws Exception {
        String nameWithWhitespace = WHITESPACE + VALID_DESCRIPTION + WHITESPACE;
        Description expectedName = new Description(VALID_DESCRIPTION);
        assertEquals(expectedName, ParserUtil.parseDescription(nameWithWhitespace));
    }

    @Test
    public void parseNote_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseNote(null));
    }

    @Test
    public void parseNote_invalidValue_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> ParserUtil.parseNote(INVALID_NOTE));
    }

    @Test
    public void parseNote_validValueWithoutWhitespace_returnsNote() throws Exception {
        Note expectedNote = new Note(VALID_NOTE);
        assertEquals(expectedNote, ParserUtil.parseNote(VALID_NOTE));
    }

    @Test
    public void parseNote_validValueWithWhitespace_returnsTrimmedNote() throws Exception {
        String noteWithWhitespace = WHITESPACE + VALID_NOTE + WHITESPACE;
        Note expectedNote = new Note(VALID_NOTE);
        assertEquals(expectedNote, ParserUtil.parseNote(noteWithWhitespace));
    }

    @Test
    public void parseMember_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseMember(null));
    }

    @Test
    public void parseMember_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseMember(INVALID_MEMBER));
    }

    @Test
    public void parseMember_validValueWithoutWhitespace_returnsMember() throws Exception {
        Member expectedMember = new Member(VALID_MEMBER_1);
        assertEquals(expectedMember, ParserUtil.parseMember(VALID_MEMBER_1));
    }

    @Test
    public void parseMember_validValueWithWhitespace_returnsTrimmedMember() throws Exception {
        String memberWithWhitespace = WHITESPACE + VALID_MEMBER_1 + WHITESPACE;
        Member expectedMember = new Member(VALID_MEMBER_1);
        assertEquals(expectedMember, ParserUtil.parseMember(memberWithWhitespace));
    }

    @Test
    public void parseMembers_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseMembers(null));
    }

    @Test
    public void parseMembers_collectionWithInvalidMembers_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil
                .parseMembers(Arrays.asList(VALID_MEMBER_1, INVALID_MEMBER)));
    }

    @Test
    public void parseMembers_emptyCollection_returnsEmptySet() throws Exception {
        assertTrue(ParserUtil.parseMembers(Collections.emptyList()).isEmpty());
    }

    @Test
    public void parseMembers_collectionWithValidMembers_returnsMemberSet() throws Exception {
        Set<Member> actualMemberSet = ParserUtil.parseMembers(Arrays.asList(VALID_MEMBER_1, VALID_MEMBER_2));
        Set<Member> expectedMemberSet = new HashSet<Member>(
                Arrays.asList(new Member(VALID_MEMBER_1), new Member(VALID_MEMBER_2)));

        assertEquals(expectedMemberSet, actualMemberSet);
    }

    @Test
    public void parseMembers_collectionWithInvalidMembers_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> ParserUtil.parseMembers(
                Arrays.asList(INVALID_MEMBER, VALID_MEMBER_1)));
    }

    @Test
    public void parseMembers_collectionWithMembersDuplicates_returnsMemberSet() {
        assertDoesNotThrow(() -> ParserUtil.parseMembers(
                Arrays.asList(VALID_MEMBER_1, VALID_MEMBER_1, VALID_MEMBER_1)));
    }

    @Test
    public void parseSortOrder_validSortOrder_valid() {
        assertDoesNotThrow(() -> ParserUtil.parseSortOrder("a"));
    }

    @Test
    public void parseSortOrder_invalidSortOrder_valid() {
        assertThrows(IllegalArgumentException.class, () -> ParserUtil.parseSortOrder("this is an error"));
    }

    @Test
    public void parseSortType_validSortOrder_valid() {
        assertDoesNotThrow(() -> ParserUtil.parseSortType("dl"));
    }

    @Test
    public void parseSortOrder_validSortOrders_valid() {
        assertThrows(IllegalArgumentException.class, () -> ParserUtil.parseSortType("this is an error"));
    }

    @Test
    public void parseDeadline_validValueWithTimeColon_returnsDeadline() throws IllegalArgumentException {
        Deadline expectedDeadline = Deadline.of(LocalDateTime.of(2023, 10, 25, 16, 0));
        assertEquals(expectedDeadline, ParserUtil.parseDeadline(VALID_DEADLINE_WITH_TIME_COLON));
    }

    @Test
    public void parseDeadline_validValueWithTimeDash_returnsDeadline() throws IllegalArgumentException {
        Deadline expectedDeadline = Deadline.of(LocalDateTime.of(2023, 10, 25, 16, 0));
        assertEquals(expectedDeadline, ParserUtil.parseDeadline(VALID_DEADLINE_WITH_TIME_DASH));
    }

    @Test
    public void parseDeadline_validValueWithTimeNoColonDash_returnsDeadline() throws IllegalArgumentException {
        Deadline expectedDeadline = Deadline.of(LocalDateTime.of(2023, 10, 25, 16, 0));
        assertEquals(expectedDeadline, ParserUtil.parseDeadline(VALID_DEADLINE_WITH_TIME_NO_COLON_DASH));
    }

    @Test
    public void parseDeadline_dateDashValidValueWithTime_returnsDeadline() throws IllegalArgumentException {
        Deadline expectedDeadline = Deadline.of(LocalDateTime.of(2023, 10, 25, 16, 0));
        assertEquals(expectedDeadline, ParserUtil.parseDeadline(VALID_DEADLINE_SLASH_WITH_TIME));
    }

    @Test
    public void parseDeadline_dateMixValidValueWithTimeColon_returnsDeadline() throws IllegalArgumentException {
        Deadline expectedDeadline = Deadline.of(LocalDateTime.of(2023, 10, 25, 16, 0));
        assertEquals(expectedDeadline, ParserUtil.parseDeadline(VALID_DEADLINE_MIX_WITH_TIME));
    }

    @Test
    public void parseDeadline_validValueWithoutTime_returnsDeadline() throws IllegalArgumentException {
        Deadline expectedDeadline = Deadline.of(LocalDateTime.of(2023, 10, 25, 0, 0));
        assertEquals(expectedDeadline, ParserUtil.parseDeadline(VALID_DEADLINE_WITHOUT_TIME));
    }

    @Test
    public void parseDeadline_validLeapDayWithoutTime_returnsDeadline() throws IllegalArgumentException {
        Deadline expectedDeadline = Deadline.of(LocalDateTime.of(2024, 02, 29, 0, 0));
        assertEquals(expectedDeadline, ParserUtil.parseDeadline(VALID_DEADLINE_LEAP_DAY_WITHOUT_TIME));
    }

    @Test
    public void parseDeadline_invalidDay_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> parseDeadline(DEADLINE_INVALID_DAY_DEADLINE));
    }

    @Test
    public void parseDeadline_invalidLeapDay_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> parseDeadline(DEADLINE_INVALID_LEAP_DAY_DEADLINE));
    }

    @Test
    public void parseDeadline_invalidMonth_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> parseDeadline(DEADLINE_INVALID_MONTH_DEADLINE));
    }

    @Test
    public void parseDeadline_invalidYear_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> parseDeadline(DEADLINE_INVALID_YEAR_DEADLINE));
    }

    @Test
    public void parseDeadline_invalidInputWhitespaceOnly_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> parseDeadline(WHITESPACE));
    }

    @Test
    public void parseDeadline_invalidInputNoWhitespace_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> parseDeadline(DEADLINE_INVALID_INPUT));
    }

    @Test
    public void parseDeadline_invalidDayInput_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> parseDeadline(DEADLINE_INVALID_DAY_INPUT));
    }

    @Test
    public void parseDeadline_invalidMonthInput_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> parseDeadline(DEADLINE_INVALID_MONTH_INPUT));
    }

    @Test
    public void parseDeadline_invalidDayNoTime_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> parseDeadline(DEADLINE_INVALID_DAY_DEADLINE_NO_TIME));
    }

    @Test
    public void parseDeadline_invalidLeapDayNoTime_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> parseDeadline(DEADLINE_INVALID_LEAP_DAY_DEADLINE_NO_TIME));
    }

    @Test
    public void parseDeadline_invalidMonthNoTime_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> parseDeadline(DEADLINE_INVALID_MONTH_DEADLINE_NO_TIME));
    }

    @Test
    public void parseDeadline_invalidYearNoTime_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> parseDeadline(DEADLINE_INVALID_YEAR_DEADLINE_NO_TIME));
    }

    @Test
    public void parseDeadline_invalidDayInputNoTime_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> parseDeadline(DEADLINE_INVALID_DAY_INPUT_NO_TIME));
    }
    @Test
    public void parseDeadline_invalidMonthInputNoTime_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> parseDeadline(DEADLINE_INVALID_MONTH_INPUT_NO_TIME));
    }
    @Test
    public void parsePriority_validValue_success() throws Exception {
        assertEquals(Priority.HIGH, ParserUtil.parsePriority(VALID_PRIORITY));
    }
    @Test
    public void parsePriority_invalidValue_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> ParserUtil.parsePriority(INVALID_PRIORITY));
    }
}
