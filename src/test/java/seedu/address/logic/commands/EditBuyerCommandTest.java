package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showBuyerAtIndex;
import static seedu.address.testutil.TypicalAddressBook.getTypicalAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.EditBuyerCommand.EditBuyerDescriptor;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.displayable.buyer.Buyer;
import seedu.address.testutil.BuyerBuilder;
import seedu.address.testutil.EditBuyerDescriptorBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditBuyerCommand.
 */
public class EditBuyerCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Buyer editedBuyer = new BuyerBuilder().build();
        EditBuyerDescriptor descriptor = new EditBuyerDescriptorBuilder(editedBuyer).build();
        EditBuyerCommand editCommand = new EditBuyerCommand(INDEX_FIRST_PERSON, descriptor);

        String expectedMessage = String.format(EditBuyerCommand.MESSAGE_EDIT_BUYER_SUCCESS,
                Messages.format(editedBuyer));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setBuyer(model.getFilteredBuyerList().get(0), editedBuyer);
        expectedModel.commitAddressBook();

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastBuyer = Index.fromOneBased(model.getFilteredBuyerList().size());
        Buyer lastBuyer = model.getFilteredBuyerList().get(indexLastBuyer.getZeroBased());

        BuyerBuilder buyerInList = new BuyerBuilder(lastBuyer);
        Buyer editedBuyer = buyerInList.withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
                .withTags(VALID_TAG_HUSBAND).build();

        EditBuyerDescriptor descriptor = new EditBuyerDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withTags(VALID_TAG_HUSBAND).build();
        EditBuyerCommand editBuyerCommand = new EditBuyerCommand(indexLastBuyer, descriptor);

        String expectedMessage = String.format(EditBuyerCommand.MESSAGE_EDIT_BUYER_SUCCESS,
                Messages.format(editedBuyer));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setBuyer(lastBuyer, editedBuyer);
        expectedModel.commitAddressBook();

        assertCommandSuccess(editBuyerCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditBuyerCommand editBuyerCommand = new EditBuyerCommand(INDEX_FIRST_PERSON, new EditBuyerDescriptor());
        Buyer editedBuyer = model.getFilteredBuyerList().get(INDEX_FIRST_PERSON.getZeroBased());

        String expectedMessage = String.format(EditBuyerCommand.MESSAGE_EDIT_BUYER_SUCCESS,
                Messages.format(editedBuyer));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.commitAddressBook();

        assertCommandSuccess(editBuyerCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showBuyerAtIndex(model, INDEX_FIRST_PERSON);

        Buyer buyerInFilteredList = model.getFilteredBuyerList().get(INDEX_FIRST_PERSON.getZeroBased());
        Buyer editedBuyer = new BuyerBuilder(buyerInFilteredList).withName(VALID_NAME_BOB).build();
        EditBuyerCommand editBuyerCommand = new EditBuyerCommand(INDEX_FIRST_PERSON,
                new EditBuyerDescriptorBuilder().withName(VALID_NAME_BOB).build());

        String expectedMessage = String.format(EditBuyerCommand.MESSAGE_EDIT_BUYER_SUCCESS,
                Messages.format(editedBuyer));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setBuyer(model.getFilteredBuyerList().get(0), editedBuyer);
        expectedModel.commitAddressBook();

        assertCommandSuccess(editBuyerCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicatePersonUnfilteredList_failure() {
        Buyer firstBuyer = model.getFilteredBuyerList().get(INDEX_FIRST_PERSON.getZeroBased());
        EditBuyerDescriptor descriptor = new EditBuyerDescriptorBuilder(firstBuyer).build();
        EditBuyerCommand editBuyerCommand = new EditBuyerCommand(INDEX_SECOND_PERSON, descriptor);

        assertCommandFailure(editBuyerCommand, model, EditBuyerCommand.MESSAGE_DUPLICATE_BUYER);
    }

    @Test
    public void execute_duplicatePersonFilteredList_failure() {
        showBuyerAtIndex(model, INDEX_FIRST_PERSON);

        // edit person in filtered list into a duplicate in address book
        Buyer buyerInList = model.getAddressBook().getBuyerList().get(INDEX_SECOND_PERSON.getZeroBased());
        EditBuyerCommand editBuyerCommand = new EditBuyerCommand(INDEX_FIRST_PERSON,
                new EditBuyerDescriptorBuilder(buyerInList).build());

        assertCommandFailure(editBuyerCommand, model, EditBuyerCommand.MESSAGE_DUPLICATE_BUYER);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredBuyerList().size() + 1);
        EditBuyerDescriptor descriptor = new EditBuyerDescriptorBuilder().withName(VALID_NAME_BOB).build();
        EditBuyerCommand editBuyerCommand = new EditBuyerCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editBuyerCommand, model, Messages.MESSAGE_INVALID_BUYER_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidPersonIndexFilteredList_failure() {
        showBuyerAtIndex(model, INDEX_FIRST_PERSON);
        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getBuyerList().size());

        EditBuyerCommand editBuyerCommand = new EditBuyerCommand(outOfBoundIndex,
                new EditBuyerDescriptorBuilder().withName(VALID_NAME_BOB).build());

        assertCommandFailure(editBuyerCommand, model, Messages.MESSAGE_INVALID_BUYER_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditBuyerCommand standardCommand = new EditBuyerCommand(INDEX_FIRST_PERSON, DESC_AMY);

        // same values -> returns true
        EditBuyerDescriptor copyDescriptor = new EditBuyerDescriptor(DESC_AMY);
        EditBuyerCommand commandWithSameValues = new EditBuyerCommand(INDEX_FIRST_PERSON, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditBuyerCommand(INDEX_SECOND_PERSON, DESC_AMY)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditBuyerCommand(INDEX_FIRST_PERSON, DESC_BOB)));
    }

    @Test
    public void toStringMethod() {
        Index index = Index.fromOneBased(1);
        EditBuyerDescriptor editBuyerDescriptor = new EditBuyerDescriptor();
        EditBuyerCommand editBuyerCommand = new EditBuyerCommand(index, editBuyerDescriptor);
        String expected = EditBuyerCommand.class.getCanonicalName() + "{index=" + index + ", editBuyerDescriptor="
                + editBuyerDescriptor + "}";
        assertEquals(expected, editBuyerCommand.toString());
    }

}
