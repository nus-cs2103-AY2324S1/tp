package seedu.staffsnap.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.staffsnap.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.staffsnap.logic.commands.CommandTestUtil.DESC_BOB;
import static seedu.staffsnap.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.staffsnap.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.staffsnap.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.staffsnap.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.staffsnap.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.staffsnap.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.staffsnap.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.staffsnap.logic.commands.CommandTestUtil.showApplicantAtIndex;
import static seedu.staffsnap.testutil.TypicalApplicants.getTypicalApplicantBook;
import static seedu.staffsnap.testutil.TypicalIndexes.INDEX_FIRST_APPLICANT;
import static seedu.staffsnap.testutil.TypicalIndexes.INDEX_SECOND_APPLICANT;

import org.junit.jupiter.api.Test;

import seedu.staffsnap.commons.core.index.Index;
import seedu.staffsnap.logic.Messages;
import seedu.staffsnap.logic.commands.EditCommand.EditApplicantDescriptor;
import seedu.staffsnap.model.ApplicantBook;
import seedu.staffsnap.model.Model;
import seedu.staffsnap.model.ModelManager;
import seedu.staffsnap.model.UserPrefs;
import seedu.staffsnap.model.applicant.Applicant;
import seedu.staffsnap.testutil.ApplicantBuilder;
import seedu.staffsnap.testutil.EditApplicantDescriptorBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class EditCommandTest {

    private Model model = new ModelManager(getTypicalApplicantBook(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Applicant editedApplicant = new ApplicantBuilder().build();
        EditApplicantDescriptor descriptor = new EditApplicantDescriptorBuilder(editedApplicant).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_APPLICANT, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_APPLICANT_SUCCESS,
                Messages.format(editedApplicant));

        Model expectedModel = new ModelManager(new ApplicantBook(model.getApplicantBook()), new UserPrefs());
        expectedModel.setApplicant(model.getFilteredApplicantList().get(0), editedApplicant);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastApplicant = Index.fromOneBased(model.getFilteredApplicantList().size());
        Applicant lastApplicant = model.getFilteredApplicantList().get(indexLastApplicant.getZeroBased());

        ApplicantBuilder applicantInList = new ApplicantBuilder(lastApplicant);
        Applicant editedApplicant = applicantInList.withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY).build();

        EditApplicantDescriptor descriptor = new EditApplicantDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).build();
        EditCommand editCommand = new EditCommand(indexLastApplicant, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_APPLICANT_SUCCESS,
                Messages.format(editedApplicant));

        Model expectedModel = new ModelManager(new ApplicantBook(model.getApplicantBook()), new UserPrefs());
        expectedModel.setApplicant(lastApplicant, editedApplicant);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditCommand editCommand = new EditCommand(INDEX_FIRST_APPLICANT, new EditApplicantDescriptor());
        Applicant editedApplicant = model.getFilteredApplicantList().get(INDEX_FIRST_APPLICANT.getZeroBased());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_APPLICANT_SUCCESS,
                Messages.format(editedApplicant));

        Model expectedModel = new ModelManager(new ApplicantBook(model.getApplicantBook()), new UserPrefs());

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showApplicantAtIndex(model, INDEX_FIRST_APPLICANT);

        Applicant applicantInFilteredList = model.getFilteredApplicantList().get(INDEX_FIRST_APPLICANT.getZeroBased());
        Applicant editedApplicant = new ApplicantBuilder(applicantInFilteredList).withName(VALID_NAME_AMY).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_APPLICANT,
                new EditApplicantDescriptorBuilder().withName(VALID_NAME_AMY).build());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_APPLICANT_SUCCESS,
                Messages.format(editedApplicant));

        Model expectedModel = new ModelManager(new ApplicantBook(model.getApplicantBook()), new UserPrefs());
        expectedModel.setApplicant(model.getFilteredApplicantList().get(0), editedApplicant);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateApplicantUnfilteredList_failure() {
        Applicant firstApplicant = model.getFilteredApplicantList().get(INDEX_FIRST_APPLICANT.getZeroBased());
        EditApplicantDescriptor descriptor = new EditApplicantDescriptorBuilder(firstApplicant).build();
        EditCommand editCommand = new EditCommand(INDEX_SECOND_APPLICANT, descriptor);

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_APPLICANT);
    }

    @Test
    public void execute_duplicateApplicantFilteredList_failure() {
        showApplicantAtIndex(model, INDEX_FIRST_APPLICANT);

        // edit applicant in filtered list into a duplicate in applicant book
        Applicant applicantInList = model.getApplicantBook()
                .getApplicantList().get(INDEX_SECOND_APPLICANT.getZeroBased());
        EditCommand editCommand = new EditCommand(INDEX_FIRST_APPLICANT,
                new EditApplicantDescriptorBuilder(applicantInList).build());

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_APPLICANT);
    }

    @Test
    public void execute_duplicateEmailUnfilteredList_failure() {
        Applicant firstApplicant = model.getFilteredApplicantList().get(INDEX_FIRST_APPLICANT.getZeroBased());
        EditApplicantDescriptor descriptor = new EditApplicantDescriptorBuilder(firstApplicant)
                .withEmail(VALID_EMAIL_BOB).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_APPLICANT, descriptor);

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_APPLICANT);
    }

    @Test
    public void execute_duplicateEmailFilteredList_failure() {
        showApplicantAtIndex(model, INDEX_FIRST_APPLICANT);

        // edit applicant in filtered list into a duplicate in applicant book
        Applicant applicantInList = model.getApplicantBook()
                .getApplicantList().get(INDEX_FIRST_APPLICANT.getZeroBased());
        EditApplicantDescriptor descriptor = new EditApplicantDescriptorBuilder(applicantInList)
                .withEmail(VALID_EMAIL_BOB).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_APPLICANT,
                new EditApplicantDescriptorBuilder(descriptor).build());

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_APPLICANT);
    }

    @Test
    public void execute_duplicatePhoneUnfilteredList_failure() {
        Applicant firstApplicant = model.getFilteredApplicantList().get(INDEX_FIRST_APPLICANT.getZeroBased());
        EditApplicantDescriptor descriptor = new EditApplicantDescriptorBuilder(firstApplicant)
                .withPhone(VALID_PHONE_BOB).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_APPLICANT, descriptor);

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_APPLICANT);
    }

    @Test
    public void execute_duplicatePhoneFilteredList_failure() {
        showApplicantAtIndex(model, INDEX_FIRST_APPLICANT);

        // edit applicant in filtered list into a duplicate in applicant book
        Applicant applicantInList = model.getApplicantBook()
                .getApplicantList().get(INDEX_FIRST_APPLICANT.getZeroBased());
        EditApplicantDescriptor descriptor = new EditApplicantDescriptorBuilder(applicantInList)
                .withPhone(VALID_PHONE_BOB).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_APPLICANT,
                new EditApplicantDescriptorBuilder(descriptor).build());

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
     * but smaller than size of applicant book
     */
    @Test
    public void execute_invalidApplicantIndexFilteredList_failure() {
        showApplicantAtIndex(model, INDEX_FIRST_APPLICANT);
        Index outOfBoundIndex = INDEX_SECOND_APPLICANT;
        // ensures that outOfBoundIndex is still in bounds of applicant book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getApplicantBook().getApplicantList().size());

        EditCommand editCommand = new EditCommand(outOfBoundIndex,
                new EditApplicantDescriptorBuilder().withName(VALID_NAME_BOB).build());

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_APPLICANT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditCommand standardCommand = new EditCommand(INDEX_FIRST_APPLICANT, DESC_AMY);

        // same values -> returns true
        EditApplicantDescriptor copyDescriptor = new EditApplicantDescriptor(DESC_AMY);
        EditCommand commandWithSameValues = new EditCommand(INDEX_FIRST_APPLICANT, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand("yes")));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_SECOND_APPLICANT, DESC_AMY)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_FIRST_APPLICANT, DESC_BOB)));
    }

    @Test
    public void toStringMethod() {
        Index index = Index.fromOneBased(1);
        EditApplicantDescriptor editApplicantDescriptor = new EditApplicantDescriptor();
        EditCommand editCommand = new EditCommand(index, editApplicantDescriptor);
        String expected = EditCommand.class.getCanonicalName() + "{index=" + index + ", editApplicantDescriptor="
                + editApplicantDescriptor + "}";
        assertEquals(expected, editCommand.toString());
    }

}
