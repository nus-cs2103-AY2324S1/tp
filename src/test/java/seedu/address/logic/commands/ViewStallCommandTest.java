package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showStallAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_STALL;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_STALL;
import static seedu.address.testutil.TypicalStalls.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.stall.Stall;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteStallCommand}.
 */
public class ViewStallCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Stall stallToView = model.getFilteredStallList().get(INDEX_FIRST_STALL.getZeroBased());
        ViewStallCommand viewStallCommand = new ViewStallCommand(INDEX_FIRST_STALL);

        CommandResult expectedMessage = new CommandResult(
                String.format(ViewStallCommand.MESSAGE_VIEW_STALL_SUCCESS,
                Messages.format(stallToView)), CommandResult.ViewType.STALL_DETAIL);
        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.showStall(stallToView);

        assertCommandSuccess(viewStallCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredStallList().size() + 1);
        ViewStallCommand viewStallCommand = new ViewStallCommand(outOfBoundIndex);

        assertCommandFailure(viewStallCommand, model, Messages.MESSAGE_INVALID_STALL_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showStallAtIndex(model, INDEX_FIRST_STALL);

        Stall stallToView = model.getFilteredStallList().get(INDEX_FIRST_STALL.getZeroBased());
        ViewStallCommand viewStallCommand = new ViewStallCommand(INDEX_FIRST_STALL);

        CommandResult expectedMessage = new CommandResult(String.format(
                ViewStallCommand.MESSAGE_VIEW_STALL_SUCCESS,
                Messages.format(stallToView)), CommandResult.ViewType.STALL_DETAIL);

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.showStall(stallToView);

    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showStallAtIndex(model, INDEX_FIRST_STALL);

        Index outOfBoundIndex = INDEX_SECOND_STALL;
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getStallList().size());

        ViewStallCommand viewStallCommand = new ViewStallCommand(outOfBoundIndex);

        assertCommandFailure(viewStallCommand, model, Messages.MESSAGE_INVALID_STALL_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        ViewStallCommand viewFirstCommand = new ViewStallCommand(INDEX_FIRST_STALL);
        ViewStallCommand viewSecondCommand = new ViewStallCommand(INDEX_SECOND_STALL);

        // same object -> returns true
        assertTrue(viewFirstCommand.equals(viewFirstCommand));

        // same values -> returns true
        ViewStallCommand viewFirstCommandCopy = new ViewStallCommand(INDEX_FIRST_STALL);
        assertTrue(viewFirstCommand.equals(viewFirstCommandCopy));

        assertFalse(viewFirstCommand.equals(1));

        assertFalse(viewFirstCommand.equals(null));

        assertFalse(viewFirstCommand.equals(viewSecondCommand));
    }

    @Test
    public void equals_sameObject_true() {
        Index index = Index.fromZeroBased(1);
        ViewStallCommand viewStallCommand = new ViewStallCommand(index);

        assertTrue(viewStallCommand.equals(viewStallCommand));
    }

    @Test
    public void equals_nullObject_false() {
        Index index = Index.fromZeroBased(1);
        ViewStallCommand viewStallCommand = new ViewStallCommand(index);

        assertFalse(viewStallCommand.equals(null));
    }

    @Test
    public void toStringMethod() {
        Index targetIndex = Index.fromOneBased(1);
        ViewStallCommand viewStallCommand = new ViewStallCommand(targetIndex);
        String expected = ViewStallCommand.class.getCanonicalName() + "{stallIndex=" + targetIndex + "}";
        assertEquals(expected, viewStallCommand.toString());
    }

}
