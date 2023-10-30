package seedu.address.model.employee;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.employee.exceptions.DuplicateEmployeeException;
import seedu.address.model.employee.exceptions.EmployeeNotFoundException;
import seedu.address.model.employee.exceptions.ManagerNotFoundException;
import seedu.address.model.employee.exceptions.SubordinatePresentException;

/**
 * A list of employees that enforces uniqueness between its elements and does not allow nulls.
 * An employee is considered unique by comparing using {@code Employee#isSameEmployee(Employee)}.
 * As such, adding and updating of employees uses Employee#isSameEmployee(Employee) for equality to ensure
 * that the employee being added or updated is unique in terms of identity in the UniqueEmployeeList.
 * However, the removal of an employee uses Employee#equals(Object) to ensure that the employee with
 * exactly the same fields will be removed.
 *
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
     * Returns true if all the managers of the given argument is within the list.
     */
    public boolean containsManager(Employee toCheck) {
        requireNonNull(toCheck);
        return toCheck.getManagersInCharge().stream().allMatch(x ->
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
        return internalList.stream().anyMatch(x -> x.getManagersInCharge().contains(toCheck.getName()));
    }

    /**
     * Adds an employee to the list.
     * The employee must not already exist in the list.
     * The managers of the employee are not
     */
    public void add(Employee toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateEmployeeException();
        }
        if (!containsManager(toAdd)) {
            throw new ManagerNotFoundException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the employee {@code target} in the list with {@code editedEmployee}.
     * {@code target} must exist in the list.
     * The employee identity of {@code editedEmployee} must not be the same as another existing employee in the list.
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
        if (!containsManager(editedEmployee)) {
            throw new ManagerNotFoundException();
        }

        internalList.set(index, editedEmployee);
    }

    /**
     * Removes the equivalent employee from the list.
     * The employee must exist in the list.
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
