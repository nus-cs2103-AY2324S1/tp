package seedu.address.model.reminder;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.person.Person;

/**
 * A list of Reminders that enforces uniqueness between its elements and does not allow nulls.
 * A Reminder is considered unique by comparing using {@code Reminder#equals(Reminder)}. This will allow the reminder
 * to be added or deleted to maintain the uniqueness of the ReminderList
 * Supports a minimal set of list operations.
 *
 */
public class UniqueReminderList implements Iterable<Reminder> {

    private static UniqueReminderList reminderListinstance;

    private boolean isReminderListDirty = false;

    private final ObservableList<Reminder> internalList;
    private final ObservableList<Reminder> internalUnmodifiableList;

    /**
     * Constructor of UniqueReminderList
     */
    private UniqueReminderList() {
        internalList = FXCollections.observableArrayList();
        internalUnmodifiableList = FXCollections.unmodifiableObservableList(internalList);
    }

    /**
     * Sets the reminder list to be dirty.
     * To be called when the reminder list is modified.
     */
    public void setRemidnerListDirty() {
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
     * Factory method of UniqueReminderList
     *
     * @return Produced UniqueReminderList object that is a Singleton.
     */
    public static UniqueReminderList getInstance() {
        if (reminderListinstance == null) {
            reminderListinstance = new UniqueReminderList();
        }
        return reminderListinstance;
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
     * Returns the list of reminders associated with a specific date.
     *
     * @param date The date that which is used to retrieve the list of reminders.
     * @return the list of reminders mapped from the given date.
     */
    public ObservableList<Reminder> getRemindersAfterDate(LocalDate date) {
        ObservableList<Reminder> reminderList = FXCollections.observableArrayList();
        List<Reminder> retrievedReminders = internalList.stream()
                .filter(a -> a.isAfter(date))
                .collect(Collectors.toList());

        if (retrievedReminders == null) {
            return reminderList;
        }
        reminderList.addAll(retrievedReminders);
        return reminderList;
    }

    /**
     * Updates the entire internal list of reminders using the given list of persons.
     *
     * @param personList The persons from which the reminders are to be produced.
     */
    public void updateReminders(ObservableList<Person> personList) {
        requireNonNull(personList);
        internalList.clear();
        for (Person person : personList) {
            person.updateReminder();
            if (person.getReminder() != null) {
                internalList.add(new Reminder(person));
                
            }
        }
    }

    public void updateRemindersIfDirty(ObservableList<Person> personList) {
        if (!isReminderListDirty) {
            return;
        }
        
        updateReminders(personList);
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

