package seedu.address.model.musician;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
    public void contains_nullMusician_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueMusicianList.contains(null));
    }

    @Test
    public void contains_musicianNotInList_returnsFalse() {
        assertFalse(uniqueMusicianList.contains(ALICE));
    }

    @Test
    public void contains_musicianInList_returnsTrue() {
        uniqueMusicianList.add(ALICE);
        assertTrue(uniqueMusicianList.contains(ALICE));
    }

    @Test
    public void contains_musicianWithSameIdentityFieldsInList_returnsTrue() {
        uniqueMusicianList.add(ALICE);
        Musician editedAlice = new MusicianBuilder(ALICE).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(uniqueMusicianList.contains(editedAlice));
    }

    @Test
    public void add_nullMusician_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueMusicianList.add(null));
    }

    @Test
    public void add_duplicateMusician_throwsDuplicateMusicianException() {
        uniqueMusicianList.add(ALICE);
        assertThrows(DuplicateMusicianException.class, () -> uniqueMusicianList.add(ALICE));
    }

    @Test
    public void setMusician_nullTargetMusician_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueMusicianList.setMusician(null, ALICE));
    }

    @Test
    public void setMusician_nullEditedMusician_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueMusicianList.setMusician(ALICE, null));
    }

    @Test
    public void setMusician_targetMusicianNotInList_throwsMusicianNotFoundException() {
        assertThrows(MusicianNotFoundException.class, () -> uniqueMusicianList.setMusician(ALICE, ALICE));
    }

    @Test
    public void setMusician_editedMusicianIsSameMusician_success() {
        uniqueMusicianList.add(ALICE);
        uniqueMusicianList.setMusician(ALICE, ALICE);
        UniqueMusicianList expectedUniqueMusicianList = new UniqueMusicianList();
        expectedUniqueMusicianList.add(ALICE);
        assertEquals(expectedUniqueMusicianList, uniqueMusicianList);
    }

    @Test
    public void setMusician_editedMusicianHasSameIdentity_success() {
        uniqueMusicianList.add(ALICE);
        Musician editedAlice = new MusicianBuilder(ALICE).withTags(VALID_TAG_HUSBAND)
                .build();
        uniqueMusicianList.setMusician(ALICE, editedAlice);
        UniqueMusicianList expectedUniqueMusicianList = new UniqueMusicianList();
        expectedUniqueMusicianList.add(editedAlice);
        assertEquals(expectedUniqueMusicianList, uniqueMusicianList);
    }

    @Test
    public void setMusician_editedMusicianHasDifferentIdentity_success() {
        uniqueMusicianList.add(ALICE);
        uniqueMusicianList.setMusician(ALICE, BOB);
        UniqueMusicianList expectedUniqueMusicianList = new UniqueMusicianList();
        expectedUniqueMusicianList.add(BOB);
        assertEquals(expectedUniqueMusicianList, uniqueMusicianList);
    }

    @Test
    public void setMusician_editedMusicianHasNonUniqueIdentity_throwsDuplicateMusicianException() {
        uniqueMusicianList.add(ALICE);
        uniqueMusicianList.add(BOB);
        assertThrows(DuplicateMusicianException.class, () -> uniqueMusicianList.setMusician(ALICE, BOB));
    }

    @Test
    public void remove_nullMusician_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueMusicianList.remove(null));
    }

    @Test
    public void remove_musicianDoesNotExist_throwsMusicianNotFoundException() {
        assertThrows(MusicianNotFoundException.class, () -> uniqueMusicianList.remove(ALICE));
    }

    @Test
    public void remove_existingMusician_removesMusician() {
        uniqueMusicianList.add(ALICE);
        uniqueMusicianList.remove(ALICE);
        UniqueMusicianList expectedUniqueMusicianList = new UniqueMusicianList();
        assertEquals(expectedUniqueMusicianList, uniqueMusicianList);
    }

    @Test
    public void setMusicians_nullUniqueMusicianList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueMusicianList.setMusicians((UniqueMusicianList) null));
    }

    @Test
    public void setMusicians_uniqueMusicianList_replacesOwnListWithProvidedUniqueMusicianList() {
        uniqueMusicianList.add(ALICE);
        UniqueMusicianList expectedUniqueMusicianList = new UniqueMusicianList();
        expectedUniqueMusicianList.add(BOB);
        uniqueMusicianList.setMusicians(expectedUniqueMusicianList);
        assertEquals(expectedUniqueMusicianList, uniqueMusicianList);
    }

    @Test
    public void setMusicians_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueMusicianList.setMusicians((List<Musician>) null));
    }

    @Test
    public void setMusicians_list_replacesOwnListWithProvidedList() {
        uniqueMusicianList.add(ALICE);
        List<Musician> musicianList = Collections.singletonList(BOB);
        uniqueMusicianList.setMusicians(musicianList);
        UniqueMusicianList expectedUniqueMusicianList = new UniqueMusicianList();
        expectedUniqueMusicianList.add(BOB);
        assertEquals(expectedUniqueMusicianList, uniqueMusicianList);
    }

    @Test
    public void setMusicians_listWithDuplicateMusicians_throwsDuplicateMusicianException() {
        List<Musician> listWithDuplicateMusicians = Arrays.asList(ALICE, ALICE);
        assertThrows(DuplicateMusicianException.class, () -> uniqueMusicianList.setMusicians(
                listWithDuplicateMusicians));
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
