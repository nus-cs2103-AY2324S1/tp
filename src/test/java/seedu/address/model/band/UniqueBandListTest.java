package seedu.address.model.band;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalBands.ACE;
import static seedu.address.testutil.TypicalBands.BOOM;
import static seedu.address.testutil.TypicalBands.CANDY;
import static seedu.address.testutil.TypicalBands.ELISE;
import static seedu.address.testutil.TypicalMusicians.ALICE;
import static seedu.address.testutil.TypicalMusicians.ELLE;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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
    public void remove_nullBand_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueBandList.remove(null));
    }

    @Test
    public void remove_bandDoesNotExist_throwsBandNotFoundException() {
        assertThrows(BandNotFoundException.class, () -> uniqueBandList.remove(ACE));
    }

    @Test
    public void remove_existingBand_withoutMusicians_removesBand() {
        uniqueBandList.add(ACE);
        uniqueBandList.remove(ACE);
        UniqueBandList expectedUniqueBandList = new UniqueBandList();
        assertEquals(expectedUniqueBandList, uniqueBandList);
    }

    @Test
    public void remove_existingBand_withMusicians_removesBand() {
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
        assertThrows(DuplicateBandException.class, () -> uniqueBandList.setBands(
            listWithDuplicateBands));
    }

    @Test
    public void hasMusician_nullMusician_throwsNullPointerException() {
        uniqueBandList.add(ACE);
        assertThrows(NullPointerException.class, () -> uniqueBandList.hasMusician
            (0, null));
    }

    @Test
    public void hasMusician_bandWithoutMusicians_returnsFalse() {
        uniqueBandList.add(ACE);
        assertFalse(uniqueBandList.hasMusician(0, ALICE));
    }

    @Test
    public void hasMusician_bandWithMusicians_correctMusician_returnsTrue() {
        uniqueBandList.add(ELISE);
        assertTrue(uniqueBandList.hasMusician(0, ELLE));
    }

    @Test
    public void hasMusician_bandWithMusicians_wrongMusician_returnsFalse() {
        uniqueBandList.add(ELISE);
        assertFalse(uniqueBandList.hasMusician(0, ALICE));
    }

    @Test
    public void addMusician_nullMusician_throwsNullPointerException() {
        uniqueBandList.add(ACE);
        assertThrows(NullPointerException.class, () -> uniqueBandList.addMusician
            (0, null));
    }

    @Test
    public void addMusician_duplicateMusician_throwsDuplicateMusicianException() {
        uniqueBandList.add(ELISE);
        assertThrows(DuplicateMusicianException.class, () -> uniqueBandList.addMusician
            (0, ELLE));
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
}
