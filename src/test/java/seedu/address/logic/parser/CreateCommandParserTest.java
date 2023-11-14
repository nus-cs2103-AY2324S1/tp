package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.APPLICATION_STATUS_DESC_JANESTREET;
import static seedu.address.logic.commands.CommandTestUtil.APPLICATION_STATUS_DESC_OPTIVER;
import static seedu.address.logic.commands.CommandTestUtil.COMPANY_NAME_DESC_JANESTREET;
import static seedu.address.logic.commands.CommandTestUtil.COMPANY_NAME_DESC_OPTIVER;
import static seedu.address.logic.commands.CommandTestUtil.DEADLINE_DESC_JANESTREET;
import static seedu.address.logic.commands.CommandTestUtil.DEADLINE_DESC_OPTIVER;
import static seedu.address.logic.commands.CommandTestUtil.DURATION_DESC_JANESTREET;
import static seedu.address.logic.commands.CommandTestUtil.DURATION_DESC_OPTIVER;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_APPLICATION_STATUS_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_COMPANY_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DEADLINE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DURATION_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_REQUIREMENT_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ROLE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_START_DATE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.REQUIREMENT_DESC_JANESTREET;
import static seedu.address.logic.commands.CommandTestUtil.REQUIREMENT_DESC_OPTIVER;
import static seedu.address.logic.commands.CommandTestUtil.ROLE_DESC_JANESTREET;
import static seedu.address.logic.commands.CommandTestUtil.ROLE_DESC_OPTIVER;
import static seedu.address.logic.commands.CommandTestUtil.START_DATE_DESC_JANESTREET;
import static seedu.address.logic.commands.CommandTestUtil.START_DATE_DESC_OPTIVER;
import static seedu.address.logic.commands.CommandTestUtil.VALID_APPLICATIONSTATUS_JANESTREET;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COMPANY_NAME_JANESTREET;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DEADLINE_JANESTREET;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DURATION_JANESTREET;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REQUIREMENT_JANESTREET;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REQUIREMENT_OPTIVER;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ROLE_JANESTREET;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STARTDATE_JANESTREET;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPLICATION_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPANY_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DURATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_DATE;
import static seedu.address.logic.parser.CommandInternshipParserUtilTest.assertInternshipParseFailure;
import static seedu.address.logic.parser.CommandInternshipParserUtilTest.assertInternshipParseSuccess;
import static seedu.address.testutil.TypicalInternships.JANESTREET;
import static seedu.address.testutil.TypicalInternships.OPTIVER;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.CreateCommand;
import seedu.address.model.internship.ApplicationStatus;
import seedu.address.model.internship.CompanyName;
import seedu.address.model.internship.Deadline;
import seedu.address.model.internship.Duration;
import seedu.address.model.internship.Internship;
import seedu.address.model.internship.Role;
import seedu.address.model.internship.StartDate;
import seedu.address.model.requirement.Requirement;
import seedu.address.testutil.InternshipBuilder;

public class CreateCommandParserTest {
    private CreateCommandParser parser = new CreateCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Internship expectedInternship = new InternshipBuilder(OPTIVER).build();

        // whitespace only preamble
        assertInternshipParseSuccess(parser, PREAMBLE_WHITESPACE + COMPANY_NAME_DESC_OPTIVER + ROLE_DESC_OPTIVER
                + APPLICATION_STATUS_DESC_OPTIVER + DEADLINE_DESC_OPTIVER + START_DATE_DESC_OPTIVER
                + DURATION_DESC_OPTIVER + REQUIREMENT_DESC_OPTIVER,
                new CreateCommand(expectedInternship));

        // multiple tags - all accepted
        Internship expectedInternshipMultipleRequiremments = new InternshipBuilder(OPTIVER)
                .withRequirements(VALID_REQUIREMENT_JANESTREET, VALID_REQUIREMENT_OPTIVER)
                .build();
        assertInternshipParseSuccess(parser,
                COMPANY_NAME_DESC_OPTIVER + ROLE_DESC_OPTIVER
                        + APPLICATION_STATUS_DESC_OPTIVER + DEADLINE_DESC_OPTIVER + START_DATE_DESC_OPTIVER
                        + DURATION_DESC_OPTIVER + REQUIREMENT_DESC_JANESTREET + REQUIREMENT_DESC_OPTIVER,
                new CreateCommand(expectedInternshipMultipleRequiremments));
    }

    @Test
    public void parse_repeatedNonRequirementValue_failure() {
        String validExpectedInternshipString = COMPANY_NAME_DESC_OPTIVER + ROLE_DESC_OPTIVER
                + APPLICATION_STATUS_DESC_OPTIVER + DEADLINE_DESC_OPTIVER + START_DATE_DESC_OPTIVER
                + DURATION_DESC_OPTIVER + REQUIREMENT_DESC_OPTIVER;

        // multiple company names
        assertInternshipParseFailure(parser, COMPANY_NAME_DESC_OPTIVER + validExpectedInternshipString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_COMPANY_NAME));

        // multiple roles
        assertInternshipParseFailure(parser, ROLE_DESC_OPTIVER + validExpectedInternshipString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_ROLE));

        // multiple application statuses
        assertInternshipParseFailure(parser, APPLICATION_STATUS_DESC_OPTIVER + validExpectedInternshipString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_APPLICATION_STATUS));

        // multiple deadlines
        assertInternshipParseFailure(parser, DEADLINE_DESC_OPTIVER + validExpectedInternshipString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_DEADLINE));

        // multiple start dates
        assertInternshipParseFailure(parser, START_DATE_DESC_OPTIVER + validExpectedInternshipString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_START_DATE));

        // multiple durations
        assertInternshipParseFailure(parser, DURATION_DESC_OPTIVER + validExpectedInternshipString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_DURATION));

        // multiple fields repeated
        assertInternshipParseFailure(parser,
                validExpectedInternshipString + COMPANY_NAME_DESC_JANESTREET + ROLE_DESC_JANESTREET
                        + APPLICATION_STATUS_DESC_JANESTREET + DEADLINE_DESC_JANESTREET + START_DATE_DESC_JANESTREET
                        + DURATION_DESC_JANESTREET + validExpectedInternshipString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_COMPANY_NAME, PREFIX_ROLE,
                        PREFIX_APPLICATION_STATUS, PREFIX_DEADLINE, PREFIX_START_DATE, PREFIX_DURATION));

        // invalid application status
        assertInternshipParseFailure(parser, INVALID_APPLICATION_STATUS_DESC + validExpectedInternshipString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_APPLICATION_STATUS));

        // invalid deadline
        assertInternshipParseFailure(parser, INVALID_DEADLINE_DESC + validExpectedInternshipString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_DEADLINE));

        // invalid start date
        assertInternshipParseFailure(parser, INVALID_START_DATE_DESC + validExpectedInternshipString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_START_DATE));

        // invalid duration
        assertInternshipParseFailure(parser, INVALID_DURATION_DESC + validExpectedInternshipString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_DURATION));

        // invalid requirement
        assertInternshipParseFailure(parser, INVALID_REQUIREMENT_DESC + validExpectedInternshipString,
                Requirement.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero requirements
        Internship expectedInternship = new InternshipBuilder(JANESTREET).withRequirements().build();
        assertInternshipParseSuccess(parser, COMPANY_NAME_DESC_JANESTREET + ROLE_DESC_JANESTREET
                        + APPLICATION_STATUS_DESC_JANESTREET + DEADLINE_DESC_JANESTREET + START_DATE_DESC_JANESTREET
                        + DURATION_DESC_JANESTREET,
                new CreateCommand(expectedInternship));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, CreateCommand.MESSAGE_USAGE);

        // missing company name prefix
        assertInternshipParseFailure(parser, VALID_COMPANY_NAME_JANESTREET + ROLE_DESC_JANESTREET
                        + APPLICATION_STATUS_DESC_JANESTREET + DEADLINE_DESC_JANESTREET + START_DATE_DESC_JANESTREET
                        + DURATION_DESC_JANESTREET,
                expectedMessage);

        // missing role prefix
        assertInternshipParseFailure(parser, COMPANY_NAME_DESC_JANESTREET + VALID_ROLE_JANESTREET
                        + APPLICATION_STATUS_DESC_JANESTREET + DEADLINE_DESC_JANESTREET + START_DATE_DESC_JANESTREET
                        + DURATION_DESC_JANESTREET,
                expectedMessage);

        // missing application status prefix
        assertInternshipParseFailure(parser, COMPANY_NAME_DESC_JANESTREET + ROLE_DESC_JANESTREET
                        + VALID_APPLICATIONSTATUS_JANESTREET + DEADLINE_DESC_JANESTREET + START_DATE_DESC_JANESTREET
                        + DURATION_DESC_JANESTREET,
                expectedMessage);

        // missing deadline prefix
        assertInternshipParseFailure(parser, COMPANY_NAME_DESC_JANESTREET + ROLE_DESC_JANESTREET
                        + APPLICATION_STATUS_DESC_JANESTREET + VALID_DEADLINE_JANESTREET + START_DATE_DESC_JANESTREET
                        + DURATION_DESC_JANESTREET,
                expectedMessage);

        // missing start date prefix
        assertInternshipParseFailure(parser, COMPANY_NAME_DESC_JANESTREET + ROLE_DESC_JANESTREET
                        + APPLICATION_STATUS_DESC_JANESTREET + DEADLINE_DESC_JANESTREET + VALID_STARTDATE_JANESTREET
                        + DURATION_DESC_JANESTREET,
                expectedMessage);

        // missing duration prefix
        assertInternshipParseFailure(parser, COMPANY_NAME_DESC_JANESTREET + ROLE_DESC_JANESTREET
                        + APPLICATION_STATUS_DESC_JANESTREET + DEADLINE_DESC_JANESTREET + START_DATE_DESC_JANESTREET
                        + VALID_DURATION_JANESTREET,
                expectedMessage);

        // all prefixes missing
        assertInternshipParseFailure(parser, VALID_COMPANY_NAME_JANESTREET + VALID_ROLE_JANESTREET
                + VALID_APPLICATIONSTATUS_JANESTREET + VALID_DEADLINE_JANESTREET + VALID_STARTDATE_JANESTREET
                + VALID_DURATION_JANESTREET,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid company name
        assertInternshipParseFailure(parser, INVALID_COMPANY_NAME_DESC + ROLE_DESC_OPTIVER
                + APPLICATION_STATUS_DESC_OPTIVER + DEADLINE_DESC_OPTIVER + START_DATE_DESC_OPTIVER
                + DURATION_DESC_OPTIVER + REQUIREMENT_DESC_OPTIVER, CompanyName.MESSAGE_CONSTRAINTS);

        // invalid role
        assertInternshipParseFailure(parser, COMPANY_NAME_DESC_OPTIVER + INVALID_ROLE_DESC
                + APPLICATION_STATUS_DESC_OPTIVER + DEADLINE_DESC_OPTIVER + START_DATE_DESC_OPTIVER
                + DURATION_DESC_OPTIVER + REQUIREMENT_DESC_OPTIVER, Role.MESSAGE_CONSTRAINTS);

        // invalid application status
        assertInternshipParseFailure(parser, COMPANY_NAME_DESC_OPTIVER + ROLE_DESC_OPTIVER
                + INVALID_APPLICATION_STATUS_DESC + DEADLINE_DESC_OPTIVER + START_DATE_DESC_OPTIVER
                + DURATION_DESC_OPTIVER + REQUIREMENT_DESC_OPTIVER, ApplicationStatus.MESSAGE_CONSTRAINTS);

        // invalid deadline
        assertInternshipParseFailure(parser, COMPANY_NAME_DESC_OPTIVER + ROLE_DESC_OPTIVER
                + APPLICATION_STATUS_DESC_OPTIVER + INVALID_DEADLINE_DESC + START_DATE_DESC_OPTIVER
                + DURATION_DESC_OPTIVER + REQUIREMENT_DESC_OPTIVER, Deadline.MESSAGE_CONSTRAINTS);

        // invalid start date
        assertInternshipParseFailure(parser, COMPANY_NAME_DESC_OPTIVER + ROLE_DESC_OPTIVER
                + APPLICATION_STATUS_DESC_OPTIVER + DEADLINE_DESC_OPTIVER + INVALID_START_DATE_DESC
                + DURATION_DESC_OPTIVER + REQUIREMENT_DESC_OPTIVER, StartDate.MESSAGE_CONSTRAINTS);

        // invalid duration
        assertInternshipParseFailure(parser, COMPANY_NAME_DESC_OPTIVER + ROLE_DESC_OPTIVER
                + APPLICATION_STATUS_DESC_OPTIVER + DEADLINE_DESC_OPTIVER + START_DATE_DESC_OPTIVER
                + INVALID_DURATION_DESC + REQUIREMENT_DESC_OPTIVER, Duration.MESSAGE_CONSTRAINTS);

        // invalid requirement
        assertInternshipParseFailure(parser, COMPANY_NAME_DESC_OPTIVER + ROLE_DESC_OPTIVER
                + APPLICATION_STATUS_DESC_OPTIVER + DEADLINE_DESC_OPTIVER + START_DATE_DESC_OPTIVER
                + DURATION_DESC_OPTIVER + INVALID_REQUIREMENT_DESC, Requirement.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertInternshipParseFailure(parser, INVALID_COMPANY_NAME_DESC + INVALID_ROLE_DESC
                        + APPLICATION_STATUS_DESC_OPTIVER + DEADLINE_DESC_OPTIVER + START_DATE_DESC_OPTIVER
                        + DURATION_DESC_OPTIVER + REQUIREMENT_DESC_OPTIVER, CompanyName.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertInternshipParseFailure(parser, PREAMBLE_NON_EMPTY + COMPANY_NAME_DESC_OPTIVER + ROLE_DESC_OPTIVER
                        + APPLICATION_STATUS_DESC_OPTIVER + DEADLINE_DESC_OPTIVER + START_DATE_DESC_OPTIVER
                        + DURATION_DESC_OPTIVER + REQUIREMENT_DESC_OPTIVER,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, CreateCommand.MESSAGE_USAGE));
    }
}
