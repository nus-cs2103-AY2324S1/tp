package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalInternships.JANESTREET;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.InternshipBook;
import seedu.address.model.InternshipModel;
import seedu.address.model.ReadOnlyInternshipBook;
import seedu.address.model.ReadOnlyInternshipUserPrefs;
import seedu.address.model.internship.Internship;
import seedu.address.testutil.InternshipBuilder;

public class CreateCommandTest {

    @Test
    public void constructor_nullInternship_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new CreateCommand(null));
    }

    @Test
    public void execute_internshipAcceptedByModel_createSuccessful() throws Exception {
        CreateCommandTest.ModelStubAcceptingInternshipCreated modelStub = new CreateCommandTest
                .ModelStubAcceptingInternshipCreated();
        Internship validInternship = new InternshipBuilder().build();

        CommandResult commandResult = new CreateCommand(validInternship).execute(modelStub);

        assertEquals(String.format(CreateCommand.MESSAGE_SUCCESS, Messages.format(validInternship)),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validInternship), modelStub.internshipsCreated);
    }

    @Test
    public void execute_duplicateInternship_throwsCommandException() {
        Internship validInternship = new InternshipBuilder().build();
        CreateCommand createCommand = new CreateCommand(validInternship);
        CreateCommandTest.ModelStub modelStub = new CreateCommandTest.ModelStubWithInternship(validInternship);

        assertThrows(
                CommandException.class,
                CreateCommand.MESSAGE_DUPLICATE_INTERNSHIP, () -> createCommand.execute(modelStub)
        );
    }

    @Test
    public void equals() {
        Internship janeStreet = new InternshipBuilder().withCompanyName("JaneStreet").build();
        Internship google = new InternshipBuilder().withCompanyName("Google").build();
        CreateCommand addJaneStreetCommand = new CreateCommand(janeStreet);
        CreateCommand addGoogleCommand = new CreateCommand(google);

        // same object -> returns true
        assertTrue(addJaneStreetCommand.equals(addJaneStreetCommand));

        // same values -> returns true
        CreateCommand addJaneStreetCommandCopy = new CreateCommand(janeStreet);
        assertTrue(addJaneStreetCommand.equals(addJaneStreetCommandCopy));

        // different types -> returns false
        assertFalse(addJaneStreetCommand.equals(1));

        // null -> returns false
        assertFalse(addJaneStreetCommand.equals(null));

        // different internship -> returns false
        assertFalse(addJaneStreetCommand.equals(addGoogleCommand));
    }

    @Test
    public void toStringMethod() {
        CreateCommand createCommand = new CreateCommand(JANESTREET);
        String expected = CreateCommand.class.getCanonicalName() + "{toAdd=" + JANESTREET + "}";
        assertEquals(expected, createCommand.toString());
    }

    /**
     * A default model stub that have all the methods failing.
     */
    private class ModelStub implements InternshipModel {
        @Override
        public void setFilterParameter(String filterParameter) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setFilterValue(String filterValue) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setComparatorOrder(SortCommand.Order order) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public String getFilterValue() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public String getFilterParameter() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setComparatorPrefix(String prefix) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public SortCommand.Order getComparatorOrder() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public String getComparatorPrefix() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setUserPrefs(ReadOnlyInternshipUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyInternshipUserPrefs getUserPrefs() {
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
        public Path getInternshipBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setInternshipBookFilePath(Path internshipBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void createInternship(Internship internship) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setInternshipBook(ReadOnlyInternshipBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyInternshipBook getInternshipBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasInternship(Internship internship) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteInternship(Internship target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setInternship(Internship target, Internship editedInternship) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Internship> getFilteredInternshipList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredInternshipList(Predicate<Internship> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updatePredicate(Predicate<Internship> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void sortInternships(Comparator<Internship> comparator) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateSortComparator(Comparator<Internship> comparator) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single internship.
     */
    private class ModelStubWithInternship extends ModelStub {
        private final Internship internship;

        ModelStubWithInternship(Internship internship) {
            requireNonNull(internship);
            this.internship = internship;
        }

        @Override
        public boolean hasInternship(Internship internship) {
            requireNonNull(internship);
            return this.internship.isSameInternship(internship);
        }
    }

    /**
     * A Model stub that always accept the internship being added.
     */
    private class ModelStubAcceptingInternshipCreated extends ModelStub {
        final ArrayList<Internship> internshipsCreated = new ArrayList<>();

        @Override
        public boolean hasInternship(Internship internship) {
            requireNonNull(internship);
            return internshipsCreated.stream().anyMatch(internship::isSameInternship);
        }

        @Override
        public void createInternship(Internship internship) {
            requireNonNull(internship);
            internshipsCreated.add(internship);
        }

        @Override
        public ReadOnlyInternshipBook getInternshipBook() {
            return new InternshipBook();
        }
    }
}
