//@@author tiongjjyi
package seedu.codesphere.logic.commands;

import static seedu.codesphere.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.codesphere.logic.parser.CliSyntax.PREFIX_PENDING_QUESTION;

import java.util.List;

import seedu.codesphere.commons.core.index.Index;
import seedu.codesphere.logic.Messages;
import seedu.codesphere.logic.commands.exceptions.CommandException;
import seedu.codesphere.logic.stagemanager.StageManager;
import seedu.codesphere.model.Model;
import seedu.codesphere.model.course.Course;
import seedu.codesphere.model.student.PendingQuestion;
import seedu.codesphere.model.student.Student;

/**
 * Changes the pending question of an existing student in the address book.
 */
public class PendingQuestionCommand extends Command {

    public static final String COMMAND_WORD = "pq";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds the pending question of the student identified "
            + "by the index number used in the displayed student list. "
            + "Existing pending question will be overwritten by the input.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_PENDING_QUESTION + "[PENDING QUESTION]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_PENDING_QUESTION + "What is the meaning of life?";

    public static final String MESSAGE_ADD_PENDING_QUESTION_SUCCESS = "Added pending question to student: \n%1$s";
    private final Index index;
    private final PendingQuestion pendingQuestion;

    /**
     * @param index of the student in the filtered student list to edit the pending question
     * @param pendingQuestion of the student to be updated to
     */
    public PendingQuestionCommand(Index index, PendingQuestion pendingQuestion) {
        requireAllNonNull(index, pendingQuestion);

        this.index = index;
        this.pendingQuestion = pendingQuestion;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        StageManager stageManager = StageManager.getInstance();
        Course course = stageManager.getSelectedCourse();
        List<Student> lastShownList = course.getFilteredStudentList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
        }

        Student studentToEdit = lastShownList.get(index.getZeroBased());
        Student editedStudent = new Student(studentToEdit.getName(), studentToEdit.getEmail(),
                studentToEdit.getRemark(), pendingQuestion, studentToEdit.getTag());

        course.setStudent(studentToEdit, editedStudent);

        return new CommandResult(String.format(MESSAGE_ADD_PENDING_QUESTION_SUCCESS, Messages.format(editedStudent)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PendingQuestionCommand)) {
            return false;
        }

        PendingQuestionCommand e = (PendingQuestionCommand) other;
        return index.equals(e.index)
                && pendingQuestion.equals(e.pendingQuestion);
    }
}
