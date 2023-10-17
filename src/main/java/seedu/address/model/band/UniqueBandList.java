package seedu.address.model.band;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.band.exceptions.*;
import seedu.address.model.musician.UniqueMusicianList;
import seedu.address.model.musician.exceptions.DuplicateMusicianException;


public class UniqueBandList implements Iterable<Band>{
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
     * The musician must not already exist in the list.
     */
    public void add(Band toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateMusicianException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the band {@code target} in the list with {@code editedMusician}.
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

    /**
     * Removes the equivalent musician from the list.
     * The musician must exist in the list.
     */
    public void remove(Band toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new BandNotFoundException();
        }
    }

    public void setBand(UniqueBandList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code musicians}.
     * {@code musicians} must not contain duplicate musicians.
     */
    public void setBand(List<Band> band) {
        requireAllNonNull(band);
        if (!bandsAreUnique(band)) {
            throw new DuplicateBandException();
        }

        internalList.setAll(band);
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
     * Returns true if {@code musicians} contains only unique musicians.
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
