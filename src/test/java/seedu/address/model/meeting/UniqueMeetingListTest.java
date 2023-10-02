package seedu.address.model.meeting;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalMeetings.MEETING1;
import static seedu.address.testutil.TypicalPersons.ALICE;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.meeting.exceptions.DuplicateMeetingException;
import seedu.address.model.meeting.exceptions.MeetingNotFoundException;
import seedu.address.testutil.AddressBookBuilder;
import seedu.address.testutil.MeetingBuilder;

public class UniqueMeetingListTest {
    private final UniqueMeetingList uniqueMeetingList = new UniqueMeetingList();

    @Test
    public void contains_nullMeeting_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueMeetingList.contains((Meeting) null));
    }

    @Test
    public void contains_personNotInList_returnsFalse() {
        assertFalse(uniqueMeetingList.contains(MEETING1));
    }

    @Test
    public void contains_meetingInList_returnsTrue() {
        uniqueMeetingList.add(MEETING1);
        assertTrue(uniqueMeetingList.contains(MEETING1));
    }

    @Test
    public void contains_meetingWithSameIdentityFieldsInList_returnsTrue() {
        uniqueMeetingList.add(MEETING1);
        AddressBookBuilder test = new AddressBookBuilder();
        test.withPerson(ALICE);
        Meeting editedMeeting1 = new MeetingBuilder(MEETING1).withAttendees("Alice Pauline")
                .build();
        assertTrue(uniqueMeetingList.contains(editedMeeting1));
    }

    @Test
    public void add_nullMeeting_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueMeetingList.add(null));
    }

    @Test
    public void add_duplicateMeeting_throwsDuplicatePersonException() {
        uniqueMeetingList.add(MEETING1);
        assertThrows(DuplicateMeetingException.class, () -> uniqueMeetingList.add(MEETING1));
    }

    @Test
    public void setMeeting_nullTargetMeeting_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueMeetingList.setMeeting(null, MEETING1));
    }

    @Test
    public void setMeeting_nullEditedMeeting_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueMeetingList.setMeeting(MEETING1, null));
    }

    @Test
    public void setMeeting_targetMeetingNotInList_throwsPersonNotFoundException() {
        assertThrows(MeetingNotFoundException.class, () -> uniqueMeetingList.setMeeting(MEETING1, MEETING1));
    }

    @Test
    public void setMeeting_editedMeetingIsSameMeeting_success() {
        uniqueMeetingList.add(MEETING1);
        uniqueMeetingList.setMeeting(MEETING1, MEETING1);
        UniqueMeetingList expectedUniqueMeetingList = new UniqueMeetingList();
        expectedUniqueMeetingList.add(MEETING1);
        assertEquals(expectedUniqueMeetingList, uniqueMeetingList);
    }

    @Test
    public void setMeeting_editedMeetingHasSameIdentity_success() {
        AddressBookBuilder test = new AddressBookBuilder();
        test.withPerson(ALICE);
        uniqueMeetingList.add(MEETING1);
        Meeting editedMeeting1 = new MeetingBuilder(MEETING1).withAttendees("Alice Pauline")
                .build();
        uniqueMeetingList.setMeeting(MEETING1, editedMeeting1);
        UniqueMeetingList expectedUniqueMeetingList = new UniqueMeetingList();
        expectedUniqueMeetingList.add(editedMeeting1);
        assertEquals(expectedUniqueMeetingList, uniqueMeetingList);
    }

    @Test
    public void setMeeting_editedMeetingHasDifferentIdentity_success() {
        Meeting editedMeeting1 = new MeetingBuilder(MEETING1).withTitle("CS2103T MEETING")
                .build();
        uniqueMeetingList.add(MEETING1);
        uniqueMeetingList.setMeeting(MEETING1, editedMeeting1);
        UniqueMeetingList expectedUniqueMeetingList = new UniqueMeetingList();
        expectedUniqueMeetingList.add(editedMeeting1);
        assertEquals(expectedUniqueMeetingList, uniqueMeetingList);
    }

    @Test
    public void setMeeting_editedMeetingHasNonUniqueIdentity_throwsDuplicatePersonException() {
        uniqueMeetingList.add(MEETING1);
        Meeting editedMeeting1 = new MeetingBuilder(MEETING1).withTitle("CS2103T MEETING")
                .build();
        uniqueMeetingList.add(editedMeeting1);
        assertThrows(DuplicateMeetingException.class, () -> uniqueMeetingList.setMeeting(MEETING1, editedMeeting1));
    }

    @Test
    public void remove_nullMeeting_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueMeetingList.remove(null));
    }

    @Test
    public void remove_meetingDoesNotExist_throwsPersonNotFoundException() {
        assertThrows(MeetingNotFoundException.class, () -> uniqueMeetingList.remove(MEETING1));
    }

    @Test
    public void remove_existingMeeting_removesPerson() {
        uniqueMeetingList.add(MEETING1);
        uniqueMeetingList.remove(MEETING1);
        UniqueMeetingList expectedUniqueMeetingsList = new UniqueMeetingList();
        assertEquals(expectedUniqueMeetingsList, uniqueMeetingList);
    }

    @Test
    public void setMeetings_nullUniqueMeetingList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueMeetingList.setMeetings((UniqueMeetingList) null));
    }

    @Test
    public void setMeetings_uniqueMeetingList_replacesOwnListWithProvidedUniqueMeetingList() {
        uniqueMeetingList.add(MEETING1);
        UniqueMeetingList expectedUniqueMeetingsList = new UniqueMeetingList();
        Meeting editedMeeting1 = new MeetingBuilder(MEETING1).withTitle("CS2103T MEETING")
                .build();
        expectedUniqueMeetingsList.add(editedMeeting1);
        uniqueMeetingList.setMeetings(expectedUniqueMeetingsList);
        assertEquals(expectedUniqueMeetingsList, uniqueMeetingList);
    }

    @Test
    public void setMeetings_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueMeetingList.setMeetings((List<Meeting>) null));
    }

    @Test
    public void setMeetings_list_replacesOwnListWithProvidedList() {
        uniqueMeetingList.add(MEETING1);
        Meeting editedMeeting1 = new MeetingBuilder(MEETING1).withTitle("CS2103T MEETING")
                .build();
        List<Meeting> meetingList = Collections.singletonList(editedMeeting1);
        uniqueMeetingList.setMeetings(meetingList);
        UniqueMeetingList expectedUniqueMeetingList = new UniqueMeetingList();
        expectedUniqueMeetingList.add(editedMeeting1);
        assertEquals(expectedUniqueMeetingList, uniqueMeetingList);
    }

    @Test
    public void setMeetings_listWithDuplicateMeetings_throwsDuplicatePersonException() {
        List<Meeting> listWithDuplicateMeetings = Arrays.asList(MEETING1, MEETING1);
        assertThrows(DuplicateMeetingException.class, () -> uniqueMeetingList.setMeetings(listWithDuplicateMeetings));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
                -> uniqueMeetingList.asUnmodifiableObservableList().remove(0));
    }

    @Test
    public void toStringMethod() {
        assertEquals(uniqueMeetingList.asUnmodifiableObservableList().toString(), uniqueMeetingList.toString());
    }


}
