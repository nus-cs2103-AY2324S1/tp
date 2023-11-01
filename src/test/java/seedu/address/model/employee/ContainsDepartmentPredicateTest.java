package seedu.address.model.employee;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.EmployeeBuilder;

public class ContainsDepartmentPredicateTest {
    @Test
    public void test_containsDepartment_returnsTrue() {
        // One keyword
        ContainsDepartmentPredicate predicate = new ContainsDepartmentPredicate("R&D");
        assertTrue(predicate.test(new EmployeeBuilder().withDepartments("R&D").build()));
    }

    @Test
    public void test_doesNotContainDepartment_returnsFalse() {
        // Zero keywords
        ContainsDepartmentPredicate predicate = new ContainsDepartmentPredicate(" ");
        assertFalse(predicate.test(new EmployeeBuilder().withDepartments("R&D").build()));

        // Non-matching keyword
        predicate = new ContainsDepartmentPredicate("R&D");
        assertFalse(predicate.test(new EmployeeBuilder().withDepartments("Finance").build()));
    }

    @Test
    public void toStringMethod() {
        String keyword = "Finance";
        ContainsDepartmentPredicate predicate = new ContainsDepartmentPredicate(keyword);

        String expected = ContainsDepartmentPredicate.class.getCanonicalName() + "{keyword=" + keyword + "}";
        assertEquals(expected, predicate.toString());
    }
}
