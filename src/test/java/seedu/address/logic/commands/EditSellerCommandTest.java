package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_SAMY;
import static seedu.address.logic.commands.CommandTestUtil.DESC_SBOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showSellerAtIndex;
import static seedu.address.testutil.TypicalAddressBook.getTypicalAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.EditSellerCommand.EditSellerDescriptor;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.displayable.seller.Seller;
import seedu.address.testutil.EditSellerDescriptorBuilder;
import seedu.address.testutil.SellerBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditSellerCommand.
 */
public class EditSellerCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Seller editedSeller = new SellerBuilder().build();
        EditSellerDescriptor descriptor = new EditSellerDescriptorBuilder(editedSeller).build();
        EditSellerCommand editSellerCommand = new EditSellerCommand(INDEX_FIRST_PERSON, descriptor);

        String expectedMessage = String.format(EditSellerCommand.MESSAGE_EDIT_SELLER_SUCCESS,
                Messages.format(editedSeller));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setSeller(model.getFilteredSellerList().get(0), editedSeller);
        expectedModel.commitAddressBook();

        assertCommandSuccess(editSellerCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastSeller = Index.fromOneBased(model.getFilteredSellerList().size());
        Seller lastSeller = model.getFilteredSellerList().get(indexLastSeller.getZeroBased());

        SellerBuilder sellerInList = new SellerBuilder(lastSeller);
        Seller editedSeller = sellerInList.withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
                .withTags(VALID_TAG_HUSBAND).build();

        EditSellerDescriptor descriptor = new EditSellerDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withTags(VALID_TAG_HUSBAND).build();
        EditSellerCommand editSellerCommand = new EditSellerCommand(indexLastSeller, descriptor);

        String expectedMessage = String.format(EditSellerCommand.MESSAGE_EDIT_SELLER_SUCCESS,
                Messages.format(editedSeller));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setSeller(lastSeller, editedSeller);
        expectedModel.commitAddressBook();

        assertCommandSuccess(editSellerCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditSellerCommand editSellerCommand = new EditSellerCommand(INDEX_FIRST_PERSON, new EditSellerDescriptor());
        Seller editedSeller = model.getFilteredSellerList().get(INDEX_FIRST_PERSON.getZeroBased());

        String expectedMessage = String.format(EditSellerCommand.MESSAGE_EDIT_SELLER_SUCCESS,
                Messages.format(editedSeller));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.commitAddressBook();

        assertCommandSuccess(editSellerCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showSellerAtIndex(model, INDEX_FIRST_PERSON);

        Seller sellerInFilteredList = model.getFilteredSellerList().get(INDEX_FIRST_PERSON.getZeroBased());
        Seller editedSeller = new SellerBuilder(sellerInFilteredList).withName(VALID_NAME_BOB).build();
        EditSellerCommand editSellerCommand = new EditSellerCommand(INDEX_FIRST_PERSON,
                new EditSellerDescriptorBuilder().withName(VALID_NAME_BOB).build());

        String expectedMessage = String.format(EditSellerCommand.MESSAGE_EDIT_SELLER_SUCCESS,
                Messages.format(editedSeller));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setSeller(model.getFilteredSellerList().get(0), editedSeller);
        expectedModel.commitAddressBook();

        assertCommandSuccess(editSellerCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicatePersonUnfilteredList_failure() {
        Seller firstSeller = model.getFilteredSellerList().get(INDEX_FIRST_PERSON.getZeroBased());
        EditSellerDescriptor descriptor = new EditSellerDescriptorBuilder(firstSeller).build();
        EditSellerCommand editSellerCommand = new EditSellerCommand(INDEX_SECOND_PERSON, descriptor);

        assertCommandFailure(editSellerCommand, model, EditSellerCommand.MESSAGE_DUPLICATE_SELLER);
    }

    @Test
    public void execute_duplicatePersonFilteredList_failure() {
        showSellerAtIndex(model, INDEX_FIRST_PERSON);

        // edit person in filtered list into a duplicate in address book
        Seller sellerInList = model.getAddressBook().getSellerList().get(INDEX_SECOND_PERSON.getZeroBased());
        EditSellerCommand editSellerCommand = new EditSellerCommand(INDEX_FIRST_PERSON,
                new EditSellerDescriptorBuilder(sellerInList).build());

        assertCommandFailure(editSellerCommand, model, EditSellerCommand.MESSAGE_DUPLICATE_SELLER);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredSellerList().size() + 1);
        EditSellerDescriptor descriptor = new EditSellerDescriptorBuilder().withName(VALID_NAME_BOB).build();
        EditSellerCommand editSellerCommand = new EditSellerCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editSellerCommand, model, Messages.MESSAGE_INVALID_SELLER_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidPersonIndexFilteredList_failure() {
        showSellerAtIndex(model, INDEX_FIRST_PERSON);
        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getSellerList().size());

        EditSellerCommand editSellerCommand = new EditSellerCommand(outOfBoundIndex,
                new EditSellerDescriptorBuilder().withName(VALID_NAME_BOB).build());

        assertCommandFailure(editSellerCommand, model, Messages.MESSAGE_INVALID_SELLER_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditSellerCommand standardCommand = new EditSellerCommand(INDEX_FIRST_PERSON, DESC_SAMY);

        // same values -> returns true
        EditSellerDescriptor copyDescriptor = new EditSellerDescriptor(DESC_SAMY);
        EditSellerCommand commandWithSameValues = new EditSellerCommand(INDEX_FIRST_PERSON, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditSellerCommand(INDEX_SECOND_PERSON, DESC_SAMY)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditSellerCommand(INDEX_FIRST_PERSON, DESC_SBOB)));
    }

    @Test
    public void toStringMethod() {
        Index index = Index.fromOneBased(1);
        EditSellerDescriptor editSellerDescriptor = new EditSellerDescriptor();
        EditSellerCommand editSellerCommand = new EditSellerCommand(index, editSellerDescriptor);
        String expected = EditSellerCommand.class.getCanonicalName() + "{index=" + index + ", editSellerDescriptor="
                + editSellerDescriptor + "}";
        assertEquals(expected, editSellerCommand.toString());
    }

}
