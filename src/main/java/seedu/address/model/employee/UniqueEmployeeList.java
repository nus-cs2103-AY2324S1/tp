package seedu.address.model.employee;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.employee.exceptions.DuplicateEmployeeException;
import seedu.address.model.employee.exceptions.EmployeeNotFoundException;
import seedu.address.model.employee.exceptions.SubordinatePresentException;
import seedu.address.model.employee.exceptions.SupervisorNotFoundException;
import seedu.address.model.name.EmployeeName;

/**
 * A list of employees that enforces uniqueness between its elements and does not allow nulls.
 * An employee is considered unique by comparing using {@code Employee#isSameEmployee(Employee)}.
 * As such, adding and updating of employees uses Employee#isSameEmployee(Employee) for equality to ensure
 * that the employee being added or updated is unique in terms of identity in the UniqueEmployeeList.
 * However, the removal of an employee uses Employee#equals(Object) to ensure that the employee with
 * exactly the same fields will be removed.
 * Supports a minimal set of list operations.
 *
 * @see Employee#isSameEmployee(Employee)
 */
public class UniqueEmployeeList implements Iterable<Employee> {

    private final ObservableList<Employee> internalList = FXCollections.observableArrayList();
    private final ObservableList<Employee> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent employee as the given argument.
     */
    public boolean contains(Employee toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameEmployee);
    }

    /**
     * Returns true if the list contains an employee with the same identity as the given argument.
     */
    public boolean contains(EmployeeName toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(employee -> employee.hasSameEmployeeName(toCheck));
    }
    /**
     * Returns true if all the managers of the given argument is within the list.
     */
    public boolean containsManager(Employee toCheck) {
        requireNonNull(toCheck);
        return toCheck.getSupervisors().stream().allMatch(x ->
                internalList.stream().anyMatch(y ->
                        y.hasSameEmployeeName(x) && y.isManager()));
    }

    /**
     * Checks if the provided employee has subordinates managed by this employee.
     *
     * @param toCheck The employee for whom subordinates are to be checked.
     * @return {@code true} if the provided 'toCheck' employee is a manager and has subordinates
     *         managed by this employee, {@code false} otherwise. Returns {@code false} if 'toCheck' is not
     *         a manager or if 'toCheck' is null.
     */
    public boolean hasSubordinates(Employee toCheck) {
        requireNonNull(toCheck);
        if (!toCheck.isManager()) {
            return false;
        }
        return internalList.stream().anyMatch(x -> x.getSupervisors().contains(toCheck.getName()));
    }


    /**
     * Adds an employee to the list.
     * The employee must not already exist in the list.
     *
     * @param toAdd The employee to be added to the list.
     * @throws DuplicateEmployeeException If the employee already exists in the list.
     * @throws SupervisorNotFoundException If the employee's manager (supervisor) is not found in the list.
     */
    public void add(Employee toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateEmployeeException();
        }
        if (!containsManager(toAdd)) {
            throw new SupervisorNotFoundException();
        }

        internalList.add(toAdd);
    }

    /**
     * Replaces the employee {@code target} in the list with {@code editedEmployee}.
     * {@code target} must exist in the list.
     * The employee identity of {@code editedEmployee} must not be the same as another existing employee in the list.
     *
     * @param target The original employee to be updated.
     * @param editedEmployee The updated employee.
     * @throws EmployeeNotFoundException If the original employee is not found in the list.
     * @throws DuplicateEmployeeException If an employee with the same information already exists in the list.
     * @throws SupervisorNotFoundException If the updated employee has supervisors not found in the list.
     * @throws SubordinatePresentException If the original employee manages subordinates.
     */
    public void setEmployee(Employee target, Employee editedEmployee) {
        requireAllNonNull(target, editedEmployee);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new EmployeeNotFoundException();
        }

        if (!target.isSameEmployee(editedEmployee) && contains(editedEmployee)) {
            throw new DuplicateEmployeeException();
        }
        if (!target.isSameEmployee(editedEmployee) && hasSubordinates(target)) {
            throw new SubordinatePresentException();
        }
        if (target.isSupervisorOf(editedEmployee)) {
            throw new SupervisorNotFoundException();
        }
        if (!containsManager(editedEmployee)) {
            throw new SupervisorNotFoundException();
        }

        internalList.set(index, editedEmployee);
    }


    /**
     * Removes the equivalent employee from the list.
     * The employee must exist in the list.
     *
     * @param toRemove The employee to be removed from the list.
     * @throws EmployeeNotFoundException If the employee is not found in the list.
     * @throws SubordinatePresentException If the employee manages subordinates, preventing removal.
     */
    public void remove(Employee toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new EmployeeNotFoundException();
        }
        if (hasSubordinates(toRemove)) {
            throw new SubordinatePresentException();
        }
    }

    public void setEmployees(UniqueEmployeeList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code people}.
     * {@code people} must not contain duplicate people.
     */
    public void setEmployees(List<Employee> people) {
        requireAllNonNull(people);
        if (!employeesAreUnique(people)) {
            throw new DuplicateEmployeeException();
        }


        internalList.setAll(people);
    }

    /**
     * Adds an employee to the internal list without applying constraints.
     * This method is intended for use when adding an employee where constraints related to the
     * supervisor-subordinate relationship are not enforced.
     *
     * @param toAdd The employee to be added. Must not be {@code null}.
     */
    public void addWithoutConstraints(Employee toAdd) {
        internalList.add(toAdd);
    }

    /**
     * Enforces constraints on the internal list of employees.
     * This method filters the internal list to include only employees who meet specific constraints.
     * The constraints applied ensures the supervisor-subordinate relationship is enforced.
     *
     * @return A list of employees who does not violate the constraints.
     */
    public List<Employee> enforceConstraints() {
        List<Employee> filteredList = internalList.stream()
                .filter(x -> this.hasSubordinates(x) && this.containsManager(x))
                .collect(Collectors.toList());
        return filteredList;

    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Employee> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Employee> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UniqueEmployeeList)) {
            return false;
        }

        UniqueEmployeeList otherUniqueEmployeeList = (UniqueEmployeeList) other;
        return internalList.equals(otherUniqueEmployeeList.internalList);
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    @Override
    public String toString() {
        return internalList.toString();
    }

    /**
     * Returns true if {@code people} contains only unique people.
     */
    private boolean employeesAreUnique(List<Employee> people) {
        for (int i = 0; i < people.size() - 1; i++) {
            for (int j = i + 1; j < people.size(); j++) {
                if (people.get(i).isSameEmployee(people.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
