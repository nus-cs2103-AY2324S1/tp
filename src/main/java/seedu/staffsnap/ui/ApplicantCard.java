package seedu.staffsnap.ui;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Region;
import seedu.staffsnap.model.applicant.Applicant;
import seedu.staffsnap.model.interview.Interview;

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
    private HBox interviews;
    @FXML
    private Label status;

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

        status.setText(applicant.getStatus().toString());


        String css = "-fx-border-color: red;\n"
                + "-fx-border-width: 2;\n"
                + "-fx-border-style: solid;\n";

        for (Interview interview : applicant.getInterviews()) {
            VBox interviewBox = new VBox();
            HBox interviewHeader = new HBox();
            HBox interviewRating = new HBox();

            interviewBox.setStyle(css);
//            interviewHeader.setStyle(css);
            interviewRating.setStyle(css);
            VBox.setVgrow(interviewRating, Priority.ALWAYS);

            interviewBox.setPrefHeight(100);
            interviewBox.setPrefWidth(100);

            Label interviewLabel = new Label(applicant.getInterviewIndexForApplicantCard(interview)
                    + ". " + interview.type);

            Region lHeaderRegion = new Region();
            Region rHeaderRegion = new Region();
            lHeaderRegion.setStyle("-fx-background-color: #3e7b91;");
            rHeaderRegion.setStyle("-fx-background-color: #3e7b91;");
            HBox.setHgrow(lHeaderRegion, Priority.ALWAYS);
            HBox.setHgrow(rHeaderRegion, Priority.ALWAYS);

            Label interviewRatingTextField = new Label();
            interviewRatingTextField.getStyleClass().add("rating");
            interviewRatingTextField.setText("9.9");
            HBox.setHgrow(interviewRatingTextField, Priority.ALWAYS);
            VBox.setVgrow(interviewRatingTextField, Priority.ALWAYS);

            Region lRatingRegion = new Region();
            Region rRatingRegion = new Region();
            lRatingRegion.setStyle("-fx-background-color: #7fc9e8;");
            rRatingRegion.setStyle("-fx-background-color: #7fc9e8;");
            HBox.setHgrow(lRatingRegion, Priority.ALWAYS);
            HBox.setHgrow(rRatingRegion, Priority.ALWAYS);

            interviewHeader.getChildren().addAll(lHeaderRegion, interviewLabel, rHeaderRegion);
            interviewRating.getChildren().addAll(interviewRatingTextField);
            interviewBox.getChildren().addAll(interviewHeader, interviewRating);
            interviews.getChildren().add(interviewBox);
        }
    }
}
