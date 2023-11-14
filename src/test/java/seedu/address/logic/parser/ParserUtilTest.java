package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.internship.ApplicationStatus;
import seedu.address.model.internship.CompanyName;
import seedu.address.model.internship.Deadline;
import seedu.address.model.internship.Duration;
import seedu.address.model.internship.Role;
import seedu.address.model.internship.StartDate;
import seedu.address.model.requirement.Requirement;

class ParserUtilTest {
    @Test
    void testParseIndex_valid() throws ParseException {
        assertEquals(1, ParserUtil.parseIndex("1").getOneBased());
        assertEquals(2, ParserUtil.parseIndex("  2  ").getOneBased());
    }

    @Test
    void testParseIndex_invalid() {
        assertThrows(ParseException.class, () -> ParserUtil.parseIndex("0"));
        assertThrows(ParseException.class, () -> ParserUtil.parseIndex("-1"));
        assertThrows(ParseException.class, () -> ParserUtil.parseIndex("a"));
    }

    @Test
    void testParseCompanyName_valid() throws ParseException {
        String input = "Google";
        CompanyName companyName = ParserUtil.parseCompanyName(input);
        assertEquals("Google", companyName.toString());
    }

    @Test
    void testParseCompanyName_invalid() {
        String input = ""; // Assuming empty company name is invalid
        assertThrows(ParseException.class, () -> ParserUtil.parseCompanyName(input));
    }

    @Test
    void testParseRole_valid() throws ParseException {
        String input = "Developer";
        Role role = ParserUtil.parseRole(input);
        assertEquals("Developer", role.toString());
    }

    @Test
    void testParseRole_invalid() {
        String input = ""; // Assuming empty role is invalid
        assertThrows(ParseException.class, () -> ParserUtil.parseRole(input));
    }

    @Test
    void testParseApplicationStatus_valid() throws ParseException {
        String input = "Yet to apply";
        ApplicationStatus status = ParserUtil.parseApplicationStatus(input);
        assertEquals("Yet to apply", status.toString());
    }

    @Test
    void testParseApplicationStatus_invalid() {
        String input = "Not a status";
        assertThrows(ParseException.class, () -> ParserUtil.parseApplicationStatus(input));
    }

    @Test
    void testParseDeadline_withStartDate_valid() throws ParseException {
        String deadline = "01/01/2023";
        String startDate = "01/01/2024";
        Deadline result = ParserUtil.parseDeadline(deadline, startDate);
        assertEquals("01/01/2023", result.toString());
    }

    @Test
    void testParseDeadline_withStartDate_invalid() {
        String deadline = "invalid date";
        String startDate = "01/01/2022";
        assertThrows(ParseException.class, () -> ParserUtil.parseDeadline(deadline, startDate));
    }

    @Test
    void testParseDeadline_single_valid() throws ParseException {
        String deadline = "01/01/2023";
        Deadline result = ParserUtil.parseDeadline(deadline);
        assertEquals("01/01/2023", result.toString());
    }

    @Test
    void testParseDeadline_single_invalid() {
        String deadline = "02/01/2023";
        String startDate = "01/01/2023";
        assertThrows(ParseException.class, () -> ParserUtil.parseDeadline(deadline, startDate));
    }

    @Test
    void testParseStartDate_valid() throws ParseException {
        String input = "01/01/2022";
        StartDate startDate = ParserUtil.parseStartDate(input);
        assertEquals("01/01/2022", startDate.toString());
    }

    @Test
    void testParseStartDate_invalid() {
        String input = "invalid date";
        assertThrows(ParseException.class, () -> ParserUtil.parseStartDate(input));
    }

    @Test
    void testParseDuration_valid() throws ParseException {
        String input = "6";
        Duration duration = ParserUtil.parseDuration(input);
        assertEquals("6", duration.toString());
    }

    @Test
    void testParseDuration_invalid() {
        String input = "forever";
        assertThrows(ParseException.class, () -> ParserUtil.parseDuration(input));
    }

    @Test
    void testParseRequirements_valid() throws ParseException {
        Set<String> input = new HashSet<>(Arrays.asList("Java", "Python"));
        Set<Requirement> result = ParserUtil.parseRequirements(input);
        assertTrue(result.stream().anyMatch(r -> r.toString().equals("[Java]")));
        assertTrue(result.stream().anyMatch(r -> r.toString().equals("[Python]")));
    }

    @Test
    void testParseRequirements_invalid() {
        Set<String> input = new HashSet<>(Collections.singletonList(""));
        assertThrows(ParseException.class, () -> ParserUtil.parseRequirements(input));
    }

    @Test
    void testParseRequirement_valid() throws ParseException {
        String input = "Java";
        Requirement requirement = ParserUtil.parseRequirement(input);
        assertEquals("[Java]", requirement.toString());
    }

    @Test
    void testParseRequirement_invalid() {
        String input = ""; // Assuming empty requirement name is invalid
        assertThrows(ParseException.class, () -> ParserUtil.parseRequirement(input));
    }
}

