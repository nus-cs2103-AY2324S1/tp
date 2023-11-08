package seedu.address.logic.commands;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalLessons.getTypicalScheduleList;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.lessons.Day;
import seedu.address.model.lessons.Lesson;
import seedu.address.model.lessons.Time;
import seedu.address.model.person.Name;
import seedu.address.model.person.Subject;

class EditLessonCommandTest {
    @Test
    public void testEditValidName() throws ParseException, CommandException {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(),
                getTypicalScheduleList());
        Lesson lesson = model.getFilteredScheduleList().get(0);
        Lesson expected = lesson.clone();
        expected.setName(Name.of("Test Name 123"));
        Lesson editDescriptor = Lesson.getDefaultLesson();
        editDescriptor.setName(Name.of("Test Name 123"));
        EditLessonCommand editLessonCommand = new EditLessonCommand(1, editDescriptor);
        editLessonCommand.execute(model);
        assertEquals(expected, model.getFilteredScheduleList().get(0));
    }
    @Test
    public void testEditValidStart() throws ParseException, CommandException {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(),
                getTypicalScheduleList());
        Lesson lesson = model.getFilteredScheduleList().get(0);
        Lesson expected = lesson.clone();
        expected.setStart(new Time("00:00"));
        Lesson editDescriptor = Lesson.getDefaultLesson();
        editDescriptor.setStart(new Time("00:00"));
        EditLessonCommand editLessonCommand = new EditLessonCommand(1, editDescriptor);
        editLessonCommand.execute(model);
        assertEquals(expected, model.getFilteredScheduleList().get(0));
    }
    @Test
    public void testEditValidEnd() throws ParseException, CommandException {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(),
                getTypicalScheduleList());
        Lesson lesson = model.getFilteredScheduleList().get(0);
        Lesson expected = lesson.clone();
        expected.setEnd(new Time("23:59"));
        Lesson editDescriptor = Lesson.getDefaultLesson();
        editDescriptor.setEnd(new Time("23:59"));
        EditLessonCommand editLessonCommand = new EditLessonCommand(1, editDescriptor);
        editLessonCommand.execute(model);
        assertEquals(expected, model.getFilteredScheduleList().get(0));
    }
    @Test
    public void testEditValidSubject() throws ParseException, CommandException {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(),
                getTypicalScheduleList());
        Lesson lesson = model.getFilteredScheduleList().get(0);
        Lesson expected = lesson.clone();
        expected.setSubject(Subject.of("Physics"));
        Lesson editDescriptor = Lesson.getDefaultLesson();
        editDescriptor.setSubject(Subject.of("Physics"));
        EditLessonCommand editLessonCommand = new EditLessonCommand(1, editDescriptor);
        editLessonCommand.execute(model);
        assertEquals(expected, model.getFilteredScheduleList().get(0));
    }
    @Test
    public void testEditValidDay() throws ParseException, CommandException {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(),
                getTypicalScheduleList());
        Lesson lesson = model.getFilteredScheduleList().get(0);
        Lesson expected = lesson.clone();
        expected.setDay(Day.of("1"));
        Lesson editDescriptor = Lesson.getDefaultLesson();
        editDescriptor.setDay(Day.of("1"));
        EditLessonCommand editLessonCommand = new EditLessonCommand(1, editDescriptor);
        editLessonCommand.execute(model);
        assertEquals(expected, model.getLessonClashWith(expected));
    }

    @Test
    public void testEditValidNameNoIndex() throws ParseException, CommandException {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(),
                getTypicalScheduleList());
        Lesson lesson = model.getFilteredScheduleList().get(0);
        model.showLesson(lesson);
        Lesson expected = lesson.clone();
        expected.setName(Name.of("Test Name 123"));
        Lesson editDescriptor = Lesson.getDefaultLesson();
        editDescriptor.setName(Name.of("Test Name 123"));
        EditLessonCommand editLessonCommand = new EditLessonCommand(null, editDescriptor);
        editLessonCommand.execute(model);
        assertEquals(expected, model.getFilteredScheduleList().get(0));
    }
    @Test
    public void testEditValidStartNoIndex() throws ParseException, CommandException {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(),
                getTypicalScheduleList());
        Lesson lesson = model.getFilteredScheduleList().get(0);
        model.showLesson(lesson);
        Lesson expected = lesson.clone();
        expected.setStart(new Time("00:00"));
        Lesson editDescriptor = Lesson.getDefaultLesson();
        editDescriptor.setStart(new Time("00:00"));
        EditLessonCommand editLessonCommand = new EditLessonCommand(null, editDescriptor);
        editLessonCommand.execute(model);
        assertEquals(expected, model.getFilteredScheduleList().get(0));
    }
    @Test
    public void testEditValidEndNoIndex() throws ParseException, CommandException {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(),
                getTypicalScheduleList());
        Lesson lesson = model.getFilteredScheduleList().get(0);
        model.showLesson(lesson);
        Lesson expected = lesson.clone();
        expected.setEnd(new Time("23:59"));
        Lesson editDescriptor = Lesson.getDefaultLesson();
        editDescriptor.setEnd(new Time("23:59"));
        EditLessonCommand editLessonCommand = new EditLessonCommand(null, editDescriptor);
        editLessonCommand.execute(model);
        assertEquals(expected, model.getFilteredScheduleList().get(0));
    }

    @Test
    public void testEditValidSubjectNoIndex() throws ParseException, CommandException {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(),
                getTypicalScheduleList());
        Lesson lesson = model.getFilteredScheduleList().get(0);
        model.showLesson(lesson);
        Lesson expected = lesson.clone();
        expected.setSubject(Subject.of("Physics"));
        Lesson editDescriptor = Lesson.getDefaultLesson();
        editDescriptor.setSubject(Subject.of("Physics"));
        EditLessonCommand editLessonCommand = new EditLessonCommand(null, editDescriptor);
        editLessonCommand.execute(model);
        assertEquals(expected, model.getFilteredScheduleList().get(0));
    }

    @Test
    public void testEditValidDayNoIndex() throws ParseException, CommandException {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(),
                getTypicalScheduleList());
        Lesson lesson = model.getFilteredScheduleList().get(0);
        model.showLesson(lesson);
        Lesson expected = lesson.clone();
        expected.setDay(Day.of("1"));
        Lesson editDescriptor = Lesson.getDefaultLesson();
        editDescriptor.setDay(Day.of("1"));
        EditLessonCommand editLessonCommand = new EditLessonCommand(null, editDescriptor);
        editLessonCommand.execute(model);
        assertEquals(expected, model.getLessonClashWith(expected));
    }

    @Test
    public void testClashingName() throws ParseException, CommandException {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(),
                getTypicalScheduleList());
        Lesson lesson1 = model.getFilteredScheduleList().get(0);
        Lesson lesson2 = model.getFilteredScheduleList().get(1);
        Lesson editDescriptor = Lesson.getDefaultLesson();
        editDescriptor.setName(lesson2.getName());
        EditLessonCommand editLessonCommand = new EditLessonCommand(1, editDescriptor);
        assertThrows(CommandException.class, () -> editLessonCommand.execute(model));
        model.showLesson(lesson1);
        assertThrows(CommandException.class, () -> editLessonCommand.execute(model));
    }

    @Test
    public void testClashingTime() throws ParseException, CommandException {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(),
                getTypicalScheduleList());
        Lesson lesson1 = model.getFilteredScheduleList().get(0);
        Lesson lesson2 = model.getFilteredScheduleList().get(1);
        Lesson editDescriptor = Lesson.getDefaultLesson();
        editDescriptor.setDay(lesson2.getDay());
        editDescriptor.setStart(lesson2.getStart());
        editDescriptor.setEnd(lesson2.getEnd());
        EditLessonCommand editLessonCommand = new EditLessonCommand(1, editDescriptor);
        assertThrows(CommandException.class, () -> editLessonCommand.execute(model));
        model.showLesson(lesson1);
        assertThrows(CommandException.class, () -> editLessonCommand.execute(model));
    }

    @Test
    public void inValidTime() throws ParseException, CommandException {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(),
                getTypicalScheduleList());
        Lesson lesson1 = model.getFilteredScheduleList().get(0);
        Lesson editDescriptor = Lesson.getDefaultLesson();
        editDescriptor.setStart(new Time("23:59"));
        EditLessonCommand editLessonCommand = new EditLessonCommand(1, editDescriptor);
        assertThrows(CommandException.class, () -> editLessonCommand.execute(model));
        model.showLesson(lesson1);
        EditLessonCommand editLessonCommand2 = new EditLessonCommand(1, editDescriptor);
        assertThrows(CommandException.class, () -> editLessonCommand2.execute(model));
        editDescriptor.setStart(Time.DEFAULT_TIME);
        editDescriptor.setEnd(new Time("00:00"));
        assertThrows(CommandException.class, () -> editLessonCommand.execute(model));
        assertThrows(CommandException.class, () -> editLessonCommand2.execute(model));
    }
    @Test
    public void combineTest() throws ParseException, CommandException {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(),
                getTypicalScheduleList());
        Lesson lesson1 = model.getFilteredScheduleList().get(0);
        Lesson editDescriptor = Lesson.getDefaultLesson();
        editDescriptor.setStart(new Time("23:45"));
        editDescriptor.setEnd(new Time("23:55"));
        editDescriptor.setDay(Day.of("1"));
        editDescriptor.setSubject(Subject.of("Physics"));
        Lesson expected = lesson1.clone();
        expected.updateStartAndEnd(new Time("23:45"), new Time("23:55"));
        expected.setDay(Day.of("1"));
        expected.setSubject(Subject.of("Physics"));
        EditLessonCommand editLessonCommand = new EditLessonCommand(1, editDescriptor);
        editLessonCommand.execute(model);
        assertEquals(expected, model.getLessonClashWith(expected));
        model.showLesson(expected);
        editDescriptor.setStart(new Time("23:50"));
        new EditLessonCommand(null, editDescriptor).execute(model);
        expected.setStart(new Time("23:50"));
        assertEquals(expected, model.getLessonClashWith(expected));
    }
    @Test
    public void noEditTest() throws ParseException, CommandException {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(),
                getTypicalScheduleList());
        Lesson lesson = model.getFilteredScheduleList().get(0);
        Lesson editDescriptor = lesson.clone();
        EditLessonCommand editLessonCommand = new EditLessonCommand(1, editDescriptor);
        assertThrows(CommandException.class, () -> editLessonCommand.execute(model));
        model.showLesson(lesson);
        EditLessonCommand editLessonCommand2 = new EditLessonCommand(null, editDescriptor);
        assertThrows(CommandException.class, () -> editLessonCommand2.execute(model));
    }
}
