package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import javafx.util.Pair;
import seedu.address.commons.core.LogsCenter;

/**
 * Panel containing the list of courses.
 */
public class CourseListPanel extends UiPart<Region> {
    private static final String FXML = "CourseListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(CourseListPanel.class);

    @FXML
    private ListView<Pair<String, Integer>> courseListView;

    /**
     * Creates a {@code CourseListPanel} with the given {@code ObservableList}.
     */
    public CourseListPanel(ObservableList<Pair<String, Integer>> courseList) {
        super(FXML);
        courseListView.setItems(courseList);
        courseListView.setCellFactory(listView -> new CourseListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Course} using a {@code
     * CourseCard}.
     */
    class CourseListViewCell extends ListCell<Pair<String, Integer>> {
        @Override
        protected void updateItem(Pair<String, Integer> coursePair, boolean empty) {
            super.updateItem(coursePair, empty);

            if (empty || coursePair == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new CourseCard(coursePair, getIndex() + 1).getRoot());
            }
        }
    }

}
