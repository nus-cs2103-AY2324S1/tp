package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ITEM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STALL;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.item.Item;
import seedu.address.model.stall.Stall;

/**
 * Deletes an item identified using it's displayed index from FoodNotes.
 */
public class DeleteItemCommand extends Command {

    public static final String COMMAND_WORD = "delete-item";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the item identified by the index number used in the displayed item list.\n"
            + "Parameters: "
            + PREFIX_STALL
            + "STALL_INDEX "
            + PREFIX_ITEM
            + "ITEM_INDEX \n"
            + "Example: "
            + COMMAND_WORD + " "
            + PREFIX_STALL
            + "1 "
            + PREFIX_ITEM
            + "1";

    public static final String MESSAGE_DELETE_ITEM_SUCCESS = "Deleted Item: %1$s from Stall: %2$s";

    private final Index itemIndex;
    private final Index stallIndex;

    /**
     * Creates a DeleteItemCommand to delete the specified {@code Item}
     */
    public DeleteItemCommand(Index stallIndex, Index itemIndex) {
        this.itemIndex = itemIndex;
        this.stallIndex = stallIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Stall> lastShownList = model.getFilteredStallList();

        if (stallIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_STALL_DISPLAYED_INDEX);
        }

        Stall stallToDeleteFrom = lastShownList.get(stallIndex.getZeroBased());
        List<Item> itemList = stallToDeleteFrom.getMenu().getItemList();

        if (itemIndex.getZeroBased() >= itemList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_ITEM_DISPLAYED_INDEX);
        }

        Item itemToDelete = model.getFilteredItem(stallIndex, itemIndex);

        model.showStall(stallToDeleteFrom);
        model.setFilteredStall(stallIndex);
        model.setFilteredItemList(stallIndex);

        model.deleteItem(stallIndex, itemIndex);
        return new CommandResult(String.format(MESSAGE_DELETE_ITEM_SUCCESS, Messages.format(itemToDelete), Messages
                .format(stallToDeleteFrom)), CommandResult.ViewType.STALL_DETAIL);
    }

    /**
     * Returns true if both DeleteItemCommands have the same itemIndex and stallIndex.
     * @param other The other DeleteItemCommand to compare to.
     * @return True if both DeleteItemCommands have the same itemIndex.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteItemCommand)) {
            return false;
        }

        DeleteItemCommand otherDeleteItemCommand = (DeleteItemCommand) other;
        return itemIndex.equals(otherDeleteItemCommand.itemIndex);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("itemIndex", itemIndex)
                .toString();
    }
}
