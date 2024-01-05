package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.person.Person;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class PersonCard extends UiPart<Region> {

    private static final String FXML = "PersonListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Person person;

    @FXML
    private HBox cardPane;
    @FXML
    private ImageView emailLogo;
    @FXML
    private ImageView phoneLogo;
    @FXML
    private ImageView telegramLogo;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label phone;
    @FXML
    private Label telegramHandle;
    @FXML
    private Label email;
    @FXML
    private FlowPane tags;
    @FXML
    private FlowPane gradedTests;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public PersonCard(Person person, int displayedIndex) {
        super(FXML);
        this.person = person;
        id.setText(displayedIndex + ". ");
        name.setText(person.getName().fullName);


        Image phoneImage = new Image(getClass().getResourceAsStream("/images/phonelogo.png"));
        phoneLogo.setImage(phoneImage);
        phone.setText(person.getPhone().value);

        Image telegramImage = new Image(getClass().getResourceAsStream("/images/telelogo.png"));
        telegramLogo.setImage(telegramImage);
        telegramHandle.setText(String.format("@%s", person.getTelegramHandle().value));

        Image emailImage = new Image(getClass().getResourceAsStream("/images/emaillogo.png"));
        emailLogo.setImage(emailImage);
        email.setText(person.getEmail().value);

        person.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));

        person.getGradedTest().stream()
                .sorted(Comparator.comparing(gradedTest -> gradedTest.gradedTestsIndv))
                .forEach(gradedTest -> {
                    Label ra1Label = new Label("RA1: " + gradedTest.getRA1());
                    Label ra2Label = new Label("RA2: " + gradedTest.getRA2());
                    Label midTermsLabel = new Label("MidTerms: " + gradedTest.getMidTerms());
                    Label finalsLabel = new Label("Finals: " + gradedTest.getFinals());
                    Label peLabel = new Label("PE: " + gradedTest.getPracticalExam());

                    gradedTests.getChildren().addAll(ra1Label, ra2Label, midTermsLabel, finalsLabel, peLabel);
                });

    }
}
