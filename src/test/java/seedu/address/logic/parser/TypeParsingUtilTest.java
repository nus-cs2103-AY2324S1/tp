package seedu.address.logic.parser;

import org.junit.jupiter.api.Test;
import seedu.address.logic.parser.exceptions.ParseException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

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
}