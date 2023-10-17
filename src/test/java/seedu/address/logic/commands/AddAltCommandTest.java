package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.MonthDay;
import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Birthday;
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
    public void execute_allFieldsSpecifiedInitiallyEmpty_success() throws Exception {
        Person personA = new PersonBuilder().withName("A").build();
        Person personACopy = new PersonBuilder().withName("A")
                .withLinkedin("LINKEDIN")
                .withSecondaryEmail("email@email.com")
                .withTelegram("@telegram")
                .withBirthday(MonthDay.of(6, 9)).build();

        AddAltCommand.AddAltPersonDescriptor descriptor = new AddAltCommand.AddAltPersonDescriptor();
        descriptor.setLinkedin(new Linkedin("LINKEDIN"));
        descriptor.setSecondaryEmail(new Email("email@email.com"));
        descriptor.setTelegram(new Telegram("@telegram"));
        descriptor.setBirthday(new Birthday(MonthDay.of(6, 9)));

        ModelStubSetPerson modelStub = new ModelStubSetPerson(personA, p -> {
            assertEquals(p.getName(), new Name("A"));
            assertEquals(p.getLinkedin().orElseThrow(), new Linkedin("LINKEDIN"));
            assertEquals(p.getSecondaryEmail().orElseThrow(), new Email("email@email.com"));
            assertEquals(p.getTelegram().orElseThrow(), new Telegram("@telegram"));
        });

        CommandResult commandResult = new AddAltCommand(Index.fromZeroBased(0), descriptor).execute(modelStub);
        String expectedMessage = String.format(AddAltCommand.MESSAGE_ADDALT_SUCCESS, Messages.format(personACopy));
        assertEquals(commandResult, new CommandResult(expectedMessage));
    }

    @Test
    public void execute_someFieldsSpecifiedInitiallyEmpty_success() throws Exception {
        Person personA = new PersonBuilder().withName("A").withTelegram("@telegram").build();
        Person personACopy = new PersonBuilder().withName("A")
                .withLinkedin("LINKEDIN")
                .withSecondaryEmail("email@email.com").build();

        AddAltCommand.AddAltPersonDescriptor descriptor = new AddAltCommand.AddAltPersonDescriptor();
        descriptor.setLinkedin(new Linkedin("LINKEDIN"));
        descriptor.setSecondaryEmail(new Email("email@email.com"));

        ModelStubSetPerson modelStub = new ModelStubSetPerson(personA, p -> {
            assertEquals(p.getName(), new Name("A"));
            assertEquals(p.getLinkedin().orElseThrow(), new Linkedin("LINKEDIN"));
            assertEquals(p.getTelegram().orElseThrow(), new Telegram("@telegram"));
        });

        CommandResult commandResult = new AddAltCommand(Index.fromZeroBased(0), descriptor).execute(modelStub);
        String expectedMessage = String.format(AddAltCommand.MESSAGE_ADDALT_SUCCESS, Messages.format(personACopy));
        assertEquals(commandResult, new CommandResult(expectedMessage));
    }

    @Test
    public void execute_allFieldsSpecifiedInitiallyNotEmpty_exceptionThrown() {
        Person personA = new PersonBuilder().withName("A")
                .withLinkedin("LINKEDIN")
                .withSecondaryEmail("email@email.com")
                .withTelegram("@telegram")
                .withBirthday(MonthDay.of(6, 9)).build();

        AddAltCommand.AddAltPersonDescriptor descriptor = new AddAltCommand.AddAltPersonDescriptor();
        descriptor.setLinkedin(new Linkedin("LINKEDIN_A"));
        descriptor.setSecondaryEmail(new Email("personA@email.com"));
        descriptor.setTelegram(new Telegram("@telegrama"));
        descriptor.setBirthday(new Birthday(MonthDay.of(6, 10)));

        ModelStubSetPerson modelStub = new ModelStubSetPerson(personA, p -> {});

        try {
            new AddAltCommand(Index.fromZeroBased(0), descriptor).execute(modelStub);
            fail();
        } catch (CommandException e) {
            String expectedMessage = String.format(AddAltCommand.MESSAGE_ADDALT_FAILURE, "A");
            assertEquals(e.getMessage(), expectedMessage);
        }
    }

    @Test
    public void execute_someFieldsSpecifiedInitiallyNotEmpty_exceptionThrown() {
        Person personA = new PersonBuilder().withName("A")
                .withTelegram("@telegram")
                .withBirthday(MonthDay.of(6, 9)).build();

        AddAltCommand.AddAltPersonDescriptor descriptor = new AddAltCommand.AddAltPersonDescriptor();
        descriptor.setLinkedin(new Linkedin("LINKEDIN_A"));
        descriptor.setSecondaryEmail(new Email("personA@email.com"));
        descriptor.setTelegram(new Telegram("@telegrama"));

        ModelStubSetPerson modelStub = new ModelStubSetPerson(personA, p -> {});

        try {
            new AddAltCommand(Index.fromZeroBased(0), descriptor).execute(modelStub);
            fail();
        } catch (CommandException e) {
            String expectedMessage = String.format(AddAltCommand.MESSAGE_ADDALT_FAILURE, "A");
            assertEquals(e.getMessage(), expectedMessage);
        }
    }

    @Test
    public void execute_samePrimaryAndSecondayEmail_exceptionThrown() {
        Person personA = new PersonBuilder().withName("A").withEmail("email@email.com").build();

        AddAltCommand.AddAltPersonDescriptor descriptor = new AddAltCommand.AddAltPersonDescriptor();
        descriptor.setSecondaryEmail(new Email("email@email.com"));

        ModelStubSetPerson modelStub = new ModelStubSetPerson(personA, p -> {});

        try {
            new AddAltCommand(Index.fromZeroBased(0), descriptor).execute(modelStub);
            fail();
        } catch (CommandException e) {
            String expectedMessage = String.format(AddAltCommand.MESSAGE_ADDALT_DUPLICATE_EMAIL, "A");
            assertEquals(e.getMessage(), expectedMessage);
        }
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
        descriptor.setBirthday(new Birthday(MonthDay.of(6, 9)));
        assertEquals(descriptor.getBirthday(), new Birthday(MonthDay.of(6, 9)));
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

        // different index, same descriptor -> returns false
        AddAltCommand commandADiffIndex = new AddAltCommand(Index.fromZeroBased(1), descriptorA);
        assertFalse(commandA.equals(commandADiffIndex));

        // different descriptor, same index -> returns false
        AddAltCommand commandADiffDesc = new AddAltCommand(Index.fromZeroBased(0), descriptorB);
        assertFalse(commandA.equals(commandADiffDesc));

        // different args -> returns false
        assertFalse(commandA.equals(commandB));
    }

    @Test
    public void hasVaild() {
        AddAltCommand.AddAltPersonDescriptor descriptorA = new AddAltCommand.AddAltPersonDescriptor();
        AddAltCommand.AddAltPersonDescriptor descriptorB = new AddAltCommand.AddAltPersonDescriptor();
        descriptorA.setTelegram(new Telegram("@telegram"));
        descriptorA.setLinkedin(new Linkedin("LINKEDIN"));
        descriptorA.setSecondaryEmail(new Email("email@email.com"));
        descriptorA.setBirthday(new Birthday(MonthDay.of(6, 9)));
        assertTrue(descriptorA.hasValidBirthday());
        assertTrue(descriptorA.hasValidTelegram());
        assertTrue(descriptorA.hasValidLinkedin());
        assertTrue(descriptorA.hasValidSecondaryEmail());
        assertFalse(descriptorB.hasValidBirthday());
        assertFalse(descriptorB.hasValidTelegram());
        assertFalse(descriptorB.hasValidLinkedin());
        assertFalse(descriptorB.hasValidSecondaryEmail());
    }
    @Test
    public void toStringMethod() {
        Index index = Index.fromOneBased(1);
        AddAltCommand.AddAltPersonDescriptor addAltPersonDescriptor = new AddAltCommand.AddAltPersonDescriptor();
        AddAltCommand addAltCommand = new AddAltCommand(index, addAltPersonDescriptor);
        String expected = AddAltCommand.class.getCanonicalName() + "{index=" + index + ", addAltPersonDescriptor="
                + addAltPersonDescriptor + "}";
        assertEquals(expected, addAltCommand.toString());
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
