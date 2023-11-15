package seedu.address.logic.parser;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.ListTodayCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class ListTodayCommandParserTest {
    private ListTodayCommandParser parser = new ListTodayCommandParser();

    @Test
    public void parse_validInput_success() throws ParseException {
        assertParseSuccess(parser, "", new ListTodayCommand());
    }

    @Test
    public void parse_invalidInput_failure() {
        assertParseFailure(parser, "invalid input",
                String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                        ListTodayCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_extraValues_failure() {
        // Random values
        assertParseFailure(parser, "ABCDEFGH",
                String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                        ListTodayCommand.MESSAGE_USAGE));

        // Random prefixes
        assertParseFailure(parser, "mn/ABCD d/2",
                String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                        ListTodayCommand.MESSAGE_USAGE));
    }

}
