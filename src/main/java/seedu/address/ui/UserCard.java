package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.model.person.Person;

/**
 * Represents a user card in the GUI.
 * Displays the user's name, phone number, address, email, free times, and tags.
 */
public class UserCard extends UiPart<Region> {

    private static final String FXML = "UserCard.fxml";

    public final Person user;

    @FXML
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
     * Creates a new UserCard with the given Person object.
     * @param user The Person object to display in the card.
     */
    public UserCard(Person user) {
        super(FXML);
        this.user = user;
        userName.setText(user.getName().fullName);
        phone.setText(user.getPhone().value);
        address.setText(user.getAddress().value);
        email.setText(user.getEmail().value);
        birthday.setText("Birthday: " + user.getBirthday().toString());
        user.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));

        user.getSchedule().getScheduleForDayOfWeek(1).stream()
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

        user.getSchedule().getScheduleForDayOfWeek(2).stream()
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

        user.getSchedule().getScheduleForDayOfWeek(3).stream()
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

        user.getSchedule().getScheduleForDayOfWeek(4).stream()
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

        user.getSchedule().getScheduleForDayOfWeek(5).stream()
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

        user.getSchedule().getScheduleForDayOfWeek(6).stream()
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

        user.getSchedule().getScheduleForDayOfWeek(7).stream()
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
