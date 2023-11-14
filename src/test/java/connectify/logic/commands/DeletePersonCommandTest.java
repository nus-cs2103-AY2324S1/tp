package connectify.logic.commands;

import static connectify.logic.commands.CommandTestUtil.assertCommandFailure;
import static connectify.logic.commands.CommandTestUtil.assertCommandSuccess;
import static connectify.logic.commands.CommandTestUtil.showPersonAtIndex;
import static connectify.testutil.TypicalIndexes.INDEX_FIRST_COMPANY;
import static connectify.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static connectify.testutil.TypicalIndexes.INDEX_SECOND_COMPANY;
import static connectify.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static connectify.testutil.TypicalIndexes.INDEX_THIRD_PERSON;
import static connectify.testutil.TypicalPersons.getTypicalAddressBook;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import connectify.commons.core.index.Index;
import connectify.logic.Messages;
import connectify.model.Model;
import connectify.model.ModelManager;
import connectify.model.UserPrefs;
import connectify.model.company.Company;
import connectify.model.person.Person;


/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeletePersonCommand}.
 */
public class DeletePersonCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexesUnfilteredList_success() {
        Person personToDelete = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        DeletePersonCommand deletePersonCommand = new DeletePersonCommand(INDEX_FIRST_COMPANY, INDEX_FIRST_PERSON);

        String expectedMessage = String.format(DeletePersonCommand.MESSAGE_DELETE_PERSON_SUCCESS,
                Messages.format(personToDelete));

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        Company expectedCompanyToUpdate = expectedModel.getFilteredCompanyList()
                .get(INDEX_FIRST_COMPANY.getZeroBased());
        Company editedCompany = expectedCompanyToUpdate.deletePersonFromCompany(personToDelete);
        expectedModel.setCompany(expectedCompanyToUpdate, editedCompany);
        expectedModel.deletePerson(personToDelete);

        assertCommandSuccess(deletePersonCommand, model, expectedMessage, expectedModel);

        assertFalse(model.getFilteredPersonList().contains(personToDelete));
        assertEquals(expectedModel.getFilteredPersonList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundPersonIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        DeletePersonCommand deletePersonCommand = new DeletePersonCommand(INDEX_FIRST_COMPANY, outOfBoundPersonIndex);

        assertCommandFailure(deletePersonCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        Company companyToDeleteFrom = model.getFilteredCompanyList().get(INDEX_SECOND_COMPANY.getZeroBased());

        Person personToDelete = companyToDeleteFrom.getPersonList().get(0);
        assertTrue(companyToDeleteFrom.getPersonList().asList().contains(personToDelete));

        DeletePersonCommand deletePersonCommand = new DeletePersonCommand(INDEX_SECOND_COMPANY, INDEX_FIRST_PERSON);

        String expectedMessage = String.format(DeletePersonCommand.MESSAGE_DELETE_PERSON_SUCCESS,
                Messages.format(personToDelete));

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        Company expectedCompanyToUpdate = expectedModel.getFilteredCompanyList()
                                                .get(INDEX_SECOND_COMPANY.getZeroBased());
        Company editedCompany = expectedCompanyToUpdate.deletePersonFromCompany(personToDelete);
        expectedModel.setCompany(expectedCompanyToUpdate, editedCompany);
        expectedModel.deletePerson(personToDelete);

        assertCommandSuccess(deletePersonCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidCompanyIndex_throwsCommandException() {
        Index outOfBoundCompanyIndex = Index.fromOneBased(model.getFilteredCompanyList().size() + 1);
        DeletePersonCommand deletePersonCommand = new DeletePersonCommand(outOfBoundCompanyIndex, INDEX_FIRST_PERSON);
        assertCommandFailure(deletePersonCommand, model, Messages.MESSAGE_INVALID_COMPANY_DISPLAYED_INDEX);
    }


    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        // Our typical address book has only 2 persons
        Index outOfBoundPersonIndex = INDEX_THIRD_PERSON;

        // Ensure that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundPersonIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        DeletePersonCommand deletePersonCommand = new DeletePersonCommand(INDEX_FIRST_COMPANY, outOfBoundPersonIndex);

        assertCommandFailure(deletePersonCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeletePersonCommand deleteFirstCommand = new DeletePersonCommand(INDEX_FIRST_COMPANY, INDEX_FIRST_PERSON);
        DeletePersonCommand deleteSecondCommand = new DeletePersonCommand(INDEX_FIRST_COMPANY, INDEX_SECOND_PERSON);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeletePersonCommand deleteFirstCommandCopy = new DeletePersonCommand(INDEX_FIRST_COMPANY, INDEX_FIRST_PERSON);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    @Test
    public void toStringMethod() {
        DeletePersonCommand deletePersonCommand = new DeletePersonCommand(INDEX_FIRST_COMPANY, INDEX_FIRST_PERSON);
        String expected = DeletePersonCommand.class.getCanonicalName() + "{companyIndex=" + INDEX_FIRST_COMPANY
                + ", personIndex=" + INDEX_FIRST_PERSON + "}";
        assertEquals(expected, deletePersonCommand.toString());
    }
}
