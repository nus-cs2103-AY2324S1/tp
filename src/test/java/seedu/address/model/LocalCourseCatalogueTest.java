package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalObjects.CS2030S;
import static seedu.address.testutil.TypicalObjects.CS2040S;
import static seedu.address.testutil.TypicalObjects.CS3230;
import static seedu.address.testutil.TypicalObjects.TYPICAL_LOCAL_COURSE_NAME;
import static seedu.address.testutil.TypicalObjects.getTypicalLocalCourseCatalogue;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.localcourse.LocalCourse;
import seedu.address.model.localcourse.exceptions.DuplicateLocalCourseException;
import seedu.address.testutil.LocalCourseBuilder;

public class LocalCourseCatalogueTest {

    private final LocalCourseCatalogue localCourseCatalogue = new LocalCourseCatalogue();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), localCourseCatalogue.getLocalCourseList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> localCourseCatalogue.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyLocalCourseCatalogue_replacesData() {
        LocalCourseCatalogue newData = getTypicalLocalCourseCatalogue();
        localCourseCatalogue.resetData(newData);
        assertEquals(newData, localCourseCatalogue);
    }

    @Test
    public void resetData_withDuplicateLocalCourses_throwsDuplicateLocalCourseException() {
        // Two local courses with the same identity fields
        LocalCourse editedCS2030S =
                new LocalCourseBuilder(CS2030S).withLocalName(TYPICAL_LOCAL_COURSE_NAME).build();
        List<LocalCourse> newLocalCourses = Arrays.asList(CS2030S, editedCS2030S);
        LocalCourseCatalogueStub newData = new LocalCourseCatalogueStub(newLocalCourses);

        assertThrows(DuplicateLocalCourseException.class, () -> localCourseCatalogue.resetData(newData));
    }

    @Test
    public void hasLocalCourse_nullLocalCourse_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> localCourseCatalogue.hasLocalCourse(null));
    }

    @Test
    public void hasLocalCourse_localCourseNotInLocalCourseCatalogue_returnsFalse() {
        assertFalse(localCourseCatalogue.hasLocalCourse(CS2040S));
    }

    @Test
    public void hasLocalCourse_localCourseInLocalCourseCatalogue_returnsTrue() {
        localCourseCatalogue.addLocalCourse(CS2040S);
        assertTrue(localCourseCatalogue.hasLocalCourse(CS2040S));
    }

    @Test
    public void hasLocalCourse_localCourseWithSameIdentityFieldsInLocalCourseCatalogue_returnsTrue() {
        localCourseCatalogue.addLocalCourse(CS3230);
        LocalCourse editedCS3230 =
                new LocalCourseBuilder(CS3230).withLocalName(TYPICAL_LOCAL_COURSE_NAME).build();
        assertTrue(localCourseCatalogue.hasLocalCourse(editedCS3230));
    }

    @Test
    public void getLocalCourseIfExists_localCourseExists_returnsLocalCourse() {
        localCourseCatalogue.addLocalCourse(CS3230);
        assertEquals(CS3230, localCourseCatalogue.getLocalCourseIfExists(CS3230.getLocalCode()).get());
    }

    @Test
    public void getLocalCourseIfExists_localCourseNotExists_returnsEmpty() {
        assertEquals(Optional.empty(), localCourseCatalogue.getLocalCourseIfExists(CS3230.getLocalCode()));
    }

    @Test
    public void getLocalCourseList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () ->
                localCourseCatalogue.getLocalCourseList().remove(0));
    }

    @Test
    public void toStringMethod() {
        String expected = LocalCourseCatalogue.class.getCanonicalName() + "{localcourses="
                + localCourseCatalogue.getLocalCourseList() + "}";
        assertEquals(expected, localCourseCatalogue.toString());
    }

    /**
     * A stub ReadOnlyLocalCourseCatalogue whose local course list can violate interface constraints.
     */
    private static class LocalCourseCatalogueStub implements ReadOnlyLocalCourseCatalogue {
        private final ObservableList<LocalCourse> localCourses = FXCollections.observableArrayList();

        LocalCourseCatalogueStub(Collection<LocalCourse> courses) {
            localCourses.setAll(courses);
        }

        @Override
        public ObservableList<LocalCourse> getLocalCourseList() {
            return localCourses;
        }
    }

}
