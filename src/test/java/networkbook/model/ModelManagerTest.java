package networkbook.model;

import static networkbook.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import networkbook.commons.core.GuiSettings;
import networkbook.model.person.NameContainsKeyTermsPredicate;
import networkbook.model.person.NameContainsKeywordsPredicate;
import networkbook.model.person.Person;
import networkbook.model.person.PersonSortComparator;
import networkbook.model.person.PersonSortComparator.SortField;
import networkbook.model.person.PersonSortComparator.SortOrder;
import networkbook.testutil.NetworkBookBuilder;
import networkbook.testutil.TypicalPersons;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new NetworkBook(), new NetworkBook(modelManager.getNetworkBook()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setNetworkBookFilePath(Paths.get("address/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setNetworkBookFilePath(Paths.get("new/address/book/file/path"));
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
    public void setNetworkBookFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setNetworkBookFilePath(null));
    }

    @Test
    public void setNetworkBookFilePath_validPath_setsNetworkBookFilePath() {
        Path path = Paths.get("address/book/file/path");
        modelManager.setNetworkBookFilePath(path);
        assertEquals(path, modelManager.getNetworkBookFilePath());
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasPerson(null));
    }

    @Test
    public void hasPerson_personNotInNetworkBook_returnsFalse() {
        assertFalse(modelManager.hasPerson(TypicalPersons.ALICE));
    }

    @Test
    public void hasPerson_personInNetworkBook_returnsTrue() {
        modelManager.addPerson(TypicalPersons.ALICE);
        assertTrue(modelManager.hasPerson(TypicalPersons.ALICE));
    }

    @Test
    public void canUndoNetworkBook_noPreviousState_returnsFalse() {
        ModelManager modelManager = new ModelManager();
        assertFalse(modelManager.canUndoNetworkBook());
    }

    @Test
    public void canUndoNetworkBook_hasPreviousState_returnsTrue() {
        ModelManager modelManager = new ModelManager();
        modelManager.addPerson(TypicalPersons.ALICE);
        assertTrue(modelManager.canUndoNetworkBook());
        modelManager.deletePerson(TypicalPersons.ALICE);
        assertTrue(modelManager.canUndoNetworkBook());
    }

    @Test
    public void canRedoNetworkBook_noNextState_returnsFalse() {
        ModelManager modelManager = new ModelManager();
        assertFalse(modelManager.canRedoNetworkBook());
    }

    @Test
    public void canRedoNetworkBook_hasNextState_returnsTrue() {
        ModelManager modelManager = new ModelManager();
        assertFalse(modelManager.canRedoNetworkBook());
        modelManager.addPerson(TypicalPersons.ALICE);
        modelManager.deletePerson(TypicalPersons.ALICE);
        modelManager.undoNetworkBook();
        modelManager.undoNetworkBook();
        assertTrue(modelManager.canRedoNetworkBook());
        modelManager.redoNetworkBook();
        assertTrue(modelManager.canRedoNetworkBook());
    }

    @Test
    public void undoNetworkBook_hasPreviousState_success() {
        ModelManager modelManager = new ModelManager();
        modelManager.addPerson(TypicalPersons.ALICE);
        modelManager.undoNetworkBook();
        assertEquals(0, modelManager.getNetworkBook().getPersonList().size());
    }

    @Test
    public void redoNetworkBook_hasNextState_success() {
        ModelManager modelManager = new ModelManager();
        modelManager.addPerson(TypicalPersons.ALICE);
        modelManager.undoNetworkBook();
        modelManager.redoNetworkBook();
        assertEquals(1, modelManager.getNetworkBook().getPersonList().size());
    }

    @Test
    public void getFilteredPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getDisplayedPersonList().remove(0));
    }

    @Test
    public void equals() {
        NetworkBook networkBook = new NetworkBookBuilder()
                .withPerson(TypicalPersons.ALICE)
                .withPerson(TypicalPersons.BENSON)
                .build();
        NetworkBook differentNetworkBook = new NetworkBook();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(networkBook, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(networkBook, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different networkBook -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentNetworkBook, userPrefs)));

        // different filter -> returns false
        String[] keywords = TypicalPersons.ALICE.getName().fullName.split("\\s+");
        modelManager.updateDisplayedPersonList(new NameContainsKeywordsPredicate(Arrays.asList(keywords)), null);
        assertFalse(modelManager.equals(new ModelManager(networkBook, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateDisplayedPersonList(Model.PREDICATE_SHOW_ALL_PERSONS, null);
        modelManager.updateDisplayedPersonList(null, PersonSortComparator.EMPTY_COMPARATOR);

        // different sort -> returns false
        modelManager.updateDisplayedPersonList(null, new PersonSortComparator(SortField.NAME, SortOrder.DESCENDING));
        assertFalse(modelManager.equals(new ModelManager(networkBook, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateDisplayedPersonList(Model.PREDICATE_SHOW_ALL_PERSONS, null);
        modelManager.updateDisplayedPersonList(null, PersonSortComparator.EMPTY_COMPARATOR);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setNetworkBookFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(networkBook, differentUserPrefs)));
    }

    @Test
    public void updateDisplayedPersonList_filterBySingleKeyword_correctlyFiltered() {
        Model model = new ModelManager(TypicalPersons.getTypicalNetworkBook(), new UserPrefs());
        model.updateDisplayedPersonList(new NameContainsKeyTermsPredicate(List.of("Ku")), null);
        List<Person> expectedPersons = List.of(TypicalPersons.CARL, TypicalPersons.FIONA);
        ObservableList<Person> expectedList = FXCollections.observableList(expectedPersons);
        assertEquals(
                expectedList,
                model.getDisplayedPersonList()
        );
    }

    @Test
    public void updateDisplayedPersonList_filterByMultipleKeywords_correctlyFiltered() {
        Model model = new ModelManager(TypicalPersons.getTypicalNetworkBook(), new UserPrefs());
        model.updateDisplayedPersonList(new NameContainsKeyTermsPredicate(List.of("Alice", "Benson")), null);
        List<Person> expectedPersons = List.of(TypicalPersons.ALICE, TypicalPersons.BENSON);
        ObservableList<Person> expectedList = FXCollections.observableList(expectedPersons);
        assertEquals(
                expectedList,
                model.getDisplayedPersonList()
        );
    }

    @Test
    public void updateDisplayedPersonList_descendingNameSort_correctlySorted() {
        PersonSortComparator comparator = new PersonSortComparator(SortField.NAME, SortOrder.DESCENDING);
        Model model = new ModelManager(TypicalPersons.getTypicalNetworkBook(), new UserPrefs());
        model.updateDisplayedPersonList(null, comparator);
        List<Person> expectedPersons = TypicalPersons.getTypicalPersons();
        Collections.reverse(expectedPersons);
        ObservableList<Person> expectedList = FXCollections.observableList(expectedPersons);
        assertEquals(
                expectedList,
                model.getDisplayedPersonList()
        );
    }

}
