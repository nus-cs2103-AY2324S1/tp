package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.ViewItemCommand.MESSAGE_VIEW_ITEM_SUCCESS;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_ITEM;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_STALL;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.item.Item;
import seedu.address.model.stall.Stall;
import seedu.address.testutil.TypicalStalls;

public class ViewItemCommandTest {
    private Model model = new ModelManager(TypicalStalls.getTypicalAddressBookWithMenuAndReview(), new UserPrefs());
    @Test
    public void execute_validItemIndexValidStallIndexUnfilteredList_success() {
        Stall stallToViewFrom = model.getFilteredStallList().get(INDEX_FIRST_STALL.getZeroBased());
        Item itemToView = model.getFilteredStallList()
                .get(INDEX_FIRST_STALL.getZeroBased())
                .getMenu().getItemList()
                .get(INDEX_FIRST_ITEM.getZeroBased());
        ViewItemCommand viewItemCommand = new ViewItemCommand(INDEX_FIRST_STALL, INDEX_FIRST_ITEM);
        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());

        CommandResult expectedMessage = new CommandResult(String.format(MESSAGE_VIEW_ITEM_SUCCESS,
                Messages.format(itemToView), Messages
                .format(stallToViewFrom)), CommandResult.ViewType.VIEW_ITEM);

        assertCommandSuccess(viewItemCommand, model, expectedMessage, expectedModel);
    }


    @Test
    public void execute_invalidItemIndexValidStallUnfilteredList_throwsCommandException() {
        Index validStallIndex = Index.fromOneBased(model.getFilteredStallList().size());
        Index outOfBoundItemIndex = Index.fromOneBased(
                model
                        .getFilteredStallList()
                        .get(0)
                        .getMenu()
                        .getItemList()
                        .size() + 1);
        ViewItemCommand viewItemCommand = new ViewItemCommand(validStallIndex, outOfBoundItemIndex);

        assertCommandFailure(viewItemCommand, model, Messages.MESSAGE_INVALID_ITEM_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validItemIndexInvalidStallIndexUnfilteredList_success() {
        Index invalidStallIndex = Index.fromOneBased(model.getFilteredStallList().size() + 1);
        ViewItemCommand viewItemCommand = new ViewItemCommand(invalidStallIndex, INDEX_FIRST_ITEM);
        assertCommandFailure(viewItemCommand, model, Messages.MESSAGE_INVALID_STALL_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidItemIndexInvalidStallIndexUnfliteredList_throwsCommandException() {
        Index invalidStallIndex = Index.fromOneBased(model.getFilteredStallList().size() + 1);
        Index outOfBoundItemIndex = Index.fromOneBased(
                model
                        .getFilteredStallList()
                        .get(0)
                        .getMenu()
                        .getItemList()
                        .size() + 1);
        ViewItemCommand viewItemCommand = new ViewItemCommand(invalidStallIndex, outOfBoundItemIndex);
        assertCommandFailure(viewItemCommand, model, Messages.MESSAGE_INVALID_STALL_DISPLAYED_INDEX);
    }

}
