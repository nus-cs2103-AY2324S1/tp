package seedu.address.model.group;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalGroups.GROUP1;
import static seedu.address.testutil.TypicalGroups.GROUP2;
import static seedu.address.testutil.TypicalGroups.GROUP3;
import static seedu.address.testutil.TypicalPersons.GEORGE;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.group.exceptions.DuplicateGroupException;
import seedu.address.model.group.exceptions.GroupNotFoundException;
import seedu.address.model.person.Person;

public class UniqueGroupListTest {

    private final UniqueGroupList uniqueGroupList = new UniqueGroupList();

    @Test
    public void contains_nullGroup_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueGroupList.contains(null));
    }

    @Test
    public void contains_groupNotInList_returnsFalse() {
        assertFalse(uniqueGroupList.contains(GROUP1));
    }

    @Test
    public void contains_groupInList_returnsTrue() {
        uniqueGroupList.add(GROUP1);
        assertTrue(uniqueGroupList.contains(GROUP1));
    }

    @Test
    public void add_nullGroup_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueGroupList.add(null));
    }

    @Test
    public void add_duplicateGroup_throwsDuplicateGroupException() {
        uniqueGroupList.add(GROUP1);
        assertThrows(DuplicateGroupException.class, () -> uniqueGroupList.add(GROUP1));
    }

    @Test
    public void remove_nullGroup_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueGroupList.remove((Group) null));
    }

    @Test
    public void remove_groupDoesNotExist_throwsGroupNotFoundException() {
        assertThrows(GroupNotFoundException.class, () -> uniqueGroupList.remove(GROUP1));
    }

    @Test
    public void remove_existingGroup_removesGroup() {
        uniqueGroupList.add(GROUP1);
        uniqueGroupList.remove(GROUP1);
        UniqueGroupList expecteduniqueGroupList = new UniqueGroupList();
        assertEquals(expecteduniqueGroupList, uniqueGroupList);
    }

    @Test
    public void remove_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueGroupList.remove((Person) null));
    }

    @Test
    public void remove_validPerson_success() {
        uniqueGroupList.add(GROUP3);
        uniqueGroupList.remove(GEORGE);
        assertFalse(GROUP3.hasMember(GEORGE));
    }

    @Test
    public void setGroups_nullUniqueGroupList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueGroupList.setGroups((UniqueGroupList) null));
    }

    @Test
    public void setGroups_uniqueGroupList_replacesOwnListWithProvidedUniqueGroupList() {
        uniqueGroupList.add(GROUP1);
        UniqueGroupList expectedUniqueGroupList = new UniqueGroupList();
        expectedUniqueGroupList.add(GROUP2);
        uniqueGroupList.setGroups(expectedUniqueGroupList);
        assertEquals(expectedUniqueGroupList, uniqueGroupList);
    }

    @Test
    public void setGroups_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueGroupList.setGroups((List<Group>) null));
    }

    @Test
    public void setGroups_list_replacesOwnListWithProvidedList() {
        uniqueGroupList.add(GROUP1);
        List<Group> groupList = Collections.singletonList(GROUP2);
        uniqueGroupList.setGroups(groupList);
        UniqueGroupList expectedUniqueGroupList = new UniqueGroupList();
        expectedUniqueGroupList.add(GROUP2);
        assertEquals(expectedUniqueGroupList, uniqueGroupList);
    }

    @Test
    public void setGroups_listWithDuplicateGroups_throwsDuplicateGroupException() {
        List<Group> listWithDuplicateGroups = Arrays.asList(GROUP1, GROUP1);
        assertThrows(DuplicateGroupException.class, () -> uniqueGroupList.setGroups(listWithDuplicateGroups));
    }

    @Test
    public void toStringMethod() {
        assertEquals(uniqueGroupList.asUnmodifiableObservableList().toString(), uniqueGroupList.toString());
    }

    @Test
    public void equals_sameList_returnsTrue() {
        UniqueGroupList firstList = new UniqueGroupList();
        UniqueGroupList secondList = new UniqueGroupList();
        assertTrue(firstList.equals(secondList));
    }

    @Test
    public void equals_differentLists_returnsFalse() {
        UniqueGroupList firstList = new UniqueGroupList();
        UniqueGroupList secondList = new UniqueGroupList();
        firstList.add(GROUP1);
        secondList.add(GROUP2);
        assertFalse(firstList.equals(secondList));
    }

    @Test
    public void hashCode_sameList_returnsSameHashCode() {
        UniqueGroupList firstList = new UniqueGroupList();
        UniqueGroupList secondList = new UniqueGroupList();
        assertEquals(firstList.hashCode(), secondList.hashCode());
    }

    @Test
    public void hashCode_differentLists_returnsDifferentHashCode() {
        UniqueGroupList firstList = new UniqueGroupList();
        UniqueGroupList secondList = new UniqueGroupList();
        firstList.add(GROUP1);
        secondList.add(GROUP2);
        assertNotEquals(firstList.hashCode(), secondList.hashCode());
    }
}
