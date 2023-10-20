package seedu.address.logic.commands.musician;

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
import static seedu.address.logic.commands.CommandTestUtil.showMusicianAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_MUSICIAN;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_MUSICIAN;
import static seedu.address.testutil.TypicalMusicians.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.general.ClearCommand;
import seedu.address.logic.commands.musician.EditCommand.EditMusicianDescriptor;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.musician.Musician;
import seedu.address.testutil.EditMusicianDescriptorBuilder;
import seedu.address.testutil.MusicianBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class EditCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Musician editedMusician = new MusicianBuilder().build();
        EditCommand.EditMusicianDescriptor descriptor = new EditMusicianDescriptorBuilder(editedMusician).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_MUSICIAN, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_MUSICIAN_SUCCESS,
                Messages.format(editedMusician));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setMusician(model.getFilteredMusicianList().get(0), editedMusician);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastMusician = Index.fromOneBased(model.getFilteredMusicianList().size());
        Musician lastMusician = model.getFilteredMusicianList().get(indexLastMusician.getZeroBased());

        MusicianBuilder musicianInList = new MusicianBuilder(lastMusician);
        Musician editedMusician = musicianInList.withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
                .withTags(VALID_TAG_HUSBAND).build();

        EditCommand.EditMusicianDescriptor descriptor = new EditMusicianDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withTags(VALID_TAG_HUSBAND).build();
        EditCommand editCommand = new EditCommand(indexLastMusician, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_MUSICIAN_SUCCESS,
                Messages.format(editedMusician));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setMusician(lastMusician, editedMusician);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditCommand editCommand = new EditCommand(INDEX_FIRST_MUSICIAN, new EditMusicianDescriptor());
        Musician editedMusician = model.getFilteredMusicianList().get(INDEX_FIRST_MUSICIAN.getZeroBased());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_MUSICIAN_SUCCESS,
                Messages.format(editedMusician));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showMusicianAtIndex(model, INDEX_FIRST_MUSICIAN);

        Musician musicianInFilteredList = model.getFilteredMusicianList().get(INDEX_FIRST_MUSICIAN.getZeroBased());
        Musician editedMusician = new MusicianBuilder(musicianInFilteredList).withName(VALID_NAME_BOB).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_MUSICIAN,
                new EditMusicianDescriptorBuilder().withName(VALID_NAME_BOB).build());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_MUSICIAN_SUCCESS,
                Messages.format(editedMusician));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setMusician(model.getFilteredMusicianList().get(0), editedMusician);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateMusicianUnfilteredList_failure() {
        Musician firstMusician = model.getFilteredMusicianList().get(INDEX_FIRST_MUSICIAN.getZeroBased());
        EditCommand.EditMusicianDescriptor descriptor = new EditMusicianDescriptorBuilder(firstMusician).build();
        EditCommand editCommand = new EditCommand(INDEX_SECOND_MUSICIAN, descriptor);

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_MUSICIAN);
    }

    @Test
    public void execute_duplicateMusicianFilteredList_failure() {
        showMusicianAtIndex(model, INDEX_FIRST_MUSICIAN);

        // edit musician in filtered list into a duplicate in address book
        Musician musicianInList = model.getAddressBook().getMusicianList().get(INDEX_SECOND_MUSICIAN.getZeroBased());
        EditCommand editCommand = new EditCommand(INDEX_FIRST_MUSICIAN,
                new EditMusicianDescriptorBuilder(musicianInList).build());

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_MUSICIAN);
    }

    @Test
    public void execute_invalidMusicianIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredMusicianList().size() + 1);
        EditCommand.EditMusicianDescriptor descriptor =
                new EditMusicianDescriptorBuilder().withName(VALID_NAME_BOB).build();
        EditCommand editCommand = new EditCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_MUSICIAN_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidMusicianIndexFilteredList_failure() {
        showMusicianAtIndex(model, INDEX_FIRST_MUSICIAN);
        Index outOfBoundIndex = INDEX_SECOND_MUSICIAN;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getMusicianList().size());

        EditCommand editCommand = new EditCommand(outOfBoundIndex,
                new EditMusicianDescriptorBuilder().withName(VALID_NAME_BOB).build());

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_MUSICIAN_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditCommand standardCommand = new EditCommand(INDEX_FIRST_MUSICIAN, DESC_AMY);

        // same values -> returns true
        EditCommand.EditMusicianDescriptor copyDescriptor = new EditMusicianDescriptor(DESC_AMY);
        EditCommand commandWithSameValues = new EditCommand(INDEX_FIRST_MUSICIAN, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_SECOND_MUSICIAN, DESC_AMY)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_FIRST_MUSICIAN, DESC_BOB)));
    }

    @Test
    public void toStringMethod() {
        Index index = Index.fromOneBased(1);
        EditCommand.EditMusicianDescriptor editMusicianDescriptor = new EditMusicianDescriptor();
        EditCommand editCommand = new EditCommand(index, editMusicianDescriptor);
        String expected = EditCommand.class.getCanonicalName() + "{index=" + index + ", editMusicianDescriptor="
                + editMusicianDescriptor + "}";
        assertEquals(expected, editCommand.toString());
    }

}
