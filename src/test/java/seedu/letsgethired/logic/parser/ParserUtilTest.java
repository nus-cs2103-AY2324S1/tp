package seedu.letsgethired.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.letsgethired.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.letsgethired.testutil.Assert.assertThrows;
import static seedu.letsgethired.testutil.TypicalIndexes.INDEX_FIRST_APPLICATION;

import org.junit.jupiter.api.Test;

import seedu.letsgethired.logic.parser.exceptions.ParseException;
import seedu.letsgethired.model.application.Company;
import seedu.letsgethired.model.application.Cycle;
import seedu.letsgethired.model.application.Deadline;
import seedu.letsgethired.model.application.Note;
import seedu.letsgethired.model.application.Role;
import seedu.letsgethired.model.application.Status;

public class ParserUtilTest {
    private static final String INVALID_COMPANY = "Jane/Street";
    private static final String INVALID_ROLE = " ";
    private static final String INVALID_STATUS = " ";
    private static final String INVALID_CYCLE = "Summer!2024";
    private static final String INVALID_NOTE_EMPTY = "";
    private static final String INVALID_NOTE_SLASH = "slashes /";
    private static final String INVALID_SORT_ORDER = "b";
    private static final String INVALID_DEADLINE = "25/03/2024";
    private static final String VALID_COMPANY = "Jane Street";
    private static final String VALID_ROLE = "Full Stack Developer";
    private static final String VALID_STATUS = "Accepted";
    private static final String VALID_CYCLE = "Summer 2024";
    private static final String VALID_NOTE = "Need to brush up on leetcode hard";
    private static final String VALID_SORT_ORDER_ASCENDING = "a";
    private static final String VALID_SORT_ORDER_DESCENDING = "d";
    private static final String VALID_DEADLINE = "25 Mar 2024";


    private static final String WHITESPACE = " \t\r\n";

    @Test
    public void parseIndex_invalidInput_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseApplicationIndex("10 a"));
    }

    @Test
    public void parseIndex_outOfRangeInput_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_INVALID_INDEX, ()
            -> ParserUtil.parseApplicationIndex(Long.toString(Integer.MAX_VALUE + 1)));
    }

    @Test
    public void parseIndex_validInput_success() throws Exception {
        // No whitespaces
        assertEquals(INDEX_FIRST_APPLICATION, ParserUtil.parseApplicationIndex("1"));

        // Leading and trailing whitespaces
        assertEquals(INDEX_FIRST_APPLICATION, ParserUtil.parseApplicationIndex("  1  "));
    }

    @Test
    public void parseCompany_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseCompany((String) null));
    }

    @Test
    public void parseCompany_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseCompany(INVALID_COMPANY));
    }

    @Test
    public void parseCompany_validValueWithoutWhitespace_returnsName() throws Exception {
        Company expectedCompany = new Company(VALID_COMPANY);
        assertEquals(expectedCompany, ParserUtil.parseCompany(VALID_COMPANY));
    }

    @Test
    public void parseCompany_validValueWithWhitespace_returnsTrimmedName() throws Exception {
        String nameWithWhitespace = WHITESPACE + VALID_COMPANY + WHITESPACE;
        Company expectedCompany = new Company(VALID_COMPANY);
        assertEquals(expectedCompany, ParserUtil.parseCompany(nameWithWhitespace));
    }

    @Test
    public void parseRole_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseRole((String) null));
    }

    @Test
    public void parseRole_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseRole(INVALID_ROLE));
    }

    @Test
    public void parseRole_validValueWithoutWhitespace_returnsRole() throws Exception {
        Role expectedRole = new Role(VALID_ROLE);
        assertEquals(expectedRole, ParserUtil.parseRole(VALID_ROLE));
    }

    @Test
    public void parseRole_validValueWithWhitespace_returnsTrimmedRole() throws Exception {
        String roleWithWhitespace = WHITESPACE + VALID_ROLE + WHITESPACE;
        Role expectedRole = new Role(VALID_ROLE);
        assertEquals(expectedRole, ParserUtil.parseRole(roleWithWhitespace));
    }

    @Test
    public void parseStatus_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseStatus((String) null));
    }

    @Test
    public void parseStatus_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseStatus(INVALID_STATUS));
    }

    @Test
    public void parseStatus_validValueWithoutWhitespace_returnsStatus() throws Exception {
        Status expectedStatus = new Status(VALID_STATUS);
        assertEquals(expectedStatus, ParserUtil.parseStatus(VALID_STATUS));
    }

    @Test
    public void parseStatus_validValueWithWhitespace_returnsTrimmedStatus() throws Exception {
        String statusWithWhitespace = WHITESPACE + VALID_STATUS + WHITESPACE;
        Status expectedStatus = new Status(VALID_STATUS);
        assertEquals(expectedStatus, ParserUtil.parseStatus(statusWithWhitespace));
    }

    @Test
    public void parseCycle_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseCycle((String) null));
    }

    @Test
    public void parseCycle_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseCycle(INVALID_CYCLE));
    }

    @Test
    public void parseCycle_validValueWithoutWhitespace_returnsCycle() throws Exception {
        Cycle expectedCycle = new Cycle(VALID_CYCLE);
        assertEquals(expectedCycle, ParserUtil.parseCycle(VALID_CYCLE));
    }

    @Test
    public void parseCycle_validValueWithWhitespace_returnsTrimmedCycle() throws Exception {
        String cycleWithWhitespace = WHITESPACE + VALID_CYCLE + WHITESPACE;
        Cycle expectedCycle = new Cycle(VALID_CYCLE);
        assertEquals(expectedCycle, ParserUtil.parseCycle(cycleWithWhitespace));
    }

    @Test
    public void parseNote_validValueWithWhitespace_returnsTrimmedCycle() throws Exception {
        String noteWithWhitespace = WHITESPACE + VALID_NOTE + WHITESPACE;
        Note expectedNote = new Note(VALID_NOTE);
        assertEquals(expectedNote, ParserUtil.parseNote(noteWithWhitespace));
    }

    @Test
    public void parseNote_emptyValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseNote(INVALID_NOTE_EMPTY));
    }

    @Test
    public void parseNote_slashValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseNote(INVALID_NOTE_SLASH));
    }

    @Test
    public void parseNoteIndex_invalidInput_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseNoteIndex("10 a"));
    }

    @Test
    public void parseNoteIndex_outOfRangeInput_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_INVALID_INDEX, ()
                -> ParserUtil.parseNoteIndex(Long.toString(Integer.MAX_VALUE + 1)));
    }

    @Test
    public void parseNoteIndex_validInput_success() throws Exception {
        // No whitespaces
        assertEquals(INDEX_FIRST_APPLICATION, ParserUtil.parseNoteIndex("1"));

        // Leading and trailing whitespaces
        assertEquals(INDEX_FIRST_APPLICATION, ParserUtil.parseNoteIndex("  1  "));
    }

    @Test
    public void parseSortOrder_validInput_success() throws Exception {
        // No whitespaces
        assertEquals(SortOrder.ASCENDING, ParserUtil.parseSortOrder(VALID_SORT_ORDER_ASCENDING));

        // Leading and trailing whitespaces
        assertEquals(SortOrder.DESCENDING, ParserUtil.parseSortOrder(VALID_SORT_ORDER_DESCENDING));
    }

    @Test
    public void parseSortOrder_invalidInput_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseSortOrder(INVALID_SORT_ORDER));
    }

    @Test
    public void parseDeadline_validInput_success() throws Exception {
        Deadline expectedDeadline = new Deadline(VALID_DEADLINE);
        // No whitespaces
        assertEquals(expectedDeadline, ParserUtil.parseDeadline(VALID_DEADLINE));

        // Leading and trailing whitespaces
        assertEquals(expectedDeadline, ParserUtil.parseDeadline(WHITESPACE + VALID_DEADLINE + WHITESPACE));
    }

    @Test
    public void parseDeadline_invalidInput_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseDeadline(INVALID_DEADLINE));
    }
}
