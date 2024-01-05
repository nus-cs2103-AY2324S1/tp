package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.DATE_TASK;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TASK_DESCRIPTION;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TASK_NAME;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.TASK_DESCRIPTION_TASK1;
import static seedu.address.logic.commands.CommandTestUtil.TASK_DESCRIPTION_TASK2;
import static seedu.address.logic.commands.CommandTestUtil.TASK_NAME_TASK1;
import static seedu.address.logic.commands.CommandTestUtil.TASK_NAME_TASK2;
import static seedu.address.logic.commands.CommandTestUtil.TASK_PRIORITY_TASK1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_DESCRIPTION;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_NAME;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalTasks.TASK1;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.AddTaskCommand;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskDescription;
import seedu.address.model.task.TaskName;
import seedu.address.testutil.TaskBuilder;

public class AddTaskCommandParserTest {
    private AddTaskCommandParser parser = new AddTaskCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Task expectedTask = new TaskBuilder(TASK1).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE
                + TASK_NAME_TASK1 + TASK_DESCRIPTION_TASK1
                + TASK_PRIORITY_TASK1 + DATE_TASK, new AddTaskCommand(expectedTask));
    }

    @Test
    public void parse_repeatedValue_failure() {
        String validExpectedTaskString = TASK_NAME_TASK1 + TASK_DESCRIPTION_TASK1;

        // multiple names
        assertParseFailure(parser, TASK_NAME_TASK2 + validExpectedTaskString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_TASK_NAME));

        // multiple description
        assertParseFailure(parser, TASK_DESCRIPTION_TASK2 + validExpectedTaskString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_TASK_DESCRIPTION));

        // invalid value followed by valid value

        // invalid name
        assertParseFailure(parser, INVALID_TASK_NAME + validExpectedTaskString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_TASK_NAME));

        // invalid description
        assertParseFailure(parser, INVALID_TASK_DESCRIPTION + validExpectedTaskString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_TASK_DESCRIPTION));


        // valid value followed by invalid value

        // invalid name
        assertParseFailure(parser, validExpectedTaskString + INVALID_TASK_NAME,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_TASK_NAME));

        // invalid description
        assertParseFailure(parser, validExpectedTaskString + INVALID_TASK_DESCRIPTION,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_TASK_DESCRIPTION));

    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTaskCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_TASK_NAME + TASK_DESCRIPTION_TASK1,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_TASK_NAME + VALID_TASK_DESCRIPTION,
                expectedMessage);
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // no description
        Task expectedTask = new TaskBuilder(TASK1).withDescription("")
                .withDate("").build();
        assertParseSuccess(parser, TASK_NAME_TASK1, new AddTaskCommand(expectedTask));
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_TASK_NAME + TASK_DESCRIPTION_TASK2,
                TaskName.MESSAGE_CONSTRAINTS);

        // invalid description
        assertParseFailure(parser, TASK_NAME_TASK1 + INVALID_TASK_DESCRIPTION,
                TaskDescription.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_TASK_NAME + INVALID_TASK_DESCRIPTION,
                TaskName.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY
                        + TASK_NAME_TASK1 + TASK_NAME_TASK1 + TASK_DESCRIPTION_TASK2,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTaskCommand.MESSAGE_USAGE));
    }
}
