package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddLessonCommand;
import seedu.address.logic.parser.exceptions.ParseException;


class AddLessonCommandParserTest {
    @Test
    void testParse() {
        AddLessonCommandParser p = new AddLessonCommandParser();
        try {
            AddLessonCommand c = p.parse("addLesson -name yiwen -start 14:30 -end 17:30 -day 2023/12/30");
            assertNotNull(c);
            c = p.parse("addLesson -name yiwen -start 14:30 -end 17:30 -day 2023/12/30 -subject english");
            assertNotNull(c);
            String invalid = "addLesson -name yiwen -start 18:30 -end 17:30 -day 2023/12/30";
            assertThrows(ParseException.class, () -> p.parse(invalid));
        } catch (Exception e) {
            fail();
        }
    }
}
