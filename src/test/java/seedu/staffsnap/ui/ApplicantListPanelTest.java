package seedu.staffsnap.ui;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.framework.junit5.Start;
import seedu.staffsnap.model.applicant.Applicant;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.staffsnap.testutil.TypicalApplicants.*;

class ApplicantListPanelTest extends ApplicationTest {

    private ObservableList<Applicant> applicantObservableList =
            FXCollections.observableArrayList(BENSON, ALICE, HOON);

    private ApplicantListPanel applicantListPanel = new ApplicantListPanel(applicantObservableList);

    private Stage stage;

    @BeforeAll
    public static void start() {
        Platform.startup(() -> {});
    }

    @Start
    public void start(Stage stage) throws Exception {
        // Set the scene with the applicantListPanel
        this.stage = stage;
        Scene scene = new Scene(applicantListPanel.getRoot());
        stage.setScene(scene);
        stage.show();
    }

    @Test
    void applicantListViewCell_addApplicants_listShouldContainApplicants() {
        // Verify the ListView contains the number of items equal to the mock data size
        ListView<Applicant> applicantListView = (ListView<Applicant>) stage.getScene().lookup("#applicantListView");
        assertEquals(3, applicantListView.getItems().size());
    }
}
