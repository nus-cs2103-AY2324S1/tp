package seedu.address.logic.commands.internships.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.commands.CommandAssignmentTestUtil.assertCommandSuccess;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.CommandAssignmentTestUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.internship.task.EditInternshipTaskCommand;
import seedu.address.logic.commands.internship.task.FindInternshipTaskCommand;
import seedu.address.model.AddressBook;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.fields.Outcome;
import seedu.address.model.fields.TaskOutcome;
import seedu.address.model.internship.role.InternshipRole;
import seedu.address.model.internship.task.InternshipTask;
import seedu.address.model.internship.task.InternshipTaskNameContainsKeywordsPredicate;
import seedu.address.testutil.InternshipRoleBuilder;
import seedu.address.testutil.InternshipTaskBuilder;

public class EditInternshipTaskCommandTest {
    private final InternshipRole role1 = new InternshipRoleBuilder().withName("Company1")
            .withRole("SWE").withCycle("Summer").build();

    private final InternshipRole role2 = new InternshipRoleBuilder().withName("Company2")
            .withRole("SWE").withCycle("Winter").build();

    private final InternshipTask task1BelongingToRole1 = new InternshipTaskBuilder()
            .withInternshipRole(role1).withTaskName("Interview Preparation_1").build();

    private final InternshipTask task2BelongingToRole1 = new InternshipTaskBuilder()
            .withInternshipRole(role1).withTaskName("OA_1").build();

    private final InternshipTask task1BelongingToRole2 = new InternshipTaskBuilder()
            .withInternshipRole(role2).withTaskName("Interview Preparation_2").build();

    private final InternshipTask task2BelongingToRole2 = new InternshipTaskBuilder()
            .withInternshipRole(role2).withTaskName("OA_2").build();

    private ModelManager model;

    private AddressBook getAddressBook() {
        AddressBook ab = new AddressBook();
        ab.addInternshipRole(role1);
        ab.addInternshipRole(role2);
        ab.addInternshipTask(task1BelongingToRole1);
        ab.addInternshipTask(task2BelongingToRole1);
        ab.addInternshipTask(task1BelongingToRole2);
        ab.addInternshipTask(task2BelongingToRole2);
        return ab;
    }

    private EditInternshipTaskCommand getSampleEditCommand() {
        return new EditInternshipTaskCommand(Index.fromOneBased(1),
                new TaskOutcome(Outcome.AWAITING));
    }

    @BeforeEach
    public void init() {
        model = new ModelManager(getAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_allFieldsProvided_taskEdited() {
        Index index = Index.fromOneBased(1);

        //When executed, the InternshipTask at the specified index will be edited with the TaskOutcome
        EditInternshipTaskCommand editCommand =
                new EditInternshipTaskCommand(index, new TaskOutcome(Outcome.ACCEPTED));

        ModelManager expectedModel = new ModelManager(getAddressBook(), new UserPrefs());

        //An InternshipTask that is identical to the task after it has been edited
        InternshipTask editedTask = new InternshipTaskBuilder().withInternshipRole(role1)
                .withTaskName("Interview Preparation_1")
                .withOutcome(Outcome.ACCEPTED).build();

        expectedModel.setInternshipTask(task1BelongingToRole1, editedTask);

        String expectedMessage = String.format(EditInternshipTaskCommand.MESSAGE_SUCCESS, Messages.format(editedTask));

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    // Integration Test with Find Task Command
    @Test
    public void execute_afterFindTaskCommand_tasksEdited() throws CommandException {
        Index index = Index.fromOneBased(1);
        EditInternshipTaskCommand editCommand =
                new EditInternshipTaskCommand(index, new TaskOutcome(Outcome.ACCEPTED));

        FindInternshipTaskCommand findTaskCommand = new FindInternshipTaskCommand(new
                InternshipTaskNameContainsKeywordsPredicate(List.of("OA")));
        findTaskCommand.execute(model);

        InternshipTask editedTask = new InternshipTaskBuilder().withInternshipRole(role1)
                .withTaskName("OA_1")
                .withOutcome(Outcome.ACCEPTED).build();

        editCommand.execute(model);

        ModelManager expectedUnfilteredModel = new ModelManager(getAddressBook(), new UserPrefs());

        expectedUnfilteredModel.setInternshipTask(task2BelongingToRole1, editedTask);

        assertEquals(model.getUnfilteredInternshipTaskList(),
                expectedUnfilteredModel.getUnfilteredInternshipTaskList());
        assertEquals(model.getFilteredInternshipTaskList(),
                List.of(expectedUnfilteredModel.getUnfilteredInternshipTaskList().get(1),
                        expectedUnfilteredModel.getUnfilteredInternshipTaskList().get(3)));
    }


    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredInternshipTaskList().size() + 1);
        EditInternshipTaskCommand editCommand =
                new EditInternshipTaskCommand(outOfBoundIndex, new TaskOutcome(Outcome.OFFERED));

        CommandAssignmentTestUtil.assertCommandFailure(editCommand, model,
                Messages.MESSAGE_INVALID_INTERNSHIP_TASK_DISPLAYED_INDEX);
    }


    @Test
    public void equals() {
        EditInternshipTaskCommand editFirstCommand = getSampleEditCommand();
        EditInternshipTaskCommand editSecondCommand = new EditInternshipTaskCommand(
                Index.fromOneBased(1), new TaskOutcome(Outcome.FOLLOW_UP));

        // same object -> returns true
        assertEquals(editFirstCommand, editFirstCommand);

        // same object -> returns true
        assertNotEquals(editFirstCommand, null);

        // same values -> returns true
        EditInternshipTaskCommand editFirstCommandCopy = getSampleEditCommand();

        assertEquals(editFirstCommand, editFirstCommandCopy);

        // different types -> returns false
        assertNotEquals(1, editFirstCommand);

        // null -> returns false
        assertNotEquals(null, editFirstCommand);

        // different edit cmd -> returns false
        assertNotEquals(editFirstCommand, editSecondCommand);
    }
}
