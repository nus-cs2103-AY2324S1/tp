package seedu.address.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;
import seedu.address.model.tag.UniqueTagList;

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
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private Label email;
    @FXML
    private FlowPane tags;
    @FXML
    private Label linkedIn;
    @FXML
    private Label github;

    @FXML
    private Label score;


    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public PersonCard(Person person, int displayedIndex) {
        super(FXML);
        this.person = person;
        id.setText(displayedIndex + ". ");
        name.setText(person.getName().fullName);
        phone.setText(person.getPhone().value);
        address.setText(person.getAddress().value);
        email.setText(person.getEmail().value);
        linkedIn.setText(person.getLinkedIn().value);
        github.setText(person.getGithub().value);
        setTagLabel(person.getTags());
    }

    private void setTagLabel(Set<Tag> tagsSet) {
        List<String> tagCategories = new ArrayList<>();
        UniqueTagList uniqueTagList = new UniqueTagList();
        for (Tag tag : uniqueTagList.asUnmodifiableObservableList()) {
            if (!tagCategories.contains(tag.tagCategory) && !tag.tagCategory.equals("assessment")) {
                tagCategories.add(tag.tagCategory);
            }
        }

        for (Tag tag : tagsSet) {
            Label label = new Label(tag.tagName);

            if (tagCategories.indexOf(tag.tagCategory) == 0) {
                label.getStyleClass().add("label2");
            } else if (tagCategories.indexOf(tag.tagCategory) == 1) {
                label.getStyleClass().add("label3");
            } else if (tagCategories.indexOf(tag.tagCategory) == 2) {
                label.getStyleClass().add("label4");
            } else if (tagCategories.indexOf(tag.tagCategory) == 3) {
                label.getStyleClass().add("label5");
            } else if (tagCategories.indexOf(tag.tagCategory) == 4) {
                label.getStyleClass().add("label6");
            } else if (tagCategories.indexOf(tag.tagCategory) == 5) {
                label.getStyleClass().add("label7");
            } else { // uncategorised
                label.getStyleClass().add("label1");
            }
            tags.getChildren().add(label);
        }
    }
}
