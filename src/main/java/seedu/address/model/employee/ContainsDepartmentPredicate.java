package seedu.address.model.employee;

import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.name.DepartmentName;

/**
 * Tests that an {@code Employee}'s {@code Name} matches the keyword given.
 */
public class ContainsDepartmentPredicate implements Predicate<Employee> {
    private final String keyword;

    public ContainsDepartmentPredicate(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public boolean test(Employee employee) {
        return employee.getDepartments().contains(new DepartmentName(keyword));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ContainsDepartmentPredicate)) {
            return false;
        }

        ContainsDepartmentPredicate otherContainsDepartmentPredicate = (ContainsDepartmentPredicate) other;
        return keyword.equals(otherContainsDepartmentPredicate.keyword);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keyword", keyword).toString();
    }
}
