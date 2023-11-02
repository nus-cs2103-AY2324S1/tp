package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeSet;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.person.Person;
import seedu.address.model.person.interaction.Interaction;
import seedu.address.model.reminder.DateReminders;
import seedu.address.model.reminder.Reminder;

/**
 * Represents the dashboard of the address book.
 */
public class Dashboard {
    private final Model model;

    private boolean isDashboardDirty = true;
    private boolean isDashboardOpen = false;

    // ============ Dashboard Data Fields ===================================
    // TODO: Extract to a separate data class
    private int numClients;
    private int totalInteraction;
    private int totalClosedInteractions;
    private int totalInterestedInteractions;
    private int totalNotInterestedInteractions;
    private int totalFollowUpRequiredInteractions;
    private int totalUnknownInteractions;
    private int totalUncontactedClients;
    private int totalContactingClients;
    private int totalClosedClients;
    private int totalHotLeads;
    private int totalWarmLeads;
    private int totalColdLeads;

    private ObservableList<Reminder> reminderList;
    private HashMap<LocalDate, ArrayList<Reminder>> dateToReminderMap = new HashMap<>();

    /**
     * Constructs a {@code Dashboard} with the given {@code Model}.
     */
    public Dashboard(Model model) {
        requireNonNull(model);
        this.model = model;
    }

    /**
     * Returns the total number of interactions in the address book.
     */
    public int getTotalInteraction() {
        updateDashboardIfDirty();
        return totalInteraction;
    }

    /**
     * Returns the percentage of interactions with outcome CLOSED.
     */
    public double closedPercentage() {
        updateDashboardIfDirty();
        return (double) totalClosedInteractions / totalInteraction * 100;
    }

    /**
     * Returns the percentage of interactions with outcome INTERESTED.
     */
    public double interestedPercentage() {
        updateDashboardIfDirty();
        return (double) totalInterestedInteractions / totalInteraction * 100;
    }

    /**
     * Returns the percentage of interactions with outcome NOT_INTERESTED.
     */
    public double notInterestedPercentage() {
        updateDashboardIfDirty();
        return (double) totalNotInterestedInteractions / totalInteraction * 100;
    }

    /**
     * Returns the percentage of interactions with outcome FOLLOWUP_REQUIRED.
     */
    public double followUpRequiredPercentage() {
        updateDashboardIfDirty();
        return (double) totalFollowUpRequiredInteractions / totalInteraction * 100;
    }

    /**
     * Returns the percentage of interactions with outcome UNKNOWN.
     */
    public double unknownPercentage() {
        updateDashboardIfDirty();
        return (double) totalUnknownInteractions / totalInteraction * 100;
    }

    /**
     * Returns the total number of hot leads.
     */
    public int getTotalHotLeads() {
        updateDashboardIfDirty();
        return totalHotLeads;
    }

    /**
     * Returns the total number of warm leads.
     */
    public int getTotalWarmLeads() {
        updateDashboardIfDirty();
        return totalWarmLeads;
    }

    /**
     * Returns the total number of cold leads.
     */
    public int getTotalColdLeads() {
        updateDashboardIfDirty();
        return totalColdLeads;
    }

    /**
     * Returns the average number of interactions per client.
     */
    public int averageInteractionsPerClient() {
        updateDashboardIfDirty();
        return (int) Math.ceil(totalInteraction / (double) numClients);
    }

    /**
     * Returns the total number of uncontacted clients.
     */
    public int getNumUncontactedClients() {
        updateDashboardIfDirty();
        return totalUncontactedClients;
    }

    /**
     * Returns the total number of clients that are still in contact.
     */
    public int getNumContactingClients() {
        updateDashboardIfDirty();
        return totalContactingClients;
    }

    /**
     * Returns the total number of closed clients.
     */
    public int getNumClosedClients() {
        updateDashboardIfDirty();
        return totalClosedClients;
    }

    public ObservableList<Reminder> getReminderList() {
        updateDashboardIfDirty();
        return reminderList;
    }

    private ObservableList<Reminder> getDateSpecificReminder(LocalDate date) {
        updateDashboardIfDirty();
        ObservableList<Reminder> reminderList = FXCollections.observableArrayList();
        ArrayList<Reminder> retrievedReminders = dateToReminderMap.get(date);
        if (retrievedReminders == null) {
            return reminderList;
        }
        reminderList.addAll(retrievedReminders);
        return reminderList;
    }

    public ObservableList<DateReminders> getDateReminders() {
        updateDashboardIfDirty();
        ObservableList<LocalDate> sortedKeys = FXCollections.observableArrayList(
                new TreeSet<>(dateToReminderMap.keySet()));
        return sortedKeys.stream().map(date -> new DateReminders(date, getDateSpecificReminder(date)))
                .collect(Collectors.toCollection(FXCollections::observableArrayList));
    }

    /**
     * Returns the updated dashboard and opens the dashboard for viewing.
     * The dashboard should always be closed after viewing as updating the dashboard may be expensive.
     */
    public Dashboard openDashboard() {
        updateDashboardIfDirty();

        this.isDashboardOpen = true;
        return this;
    }

    /**
     * Closes the dashboard.
     */
    public Dashboard closeDashboard() {
        this.isDashboardOpen = false;
        return this;
    }

    public boolean isDashboardOpen() {
        return this.isDashboardOpen;
    }

    /**
     * Sets the dashboard to be dirty.
     * To be called when data related to the dashboard is changed.
     */
    public void setDashboardDirty() {
        this.isDashboardDirty = true;
    }

    private void updateDashboardIfDirty() {
        if (!isDashboardDirty) {
            return;
        }

        numClients = model.getAddressBook().getPersonList().size();
        totalInteraction = countPersonListInteraction();
        totalClosedInteractions = countPersonListWithSpecifiedOutcome(Interaction.Outcome.CLOSED);
        totalInterestedInteractions = countPersonListWithSpecifiedOutcome(Interaction.Outcome.INTERESTED);
        totalNotInterestedInteractions = countPersonListWithSpecifiedOutcome(Interaction.Outcome.NOT_INTERESTED);
        totalFollowUpRequiredInteractions = countPersonListWithSpecifiedOutcome(Interaction.Outcome.FOLLOWUP_REQUIRED);
        totalUnknownInteractions = countPersonListWithSpecifiedOutcome(Interaction.Outcome.UNKNOWN);
        totalUncontactedClients = filteredPersonCount(Person::isUncontacted);
        totalContactingClients = filteredPersonCount(Person::isContacting);
        totalClosedClients = filteredPersonCount(Person::isClosed);
        totalHotLeads = filteredPersonCount(Person::isHotLead);
        totalWarmLeads = filteredPersonCount(Person::isWarmLead);
        totalColdLeads = filteredPersonCount(Person::isColdLead);

        model.updateReminderList();
        model.updateFilteredReminderList(Reminder::isAfterNow);
        reminderList = model.getFilteredReminderList();
        updateDateToReminderMap();

        /*
         Set dashboard to be clean below. Not written yet as there is no overarching mechanism to integrate
         the dashboard logic with the rest of the code.
        */
    }

    private int countPersonListWithSpecifiedOutcome(Interaction.Outcome outcome) {
        ObservableList<Person> personList = model.getAddressBook().getPersonList();

        return personList.stream()
                         .map(person -> person.getFilteredInteractions(i -> i.isOutcome(outcome)).size())
                         .reduce(0, Integer::sum);
    }

    private int countPersonListInteraction() {
        ObservableList<Person> personList = model.getAddressBook().getPersonList();

        return personList.stream().mapToInt(person -> person.getInteractions().size()).sum();
    }

    private int filteredPersonCount(Predicate<Person> predicate) {
        ObservableList<Person> personList = model.getAddressBook().getPersonList();

        return (int) personList.stream().filter(predicate).count();
    }

    private void updateDateToReminderMap() {
        dateToReminderMap.clear();
        this.reminderList.forEach(i -> addDateToReminderMap(i));
    }

    private void addDateToReminderMap(Reminder reminder) {
        LocalDate reminderDate = reminder.getFollowUpDate();
        dateToReminderMap.computeIfAbsent(reminderDate, k -> new ArrayList<>());
        dateToReminderMap.get(reminderDate).add(reminder);
    }

    /**
     * Two dashboards are equal if and only if they have the same model.
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Dashboard // instanceof handles nulls
                && model.equals(((Dashboard) other).model));
    }

}
