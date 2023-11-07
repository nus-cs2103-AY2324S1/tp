package seedu.address.ui;

import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import seedu.address.model.booking.Booking;
import seedu.address.model.booking.RoomTypeTag;

/**
 * A UI component that displays information of a {@code Person}.
 */
public class BookingCard extends UiPart<Region> {

    private static final String FXML = "PersonListCard.fxml";
    private static final List<Stage> popupStages = new ArrayList<>();

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Booking booking;

    @FXML
    private HBox cardPane;
    @FXML
    private Label room;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label phone;
    @FXML
    private Label bookingPeriod;
    @FXML
    private Label email;
    @FXML
    private Label remark;
    @FXML
    private ImageView flagImage;
    @FXML
    private FlowPane tags;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public BookingCard(Booking booking, int displayedIndex) {
        super(FXML);
        this.booking = booking;
        id.setText(displayedIndex + ". ");
        room.setText("Room " + booking.getRoom().value);
        bookingPeriod.setText(booking.getBookingPeriod().value);
        remark.setText(booking.getRemark().value);
        RoomTypeTag tag = booking.getTags();
        Label tagLabel = new Label(tag.roomTypeTagName);
        tagLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 14; -fx-background-color: #64a4db;"
                 + " -fx-background-radius: 15; -fx-padding: 5px;");
        tags.getChildren().add(tagLabel);

        // Adding spacing between tags and room
        FlowPane.setMargin(tagLabel, new Insets(0, 0, 0, 50)); // Adjust the insets as needed
    }

    public static void closeAllPopups() {
        for (Stage stage : popupStages) {
            stage.close();
        }
    }

    @FXML
    private void handleCardClick(MouseEvent event) {
        if (booking != null) {
            Stage existingPopup = findExistingPopup(booking);
            if (existingPopup != null) {
                // If a pop-up for this booking is already open, bring it to the front
                existingPopup.toFront();
            } else {
                // Create a new pop-up for the booking
                createNewPopup();
            }
        }
    }

    private Stage findExistingPopup(Booking booking) {
        // Check if a pop-up for the given booking already exists
        for (Stage popupStage : popupStages) {
            // Compare the booking information
            if (popupStage.getTitle().equals("Room " + booking.getRoom().value)) {
                return popupStage;
            }
        }
        return null;
    }

    private void createNewPopup() {
        if (booking != null) {
            // Create a VBox to hold the icons and set its style
            VBox popupRoot = new VBox();
            popupRoot.setStyle("-fx-background-color: #6ccaf0; -fx-padding: 10px;");

            // Set the icons for Name, Email, and Phone
            ImageView nameIcon = new ImageView(new Image("images/Name.png"));
            ImageView emailIcon = new ImageView(new Image("images/Email.png"));
            ImageView phoneIcon = new ImageView(new Image("images/Phone.png"));

            // Set the icon size
            nameIcon.setFitHeight(30);
            nameIcon.setFitWidth(30);
            emailIcon.setFitHeight(30);
            emailIcon.setFitWidth(30);
            phoneIcon.setFitHeight(30);
            phoneIcon.setFitWidth(30);

            // Create Labels and set the actual data for Name, Email, and Phone
            Label nameLabel = new Label(booking.getName().toString());
            Label phoneLabel = new Label(booking.getPhone().toString());
            Label emailLabel = new Label(booking.getEmail().toString());

            // Apply bold style, increase font size, and increase letter-spacing to the labels
            nameLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 14; -fx-text-letter-spacing: 1.5;");
            phoneLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 14; -fx-text-letter-spacing: 1.5;");
            emailLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 14; -fx-text-letter-spacing: 1.5;");

            // Create HBoxes for icons and labels with spacing
            HBox nameBox = new HBox(nameIcon, nameLabel);
            nameBox.setSpacing(10); // Adjust the spacing as needed

            HBox emailBox = new HBox(emailIcon, emailLabel);
            emailBox.setSpacing(10); // Adjust the spacing as needed

            HBox phoneBox = new HBox(phoneIcon, phoneLabel);
            phoneBox.setSpacing(10); // Adjust the spacing as needed

            // Add icon-label HBoxes to the VBox
            popupRoot.getChildren().addAll(nameBox, emailBox, phoneBox);

            // Create a Scene with the VBox and set its dimensions
            Scene scene = new Scene(popupRoot);

            Stage popupStage = new Stage();
            popupStage.setTitle("Room " + booking.getRoom().value);
            popupStage.setResizable(false);

            // Set the room image as the icon
            popupStage.getIcons().add(new Image("images/Room.png"));

            // Set a tooltip for the stage title to show the full room number
            Tooltip tooltip = new Tooltip("Room " + booking.getRoom().value);
            Tooltip.install(scene.getRoot(), tooltip); // Install tooltip on the scene root

            popupStage.setScene(scene);

            // Set a minimum width for the stage to accommodate the full title
            Text titleText = new Text(popupStage.getTitle());
            titleText.setFont(Font.getDefault());
            double titleWidth = titleText.getLayoutBounds().getWidth();
            popupStage.setMinWidth(titleWidth + 200); // Adjust the extra space as necessary

            popupStage.setOnCloseRequest(windowEvent -> {
                // Remove the closed stage from the list of pop-up stages
                popupStages.remove(popupStage);
            });

            // Add the newly created pop-up stage to the list of stages
            popupStages.add(popupStage);

            popupStage.show();
        }
    }

}
