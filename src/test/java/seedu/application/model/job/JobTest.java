package seedu.application.model.job;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.application.logic.commands.CommandTestUtil.VALID_COMPANY_CHEF;
import static seedu.application.logic.commands.CommandTestUtil.VALID_COMPANY_CLEANER;
import static seedu.application.logic.commands.CommandTestUtil.VALID_DEADLINE_CLEANER;
import static seedu.application.logic.commands.CommandTestUtil.VALID_INDUSTRY_CLEANER;
import static seedu.application.logic.commands.CommandTestUtil.VALID_JOB_TYPE_CLEANER;
import static seedu.application.logic.commands.CommandTestUtil.VALID_ROLE_CLEANER;
import static seedu.application.logic.commands.CommandTestUtil.VALID_STATUS_CLEANER;
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

        // role and company differ in case, all other attributes same -> returns true
        Job editedCleaner = new JobBuilder(CLEANER).withRole(VALID_ROLE_CLEANER.toLowerCase())
                .withCompany(VALID_COMPANY_CLEANER.toLowerCase()).build();
        assertTrue(CLEANER.isSameJob(editedCleaner));

        // role has trailing spaces, all other attributes same -> returns false
        String roleWithTrailingSpaces = VALID_ROLE_CLEANER + " ";
        editedCleaner = new JobBuilder(CLEANER).withRole(roleWithTrailingSpaces).build();
        assertFalse(CLEANER.isSameJob(editedCleaner));
    }

    @Test
    public void testEqualsAndHashcode() {
        // same values -> returns true
        Job chefCopy = new JobBuilder(CHEF).build();
        assertTrue(CHEF.equals(chefCopy));
        assertEquals(CHEF.hashCode(), chefCopy.hashCode());

        // same object -> returns true
        assertTrue(CHEF.equals(CHEF));

        // null -> returns false
        assertFalse(CHEF.equals(null));

        // different type -> returns false
        assertFalse(CHEF.equals(5.0f));

        // different job -> returns false
        assertFalse(CHEF.equals(CLEANER));
        assertNotEquals(CHEF.hashCode(), CLEANER.hashCode());

        // different role -> returns false
        Job chefEditedRole = new JobBuilder(CHEF).withRole(VALID_ROLE_CLEANER).build();
        assertFalse(CHEF.equals(chefEditedRole));
        assertNotEquals(CHEF.hashCode(), chefEditedRole.hashCode());

        // different company -> returns false
        Job chefEditedCompany = new JobBuilder(CHEF).withCompany(VALID_COMPANY_CLEANER).build();
        assertFalse(CHEF.equals(chefEditedCompany));
        assertNotEquals(CHEF.hashCode(), chefEditedCompany.hashCode());

        // different status -> returns false
        Job chefEditedStatus = new JobBuilder(CHEF).withStatus(VALID_STATUS_CLEANER).build();
        assertFalse(CHEF.equals(chefEditedStatus));
        assertNotEquals(CHEF.hashCode(), chefEditedStatus.hashCode());

        // different deadline -> returns false
        Job editedChefDeadline = new JobBuilder(CHEF).withDeadline(VALID_DEADLINE_CLEANER).build();
        assertFalse(CHEF.equals(editedChefDeadline));
        assertNotEquals(CHEF.hashCode(), editedChefDeadline.hashCode());

        // different job type -> returns false
        Job editedChefJobType = new JobBuilder(CHEF).withJobType(VALID_JOB_TYPE_CLEANER).build();
        assertFalse(CHEF.equals(editedChefJobType));
        assertNotEquals(CHEF.hashCode(), editedChefJobType.hashCode());

        // different industry -> returns false
        Job editedChefIndustry = new JobBuilder(CHEF).withIndustry(VALID_INDUSTRY_CLEANER).build();
        assertFalse(CHEF.equals(editedChefIndustry));
        assertNotEquals(CHEF.hashCode(), editedChefIndustry.hashCode());
    }

    @Test
    public void toStringMethod() {
        String expected = Job.class.getCanonicalName()
                + "{role=" + CHEF.getRole()
                + ", company=" + CHEF.getCompany()
                + ", deadline=" + CHEF.getDeadline()
                + ", status=" + CHEF.getStatus()
                + ", jobType=" + CHEF.getJobType()
                + ", industry=" + CHEF.getIndustry()
                + "}";
        assertEquals(expected, CHEF.toString());
    }
}
