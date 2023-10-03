package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

import org.junit.jupiter.api.Test;
import seedu.address.testutil.TypicalPersons;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

/**
 * Contains integration tests (interaction with the Model) and unit tests for TagCommand.
 */
public class TagCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_addTag_success() {
        TagCommand tagCommand = new TagCommand(INDEX_FIRST_PERSON, TypicalPersons.ALICE.getTags());

        String expectedMessage = String.format(TagCommand.MESSAGE_ADD_TAG_SUCCESS, Messages.format(TypicalPersons.ALICE));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), TypicalPersons.ALICE);

        assertCommandSuccess(tagCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_deleteTag_success() {
        TagCommand tagCommand = new TagCommand(INDEX_THIRD_PERSON, TypicalPersons.CARL.getTags());

        String expectedMessage = String.format(TagCommand.MESSAGE_DELETE_TAG_SUCCESS, Messages.format(TypicalPersons.CARL));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(2), TypicalPersons.CARL);

        assertCommandSuccess(tagCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void toStringMethod() {
        Index targetIndex = Index.fromOneBased(1);
        TagCommand tagCommand = new TagCommand(targetIndex, TypicalPersons.ALICE.getTags());
        String expected = TagCommand.class.getCanonicalName() + "{tags=" + TypicalPersons.ALICE.getTags() + "}";
        assertEquals(expected, tagCommand.toString());
    }
}
