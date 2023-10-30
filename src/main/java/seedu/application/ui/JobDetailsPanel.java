package seedu.application.ui;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import seedu.application.commons.core.LogsCenter;
import seedu.application.model.job.interview.Interview;
import seedu.application.model.job.Job;

/**
 * Panel containing the details of the selected job.
 */
public class JobDetailsPanel extends UiPart<Region> {
    private static final String FXML = "JobDetailsPanel.fxml";

    private final Logger logger = LogsCenter.getLogger(JobDetailsPanel.class);

    private final Job job;
    private InterviewListPanel interviewListPanel;

    @FXML
    private StackPane detailsPanelPlaceholder;
    @FXML
    private StackPane interviewListPanelPlaceHolder;
    @FXML
    private VBox jobDetailsPanel;
    @FXML
    private Label role;
    @FXML
    private Label company;
    @FXML
    private Label status;
    @FXML
    private Label deadline;
    @FXML
    private Label jobType;
    @FXML
    private Label industry;
    @FXML
    private Label interviewPreamble;

    /**
     * Creates a {@code JobDetailsPanel} with the selected {@code Job}.
     */
    public JobDetailsPanel(Job job) {
        super(FXML);
        this.job = job;
        role.setText(job.getRole().description);
        company.setText(job.getCompany().name);
        status.setText(job.getStatus().status);
        deadline.setText(job.getDeadline().deadline);
        jobType.setText(job.getJobType().jobType);
        industry.setText(job.getIndustry().industry);
        interviewPreamble.setText("Interviews: ");
        // placeholder interviews **TO BE UPDATED**
        List<Interview> interviews = new ArrayList<>();
        interviews.add(Interview.DEFAULT_INTERVIEW);
        interviewListPanel = new InterviewListPanel(
                FXCollections.observableArrayList(interviews));
        interviewListPanelPlaceHolder.getChildren().add(interviewListPanel.getRoot());
    }
}

