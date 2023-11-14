package seedu.address.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.group.Group;
import seedu.address.model.person.Person;

/**
 * An UI component that displays information of a {@code Group}.
 */
public class GroupCard extends UiPart<Region> {

    private static final String FXML = "GroupListCard.fxml";
    private static final Logger logger = Logger.getLogger(GroupCard.class.getName());
    public final Group group;

    @FXML
    private HBox cardPane;
    @FXML
    private Label id; // No need to change the id label style
    @FXML
    private Label number;
    @FXML
    private HBox tutorialBox;
    @FXML
    private Label tutorial;
    @FXML
    private Label member1;
    @FXML
    private Label member2;
    @FXML
    private Label member3;
    @FXML
    private Label member4;
    @FXML
    private Label member5;
    @FXML
    private List<Label> members = List.of(member1, member2, member3, member4, member5);

    /**
     * Creates a {@code GroupCard} with the given {@code Group} and index to display.
     */
    public GroupCard(Group group, int displayedIndex) {
        super(FXML);
        this.group = group;
        id.setText(displayedIndex + ". ");
        id.setStyle("-fx-font-size: 17px; -fx-text-fill: #E7BE34; -fx-font-family: 'Arial'; -fx-font-weight: bold");

        assert number != null : "Number is null";
        number.setText("Group " + group.getNumber() + " ");
        number.setStyle("-fx-font-size: 17px; -fx-text-fill: #E7BE34; -fx-font-family: 'Arial'; "
                + "-fx-font-weight: bold;");
        // Apply the same style as id

        tutorialBox.setStyle("-fx-background-color: rgba(101,152,60,0.82); -fx-background-radius: 10;");
        tutorial.setText("T" + group.getTutorial().getValue());
        tutorial.setStyle("-fx-font-size: 14px; -fx-text-fill: white;");

        int groupSize = group.getMembers().size();
        List<Person> memberList = new ArrayList<>(group.getMembers());
        for (int i = 0; i < 5; i++) {
            if (i < groupSize) {
                Person member = memberList.get(i);
                members.get(i).setText((i + 1) + ". " + member.getName() + " (" + member.getEmail() + ")");
            } else {
                members.get(i).setText("");
            }
        }
    }

    // Getter methods for testing
    public String getId() {
        return id.getId();
    }

    public String getNumber() {
        return number.getText();
    }

    public String getTutorial() {
        return tutorial.getText();
    }

    public String getMember1() {
        return member1.getText();
    }

    public String getMember2() {
        return member2.getText();
    }

    public String getMember3() {
        return member3.getText();
    }

    public String getMember4() {
        return member4.getText();
    }

    public String getMember5() {
        return member5.getText();
    }
}
