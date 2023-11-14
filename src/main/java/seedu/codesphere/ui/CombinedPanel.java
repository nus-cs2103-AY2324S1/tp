//@@author phiphi-tan
package seedu.codesphere.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import seedu.codesphere.model.course.Course;
import seedu.codesphere.model.student.Student;

/**
 * CombinedPanel containing a side panel of Courses and a main panel of Students
 */
public class CombinedPanel extends UiComponent<Region> {
    private static final String FXML = "CombinedPanel.fxml";

    private StudentListPanel studentListPanel;
    private CourseListPanel courseListPanel;

    @FXML
    private StackPane studentListPlaceholder;
    @FXML
    private StackPane courseListPlaceholder;

    /**
     * Creates a {@code CombinedPanel} with two of the given {@code ObservableList}.
     */
    public CombinedPanel(ObservableList<Course> courseList,
                         ObservableList<Student> studentList) {
        super(FXML);

        courseListPanel = new CourseListPanel(courseList);
        courseListPlaceholder.getChildren().add(courseListPanel.getRoot());

        studentListPanel = new StudentListPanel(studentList);
        studentListPlaceholder.getChildren().add(studentListPanel.getRoot());

    }


}
