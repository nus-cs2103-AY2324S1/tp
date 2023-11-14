package seedu.address.logic.commands.events;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalEvents.EVENT1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.contacts.AddContactCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.AddressBook;
import seedu.address.model.ModelStub;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.event.Event;
import seedu.address.model.person.Person;
import seedu.address.testutil.EventBuilder;
import seedu.address.testutil.PersonBuilder;

public class AddEventCommandTest {

    @Test
    public void constructor_nullEvent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddEventCommand(null));
    }

    @Test
    public void execute_eventAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingEventAdded modelStub = new ModelStubAcceptingEventAdded();
        Person validPerson = new PersonBuilder().withName("Daniel").build();
        new AddContactCommand(validPerson).execute(modelStub);

        Event validEvent = new EventBuilder().withClient(new PersonBuilder().withName("Daniel").build()).build();
        CommandResult commandResult = new AddEventCommand(validEvent).execute(modelStub);

        assertEquals(String.format(AddEventCommand.MESSAGE_SUCCESS, Messages.format(validEvent)),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validEvent), modelStub.eventsAdded);
    }

    @Test
    public void execute_invalidClients_throwsCommandException() throws CommandException {
        ModelStubAcceptingEventAdded modelStub = new ModelStubAcceptingEventAdded();
        Person validPerson = new PersonBuilder().build();
        new AddContactCommand(validPerson).execute(modelStub);

        Event validEvent = null;
        try {
            validEvent = new EventBuilder().withClient(new PersonBuilder().withName("Daniel").build()).build();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        AddEventCommand addEventCommand = new AddEventCommand(validEvent);

        assertThrows(CommandException.class, Messages.MESSAGE_CLIENT_DOES_NOT_EXIST, () ->
                addEventCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Event a = null;
        Event b = null;
        try {
            a = new EventBuilder().withName("a").build();
            b = new EventBuilder().withName("b").build();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        AddEventCommand addACommand = new AddEventCommand(a);
        AddEventCommand addBCommand = new AddEventCommand(b);

        // same object -> returns true
        assertTrue(addACommand.equals(addACommand));

        // same values -> returns true
        AddEventCommand addACommandCopy = new AddEventCommand(a);
        assertTrue(addACommand.equals(addACommandCopy));

        // different types -> returns false
        assertFalse(addACommand.equals(1));

        // null -> returns false
        assertFalse(addACommand.equals(null));

        // different person -> returns false
        assertFalse(addACommand.equals(addBCommand));
    }

    @Test
    public void toStringMethod() {
        AddEventCommand addEventCommand = new AddEventCommand(EVENT1);
        String expected = AddEventCommand.class.getCanonicalName() + "{toAdd=" + EVENT1 + "}";
        assertEquals(expected, addEventCommand.toString());
    }

    /**
     * A Model stub that contains a single person.
     */
    private class ModelStubWithPerson extends ModelStub {
        private final Person person;

        ModelStubWithPerson(Person person) {
            requireNonNull(person);
            this.person = person;
        }

        @Override
        public boolean hasPerson(Person person) {
            requireNonNull(person);
            return this.person.isSamePerson(person);
        }
    }

    /**
     * A Model stub that always accept the person being added.
     */
    private class ModelStubAcceptingEventAdded extends ModelStub {
        final ArrayList<Person> personsAdded = new ArrayList<>();
        final ArrayList<Event> eventsAdded = new ArrayList<>();

        @Override
        public boolean hasPerson(Person person) {
            requireNonNull(person);
            return personsAdded.stream().anyMatch(person::isSamePerson);
        }

        @Override
        public void addPerson(Person person) {
            requireNonNull(person);
            personsAdded.add(person);
        }

        @Override
        public void addEvent(Event event) {
            requireNonNull(event);
            eventsAdded.add(event);
        }

        @Override
        public boolean hasEvent(Event event) {
            return false;
        }

        @Override
        public boolean isValidClient(Person client) {
            requireAllNonNull(client);
            return personsAdded.stream().anyMatch(client::isSamePerson);
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }

        @Override
        public Set<Person> getAllMatchedClients(Set<Person> clients) {
            return clients;
        }
    }

}
