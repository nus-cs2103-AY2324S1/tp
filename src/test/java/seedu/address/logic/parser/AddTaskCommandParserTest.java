package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.AddTaskCommand.MESSAGE_INVALID_DESCRIPTION;
import static seedu.address.logic.commands.AddTaskCommand.MESSAGE_USAGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_END_DATE_TIME;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.model.task.Deadline.MESSAGE_CONSTRAINTS;
import static seedu.address.testutil.TaskBuilder.DEFAULT_DEADLINE;
import static seedu.address.testutil.TaskBuilder.DEFAULT_DESCRIPTION;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.AddTaskCommand;
import seedu.address.model.task.Task;
import seedu.address.testutil.TaskBuilder;

class AddTaskCommandParserTest {
    private AddTaskCommandParser parser = new AddTaskCommandParser();

    @Test
    void parse_allFieldsPresent_success() {
        Task expectedTask = new TaskBuilder().build();
        String validUserInput = " " + PREFIX_EVENT_DESCRIPTION + DEFAULT_DESCRIPTION
                + " " + PREFIX_EVENT_END_DATE_TIME + DEFAULT_DEADLINE;

        assertParseSuccess(parser, validUserInput, new AddTaskCommand(expectedTask));
    }

    @Test
    void parse_descriptionPresent_success() {
        Task expectedTask = new TaskBuilder().withNoDeadline().build();
        String validUserInput = " " + PREFIX_EVENT_DESCRIPTION + DEFAULT_DESCRIPTION;

        assertParseSuccess(parser, validUserInput, new AddTaskCommand(expectedTask));
    }

    @Test
    public void parse_repeatedPrefix_failure() {
        String userInputRepeatDescriptionPrefix = " " + PREFIX_EVENT_DESCRIPTION + DEFAULT_DESCRIPTION
                + " " + PREFIX_EVENT_DESCRIPTION + DEFAULT_DESCRIPTION;

        assertParseFailure(parser, userInputRepeatDescriptionPrefix,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_EVENT_DESCRIPTION));

        String userInputRepeatEndPrefix = " " + PREFIX_EVENT_DESCRIPTION + DEFAULT_DESCRIPTION
                + " " + PREFIX_EVENT_END_DATE_TIME + DEFAULT_DEADLINE
                + " " + PREFIX_EVENT_END_DATE_TIME + DEFAULT_DEADLINE;

        assertParseFailure(parser, userInputRepeatEndPrefix,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_EVENT_END_DATE_TIME));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTaskCommand.MESSAGE_USAGE);

        String missingDescription = " ";

        assertParseFailure(parser, missingDescription, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        String userInputInvalidDescription = " " + PREFIX_EVENT_DESCRIPTION;

        assertParseFailure(parser, userInputInvalidDescription,
                String.format(MESSAGE_INVALID_DESCRIPTION, MESSAGE_USAGE));

        String userInputInvalidDeadline = " " + PREFIX_EVENT_DESCRIPTION + DEFAULT_DESCRIPTION
                + " " + PREFIX_EVENT_END_DATE_TIME;

        assertParseFailure(parser, userInputInvalidDeadline,
                MESSAGE_CONSTRAINTS);
    }
}
