package seedu.address.model.partnercourse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalObjects.COMP1000;
import static seedu.address.testutil.TypicalObjects.COMP2000;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.partnercourse.exceptions.DuplicatePartnerCourseException;
import seedu.address.model.partnercourse.exceptions.PartnerCourseNotFoundException;
import seedu.address.testutil.PartnerCourseBuilder;

public class UniquePartnerCourseListTest {

    private final UniquePartnerCourseList uniquePartnerCourseList = new UniquePartnerCourseList();

    @Test
    public void contains_nullPartnerCourse_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePartnerCourseList.contains(null));
    }

    @Test
    public void contains_partnerCourseNotInList_returnsFalse() {
        assertFalse(uniquePartnerCourseList.contains(COMP1000));
    }

    @Test
    public void contains_partnerCourseInList_returnTrue() {
        uniquePartnerCourseList.add(COMP1000);
        assertTrue(uniquePartnerCourseList.contains(COMP1000));
    }

    @Test
    public void contains_partnerCourseWithSameIdentityFieldsInList_returnsTrue() {
        uniquePartnerCourseList.add(COMP2000);
        PartnerCourse partnerCourseWithSameIdentityFields =
                new PartnerCourseBuilder(COMP1000)
                        .withPartnerCode(COMP2000.getPartnerCode().getValue())
                        .withPartnerUniversity(COMP2000.getPartnerUniversity().getUniversityName())
                        .build();
        assertTrue(uniquePartnerCourseList.contains(partnerCourseWithSameIdentityFields));
    }

    @Test
    public void contains_partnerCourseWithSamePartnerCodeDifferentUniversityNameInList_returnsFalse() {
        uniquePartnerCourseList.add(COMP2000);
        PartnerCourse partnerCourseWithSamePartnerCodeDifferentUniversityName =
                new PartnerCourseBuilder(COMP2000)
                        .withPartnerCode(COMP2000.getPartnerCode().getValue())
                        .withPartnerUniversity(COMP1000.getPartnerUniversity().getUniversityName())
                        .build();
        assertFalse(uniquePartnerCourseList.contains(partnerCourseWithSamePartnerCodeDifferentUniversityName));
    }

    @Test
    public void contains_partnerCourseWithDifferentPartnerCodeSameUniversityNameInList_returnsFalse() {
        uniquePartnerCourseList.add(COMP2000);
        PartnerCourse partnerCourseWithDifferentPartnerCodeSameUniversityName =
                new PartnerCourseBuilder(COMP2000)
                        .withPartnerCode(COMP1000.getPartnerCode().getValue())
                        .withPartnerUniversity(COMP2000.getPartnerUniversity().getUniversityName())
                        .build();
        assertFalse(uniquePartnerCourseList.contains(partnerCourseWithDifferentPartnerCodeSameUniversityName));
    }

    @Test
    public void add_nullPartnerCourse_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePartnerCourseList.add(null));
    }

    @Test
    public void add_duplicatePartnerCourse_throwsDuplicatePartnerCourseException() {
        uniquePartnerCourseList.add(COMP1000);
        assertThrows(DuplicatePartnerCourseException.class, () -> uniquePartnerCourseList.add(COMP1000));
    }

    @Test
    public void setPartnerCourse_nullTargetPartnerCourse_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePartnerCourseList.setPartnerCourse(null, COMP1000));
    }

    @Test
    public void setPartnerCourse_nullEditedPartnerCourse_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                uniquePartnerCourseList.setPartnerCourse(COMP1000, null));
    }

    @Test
    public void setPartnerCourse_targetPartnerCourseNotInList_throwsPartnerCourseNotFoundException() {
        assertThrows(PartnerCourseNotFoundException.class, () ->
                uniquePartnerCourseList.setPartnerCourse(COMP1000, COMP1000));
    }

    @Test
    public void setPartnerCourse_editedPartnerCourseIsSamePartnerCourse_success() {
        uniquePartnerCourseList.add(COMP1000);
        uniquePartnerCourseList.setPartnerCourse(COMP1000, COMP1000);
        UniquePartnerCourseList expectedUniquePartnerCourseList = new UniquePartnerCourseList();
        expectedUniquePartnerCourseList.add(COMP1000);
        assertEquals(expectedUniquePartnerCourseList, uniquePartnerCourseList);
    }

    @Test
    public void setPartnerCourse_editedPartnerCourseHasSameIdentity_success() {
        uniquePartnerCourseList.add(COMP1000);
        PartnerCourse editedComp1000 =
                new PartnerCourseBuilder(COMP1000).withPartnerName("Computer things").build();

        uniquePartnerCourseList.setPartnerCourse(COMP1000, editedComp1000);

        UniquePartnerCourseList expectedUniquePartnerCourseList = new UniquePartnerCourseList();
        expectedUniquePartnerCourseList.add(editedComp1000);
        assertEquals(expectedUniquePartnerCourseList, uniquePartnerCourseList);
    }

    @Test
    public void setPartnerCourse_editedPartnerCourseHasDifferentIdentity_success() {
        uniquePartnerCourseList.add(COMP1000);
        uniquePartnerCourseList.setPartnerCourse(COMP1000, COMP2000);
        UniquePartnerCourseList expectedUniquePartnerCourseList = new UniquePartnerCourseList();
        expectedUniquePartnerCourseList.add(COMP2000);
        assertEquals(expectedUniquePartnerCourseList, uniquePartnerCourseList);
    }

    @Test
    public void setPartnerCourse_editedPartnerCourseHasNonUniqueIdentity_throwsDuplicatePartnerCourseException() {
        uniquePartnerCourseList.add(COMP1000);
        uniquePartnerCourseList.add(COMP2000);
        assertThrows(DuplicatePartnerCourseException.class, () ->
                uniquePartnerCourseList.setPartnerCourse(COMP1000, COMP2000));
    }

    // Tests remove

    @Test
    public void remove_nullPartnerCourse_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePartnerCourseList.remove(null));
    }

    @Test
    public void remove_partnerCourseDoesNotExist_throwsPartnerCourseNotFoundException() {
        assertThrows(PartnerCourseNotFoundException.class, () -> uniquePartnerCourseList.remove(COMP1000));
    }

    @Test
    public void remove_existingPartnerCourse_removesPartnerCourse() {
        uniquePartnerCourseList.add(COMP1000);
        uniquePartnerCourseList.remove(COMP1000);
        UniquePartnerCourseList expectedUniquePartnerCourseList = new UniquePartnerCourseList();
        assertEquals(expectedUniquePartnerCourseList, uniquePartnerCourseList);
    }

    // Tests setPartnerCourses

    @Test
    public void setPartnerCourses_nullUniquePartnerCourseList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                uniquePartnerCourseList.setPartnerCourses((UniquePartnerCourseList) null));
    }

    @Test
    public void setPartnerCourses_uniquePartnerCourseList_replacesOwnListWithProvidedUniquePartnerCourseList() {
        uniquePartnerCourseList.add(COMP1000);
        UniquePartnerCourseList expectedUniquePartnerCourseList = new UniquePartnerCourseList();
        expectedUniquePartnerCourseList.add(COMP2000);
        uniquePartnerCourseList.setPartnerCourses(expectedUniquePartnerCourseList);
        assertEquals(expectedUniquePartnerCourseList, uniquePartnerCourseList);
    }

    @Test
    public void setPartnerCourses_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                uniquePartnerCourseList.setPartnerCourses((List<PartnerCourse>) null));
    }

    @Test
    public void setPartnerCourses_list_replacesOwnListWithProvidedList() {
        uniquePartnerCourseList.add(COMP1000);
        List<PartnerCourse> partnerCourseList = Collections.singletonList(COMP2000);
        uniquePartnerCourseList.setPartnerCourses(partnerCourseList);
        UniquePartnerCourseList expectedUniquePartnerCourseList = new UniquePartnerCourseList();
        expectedUniquePartnerCourseList.add(COMP2000);
        assertEquals(expectedUniquePartnerCourseList, uniquePartnerCourseList);
    }

    @Test
    public void setPartnerCourses_listWithDuplicatePartnerCourses_throwsDuplicatePartnerCourseException() {
        List<PartnerCourse> listWithDuplicatePartnerCourses = Arrays.asList(COMP1000, COMP1000);
        assertThrows(DuplicatePartnerCourseException.class, () -> uniquePartnerCourseList.setPartnerCourses(
                listWithDuplicatePartnerCourses));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
                -> uniquePartnerCourseList.asUnmodifiableObservableList().remove(0));
    }

    @Test
    public void toStringMethod() {
        assertEquals(uniquePartnerCourseList.asUnmodifiableObservableList().toString(),
                uniquePartnerCourseList.toString());
    }
}
