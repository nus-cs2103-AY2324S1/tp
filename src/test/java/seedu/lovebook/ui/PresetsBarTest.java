package seedu.lovebook.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class PresetsBarTest extends ApplicationTest {
    private PresetsBar presetsBar;
    @Override
    public void start(Stage stage) throws Exception {
        StubCommandExecutor commandExecutor = new StubCommandExecutor();
        CommandBox commandBox = new CommandBox(commandExecutor);
        this.presetsBar = new PresetsBar(commandBox);
        stage.setScene(new Scene(this.presetsBar.getRoot()));
        stage.show();
    }

    @Test
    public void handleClearTest() {
        clickOn("#clearButton");
        String expected = "";
        assertEquals(expected, presetsBar.getCommandBox().getCommandTextField());
    }

    @Test
    public void handleAddDTest() {
        clickOn("#addButton");
        String expected = "add name/NAME age/AGE gender/GENDER height/HEIGHT income/INCOME horoscope/HOROSCOPE";
        assertEquals(expected, presetsBar.getCommandBox().getCommandTextField());
    }

    @Test
    public void handleDeleteDTest() {
        clickOn("#deleteButton");
        String expected = "delete INDEX";
        assertEquals(expected, presetsBar.getCommandBox().getCommandTextField());
    }

    @Test
    public void handleShowPreferencesTest() {
        clickOn("#showPrefButton");
        String expected = "showP";
        assertEquals(expected, presetsBar.getCommandBox().getCommandTextField());
    }
}
