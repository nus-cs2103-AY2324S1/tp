package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;




class TypeParsingUtilTest {
    @Test
    void parseFlagTest() {
        assertThrows(ParseException.class, () -> TypeParsingUtil.parseFlag("num", "hello"));
        assertThrows(ParseException.class, () -> TypeParsingUtil.parseFlag("hello", "hello 123"));
        try {
            assertEquals(TypeParsingUtil.parseFlag("index", "-index 123"), "123");
            assertEquals(TypeParsingUtil.parseFlag("index", "-index 123 -email email"), "123");
        } catch (ParseException e) {
            fail(e.getMessage());
        }
    }
    @Test
    void parseDateTest() {
        String testString = "addLesson -name yiwen -start 14:30 -end 17:30 -day 2023/12/30";
        try {
            assertEquals(TypeParsingUtil.parseDate("day", testString).toString(), "2023-12-30");
        } catch (ParseException e) {
            fail(e.getMessage());
        }
    }
}
