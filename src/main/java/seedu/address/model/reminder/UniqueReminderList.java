package seedu.address.model.reminder;

import java.util.Iterator;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * A list of Reminders that enforces uniqueness between its elements and does not allow nulls.
 * A Reminder is considered unique by comparing using {@code Reminder#equals(Reminder)}. This will allow the reminder
 * to be added or deleted to maintain the uniqueness of the ReminderList
 * Supports a minimal set of list operations.
 *
 */
public class UniqueReminderList implements Iterable<Reminder> {

    private final Model model;
    private final ObservableList<Reminder> internalList;
    private final ObservableList<Reminder> internalUnmodifiableList;

    /**
     * Constructor of UniqueReminderList
     */
    public UniqueReminderList(Model model) {
        this.model = model;
        internalList = FXCollections.observableArrayList();
        internalUnmodifiableList = FXCollections.unmodifiableObservableList(internalList);
    }

    /**
     * Returns true if the list contains no Reminders.
     */
    public boolean isEmpty() {
        return internalList.isEmpty();
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Reminder> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    /**
     * Updates the entire internal list of reminders using the list of persons in {@code model}.
     */
    public void updateReminders() {
        internalList.clear();
        for (Person person : model.getAddressBook().getPersonList()) {
            person.updateReminder();
            person.getReminder().ifPresent(internalList::add);
        }
    }

    public Iterator<Reminder> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueReminderList // instanceof handles nulls
                && internalList.equals(((UniqueReminderList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

}

