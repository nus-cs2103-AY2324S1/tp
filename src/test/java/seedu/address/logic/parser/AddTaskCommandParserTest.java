package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NOTE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TITLE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NOTE_DESC_AGENDA;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_FINANCE;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_LOCATION;
import static seedu.address.logic.commands.CommandTestUtil.TITLE_DESC_AGENDA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NOTE_AGENDA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FINANCE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_LOCATION;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TITLE_AGENDA;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_NOTE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_TITLE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalTasks.AGENDA;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.AddTaskCommand;
import seedu.address.model.task.Note;
import seedu.address.model.task.Task;
import seedu.address.model.task.Title;
import seedu.address.testutil.TaskBuilder;

public class AddTaskCommandParserTest {
    private AddTaskCommandParser parser = new AddTaskCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Task expectedTask = new TaskBuilder(AGENDA).withTags(VALID_TAG_LOCATION).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + TITLE_DESC_AGENDA + NOTE_DESC_AGENDA + TAG_DESC_LOCATION,
                new AddTaskCommand(expectedTask));

        // multiple tags - all accepted
        Task expectedTaskMultipleTags = new TaskBuilder(AGENDA)
                .withTags(VALID_TAG_LOCATION, VALID_TAG_FINANCE)
                .build();
        assertParseSuccess(parser,
                TITLE_DESC_AGENDA + NOTE_DESC_AGENDA + TAG_DESC_LOCATION + TAG_DESC_FINANCE,
                new AddTaskCommand(expectedTaskMultipleTags));
    }

    @Test
    public void parse_repeatedValue_failure() {
        String validExpectedTaskString = TITLE_DESC_AGENDA + NOTE_DESC_AGENDA + TAG_DESC_LOCATION;

        // multiple titles
        assertParseFailure(parser, TITLE_DESC_AGENDA + validExpectedTaskString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_TASK_TITLE));

        // multiple notes
        assertParseFailure(parser, NOTE_DESC_AGENDA + validExpectedTaskString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_TASK_NOTE));

        // multiple fields repeated
        assertParseFailure(parser,
                validExpectedTaskString + TITLE_DESC_AGENDA + NOTE_DESC_AGENDA + validExpectedTaskString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_TASK_TITLE, PREFIX_TASK_NOTE));

        // invalid value followed by valid value

        // invalid title
        assertParseFailure(parser, INVALID_TITLE_DESC + validExpectedTaskString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_TASK_TITLE));

        // invalid note
        assertParseFailure(parser, INVALID_NOTE_DESC + validExpectedTaskString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_TASK_NOTE));

        // valid value followed by invalid value

        // invalid title
        assertParseFailure(parser, validExpectedTaskString + INVALID_TITLE_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_TASK_TITLE));

        // invalid note
        assertParseFailure(parser, validExpectedTaskString + INVALID_NOTE_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_TASK_NOTE));

    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Task expectedTask = new TaskBuilder(AGENDA).withTags().build();
        assertParseSuccess(parser, TITLE_DESC_AGENDA + NOTE_DESC_AGENDA,
                new AddTaskCommand(expectedTask));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTaskCommand.MESSAGE_USAGE);

        // missing title prefix
        assertParseFailure(parser, VALID_TITLE_AGENDA + NOTE_DESC_AGENDA,
                expectedMessage);

        // missing note prefix
        assertParseFailure(parser, TITLE_DESC_AGENDA + VALID_NOTE_AGENDA,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid title
        assertParseFailure(parser, INVALID_TITLE_DESC + NOTE_DESC_AGENDA,
                Title.MESSAGE_CONSTRAINTS);

        // invalid note
        assertParseFailure(parser, TITLE_DESC_AGENDA + INVALID_NOTE_DESC,
                Note.MESSAGE_CONSTRAINTS);
    }
}
