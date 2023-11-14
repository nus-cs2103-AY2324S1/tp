package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.APPLICATION_STATUS_DESC_JANESTREET;
import static seedu.address.logic.commands.CommandTestUtil.COMPANY_NAME_DESC_JANESTREET;
import static seedu.address.logic.commands.CommandTestUtil.COMPANY_NAME_DESC_OPTIVER;
import static seedu.address.logic.commands.CommandTestUtil.DEADLINE_DESC_JANESTREET;
import static seedu.address.logic.commands.CommandTestUtil.DURATION_DESC_JANESTREET;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_APPLICATION_STATUS_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_COMPANY_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DEADLINE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DURATION_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_REQUIREMENT_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ROLE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_START_DATE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.REQUIREMENT_DESC_JANESTREET;
import static seedu.address.logic.commands.CommandTestUtil.REQUIREMENT_DESC_OPTIVER;
import static seedu.address.logic.commands.CommandTestUtil.ROLE_DESC_JANESTREET;
import static seedu.address.logic.commands.CommandTestUtil.ROLE_DESC_OPTIVER;
import static seedu.address.logic.commands.CommandTestUtil.START_DATE_DESC_JANESTREET;
import static seedu.address.logic.commands.CommandTestUtil.VALID_APPLICATIONSTATUS_JANESTREET;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COMPANY_NAME_JANESTREET;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DEADLINE_JANESTREET;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DURATION_JANESTREET;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REQUIREMENT_JANESTREET;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REQUIREMENT_OPTIVER;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ROLE_JANESTREET;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STARTDATE_JANESTREET;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPANY_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REQUIREMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.address.logic.parser.CommandInternshipParserUtilTest.assertInternshipParseFailure;
import static seedu.address.logic.parser.CommandInternshipParserUtilTest.assertInternshipParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_INTERNSHIP;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_INTERNSHIP;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_INTERNSHIP;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.ModifyCommand;
import seedu.address.logic.commands.ModifyCommand.EditInternshipDescriptor;
import seedu.address.model.internship.ApplicationStatus;
import seedu.address.model.internship.CompanyName;
import seedu.address.model.internship.Deadline;
import seedu.address.model.internship.Duration;
import seedu.address.model.internship.Role;
import seedu.address.model.internship.StartDate;
import seedu.address.model.requirement.Requirement;
import seedu.address.testutil.EditInternshipDescriptorBuilder;

public class ModifyCommandParserTest {

    private static final String REQUIREMENT_EMPTY = " " + PREFIX_REQUIREMENT;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, ModifyCommand.MESSAGE_USAGE);

    private ModifyCommandParser parser = new ModifyCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertInternshipParseFailure(parser, COMPANY_NAME_DESC_JANESTREET, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertInternshipParseFailure(parser, "1", ModifyCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertInternshipParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertInternshipParseFailure(parser, "-5" + COMPANY_NAME_DESC_JANESTREET, MESSAGE_INVALID_FORMAT);

        // zero index
        assertInternshipParseFailure(parser, "0" + COMPANY_NAME_DESC_JANESTREET, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertInternshipParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertInternshipParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }


    @Test
    public void parse_invalidValue_failure() {
        assertInternshipParseFailure(
                parser,
                "1" + INVALID_COMPANY_NAME_DESC,
                CompanyName.MESSAGE_CONSTRAINTS); // invalid name
        assertInternshipParseFailure(
                parser,
                "1" + INVALID_ROLE_DESC,
                Role.MESSAGE_CONSTRAINTS); // invalid role
        assertInternshipParseFailure(
                parser,
                "1" + INVALID_APPLICATION_STATUS_DESC,
                ApplicationStatus.MESSAGE_CONSTRAINTS); // invalid application status
        assertInternshipParseFailure(
                parser,
                "1" + INVALID_DEADLINE_DESC,
                Deadline.MESSAGE_CONSTRAINTS); // invalid deadline
        assertInternshipParseFailure(
                parser,
                "1" + INVALID_START_DATE_DESC,
                StartDate.MESSAGE_CONSTRAINTS); // invalid start date
        assertInternshipParseFailure(
                parser,
                "1" + INVALID_DURATION_DESC,
                Duration.MESSAGE_CONSTRAINTS); // invalid duration
        assertInternshipParseFailure(
                parser,
                "1" + INVALID_REQUIREMENT_DESC,
                Requirement.MESSAGE_CONSTRAINTS); // invalid requirement
        assertInternshipParseFailure(
                parser,
                "1" + INVALID_COMPANY_NAME_DESC + ROLE_DESC_JANESTREET,
                CompanyName.MESSAGE_CONSTRAINTS);

        // while parsing {@code PREFIX_TAG} alone will reset the tags of the {@code Person} being edited,
        // parsing it together with a valid tag results in error

        assertInternshipParseFailure(parser, "1" + REQUIREMENT_DESC_JANESTREET
                + REQUIREMENT_DESC_OPTIVER + REQUIREMENT_EMPTY, Requirement.MESSAGE_CONSTRAINTS);
        assertInternshipParseFailure(parser, "1" + REQUIREMENT_DESC_JANESTREET
                + REQUIREMENT_EMPTY + REQUIREMENT_DESC_OPTIVER, Requirement.MESSAGE_CONSTRAINTS);
        assertInternshipParseFailure(parser, "1" + REQUIREMENT_EMPTY
                + REQUIREMENT_DESC_JANESTREET + REQUIREMENT_DESC_OPTIVER, Requirement.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertInternshipParseFailure(parser, "1" + INVALID_COMPANY_NAME_DESC + INVALID_ROLE_DESC,
                CompanyName.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_INTERNSHIP;
        String userInput = targetIndex.getOneBased() + ROLE_DESC_JANESTREET
                + REQUIREMENT_DESC_JANESTREET + START_DATE_DESC_JANESTREET
                + APPLICATION_STATUS_DESC_JANESTREET + DEADLINE_DESC_JANESTREET
                + COMPANY_NAME_DESC_JANESTREET + REQUIREMENT_DESC_OPTIVER
                + DURATION_DESC_JANESTREET;

        EditInternshipDescriptor descriptor = new EditInternshipDescriptorBuilder()
                .withCompanyName(VALID_COMPANY_NAME_JANESTREET).withRole(VALID_ROLE_JANESTREET)
                .withApplicationStatus(VALID_APPLICATIONSTATUS_JANESTREET)
                .withDeadline(VALID_DEADLINE_JANESTREET, VALID_STARTDATE_JANESTREET)
                .withStartDate(VALID_STARTDATE_JANESTREET).withDuration(VALID_DURATION_JANESTREET)
                .withRequirements(VALID_REQUIREMENT_JANESTREET, VALID_REQUIREMENT_OPTIVER).build();
        ModifyCommand expectedCommand = new ModifyCommand(targetIndex, descriptor);

        assertInternshipParseSuccess(parser, userInput, expectedCommand);
    }



    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_INTERNSHIP;
        String userInput = targetIndex.getOneBased() + COMPANY_NAME_DESC_JANESTREET + ROLE_DESC_JANESTREET;

        EditInternshipDescriptor descriptor = new EditInternshipDescriptorBuilder()
                .withCompanyName(VALID_COMPANY_NAME_JANESTREET).withRole(VALID_ROLE_JANESTREET).build();
        ModifyCommand expectedCommand = new ModifyCommand(targetIndex, descriptor);
        assertInternshipParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // company name
        Index targetIndex = INDEX_THIRD_INTERNSHIP;
        String userInput = targetIndex.getOneBased() + COMPANY_NAME_DESC_JANESTREET;
        EditInternshipDescriptor descriptor = new EditInternshipDescriptorBuilder()
                .withCompanyName(VALID_COMPANY_NAME_JANESTREET).build();
        ModifyCommand expectedCommand = new ModifyCommand(targetIndex, descriptor);
        assertInternshipParseSuccess(parser, userInput, expectedCommand);

        // role
        userInput = targetIndex.getOneBased() + ROLE_DESC_JANESTREET;
        descriptor = new EditInternshipDescriptorBuilder().withRole(VALID_ROLE_JANESTREET).build();
        expectedCommand = new ModifyCommand(targetIndex, descriptor);
        assertInternshipParseSuccess(parser, userInput, expectedCommand);

        // application status
        userInput = targetIndex.getOneBased() + APPLICATION_STATUS_DESC_JANESTREET;
        descriptor = new EditInternshipDescriptorBuilder()
                .withApplicationStatus(VALID_APPLICATIONSTATUS_JANESTREET).build();
        expectedCommand = new ModifyCommand(targetIndex, descriptor);
        assertInternshipParseSuccess(parser, userInput, expectedCommand);

        // deadline
        userInput = targetIndex.getOneBased() + DEADLINE_DESC_JANESTREET;
        descriptor = new EditInternshipDescriptorBuilder()
                .withDeadline(VALID_DEADLINE_JANESTREET, VALID_STARTDATE_JANESTREET).build();
        expectedCommand = new ModifyCommand(targetIndex, descriptor);
        assertInternshipParseSuccess(parser, userInput, expectedCommand);

        // start date
        userInput = targetIndex.getOneBased() + START_DATE_DESC_JANESTREET;
        descriptor = new EditInternshipDescriptorBuilder().withStartDate(VALID_STARTDATE_JANESTREET).build();
        expectedCommand = new ModifyCommand(targetIndex, descriptor);
        assertInternshipParseSuccess(parser, userInput, expectedCommand);

        // duration
        userInput = targetIndex.getOneBased() + DURATION_DESC_JANESTREET;
        descriptor = new EditInternshipDescriptorBuilder().withDuration(VALID_DURATION_JANESTREET).build();
        expectedCommand = new ModifyCommand(targetIndex, descriptor);
        assertInternshipParseSuccess(parser, userInput, expectedCommand);

        // requirements
        userInput = targetIndex.getOneBased() + REQUIREMENT_DESC_JANESTREET;
        descriptor = new EditInternshipDescriptorBuilder().withRequirements(VALID_REQUIREMENT_JANESTREET).build();
        expectedCommand = new ModifyCommand(targetIndex, descriptor);
        assertInternshipParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_failure() {
        // More extensive testing of duplicate parameter detections is done in
        // CreateCommandParserTest#parse_repeatedNonTagValue_failure()

        // valid followed by invalid
        Index targetIndex = INDEX_FIRST_INTERNSHIP;
        String userInput = targetIndex.getOneBased() + COMPANY_NAME_DESC_JANESTREET + INVALID_COMPANY_NAME_DESC;

        assertInternshipParseFailure(parser, userInput, Messages
              .getErrorMessageForDuplicatePrefixes(PREFIX_COMPANY_NAME));

        // invalid followed by valid
        userInput = targetIndex.getOneBased() + INVALID_COMPANY_NAME_DESC + COMPANY_NAME_DESC_JANESTREET;

        assertInternshipParseFailure(
              parser,
              userInput,
              Messages.getErrorMessageForDuplicatePrefixes(PREFIX_COMPANY_NAME));

        // multiple valid fields repeated
        userInput = targetIndex.getOneBased() + COMPANY_NAME_DESC_JANESTREET + COMPANY_NAME_DESC_OPTIVER
                + ROLE_DESC_JANESTREET + ROLE_DESC_OPTIVER;

        assertInternshipParseFailure(parser, userInput,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_COMPANY_NAME, PREFIX_ROLE));

        // multiple invalid values
        userInput = targetIndex.getOneBased() + INVALID_ROLE_DESC + INVALID_COMPANY_NAME_DESC
                + INVALID_ROLE_DESC + INVALID_COMPANY_NAME_DESC;

        assertInternshipParseFailure(parser, userInput,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_ROLE, PREFIX_COMPANY_NAME));
    }

    @Test
    public void parse_resetRequirements_failure() {
        Index targetIndex = INDEX_THIRD_INTERNSHIP;
        String userInput = targetIndex.getOneBased() + REQUIREMENT_EMPTY;

        EditInternshipDescriptor descriptor = new EditInternshipDescriptorBuilder()
                .withRequirements().build();
        ModifyCommand expectedCommand = new ModifyCommand(targetIndex, descriptor);
        assertInternshipParseFailure(parser, userInput, Requirement.MESSAGE_CONSTRAINTS);
    }
}
