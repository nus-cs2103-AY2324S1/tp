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
import static seedu.address.logic.commands.CommandTestUtil.showApplicantAtIndex;
import static seedu.address.testutil.TypicalAddressBook.getTypicalAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.EditCommand.EditApplicantDescriptor;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.applicant.Applicant;
import seedu.address.testutil.ApplicantBuilder;
import seedu.address.testutil.EditApplicantDescriptorBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class EditCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Applicant editedApplicant = new ApplicantBuilder().build();
        EditCommand.EditApplicantDescriptor descriptor = new EditApplicantDescriptorBuilder(editedApplicant).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST, descriptor);

        String expectedMessage = String.format(
                EditCommand.MESSAGE_EDIT_APPLICANT_SUCCESS, Messages.formatApplicant(editedApplicant));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setApplicant(model.getFilteredApplicantList().get(0), editedApplicant);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastApplicant = Index.fromOneBased(model.getFilteredApplicantList().size());
        Applicant lastApplicant = model.getFilteredApplicantList().get(indexLastApplicant.getZeroBased());

        ApplicantBuilder applicantInList = new ApplicantBuilder(lastApplicant);
        Applicant editedApplicant = applicantInList.withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
                .withTags(VALID_TAG_HUSBAND).build();

        EditCommand.EditApplicantDescriptor descriptor = new EditApplicantDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withTags(VALID_TAG_HUSBAND).build();
        EditCommand editCommand = new EditCommand(indexLastApplicant, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_APPLICANT_SUCCESS,
                Messages.formatApplicant(editedApplicant));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setApplicant(lastApplicant, editedApplicant);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditCommand editCommand = new EditCommand(INDEX_FIRST, new EditCommand.EditApplicantDescriptor());
        Applicant editedApplicant = model.getFilteredApplicantList().get(INDEX_FIRST.getZeroBased());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_APPLICANT_SUCCESS,
                Messages.formatApplicant(editedApplicant));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showApplicantAtIndex(model, INDEX_FIRST);

        Applicant applicantInFilteredList = model.getFilteredApplicantList().get(INDEX_FIRST.getZeroBased());
        Applicant editedApplicant = new ApplicantBuilder(applicantInFilteredList).withName(VALID_NAME_BOB).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST,
                new EditApplicantDescriptorBuilder().withName(VALID_NAME_BOB).build());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_APPLICANT_SUCCESS,
                Messages.formatApplicant(editedApplicant));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setApplicant(model.getFilteredApplicantList().get(0), editedApplicant);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateApplicantUnfilteredList_failure() {
        Applicant firstApplicant = model.getFilteredApplicantList().get(INDEX_FIRST.getZeroBased());
        EditApplicantDescriptor descriptor = new EditApplicantDescriptorBuilder(firstApplicant).build();
        EditCommand editCommand = new EditCommand(INDEX_SECOND, descriptor);

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_APPLICANT);
    }

    @Test
    public void execute_duplicateApplicantFilteredList_failure() {
        showApplicantAtIndex(model, INDEX_FIRST);

        // edit applicant in filtered list into a duplicate in address book
        Applicant applicantInList = model.getAddressBook().getApplicantList().get(INDEX_SECOND.getZeroBased());
        EditCommand editCommand = new EditCommand(INDEX_FIRST,
                new EditApplicantDescriptorBuilder(applicantInList).build());

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_APPLICANT);
    }

    @Test
    public void execute_invalidApplicantIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredApplicantList().size() + 1);
        EditApplicantDescriptor descriptor = new EditApplicantDescriptorBuilder().withName(VALID_NAME_BOB).build();
        EditCommand editCommand = new EditCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_APPLICANT_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidApplicantIndexFilteredList_failure() {
        showApplicantAtIndex(model, INDEX_FIRST);
        Index outOfBoundIndex = INDEX_SECOND;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getApplicantList().size());

        EditCommand editCommand = new EditCommand(outOfBoundIndex,
                new EditApplicantDescriptorBuilder().withName(VALID_NAME_BOB).build());

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_APPLICANT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditCommand standardCommand = new EditCommand(INDEX_FIRST, DESC_AMY);

        // same values -> returns true
        EditCommand.EditApplicantDescriptor copyDescriptor = new EditCommand.EditApplicantDescriptor(DESC_AMY);
        EditCommand commandWithSameValues = new EditCommand(INDEX_FIRST, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_SECOND, DESC_AMY)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_FIRST, DESC_BOB)));
    }

    @Test
    public void toStringMethod() {
        Index index = Index.fromOneBased(1);
        EditCommand.EditApplicantDescriptor editApplicantDescriptor = new EditApplicantDescriptor();
        EditCommand editCommand = new EditCommand(index, editApplicantDescriptor);
        String expected = EditCommand.class.getCanonicalName() + "{index=" + index + ", editApplicantDescriptor="
                + editApplicantDescriptor + "}";
        assertEquals(expected, editCommand.toString());
    }

}
