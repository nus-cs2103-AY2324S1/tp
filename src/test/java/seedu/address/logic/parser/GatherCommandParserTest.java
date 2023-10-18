package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.GatherCommandParser.MESSAGE_CONSTRAINTS;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.GatherCommand;

public class GatherCommandParserTest {
    private GatherCommandParser parser = new GatherCommandParser();
    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, GatherCommand.MESSAGE_USAGE));
    }
    @Test
    public void parse_invalidArgs_returnsException() {
        String prompt = "***";
        String exceptionMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, prompt, exceptionMessage);
    }

    @Test
    public void parse_validArgs_returnsGatherCommand() {
        String prompt = "Sample Prompt";
        GatherCommand expectedGatherCommand = new GatherCommand(prompt);
        assertParseSuccess(parser, prompt, expectedGatherCommand);
    }
}
