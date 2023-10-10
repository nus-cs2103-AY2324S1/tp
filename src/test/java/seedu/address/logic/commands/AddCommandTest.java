package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.musician.Musician;
import seedu.address.testutil.PersonBuilder;

public class AddCommandTest {

    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(null));
    }

    @Test
    public void execute_personAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingPersonAdded modelStub = new ModelStubAcceptingPersonAdded();
        Musician validMusician = new PersonBuilder().build();

        CommandResult commandResult = new AddCommand(validMusician).execute(modelStub);

        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, Messages.format(validMusician)),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validMusician), modelStub.personsAdded);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Musician validMusician = new PersonBuilder().build();
        AddCommand addCommand = new AddCommand(validMusician);
        ModelStub modelStub = new ModelStubWithPerson(validMusician);

        assertThrows(CommandException.class,
                AddCommand.MESSAGE_DUPLICATE_MUSICIAN, () -> addCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Musician alice = new PersonBuilder().withName("Alice").build();
        Musician bob = new PersonBuilder().withName("Bob").build();
        AddCommand addAliceCommand = new AddCommand(alice);
        AddCommand addBobCommand = new AddCommand(bob);

        // same object -> returns true
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> returns true
        AddCommand addAliceCommandCopy = new AddCommand(alice);
        assertTrue(addAliceCommand.equals(addAliceCommandCopy));

        // different types -> returns false
        assertFalse(addAliceCommand.equals(1));

        // null -> returns false
        assertFalse(addAliceCommand.equals(null));

        // different musician -> returns false
        assertFalse(addAliceCommand.equals(addBobCommand));
    }

    @Test
    public void toStringMethod() {
        AddCommand addCommand = new AddCommand(ALICE);
        String expected = AddCommand.class.getCanonicalName() + "{toAdd=" + ALICE + "}";
        assertEquals(expected, addCommand.toString());
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getAddressBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBookFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addMusician(Musician musician) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBook(ReadOnlyAddressBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasMusician(Musician musician) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteMusician(Musician target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setMusician(Musician target, Musician editedMusician) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Musician> getFilteredMusicianList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredMusicianList(Predicate<Musician> predicate) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single musician.
     */
    private class ModelStubWithPerson extends ModelStub {
        private final Musician musician;

        ModelStubWithPerson(Musician musician) {
            requireNonNull(musician);
            this.musician = musician;
        }

        @Override
        public boolean hasMusician(Musician musician) {
            requireNonNull(musician);
            return this.musician.isSamePerson(musician);
        }
    }

    /**
     * A Model stub that always accept the musician being added.
     */
    private class ModelStubAcceptingPersonAdded extends ModelStub {
        final ArrayList<Musician> personsAdded = new ArrayList<>();

        @Override
        public boolean hasMusician(Musician musician) {
            requireNonNull(musician);
            return personsAdded.stream().anyMatch(musician::isSamePerson);
        }

        @Override
        public void addMusician(Musician musician) {
            requireNonNull(musician);
            personsAdded.add(musician);
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }

}
