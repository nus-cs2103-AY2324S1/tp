package seedu.staffsnap.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static seedu.staffsnap.logic.commands.CommandTestUtil.VALID_INTERVIEW_HR;
import static seedu.staffsnap.testutil.TypicalApplicants.BENSON;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import seedu.staffsnap.model.interview.Interview;


/**
 * Tests the UI for the ApplicantCard.
 * Ensures that the information displayed is correct and is updated correctly.
 */
@ExtendWith(ApplicationExtension.class)
public class ApplicantCardTest {

    private Stage stage;
    private ApplicantCard testCard = new ApplicantCard(BENSON, 1);

    @BeforeAll
    public static void setUpClass() {
        // Initialize JavaFX runtime.
        Platform.startup(() -> {});
    }

    @Start
    private void start(Stage stage) {
        this.stage = stage;
        Scene scene = new Scene(testCard.getRoot());
        stage.setScene(scene);
        stage.show();
    }

    @Test
    public void displayApplicantId_applicantIdDisplayed() {
        //ApplicantCard should display the correct id
        HBox idLabel = (HBox) stage.getScene().lookup("#details");
        assertNotNull(idLabel);
        Label id = (Label) idLabel.lookup("#id");
        assertEquals("1. ", id.getText());
    }

    @Test
    public void displayApplicantDetails_applicantDetailsDisplayed() {
        //Applicant Card should display the correct name, phone, email, and position
        HBox idLabel = (HBox) stage.getScene().lookup("#details");
        assertNotNull(idLabel);

        HBox phone = (HBox) stage.getScene().lookup("#phone");
        assertNotNull(phone);
        HBox email = (HBox) stage.getScene().lookup("#email");
        assertNotNull(email);
        HBox position = (HBox) stage.getScene().lookup("#position");
        assertNotNull(position);

        Label name = (Label) idLabel.lookup("#name");
        assertNotNull(name);
        Label phoneNumber = (Label) phone.getChildren().get(1);
        assertNotNull(phoneNumber);
        Label emailAddress = (Label) email.getChildren().get(1);
        assertNotNull(emailAddress);
        Label positionApplied = (Label) position.getChildren().get(1);
        assertNotNull(positionApplied);

        assertNotNull(name);
        assertNotNull(phoneNumber);
        assertNotNull(emailAddress);
        assertNotNull(positionApplied);

        assertEquals(BENSON.getName().fullName, name.getText());
        assertEquals(BENSON.getPhone().toString(), phoneNumber.getText());
        assertEquals(BENSON.getEmail().toString(), emailAddress.getText());
        assertEquals(BENSON.getPosition().toString(), positionApplied.getText());
    }

    @Test
    public void displayApplicantStatus_applicantStatusDisplayed() {
        //ApplicantCard should correctly display the status of the applicant
        HBox idLabel = (HBox) stage.getScene().lookup("#details");
        Label status = (Label) idLabel.lookup("#status");
        assertNotNull(status);
        assertEquals("OFFERED", status.getText());
    }

    @Test
    public void displayApplicantInterviews_correctDataDisplayed() {
        //ApplicantCard should display the correct title and score for each interview
        List<Interview> interviewList = BENSON.getInterviews();
        HBox interviews = (HBox) stage.getScene().lookup("#interviews");

        for (int i = 0; i < interviewList.size(); i++) {
            Interview interviewToCheck = interviewList.get(i);
            VBox interviewBox = (VBox) interviews.getChildren()
                    .get(BENSON.getInterviewIndexForApplicantCard(interviewToCheck) - 1);

            HBox interviewHeader = (HBox) interviewBox.getChildren().get(0);
            Label interviewLabel = (Label) interviewHeader.getChildren().get(0);

            HBox interviewRating = (HBox) interviewBox.getChildren().get(1);
            Label interviewRatingLabel = (Label) interviewRating.getChildren().get(0);

            String correctHeader = BENSON.getInterviewIndexForApplicantCard(interviewToCheck) + ". "
                    + interviewToCheck.getType();
            String correctRating = interviewToCheck.getRating().toString();

            assertEquals(correctHeader, interviewLabel.getText());
            assertEquals(correctRating, interviewRatingLabel.getText());
        }

    }

    @Test
    public void displayApplicantScore_correctScore() {
        //ApplicantCard should display the correct average score of an applicant
        StackPane overallRating = (StackPane) stage.getScene().lookup("#overallRating");
        assertNotNull(overallRating);
        Label ratingLabel = (Label) overallRating.getChildren().get(2);
        assertNotNull(ratingLabel);
        String correctScore = Double.toString(BENSON.getScore().getAverageScore());
        assertEquals(correctScore, ratingLabel.getText());
    }

    @Test
    public void displayApplicantScore_addInterview_scoreUpdated() {
        //ApplicantCard should display the correct updated score when a new interview is added
        BENSON.addInterview(VALID_INTERVIEW_HR);

        StackPane overallRating = (StackPane) stage.getScene().lookup("#overallRating");
        Label ratingLabel = (Label) overallRating.getChildren().get(2);
        assertNotNull(ratingLabel);
        String correctScore = Double.toString(BENSON.getScore().getAverageScore());
        assertEquals(correctScore, ratingLabel.getText());
    }

    @Test
    public void displayApplicantScore_deleteInterview_scoreUpdated() {
        //ApplicantCard should display the correct updated score when a new interview is removed
        BENSON.deleteInterview(VALID_INTERVIEW_HR);

        StackPane overallRating = (StackPane) stage.getScene().lookup("#overallRating");
        Label ratingLabel = (Label) overallRating.getChildren().get(2);
        assertNotNull(ratingLabel);
        String correctScore = Double.toString(BENSON.getScore().getAverageScore());
        assertEquals(correctScore, ratingLabel.getText());
    }

}
