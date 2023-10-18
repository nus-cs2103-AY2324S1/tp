package networkbook.model.util;

import static networkbook.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import networkbook.model.person.exceptions.DuplicateException;
import networkbook.model.person.exceptions.ItemNotFoundException;

public class UniqueListTest {
    @Test
    public void contains_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new UniqueList<UniqueNumber>().contains(null));
    }

    @Test
    public void contains_elementNotInList_returnsFalse() {
        assertFalse(new UniqueList<UniqueNumber>().contains(new UniqueNumber(3, 0)));
        UniqueList<UniqueNumber> uniqueList = new UniqueList<>();
        uniqueList.add(new UniqueNumber(3, 0));
        assertFalse(uniqueList.contains(new UniqueNumber(4, 0)));
        uniqueList.add(new UniqueNumber(2, 0));
        assertFalse(uniqueList.contains(new UniqueNumber(4, 0)));
    }

    @Test
    public void contains_elementInList_returnsTrue() {
        UniqueList<UniqueNumber> uniqueList = new UniqueList<>();
        uniqueList.add(new UniqueNumber(3, 0));
        assertTrue(uniqueList.contains(new UniqueNumber(3, 1)));
        assertTrue(uniqueList.contains(new UniqueNumber(3, 0)));
        UniqueNumber uniqueNumber = new UniqueNumber(4, 10);
        uniqueList.add(uniqueNumber);
        assertTrue(uniqueList.contains(uniqueNumber));
    }

    @Test
    public void add_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new UniqueList<UniqueNumber>().add(null));
    }

    @Test
    public void add_duplicate_throwsDuplicateException() {
        UniqueList<UniqueNumber> uniqueList = new UniqueList<>();
        uniqueList.add(new UniqueNumber(1, 0));
        assertThrows(DuplicateException.class, () -> uniqueList.add(new UniqueNumber(1, 10)));
    }

    @Test
    public void addAll_null_throwsNullPointerException() {
        UniqueList<UniqueNumber> uniqueList = new UniqueList<>();
        assertThrows(NullPointerException.class, () -> uniqueList.addAllFromList(null));
    }

    @Test
    public void addAll_duplicate_duplicateNotAdded() {
        UniqueList<UniqueNumber> uniqueList = new UniqueList<>();
        uniqueList.add(new UniqueNumber(1, 0));
        UniqueList<UniqueNumber> duplicateUniqueList = new UniqueList<>();
        duplicateUniqueList.add(new UniqueNumber(1, 10));
        uniqueList.addAllFromList(duplicateUniqueList);
        UniqueList<UniqueNumber> expectedList = new UniqueList<>();
        expectedList.add(new UniqueNumber(1, 0));
        assertEquals(expectedList, uniqueList);
    }

    @Test
    public void addAll_noDuplicate_success() {
        UniqueList<UniqueNumber> uniqueList = new UniqueList<>();
        uniqueList.add(new UniqueNumber(1, 0));
        UniqueList<UniqueNumber> toAddList = new UniqueList<>();
        toAddList.add(new UniqueNumber(2, 2));
        uniqueList.addAllFromList(toAddList);
        UniqueList<UniqueNumber> expectedList = new UniqueList<>();
        expectedList.add(new UniqueNumber(1, 0));
        expectedList.add(new UniqueNumber(2, 2));
        assertEquals(expectedList, uniqueList);
    }

    @Test
    public void setItem_nullTarget_throwsNullPointerException() {
        assertThrows(NullPointerException.class, ()
                -> new UniqueList<UniqueNumber>().setItem(null, new UniqueNumber(5, 0)));
    }

    @Test
    public void setItem_nullEdited_throwsNullPointerException() {
        assertThrows(NullPointerException.class, ()
                -> new UniqueList<UniqueNumber>().setItem(new UniqueNumber(5, 0), null));
    }

    @Test
    public void setItem_targetNotInList_throwsItemNotFoundException() {
        UniqueList<UniqueNumber> uniqueList = new UniqueList<UniqueNumber>();
        uniqueList.add(new UniqueNumber(1, 0));
        assertThrows(ItemNotFoundException.class, ()
                -> uniqueList.setItem(new UniqueNumber(1, 1), new UniqueNumber(2, 0)));
    }

    @Test
    public void setItem_editedEqualToTarget_success() {
        UniqueList<UniqueNumber> uniqueList = new UniqueList<>();
        UniqueNumber uniqueNumber = new UniqueNumber(1, 0);
        uniqueList.add(uniqueNumber);
        uniqueList.setItem(uniqueNumber, uniqueNumber);
        UniqueList<UniqueNumber> expectedList = new UniqueList<>();
        expectedList.add(uniqueNumber);
        assertEquals(expectedList, uniqueList);
    }

    @Test
    public void setItem_editedIsSameAsTarget_success() {
        UniqueList<UniqueNumber> uniqueList = new UniqueList<>();
        UniqueNumber target = new UniqueNumber(1, 0);
        UniqueNumber edited = new UniqueNumber(1, 10);
        uniqueList.add(target);
        uniqueList.setItem(target, edited);
        UniqueList<UniqueNumber> expectedList = new UniqueList<>();
        expectedList.add(edited);
        UniqueList<UniqueNumber> wrongList = new UniqueList<>();
        wrongList.add(target);
        assertEquals(expectedList, uniqueList);
        assertNotEquals(wrongList, uniqueList);
    }

    @Test
    public void setItem_editedDifferentFromTarget_success() {
        UniqueList<UniqueNumber> uniqueList = new UniqueList<>();
        UniqueNumber target = new UniqueNumber(1, 0);
        UniqueNumber edited = new UniqueNumber(2, 10);
        uniqueList.add(target);
        uniqueList.setItem(target, edited);
        UniqueList<UniqueNumber> expectedList = new UniqueList<>();
        expectedList.add(edited);
        UniqueList<UniqueNumber> wrongList = new UniqueList<>();
        wrongList.add(target);
        assertEquals(expectedList, uniqueList);
        assertNotEquals(wrongList, uniqueList);
    }

    @Test
    public void setItem_editedNotUnique_throwsDuplicateException() {
        UniqueList<UniqueNumber> uniqueList = new UniqueList<>();
        UniqueNumber target = new UniqueNumber(1, 0);
        UniqueNumber edited = new UniqueNumber(2, 10);
        UniqueNumber duplicate = new UniqueNumber(2, 10);
        uniqueList.add(target);
        uniqueList.add(edited);
        assertThrows(DuplicateException.class, () -> uniqueList.setItem(target, duplicate));
    }

    @Test
    public void remove_itemDoesNotExist_throwsItemNotFoundException() {
        assertThrows(ItemNotFoundException.class, ()
                -> new UniqueList<UniqueNumber>().remove(new UniqueNumber(1, 0)));
    }

    @Test
    public void remove_itemExists_removesItem() {
        UniqueList<UniqueNumber> uniqueList = new UniqueList<>();
        uniqueList.add(new UniqueNumber(1, 0));
        uniqueList.remove(new UniqueNumber(1, 0));
        UniqueList<UniqueNumber> expected = new UniqueList<>();
        assertEquals(expected, uniqueList);
    }

    @Test
    public void setItems_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, ()
                -> new UniqueList<UniqueNumber>().setItems((List<UniqueNumber>) null));
        assertThrows(NullPointerException.class, ()
                -> new UniqueList<UniqueNumber>().setItems((UniqueList<UniqueNumber>) null));
    }

    @Test
    public void setItems_uniqueList_replacesOwnListWithProvided() {
        UniqueList<UniqueNumber> expectedUniqueList = new UniqueList<>();
        List<UniqueNumber> expectedList = List.of(new UniqueNumber(3, 1));
        expectedList.forEach(expectedUniqueList::add);
        assertEquals(expectedUniqueList, new UniqueList<UniqueNumber>().setItems(expectedUniqueList));
        assertNotEquals(new UniqueList<UniqueNumber>(),
                new UniqueList<UniqueNumber>().setItems(expectedUniqueList));
        assertEquals(expectedUniqueList, new UniqueList<UniqueNumber>().setItems(expectedList));
    }

    @Test
    public void setItems_listWithDuplicate_throwsDuplicateException() {
        List<UniqueNumber> list = List.of(new UniqueNumber(1, 0), new UniqueNumber(1, 1));
        assertThrows(DuplicateException.class, () -> new UniqueList<UniqueNumber>().setItems(list));
    }

    @Test
    public void isEmpty_emptyList_returnsTrue() {
        assertTrue(new UniqueList<UniqueNumber>().isEmpty());
    }

    @Test
    public void isEmpty_notEmptyList_returnsFalse() {
        UniqueList<UniqueNumber> uniqueList = new UniqueList<>();
        uniqueList.add(new UniqueNumber(1, 0));
        assertFalse(uniqueList.isEmpty());
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
                -> new UniqueList<UniqueNumber>()
                .asUnmodifiableObservableList()
                .add(new UniqueNumber(1, 0)));
    }

    @Test
    public void equals() {
        UniqueList<UniqueNumber> uniqueList = new UniqueList<>();
        UniqueNumber uniqueNumber = new UniqueNumber(1, 0);
        UniqueNumber sameUniqueNumber = new UniqueNumber(1, 0);
        UniqueNumber differentUniqueNumber = new UniqueNumber(1, 10);

        // same reference
        assertTrue(uniqueList.equals(uniqueList));

        // different class
        assertFalse(uniqueList.equals(uniqueNumber));

        // same list
        assertTrue(uniqueList.equals(new UniqueList<UniqueNumber>()));
        uniqueList.add(uniqueNumber);
        UniqueList<UniqueNumber> sameUniqueList = new UniqueList<>();
        sameUniqueList.add(sameUniqueNumber);
        assertTrue(uniqueList.equals(sameUniqueList));

        // different list
        assertFalse(uniqueList.equals(new UniqueList<UniqueNumber>()));
        UniqueList<UniqueNumber> differentUniqueList = new UniqueList<>();
        differentUniqueList.add(differentUniqueNumber);
        assertFalse(uniqueList.equals(differentUniqueList));
    }

    @Test
    public void toStringMethod() {
        UniqueList<UniqueNumber> uniqueList = new UniqueList<>();
        uniqueList.setItems(List.of(new UniqueNumber(1, 0)));
        assertEquals(uniqueList.asUnmodifiableObservableList().toString(), uniqueList.toString());
    }
}
