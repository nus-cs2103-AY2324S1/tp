package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SESSION;

import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.StudentSet;
import seedu.address.model.person.exceptions.PersonNotFoundException;
import seedu.address.model.session.Session;
import seedu.address.model.session.SessionNumber;


/**
 * Command to create a session with the specified student(s).
 */
public class CreateSessionCommand extends Command {
    public static final String COMMAND_WORD = "createsession";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Creates a session with the student(s). "
            + "Parameters: "
            + PREFIX_SESSION + "SESSION_NUMBER "
            + "[" + PREFIX_NAME + "NAME]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_SESSION + "3 "
            + PREFIX_NAME + "John Doe "
            + PREFIX_NAME + "Foo Bar";
    public static final String MESSAGE_SUCCESS = "New session added: %1$s";
    public static final String MESSAGE_DUPLICATE_SESSION = "Session list contains duplicate session(s).";

    public static final CommandType COMMAND_TYPE = CommandType.CREATE_SESSION;

    private SessionNumber sessionNumber;
    private Set<Name> names;
    private Session sessionToAdd;

    /**
     * Creates a new CreateSessionCommand with a single student to be added to the session.
     *
     * @param sessionNumber The session number to create.
     * @param name The name of the student to add to the session.
     */
    public CreateSessionCommand(SessionNumber sessionNumber, Name name) {
        requireAllNonNull(sessionNumber, name);

        this.sessionNumber = sessionNumber;
        this.names = new HashSet<>();
        this.names.add(name);
    }

    /**
     * Creates a new CreateSessionCommand with multiple students to be added to the session.
     *
     * @param sessionNumber The session number to create.
     * @param names A set of names of the students to add to the session.
     */
    public CreateSessionCommand(SessionNumber sessionNumber, Set<Name> names) {
        requireAllNonNull(sessionNumber, names);

        this.sessionNumber = sessionNumber;
        this.names = names;
    }

    /**
     * Executes the CreateSessionCommand to create a new session in the model.
     *
     * @param model The model to execute the command on.
     * @return A CommandResult containing the success message.
     * @throws CommandException If an error occurs during execution.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        this.sessionToAdd = new Session(sessionNumber);

        try {
            StudentSet studentsToAdd = new StudentSet();
            for (Name name : names) {
                Person studentToAdd = model.getMatchingStudentName(name);
                studentsToAdd.add(studentToAdd);
            }
            this.sessionToAdd = new Session(sessionNumber, studentsToAdd);
        } catch (PersonNotFoundException e) {
            throw new CommandException(Messages.MESSAGE_PERSON_NOT_FOUND);
        }


        if (model.hasSession(this.sessionToAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_SESSION);
        }
        // Add the session to the model
        model.addSession(this.sessionToAdd);

        // Return a success message
        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.format(this.sessionToAdd)),
                COMMAND_TYPE);
    }

    @Override
    public CommandType getCommandType() {
        return COMMAND_TYPE;
    }

    /**
     * Checks if this CreateSessionCommand is equal to another object.
     *
     * @param other The object to compare with.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CreateSessionCommand)) {
            return false;
        }

        CreateSessionCommand otherCreateSessionCommand = (CreateSessionCommand) other;
        return this.sessionNumber.equals(otherCreateSessionCommand.sessionNumber)
                && this.names.equals(otherCreateSessionCommand.names);
    }

    /**
     * Returns a string representation of the CreateSessionCommand.
     *
     * @return A string representation of the command.
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("toCreate", sessionToAdd)
                .toString();
    }
}
