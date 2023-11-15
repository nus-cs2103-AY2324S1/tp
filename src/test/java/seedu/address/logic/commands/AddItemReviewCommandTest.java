package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_ITEM;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_STALL;
import static seedu.address.testutil.TypicalItemReviews.ITEM_REVIEW_1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.item.Item;
import seedu.address.model.stall.Stall;
import seedu.address.testutil.ItemBuilder;
import seedu.address.testutil.ItemReviewBuilder;
import seedu.address.testutil.StallBuilder;

public class AddItemReviewCommandTest {
    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        Item itemToReview = new ItemBuilder().build();
        Stall stallWithItem = new StallBuilder().build();
        stallWithItem.addItem(itemToReview);

        Item itemReviewed = new ItemBuilder().withItemReview(ITEM_REVIEW_1).build();
        Stall stallWithItemCopy = new StallBuilder().build();
        stallWithItemCopy.addItem(itemReviewed);

        model = new ModelManager();
        model.addStall(stallWithItem);

        expectedModel = new ModelManager();
        expectedModel.addStall(stallWithItemCopy);
    }

    @Test
    public void constructor_nullStallIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                new AddItemReviewCommand(null, INDEX_FIRST_ITEM, ITEM_REVIEW_1));
    }

    @Test
    public void constructor_nullItemIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                new AddItemReviewCommand(INDEX_FIRST_STALL, null, ITEM_REVIEW_1));
    }

    @Test
    public void constructor_nullItemReview_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                new AddItemReviewCommand(INDEX_FIRST_STALL, INDEX_FIRST_ITEM, null));
    }

    @Test
    public void execute_itemReviewAcceptedByModel_addSuccessful() throws Exception {
        AddItemReviewCommand addItemReviewCommand =
                new AddItemReviewCommand(INDEX_FIRST_STALL, INDEX_FIRST_ITEM, ITEM_REVIEW_1);

        Stall stallWithItem = model.getFilteredStall(INDEX_FIRST_STALL);
        Item itemToReview = stallWithItem.getItem(INDEX_FIRST_ITEM);

        String expectedMessage = String.format(AddItemReviewCommand.MESSAGE_SUCCESS,
                Messages.format(itemToReview),
                stallWithItem.getName());

        assertCommandSuccess(addItemReviewCommand,
                model,
                expectedMessage,
                CommandResult.ViewType.VIEW_ITEM,
                expectedModel);
    }

    @Test
    public void execute_itemReviewAlreadyExists_throwsCommandException() throws Exception {
        AddItemReviewCommand addItemReviewCommand =
                new AddItemReviewCommand(INDEX_FIRST_STALL, INDEX_FIRST_ITEM, ITEM_REVIEW_1);

        assertThrows(CommandException.class, AddItemReviewCommand.MESSAGE_DUPLICATE_ITEM_REVIEW, () ->
                addItemReviewCommand.execute(expectedModel));
    }

    @Test
    public void execute_invalidStallIndex_throwsCommandException() {
        Index invalidStallIndex =
                Index.fromOneBased(model.getFilteredStallList().size() + 1);
        AddItemReviewCommand addItemReviewCommand =
                new AddItemReviewCommand(invalidStallIndex, INDEX_FIRST_ITEM, ITEM_REVIEW_1);

        assertThrows(CommandException.class, Messages.MESSAGE_INVALID_STALL_DISPLAYED_INDEX, () ->
                addItemReviewCommand.execute(model));
    }

    @Test
    public void execute_invalidItemIndex_throwsCommandException() {
        Stall stallWithItem = model.getFilteredStall(INDEX_FIRST_STALL);
        Index invalidItemIndex =
                Index.fromOneBased(stallWithItem.getMenu().numOfItem() + 1);
        AddItemReviewCommand addItemReviewCommand =
                new AddItemReviewCommand(INDEX_FIRST_STALL, invalidItemIndex, ITEM_REVIEW_1);

        assertThrows(CommandException.class, Messages.MESSAGE_INVALID_ITEM_DISPLAYED_INDEX, () ->
                addItemReviewCommand.execute(model));
    }

    @Test
    public void equals() {
        AddItemReviewCommand addItemReviewCommand = new AddItemReviewCommand(
                INDEX_FIRST_STALL,
                INDEX_FIRST_ITEM,
                ITEM_REVIEW_1);

        // same object -> returns true
        assertTrue(addItemReviewCommand.equals(addItemReviewCommand));

        // same values -> returns true
        AddItemReviewCommand addItemReviewCommandCopy = new AddItemReviewCommand(
                INDEX_FIRST_STALL,
                INDEX_FIRST_ITEM,
                ITEM_REVIEW_1);
        assertTrue(addItemReviewCommand.equals(addItemReviewCommandCopy));

        // different types -> returns false
        assertFalse(addItemReviewCommand.equals(1));

        // null -> returns false
        assertFalse(addItemReviewCommand.equals(null));

        // different stall index -> returns false
        AddItemReviewCommand addItemReviewCommandDifferentStallIndex =
                new AddItemReviewCommand(Index.fromZeroBased(2), INDEX_FIRST_ITEM, ITEM_REVIEW_1);
        assertFalse(addItemReviewCommand.equals(addItemReviewCommandDifferentStallIndex));

        // different item index -> returns false
        AddItemReviewCommand addItemReviewCommandDifferentItemIndex =
                new AddItemReviewCommand(INDEX_FIRST_STALL, Index.fromZeroBased(2), ITEM_REVIEW_1);
        assertFalse(addItemReviewCommand.equals(addItemReviewCommandDifferentItemIndex));

        // different item review -> returns false
        AddItemReviewCommand addItemReviewCommandDifferentItemReview =
                new AddItemReviewCommand(
                        INDEX_FIRST_STALL,
                        INDEX_FIRST_ITEM,
                        new ItemReviewBuilder().withDescription("Different review").build());
        assertFalse(addItemReviewCommand.equals(addItemReviewCommandDifferentItemReview));
    }

    @Test
    public void toStringMethod() {
        AddItemReviewCommand addItemReviewCommand =
                new AddItemReviewCommand(INDEX_FIRST_STALL, INDEX_FIRST_ITEM, ITEM_REVIEW_1);
        String expectedString = AddItemReviewCommand.class.getCanonicalName() + "{toAdd=" + ITEM_REVIEW_1 + "}";
        assertEquals(expectedString, addItemReviewCommand.toString());
    }


}
