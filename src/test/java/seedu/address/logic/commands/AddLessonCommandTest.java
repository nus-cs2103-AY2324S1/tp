package seedu.address.logic.commands;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.lessons.Lesson;



class AddLessonCommandTest {

    @Test
    void executeTest() {
        try {
            AddLessonCommand addLessonCommand = new AddLessonCommand(null);
            fail();
        } catch (Exception e) {
            assertTrue(e instanceof NullPointerException);
        }
        Lesson lesson = new Lesson(LocalDateTime.now(), LocalDateTime.now(), "test");
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
        } catch (CommandException e) {
            fail();
        }
    }

}
