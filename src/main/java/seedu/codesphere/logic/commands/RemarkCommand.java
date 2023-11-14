package seedu.codesphere.logic.commands;

import static seedu.codesphere.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.codesphere.logic.parser.CliSyntax.PREFIX_REMARK;

import java.util.List;

import seedu.codesphere.commons.core.index.Index;
import seedu.codesphere.logic.Messages;
import seedu.codesphere.logic.commands.exceptions.CommandException;
import seedu.codesphere.logic.stagemanager.StageManager;
import seedu.codesphere.model.Model;
import seedu.codesphere.model.course.Course;
import seedu.codesphere.model.student.Remark;
import seedu.codesphere.model.student.Student;

/**
 * Changes the remark of an existing student in the student list.
 */
public class RemarkCommand extends Command {

    public static final String COMMAND_WORD = "remark";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the remark of the student identified "
            + "by the index number used in the displayed student list. "
            + "Existing remark will be overwritten by the input.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_REMARK + "[REMARK]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_REMARK + "Likes to swim.";

    public static final String MESSAGE_ADD_REMARK_SUCCESS = "Added remark to student: \n%1$s";

    private final Index index;
    private final Remark remark;

    /**
     * @param index of the student in the filtered student list to edit the remark.
     * @param remark of the student to be updated to.
     */
    public RemarkCommand(Index index, Remark remark) {
        requireAllNonNull(index, remark);

        this.index = index;
        this.remark = remark;
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
                remark, studentToEdit.getPendingQuestion(), studentToEdit.getTag());

        course.setStudent(studentToEdit, editedStudent);

        return new CommandResult(String.format(MESSAGE_ADD_REMARK_SUCCESS, Messages.format(editedStudent)));
    }


    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof RemarkCommand)) {
            return false;
        }

        // state check
        RemarkCommand e = (RemarkCommand) other;
        return index.equals(e.index)
                && remark.equals(e.remark);
    }
}
