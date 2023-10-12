package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIENDS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_SMART;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.TypicalPersons;


public class AddTagCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_addTag_success() {
        Person editedStudent = new PersonBuilder(model.getFilteredPersonList().get(0))
            .withTags(VALID_TAG_FRIENDS, VALID_TAG_SMART).build();
        Set<Tag> tagToAdd = SampleDataUtil.getTagSet(VALID_TAG_SMART);
        AddTagCommand addTagCommand = new AddTagCommand(INDEX_FIRST_PERSON,
            tagToAdd);

        String expectedMessage = String.format(TagCommand.MESSAGE_ADD_TAG_SUCCESS,
            editedStudent.getName()) + tagToAdd;

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), editedStudent);

        assertCommandSuccess(addTagCommand, model, expectedMessage, expectedModel);
        assertEquals(editedStudent.getTags(), model.getFilteredPersonList().get(0).getTags());
    }

    @Test
    public void execute_indexOutOfBound_failure() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        Index outOfBoundIndex = INDEX_SECOND_PERSON;

        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        AddTagCommand addTagCommand = new AddTagCommand(outOfBoundIndex, TypicalPersons.ALICE.getTags());

        assertCommandFailure(addTagCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final AddTagCommand standardCommand = new AddTagCommand(INDEX_FIRST_PERSON,
            TypicalPersons.ALICE.getTags());

        AddTagCommand commandWithSameValue = new AddTagCommand(INDEX_FIRST_PERSON,
            SampleDataUtil.getTagSet(VALID_TAG_FRIENDS));

        assertTrue(standardCommand.equals(commandWithSameValue));

        assertTrue(standardCommand.equals(standardCommand));

        assertFalse(standardCommand.equals(null));

        assertFalse(standardCommand.equals(new ClearCommand()));
    }

    @Test
    public void toStringMethod() {
        Index targetIndex = Index.fromOneBased(1);
        AddTagCommand addTagCommand = new AddTagCommand(targetIndex, TypicalPersons.ALICE.getTags());
        String expected = AddTagCommand.class.getCanonicalName() + "{tags=" + TypicalPersons.ALICE.getTags() + "}";
        assertEquals(expected, addTagCommand.toString());
    }
}
