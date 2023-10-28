package seedu.address.model;

import static java.util.Objects.requireNonNull;

import javafx.collections.ObservableList;
import seedu.address.model.person.Person;
import seedu.address.model.person.interaction.Interaction;

/**
 * Represents the dashboard of the address book.
 */
public class Dashboard {
    private final Model model;

    private boolean isDashboardDirty = true;
    private boolean isDashboardOpen = false;

    // ============ Dashboard Data Fields ===================================
    // These fields may be grouped into a DashboardData class in the future.
    private int totalInteraction;
    private int totalInterestedInteractions;
    private int totalNotInterestedInteractions;

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
     * Returns the updated dashboard and opens the dashboard for viewing.
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

        totalInteraction = countPersonListInteraction();
        totalInterestedInteractions = countPersonListWithSpecifiedOutcome(Interaction.Outcome.INTERESTED);
        totalNotInterestedInteractions = countPersonListWithSpecifiedOutcome(Interaction.Outcome.NOT_INTERESTED);

        /*
         Set dashboard to be clean below. Not written yet as there is no overarching mechanism to integrate
         the dashboard logic with the rest of the code.
        */
    }

    private int countPersonListWithSpecifiedOutcome(Interaction.Outcome outcome) {
        ObservableList<Person> personList = model.getAddressBook().getPersonList();

        return personList.stream()
                .map(person ->
                        person.getFilteredInteractions(i -> i.isOutcome(outcome)).size())
                .reduce(0, Integer::sum);
    }

    private int countPersonListInteraction() {
        ObservableList<Person> personList = model.getAddressBook().getPersonList();

        return personList.stream()
                .mapToInt(person -> person.getInteractions().size())
                .sum();
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
