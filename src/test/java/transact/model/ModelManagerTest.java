package transact.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static transact.model.Model.PREDICATE_HIDE_ALL_TRANSACTIONS;
import static transact.model.Model.PREDICATE_SHOW_ALL_PERSONS;
import static transact.model.Model.PREDICATE_SHOW_ALL_TRANSACTIONS;
import static transact.testutil.Assert.assertThrows;
import static transact.testutil.TypicalPersons.ALICE;
import static transact.testutil.TypicalPersons.BENSON;
import static transact.testutil.TypicalTransactions.APPLES;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import transact.commons.core.GuiSettings;
import transact.model.person.NameContainsKeywordsPredicate;
import transact.testutil.AddressBookBuilder;
import transact.testutil.TransactionBookBuilder;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new AddressBook(), new AddressBook(modelManager.getAddressBook()));
        assertEquals(new TransactionBook(), new TransactionBook(modelManager.getTransactionBook()));
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
    public void setTransactionBookFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setTransactionBookFilePath(null));
    }

    @Test
    public void setTransactionBookFilePath_validPath_setsTransactionBookFilePath() {
        Path path = Paths.get("transaction/book/file/path");
        modelManager.setTransactionBookFilePath(path);
        assertEquals(path, modelManager.getTransactionBookFilePath());
    }

    @Test
    public void hasTransaction_nullTransaction_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasTransaction(null));
    }

    @Test
    public void hasTransaction_transactionNotInTransactionBook_returnsFalse() {
        assertFalse(modelManager.hasTransaction(APPLES.getTransactionId()));
    }

    @Test
    public void hasTransaction_transactionInTransactionBook_returnsTrue() {
        modelManager.addTransaction(APPLES);
        assertTrue(modelManager.hasTransaction(APPLES.getTransactionId()));
    }

    @Test
    public void getFilteredTransactionList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredTransactionList().remove(0));
    }

    @Test
    public void equals() {
        AddressBook addressBook = new AddressBookBuilder().withPerson(ALICE).withPerson(BENSON).build();
        AddressBook differentAddressBook = new AddressBook();
        TransactionBook transactionBook = new TransactionBookBuilder().withTransaction(APPLES).build();
        TransactionBook differentTransactionBook = new TransactionBook();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(addressBook, transactionBook, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(addressBook, transactionBook, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different addressBook -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentAddressBook, transactionBook, userPrefs)));

        // different filteredList (address) -> returns false
        String[] keywords = ALICE.getName().fullName.split("\\s+");
        modelManager.updateFilteredPersonList(new NameContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelManager(addressBook, transactionBook, userPrefs)));

        // different transactionBook -> returns false
        modelManager.updateFilteredTransactionList(PREDICATE_HIDE_ALL_TRANSACTIONS);
        assertFalse(modelManager.equals(new ModelManager(addressBook, differentTransactionBook, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        modelManager.updateFilteredTransactionList(PREDICATE_SHOW_ALL_TRANSACTIONS);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setAddressBookFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(addressBook, transactionBook, differentUserPrefs)));
    }
}
