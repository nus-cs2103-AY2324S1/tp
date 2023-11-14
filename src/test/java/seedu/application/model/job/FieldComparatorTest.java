package seedu.application.model.job;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.application.logic.parser.CliSyntax.*;
import static seedu.application.testutil.TypicalJobs.CHEF;
import static seedu.application.testutil.TypicalJobs.CLEANER;

import org.junit.jupiter.api.Test;

import seedu.application.logic.parser.Prefix;
import seedu.application.testutil.JobBuilder;

public class FieldComparatorTest {

    @Test
    public void isValidSpecifier_validSpecifiers_returnTrue() {
        assertTrue(FieldComparator.isValidPrefix(PREFIX_COMPANY.getPrefix()));
        assertTrue(FieldComparator.isValidPrefix(PREFIX_ROLE.getPrefix()));
        assertTrue(FieldComparator.isValidPrefix(PREFIX_STATUS.getPrefix()));
        assertTrue(FieldComparator.isValidPrefix(PREFIX_INDUSTRY.getPrefix()));
        assertTrue(FieldComparator.isValidPrefix(PREFIX_DEADLINE.getPrefix()));
        assertTrue(FieldComparator.isValidPrefix(PREFIX_JOB_TYPE.getPrefix()));
    }

    @Test
    public void isValidSpecifier_invalidSpecifiers_returnFalse() {
        assertFalse(FieldComparator.isValidPrefix("x"));
        assertFalse(FieldComparator.isValidPrefix("description"));
        assertFalse(FieldComparator.isValidPrefix("-k"));
        assertFalse(FieldComparator.isValidPrefix(" "));
    }

    @Test
    public void compare_validSpecifiers_correctComparison() {
        Job job1 = new JobBuilder(CLEANER).withDeadline("Dec 15 2024 1200").build();
        Job job2 = new JobBuilder(CHEF).withDeadline("Dec 15 2024 1200").build();

        FieldComparator roleComparator = new FieldComparator(PREFIX_ROLE);
        assertTrue(roleComparator.compare(job1, job2) > 0); // job2 should be before job1

        FieldComparator deadlineComparator = new FieldComparator(PREFIX_DEADLINE);
        assertEquals(0, deadlineComparator.compare(job1, job2)); // job1 and job2 has the same deadline

        FieldComparator statusComparator = new FieldComparator(PREFIX_STATUS);
        assertTrue(statusComparator.compare(job1, job2) < 0); // job2 should be after job1
    }

    @Test
    public void compare_invalidSpecifier_defaultComparison() {
        Job job1 = new JobBuilder(CHEF).build();
        Job job2 = new JobBuilder(CLEANER).build();

        FieldComparator invalidComparator = new FieldComparator(new Prefix("x/"));
        assertEquals(-1, invalidComparator.compare(job1, job2));
    }

    @Test
    public void equals() {
        FieldComparator fieldComparator = new FieldComparator(PREFIX_ROLE);

        // same values -> returns true
        assertEquals(fieldComparator, new FieldComparator(PREFIX_ROLE));

        // same object -> returns true
        assertEquals(fieldComparator, fieldComparator);

        // null -> returns false
        assertNotEquals(fieldComparator, null);

        // different types -> returns false
        assertNotEquals(fieldComparator, 5.0f);

        // different values -> returns false
        assertNotEquals(fieldComparator, new FieldComparator(PREFIX_STATUS));
    }
}
