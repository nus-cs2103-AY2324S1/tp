package seedu.address.model.person.gatheremail;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_FINANCIAL_PLAN_1;
import static seedu.address.testutil.TypicalPersons.BOB;
import static seedu.address.testutil.TypicalPersons.ELLE;

import org.junit.jupiter.api.Test;

public class GatherEmailByFinancialPlanTest {

    @Test
    public void gatherEmails() {
        GatherEmailByFinancialPlan fpPrompt = new GatherEmailByFinancialPlan(VALID_FINANCIAL_PLAN_1);
        assertEquals(new String(), fpPrompt.gatherEmails(ELLE));
        assertEquals(BOB.getEmail().toString(), fpPrompt.gatherEmails(BOB));
    }

    @Test
    public void testEquals() {
        GatherEmailByFinancialPlan first = new GatherEmailByFinancialPlan("first");
        GatherEmailByFinancialPlan second = new GatherEmailByFinancialPlan("second");
        GatherEmailByFinancialPlan firstOther = new GatherEmailByFinancialPlan("first");

        // same object -> returns true
        assertTrue(first.equals(first));

        // different types -> returns false
        assertFalse(first.equals(1));

        // null -> returns false
        assertFalse(first.equals(null));

        // different object -> returns false
        assertFalse(first.equals(second));

        // different object -> returns true
        assertTrue(first.equals(firstOther));
    }

    @Test
    public void testToString() {
        String prompt = "prompt";
        String expected = "Financial Plan: " + prompt;
        GatherEmailByFinancialPlan fpPrompt = new GatherEmailByFinancialPlan(prompt);
        assertEquals(fpPrompt.toString(), expected);
    }
}
