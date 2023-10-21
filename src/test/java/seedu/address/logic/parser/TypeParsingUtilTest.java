package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;

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
            assertEquals(TypeParsingUtil.parseDate("day", "-day 12/30"),
                    LocalDate.now().withMonth(12).withDayOfMonth(30));
            assertEquals(TypeParsingUtil.parseDate("day", "-day 30"),
                    LocalDate.now().withDayOfMonth(30));
            assertEquals(TypeParsingUtil.parseDate("day", "-day 3"),
                    LocalDate.now().withDayOfMonth(3));
            assertEquals(TypeParsingUtil.parseDate("day", "-day 24/2/23"),
                    LocalDate.now().withYear(2024).withMonth(2).withDayOfMonth(23));
            assertThrows(ParseException.class, () -> TypeParsingUtil.parseDate("day", "-day 30/12"));
            assertThrows(ParseException.class, () -> TypeParsingUtil.parseDate("day", "-day 33"));
            //this line should not throw exception as day flag is not in string and is optional
            TypeParsingUtil.parseDate("day", "day 30/13/2023", true);
        } catch (ParseException e) {
            fail(e.getMessage());
        }
    }

    @Test
    void parseTimeTest() {
        String testString = "addLesson -name yiwen -start 14:30 -end 17:30 -day 2023/12/30";
        try {
            assertEquals(TypeParsingUtil.parseTime("start", testString).toString(), "14:30");
            assertThrows(ParseException.class, () -> TypeParsingUtil.parseTime("start", "-start 14:60"));
            TypeParsingUtil.parseTime("start", "start 14:60", true);
        } catch (ParseException e) {
            fail(e.getMessage());
        }
    }

    @Test
    void parseDayOfWeekTest() {
        String testString = "addLesson -name yiwen -start 14:30 -end 17:30 -day 2023/12/30 -dayOfWeek MONDAY";
        try {
            assertEquals(TypeParsingUtil.parseDayOfWeek("dayOfWeek", testString).toString(), "MONDAY");
            assertThrows(ParseException.class, () -> TypeParsingUtil.parseDayOfWeek("dayOfWeek", "-dayOfWeek 14:60"));
            TypeParsingUtil.parseDayOfWeek("dayOfWeek", "dayOfWeek 14:60", true);
        } catch (ParseException e) {
            fail(e.getMessage());
        }
    }

    @Test
    void parseSubjectTest() {
        String testString = "addLesson -name yiwen -start 14:30 -end 17:30 -day 2023/12/30 -subject english";
        try {
            assertEquals(TypeParsingUtil.parseSubject("subject", testString).toString(), "[ENGLISH]");
            assertThrows(ParseException.class, () -> TypeParsingUtil.parseSubject("subject", "-subject cs2103"));
            TypeParsingUtil.parseSubject("subject", "subject 14:60", true);
        } catch (ParseException e) {
            fail(e.getMessage());
        }
    }

    @Test
    void parseStrTest() {
        String testString = "addLesson -name yiwen -start 14:30 -end 17:30 -day 2023/12/30";
        try {
            assertEquals(TypeParsingUtil.parseStr("name", testString), "yiwen");
            TypeParsingUtil.parseStr("name", "name 14:60", true);
        } catch (ParseException e) {
            fail(e.getMessage());
        }
    }

    @Test
    void parseNumTest() {
        try {
            assertThrows(ParseException.class, () -> TypeParsingUtil.parseNum("num", "hello"));
            assertEquals(TypeParsingUtil.parseNum("num", "-num 123"), 123);
            assertThrows(ParseException.class, () -> TypeParsingUtil.parseNum("num", "-num 123", 0, 100));
            TypeParsingUtil.parseNum("num", "num text", true);
        } catch (ParseException e) {
            fail(e.getMessage());
        }
    }
}
