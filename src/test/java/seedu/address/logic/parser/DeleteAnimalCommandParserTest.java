package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteAnimalCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class DeleteAnimalCommandParserTest {
    private final DeleteAnimalCommandParser parser = new DeleteAnimalCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteCommand() throws ParseException {
        DeleteAnimalCommand expectedDeleteCommand = new DeleteAnimalCommand(Index.fromOneBased(1));
        assertEquals(parser.parse("1"), expectedDeleteCommand);
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertThrows(ParseException.class, () -> parser.parse("a"));
    }

    @Test
    public void parse_negativeIndex_throwsParseException() {
        assertThrows(ParseException.class, () -> parser.parse("-1"));
    }

    @Test
    public void parse_zeroIndex_throwsParseException() {
        assertThrows(ParseException.class, () -> parser.parse("0"));
    }

    @Test
    public void parse_emptyArgs_throwsParseException() {
        assertThrows(ParseException.class, () -> parser.parse(""));
    }

    @Test
    public void parse_multipleArgs_throwsParseException() {
        assertThrows(ParseException.class, () -> parser.parse("1 2"));
    }
}
