package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_PLANS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.AddPlanCommand.MESSAGE_FRIEND_NOT_FOUND;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPlans.BALI;
import static seedu.address.testutil.TypicalPlans.EXERCISING;
import static seedu.address.testutil.TypicalPlans.GAMING;
import static seedu.address.testutil.TypicalPlans.HACKATHON;
import static seedu.address.testutil.TypicalPlans.MEETING;
import static seedu.address.testutil.TypicalPlans.getTypicalAddressBookWithPlans;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Name;
import seedu.address.model.plan.PlanContainsFriendPredicate;


/**
 * Contains integration tests (interaction with the Model) for {@code FindPlanCommand}.
 */
public class FindPlanCommandTest {

    private Model model = new ModelManager(getTypicalAddressBookWithPlans(), new UserPrefs());

    private Model expectedModel = new ModelManager(getTypicalAddressBookWithPlans(), new UserPrefs());

    @Test
    public void equals() {
        Name firstName = new Name("Alice Pauline");
        Name secondName = new Name("Benson Meier");

        FindPlanCommand findFirstPlanCommand = new FindPlanCommand(firstName);
        FindPlanCommand findSecondPlanCommand = new FindPlanCommand(secondName);

        // same object -> returns true
        assertTrue(findFirstPlanCommand.equals(findFirstPlanCommand));

        // same values -> returns true
        FindPlanCommand findFirstPlanCommandCopy = new FindPlanCommand(firstName);
        assertTrue(findFirstPlanCommand.equals(findFirstPlanCommandCopy));

        // different types -> returns false
        assertFalse(findFirstPlanCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstPlanCommand.equals(null));

        // different person -> returns false
        assertFalse(findFirstPlanCommand.equals(findSecondPlanCommand));
    }

    @Test
    public void execute_friendNameDoesNotExist_throwsCommandException() {
        Name friendName = new Name("Victor Lai");
        FindPlanCommand command = new FindPlanCommand(friendName);
        assertCommandFailure(command, model, MESSAGE_FRIEND_NOT_FOUND);
    }

    @Test
    public void execute_friendWithNoPlans_noPlanFound() {
        String expectedMessage = String.format(MESSAGE_PLANS_LISTED_OVERVIEW, 0);
        Name friendName = new Name("Carl Kurz");
        PlanContainsFriendPredicate predicate = preparePredicate(friendName);
        FindPlanCommand command = new FindPlanCommand(friendName);
        expectedModel.updateFilteredPlanList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPlanList());
    }

    @Test
    public void execute_friendWithPlans_multiplePlansFound() {
        String expectedMessage = String.format(MESSAGE_PLANS_LISTED_OVERVIEW, 5);
        Name friendName = new Name("Alice Pauline");
        PlanContainsFriendPredicate predicate = preparePredicate(friendName);
        FindPlanCommand command = new FindPlanCommand(friendName);
        expectedModel.updateFilteredPlanList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(MEETING, GAMING, EXERCISING, HACKATHON, BALI), model.getFilteredPlanList());
    }

    @Test
    public void toStringMethod() {
        Name friendName = new Name("Alice Pauline");
        FindPlanCommand command = new FindPlanCommand(friendName);
        String expected = FindPlanCommand.class.getCanonicalName() + "{Friend=" + friendName + "}";
        assertEquals(expected, command.toString());
    }

    /**
     * Parses {@code userInput} into a {@code PlanContainsFriendPredicate}.
     */
    private PlanContainsFriendPredicate preparePredicate(Name friendName) {
        return new PlanContainsFriendPredicate(model.getPersonByName(friendName));
    }
}
