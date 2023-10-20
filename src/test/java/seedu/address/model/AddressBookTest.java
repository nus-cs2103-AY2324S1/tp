package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalApplicants.ALICE_APPLICANT;
import static seedu.address.testutil.TypicalMembers.ALAN_MEMBER;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBookWithMembersApplicants;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.person.Applicant;
import seedu.address.model.person.Member;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.testutil.ApplicantBuilder;
import seedu.address.testutil.MemberBuilder;

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
        AddressBook newData = getTypicalAddressBookWithMembersApplicants();
        addressBook.resetData(newData);
        assertEquals(newData, addressBook);
    }

    @Test
    public void resetData_withDuplicatePersons_throwsDuplicatePersonException() {
        // Two applicants with the same identity fields
        Applicant editedAlice = new ApplicantBuilder(ALICE_APPLICANT).withName(VALID_NAME_BOB)
                .build();
        List<Applicant> newApplicants = Arrays.asList(ALICE_APPLICANT, editedAlice);

        // Two members with the same identity fields
        Member editedAlan = new MemberBuilder(ALAN_MEMBER).withName(VALID_NAME_BOB)
                .build();
        List<Member> newMembers = Arrays.asList(ALAN_MEMBER, editedAlan);

        AddressBookStub newData = new AddressBookStub(newMembers, newApplicants);
        assertThrows(DuplicatePersonException.class, () -> addressBook.resetData(newData));
    }

    @Test
    public void hasMember_nullMember_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasMember(null));
    }

    @Test
    public void hasApplicant_nullApplicant_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasApplicant(null));
    }

    @Test
    public void hasMember_memberNotInAddressBook_returnsFalse() {
        assertFalse(addressBook.hasMember(ALAN_MEMBER));
    }

    @Test
    public void hasApplicant_applicantNotInAddressBook_returnsFalse() {
        assertFalse(addressBook.hasApplicant(ALICE_APPLICANT));
    }


    @Test
    public void hasMember_memberInAddressBook_returnsTrue() {
        addressBook.addMember(ALAN_MEMBER);
        assertTrue(addressBook.hasMember(ALAN_MEMBER));
    }

    @Test
    public void hasPerson_personInAddressBook_returnsTrue() {
        addressBook.addApplicant(ALICE_APPLICANT);
        assertTrue(addressBook.hasApplicant(ALICE_APPLICANT));
    }

    @Test
    public void hasMember_memberWithSameIdentityFieldsInAddressBook_returnsTrue() {
        addressBook.addMember(ALAN_MEMBER);
        Member editedAlan = new MemberBuilder(ALAN_MEMBER).withName(VALID_NAME_BOB)
                .build();
        assertTrue(addressBook.hasMember(editedAlan));
    }

    @Test
    public void hasApplicant_applicantWithSameIdentityFieldsInAddressBook_returnsTrue() {
        addressBook.addApplicant(ALICE_APPLICANT);
        Applicant editedAlice = new ApplicantBuilder(ALICE_APPLICANT).withName(VALID_NAME_BOB)
                .build();
        assertTrue(addressBook.hasApplicant(editedAlice));
    }

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
