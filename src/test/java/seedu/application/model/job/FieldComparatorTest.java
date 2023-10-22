package seedu.application.model.job;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.application.model.job.Company.COMPANY_SPECIFIER;
import static seedu.application.model.job.Deadline.DEADLINE_SPECIFIER;
import static seedu.application.model.job.Deadline.TO_ADD_DEADLINE;
import static seedu.application.model.job.FieldComparator.EMPTY_COMPARATOR_SPECIFIER;
import static seedu.application.model.job.Role.ROLE_SPECIFIER;
import static seedu.application.model.job.Status.STATUS_SPECIFIER;
import static seedu.application.testutil.TypicalJobs.CHEF;
import static seedu.application.testutil.TypicalJobs.CLEANER;

import org.junit.jupiter.api.Test;
import seedu.application.testutil.JobBuilder;

public class FieldComparatorTest {

    @Test
    public void isValidSpecifier_validSpecifiers_returnTrue() {
        assertTrue(FieldComparator.isValidSpecifier(EMPTY_COMPARATOR_SPECIFIER));
        assertTrue(FieldComparator.isValidSpecifier(COMPANY_SPECIFIER));
        assertTrue(FieldComparator.isValidSpecifier(ROLE_SPECIFIER));
        assertTrue(FieldComparator.isValidSpecifier(DEADLINE_SPECIFIER));
        assertTrue(FieldComparator.isValidSpecifier(STATUS_SPECIFIER));
    }

    @Test
    public void isValidSpecifier_invalidSpecifiers_returnFalse() {
        assertFalse(FieldComparator.isValidSpecifier("x"));
        assertFalse(FieldComparator.isValidSpecifier("description"));
        assertFalse(FieldComparator.isValidSpecifier("-k"));
        assertFalse(FieldComparator.isValidSpecifier(" "));
    }

    @Test
    public void hasSpecifier_specifierPresent_returnsTrue() {
        FieldComparator comparator = new FieldComparator(ROLE_SPECIFIER);
        assertTrue(comparator.hasSpecifier());
    }

    @Test
    public void hasSpecifier_noSpecifier_returnsFalse() {
        FieldComparator comparator = new FieldComparator(EMPTY_COMPARATOR_SPECIFIER);
        assertFalse(comparator.hasSpecifier());
    }

    @Test
    public void compare_validSpecifiers_correctComparison() {
        Job job1 = new JobBuilder(CLEANER).withDeadline(TO_ADD_DEADLINE).build();
        Job job2 = new JobBuilder(CHEF).withDeadline(TO_ADD_DEADLINE).build();

        FieldComparator roleComparator = new FieldComparator(ROLE_SPECIFIER);
        assertTrue(roleComparator.compare(job1, job2) > 0); // job2 should be before job1

        FieldComparator deadlineComparator = new FieldComparator(DEADLINE_SPECIFIER);
        assertEquals(0, deadlineComparator.compare(job1, job2)); // job1 and job2 has the same deadline

        FieldComparator statusComparator = new FieldComparator(STATUS_SPECIFIER);
        assertTrue(statusComparator.compare(job1, job2) < 0); // job2 should be after job1

        FieldComparator emptyComparator = new FieldComparator(EMPTY_COMPARATOR_SPECIFIER);
        assertEquals(-1, emptyComparator.compare(job1, job2));
    }

    @Test
    public void compare_invalidSpecifier_defaultComparison() {
        Job job1 = new JobBuilder(CHEF).build();
        Job job2 = new JobBuilder(CLEANER).build();

        FieldComparator invalidComparator = new FieldComparator("x");
        assertEquals(-1, invalidComparator.compare(job1, job2));
    }
}