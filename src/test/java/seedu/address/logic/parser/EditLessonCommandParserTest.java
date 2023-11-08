package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.DeleteLessonCommand;
import seedu.address.logic.commands.EditLessonCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.lessons.Day;
import seedu.address.model.lessons.Lesson;
import seedu.address.model.lessons.Time;
import seedu.address.model.person.Name;
import seedu.address.model.person.Subject;

class EditLessonCommandParserTest {
    private EditLessonCommandParser p = new EditLessonCommandParser();
    @Test
    void test_startAfterEnd() {
        Assertions.assertThrows(ParseException.class, () -> p.parse("1 -name name -start 14:01 -end 14:00"));
        Assertions.assertThrows(ParseException.class, () -> p.parse(" -name name -start 14:01 -end 14:00"));
    }
    @Test
    void test_parseName() throws ParseException {
        Lesson expected = Lesson.getDefaultLesson();
        expected.setNameIfNotDefault(Name.of("name"));
        assertEquals(expected, p.parse(" -name name").getEditDescriptor());
        assertEquals(expected, p.parse("1 -name name").getEditDescriptor());
        assertNull(p.parse(" -name name").getIndex());
        assertEquals(1, p.parse("1 -name name").getIndex());
        assertThrows(ParseException.class, () -> p.parse(" -name"));
        assertThrows(ParseException.class, () -> p.parse("-1 -name 1"));
    }
    @Test
    void test_parseStart_parseEnd() throws ParseException {
        Lesson expected = Lesson.getDefaultLesson();
        expected.updateStartAndEnd(Time.of("14:00"), Time.of("15:00"));
        assertEquals(expected, p.parse("-start 14:00 -end 15:00").getEditDescriptor());
        assertNull(p.parse("-start 14:00 -end 15:00").getIndex());
        assertEquals(expected, p.parse("1 -start 14:00 -end 15:00").getEditDescriptor());
        assertEquals(1, p.parse("1 -start 14:00 -end 15:00").getIndex());
        expected = Lesson.getDefaultLesson();
        expected.setEnd(Time.of("15:00"));
        assertEquals(expected, p.parse("-end 15:00").getEditDescriptor());
        assertNull(p.parse("-end 15:00").getIndex());
        assertEquals(expected, p.parse("1 -end 15:00").getEditDescriptor());
        assertEquals(1, p.parse("1 -end 15:00").getIndex());
        expected = Lesson.getDefaultLesson();
        expected.setStart(Time.of("14:00"));
        assertEquals(expected, p.parse("-start 14:00").getEditDescriptor());
        assertNull(p.parse("-start 14:00").getIndex());
        assertEquals(expected, p.parse("1 -start 14:00").getEditDescriptor());
        assertEquals(1, p.parse("1 -start 14:00").getIndex());
        assertThrows(ParseException.class, () -> p.parse("-name name -start 16:00 -end 15:00 -start 14:00"));
    }
    @Test
    void test_parseSubject() throws ParseException {
        Lesson expected = Lesson.getDefaultLesson();
        expected.setSubject(Subject.of("physics"));
        assertEquals(expected, p.parse("-subject physics").getEditDescriptor());
        assertNull(p.parse("-subject physics").getIndex());
        assertEquals(expected, p.parse("1 -subject physics").getEditDescriptor());
        assertEquals(1, p.parse("1 -subject physics").getIndex());
        Assertions.assertThrows(ParseException.class, () -> p.parse("-name name -subject physics, chemistry"));
        Assertions.assertThrows(ParseException.class, () -> p.parse("-name name -subject social studies"));
    }
    @Test
    void test_parseDay() throws ParseException {
        Lesson expected = Lesson.getDefaultLesson();
        expected.setName(Name.of("name"));
        expected.setDay(Day.of("1"));
        assertEquals(expected, p.parse("-name name -day 1").getEditDescriptor());
        assertNull(p.parse("-name name -day 1").getIndex());
        assertEquals(expected, p.parse("1 -name name -day 1").getEditDescriptor());
        assertEquals(1, p.parse("1 -name name -day 1").getIndex());
        Assertions.assertThrows(ParseException.class, () -> p.parse("-name name -day monday"));
        Assertions.assertThrows(ParseException.class, () -> p.parse("-name name -day -1"));
        Assertions.assertThrows(ParseException.class, () -> p.parse("-name name -day 23/2/29"));
    }

    @Test
    void test_combine() throws ParseException {
        Lesson expected = Lesson.getDefaultLesson();
        expected.setName(Name.of("name"));
        expected.setSubject(Subject.of("physics"));
        expected.setDay(Day.of("1"));
        expected.updateStartAndEnd(Time.of("14:00"), Time.of("15:00"));
        assertEquals(expected, p.parse("-name name -subject physics -day 1"
                + " -start 14:00 -end 15:00").getEditDescriptor());
        assertNull(p.parse("-name name -subject physics -day 1"
                + " -start 14:00 -end 15:00").getIndex());
        assertEquals(expected, p.parse("1 -name name -subject physics -day 1"
                + " -start 14:00 -end 15:00").getEditDescriptor());
        assertEquals(1, p.parse("1 -name name -subject physics -day 1"
                + " -start 14:00 -end 15:00").getIndex());
    }
    @Test
    void test_duplicateFlag_unrecognisedFlags() throws ParseException {
        assertThrows(ParseException.class, () -> p.parse("-name name -name name"));
        assertThrows(ParseException.class, () -> p.parse("1 -name name -name name"));
        p.parse("this is alright -name name -subject physics -day 1 -start 14:00 -end 15:00 -flag flag");
    }

}