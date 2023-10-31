package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.function.Consumer;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.person.Note;
import seedu.address.model.person.Person;
import seedu.address.testutil.ModelStub;
import seedu.address.testutil.PersonBuilder;

public class AddNoteCommandTest {

    @Test
    public void constructor_nullArgs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddNoteCommand(Index.fromZeroBased(0), null));
        assertThrows(NullPointerException.class, () -> new AddNoteCommand(null, new Note("Sample note")));
        assertThrows(NullPointerException.class, () -> new AddNoteCommand(null, null));
    }

    @Test
    public void execute_addNote_successful() throws Exception {
        Person personA = new PersonBuilder().withName("A").build();

        Note noteToAdd = new Note("Sample note for person A");

        ModelStubWithNote modelStub = new ModelStubWithNote(personA, note -> assertEquals(note, noteToAdd));

        CommandResult commandResult = new AddNoteCommand(Index.fromZeroBased(0), noteToAdd).execute(modelStub);

    }
    @Test
    public void execute_invalidIndex_throwsCommandException() {
        Person personA = new PersonBuilder().withName("A").build();

        Note noteToAdd = new Note("Sample note for person A");

        ModelStubWithNote modelStub = new ModelStubWithNote(personA, note -> {});

        AddNoteCommand command = new AddNoteCommand(Index.fromZeroBased(1), noteToAdd);

        assertThrows(CommandException.class, () -> command.execute(modelStub),
            Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }


    @Test
    public void equals() {
        Note noteA = new Note("Note A");
        Note noteB = new Note("Note B");
        AddNoteCommand commandA = new AddNoteCommand(Index.fromZeroBased(0), noteA);
        AddNoteCommand commandB = new AddNoteCommand(Index.fromZeroBased(1), noteB);

        // same object -> returns true
        assertTrue(commandA.equals(commandA));

        // same values -> returns true
        AddNoteCommand commandACopy = new AddNoteCommand(Index.fromZeroBased(0), noteA);
        assertTrue(commandA.equals(commandACopy));

        // different types -> returns false
        assertFalse(commandA.equals(1));

        // null -> returns false
        assertFalse(commandA.equals(null));

        // different note -> returns false
        assertFalse(commandA.equals(commandB));
    }

    /**
     * A Model stub that contains a single person.
     */
    private class ModelStubWithNote extends ModelStub {
        private final Person person;
        private final Consumer<Note> consumer;

        ModelStubWithNote(Person person, Consumer<Note> consumer) {
            this.person = person;
            this.consumer = consumer;
        }
        @Override
        public ObservableList<Person> getFilteredPersonList() {
            return FXCollections.observableArrayList(person);
        }
    }
}
