package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.student.Student;
import seedu.address.model.student.StudentNumber;

/**
 * Selects a student in the address book to view their class details.
 */
public class ViewCommand extends Command {

    public static final String COMMAND_WORD = "view";
    public static final String MESSAGE_USAGE = COMMAND_WORD
        + ": selects a student to view their class details"
        + "Parameters: STUDENT_NUMBER\n"
        + "Example: " + COMMAND_WORD + " A1234567N";
    public static final String MESSAGE_COMMAND_SUCCESS =
        "You are now viewing the class details of Student %1$s";
    public static final String MESSAGE_COMMAND_FAILURE =
        "Please check that the student exist in the address book.";
    protected final StudentNumber studentNumber;

    /**
     * ViewCommand object to execute the user input.
     * @param studentNumber of the the student to be viewed.
     */
    public ViewCommand(StudentNumber studentNumber) {
        requireAllNonNull(studentNumber);
        this.studentNumber = studentNumber;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        if (!model.hasStudent(new Student(studentNumber))) {
            throw new CommandException(MESSAGE_COMMAND_FAILURE);
        }

        Student studentToView = model.getStudent(studentNumber);
        model.setSelectedStudent(studentToView);

        return new CommandResult(String.format(MESSAGE_COMMAND_SUCCESS, studentToView.getName()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ViewCommand)) {
            return false;
        }

        ViewCommand e = (ViewCommand) other;
        return studentNumber.equals(e.studentNumber);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .add("studentNumber", studentNumber)
            .toString();
    }
}
