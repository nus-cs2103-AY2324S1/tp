package seedu.address.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Person;
import seedu.address.model.person.Status;
import seedu.address.model.person.StatusTypes;
import seedu.address.model.tag.Tag;
import seedu.address.model.tag.UniqueTagList;

/**
 * An UI component that displays information of a {@code Person}
 */
public class PersonInformationPanel extends UiPart<Region> {
    private static final String FXML = "PersonInformationPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(PersonListPanel.class);
    private final Person person;

    @FXML
    private Label name;
    @FXML
    private FlowPane tags;
    @FXML
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private Label email;
    @FXML
    private Label remark;
    @FXML
    private ToggleButton preliminary;
    @FXML
    private ToggleButton interviewed;
    @FXML
    private ToggleButton status;
    @FXML
    private Label linkedIn;
    @FXML
    private Label github;


    /**
     * Creates a {@code PersonInformationPanel} with the given {@code Person}.
     * @param person the person to display
     */
    public PersonInformationPanel(Person person) {
        super(FXML);
        this.person = person;
        name.setText(person.getName().fullName);
        phone.setText(person.getPhone().value);
        address.setText(person.getAddress().value);
        email.setText(person.getEmail().value);
        remark.setText(person.getRemark().value);
        setTagLabel(person.getTags());
        linkedIn.setText(person.getLinkedIn().value);
        github.setText(person.getGithub().value);
        setResultButton(person.getStatus(), status);
        setButton(person.getStatus());
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

    private static void setResultButton(Status status, ToggleButton statusButton) {
        StatusTypes statusType = status.getStatusType();
        if (statusType == StatusTypes.OFFERED) {
            statusButton.setText("Offered");
        } else if (statusType == StatusTypes.REJECTED) {
            statusButton.setText("Rejected");
        } else {
            statusButton.setText("Pending");
        }
    }

    private void setButton(Status currentStatus) {
        StatusTypes statusType = currentStatus.getStatusType();
        if (statusType == StatusTypes.OFFERED) {
            status.getStyleClass().clear();
            status.getStyleClass().add("offered-button");

            preliminary.getStyleClass().clear();;
            preliminary.getStyleClass().add("offered-button");

            interviewed.getStyleClass().clear();
            interviewed.getStyleClass().add("offered-button");
        } else if (statusType == StatusTypes.REJECTED) {
            status.getStyleClass().clear();
            status.getStyleClass().add("rejected-button");
        } else if (statusType == StatusTypes.INTERVIEWED) {
            preliminary.getStyleClass().clear();;
            preliminary.getStyleClass().add("offered-button");

            interviewed.getStyleClass().clear();
            interviewed.getStyleClass().add("offered-button");
        } else {
            preliminary.getStyleClass().clear();;
            preliminary.getStyleClass().add("offered-button");

        }
    }






}
