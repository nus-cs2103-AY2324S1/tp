package seedu.classmanager.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.classmanager.logic.Messages.MESSAGE_NONEXISTENT_STUDENT_NUMBER;
import static seedu.classmanager.logic.commands.CommandTestUtil.NONEXISTENT_STUDENT_NUMBER;
import static seedu.classmanager.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.classmanager.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Test;

import seedu.classmanager.logic.CommandHistory;
import seedu.classmanager.logic.commands.exceptions.CommandException;
import seedu.classmanager.model.Model;
import seedu.classmanager.model.ModelManager;
import seedu.classmanager.model.UserPrefs;
import seedu.classmanager.model.student.Comment;
import seedu.classmanager.model.student.Student;
import seedu.classmanager.model.student.StudentNumber;
import seedu.classmanager.testutil.StudentBuilder;
import seedu.classmanager.testutil.TypicalStudents;

public class CommentCommandTest {

    private final Model model = new ModelManager(TypicalStudents.getTypicalClassManager(), new UserPrefs());
    private final CommandHistory commandHistory = new CommandHistory();

    @Test
    public void execute_commentSuccess() throws CommandException {

        Comment comment = new Comment("Struggling with tutorials");
        // Define student
        Student studentToComment = new StudentBuilder(TypicalStudents.ALICE)
                .withComment("Struggling with tutorials").build();

        // Create a CommentCommand
        CommentCommand commentCommand = new CommentCommand(studentToComment.getStudentNumber(), comment);

        ModelManager expectedModel = new ModelManager(model.getClassManager(), new UserPrefs());
        expectedModel.setStudent(TypicalStudents.ALICE, studentToComment);
        expectedModel.commitClassManager();

        assertCommandSuccess(commentCommand, model, CommentCommand.MESSAGE_COMMENT_SUCCESS,
                expectedModel, commandHistory);
    }

    @Test
    public void execute_nonexistentStudentNumber_throwsCommandException() {

        Comment comment = new Comment("Struggling with tutorials");
        // Define student
        Student studentToComment = new StudentBuilder(TypicalStudents.ALICE)
                .withComment("Struggling with tutorials").build();

        // Create a CommentCommand
        CommentCommand commentCommand = new CommentCommand(NONEXISTENT_STUDENT_NUMBER, comment);

        ModelManager expectedModel = new ModelManager(model.getClassManager(), new UserPrefs());
        expectedModel.setStudent(TypicalStudents.ALICE, studentToComment);
        expectedModel.commitClassManager();

        assertCommandFailure(commentCommand, model,
                MESSAGE_NONEXISTENT_STUDENT_NUMBER, commandHistory);
    }

    public void equals() {
        StudentNumber studentNumber = new StudentNumber("A0239123A");
        Comment comment = new Comment("Struggling with tutorials");
        CommentCommand commentCommand = new CommentCommand(studentNumber, comment);
        CommentCommand diffCommentCommand = new CommentCommand(studentNumber, new Comment("Different comment"));

        // same object -> returns true
        assertEquals(commentCommand, commentCommand);

        // same values -> returns true
        assertEquals(commentCommand, new CommentCommand(studentNumber, comment));

        // different types -> returns false
        assertEquals(commentCommand, 1);

        // null -> returns false
        assertEquals(commentCommand, null);

        // different student -> returns false
        assertEquals(commentCommand, diffCommentCommand);

        // different comment -> returns false
        assertEquals(commentCommand, new CommentCommand(studentNumber, new Comment("Different comment")));
    }
}
