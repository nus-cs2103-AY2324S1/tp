package seedu.application.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.application.commons.core.LogsCenter;
import seedu.application.model.job.interview.Interview;

/**
 * Panel containing the list of interviews in a specific Job.
 */
public class InterviewListPanel extends UiPart<Region> {
    private static final String FXML = "InterviewListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(InterviewListPanel.class);
    @FXML
    private ListView<Interview> interviewListView;

    /**
     * Creates a {@code InterviewListPanel} with the given {@code ObservableList}.
     */
    public InterviewListPanel(ObservableList<Interview> interviewList) {
        super(FXML);
        interviewListView.setItems(interviewList);
        interviewListView.setCellFactory(listView -> new InterviewListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Interview} using a {@code InterviewCard}.
     */
    class InterviewListViewCell extends ListCell<Interview> {
        @Override
        protected void updateItem(Interview interview, boolean empty) {
            super.updateItem(interview, empty);

            if (empty || interview == null) {
                setGraphic(null);
                setText(null);
                setStyle("-fx-background-color: #FAF0FF;");
            } else {
                setGraphic(new InterviewCard(interview, getIndex() + 1).getRoot());
            }
        }
    }
}
