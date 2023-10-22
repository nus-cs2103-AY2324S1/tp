package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.END_TIME_DESC_ONE;
import static seedu.address.logic.commands.CommandTestUtil.END_TIME_DESC_TWO;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_END_TIME;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_START_TIME;
import static seedu.address.logic.commands.CommandTestUtil.START_TIME_DESC_ONE;
import static seedu.address.logic.commands.CommandTestUtil.START_TIME_DESC_TWO;
import static seedu.address.logic.commands.CommandTestUtil.VALID_END_TIME_ONE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_START_TIME_ONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_TIME;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_SCHEDULE;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_SCHEDULE;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_SCHEDULE;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.EditScheduleCommand;
import seedu.address.logic.commands.EditScheduleCommand.EditScheduleDescriptor;
import seedu.address.model.schedule.EndTime;
import seedu.address.model.schedule.StartTime;
import seedu.address.testutil.EditScheduleDescriptorBuilder;

public class EditScheduleCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
        String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditScheduleCommand.MESSAGE_USAGE);

    private EditScheduleCommandParser parser = new EditScheduleCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_START_TIME_ONE, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditScheduleCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + VALID_START_TIME_ONE, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + VALID_START_TIME_ONE, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_START_TIME, StartTime.MESSAGE_CONSTRAINTS); // invalid start time
        assertParseFailure(parser, "1" + INVALID_END_TIME, EndTime.MESSAGE_CONSTRAINTS); // invalid end time

        // invalid start time followed by valid end time
        assertParseFailure(parser, "1" + INVALID_START_TIME + END_TIME_DESC_ONE, StartTime.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_START_TIME + INVALID_END_TIME, StartTime.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_SCHEDULE;
        String userInput = targetIndex.getOneBased() + START_TIME_DESC_ONE + END_TIME_DESC_ONE;

        EditScheduleDescriptor descriptor = new EditScheduleDescriptorBuilder().withStartTime(VALID_START_TIME_ONE)
            .withEndTime(VALID_END_TIME_ONE).build();
        EditScheduleCommand expectedCommand = new EditScheduleCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_SCHEDULE;
        String userInput = targetIndex.getOneBased() + START_TIME_DESC_ONE + END_TIME_DESC_ONE;

        EditScheduleDescriptor descriptor = new EditScheduleDescriptorBuilder().withStartTime(VALID_START_TIME_ONE)
            .withEndTime(VALID_END_TIME_ONE).build();
        EditScheduleCommand expectedCommand = new EditScheduleCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // start time
        Index targetIndex = INDEX_THIRD_SCHEDULE;
        String userInput = targetIndex.getOneBased() + START_TIME_DESC_ONE;
        EditScheduleDescriptor descriptor = new EditScheduleDescriptorBuilder()
            .withStartTime(VALID_START_TIME_ONE).build();
        EditScheduleCommand expectedCommand = new EditScheduleCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // end time
        userInput = targetIndex.getOneBased() + END_TIME_DESC_ONE;
        descriptor = new EditScheduleDescriptorBuilder().withEndTime(VALID_END_TIME_ONE).build();
        expectedCommand = new EditScheduleCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_failure() {
        // More extensive testing of duplicate parameter detections is done in
        // AddCommandParserTest#parse_repeatedNonTagValue_failure()

        // valid followed by invalid
        Index targetIndex = INDEX_FIRST_SCHEDULE;
        String userInput = targetIndex.getOneBased() + INVALID_START_TIME + START_TIME_DESC_TWO;

        assertParseFailure(parser, userInput, Messages.getErrorMessageForDuplicatePrefixes(PREFIX_START_TIME));

        // invalid followed by valid
        userInput = targetIndex.getOneBased() + START_TIME_DESC_TWO + INVALID_START_TIME;

        assertParseFailure(parser, userInput, Messages.getErrorMessageForDuplicatePrefixes(PREFIX_START_TIME));

        // multiple valid fields repeated
        userInput =
            targetIndex.getOneBased() + START_TIME_DESC_ONE + END_TIME_DESC_ONE + START_TIME_DESC_TWO
                + END_TIME_DESC_ONE + START_TIME_DESC_ONE + END_TIME_DESC_TWO;

        assertParseFailure(parser, userInput,
            Messages.getErrorMessageForDuplicatePrefixes(PREFIX_START_TIME, PREFIX_END_TIME));

        // multiple invalid values
        userInput = targetIndex.getOneBased() + INVALID_START_TIME + INVALID_END_TIME + INVALID_START_TIME
            + INVALID_END_TIME;

        assertParseFailure(parser, userInput,
            Messages.getErrorMessageForDuplicatePrefixes(PREFIX_START_TIME, PREFIX_END_TIME));
    }
}
