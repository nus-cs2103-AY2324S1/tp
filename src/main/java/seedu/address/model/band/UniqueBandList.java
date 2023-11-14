package seedu.address.model.band;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.commons.core.index.Index;
import seedu.address.model.band.exceptions.BandNotFoundException;
import seedu.address.model.band.exceptions.DuplicateBandException;
import seedu.address.model.musician.Musician;
import seedu.address.model.musician.exceptions.DuplicateMusicianException;
import seedu.address.model.musician.exceptions.MusicianNotFoundException;

/**
 * A list of bands that enforces uniqueness between its elements and does not allow nulls.
 * A band is considered unique by comparing using {@code Band#isSameBand(Band)}.
 * As such, adding and updating of persons uses Band#isSameBand(Band) for equality
 * so as to ensure that the band being added or updated is unique in terms of identity in the UniqueBandList.
 * However, the removal of a band uses Band#equals(Object) so as to ensure that
 * the band with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Band#isSameBand(Band)
 */
public class UniqueBandList implements Iterable<Band> {
    private final ObservableList<Band> internalList = FXCollections.observableArrayList();
    private final ObservableList<Band> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent band as the given argument.
     */
    public boolean contains(Band toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameBand);
    }

    /**
     * Adds a band to the list.
     * The band must not already exist in the list.
     */
    public void add(Band toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateBandException();
        }
        internalList.add(toAdd);
    }

    /**
     * Returns the band at the index
     */
    public Band get(Index index) {
        requireNonNull(index);
        return internalList.get(index.getZeroBased());
    }

    /**
     * Replaces musician {@code target} in every band she is in with {@code editedMusician}.
     */
    public void updateMusicianInAllBands(Musician target, Musician editedMusician) {
        requireNonNull(target);

        for (int i = 0; i < internalList.size(); i++) {
            if (internalList.get(i).hasMusician(target)) {
                internalList.get(i).setMusician(target, editedMusician);
            }
        }
    }

    /**
     * Replaces the band {@code target} in the list with {@code editedBand}.
     * {@code target} must exist in the list.
     * The band identity of {@code editedBand} must not be the same as another existing band in the list.
     */
    public void setBand(Band target, Band editedBand) {
        requireAllNonNull(target, editedBand);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new BandNotFoundException();
        }

        if (!target.isSameBand(editedBand) && contains(editedBand)) {
            throw new DuplicateBandException();
        }

        internalList.set(index, editedBand);
    }

    public void setBand(UniqueBandList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code band}.
     * {@code band} must not contain duplicate bands.
     */
    public void setBand(List<Band> band) {
        requireAllNonNull(band);
        if (!bandsAreUnique(band)) {
            throw new DuplicateBandException();
        }

        internalList.setAll(band);
    }

    /**
     * Replaces the contents of this list with all bands in {@code replacement}.
     * {@code replacement} must not contain duplicate bands.
     */
    public void setBands(UniqueBandList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code bands}.
     * {@code bands} must not contain duplicate musicians.
     */
    public void setBands(List<Band> bands) {
        requireAllNonNull(bands);
        if (!bandsAreUnique(bands)) {
            throw new DuplicateBandException();
        }

        internalList.setAll(bands);
    }

    /**
     * Removes the equivalent band from the list.
     * The band must exist in the list.
     */
    public void remove(Band toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new BandNotFoundException();
        }
    }
    /**
     * Returns true if musician already exists in the band.
     */
    public boolean hasMusician(Band band, Musician musician) {
        requireNonNull(band);
        requireNonNull(musician);
        return band.hasMusician(musician);
    }
    /**
     * Adds a musician to a band.
     */
    public void addMusician(Band band, Musician musician) {
        requireNonNull(band);
        requireNonNull(musician);
        if (band.getModifiableMusicianList().contains((musician))) {
            throw new DuplicateMusicianException();
        }
        band.getModifiableMusicianList().add(musician);
    }

    /**
     * Removes a musician from a band.
     */
    public void removeMusician(Band band, Musician musician) {
        requireNonNull(band);
        requireNonNull(musician);
        if (!band.getModifiableMusicianList().contains(musician)) {
            throw new MusicianNotFoundException();
        }
        band.getModifiableMusicianList().remove(musician);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Band> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Band> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UniqueBandList)) {
            return false;
        }

        UniqueBandList otherUniqueBandList = (UniqueBandList) other;
        return internalList.equals(otherUniqueBandList.internalList);
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
     * Returns true if {@code bands} contains only unique bands.
     */
    private boolean bandsAreUnique(List<Band> bands) {
        for (int i = 0; i < bands.size() - 1; i++) {
            for (int j = i + 1; j < bands.size(); j++) {
                if (bands.get(i).isSameBand(bands.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
