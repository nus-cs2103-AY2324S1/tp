package connectify.logic.commands;

import static connectify.logic.commands.CommandTestUtil.assertCommandSuccess;
import static connectify.testutil.TypicalIndexes.INDEX_FIRST_COMPANY;
import static connectify.testutil.TypicalPersons.getTypicalAddressBook;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import connectify.commons.core.index.Index;
import connectify.logic.Messages;
import connectify.model.AddressBook;
import connectify.model.Model;
import connectify.model.ModelManager;
import connectify.model.UserPrefs;
import connectify.model.company.Company;
import connectify.model.company.CompanyNote;
import connectify.testutil.CompanyBuilder;

public class CompanyNoteCommandTest {
    private static final String NOTE_STUB = "Some note";

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_addNote_success() {
        Company companyWithoutNote = model.getFilteredCompanyList().get(INDEX_FIRST_COMPANY.getZeroBased());
        Company editedCompanyWithNote = new CompanyBuilder(companyWithoutNote).withNote(NOTE_STUB).build();

        CompanyNoteCommand companyNoteCommand = new CompanyNoteCommand(INDEX_FIRST_COMPANY,
                new CompanyNote(editedCompanyWithNote.getNote().getContent()));

        String expectedMessage = String.format(CompanyNoteCommand.MESSAGE_ADD_NOTE_SUCCESS,
                Messages.format(editedCompanyWithNote));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setCompany(companyWithoutNote, editedCompanyWithNote);

        assertCommandSuccess(companyNoteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_deleteNote_success() {
        Company companyWithoutNote = model.getFilteredCompanyList().get(INDEX_FIRST_COMPANY.getZeroBased());
        Company editedCompanyWithNote = new CompanyBuilder(companyWithoutNote).withNote(NOTE_STUB).build();

        CompanyNoteCommand companyNoteCommand = new CompanyNoteCommand(INDEX_FIRST_COMPANY,
                new CompanyNote("")); // Empty note to delete note from selected company

        String expectedMessage = String.format(CompanyNoteCommand.MESSAGE_DELETE_NOTE_SUCCESS,
                Messages.format(companyWithoutNote));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        model.setCompany(companyWithoutNote, editedCompanyWithNote);

        assertCommandSuccess(companyNoteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidCompanyIndex_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredCompanyList().size() + 1);
        CompanyNoteCommand companyNoteCommand = new CompanyNoteCommand(outOfBoundIndex,
                new CompanyNote(""));

        CommandTestUtil.assertCommandFailure(companyNoteCommand, model,
                Messages.MESSAGE_INVALID_COMPANY_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final CompanyNoteCommand standardCommand = new CompanyNoteCommand(INDEX_FIRST_COMPANY,
                new CompanyNote(
                        "Some note"));

        // same values -> returns true
        CompanyNoteCommand commandWithSameValues = new CompanyNoteCommand(INDEX_FIRST_COMPANY,
                new CompanyNote(
                        "Some note"));
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new CompanyNoteCommand(INDEX_FIRST_COMPANY,
                new CompanyNote(
                        ""))));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new CompanyNoteCommand(INDEX_FIRST_COMPANY,
                new CompanyNote(
                        "Some other note"))));
    }

}
