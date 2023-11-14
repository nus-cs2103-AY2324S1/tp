package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;
import java.util.Optional;

import javafx.collections.ObservableList;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.localcourse.LocalCode;
import seedu.address.model.mapping.Mapping;
import seedu.address.model.mapping.UniqueMappingList;
import seedu.address.model.partnercourse.PartnerCode;
import seedu.address.model.university.UniversityName;

/**
 * Wraps mapping data at the catalogue level
 * Duplicates are not allowed (by .isSameMapping comparison)
 */
public class MappingCatalogue implements ReadOnlyMappingCatalogue {

    private final UniqueMappingList mappings;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     * <p>
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     * among constructors.
     */

    {
        mappings = new UniqueMappingList();
    }

    public MappingCatalogue() {
    }

    /**
     * Creates a MappingCatalogue using the Mappings in the {@code toBeCopied}
     */
    public MappingCatalogue(ReadOnlyMappingCatalogue toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the Mapping list with {@code mappings}.
     * {@code mappings} must not contain duplicate Mappings.
     */
    public void setMappings(List<Mapping> mappings) {
        this.mappings.setMappings(mappings);
    }

    /**
     * Resets the existing data of this {@code MappingCatalogue} with {@code newData}.
     */
    public void resetData(ReadOnlyMappingCatalogue newData) {
        requireNonNull(newData);
        setMappings(newData.getMappingList());
    }

    //// mapping-level operations

    /**
     * Returns true if a Mapping with the same identity as {@code mappingQuery} exists in the catalogue.
     */
    public boolean hasMapping(Mapping mappingQuery) {
        requireNonNull(mappingQuery);
        return mappings.contains(mappingQuery);
    }

    /**
     * Returns true is a mapping with {@code localCode} exists in the MappingCatalogue.
     */
    public boolean hasMappingWithLocalCode(LocalCode localCode) {
        requireNonNull(localCode);
        return mappings.hasMappingWithLocalCode(localCode);
    }

    /**
     * Returns true is a mapping with {@code partnerCode} and {@code universityName}
     * exists in the MappingCatalogue.
     */
    public boolean hasMappingWithPartnerCodeAndUniversityName(PartnerCode partnerCode,
                                                              UniversityName universityName) {
        requireAllNonNull(partnerCode, universityName);
        return mappings.hasMappingWithPartnerCodeAndUniversityName(partnerCode, universityName);
    }

    /**
     * Gets the mapping if it exists.
     */
    public Optional<Mapping> getMappingIfExists(LocalCode localCodeQuery, UniversityName universityNameQuery,
                                                PartnerCode partnerCodeQuery) {
        requireAllNonNull(localCodeQuery, universityNameQuery, partnerCodeQuery);
        return mappings.getMappingIfExists(localCodeQuery, universityNameQuery, partnerCodeQuery);
    }

    /**
     * Adds a Mapping to the MappingCatalogue.
     * The Mapping must not already exist in the catalogue.
     */
    public void addMapping(Mapping mappingToAdd) {
        mappings.add(mappingToAdd);
    }

    /**
     * Removes {@code toRemove} from this {@code MappingCatalogue}.
     * {@code toRemove} must exist in the mappings catalogue.
     */
    public void removeMapping(Mapping toRemove) {
        mappings.remove(toRemove);
    }

    //// util methods

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("mappings", mappings)
                .toString();
    }

    @Override
    public ObservableList<Mapping> getMappingList() {
        return mappings.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof MappingCatalogue)) {
            return false;
        }

        MappingCatalogue otherMappingCatalogue = (MappingCatalogue) other;
        return mappings.equals(otherMappingCatalogue.mappings);
    }

    @Override
    public int hashCode() {
        return mappings.hashCode();
    }
}
