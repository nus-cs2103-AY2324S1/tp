package seedu.address.model.employee;

import java.util.Set;
import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.name.DepartmentName;
import seedu.address.model.name.EmployeeName;

/**
 * A predicate used for filtering Employee objects based on a set of criteria.
 * An Employee object must meet one of each attributes in order to pass this predicate.
 */
public class ContainsAllPredicate implements Predicate<Employee> {

    private final Set<EmployeeName> nameSet;
    private final Set<Phone> phoneSet;
    private final Set<Email> emailSet;
    private final Set<Address> addressSet;
    private final Set<Salary> salarySet;
    private final Set<Leave> leaveSet;
    private final Set<Role> roleSet;
    private final Set<EmployeeName> supervisorNameSet;
    private final Set<DepartmentName> departmentSet;


    /**
     * Constructs a ContainsAllPredicate with sets of criteria for filtering Employee objects.
     *
     * @param nameSet           A set of criteria for filtering Employee objects by name.
     * @param phoneSet          A set of criteria for filtering Employee objects by phone.
     * @param emailSet          A set of criteria for filtering Employee objects by email.
     * @param addressSet        A set of criteria for filtering Employee objects by address.
     * @param salarySet         A set of criteria for filtering Employee objects by salary.
     * @param leaveSet          A set of criteria for filtering Employee objects by leave.
     * @param roleSet           A set of criteria for filtering Employee objects by role.
     * @param supervisorNameSet A set of criteria for filtering Employee objects by supervisor's name.
     * @param departmentSet     A set of criteria for filtering Employee objects by department.
     */
    public ContainsAllPredicate(Set<EmployeeName> nameSet, Set<Phone> phoneSet, Set<Email> emailSet,
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
                && (isMoreThanEqual(salarySet, employee) || salarySet.isEmpty())
                && leaveSet.contains(employee.getLeave())
                && roleSet.contains(employee.getRole())
                && (supervisorNameSet.isEmpty()
                || employee.getSupervisors().stream().anyMatch(supervisorNameSet::contains))
                && (departmentSet.isEmpty()
                || employee.getDepartments().stream().anyMatch(departmentSet::contains));
    }

    private boolean isMoreThanEqual(Set<Salary> salarySet, Employee employee) {
        return salarySet.stream().anyMatch(x -> x.isMoreThanEqual(employee.getSalary()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ContainsAllPredicate)) {
            return false;
        }

        ContainsAllPredicate otherContainsPredicate = (ContainsAllPredicate) other;
        return nameSet.equals(otherContainsPredicate.nameSet)
                && phoneSet.equals(otherContainsPredicate.phoneSet)
                && emailSet.equals(otherContainsPredicate.emailSet)
                && addressSet.equals(otherContainsPredicate.addressSet)
                //@@author kenvynKwek
                && salarySet.equals(otherContainsPredicate.salarySet)
                //@@author
                && leaveSet.equals(otherContainsPredicate.leaveSet)
                && roleSet.equals(otherContainsPredicate.roleSet)
                && supervisorNameSet.equals(otherContainsPredicate.supervisorNameSet)
                //@@author kenvynKwek
                && departmentSet.equals(otherContainsPredicate.departmentSet);
                //@@author
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("nameSet : ", nameSet)
                .add(", phoneSet: ", phoneSet)
                .add(", emailSet: ", emailSet)
                .add(", addressSet: ", addressSet)
                //@@author kenvynKwek
                .add(", salarySet: ", salarySet)
                //@@author
                .add(", leaveSet: ", leaveSet)
                .add(", roleSet: ", roleSet)
                .add(", supervisorNameSet: ", supervisorNameSet)
                //@@author kenvynKwek
                .add(", departmentSet: ", departmentSet)
                .toString();
                //@@author
    }
}
