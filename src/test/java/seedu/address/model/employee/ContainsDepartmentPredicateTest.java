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
        String keyword = "R&D";
        ContainsDepartmentPredicate predicate = new ContainsDepartmentPredicate();
        predicate.setDepartment(keyword);
        assertTrue(predicate.test(new EmployeeBuilder().withDepartments(keyword).build()));
    }

    @Test
    public void test_doesNotContainDepartment_returnsFalse() {
        // Zero keywords
        ContainsDepartmentPredicate predicate = new ContainsDepartmentPredicate();
        predicate.setDepartment(" ");
        assertFalse(predicate.test(new EmployeeBuilder().withDepartments("R&D").build()));

        // Non-matching keyword
        predicate = new ContainsDepartmentPredicate();
        predicate.setDepartment("R&D");
        assertFalse(predicate.test(new EmployeeBuilder().withDepartments("Finance").build()));
    }

    @Test
    public void test_containsSalary_returnsTrue() {
        String salary = "4000";
        ContainsDepartmentPredicate predicate = new ContainsDepartmentPredicate();
        predicate.setSalary(new Salary(salary));
        assertTrue(predicate.test(new EmployeeBuilder().withSalary(salary).build()));
    }

    @Test
    public void test_doesNotContainSalary_returnsFalse() {
        // 4000 <= 2000 return false
        String salary = "2000";
        ContainsDepartmentPredicate predicate = new ContainsDepartmentPredicate();
        predicate.setSalary(new Salary(salary));
        assertFalse(predicate.test(new EmployeeBuilder().withSalary("4000").build()));
    }

    @Test
    public void toStringMethod() {
        String keyword = "Finance";
        String salary = "2000";
        ContainsDepartmentPredicate predicate = new ContainsDepartmentPredicate();
        predicate.setDepartment(keyword);
        predicate.setSalary(new Salary(salary));

        String expected = ContainsDepartmentPredicate.class.getCanonicalName() + "{keyword=" + keyword
                + ", salary=" + salary + "}";
        assertEquals(expected, predicate.toString());
    }
}
