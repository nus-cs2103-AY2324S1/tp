package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.RemoveReminderCommand;

public class RemoveReminderCommandParserTest {

    private RemoveReminderCommandParser parser = new RemoveReminderCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, RemoveReminderCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsRemoveReminderCommand() {
        // no leading and trailing whitespaces
        RemoveReminderCommand expectedRemoveReminderCommand = new RemoveReminderCommand("CS2103 Meeting");
        assertParseSuccess(parser, "CS2103 Meeting", expectedRemoveReminderCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n CS2103 Meeting  \t", expectedRemoveReminderCommand);
    }
}
