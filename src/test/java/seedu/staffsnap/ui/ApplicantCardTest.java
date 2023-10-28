package seedu.staffsnap.ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

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
    public void displayApplicantDetails_newApplicant_applicantDetailsDisplayed() {
        HBox idLabel = (HBox) stage.getScene().lookup("#details");
        assertNotNull(idLabel);
        Label id = (Label) idLabel.lookup("#id");
        Label name = (Label) idLabel.lookup("#name");
        Label status = (Label) idLabel.lookup("#status");
        assertEquals("1. ", id.getText());
        assertEquals("Benson Meier", name.getText());
        assertEquals("UNDECIDED", status.getText());
    }

}
