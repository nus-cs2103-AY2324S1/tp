package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showBuyerAtIndex;
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
import seedu.address.model.displayable.buyer.Buyer;


/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class SetBuyerPriorityCommandTest {

    private static final Priority DEFAULT_PRIORITY = new Priority("nil");
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_firstIndexUnfilteredList_success() {
        Buyer targetBuyer = model.getFilteredBuyerList().get(INDEX_FIRST_PERSON.getZeroBased());
        SetBuyerPriorityCommand setBuyerPriorityCommand = new SetBuyerPriorityCommand(
                INDEX_FIRST_PERSON,
                DEFAULT_PRIORITY
        );

        String expectedMessage = String.format(SetBuyerPriorityCommand.MESSAGE_SUCCESS,
                Messages.format(targetBuyer));

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.setBuyer(targetBuyer, targetBuyer);
        expectedModel.commitAddressBook();

        assertCommandSuccess(setBuyerPriorityCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_lastIndexUnfilteredList_success() {
        ObservableList<Buyer> buyerList = model.getFilteredBuyerList();
        Index lastIndex = Index.fromOneBased(buyerList.size());
        Buyer targetBuyer = buyerList.get(lastIndex.getZeroBased());
        SetBuyerPriorityCommand setBuyerPriorityCommand = new SetBuyerPriorityCommand(
                lastIndex,
                DEFAULT_PRIORITY
        );

        String expectedMessage = String.format(SetBuyerPriorityCommand.MESSAGE_SUCCESS,
                Messages.format(targetBuyer));

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.setBuyer(targetBuyer, targetBuyer);
        expectedModel.commitAddressBook();

        assertCommandSuccess(setBuyerPriorityCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_outOfBoundIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredBuyerList().size() + 1);
        SetBuyerPriorityCommand setBuyerPriorityCommand = new SetBuyerPriorityCommand(
                outOfBoundIndex,
                DEFAULT_PRIORITY
        );

        assertCommandFailure(setBuyerPriorityCommand, model, Messages.MESSAGE_INVALID_BUYER_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showBuyerAtIndex(model, INDEX_FIRST_PERSON);
        Buyer targetBuyer = model.getFilteredBuyerList().get(INDEX_FIRST_PERSON.getZeroBased());
        SetBuyerPriorityCommand setBuyerPriorityCommand = new SetBuyerPriorityCommand(
                INDEX_FIRST_PERSON,
                DEFAULT_PRIORITY
        );
        String expectedMessage = String.format(SetBuyerPriorityCommand.MESSAGE_SUCCESS,
                Messages.format(targetBuyer));

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        showBuyerAtIndex(expectedModel, INDEX_FIRST_PERSON);
        expectedModel.setBuyer(targetBuyer, targetBuyer);
        expectedModel.commitAddressBook();

        assertCommandSuccess(setBuyerPriorityCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showBuyerAtIndex(model, INDEX_FIRST_PERSON);

        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getBuyerList().size());

        SetBuyerPriorityCommand setBuyerPriorityCommand = new SetBuyerPriorityCommand(
                outOfBoundIndex,
                DEFAULT_PRIORITY
        );

        assertCommandFailure(setBuyerPriorityCommand, model, Messages.MESSAGE_INVALID_BUYER_DISPLAYED_INDEX);
    }

    @Test
    public void execute_emptyFilteredList_throwsCommandException() {
        model.updateFilteredBuyerList(x -> false);

        SetBuyerPriorityCommand setBuyerPriorityCommand = new SetBuyerPriorityCommand(
                INDEX_FIRST_PERSON,
                DEFAULT_PRIORITY
        );

        assertCommandFailure(setBuyerPriorityCommand, model, Messages.MESSAGE_INVALID_BUYER_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        SetBuyerPriorityCommand setFirstCommand = new SetBuyerPriorityCommand(INDEX_FIRST_PERSON, DEFAULT_PRIORITY);
        SetBuyerPriorityCommand setSecondCommand = new SetBuyerPriorityCommand(INDEX_SECOND_PERSON, DEFAULT_PRIORITY);

        // same object -> returns true
        assertTrue(setFirstCommand.equals(setFirstCommand));

        // same values -> returns true
        SetBuyerPriorityCommand setFirstCommandCopy = new SetBuyerPriorityCommand(
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
        SetBuyerPriorityCommand setBuyerPriorityCommand = new SetBuyerPriorityCommand(targetIndex, DEFAULT_PRIORITY);
        String expected = SetBuyerPriorityCommand.class.getCanonicalName()
                + "{targetIndex=" + targetIndex
                + ", priority=" + DEFAULT_PRIORITY
                + ", warnings=[]"
                + "}";
        assertEquals(expected, setBuyerPriorityCommand.toString());
    }
}
