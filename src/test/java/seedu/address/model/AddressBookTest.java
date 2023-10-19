package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalApplicants.ALICE_APPLICANT;
import static seedu.address.testutil.TypicalApplicants.getTypicalAddressBookWithApplicants;

import java.util.Collection;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.person.Applicant;
import seedu.address.model.person.Member;
import seedu.address.testutil.ApplicantBuilder;

public class AddressBookTest {

    private final AddressBook addressBook = new AddressBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), addressBook.getMemberList());
        assertEquals(Collections.emptyList(), addressBook.getApplicantList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        // TODO: check with members also
        AddressBook newData = getTypicalAddressBookWithApplicants();
        addressBook.resetData(newData);
        assertEquals(newData, addressBook);
    }

    //    @Test
    //    public void resetData_withDuplicatePersons_throwsDuplicatePersonException() {
    //        // Two persons with the same identity fields
    //        Person editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
    //                .build();
    //        List<Person> newPersons = Arrays.asList(ALICE, editedAlice);
    //        AddressBookStub newData = new AddressBookStub(newPersons);
    //
    //        assertThrows(DuplicatePersonException.class, () -> addressBook.resetData(newData));
    //    }

    @Test
    public void resetData_withDuplicateMembers_throwsDuplicatePersonException() {
        // TODO: implement this
    }

    @Test
    public void resetData_withDuplicateApplicants_throwsDuplicatePersonException() {
        // Two persons with the same identity fields
        // TODO: need to pass in newMembers into AddressBookStub
        //        Applicant editedAlice = new ApplicantBuilder(ALICE_APPLICANT).withName(VALID_NAME_BOB)
        //                .build();
        //        List<Applicant> newApplicants = Arrays.asList(ALICE_APPLICANT, editedAlice);
        //        AddressBookStub newData = new AddressBookStub(newMembers, newApplicants);
        //
        //        assertThrows(DuplicatePersonException.class, () -> addressBook.resetData(newData));
    }

    @Test
    public void hasMember_nullMember_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasMember(null));
    }

    @Test
    public void hasApplicant_nullApplicant_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasApplicant(null));
    }

    //    @Test
    //    public void hasPerson_personNotInAddressBook_returnsFalse() {
    //        assertFalse(addressBook.hasPerson(ALICE));
    //    }

    @Test
    public void hasMember_memberNotInAddressBook_returnsFalse() {
        // TODO: implement this
    }

    @Test
    public void hasApplicant_applicantNotInAddressBook_returnsFalse() {
        assertFalse(addressBook.hasApplicant(ALICE_APPLICANT));
    }

    //    @Test
    //    public void hasPerson_personInAddressBook_returnsTrue() {
    //        addressBook.addPerson(ALICE);
    //        assertTrue(addressBook.hasPerson(ALICE));
    //    }

    @Test
    public void hasMember_memberInAddressBook_returnsTrue() {
        // TODO: implement this
    }

    @Test
    public void hasPerson_personInAddressBook_returnsTrue() {
        addressBook.addApplicant(ALICE_APPLICANT);
        assertTrue(addressBook.hasApplicant(ALICE_APPLICANT));
    }

    //    @Test
    //    public void hasPerson_personWithSameIdentityFieldsInAddressBook_returnsTrue() {
    //        addressBook.addPerson(ALICE);
    //        Person editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
    //                .build();
    //        assertTrue(addressBook.hasPerson(editedAlice));
    //    }

    @Test
    public void hasMember_memberWithSameIdentityFieldsInAddressBook_returnsTrue() {
        // TODO: implement this
    }

    @Test
    public void hasApplicant_applicantWithSameIdentityFieldsInAddressBook_returnsTrue() {
        addressBook.addApplicant(ALICE_APPLICANT);
        Applicant editedAlice = new ApplicantBuilder(ALICE_APPLICANT).withName(VALID_NAME_BOB)
                .build();
        assertTrue(addressBook.hasApplicant(editedAlice));
    }

    //    @Test
    //    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
    //        assertThrows(UnsupportedOperationException.class, () -> addressBook.getPersonList().remove(0));
    //    }

    @Test
    public void getMemberList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> addressBook.getMemberList().remove(0));
    }

    @Test
    public void getApplicantList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> addressBook.getApplicantList().remove(0));
    }

    @Test
    public void toStringMethod() {
        String expected = AddressBook.class.getCanonicalName()
                + "{members=" + addressBook.getMemberList() + ", "
                + "applicants=" + addressBook.getApplicantList() + "}";

        assertEquals(expected, addressBook.toString());
    }

    /**
     * A stub ReadOnlyAddressBook whose persons list can violate interface constraints.
     */
    private class AddressBookStub implements ReadOnlyAddressBook {
        private final ObservableList<Member> memberList = FXCollections.observableArrayList();
        private final ObservableList<Applicant> applicants = FXCollections.observableArrayList();

        AddressBookStub(Collection<Member> members, Collection<Applicant> applicants) {
            this.memberList.setAll(members);
            this.applicants.setAll(applicants);
        }

        @Override
        public ObservableList<Member> getMemberList() {
            return memberList;
        }

        @Override
        public ObservableList<Applicant> getApplicantList() {
            return applicants;
        }
    }

}
