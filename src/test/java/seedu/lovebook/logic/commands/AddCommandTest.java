package seedu.lovebook.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.lovebook.testutil.Assert.assertThrows;
import static seedu.lovebook.testutil.TypicalPersons.ALICE;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.lovebook.commons.core.GuiSettings;
import seedu.lovebook.logic.Messages;
import seedu.lovebook.logic.commands.exceptions.CommandException;
import seedu.lovebook.model.DatePrefs;
import seedu.lovebook.model.LoveBook;
import seedu.lovebook.model.Model;
import seedu.lovebook.model.ReadOnlyDatePrefs;
import seedu.lovebook.model.ReadOnlyLoveBook;
import seedu.lovebook.model.ReadOnlyUserPrefs;
import seedu.lovebook.model.person.Date;
import seedu.lovebook.testutil.PersonBuilder;

public class AddCommandTest {

    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(null));
    }

    @Test
    public void execute_personAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingPersonAdded modelStub = new ModelStubAcceptingPersonAdded();
        Date validDate = new PersonBuilder().build();

        CommandResult commandResult = new AddCommand(validDate).execute(modelStub);

        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, Messages.format(validDate)),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validDate), modelStub.datesAdded);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Date validDate = new PersonBuilder().build();
        AddCommand addCommand = new AddCommand(validDate);
        ModelStub modelStub = new ModelStubWithPerson(validDate);

        assertThrows(CommandException.class, AddCommand.MESSAGE_DUPLICATE_PERSON, () -> addCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Date alice = new PersonBuilder().withName("Alice").build();
        Date bob = new PersonBuilder().withName("Bob").build();
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

        // different date -> returns false
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
        public Path getLoveBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setLoveBookFilePath(Path loveBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addPerson(Date date) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setLoveBook(ReadOnlyLoveBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyLoveBook getLoveBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPerson(Date date) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deletePerson(Date target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPerson(Date target, Date editedDate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Date> getFilteredPersonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void getRandomPerson() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPersonList(Predicate<Date> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public String getWelcomeMessage() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public DatePrefs getDatePrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setDatePrefs(ReadOnlyDatePrefs datePrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getDatePrefsFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setDatePrefsFilePath(Path datePrefsFilePath) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single date.
     */
    private class ModelStubWithPerson extends ModelStub {
        private final Date date;

        ModelStubWithPerson(Date date) {
            requireNonNull(date);
            this.date = date;
        }

        @Override
        public boolean hasPerson(Date date) {
            requireNonNull(date);
            return this.date.isSamePerson(date);
        }
    }

    /**
     * A Model stub that always accept the date being added.
     */
    private class ModelStubAcceptingPersonAdded extends ModelStub {
        final ArrayList<Date> datesAdded = new ArrayList<>();

        @Override
        public boolean hasPerson(Date date) {
            requireNonNull(date);
            return datesAdded.stream().anyMatch(date::isSamePerson);
        }

        @Override
        public void addPerson(Date date) {
            requireNonNull(date);
            datesAdded.add(date);
        }

        @Override
        public ReadOnlyLoveBook getLoveBook() {
            return new LoveBook();
        }
    }

}
