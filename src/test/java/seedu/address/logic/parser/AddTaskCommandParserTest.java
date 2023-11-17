package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddTaskCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.animal.Task;

public class AddTaskCommandParserTest {
    private final AddTaskCommandParser parser = new AddTaskCommandParser();

    @Test
    public void parse_validArgs_returnsAddTaskCommand() throws ParseException {
        Task taskStub = new Task("Feed dog");
        AddTaskCommand expectedAddTaskCommand = new AddTaskCommand(Index.fromOneBased(1), taskStub);
        assertEquals(parser.parse("1 Feed dog"), expectedAddTaskCommand);
    }

    @Test
    public void parse_validArgsWithMultipleWhitespace_returnsAddTaskCommand() throws ParseException {
        Task taskStub = new Task("Feed dog");
        AddTaskCommand expectedAddTaskCommand = new AddTaskCommand(Index.fromOneBased(1), taskStub);
        assertEquals(parser.parse("1   Feed dog"), expectedAddTaskCommand);
    }

    @Test
    public void parse_nonNumericIndex_throwsParseException() {
        assertThrows(ParseException.class, () -> parser.parse("a"));
    }

    @Test
    public void parse_negativeIndex_throwsParseException() {
        assertThrows(ParseException.class, () -> parser.parse("-1 Feed dog"));
    }

    @Test
    public void parse_zeroIndex_throwsParseException() {
        assertThrows(ParseException.class, () -> parser.parse("0 Feed dog"));
    }

    @Test
    public void parse_emptyArgs_throwsParseException() {
        assertThrows(ParseException.class, () -> parser.parse(""));
    }

    @Test
    public void parse_emptyTaskDescription_throwsParseException() {
        assertThrows(ParseException.class, () -> parser.parse("1"));
    }
}
