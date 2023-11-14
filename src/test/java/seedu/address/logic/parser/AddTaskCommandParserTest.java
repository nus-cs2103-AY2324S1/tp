package seedu.address.logic.parser;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.lessons.Task;

class AddTaskCommandParserTest {
    private AddTaskCommandParser p = new AddTaskCommandParser();
    @Test
    void test_parseIndex_parseTask() throws ParseException {
        assertEquals(1, p.parse("1 description").getIndex());
        assertEquals(Task.of("description, some description."), p.parse("1 description, some description.").getTask());
        assertEquals(Task.of("description lala?"), p.parse("description lala?").getTask());
        assertNull(p.parse("description lala?").getIndex());
        assertEquals(Task.of("99description-l"), p.parse("99description-l").getTask());
    }
    @Test
    void test_invalidIndex_invalidTask() throws ParseException {
        assertThrows(ParseException.class, () -> p.parse("-1 description"));
        assertThrows(ParseException.class, () -> p.parse("1"));
        assertThrows(ParseException.class, () -> p.parse("1 "));
        assertThrows(ParseException.class, () -> p.parse("199999  "));
    }
}
