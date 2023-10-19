package seedu.staffsnap.ui;

import java.util.Comparator;
import java.util.concurrent.Flow;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.Orientation;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.staffsnap.model.applicant.Applicant;

/**
 * A UI component that displays information of a {@code Applicant}.
 */
public class ApplicantCard extends UiPart<Region> {

    private static final String FXML = "ApplicantListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Applicant applicant;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private HBox phone;
    @FXML
    private HBox email;
    @FXML
    private HBox position;
    @FXML
    private FlowPane interviews;

    /**
     * Creates a {@code ApplicantCode} with the given {@code Applicant} and index to display.
     */
    public ApplicantCard(Applicant applicant, int displayedIndex) {
        super(FXML);
        this.applicant = applicant;
        id.setText(displayedIndex + ". ");

        name.setText(applicant.getName().fullName);

        Label phoneLabel = new Label(applicant.getPhone().value);
        ImageView phoneIcon = new ImageView("/images/phone_white_icon.png");
        phoneIcon.setFitHeight(13.5);
        phoneIcon.setFitWidth(13.5);
        phone.getChildren().add(phoneIcon);
        phone.getChildren().add(phoneLabel);

        Label emailLabel = new Label(applicant.getEmail().value);
        ImageView emailIcon = new ImageView("/images/email_white_icon.png");
        emailIcon.setFitHeight(13.5);
        emailIcon.setFitWidth(13.5);
        email.getChildren().add(emailIcon);
        email.getChildren().add(emailLabel);

        Label positionLabel = new Label(applicant.getPosition().value);
        ImageView positionIcon = new ImageView("/images/position_white_icon.png");
        positionIcon.setFitHeight(13.5);
        positionIcon.setFitWidth(13.5);
        position.getChildren().add(positionIcon);
        position.getChildren().add(positionLabel);

        applicant.getInterviews().stream()
                .sorted(Comparator.comparing(interview -> interview.type))
                .forEach(interview -> interviews.getChildren().add(new Label(interview.type)));
    }
}
