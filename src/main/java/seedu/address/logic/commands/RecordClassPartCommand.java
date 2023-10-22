package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PARTICIPATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENT_NUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIAL_SESSION;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.student.ClassDetails;
import seedu.address.model.student.Student;
import seedu.address.model.student.StudentNumber;

/**
 * Records the class participation for a student in a specific tutorial session.
 */
public class RecordClassPartCommand extends Command {
    public static final String COMMAND_WORD = "record-part";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Records the class participation for a student in a specific tutorial session.\n"
            + "Parameters: \n"
            + PREFIX_STUDENT_NUMBER + "STUDENT_NUMBER "
            + PREFIX_TUTORIAL_SESSION + "TUTORIAL_SESSION "
            + PREFIX_PARTICIPATION + "PARTICIPATION\n"
            + "Example: \n"
            + COMMAND_WORD + " " + PREFIX_STUDENT_NUMBER + "A0299999X "
            + PREFIX_TUTORIAL_SESSION + "1 " + PREFIX_PARTICIPATION + "true";

    public static final String MESSAGE_SUCCESS = "Recorded participation for student: %1$s, "
            + "here are the details:\n";


    private final StudentNumber studentNumber;
    private final int sessionNumber;
    private final boolean isParticipated;

    /**
     * Creates an RecordPartCommand to record the specified {@code Student}'s participation
     */
    public RecordClassPartCommand(StudentNumber studentNumber, int sessionNumber, boolean isParticipated) {
        this.studentNumber = studentNumber;
        this.sessionNumber = sessionNumber;
        this.isParticipated = isParticipated;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.hasStudent(new Student(studentNumber))) {
            throw new CommandException(Messages.MESSAGE_NONEXISTENT_STUDENT_NUMBER);
        }

        Student studentToGrade = model.getStudent(studentNumber);
        ClassDetails classDetails = studentToGrade.getClassDetails();
        classDetails.recordClassPart(sessionNumber, isParticipated);

        return new CommandResult(String.format(MESSAGE_SUCCESS, studentNumber)
                + classDetails.displayParticipations());
    }


    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof RecordClassPartCommand)) {
            return false;
        }

        RecordClassPartCommand otherSetGradeCommand = (RecordClassPartCommand) other;
        return studentNumber.equals(otherSetGradeCommand.studentNumber)
                && sessionNumber == otherSetGradeCommand.sessionNumber
                && isParticipated == otherSetGradeCommand.isParticipated;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("studentNumber", studentNumber)
                .add("sessionNumber", sessionNumber)
                .add("isParticipated", isParticipated)
                .toString();
    }
}
