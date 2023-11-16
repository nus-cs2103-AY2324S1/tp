package seedu.address.logic.commands.delete;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.TabIndex;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.developer.Developer;

/**
 * Deletes a developer identified using it's displayed index from the address book.
 */
public class DeleteDeveloperCommand extends Command {

    public static final String COMMAND_WORD = "delete-developer";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the developer identified by the index number used in the displayed developer list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_DEVELOPER_SUCCESS = "Deleted Developer: %1$s";

    private final Index targetIndex;

    public DeleteDeveloperCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Developer> lastShownList = model.getFilteredDeveloperList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_DEVELOPER_DISPLAYED_INDEX);
        }

        Developer developerToDelete = lastShownList.get(targetIndex.getZeroBased());
        String successMessage = String.format(MESSAGE_DELETE_DEVELOPER_SUCCESS, Messages.format(developerToDelete));
        TabIndex index = TabIndex.Developer;

        model.deleteDeveloper(developerToDelete);
        model.commitAddressBook(model, successMessage, index);
        return new CommandResult(successMessage, index);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteDeveloperCommand)) {
            return false;
        }

        DeleteDeveloperCommand otherDeleteDeveloperCommand = (DeleteDeveloperCommand) other;
        return targetIndex.equals(otherDeleteDeveloperCommand.targetIndex);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetIndex", targetIndex)
                .toString();
    }
}
