package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showSellerAtIndex;
import static seedu.address.testutil.TypicalAddressBook.getTypicalAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.displayable.Priority;
import seedu.address.model.displayable.seller.Seller;


/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class SetSellerPriorityCommandTest {

    private static final Priority DEFAULT_PRIORITY = new Priority("nil");
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Seller targetSeller = model.getFilteredSellerList().get(INDEX_FIRST_PERSON.getZeroBased());
        SetSellerPriorityCommand setSellerPriorityCommand = new SetSellerPriorityCommand(
                INDEX_FIRST_PERSON,
                DEFAULT_PRIORITY
        );

        String expectedMessage = String.format(SetSellerPriorityCommand.MESSAGE_SUCCESS,
                Messages.format(targetSeller));

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.setSeller(targetSeller, targetSeller);
        expectedModel.commitAddressBook();

        assertCommandSuccess(setSellerPriorityCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_lastIndexUnfilteredList_success() {
        ObservableList<Seller> sellerList = model.getFilteredSellerList();
        Index lastIndex = Index.fromOneBased(sellerList.size());
        Seller targetSeller = sellerList.get(lastIndex.getZeroBased());
        SetSellerPriorityCommand setSellerPriorityCommand = new SetSellerPriorityCommand(
                lastIndex,
                DEFAULT_PRIORITY
        );

        String expectedMessage = String.format(SetSellerPriorityCommand.MESSAGE_SUCCESS,
                Messages.format(targetSeller));

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.setSeller(targetSeller, targetSeller);
        expectedModel.commitAddressBook();

        assertCommandSuccess(setSellerPriorityCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredSellerList().size() + 1);
        SetSellerPriorityCommand setSellerPriorityCommand = new SetSellerPriorityCommand(
                outOfBoundIndex,
                DEFAULT_PRIORITY
        );

        assertCommandFailure(setSellerPriorityCommand, model, Messages.MESSAGE_INVALID_SELLER_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showSellerAtIndex(model, INDEX_FIRST_PERSON);
        Seller targetSeller = model.getFilteredSellerList().get(INDEX_FIRST_PERSON.getZeroBased());
        SetSellerPriorityCommand setSellerPriorityCommand = new SetSellerPriorityCommand(
                INDEX_FIRST_PERSON,
                DEFAULT_PRIORITY
        );
        String expectedMessage = String.format(SetSellerPriorityCommand.MESSAGE_SUCCESS,
                Messages.format(targetSeller));

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        showSellerAtIndex(expectedModel, INDEX_FIRST_PERSON);
        expectedModel.setSeller(targetSeller, targetSeller);
        expectedModel.commitAddressBook();

        assertCommandSuccess(setSellerPriorityCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showSellerAtIndex(model, INDEX_FIRST_PERSON);

        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getSellerList().size());

        SetSellerPriorityCommand setSellerPriorityCommand = new SetSellerPriorityCommand(
                outOfBoundIndex,
                DEFAULT_PRIORITY
        );

        assertCommandFailure(setSellerPriorityCommand, model, Messages.MESSAGE_INVALID_SELLER_DISPLAYED_INDEX);
    }

    @Test
    public void execute_emptyFilteredList_throwsCommandException() {
        model.updateFilteredSellerList(x -> false);

        SetSellerPriorityCommand setSellerPriorityCommand = new SetSellerPriorityCommand(
                INDEX_FIRST_PERSON,
                DEFAULT_PRIORITY
        );

        assertCommandFailure(setSellerPriorityCommand, model, Messages.MESSAGE_INVALID_SELLER_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        SetSellerPriorityCommand setFirstCommand = new SetSellerPriorityCommand(INDEX_FIRST_PERSON, DEFAULT_PRIORITY);
        SetSellerPriorityCommand setSecondCommand = new SetSellerPriorityCommand(INDEX_SECOND_PERSON, DEFAULT_PRIORITY);

        // same object -> returns true
        assertTrue(setFirstCommand.equals(setFirstCommand));

        // same values -> returns true
        SetSellerPriorityCommand setFirstCommandCopy = new SetSellerPriorityCommand(
                INDEX_FIRST_PERSON,
                DEFAULT_PRIORITY
        );
        assertTrue(setFirstCommand.equals(setFirstCommandCopy));

        // different types -> returns false
        assertFalse(setFirstCommand.equals(1));

        // null -> returns false
        assertFalse(setFirstCommand.equals(null));

        // different displayable -> returns false
        assertFalse(setFirstCommand.equals(setSecondCommand));
    }

    @Test
    public void toStringMethod() {
        Index targetIndex = Index.fromOneBased(1);
        SetSellerPriorityCommand setSellerPriorityCommand = new SetSellerPriorityCommand(targetIndex, DEFAULT_PRIORITY);
        String expected = SetSellerPriorityCommand.class.getCanonicalName()
                + "{targetIndex=" + targetIndex
                + ", priority=" + DEFAULT_PRIORITY
                + ", warnings=[]"
                + "}";
        assertEquals(expected, setSellerPriorityCommand.toString());
    }
}
