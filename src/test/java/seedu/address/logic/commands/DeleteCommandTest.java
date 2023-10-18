package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.getPeople;
import static seedu.address.logic.commands.CommandTestUtil.showPeopleAtIndices;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndices.ONEBASED_FOUR_TO_SIX;
import static seedu.address.testutil.TypicalIndices.ONEBASED_ONE;
import static seedu.address.testutil.TypicalIndices.ONEBASED_ONE_TO_THREE;
import static seedu.address.testutil.TypicalIndices.ONEBASED_ONE_TO_THREE_JUMBLED;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Indices;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
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
    public void isValidIndex() throws CommandException {
        assertTrue(DeleteCommand.isValidIndex(0, 1));
        assertThrows(CommandException.class, () -> DeleteCommand.isValidIndex(1, 1));
        assertThrows(CommandException.class, () -> DeleteCommand.isValidIndex(-1, 1));
    }
    @Test
    public void getPeopleToDelete_validIndices_success() throws CommandException {
        Person[] peopleToDelete = getPeople(model.getFilteredPersonList(), ONEBASED_ONE_TO_THREE);
        DeleteCommand deleteCommand = new DeleteCommand(ONEBASED_ONE_TO_THREE);
        assertArrayEquals(deleteCommand.getPeopleToDelete(model.getFilteredPersonList()), peopleToDelete);
    }

    @Test
    public void getPeopleToDelete_invalidIndices_throwsCommandException() {
        List<Person> unfilteredList = model.getFilteredPersonList();
        int size = unfilteredList.size() + 1;
        Indices outOfBoundIndices = Indices.fromOneBased(new int[]{size, size + 10, size + 20});
        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndices);
        assertThrows(CommandException.class, () -> deleteCommand.getPeopleToDelete(unfilteredList));
    }

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Person[] peopleToDelete = getPeople(model.getFilteredPersonList(), ONEBASED_ONE);

        DeleteCommand deleteCommand = new DeleteCommand(ONEBASED_ONE);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_PERSON_SUCCESS,
                Messages.format(peopleToDelete));

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        for (Person person : peopleToDelete) {
            expectedModel.deletePerson(person);
        }

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validIndicesUnfilteredList_success() {
        Person[] peopleToDelete = getPeople(model.getFilteredPersonList(), ONEBASED_ONE_TO_THREE);

        DeleteCommand deleteCommand = new DeleteCommand(ONEBASED_ONE_TO_THREE);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_PEOPLE_SUCCESS,
                Messages.format(peopleToDelete));

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        for (Person person : peopleToDelete) {
            expectedModel.deletePerson(person);
        }

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndicesUnfilteredList_throwsCommandException() {
        List<Person> unfilteredList = model.getFilteredPersonList();
        int size = unfilteredList.size() + 1;
        Indices outOfBoundIndices = Indices.fromOneBased(new int[]{size, size + 10, size + 20});
        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndices);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Person personToDelete = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(ONEBASED_ONE);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_PERSON_SUCCESS,
                Messages.format(personToDelete));

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deletePerson(personToDelete);
        showNoPerson(expectedModel);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validIndicesFilteredList_success() {
        showPeopleAtIndices(model, ONEBASED_ONE_TO_THREE);

        Person[] peopleToDelete = getPeople(model.getFilteredPersonList(), ONEBASED_ONE_TO_THREE);
        DeleteCommand deleteCommand = new DeleteCommand(ONEBASED_ONE_TO_THREE);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_PEOPLE_SUCCESS,
                Messages.format(peopleToDelete));

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        for (Person person : peopleToDelete) {
            expectedModel.deletePerson(person);
        }
        showNoPerson(expectedModel);
        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndicesFilteredList_throwsCommandException() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Indices outOfBoundIndices = ONEBASED_FOUR_TO_SIX;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndices.getZeroBasedMax() < model.getAddressBook().getPersonList().size());

        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndices);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteCommand deleteFirstCommand = new DeleteCommand(ONEBASED_ONE_TO_THREE);
        DeleteCommand deleteSecondCommand = new DeleteCommand(ONEBASED_FOUR_TO_SIX);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteCommand deleteFirstCommandCopy = new DeleteCommand(ONEBASED_ONE_TO_THREE_JUMBLED);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different indices -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    @Test
    public void toStringMethod() {
        DeleteCommand deleteCommand = new DeleteCommand(ONEBASED_ONE_TO_THREE);
        String expected = DeleteCommand.class.getCanonicalName() + "{targetIndices=" + ONEBASED_ONE_TO_THREE + "}";
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
