package networkbook.ui;

import static java.util.Objects.requireNonNull;

import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import networkbook.commons.core.index.Index;
import networkbook.logic.parser.FilterCommandParser;
import networkbook.logic.parser.OpenEmailCommandParser;
import networkbook.logic.parser.OpenLinkCommandParser;
import networkbook.model.person.Graduation;
import networkbook.model.person.Person;
import networkbook.model.person.Priority;
import networkbook.model.person.Tag;
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

    private final Consumer<String> submitCommandCallback;

    /**
     * Creates a {@code PersonCard} with the given {@code Person} and index to display.
     */
    public PersonCard(Person person, int displayedIndex, Consumer<String> submitCommandCallback) {
        super(FXML);
        requireNonNull(person);
        this.person = person;
        this.submitCommandCallback = submitCommandCallback;

        // Name and ID
        id.setText(displayedIndex + ". ");
        name.setText(person.getName().fullName);

        // Phone numbers
        phonesHeader.setText(PHONES_HEADER);
        populateListChildren(person.getPhones(), phones);

        // Email addresses
        emailsHeader.setText(EMAILS_HEADER);
        populateExternalHyperlinkListChildren(person.getEmails(), emails, (email, index) -> {
            LOGGER.log(Level.INFO, "Opening email: " + email.getValue());
            submitCommandCallback.accept(
                    OpenEmailCommandParser.generateCommandString(displayedIndex, index.getOneBased()));
        });

        // Website links
        linksHeader.setText(LINKS_HEADER);
        populateExternalHyperlinkListChildren(person.getLinks(), links, (link, index) -> {
            LOGGER.log(Level.INFO, "Opening link: " + link.getValue());
            submitCommandCallback.accept(
                    OpenLinkCommandParser.generateCommandString(displayedIndex, index.getOneBased()));
        });

        // Graduation
        graduationHeader.setText(GRADUATION_HEADER);
        populateGrad(person.getGraduation(), graduation);

        // Courses
        coursesHeader.setText(COURSE_HEADER);
        populateHyperlinkListChildren(person.getCourses(), courses, (course, index) -> {
            submitCommandCallback.accept(
                    FilterCommandParser.generateCommandString(course.getValue()));
        });

        // Specialisations
        specialisationsHeader.setText(SPECIALISATION_HEADER);
        populateHyperlinkListChildren(person.getSpecialisations(), specialisations, (spec, index) -> {
            submitCommandCallback.accept("");
            // TODO: Add filter by spec command once it is implemented
        });

        // Tags
        populateTags(person.getTags(), tags);
        // person.getTags().stream()
        //         .forEach(tag -> tags.getChildren().add(new Label(tag.getValue())));

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
     * @param action Action to perform on link click. Takes in list item and index of list item.
     */
    private <T extends Identifiable<T>> void populateHyperlinkListChildren(UniqueList<T> list,
            FlowPane pane, BiConsumer<T, Index> action) {
        if (list.isEmpty()) {
            pane.getChildren().add(new EmptyFieldLabel());
        } else {
            for (int i = 0; i < list.size(); i++) {
                T link = list.get(i);
                Index index = Index.fromZeroBased(i);
                FieldHyperlink hyperlink = new FieldHyperlink(
                        String.format(FIELD_WITH_INDEX_FORMAT, index.getOneBased(), link.getValue()), () ->
                        action.accept(link, index));
                pane.getChildren().add(hyperlink);
            }
        }
    }

    /**
     * Populates FlowPane with external hyperlinks from list.
     * @param <T> Type of list item.
     * @param list Source list of items.
     * @param pane FlowPane to populate.
     * @param action Action to perform on link click. Takes in list item and index of list item.
     */
    private <T extends Identifiable<T>> void populateExternalHyperlinkListChildren(UniqueList<T> list,
            FlowPane pane, BiConsumer<T, Index> action) {
        if (list.isEmpty()) {
            pane.getChildren().add(new EmptyFieldLabel());
        } else {
            for (int i = 0; i < list.size(); i++) {
                T link = list.get(i);
                Index index = Index.fromZeroBased(i);
                FieldHyperlink hyperlink = new ExternalFieldHyperlink(
                        String.format(FIELD_WITH_INDEX_FORMAT, index.getOneBased(), link.getValue()), () ->
                        action.accept(link, index));
                pane.getChildren().add(hyperlink);
            }
        }
    }

    /**
     * Populates tags.
     * @param tags UniqueList of tags.
     * @param pane FlowPane to populate.
     */
    private void populateTags(UniqueList<Tag> tags, FlowPane pane) {
        tags.stream()
                .forEach(tag -> pane.getChildren().add(new TagHyperlink(tag.getValue(), () -> {
                    submitCommandCallback.accept("");
                    // TODO: Add filter by tag command once it is implemented
                })));
    }

    /**
     * Populates graduation.
     * @param g Optional of Graduation.
     * @param pane FlowPane to populate.
     */
    private void populateGrad(Optional<Graduation> g, FlowPane pane) {
        g.ifPresentOrElse((Graduation grad) -> {
            pane.getChildren().add(new FieldHyperlink(grad.getFullString(), () -> {
                submitCommandCallback.accept("");
                // TODO: Add filter by grad command once it is implemented
            }));
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
    FlowPane getGraduation() {
        return graduation;
    }

    FlowPane getPriority() {
        return priority;
    }

    FlowPane getCourses() {
        return courses;
    }

    FlowPane getEmails() {
        return emails;
    }

    FlowPane getLinks() {
        return links;
    }

    FlowPane getPhones() {
        return phones;
    }

    FlowPane getSpecialisations() {
        return specialisations;
    }

    Label getName() {
        return name;
    }
}
