//package seedu.address.logic.export;
//
//import javafx.application.Platform;
//import javafx.embed.swing.SwingFXUtils;
//import javafx.scene.SnapshotParameters;
//import javafx.scene.control.Alert;
//import javafx.scene.control.ButtonType;
//import javafx.scene.control.TableView;
//import javafx.scene.image.WritableImage;
//import javafx.stage.FileChooser;
//import seedu.address.logic.commands.CommandResult;
//
//import javax.imageio.ImageIO;
//import java.io.File;
//import java.io.IOException;
//import java.util.Optional;
//
//public class ExportTable {
//    private TableView<? extends CommandResult> table;
//
//    /**
//     * Displays a file chooser dialog for saving the current table as a PNG image.
//     * If the selected file already exists, prompts the user for confirmation before overwriting.
//     * The image is saved after a short delay to allow JavaFX to properly render the chart.
//     */
//    public void exportAsPng() {
//        FileChooser fileChooser = new FileChooser();
//        fileChooser.setInitialFileName("table.png");
//        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PNG Files", "*.png"));
//        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
//
//        File file = fileChooser.showSaveDialog(super.getRoot());
//
//        if (file != null) {
//            if (file.exists()) {
//                // File already exists, prompt user to confirm overwrite
//                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//                alert.setTitle("Confirmation");
//                alert.setHeaderText("File Already Exists");
//                alert.setContentText("The file already exists. Do you want to overwrite it?");
//
//                Optional<ButtonType> result = alert.showAndWait();
//                if (result.isPresent() && result.get() == ButtonType.OK) {
//                    // User confirmed, proceed with saving the image after a delay
//                    Platform.runLater(() -> saveImage(file));
//                }
//            } else {
//                // File doesn't exist, directly save the image after a delay
//                Platform.runLater(() -> saveImage(file));
//            }
//        }
//    }
//
//    /**
//     * Saves the current state of the table as a PNG image to the specified file.
//     * The method captures a snapshot of the table after a delay and writes it to the file.
//     *
//     * @param file The file where the table image will be saved.
//     */
//    private void saveImage(File file) {
//        WritableImage image = table.snapshot(new SnapshotParameters(), null);
//        try {
//            ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
//            System.out.println("Image saved successfully.");
//        } catch (IOException e) {
//            // Handle IO exception, e.g., show an error dialog or log the exception
//            e.printStackTrace();
//            // Display an error dialog to the user
//            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
//            errorAlert.setTitle("Error");
//            errorAlert.setHeaderText("Error Saving Image");
//            errorAlert.setContentText("An error occurred while saving the image. Please try again.");
//            errorAlert.showAndWait();
//        }
//    }
//}
