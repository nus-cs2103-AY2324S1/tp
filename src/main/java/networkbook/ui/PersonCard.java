package networkbook.ui;

import static java.util.Objects.requireNonNull;

import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import networkbook.model.person.Graduation;
import networkbook.model.person.Person;
import networkbook.model.person.Priority;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class PersonCard extends UiPart<Region> {

    private static final String FXML = "PersonListCard.fxml";
    private static final String PHONES_HEADER = "Phones: ";
    private static final String EMAILS_HEADER = "Emails: ";
    private static final String LINKS_HEADER = "Links: ";
    private static final String GRADUATION_HEADER = "Graduation: ";
    private static final String COURSE_HEADER = "Courses: ";
    private static final String SPECIALISATION_HEADER = "Specialisations: ";
    private static final String PRIORITY_HEADER = "Priority: ";
    private static final String EMPTY_FIELD = "-";

    private static final String FIELD_STYLE_CLASS = "cell_field";
    private static final String EMPTY_STYLE_CLASS = "empty";
    private static final String PRIORITY_STYLE_CLASS_PREFIX = "priority_";

    private static final Logger LOGGER = Logger.getLogger("PersonCard");

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
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label phonesHeader;
    @FXML
    private FlowPane phones;
    @FXML
    private Label linksHeader;
    @FXML
    private FlowPane links;
    @FXML
    private Label graduationHeader;
    @FXML
    private Label graduation;
    @FXML
    private Label coursesHeader;
    @FXML
    private FlowPane courses;
    @FXML
    private Label specialisationsHeader;
    @FXML
    private FlowPane specialisations;
    @FXML
    private Label emailsHeader;
    @FXML
    private FlowPane emails;
    @FXML
    private FlowPane tags;
    @FXML
    private Label priority;
    @FXML
    private Label priorityHeader;

    /**
     * Creates a {@code PersonCard} with the given {@code Person} and index to display.
     */
    public PersonCard(Person person, int displayedIndex) {
        super(FXML);
        requireNonNull(person);
        this.person = person;

        // Name and ID
        id.setText(displayedIndex + ". ");
        name.setText(person.getName().fullName);

        // Phone numbers
        phonesHeader.setText(PHONES_HEADER);
        person.getPhones().stream()
                .forEach(phone -> {
                    Label phoneLabel = new Label(phone.getValue());
                    phoneLabel.getStyleClass().add(FIELD_STYLE_CLASS);
                    phones.getChildren().add(phoneLabel);
                });
        if (person.getPhones().isEmpty()) {
            phones.getChildren().add(getEmptyFieldLabel());
        }

        // Email addresses
        emailsHeader.setText(EMAILS_HEADER);
        // TODO: implement actual link opening
        person.getEmails().stream()
                .forEach(email -> emails.getChildren().add(new FieldHyperlink(email.getValue(), () -> {
                    LOGGER.log(Level.INFO, "Opening email: " + email.getValue());
                })));
        if (person.getEmails().isEmpty()) {
            emails.getChildren().add(getEmptyFieldLabel());
        }

        // Website links
        linksHeader.setText(LINKS_HEADER);
        // TODO: implement actual link opening
        person.getLinks().stream()
                .forEach(link -> links.getChildren().add(new FieldHyperlink(link.getValue(), () -> {
                    LOGGER.log(Level.INFO, "Opening link: " + link.getValue());
                })));
        if (person.getLinks().isEmpty()) {
            links.getChildren().add(getEmptyFieldLabel());
        }

        // Graduation
        graduationHeader.setText(GRADUATION_HEADER);
        person.getGraduation().ifPresentOrElse((Graduation g) -> {
                graduation.setText(g.getFullString());
                graduation.getStyleClass().add(EMPTY_STYLE_CLASS);
            }, () -> {
                graduation.setText(EMPTY_FIELD);
                graduation.getStyleClass().add(EMPTY_STYLE_CLASS);
            });

        // Courses
        coursesHeader.setText(COURSE_HEADER);
        person.getCourses().stream()
                .forEach(course -> {
                    Label courseLabel = new Label(course.getValue());
                    courseLabel.getStyleClass().add(FIELD_STYLE_CLASS);
                    courses.getChildren().add(courseLabel);
                });
        if (person.getCourses().isEmpty()) {
            courses.getChildren().add(getEmptyFieldLabel());
        }

        // Specialisations
        specialisationsHeader.setText(SPECIALISATION_HEADER);
        person.getSpecialisations().stream()
                .forEach(spec -> {
                    Label specLabel = new Label(spec.getValue());
                    specLabel.getStyleClass().add(FIELD_STYLE_CLASS);
                    specialisations.getChildren().add(specLabel);
                });
        if (person.getSpecialisations().isEmpty()) {
            specialisations.getChildren().add(getEmptyFieldLabel());
        }

        // Tags
        person.getTags().stream()
                .forEach(tag -> tags.getChildren().add(new Label(tag.getValue())));

        // Priority
        priorityHeader.setText(PRIORITY_HEADER);
        person.getPriority().ifPresentOrElse((Priority p) -> {
            priority.setText(p.toString());
            priority.getStyleClass().add(PRIORITY_STYLE_CLASS_PREFIX + p.toString());
        }, () -> {
            priority.setText(EMPTY_FIELD);
            priority.getStyleClass().add(EMPTY_STYLE_CLASS);
        });
    }

    /**
     * Returns new label to indicate empty field or list.
     */
    private static Label getEmptyFieldLabel() {
        Label emptyFieldLabel = new Label(EMPTY_FIELD);
        emptyFieldLabel.getStyleClass().add(FIELD_STYLE_CLASS);
        emptyFieldLabel.getStyleClass().add(EMPTY_STYLE_CLASS);
        return emptyFieldLabel;
    }

    // Below: getter methods for testing
    public Label getGraduation() {
        return graduation;
    }
    public Label getPriority() {
        return priority;
    }
}
