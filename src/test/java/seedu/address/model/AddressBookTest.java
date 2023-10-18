package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalMusicians.ALICE;
import static seedu.address.testutil.TypicalMusicians.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.band.Band;
import seedu.address.model.musician.Musician;
import seedu.address.model.musician.exceptions.DuplicateMusicianException;
import seedu.address.testutil.MusicianBuilder;

public class AddressBookTest {

    private final AddressBook addressBook = new AddressBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), addressBook.getMusicianList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        AddressBook newData = getTypicalAddressBook();
        addressBook.resetData(newData);
        assertEquals(newData, addressBook);
    }

    @Test
    public void resetData_withDuplicateMusicians_throwsDuplicateMusicianException() {
        // Two musicians with the same identity fields
        Musician editedAlice = new MusicianBuilder(ALICE).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Musician> newMusicians = Arrays.asList(ALICE, editedAlice);
        AddressBookStub newData = new AddressBookStub(newMusicians);

        assertThrows(DuplicateMusicianException.class, () -> addressBook.resetData(newData));
    }

    @Test
    public void hasMusician_nullMusician_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasMusician(null));
    }

    @Test
    public void hasMusician_musicianNotInAddressBook_returnsFalse() {
        assertFalse(addressBook.hasMusician(ALICE));
    }

    @Test
    public void hasMusician_musicianInAddressBook_returnsTrue() {
        addressBook.addMusician(ALICE);
        assertTrue(addressBook.hasMusician(ALICE));
    }

    @Test
    public void hasMusician_musicianWithSameIdentityFieldsInAddressBook_returnsTrue() {
        addressBook.addMusician(ALICE);
        Musician editedAlice = new MusicianBuilder(ALICE).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(addressBook.hasMusician(editedAlice));
    }

    @Test
    public void getMusicianList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> addressBook.getMusicianList().remove(0));
    }

    @Test
    public void toStringMethod() {
        String expected = AddressBook.class.getCanonicalName() + "{musicians=" + addressBook.getMusicianList() + "}";
        assertEquals(expected, addressBook.toString());
    }

    /**
     * A stub ReadOnlyAddressBook whose musicians list can violate interface constraints.
     */
    private static class AddressBookStub implements ReadOnlyAddressBook {
        private final ObservableList<Musician> musicians = FXCollections.observableArrayList();

        AddressBookStub(Collection<Musician> musicians) {
            this.musicians.setAll(musicians);
        }

        @Override
        public ObservableList<Musician> getMusicianList() {
            return musicians;
        }

        @Override
        public ObservableList<Band> getBandList() {
            return null; // to be implemented
        }
    }

}
