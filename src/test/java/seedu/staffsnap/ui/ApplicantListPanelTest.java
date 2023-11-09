package seedu.staffsnap.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.staffsnap.testutil.TypicalApplicants.ALICE;
import static seedu.staffsnap.testutil.TypicalApplicants.BENSON;
import static seedu.staffsnap.testutil.TypicalApplicants.HOON;

import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.framework.junit5.Start;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import seedu.staffsnap.model.applicant.Applicant;

class ApplicantListPanelTest extends ApplicationTest {

    private ObservableList<Applicant> applicantObservableList =
            FXCollections.observableArrayList(BENSON, ALICE, HOON);

    private ApplicantListPanel applicantListPanel;

    private Stage stage;

    @Start
    public void start(Stage stage) {
        // Set the scene with the applicantListPanel
        applicantListPanel = new ApplicantListPanel(applicantObservableList);
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
