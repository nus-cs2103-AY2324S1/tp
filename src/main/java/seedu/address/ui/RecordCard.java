package seedu.address.ui;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.stage.FileChooser;
import seedu.address.model.record.Record;

/**
 * An UI component that displays information of a {@code Record}.
 */
public class RecordCard extends UiPart<Region> {

    private static final String FXML = "RecordListCard.fxml";

    public final Record record;
    @FXML
    private HBox cardPane;
    @FXML
    private Label id;
    @FXML
    private Label dateTime;
    @FXML
    private Label condition;
    @FXML
    private Hyperlink filePathLabel;
    @FXML
    private Label medication;

    private int displayedIndex;
    private MainWindow mainWindow;

    /**
     * Creates a {@code RecordCard} with the given {@code Record} and index to
     * display.
     *
     * @param record         The record object to be displayed.
     * @param displayedIndex The index at which the record will be displayed.
     */
    public RecordCard(Record record, int displayedIndex) {
        super(FXML);
        this.record = record;
        id.setText(displayedIndex + ". ");
        dateTime.setText(record.getDateTime().toString());
        condition.setText(record.getConditions().toString());
        if (record.getFilePath() != null) {
            filePathLabel.setText(record.getFilePath().toString());
        } else {
            filePathLabel.setText("No file attached");
            filePathLabel.setDisable(true); // Disable the hyperlink if no file is attached
        }
        this.displayedIndex = displayedIndex;
        medication.setText(record.getMedications().toString());
        mainWindow = MainWindow.getInstance();
    }

    /**
     * Opens a file chooser dialog to attach a file to the record.
     *
     * @param event The event triggering this method call.
     */
    @FXML
    public void handleAttachFile(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select File to Attach");
        File file = fileChooser.showOpenDialog(cardPane.getScene().getWindow());

        if (file != null) {
            this.record.setFilePath(file.toPath(), displayedIndex);
            filePathLabel.setText(file.toPath().toString());
            mainWindow.setResultDisplay("File successfully attached!");
        } else {
            mainWindow.setResultDisplay("No file chosen");
        }

    }

    /**
     * Opens the file associated with the record if available.
     *
     * @param event The event triggering this method call.
     */
    @FXML
    public void handleOpenFile(ActionEvent event) {
        if (record.getFilePath() != null && Desktop.isDesktopSupported()) {
            try {
                Desktop.getDesktop().open(record.getFilePath().toFile());
                mainWindow.setResultDisplay("File successfully opened");
            } catch (IllegalArgumentException e) {
                // Handle the case where the file does not exist or is not valid.

                mainWindow.setResultDisplay("There was an issue with the file: " + e.getMessage());
            } catch (IOException e) {
                // Handle other I/O errors.
                e.printStackTrace();
            }
        }
    }

}
