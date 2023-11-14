package seedu.address.model.mapping;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.localcourse.LocalCode;
import seedu.address.model.mapping.exceptions.DuplicateMappingException;
import seedu.address.model.mapping.exceptions.MappingNotFoundException;
import seedu.address.model.partnercourse.PartnerCode;
import seedu.address.model.university.UniversityName;

/**
 * A list of mapping that enforces uniqueness between its elements and does not allow nulls.
 * Note that a Mapping is considered unique by {@code Mapping#isSameMapping(Mapping)},
 * which concerns adding and updating. On the other hand, the removal of a Mapping uses
 * Mapping#equals(Object) to ensure that the mapping with exact matching fields is
 * removed.
 * <p>
 * Implements {@code Iterable<Mapping>} amd thus needs an iterator. Able to utilise in for-each.
 * <p>
 * Note that this is the list containing the records, and the internal list must be an
 * {@code ObservableList<Mapping>} to return in ReadOnlyMappingCatalogue#getMappingList.
 */
public class UniqueMappingList implements Iterable<Mapping> {
    private final ObservableList<Mapping> internalList = FXCollections.observableArrayList();
    private final ObservableList<Mapping> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent Mapping as the given argument.
     */
    public boolean contains(Mapping toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameMapping);
    }

    /**
     * Returns an Optional containing first occurrence of desired Mapping.
     * An empty Optional is returned if Mapping is not found.
     *
     * @param localCode      This is the LocalCode to check.
     * @param universityName This is the UniversityName to check.
     * @param partnerCode    This is the PartnerCode to check.
     * @return Optional containing first occurrence of desired Mapping, if any.
     */
    public Optional<Mapping> getMappingIfExists(LocalCode localCode, UniversityName universityName,
                                                PartnerCode partnerCode) {
        requireAllNonNull(localCode, universityName, partnerCode);
        return internalList.stream().filter(m -> m.getLocalCode().equals(localCode)
                        && m.getUniversityName().equals(universityName)
                        && m.getPartnerCode().equals(partnerCode))
                .findFirst();
    }

    /**
     * Returns true is a mapping with {@code localCode} exists in the MappingCatalogue.
     */
    public boolean hasMappingWithLocalCode(LocalCode localCode) {
        requireNonNull(localCode);
        return internalList.stream().anyMatch(m -> m.getLocalCode().equals(localCode));
    }

    /**
     * Returns true is a mapping with {@code partnerCode} exists in the MappingCatalogue.
     */
    public boolean hasMappingWithPartnerCodeAndUniversityName(PartnerCode partnerCode,
                                                              UniversityName universityName) {
        requireAllNonNull(partnerCode, universityName);
        return internalList.stream().anyMatch(m -> m.getPartnerCode().equals(partnerCode)
                && m.getUniversityName().equals(universityName));
    }

    /**
     * Adds a Mapping to the list.
     * The Mapping must not already exist in the list.
     */
    public void add(Mapping toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateMappingException();
        }
        internalList.add(toAdd);
    }

    /**
     * Removes the equivalent (as per {@code Mapping#equals(Object)}) Mapping from the list.
     * The Mapping must exist in the list.
     */
    public void remove(Mapping toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new MappingNotFoundException();
        }
    }

    public void setMappings(UniqueMappingList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code Mappings}.
     * {@code mappings} must not contain duplicate Mappings.
     */
    public void setMappings(List<Mapping> mappings) {
        requireAllNonNull(mappings);
        if (!mappingsAreUnique(mappings)) {
            throw new DuplicateMappingException();
        }

        internalList.setAll(mappings);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Mapping> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Mapping> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UniqueMappingList)) {
            return false;
        }

        UniqueMappingList otherUniqueMappingList = (UniqueMappingList) other;
        return internalList.equals(otherUniqueMappingList.internalList);
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
     * Returns true if {@code mappings} contains only unique Mappings.
     */
    private boolean mappingsAreUnique(List<Mapping> mappings) {
        // Last element would be second to last
        for (int i = 0; i < mappings.size() - 1; i++) {
            // Starts from i + 1, to last element
            for (int j = i + 1; j < mappings.size(); j++) {
                if (mappings.get(i).isSameMapping(mappings.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
