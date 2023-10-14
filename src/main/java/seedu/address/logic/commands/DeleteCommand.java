package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Name;
import seedu.address.model.person.Student;

/**
 * Deletes a person identified using it's displayed index from the address book.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the person identified by the name of that person.\n"
            + "Parameters: NAME (must be a string)\n"
            + "Example: " + COMMAND_WORD + " Joe";

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Person: %1$s";

    private final Name targetName;

    public DeleteCommand(Name targetName) {
        this.targetName = targetName;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Student> lastShownList = model.getFilteredPersonList();

        Optional<Student> toDelete = lastShownList.stream().filter(
                person -> person.getName().equals(targetName)).findFirst();

        try {
            Student personToDelete = toDelete.get();
            model.deletePerson(personToDelete);
            return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, Messages.format(personToDelete)));
        } catch (NoSuchElementException exception) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_NAME);
        }

    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteCommand)) {
            return false;
        }

        DeleteCommand otherDeleteCommand = (DeleteCommand) other;
        return targetName.equals(otherDeleteCommand.targetName);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetName", targetName)
                .toString();
    }
}
