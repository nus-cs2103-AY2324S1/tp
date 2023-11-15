package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRICE;
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
 * Adds an item to a stall's menu.
 */
public class AddItemCommand extends Command {

    public static final String COMMAND_WORD = "add-item";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an item to a stall's menu. "
        + "Parameters: "
        + PREFIX_STALL + "STALL_INDEX "
        + PREFIX_NAME + "ITEM_NAME "
            + PREFIX_PRICE + "ITEM_PRICE \n"
        + "Example: " + COMMAND_WORD + " "
        + PREFIX_STALL + "1 "
        + PREFIX_NAME + "Chicken Rice "
        + PREFIX_PRICE + "5.50 ";

    public static final String MESSAGE_SUCCESS = "New Item: %1$s successfully added to stall: %2$s";
    public static final String MESSAGE_DUPLICATE_ITEM = "This Item already exists in this Stall";
    private final Item toAdd;
    private final Index stallIndex;

    /**
     * Creates an AddItemCommand to add the specified {@code Item}
     * @param item Item to be added to the menu
     * @param stallIndex Index of the stall to add the item to
     */
    public AddItemCommand(Index stallIndex, Item item) {
        requireNonNull(item);
        this.toAdd = item;
        this.stallIndex = stallIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Stall> lastShownList = model.getFilteredStallList();

        if (stallIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_STALL_DISPLAYED_INDEX);
        }

        Stall stallToUpdate = model.getFilteredStall(stallIndex);

        if (model.hasItem(stallToUpdate, toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_ITEM);
        }

        model.addItem(stallIndex, toAdd);

        model.setFilteredItem(toAdd);
        model.setFilteredItemList(stallIndex);
        model.setFilteredStall(stallIndex);
        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.format(toAdd), stallToUpdate.getName()),
                CommandResult.ViewType.VIEW_ITEM);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddItemCommand)) {
            return false;
        }

        AddItemCommand otherAddItemCommand = (AddItemCommand) other;
        return toAdd.equals(otherAddItemCommand.toAdd);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
          .add("toAdd", toAdd)
          .toString();
    }
}
