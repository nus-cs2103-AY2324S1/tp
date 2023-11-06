package seedu.address.ui;

import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.testfx.framework.junit5.ApplicationTest;
import seedu.address.logic.Logic;
import seedu.address.logic.LogicManager;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.storage.JsonAddressBookStorage;
import seedu.address.storage.JsonTeamBookStorage;
import seedu.address.storage.JsonUserPrefsStorage;
import seedu.address.storage.StorageManager;

import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class MainWindowTest extends ApplicationTest {

    private MainWindow mainWindow;
    private Stage primaryStage;
    private Logic logic;

    @TempDir
    public Path temporaryFolder;

    @Override
    public void start(Stage stage) {
        Model model = new ModelManager();
        JsonAddressBookStorage addressBookStorage =
                new JsonAddressBookStorage(temporaryFolder.resolve("addressBook.json"));
        JsonTeamBookStorage teamBookStorage = new JsonTeamBookStorage(temporaryFolder.resolve("teamBook.json"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(temporaryFolder.resolve("userPrefs.json"));
        StorageManager storage = new StorageManager(addressBookStorage, teamBookStorage, userPrefsStorage);
        logic = new LogicManager(model, storage);

        primaryStage = stage;
        mainWindow = new MainWindow(primaryStage, logic);
        mainWindow.fillInnerParts(); // This method should initialize your UI components
    }

    @Test
    public void checkComponentsNotNull() {
        assertNotNull(mainWindow.getPersonListPanel());
        assertNotNull(mainWindow.getTeamListPanel());
    }
}
