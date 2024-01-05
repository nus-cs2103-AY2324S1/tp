package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_SESSIONS;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.exceptions.PersonNotFoundException;
import seedu.address.model.session.Session;
import seedu.address.model.session.SessionStudentsContainsStudentsPredicate;


/**
 * Represents a command for viewing attendance of students.
 */
public class ViewAttendanceCommand extends Command {
    public static final String COMMAND_WORD = "viewattendance";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Views the attendance of a student. "
            + "Parameters: "
            + "[" + PREFIX_NAME + "NAME]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe Alice";

    public static final CommandType COMMAND_TYPE = CommandType.VIEW_ATTENDANCE;

    private final Set<Name> names;

    /**
     * Creates a `ViewAttendanceCommand` to view attendance of student(s) listed.
     *
     * @param names A set of names of the student(s).
     */
    public ViewAttendanceCommand(Set<Name> names) {
        this.names = names;
    }

    /**
     * Executes the `ViewAttendanceCommand` to view the attendance of student(s) in the model.
     *
     * @param model The model to execute the command on.
     * @return A `CommandResult` containing the success message.
     * @throws CommandException If an error occurs during execution.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        Predicate<Session> predicate = PREDICATE_SHOW_ALL_SESSIONS;
        try {
            if (!names.isEmpty()) {
                Set<Person> students = new HashSet<>();
                for (Name name : names) {
                    students.add(model.getMatchingStudentName(name));
                }
                predicate = new SessionStudentsContainsStudentsPredicate(students);
            }
        } catch (PersonNotFoundException e) {
            throw new CommandException(Messages.MESSAGE_PERSON_NOT_FOUND);
        }


        model.updateFilteredSessionList(predicate);

        // Return a success message
        return new CommandResult(
                String.format(Messages.MESSAGE_SESSIONS_LISTED_OVERVIEW, model.getFilteredSessionList().size()),
                CommandType.VIEW_ATTENDANCE);
    }

    @Override
    public CommandType getCommandType() {
        return COMMAND_TYPE;
    }

    /**
     * Checks if this `TakeAttendanceCommand` is equal to another object.
     *
     * @param other The object to compare with.
     * @return `true` if the objects are equal, `false` otherwise.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ViewAttendanceCommand)) {
            return false;
        }

        ViewAttendanceCommand otherViewAttendanceCommand = (ViewAttendanceCommand) other;
        return names.equals(otherViewAttendanceCommand.names);
    }

    /**
     * Returns a string representation of the `ViewAttendanceCommand`.
     *
     * @return A string representation of the command.
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("names", names)
                .toString();
    }
}
