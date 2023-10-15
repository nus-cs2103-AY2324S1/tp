//package seedu.address.model;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertFalse;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static seedu.address.testutil.Assert.assertThrows;
//import static seedu.address.testutil.TypicalCards.CS1101S;
//import static seedu.address.testutil.TypicalCards.CS2100;
//
//import java.nio.file.Path;
//import java.nio.file.Paths;
//
//import org.junit.jupiter.api.Test;
//
//import seedu.address.commons.core.GuiSettings;
//import seedu.address.testutil.DeckBuilder;
//
//public class ModelManagerTest {
//
//    private ModelManager2 modelManager = new ModelManager2();
//
//    @Test
//    public void constructor() {
//        assertEquals(new UserPrefs2(), modelManager.getUserPrefs());
//        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
//        assertEquals(new AddressBook(), new Deck(modelManager.getDeck()));
//    }
//
//    @Test
//    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
//        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
//    }
//
//    @Test
//    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
//        UserPrefs2 userPrefs = new UserPrefs2();
//        userPrefs.setDeckFilePath(Paths.get("address/book/file/path"));
//        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
//        modelManager.setUserPrefs(userPrefs);
//        assertEquals(userPrefs, modelManager.getUserPrefs());
//
//        // Modifying userPrefs should not modify modelManager's userPrefs
//        UserPrefs2 oldUserPrefs = new UserPrefs2(userPrefs);
//        userPrefs.setDeckFilePath(Paths.get("new/address/book/file/path"));
//        assertEquals(oldUserPrefs, modelManager.getUserPrefs());
//    }
//
//    @Test
//    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
//        assertThrows(NullPointerException.class, () -> modelManager.setGuiSettings(null));
//    }
//
//    @Test
//    public void setGuiSettings_validGuiSettings_setsGuiSettings() {
//        GuiSettings guiSettings = new GuiSettings(1, 2, 3, 4);
//        modelManager.setGuiSettings(guiSettings);
//        assertEquals(guiSettings, modelManager.getGuiSettings());
//    }
//
//    @Test
//    public void setAddressBookFilePath_nullPath_throwsNullPointerException() {
//        assertThrows(NullPointerException.class, () -> modelManager.setDeckFilePath(null));
//    }
//
//    @Test
//    public void setAddressBookFilePath_validPath_setsAddressBookFilePath() {
//        Path path = Paths.get("address/book/file/path");
//        modelManager.setDeckFilePath(path);
//        assertEquals(path, modelManager.getDeckFilePath());
//    }
//
//    @Test
//    public void hasPerson_nullPerson_throwsNullPointerException() {
//        assertThrows(NullPointerException.class, () -> modelManager.hasCard(null));
//    }
//
//    @Test
//    public void hasPerson_personNotInAddressBook_returnsFalse() {
//        assertFalse(modelManager.hasCard(CS2100));
//    }
//
//    @Test
//    public void hasPerson_personInAddressBook_returnsTrue() {
//        modelManager.addCard(CS2100);
//        assertTrue(modelManager.hasCard(CS2100));
//    }
//
//    @Test
//    public void getFilteredPersonList_modifyList_throwsUnsupportedOperationException() {
//        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredCardList().remove(0));
//    }
//
//    @Test
//    public void equals() {
//        Deck deck = new DeckBuilder().withCard(CS2100).withCard(CS1101S).build();
//        Deck diffDeck = new Deck();
//        UserPrefs2 userPrefs = new UserPrefs2();
//
//        // same values -> returns true
//        modelManager = new ModelManager2(deck, userPrefs);
//        ModelManager2 modelManagerCopy = new ModelManager2(deck, userPrefs);
//        assertTrue(modelManager.equals(modelManagerCopy));
//
//        // same object -> returns true
//        assertTrue(modelManager.equals(modelManager));
//
//        // null -> returns false
//        assertFalse(modelManager.equals(null));
//
//        // different types -> returns false
//        assertFalse(modelManager.equals(5));
//
//        // different addressBook -> returns false
//        assertFalse(modelManager.equals(new ModelManager2(diffDeck, userPrefs)));
//    }
//}
