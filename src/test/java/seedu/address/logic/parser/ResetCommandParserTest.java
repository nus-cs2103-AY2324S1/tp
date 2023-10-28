package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FIELD;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ResetCommand;

public class ResetCommandParserTest {
    private ResetCommandParser parser = new ResetCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, ResetCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsResetCommand() {

        String userInput = " " + PREFIX_FIELD + "overtime";

        ResetCommand expectedResetCommand = new ResetCommand("overtime");

        assertParseSuccess(parser, userInput, expectedResetCommand);
    }
}
