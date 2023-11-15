package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_ASIAN;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BRITISH;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LOCATION_BRITISH;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BRITISH;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showStallAtIndex;
import static seedu.address.testutil.StallBuilder.VALID_MENU_1;
import static seedu.address.testutil.StallBuilder.VALID_STALL_REVIEW_1;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_STALL;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_STALL;
import static seedu.address.testutil.TypicalStalls.getTypicalAddressBookWithMenuAndReview;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.EditStallCommand.EditStallDescriptor;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.stall.Stall;
import seedu.address.testutil.EditStallDescriptorBuilder;
import seedu.address.testutil.StallBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditStallCommand.
 */
public class EditStallCommandTest {

    private Model model = new ModelManager(getTypicalAddressBookWithMenuAndReview(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Stall editedStall = new StallBuilder().withMenu(VALID_MENU_1).withStallReview(VALID_STALL_REVIEW_1).build();
        EditStallDescriptor descriptor = new EditStallDescriptorBuilder(editedStall).build();
        EditStallCommand editStallCommand = new EditStallCommand(INDEX_FIRST_STALL, descriptor);

        String expectedMessage = String
                .format(EditStallCommand.MESSAGE_EDIT_STALL_SUCCESS, Messages.format(editedStall));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setStall(model.getFilteredStallList().get(0), editedStall);

        assertCommandSuccess(editStallCommand,
                model,
                expectedMessage,
                CommandResult.ViewType.STALL_DETAIL,
                expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        // Only stall name is edited
        Index indexLastStall = Index.fromOneBased(model.getFilteredStallList().size());
        Stall lastStall = model.getFilteredStallList().get(indexLastStall.getZeroBased());
        StallBuilder stallInList = new StallBuilder(lastStall);

        Stall editedStall = stallInList
                .withName(VALID_NAME_BRITISH).build();

        EditStallDescriptor descriptor = new EditStallDescriptorBuilder(editedStall).withName(VALID_NAME_BRITISH)
                .build();
        EditStallCommand editStallCommand = new EditStallCommand(indexLastStall, descriptor);

        String expectedMessage = String
                .format(EditStallCommand.MESSAGE_EDIT_STALL_SUCCESS, Messages.format(editedStall));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setStall(lastStall, editedStall);

        assertCommandSuccess(editStallCommand,
                model,
                expectedMessage,
                CommandResult.ViewType.STALL_DETAIL,
                expectedModel);

        // Only stall location is edited
        indexLastStall = Index.fromOneBased(model.getFilteredStallList().size());
        lastStall = model.getFilteredStallList().get(indexLastStall.getZeroBased());
        stallInList = new StallBuilder(lastStall);

        editedStall = stallInList
                .withLocation(VALID_LOCATION_BRITISH).build();

        descriptor = new EditStallDescriptorBuilder(editedStall)
                .withLocation(VALID_LOCATION_BRITISH).build();
        editStallCommand = new EditStallCommand(indexLastStall, descriptor);

        expectedMessage = String
                .format(EditStallCommand.MESSAGE_EDIT_STALL_SUCCESS, Messages.format(editedStall));

        expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setStall(lastStall, editedStall);

        assertCommandSuccess(editStallCommand,
                model,
                expectedMessage,
                CommandResult.ViewType.STALL_DETAIL,
                expectedModel);
    }
    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditStallCommand editStallCommand = new EditStallCommand(INDEX_FIRST_STALL, new EditStallDescriptor());
        Stall editedStall = model.getFilteredStallList().get(INDEX_FIRST_STALL.getZeroBased());

        String expectedMessage = String
                .format(EditStallCommand.MESSAGE_EDIT_STALL_SUCCESS, Messages.format(editedStall));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        assertCommandSuccess(editStallCommand,
                model,
                expectedMessage,
                CommandResult.ViewType.STALL_DETAIL,
                expectedModel);
    }

    @Test
    public void execute_duplicateStallUnfilteredList_failure() {
        Stall firstStall = model.getFilteredStallList().get(INDEX_FIRST_STALL.getZeroBased());
        EditStallDescriptor descriptor = new EditStallDescriptorBuilder(firstStall).build();
        EditStallCommand editStallCommand = new EditStallCommand(INDEX_SECOND_STALL, descriptor);

        assertCommandFailure(editStallCommand, model, EditStallCommand.MESSAGE_DUPLICATE_STALL);
    }

    @Test
    public void execute_duplicateStallFilteredList_failure() {
        showStallAtIndex(model, INDEX_FIRST_STALL);

        // edit stall in filtered list into a duplicate in address book
        Stall stallInList = model.getAddressBook().getStallList().get(INDEX_SECOND_STALL.getZeroBased());
        EditStallCommand editStallCommand = new EditStallCommand(INDEX_FIRST_STALL,
                new EditStallDescriptorBuilder(stallInList).build());

        assertCommandFailure(editStallCommand, model, EditStallCommand.MESSAGE_DUPLICATE_STALL);
    }

    @Test
    public void execute_invalidStallIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredStallList().size() + 1);
        EditStallDescriptor descriptor = new EditStallDescriptorBuilder().withName(VALID_NAME_BRITISH).build();
        EditStallCommand editStallCommand = new EditStallCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editStallCommand, model, Messages.MESSAGE_INVALID_STALL_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidStallIndexFilteredList_failure() {
        showStallAtIndex(model, INDEX_FIRST_STALL);
        Index outOfBoundIndex = INDEX_SECOND_STALL;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getStallList().size());

        EditStallCommand editStallCommand = new EditStallCommand(outOfBoundIndex,
                new EditStallDescriptorBuilder().withName(VALID_NAME_BRITISH).build());

        assertCommandFailure(editStallCommand, model, Messages.MESSAGE_INVALID_STALL_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditStallCommand standardCommand = new EditStallCommand(INDEX_FIRST_STALL, DESC_ASIAN);

        // same values -> returns true
        EditStallDescriptor copyDescriptor = new EditStallDescriptor(DESC_ASIAN);
        EditStallCommand commandWithSameValues = new EditStallCommand(INDEX_FIRST_STALL, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditStallCommand(INDEX_SECOND_STALL, DESC_ASIAN)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditStallCommand(INDEX_FIRST_STALL, DESC_BRITISH)));
    }

    @Test
    public void toStringMethod() {
        Index index = Index.fromOneBased(1);
        EditStallDescriptor editStallDescriptor = new EditStallDescriptor();
        EditStallCommand editStallCommand = new EditStallCommand(index, editStallDescriptor);
        String expected = EditStallCommand.class.getCanonicalName() + "{index=" + index + ", editStallDescriptor="
                + editStallDescriptor + "}";
        assertEquals(expected, editStallCommand.toString());
    }

}
