package seedu.application.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.*;
import static seedu.application.testutil.Assert.assertThrows;
import static seedu.application.testutil.TypicalJobs.CHEF;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.application.commons.core.GuiSettings;
import seedu.application.logic.Messages;
import seedu.application.logic.commands.exceptions.CommandException;
import seedu.application.model.Model;
import seedu.application.model.ReadOnlyApplicationBook;
import seedu.application.model.ReadOnlyUserPrefs;
import seedu.application.model.job.Job;
import seedu.application.testutil.JobBuilder;

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
        assertEquals(addChefCommand, addChefCommand);

        // same values -> returns true
        AddCommand addChefCommandCopy = new AddCommand(chef);
        assertEquals(addChefCommand, addChefCommandCopy);

        // different types -> returns false
        assertNotEquals(1, addChefCommand);

        // null -> returns false
        assertNotEquals(null, addChefCommand);

        // different job -> returns false
        assertNotEquals(addChefCommand, addCleanerCommand);
    }

    @Test
    public void toStringMethod() {
        AddCommand addCommand = new AddCommand(CHEF);
        String expected = AddCommand.class.getCanonicalName() + "{toAdd=" + CHEF + "}";
        assertEquals(expected, addCommand.toString());
    }

    /**
     * A default model stub that have all the methods failing.
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
        public Path getApplicationBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setApplicationBookFilePath(Path applicationBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addJob(Job job) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setApplicationBook(ReadOnlyApplicationBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyApplicationBook getApplicationBook() {
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
    }

}
