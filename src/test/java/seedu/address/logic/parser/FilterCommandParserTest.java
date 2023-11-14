package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandInternshipParserUtilTest.assertInternshipParseFailure;
import static seedu.address.logic.parser.CommandInternshipParserUtilTest.assertInternshipParseSuccess;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FilterCommand;
import seedu.address.model.InternshipModel;
import seedu.address.model.internship.ApplicationStatusContainsKeywordsPredicate;
import seedu.address.model.internship.CompanyNameContainsKeywordsPredicate;
import seedu.address.model.internship.Deadline;
import seedu.address.model.internship.DeadlineWithinRangePredicate;
import seedu.address.model.internship.Duration;
import seedu.address.model.internship.DurationWithinRangePredicate;
import seedu.address.model.internship.RequirementContainsKeywordsPredicate;
import seedu.address.model.internship.RoleContainsKeywordsPredicate;
import seedu.address.model.internship.StartDate;
import seedu.address.model.internship.StartDateWithinRangePredicate;

public class FilterCommandParserTest {
    private final FilterCommandParser parser = new FilterCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertInternshipParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommandParser.MESSAGE_NON_EMPTY));
    }

    @Test
    public void parse_prefixWithEmptyFields_throwsParseException() {
        assertInternshipParseFailure(parser, " c/",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommandParser.MESSAGE_NON_EMPTY));
        assertInternshipParseFailure(parser, " ro/",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommandParser.MESSAGE_NON_EMPTY));
        assertInternshipParseFailure(parser, " du/",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommandParser.MESSAGE_NON_EMPTY));
        assertInternshipParseFailure(parser, " s/",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommandParser.MESSAGE_NON_EMPTY));
        assertInternshipParseFailure(parser, " re/",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommandParser.MESSAGE_NON_EMPTY));
        assertInternshipParseFailure(parser, " de/",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommandParser.MESSAGE_NON_EMPTY));
    }

    @Test
    public void parse_defaultArgs_returnsShowAllPredicate() {
        FilterCommand expectedFilterCommand = new FilterCommand(InternshipModel.PREDICATE_SHOW_ALL_INTERNSHIPS);
        assertInternshipParseSuccess(parser, " default", expectedFilterCommand);
    }

    @Test
    public void parse_multiplePredicates_throwsParseException() {
        assertInternshipParseFailure(parser, " c/Google ro/swe",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommandParser.MESSAGE_ONE_PARAMETER));
        // Multiple different prefixes
        assertInternshipParseFailure(parser, " c/Google s/20/03/2023-20/06/2023",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommandParser.MESSAGE_ONE_PARAMETER));
    }

    @Test
    public void parse_invalidDurationArg_throwsParseException() {
        assertInternshipParseFailure(parser, " du/4to5",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommandParser.MESSAGE_DURATION_RANGE_FORMAT));
    }

    @Test
    public void parse_invalidDeadlineArg_throwsParseException() {
        assertInternshipParseFailure(parser, " de/20/23/2023",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommandParser.MESSAGE_DEADLINE_RANGE_FORMAT));
    }

    @Test
    public void parse_invalidRange_throwsParseException() {
        assertInternshipParseFailure(parser, " de/20/23/2023-20/23/2024",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommandParser.MESSAGE_DEADLINE_RANGE_FORMAT));

        assertInternshipParseFailure(parser, " du/4-1",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommandParser.MESSAGE_DURATION_RANGE_FORMAT));

        assertInternshipParseFailure(parser, " s/20/23/2023-20/23/2022",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommandParser.MESSAGE_STARTDATE_RANGE_FORMAT));
    }


    @Test
    public void parse_invalidStartDateArg_throwsParseException() {
        assertInternshipParseFailure(parser, " s/20/23/2027",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommandParser.MESSAGE_STARTDATE_RANGE_FORMAT));
    }

    @Test
    public void parse_invalidDurationRangeFormat_throwsParseException() {
        // Testing without a dash
        assertInternshipParseFailure(parser, " du/56",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommandParser.MESSAGE_DURATION_RANGE_FORMAT));

        // Testing with multiple dashes
        assertInternshipParseFailure(parser, " du/4-5-6",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommandParser.MESSAGE_DURATION_RANGE_FORMAT));

        // Testing with non-numeric characters
        assertInternshipParseFailure(parser, " du/4a-5",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommandParser.MESSAGE_DURATION_RANGE_FORMAT));
    }

    @Test
    public void parse_validCompanyNameArgs_returnsFilterCommand() {
        List<String> keywords = List.of("Optiver");

        FilterCommand expectedFilterCommand =
                new FilterCommand(new CompanyNameContainsKeywordsPredicate(keywords));
        assertInternshipParseSuccess(parser, " c/Optiver", expectedFilterCommand);
    }

    @Test
    public void parse_validApplicationStatusArgs_returnsFilterCommand() {
        List<String> keywords = List.of("Applied");
        FilterCommand expectedFilterCommand = new FilterCommand(
                new ApplicationStatusContainsKeywordsPredicate(keywords));
        assertInternshipParseSuccess(parser, " a/Applied", expectedFilterCommand);
    }


    @Test
    public void parse_validRoleArgs_returnsFilterCommand() {
        List<String> keywords = List.of("Developer");
        FilterCommand expectedFilterCommand = new FilterCommand(new RoleContainsKeywordsPredicate(keywords));
        assertInternshipParseSuccess(parser, " ro/Developer", expectedFilterCommand);
    }

    @Test
    public void parse_validDurationArgs_returnsFilterCommand() {
        List<Duration> durationList = Arrays.asList(new Duration("6"), new Duration("12"));

        FilterCommand expectedFilterCommand =
                new FilterCommand(new DurationWithinRangePredicate(durationList));
        assertInternshipParseSuccess(parser, " du/6-12", expectedFilterCommand);
    }

    @Test
    public void parse_validStartDateArgs_returnsFilterCommand() {
        List<StartDate> startDateList = Arrays.asList(new StartDate("20/03/2023"), new StartDate("20/06/2023"));

        FilterCommand expectedFilterCommand =
                new FilterCommand(new StartDateWithinRangePredicate(startDateList));
        assertInternshipParseSuccess(parser, " s/20/03/2023-20/06/2023", expectedFilterCommand);
    }

    @Test
    public void parse_validRequirementsArgs_returnsFilterCommand() {
        List<String> keywords = List.of("C++");

        FilterCommand expectedFilterCommand =
                new FilterCommand(new RequirementContainsKeywordsPredicate(keywords));
        assertInternshipParseSuccess(parser, " re/C++", expectedFilterCommand);

        List<String> multipleKeywords = List.of("Java", "Python");
        FilterCommand expectedFilterCommandMultipleKeywords =
                new FilterCommand(new RequirementContainsKeywordsPredicate(multipleKeywords));
        assertInternshipParseSuccess(parser, " re/Java re/Python",
                expectedFilterCommandMultipleKeywords);
    }

    @Test
    public void parse_emptyRequirementArgs_throwsParseException() {
        assertInternshipParseFailure(parser, " re/",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommandParser.MESSAGE_NON_EMPTY));
    }

    @Test
    public void parse_emptyDeadlineArg_throwsParseException() {
        assertInternshipParseFailure(parser, " de/",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommandParser.MESSAGE_NON_EMPTY));
    }

    @Test
    public void parse_invalidDeadlineRangeFormat_throwsParseException() {
        // Testing without a dash
        assertInternshipParseFailure(parser, " de/20/03/2023",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommandParser.MESSAGE_DEADLINE_RANGE_FORMAT));

        // Testing with multiple dashes
        assertInternshipParseFailure(parser, " de/20/03/2023-20/06/2023-20/09/2023",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommandParser.MESSAGE_DEADLINE_RANGE_FORMAT));
    }

    @Test
    public void parse_validDeadlineArgs_returnsFilterCommand() {
        List<Deadline> deadlines = Arrays.asList(new Deadline("01/12/2023", "02/12/2023"),
                new Deadline("15/12/2023", "16/12/2023"));

        FilterCommand expectedFilterCommand = new FilterCommand(
                new DeadlineWithinRangePredicate(deadlines));
        assertInternshipParseSuccess(parser, " de/01/12/2023-15/12/2023", expectedFilterCommand);
    }
}
