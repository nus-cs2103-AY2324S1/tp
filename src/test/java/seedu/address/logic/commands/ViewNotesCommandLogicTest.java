package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.person.Person;
import seedu.address.testutil.ModelStub;
import seedu.address.testutil.PersonBuilder;

public class ViewNotesCommandLogicTest {
    @Test
    public void execute_invalidIndex_throwsCommandException() {
        Person personA = new PersonBuilder().withName("A").build();

        ModelStubWithPerson modelStub = new ModelStubWithPerson(personA);

        ViewNotesCommand command = new ViewNotesCommand(1);

        assertThrows(CommandException.class,
            Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX, () -> command.execute(modelStub));
    }

    @Test
    public void equals() {
        ViewNotesCommand commandA = new ViewNotesCommand(0);
        ViewNotesCommand commandB = new ViewNotesCommand(1);

        // same object -> returns true
        assertTrue(commandA.equals(commandA));

        // same values -> returns true
        ViewNotesCommand commandACopy = new ViewNotesCommand(0);
        assertTrue(commandA.equals(commandACopy));

        // different types -> returns false
        assertFalse(commandA.equals(1));

        // null -> returns false
        assertFalse(commandA.equals(null));

        // different index -> returns false
        assertFalse(commandA.equals(commandB));
    }

    /**
     * A Model stub that contains a single person.
     */
    private class ModelStubWithPerson extends ModelStub {
        private final Person person;

        ModelStubWithPerson(Person person) {
            this.person = person;
        }

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            return FXCollections.observableArrayList(person);
        }
    }
}
