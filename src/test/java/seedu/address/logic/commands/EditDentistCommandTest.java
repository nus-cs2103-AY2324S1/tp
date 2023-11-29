package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_AMY_DENTIST;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BOB_DENTIST;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showDentistWithId;
import static seedu.address.testutil.TypicalDentists.getTypicalAddressBook;
import static seedu.address.testutil.TypicalIndexes.FIRST_DENTIST_ID;
import static seedu.address.testutil.TypicalIndexes.SECOND_DENTIST_ID;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.EditDentistCommand.EditDentistDescriptor;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.dentist.Dentist;
import seedu.address.testutil.DentistBuilder;
import seedu.address.testutil.EditDentistDescriptorBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class EditDentistCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Dentist editedDentist = new DentistBuilder().buildWithId(FIRST_DENTIST_ID);
        EditDentistDescriptor descriptor = new EditDentistDescriptorBuilder(editedDentist).build();
        EditDentistCommand editDentistCommand = new EditDentistCommand(FIRST_DENTIST_ID, descriptor);

        String expectedMessage = String.format(EditDentistCommand.MESSAGE_EDIT_DENTIST_SUCCESS,
                Messages.format(editedDentist));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setDentist(model.getFilteredDentistList().get(1), editedDentist);

        assertCommandSuccess(editDentistCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        int modelDentistListLength = model.getFilteredDentistList().size();
        long lastDentistId = model.getFilteredDentistList().get(modelDentistListLength - 1).getId();
        Dentist lastDentist = model.getFilteredDentistList().get(modelDentistListLength - 1);

        DentistBuilder dentistInList = new DentistBuilder(lastDentist);
        Dentist editedDentist = dentistInList.withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
                .withTags(VALID_TAG_HUSBAND).buildWithId(lastDentistId);

        EditDentistDescriptor descriptor = new EditDentistDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withTags(VALID_TAG_HUSBAND).build();
        EditDentistCommand editDentistCommand = new EditDentistCommand(lastDentistId, descriptor);

        String expectedMessage = String.format(EditDentistCommand.MESSAGE_EDIT_DENTIST_SUCCESS,
                Messages.format(editedDentist));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setDentist(lastDentist, editedDentist);

        assertCommandSuccess(editDentistCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditDentistCommand editDentistCommand = new EditDentistCommand(FIRST_DENTIST_ID, new EditDentistDescriptor());
        Dentist editedDentist = model.getDentistById(FIRST_DENTIST_ID);

        String expectedMessage = String.format(EditDentistCommand.MESSAGE_EDIT_DENTIST_SUCCESS,
                Messages.format(editedDentist));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        assertCommandSuccess(editDentistCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showDentistWithId(model, FIRST_DENTIST_ID);

        Dentist dentistInFilteredList = model.getDentistById(FIRST_DENTIST_ID);
        Dentist editedDentist = new DentistBuilder(dentistInFilteredList).withName(VALID_NAME_BOB)
                .buildWithId(dentistInFilteredList.getId());
        EditDentistCommand editDentistCommand = new EditDentistCommand(FIRST_DENTIST_ID,
                new EditDentistDescriptorBuilder().withName(VALID_NAME_BOB).build());

        String expectedMessage = String.format(EditDentistCommand.MESSAGE_EDIT_DENTIST_SUCCESS,
                Messages.format(editedDentist));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setDentist(model.getDentistById(FIRST_DENTIST_ID), editedDentist);

        assertCommandSuccess(editDentistCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateDentistUnfilteredList_failure() {
        Dentist firstDentist = model.getDentistById(FIRST_DENTIST_ID);
        EditDentistDescriptor descriptor = new EditDentistDescriptorBuilder(firstDentist).build();
        EditDentistCommand editDentistCommand = new EditDentistCommand(SECOND_DENTIST_ID, descriptor);

        assertCommandFailure(editDentistCommand, model, EditDentistCommand.MESSAGE_DUPLICATE_DENTIST);
    }

    @Test
    public void execute_duplicateDentistFilteredList_failure() {
        Dentist dentistInList = model.getDentistById(SECOND_DENTIST_ID);
        showDentistWithId(model, FIRST_DENTIST_ID);

        // edit dentist in filtered list into a duplicate dentist in ToothTracker
        EditDentistCommand editDentistCommand = new EditDentistCommand(FIRST_DENTIST_ID,
                new EditDentistDescriptorBuilder(dentistInList).build());

        assertCommandFailure(editDentistCommand, model, EditDentistCommand.MESSAGE_DUPLICATE_DENTIST);
    }

    @Test
    public void execute_invalidDentistIdUnfilteredList_failure() {
        long outOfBoundIndex = -1;
        EditDentistDescriptor descriptor = new EditDentistDescriptorBuilder().withName(VALID_NAME_BOB).build();
        EditDentistCommand editCommand = new EditDentistCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_DENTIST_DISPLAYED_INDEX);
    }

    //    /**
    //     * Edit filtered list where index is larger than size of filtered list,
    //     * but smaller than size of address book
    //     */
    //    @Test
    //    public void execute_invalidPersonIndexFilteredList_failure() {
    //        showPersonAtIndex(model, INDEX_FIRST_PERSON);
    //        Index outOfBoundIndex = INDEX_SECOND_PERSON;
    //        // ensures that outOfBoundIndex is still in bounds of address book list
    //        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());
    //
    //        EditCommand editCommand = new EditCommand(outOfBoundIndex,
    //                new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB).build());
    //
    //        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    //    }

    @Test
    public void equals() {
        final EditDentistCommand standardCommand = new EditDentistCommand(FIRST_DENTIST_ID, DESC_AMY_DENTIST);

        // same values -> returns true
        EditDentistDescriptor copyDescriptor = new EditDentistDescriptor(DESC_AMY_DENTIST);
        EditDentistCommand commandWithSameValues = new EditDentistCommand(FIRST_DENTIST_ID, copyDescriptor);
        // assertEquals(standardCommand, commandWithSameValues);
        // assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditDentistCommand(SECOND_DENTIST_ID, DESC_AMY_DENTIST)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditDentistCommand(FIRST_DENTIST_ID, DESC_BOB_DENTIST)));
    }

    @Test
    public void toStringMethod() {
        long dentistId = FIRST_DENTIST_ID;
        EditDentistDescriptor editDentistDescriptor = new EditDentistDescriptor();
        EditDentistCommand editDentistCommand = new EditDentistCommand(dentistId, editDentistDescriptor);
        String expected = EditDentistCommand.class.getCanonicalName()
                + "{dentistId=" + dentistId + ", editDentistDescriptor=" + editDentistDescriptor + "}";
        assertEquals(expected, editDentistCommand.toString());
    }

}
