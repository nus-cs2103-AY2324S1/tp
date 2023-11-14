package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MAX_SCORE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Assignment;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;

/**
 * Marks the attendance of an existing student in the taa.
 */
public class AssignmentCommand extends Command {
    public static final String COMMAND_WORD = "assign";
    public static final String MESSAGE_SUCCESS = "Assignment created successfully!";
    public static final String MESSAGE_FAIL = "Assignment not created.";
    public static final String MESSAGE_USAGE = COMMAND_WORD
        + ": Creates an assignment for all students.\n"
        + "Compulsory Parameters: "
        + PREFIX_NAME + "ASSIGNMENT_NAME " + PREFIX_MAX_SCORE + "MAX_SCORE "
        + "\nExample: " + COMMAND_WORD + " "
        + PREFIX_NAME + "Tutorial1 " + PREFIX_MAX_SCORE + "100";
    private final Name name;
    private final int maxScore;

    /**
     * Constructs an AssignmentCommand with the specified name and maximum score.
     *
     * @param name The name of the assignment. Must not be null.
     * @param maxScore The maximum possible score for the assignment. Must be greater than 0.
     * @throws NullPointerException If the provided name is null.
     */
    public AssignmentCommand(Name name, int maxScore) {
        requireNonNull(name);

        this.name = name;
        this.maxScore = maxScore;
    }

    /**
     * Executes the command to create a new assignment for all students in the last shown list.
     *
     * @param model Model representing the current state of the address book.
     * @return CommandResult representing the result of the execution.
     * @throws CommandException If there is an error during command execution.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (maxScore <= 0 || maxScore > Assignment.MAXIMUM_ALLOWED_MAX_SCORE) {
            throw new CommandException(Assignment.MESSAGE_INVALID_MAX_SCORE);
        }

        // Loop through the list and update each person's assignments
        for (Person studentToEdit : lastShownList) {
            Set<Assignment> updatedAssignments = new HashSet<>();
            Assignment newAssignment = new Assignment(name, maxScore);

            updatedAssignments.addAll(studentToEdit.getAssignments());
            updatedAssignments.remove(newAssignment);
            updatedAssignments.add(newAssignment);

            // Create a new student with the updated assignments
            Person editedStudent = new Person(
                    studentToEdit.getName(), Optional.ofNullable(studentToEdit.getPhone()),
                    Optional.ofNullable(studentToEdit.getEmail()),
                    Optional.ofNullable(studentToEdit.getTelegramHandle()),
                    Optional.ofNullable(studentToEdit.getAttendance()),
                    studentToEdit.getTags(),
                    studentToEdit.getComments(), updatedAssignments, Optional.ofNullable(studentToEdit.getGroup()));

            // Set the updated student in the model
            model.setPerson(studentToEdit, editedStudent);
        }

        model.updateFilteredPersonList(Model.PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_SUCCESS));
    }


    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AssignmentCommand)) {
            return false;
        }

        AssignmentCommand otherAssignmentCommand = (AssignmentCommand) other;
        return this.name.equals(otherAssignmentCommand.name)
            && this.maxScore == otherAssignmentCommand.maxScore;
    }
}
