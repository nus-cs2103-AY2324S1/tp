package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.END_TIME_DESC_ONE;
import static seedu.address.logic.commands.CommandTestUtil.END_TIME_DESC_TWO;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_END_TIME;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_START_TIME;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TUTOR_INDEX;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.START_TIME_DESC_ONE;
import static seedu.address.logic.commands.CommandTestUtil.START_TIME_DESC_TWO;
import static seedu.address.logic.commands.CommandTestUtil.TUTOR_INDEX_DESC_ONE;
import static seedu.address.logic.commands.CommandTestUtil.TUTOR_INDEX_DESC_TWO;
import static seedu.address.logic.commands.CommandTestUtil.VALID_END_TIME_ONE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_START_TIME_ONE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TUTOR_INDEX_ONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTOR_INDEX;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.AddScheduleCommand;
import seedu.address.model.schedule.EndTime;
import seedu.address.model.schedule.StartTime;

public class AddScheduleCommandParserTest {
    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddScheduleCommand.MESSAGE_USAGE);
    private AddScheduleCommandParser parser = new AddScheduleCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        StartTime startTime = new StartTime(LocalDateTime.parse(VALID_START_TIME_ONE));
        EndTime endTime = new EndTime(LocalDateTime.parse(VALID_END_TIME_ONE));

        // whitespace only preamble
        assertParseSuccess(parser,
                PREAMBLE_WHITESPACE + TUTOR_INDEX_DESC_ONE + START_TIME_DESC_ONE
                        + END_TIME_DESC_ONE, new AddScheduleCommand(INDEX_FIRST_PERSON, startTime, endTime));
    }

    @Test
    public void parse_repeatedValue_failure() {
        String validExpectedScheduleString = TUTOR_INDEX_DESC_ONE + START_TIME_DESC_ONE
                + END_TIME_DESC_ONE;

        // multiple tutor indices
        assertParseFailure(parser, TUTOR_INDEX_DESC_TWO + validExpectedScheduleString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_TUTOR_INDEX));

        // multiple start times
        assertParseFailure(parser, START_TIME_DESC_TWO + validExpectedScheduleString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_START_TIME));

        // multiple end times
        assertParseFailure(parser, END_TIME_DESC_TWO + validExpectedScheduleString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_END_TIME));

        // invalid value followed by valid value

        // invalid tutor index
        assertParseFailure(parser, INVALID_TUTOR_INDEX + validExpectedScheduleString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_TUTOR_INDEX));

        // invalid start time
        assertParseFailure(parser, INVALID_START_TIME + validExpectedScheduleString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_START_TIME));

        // invalid end time
        assertParseFailure(parser, INVALID_END_TIME + validExpectedScheduleString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_END_TIME));

        // valid value followed by invalid value

        // invalid name
        assertParseFailure(parser, validExpectedScheduleString + INVALID_TUTOR_INDEX,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_TUTOR_INDEX));

        // invalid start time
        assertParseFailure(parser, validExpectedScheduleString + INVALID_START_TIME,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_START_TIME));

        // invalid end time
        assertParseFailure(parser, validExpectedScheduleString + INVALID_END_TIME,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_END_TIME));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddScheduleCommand.MESSAGE_USAGE);

        // missing tutor prefix
        assertParseFailure(parser, VALID_TUTOR_INDEX_ONE + START_TIME_DESC_ONE + END_TIME_DESC_ONE,
                expectedMessage);

        // missing start time prefix
        assertParseFailure(parser, TUTOR_INDEX_DESC_ONE + VALID_START_TIME_ONE + END_TIME_DESC_ONE,
                expectedMessage);

        // missing end time prefix
        assertParseFailure(parser, TUTOR_INDEX_DESC_ONE + START_TIME_DESC_ONE + VALID_END_TIME_ONE,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_TUTOR_INDEX_ONE + VALID_START_TIME_ONE + VALID_END_TIME_ONE,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid tutor index
        assertParseFailure(parser, INVALID_TUTOR_INDEX + START_TIME_DESC_ONE + END_TIME_DESC_ONE,
                MESSAGE_INVALID_FORMAT);

        // invalid start time
        assertParseFailure(parser, TUTOR_INDEX_DESC_TWO + INVALID_START_TIME + END_TIME_DESC_ONE,
                StartTime.MESSAGE_CONSTRAINTS);

        // invalid end time
        assertParseFailure(parser, TUTOR_INDEX_DESC_TWO + START_TIME_DESC_ONE + INVALID_END_TIME,
                EndTime.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_TUTOR_INDEX + START_TIME_DESC_ONE + INVALID_END_TIME,
                MESSAGE_INVALID_FORMAT);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + TUTOR_INDEX_DESC_TWO + START_TIME_DESC_ONE
                + END_TIME_DESC_ONE, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AddScheduleCommand.MESSAGE_USAGE));
    }
}
