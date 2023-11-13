package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.OPTIONAL_TAG_G01;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_G01;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_T09;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.logic.commands.DeleteCommand.MESSAGE_NO_STUDENTS;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBookManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.predicate.ContainsTagPredicate;
import seedu.address.model.predicate.SerializablePredicate;
import seedu.address.model.tag.Tag;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteCommandTest {

    private Model model = new ModelManager(getTypicalAddressBookManager(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Person personToDelete = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST_PERSON);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_PERSON_SUCCESS,
                Messages.format(personToDelete));

        ModelManager expectedModel = new ModelManager(model.getAddressBookManager(), new UserPrefs());
        expectedModel.deletePerson(personToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Person personToDelete = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST_PERSON);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_PERSON_SUCCESS,
                Messages.format(personToDelete));

        Model expectedModel = new ModelManager(model.getAddressBookManager(), new UserPrefs());
        expectedModel.deletePerson(personToDelete);
        showNoPerson(expectedModel);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_allWithNoTag_success() {
        Optional<Tag> tag = Optional.empty();
        ContainsTagPredicate pred = new ContainsTagPredicate(tag);
        DeleteCommand deleteCommand = new DeleteCommand(tag, pred);

        ModelManager expectedModel = new ModelManager(model.getAddressBookManager(), new UserPrefs());
        List<Person> personsToDelete = new ArrayList<>(expectedModel.getFilteredPersonList());
        for (Person p : personsToDelete) {
            expectedModel.deletePerson(p);
        }
        String expectedNameList = personsToDelete.stream().map(person -> Messages.format(person))
                .collect(Collectors.joining(",\n"));

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_NO_TAG_SUCCESS,
                expectedModel.getAddressBook().getCourseCode(), expectedNameList);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_allWithTagWithNoStudents_showsNoStudentsMessage() {
        Optional<Tag> tag = Optional.of(new Tag("UNUSEDTAG"));
        ContainsTagPredicate pred = new ContainsTagPredicate(tag);
        DeleteCommand deleteCommand = new DeleteCommand(tag, pred);

        ModelManager expectedModel = new ModelManager(model.getAddressBookManager(), new UserPrefs());

        String expectedMessage = String.format(MESSAGE_NO_STUDENTS, String.format("%s Tutorial Group %s",
                expectedModel.getAddressBook().getCourseCode(), tag.get().getTagName()));

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_allWithTag_success() {
        Optional<Tag> tag = OPTIONAL_TAG_G01;
        ContainsTagPredicate pred = new ContainsTagPredicate(tag);
        DeleteCommand deleteCommand = new DeleteCommand(tag, pred);

        ModelManager expectedModel = new ModelManager(model.getAddressBookManager(), new UserPrefs());
        // From TypicalPersons, Person expected to be deleted is BENSON
        expectedModel.deletePerson(BENSON);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_TAGGED_SUCCESS,
                expectedModel.getAddressBook().getCourseCode(), tag.get().getTagName(), Messages.format(BENSON));

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        DeleteCommand deleteOneFirstCommand = new DeleteCommand(INDEX_FIRST_PERSON);
        DeleteCommand deleteOneSecondCommand = new DeleteCommand(INDEX_SECOND_PERSON);
        Optional<Tag> tag1 = Optional.of(Tag.create(VALID_TAG_G01));
        Optional<Tag> tag2 = Optional.of(Tag.create(VALID_TAG_T09));
        DeleteCommand deleteAllFirstCommand = new DeleteCommand(tag1, ContainsTagPredicate.create(tag1));
        DeleteCommand deleteAllSecondCommand = new DeleteCommand(tag2, ContainsTagPredicate.create(tag2));

        // same object -> returns true
        assertTrue(deleteOneFirstCommand.equals(deleteOneFirstCommand));
        assertTrue(deleteAllFirstCommand.equals(deleteAllFirstCommand));

        // same values -> returns true
        DeleteCommand deleteOneFirstCommandCopy = new DeleteCommand(INDEX_FIRST_PERSON);
        assertTrue(deleteOneFirstCommand.equals(deleteOneFirstCommandCopy));
        DeleteCommand deleteAllFirstCommandCopy = new DeleteCommand(tag1, ContainsTagPredicate.create(tag1));
        assertTrue(deleteAllFirstCommand.equals(deleteAllFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteOneFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteOneFirstCommand.equals(null));
        assertFalse(deleteAllFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(deleteOneFirstCommand.equals(deleteOneSecondCommand));

        // different tags -> returns false
        assertFalse(deleteAllFirstCommand.equals(deleteAllSecondCommand));
    }

    @Test
    public void toStringMethod() {
        Index targetIndex = Index.fromOneBased(1);
        DeleteCommand deleteCommand = new DeleteCommand(targetIndex);
        String expected = DeleteCommand.class.getCanonicalName() + "{targetIndex=" + targetIndex + "}";
        assertEquals(expected, deleteCommand.toString());
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoPerson(Model model) {
        model.clearFilters();
        model.addFilter(new SerializablePredicate(unused -> false));

        assertTrue(model.getFilteredPersonList().isEmpty());
    }
}
