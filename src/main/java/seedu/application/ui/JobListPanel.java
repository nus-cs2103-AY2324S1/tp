package seedu.application.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.application.commons.core.LogsCenter;
import seedu.application.model.job.Job;

/**
 * Panel containing the list of jobs.
 */
public class JobListPanel extends UiPart<Region> {
    private static final String FXML = "JobListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(JobListPanel.class);
    private MainWindow mainWindow;
    @FXML
    private ListView<Job> jobListView;

    /**
     * Creates a {@code JobListPanel} with the given {@code ObservableList}.
     */
    public JobListPanel(ObservableList<Job> jobList, MainWindow mainWindow) {
        super(FXML);
        this.mainWindow = mainWindow;
        jobListView.setItems(jobList);
        jobListView.setCellFactory(listView -> new JobListViewCell());

        // Event listener recognise the event where a jobCard is selected
        jobListView.getSelectionModel().selectedItemProperty()
            .addListener((observable, oldValue, newValue) -> {
                if (newValue != null) {
                    mainWindow.displayJobDetails(newValue);
                }
            });
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Job} using a {@code JobCard}.
     */
    class JobListViewCell extends ListCell<Job> {
        @Override
        protected void updateItem(Job job, boolean empty) {
            super.updateItem(job, empty);

            if (empty || job == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new JobCard(job, getIndex() + 1).getRoot());
            }
        }
    }

}
