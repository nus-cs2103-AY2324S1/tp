package seedu.application.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.application.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.application.testutil.Assert.assertThrows;
import static seedu.application.testutil.TypicalIndexes.INDEX_FIRST_JOB;

import org.junit.jupiter.api.Test;

import seedu.application.logic.parser.exceptions.ParseException;
import seedu.application.model.job.Company;
import seedu.application.model.job.Role;

public class ParserUtilTest {
    private static final String INVALID_ROLE = "Softw@re Engineer";
    private static final String INVALID_COMPANY = "      ";
    private static final String VALID_ROLE = "Software Engineer";
    private static final String VALID_COMPANY = "Google";
    private static final String WHITESPACE = " \t\r\n";
    private static final String INVALID_INDUSTRY = "$Finance";

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
    public void parseIndustry_invalidValue_throwsParseException() throws Exception {
        assertThrows(ParseException.class, () -> ParserUtil.parseIndustry(INVALID_INDUSTRY));
    }
}
