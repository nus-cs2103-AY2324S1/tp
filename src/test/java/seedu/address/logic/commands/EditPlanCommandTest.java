package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_GAMING;
import static seedu.address.logic.commands.CommandTestUtil.DESC_MEETING;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PLAN_NAME_GAMING;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPlanAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PLAN;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PLAN;
import static seedu.address.testutil.TypicalPlans.getTypicalAddressBookWithPlans;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.EditPlanCommand.EditPlanDescriptor;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.plan.Plan;
import seedu.address.testutil.EditPlanDescriptorBuilder;
import seedu.address.testutil.PlanBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditPlanCommand.
 */
public class EditPlanCommandTest {

    private Model model = new ModelManager(getTypicalAddressBookWithPlans(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Plan editedPlan = new PlanBuilder().build();
        EditPlanDescriptor descriptor = new EditPlanDescriptorBuilder(editedPlan).build();
        EditPlanCommand editPlanCommand = new EditPlanCommand(INDEX_FIRST_PLAN, descriptor);

        String expectedMessage = String.format(EditPlanCommand.MESSAGE_EDIT_PLAN_SUCCESS, Messages.format(editedPlan));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPlan(model.getFilteredPlanList().get(0), editedPlan);

        assertCommandSuccess(editPlanCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastPlan = Index.fromOneBased(model.getFilteredPlanList().size());
        Plan lastPlan = model.getFilteredPlanList().get(indexLastPlan.getZeroBased());

        PlanBuilder planInList = new PlanBuilder(lastPlan);
        Plan editedPlan = planInList.withPlanName(VALID_PLAN_NAME_GAMING).build();

        EditPlanDescriptor descriptor = new EditPlanDescriptorBuilder().withPlanName(VALID_PLAN_NAME_GAMING).build();
        EditPlanCommand editPlanCommand = new EditPlanCommand(indexLastPlan, descriptor);

        String expectedMessage = String.format(EditPlanCommand.MESSAGE_EDIT_PLAN_SUCCESS, Messages.format(editedPlan));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPlan(lastPlan, editedPlan);

        assertCommandSuccess(editPlanCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditPlanCommand editPlanCommand = new EditPlanCommand(INDEX_FIRST_PLAN, new EditPlanDescriptor());
        Plan editedPlan = model.getFilteredPlanList().get(INDEX_FIRST_PLAN.getZeroBased());

        String expectedMessage = String.format(EditPlanCommand.MESSAGE_EDIT_PLAN_SUCCESS, Messages.format(editedPlan));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        assertCommandSuccess(editPlanCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showPlanAtIndex(model, INDEX_FIRST_PLAN);

        Plan planInFilteredList = model.getFilteredPlanList().get(INDEX_FIRST_PLAN.getZeroBased());
        Plan editedPlan = new PlanBuilder(planInFilteredList).withPlanName(VALID_PLAN_NAME_GAMING).build();
        EditPlanCommand editPlanCommand = new EditPlanCommand(INDEX_FIRST_PLAN,
                new EditPlanDescriptorBuilder().withPlanName(VALID_PLAN_NAME_GAMING).build());

        String expectedMessage = String.format(EditPlanCommand.MESSAGE_EDIT_PLAN_SUCCESS, Messages.format(editedPlan));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPlan(model.getFilteredPlanList().get(0), editedPlan);

        assertCommandSuccess(editPlanCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicatePlanUnfilteredList_failure() {
        Plan firstPlan = model.getFilteredPlanList().get(INDEX_FIRST_PLAN.getZeroBased());
        EditPlanDescriptor descriptor = new EditPlanDescriptorBuilder(firstPlan).build();
        EditPlanCommand editPlanCommand = new EditPlanCommand(INDEX_SECOND_PLAN, descriptor);

        assertCommandFailure(editPlanCommand, model, EditPlanCommand.MESSAGE_DUPLICATE_PLAN);
    }

    @Test
    public void execute_duplicatePlanFilteredList_failure() {
        showPlanAtIndex(model, INDEX_FIRST_PLAN);

        // edit plan in filtered list into a duplicate in address book
        Plan planInList = model.getAddressBook().getPlanList().get(INDEX_SECOND_PLAN.getZeroBased());
        EditPlanCommand editPlanCommand = new EditPlanCommand(INDEX_FIRST_PLAN,
                new EditPlanDescriptorBuilder(planInList).build());

        assertCommandFailure(editPlanCommand, model, EditPlanCommand.MESSAGE_DUPLICATE_PLAN);
    }

    @Test
    public void execute_invalidPlanIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPlanList().size() + 1);
        EditPlanDescriptor descriptor = new EditPlanDescriptorBuilder().withPlanName(VALID_PLAN_NAME_GAMING).build();
        EditPlanCommand editPlanCommand = new EditPlanCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editPlanCommand, model, Messages.MESSAGE_INVALID_PLAN_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidPlanIndexFilteredList_failure() {
        showPlanAtIndex(model, INDEX_FIRST_PLAN);
        Index outOfBoundIndex = INDEX_SECOND_PLAN;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPlanList().size());

        EditPlanCommand editPlanCommand = new EditPlanCommand(outOfBoundIndex,
                new EditPlanDescriptorBuilder().withPlanName(VALID_PLAN_NAME_GAMING).build());

        assertCommandFailure(editPlanCommand, model, Messages.MESSAGE_INVALID_PLAN_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditPlanCommand standardCommand = new EditPlanCommand(INDEX_FIRST_PLAN, DESC_MEETING);

        // same values -> returns true
        EditPlanDescriptor copyDescriptor = new EditPlanDescriptor(DESC_MEETING);
        EditPlanCommand commandWithSameValues = new EditPlanCommand(INDEX_FIRST_PLAN, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditPlanCommand(INDEX_SECOND_PLAN, DESC_MEETING)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditPlanCommand(INDEX_SECOND_PLAN, DESC_GAMING)));
    }

    @Test
    public void toStringMethod() {
        Index index = Index.fromOneBased(1);
        EditPlanDescriptor editPlanDescriptor = new EditPlanDescriptor();
        EditPlanCommand editPlanCommand = new EditPlanCommand(index, editPlanDescriptor);
        String expected = EditPlanCommand.class.getCanonicalName() + "{index=" + index + ", editPlanDescriptor="
                + editPlanDescriptor + "}";
        assertEquals(expected, editPlanCommand.toString());
    }

}
