package seedu.address.logic.commands.assignment;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAssignments.ASSIGNMENT1;

import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.View;
import seedu.address.model.assignment.Assignment;
import seedu.address.model.internship.role.InternshipRole;
import seedu.address.model.internship.task.InternshipTask;
import seedu.address.testutil.AssignmentBuilder;

public class AddAssignmentCommandTest {

    @Test
    public void constructor_nullAssignment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddAssignmentCommand(null));
    }

    @Test
    public void execute_assignmentAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingAssignmentAdded modelStub = new ModelStubAcceptingAssignmentAdded();
        Assignment validAssignment = new AssignmentBuilder().build();

        CommandResult commandResult = new AddAssignmentCommand(validAssignment).execute(modelStub);

        assertEquals(String.format(AddAssignmentCommand.MESSAGE_SUCCESS, Messages.format(validAssignment)),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validAssignment), modelStub.assignmentsAdded);
    }

    @Test
    public void execute_plannedFinishDateAfterDeadline_throwsCommandException() throws Exception {
        ModelStubAcceptingAssignmentAdded modelStub = new ModelStubAcceptingAssignmentAdded();
        LocalDateTime deadline = LocalDateTime.now();
        LocalDateTime plannedFinishDate = deadline.plusHours(10);
        Assignment invalidAssignment =
                new AssignmentBuilder().withDeadline(deadline).withPlannedDate(plannedFinishDate).build();
        AddAssignmentCommand addCommand = new AddAssignmentCommand(invalidAssignment);

        assertThrows(CommandException.class, () -> addCommand.execute(modelStub));
    }

    @Test
    public void execute_duplicateAssignment_throwsCommandException() {
        Assignment validAssignment = new AssignmentBuilder().build();
        AddAssignmentCommand addCommand = new AddAssignmentCommand(validAssignment);
        ModelStub modelStub = new ModelStubWithAssignment(validAssignment);

        assertThrows(CommandException.class,
                AddAssignmentCommand.MESSAGE_DUPLICATE_ASSIGNMENT, () -> addCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Assignment assignment1 = new AssignmentBuilder().withName("CS2103T TP").build();
        Assignment assignment2 = new AssignmentBuilder().withName("CS2100 Assignment").build();
        AddAssignmentCommand addOneCommand = new AddAssignmentCommand(assignment1);
        AddAssignmentCommand addTwoCommand = new AddAssignmentCommand(assignment2);

        // same object -> returns true
        assertTrue(addOneCommand.equals(addOneCommand));

        // same values -> returns true
        AddAssignmentCommand addOneCommandCopy = new AddAssignmentCommand(assignment1);
        assertTrue(addOneCommand.equals(addOneCommandCopy));

        // different types -> returns false
        assertFalse(addOneCommand.equals(1));

        // null -> returns false
        assertFalse(addOneCommand.equals(null));

        // different assignment -> returns false
        assertFalse(addOneCommand.equals(addTwoCommand));
    }

    @Test
    public void toStringMethod() {
        AddAssignmentCommand addCommand = new AddAssignmentCommand(ASSIGNMENT1);
        String expected = AddAssignmentCommand.class.getCanonicalName() + "{toAdd=" + ASSIGNMENT1 + "}";
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
        public ObservableList<InternshipTask> getFilteredInternshipTaskList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<InternshipRole> getFilteredInternshipRoleList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasInternshipRole(InternshipRole role) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addInternshipRole(InternshipRole role) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteInternshipRole(InternshipRole role) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setInternshipRole(InternshipRole role, InternshipRole newRole) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean isValidOperationWith(View v) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAssignment(Assignment a, Assignment newA) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Assignment> getUnfilteredAssignmentList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void removeViewChangeListener(ListChangeListener<View> listener) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addViewChangeListener(ListChangeListener<View> listener, View defaultView) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setView(View v) {
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

        @Override public void addAssignment(Assignment assignment) {
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
        public boolean hasAssignment(Assignment assignment) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteAssignment(Assignment target) {
            throw new AssertionError("This method should not be called.");
        }


        @Override
        public ObservableList<Assignment> getFilteredAssignmentList() {
            throw new AssertionError("This method should not be called.");
        }


        @Override
        public void updateFilteredAssignmentList(Predicate<Assignment> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void sortAssignments() {
        }

        @Override
        public boolean hasInternshipTask(InternshipTask internshipTask) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredInternshipTaskList(Predicate<InternshipTask> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void sortInternshipTasks() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setInternshipTask(InternshipTask task, InternshipTask newTask) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addInternshipTask(InternshipTask internshipTask) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteInternshipTask(InternshipTask internshipTask) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<InternshipTask> getUnfilteredInternshipTaskList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<InternshipRole> getUnfilteredInternshipRoleList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredInternshipRoleList(Predicate<InternshipRole> predicate) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single assignment.
     */
    private class ModelStubWithAssignment extends ModelStub {
        private final Assignment assignment;

        ModelStubWithAssignment(Assignment assignment) {
            requireNonNull(assignment);
            this.assignment = assignment;
        }

        @Override
        public boolean hasAssignment(Assignment assignment) {
            requireNonNull(assignment);
            return this.assignment.isDuplicate(assignment);
        }
    }

    /**
     * A Model stub that always accept the assignment being added.
     */
    private class ModelStubAcceptingAssignmentAdded extends ModelStub {
        private final ArrayList<Assignment> assignmentsAdded = new ArrayList<>();

        @Override
        public boolean hasAssignment(Assignment assignment) {
            requireNonNull(assignment);
            return assignmentsAdded.stream().anyMatch(assignment::isDuplicate);
        }

        @Override
        public void addAssignment(Assignment assignment) {
            requireNonNull(assignment);
            assignmentsAdded.add(assignment);
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }

}
