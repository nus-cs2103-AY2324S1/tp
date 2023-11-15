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
import seedu.address.testutil.StallBuilder;

public class DeleteItemReviewCommandTest {
    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        Item itemToDeleteReview = new ItemBuilder().withItemReview(ITEM_REVIEW_1).build();
        Stall stallWithItem = new StallBuilder().build();
        stallWithItem.addItem(itemToDeleteReview);

        Item itemReviewDeleted = new ItemBuilder().build();
        Stall stallWithItemCopy = new StallBuilder().build();
        stallWithItemCopy.addItem(itemReviewDeleted);

        model = new ModelManager();
        model.addStall(stallWithItem);

        expectedModel = new ModelManager();
        expectedModel.addStall(stallWithItemCopy);
    }

    @Test
    public void constructor_nullStallIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                new DeleteItemReviewCommand(null, INDEX_FIRST_ITEM));
    }

    @Test
    public void constructor_nullItemIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                new DeleteItemReviewCommand(INDEX_FIRST_ITEM, null));
    }

    @Test
    public void execute_itemReviewDeletedByModel_deleteSuccessful() throws Exception {
        DeleteItemReviewCommand deleteItemReviewCommand = new DeleteItemReviewCommand(INDEX_FIRST_ITEM,
                INDEX_FIRST_ITEM);
        Stall stallWithItem = model.getFilteredStall(INDEX_FIRST_STALL);
        Item itemToDeleteReview = stallWithItem.getItem(INDEX_FIRST_ITEM);

        String expectedMessage = String.format(DeleteItemReviewCommand.MESSAGE_SUCCESS,
                itemToDeleteReview.getName(),
                Messages.format(stallWithItem));

        assertCommandSuccess(deleteItemReviewCommand,
                model,
                expectedMessage,
                CommandResult.ViewType.VIEW_ITEM,
                expectedModel);
    }

    @Test
    public void execute_itemReviewNotFound_throwsCommandException() {
        DeleteItemReviewCommand deleteItemReviewCommand = new DeleteItemReviewCommand(INDEX_FIRST_ITEM,
                INDEX_FIRST_ITEM);
        assertThrows(CommandException.class, DeleteItemReviewCommand.MESSAGE_ITEM_REVIEW_NOT_FOUND, () ->
                        deleteItemReviewCommand.execute(expectedModel));
    }

    @Test
    public void execute_invalidStallIndex_throwsCommandException() {
        Index invalidStallIndex = Index.fromZeroBased(model.getFilteredStallList().size() + 1);
        DeleteItemReviewCommand deleteItemReviewCommand = new DeleteItemReviewCommand(invalidStallIndex,
                INDEX_FIRST_ITEM);
        assertThrows(CommandException.class, Messages.MESSAGE_INVALID_STALL_DISPLAYED_INDEX, () ->
                        deleteItemReviewCommand.execute(model));
    }

    @Test
    public void execute_invalidItemIndex_throwsCommandException() {
        Stall stallWithItem = model.getFilteredStall(INDEX_FIRST_STALL);
        Index invalidItemIndex = Index.fromZeroBased(stallWithItem.getMenu().numOfItem() + 1);
        DeleteItemReviewCommand deleteItemReviewCommand = new DeleteItemReviewCommand(INDEX_FIRST_ITEM,
                invalidItemIndex);
        assertThrows(CommandException.class, Messages.MESSAGE_INVALID_ITEM_DISPLAYED_INDEX, () ->
                deleteItemReviewCommand.execute(model));
    }

    @Test
    public void equals() {
        DeleteItemReviewCommand deleteItemReviewCommand =
                new DeleteItemReviewCommand(INDEX_FIRST_ITEM, INDEX_FIRST_ITEM);

        // same object -> returns true
        assertTrue(deleteItemReviewCommand.equals(deleteItemReviewCommand));

        // same values -> returns true
        DeleteItemReviewCommand deleteItemReviewCommandCopy =
                new DeleteItemReviewCommand(INDEX_FIRST_ITEM, INDEX_FIRST_ITEM);
        assertTrue(deleteItemReviewCommand.equals(deleteItemReviewCommandCopy));

        // different types -> returns false
        assertFalse(deleteItemReviewCommand.equals(1));

        // null -> returns false
        assertFalse(deleteItemReviewCommand.equals(null));

        // different stall index -> returns false
        DeleteItemReviewCommand deleteItemReviewCommandDifferentStallIndex =
                new DeleteItemReviewCommand(Index.fromZeroBased(2), INDEX_FIRST_ITEM);
        assertFalse(deleteItemReviewCommand.equals(deleteItemReviewCommandDifferentStallIndex));

        // different item index -> returns false
        DeleteItemReviewCommand deleteItemReviewCommandDifferentItemIndex =
                new DeleteItemReviewCommand(INDEX_FIRST_STALL, Index.fromZeroBased(2));
        assertFalse(deleteItemReviewCommand.equals(deleteItemReviewCommandDifferentItemIndex));
    }

    @Test
    public void toStringTest() {
        DeleteItemReviewCommand deleteItemReviewCommand =
                new DeleteItemReviewCommand(INDEX_FIRST_ITEM, INDEX_FIRST_ITEM);
        String expectedString = DeleteItemReviewCommand.class.getCanonicalName() + "{stallIndex=" + INDEX_FIRST_ITEM
                + ", itemIndex=" + INDEX_FIRST_ITEM + "}";
        assertEquals(deleteItemReviewCommand.toString(), expectedString);
    }



}
