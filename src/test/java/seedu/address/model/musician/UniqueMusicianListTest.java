package seedu.address.model.musician;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalMusicians.ALICE;
import static seedu.address.testutil.TypicalMusicians.BOB;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.musician.exceptions.DuplicateMusicianException;
import seedu.address.model.musician.exceptions.MusicianNotFoundException;
import seedu.address.testutil.MusicianBuilder;

public class UniqueMusicianListTest {

    private final UniqueMusicianList uniqueMusicianList = new UniqueMusicianList();

    @Test
    public void contains_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueMusicianList.contains(null));
    }

    @Test
    public void contains_personNotInList_returnsFalse() {
        assertFalse(uniqueMusicianList.contains(ALICE));
    }

    @Test
    public void contains_personInList_returnsTrue() {
        uniqueMusicianList.add(ALICE);
        assertTrue(uniqueMusicianList.contains(ALICE));
    }

    @Test
    public void contains_personWithSameIdentityFieldsInList_returnsTrue() {
        uniqueMusicianList.add(ALICE);
        Musician editedAlice = new MusicianBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(uniqueMusicianList.contains(editedAlice));
    }

    @Test
    public void add_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueMusicianList.add(null));
    }

    @Test
    public void add_duplicatePerson_throwsDuplicatePersonException() {
        uniqueMusicianList.add(ALICE);
        assertThrows(DuplicateMusicianException.class, () -> uniqueMusicianList.add(ALICE));
    }

    @Test
    public void setPerson_nullTargetPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueMusicianList.setPerson(null, ALICE));
    }

    @Test
    public void setPerson_nullEditedPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueMusicianList.setPerson(ALICE, null));
    }

    @Test
    public void setPerson_targetPersonNotInList_throwsPersonNotFoundException() {
        assertThrows(MusicianNotFoundException.class, () -> uniqueMusicianList.setPerson(ALICE, ALICE));
    }

    @Test
    public void setPerson_editedPersonIsSamePerson_success() {
        uniqueMusicianList.add(ALICE);
        uniqueMusicianList.setPerson(ALICE, ALICE);
        UniqueMusicianList expectedUniqueMusicianList = new UniqueMusicianList();
        expectedUniqueMusicianList.add(ALICE);
        assertEquals(expectedUniqueMusicianList, uniqueMusicianList);
    }

    @Test
    public void setPerson_editedPersonHasSameIdentity_success() {
        uniqueMusicianList.add(ALICE);
        Musician editedAlice = new MusicianBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        uniqueMusicianList.setPerson(ALICE, editedAlice);
        UniqueMusicianList expectedUniqueMusicianList = new UniqueMusicianList();
        expectedUniqueMusicianList.add(editedAlice);
        assertEquals(expectedUniqueMusicianList, uniqueMusicianList);
    }

    @Test
    public void setPerson_editedPersonHasDifferentIdentity_success() {
        uniqueMusicianList.add(ALICE);
        uniqueMusicianList.setPerson(ALICE, BOB);
        UniqueMusicianList expectedUniqueMusicianList = new UniqueMusicianList();
        expectedUniqueMusicianList.add(BOB);
        assertEquals(expectedUniqueMusicianList, uniqueMusicianList);
    }

    @Test
    public void setPerson_editedPersonHasNonUniqueIdentity_throwsDuplicatePersonException() {
        uniqueMusicianList.add(ALICE);
        uniqueMusicianList.add(BOB);
        assertThrows(DuplicateMusicianException.class, () -> uniqueMusicianList.setPerson(ALICE, BOB));
    }

    @Test
    public void remove_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueMusicianList.remove(null));
    }

    @Test
    public void remove_personDoesNotExist_throwsPersonNotFoundException() {
        assertThrows(MusicianNotFoundException.class, () -> uniqueMusicianList.remove(ALICE));
    }

    @Test
    public void remove_existingPerson_removesPerson() {
        uniqueMusicianList.add(ALICE);
        uniqueMusicianList.remove(ALICE);
        UniqueMusicianList expectedUniqueMusicianList = new UniqueMusicianList();
        assertEquals(expectedUniqueMusicianList, uniqueMusicianList);
    }

    @Test
    public void setPersons_nullUniquePersonList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueMusicianList.setPersons((UniqueMusicianList) null));
    }

    @Test
    public void setPersons_uniquePersonList_replacesOwnListWithProvidedUniquePersonList() {
        uniqueMusicianList.add(ALICE);
        UniqueMusicianList expectedUniqueMusicianList = new UniqueMusicianList();
        expectedUniqueMusicianList.add(BOB);
        uniqueMusicianList.setPersons(expectedUniqueMusicianList);
        assertEquals(expectedUniqueMusicianList, uniqueMusicianList);
    }

    @Test
    public void setPersons_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueMusicianList.setPersons((List<Musician>) null));
    }

    @Test
    public void setPersons_list_replacesOwnListWithProvidedList() {
        uniqueMusicianList.add(ALICE);
        List<Musician> musicianList = Collections.singletonList(BOB);
        uniqueMusicianList.setPersons(musicianList);
        UniqueMusicianList expectedUniqueMusicianList = new UniqueMusicianList();
        expectedUniqueMusicianList.add(BOB);
        assertEquals(expectedUniqueMusicianList, uniqueMusicianList);
    }

    @Test
    public void setPersons_listWithDuplicatePersons_throwsDuplicatePersonException() {
        List<Musician> listWithDuplicateMusicians = Arrays.asList(ALICE, ALICE);
        assertThrows(DuplicateMusicianException.class, () -> uniqueMusicianList.setPersons(listWithDuplicateMusicians));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueMusicianList.asUnmodifiableObservableList().remove(0));
    }

    @Test
    public void toStringMethod() {
        assertEquals(uniqueMusicianList.asUnmodifiableObservableList().toString(), uniqueMusicianList.toString());
    }
}
