package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteTaskCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class DeleteTaskCommandParserTest {
    private final DeleteTaskCommandParser parser = new DeleteTaskCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteTaskCommand() throws ParseException {
        DeleteTaskCommand expectedDeleteTaskCommand = new DeleteTaskCommand(Index.fromOneBased(1),
                Index.fromOneBased(1));
        assertEquals(expectedDeleteTaskCommand, parser.parse("1 1"));
    }

    @Test
    public void parse_validArgsWithMultipleWhitespace_returnsDeleteTaskCommand() throws ParseException {
        DeleteTaskCommand expectedDeleteTaskCommand = new DeleteTaskCommand(Index.fromOneBased(1),
                Index.fromOneBased(1));
        assertEquals(expectedDeleteTaskCommand, parser.parse(" 1   1 "));
    }

    @Test
    public void parse_nonNumericAnimalIndex_throwsParseException() {
        assertThrows(ParseException.class, () -> parser.parse("a 1"));
    }

    @Test
    public void parse_nonNumericTaskIndex_throwsParseException() {
        assertThrows(ParseException.class, () -> parser.parse("1 a"));
    }

    @Test
    public void parse_negativeAnimalIndex_throwsParseException() {
        assertThrows(ParseException.class, () -> parser.parse("-1 1"));
    }

    @Test
    public void parse_negativeTaskIndex_throwsParseException() {
        assertThrows(ParseException.class, () -> parser.parse("1 -1"));
    }

    @Test
    public void parse_zeroAnimalIndex_throwsParseException() {
        assertThrows(ParseException.class, () -> parser.parse("0 1"));
    }

    @Test
    public void parse_zeroTaskIndex_throwsParseException() {
        assertThrows(ParseException.class, () -> parser.parse("1 0"));
    }

    @Test
    public void parse_emptyArgs_throwsParseException() {
        assertThrows(ParseException.class, () -> parser.parse(""));
    }

    @Test
    public void parse_singleIndex_throwsParseException() {
        assertThrows(ParseException.class, () -> parser.parse("1"));
    }

    @Test
    public void parse_moreThanTwoIndices_throwsParseException() {
        assertThrows(ParseException.class, () -> parser.parse("1 2 3"));
    }
}
