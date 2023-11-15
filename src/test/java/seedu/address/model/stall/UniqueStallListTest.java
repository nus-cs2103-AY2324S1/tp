package seedu.address.model.stall;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LOCATION_BRITISH;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalStalls.AUNTIES_COOKING;
import static seedu.address.testutil.TypicalStalls.BRITISH;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.stall.exceptions.DuplicateStallException;
import seedu.address.model.stall.exceptions.StallNotFoundException;
import seedu.address.testutil.StallBuilder;

public class UniqueStallListTest {

    private final UniqueStallList uniqueStallList = new UniqueStallList();

    @Test
    public void contains_nullStall_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueStallList.contains(null));
    }

    @Test
    public void contains_stallNotInList_returnsFalse() {
        assertFalse(uniqueStallList.contains(AUNTIES_COOKING));
    }

    @Test
    public void contains_stallInList_returnsTrue() {
        uniqueStallList.add(AUNTIES_COOKING);
        assertTrue(uniqueStallList.contains(AUNTIES_COOKING));
    }

    @Test
    public void contains_stallWithSameIdentityFieldsInList_returnsFalse() {
        uniqueStallList.add(AUNTIES_COOKING);
        Stall editedAlice = new StallBuilder(AUNTIES_COOKING).withLocation(VALID_LOCATION_BRITISH)
                .build();
        assertFalse(uniqueStallList.contains(editedAlice));
    }

    @Test
    public void add_nullStall_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueStallList.add(null));
    }

    @Test
    public void add_duplicateStall_throwsDuplicateStallException() {
        uniqueStallList.add(AUNTIES_COOKING);
        assertThrows(DuplicateStallException.class, () -> uniqueStallList.add(AUNTIES_COOKING));
    }

    @Test
    public void setStall_nullTargetStall_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueStallList.setStall(null, AUNTIES_COOKING));
    }

    @Test
    public void setStall_nullEditedStall_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueStallList.setStall(AUNTIES_COOKING, null));
    }

    @Test
    public void setStall_targetStallNotInList_throwsStallNotFoundException() {
        assertThrows(StallNotFoundException.class, () -> uniqueStallList.setStall(AUNTIES_COOKING, AUNTIES_COOKING));
    }

    @Test
    public void setStall_editedStallIsSameStall_success() {
        uniqueStallList.add(AUNTIES_COOKING);
        uniqueStallList.setStall(AUNTIES_COOKING, AUNTIES_COOKING);
        UniqueStallList expectedUniqueStallList = new UniqueStallList();
        expectedUniqueStallList.add(AUNTIES_COOKING);
        assertEquals(expectedUniqueStallList, uniqueStallList);
    }

    @Test
    public void setStall_editedStallHasSameIdentity_success() {
        uniqueStallList.add(AUNTIES_COOKING);
        Stall editedAlice = new StallBuilder(AUNTIES_COOKING).withLocation(VALID_LOCATION_BRITISH)
                .build();
        uniqueStallList.setStall(AUNTIES_COOKING, editedAlice);
        UniqueStallList expectedUniqueStallList = new UniqueStallList();
        expectedUniqueStallList.add(editedAlice);
        assertEquals(expectedUniqueStallList, uniqueStallList);
    }

    @Test
    public void setStall_editedStallHasDifferentIdentity_success() {
        uniqueStallList.add(AUNTIES_COOKING);
        uniqueStallList.setStall(AUNTIES_COOKING, BRITISH);
        UniqueStallList expectedUniqueStallList = new UniqueStallList();
        expectedUniqueStallList.add(BRITISH);
        assertEquals(expectedUniqueStallList, uniqueStallList);
    }

    @Test
    public void setStall_editedStallHasNonUniqueIdentity_throwsDuplicateStallException() {
        uniqueStallList.add(AUNTIES_COOKING);
        uniqueStallList.add(BRITISH);
        assertThrows(DuplicateStallException.class, () -> uniqueStallList.setStall(AUNTIES_COOKING, BRITISH));
    }

    @Test
    public void remove_nullStall_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueStallList.remove(null));
    }

    @Test
    public void remove_stallDoesNotExist_throwsStallNotFoundException() {
        assertThrows(StallNotFoundException.class, () -> uniqueStallList.remove(AUNTIES_COOKING));
    }

    @Test
    public void remove_existingStall_removesStall() {
        uniqueStallList.add(AUNTIES_COOKING);
        uniqueStallList.remove(AUNTIES_COOKING);
        UniqueStallList expectedUniqueStallList = new UniqueStallList();
        assertEquals(expectedUniqueStallList, uniqueStallList);
    }

    @Test
    public void setStalls_nullUniqueStallList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueStallList.setStalls((UniqueStallList) null));
    }

    @Test
    public void setStalls_uniqueStallList_replacesOwnListWithProvidedUniqueStallList() {
        uniqueStallList.add(AUNTIES_COOKING);
        UniqueStallList expectedUniqueStallList = new UniqueStallList();
        expectedUniqueStallList.add(BRITISH);
        uniqueStallList.setStalls(expectedUniqueStallList);
        assertEquals(expectedUniqueStallList, uniqueStallList);
    }

    @Test
    public void setStalls_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueStallList.setStalls((List<Stall>) null));
    }

    @Test
    public void setStalls_list_replacesOwnListWithProvidedList() {
        uniqueStallList.add(AUNTIES_COOKING);
        List<Stall> stallList = Collections.singletonList(BRITISH);
        uniqueStallList.setStalls(stallList);
        UniqueStallList expectedUniqueStallList = new UniqueStallList();
        expectedUniqueStallList.add(BRITISH);
        assertEquals(expectedUniqueStallList, uniqueStallList);
    }

    @Test
    public void setStalls_listWithDuplicateStalls_throwsDuplicateStallException() {
        List<Stall> listWithDuplicateStalls = Arrays.asList(AUNTIES_COOKING, AUNTIES_COOKING);
        assertThrows(DuplicateStallException.class, () -> uniqueStallList.setStalls(listWithDuplicateStalls));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueStallList.asUnmodifiableObservableList().remove(0));
    }

    @Test
    public void toStringMethod() {
        assertEquals(uniqueStallList.asUnmodifiableObservableList().toString(), uniqueStallList.toString());
    }
}
