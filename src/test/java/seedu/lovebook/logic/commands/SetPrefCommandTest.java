package seedu.lovebook.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.lovebook.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.lovebook.commons.core.GuiSettings;
import seedu.lovebook.logic.Messages;
import seedu.lovebook.model.DatePrefs;
import seedu.lovebook.model.Model;
import seedu.lovebook.model.ReadOnlyDatePrefs;
import seedu.lovebook.model.ReadOnlyLoveBook;
import seedu.lovebook.model.ReadOnlyUserPrefs;
import seedu.lovebook.model.person.Age;
import seedu.lovebook.model.person.Date;
import seedu.lovebook.model.person.Gender;
import seedu.lovebook.model.person.Height;
import seedu.lovebook.model.person.Income;
import seedu.lovebook.model.person.horoscope.Horoscope;
import seedu.lovebook.testutil.PreferenceBuilder;


public class SetPrefCommandTest {

    @Test
    public void constructor_nullPreference_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new SetPrefCommand(null));
    }

    @Test
    public void execute_preferenceAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingPreferenceAdded modelStub = new ModelStubAcceptingPreferenceAdded();
        SetPrefCommand.SetPreferenceDescriptor validPreference = new PreferenceBuilder().build();

        CommandResult commandResult = new SetPrefCommand(validPreference).execute(modelStub);
        assertEquals(String.format(SetPrefCommand.MESSAGE_EDIT_PREF_SUCCESS, Messages.format(validPreference)),
                commandResult.getFeedbackToUser());
    }

    @Test
    public void execute_emptyPreference_throwException() throws Exception {
        ModelStubAcceptingPreferenceAdded modelStub = new ModelStubAcceptingPreferenceAdded();
        SetPrefCommand.SetPreferenceDescriptor emptyPreference = new SetPrefCommand.SetPreferenceDescriptor();
        assertThrows(RuntimeException.class, () -> new SetPrefCommand(emptyPreference).execute(modelStub));
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
     * A Model stub that always accept the date being added.
     */
    private class ModelStubAcceptingPreferenceAdded extends SetPrefCommandTest.ModelStub {

        private Age defaultAge = new Age("23");
        private Gender defaultGender = new Gender("F");
        private Height defaultHeight = new Height("168");
        private Income defaultIncome = new Income("3000");
        private final Horoscope defaultHoroscope = new Horoscope("Aquarius");
        @Override
        public DatePrefs getDatePrefs() {
            return new DatePrefs(defaultAge, defaultGender, defaultHeight, defaultIncome, defaultHoroscope);
        }

        @Override
        public void setDatePrefs(ReadOnlyDatePrefs datePrefs) {}

    }
}
