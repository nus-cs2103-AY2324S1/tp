package seedu.address.model.tag;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Map;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBook;
import seedu.address.testutil.TypicalPersons;
import seedu.address.testutil.TypicalTasks;

class TagFrequencyTableTest {

    private static TagFrequencyTable tagFrequencyTable;
    private static TagFrequencyTable tagFrequencyTableCopy;
    private static TagFrequencyTable personOnlyTagFrequencyTable;
    private static TagFrequencyTable taskOnlyTagFrequencyTable;
    private static TagFrequencyTable emptyTagFrequencyTable;

    @BeforeAll
    static void beforeAll() {
        AddressBook addressBook = new AddressBook();
        addressBook.setPersons(TypicalPersons.getTypicalPersons());
        addressBook.setTasks(TypicalTasks.getTypicalTasks());
        tagFrequencyTable =
                new TagFrequencyTable(addressBook.getPersonList(), addressBook.getTaskList());
        tagFrequencyTableCopy =
                new TagFrequencyTable(addressBook.getPersonList(), addressBook.getTaskList());
        personOnlyTagFrequencyTable =
                new TagFrequencyTable(addressBook.getPersonList(), new ArrayList<>());
        taskOnlyTagFrequencyTable =
                new TagFrequencyTable(new ArrayList<>(), addressBook.getTaskList());
        emptyTagFrequencyTable =
                new TagFrequencyTable(new ArrayList<>(), new ArrayList<>());
    }

    @Test
    public void getPersonTagFrequencyTable_addEntry_notModified() {
        Map<Tag, Integer> modifiedMap = tagFrequencyTable.getPersonTagFrequencyTable();
        modifiedMap.put(new Tag("modifiedEntry"), 1);
        assertNotEquals(modifiedMap, tagFrequencyTable.getPersonTagFrequencyTable());
    }

    @Test
    public void getTaskTagFrequencyTable_addEntry_notModified() {
        Map<Tag, Integer> modifiedMap = tagFrequencyTable.getTaskTagFrequencyTable();
        modifiedMap.put(new Tag("modifiedEntry"), 1);
        assertNotEquals(modifiedMap, tagFrequencyTable.getTaskTagFrequencyTable());
    }

    @Test
    public void getCombinedTagFrequencyTable_addEntry_notModified() {
        Map<Tag, Integer> modifiedMap = tagFrequencyTable.getCombinedTagFrequencyTable();
        modifiedMap.put(new Tag("modifiedEntry"), 1);
        assertNotEquals(modifiedMap, tagFrequencyTable.getCombinedTagFrequencyTable());
    }

    @Test
    public void format() {
        String expected = "[class]: 8 items (0 Persons, 8 Tasks)\n"
                          + "[colleague]: 6 items (3 Persons, 3 Tasks)\n"
                          + "[syndicateA]: 4 items (2 Persons, 2 Tasks)\n"
                          + "[admin]: 3 items (0 Persons, 3 Tasks)\n"
                          + "[finance]: 3 items (0 Persons, 3 Tasks)\n"
                          + "[friends]: 3 items (3 Persons, 0 Tasks)\n"
                          + "[syndicateB]: 2 items (1 Persons, 1 Tasks)\n"
                          + "[caterer]: 1 items (0 Persons, 1 Tasks)\n"
                          + "[location]: 1 items (0 Persons, 1 Tasks)\n"
                          + "[owesMoney]: 1 items (1 Persons, 0 Tasks)";
        assertEquals(expected, tagFrequencyTable.format());
    }

    @Test
    public void isEmpty() {
        assertFalse(tagFrequencyTable.isEmpty());
        assertTrue(emptyTagFrequencyTable.isEmpty());
    }

    @Test
    public void toStringMethod() {
        // different contents -> different string
        assertNotEquals(tagFrequencyTable.toString(), personOnlyTagFrequencyTable.toString());

        // same contents -> same string
        assertEquals(tagFrequencyTable.toString(), tagFrequencyTableCopy.toString());

        // check format
        String expected = TagFrequencyTable.class.getCanonicalName()
                          + "{personTagFrequencyTable=" + tagFrequencyTable.getPersonTagFrequencyTable()
                          + ", taskTagFrequencyTable=" + tagFrequencyTable.getTaskTagFrequencyTable()
                          + ", combinedTagFrequencyTable=" + tagFrequencyTable.getCombinedTagFrequencyTable()
                          + "}";
        assertEquals(expected, tagFrequencyTable.toString());
    }

    @Test
    public void equals() {
        // same object -> returns true
        assertEquals(tagFrequencyTable, tagFrequencyTable);

        // null -> returns false
        assertNotEquals(tagFrequencyTable, null);

        // different types -> returns false
        assertNotEquals(tagFrequencyTable, 5);

        // different contents -> returns false
        assertNotEquals(tagFrequencyTable, personOnlyTagFrequencyTable);
        assertNotEquals(tagFrequencyTable, taskOnlyTagFrequencyTable);
        assertNotEquals(tagFrequencyTable, emptyTagFrequencyTable);

        // same contents -> returns true
        assertEquals(tagFrequencyTable, tagFrequencyTableCopy);
    }

    @Test
    public void hashCodeMethod() {
        // same object -> same hashcode
        assertEquals(tagFrequencyTable.hashCode(), tagFrequencyTable.hashCode());

        // different contents -> different hashcode
        assertNotEquals(tagFrequencyTable.hashCode(), personOnlyTagFrequencyTable.hashCode());

        // same contents -> same hashcode
        assertEquals(tagFrequencyTable.hashCode(), tagFrequencyTableCopy.hashCode());
    }
}
