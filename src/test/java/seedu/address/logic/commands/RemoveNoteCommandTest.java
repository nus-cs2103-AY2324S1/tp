package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_NOTE;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_NOTE;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BOB;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Note;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

public class RemoveNoteCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndex_success() throws Exception {
        Person personToEdit = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(personToEdit).build();
        editedPerson.addNote(new Note("Test note"));

        RemoveNoteCommand removeNoteCommand = new RemoveNoteCommand(INDEX_FIRST_PERSON, Index.fromOneBased(1));

        String expectedMessage = String.format(RemoveNoteCommand.MESSAGE_REMOVE_NOTE_SUCCESS, editedPerson.getName());

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(personToEdit, editedPerson);

        assertCommandSuccess(removeNoteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidPersonIndex_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        RemoveNoteCommand removeNoteCommand = new RemoveNoteCommand(outOfBoundIndex, Index.fromOneBased(1));

        assertThrows(CommandException.class, () -> removeNoteCommand.execute(model));
    }

    @Test
    public void execute_invalidNoteIndex_throwsCommandException() {
        RemoveNoteCommand removeNoteCommand = new RemoveNoteCommand(INDEX_FIRST_PERSON, Index.fromOneBased(1000)); // Assuming 1000 is an invalid note index

        assertThrows(CommandException.class, () -> removeNoteCommand.execute(model));
    }

    @Test
    public void equals() {
        RemoveNoteCommand removeFirstCommand = new RemoveNoteCommand(INDEX_FIRST_PERSON, INDEX_FIRST_NOTE);
        RemoveNoteCommand removeSecondCommand = new RemoveNoteCommand(INDEX_SECOND_PERSON, INDEX_SECOND_NOTE);

        // same object -> returns true
        assertTrue(removeFirstCommand.equals(removeFirstCommand));

        // same values -> returns true
        RemoveNoteCommand removeFirstCommandCopy = new RemoveNoteCommand(INDEX_FIRST_PERSON, INDEX_FIRST_NOTE);
        assertTrue(removeFirstCommand.equals(removeFirstCommandCopy));

        // different types -> returns false
        assertFalse(removeFirstCommand.equals(1));

        // null -> returns false
        assertFalse(removeFirstCommand.equals(null));

        // different index -> returns false
        assertFalse(removeFirstCommand.equals(removeSecondCommand));
    }
}
