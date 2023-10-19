package seedu.address.logic.commands;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.StatusTypes;

import java.util.List;

import static java.util.Objects.requireNonNull;

/**
 * Sets the status of a person in the address book.
 */

public class SetCommand extends Command {
    public static final String MESSAGE_SET_PERSON_SUCCESS = "SET Person: %1$s";

    public static final String COMMAND_WORD = "set";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sets the status of a person. " + "Parameters: " + "[" + COMMAND_WORD + " <USERID> <STATUS>]...\n" + "Example: " + COMMAND_WORD + " 5 Interviewed";

    public static final String MESSAGE_SUCCESS = "Status set for: %1$s";

    private final StatusTypes newStatus;

    private final Index index;

    /**
     * Creates a SetCommand to set the status of the person at the specified target index.
     *
     * @param index     The index of the person to set the status.
     * @param newStatus The new status to set.
     */

    public SetCommand(Index index, StatusTypes newStatus) {
        requireNonNull(index);
        requireNonNull(newStatus);
        this.index = index;
        this.newStatus = newStatus;
    }


    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());
        personToEdit.getStatus().setStatusType(newStatus);
        model.updateFilteredPersonList(Model.PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_SET_PERSON_SUCCESS, Messages.format(personToEdit)));
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("targetIndex", index).add("newStatus", newStatus).toString();
    }
}


