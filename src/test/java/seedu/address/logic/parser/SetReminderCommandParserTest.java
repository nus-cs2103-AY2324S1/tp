package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.SetReminderCommand;

public class SetReminderCommandParserTest {
    private SetReminderCommandParser parser = new SetReminderCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SetReminderCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsRemoveReminderCommand() {
        // no leading and trailing whitespaces
        SetReminderCommand expectedSetReminderCommand = new SetReminderCommand("CS2103 Meeting");
        assertParseSuccess(parser, "CS2103 Meeting", expectedSetReminderCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n CS2103 Meeting  \t", expectedSetReminderCommand);
    }
}
