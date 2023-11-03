package seedu.address.logic.commands.band;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_POPSTARS;
import static seedu.address.logic.commands.CommandTestUtil.DESC_ROCKSTARS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_ROCKSTARS;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showBandAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_MUSICIAN;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_MUSICIAN;
import static seedu.address.testutil.typicalentities.TypicalAddressBook.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.band.EditBandCommand.EditBandDescriptor;
import seedu.address.logic.commands.general.ClearCommand;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.band.Band;
import seedu.address.testutil.BandBuilder;
import seedu.address.testutil.EditBandDescriptorBuilder;


public class EditBandCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Band editedBand = new BandBuilder().build();
        EditBandCommand.EditBandDescriptor descriptor = new EditBandDescriptorBuilder(editedBand).build();
        EditBandCommand editBandCommand = new EditBandCommand(INDEX_FIRST_MUSICIAN, descriptor);

        String expectedMessage = String.format(EditBandCommand.MESSAGE_EDIT_BAND_SUCCESS,
                Messages.format(editedBand));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setBand(model.getFilteredBandList().get(0), editedBand);

        assertCommandSuccess(editBandCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastBand = Index.fromOneBased(model.getFilteredBandList().size());
        Band lastBand = model.getFilteredBandList().get(indexLastBand.getZeroBased());

        BandBuilder bandInList = new BandBuilder(lastBand);
        Band editedBand = bandInList.withName(VALID_NAME_ROCKSTARS).build();

        EditBandCommand.EditBandDescriptor descriptor = new EditBandDescriptorBuilder().withName(VALID_NAME_ROCKSTARS)
                .build();
        EditBandCommand editBandCommand = new EditBandCommand(indexLastBand, descriptor);

        String expectedMessage = String.format(EditBandCommand.MESSAGE_EDIT_BAND_SUCCESS,
                Messages.format(editedBand));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setBand(lastBand, editedBand);

        assertCommandSuccess(editBandCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditBandCommand editBandCommand = new EditBandCommand(INDEX_FIRST_MUSICIAN, new EditBandDescriptor());
        Band editedBand = model.getFilteredBandList().get(INDEX_FIRST_MUSICIAN.getZeroBased());

        String expectedMessage = String.format(EditBandCommand.MESSAGE_EDIT_BAND_SUCCESS,
                Messages.format(editedBand));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        assertCommandSuccess(editBandCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showBandAtIndex(model, INDEX_FIRST_MUSICIAN);

        Band bandInFilteredList = model.getFilteredBandList().get(INDEX_FIRST_MUSICIAN.getZeroBased());
        Band editedBand = new BandBuilder(bandInFilteredList).withName(VALID_NAME_ROCKSTARS).build();
        EditBandCommand editBandCommand = new EditBandCommand(INDEX_FIRST_MUSICIAN,
                new EditBandDescriptorBuilder().withName(VALID_NAME_ROCKSTARS).build());

        String expectedMessage = String.format(EditBandCommand.MESSAGE_EDIT_BAND_SUCCESS,
                Messages.format(editedBand));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setBand(model.getFilteredBandList().get(0), editedBand);

        assertCommandSuccess(editBandCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateBandUnfilteredList_failure() {
        Band firstBand = model.getFilteredBandList().get(INDEX_FIRST_MUSICIAN.getZeroBased());
        EditBandCommand.EditBandDescriptor descriptor = new EditBandDescriptorBuilder(firstBand).build();
        EditBandCommand editBandCommand = new EditBandCommand(INDEX_SECOND_MUSICIAN, descriptor);

        assertCommandFailure(editBandCommand, model, EditBandCommand.MESSAGE_DUPLICATE_BAND);
    }

    @Test
    public void execute_duplicateMusicianFilteredList_failure() {
        showBandAtIndex(model, INDEX_FIRST_MUSICIAN);

        // edit band in filtered list into a duplicate in address book
        Band bandInList = model.getAddressBook().getBandList().get(INDEX_SECOND_MUSICIAN.getZeroBased());
        EditBandCommand editBandCommand = new EditBandCommand(INDEX_FIRST_MUSICIAN,
                new EditBandDescriptorBuilder(bandInList).build());

        assertCommandFailure(editBandCommand, model, EditBandCommand.MESSAGE_DUPLICATE_BAND);
    }

    @Test
    public void execute_invalidBandIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredBandList().size() + 1);
        EditBandCommand.EditBandDescriptor descriptor =
                new EditBandDescriptorBuilder().withName(VALID_NAME_ROCKSTARS).build();
        EditBandCommand editBandCommand = new EditBandCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editBandCommand, model, Messages.MESSAGE_INVALID_BAND_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidBandIndexFilteredList_failure() {
        showBandAtIndex(model, INDEX_FIRST_MUSICIAN);
        Index outOfBoundIndex = INDEX_SECOND_MUSICIAN;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getBandList().size());

        EditBandCommand editBandCommand = new EditBandCommand(outOfBoundIndex,
                new EditBandDescriptorBuilder().withName(VALID_NAME_ROCKSTARS).build());

        assertCommandFailure(editBandCommand, model, Messages.MESSAGE_INVALID_BAND_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditBandCommand standardCommand = new EditBandCommand(INDEX_FIRST_MUSICIAN, DESC_ROCKSTARS);

        // same values -> returns true
        EditBandCommand.EditBandDescriptor copyDescriptor = new EditBandDescriptor(DESC_ROCKSTARS);
        EditBandCommand commandWithSameValues = new EditBandCommand(INDEX_FIRST_MUSICIAN, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditBandCommand(INDEX_SECOND_MUSICIAN, DESC_ROCKSTARS)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditBandCommand(INDEX_FIRST_MUSICIAN, DESC_POPSTARS)));
    }

    @Test
    public void toStringMethod() {
        Index index = Index.fromOneBased(1);
        EditBandCommand.EditBandDescriptor editBandDescriptor = new EditBandDescriptor();
        EditBandCommand editBandCommand = new EditBandCommand(index, editBandDescriptor);
        String expected = EditBandCommand.class.getCanonicalName() + "{index=" + index + ", editBandDescriptor="
                + editBandDescriptor + "}";
        assertEquals(expected, editBandCommand.toString());
    }
}
