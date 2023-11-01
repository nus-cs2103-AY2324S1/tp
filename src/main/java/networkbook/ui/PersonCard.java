package networkbook.ui;

import static java.util.Objects.requireNonNull;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import networkbook.model.person.Person;
import networkbook.model.person.Priority;
import networkbook.model.util.Identifiable;
import networkbook.model.util.UniqueList;

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
    private static final String FIELD_WITH_INDEX_FORMAT = "%d) %s";

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
    private FlowPane graduation;
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
    private FlowPane priority;

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
        populateListChildren(person.getPhones(), phones);

        // Email addresses
        emailsHeader.setText(EMAILS_HEADER);
        populateHyperlinkListChildren(person.getEmails(), emails, (email) -> {
            // TODO: implement actual email opening
            LOGGER.log(Level.INFO, "Opening email: " + email.getValue());
        });

        // Website links
        linksHeader.setText(LINKS_HEADER);
        populateHyperlinkListChildren(person.getLinks(), links, (link)-> {
            // TODO: implement actual link opening
            LOGGER.log(Level.INFO, "Opening link: " + link.getValue());
        });

        // Graduation
        graduationHeader.setText(GRADUATION_HEADER);
        populateField(person.getGraduation(), graduation);

        // Courses
        coursesHeader.setText(COURSE_HEADER);
        populateListChildren(person.getCourses(), courses);

        // Specialisations
        specialisationsHeader.setText(SPECIALISATION_HEADER);
        populateListChildren(person.getSpecialisations(), specialisations);

        // Tags
        person.getTags().stream()
                .forEach(tag -> tags.getChildren().add(new Label(tag.getValue())));

        // Priority
        populatePriority(person.getPriority(), priority);
    }

    /**
     * Populates FlowPane with labels from list.
     * @param <T> Type of list item.
     * @param list Source list of items.
     * @param pane FlowPane to populate.
     */
    private <T extends Identifiable<T>> void populateListChildren(UniqueList<T> list, FlowPane pane) {
        if (list.isEmpty()) {
            pane.getChildren().add(new EmptyFieldLabel());
        } else {
            for (int i = 0; i < list.size(); i++) {
                T t = list.get(i);
                FieldLabel label = new FieldLabel(String.format(FIELD_WITH_INDEX_FORMAT, i + 1, t.getValue()));
                pane.getChildren().add(label);
            }
        }
    }

    /**
     * Populates FlowPane with hyperlinks from list.
     * @param <T> Type of list item.
     * @param list Source list of items.
     * @param pane FlowPane to populate.
     * @param action Action to perform on link click. Takes in list item.
     */
    private <T extends Identifiable<T>> void populateHyperlinkListChildren(UniqueList<T> list,
            FlowPane pane, Consumer<T> action) {
        if (list.isEmpty()) {
            pane.getChildren().add(new EmptyFieldLabel());
        } else {
            for (int i = 0; i < list.size(); i++) {
                T link = list.get(i);
                FieldHyperlink hyperlink = new FieldHyperlink(
                        String.format(FIELD_WITH_INDEX_FORMAT, i + 1, link.getValue()), () -> action.accept(link));
                pane.getChildren().add(hyperlink);
            }
        }
    }

    /**
     * Populates field with single value.
     * @param <T> Type of value.
     * @param item Value.
     * @param pane FlowPane to populate.
     */
    private <T> void populateField(Optional<T> item, FlowPane pane) {
        item.ifPresentOrElse((T t) -> {
            pane.getChildren().add(new FieldLabel(t.toString()));
        }, () -> {
            pane.getChildren().add(new EmptyFieldLabel());
        });
    }

    /**
     * Populates priority.
     * @param p Optional of Priority.
     * @param pane FlowPane to populate.
     */
    private void populatePriority(Optional<Priority> p, FlowPane pane) {
        p.ifPresentOrElse((Priority priority) -> {
            pane.getChildren().add(new PriorityFieldLabel(priority));
        }, () -> {
            pane.getChildren().add(new EmptyFieldLabel());
        });
    }
    // Below: getter methods for testing
}
