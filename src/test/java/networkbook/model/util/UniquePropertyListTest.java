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

public class UniquePropertyListTest {
    @Test
    public void contains_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new UniquePropertyList<UniqueNumber>().contains(null));
    }

    @Test
    public void contains_elementNotInList_returnsFalse() {
        assertFalse(new UniquePropertyList<UniqueNumber>().contains(new UniqueNumber(3, 0)));
        UniquePropertyList<UniqueNumber> uniqueList = new UniquePropertyList<>();
        uniqueList.add(new UniqueNumber(3, 0));
        assertFalse(uniqueList.contains(new UniqueNumber(4, 0)));
        uniqueList.add(new UniqueNumber(2, 0));
        assertFalse(uniqueList.contains(new UniqueNumber(4, 0)));
    }

    @Test
    public void contains_elementInList_returnsTrue() {
        UniquePropertyList<UniqueNumber> uniqueList = new UniquePropertyList<>();
        uniqueList.add(new UniqueNumber(3, 0));
        assertTrue(uniqueList.contains(new UniqueNumber(3, 1)));
        assertTrue(uniqueList.contains(new UniqueNumber(3, 0)));
        UniqueNumber uniqueNumber = new UniqueNumber(4, 10);
        uniqueList.add(uniqueNumber);
        assertTrue(uniqueList.contains(uniqueNumber));
    }

    @Test
    public void add_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new UniquePropertyList<UniqueNumber>().add(null));
    }

    @Test
    public void add_duplicate_throwsDuplicateException() {
        UniquePropertyList<UniqueNumber> uniqueList = new UniquePropertyList<>();
        uniqueList.add(new UniqueNumber(1, 0));
        assertThrows(DuplicateException.class, () -> uniqueList.add(new UniqueNumber(1, 10)));
    }

    @Test
    public void set_nullTarget_throwsNullPointerException() {
        assertThrows(NullPointerException.class, ()
                -> new UniquePropertyList<UniqueNumber>().set(null, new UniqueNumber(5, 0)));
    }

    @Test
    public void set_nullEdited_throwsNullPointerException() {
        assertThrows(NullPointerException.class, ()
                -> new UniquePropertyList<UniqueNumber>().set(new UniqueNumber(5, 0), null));
    }

    @Test
    public void set_targetNotInList_throwsItemNotFoundException() {
        UniquePropertyList<UniqueNumber> uniqueList = new UniquePropertyList<UniqueNumber>();
        uniqueList.add(new UniqueNumber(1, 0));
        assertThrows(ItemNotFoundException.class, ()
                -> uniqueList.set(new UniqueNumber(1, 1), new UniqueNumber(2, 0)));
    }

    @Test
    public void set_editedEqualToTarget_success() {
        UniquePropertyList<UniqueNumber> uniqueList = new UniquePropertyList<>();
        UniqueNumber uniqueNumber = new UniqueNumber(1, 0);
        uniqueList.add(uniqueNumber);
        uniqueList.set(uniqueNumber, uniqueNumber);
        UniquePropertyList<UniqueNumber> expectedList = new UniquePropertyList<>();
        expectedList.add(uniqueNumber);
        assertEquals(expectedList, uniqueList);
    }

    @Test
    public void set_editedIsSameAsTarget_success() {
        UniquePropertyList<UniqueNumber> uniqueList = new UniquePropertyList<>();
        UniqueNumber target = new UniqueNumber(1, 0);
        UniqueNumber edited = new UniqueNumber(1, 10);
        uniqueList.add(target);
        uniqueList.set(target, edited);
        UniquePropertyList<UniqueNumber> expectedList = new UniquePropertyList<>();
        expectedList.add(edited);
        UniquePropertyList<UniqueNumber> wrongList = new UniquePropertyList<>();
        wrongList.add(target);
        assertEquals(expectedList, uniqueList);
        assertNotEquals(wrongList, uniqueList);
    }

    @Test
    public void set_editedDifferentFromTarget_success() {
        UniquePropertyList<UniqueNumber> uniqueList = new UniquePropertyList<>();
        UniqueNumber target = new UniqueNumber(1, 0);
        UniqueNumber edited = new UniqueNumber(2, 10);
        uniqueList.add(target);
        uniqueList.set(target, edited);
        UniquePropertyList<UniqueNumber> expectedList = new UniquePropertyList<>();
        expectedList.add(edited);
        UniquePropertyList<UniqueNumber> wrongList = new UniquePropertyList<>();
        wrongList.add(target);
        assertEquals(expectedList, uniqueList);
        assertNotEquals(wrongList, uniqueList);
    }

    @Test
    public void set_editedNotUnique_throwsDuplicateException() {
        UniquePropertyList<UniqueNumber> uniqueList = new UniquePropertyList<>();
        UniqueNumber target = new UniqueNumber(1, 0);
        UniqueNumber edited = new UniqueNumber(2, 10);
        UniqueNumber duplicate = new UniqueNumber(2, 10);
        uniqueList.add(target);
        uniqueList.add(edited);
        assertThrows(DuplicateException.class, () -> uniqueList.set(target, duplicate));
    }

    @Test
    public void remove_itemDoesNotExist_throwsItemNotFoundException() {
        assertThrows(ItemNotFoundException.class, ()
                -> new UniquePropertyList<UniqueNumber>().remove(new UniqueNumber(1, 0)));
    }

    @Test
    public void remove_itemExists_removesItem() {
        UniquePropertyList<UniqueNumber> uniqueList = new UniquePropertyList<>();
        uniqueList.add(new UniqueNumber(1, 0));
        uniqueList.remove(new UniqueNumber(1, 0));
        UniquePropertyList<UniqueNumber> expected = new UniquePropertyList<>();
        assertEquals(expected, uniqueList);
    }

    @Test
    public void setItems_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, ()
                -> new UniquePropertyList<UniqueNumber>().setItems((List<UniqueNumber>) null));
        assertThrows(NullPointerException.class, ()
                -> new UniquePropertyList<UniqueNumber>().setItems((UniquePropertyList<UniqueNumber>) null));
    }

    @Test
    public void setItems_uniqueList_replacesOwnListWithProvided() {
        UniquePropertyList<UniqueNumber> expectedUniqueList = new UniquePropertyList<>();
        List<UniqueNumber> expectedList = List.of(new UniqueNumber(3, 1));
        expectedList.forEach(expectedUniqueList::add);
        assertEquals(expectedUniqueList, new UniquePropertyList<UniqueNumber>().setItems(expectedUniqueList));
        assertNotEquals(new UniquePropertyList<UniqueNumber>(),
                new UniquePropertyList<UniqueNumber>().setItems(expectedUniqueList));
        assertEquals(expectedUniqueList, new UniquePropertyList<UniqueNumber>().setItems(expectedList));
    }

    @Test
    public void setItems_listWithDuplicate_throwsDuplicateException() {
        List<UniqueNumber> list = List.of(new UniqueNumber(1, 0), new UniqueNumber(1, 1));
        assertThrows(DuplicateException.class, () -> new UniquePropertyList<UniqueNumber>().setItems(list));
    }

    @Test
    public void isEmpty_emptyList_returnsTrue() {
        assertTrue(new UniquePropertyList<UniqueNumber>().isEmpty());
    }

    @Test
    public void isEmpty_notEmptyList_returnsFalse() {
        UniquePropertyList<UniqueNumber> uniqueList = new UniquePropertyList<>();
        uniqueList.add(new UniqueNumber(1, 0));
        assertFalse(uniqueList.isEmpty());
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
                -> new UniquePropertyList<UniqueNumber>()
                .asUnmodifiableObservableList()
                .add(new UniqueNumber(1, 0)));
    }

    @Test
    public void toStringMethod() {
        UniquePropertyList<UniqueNumber> uniqueList = new UniquePropertyList<>();
        uniqueList.setItems(List.of(new UniqueNumber(1, 0)));
        assertEquals(uniqueList.asUnmodifiableObservableList().toString(), uniqueList.toString());
    }
}
