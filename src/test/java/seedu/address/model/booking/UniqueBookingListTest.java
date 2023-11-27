package seedu.address.model.booking;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BOB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.booking.exceptions.BookingNotFoundException;
import seedu.address.model.booking.exceptions.DuplicateBookingException;
import seedu.address.model.booking.exceptions.UniqueBookingListNotFoundException;
import seedu.address.testutil.BookingBuilder;

public class UniqueBookingListTest {

    private final UniqueBookingList uniqueBookingList = new UniqueBookingList();

    @Test
    public void contains_nullPerson_throwsNullPointerException() {
        assertThrows(BookingNotFoundException.class, () -> uniqueBookingList.contains(null));
    }

    @Test
    public void contains_personNotInList_returnsFalse() {
        assertFalse(uniqueBookingList.contains(ALICE));
    }

    @Test
    public void contains_personInList_returnsTrue() {
        uniqueBookingList.add(ALICE);
        assertTrue(uniqueBookingList.contains(ALICE));
    }

    @Test
    public void add_nullPerson_throwsNullPointerException() {
        assertThrows(BookingNotFoundException.class, () -> uniqueBookingList.add(null));
    }

    @Test
    public void setPerson_nullTargetPerson_throwsNullPointerException() {
        assertThrows(BookingNotFoundException.class, () -> uniqueBookingList.setBooking(null, ALICE));
    }

    @Test
    public void setPerson_nullEditedPerson_throwsNullPointerException() {
        assertThrows(BookingNotFoundException.class, () -> uniqueBookingList.setBooking(ALICE, null));
    }

    @Test
    public void setPerson_targetPersonNotInList_throwsPersonNotFoundException() {
        assertThrows(BookingNotFoundException.class, () -> uniqueBookingList.setBooking(ALICE, ALICE));
    }

    @Test
    public void setPerson_editedPersonIsSamePerson_success() {
        uniqueBookingList.add(ALICE);
        uniqueBookingList.setBooking(ALICE, ALICE);
        UniqueBookingList expectedUniqueBookingList = new UniqueBookingList();
        expectedUniqueBookingList.add(ALICE);
        assertEquals(expectedUniqueBookingList, uniqueBookingList);
    }

    @Test
    public void setPerson_editedPersonHasSameIdentity_success() {
        uniqueBookingList.add(ALICE);
        Booking editedAlice = new BookingBuilder(ALICE).withPhone(VALID_PHONE_BOB)
                .build();
        uniqueBookingList.setBooking(ALICE, editedAlice);
        UniqueBookingList expectedUniqueBookingList = new UniqueBookingList();
        expectedUniqueBookingList.add(editedAlice);
        assertEquals(expectedUniqueBookingList, uniqueBookingList);
    }

    @Test
    public void setPerson_editedPersonHasDifferentIdentity_success() {
        uniqueBookingList.add(ALICE);
        uniqueBookingList.setBooking(ALICE, BOB);
        UniqueBookingList expectedUniqueBookingList = new UniqueBookingList();
        expectedUniqueBookingList.add(BOB);
        assertEquals(expectedUniqueBookingList, uniqueBookingList);
    }

    @Test
    public void setPerson_editedPersonHasNonUniqueIdentity_throwsDuplicatePersonException() {
        uniqueBookingList.add(ALICE);
        uniqueBookingList.add(BOB);
        assertThrows(DuplicateBookingException.class, () -> uniqueBookingList.setBooking(ALICE, BOB));
    }

    @Test
    public void remove_nullPerson_throwsNullPointerException() {
        assertThrows(BookingNotFoundException.class, () -> uniqueBookingList.remove(null));
    }

    @Test
    public void remove_personDoesNotExist_throwsPersonNotFoundException() {
        assertThrows(BookingNotFoundException.class, () -> uniqueBookingList.remove(ALICE));
    }

    @Test
    public void remove_existingPerson_removesPerson() {
        uniqueBookingList.add(ALICE);
        uniqueBookingList.remove(ALICE);
        UniqueBookingList expectedUniqueBookingList = new UniqueBookingList();
        assertEquals(expectedUniqueBookingList, uniqueBookingList);
    }

    @Test
    public void setPersons_nullUniquePersonList_throwsNullPointerException() {
        assertThrows(UniqueBookingListNotFoundException.class, () ->
                uniqueBookingList.setBookings((UniqueBookingList) null));
    }

    @Test
    public void setPersons_uniquePersonList_replacesOwnListWithProvidedUniquePersonList() {
        uniqueBookingList.add(ALICE);
        UniqueBookingList expectedUniqueBookingList = new UniqueBookingList();
        expectedUniqueBookingList.add(BOB);
        uniqueBookingList.setBookings(expectedUniqueBookingList);
        assertEquals(expectedUniqueBookingList, uniqueBookingList);
    }

    @Test
    public void setPersons_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueBookingList.setBookings((List<Booking>) null));
    }

    @Test
    public void setPersons_list_replacesOwnListWithProvidedList() {
        uniqueBookingList.add(ALICE);
        List<Booking> bookingList = Collections.singletonList(BOB);
        uniqueBookingList.setBookings(bookingList);
        UniqueBookingList expectedUniqueBookingList = new UniqueBookingList();
        expectedUniqueBookingList.add(BOB);
        assertEquals(expectedUniqueBookingList, uniqueBookingList);
    }

    @Test
    public void setPersons_listWithDuplicatePersons_throwsDuplicatePersonException() {
        List<Booking> listWithDuplicateBookings = Arrays.asList(ALICE, ALICE);
        assertThrows(DuplicateBookingException.class, () -> uniqueBookingList.setBookings(listWithDuplicateBookings));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
                -> uniqueBookingList.asUnmodifiableObservableList().remove(0));
    }

    @Test
    public void toStringMethod() {
        assertEquals(uniqueBookingList.asUnmodifiableObservableList().toString(), uniqueBookingList.toString());
    }

    @Test
    public void setPerson_editedPersonWithDifferentIdentity_success() {
        uniqueBookingList.add(ALICE);
        Booking editedAlice = new BookingBuilder(ALICE)
                .withName(BOB.getName().toString())
                .withEmail(BOB.getEmail().toString())
                .withPhone(BOB.getPhone().toString())
                .build();
        uniqueBookingList.setBooking(ALICE, editedAlice);
        UniqueBookingList expectedUniqueBookingList = new UniqueBookingList();
        expectedUniqueBookingList.add(editedAlice);
        assertEquals(expectedUniqueBookingList, uniqueBookingList);
    }

    @Test
    public void setPersons_listWithDuplicatePersonInList_throwsDuplicatePersonException() {
        uniqueBookingList.add(ALICE);
        List<Booking> listWithDuplicateBookings = Arrays.asList(ALICE, BOB, ALICE);
        assertThrows(DuplicateBookingException.class, () -> uniqueBookingList.setBookings(listWithDuplicateBookings));
    }

    @Test
    public void setPersons_listWithDuplicatePersonToAdd_throwsDuplicatePersonException() {
        uniqueBookingList.add(ALICE);
        assertThrows(DuplicateBookingException.class, () -> uniqueBookingList.setBookings(Arrays.asList(ALICE,
                BOB, BOB)));
    }

    @Test
    public void setPersonWithNullTargetPerson_throwsNullPointerException() {
        assertThrows(BookingNotFoundException.class, () -> uniqueBookingList.setBooking(null, ALICE));
    }

    @Test
    public void setPersonWithNullEditedPerson_throwsNullPointerException() {
        assertThrows(BookingNotFoundException.class, () -> uniqueBookingList.setBooking(ALICE, null));
    }

    @Test
    public void setPersonTargetPersonNotInListThrowsPersonNotFoundException() {
        assertThrows(BookingNotFoundException.class, () -> uniqueBookingList.setBooking(ALICE, ALICE));
    }

    @Test
    public void removeNullPersonThrowsNullPointerException() {
        assertThrows(BookingNotFoundException.class, () -> uniqueBookingList.remove(null));
    }

    @Test
    public void removePersonDoesNotExistThrowsPersonNotFoundException() {
        assertThrows(BookingNotFoundException.class, () -> uniqueBookingList.remove(ALICE));
    }

    @Test
    public void asUnmodifiableObservableListModifyListThrowsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
                -> uniqueBookingList.asUnmodifiableObservableList().remove(0));
    }

    @Test
    public void equals() {
        // Test for equality with itself
        assertTrue(uniqueBookingList.equals(uniqueBookingList));

        assertFalse(uniqueBookingList.equals(null));

        // Test for equality with a copy of the list
        UniqueBookingList copyUniqueBookingList = new UniqueBookingList();
        copyUniqueBookingList.setBookings(new ArrayList<>(uniqueBookingList.asUnmodifiableObservableList()));
        assertTrue(uniqueBookingList.equals(copyUniqueBookingList));

        // Test for equality with a list containing different bookings
        UniqueBookingList differentUniqueBookingList = new UniqueBookingList();
        differentUniqueBookingList.add(BOB);
        assertFalse(uniqueBookingList.equals(differentUniqueBookingList));
    }

    @Test
    public void hashCodeTest() {
        // Test for hashCode consistency with the equals method
        UniqueBookingList copyUniqueBookingList = new UniqueBookingList();
        copyUniqueBookingList.setBookings(new ArrayList<>(uniqueBookingList.asUnmodifiableObservableList()));
        assertEquals(uniqueBookingList.hashCode(), copyUniqueBookingList.hashCode());
    }

    @Test
    public void iteratorTest() {
        // Add some bookings to the list
        uniqueBookingList.add(ALICE);
        uniqueBookingList.add(BOB);

        // Create an iterator for the list
        Iterator<Booking> iterator = uniqueBookingList.iterator();

        // Iterate through the list and check if the elements match
        assertTrue(iterator.hasNext());
        assertEquals(ALICE, iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(BOB, iterator.next());
        assertFalse(iterator.hasNext());
    }

}
