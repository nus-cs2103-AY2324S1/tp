package seedu.application.model.job;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.application.logic.commands.CommandTestUtil.*;
import static seedu.application.testutil.TypicalJobs.CHEF;
import static seedu.application.testutil.TypicalJobs.CLEANER;

import org.junit.jupiter.api.Test;

import seedu.application.testutil.JobBuilder;

public class JobTest {

    @Test
    public void isSameJob() {
        // same object -> returns true
        assertTrue(CHEF.isSameJob(CHEF));

        // null -> returns false
        assertFalse(CHEF.isSameJob(null));

        // To be implemented after non-identity fields are created
        // same role and company, all other attributes different -> returns true
        Job editedChef = new JobBuilder(CHEF).withCompany(VALID_COMPANY_CHEF).build();
        assertTrue(CHEF.isSameJob(editedChef));

        // different role, all other attributes same -> returns false
        editedChef = new JobBuilder(CHEF).withRole(VALID_ROLE_CLEANER).build();
        assertFalse(CHEF.isSameJob(editedChef));

        // role differs in case, all other attributes same -> returns false
        Job editedCleaner = new JobBuilder(CLEANER).withRole(VALID_ROLE_CLEANER.toLowerCase()).build();
        assertFalse(CLEANER.isSameJob(editedCleaner));

        // role has trailing spaces, all other attributes same -> returns false
        String roleWithTrailingSpaces = VALID_ROLE_CLEANER + " ";
        editedCleaner = new JobBuilder(CLEANER).withRole(roleWithTrailingSpaces).build();
        assertFalse(CLEANER.isSameJob(editedCleaner));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Job chefCopy = new JobBuilder(CHEF).build();
        assertTrue(CHEF.equals(chefCopy));

        // same object -> returns true
        assertTrue(CHEF.equals(CHEF));

        // null -> returns false
        assertFalse(CHEF.equals(null));

        // different type -> returns false
        assertFalse(CHEF.equals(5));

        // different job -> returns false
        assertFalse(CHEF.equals(CLEANER));

        // different role -> returns false
        Job editedChef = new JobBuilder(CHEF).withRole(VALID_ROLE_CLEANER).build();
        assertFalse(CHEF.equals(editedChef));

        // different company -> returns false
        editedChef = new JobBuilder(CHEF).withCompany(VALID_COMPANY_CLEANER).build();
        assertFalse(CHEF.equals(editedChef));

        // different status -> returns false
        editedChef = new JobBuilder(CHEF).withStatus(VALID_STATUS_CLEANER).build();
        assertFalse(CHEF.equals(editedChef));
    }

    @Test
    public void toStringMethod() {
        String expected = Job.class.getCanonicalName() + "{role=" + CHEF.getRole() + ", company="
                + CHEF.getCompany() + ", status=" + CHEF.getStatus() + ", deadline=" + CHEF.getDeadline()
                + ", jobType=" + CHEF.getJobType() + "}";
        assertEquals(expected, CHEF.toString());
    }
}
