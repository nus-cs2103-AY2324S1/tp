package seedu.address.model.employee;

import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.name.DepartmentName;

/**
 * Tests that an {@code Employee}'s {@code Name} matches the keyword given.
 */
public class ContainsDepartmentPredicate implements Predicate<Employee> {
    private String keyword = "";
    private Salary salary = new Salary("0");

    public ContainsDepartmentPredicate() {
    }

    @Override
    public boolean test(Employee employee) {
        boolean departmentResult = true;
        boolean salaryResult = true;

        if (keyword != "") {
            departmentResult = employee.getDepartments().contains(new DepartmentName(keyword)) ? true : false;
        }

        if (salary.value != "0") {
            Integer actualValue = Integer.valueOf(employee.getSalary().toString());
            Integer filterValue = Integer.valueOf(salary.toString());
            salaryResult = actualValue <= filterValue ? true : false;
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
        return keyword.equals(otherContainsDepartmentPredicate.keyword)
                && salary.equals(otherContainsDepartmentPredicate.salary);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("keyword", keyword)
                .add("salary", salary)
                .toString();
    }
}
