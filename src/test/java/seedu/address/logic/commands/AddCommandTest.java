package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalJobs.CHEF;

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
import seedu.address.model.job.Job;
import seedu.address.testutil.JobBuilder;

public class AddCommandTest {

    @Test
    public void constructor_nullJob_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(null));
    }

    @Test
    public void execute_jobAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingJobAdded modelStub = new ModelStubAcceptingJobAdded();
        Job validJob = new JobBuilder().build();

        CommandResult commandResult = new AddCommand(validJob).execute(modelStub);

        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, Messages.format(validJob)),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validJob), modelStub.jobsAdded);
    }

    @Test
    public void execute_duplicateJob_throwsCommandException() {
        Job validJob = new JobBuilder().build();
        AddCommand addCommand = new AddCommand(validJob);
        ModelStub modelStub = new ModelStubWithJob(validJob);

        assertThrows(CommandException.class, AddCommand.MESSAGE_DUPLICATE_JOB, () -> addCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Job chef = new JobBuilder().withRole("Chef").build();
        Job cleaner = new JobBuilder().withRole("Cleaner").build();
        AddCommand addChefCommand = new AddCommand(chef);
        AddCommand addCleanerCommand = new AddCommand(cleaner);

        // same object -> returns true
        assertTrue(addChefCommand.equals(addChefCommand));

        // same values -> returns true
        AddCommand addChefCommandCopy = new AddCommand(chef);
        assertTrue(addChefCommand.equals(addChefCommandCopy));

        // different types -> returns false
        assertFalse(addChefCommand.equals(1));

        // null -> returns false
        assertFalse(addChefCommand.equals(null));

        // different job -> returns false
        assertFalse(addChefCommand.equals(addCleanerCommand));
    }

    @Test
    public void toStringMethod() {
        AddCommand addCommand = new AddCommand(CHEF);
        String expected = AddCommand.class.getCanonicalName() + "{toAdd=" + CHEF + "}";
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
        public void addJob(Job job) {
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
        public boolean hasJob(Job job) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteJob(Job target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setJob(Job target, Job editedJob) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Job> getFilteredJobList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredJobList(Predicate<Job> predicate) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single person.
     */
    private class ModelStubWithJob extends ModelStub {
        private final Job job;

        ModelStubWithJob(Job job) {
            requireNonNull(job);
            this.job = job;
        }

        @Override
        public boolean hasJob(Job job) {
            requireNonNull(job);
            return this.job.isSameJob(job);
        }
    }

    /**
     * A Model stub that always accept the job being added.
     */
    private class ModelStubAcceptingJobAdded extends ModelStub {
        final ArrayList<Job> jobsAdded = new ArrayList<>();

        @Override
        public boolean hasJob(Job job) {
            requireNonNull(job);
            return jobsAdded.stream().anyMatch(job::isSameJob);
        }

        @Override
        public void addJob(Job job) {
            requireNonNull(job);
            jobsAdded.add(job);
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }

}
