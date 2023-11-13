package seedu.letsgethired.model.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static seedu.letsgethired.logic.parser.CliSyntax.PREFIX_COMPANY;
import static seedu.letsgethired.logic.parser.CliSyntax.PREFIX_CYCLE;
import static seedu.letsgethired.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.letsgethired.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.letsgethired.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.letsgethired.logic.parser.SortOrder.ASCENDING;
import static seedu.letsgethired.logic.parser.SortOrder.DESCENDING;
import static seedu.letsgethired.testutil.TypicalInternApplications.JANE_STREET;
import static seedu.letsgethired.testutil.TypicalInternApplications.OPTIVER;

import java.util.List;

import org.junit.jupiter.api.Test;

public class InternApplicationComparatorTest {
    @Test
    public void getComparator() {
        InternApplicationComparator companyAscending = InternApplicationComparator
                .getComparator(PREFIX_COMPANY, ASCENDING);
        InternApplicationComparator companyDescending = InternApplicationComparator
                .getComparator(PREFIX_COMPANY, DESCENDING);

        InternApplicationComparator cycleAscending = InternApplicationComparator
                .getComparator(PREFIX_CYCLE, ASCENDING);
        InternApplicationComparator cycleDescending = InternApplicationComparator
                .getComparator(PREFIX_CYCLE, DESCENDING);

        InternApplicationComparator roleAscending = InternApplicationComparator
                .getComparator(PREFIX_ROLE, ASCENDING);
        InternApplicationComparator roleDescending = InternApplicationComparator
                .getComparator(PREFIX_ROLE, DESCENDING);

        InternApplicationComparator statusAscending = InternApplicationComparator
                .getComparator(PREFIX_STATUS, ASCENDING);
        InternApplicationComparator statusDescending = InternApplicationComparator
                .getComparator(PREFIX_STATUS, DESCENDING);

        InternApplicationComparator deadlineDescending = InternApplicationComparator
                .getComparator(PREFIX_DEADLINE, DESCENDING);
        InternApplicationComparator deadlineAscending = InternApplicationComparator
                .getComparator(PREFIX_DEADLINE, ASCENDING);

        assertNotNull(companyAscending);
        assertNotNull(companyDescending);

        assertNotNull(cycleAscending);
        assertNotNull(cycleDescending);

        assertNotNull(roleAscending);
        assertNotNull(roleDescending);

        assertNotNull(statusAscending);
        assertNotNull(statusDescending);

        assertNotNull(deadlineDescending);
        assertNotNull(deadlineAscending);
    }

    @Test
    public void equals() {
        InternApplicationComparator companyAscending = InternApplicationComparator
                .getComparator(PREFIX_COMPANY, ASCENDING);
        InternApplicationComparator companyDescending = InternApplicationComparator
                .getComparator(PREFIX_COMPANY, DESCENDING);

        InternApplicationComparator cycleAscending = InternApplicationComparator
                .getComparator(PREFIX_CYCLE, ASCENDING);

        assertNotNull(companyAscending);
        assertNotNull(companyDescending);

        assertNotNull(cycleAscending);

        // Same prefix and order -> returns true
        assertEquals(companyAscending, InternApplicationComparator.getComparator(PREFIX_COMPANY, ASCENDING));

        // Same prefix different order -> returns false
        assertNotEquals(companyAscending, companyDescending);

        // Different prefix same order -> returns false
        assertNotEquals(companyAscending, cycleAscending);
    }

    @Test
    public void createCompositeComparator() {
        InternApplicationComparator companyAscending = InternApplicationComparator
                .getComparator(PREFIX_COMPANY, ASCENDING);
        InternApplicationComparator companyDescending = InternApplicationComparator
                .getComparator(PREFIX_COMPANY, DESCENDING);

        InternApplicationComparator cycleAscending = InternApplicationComparator
                .getComparator(PREFIX_CYCLE, ASCENDING);

        assertNotNull(companyAscending);
        assertNotNull(companyDescending);

        assertNotNull(cycleAscending);

        // not a Comparator object
        assertNotEquals(companyAscending, new Object());

        // Composite comparator in same order -> returns true
        InternApplicationComparator firstComposite = InternApplicationComparator.createCompositeComparator(
                List.of(companyAscending, cycleAscending));
        InternApplicationComparator firstCompositeSame = InternApplicationComparator.createCompositeComparator(
                List.of(companyAscending, cycleAscending));
        assertEquals(firstComposite, firstCompositeSame);

        InternApplicationComparator secondComposite = InternApplicationComparator.createCompositeComparator(
                List.of(companyAscending));
        // Different lengths -> returns false
        assertNotEquals(firstComposite, secondComposite);

        InternApplicationComparator thirdComposite = InternApplicationComparator.createCompositeComparator(
                List.of(cycleAscending, companyAscending));
        // Different order -> returns false
        assertNotEquals(firstComposite, thirdComposite);
    }

    @Test
    public void compare() {
        InternApplicationComparator companyAscending = InternApplicationComparator
                .getComparator(PREFIX_COMPANY, ASCENDING);

        assertNotNull(companyAscending);

        // same internship applications
        assertEquals(companyAscending.compare(JANE_STREET, JANE_STREET), 0);

        // different internship applications
        assertNotEquals(companyAscending.compare(JANE_STREET, OPTIVER), 0);
    }
}
