package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_UNKNOWN_THEME;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ThemeCommand;

public class ThemeCommandParserTest {

    private ThemeCommandParser parser = new ThemeCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, ThemeCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_unknownTheme_throwsParseException() {
        assertParseFailure(parser, "invalidTheme", String.format(MESSAGE_UNKNOWN_THEME,
            ThemeCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsThemeCommand() {
        ThemeCommand expectedThemeCommand =
            new ThemeCommand("/view/DarkTheme.css", "dark");
        assertParseSuccess(parser, "dark", expectedThemeCommand);
    }
}
