package seedu.address.ui;


import javafx.fxml.FXML;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class TableWindow extends UiPart<Stage> {
    public static final String FXML = "TableWindow.fxml";
    private String[] columnTitles;
    private Long[] columnValues;

    @FXML
    private TableView<Long> table;

    public TableWindow(String[] columnTitles, Long[] columnValues) {
        super(FXML, new Stage());
        this.columnTitles = columnTitles;
        this.columnValues = columnValues;
        table = createTable();
        Scene scene = new Scene(table);
        super.getRoot().setScene(scene);
    }



    /**
     * Creates a table with the given column titles and values.
     */
    private TableView<Long> createTable() {
        for (int i = 0; i < columnTitles.length; i++) {
            // declare temp here because the following lambda needs effectively final
            int temp = i;
            TableColumn<Long, Long> column = new TableColumn<>(columnTitles[i]);
            column.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(columnValues[temp]));
            table.getColumns().add(column);
        }
        return table;
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
