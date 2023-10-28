package seedu.address.ui;

import java.util.logging.Logger;

import javafx.beans.value.ObservableStringValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import javafx.scene.text.TextAlignment;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.Logic;

/**
 * Panel containing the list of courses.
 */
public class CourseListPanel extends UiPart<Region> {
    private static final String FXML = "CourseListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(CourseListPanel.class);

    @FXML
    private ListView<String> courseListView;

    /**
     * Creates a {@code PersonListPanel} with the given {@code ObservableList}.
     */
    public CourseListPanel(Logic logic, ObservableList<String> courseList, ObservableStringValue selectedCourse) {
        super(FXML);
        courseListView.setItems(courseList);
        courseListView.setCellFactory(listView -> new CourseListViewCell(logic, selectedCourse));
        courseListView.getSelectionModel().select(selectedCourse.get());

        selectedCourse.addListener((observable, oldValue, newValue) -> {
            courseListView.getSelectionModel().select(newValue);
        });
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code PersonCard}.
     */
    class CourseListViewCell extends ListCell<String> {
        public CourseListViewCell(Logic logic, ObservableStringValue selectedCourse) {

            setOnMouseClicked(event -> {
                if (getItem() != null) {
                    logic.setActiveAddressBook(getItem());
                    logger.info("Course clicked: " + getItem());
                }
            });
        }

        @Override
        protected void updateItem(String courseCode, boolean empty) {
            super.updateItem(courseCode, empty);

            if (empty || courseCode == null) {
                setGraphic(null);
                setText(null);
            } else {
                Label courseLabel = new Label(courseCode);
                courseLabel.setTextAlignment(TextAlignment.CENTER);
                courseLabel.setAlignment(Pos.CENTER);
                courseLabel.setMaxWidth(Double.MAX_VALUE);
                setGraphic(courseLabel);
            }
        }
    }
}
