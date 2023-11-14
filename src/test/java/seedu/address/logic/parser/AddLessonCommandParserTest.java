package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.lessons.Day;
import seedu.address.model.lessons.Lesson;
import seedu.address.model.lessons.Time;
import seedu.address.model.person.Name;
import seedu.address.model.person.Subject;


class AddLessonCommandParserTest {
    private AddLessonCommandParser p = new AddLessonCommandParser();
    @Test
    void test_parseWithoutName_startAfterEnd() {
        assertThrows(ParseException.class, () -> p.parse(" -start 14:00 -end 15:00"));
        assertThrows(ParseException.class, () -> p.parse(" -name name -start 14:01 -end 14:00"));
    }
    @Test
    void test_parseName_missingName() throws ParseException {
        Lesson expected = Lesson.getDefaultLesson();
        expected.setNameIfNotDefault(Name.of("name"));
        assertEquals(expected, p.parse(" -name name").getLesson());
        assertThrows(ParseException.class, () -> p.parse(" -name"));
        assertThrows(ParseException.class, () -> p.parse(" -start 14:00"));
        assertThrows(ParseException.class, () -> p.parse(""));
    }
    @Test
    void test_parseStart_parseEnd() throws ParseException {
        Lesson expected = Lesson.getDefaultLesson();
        expected.setName(Name.of("name"));
        expected.updateStartAndEnd(Time.of("14:00"), Time.of("15:00"));
        assertEquals(expected, p.parse("-name name -start 14:00 -end 15:00").getLesson());
        expected = Lesson.getDefaultLesson();
        expected.setName(Name.of("name"));
        expected.setEnd(Time.of("15:00"));
        assertEquals(expected, p.parse("-name name -end 15:00").getLesson());
        expected = Lesson.getDefaultLesson();
        expected.setName(Name.of("name"));
        expected.setStart(Time.of("14:00"));
        assertEquals(expected, p.parse("-name name -start 14:00").getLesson());
        assertThrows(ParseException.class, () -> p.parse("-name name -start 16:00 -end 15:00 -start 14:00"));
    }
    @Test
    void test_parseSubject() throws ParseException {
        Lesson expected = Lesson.getDefaultLesson();
        expected.setName(Name.of("name"));
        expected.setSubject(Subject.of("physics"));
        assertEquals(expected, p.parse("-name name -subject physics").getLesson());
        assertThrows(ParseException.class, () -> p.parse("-name name -subject physics, chemistry"));
        assertThrows(ParseException.class, () -> p.parse("-name name -subject social studies"));
    }
    @Test
    void test_parseDay() throws ParseException {
        Lesson expected = Lesson.getDefaultLesson();
        expected.setName(Name.of("name"));
        expected.setDay(Day.of("1"));
        assertEquals(expected, p.parse("-name name -day 1").getLesson());
        assertThrows(ParseException.class, () -> p.parse("-name name -day monday"));
        assertThrows(ParseException.class, () -> p.parse("-name name -day -1"));
        assertThrows(ParseException.class, () -> p.parse("-name name -day 23/2/29"));
    }

    @Test
    void test_combine() throws ParseException {
        Lesson expected = Lesson.getDefaultLesson();
        expected.setName(Name.of("name"));
        expected.setSubject(Subject.of("physics"));
        expected.setDay(Day.of("1"));
        expected.updateStartAndEnd(Time.of("14:00"), Time.of("15:00"));
        assertEquals(expected, p.parse("-name name -subject physics -day 1"
                + " -start 14:00 -end 15:00").getLesson());
    }
    @Test
    void test_duplicateFlag_unrecognisedFlags() throws ParseException {
        assertThrows(ParseException.class, () -> p.parse("-name name -name name"));
        assertThrows(ParseException.class, () -> p.parse(""));
        p.parse("this is alright -name name -subject physics -day 1 -start 14:00 -end 15:00 -flag flag");
    }
}
