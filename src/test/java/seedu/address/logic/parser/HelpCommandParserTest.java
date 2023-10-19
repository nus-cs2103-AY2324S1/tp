package seedu.address.logic.parser;

import seedu.address.logic.commands.HelpCommand;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

public class HelpCommandParserTest {

    private HelpCommandParser parser = new HelpCommandParser();

    @Test
    public void parse_emptyArg() {
        assertParseSuccess(parser, "     ", new HelpCommand(true));
    }

    @Test
    public void parse_recognizableArg() {
        assertParseSuccess(parser, "add", new HelpCommand(true, "add"));
        assertParseSuccess(parser, "delete", new HelpCommand(true, "delete"));
        assertParseSuccess(parser, "clear      ", new HelpCommand(true, "clear"));
        assertParseSuccess(parser, "    edit   \n", new HelpCommand(true, "edit"));
        assertParseSuccess(parser, "find\t\t\t", new HelpCommand(true, "find"));
        assertParseSuccess(parser, "list", new HelpCommand(true, "list"));
    }

    @Test
    public void parse_unrecognizableArg() {
        assertParseSuccess(parser, "baddt", new HelpCommand(false, "add"));
        assertParseSuccess(parser, "deleet", new HelpCommand(false, "delete"));
        assertParseSuccess(parser, "clean      ", new HelpCommand(false, "clear"));
    }
}
