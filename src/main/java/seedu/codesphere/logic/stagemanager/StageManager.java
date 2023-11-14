//@@author devanshubisht
// Solution below adapted from github.com/AY2223S1-CS2103T-W11-4/tp
package seedu.codesphere.logic.stagemanager;

import static java.util.Objects.requireNonNull;

import seedu.codesphere.model.course.Course;

/**
 * This Singleton class manages the stages of the application.
 */
public class StageManager {
    private static StageManager singleInstance = null;
    private Stages currentStage = Stages.HOME;
    private Course selectedCourse = null;

    private StageManager() {
    }

    /**
     * Returns the single instance of StageManager.
     *
     * @return The single instance of StageManager.
     */
    public static StageManager getInstance() {
        if (singleInstance == null) {
            singleInstance = new StageManager();
            return singleInstance;
        }
        return singleInstance;
    }

    /**
     * Returns the current stage of the application.
     *
     * @return The current stage of the application.
     */
    public Stages getStage() {
        return currentStage;
    }

    /**
     * Gets the selected course.
     *
     * @return The selected course application is at.
     */
    public Course getSelectedCourse() {
        return selectedCourse;
    }

    /**
     * Sets the selected course which user selected.
     *
     * @param selectedCourse The course which user selected.
     */
    public void setCourseStage(Course selectedCourse) {
        requireNonNull(selectedCourse);
        currentStage = Stages.SELECTED_COURSE;
        this.selectedCourse = selectedCourse;
    }

    /**
     * Sets the home stage.
     */
    public void setHomeStage() {
        currentStage = Stages.HOME;
        selectedCourse = null;
    }

    /**
     * Checks if the selected course is null.
     *
     * @return True if the selected course is null, false otherwise.
     */
    public boolean isSelectedCourseNull() {
        return selectedCourse == null;
    }
}
