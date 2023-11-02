package swe.context.logic.parser;

import static swe.context.logic.parser.CommandParserTestUtil.assertParseFailure;
import static swe.context.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import swe.context.logic.Messages;
import swe.context.logic.commands.FilterCommand;
import swe.context.model.contact.ContainsTagPredicate;


public class FilterCommandParserTest {
    private FilterCommandParser parser = new FilterCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", Messages.commandInvalidFormat(FilterCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFilterCommand() {
        FilterCommand expectedFindCommand =
                new FilterCommand(new ContainsTagPredicate("Friends"));
        assertParseSuccess(parser, "Friends", expectedFindCommand);
    }
}
