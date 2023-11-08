package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Name;
import seedu.address.model.person.Tags;

//todo add on test
//todo, check the name of variable and test are good.
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
    void parseNum_invalidNum_outOfBound() throws ParseException {
        assertThrows(ParseException.class, () -> TypeParsingUtil.parseNum("hello"));
        assertThrows(ParseException.class, () -> TypeParsingUtil.parseNum("123 hello"));
        assertThrows(ParseException.class, () -> TypeParsingUtil.parseNum("123 123"));
        assertThrows(ParseException.class, () -> TypeParsingUtil.parseNum("123", 121, 122));
        assertThrows(ParseException.class, () -> TypeParsingUtil.parseNum("123", 124, 125));
        assertThrows(ParseException.class, () -> TypeParsingUtil.parseNum("123", 124, 122));
        assertThrows(ParseException.class, () -> TypeParsingUtil.parseNum("-1", 0, 1));
    }

    @Test
    void parseNum_validNum() throws ParseException {
        assertEquals(TypeParsingUtil.parseNum("123"), 123);
        assertEquals(TypeParsingUtil.parseNum("123", 123, 123), 123);
        assertEquals(TypeParsingUtil.parseNum("0", -1, 1), 0);
        assertEquals(TypeParsingUtil.parseNum("-1"), -1);
    }

    @Test
    void parseIndex_valid_isOptionalIndex() throws ParseException {
        assertEquals(TypeParsingUtil.parseIndex("1", false), 1);
        assertEquals(TypeParsingUtil.parseIndex("99999", true), 99999);
        assertEquals(TypeParsingUtil.parseIndex("99999 some text -flag flag", false), 99999);
        assertNull(TypeParsingUtil.parseIndex("some text -flag flag", true));
    }
    //todo make it clear that description cannot start with number in this case.
    @Test
    void parseIndex_invalid_outOfBound() throws ParseException {
        assertThrows(ParseException.class, () -> TypeParsingUtil.parseIndex("-1", true));
        assertThrows(ParseException.class, () -> TypeParsingUtil.parseIndex("-1 some text -flag flag", true));
        assertThrows(ParseException.class, () -> TypeParsingUtil.parseIndex("0 some text -flag flag", false));
        assertThrows(ParseException.class, () -> TypeParsingUtil.parseIndex("100000 some text -flag flag", true));
        assertThrows(ParseException.class, () -> TypeParsingUtil.parseIndex("1/2 some text -flag flag", false));
        assertThrows(ParseException.class, () -> TypeParsingUtil.parseIndex("1.2 some text -flag flag", true));
    }
    @Test
    void parseField() throws ParseException {
        assertEquals(TypeParsingUtil.parseField("name", "-name name", Name::of), Name.of("name"));
        assertNull(TypeParsingUtil.parseField("name", "", Name::of, true));
        assertThrows(ParseException.class, () -> TypeParsingUtil.parseField("name", "", Name::of, false));

        assertEquals(TypeParsingUtil.parseField("tag", "-tag tag1,tag2", Tags::of), Tags.of("tag1,tag2"));
        assertNull(TypeParsingUtil.parseField("tag", "", Tags::of, true));
        assertThrows(ParseException.class, () -> TypeParsingUtil.parseField("tag", "", Tags::of, false));
    }


}
