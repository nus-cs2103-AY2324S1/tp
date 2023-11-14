package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for AddTagPersonCommand.
 */
public class AddTagPersonCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_unfilteredList_success() {
        Tag tag = new Tag("caterer");
        Set<Tag> tags = new HashSet<>();
        tags.add(tag);

        AddTagPersonCommand addTagPersonCommand = new AddTagPersonCommand(INDEX_FIRST, tags);

        Person personToEdit = model.getFilteredPersonList().get(INDEX_FIRST.getZeroBased());
        Set<Tag> existingTags = personToEdit.getTags();
        Set<Tag> updatedTags = new HashSet<>(existingTags);
        updatedTags.add(tag);

        Set<Tag> newTags = new HashSet<>();
        Set<Tag> oldTags = new HashSet<>();

        Set<Tag> commonTags = new HashSet<>(existingTags);
        commonTags.retainAll(tags);

        oldTags.addAll(commonTags);

        newTags.addAll(tags);
        newTags.removeAll(existingTags);

        Person editedPerson = new PersonBuilder(personToEdit).setTags(updatedTags).build();

        String expectedMessage = String.format(AddTagPersonCommand.MESSAGE_EDIT_PERSON_SUCCESS,
                Messages.format(editedPerson), AddTagPersonCommand.setToString(newTags),
                AddTagPersonCommand.setToString(oldTags));

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.setPerson(personToEdit, editedPerson);

        assertCommandSuccess(addTagPersonCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Tag tag = new Tag("caterer");
        Set<Tag> tags = new HashSet<>();
        tags.add(tag);

        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        AddTagPersonCommand addTagPersonCommand = new AddTagPersonCommand(outOfBoundIndex, tags);

        assertCommandFailure(addTagPersonCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_filteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST);

        Tag tag = new Tag("caterer");
        Set<Tag> tags = new HashSet<>();
        tags.add(tag);

        Person personToEdit = model.getFilteredPersonList().get(INDEX_FIRST.getZeroBased());
        AddTagPersonCommand addTagPersonCommand = new AddTagPersonCommand(INDEX_FIRST, tags);

        Set<Tag> existingTags = personToEdit.getTags();
        Set<Tag> updatedTags = new HashSet<>(existingTags);
        updatedTags.add(tag);

        Set<Tag> newTags = new HashSet<>();
        Set<Tag> oldTags = new HashSet<>();

        Set<Tag> commonTags = new HashSet<>(existingTags);
        commonTags.retainAll(tags);

        oldTags.addAll(commonTags);

        newTags.addAll(tags);
        newTags.removeAll(existingTags);

        Person editedPerson = new PersonBuilder(personToEdit).setTags(updatedTags).build();

        String expectedMessage = String.format(AddTagPersonCommand.MESSAGE_EDIT_PERSON_SUCCESS,
                Messages.format(editedPerson), AddTagPersonCommand.setToString(newTags),
                AddTagPersonCommand.setToString(oldTags));

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.setPerson(personToEdit, editedPerson);

        assertCommandSuccess(addTagPersonCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        Tag tag = new Tag("caterer");
        Set<Tag> tags = new HashSet<>();
        tags.add(tag);

        showPersonAtIndex(model, INDEX_FIRST);

        Index outOfBoundIndex = INDEX_SECOND;
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        AddTagPersonCommand addTagPersonCommand = new AddTagPersonCommand(outOfBoundIndex, tags);

        assertCommandFailure(addTagPersonCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        Tag tag = new Tag("caterer");
        Set<Tag> tags = new HashSet<>();
        tags.add(tag);

        AddTagPersonCommand addTagPersonCommand1 = new AddTagPersonCommand(INDEX_FIRST, tags);
        AddTagPersonCommand addTagPersonCommand2 = new AddTagPersonCommand(INDEX_FIRST, tags);

        // Different object -> returns false
        assertFalse(addTagPersonCommand1.equals(1));

        // Same values -> returns true
        assertTrue(addTagPersonCommand1.equals(addTagPersonCommand1));
        assertTrue(addTagPersonCommand1.equals(addTagPersonCommand2));

        // Different index -> returns false
        AddTagPersonCommand addTagPersonCommandDifferentIndex = new AddTagPersonCommand(INDEX_SECOND, tags);
        assertFalse(addTagPersonCommand1.equals(addTagPersonCommandDifferentIndex));

        // Different tags -> returns false
        Set<Tag> differentTags = new HashSet<>();
        differentTags.add(new Tag("differentTag"));
        AddTagPersonCommand addTagPersonCommandDifferentTags = new AddTagPersonCommand(INDEX_FIRST, differentTags);
        assertFalse(addTagPersonCommand1.equals(addTagPersonCommandDifferentTags));
    }

    @Test
    public void toStringMethod() {
        Index index = Index.fromOneBased(1);
        Tag tag = new Tag("caterer");
        Set<Tag> tags = new HashSet<>();
        tags.add(tag);

        AddTagPersonCommand addTagPersonCommand = new AddTagPersonCommand(INDEX_FIRST, tags);
        String expected = AddTagPersonCommand.class.getCanonicalName() + "{index=" + index + ", tagsToAdd="
                + tags + "}";
        assertEquals(expected, addTagPersonCommand.toString());
    }

}
