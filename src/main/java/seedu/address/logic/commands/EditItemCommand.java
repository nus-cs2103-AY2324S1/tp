package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ITEM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRICE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RATING;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STALL;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.item.Item;
import seedu.address.model.item.ItemName;
import seedu.address.model.item.Price;
import seedu.address.model.item.review.ItemReview;
import seedu.address.model.review.Description;
import seedu.address.model.review.Rating;
import seedu.address.model.stall.Stall;





/**
 * Edits the details of an existing item in FoodNotes.
 */
public class EditItemCommand extends Command {

    public static final String COMMAND_WORD = "edit-item";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the item identified "
            + "by the index number used in the displayed item list.\n"
            + "Existing values will be overwritten by the input values.\n"
            + "Item must have a review before rating and description can be edited.\n"
            + "Parameters: "
            + PREFIX_STALL + "STALL_INDEX "
            + PREFIX_ITEM + "ITEM_INDEX "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_PRICE + "PRICE] "
            + "[" + PREFIX_RATING + "RATING] "
            + "[" + PREFIX_DESCRIPTION + "DESCRIPTION] \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_STALL + "1 "
            + PREFIX_ITEM + "1 "
            + PREFIX_NAME + "Chicken Rice ";


    public static final String MESSAGE_EDIT_ITEM_SUCCESS = "Edited Item: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_ITEM = "This item already exists in the menu.";
    public static final String MESSAGE_NO_REVIEW_FOUND = "This item has no review to edit.";

    private final Index stallIndex;
    private final Index itemIndex;
    private final EditItemDescriptor editItemDescriptor;
    private final Logger logger = LogsCenter.getLogger(getClass());

    /**
     * @param stallIndex of the item in the filtered item list to edit
     * @param itemIndex of the item in the filtered item list to edit
     * @param editItemDescriptor details to edit the item with
     */
    public EditItemCommand(Index stallIndex, Index itemIndex, EditItemDescriptor editItemDescriptor) {
        requireNonNull(stallIndex);
        requireNonNull(itemIndex);
        requireNonNull(editItemDescriptor);

        this.stallIndex = stallIndex;
        this.itemIndex = itemIndex;
        this.editItemDescriptor = new EditItemDescriptor(editItemDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        logger.entering(getClass().getName(), "execute");

        List<Stall> lastShownList = model.getFilteredStallList();

        if (stallIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_STALL_DISPLAYED_INDEX);
        }

        Stall stallToEdit = lastShownList.get(stallIndex.getZeroBased());

        List<Item> itemList = stallToEdit.getMenu().getItemList();

        if (itemIndex.getZeroBased() >= itemList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_ITEM_DISPLAYED_INDEX);
        }

        Item itemToEdit = model.getFilteredItem(stallIndex, itemIndex);

        // only allow editing of review if item has a review
        if (!itemToEdit.hasItemReview() && editItemDescriptor.isReviewEdited()) {
            logger.warning("Attempted to edit a review that doesn't exist.");
            throw new CommandException(MESSAGE_NO_REVIEW_FOUND);
        }

        Item editedItem = createEditedItem(itemToEdit, editItemDescriptor);

        if (!itemToEdit.isSameItem(editedItem) && model.hasItem(stallToEdit, editedItem)) {
            logger.warning("Attempted to add a duplicate item.");
            throw new CommandException(MESSAGE_DUPLICATE_ITEM);
        }

        model.setItem(stallIndex, itemIndex, editedItem);

        model.setFilteredItem(editedItem);
        model.setFilteredItemList(stallIndex);
        model.setFilteredStall(stallIndex);

        logger.exiting(getClass().getName(), "execute");
        return new CommandResult(String.format(MESSAGE_EDIT_ITEM_SUCCESS, Messages.format(editedItem)),
                CommandResult.ViewType.VIEW_ITEM);
    }

    /**
     * Creates and returns a {@code Item} with the details of {@code itemToEdit}
     * edited with {@code editItemDescriptor}.
     * @param itemToEdit item to edit
     * @param editItemDescriptor details to edit the item with
     * @return Item with details edited
     */
    private static Item createEditedItem(Item itemToEdit, EditItemDescriptor editItemDescriptor) {
        assert itemToEdit != null : "itemToEdit should not be null at this point";

        ItemName updatedItemName = editItemDescriptor.getItemName().orElse(itemToEdit.getName());
        Price updatedPrice = editItemDescriptor.getPrice().orElse(itemToEdit.getPrice());

        // guard clause to prevent null pointer exception
        if (itemToEdit.getItemReview() == null) {
            Item editedItem = new Item(updatedItemName, updatedPrice);
            return editedItem;
        }
        Rating updatedRating = editItemDescriptor.getRating().orElse(itemToEdit.getItemReview().getRating());
        Description updatedDescription = editItemDescriptor.getDescription()
                .orElse(itemToEdit.getItemReview().getDescription());
        ItemReview updatedItemReview = new ItemReview(updatedRating, updatedDescription);
        Item editedItem = new Item(updatedItemName, updatedPrice, updatedItemReview);
        return editedItem;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditItemCommand)) {
            return false;
        }

        EditItemCommand otherEditItemCommand = (EditItemCommand) other;
        return stallIndex.equals(otherEditItemCommand.stallIndex)
                && itemIndex.equals(otherEditItemCommand.itemIndex)
                && editItemDescriptor.equals(otherEditItemCommand.editItemDescriptor);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("stallIndex", stallIndex)
                .add("itemIndex", itemIndex)
                .add("editItemDescriptor", editItemDescriptor)
                .toString();
    }

    /**
     * Stores the details to edit the item with. Each non-empty field value will replace the
     * corresponding field value of the item.
     */
    public static class EditItemDescriptor {
        private ItemName name;
        private Price price;
        private Rating rating;
        private Description description;
        public EditItemDescriptor() {}

        /**
         * Copy constructor.
         */
        public EditItemDescriptor(EditItemDescriptor toCopy) {
            setItemName(toCopy.name);
            setPrice(toCopy.price);
            setRating(toCopy.rating);
            setDescription(toCopy.description);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, price, rating, description);
        }

        /**
         * Returns true if item review is edited.
         */
        public boolean isReviewEdited() {
            return CollectionUtil.isAnyNonNull(rating, description);
        }

        /**
         * Sets {@code name} to this object's {@code name}.
         * @param name
         */
        public void setItemName(ItemName name) {
            this.name = name;
        }

        /**
         * Returns an optional of {@code name} if it is not null, else returns an empty optional.
         * @return Optional of {@code name} if it is not null, else returns an empty optional.
         */
        public Optional<ItemName> getItemName() {
            return Optional.ofNullable(name);
        }

        /**
         * Sets {@code price} to this object's {@code price}.
         * @param price
         */
        public void setPrice(Price price) {
            this.price = price;
        }

        /**
         * Returns an optional of {@code price} if it is not null, else returns an empty optional.
         * @return Optional of {@code price} if it is not null, else returns an empty optional.
         */
        public Optional<Price> getPrice() {
            return Optional.ofNullable(price);
        }

        /**
         * Sets {@code rating} to this object's {@code rating}.
         * @param rating
         */
        public void setRating(Rating rating) {
            this.rating = rating;
        }

        /**
         * Returns an optional of {@code rating} if it is not null, else returns an empty optional.
         * @return Optional of {@code rating} if it is not null, else returns an empty optional.
         */
        public Optional<Rating> getRating() {
            return Optional.ofNullable(rating);
        }

        /**
         * Sets {@code description} to this object's {@code description}.
         * @param description
         */
        public void setDescription(Description description) {
            this.description = description;
        }

        /**
         * Returns an optional of {@code description} if it is not null, else returns an empty optional.
         * @return Optional of {@code description} if it is not null, else returns an empty optional.
         */
        public Optional<Description> getDescription() {
            return Optional.ofNullable(description);
        }

        /**
         * Returns true if both edit item descriptors have the same fields.
         * This defines a stronger notion of equality between two edit item descriptors.
         * @param other
         * @return True if both edit item descriptors have the same fields.
         */
        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditItemDescriptor)) {
                return false;
            }

            EditItemDescriptor otherEditItemDescriptor = (EditItemDescriptor) other;
            return Objects.equals(name, otherEditItemDescriptor.name)
                    && Objects.equals(price, otherEditItemDescriptor.price)
                    && Objects.equals(rating, otherEditItemDescriptor.rating)
                    && Objects.equals(description, otherEditItemDescriptor.description);
        }

        /**
         * Returns the string representation of this object.
         * @return String representation of this object.
         */
        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .add("name", name)
                    .add("price", price)
                    .add("rating", rating)
                    .add("description", description)
                    .toString();
        }
    }
}
