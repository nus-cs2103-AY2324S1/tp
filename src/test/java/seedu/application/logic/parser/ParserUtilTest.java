package seedu.application.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.application.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.application.testutil.Assert.assertThrows;
import static seedu.application.testutil.TypicalIndexes.INDEX_FIRST;

import org.junit.jupiter.api.Test;

import seedu.application.logic.parser.exceptions.ParseException;
import seedu.application.model.job.Company;
import seedu.application.model.job.Deadline;
import seedu.application.model.job.Industry;
import seedu.application.model.job.JobType;
import seedu.application.model.job.Role;
import seedu.application.model.job.Status;
import seedu.application.model.job.interview.InterviewAddress;
import seedu.application.model.job.interview.InterviewDateTime;
import seedu.application.model.job.interview.InterviewType;

public class ParserUtilTest {
    private static final String INVALID_ROLE = "Softw@re Engineer";
    private static final String INVALID_COMPANY = "      ";
    private static final String INVALID_DEADLINE = "18 August";
    private static final String INVALID_STATUS = "Submitted";
    private static final String INVALID_JOBTYPE = "Intern";
    private static final String INVALID_INDUSTRY = "$Finance";
    private static final String INVALID_INTERVIEW_TYPE = "Tech";
    private static final String INVALID_INTERVIEW_ADDRESS = "a/@ffi$e";
    private static final String VALID_ROLE = "Software Engineer";
    private static final String VALID_COMPANY = "Google";
    private static final String VALID_DEADLINE = "Dec 31 2030 1200";
    private static final String VALID_DEADLINE_UNFORMATTED = "dEc 31 2030 1200";
    private static final String VALID_STATUS = "Pending";
    private static final String VALID_JOBTYPE = "INTERNSHIP";
    private static final String VALID_INDUSTRY = "Finance";
    private static final String VALID_INTERVIEW_TYPE = "TECHNICAL";
    private static final String VALID_INTERVIEW_ADDRESS = "Office";
    private static final String WHITESPACE = " \t\r\n";

    @Test
    public void parseIndex_invalidInput_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseIndex("10 a"));
    }

    @Test
    public void parseIndex_outOfRangeInput_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_INVALID_INDEX, () -> ParserUtil.parseIndex(
            Long.toString(Integer.MAX_VALUE + 1)));
    }

    @Test
    public void parseIndex_validInput_success() throws Exception {
        // No whitespaces
        assertEquals(INDEX_FIRST, ParserUtil.parseIndex("1"));

        // Leading and trailing whitespaces
        assertEquals(INDEX_FIRST, ParserUtil.parseIndex("  1  "));
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
    public void parseDeadline_validValueConversionRequired_returnsDeadlineWithConvertedTime() throws Exception {
        Deadline expectedDeadline = new Deadline("Dec 31 2023 0000");
        assertEquals(expectedDeadline, ParserUtil.parseDeadline("Dec 31 2023 2400"));
    }

    @Test
    public void parseDeadline_validUnformattedValue_returnsFormattedDeadline() throws Exception {
        Deadline expectedDeadline = new Deadline(VALID_DEADLINE);
        assertEquals(expectedDeadline, ParserUtil.parseDeadline(VALID_DEADLINE_UNFORMATTED));
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

    @Test
    public void parseInterviewType_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseInterviewType((String) null));
    }

    @Test
    public void parseInterviewType_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseInterviewType(INVALID_INTERVIEW_TYPE));
    }

    @Test
    public void parseInterviewType_validValueWithoutWhitespace_returnsJobType() throws Exception {
        InterviewType expectedInterviewType = new InterviewType(VALID_INTERVIEW_TYPE);
        assertEquals(expectedInterviewType, ParserUtil.parseInterviewType(VALID_INTERVIEW_TYPE));
    }

    @Test
    public void parseInterviewType_validValueWithWhitespace_returnsTrimmedInterviewType() throws Exception {
        String interviewTypeWithWhitespace = WHITESPACE + VALID_INTERVIEW_TYPE + WHITESPACE;
        InterviewType expectedInterviewType = new InterviewType(VALID_INTERVIEW_TYPE);
        assertEquals(expectedInterviewType, ParserUtil.parseInterviewType(interviewTypeWithWhitespace));
    }

    @Test
    public void parseInterviewDateTime_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseInterviewDateTime((String) null));
    }

    @Test
    public void parseInterviewDateTime_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseInterviewDateTime(INVALID_DEADLINE));
    }

    @Test
    public void parseInterviewDateTime_validValueWithoutWhitespace_returnsDeadline() throws Exception {
        InterviewDateTime expectedInterviewDateTime = new InterviewDateTime(VALID_DEADLINE);
        assertEquals(expectedInterviewDateTime, ParserUtil.parseInterviewDateTime(VALID_DEADLINE));
    }

    @Test
    public void parseInterviewDateTime_validValueWithWhitespace_returnsTrimmedDeadline() throws Exception {
        String interviewDateTimeWithWhitespace = WHITESPACE + VALID_DEADLINE + WHITESPACE;
        InterviewDateTime expectedInterviewDateTime = new InterviewDateTime(VALID_DEADLINE);
        assertEquals(expectedInterviewDateTime, ParserUtil.parseInterviewDateTime(interviewDateTimeWithWhitespace));
    }

    @Test
    public void parseInterviewDateTime_validValueConversionRequired_returnsDeadlineWithConvertedTime() throws Exception {
        InterviewDateTime expectedInterviewDateTime = new InterviewDateTime("Dec 31 2023 0000");
        assertEquals(expectedInterviewDateTime, ParserUtil.parseInterviewDateTime("Dec 31 2023 2400"));
    }

    @Test
    public void parseInterviewDateTime_validUnformattedValue_returnsFormattedDeadline() throws Exception {
        InterviewDateTime expectedInterviewDateTime = new InterviewDateTime(VALID_DEADLINE);
        assertEquals(expectedInterviewDateTime, ParserUtil.parseInterviewDateTime(VALID_DEADLINE_UNFORMATTED));
    }

    @Test
    public void parseInterviewAddress_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseInterviewAddress((String) null));
    }

    @Test
    public void parseInterviewAddress_validValueWithoutWhitespace_returnsRole() throws Exception {
        InterviewAddress expectedAddress = new InterviewAddress(VALID_INTERVIEW_ADDRESS);
        assertEquals(expectedAddress, ParserUtil.parseInterviewAddress(VALID_INTERVIEW_ADDRESS));
    }

    @Test
    public void parseInterviewAddress_validValueWithWhitespace_returnsTrimmedName() throws Exception {
        String interviewAddressWithWhitespace = WHITESPACE + VALID_INTERVIEW_ADDRESS + WHITESPACE;
        InterviewAddress expectedAddress = new InterviewAddress(VALID_INTERVIEW_ADDRESS);
        assertEquals(expectedAddress, ParserUtil.parseInterviewAddress(interviewAddressWithWhitespace));
    }
}
