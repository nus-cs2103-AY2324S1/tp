package seedu.address.ui;

import java.io.File;
import java.io.IOException;
import java.util.Optional;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.tableresults.EnrolDateTableCommandResult;
import seedu.address.logic.commands.tableresults.GenderTableCommandResult;
import seedu.address.logic.commands.tableresults.SecLevelTableCommandResult;
import seedu.address.logic.commands.tableresults.SubjectTableCommandResult;
import seedu.address.model.ModelManager;
import seedu.address.model.tag.Subject;

/**
 * Controller of a table page.
 */
public class TableWindow extends UiPart<Stage> {
    public static final String FXML = "TableWindow.fxml";
    private static final Logger logger = LogsCenter.getLogger(TableWindow.class);

    @FXML
    private TableView<? extends CommandResult> table;

    /**
     * Constructor for creating TableWindow instance.
     * @param commandResult Table command result instance containing column titles and values.
     */
    public TableWindow(CommandResult commandResult) {
        super(FXML, new Stage());
        table = createTable(commandResult);
        ModelManager.getTable(this);
        initialize();
    }

    /**
     * Creates a table with table command result instance containing
     * given column titles and values.
     */
    public static TableView<? extends CommandResult> createTable(CommandResult commandResult) {
        if (commandResult instanceof GenderTableCommandResult) {
            GenderTableCommandResult genderTableCommandResult = (GenderTableCommandResult) commandResult;
            return createGenderTable(genderTableCommandResult);
        } else if (commandResult instanceof SecLevelTableCommandResult) {
            SecLevelTableCommandResult secLevelTableCommandResult = (SecLevelTableCommandResult) commandResult;
            return createSecLevelTable(secLevelTableCommandResult);
        } else if (commandResult instanceof SubjectTableCommandResult) {
            SubjectTableCommandResult subjectTableCommandResult = (SubjectTableCommandResult) commandResult;
            return createSubjectTable(subjectTableCommandResult);
        } else {
            EnrolDateTableCommandResult enrolDateTableCommandResult = (EnrolDateTableCommandResult) commandResult;
            return createEnrolDateTable(enrolDateTableCommandResult);
        }
    }

    /**
     * Create a table with GenderTableCommandResult instance containing counts for each gender.
     * @param commandResult GenderTableCommandResult containing column titles and counts mapping.
     * @return a TableView instance generated with given column titles and counts from argument passed in.
     */
    private static TableView<GenderTableCommandResult> createGenderTable(GenderTableCommandResult commandResult) {
        TableView<GenderTableCommandResult> tableToCreate = new TableView<>();

        TableColumn<GenderTableCommandResult, Integer> maleColumn = new TableColumn<>("Male");
        maleColumn.setCellValueFactory(new PropertyValueFactory<>("maleCount"));
        TableColumn<GenderTableCommandResult, Integer> femaleColumn = new TableColumn<>("Female");
        femaleColumn.setCellValueFactory(new PropertyValueFactory<>("femaleCount"));

        tableToCreate.getColumns().add(maleColumn);
        tableToCreate.getColumns().add(femaleColumn);

        tableToCreate.getItems().add(commandResult);

        tableToCreate.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        return tableToCreate;
    }

    /**
     * Create a table with SecLevelTableCommandResult instance containing counts for each sec level.
     * @param commandResult SecLevelTableCommand instance containing column titles and counts mapping.
     * @return a TableView instance generated with given column titles and counts from argument passed in.
     */
    private static TableView<SecLevelTableCommandResult> createSecLevelTable(SecLevelTableCommandResult commandResult) {
        TableView<SecLevelTableCommandResult> tableToCreate = new TableView<>();
        String[] columnNames = new String[] {"Sec 1", "Sec 2", "Sec 3", "Sec 4"};
        String[] fieldNames = new String[] {"sec1Count", "sec2Count", "sec3Count", "sec4Count"};

        for (int i = 0; i < fieldNames.length; i++) {
            TableColumn<SecLevelTableCommandResult, Integer> secColumn = new TableColumn<>(columnNames[i]);
            secColumn.setCellValueFactory(new PropertyValueFactory<>(fieldNames[i]));
            tableToCreate.getColumns().add(secColumn);
        }

        tableToCreate.getItems().add(commandResult);

        tableToCreate.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        return tableToCreate;
    }

    /**
     * Create a table with SubjectTableCommandResult instance containing counts for each subject
     * @param commandResult SubjectTableCommandResult instance containing column titles and counts mapping.
     * @return a TableView instance generated with given column titles and counts from argument passed in.
     */
    private static TableView<SubjectTableCommandResult> createSubjectTable(SubjectTableCommandResult commandResult) {
        TableView<SubjectTableCommandResult> tableToCreate = new TableView<>();
        String[] columnNames = new String[] {Subject.ENG, Subject.CHI, Subject.EMATH, Subject.AMATH, Subject.PHY,
                                                Subject.CHEMI, Subject.BIO, Subject.GEOG, Subject.HIST, Subject.SOC};
        String[] fieldNames = new String[] {"engCount", "chiCount", "emathCount", "amathCount", "phyCount",
                                            "chemiCount", "bioCount", "geogCount", "histCount", "socCount"};

        for (int i = 0; i < fieldNames.length; i++) {
            TableColumn<SubjectTableCommandResult, Integer> subjectColumn = new TableColumn<>(columnNames[i]);
            subjectColumn.setCellValueFactory(new PropertyValueFactory<>(fieldNames[i]));
            tableToCreate.getColumns().add(subjectColumn);
        }

        tableToCreate.getItems().add(commandResult);

        tableToCreate.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        return tableToCreate;
    }

    /**
     * Create a table with EnrolDateTableCommandResult instance containing counts for each month
     * @param commandResult EnrolDateTableCommandResult instance containing column titles and counts mapping.
     * @return a TableView instance generated with given column titles and counts from argument passed in.
     */
    private static TableView<EnrolDateTableCommandResult> createEnrolDateTable(EnrolDateTableCommandResult
                                                                                       commandResult) {
        TableView<EnrolDateTableCommandResult> tableToCreate = new TableView<>();
        String[] columnNames = new String[] {"Jan", "Feb", "Mar", "Apr", "May", "Jun",
                                                "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        String[] fieldNames = new String[] {"janCount", "febCount", "marCount", "aprCount", "mayCount", "junCount",
                                            "julCount", "augCount", "sepCount", "octCount", "novCount", "decCount"};

        for (int i = 0; i < fieldNames.length; i++) {
            TableColumn<EnrolDateTableCommandResult, Integer> enrolDateColumn = new TableColumn<>(columnNames[i]);
            enrolDateColumn.setCellValueFactory(new PropertyValueFactory<>(fieldNames[i]));
            tableToCreate.getColumns().add(enrolDateColumn);
        }

        tableToCreate.getItems().add(commandResult);

        tableToCreate.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        return tableToCreate;
    }

    /**
     * Set up the root control, scene and css for the table window.
     */
    public void initialize() {
        BorderPane root = new BorderPane();
        root.setCenter(table);

        Scene scene = new Scene(root, 500, 300);
        scene.getStylesheets().add("resources/view/TableWindow.css");
        super.getRoot().setScene(scene);
    }

    /**
     * Shows the table window.
     */
    public void show() {
        logger.fine("Showing statistical table in another window.");
        super.getRoot().show();
        super.getRoot().centerOnScreen();
    }

    /**
     * Returns true if the help window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Focuses on the table window.
     */
    public void focus() {
        getRoot().requestFocus();
    }


    /**
     * Displays a file chooser dialog for saving the current table as a PNG image.
     * If the selected file already exists, prompts the user for confirmation before overwriting.
     * The image is saved after a short delay to allow JavaFX to properly render the chart.
     */
    public void exportAsPng() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialFileName("table.png");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PNG Files", "*.png"));
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));

        File file = fileChooser.showSaveDialog(super.getRoot());

        if (file != null) {
            if (file.exists()) {
                // File already exists, prompt user to confirm overwrite
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation");
                alert.setHeaderText("File Already Exists");
                alert.setContentText("The file already exists. Do you want to overwrite it?");

                Optional<ButtonType> result = alert.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK) {
                    // User confirmed, proceed with saving the image after a delay
                    Platform.runLater(() -> saveImage(file));
                }
            } else {
                // File doesn't exist, directly save the image after a delay
                Platform.runLater(() -> saveImage(file));
            }
        }
    }

    /**
     * Saves the current state of the table as a PNG image to the specified file.
     * The method captures a snapshot of the table after a delay and writes it to the file.
     *
     * @param file The file where the table image will be saved.
     */
    private void saveImage(File file) {
        WritableImage image = table.snapshot(new SnapshotParameters(), null);
        try {
            ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
            System.out.println("Image saved successfully.");
        } catch (IOException e) {
            // Handle IO exception, e.g., show an error dialog or log the exception
            e.printStackTrace();
            // Display an error dialog to the user
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("Error");
            errorAlert.setHeaderText("Error Saving Image");
            errorAlert.setContentText("An error occurred while saving the image. Please try again.");
            errorAlert.showAndWait();
        }
    }
}
