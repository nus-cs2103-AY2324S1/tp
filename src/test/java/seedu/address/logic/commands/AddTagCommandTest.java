package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;

public class AddTagCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void constructor_nullTag_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddTagCommand(null, null));
    }

    @Test
    public void execute_tagAcceptedByModel_addTagSuccessful() throws Exception {
        Person person = model.getFilteredPersonList().get(0);
        AddTagCommand addTagCommand = new AddTagCommand(INDEX_FIRST_PERSON, new AddTagCommand.AddTagDescriptor());
        String expectedMessage = String.format(AddTagCommand.MESSAGE_ADD_TAG_SUCCESS, Messages.format(person));
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), person);
        assertCommandSuccess(addTagCommand, model, expectedMessage, expectedModel);
    }
}
