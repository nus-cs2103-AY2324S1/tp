package seedu.application.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.application.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.application.testutil.Assert.assertThrows;
import static seedu.application.testutil.TypicalIndexes.INDEX_FIRST_JOB;

import org.junit.jupiter.api.Test;

import seedu.application.logic.parser.exceptions.ParseException;
import seedu.application.model.job.*;

public class ParserUtilTest {
    private static final String INVALID_ROLE = "Softw@re Engineer";
    private static final String INVALID_COMPANY = "      ";
    private static final String INVALID_DEADLINE = "18 August";
    private static final String INVALID_STATUS = "Submitted";
    private static final String INVALID_JOBTYPE = "Intern";
    private static final String INVALID_INDUSTRY = "$Finance";
    private static final String VALID_ROLE = "Software Engineer";
    private static final String VALID_COMPANY = "Google";
    private static final String VALID_DEADLINE = "Dec 31 2030 1200";
    private static final String VALID_STATUS = "Pending";
    private static final String VALID_JOBTYPE = "INTERNSHIP";
    private static final String VALID_INDUSTRY = "Finance";
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
        assertEquals(INDEX_FIRST_JOB, ParserUtil.parseIndex("1"));

        // Leading and trailing whitespaces
        assertEquals(INDEX_FIRST_JOB, ParserUtil.parseIndex("  1  "));
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
        Role expectedName = new Role(VALID_ROLE);
        assertEquals(expectedName, ParserUtil.parseRole(VALID_ROLE));
    }

    @Test
    public void parseRole_validValueWithWhitespace_returnsTrimmedName() throws Exception {
        String roleWithWhitespace = WHITESPACE + VALID_ROLE + WHITESPACE;
        Role expectedRole = new Role(VALID_ROLE);
        assertEquals(expectedRole, ParserUtil.parseRole(roleWithWhitespace));
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
    public void parseCompany_validValueWithoutWhitespace_returnsCompany() throws Exception {
        Company expectedCompany = new Company(VALID_COMPANY);
        assertEquals(expectedCompany, ParserUtil.parseCompany(VALID_COMPANY));
    }

    @Test
    public void parseCompany_validValueWithWhitespace_returnsTrimmedCompany() throws Exception {
        String companyWithWhitespace = WHITESPACE + VALID_COMPANY + WHITESPACE;
        Company expectedCompany = new Company(VALID_COMPANY);
        assertEquals(expectedCompany, ParserUtil.parseCompany(companyWithWhitespace));
    }

    @Test
    public void parseDeadline_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseDeadline((String) null));
    }

    @Test
    public void parseDeadline_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseDeadline(INVALID_DEADLINE));
    }

    @Test
    public void parseDeadline_validValueWithoutWhitespace_returnsDeadline() throws Exception {
        Deadline expectedDeadline = new Deadline(VALID_DEADLINE);
        assertEquals(expectedDeadline, ParserUtil.parseDeadline(VALID_DEADLINE));
    }

    @Test
    public void parseDeadline_validValueWithWhitespace_returnsTrimmedDeadline() throws Exception {
        String deadlineWithWhitespace = WHITESPACE + VALID_DEADLINE + WHITESPACE;
        Deadline expectedDeadline = new Deadline(VALID_DEADLINE);
        assertEquals(expectedDeadline, ParserUtil.parseDeadline(deadlineWithWhitespace));
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
    public void parseJobType_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseJobType((String) null));
    }

    @Test
    public void parseJobType_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseJobType(INVALID_JOBTYPE));
    }

    @Test
    public void parseJobType_validValueWithoutWhitespace_returnsJobType() throws Exception {
        JobType expectedJobType = new JobType(VALID_JOBTYPE);
        assertEquals(expectedJobType, ParserUtil.parseJobType(VALID_JOBTYPE));
    }

    @Test
    public void parseJobType_validValueWithWhitespace_returnsTrimmedJobType() throws Exception {
        String jobTypeWithWhitespace = WHITESPACE + VALID_JOBTYPE + WHITESPACE;
        JobType expectedJobType = new JobType(VALID_JOBTYPE);
        assertEquals(expectedJobType, ParserUtil.parseJobType(jobTypeWithWhitespace));
    }

    @Test
    public void parseIndustry_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseIndustry((String) null));
    }

    @Test
    public void parseIndustry_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseIndustry(INVALID_INDUSTRY));
    }

    @Test
    public void parseIndustry_validValueWithoutWhitespace_returnsIndustry() throws Exception {
        Industry expectedIndustry = new Industry(VALID_INDUSTRY);
        assertEquals(expectedIndustry, ParserUtil.parseIndustry(VALID_INDUSTRY));
    }

    @Test
    public void parseIndustry_validValueWithWhitespace_returnsTrimmedIndustry() throws Exception {
        String industryWithWhitespace = WHITESPACE + VALID_INDUSTRY + WHITESPACE;
        Industry expectedIndustry = new Industry(VALID_INDUSTRY);
        assertEquals(expectedIndustry, ParserUtil.parseIndustry(industryWithWhitespace));
    }
}
