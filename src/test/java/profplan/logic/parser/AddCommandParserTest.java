package profplan.logic.parser;

import static profplan.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static profplan.logic.parser.CliSyntax.PREFIX_NAME;
import static profplan.logic.parser.CliSyntax.PREFIX_PRIORITY;

import org.junit.jupiter.api.Test;

import profplan.logic.Messages;
import profplan.logic.commands.AddCommand;
import profplan.logic.commands.CommandTestUtil;
import profplan.model.task.Task;
import profplan.testutil.TaskBuilder;
import profplan.testutil.TypicalTasks;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Task expectedTask = new TaskBuilder(TypicalTasks.BOB).withTags(CommandTestUtil.VALID_TAG_FRIEND)
                .build();

        // whitespace only preamble
        CommandParserTestUtil.assertParseSuccess(parser, CommandTestUtil.PREAMBLE_WHITESPACE
                + CommandTestUtil.NAME_DESC_BOB + CommandTestUtil.PRIORITY_DESC_BOB + CommandTestUtil.DUEDATE_DESC
                + CommandTestUtil.TAG_DESC_FRIEND, new AddCommand(expectedTask));


        // multiple tags - all accepted
        Task expectedTaskMultipleTags = new TaskBuilder(TypicalTasks.BOB)
                .withTags(CommandTestUtil.VALID_TAG_FRIEND, CommandTestUtil.VALID_TAG_HUSBAND)
                .build();
        CommandParserTestUtil.assertParseSuccess(parser,
                CommandTestUtil.NAME_DESC_BOB + CommandTestUtil.PRIORITY_DESC_BOB
                        + CommandTestUtil.DUEDATE_DESC
                        + CommandTestUtil.TAG_DESC_HUSBAND
                        + CommandTestUtil.TAG_DESC_FRIEND,
                new AddCommand(expectedTaskMultipleTags));
    }

    @Test
    public void parse_repeatedNonTagValue_failure() {
        String validExpectedTaskString = CommandTestUtil.NAME_DESC_BOB + CommandTestUtil.PRIORITY_DESC_BOB
                + CommandTestUtil.TAG_DESC_FRIEND;

        // multiple names
        CommandParserTestUtil.assertParseFailure(parser, CommandTestUtil.NAME_DESC_AMY
                        + validExpectedTaskString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));

        // multiple prioritys
        CommandParserTestUtil.assertParseFailure(parser, CommandTestUtil.PRIORITY_DESC_AMY
                        + validExpectedTaskString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PRIORITY));

        // multiple fields repeated
        CommandParserTestUtil.assertParseFailure(parser,
                validExpectedTaskString + CommandTestUtil.PRIORITY_DESC_AMY
                        + CommandTestUtil.NAME_DESC_AMY
                        + validExpectedTaskString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME, PREFIX_PRIORITY));

        // invalid value followed by valid value

        // invalid name
        CommandParserTestUtil.assertParseFailure(parser, CommandTestUtil.INVALID_NAME_DESC
                        + validExpectedTaskString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));

        // invalid priority
        CommandParserTestUtil.assertParseFailure(parser, CommandTestUtil.INVALID_PRIORITY_DESC
                        + validExpectedTaskString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PRIORITY));

        // valid value followed by invalid value

        // invalid name
        CommandParserTestUtil.assertParseFailure(parser, validExpectedTaskString
                        + CommandTestUtil.INVALID_NAME_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));

        // invalid priority
        CommandParserTestUtil.assertParseFailure(parser, validExpectedTaskString
                        + CommandTestUtil.INVALID_PRIORITY_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PRIORITY));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Task expectedTask = new TaskBuilder(TypicalTasks.AMY).withTags().build();
        CommandParserTestUtil.assertParseSuccess(parser,
                CommandTestUtil.NAME_DESC_AMY + CommandTestUtil.PRIORITY_DESC_AMY
                        + CommandTestUtil.DUEDATE_DESC,
                new AddCommand(expectedTask));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_FULL_HELP);

        // missing name prefix
        CommandParserTestUtil.assertParseFailure(parser, CommandTestUtil.PRIORITY_DESC_BOB
                        + CommandTestUtil.DUEDATE_DESC,
                expectedMessage);

        // missing priority prefix
        CommandParserTestUtil.assertParseFailure(parser, CommandTestUtil.VALID_NAME_BOB
                        + CommandTestUtil.DUEDATE_DESC,
                expectedMessage);

        // missing due date prefix
        CommandParserTestUtil.assertParseFailure(parser, CommandTestUtil.VALID_NAME_BOB
                        + CommandTestUtil.PRIORITY_DESC_BOB,
                expectedMessage);
    }


    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        CommandParserTestUtil.assertParseFailure(parser, CommandTestUtil.INVALID_NAME_DESC
                + CommandTestUtil.PRIORITY_DESC_BOB + CommandTestUtil.TAG_DESC_HUSBAND
                + CommandTestUtil.TAG_DESC_FRIEND, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AddCommand.MESSAGE_FULL_HELP));

        // invalid priority
        CommandParserTestUtil.assertParseFailure(parser, CommandTestUtil.NAME_DESC_BOB
                + CommandTestUtil.INVALID_PRIORITY_DESC + CommandTestUtil.TAG_DESC_HUSBAND
                + CommandTestUtil.TAG_DESC_FRIEND, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AddCommand.MESSAGE_FULL_HELP));

        // invalid tag
        CommandParserTestUtil.assertParseFailure(parser, CommandTestUtil.NAME_DESC_BOB
                + CommandTestUtil.PRIORITY_DESC_BOB
                + CommandTestUtil.INVALID_TAG_DESC + CommandTestUtil.VALID_TAG_FRIEND,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_FULL_HELP));

        // two invalid values, only first invalid value reported
        CommandParserTestUtil.assertParseFailure(parser, CommandTestUtil.INVALID_NAME_DESC
                        + CommandTestUtil.PRIORITY_DESC_BOB, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AddCommand.MESSAGE_FULL_HELP));

        // non-empty preamble
        CommandParserTestUtil.assertParseFailure(parser, CommandTestUtil.PREAMBLE_NON_EMPTY
                        + CommandTestUtil.NAME_DESC_BOB + CommandTestUtil.PRIORITY_DESC_BOB
                        + CommandTestUtil.TAG_DESC_HUSBAND + CommandTestUtil.TAG_DESC_FRIEND,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_FULL_HELP));
    }
}
