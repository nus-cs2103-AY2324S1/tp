package connectify.model.company;

import static java.util.Objects.requireNonNull;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import connectify.commons.util.CollectionUtil;
import connectify.model.company.exceptions.CompanyNotFoundException;
import connectify.model.company.exceptions.DuplicateCompanyException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * A list of Companies that enforces uniqueness between its elements and does not allow nulls.
 * A Company is considered unique by comparing using {@code Company#isSameCompany(Company)}.
 * As such, adding and updating of
 * Companies uses Company#isSameCompany(Company) for equality to ensure that the Company being added or updated is
 * unique in terms of identity in the UniqueCompanyList. However, the removal of a Company uses Company#equals(Object)
 * to ensure that the Company with exactly the same fields will be removed.
 * Supports a minimal set of list operations.
 *
 */
public class UniqueCompanyList implements Iterable<Company> {

    private final ObservableList<Company> internalList = FXCollections.observableArrayList();
    private final ObservableList<Company> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent Company as the given argument.
     */
    public boolean contains(Company toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameCompany);
    }

    /**
     * Adds a Company to the list.
     * The Company must not already exist in the list.
     */
    public void add(Company toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateCompanyException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the Company {@code target} in the list with {@code editedCompany}.
     * {@code target} must exist in the list.
     * The Company identity of {@code editedCompany} must not be the same as another existing Company in the list.
     */
    public void setCompany(Company target, Company editedCompany) {
        CollectionUtil.requireAllNonNull(target, editedCompany);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new CompanyNotFoundException();
        }

        if (!target.isSameCompany(editedCompany) && contains(editedCompany)) {
            throw new DuplicateCompanyException();
        }

        internalList.set(index, editedCompany);
    }

    /**
     * Removes the equivalent Company from the list.
     * The Company must exist in the list.
     */
    public void remove(Company toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new CompanyNotFoundException();
        }
    }

    public void setCompanies(UniqueCompanyList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code Companies}.
     * {@code Companies} must not contain duplicate Companies.
     */
    public void setCompanies(List<Company> companies) {
        CollectionUtil.requireAllNonNull(companies);
        if (!companiesAreUnique(companies)) {
            throw new DuplicateCompanyException();
        }

        internalList.setAll(companies);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Company> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Company> iterator() {
        return internalList.iterator();
    }

    public void sort(Comparator<Company> companyComparator) {
        internalList.sort(companyComparator);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UniqueCompanyList)) {
            return false;
        }

        UniqueCompanyList otherUniqueCompanyList = (UniqueCompanyList) other;
        return internalList.equals(otherUniqueCompanyList.internalList);
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
     * Returns true if {@code Companies} contains only unique Companies.
     */
    private boolean companiesAreUnique(List<Company> companies) {
        for (int i = 0; i < companies.size() - 1; i++) {
            for (int j = i + 1; j < companies.size(); j++) {
                if (companies.get(i).isSameCompany(companies.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
