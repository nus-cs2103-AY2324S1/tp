package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TestData.ALICE;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.GuiSettings;



public class ModelManagerTest {
    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new Settings(), modelManager.getSettings());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new Contacts(), new Contacts(modelManager.getContacts()));
    }

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setGuiSettings(null));
    }

    @Test
    public void setGuiSettings_validGuiSettings_setsGuiSettings() {
        GuiSettings guiSettings = new GuiSettings(1, 2, 3, 4);
        modelManager.setGuiSettings(guiSettings);
        assertEquals(guiSettings, modelManager.getGuiSettings());
    }

    @Test
    public void hasContact_nullContact_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.containsContact(null));
    }

    @Test
    public void hasContact_contactNotInContactsManager_returnsFalse() {
        assertFalse(modelManager.containsContact(ALICE));
    }

    @Test
    public void hasContact_contactInContactsManager_returnsTrue() {
        modelManager.addContact(ALICE);
        assertTrue(modelManager.containsContact(ALICE));
    }

    @Test
    public void getFilteredContactList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredContactList().remove(0));
    }
}
