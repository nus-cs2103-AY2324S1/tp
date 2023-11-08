package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Name;
import seedu.address.model.person.Tags;
import seedu.address.model.tag.Tag;

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
    void getValueImmediatelyAfterCommandNameTest() {
        try {
            String result = TypeParsingUtil.getValueImmediatelyAfterCommandName("edit", "index", "edit 1 yiwen");
            assertEquals(result, "1");
        } catch (ParseException e) {
            fail(e.getMessage());
        }
    }

}
