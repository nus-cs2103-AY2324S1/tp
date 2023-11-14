package seedu.address.logic.commands.contact;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.contact.Contact;

/**
 * Deletes a contact identified using it's displayed index from the JobFestGo.
 */
public class DeleteContactCommand extends Command {
    public static final String COMMAND_WORD = "delete_contact";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the contact identified by the index number used in the displayed contact list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_CONTACT_SUCCESS = "Deleted Contact: %1$s";

    private final Index targetIndex;

    public DeleteContactCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Contact> lastShownList = model.getFilteredContactList();

        if (model.isOnEventsScreen() || model.isOnTagsScreen()) {
            throw new CommandException(Messages.MESSAGE_INVALID_CONTACT_NOT_DISPLAYED);
        }

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_CONTACT_DISPLAYED_INDEX);
        }

        Contact contactToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteContact(contactToDelete);

        // Update model to depict which screen it is on currently.
        model.switchToContactsScreen(false);
        model.switchToEventsScreen(false);
        model.switchToTagsScreen(false);

        // Update the respective filtered lists to show the correct lists.
        // The flow of the command should be returning to home page after deleting a contact.
        model.updateFilteredContactList(Model.PREDICATE_SHOW_ALL_CONTACTS);
        model.updateFilteredTaskList(Model.PREDICATE_SHOW_ALL_TASKS);

        return new CommandResult(String.format(MESSAGE_DELETE_CONTACT_SUCCESS, Messages.format(contactToDelete)));

    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteContactCommand)) {
            return false;
        }

        DeleteContactCommand otherDeleteCommand = (DeleteContactCommand) other;
        return targetIndex.equals(otherDeleteCommand.targetIndex);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetIndex", targetIndex)
                .toString();
    }
}
