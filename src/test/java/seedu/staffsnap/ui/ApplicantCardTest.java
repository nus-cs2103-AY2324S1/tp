package seedu.staffsnap.ui;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import javafx.application.Platform;
import org.junit.jupiter.api.BeforeAll;
import seedu.staffsnap.model.interview.Interview;
import seedu.staffsnap.model.interview.Rating;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.staffsnap.testutil.TypicalApplicants.BENSON;

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
    public void displayApplicantId_newApplicant_applicantIdDisplayed() {
        HBox idLabel = (HBox) stage.getScene().lookup("#details");
        assertNotNull(idLabel);
        Label id = (Label) idLabel.lookup("#id");
        assertEquals("1. ", id.getText());
    }

    @Test
    public void displayApplicantDetails_newApplicant_applicantDetailsDisplayed() {
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
    public void displayApplicantStatus_newApplicant_applicantStatusDisplayed() {
        HBox idLabel = (HBox) stage.getScene().lookup("#details");
        Label status = (Label) idLabel.lookup("#status");
        assertNotNull(status);
        assertEquals("UNDECIDED", status.getText());
    }

    @Test
    public void displayApplicantScore_newInterview_correctScore() {
        StackPane overallRating = (StackPane) stage.getScene().lookup("#overallRating");
        assertNotNull(overallRating);
        Label ratingLabel = (Label) overallRating.getChildren().get(2);
        assertNotNull(ratingLabel);
        String correctScore = Double.toString(BENSON.getScore().getAverageScore());
        assertEquals(correctScore, ratingLabel.getText());
    }

    @Test
    public void displayApplicantScore_updateScore_scoreUpdated() {
        Interview newInterview = new Interview("test", new Rating("9.0"));
        BENSON.addInterview(newInterview);

        StackPane overallRating = (StackPane) stage.getScene().lookup("#overallRating");
        Label ratingLabel = (Label) overallRating.getChildren().get(2);
        assertNotNull(ratingLabel);
        String correctScore = Double.toString(BENSON.getScore().getAverageScore());
        assertEquals(correctScore, ratingLabel.getText());

        BENSON.deleteInterview(newInterview);
    }

}
