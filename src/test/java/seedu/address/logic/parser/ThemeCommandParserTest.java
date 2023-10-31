package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseBasicFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseBasicSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ThemeCommand;
import seedu.address.model.Theme;

public class ThemeCommandParserTest {
    private ThemeCommandParser parser = new ThemeCommandParser();

    @Test
    public void parse_validArgs_returnsThemeCommand() {
        assertParseBasicSuccess(parser, "dArk", new ThemeCommand(Theme.DARK));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseBasicFailure(parser, "darke", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                ThemeCommand.MESSAGE_USAGE));
    }
}
