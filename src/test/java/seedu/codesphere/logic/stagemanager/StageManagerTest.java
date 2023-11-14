package seedu.codesphere.logic.stagemanager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.codesphere.model.course.Course;
import seedu.codesphere.testutil.CourseBuilder;

class StageManagerTest {

    private StageManager stageManager;

    @BeforeEach
    void setUp() {
        stageManager = StageManager.getInstance();
        stageManager.setHomeStage();
    }

    @Test
    void testGetInstance() {
        assertNotNull(stageManager);
        StageManager sameInstance = StageManager.getInstance();
        assertEquals(stageManager, sameInstance, "getInstance should return the same instance");
    }

    @Test
    void testGetStage() {
        stageManager.setHomeStage();
        assertEquals(Stages.HOME, stageManager.getStage(), "Initial stage should be HOME");
    }

    @Test
    void testGetSelectedCourse() {
        stageManager.setHomeStage();
        assertNull(stageManager.getSelectedCourse(), "Current course should be null initially");
        Course course = new CourseBuilder().build();
        stageManager.setCourseStage(course);
        assertEquals(course, stageManager.getSelectedCourse(),
                "Current course should be the same as the selected course");
    }

    @Test
    void testSetCourseStage() {
        Course course = new CourseBuilder().build();
        stageManager.setCourseStage(course);
        assertEquals(Stages.SELECTED_COURSE, stageManager.getStage(),
                "Stage should be SELECTED_COURSE");
        assertEquals(course, stageManager.getSelectedCourse(),
                "Selected course should be the same as the input course");
    }

    @Test
    void testSetHomeStage() {
        stageManager.setHomeStage();
        assertEquals(Stages.HOME, stageManager.getStage(), "Stage should be HOME");
        assertNull(stageManager.getSelectedCourse(),
                "Selected course should be null after setting to HOME stage");
    }

    @Test
    void testIsSelectedCourseNull() {
        stageManager.setHomeStage();
        assertTrue(stageManager.isSelectedCourseNull(), "Selected course should be null initially");
        Course course = new CourseBuilder().build();
        stageManager.setCourseStage(course);
        assertFalse(stageManager.isSelectedCourseNull(),
                "Selected course should not be null after setting a course");
    }
}
