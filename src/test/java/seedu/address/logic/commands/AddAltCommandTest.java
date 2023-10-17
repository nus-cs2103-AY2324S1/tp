package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.commons.core.index.Index;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Email;
import seedu.address.model.person.Linkedin;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Telegram;
import seedu.address.testutil.ModelStub;
import seedu.address.testutil.PersonBuilder;


public class AddAltCommandTest {

    @Test
    public void constructor_nullArgs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddAltCommand(
                Index.fromZeroBased(0), null)
        );
        assertThrows(NullPointerException.class, () -> new AddAltCommand(
                null, new AddAltCommand.AddAltPersonDescriptor())
        );
        assertThrows(NullPointerException.class, () -> new AddAltCommand(null, null));
    }

    @Test
    public void execute_personEditsModel_setSuccessful() throws Exception {
        Person personA = new PersonBuilder().withName("A").build();

        AddAltCommand.AddAltPersonDescriptor descriptor = new AddAltCommand.AddAltPersonDescriptor();
        descriptor.setLinkedin(new Linkedin("LINKEDIN"));
        descriptor.setSecondaryEmail(new Email("email@email.com"));
        descriptor.setTelegram(new Telegram("@telegram"));

        ModelStubSetPerson modelStub = new ModelStubSetPerson(personA, p -> {
            assertEquals(p.getName(), new Name("A"));
            assertEquals(p.getLinkedin().orElseThrow(), new Linkedin("LINKEDIN"));
            assertEquals(p.getSecondaryEmail().orElseThrow(), new Email("email@email.com"));
            assertEquals(p.getTelegram().orElseThrow(), new Telegram("@telegram"));
        });

        CommandResult commandResult = new AddAltCommand(Index.fromZeroBased(0), descriptor).execute(modelStub);
    }

    @Test
    public void addAltPersonDescriptor() {
        AddAltCommand.AddAltPersonDescriptor descriptor = new AddAltCommand.AddAltPersonDescriptor();
        descriptor.setTelegram(new Telegram("@telegram"));
        assertEquals(descriptor.getTelegram(), new Telegram("@telegram"));
        descriptor.setLinkedin(new Linkedin("LINKEDIN"));
        assertEquals(descriptor.getLinkedin(), new Linkedin("LINKEDIN"));
        descriptor.setSecondaryEmail(new Email("email@email.com"));
        assertEquals(descriptor.getSecondaryEmail(), new Email("email@email.com"));
    }

    @Test
    public void equals() {
        AddAltCommand.AddAltPersonDescriptor descriptorA = new AddAltCommand.AddAltPersonDescriptor();
        AddAltCommand.AddAltPersonDescriptor descriptorB = new AddAltCommand.AddAltPersonDescriptor();
        descriptorA.setTelegram(new Telegram("@atelegram"));
        descriptorB.setTelegram(new Telegram("@btelegram"));
        AddAltCommand commandA = new AddAltCommand(Index.fromZeroBased(0), descriptorA);
        AddAltCommand commandB = new AddAltCommand(Index.fromZeroBased(1), descriptorB);

        // same object -> returns true
        assertTrue(commandA.equals(commandA));

        // same values -> returns true
        AddAltCommand commandACopy = new AddAltCommand(Index.fromZeroBased(0), descriptorA);
        assertTrue(commandA.equals(commandACopy));

        // different types -> returns false
        assertFalse(commandA.equals(1));

        // null -> returns false
        assertFalse(commandA.equals(null));

        // different args -> returns false
        assertFalse(commandA.equals(commandB));
    }

    /**
     * A Model stub that contains a single person.
     */
    private class ModelStubSetPerson extends ModelStub {
        private final Person person;
        private final Consumer<Person> consumer;

        /**
         * Model with single person.
         * The predicate {@code pred} must pass after setPerson is called.
         * @param person Original person.
         * @param consumer {@code Consumer} to test the edited person.
         */
        ModelStubSetPerson(Person person, Consumer<Person> consumer) {
            requireNonNull(person);
            this.person = person;
            this.consumer = consumer;
        }

        @Override
        public void setPerson(Person personA, Person personB) {
            consumer.accept(personB);
        }

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            return FXCollections.observableArrayList(person);
        }

        @Override
        public void updateFilteredPersonList(Predicate<Person> unused) {
           // Do nothing
        }
    }

    /**
     * A Model stub that always accept the person being added.
     */
    private class ModelStubAcceptingPersonAdded extends ModelStub {
        final ArrayList<Person> personsAdded = new ArrayList<>();
        private int id = 0;

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
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }

    }

}
