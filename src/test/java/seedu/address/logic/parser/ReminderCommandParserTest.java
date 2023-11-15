package seedu.address.logic.parser;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ReminderCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class ReminderCommandParserTest {
    private ReminderCommandParser parser = new ReminderCommandParser();

    @Test
    public void parse_validInput_success() throws ParseException {
        assertParseSuccess(parser, "", new ReminderCommand());
    }

    @Test
    public void parse_invalidInput_failure() {
        assertParseFailure(parser, "invalid input",
                String.format(ReminderCommand.MESSAGE_USAGE, ReminderCommand.MESSAGE_USAGE));
    }
}
