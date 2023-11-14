package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_JANESTREET;
import static seedu.address.logic.commands.CommandTestUtil.DESC_OPTIVER;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COMPANY_NAME_JANESTREET;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COMPANY_NAME_OPTIVER;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DEADLINE_JANESTREET;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REQUIREMENT_JANESTREET;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ROLE_JANESTREET;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STARTDATE_JANESTREET;
import static seedu.address.logic.commands.CommandTestUtil.assertInternshipCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertInternshipCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showInternshipAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_INTERNSHIP;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_INTERNSHIP;
import static seedu.address.testutil.TypicalInternships.getTypicalInternshipBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.ModifyCommand.EditInternshipDescriptor;
import seedu.address.model.InternshipBook;
import seedu.address.model.InternshipModel;
import seedu.address.model.InternshipModelManager;
import seedu.address.model.InternshipUserPrefs;
import seedu.address.model.internship.Internship;
import seedu.address.testutil.EditInternshipDescriptorBuilder;
import seedu.address.testutil.InternshipBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ModifyCommand.
 */
public class ModifyCommandTest {

    private InternshipModel model = new InternshipModelManager(getTypicalInternshipBook(), new InternshipUserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Internship editedInternship = new InternshipBuilder().build();
        EditInternshipDescriptor descriptor = new EditInternshipDescriptorBuilder(editedInternship).build();
        ModifyCommand modifyCommand = new ModifyCommand(INDEX_FIRST_INTERNSHIP, descriptor);

        String expectedMessage = String.format(
                ModifyCommand.MESSAGE_EDIT_INTERNSHIP_SUCCESS,
                Messages.format(editedInternship));

        InternshipModel expectedModel = new InternshipModelManager(
                new InternshipBook(model.getInternshipBook()),
                new InternshipUserPrefs());
        expectedModel.setInternship(model.getFilteredInternshipList().get(0), editedInternship);

        assertInternshipCommandSuccess(modifyCommand, model, expectedMessage, expectedModel);
    }
    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastInternship = Index.fromOneBased(model.getFilteredInternshipList().size());
        Internship lastInternship = model.getFilteredInternshipList().get(indexLastInternship.getZeroBased());

        InternshipBuilder internshipInList = new InternshipBuilder(lastInternship);
        Internship editedInternship = internshipInList.withCompanyName(VALID_COMPANY_NAME_JANESTREET)
                .withRole(VALID_ROLE_JANESTREET)
                .withDeadline(VALID_DEADLINE_JANESTREET, VALID_STARTDATE_JANESTREET)
                .withRequirements(VALID_REQUIREMENT_JANESTREET).build();

        EditInternshipDescriptor descriptor = new EditInternshipDescriptorBuilder()
                .withCompanyName(VALID_COMPANY_NAME_JANESTREET)
                .withRole(VALID_ROLE_JANESTREET)
                .withDeadline(VALID_DEADLINE_JANESTREET, VALID_STARTDATE_JANESTREET)
                .withRequirements(VALID_REQUIREMENT_JANESTREET).build();
        ModifyCommand modifyCommand = new ModifyCommand(indexLastInternship, descriptor);

        String expectedMessage = String.format(
                ModifyCommand.MESSAGE_EDIT_INTERNSHIP_SUCCESS,
                Messages.format(editedInternship));

        InternshipModel expectedModel = new InternshipModelManager(
                new InternshipBook(model.getInternshipBook()),
                new InternshipUserPrefs());
        expectedModel.setInternship(lastInternship, editedInternship);

        assertInternshipCommandSuccess(modifyCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        ModifyCommand modifyCommand = new ModifyCommand(INDEX_FIRST_INTERNSHIP, new EditInternshipDescriptor());
        Internship editedInternship = model.getFilteredInternshipList().get(INDEX_FIRST_INTERNSHIP.getZeroBased());

        String expectedMessage = String.format(
                ModifyCommand.MESSAGE_EDIT_INTERNSHIP_SUCCESS,
                Messages.format(editedInternship));

        InternshipModel expectedModel = new InternshipModelManager(
                new InternshipBook(model.getInternshipBook()),
                new InternshipUserPrefs());

        assertInternshipCommandSuccess(modifyCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateInternshipUnfilteredList_failure() {
        Internship firstInternship = model.getFilteredInternshipList().get(INDEX_FIRST_INTERNSHIP.getZeroBased());
        EditInternshipDescriptor descriptor = new EditInternshipDescriptorBuilder(firstInternship).build();
        ModifyCommand modifyCommand = new ModifyCommand(INDEX_SECOND_INTERNSHIP, descriptor);

        assertInternshipCommandFailure(modifyCommand, model, ModifyCommand.MESSAGE_DUPLICATE_INTERNSHIP);
    }

    @Test
    public void execute_duplicateInternshipFilteredList_failure() {
        showInternshipAtIndex(model, INDEX_FIRST_INTERNSHIP);

        // edit internship in filtered list into a duplicate in address book
        Internship internshipInList = model.getInternshipBook().getInternshipList()
                .get(INDEX_SECOND_INTERNSHIP.getZeroBased());
        ModifyCommand modifyCommand = new ModifyCommand(INDEX_FIRST_INTERNSHIP,
                new EditInternshipDescriptorBuilder(internshipInList).build());

        assertInternshipCommandFailure(modifyCommand, model, ModifyCommand.MESSAGE_DUPLICATE_INTERNSHIP);
    }

    @Test
    public void execute_invalidInternshipIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredInternshipList().size() + 1);
        EditInternshipDescriptor descriptor = new EditInternshipDescriptorBuilder()
                .withCompanyName(VALID_COMPANY_NAME_OPTIVER).build();
        ModifyCommand modifyCommand = new ModifyCommand(outOfBoundIndex, descriptor);

        assertInternshipCommandFailure(modifyCommand, model, Messages.MESSAGE_INVALID_INTERNSHIP_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidInternshipIndexFilteredList_failure() {
        showInternshipAtIndex(model, INDEX_FIRST_INTERNSHIP);
        Index outOfBoundIndex = INDEX_SECOND_INTERNSHIP;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getInternshipBook().getInternshipList().size());

        ModifyCommand modifyCommand = new ModifyCommand(outOfBoundIndex,
                new EditInternshipDescriptorBuilder().withCompanyName(VALID_COMPANY_NAME_JANESTREET).build());

        assertInternshipCommandFailure(modifyCommand, model, Messages.MESSAGE_INVALID_INTERNSHIP_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final ModifyCommand standardCommand = new ModifyCommand(INDEX_FIRST_INTERNSHIP, DESC_OPTIVER);

        // same values -> returns true
        EditInternshipDescriptor copyDescriptor = new EditInternshipDescriptor(DESC_OPTIVER);
        ModifyCommand commandWithSameValues = new ModifyCommand(INDEX_FIRST_INTERNSHIP, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(
                new CreateCommand(model.getFilteredInternshipList().get(INDEX_FIRST_INTERNSHIP.getZeroBased()))));

        // different index -> returns false
        assertFalse(standardCommand.equals(new ModifyCommand(INDEX_SECOND_INTERNSHIP, DESC_OPTIVER)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new ModifyCommand(INDEX_FIRST_INTERNSHIP, DESC_JANESTREET)));
    }

    @Test
    public void toStringMethod() {
        Index index = Index.fromOneBased(1);
        EditInternshipDescriptor editInternshipDescriptor = new EditInternshipDescriptor();
        ModifyCommand modifyCommand = new ModifyCommand(index, editInternshipDescriptor);
        String expected = ModifyCommand.class.getCanonicalName() + "{index=" + index + ", editInternshipDescriptor="
                + editInternshipDescriptor + "}";
        assertEquals(expected, modifyCommand.toString());
    }

}
