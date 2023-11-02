package seedu.address.model.department;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.department.exceptions.DepartmentNotFoundException;
import seedu.address.model.department.exceptions.DuplicateDepartmentException;
import seedu.address.model.name.DepartmentName;

/**
 * A list of departments that enforces uniqueness between its elements and does not allow nulls.
 * An department is considered unique by comparing using {@code Department#isSameDepartment(Department)}.
 * As such, adding and updating of departments uses Department#isSameDepartment(Department) for equality to ensure
 * that the department being added or updated is unique in terms of identity in the UniqueDepartmentList.
 * However, the removal of a department uses Department#equals(Object) to ensure that the department with
 * exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Department#isSameDepartment(Department)
 */
public class UniqueDepartmentList implements Iterable<Department> {

    private final ObservableList<Department> internalList = FXCollections.observableArrayList();
    private final ObservableList<Department> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent department as the given argument.
     */
    public boolean contains(Department toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameDepartment);
    }

    /**
     * Returns true if the list contains an equivalent department as the given argument.
     */
    public boolean contains(DepartmentName toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(department -> department.isSameDepartmentName(toCheck));
    }

    /**
     * Adds a department to the list.
     * The department must not already exist in the list.
     */
    public void add(Department toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateDepartmentException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the department {@code target} in the list with {@code editeddepartment}.
     * {@code target} must exist in the list.
     * The department identity of {@code editedDepartment} must not be the same as another
     * existing department in the list.
     */
    public void setDepartment(Department target, Department editedDepartment) {
        requireAllNonNull(target, editedDepartment);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new DepartmentNotFoundException();
        }

        if (!target.isSameDepartment(editedDepartment) && contains(editedDepartment)) {
            throw new DuplicateDepartmentException();
        }

        internalList.set(index, editedDepartment);
    }

    /**
     * Removes the equivalent department from the list.
     * The department must exist in the list.
     */
    public void remove(Department toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new DepartmentNotFoundException();
        }
    }

    public void setDepartments(UniqueDepartmentList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code department}.
     * {@code department} must not contain duplicate department.
     */
    public void setDepartments(List<Department> departments) {
        requireAllNonNull(departments);
        if (!departmentsAreUnique(departments)) {
            throw new DuplicateDepartmentException();
        }

        internalList.setAll(departments);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Department> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Department> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UniqueDepartmentList)) {
            return false;
        }

        UniqueDepartmentList otherUniquedepartmentList = (UniqueDepartmentList) other;
        return internalList.equals(otherUniquedepartmentList.internalList);
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
     * Returns true if {@code department} contains only unique department.
     */
    private boolean departmentsAreUnique(List<Department> department) {
        for (int i = 0; i < department.size() - 1; i++) {
            for (int j = i + 1; j < department.size(); j++) {
                if (department.get(i).isSameDepartment(department.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
