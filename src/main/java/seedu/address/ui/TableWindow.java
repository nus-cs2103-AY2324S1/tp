package seedu.address.ui;


import javafx.fxml.FXML;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.scene.Scene;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.GenderTableCommandResult;
import seedu.address.logic.commands.SecLevelTableCommandResult;
import seedu.address.logic.commands.SubjectTableCommandResult;
import seedu.address.model.person.Gender;
import seedu.address.model.tag.Subject;

public class TableWindow extends UiPart<Stage> {
    public static final String FXML = "TableWindow.fxml";
    private CommandResult commandResult;

    @FXML
    private TableView<? extends CommandResult> table;

    public TableWindow(CommandResult commandResult) {
        super(FXML, new Stage());
        this.commandResult = commandResult;
        table = createTable(commandResult);
        initialize();
    }

    public void initialize() {
        BorderPane root = new BorderPane();
        root.setCenter(table);

        Scene scene = new Scene(root, 500, 300);
        scene.getStylesheets().add("resources/view/TableWindow.css");
        super.getRoot().setScene(scene);
    }


    /**
     * Creates a table with the given column titles and values.
     */
    private TableView<? extends CommandResult> createTable(CommandResult commandResult) {
        if (commandResult instanceof GenderTableCommandResult) {
            GenderTableCommandResult genderTableCommandResult = (GenderTableCommandResult) commandResult;
            return createGenderTable(genderTableCommandResult);
        } else if (commandResult instanceof SecLevelTableCommandResult) {
            SecLevelTableCommandResult secLevelTableCommandResult = (SecLevelTableCommandResult) commandResult;
            return createSecLevelTable(secLevelTableCommandResult);
        } else {
            SubjectTableCommandResult subjectTableCommandResult = (SubjectTableCommandResult) commandResult;
            return createSubjectTable(subjectTableCommandResult);
        }
    }

    private TableView<GenderTableCommandResult> createGenderTable(GenderTableCommandResult commandResult) {
        TableView<GenderTableCommandResult> tableToCreate = new TableView<>();

        TableColumn<GenderTableCommandResult, Long> maleColumn = new TableColumn<>("Male");
        maleColumn.setCellValueFactory(new PropertyValueFactory<>("maleCount"));
        TableColumn<GenderTableCommandResult, Long> femaleColumn = new TableColumn<>("Female");
        maleColumn.setCellValueFactory(new PropertyValueFactory<>("femaleCount"));

        tableToCreate.getColumns().add(maleColumn);
        tableToCreate.getColumns().add(femaleColumn);

        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        return tableToCreate;
    }

    private TableView<SecLevelTableCommandResult> createSecLevelTable(SecLevelTableCommandResult commandResult) {
        TableView<SecLevelTableCommandResult> tableToCreate = new TableView<>();

        TableColumn<SecLevelTableCommandResult, Long> sec1Column = new TableColumn<>("Sec 1");
        sec1Column.setCellValueFactory(new PropertyValueFactory<>("sec1Count"));
        TableColumn<SecLevelTableCommandResult, Long> sec2Column = new TableColumn<>("Sec 2");
        sec2Column.setCellValueFactory(new PropertyValueFactory<>("sec2Count"));
        TableColumn<SecLevelTableCommandResult, Long> sec3Column = new TableColumn<>("Sec 3");
        sec3Column.setCellValueFactory(new PropertyValueFactory<>("sec3Count"));
        TableColumn<SecLevelTableCommandResult, Long> sec4Column = new TableColumn<>("Sec 4");
        sec4Column.setCellValueFactory(new PropertyValueFactory<>("sec4Count"));

        tableToCreate.getColumns().add(sec1Column);
        tableToCreate.getColumns().add(sec2Column);
        tableToCreate.getColumns().add(sec3Column);
        tableToCreate.getColumns().add(sec4Column);

        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        return tableToCreate;
    }

    private TableView<SubjectTableCommandResult> createSubjectTable(SubjectTableCommandResult commandResult) {
        TableView<SubjectTableCommandResult> tableToCreate = new TableView<>();

        TableColumn<SubjectTableCommandResult, Long> csColumn = new TableColumn<>(Subject.CS);
        csColumn.setCellValueFactory(new PropertyValueFactory<>("csCount"));
        TableColumn<SubjectTableCommandResult, Long> mathsColumn = new TableColumn<>(Subject.MATHS);
        mathsColumn.setCellValueFactory(new PropertyValueFactory<>("mathsCount"));
        TableColumn<SubjectTableCommandResult, Long> phyColumn = new TableColumn<>(Subject.PHY);
        phyColumn.setCellValueFactory(new PropertyValueFactory<>("phyCount"));
        TableColumn<SubjectTableCommandResult, Long> chemiColumn = new TableColumn<>(Subject.CHEMI);
        chemiColumn.setCellValueFactory(new PropertyValueFactory<>("chemiCount"));
        TableColumn<SubjectTableCommandResult, Long> bioColumn = new TableColumn<>(Subject.BIO);
        bioColumn.setCellValueFactory(new PropertyValueFactory<>("bioCount"));
        TableColumn<SubjectTableCommandResult, Long> engColumn = new TableColumn<>(Subject.ENG);
        engColumn.setCellValueFactory(new PropertyValueFactory<>("engCount"));

        tableToCreate.getColumns().add(csColumn);
        tableToCreate.getColumns().add(mathsColumn);
        tableToCreate.getColumns().add(phyColumn);
        tableToCreate.getColumns().add(chemiColumn);
        tableToCreate.getColumns().add(bioColumn);
        tableToCreate.getColumns().add(engColumn);

        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        return tableToCreate;
    }

    /**
     * Shows the table window.
     */
    public void show() {
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


}
