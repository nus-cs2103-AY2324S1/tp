package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIENDS;
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

public class DeleteTagCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_deleteTag_success() {
        Person editedStudent = new PersonBuilder(model.getFilteredPersonList().get(1))
            .withTags(VALID_TAG_FRIENDS).build();
        Set<Tag> tagToDelete = SampleDataUtil.getTagSet("owesMoney");
        DeleteTagCommand deleteTagCommand = new DeleteTagCommand(INDEX_SECOND_PERSON,
            tagToDelete);

        String expectedMessage = String.format(TagCommand.MESSAGE_DELETE_TAG_SUCCESS,
            editedStudent.getName()) + tagToDelete;

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(1), editedStudent);

        assertCommandSuccess(deleteTagCommand, model, expectedMessage, expectedModel);
        assertEquals(editedStudent.getTags(), model.getFilteredPersonList().get(1).getTags());
    }

    @Test
    public void execute_indexOutOfBound_failure() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        Index outOfBoundIndex = INDEX_SECOND_PERSON;

        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        DeleteTagCommand deleteTagCommand = new DeleteTagCommand(outOfBoundIndex, TypicalPersons.ALICE.getTags());

        assertCommandFailure(deleteTagCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final DeleteTagCommand standardCommand = new DeleteTagCommand(INDEX_FIRST_PERSON,
            TypicalPersons.ALICE.getTags());

        DeleteTagCommand commandWithSameValue = new DeleteTagCommand(INDEX_FIRST_PERSON,
            SampleDataUtil.getTagSet(VALID_TAG_FRIENDS));

        assertTrue(standardCommand.equals(commandWithSameValue));

        assertTrue(standardCommand.equals(standardCommand));

        assertFalse(standardCommand.equals(null));

        assertFalse(standardCommand.equals(new ClearCommand()));
    }

    @Test
    public void toStringMethod() {
        Index targetIndex = Index.fromOneBased(1);
        DeleteTagCommand deleteTagCommand = new DeleteTagCommand(targetIndex, TypicalPersons.ALICE.getTags());
        String expected = DeleteTagCommand.class.getCanonicalName() + "{tags=" + TypicalPersons.ALICE.getTags() + "}";
        assertEquals(expected, deleteTagCommand.toString());
    }
}
