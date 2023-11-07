package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.lessons.Lesson;
import seedu.address.model.lessons.TaskList;
import seedu.address.model.lessons.Time;
import seedu.address.model.person.Name;


class AddLessonCommandTest {
    private final TaskList taskList = new TaskList();
    @Test
    void executeTest() {
        Lesson lesson = Lesson.getDefaultLesson();
        try {
            lesson.setName(new Name("test"));
            AddLessonCommand addLessonCommand = new AddLessonCommand(null);
            fail();
        } catch (Exception e) {
            assertTrue(e instanceof NullPointerException);
        }
        AddLessonCommand addLessonCommand = new AddLessonCommand(lesson);
        try {
            addLessonCommand.execute(null);
            fail();
        } catch (Exception e) {
            assertTrue(e instanceof NullPointerException);
        }
        try {
            Model model = new ModelManager();
            assertFalse(model.hasLessonClashWith(lesson));
            addLessonCommand.execute(model);
            assertTrue(model.hasLessonClashWith(lesson));
            assertThrows(CommandException.class, () -> addLessonCommand.execute(model));
        } catch (CommandException e) {
            fail();
        }
    }

    @Test
    public void lesson_startAfterEnd() throws ParseException {
        Lesson lesson = Lesson.getDefaultLesson();
        lesson.setStart(new Time("10:00"));
        assertThrows(IllegalArgumentException.class, () -> lesson.setEnd(new Time("09:00")));
        lesson.setEnd(new Time("11:00"));
        assertThrows(IllegalArgumentException.class, () -> lesson.setStart(new Time("12:00")));
    }

    @Test
    public void clash_withItself_true() throws ParseException {

        Lesson lesson1 = new Lesson("Test name", "10:00", "12:00", "20", "Chemistry",
                taskList);
        assertTrue(lesson1.isClashWith(lesson1));
    }

    @Test
    public void clash_withSameName_true() throws ParseException {
        Lesson lesson1 = new Lesson("Test name", "10:00", "12:00", "20", "Chemistry",
                taskList);
        Lesson lesson2 = new Lesson("Test name", "15:00", "17:00", "23", "Chemistry",
                taskList);
        assertTrue(lesson1.isClashWith(lesson2));
    }

    @Test
    public void doesNotClash_withNonOverlapping_true() throws ParseException {
        Lesson lesson1 = new Lesson("Test name", "10:00", "12:00", "20", "Chemistry",
                taskList);
        Lesson lesson2 = new Lesson("Test name 2", "10:00", "12:00", "23", "Chemistry",
                taskList);

        assertFalse(lesson1.isClashWith(lesson2));
    }

    @Test
    public void clash_withOverlappingLesson_true() throws ParseException {
        Lesson lesson1 = new Lesson("Test name", "10:00", "12:00", "20", "Chemistry",
                taskList);
        Lesson lesson2 = new Lesson("Test name 2", "11:00", "13:00", "20", "Chemistry",
                taskList);

        assertTrue(lesson1.isClashWith(lesson2));
    }

    @Test
    public void doesNotClash_startTimeEqualsEndTime_true() throws ParseException {
        Lesson lesson1 = new Lesson("Test name", "10:00", "12:00", "20", "Chemistry",
                taskList);
        Lesson lesson2 = new Lesson("Test name 2", "12:00", "13:00", "20", "Chemistry",
                taskList);

        assertFalse(lesson1.isClashWith(lesson2));
    }

}
