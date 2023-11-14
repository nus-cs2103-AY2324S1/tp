package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.property.Property;

/**
 * Deletes a property identified using it's displayed index from the address book.
 */
public class DeletePropertyCommand extends Command {

    public static final String COMMAND_WORD = "delprop";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the property identified by the index number used in the displayed property list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_PROPERTY_SUCCESS = "Deleted Property: %1$s";

    private final Index targetIndex;

    public DeletePropertyCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Property> lastShownList = model.getFilteredPropertyList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PROPERTY_DISPLAYED_INDEX);
        }

        Property propertyToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteProperty(propertyToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_PROPERTY_SUCCESS, Messages.format(propertyToDelete)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeletePropertyCommand)) {
            return false;
        }

        DeletePropertyCommand otherDeleteCommand = (DeletePropertyCommand) other;
        return targetIndex.equals(otherDeleteCommand.targetIndex);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetIndex", targetIndex)
                .toString();
    }
}
