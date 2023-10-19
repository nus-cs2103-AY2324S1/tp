package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.END_TIME_DESC_ONE;
import static seedu.address.logic.commands.CommandTestUtil.END_TIME_DESC_TWO;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_END_TIME;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_START_TIME;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TUTOR_INDEX;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.START_TIME_DESC_ONE;
import static seedu.address.logic.commands.CommandTestUtil.START_TIME_DESC_TWO;
import static seedu.address.logic.commands.CommandTestUtil.TUTOR_INDEX_DESC_TWO;
import static seedu.address.logic.commands.CommandTestUtil.VALID_END_TIME_ONE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_START_TIME_ONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_TIME;
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

        String userInput = INDEX_FIRST_PERSON.getOneBased() + START_TIME_DESC_ONE + END_TIME_DESC_ONE;
        System.out.println(userInput);

        assertParseSuccess(parser,
                INDEX_FIRST_PERSON.getOneBased() + START_TIME_DESC_ONE + END_TIME_DESC_ONE,
                new AddScheduleCommand(INDEX_FIRST_PERSON, startTime, endTime));
    }

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, START_TIME_DESC_ONE + END_TIME_DESC_ONE, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", MESSAGE_INVALID_FORMAT);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_repeatedValue_failure() {
        String validExpectedScheduleString = START_TIME_DESC_ONE + END_TIME_DESC_ONE;

        // multiple start times
        assertParseFailure(parser,
                INDEX_FIRST_PERSON.getOneBased() + START_TIME_DESC_TWO + validExpectedScheduleString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_START_TIME));

        // multiple end times
        assertParseFailure(parser, INDEX_FIRST_PERSON.getOneBased() + END_TIME_DESC_TWO + validExpectedScheduleString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_END_TIME));

        // invalid value followed by valid value

        // invalid start time
        assertParseFailure(parser, INDEX_FIRST_PERSON.getOneBased() + INVALID_START_TIME + validExpectedScheduleString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_START_TIME));

        // invalid end time
        assertParseFailure(parser, INDEX_FIRST_PERSON.getOneBased() + INVALID_END_TIME + validExpectedScheduleString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_END_TIME));

        // valid value followed by invalid value

        // invalid start time
        assertParseFailure(parser, INDEX_FIRST_PERSON.getOneBased() + validExpectedScheduleString + INVALID_START_TIME,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_START_TIME));

        // invalid end time
        assertParseFailure(parser, INDEX_FIRST_PERSON.getOneBased() + validExpectedScheduleString + INVALID_END_TIME,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_END_TIME));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddScheduleCommand.MESSAGE_USAGE);

        // missing start time prefix
        assertParseFailure(parser, INDEX_FIRST_PERSON.getOneBased() + VALID_START_TIME_ONE + END_TIME_DESC_ONE,
                expectedMessage);

        // missing end time prefix
        assertParseFailure(parser, INDEX_FIRST_PERSON.getOneBased() + START_TIME_DESC_ONE + VALID_END_TIME_ONE,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, INDEX_FIRST_PERSON.getOneBased() + VALID_START_TIME_ONE + VALID_END_TIME_ONE,
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
