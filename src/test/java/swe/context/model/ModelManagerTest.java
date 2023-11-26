package swe.context.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static swe.context.testutil.Assert.assertThrows;
import static swe.context.testutil.TestData.Valid.Contact.ALICE;

import org.junit.jupiter.api.Test;

import swe.context.commons.core.GuiSettings;

public class ModelManagerTest {
    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new Settings(), modelManager.getSettings());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new Contacts(), new Contacts(modelManager.getContacts()));
    }

    @Test
    public void setGuiSettings() {
        GuiSettings guiSettings = new GuiSettings(1, 2, 3, 4);
        modelManager.setGuiSettings(guiSettings);
        assertEquals(guiSettings, modelManager.getGuiSettings());
    }

    @Test
    public void containsContact_doesNotContain_false() {
        assertFalse(modelManager.containsContact(ALICE));
    }

    @Test
    public void containsContact_contains_true() {
        modelManager.addContact(ALICE);
        assertTrue(modelManager.containsContact(ALICE));
    }

    @Test
    public void getFilteredContactList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredContactList().remove(0));
    }

    @Test
    public void addContact_contactNotPresent_addSuccessful() {
        assertFalse(modelManager.containsContact(ALICE));
        modelManager.addContact(ALICE);
        assertTrue(modelManager.containsContact(ALICE));
    }

    @Test
    public void removeContact_contactPresent_removeSuccessful() {
        modelManager.addContact(ALICE);
        assertTrue(modelManager.containsContact(ALICE));
        modelManager.removeContact(ALICE);
        assertFalse(modelManager.containsContact(ALICE));
    }

}
