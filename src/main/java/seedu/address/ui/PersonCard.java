package seedu.address.ui;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.person.Person;
import seedu.address.model.socialmedialink.SocialMediaLink;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class PersonCard extends UiPart<Region> {

    private static final String FXML = "PersonListCard.fxml";
    private static final Logger logger = Logger.getLogger(PersonCard.class.getName());
    public final Person person;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label major;
    @FXML
    private Label year;
    @FXML
    private Label email;
    @FXML
    private Label description;
    @FXML
    private Label tutorials;
    @FXML
    private FlowPane socialMediaLinks;
    @FXML
    private Label nationality;
    @FXML
    private HBox nationalityBox;
    @FXML
    private Label gender;
    @FXML
    private HBox genderBox;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public PersonCard(Person person, int displayedIndex) {
        super(FXML);
        this.person = person;
        id.setText(displayedIndex + ". ");
        id.setStyle("-fx-font-size: 17px; -fx-text-fill: #E7BE34; -fx-font-family: 'Arial';");
        name.setText(person.getName().fullName);
        name.setStyle("-fx-font-size: 20px");
        if (person.getNationality().value.equals("local")) {
            nationality.setText("Local");
            nationalityBox.setStyle("-fx-background-color: rgba(101,152,60,0.82); -fx-background-radius: 10;");
        } else {
            nationality.setText("Foreigner");
            nationalityBox.setStyle("-fx-background-color: rgba(17,63,3,0.68); -fx-background-radius: 10;");
        }
        nationality.setStyle("-fx-font-size: 14px");
        if (person.getGender().value.equals("M")) {
            gender.setText("Male");
            genderBox.setStyle("-fx-background-color: rgb(78,88,136); -fx-background-radius: 10;");
        } else {
            gender.setText("Female");
            genderBox.setStyle("-fx-background-color: rgb(93,81,87); -fx-background-radius: 10;");
        }
        gender.setStyle("-fx-font-size: 14px; -fx-text-fill: white");
        major.setText(person.getMajor().value);
        year.setText("Y" + person.getYear().value);
        email.setText(person.getEmail().value);
        description.setText(person.getDescription().value);
        String tutorialsText = person.getTutorials().stream()
                .map(t -> "T" + t.getValue())
                .sorted()
                .collect(Collectors.joining(", "));
        tutorials.setText(tutorialsText);

        int initialHyperlinksCount = socialMediaLinks.getChildren().size(); // Get the initial count

        if (!person.getSocialMediaLinks().isEmpty()) {
            person.getSocialMediaLinks().stream()
                    .sorted(Comparator.comparing(sm -> sm.socialMediaLink))
                    .forEach(sm -> {
                        Hyperlink hyperlink = new Hyperlink(sm.socialMediaLink);
                        hyperlink.setStyle(
                                "-fx-font-size: 13px; -fx-text-fill: white; -fx-font-family: 'Segoe UI Semibold';");
                        hyperlink.setOnAction(event -> openWebBrowser(sm.socialMediaLink));
                        socialMediaLinks.getChildren().add(hyperlink);
                    });

            int finalHyperlinksCount = socialMediaLinks.getChildren().size(); // Get the final count

            // Use an assertion to check the condition
            assert finalHyperlinksCount >= initialHyperlinksCount
                    : "No hyperlinks were added. Please check the code that adds hyperlinks.";
        }

    }

    /**
     * Opens a web browser with the specified URL.
     *
     * @param link The URL to be opened in the web browser.
     * @throws UnsupportedOperationException if the platform does not support the {@code Desktop} class.
     * @throws java.io.IOException If the default browser is not found or it fails to be launched.
     * @throws java.net.URISyntaxException If the specified link is not a valid URI.
     */
    public void openWebBrowser(String link) {
        try {
            java.awt.Desktop.getDesktop().browse(new java.net.URI(link));
            logger.info("Opened web browser for link: " + link);
        } catch (java.io.IOException | java.net.URISyntaxException e) {
            // Exceptions handled in other classes
            logger.log(Level.WARNING, "Failed to open the web browser with link: " + link, e);
        }
    }

    // Getter methods for testing
    public String getName() {
        return name.getText();
    }

    public String getMajor() {
        return major.getText();
    }

    public String getYear() {
        return year.getText();
    }

    public String getEmail() {
        return email.getText();
    }

    public String getId() {
        return id.getId();
    }

    public String getTutorials() {
        return tutorials.getText();
    }

    public Set<SocialMediaLink> getSocialMediaLinks() {
        Set<SocialMediaLink> links = new HashSet<>();
        for (Node node : socialMediaLinks.getChildren()) {
            if (node instanceof Hyperlink) {
                Hyperlink hyperlink = (Hyperlink) node;
                SocialMediaLink socialMediaLink = new SocialMediaLink(hyperlink.getText());
                links.add(socialMediaLink);
            }
        }
        return links;
    }

}
