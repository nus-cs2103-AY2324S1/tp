package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.TasksCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.group.GroupContainsKeywordsPredicate;

public class TasksCommandParserTest {

    private final TasksCommandParser parser = new TasksCommandParser();

    @Test
    public void parse_validArgs_returnsTasksCommand() {
        int groupNumber = 1;
        TasksCommand expectedTasksCommand = new TasksCommand(
            groupNumber,
            new GroupContainsKeywordsPredicate(Arrays.asList(String.valueOf(groupNumber)))
        );

        try {
            TasksCommand command = parser.parse("1");
            assertEquals(expectedTasksCommand, command);
        } catch (ParseException pe) {
            throw new AssertionError("Valid input should not result in ParseException.", pe);
        }
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        // Test non-numeric input
        assertThrows(ParseException.class, () -> parser.parse("a"));

        // Test zero as input
        assertThrows(ParseException.class, () -> parser.parse("0"));

        // Test negative input
        assertThrows(ParseException.class, () -> parser.parse("-1"));
    }

    @Test
    public void parse_emptyArgs_throwsParseException() {
        assertThrows(ParseException.class, () -> parser.parse(""));
    }

    @Test
    public void parse_spacesArgs_throwsParseException() {
        assertThrows(ParseException.class, () -> parser.parse(" "));
    }
}
