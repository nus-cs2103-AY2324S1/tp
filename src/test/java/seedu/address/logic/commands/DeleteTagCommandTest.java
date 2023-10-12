package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

public class DeleteTagCommandTest {
    @Test
    public void constructor_nullIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DeleteTagCommand(null, List.of(new Tag(VALID_TAG_FRIEND))));
    }

    @Test
    public void constructor_nullTags_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DeleteTagCommand(INDEX_FIRST_PERSON, null));
    }

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new Person(firstPerson);
        editedPerson.removeTags(firstPerson.getTags());
        DeleteTagCommand deleteTagCommand = new DeleteTagCommand(INDEX_FIRST_PERSON, firstPerson.getTags());
        String expectedMessage = String.format(
                DeleteTagCommand.MESSAGE_REMOVE_TAG_SUCCESS, Messages.format(editedPerson));
        ModelManager expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(firstPerson, editedPerson);
        assertCommandSuccess(deleteTagCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_failure() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        DeleteTagCommand deleteTagCommand = new DeleteTagCommand(outOfBoundIndex, List.of(new Tag(VALID_TAG_FRIEND)));
        assertCommandFailure(deleteTagCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        Person personInFilteredList = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new Person(personInFilteredList);
        editedPerson.removeTags(personInFilteredList.getTags());
        DeleteTagCommand deleteTagCommand = new DeleteTagCommand(INDEX_FIRST_PERSON, personInFilteredList.getTags());
        String expectedMessage = String.format(
                DeleteTagCommand.MESSAGE_REMOVE_TAG_SUCCESS, Messages.format(editedPerson));
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(personInFilteredList, editedPerson);
        assertCommandSuccess(deleteTagCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_failure() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());
        DeleteTagCommand deleteTagCommand = new DeleteTagCommand(outOfBoundIndex, List.of(new Tag(VALID_TAG_FRIEND)));
        assertCommandFailure(deleteTagCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void toStringMethod() {
        Index index = Index.fromOneBased(1);
        Collection<Tag> tagsToAdd = new HashSet<>(List.of(new Tag(VALID_TAG_FRIEND)));
        DeleteTagCommand deleteTagCommand = new DeleteTagCommand(index, tagsToAdd);
        String expected = DeleteTagCommand.class.getCanonicalName() + "{index=" + index + ", tagsToAdd="
                + tagsToAdd + "}";
        assertEquals(expected, deleteTagCommand.toString());
    }
}
