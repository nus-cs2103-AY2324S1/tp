package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ViewNotesCommand;

public class ViewNotesCommandParserTest {
    private static final String MESSAGE_INVALID_FORMAT =
        String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewNotesCommand.MESSAGE_USAGE);
    private ViewNotesCommandParser parser = new ViewNotesCommandParser();

    @Test
    public void parse_validArgs_returnsViewNotesCommand() {
        assertParseSuccess(parser, "1", new ViewNotesCommand(0));
        assertParseSuccess(parser, "  3", new ViewNotesCommand(2)); // Whitespaces should be trimmed
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        // Invalid format
        assertParseFailure(parser, "a", MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, "-1", MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, "0", MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, "1 a", MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, " ", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_emptyArgs_throwsParseException() {
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }
}
