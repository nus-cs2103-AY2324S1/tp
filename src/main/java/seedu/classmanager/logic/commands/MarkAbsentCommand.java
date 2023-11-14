package seedu.classmanager.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.classmanager.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.classmanager.logic.parser.CliSyntax.PREFIX_STUDENT_NUMBER;
import static seedu.classmanager.logic.parser.CliSyntax.PREFIX_TUTORIAL_INDEX;

import seedu.classmanager.commons.core.index.Index;
import seedu.classmanager.logic.CommandHistory;
import seedu.classmanager.logic.Messages;
import seedu.classmanager.logic.commands.exceptions.CommandException;
import seedu.classmanager.model.Model;
import seedu.classmanager.model.student.Student;
import seedu.classmanager.model.student.StudentNumber;

/**
 * Marks a student's attendance.
 */
public class MarkAbsentCommand extends Command {
    public static final String COMMAND_WORD = "absent";
    public static final String MESSAGE_MARK_SUCCESS = "Successfully marked the student's attendance as absent.";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Marks a student's attendance as absent.\n"
            + "Parameters: "
            + PREFIX_STUDENT_NUMBER + "STUDENT_NUMBER "
            + PREFIX_TUTORIAL_INDEX + "TUTORIAL_INDEX\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_STUDENT_NUMBER + "A0245234A "
            + PREFIX_TUTORIAL_INDEX + "1";
    private final Index index;
    private final StudentNumber targetStudentNumber;

    /**
     * Constructs a MarkAbsentCommand object.
     *
     * @param index of the class.
     * @param targetStudentNumber of the student.
     */
    public MarkAbsentCommand(Index index, StudentNumber targetStudentNumber) {
        requireAllNonNull(index, targetStudentNumber);

        this.index = index;
        this.targetStudentNumber = targetStudentNumber;
    }

    /**
     * Marks a specified student's attendance as absent.
     * @param model {@code Model} which the command should operate on.
     * @param commandHistory The command history to record this command.
     * @return A {@code CommandResult} with the feedback message of the operation result.
     * @throws CommandException If an error occurs during command execution.
     */
    @Override
    public CommandResult execute(Model model, CommandHistory commandHistory)
            throws CommandException {
        requireNonNull(model);

        if (!model.hasStudent(new Student(targetStudentNumber))) {
            throw new CommandException(Messages.MESSAGE_NONEXISTENT_STUDENT_NUMBER);
        }

        Student studentToMark = model.getStudent(targetStudentNumber);
        Student markedStudent = studentToMark.copy();

        markedStudent.markAbsent(this.index);
        model.setStudent(studentToMark, markedStudent);
        if (model.isSelectedStudent(studentToMark)) {
            model.setSelectedStudent(markedStudent);
        }
        model.commitClassManager();

        return new CommandResult(MESSAGE_MARK_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof MarkAbsentCommand)) {
            return false;
        }

        MarkAbsentCommand e = (MarkAbsentCommand) other;
        return index.equals(e.index)
                && targetStudentNumber.equals(e.targetStudentNumber);
    }
}
