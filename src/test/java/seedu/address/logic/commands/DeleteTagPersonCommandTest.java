package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.PersonBuilder;

public class DeleteTagPersonCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndex_success() {
        Set<Tag> tags = new HashSet<>();
        tags.add(new Tag(VALID_TAG_FRIEND));
        DeleteTagPersonCommand deleteTagPersonCommand = new DeleteTagPersonCommand(INDEX_FIRST, tags);

        // Starting state of first person only has friend tag
        Person personToEdit = model.getFilteredPersonList().get(INDEX_FIRST.getZeroBased());

        // Manually remove friend tag
        Set<Tag> newTags = new HashSet<>(personToEdit.getTags());
        newTags.remove(new Tag(VALID_TAG_FRIEND));

        Person editedPerson = new Person(
                personToEdit.getName(),
                personToEdit.getPhone(),
                personToEdit.getEmail(),
                personToEdit.getAddress(),
                newTags);

        String expectedMessage = DeleteTagPersonCommand.formatResultMessage(personToEdit, editedPerson, tags);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.setPerson(personToEdit, editedPerson);

        assertCommandSuccess(deleteTagPersonCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndex_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        DeleteTagPersonCommand deleteTagPersonCommand = new DeleteTagPersonCommand(outOfBoundIndex,
                new HashSet<>());

        assertCommandFailure(deleteTagPersonCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void formatResultMessage_matching() {
        Person personToEdit = new PersonBuilder().withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND).build();

        Set<Tag> tags = new HashSet<>();
        tags.add(new Tag(VALID_TAG_FRIEND));
        tags.add(new Tag(VALID_TAG_HUSBAND));

        Person editPerson = new PersonBuilder(personToEdit).withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND).build();

        Set<Tag> matchingTag = new HashSet<>(Arrays.asList(new Tag(VALID_TAG_FRIEND), new Tag(VALID_TAG_HUSBAND)));
        String matchingTagsString = matchingTag.stream().map(Tag::toString).collect(Collectors.joining(", "));

        String expectedString = String.format(DeleteTagPersonCommand.MESSAGE_DELETE_TAG_PERSON_SUCCESS,
                Messages.format(editPerson))
                + String.format(DeleteTagPersonCommand.MESSAGE_DELETE_TAG_PERSON_MATCHING_TAGS, matchingTagsString);

        assertEquals(expectedString, DeleteTagPersonCommand.formatResultMessage(personToEdit, editPerson, tags));
    }

    @Test
    public void formatResultMessage_missing() {
        Person personToEdit = new PersonBuilder().withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND).build();

        Set<Tag> tags = new HashSet<>();
        tags.add(new Tag("blabla"));

        Person editPerson = new Person(
                personToEdit.getName(),
                personToEdit.getPhone(),
                personToEdit.getEmail(),
                personToEdit.getAddress(),
                new HashSet<>());

        Set<Tag> missingTag = new HashSet<>(Arrays.asList(new Tag("blabla")));
        String missingTagsString = missingTag.stream().map(Tag::toString).collect(Collectors.joining(", "));

        String expectedString = String.format(DeleteTagPersonCommand.MESSAGE_DELETE_TAG_PERSON_SUCCESS,
                Messages.format(editPerson))
                + String.format(DeleteTagPersonCommand.MESSAGE_DELETE_TAG_PERSON_MISSING_TAGS, missingTagsString);

        assertEquals(expectedString, DeleteTagPersonCommand.formatResultMessage(personToEdit, editPerson, tags));
    }

    @Test
    public void formatResultMessage_matchingAndMissing() {
        Person personToEdit = new PersonBuilder().withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND).build();

        Set<Tag> tags = new HashSet<>();
        tags.add(new Tag(VALID_TAG_FRIEND));
        tags.add(new Tag("blabla"));

        Person editPerson = new PersonBuilder(personToEdit).withTags(VALID_TAG_HUSBAND).build();

        Set<Tag> matchingTag = new HashSet<>(Arrays.asList(new Tag(VALID_TAG_FRIEND)));
        Set<Tag> missingTag = new HashSet<>(Arrays.asList(new Tag("blabla")));
        String matchingTagsString = matchingTag.stream().map(Tag::toString).collect(Collectors.joining(", "));
        String missingTagsString = missingTag.stream().map(Tag::toString).collect(Collectors.joining(", "));

        String expectedString = String.format(DeleteTagPersonCommand.MESSAGE_DELETE_TAG_PERSON_SUCCESS,
                Messages.format(editPerson))
                + String.format(DeleteTagPersonCommand.MESSAGE_DELETE_TAG_PERSON_MATCHING_TAGS, matchingTagsString)
                + String.format(DeleteTagPersonCommand.MESSAGE_DELETE_TAG_PERSON_MISSING_TAGS, missingTagsString);

        assertEquals(expectedString, DeleteTagPersonCommand.formatResultMessage(personToEdit, editPerson, tags));
    }

    @Test
    public void equals() {
        DeleteTagPersonCommand deleteTagPersonFirstCommand = new DeleteTagPersonCommand(INDEX_FIRST, new HashSet<>());
        DeleteTagPersonCommand deleteTagPersonSecondCommand = new DeleteTagPersonCommand(INDEX_SECOND, new HashSet<>());

        // same object -> returns true
        assertTrue(deleteTagPersonFirstCommand.equals(deleteTagPersonFirstCommand));

        // same values -> returns true
        DeleteTagPersonCommand deleteTagPersonFirstCommandCopy = new DeleteTagPersonCommand(INDEX_FIRST,
                new HashSet<>());
        assertTrue(deleteTagPersonFirstCommand.equals(deleteTagPersonFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteTagPersonFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteTagPersonFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(deleteTagPersonFirstCommand.equals(deleteTagPersonSecondCommand));

        // different tags -> returns false
        DeleteTagPersonCommand deleteTagPersonThirdCommand = new DeleteTagPersonCommand(INDEX_FIRST,
                new HashSet<>(Arrays.asList(new Tag("new"))));
        assertFalse(deleteTagPersonFirstCommand.equals(deleteTagPersonThirdCommand));
    }

    @Test
    public void toStringMethod() {
        Index targetIndex = Index.fromOneBased(1);
        Set<Tag> tags = new HashSet<>();
        tags.add(new Tag(VALID_TAG_FRIEND));

        DeleteTagPersonCommand deleteTagPersonCommand = new DeleteTagPersonCommand(targetIndex, tags);
        String expected = DeleteTagPersonCommand.class.getCanonicalName() + "{targetIndex=" + targetIndex
                + ", tagsToDelete="
                + tags + "}";
        assertEquals(expected, deleteTagPersonCommand.toString());
    }
}
