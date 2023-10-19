package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalApplicants.ALICE_APPLICANT;
import static seedu.address.testutil.TypicalApplicants.BOB_APPLICANT;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.person.exceptions.PersonNotFoundException;
import seedu.address.testutil.ApplicantBuilder;

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

    //    @Test
    //    public void contains_personNotInList_returnsFalse() {
    //        assertFalse(uniquePersonList.contains(ALICE));
    //    }

    @Test
    public void contains_memberNotInList_returnsFalse() {
        // TODO: implement this
    }

    @Test
    public void contains_applicantNotInList_returnsFalse() {
        assertFalse(uniqueApplicantList.contains(ALICE_APPLICANT));
    }

    //    @Test
    //    public void contains_personInList_returnsTrue() {
    //        uniquePersonList.add(ALICE);
    //        assertTrue(uniquePersonList.contains(ALICE));
    //    }

    @Test
    public void contains_memberInList_returnsTrue() {
        // TODO: implement this
    }

    @Test
    public void contains_applicantInList_returnsTrue() {
        uniqueApplicantList.add(ALICE_APPLICANT);
        assertTrue(uniqueApplicantList.contains(ALICE_APPLICANT));
    }

    //    @Test
    //    public void contains_personWithSameIdentityFieldsInList_returnsTrue() {
    //        uniquePersonList.add(ALICE);
    //        Person editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
    //                .build();
    //        assertTrue(uniquePersonList.contains(editedAlice));
    //    }

    @Test
    public void contains_memberWithSameIdentityFieldsInList_returnsTrue() {
        // TODO: implement this
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

    //    @Test
    //    public void add_duplicatePerson_throwsDuplicatePersonException() {
    //        uniquePersonList.add(ALICE);
    //        assertThrows(DuplicatePersonException.class, () -> uniquePersonList.add(ALICE));
    //    }

    @Test
    public void add_duplicateMember_throwsDuplicatePersonException() {
        // TODO: implement this
    }

    @Test
    public void add_duplicateApplicant_throwsDuplicatePersonException() {
        uniqueApplicantList.add(ALICE_APPLICANT);
        assertThrows(DuplicatePersonException.class, () -> uniqueApplicantList.add(ALICE_APPLICANT));
    }

    //    @Test
    //    public void setPerson_nullTargetPerson_throwsNullPointerException() {
    //        assertThrows(NullPointerException.class, () -> uniquePersonList.setPerson(null, ALICE));
    //    }

    @Test
    public void setMember_nullTargetMember_throwsNullPointerException() {
        // TODO: implement this
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

    //    @Test
    //    public void setPerson_targetPersonNotInList_throwsPersonNotFoundException() {
    //        assertThrows(PersonNotFoundException.class, () -> uniquePersonList.setPerson(ALICE, ALICE));
    //    }

    @Test
    public void setMember_targetMemberNotInList_throwsPersonNotFoundException() {
        // TODO: implement this
    }

    @Test
    public void setApplicant_targetApplicantNotInList_throwsPersonNotFoundException() {
        assertThrows(PersonNotFoundException.class, () -> uniqueApplicantList.setPerson(ALICE_APPLICANT,
                ALICE_APPLICANT));
    }

    //    @Test
    //    public void setPerson_editedPersonIsSamePerson_success() {
    //        uniquePersonList.add(ALICE);
    //        uniquePersonList.setPerson(ALICE, ALICE);
    //        UniquePersonList<Person> expectedUniquePersonList = new UniquePersonList<>();
    //        expectedUniquePersonList.add(ALICE);
    //        assertEquals(expectedUniquePersonList, uniquePersonList);
    //    }

    @Test
    public void setMember_editedMemberIsSameMember_success() {
        // TODO: implement this
    }

    @Test
    public void setApplicant_editedApplicantIsSameApplicant_success() {
        uniqueApplicantList.add(ALICE_APPLICANT);
        uniqueApplicantList.setPerson(ALICE_APPLICANT, ALICE_APPLICANT);
        UniquePersonList<Applicant> expectedUniqueApplicantList = new UniquePersonList<>();
        expectedUniqueApplicantList.add(ALICE_APPLICANT);
        assertEquals(expectedUniqueApplicantList, uniqueApplicantList);
    }

    //    @Test
    //    public void setPerson_editedPersonHasSameIdentity_success() {
    //        uniquePersonList.add(ALICE);
    //        Person editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
    //                .build();
    //        uniquePersonList.setPerson(ALICE, editedAlice);
    //        UniquePersonList<Person> expectedUniquePersonList = new UniquePersonList<>();
    //        expectedUniquePersonList.add(editedAlice);
    //        assertEquals(expectedUniquePersonList, uniquePersonList);
    //    }

    @Test
    public void setMember_editedMemberHasSameIdentity_success() {
        // TODO: implement this
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

    //    @Test
    //    public void setPerson_editedPersonHasDifferentIdentity_success() {
    //        uniquePersonList.add(ALICE);
    //        uniquePersonList.setPerson(ALICE, BOB);
    //        UniquePersonList<Person> expectedUniquePersonList = new UniquePersonList<>();
    //        expectedUniquePersonList.add(BOB);
    //        assertEquals(expectedUniquePersonList, uniquePersonList);
    //    }

    @Test
    public void setMember_editedMemberHasDifferentIdentity_success() {
        // TODO: implement this
    }

    @Test
    public void setApplicant_editedApplicantHasDifferentIdentity_success() {
        uniqueApplicantList.add(ALICE_APPLICANT);
        uniqueApplicantList.setPerson(ALICE_APPLICANT, BOB_APPLICANT);
        UniquePersonList<Applicant> expectedUniqueApplicantList = new UniquePersonList<>();
        expectedUniqueApplicantList.add(BOB_APPLICANT);
        assertEquals(expectedUniqueApplicantList, uniqueApplicantList);
    }

    //    @Test
    //    public void setPerson_editedPersonHasNonUniqueIdentity_throwsDuplicatePersonException() {
    //        uniquePersonList.add(ALICE);
    //        uniquePersonList.add(BOB);
    //        assertThrows(DuplicatePersonException.class, () -> uniquePersonList.setPerson(ALICE, BOB));
    //    }

    @Test
    public void setMember_editedMemberHasNonUniqueIdentity_throwsDuplicatePersonException() {
        // TODO: implement this
    }

    @Test
    public void setApplicant_editedApplicantHasNonUniqueIdentity_throwsDuplicatePersonException() {
        uniqueApplicantList.add(ALICE_APPLICANT);
        uniqueApplicantList.add(BOB_APPLICANT);
        assertThrows(DuplicatePersonException.class, () -> uniqueApplicantList.setPerson(ALICE_APPLICANT,
                BOB_APPLICANT));
    }

    //    @Test
    //    public void remove_nullPerson_throwsNullPointerException() {
    //        assertThrows(NullPointerException.class, () -> uniquePersonList.remove(null));
    //    }

    @Test
    public void remove_nullMember_throwsNullPointerException() {
        // TODO: implement this
    }

    @Test
    public void remove_nullApplicant_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueApplicantList.remove(null));
    }

    //    @Test
    //    public void remove_personDoesNotExist_throwsPersonNotFoundException() {
    //        assertThrows(PersonNotFoundException.class, () -> uniquePersonList.remove(ALICE));
    //    }

    @Test
    public void remove_memberDoesNotExist_throwsPersonNotFoundException() {
        // TODO: implement this
    }

    @Test
    public void remove_applicantDoesNotExist_throwsPersonNotFoundException() {
        assertThrows(PersonNotFoundException.class, () -> uniqueApplicantList.remove(ALICE_APPLICANT));
    }

    //    @Test
    //    public void remove_existingPerson_removesPerson() {
    //        uniquePersonList.add(ALICE);
    //        uniquePersonList.remove(ALICE);
    //        UniquePersonList<Person> expectedUniquePersonList = new UniquePersonList<>();
    //        assertEquals(expectedUniquePersonList, uniquePersonList);
    //    }

    @Test
    public void remove_existingMember_removesMember() {
        // TODO: implement this
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

    //    @Test
    //    public void setPersons_uniquePersonList_replacesOwnListWithProvidedUniquePersonList() {
    //        uniquePersonList.add(ALICE);
    //        UniquePersonList<Person> expectedUniquePersonList = new UniquePersonList<>();
    //        expectedUniquePersonList.add(BOB);
    //        uniquePersonList.setPersons(expectedUniquePersonList);
    //        assertEquals(expectedUniquePersonList, uniquePersonList);
    //    }

    @Test
    public void setMembers_uniqueMemberList_replacesOwnListWithProvidedUniqueMemberList() {
        // TODO: implement this
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

    //    @Test
    //    public void setPersons_list_replacesOwnListWithProvidedList() {
    //        uniquePersonList.add(ALICE);
    //        List<Person> personList = Collections.singletonList(BOB);
    //        uniquePersonList.setPersons(personList);
    //        UniquePersonList<Person> expectedUniquePersonList = new UniquePersonList<>();
    //        expectedUniquePersonList.add(BOB);
    //        assertEquals(expectedUniquePersonList, uniquePersonList);
    //    }

    @Test
    public void setMembers_list_replacesOwnListWithProvidedList() {
        // TODO: implement this
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

    //    @Test
    //    public void setPersons_listWithDuplicatePersons_throwsDuplicatePersonException() {
    //        List<Person> listWithDuplicatePersons = Arrays.asList(ALICE, ALICE);
    //        assertThrows(DuplicatePersonException.class, () -> uniquePersonList.setPersons(listWithDuplicatePersons));
    //    }

    @Test
    public void setMembers_listWithDuplicateMembers_throwsDuplicatePersonException() {
        // TODO: implement this
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
