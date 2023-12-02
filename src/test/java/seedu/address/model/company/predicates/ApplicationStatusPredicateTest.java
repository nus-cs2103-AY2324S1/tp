package seedu.address.model.company.predicates;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.model.company.ApplicationStatus;
import seedu.address.testutil.CompanyBuilder;

public class ApplicationStatusPredicateTest {

    @Test
    public void equals() {
        ApplicationStatus firstPredicateStatus = new ApplicationStatus("pa");
        ApplicationStatus secondPredicateStatus = new ApplicationStatus("pi");

        ApplicationStatusPredicate firstPredicate = new ApplicationStatusPredicate(firstPredicateStatus);
        ApplicationStatusPredicate secondPredicate = new ApplicationStatusPredicate(secondPredicateStatus);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        ApplicationStatusPredicate firstPredicateCopy = new ApplicationStatusPredicate(firstPredicateStatus);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different predicate -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_companyContainsStatus_returnsTrue() {
        ApplicationStatusPredicate predicate = new ApplicationStatusPredicate(new ApplicationStatus("pa"));
        assertTrue(predicate.test(new CompanyBuilder().withStatus("pa").build()));

        predicate = new ApplicationStatusPredicate(new ApplicationStatus("pi"));
        assertTrue(predicate.test(new CompanyBuilder().withStatus("pi").build()));
    }

    @Test
    public void test_companyDoesNotContainStatus_returnsFalse() {
        ApplicationStatusPredicate predicate = new ApplicationStatusPredicate(new ApplicationStatus("pa"));
        assertFalse(predicate.test(new CompanyBuilder().withStatus("pi").build()));

        predicate = new ApplicationStatusPredicate(new ApplicationStatus("pi"));
        assertFalse(predicate.test(new CompanyBuilder().withStatus("pa").build()));
    }
}
