package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class FindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_hasPreamble_throwsParseException() {
        // just preamble
        assertParseFailure(parser, " hello world",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));

        // preamble with valid args after
        assertParseFailure(parser, " hello world n/Alice",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validOneArg_returnsFindCommand() throws ParseException {
        // name arg
        assertDoesNotThrow(() -> parser.parse(" n/Alice"));
        assertTrue(parser.parse(" n/Alice") instanceof FindCommand);

        // other arg
        assertDoesNotThrow(() -> parser.parse(" t/friends"));
        assertTrue(parser.parse(" t/friends") instanceof FindCommand);
    }

    @Test
    public void parse_validMultipleArgs_returnsFindCommand() throws ParseException {
        // name args
        assertDoesNotThrow(() -> parser.parse(" n/Alice n/Li"));
        assertTrue(parser.parse(" n/Alice n/Li") instanceof FindCommand);

        // multiple distinct args
        assertDoesNotThrow(() -> parser.parse(" n/Alice t/friends"));
        assertTrue(parser.parse(" n/Alice t/friends") instanceof FindCommand);

        // multiple distinct args with repeated fields
        assertDoesNotThrow(() -> parser.parse(" n/Alice t/colleagues t/friends"));
        assertTrue(parser.parse( " n/Alice t/colleagues t/friends") instanceof FindCommand);
    }

}
