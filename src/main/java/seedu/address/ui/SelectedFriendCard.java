package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.model.person.Person;

/**
 * The {@code SelectedFriendCard} class represents a card that displays detailed information
 * about a selected friend (person).
 * It is typically used to display the information of a friend when selected in the user interface.
 */
public class SelectedFriendCard extends UiPart<Region> {
    private static final String FXML = "SelectedFriendCard.fxml";

    public final Person selectedFriend;

    @javafx.fxml.FXML
    private Label userName;
    @FXML
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private Label email;
    @FXML
    private Label birthday;
    @FXML
    private FlowPane tags;
    @FXML
    private VBox mondaySchedule;
    @FXML
    private VBox tuesdaySchedule;
    @FXML
    private VBox wednesdaySchedule;
    @FXML
    private VBox thursdaySchedule;
    @FXML
    private VBox fridaySchedule;
    @FXML
    private VBox saturdaySchedule;
    @FXML
    private VBox sundaySchedule;

    /**
     * Constructs a new {@code SelectedFriendCard} with the specified selected friend.
     *
     * @param selectedFriend The selected friend whose information is to be displayed on this card.
     */
    public SelectedFriendCard(Person selectedFriend) {
        super(FXML);
        this.selectedFriend = selectedFriend;
        userName.setText(selectedFriend.getName().fullName);
        phone.setText(selectedFriend.getPhone().value);
        address.setText(selectedFriend.getAddress().value);
        email.setText(selectedFriend.getEmail().value);
        birthday.setText("Birthday: " + selectedFriend.getBirthday().toString());

        selectedFriend.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));

        selectedFriend.getSchedule().getScheduleForDayOfWeek(1).stream()
                .sorted(Comparator.comparing(timeBlock -> timeBlock.getTimeBlockString()))
                .forEach(timeBlock -> {
                    Label label = new Label(timeBlock.getName() + "\n"
                            + timeBlock.getStartTime() + "-" + timeBlock.getEndTime());

                    mondaySchedule.getChildren().add(label);

                    switch (timeBlock.getType()) {
                    case "CCA":
                        label.getStyleClass().add("cca-time-block");
                        break;
                    case "Module":
                        label.getStyleClass().add("module-time-block");
                        break;
                    case "Event":
                        label.getStyleClass().add("event-time-block");
                        break;
                    default:
                        break;
                    }
                });

        selectedFriend.getSchedule().getScheduleForDayOfWeek(2).stream()
            .sorted(Comparator.comparing(timeBlock -> timeBlock.getTimeBlockString()))
            .forEach(timeBlock -> {
                Label label = new Label(timeBlock.getName() + "\n"
                    + timeBlock.getStartTime() + "-" + timeBlock.getEndTime());

                tuesdaySchedule.getChildren().add(label);

                switch (timeBlock.getType()) {
                case "CCA":
                    label.getStyleClass().add("cca-time-block");
                    break;
                case "Module":
                    label.getStyleClass().add("module-time-block");
                    break;
                case "Event":
                    label.getStyleClass().add("event-time-block");
                    break;
                default:
                    break;
                }
            });

        selectedFriend.getSchedule().getScheduleForDayOfWeek(3).stream()
            .sorted(Comparator.comparing(timeBlock -> timeBlock.getTimeBlockString()))
            .forEach(timeBlock -> {
                Label label = new Label(timeBlock.getName() + "\n"
                        + timeBlock.getStartTime() + "-" + timeBlock.getEndTime());

                wednesdaySchedule.getChildren().add(label);

                switch (timeBlock.getType()) {
                case "CCA":
                    label.getStyleClass().add("cca-time-block");
                    break;
                case "Module":
                    label.getStyleClass().add("module-time-block");
                    break;
                case "Event":
                    label.getStyleClass().add("event-time-block");
                    break;
                default:
                    break;
                }
            });

        selectedFriend.getSchedule().getScheduleForDayOfWeek(4).stream()
            .sorted(Comparator.comparing(timeBlock -> timeBlock.getTimeBlockString()))
            .forEach(timeBlock -> {
                Label label = new Label(timeBlock.getName() + "\n"
                        + timeBlock.getStartTime() + "-" + timeBlock.getEndTime());

                thursdaySchedule.getChildren().add(label);

                switch (timeBlock.getType()) {
                case "CCA":
                    label.getStyleClass().add("cca-time-block");
                    break;
                case "Module":
                    label.getStyleClass().add("module-time-block");
                    break;
                case "Event":
                    label.getStyleClass().add("event-time-block");
                    break;
                default:
                    break;
                }
            });

        selectedFriend.getSchedule().getScheduleForDayOfWeek(5).stream()
            .sorted(Comparator.comparing(timeBlock -> timeBlock.getTimeBlockString()))
            .forEach(timeBlock -> {
                Label label = new Label(timeBlock.getName() + "\n"
                        + timeBlock.getStartTime() + "-" + timeBlock.getEndTime());

                fridaySchedule.getChildren().add(label);

                switch (timeBlock.getType()) {
                case "CCA":
                    label.getStyleClass().add("cca-time-block");
                    break;
                case "Module":
                    label.getStyleClass().add("module-time-block");
                    break;
                case "Event":
                    label.getStyleClass().add("event-time-block");
                    break;
                default:
                    break;
                }
            });

        selectedFriend.getSchedule().getScheduleForDayOfWeek(6).stream()
                .sorted(Comparator.comparing(timeBlock -> timeBlock.getTimeBlockString()))
                .forEach(timeBlock -> {
                    Label label = new Label(timeBlock.getName() + "\n"
                            + timeBlock.getStartTime() + "-" + timeBlock.getEndTime());

                    saturdaySchedule.getChildren().add(label);

                    switch (timeBlock.getType()) {
                    case "CCA":
                        label.getStyleClass().add("cca-time-block");
                        break;
                    case "Module":
                        label.getStyleClass().add("module-time-block");
                        break;
                    case "Event":
                        label.getStyleClass().add("event-time-block");
                        break;
                    default:
                        break;
                    }
                });

        selectedFriend.getSchedule().getScheduleForDayOfWeek(7).stream()
            .sorted(Comparator.comparing(timeBlock -> timeBlock.getTimeBlockString()))
            .forEach(timeBlock -> {
                Label label = new Label(timeBlock.getName() + "\n"
                        + timeBlock.getStartTime() + "-" + timeBlock.getEndTime());

                sundaySchedule.getChildren().add(label);

                switch (timeBlock.getType()) {
                case "CCA":
                    label.getStyleClass().add("cca-time-block");
                    break;
                case "Module":
                    label.getStyleClass().add("module-time-block");
                    break;
                case "Event":
                    label.getStyleClass().add("event-time-block");
                    break;
                default:
                    break;
                }
            });
    }
}
