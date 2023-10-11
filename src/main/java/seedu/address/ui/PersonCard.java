package seedu.address.ui;


import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import seedu.address.model.person.Person;

/**
 * A UI component that displays information of a {@code Person}.
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
    private VBox fields;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private FlowPane tags;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public PersonCard(Person person, int displayedIndex, String[] displayFields) {
        super(FXML);
        this.person = person;
        id.setText(displayedIndex + ". ");
        name.setText(person.getName().fullName);
        for (String field : displayFields) {
            build(field);
        }
    }
    void build(String field) {
        switch (field) {
        case "phone":
            buildPhone();
            break;
        case "address":
            buildAddress();
            break;
        case "email":
            buildEmail();
            break;
        case "tags":
            buildTags();
            break;
        case "subjects":
            buildSubjects();
            break;
        default:
            break;
        }
    }

    void buildPhone() {
        Label phone = new Label(person.getPhone().value);
        phone.getStyleClass().add("cell_small_label");
        fields.getChildren().add(phone);
    }

    void buildAddress() {
        Label address = new Label(person.getAddress().value);
        address.getStyleClass().add("cell_small_label");
        fields.getChildren().add(address);
    }

    void buildEmail() {
        Label email = new Label(person.getEmail().value);
        email.getStyleClass().add("cell_small_label");
        fields.getChildren().add(email);
    }

    void buildTags() {
        //why creating a new pane tags and push to fields does not work?
        person.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
    }
    void buildSubjects() {
        FlowPane subjects = new FlowPane();
        person.getSubjects().stream()
                .sorted(Comparator.comparing(subject -> subject.subjectName))
                .forEach(subject -> subjects.getChildren()
                        .add(new ColoredTextEntry(subject.subjectName.toString(), subject.colour)));
        subjects.setHgap(10);
        fields.getChildren().add(subjects);
    }

    /**
     * A custom UI component that displays a colored text entry.
     */
    public class ColoredTextEntry extends StackPane {
        /**
         * Creates a colored text entry.
         * @param text The text content to display.
         * @param color The color of the background fillout.
         */
        public ColoredTextEntry(String text, String color) {
            Text textNode = new Text(text);
            textNode.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
            Rectangle rectangle = new Rectangle(textNode.getLayoutBounds().getWidth() + 10,
                    textNode.getLayoutBounds().getHeight() + 10);
            rectangle.setArcWidth(20); // Customize the arc width to make it curved.
            rectangle.setArcHeight(20); // Customize the arc height to make it curved.
            rectangle.setFill(Color.web(color));

            getChildren().addAll(rectangle, textNode);
        }
    }

}
