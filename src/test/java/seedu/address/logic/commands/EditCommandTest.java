package seedu.address.logic.commands;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static seedu.address.testutil.TypicalLessons.getTypicalScheduleList;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalTasks.getTypicalFullTaskList;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.lessons.Lesson;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;

public class EditCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(),
            getTypicalScheduleList(), getTypicalFullTaskList());

    @Test
    void happyCases() {
        try {
            Person person = Person.getDefaultPerson();
            person.setName(new Name("test name 1 no collision"));
            assertFalse(model.hasPerson(person));
            new EditPersonCommand(1, person).execute(model);
            assertTrue(model.hasPerson(person));
            Lesson lesson = Lesson.getDefaultLesson();
            lesson.setName(new Name("test name 1 no collision"));
            assertFalse(model.hasLessonClashWith(lesson));
            new EditLessonCommand(1, lesson).execute(model);
            assertTrue(model.getFilteredScheduleList().get(0).getName().equals(lesson.getName()));
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void noNameCollisionAndNoDetectableModificationTest() {
        try {
            Person person = Person.getDefaultPerson();
            person.setName(new Name("test name 1 no collision"));
            assertFalse(model.hasPerson(person));
            new EditPersonCommand(1, person).execute(model);
            assertTrue(model.hasPerson(person));
            assertThrows(CommandException.class, () -> new EditPersonCommand(1, person).execute(model));
            assertThrows(CommandException.class, () -> new EditPersonCommand(2, person).execute(model));
            Lesson lesson = Lesson.getDefaultLesson();
            lesson.setName(new Name("test name 1 no collision"));
            assertFalse(model.hasLessonClashWith(lesson));
            new EditLessonCommand(1, lesson).execute(model);
            assertTrue(model.getFilteredScheduleList().get(0).getName().equals(lesson.getName()));
            assertThrows(CommandException.class, () -> new EditLessonCommand(1, lesson).execute(model));
            Lesson lesson1 = model.getFilteredScheduleList().get(1);
            lesson.setStart(lesson1.getStart());
            lesson.setEnd(lesson1.getEnd());
            lesson.setDay(lesson1.getDay());
            assertThrows(CommandException.class, () -> new EditLessonCommand(1, lesson).execute(model));
        } catch (Exception e) {
            fail();
        }
    }
}

