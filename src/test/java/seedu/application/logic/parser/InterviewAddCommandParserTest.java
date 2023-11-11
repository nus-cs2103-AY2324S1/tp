package seedu.application.logic.parser;

import static seedu.application.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.application.logic.commands.CommandTestUtil.*;
import static seedu.application.logic.parser.CliSyntax.*;
import static seedu.application.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.application.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.application.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.application.testutil.TypicalInterviews.CLEANER_INTERVIEW;

import org.junit.jupiter.api.Test;

import seedu.application.logic.Messages;
import seedu.application.logic.commands.InterviewAddCommand;
import seedu.application.model.job.interview.Interview;
import seedu.application.model.job.interview.InterviewAddress;
import seedu.application.model.job.interview.InterviewDateTime;
import seedu.application.model.job.interview.InterviewType;
import seedu.application.testutil.InterviewBuilder;

public class InterviewAddCommandParserTest {
    private InterviewAddCommandParser parser = new InterviewAddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Interview expectedInterview = new InterviewBuilder(CLEANER_INTERVIEW).build();

        assertParseSuccess(parser, PREAMBLE_WHITESPACE + "1" + INTERVIEW_TYPE_DESC_CLEANER
            + INTERVIEW_DATETIME_DESC_CLEANER + INTERVIEW_ADDRESS_DESC_CLEANER,
            new InterviewAddCommand(INDEX_FIRST, expectedInterview));
    }

    @Test
    public void parse_repeatedNonTagValue_failure() {
        String validExpectedInterviewString = INTERVIEW_TYPE_DESC_CLEANER
            + INTERVIEW_DATETIME_DESC_CLEANER + INTERVIEW_ADDRESS_DESC_CLEANER;

        // multiple types
        assertParseFailure(parser, "1" + INTERVIEW_TYPE_DESC_CHEF + validExpectedInterviewString,
            Messages.getErrorMessageForDuplicatePrefixes(PREFIX_INTERVIEW_TYPE));

        // multiple datetimes
        assertParseFailure(parser, "1" + INTERVIEW_DATETIME_DESC_CHEF + validExpectedInterviewString,
            Messages.getErrorMessageForDuplicatePrefixes(PREFIX_INTERVIEW_DATETIME));

        // multiple addresses
        assertParseFailure(parser, "1" + INTERVIEW_ADDRESS_DESC_CHEF + validExpectedInterviewString,
            Messages.getErrorMessageForDuplicatePrefixes(PREFIX_INTERVIEW_ADDRESS));

        // multiple fields repeated
        assertParseFailure(parser,
            "1" + validExpectedInterviewString + INTERVIEW_TYPE_DESC_CHEF + INTERVIEW_DATETIME_DESC_CHEF
                + INTERVIEW_ADDRESS_DESC_CHEF + validExpectedInterviewString,
            Messages.getErrorMessageForDuplicatePrefixes(PREFIX_INTERVIEW_TYPE,
                PREFIX_INTERVIEW_DATETIME, PREFIX_INTERVIEW_ADDRESS));

        // invalid value followed by valid value

        // invalid type
        assertParseFailure(parser, "1" + INVALID_INTERVIEW_TYPE_DESC + validExpectedInterviewString,
            Messages.getErrorMessageForDuplicatePrefixes(PREFIX_INTERVIEW_TYPE));

        // invalid datetime
        assertParseFailure(parser, "1" + INVALID_INTERVIEW_DATETIME_DESC + validExpectedInterviewString,
            Messages.getErrorMessageForDuplicatePrefixes(PREFIX_INTERVIEW_DATETIME));

        // invalid address
        assertParseFailure(parser, "1" + INVALID_INTERVIEW_ADDRESS_DESC + validExpectedInterviewString,
            Messages.getErrorMessageForDuplicatePrefixes(PREFIX_INTERVIEW_ADDRESS));

        // valid value followed by invalid value

        // invalid type
        assertParseFailure(parser, "1" + validExpectedInterviewString + INVALID_INTERVIEW_TYPE_DESC,
            Messages.getErrorMessageForDuplicatePrefixes(PREFIX_INTERVIEW_TYPE));

        // invalid datetime
        assertParseFailure(parser, "1" + validExpectedInterviewString + INVALID_INTERVIEW_DATETIME_DESC,
            Messages.getErrorMessageForDuplicatePrefixes(PREFIX_INTERVIEW_DATETIME));

        // invalid address
        assertParseFailure(parser, "1" + validExpectedInterviewString + INVALID_INTERVIEW_ADDRESS_DESC,
            Messages.getErrorMessageForDuplicatePrefixes(PREFIX_INTERVIEW_ADDRESS));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, InterviewAddCommand.MESSAGE_USAGE);

        // missing interview type prefix
        assertParseFailure(parser, "1" + VALID_INTERVIEW_TYPE_CLEANER + INTERVIEW_DATETIME_DESC_CLEANER
            + INTERVIEW_ADDRESS_DESC_CLEANER, expectedMessage);

        // missing interview datetime prefix
        assertParseFailure(parser, "1" + INTERVIEW_TYPE_DESC_CLEANER + VALID_INTERVIEW_DATETIME_CLEANER
            + INTERVIEW_ADDRESS_DESC_CLEANER, expectedMessage);

        // missing interview address prefix
        assertParseFailure(parser, "1" + INTERVIEW_TYPE_DESC_CLEANER + INTERVIEW_DATETIME_DESC_CLEANER
            + VALID_INTERVIEW_ADDRESS_CLEANER, expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, "1" + VALID_INTERVIEW_TYPE_CLEANER + VALID_INTERVIEW_DATETIME_CLEANER
            + VALID_INTERVIEW_ADDRESS_CLEANER, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid type
        assertParseFailure(parser, "1" + INVALID_INTERVIEW_TYPE_DESC + INTERVIEW_DATETIME_DESC_CLEANER
            + INTERVIEW_ADDRESS_DESC_CLEANER, InterviewType.MESSAGE_CONSTRAINTS);

        // invalid datetime
        assertParseFailure(parser, "1" + INTERVIEW_TYPE_DESC_CLEANER + INVALID_INTERVIEW_DATETIME_DESC
            + INTERVIEW_ADDRESS_DESC_CLEANER, InterviewDateTime.MESSAGE_CONSTRAINTS);

        // invalid address
        assertParseFailure(parser, "1" + INTERVIEW_TYPE_DESC_CLEANER + INTERVIEW_DATETIME_DESC_CLEANER
            + INVALID_INTERVIEW_ADDRESS_DESC, InterviewAddress.MESSAGE_CONSTRAINTS);

        // three invalid values, only first invalid value reported
        assertParseFailure(parser, "1" + INVALID_INTERVIEW_TYPE_DESC + INVALID_INTERVIEW_DATETIME_DESC
            + INVALID_INTERVIEW_ADDRESS_DESC, InterviewType.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + " 1" + INTERVIEW_TYPE_DESC_CLEANER
            + INTERVIEW_DATETIME_DESC_CLEANER + INTERVIEW_ADDRESS_DESC_CLEANER,
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, InterviewAddCommand.MESSAGE_USAGE));
    }
}
