package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_EVENTS_AFTER_TODAY;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalEvents.EVENT1;
import static seedu.address.testutil.TypicalEvents.EVENT5;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.GuiSettings;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.testutil.AddressBookBuilder;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new AddressBook(), new AddressBook(modelManager.getAddressBook()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setAddressBookFilePath(Paths.get("address/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setAddressBookFilePath(Paths.get("new/address/book/file/path"));
        assertEquals(oldUserPrefs, modelManager.getUserPrefs());
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
    public void setAddressBookFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setAddressBookFilePath(null));
    }

    @Test
    public void setAddressBookFilePath_validPath_setsAddressBookFilePath() {
        Path path = Paths.get("address/book/file/path");
        modelManager.setAddressBookFilePath(path);
        assertEquals(path, modelManager.getAddressBookFilePath());
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasPerson(null));
    }

    @Test
    public void hasPerson_personNotInAddressBook_returnsFalse() {
        assertFalse(modelManager.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personInAddressBook_returnsTrue() {
        modelManager.addPerson(ALICE);
        assertTrue(modelManager.hasPerson(ALICE));
    }

    @Test
    public void getFilteredPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredPersonList().remove(0));
    }


    @Test
    public void hasEvent_nullEvent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasEvent(null));
    }

    @Test
    public void hasEvent_eventNotInAddressBook_returnsFalse() {
        assertFalse(modelManager.hasEvent(EVENT1));
    }

    @Test
    public void hasEvent_eventInAddressBook_returnsTrue() {
        modelManager.addEvent(EVENT1);
        assertTrue(modelManager.hasEvent(EVENT1));
    }

    @Test
    public void deleteEvent_eventInAddressBook_returnsTrue() {
        modelManager.addEvent(EVENT1);
        modelManager.deleteEvent(EVENT1);
        assertFalse(modelManager.hasEvent(EVENT1));
    }

    @Test
    public void isValidClient_nullClient_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.isValidClient(null));
    }


    @Test
    public void getEventList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getEventList().remove(0));
    }

    @Test
    public void getFilteredEventList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredEventList().remove(0));
    }

    @Test
    public void updateFilteredEventList_nullPredicate_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.updateFilteredEventList(null));
    }

    @Test
    public void updateFilteredEventList_validPredicate_updatesFilteredList() {
        // Add an event to the model
        modelManager.addEvent(EVENT5);

        // Filter the event list to show all events after today
        modelManager.updateFilteredEventList(PREDICATE_SHOW_ALL_EVENTS_AFTER_TODAY);

        // The filtered list should contain the added event
        assertTrue(modelManager.getFilteredEventList().contains(EVENT5));
    }

    @Test
    public void updateFilteredEventList_emptyPredicate_resetsFilteredList() {
        // Add an event to the model
        modelManager.addEvent(EVENT5);

        // Filter the event list to show all events after today
        modelManager.updateFilteredEventList(PREDICATE_SHOW_ALL_EVENTS_AFTER_TODAY);

        // Reset the filter by passing an empty predicate
        modelManager.updateFilteredEventList(p -> true);

        // The filtered list should contain all events, including EVENT5
        assertTrue(modelManager.getFilteredEventList().contains(EVENT5));
    }

    @Test
    public void equals() {
        AddressBook addressBook = new AddressBookBuilder().withPerson(ALICE).withPerson(BENSON).build();
        AddressBook differentAddressBook = new AddressBook();
        EventsBook emptyEventsBook = new EventsBook();
        FinancesBook emptyFinancesBook = new FinancesBook();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(addressBook, emptyEventsBook, emptyFinancesBook, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(addressBook, emptyEventsBook, emptyFinancesBook, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different addressBook -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentAddressBook, emptyEventsBook, emptyFinancesBook,
                userPrefs)));

        // different filteredList -> returns false
        String[] keywords = ALICE.getName().fullName.split("\\s+");
        modelManager.updateFilteredPersonList(new NameContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelManager(addressBook, emptyEventsBook, emptyFinancesBook, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setAddressBookFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(addressBook, emptyEventsBook, emptyFinancesBook,
                differentUserPrefs)));
    }

    @Test
    public void constructor_withNullParameters_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ModelManager(null, null, null, null));
    }

    @Test
    public void setAddressBook_nullAddressBook_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setAddressBook(null));
    }

    @Test
    public void setEventsBook_nullEventsBook_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setEventsBook(null));
    }

    @Test
    public void setFinancesBook_nullFinancesBook_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setFinancesBook(null));
    }
}
