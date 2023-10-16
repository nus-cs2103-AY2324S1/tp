package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalFields.EMAIL_FIELD;
import static seedu.address.testutil.TypicalFields.PHONE_FIELD;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;

class ReadCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexAndFieldToRead_success() throws CommandException {
        ReadCommand readCommand = new ReadCommand(INDEX_FIRST_PERSON, PHONE_FIELD);

        String expectedMessage = String.format(readCommand.MESSAGE_READ_PERSON_SUCCESS, PHONE_FIELD);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        Person personToRead = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        expectedModel.setSpecificPersonToDisplay(personToRead);

        assertCommandSuccess(readCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndex_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        ReadCommand readCommand = new ReadCommand(outOfBoundIndex, PHONE_FIELD);

        assertCommandFailure(readCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidField_throwsCommandException() {
        String invalidField = "invalid";
        ReadCommand readCommand = new ReadCommand(INDEX_FIRST_PERSON, invalidField);

        assertCommandFailure(readCommand, model, Messages.MESSAGE_INVALID_FIELD_TO_READ);
    }

    @Test
    public void equals() {
        ReadCommand readFirstCommand = new ReadCommand(INDEX_FIRST_PERSON, EMAIL_FIELD);
        ReadCommand readSecondCommand = new ReadCommand(INDEX_SECOND_PERSON, EMAIL_FIELD);

        // same object -> returns true
        assertTrue(readFirstCommand.equals(readFirstCommand));

        // same values -> returns true
        ReadCommand readFirstCommandCopy = new ReadCommand(INDEX_FIRST_PERSON, EMAIL_FIELD);
        assertTrue(readFirstCommand.equals(readFirstCommandCopy));

        // different types -> returns false
        assertFalse(readFirstCommand.equals(1));

        // null -> returns false
        assertFalse(readFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(readFirstCommand.equals(readSecondCommand));
    }

    @Test
    public void fieldValueToString_validPhoneField_success() throws CommandException {
        // Create a sample Person object with a valid phone field
        Person personToRead = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());

        ReadCommand readCommandPhoneTest = new ReadCommand(INDEX_FIRST_PERSON, "phone");
        ReadCommand readCommandEmailTest = new ReadCommand(INDEX_FIRST_PERSON, "email");
        ReadCommand readCommandAddressTest = new ReadCommand(INDEX_FIRST_PERSON, "address");

        String phoneValue = readCommandPhoneTest.fieldValueToString(personToRead);
        String emailValue = readCommandEmailTest.fieldValueToString(personToRead);
        String addressValue = readCommandAddressTest.fieldValueToString(personToRead);

        assertEquals("94351253", phoneValue);
        assertEquals("alice@example.com", emailValue);
        assertEquals("123, Jurong West Ave 6, #08-111", addressValue);
    }

}
