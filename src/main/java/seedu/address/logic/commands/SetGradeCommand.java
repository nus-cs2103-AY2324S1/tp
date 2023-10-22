package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ASSIGNMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GRADE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENT_NUMBER;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.student.ClassDetails;
import seedu.address.model.student.Student;
import seedu.address.model.student.StudentNumber;

/**
 * Sets a specific assignment grade for a student.
 */
public class SetGradeCommand extends Command {
    public static final String COMMAND_WORD = "set-grade";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Set a specific assignment grade of for a student.\n"
            + "Parameters: \n"
            + PREFIX_STUDENT_NUMBER + "STUDENT_NUMBER "
            + PREFIX_ASSIGNMENT + "ASSIGNMENT_NUMBER "
            + PREFIX_GRADE + "GRADE\n"
            + "Example: \n"
            + COMMAND_WORD + " " + PREFIX_STUDENT_NUMBER + "A0299999X "
            + PREFIX_ASSIGNMENT + "1 " + PREFIX_GRADE + "100";

    public static final String MESSAGE_SUCCESS = "Grade set for student: %1$s, "
            + "here are the details:\n";


    private final StudentNumber studentNumber;
    private final int assignmentNumber;
    private final int grade;

    /**
     * Creates an SetGradeCommand to set the specified {@code Student}'s grade
     */
    public SetGradeCommand(StudentNumber studentNumber, int assignmentNumber, int grade) {
        this.studentNumber = studentNumber;
        this.assignmentNumber = assignmentNumber;
        this.grade = grade;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.hasStudent(new Student(studentNumber))) {
            throw new CommandException(Messages.MESSAGE_NONEXISTENT_STUDENT_NUMBER);
        }

        Student studentToGrade = model.getStudent(studentNumber);
        ClassDetails classDetails = studentToGrade.getClassDetails();
        classDetails.setAssignGrade(assignmentNumber, grade);

        return new CommandResult(String.format(MESSAGE_SUCCESS, studentNumber)
                + classDetails.displayAssignments());
    }


    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof SetGradeCommand)) {
            return false;
        }

        SetGradeCommand otherSetGradeCommand = (SetGradeCommand) other;
        return studentNumber.equals(otherSetGradeCommand.studentNumber)
                && assignmentNumber == otherSetGradeCommand.assignmentNumber
                && grade == otherSetGradeCommand.grade;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("studentNumber", studentNumber)
                .add("assignmentNumber", assignmentNumber)
                .add("grade", grade)
                .toString();
    }
}
