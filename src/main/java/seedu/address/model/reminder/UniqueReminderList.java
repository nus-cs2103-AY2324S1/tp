package seedu.address.model.reminder;

import static java.util.Objects.requireNonNull;

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

    private boolean isReminderListDirty = false;

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
     * Sets the reminder list to be dirty.
     * To be called when the reminder list is modified.
     */
    public void setReminderListDirty() {
        isReminderListDirty = true;
    }

    /**
     * Returns true if the list contains no Reminders.
     */
    public boolean isEmpty() {
        return internalList.isEmpty();
    }

    /**
     * Returns true if the list contains an equivalent Reminder as the given argument.
     */
    public boolean contains(Reminder toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::equals);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Reminder> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    /**
     * Returns the earliest reminder time in the {@code UniqueReminderList}
     */
    public long getEarliestReminderTime() {
        if (internalList.isEmpty()) {
            return -1;
        }
        return internalList.stream().mapToLong(Reminder::getDueTime).min().getAsLong();
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

    /**
     * Updates the internal list of reminders if the list is dirty.
     */
    public void updateRemindersIfDirty() {
        if (!isReminderListDirty) {
            return;
        }

        updateReminders();
        isReminderListDirty = false;
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

