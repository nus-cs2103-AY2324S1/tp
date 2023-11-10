//@@author kenvynKwek
package seedu.address.model.employee;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;

import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.commons.util.CustomSet;
import seedu.address.testutil.EmployeeBuilder;


public class ContainsAllPredicateTest {
    @Test
    public void test_containsAddress_returnsTrue() {
        // One keyword
        String keyword = VALID_ADDRESS_BOB;
        Set<Address> addressSet = new CustomSet<>();
        addressSet.add(new Address(keyword));
        ContainsAllPredicate predicate = new ContainsAllPredicate(new CustomSet<>(), new CustomSet<>(),
                new CustomSet<>(), addressSet, new CustomSet<>(), new CustomSet<>(), new CustomSet<>(),
                new CustomSet<>(), new CustomSet<>());
        System.out.println(predicate);
        System.out.println(new EmployeeBuilder().withAddress(keyword).build());
        assertTrue(predicate.test(new EmployeeBuilder().withAddress(keyword).build()));
    }


    @Test
    public void test_emptyPredicate_returnsTrue() {
        // Zero keywords
        ContainsAllPredicate predicate = new ContainsAllPredicate(new CustomSet<>(), new CustomSet<>(),
                new CustomSet<>(), new CustomSet<>(), new CustomSet<>(), new CustomSet<>(), new CustomSet<>(),
                new CustomSet<>(), new CustomSet<>());
        assertTrue(predicate.test(new EmployeeBuilder().withDepartments("R&D").build()));
    }

    @Test
    public void test_differentAttributeValueBetweenPredicateAndEmployee_returnsFalse() {
        // Non-matching keyword
        Set<Address> addressSet = new CustomSet<>();
        addressSet.add(new Address(VALID_ADDRESS_AMY));
        ContainsAllPredicate predicate = new ContainsAllPredicate(new CustomSet<>(), new CustomSet<>(),
                new CustomSet<>(), addressSet, new CustomSet<>(), new CustomSet<>(), new CustomSet<>(),
                new CustomSet<>(), new CustomSet<>());
        assertFalse(predicate.test(new EmployeeBuilder().withAddress(VALID_ADDRESS_BOB).build()));
    }

    @Test
    public void test_containsSalary_returnsTrue() {
        String salary = "4000";
        Set<Salary> salarySet = new CustomSet<>();
        salarySet.add(new Salary(salary));
        ContainsAllPredicate predicate = new ContainsAllPredicate(new CustomSet<>(), new CustomSet<>(),
                new CustomSet<>(), new CustomSet<>(), salarySet, new CustomSet<>(), new CustomSet<>(),
                new CustomSet<>(), new CustomSet<>());
        assertTrue(predicate.test(new EmployeeBuilder().withSalary(salary).build()));
    }

    @Test
    public void test_doesNotContainSalary_returnsFalse() {
        // 4000 <= 2000 return false
        String salary = "2000";
        Set<Salary> salarySet = new CustomSet<>();
        salarySet.add(new Salary(salary));
        ContainsAllPredicate predicate = new ContainsAllPredicate(new CustomSet<>(), new CustomSet<>(),
                new CustomSet<>(), new CustomSet<>(), salarySet, new CustomSet<>(), new CustomSet<>(),
                new CustomSet<>(), new CustomSet<>());
        assertFalse(predicate.test(new EmployeeBuilder().withSalary("4000").build()));
    }

}
//@@author
