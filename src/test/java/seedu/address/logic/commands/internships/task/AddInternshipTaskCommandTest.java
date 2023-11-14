package seedu.address.logic.commands.internships.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.internship.role.AddInternshipRoleCommand;
import seedu.address.logic.commands.internship.task.AddInternshipTaskCommand;
import seedu.address.model.ModelManager;
import seedu.address.model.fields.IsoDate;
import seedu.address.model.fields.Name;
import seedu.address.model.fields.Outcome;
import seedu.address.model.fields.Status;
import seedu.address.model.fields.TaskOutcome;
import seedu.address.model.internship.role.InternshipRole;
import seedu.address.model.internship.task.InternshipTask;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.InternshipTaskBuilder;
import seedu.address.testutil.TypicalInternshipRoles;



public class AddInternshipTaskCommandTest {
    private final Name defaultTaskName = new Name("Grind Leetcode");
    private final IsoDate defaultDate = new IsoDate(LocalDateTime.parse("2024-04-01 23:59",
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
    private final Status defaultStatus = new Status(false);
    private final TaskOutcome defaultOutcome = new TaskOutcome(Outcome.FOLLOW_UP);
    private Set<Tag> tags = new HashSet<>();

    private ModelManager manager = new ModelManager();

    private final InternshipRole role1 = TypicalInternshipRoles.getTypicalInternshipRole1();

    private final InternshipTask task1 = new InternshipTaskBuilder(role1).build();

    @BeforeEach
    public void init() {
        manager = new ModelManager();
    }

    @Test
    public void constructor_nullInternshipRole_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddInternshipRoleCommand(null));
    }

    @Test
    public void execute_internshipTaskAcceptedByModel_addSuccessful() throws CommandException {
        CommandResult cr = new AddInternshipRoleCommand(role1).execute(manager);
        assertEquals(String.format(AddInternshipRoleCommand.MESSAGE_SUCCESS, Messages.format(role1)),
                cr.getFeedbackToUser());
        CommandResult cr2 = new AddInternshipTaskCommand(Index.fromOneBased(1), defaultTaskName,
                defaultDate, defaultStatus, defaultOutcome, tags).execute(manager);
        assertEquals(String.format(AddInternshipTaskCommand.MESSAGE_SUCCESS, Messages.format(task1)),
                cr2.getFeedbackToUser());
        assertEquals(manager.getFilteredInternshipRoleList(), FXCollections.observableArrayList(role1));
        assertEquals(manager.getFilteredInternshipTaskList(), FXCollections.observableArrayList(task1));
    }

    @Test
    public void execute_internshipTaskDuplicate_failure() {
        manager.addInternshipRole(role1);
        manager.addInternshipTask(task1);
        assertThrows(CommandException.class, AddInternshipTaskCommand.MESSAGE_DUPLICATE_TASK, () ->
                new AddInternshipTaskCommand(Index.fromOneBased(1), defaultTaskName,
                        defaultDate, defaultStatus, defaultOutcome, tags).execute(manager));
    }

    @Test
    public void execute_internshipTaskNonExistentRoleAtIndex_addFailure() throws CommandException {
        AddInternshipTaskCommand addTaskCommand = new AddInternshipTaskCommand(Index.fromOneBased(1), defaultTaskName,
                defaultDate, defaultStatus, defaultOutcome, tags);
        assertThrows(CommandException.class, AddInternshipTaskCommand.MESSAGE_INVALID_ROLE_INDEX, () ->
                addTaskCommand.execute(manager));
    }

    @Test
    public void equals() {
        AddInternshipTaskCommand addTask1Command = new AddInternshipTaskCommand(Index.fromOneBased(1),
                defaultTaskName, defaultDate, defaultStatus, defaultOutcome, tags);
        AddInternshipTaskCommand addTask2Command = new AddInternshipTaskCommand(Index.fromOneBased(1),
                new Name("Leetcode"), defaultDate, new Status(true), defaultOutcome, tags);

        // same object -> returns true
        assertTrue(addTask1Command.equals(addTask1Command));

        // same values -> returns true
        AddInternshipTaskCommand addTask1CommandCopy =
                new AddInternshipTaskCommand(Index.fromOneBased(1), defaultTaskName,
                        defaultDate, defaultStatus, defaultOutcome, tags);
        assertTrue(addTask1Command.equals(addTask1CommandCopy));

        // different types -> returns false
        assertFalse(addTask1Command.equals(1));

        // null -> returns false
        assertFalse(addTask1Command.equals(null));

        // different tasks -> returns false
        assertFalse(addTask1Command.equals(addTask2Command));
    }

    @Test
    public void toStringMethod() {
        Index idx = Index.fromZeroBased(1);
        AddInternshipTaskCommand addCommand = new AddInternshipTaskCommand(idx,
                defaultTaskName, defaultDate, defaultStatus, defaultOutcome, tags);
        String expected = AddInternshipTaskCommand.class.getCanonicalName() + "{internshipRoleIndex="
                + idx + ", "
                + "taskName=" + defaultTaskName + ", "
                + "deadline=" + defaultDate + ", "
                + "status=" + defaultStatus + ", "
                + "outcome=" + defaultOutcome + ", "
                + "tags=" + tags
                + "}";
        assertEquals(expected, addCommand.toString());
    }

}
