package seedu.address.model.employee;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.commons.util.ToStringBuilder;

/**
 * Tests that an {@code Employees} fields matches any of the keywords given.
 */
public class EmployeeContainsKeywordsPredicate implements Predicate<Employee> {
    private final List<String> keywords;

    public EmployeeContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Employee employee) {
        System.out.println(StringUtil.containsWordIgnoreCase(employee.getDepartments().toString(), keywords.get(0)));
        return keywords.stream()
                .anyMatch(keyword -> 
                StringUtil.containsWordIgnoreCase(employee.getName().fullName, keyword) ||
                StringUtil.containsWordIgnoreCase(employee.getDepartments().toString(), keyword) ||
                StringUtil.containsWordIgnoreCase(employee.getPosition().value, keyword) ||
                StringUtil.containsWordIgnoreCase(employee.getPhone().value, keyword) ||
                StringUtil.containsWordIgnoreCase(employee.getEmail().value, keyword) ||
                StringUtil.containsWordIgnoreCase(employee.getId().value, keyword)
                );
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EmployeeContainsKeywordsPredicate)) {
            return false;
        }

        EmployeeContainsKeywordsPredicate otherNameContainsKeywordsPredicate = (EmployeeContainsKeywordsPredicate) other;
        return keywords.equals(otherNameContainsKeywordsPredicate.keywords);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keywords", keywords).toString();
    }
}
