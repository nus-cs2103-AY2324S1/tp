package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ThemeCommand;

public class ThemeCommandParserTest {
    private ThemeCommandParser parser = new ThemeCommandParser();

    @Test
    public void parse_validDarkTheme_success() {
        assertParseSuccess(parser, " DARK", new ThemeCommand(false));
    }

    @Test
    public void parse_validLightTheme_success() {
        assertParseSuccess(parser, " LIGHT", new ThemeCommand(true));
    }

    @Test
    public void parse_caseInsensitiveDarkTheme_success() {
        assertParseSuccess(parser, " daRK", new ThemeCommand(false));
    }

    @Test
    public void parse_caseInsensitiveLightTheme_success() {
        assertParseSuccess(parser, " lIGhT", new ThemeCommand(true));
    }

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, ThemeCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidInput_throwsParseException() {
        assertParseFailure(parser, " YELLOW",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ThemeCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validDarkThemeWithWhitespace_success() {
        assertParseSuccess(parser, " dark   ", new ThemeCommand(false));
    }

    @Test
    public void parse_validLightThemeWithWhitespace_success() {
        assertParseSuccess(parser, "   LIGHT   ", new ThemeCommand(true));
    }

    @Test
    public void parse_validMixedCaseTheme_success() {
        assertParseSuccess(parser, " DaRk", new ThemeCommand(false));
        assertParseSuccess(parser, " LiGhT", new ThemeCommand(true));
    }

    @Test
    public void parse_invalidInputWithWhitespace_throwsParseException() {
        assertParseFailure(parser, " invalid ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ThemeCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidInputWithWhitespaceAndValidInput_throwsParseException() {
        assertParseFailure(parser, " invalid dark",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ThemeCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidInputWithAbbreviation_throwsParseException() {
        assertParseFailure(parser, " i",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ThemeCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidInputWithAbbreviationAndValidInput_throwsParseException() {
        assertParseFailure(parser, " i dark",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ThemeCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_multipleThemesInSingleInput_throwsParseException() {
        assertParseFailure(parser, "DARK LIGHT",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ThemeCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidInputWithoutSpacing_throwsParseException() {
        assertParseFailure(parser, "DARKLIGHT",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ThemeCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_excessivelyLongInput_throwsParseException() {
        assertParseFailure(parser, "DARK LIGHT DARK LIGHT DARK LIGHT DARK LIGHT DARK LIGHT DARK LIGHT DARK LIGHT",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ThemeCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_excessivelyLongInputMixedCase_throwsParseException() {
        assertParseFailure(parser, "DaRk LiGhT DaRk LiGhT DaRk LiGhT DaRk LiGhT DaRk LiGhT DaRk LiGhT DaRk LiGhT",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ThemeCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_singleCharacterAbbreviation_throwsParseException() {
        assertParseFailure(parser, "d", String.format(MESSAGE_INVALID_COMMAND_FORMAT, ThemeCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_singleCharacterAbbreviationMixedCase_throwsParseException() {
        assertParseFailure(parser, "l", String.format(MESSAGE_INVALID_COMMAND_FORMAT, ThemeCommand.MESSAGE_USAGE));
    }



}
