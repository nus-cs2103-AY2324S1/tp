package seedu.address.model.employee;

import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.name.DepartmentName;

/**
 * Tests that an {@code Employee}'s {@code Name} matches the keyword given.
 */
public class ContainsDepartmentPredicate implements Predicate<Employee> {
    private String keyword;
    private Salary salary;

    public ContainsDepartmentPredicate() {
    }

    @Override
    public boolean test(Employee employee) {
        boolean departmentResult = true;
        boolean salaryResult = true;

        if (keyword != null) {
            departmentResult = employee.getDepartments().contains(new DepartmentName(keyword)) ? true : false;
        }

        if (salary != null) {
            Integer actual = Integer.valueOf(employee.getSalary().toString());
            Integer test = Integer.valueOf(salary.toString());
            salaryResult = actual <= test ? true : false;
        }

        return departmentResult && salaryResult;
    }

    public void setDepartment(String keyword) {
        this.keyword = keyword;
    }

    public void setSalary(Salary salary) {
        this.salary = salary;
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
