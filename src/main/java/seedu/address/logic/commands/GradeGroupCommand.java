package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GRADE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.List;
import java.util.Set;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Assignment;
import seedu.address.model.person.Group;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;

/**
 * Grades the assignment of the group of student.
 */
public class GradeGroupCommand extends Command {
    public static final String COMMAND_WORD = "gradeGroup";
    public static final String ASSIGNMENT_GRADE_SUCCESS = "Assignment graded successfully!";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Marks the specific assignment of the group identified.\n"
            + "Compulsory Parameters: GROUP "
            + PREFIX_NAME + "ASSIGNMENT_NAME " + PREFIX_GRADE + "SCORE\n "
            + "Example: " + COMMAND_WORD + " tut33 n/assignment1 g/96 ";
    private final Group group;
    private final Name name;
    private final int score;

    /**
     * Constructs a new GradeGroupCommand to grade student's assignment.
     * @param group The group of the student to mark assignment for.
     * @param name The name of the assignment to grade.
     * @param score The score to be given to the assignment.
     */
    public GradeGroupCommand(Group group, Name name, int score) {
        requireNonNull(group);
        requireNonNull(name);
        this.group = group;
        this.name = name;
        this.score = score;
    }

    /**
     * Executes the GradeCommand to grade group's assignment.
     * @param model The model that the command operates on.
     * @return A CommandResult indicating the outcome of the execution.
     * @throws CommandException If there is an error in executing the command.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList().filtered(person -> person.getGroup().equals(group));
        if (lastShownList.isEmpty()) {
            throw new CommandException(Messages.INVALID_GROUP);
        }
        // checks for validity
        for (Person studentToGrade : lastShownList) {
            // From the specific students' assignments, get that specific named assignment
            Assignment studentAssignment = new Assignment(this.name);
            Set<Assignment> studentAssignments = studentToGrade.getAssignments();
            // Throw error if assignment is not found
            if (!studentAssignments.contains(studentAssignment)) {
                throw new CommandException(Messages.MESSAGE_INVALID_ASSIGNMENT_NAME);
            }
        }

        // Get the student to be graded
        for (Person studentToGrade : lastShownList) {
            // From the specific students' assignments, get that specific named assignment
            Assignment studentAssignment = new Assignment(this.name);
            Set<Assignment> studentAssignments = studentToGrade.getAssignments();
            for (Assignment assignment : studentAssignments) {
                if (assignment.equals(studentAssignment)) {
                    if (score > assignment.getMaxScore() || score < 0) {
                        throw new CommandException(Messages.MESSAGE_INVALID_ASSIGNMENT_SCORE);
                    }
                    assignment.setScore(score);
                }
            }
            model.updatePerson(studentToGrade);
        }
        return new CommandResult(String.format(ASSIGNMENT_GRADE_SUCCESS));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof GradeGroupCommand)) {
            return false;
        }

        GradeGroupCommand otherGradeCommand = (GradeGroupCommand) other;
        return this.name.equals(otherGradeCommand.name)
                && this.group.equals(otherGradeCommand.group)
                && this.score == otherGradeCommand.score;
    }
}
