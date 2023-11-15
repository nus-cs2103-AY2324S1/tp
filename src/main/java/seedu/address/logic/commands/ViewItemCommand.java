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
import seedu.address.model.stall.Menu;
import seedu.address.model.stall.Stall;

/**
 * View an item's details identified using it's displayed index from FoodNotes.
 */
public class ViewItemCommand extends Command {

    public static final String COMMAND_WORD = "view-item";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Displays the details of the item identified by the index number used in the displayed item list.\n"
            + "Parameters: "
            + PREFIX_STALL
            + "STALL_INDEX "
            + PREFIX_ITEM
            + "ITEM_INDEX \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_STALL + "1 "
            + PREFIX_ITEM + "1";

    public static final String MESSAGE_VIEW_ITEM_SUCCESS = "Viewing Item: %1$s from Stall: %2$s";
    private final Index itemIndex;
    private final Index stallIndex;

    /**
     * Creates a ViewItemCommand to view the specified {@code Item}
     */
    public ViewItemCommand(Index stallIndex, Index itemIndex) {
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

        Stall stallToViewFrom = lastShownList.get(stallIndex.getZeroBased());
        Menu menu = stallToViewFrom.getMenu();

        if (itemIndex.getZeroBased() >= menu.numOfItem()) {
            throw new CommandException(Messages.MESSAGE_INVALID_ITEM_DISPLAYED_INDEX);
        }

        Item itemToView = model.getFilteredItem(stallIndex, itemIndex);

        model.setFilteredItem(itemToView);
        model.setFilteredItemList(stallIndex);
        model.setFilteredStall(stallIndex);
        return new CommandResult(String.format(MESSAGE_VIEW_ITEM_SUCCESS, Messages.format(itemToView), Messages
                .format(stallToViewFrom)), CommandResult.ViewType.VIEW_ITEM);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ViewItemCommand)) {
            return false;
        }

        ViewItemCommand otherViewItemCommand = (ViewItemCommand) other;
        return itemIndex.equals(otherViewItemCommand.itemIndex)
                && stallIndex.equals(otherViewItemCommand.stallIndex);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("stallIndex", stallIndex)
                .add("itemIndex", itemIndex)
                .toString();
    }
}
