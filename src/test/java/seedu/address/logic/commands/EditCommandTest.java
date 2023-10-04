package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_CHEF;
import static seedu.address.logic.commands.CommandTestUtil.DESC_CLEANER;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COMPANY_CLEANER;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ROLE_CLEANER;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showJobAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_JOB;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_JOB;
import static seedu.address.testutil.TypicalJobs.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.EditCommand.EditJobDescriptor;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.job.Job;
import seedu.address.testutil.EditJobDescriptorBuilder;
import seedu.address.testutil.JobBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class EditCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Job editedJob = new JobBuilder().build();
        EditJobDescriptor descriptor = new EditJobDescriptorBuilder(editedJob).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_JOB, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_JOB_SUCCESS, Messages.format(editedJob));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setJob(model.getFilteredJobList().get(0), editedJob);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastJob = Index.fromOneBased(model.getFilteredJobList().size());
        Job lastJob = model.getFilteredJobList().get(indexLastJob.getZeroBased());

        JobBuilder jobInList = new JobBuilder(lastJob);
        Job editedJob = jobInList.withRole(VALID_ROLE_CLEANER).withCompany(VALID_COMPANY_CLEANER).build();

        EditJobDescriptor descriptor = new EditJobDescriptorBuilder().withRole(VALID_ROLE_CLEANER)
                .withCompany(VALID_COMPANY_CLEANER).build();
        EditCommand editCommand = new EditCommand(indexLastJob, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_JOB_SUCCESS, Messages.format(editedJob));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setJob(lastJob, editedJob);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditCommand editCommand = new EditCommand(INDEX_FIRST_JOB, new EditJobDescriptor());
        Job editedJob = model.getFilteredJobList().get(INDEX_FIRST_JOB.getZeroBased());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_JOB_SUCCESS, Messages.format(editedJob));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showJobAtIndex(model, INDEX_FIRST_JOB);

        Job jobInFilteredList = model.getFilteredJobList().get(INDEX_FIRST_JOB.getZeroBased());
        Job editedJob = new JobBuilder(jobInFilteredList).withRole(VALID_ROLE_CLEANER).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_JOB,
                new EditJobDescriptorBuilder().withRole(VALID_ROLE_CLEANER).build());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_JOB_SUCCESS, Messages.format(editedJob));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setJob(model.getFilteredJobList().get(0), editedJob);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateJobUnfilteredList_failure() {
        Job firstJob = model.getFilteredJobList().get(INDEX_FIRST_JOB.getZeroBased());
        EditJobDescriptor descriptor = new EditJobDescriptorBuilder(firstJob).build();
        EditCommand editCommand = new EditCommand(INDEX_SECOND_JOB, descriptor);

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_JOB);
    }

    @Test
    public void execute_duplicateJobFilteredList_failure() {
        showJobAtIndex(model, INDEX_FIRST_JOB);

        // edit job in filtered list into a duplicate in address book
        Job jobInList = model.getAddressBook().getJobList().get(INDEX_SECOND_JOB.getZeroBased());
        EditCommand editCommand = new EditCommand(INDEX_FIRST_JOB,
                new EditJobDescriptorBuilder(jobInList).build());

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_JOB);
    }

    @Test
    public void execute_invalidJobIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredJobList().size() + 1);
        EditJobDescriptor descriptor = new EditJobDescriptorBuilder().withRole(VALID_ROLE_CLEANER).build();
        EditCommand editCommand = new EditCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_JOB_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidJobIndexFilteredList_failure() {
        showJobAtIndex(model, INDEX_FIRST_JOB);
        Index outOfBoundIndex = INDEX_SECOND_JOB;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getJobList().size());

        EditCommand editCommand = new EditCommand(outOfBoundIndex,
                new EditJobDescriptorBuilder().withRole(VALID_ROLE_CLEANER).build());

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_JOB_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditCommand standardCommand = new EditCommand(INDEX_FIRST_JOB, DESC_CHEF);

        // same values -> returns true
        EditJobDescriptor copyDescriptor = new EditJobDescriptor(DESC_CHEF);
        EditCommand commandWithSameValues = new EditCommand(INDEX_FIRST_JOB, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_SECOND_JOB, DESC_CHEF)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_FIRST_JOB, DESC_CLEANER)));
    }

    @Test
    public void toStringMethod() {
        Index index = Index.fromOneBased(1);
        EditJobDescriptor editJobDescriptor = new EditJobDescriptor();
        EditCommand editCommand = new EditCommand(index, editJobDescriptor);
        String expected = EditCommand.class.getCanonicalName() + "{index=" + index + ", editJobDescriptor="
                + editJobDescriptor + "}";
        assertEquals(expected, editCommand.toString());
    }

}
