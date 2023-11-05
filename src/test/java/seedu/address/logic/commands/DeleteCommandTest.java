package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validSingleIndexUnfilteredList_success() {
        Person personToDelete = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(List.of(INDEX_FIRST_PERSON));
        StringBuilder expectedMessageBuilder =
                new StringBuilder(String.format(DeleteCommand.MESSAGE_DELETE_PERSON_SUCCESS_HEADER, 1));
        expectedMessageBuilder.append(String.format("\n%1$d. %2$s", 1,
                Messages.formatShortForm(personToDelete)));

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deletePerson(personToDelete);
        expectedModel.canRedo();

        assertCommandSuccess(deleteCommand, model, expectedMessageBuilder.toString(), expectedModel);
    }

    @Test
    public void execute_validMultipleIndexUnfilteredList_success() {
        List<Index> indexList = Arrays.asList(Index.fromOneBased(1),
                Index.fromOneBased(2),
                Index.fromOneBased(4));
        StringBuilder expectedMessageBuilder =
                new StringBuilder(String.format(DeleteCommand.MESSAGE_DELETE_PERSON_SUCCESS_HEADER, indexList.size()));
        List<Person> personsToDelete = new ArrayList<>();
        for (int i = 0; i < indexList.size(); i++) {
            Index index = indexList.get(i);
            Person currPerson = model.getFilteredPersonList().get(index.getZeroBased());
            personsToDelete.add(currPerson);
            expectedMessageBuilder.append(String.format("\n%1$d. %2$s", i + 1,
                            Messages.formatShortForm(currPerson)));
        }
        DeleteCommand deleteCommand = new DeleteCommand(indexList);
        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        for (Person person: personsToDelete) {
            expectedModel.deletePerson(person);
        }
        expectedModel.commit();
        assertCommandSuccess(deleteCommand, model, expectedMessageBuilder.toString(), expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index validIndex = Index.fromOneBased(model.getFilteredPersonList().size());
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        List<Index> invalidIndexes = Arrays.asList(validIndex, outOfBoundIndex);
        DeleteCommand deleteCommand = new DeleteCommand(invalidIndexes);
        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validSingleIndexFilteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Person personToDelete = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(List.of(INDEX_FIRST_PERSON));

        StringBuilder expectedMessageBuilder =
                new StringBuilder(String.format(DeleteCommand.MESSAGE_DELETE_PERSON_SUCCESS_HEADER, 1));
        expectedMessageBuilder.append(String.format("\n%1$d. %2$s", 1,
                Messages.formatShortForm(personToDelete)));

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deletePerson(personToDelete);
        showNoPerson(expectedModel);
        expectedModel.commit();

        assertCommandSuccess(deleteCommand, model, expectedMessageBuilder.toString(), expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        DeleteCommand deleteCommand = new DeleteCommand(List.of(outOfBoundIndex));

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        List<Index> firstIndexList = Arrays.asList(Index.fromOneBased(1), Index.fromOneBased(2));
        List<Index> secondIndexList = Arrays.asList(Index.fromOneBased(2), Index.fromOneBased(3));

        DeleteCommand deleteFirstCommand = new DeleteCommand(firstIndexList);
        DeleteCommand deleteSecondCommand = new DeleteCommand(secondIndexList);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteCommand deleteFirstCommandCopy = new DeleteCommand(firstIndexList);
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
        List<Index> indexList = Arrays.asList(Index.fromOneBased(1), Index.fromOneBased(2));
        DeleteCommand deleteCommand = new DeleteCommand(indexList);
        String expected = DeleteCommand.class.getCanonicalName() + "{targetIndexes=" + indexList
                + "}";
        assertEquals(expected, deleteCommand.toString());
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoPerson(Model model) {
        model.updateFilteredPersonList(p -> false);

        assertTrue(model.getFilteredPersonList().isEmpty());
    }
}
