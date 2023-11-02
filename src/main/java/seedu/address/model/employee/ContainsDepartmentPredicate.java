package seedu.address.model.employee;

import java.util.Set;
import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.name.DepartmentName;
import seedu.address.model.name.EmployeeName;

/**
 * Tests that an {@code Employee}'s {@code Name} matches the keyword given.
 */
public class ContainsDepartmentPredicate implements Predicate<Employee> {

    private final Set<EmployeeName> nameSet;
    private final Set<Phone> phoneSet;
    private final Set<Email> emailSet;
    private final Set<Address> addressSet;
    private final Set<Salary> salarySet;
    private final Set<Leave> leaveSet;
    private final Set<Role> roleSet;
    private final Set<EmployeeName> supervisorNameSet;
    private final Set<DepartmentName> departmentSet;


    public ContainsDepartmentPredicate(Set<EmployeeName> nameSet, Set<Phone> phoneSet, Set<Email> emailSet,
                                       Set<Address> addressSet, Set<Salary> salarySet, Set<Leave> leaveSet,
                                       Set<Role> roleSet, Set<EmployeeName> supervisorNameSet,
                                       Set<DepartmentName> departmentSet) {
        this.nameSet = nameSet;
        this.phoneSet = phoneSet;
        this.emailSet = emailSet;
        this.addressSet = addressSet;
        this.salarySet = salarySet;
        this.leaveSet = leaveSet;
        this.roleSet = roleSet;
        this.supervisorNameSet = supervisorNameSet;
        this.departmentSet = departmentSet;
    }

    @Override
    public boolean test(Employee employee) {
        return nameSet.contains(employee.getName())
                && phoneSet.contains(employee.getPhone())
                && emailSet.contains(employee.getEmail())
                && addressSet.contains(employee.getAddress())
                && salarySet.contains(employee.getSalary())
                && leaveSet.contains(employee.getLeave())
                && roleSet.contains(employee.getRole())
                && (supervisorNameSet.isEmpty()
                || employee.getSupervisors().stream().anyMatch(supervisorNameSet::contains))
                && (departmentSet.isEmpty()
                || employee.getDepartments().stream().anyMatch(departmentSet::contains));
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

        ContainsDepartmentPredicate otherContainsPredicate = (ContainsDepartmentPredicate) other;
        return nameSet.equals(otherContainsPredicate.nameSet)
                && phoneSet.equals(otherContainsPredicate.phoneSet)
                && emailSet.equals(otherContainsPredicate.emailSet)
                && addressSet.equals(otherContainsPredicate.addressSet)
                && salarySet.equals(otherContainsPredicate.salarySet)
                && leaveSet.equals(otherContainsPredicate.leaveSet)
                && roleSet.equals(otherContainsPredicate.roleSet)
                && supervisorNameSet.equals(otherContainsPredicate.supervisorNameSet)
                && departmentSet.equals(otherContainsPredicate.departmentSet);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("nameSet : ", nameSet)
                .add(", phoneSet: ", phoneSet)
                .add(", emailSet: ", emailSet)
                .add(", addressSet: ", addressSet)
                .add(", salarySet: ", salarySet)
                .add(", leaveSet: ", leaveSet)
                .add(", roleSet: ", roleSet)
                .add(", supervisorNameSet: ", supervisorNameSet)
                .add(", departmentSet: ", departmentSet)
                .toString();
    }
}
