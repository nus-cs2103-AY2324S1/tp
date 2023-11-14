package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.DeleteTaskCommand;
import seedu.address.logic.parser.exceptions.ParseException;

class DeleteTaskCommandParserTest {
    private DeleteTaskCommandParser parser = new DeleteTaskCommandParser();
    @Test
    public void parse_validArgs_returnsDeleteTaskCommand() throws ParseException {
        DeleteTaskCommand c = new DeleteTaskCommand(1);
        parser.parse("1");
        assertParseSuccess(parser, "1", new DeleteTaskCommand(1));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertThrows(ParseException.class, () -> parser.parse("-1"));
        assertThrows(ParseException.class, () -> parser.parse("0"));
        assertThrows(ParseException.class, () -> parser.parse("1. 1"));
        assertThrows(ParseException.class, () -> parser.parse("1.1"));
        assertThrows(ParseException.class, () -> parser.parse("1/ 2"));
        assertThrows(ParseException.class, () -> parser.parse("1/2"));
        assertThrows(ParseException.class, () -> parser.parse("100000"));
    }

}
