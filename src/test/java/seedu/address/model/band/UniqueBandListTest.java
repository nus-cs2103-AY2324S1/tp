package seedu.address.model.band;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.typicalentities.TypicalBands.ACE;
import static seedu.address.testutil.typicalentities.TypicalBands.BOOM;
import static seedu.address.testutil.typicalentities.TypicalBands.CANDY;
import static seedu.address.testutil.typicalentities.TypicalBands.DRAGON;
import static seedu.address.testutil.typicalentities.TypicalBands.ELISE;
import static seedu.address.testutil.typicalentities.TypicalMusicians.ALICE;
import static seedu.address.testutil.typicalentities.TypicalMusicians.BOB;
import static seedu.address.testutil.typicalentities.TypicalMusicians.ELLE;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

import seedu.address.model.band.exceptions.BandNotFoundException;
import seedu.address.model.band.exceptions.DuplicateBandException;
import seedu.address.model.musician.exceptions.DuplicateMusicianException;
import seedu.address.testutil.BandBuilder;

public class UniqueBandListTest {

    private final UniqueBandList uniqueBandList = new UniqueBandList();


    @Test
    public void contains_nullBand_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueBandList.contains(null));
    }

    @Test
    public void contains_bandNotInList_returnsFalse() {
        assertFalse(uniqueBandList.contains(ACE));
    }

    @Test
    public void contains_bandInList_returnsTrue() {
        uniqueBandList.add(ACE);
        assertTrue(uniqueBandList.contains(ACE));
    }

    @Test
    public void add_nullBand_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueBandList.add(null));
    }

    @Test
    public void add_duplicateBand_throwsDuplicateBandException() {
        uniqueBandList.add(ACE);
        assertThrows(DuplicateBandException.class, () -> uniqueBandList.add(ACE));
    }

    @Test
    public void add_duplicateBandWithNewMusician_throwsDuplicateBandException() {
        uniqueBandList.add(ACE);
        Band newAce = new BandBuilder(ACE).withMusicians(ALICE)
            .build();
        assertThrows(DuplicateBandException.class, () -> uniqueBandList.add(newAce));
    }

    @Test
    public void setBand_setIndividualBand_replaceTargetBand() {
        uniqueBandList.add(ACE);
        uniqueBandList.setBand(ACE, BOOM);
        UniqueBandList copyList = new UniqueBandList();
        copyList.add(BOOM);
        assertEquals(uniqueBandList, copyList);
    }

    @Test
    public void setBand_nullTargetBand_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueBandList.setBand(null, ACE));
    }

    @Test
    public void setBand_nullEditedBand_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueBandList.setBand(ACE, null));
    }

    @Test
    public void setBand_targetBandNotInList_throwsBandNotFoundException() {
        assertThrows(BandNotFoundException.class, () -> uniqueBandList.setBand(ACE, ACE));
    }

    @Test
    public void setBand_indexOutOfBounds_throwsDuplicateBandException() {
        uniqueBandList.add(ACE);
        uniqueBandList.add(BOOM);
        assertThrows(DuplicateBandException.class, () -> uniqueBandList.setBand(BOOM, ACE));
    }
    @Test
    public void setBand_setWholeList_replaceMusicianList() {
        UniqueBandList copyList = new UniqueBandList();
        copyList.add(ACE);
        uniqueBandList.setBand(copyList);
        assertEquals(uniqueBandList, copyList);
    }
    @Test
    public void setBand_setNullList_throwNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueBandList.setBand((UniqueBandList) null));
    }
    @Test
    public void remove_nullBand_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueBandList.remove(null));
    }

    @Test
    public void remove_bandDoesNotExist_throwsBandNotFoundException() {
        assertThrows(BandNotFoundException.class, () -> uniqueBandList.remove(ACE));
    }

    @Test
    public void remove_existingBandWithoutMusicians_removesBand() {
        uniqueBandList.add(ACE);
        uniqueBandList.remove(ACE);
        UniqueBandList expectedUniqueBandList = new UniqueBandList();
        assertEquals(expectedUniqueBandList, uniqueBandList);
    }

    @Test
    public void remove_existingBandWithMusicians_removesBand() {
        uniqueBandList.add(CANDY);
        uniqueBandList.remove(CANDY);
        UniqueBandList expectedUniqueBandList = new UniqueBandList();
        assertEquals(expectedUniqueBandList, uniqueBandList);
    }

    @Test
    public void setBands_uniqueBandList_replacesOwnListWithProvidedUniqueBandList() {
        uniqueBandList.add(ACE);
        UniqueBandList expectedUniqueBandList = new UniqueBandList();
        expectedUniqueBandList.add(BOOM);
        uniqueBandList.setBands(expectedUniqueBandList);
        assertEquals(expectedUniqueBandList, uniqueBandList);
    }

    @Test
    public void setBands_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueBandList.setBands((List<Band>) null));
    }

    @Test
    public void setBands_list_replacesOwnListWithProvidedList() {
        uniqueBandList.add(ACE);
        List<Band> bandList = Collections.singletonList(BOOM);
        uniqueBandList.setBands(bandList);
        UniqueBandList expectedUniqueBandList = new UniqueBandList();
        expectedUniqueBandList.add(BOOM);
        assertEquals(expectedUniqueBandList, uniqueBandList);
    }

    @Test
    public void setBands_listWithDuplicateBands_throwsDuplicateBandException() {
        List<Band> listWithDuplicateBands = Arrays.asList(ACE, ACE);
        assertThrows(DuplicateBandException.class, () -> uniqueBandList.setBands(listWithDuplicateBands));
    }

    @Test
    public void hasMusician_nullMusician_throwsNullPointerException() {
        uniqueBandList.add(ACE);
        assertThrows(NullPointerException.class, () -> uniqueBandList.hasMusician(ACE, null));
    }

    @Test
    public void hasMusician_bandWithoutMusicians_returnsFalse() {
        uniqueBandList.add(ACE);
        assertFalse(uniqueBandList.hasMusician(ACE, BOB));
    }

    @Test
    public void hasMusician_musicianInBand_returnsTrue() {
        uniqueBandList.add(ELISE);
        assertTrue(uniqueBandList.hasMusician(ELISE, ELLE));
    }

    @Test
    public void hasMusician_musicianNotInBand_returnsFalse() {
        uniqueBandList.add(ELISE);
        assertFalse(uniqueBandList.hasMusician(ELISE, BOB));
    }
    @Test
    public void addMusician_musicianAdded_bandGetsUpdated() {
        uniqueBandList.add(ACE);
        uniqueBandList.addMusician(ACE, ELLE);
        assertTrue(uniqueBandList.hasMusician(ACE, ELLE));
    }

    @Test
    public void addMusician_nullMusician_throwsNullPointerException() {
        uniqueBandList.add(ACE);
        assertThrows(NullPointerException.class, () -> uniqueBandList.addMusician(
            ACE, null));
    }

    @Test
    public void addMusician_duplicateMusician_throwsDuplicateMusicianException() {
        uniqueBandList.add(ELISE);
        assertThrows(DuplicateMusicianException.class, () -> uniqueBandList.addMusician(ELISE, ELLE));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueBandList.asUnmodifiableObservableList().remove(0));
    }

    @Test
    public void toStringMethod() {
        assertEquals(uniqueBandList.asUnmodifiableObservableList().toString(), uniqueBandList.toString());
    }
    @Test
    public void hashcode() {
        uniqueBandList.add(ACE);
        UniqueBandList copyList = new UniqueBandList();
        copyList.add(ACE);
        // not same object but same hashcode
        assertNotSame(uniqueBandList, copyList);
        assertEquals(uniqueBandList.hashCode(), copyList.hashCode());
    }

    @Test
    public void iterator() {
        // empty iterator hasNext -> returns false
        Iterator<Band> emptyIterated = uniqueBandList.iterator();
        assertFalse(emptyIterated.hasNext());
        // empty iterator next -> throws exception
        assertThrows(NoSuchElementException.class, emptyIterated::next);
        // iterator with a musician hasNext -> returns true
        uniqueBandList.add(ACE);
        Iterator<Band> iterated = uniqueBandList.iterator();
        assertTrue(iterated.hasNext());
        // iterator with musician next -> returns the musician
        assertEquals(iterated.next(), ACE);
        // iterator with a musician hasNext after next -> returns false
        assertFalse(iterated.hasNext());
    }

    @Test
    public void equals() {
        uniqueBandList.add(ACE);
        uniqueBandList.add(BOOM);
        // same values -> returns true
        UniqueBandList copyList = new UniqueBandList();
        copyList.add(ACE);
        copyList.add(BOOM);
        assertEquals(uniqueBandList, copyList);

        // same object -> returns true
        assertEquals(uniqueBandList, uniqueBandList);

        // null -> returns false
        assertNotEquals(null, uniqueBandList);

        // different type -> returns false
        assertNotEquals(uniqueBandList, ALICE);

        // empty list -> returns false
        UniqueBandList emptyList = new UniqueBandList();
        assertNotEquals(uniqueBandList, emptyList);

        // different musicians -> returns false
        UniqueBandList differentList = new UniqueBandList();
        differentList.add(CANDY);
        differentList.add(DRAGON);
        assertNotEquals(uniqueBandList, differentList);

        // empty list and empty list -> returns true
        UniqueBandList emptyListTwo = new UniqueBandList();
        assertEquals(emptyList, emptyListTwo);
    }
}
