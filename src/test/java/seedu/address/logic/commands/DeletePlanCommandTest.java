package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPlanAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PLAN;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PLAN;
import static seedu.address.testutil.TypicalPlans.getTypicalAddressBookWithPlans;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.plan.Plan;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeletePlanCommand}.
 */
public class DeletePlanCommandTest {

    private Model model = new ModelManager(getTypicalAddressBookWithPlans(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Plan planToDelete = model.getFilteredPlanList().get(INDEX_FIRST_PLAN.getZeroBased());
        DeletePlanCommand deletePlanCommand = new DeletePlanCommand(INDEX_FIRST_PLAN);

        String expectedMessage = String.format(DeletePlanCommand.MESSAGE_DELETE_PLAN_SUCCESS,
                Messages.format(planToDelete));

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deletePlan(planToDelete);

        assertCommandSuccess(deletePlanCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPlanList().size() + 1);
        DeletePlanCommand deletePlanCommand = new DeletePlanCommand(outOfBoundIndex);

        assertCommandFailure(deletePlanCommand, model, Messages.MESSAGE_INVALID_PLAN_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showPlanAtIndex(model, INDEX_FIRST_PLAN);

        Plan planToDelete = model.getFilteredPlanList().get(INDEX_FIRST_PLAN.getZeroBased());
        DeletePlanCommand deletePlanCommand = new DeletePlanCommand(INDEX_FIRST_PLAN);

        String expectedMessage = String.format(DeletePlanCommand.MESSAGE_DELETE_PLAN_SUCCESS,
                Messages.format(planToDelete));

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deletePlan(planToDelete);
        showNoPlan(expectedModel);

        assertCommandSuccess(deletePlanCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showPlanAtIndex(model, INDEX_FIRST_PLAN);

        Index outOfBoundIndex = INDEX_SECOND_PLAN;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPlanList().size());

        DeletePlanCommand deletePlanCommand = new DeletePlanCommand(outOfBoundIndex);

        assertCommandFailure(deletePlanCommand, model, Messages.MESSAGE_INVALID_PLAN_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeletePlanCommand deletePlanFirstCommand = new DeletePlanCommand(INDEX_FIRST_PLAN);
        DeletePlanCommand deletePlanSecondCommand = new DeletePlanCommand(INDEX_SECOND_PLAN);

        // same object -> returns true
        assertTrue(deletePlanFirstCommand.equals(deletePlanFirstCommand));

        // same values -> returns true
        DeletePlanCommand deletePlanFirstCommandCopy = new DeletePlanCommand(INDEX_FIRST_PLAN);
        assertTrue(deletePlanFirstCommand.equals(deletePlanFirstCommandCopy));

        // different types -> returns false
        assertFalse(deletePlanFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deletePlanFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(deletePlanFirstCommand.equals(deletePlanSecondCommand));
    }

    @Test
    public void toStringMethod() {
        Index targetIndex = Index.fromOneBased(1);
        DeletePlanCommand deletePlanCommand = new DeletePlanCommand(targetIndex);
        String expected = DeletePlanCommand.class.getCanonicalName() + "{targetIndex=" + targetIndex + "}";
        assertEquals(expected, deletePlanCommand.toString());
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoPlan(Model model) {
        model.updateFilteredPlanList(p -> false);

        assertTrue(model.getFilteredPlanList().isEmpty());
    }
}
