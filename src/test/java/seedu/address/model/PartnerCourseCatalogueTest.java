package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalObjects.COMP1000;
import static seedu.address.testutil.TypicalObjects.TYPICAL_PARTNER_COURSE_NAME;
import static seedu.address.testutil.TypicalObjects.getTypicalPartnerCourseCatalogue;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.partnercourse.PartnerCourse;
import seedu.address.model.partnercourse.exceptions.DuplicatePartnerCourseException;
import seedu.address.testutil.PartnerCourseBuilder;

public class PartnerCourseCatalogueTest {
    private final PartnerCourseCatalogue partnerCourseCatalogue = new PartnerCourseCatalogue();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), partnerCourseCatalogue.getPartnerCourseList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> partnerCourseCatalogue.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyPartnerCourseCatalogue_replacesData() {
        PartnerCourseCatalogue newData = getTypicalPartnerCourseCatalogue();
        partnerCourseCatalogue.resetData(newData);
        assertEquals(newData, partnerCourseCatalogue);
    }

    @Test
    public void resetData_withDuplicatePartnerCourses_throwsDuplicatePartnerCourseException() {
        // Two partner courses with the same identity fields
        PartnerCourse editedComp1000 =
                new PartnerCourseBuilder(COMP1000).withPartnerName(TYPICAL_PARTNER_COURSE_NAME).build();
        List<PartnerCourse> newPartnerCourses = Arrays.asList(COMP1000, editedComp1000);
        PartnerCourseCatalogueStub newData = new PartnerCourseCatalogueStub(newPartnerCourses);

        assertThrows(DuplicatePartnerCourseException.class, () -> partnerCourseCatalogue.resetData(newData));
    }

    @Test
    public void hasPartnerCourse_nullPartnerCourse_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> partnerCourseCatalogue.hasPartnerCourse(null));
    }

    @Test
    public void hasPartnerCourse_partnerCourseNotInPartnerCourseCatalogue_returnsFalse() {
        assertFalse(partnerCourseCatalogue.hasPartnerCourse(COMP1000));
    }

    @Test
    public void hasPartnerCourse_partnerCourseInPartnerCourseCatalogue_returnsTrue() {
        partnerCourseCatalogue.addPartnerCourse(COMP1000);
        assertTrue(partnerCourseCatalogue.hasPartnerCourse(COMP1000));
    }

    @Test
    public void hasPartnerCourse_partnerCourseWithSameIdentityFieldsInPartnerCourseCatalogue_returnsTrue() {
        partnerCourseCatalogue.addPartnerCourse(COMP1000);
        PartnerCourse editedComp1000 =
                new PartnerCourseBuilder(COMP1000).withPartnerName(TYPICAL_PARTNER_COURSE_NAME).build();
        assertTrue(partnerCourseCatalogue.hasPartnerCourse(editedComp1000));
    }

    @Test
    public void getPartnerCourseIfExists_partnerCourseExists_returnsPartnerCourse() {
        partnerCourseCatalogue.addPartnerCourse(COMP1000);
        assertEquals(COMP1000,
                partnerCourseCatalogue
                        .getPartnerCourseIfExists(COMP1000.getPartnerCode(),
                                COMP1000.getPartnerUniversity().getUniversityName()).get());
    }

    @Test
    public void getPartnerCourseIfExists_partnerCourseNotExists_returnsEmpty() {
        assertEquals(
                Optional.empty(),
                partnerCourseCatalogue
                        .getPartnerCourseIfExists(COMP1000.getPartnerCode(),
                                COMP1000.getPartnerUniversity().getUniversityName()));
    }

    @Test
    public void getPartnerCourseList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () ->
                partnerCourseCatalogue.getPartnerCourseList().remove(0));
    }

    @Test
    public void toStringMethod() {
        String expected = PartnerCourseCatalogue.class.getCanonicalName() + "{partnercourses="
                + partnerCourseCatalogue.getPartnerCourseList() + "}";
        assertEquals(expected, partnerCourseCatalogue.toString());
    }

    /**
     * A stub ReadOnlyPartnerCourseCatalogue whose partner course list can violate interface constraints.
     */
    private static class PartnerCourseCatalogueStub implements ReadOnlyPartnerCourseCatalogue {
        private final ObservableList<PartnerCourse> partnerCourses = FXCollections.observableArrayList();

        PartnerCourseCatalogueStub(Collection<PartnerCourse> courses) {
            partnerCourses.setAll(courses);
        }

        @Override
        public ObservableList<PartnerCourse> getPartnerCourseList() {
            return partnerCourses;
        }
    }
}
