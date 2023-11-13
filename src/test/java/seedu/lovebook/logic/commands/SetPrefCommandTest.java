package seedu.lovebook.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.lovebook.logic.commands.CommandTestUtil.DESC_PREF_AMY;
import static seedu.lovebook.logic.commands.CommandTestUtil.DESC_PREF_BOB;
import static seedu.lovebook.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.Comparator;
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
import seedu.lovebook.model.date.Age;
import seedu.lovebook.model.date.Date;
import seedu.lovebook.model.date.Height;
import seedu.lovebook.model.date.Income;
import seedu.lovebook.model.date.horoscope.Horoscope;
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

    @Test
    public void equals() {
        SetPrefCommand.SetPreferenceDescriptor descriptor =
                new SetPrefCommand.SetPreferenceDescriptor(DESC_PREF_AMY);

        SetPrefCommand.SetPreferenceDescriptor secondDescriptor =
                new SetPrefCommand.SetPreferenceDescriptor(DESC_PREF_BOB);

        final SetPrefCommand standardCommand = new SetPrefCommand(descriptor);

        // same values -> returns true
        SetPrefCommand commandWithSameValues = new SetPrefCommand(descriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new SetPrefCommand(secondDescriptor)));
    }

    @Test
    public void toStringMethod() {
        SetPrefCommand.SetPreferenceDescriptor setPrefDescriptor =
                new SetPrefCommand.SetPreferenceDescriptor(DESC_PREF_BOB);
        SetPrefCommand setPrefCommand = new SetPrefCommand(setPrefDescriptor);
        String expected = SetPrefCommand.class.getCanonicalName() + "{setPreferenceDescriptor="
                + setPrefDescriptor + "}";
        assertEquals(expected, setPrefCommand.toString());
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
        public void addDate(Date date) {
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
        public boolean hasDate(Date date) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteDate(Date target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setDate(Date target, Date editedDate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Date> getFilteredPersonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void getBlindDate() {
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
        public void updateSortedDateList(Comparator<Date> comparator) {
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

        @Override
        public void getBestDate() {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that always accept the date being added.
     */
    private class ModelStubAcceptingPreferenceAdded extends SetPrefCommandTest.ModelStub {

        private Age defaultAge = new Age("23");
        private Height defaultHeight = new Height("168");
        private Income defaultIncome = new Income("3000");
        private final Horoscope defaultHoroscope = new Horoscope("Aquarius");
        @Override
        public DatePrefs getDatePrefs() {
            return new DatePrefs(defaultAge, defaultHeight, defaultIncome, defaultHoroscope);
        }

        @Override
        public void setDatePrefs(ReadOnlyDatePrefs datePrefs) {}

    }
}
