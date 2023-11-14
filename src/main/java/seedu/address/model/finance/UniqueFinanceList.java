package seedu.address.model.finance;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.finance.exceptions.CommissionNotFoundException;
import seedu.address.model.finance.exceptions.DuplicateCommissionException;
import seedu.address.model.finance.exceptions.DuplicateExpenseException;
import seedu.address.model.finance.exceptions.ExpenseNotFoundException;
import seedu.address.model.person.exceptions.PersonNotFoundException;

/**
 * A list of Finances that does not allow nulls.
 * Supports a minimal set of list operations.
 */
public class UniqueFinanceList implements Iterable<Finance> {
    private final ObservableList<Finance> internalList = FXCollections.observableArrayList();
    private final ObservableList<Finance> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Adds a person to the list.
     * The person must not already exist in the list.
     */
    public void add(Finance toAdd) {
        requireNonNull(toAdd);
        internalList.add(toAdd);
    }
    /**
     * Returns true if the list contains an equivalent Finance as the given argument.
     */
    public boolean contains(Finance toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::equals);
    }

    public void setFinances(UniqueFinanceList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code finances}.
     * {@code finances} must not contain duplicate finances.
     */
    public void setFinances(List<Finance> finances) {
        requireNonNull(finances);
        internalList.setAll(finances);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Finance> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }


    @Override
    public Iterator<Finance> iterator() {
        return internalList.iterator();
    }
    @Override
    public String toString() {
        return internalList.toString();
    }

    /**
     * Removes the equivalent finance entry from the list.
     * The entry must exist in the list.
     */
    public void remove(Finance toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new PersonNotFoundException();
        }
    }

    /**
     * Replaces the expense {@code target} in the list with {@code editedExpense}.
     * {@code target} must exist in the list.
     * The expense identity of {@code editedExpense} must not be the same as another existing expense in the list.
     */
    public void setExpense(Expense target, Expense editedExpense) {
        requireAllNonNull(target, editedExpense);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new ExpenseNotFoundException();
        }

        if (!target.isSameExpense(editedExpense) && contains(editedExpense)) {
            throw new DuplicateExpenseException();
        }

        internalList.set(index, editedExpense);
    }

    /**
     * Replaces the commission {@code target} in the list with {@code editedCommission}.
     * {@code target} must exist in the list.
     * The commission identity of {@code editedCommission} must not be the same as
     * another existing commission in the list.
     */
    public void setCommission(Commission target, Commission editedCommission) {
        requireAllNonNull(target, editedCommission);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new CommissionNotFoundException();
        }

        if (!target.isSameCommission(editedCommission) && contains(editedCommission)) {
            throw new DuplicateCommissionException();
        }

        internalList.set(index, editedCommission);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UniqueFinanceList)) {
            return false;
        }

        UniqueFinanceList uniqueFinanceList = (UniqueFinanceList) other;
        return internalList.equals(uniqueFinanceList.internalList);
    }
}
