package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_CHICKEN_RICE;
import static seedu.address.logic.commands.CommandTestUtil.DESC_NASI_LEMAK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ITEM_NAME_CHICKEN_RICE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ITEM_PRICE_2;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.ItemBuilder.VALID_ITEM_REVIEW_1;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_ITEM;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_STALL;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_ITEM;
import static seedu.address.testutil.TypicalStalls.getTypicalAddressBookWithMenuAndReview;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.EditItemCommand.EditItemDescriptor;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.item.Item;
import seedu.address.model.stall.Stall;
import seedu.address.testutil.EditItemDescriptorBuilder;
import seedu.address.testutil.ItemBuilder;



/**
 * Contains integration tests (interaction with the Model) and unit tests for EditItemCommand.
 */
public class EditItemCommandTest {

    private Model model = new ModelManager(getTypicalAddressBookWithMenuAndReview(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Item editedItem = new ItemBuilder().withItemReview(VALID_ITEM_REVIEW_1).build();
        EditItemCommand.EditItemDescriptor descriptor = new EditItemDescriptorBuilder(editedItem).build();
        EditItemCommand editItemCommand = new EditItemCommand(INDEX_FIRST_STALL, INDEX_FIRST_ITEM, descriptor);

        String expectedMessage = String
                .format(EditItemCommand.MESSAGE_EDIT_ITEM_SUCCESS, Messages.format(editedItem));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setItem(INDEX_FIRST_STALL, INDEX_FIRST_ITEM, editedItem);

        assertCommandSuccess(editItemCommand,
                model,
                expectedMessage,
                CommandResult.ViewType.VIEW_ITEM,
                expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        // Only item name is edited
        Index indexLastStall = Index.fromOneBased(model.getFilteredStallList().size());
        Stall lastStall = model.getFilteredStallList().get(indexLastStall.getZeroBased());
        Item lastItem = lastStall.getMenu().getItem(INDEX_FIRST_ITEM);
        ItemBuilder itemInList = new ItemBuilder(lastItem);

        Item editedItem = itemInList.withName(VALID_ITEM_NAME_CHICKEN_RICE).build();

        EditItemDescriptor itemDescriptor = new EditItemDescriptorBuilder(editedItem)
                .withName(VALID_ITEM_NAME_CHICKEN_RICE)
                .build();
        EditItemCommand editItemCommand = new EditItemCommand(indexLastStall, INDEX_FIRST_ITEM, itemDescriptor);

        String expectedMessage = String
                .format(EditItemCommand.MESSAGE_EDIT_ITEM_SUCCESS, Messages.format(editedItem));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setItem(indexLastStall, INDEX_FIRST_ITEM, editedItem);

        assertCommandSuccess(editItemCommand,
                model,
                expectedMessage,
                CommandResult.ViewType.VIEW_ITEM,
                expectedModel);

        // Only item price is edited
        indexLastStall = Index.fromOneBased(model.getFilteredStallList().size());
        lastStall = model.getFilteredStallList().get(indexLastStall.getZeroBased());
        lastItem = lastStall.getMenu().getItem(INDEX_FIRST_ITEM);
        itemInList = new ItemBuilder(lastItem);

        editedItem = itemInList.withPrice(VALID_ITEM_PRICE_2).build();
        itemDescriptor = new EditItemDescriptorBuilder(editedItem).withPrice(VALID_ITEM_PRICE_2).build();
        editItemCommand = new EditItemCommand(indexLastStall, INDEX_FIRST_ITEM, itemDescriptor);

        expectedMessage = String
                .format(EditItemCommand.MESSAGE_EDIT_ITEM_SUCCESS, Messages.format(editedItem));

        expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setItem(indexLastStall, INDEX_FIRST_ITEM, editedItem);

        assertCommandSuccess(editItemCommand,
                model,
                expectedMessage,
                CommandResult.ViewType.VIEW_ITEM,
                expectedModel);
    }
    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        Stall stall = model.getFilteredStallList().get(INDEX_FIRST_STALL.getZeroBased());
        EditItemCommand editItemCommand = new EditItemCommand(INDEX_FIRST_STALL, INDEX_FIRST_ITEM,
                new EditItemDescriptor());
        Item editedItem = stall.getMenu().getItem(INDEX_FIRST_ITEM);

        String expectedMessage = String
                .format(EditItemCommand.MESSAGE_EDIT_ITEM_SUCCESS, Messages.format(editedItem));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        assertCommandSuccess(editItemCommand,
                model,
                expectedMessage,
                CommandResult.ViewType.VIEW_ITEM,
                expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        Stall stall = model.getFilteredStallList().get(INDEX_FIRST_STALL.getZeroBased());
        Item editedItem = stall.getMenu().getItem(INDEX_FIRST_ITEM);
        EditItemCommand editItemCommand = new EditItemCommand(INDEX_FIRST_STALL, INDEX_FIRST_ITEM,
                new EditItemDescriptor());

        String expectedMessage = String
                .format(EditItemCommand.MESSAGE_EDIT_ITEM_SUCCESS, Messages.format(editedItem));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setStall(model.getFilteredStallList().get(0), stall);

        assertCommandSuccess(editItemCommand,
                model,
                expectedMessage,
                CommandResult.ViewType.VIEW_ITEM,
                expectedModel);
    }

    @Test
    public void execute_duplicateItemUnfilteredList_failure() {
        Stall firstStall = model.getFilteredStallList().get(INDEX_FIRST_STALL.getZeroBased());
        Item secondItem = firstStall.getMenu().getItem(INDEX_SECOND_ITEM);
        EditItemDescriptor descriptor = new EditItemDescriptorBuilder(secondItem).build();
        EditItemCommand editItemCommand = new EditItemCommand(INDEX_FIRST_STALL, INDEX_FIRST_ITEM, descriptor);

        assertCommandFailure(editItemCommand, model, EditItemCommand.MESSAGE_DUPLICATE_ITEM);
    }

    @Test
    public void execute_duplicateStallFilteredList_failure() {
        // edit item in filtered list into a duplicate in address book
        Stall stall = model.getAddressBook().getStallList().get(INDEX_FIRST_STALL.getZeroBased());
        Item itemInList = stall.getMenu().getItem(INDEX_SECOND_ITEM);
        EditItemCommand editItemCommand = new EditItemCommand(INDEX_FIRST_STALL, INDEX_FIRST_ITEM,
                new EditItemDescriptorBuilder(itemInList).build());

        assertCommandFailure(editItemCommand, model, EditItemCommand.MESSAGE_DUPLICATE_ITEM);
    }

    @Test
    public void execute_invalidItemIndexUnfilteredList_failure() {
        Stall stall = model.getAddressBook().getStallList().get(INDEX_FIRST_STALL.getZeroBased());
        Index outOfBoundIndex = Index.fromOneBased(stall.getMenu().getItemList().size() + 1);
        EditItemDescriptor descriptor = new EditItemDescriptorBuilder().withName(VALID_ITEM_NAME_CHICKEN_RICE).build();
        EditItemCommand editItemCommand = new EditItemCommand(INDEX_FIRST_STALL, outOfBoundIndex, descriptor);

        assertCommandFailure(editItemCommand, model, Messages.MESSAGE_INVALID_ITEM_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditItemCommand standardEditItemCommand = new EditItemCommand(INDEX_FIRST_STALL, INDEX_FIRST_ITEM,
                DESC_NASI_LEMAK);

        // same values -> returns true
        EditItemDescriptor copyDescriptor = new EditItemDescriptor(DESC_NASI_LEMAK);
        EditItemCommand commandWithSameValues =
                new EditItemCommand(INDEX_FIRST_STALL, INDEX_FIRST_ITEM, copyDescriptor);
        assertTrue(standardEditItemCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardEditItemCommand.equals(standardEditItemCommand));

        // null -> returns false
        assertFalse(standardEditItemCommand.equals(null));

        // different types -> returns false
        assertFalse(standardEditItemCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardEditItemCommand.equals(new EditItemCommand(INDEX_FIRST_STALL, INDEX_SECOND_ITEM,
                DESC_NASI_LEMAK)));

        // different descriptor -> returns false
        assertFalse(standardEditItemCommand.equals(new EditItemCommand(INDEX_FIRST_STALL, INDEX_FIRST_ITEM,
                DESC_CHICKEN_RICE)));
    }

    @Test
    public void toStringMethod() {
        Index stallIndex = Index.fromOneBased(1);
        Index itemIndex = Index.fromOneBased(1);
        EditItemDescriptor editItemDescriptor = new EditItemDescriptor();
        EditItemCommand editItemCommand = new EditItemCommand(stallIndex, itemIndex, editItemDescriptor);
        String expected = EditItemCommand.class.getCanonicalName() + "{stallIndex=" + stallIndex + ", itemIndex="
                + itemIndex + ", editItemDescriptor=" + editItemDescriptor + "}";
        assertEquals(expected, editItemCommand.toString());
    }
}
