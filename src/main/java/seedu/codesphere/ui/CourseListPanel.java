//@@author phiphi-tan
package seedu.codesphere.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.codesphere.commons.core.LogsCenter;
import seedu.codesphere.logic.stagemanager.StageManager;
import seedu.codesphere.model.course.Course;



/**
 * Panel containing the list of courses.
 */
public class CourseListPanel extends UiComponent<Region> {
    private static final String FXML = "CourseListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(CourseListPanel.class);

    @FXML
    private ListView<Course> courseListView;

    /**
     * Creates a {@code CourseListPanel} with the given {@code ObservableList}.
     */
    public CourseListPanel(ObservableList<Course> courseList) {
        super(FXML);
        courseListView.setItems(courseList);
        courseListView.setCellFactory(listView -> new CourseListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Course} using a {@code CourseCard}.
     */
    class CourseListViewCell extends ListCell<Course> {
        @Override
        protected void updateItem(Course course, boolean empty) {
            super.updateItem(course, empty);

            if (empty || course == null) {
                setGraphic(null);
                setText(null);
            } else {
                StageManager stageManager = StageManager.getInstance();
                if (stageManager.isSelectedCourseNull()) {
                    setGraphic(new CourseCard(course, getIndex() + 1, false, false).getRoot());
                } else if (stageManager.getSelectedCourse().equals(course)) {
                    setGraphic(new CourseCard(course, getIndex() + 1, false, true).getRoot());
                } else if (!stageManager.getSelectedCourse().equals(course)) {
                    setGraphic(new CourseCard(course, getIndex() + 1, true, false).getRoot());
                }
            }
        }
    }
}
