package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.testutil.TypicalLessons.getTypicalScheduleList;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.lessons.Lesson;
import seedu.address.model.person.Person;
import seedu.address.model.state.State;

class NavigateCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(),
            getTypicalScheduleList());

    @Test
    public void test_navigateStudentToLesson() throws CommandException {
        Person p = model.getFilteredPersonList().get(0);
        Lesson l = model.getFilteredScheduleList().get(0);
        model.showPerson(p);
        model.setState(State.STUDENT);
        model.linkWith(p, l);
        new NavigateCommand().execute(model);
        assertEquals(model.getState(), State.SCHEDULE);
        assertEquals(model.getCurrentlyDisplayedLesson(), l);
        assertEquals(model.getFilteredScheduleList().get(0), l);
        assertEquals(model.getFilteredScheduleList().size(), 1);
    }
    @Test
    public void test_navigateLessonToStudent() throws CommandException {
        Person p = model.getFilteredPersonList().get(0);
        Lesson l = model.getFilteredScheduleList().get(0);
        model.showLesson(l);
        model.setState(State.SCHEDULE);
        model.linkWith(p, l);
        new NavigateCommand().execute(model);
        assertEquals(model.getState(), State.STUDENT);
        assertEquals(model.getCurrentlyDisplayedPerson(), p);
        assertEquals(model.getFilteredPersonList().get(0), p);
        assertEquals(model.getFilteredPersonList().size(), 1);
    }

    @Test
    public void test_noLessonOrStudentShown_noLinkedLessonsOrStudent() throws CommandException {
        Person p = model.getFilteredPersonList().get(0);
        Lesson l = model.getFilteredScheduleList().get(0);
        model.showPerson(p);
        model.setState(State.STUDENT);
        assertThrows(CommandException.class, () -> new NavigateCommand().execute(model));
        model.linkWith(p, l);
        model.resetAllShowFields();
        assertThrows(CommandException.class, () -> new NavigateCommand().execute(model));
        model.setState(State.SCHEDULE);
        assertThrows(CommandException.class, () -> new NavigateCommand().execute(model));
    }

    @Test
    public void test_taskState() throws CommandException {
        Person p = model.getFilteredPersonList().get(0);
        Lesson l = model.getFilteredScheduleList().get(0);
        model.linkWith(p, l);
        model.showLesson(l);
        model.setState(State.TASK);
        assertThrows(CommandException.class, () -> new NavigateCommand().execute(model));
    }

}
