package seedu.staffsnap.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.staffsnap.testutil.Assert.assertThrows;
import static seedu.staffsnap.testutil.TypicalApplicants.ALICE;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.staffsnap.commons.core.GuiSettings;
import seedu.staffsnap.logic.Messages;
import seedu.staffsnap.logic.commands.exceptions.CommandException;
import seedu.staffsnap.model.ApplicantBook;
import seedu.staffsnap.model.Model;
import seedu.staffsnap.model.ReadOnlyApplicantBook;
import seedu.staffsnap.model.ReadOnlyUserPrefs;
import seedu.staffsnap.model.applicant.Applicant;
import seedu.staffsnap.model.applicant.Descriptor;
import seedu.staffsnap.testutil.ApplicantBuilder;

public class AddCommandTest {

    @Test
    public void constructor_nullApplicant_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(null));
    }

    @Test
    public void execute_applicantAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingApplicantAdded modelStub = new ModelStubAcceptingApplicantAdded();
        Applicant validApplicant = new ApplicantBuilder().build();

        CommandResult commandResult = new AddCommand(validApplicant).execute(modelStub);

        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, Messages.format(validApplicant)),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validApplicant), modelStub.applicantsAdded);
    }

    @Test
    public void execute_duplicateApplicant_throwsCommandException() {
        Applicant validApplicant = new ApplicantBuilder().build();
        AddCommand addCommand = new AddCommand(validApplicant);
        ModelStub modelStub = new ModelStubWithApplicant(validApplicant);

        assertThrows(CommandException.class,
                AddCommand.MESSAGE_DUPLICATE_APPLICANT, () -> addCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Applicant alice = new ApplicantBuilder().withName("Alice").build();
        Applicant bob = new ApplicantBuilder().withName("Bob").build();
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

        // different applicant -> returns false
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
        public Path getApplicantBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setApplicantBookFilePath(Path applicantBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addApplicant(Applicant applicant) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setApplicantBook(ReadOnlyApplicantBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyApplicantBook getApplicantBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasApplicant(Applicant applicant) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean isDuplicateApplicant(Applicant applicant) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteApplicant(Applicant target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setApplicant(Applicant target, Applicant editedApplicant) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Applicant> getFilteredApplicantList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredApplicantList(Predicate<? super Applicant> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateSortedApplicantList(Descriptor descriptor, Boolean descendingOrder) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void refreshApplicantList() {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single applicant.
     */
    private class ModelStubWithApplicant extends ModelStub {
        private final Applicant applicant;

        ModelStubWithApplicant(Applicant applicant) {
            requireNonNull(applicant);
            this.applicant = applicant;
        }

        @Override
        public boolean hasApplicant(Applicant applicant) {
            requireNonNull(applicant);
            return this.applicant.isSameApplicant(applicant);
        }
    }

    /**
     * A Model stub that always accept the applicant being added.
     */
    private class ModelStubAcceptingApplicantAdded extends ModelStub {
        final ArrayList<Applicant> applicantsAdded = new ArrayList<>();

        @Override
        public boolean hasApplicant(Applicant applicant) {
            requireNonNull(applicant);
            return applicantsAdded.stream().anyMatch(applicant::isSameApplicant);
        }

        @Override
        public void addApplicant(Applicant applicant) {
            requireNonNull(applicant);
            applicantsAdded.add(applicant);
        }

        @Override
        public ReadOnlyApplicantBook getApplicantBook() {
            return new ApplicantBook();
        }
    }

}
