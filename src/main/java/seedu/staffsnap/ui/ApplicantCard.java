package seedu.staffsnap.ui;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
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
    private Label status;
    @FXML
    private StackPane overallRating;
    @FXML
    private HBox interviews;

    /**
     * Creates a {@code ApplicantCode} with the given {@code Applicant} and index to display.
     */
    public ApplicantCard(Applicant applicant, int displayedIndex) {
        super(FXML);
        this.applicant = applicant;

        displayApplicantId(displayedIndex);
        displayApplicantDetails();
        displayApplicantStatus();
        displayApplicantInterviews();
        displayApplicantScore();
    }

    private void displayApplicantId(int displayedIndex) {
        id.setText(displayedIndex + ". ");
    }
    private void displayApplicantDetails() {
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
    }

    private void displayApplicantStatus() {
        status.setText(applicant.getStatus().toString());

        switch (applicant.getStatus()) {
        case OFFERED:
            status.setStyle("-fx-background-color: #0da811");
            break;
        case REJECTED:
            status.setStyle("-fx-background-color: #cc4242");
            break;
        case UNDECIDED:
            status.setStyle("-fx-background-color: #36769a");
            break;
        default:
        }
    }

    private void displayApplicantInterviews() {
        for (Interview interview : applicant.getInterviews()) {
            VBox interviewBox = new VBox();
            HBox interviewHeader = new HBox();
            HBox interviewRating = new HBox();

            interviewBox.setMinHeight(100);
            interviewBox.setMinWidth(120);
            interviewRating.setPrefWidth(100);
            interviewRating.setAlignment(Pos.CENTER);
            interviewHeader.setAlignment(Pos.CENTER);
            VBox.setVgrow(interviewRating, Priority.ALWAYS);

            interviewBox.setBackground(new Background(new BackgroundFill(
                    Color.TRANSPARENT,
                    new CornerRadii(10),
                    Insets.EMPTY)));
            interviewBox.setBorder(new Border(new BorderStroke(
                    Color.TRANSPARENT,
                    BorderStrokeStyle.NONE,
                    new CornerRadii(10),
                    BorderWidths.DEFAULT)));
            interviewHeader.setBackground(new Background(new BackgroundFill(
                    Color.web("#3e7b91"),
                    new CornerRadii(10, 10, 0, 0, false),
                    Insets.EMPTY)));
            interviewRating.setBackground(new Background(new BackgroundFill(
                    Color.web("#7fc9e8"),
                    new CornerRadii(0, 0, 10, 10, false),
                    Insets.EMPTY)));

            Label interviewLabel = new Label(applicant.getInterviewIndexForApplicantCard(interview)
                    + ". " + interview.type);

            Label interviewRatingLabel = new Label();
            interviewRatingLabel.getStyleClass().add("rating");
            interviewRatingLabel.setText(interview.getRating().toString());

            interviewHeader.getChildren().add(interviewLabel);
            interviewRating.getChildren().add(interviewRatingLabel);
            interviewBox.getChildren().addAll(interviewHeader, interviewRating);

            interviews.getChildren().add(interviewBox);
        }
    }
    private void displayApplicantScore() {
        Circle outerCircle = new Circle(50);
        outerCircle.setFill(Color.web("#454545"));

        Circle midCircle = new Circle(43);
        midCircle.setFill(Color.web("#454545"));

        Circle innerCircle = new Circle(36);
        innerCircle.setFill(Color.web("#454545"));

        Group stackedArcs = new Group();
        stackedArcs.getChildren().addAll(outerCircle, midCircle);

        String labelText = applicant.getScore().hasRating() ? applicant.getScore().toString() : "-";
        Label scoreLabel = new Label(labelText);
        scoreLabel.getStyleClass().add("score_label");

        Color[] colours = { Color.TRANSPARENT, Color.web("#1a8cff"), Color.web("#3333cc"),
                Color.web("#7a00cc"), Color.web("#cc0099"), Color.web("#ff0066"),
                Color.web("#ff6600"), Color.web("#ffcc00"), Color.web("#ccff33"),
                Color.web("#66ff33"), Color.web("#00ffcc")};

        for (int i = 0; i < 10; i++) {
            Arc arc = new Arc(0, 0, 43, 43,
                    90 + i * 36, -30);
            arc.setType(ArcType.ROUND);
            arc.setFill(Color.GREY);
            stackedArcs.getChildren().add(arc);
        }

        double applicantRating = labelText.equals("-") ? 0 : Double.parseDouble(labelText);
        double ratingArcLength = -360 * (applicantRating / 10);
        Arc ratingArc = new Arc(0, 0, 43, 43, 90, ratingArcLength);
        Color arcColour = colours[(int) Math.floor(applicantRating)];
        ratingArc.setFill(arcColour);
        ratingArc.setType(ArcType.ROUND);

        stackedArcs.getChildren().add(ratingArc);
        overallRating.getChildren().addAll(stackedArcs, innerCircle, scoreLabel);
    }

}
