package swe.context.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Path;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import swe.context.commons.core.GuiSettings;
import swe.context.model.ReadOnlyContacts;
import swe.context.model.ReadOnlySettings;
import swe.context.model.Settings;
import swe.context.testutil.TestData;



public class StorageManagerTest {
    @TempDir
    public static Path tempDir;

    private StorageManager manager;

    public StorageManagerTest() {
        this.manager = new StorageManager(
            new JsonContactsStorage(StorageManagerTest.tempDir.resolve("contacts.json")),
            new JsonSettingsStorage(StorageManagerTest.tempDir.resolve("settings.json"))
        );
    }

    /**
     * Integration test that verifies {@link StorageManager} is properly wired
     * to {@link SettingsStorage}.
     */
    @Test
    public void saveSettings_saveRead_equal() throws Exception {
        Settings expected = new Settings();
        expected.setGuiSettings(new GuiSettings(300, 600, 4, 6));
        this.manager.saveSettings(expected);

        ReadOnlySettings actual = this.manager.readSettings().get();

        assertEquals(expected, actual);
    }

    /**
     * Integration test that verifies {@link StorageManager} is properly wired
     * to {@link ContactsStorage}.
     */
    @Test
    public void saveContacts_saveRead_equal() throws Exception {
        ReadOnlyContacts expected = TestData.Valid.Contact.getTypicalContacts();
        this.manager.saveContacts(expected);

        ReadOnlyContacts actual = this.manager.readContacts().get();

        assertEquals(expected, actual);
    }
}
