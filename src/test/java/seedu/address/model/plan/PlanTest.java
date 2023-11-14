package seedu.address.model.plan;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PLAN_DATETIME_GAMING;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PLAN_NAME_GAMING;
import static seedu.address.testutil.TypicalPersons.BOB;
import static seedu.address.testutil.TypicalPlans.BALI;
import static seedu.address.testutil.TypicalPlans.HACKATHON;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PlanBuilder;

class PlanTest {

    @Test
    public void equals() {
        // same values -> returns true
        Plan baliCopy = new PlanBuilder(BALI).build();
        assertTrue(BALI.equals(baliCopy));

        // same object -> returns true
        assertTrue(BALI.equals(BALI));

        // null -> returns false
        assertFalse(BALI.equals(null));

        // different type -> returns false
        assertFalse(BALI.equals(5));

        // different plans -> returns false
        assertFalse(BALI.equals(HACKATHON));

        // different plan name -> returns false
        Plan editedBaliPlan = new PlanBuilder(BALI).withPlanName(VALID_PLAN_NAME_GAMING).build();
        assertFalse(BALI.equals(editedBaliPlan));

        // different friend -> returns false
        editedBaliPlan = new PlanBuilder(BALI).withPlanFriend(BOB).build();
        assertFalse(BALI.equals(editedBaliPlan));

        // different date time -> returns false
        editedBaliPlan = new PlanBuilder(BALI).withPlanDateTime(VALID_PLAN_DATETIME_GAMING).build();
        assertFalse(BALI.equals(editedBaliPlan));
    }

    @Test
    public void isSamePlan() {
        // same object -> returns true
        assertTrue(BALI.isSamePlan(BALI));

        // null -> returns false
        assertFalse(BALI.isSamePlan(null));

        // same planName, all other attributes different -> returns false
        Plan editedBali = new PlanBuilder(BALI).withPlanFriend(BOB)
                .withPlanDateTime("2026-06-01-09:00").build();
        assertFalse(BALI.isSamePlan(editedBali));

        // different name, all other attributes same -> returns false
        editedBali = new PlanBuilder(BALI).withPlanName("Dota 2").build();
        assertFalse(BALI.isSamePlan(editedBali));

        // name differs in case, all other attributes same -> returns false
        editedBali = new PlanBuilder(BALI).withPlanName("Bali Trip".toLowerCase()).build();
        assertTrue(BALI.isSamePlan(editedBali));

        // name has trailing spaces, all other attributes same -> returns false
        String planNameWithTrailingSpaces = "Bali Trip" + " ";
        editedBali = new PlanBuilder(BALI).withPlanName(planNameWithTrailingSpaces).build();
        assertFalse(BALI.isSamePlan(editedBali));
    }

    @Test
    public void toStringMethod() {
        String expected = '[' + BALI.getPlanName().toString() + " with "
                + BALI.getPlanFriend().getName() + " at " + BALI.getPlanDateTime() + ']';
        assertEquals(expected, BALI.toString());
    }
}
