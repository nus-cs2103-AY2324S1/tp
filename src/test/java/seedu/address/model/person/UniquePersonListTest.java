package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalApplicants.ALICE_APPLICANT;
import static seedu.address.testutil.TypicalApplicants.BOB_APPLICANT;
import static seedu.address.testutil.TypicalMembers.ALAN_MEMBER;
import static seedu.address.testutil.TypicalMembers.BETTY_MEMBER;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.person.exceptions.PersonNotFoundException;
import seedu.address.testutil.ApplicantBuilder;
import seedu.address.testutil.MemberBuilder;

public class UniquePersonListTest {

    private final UniquePersonList<Member> uniqueMemberList = new UniquePersonList<>();
    private final UniquePersonList<Applicant> uniqueApplicantList = new UniquePersonList<>();

    @Test
    public void contains_nullMember_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueMemberList.contains(null));
    }

    @Test
    public void contains_nullApplicant_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueApplicantList.contains(null));
    }

    @Test
    public void contains_memberNotInList_returnsFalse() {
        assertFalse(uniqueMemberList.contains(ALAN_MEMBER));
    }

    @Test
    public void contains_applicantNotInList_returnsFalse() {
        assertFalse(uniqueApplicantList.contains(ALICE_APPLICANT));
    }

    @Test
    public void contains_memberInList_returnsTrue() {
        uniqueMemberList.add(ALAN_MEMBER);
        assertTrue(uniqueMemberList.contains(ALAN_MEMBER));
    }

    @Test
    public void contains_applicantInList_returnsTrue() {
        uniqueApplicantList.add(ALICE_APPLICANT);
        assertTrue(uniqueApplicantList.contains(ALICE_APPLICANT));
    }

    @Test
    public void contains_memberWithSameIdentityFieldsInList_returnsTrue() {
        uniqueMemberList.add(ALAN_MEMBER);
        Member editedAlan = new MemberBuilder(ALAN_MEMBER).withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
                .build();
        assertTrue(uniqueMemberList.contains(editedAlan));
    }

    @Test
    public void contains_applicantWithSameIdentityFieldsInList_returnsTrue() {
        uniqueApplicantList.add(ALICE_APPLICANT);
        Applicant editedAlice = new ApplicantBuilder(ALICE_APPLICANT).withName(VALID_NAME_BOB)
                .build();
        assertTrue(uniqueApplicantList.contains(editedAlice));
    }

    @Test
    public void add_nullMember_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueMemberList.add(null));
    }

    @Test
    public void add_nullApplicant_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueApplicantList.add(null));
    }

    @Test
    public void add_duplicateMember_throwsDuplicatePersonException() {
        uniqueMemberList.add(ALAN_MEMBER);
        assertThrows(DuplicatePersonException.class, () -> uniqueMemberList.add(ALAN_MEMBER));
    }

    @Test
    public void add_duplicateApplicant_throwsDuplicatePersonException() {
        uniqueApplicantList.add(ALICE_APPLICANT);
        assertThrows(DuplicatePersonException.class, () -> uniqueApplicantList.add(ALICE_APPLICANT));
    }

    @Test
    public void setMember_nullTargetMember_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueMemberList.setPerson(null, ALAN_MEMBER));
    }

    @Test
    public void setApplicant_nullTargetApplicant_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueApplicantList.setPerson(null, ALICE_APPLICANT));
    }

    @Test
    public void setMember_nullEditedMember_throwsNullPointerException() {
        // TODO: implement this
    }

    @Test
    public void setApplicant_nullEditedApplicant_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueApplicantList.setPerson(ALICE_APPLICANT, null));
    }

    @Test
    public void setMember_targetMemberNotInList_throwsPersonNotFoundException() {
        assertThrows(PersonNotFoundException.class, () -> uniqueMemberList.setPerson(ALAN_MEMBER, ALAN_MEMBER));
    }

    @Test
    public void setApplicant_targetApplicantNotInList_throwsPersonNotFoundException() {
        assertThrows(PersonNotFoundException.class, () -> uniqueApplicantList.setPerson(ALICE_APPLICANT,
                ALICE_APPLICANT));
    }

    @Test
    public void setMember_editedMemberIsSameMember_success() {
        uniqueMemberList.add(ALAN_MEMBER);
        uniqueMemberList.setPerson(ALAN_MEMBER, ALAN_MEMBER);
        UniquePersonList<Member> expectedUniqueMemberList = new UniquePersonList<>();
        expectedUniqueMemberList.add(ALAN_MEMBER);
        assertEquals(expectedUniqueMemberList, uniqueMemberList);
    }

    @Test
    public void setApplicant_editedApplicantIsSameApplicant_success() {
        uniqueApplicantList.add(ALICE_APPLICANT);
        uniqueApplicantList.setPerson(ALICE_APPLICANT, ALICE_APPLICANT);
        UniquePersonList<Applicant> expectedUniqueApplicantList = new UniquePersonList<>();
        expectedUniqueApplicantList.add(ALICE_APPLICANT);
        assertEquals(expectedUniqueApplicantList, uniqueApplicantList);
    }

    @Test
    public void setMember_editedMemberHasSameIdentity_success() {
        uniqueMemberList.add(ALAN_MEMBER);
        Member editedAlan = new MemberBuilder(ALAN_MEMBER).withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
                .build();
        uniqueMemberList.setPerson(ALAN_MEMBER, editedAlan);
        UniquePersonList<Member> expectedUniqueMemberList = new UniquePersonList<>();
        expectedUniqueMemberList.add(editedAlan);
        assertEquals(expectedUniqueMemberList, uniqueMemberList);
    }

    @Test
    public void setApplicant_editedApplicantHasSameIdentity_success() {
        uniqueApplicantList.add(ALICE_APPLICANT);
        Applicant editedAlice = new ApplicantBuilder(ALICE_APPLICANT).withName(VALID_NAME_BOB)
                .build();
        uniqueApplicantList.setPerson(ALICE_APPLICANT, editedAlice);
        UniquePersonList<Applicant> expectedUniqueApplicantList = new UniquePersonList<>();
        expectedUniqueApplicantList.add(editedAlice);
        assertEquals(expectedUniqueApplicantList, uniqueApplicantList);
    }

    @Test
    public void setMember_editedMemberHasDifferentIdentity_success() {
        uniqueMemberList.add(ALAN_MEMBER);
        uniqueMemberList.setPerson(ALAN_MEMBER, BETTY_MEMBER);
        UniquePersonList<Member> expectedUniqueMemberList = new UniquePersonList<>();
        expectedUniqueMemberList.add(BETTY_MEMBER);
        assertEquals(expectedUniqueMemberList, uniqueMemberList);
    }

    @Test
    public void setApplicant_editedApplicantHasDifferentIdentity_success() {
        uniqueApplicantList.add(ALICE_APPLICANT);
        uniqueApplicantList.setPerson(ALICE_APPLICANT, BOB_APPLICANT);
        UniquePersonList<Applicant> expectedUniqueApplicantList = new UniquePersonList<>();
        expectedUniqueApplicantList.add(BOB_APPLICANT);
        assertEquals(expectedUniqueApplicantList, uniqueApplicantList);
    }

    @Test
    public void setMember_editedMemberHasNonUniqueIdentity_throwsDuplicatePersonException() {
        uniqueMemberList.add(ALAN_MEMBER);
        uniqueMemberList.add(BETTY_MEMBER);
        assertThrows(DuplicatePersonException.class, () -> uniqueMemberList.setPerson(ALAN_MEMBER, BETTY_MEMBER));
    }

    @Test
    public void setApplicant_editedApplicantHasNonUniqueIdentity_throwsDuplicatePersonException() {
        uniqueApplicantList.add(ALICE_APPLICANT);
        uniqueApplicantList.add(BOB_APPLICANT);
        assertThrows(DuplicatePersonException.class, () -> uniqueApplicantList.setPerson(ALICE_APPLICANT,
                BOB_APPLICANT));
    }

    @Test
    public void remove_nullMember_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueMemberList.remove(null));
    }

    @Test
    public void remove_nullApplicant_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueApplicantList.remove(null));
    }

    @Test
    public void remove_memberDoesNotExist_throwsPersonNotFoundException() {
        assertThrows(PersonNotFoundException.class, () -> uniqueMemberList.remove(ALAN_MEMBER));
    }

    @Test
    public void remove_applicantDoesNotExist_throwsPersonNotFoundException() {
        assertThrows(PersonNotFoundException.class, () -> uniqueApplicantList.remove(ALICE_APPLICANT));
    }

    @Test
    public void remove_existingMember_removesMember() {
        uniqueMemberList.add(ALAN_MEMBER);
        uniqueMemberList.remove(ALAN_MEMBER);
        UniquePersonList<Member> expectedUniqueMemberList = new UniquePersonList<>();
        assertEquals(expectedUniqueMemberList, uniqueMemberList);
    }

    @Test
    public void remove_existingApplicant_removesApplicant() {
        uniqueApplicantList.add(ALICE_APPLICANT);
        uniqueApplicantList.remove(ALICE_APPLICANT);
        UniquePersonList<Applicant> expectedUniqueApplicantList = new UniquePersonList<>();
        assertEquals(expectedUniqueApplicantList, uniqueApplicantList);
    }

    @Test
    public void setMembers_nullUniqueMemberList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueMemberList.setPersons((UniquePersonList<Member>) null));
    }

    @Test
    public void setApplicants_nullUniqueApplicantList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueApplicantList.setPersons(
                (UniquePersonList<Applicant>) null));
    }

    @Test
    public void setMembers_uniqueMemberList_replacesOwnListWithProvidedUniqueMemberList() {
        uniqueMemberList.add(ALAN_MEMBER);
        UniquePersonList<Member> expectedUniqueMemberList = new UniquePersonList<>();
        expectedUniqueMemberList.add(BETTY_MEMBER);
        uniqueMemberList.setPersons(expectedUniqueMemberList);
        assertEquals(expectedUniqueMemberList, uniqueMemberList);
    }

    @Test
    public void setApplicants_uniqueApplicantList_replacesOwnListWithProvidedUniqueApplicantList() {
        uniqueApplicantList.add(ALICE_APPLICANT);
        UniquePersonList<Applicant> expectedUniqueApplicantList = new UniquePersonList<>();
        expectedUniqueApplicantList.add(BOB_APPLICANT);
        uniqueApplicantList.setPersons(expectedUniqueApplicantList);
        assertEquals(expectedUniqueApplicantList, uniqueApplicantList);
    }

    @Test
    public void setMembers_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueMemberList.setPersons((List<Member>) null));
    }

    @Test
    public void setApplicants_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueApplicantList.setPersons((List<Applicant>) null));
    }

    @Test
    public void setMembers_list_replacesOwnListWithProvidedList() {
        uniqueMemberList.add(ALAN_MEMBER);
        List<Member> memberList = Collections.singletonList(BETTY_MEMBER);
        uniqueMemberList.setPersons(memberList);
        UniquePersonList<Member> expectedUniqueMemberList = new UniquePersonList<>();
        expectedUniqueMemberList.add(BETTY_MEMBER);
        assertEquals(expectedUniqueMemberList, uniqueMemberList);
    }

    @Test
    public void setApplicants_list_replacesOwnListWithProvidedList() {
        uniqueApplicantList.add(ALICE_APPLICANT);
        List<Applicant> applicantList = Collections.singletonList(BOB_APPLICANT);
        uniqueApplicantList.setPersons(applicantList);
        UniquePersonList<Applicant> expectedUniqueApplicantList = new UniquePersonList<>();
        expectedUniqueApplicantList.add(BOB_APPLICANT);
        assertEquals(expectedUniqueApplicantList, uniqueApplicantList);
    }

    @Test
    public void setMembers_listWithDuplicateMembers_throwsDuplicatePersonException() {
        List<Member> listWithDuplicateMembers = Arrays.asList(ALAN_MEMBER, ALAN_MEMBER);
        assertThrows(DuplicatePersonException.class, () -> uniqueMemberList.setPersons(listWithDuplicateMembers));
    }

    @Test
    public void setApplicants_listWithDuplicateApplicants_throwsDuplicatePersonException() {
        List<Applicant> listWithDuplicateApplicants = Arrays.asList(ALICE_APPLICANT, ALICE_APPLICANT);
        assertThrows(DuplicatePersonException.class, () -> uniqueApplicantList.setPersons(listWithDuplicateApplicants));
    }

    @Test
    public void asUnmodifiableObservableList_modifyMemberList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
                -> uniqueMemberList.asUnmodifiableObservableList().remove(0));
    }

    @Test
    public void asUnmodifiableObservableList_modifyApplicantList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
                -> uniqueApplicantList.asUnmodifiableObservableList().remove(0));
    }

    @Test
    public void toStringMethodMember() {
        assertEquals(uniqueMemberList.asUnmodifiableObservableList().toString(), uniqueMemberList.toString());
    }

    @Test
    public void toStringMethodApplicant() {
        assertEquals(uniqueApplicantList.asUnmodifiableObservableList().toString(), uniqueApplicantList.toString());
    }
}
