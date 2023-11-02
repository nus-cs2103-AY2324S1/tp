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

        TableColumn<SecLevelTableCommandResult, Integer> sec1Column = new TableColumn<>("Sec 1");
        sec1Column.setCellValueFactory(new PropertyValueFactory<>("sec1Count"));
        TableColumn<SecLevelTableCommandResult, Integer> sec2Column = new TableColumn<>("Sec 2");
        sec2Column.setCellValueFactory(new PropertyValueFactory<>("sec2Count"));
        TableColumn<SecLevelTableCommandResult, Integer> sec3Column = new TableColumn<>("Sec 3");
        sec3Column.setCellValueFactory(new PropertyValueFactory<>("sec3Count"));
        TableColumn<SecLevelTableCommandResult, Integer> sec4Column = new TableColumn<>("Sec 4");
        sec4Column.setCellValueFactory(new PropertyValueFactory<>("sec4Count"));

        tableToCreate.getColumns().add(sec1Column);
        tableToCreate.getColumns().add(sec2Column);
        tableToCreate.getColumns().add(sec3Column);
        tableToCreate.getColumns().add(sec4Column);

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

        TableColumn<SubjectTableCommandResult, Integer> engColumn = new TableColumn<>(Subject.ENG);
        engColumn.setCellValueFactory(new PropertyValueFactory<>("engCount"));
        TableColumn<SubjectTableCommandResult, Integer> chiColumn = new TableColumn<>(Subject.CHI);
        chiColumn.setCellValueFactory(new PropertyValueFactory<>("chiCount"));
        TableColumn<SubjectTableCommandResult, Integer> emathColumn = new TableColumn<>(Subject.EMATH);
        emathColumn.setCellValueFactory(new PropertyValueFactory<>("emathCount"));
        TableColumn<SubjectTableCommandResult, Integer> amathColumn = new TableColumn<>(Subject.AMATH);
        amathColumn.setCellValueFactory(new PropertyValueFactory<>("amathCount"));
        TableColumn<SubjectTableCommandResult, Integer> phyColumn = new TableColumn<>(Subject.PHY);
        phyColumn.setCellValueFactory(new PropertyValueFactory<>("phyCount"));
        TableColumn<SubjectTableCommandResult, Integer> chemiColumn = new TableColumn<>(Subject.CHEMI);
        chemiColumn.setCellValueFactory(new PropertyValueFactory<>("chemiCount"));
        TableColumn<SubjectTableCommandResult, Integer> bioColumn = new TableColumn<>(Subject.BIO);
        bioColumn.setCellValueFactory(new PropertyValueFactory<>("bioCount"));
        TableColumn<SubjectTableCommandResult, Integer> geogColumn = new TableColumn<>(Subject.GEOG);
        geogColumn.setCellValueFactory(new PropertyValueFactory<>("geogCount"));
        TableColumn<SubjectTableCommandResult, Integer> histColumn = new TableColumn<>(Subject.HIST);
        histColumn.setCellValueFactory(new PropertyValueFactory<>("histCount"));
        TableColumn<SubjectTableCommandResult, Integer> socColumn = new TableColumn<>(Subject.SOC);
        socColumn.setCellValueFactory(new PropertyValueFactory<>("socCount"));

        tableToCreate.getColumns().add(engColumn);
        tableToCreate.getColumns().add(chiColumn);
        tableToCreate.getColumns().add(emathColumn);
        tableToCreate.getColumns().add(amathColumn);
        tableToCreate.getColumns().add(phyColumn);
        tableToCreate.getColumns().add(chemiColumn);
        tableToCreate.getColumns().add(bioColumn);
        tableToCreate.getColumns().add(geogColumn);
        tableToCreate.getColumns().add(histColumn);
        tableToCreate.getColumns().add(socColumn);

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

        TableColumn<EnrolDateTableCommandResult, Integer> janColumn = new TableColumn<>("Jan");
        janColumn.setCellValueFactory(new PropertyValueFactory<>("janCount"));
        TableColumn<EnrolDateTableCommandResult, Integer> febColumn = new TableColumn<>("Feb");
        febColumn.setCellValueFactory(new PropertyValueFactory<>("febCount"));
        TableColumn<EnrolDateTableCommandResult, Integer> marColumn = new TableColumn<>("Mar");
        marColumn.setCellValueFactory(new PropertyValueFactory<>("marCount"));
        TableColumn<EnrolDateTableCommandResult, Integer> aprColumn = new TableColumn<>("Apr");
        aprColumn.setCellValueFactory(new PropertyValueFactory<>("aprCount"));
        TableColumn<EnrolDateTableCommandResult, Integer> mayColumn = new TableColumn<>("May");
        mayColumn.setCellValueFactory(new PropertyValueFactory<>("mayCount"));
        TableColumn<EnrolDateTableCommandResult, Integer> junColumn = new TableColumn<>("Jun");
        junColumn.setCellValueFactory(new PropertyValueFactory<>("junCount"));
        TableColumn<EnrolDateTableCommandResult, Integer> julColumn = new TableColumn<>("Jul");
        julColumn.setCellValueFactory(new PropertyValueFactory<>("julCount"));
        TableColumn<EnrolDateTableCommandResult, Integer> augColumn = new TableColumn<>("Aug");
        augColumn.setCellValueFactory(new PropertyValueFactory<>("augCount"));
        TableColumn<EnrolDateTableCommandResult, Integer> sepColumn = new TableColumn<>("Sep");
        sepColumn.setCellValueFactory(new PropertyValueFactory<>("sepCount"));
        TableColumn<EnrolDateTableCommandResult, Integer> octColumn = new TableColumn<>("Oct");
        octColumn.setCellValueFactory(new PropertyValueFactory<>("octCount"));
        TableColumn<EnrolDateTableCommandResult, Integer> novColumn = new TableColumn<>("Nov");
        novColumn.setCellValueFactory(new PropertyValueFactory<>("novCount"));
        TableColumn<EnrolDateTableCommandResult, Integer> decColumn = new TableColumn<>("Dec");
        decColumn.setCellValueFactory(new PropertyValueFactory<>("decCount"));

        tableToCreate.getColumns().add(janColumn);
        tableToCreate.getColumns().add(febColumn);
        tableToCreate.getColumns().add(marColumn);
        tableToCreate.getColumns().add(aprColumn);
        tableToCreate.getColumns().add(mayColumn);
        tableToCreate.getColumns().add(junColumn);
        tableToCreate.getColumns().add(julColumn);
        tableToCreate.getColumns().add(augColumn);
        tableToCreate.getColumns().add(sepColumn);
        tableToCreate.getColumns().add(octColumn);
        tableToCreate.getColumns().add(novColumn);
        tableToCreate.getColumns().add(decColumn);

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
