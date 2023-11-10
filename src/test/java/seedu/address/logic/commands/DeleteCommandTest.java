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
import static seedu.address.testutil.TypicalPersons.getTypicalStatusPerson;
import static seedu.address.testutil.TypicalPersons.getTypicalTagAndStatusPerson;
import static seedu.address.testutil.TypicalPersons.getTypicalTagPerson;
import static seedu.address.testutil.TypicalPredicateLists.PREDICATE_LIST_CONTAINING_STATUS_ONE;
import static seedu.address.testutil.TypicalPredicateLists.PREDICATE_LIST_CONTAINING_STATUS_TWO;
import static seedu.address.testutil.TypicalPredicateLists.PREDICATE_LIST_CONTAINING_TAG_AND_STATUS_MIX;
import static seedu.address.testutil.TypicalPredicateLists.PREDICATE_LIST_CONTAINING_TAG_AND_STATUS_ONE;
import static seedu.address.testutil.TypicalPredicateLists.PREDICATE_LIST_CONTAINING_TAG_AND_STATUS_TWO;
import static seedu.address.testutil.TypicalPredicateLists.PREDICATE_LIST_CONTAINING_TAG_ONE;
import static seedu.address.testutil.TypicalPredicateLists.PREDICATE_LIST_CONTAINING_TAG_TWO;
import static seedu.address.testutil.TypicalTags.NO_MATCHING_TAG_NAME_STRING;
import static seedu.address.testutil.TypicalTags.TEST_TAG_NAME_STRING;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.model.EventBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.person.StatusContainsKeywordsPredicate;
import seedu.address.model.person.StatusTypes;
import seedu.address.model.person.TagContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new EventBook(), new UserPrefs());

    // Delete by index tests
    @Test
    public void execute_validIndexUnfilteredList_success() {
        Person personToDelete = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST_PERSON);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_PERSON_SUCCESS,
                Messages.format(personToDelete));

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new EventBook(), new UserPrefs());
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

        Model expectedModel = new ModelManager(model.getAddressBook(), new EventBook(), new UserPrefs());
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

    // Delete by tags tests
    @Test
    public void execute_validTagsUnfilteredList_success() {
        Person personToDelete = getTypicalTagPerson();

        TagContainsKeywordsPredicate tagPredicate = new TagContainsKeywordsPredicate(List.of(TEST_TAG_NAME_STRING));
        List<Predicate<Person>> predicateList = new ArrayList<>() {{
                add(tagPredicate);
            }};

        DeleteCommand deleteCommand = new DeleteCommand(predicateList);
        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_PERSON_SUCCESS,
                Messages.format(personToDelete));

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new EventBook(), new UserPrefs());
        expectedModel.deletePerson(personToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noMatchingTagsUnfilteredList_throwsCommandException() {
        TagContainsKeywordsPredicate tagPredicate =
                new TagContainsKeywordsPredicate(List.of(NO_MATCHING_TAG_NAME_STRING));
        List<Predicate<Person>> predicateList = new ArrayList<>() {{
                add(tagPredicate);
            }};

        DeleteCommand deleteCommand = new DeleteCommand(predicateList);
        assertCommandFailure(deleteCommand, model, DeleteCommand.MESSAGE_PERSONS_NOT_FOUND);
    }

    // Delete by status tests
    @Test
    public void execute_validStatusUnfilteredList_success() {
        Person personToDelete = getTypicalStatusPerson(); // Person with rejected status
        String testStatus = StatusTypes.REJECTED.toString();
        StatusContainsKeywordsPredicate statusPredicate = new StatusContainsKeywordsPredicate(List.of(testStatus));
        List<Predicate<Person>> predicateList = new ArrayList<>() {{
                add(statusPredicate);
            }};


        DeleteCommand deleteCommand = new DeleteCommand(predicateList);
        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_PERSON_SUCCESS,
                Messages.format(personToDelete));

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new EventBook(), new UserPrefs());
        expectedModel.deletePerson(personToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noMatchingStatusUnfilteredList_throwsCommandException() {
        String testStatus = StatusTypes.OFFERED.toString();
        StatusContainsKeywordsPredicate statusPredicate = new StatusContainsKeywordsPredicate(List.of(testStatus));
        List<Predicate<Person>> predicateList = new ArrayList<>() {{
                add(statusPredicate);
            }};

        DeleteCommand deleteCommand = new DeleteCommand(predicateList);
        assertCommandFailure(deleteCommand, model, DeleteCommand.MESSAGE_PERSONS_NOT_FOUND);
    }

    // Delete by tags and status tests
    @Test
    public void execute_validTagsAndStatusUnfilteredList_success() {
        Person personToDelete = getTypicalTagAndStatusPerson(); // Benson

        TagContainsKeywordsPredicate tagPredicate = new TagContainsKeywordsPredicate(List.of(TEST_TAG_NAME_STRING));

        String testStatus = StatusTypes.REJECTED.toString();
        StatusContainsKeywordsPredicate statusPredicate = new StatusContainsKeywordsPredicate(List.of(testStatus));

        List<Predicate<Person>> predicateList = new ArrayList<>() {{
                add(tagPredicate);
                add(statusPredicate);
            }};

        DeleteCommand deleteCommand = new DeleteCommand(predicateList);
        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_PERSON_SUCCESS,
                Messages.format(personToDelete));

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new EventBook(), new UserPrefs());
        expectedModel.deletePerson(personToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals_index() {
        // Delete by index
        DeleteCommand deleteByIndexFirstCommand = new DeleteCommand(INDEX_FIRST_PERSON);
        DeleteCommand deleteByIndexSecondCommand = new DeleteCommand(INDEX_SECOND_PERSON);

        // same object -> returns true
        assertTrue(deleteByIndexFirstCommand.equals(deleteByIndexFirstCommand));

        // same values (delete by index) -> returns true
        DeleteCommand deleteByIndexFirstCommandCopy = new DeleteCommand(INDEX_FIRST_PERSON);
        assertTrue(deleteByIndexFirstCommand.equals(deleteByIndexFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteByIndexFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteByIndexFirstCommand.equals(null));

        // different person or set of tags -> returns false
        assertFalse(deleteByIndexFirstCommand.equals(deleteByIndexSecondCommand));
    }

    @Test
    public void equals_tags() {
        DeleteCommand deleteByTagsFirstCommand = new DeleteCommand(PREDICATE_LIST_CONTAINING_TAG_ONE);
        DeleteCommand deleteByTagsSecondCommand = new DeleteCommand(PREDICATE_LIST_CONTAINING_TAG_TWO);

        // same object -> returns true
        assertTrue(deleteByTagsFirstCommand.equals(deleteByTagsFirstCommand));

        // same values (delete by tags) -> returns true
        DeleteCommand deleteByTagsFirstCommandCopy = new DeleteCommand(PREDICATE_LIST_CONTAINING_TAG_ONE);
        assertTrue(deleteByTagsFirstCommand.equals(deleteByTagsFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteByTagsFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteByTagsFirstCommand.equals(null));

        // different person or set of tags -> returns false
        assertFalse(deleteByTagsFirstCommand.equals(deleteByTagsSecondCommand));
    }

    @Test
    public void equals_status() {
        DeleteCommand deleteByStatusFirstCommand = new DeleteCommand(PREDICATE_LIST_CONTAINING_STATUS_ONE);
        DeleteCommand deleteByStatusSecondCommand = new DeleteCommand(PREDICATE_LIST_CONTAINING_STATUS_TWO);

        // same object -> returns true
        assertTrue(deleteByStatusFirstCommand.equals(deleteByStatusFirstCommand));

        // same values (delete by status) -> returns true
        DeleteCommand deleteByStatusFirstCommandCopy = new DeleteCommand(PREDICATE_LIST_CONTAINING_STATUS_ONE);
        assertTrue(deleteByStatusFirstCommand.equals(deleteByStatusFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteByStatusFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteByStatusFirstCommand.equals(null));

        // different person or set of tags -> returns false
        assertFalse(deleteByStatusFirstCommand.equals(deleteByStatusSecondCommand));
    }

    @Test
    public void equals_tagsAndStatus() {
        DeleteCommand deleteByTagsAndStatusFirstCommand =
                new DeleteCommand(PREDICATE_LIST_CONTAINING_TAG_AND_STATUS_ONE);
        DeleteCommand deleteByTagsAndStatusSecondCommand =
                new DeleteCommand(PREDICATE_LIST_CONTAINING_TAG_AND_STATUS_TWO);
        DeleteCommand deleteByTagsAndStatusMixCommand =
                new DeleteCommand(PREDICATE_LIST_CONTAINING_TAG_AND_STATUS_MIX);

        // same object -> returns true
        assertTrue(deleteByTagsAndStatusFirstCommand.equals(deleteByTagsAndStatusFirstCommand));

        // same values (delete by tags and status) -> returns true
        DeleteCommand deleteByTagsAndStatusFirstCommandCopy =
                new DeleteCommand(PREDICATE_LIST_CONTAINING_TAG_AND_STATUS_ONE);
        assertTrue(deleteByTagsAndStatusFirstCommand.equals(deleteByTagsAndStatusFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteByTagsAndStatusFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteByTagsAndStatusFirstCommand.equals(null));

        // different person or set of tags -> returns false
        assertFalse(deleteByTagsAndStatusFirstCommand.equals(deleteByTagsAndStatusSecondCommand));
        assertFalse(deleteByTagsAndStatusFirstCommand.equals(deleteByTagsAndStatusMixCommand));
    }

    @Test
    public void toString_index() {
        Index targetIndex = Index.fromOneBased(1);
        DeleteCommand deleteCommand = new DeleteCommand(targetIndex);
        String expectedToString = DeleteCommand.class.getCanonicalName() + "{targetIndex=" + targetIndex + "}";
        assertEquals(expectedToString, deleteCommand.toString());
    }

    @Test
    public void toString_tags() {
        DeleteCommand deleteCommand = new DeleteCommand(PREDICATE_LIST_CONTAINING_TAG_ONE);
        String expectedString = DeleteCommand.class.getCanonicalName()
                + String.format("{predicateList=%s}", PREDICATE_LIST_CONTAINING_TAG_ONE.toString());
        assertEquals(expectedString, deleteCommand.toString());
    }

    @Test
    public void toString_status() {
        DeleteCommand deleteCommand = new DeleteCommand(PREDICATE_LIST_CONTAINING_STATUS_ONE);
        String expectedString = DeleteCommand.class.getCanonicalName()
                + String.format("{predicateList=%s}", PREDICATE_LIST_CONTAINING_STATUS_ONE.toString());
        assertEquals(expectedString, deleteCommand.toString());
    }

    @Test
    public void toString_tagsAndStatus() {
        DeleteCommand deleteCommand = new DeleteCommand(PREDICATE_LIST_CONTAINING_TAG_AND_STATUS_ONE);
        String expectedString = DeleteCommand.class.getCanonicalName()
                + String.format("{predicateList=%s}", PREDICATE_LIST_CONTAINING_TAG_AND_STATUS_ONE.toString());
        assertEquals(expectedString, deleteCommand.toString());
    }

    @Test
    public void toString_invalid() {
        DeleteCommand deleteCommand = new DeleteCommand(Index.getDefaultIndex());
        String expectedToString = "seedu.address.logic.commands.DeleteCommand{invalid=No valid target specified}";
        assertEquals(expectedToString, deleteCommand.toString());
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoPerson(Model model) {
        model.updateFilteredPersonList(p -> false);

        assertTrue(model.getFilteredPersonList().isEmpty());
    }
}
