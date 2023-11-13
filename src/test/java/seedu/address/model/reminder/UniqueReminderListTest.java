package seedu.address.model.reminder;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class UniqueReminderListTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private final UniqueReminderList uniqueReminderList = new UniqueReminderList(model);

    @Test
    public void isEmpty() {
        assertTrue(uniqueReminderList.isEmpty());
        uniqueReminderList.updateReminders();
        assertTrue(!uniqueReminderList.isEmpty());
    }

    @Test
    public void iterator() {
        uniqueReminderList.updateReminders();
        assertTrue(uniqueReminderList.iterator().hasNext());
    }

    @Test
    public void equalsMethod() {
        assertTrue(uniqueReminderList.equals(uniqueReminderList));
        assertFalse(uniqueReminderList.equals(null));
        Model model2 = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        model2.deletePerson(model2.getFilteredPersonList().get(0));
        UniqueReminderList uniqueReminderList2 = new UniqueReminderList(model2);
        uniqueReminderList2.updateReminders();
        assertFalse(uniqueReminderList.equals(uniqueReminderList2));
    }
}
