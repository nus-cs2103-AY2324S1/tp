package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.Set;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.event.Event;
import seedu.address.model.event.EventComparator;
import seedu.address.model.finance.Commission;
import seedu.address.model.finance.Expense;
import seedu.address.model.finance.Finance;
import seedu.address.model.person.Person;

/**
 * Represents the in-memory model of all address, events, and finance books data.
 */
public class ModelManager implements Model {
    public static final EventComparator EVENT_COMPARATOR = new EventComparator();
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AddressBook addressBook;
    private final EventsBook eventsBook;
    private final FinancesBook financesBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Person> filteredPersons;
    private final FilteredList<Event> filteredEvents;
    private final FilteredList<Finance> filteredFinances;


    /**
     * Initializes a ModelManager with the given addressBook, eventsBook, financeBook, and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyEventsBook eventsBook,
                        ReadOnlyFinancesBook financesBook, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.addressBook = new AddressBook(addressBook);
        this.eventsBook = new EventsBook(eventsBook);
        this.financesBook = new FinancesBook(financesBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPersons = new FilteredList<>(this.addressBook.getPersonList());
        filteredEvents = new FilteredList<>(this.eventsBook.getEventList());
        filteredFinances = new FilteredList<>(this.financesBook.getFinanceList());
    }

    public ModelManager() {
        this(new AddressBook(), new EventsBook(), new FinancesBook(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getAddressBookFilePath() {
        return userPrefs.getAddressBookFilePath();
    }

    @Override
    public Path getEventsFilePath() {
        return userPrefs.getEventsFilePath();
    }

    @Override
    public Path getFinanceFilePath() {
        return userPrefs.getFinanceFilePath();
    }

    @Override
    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setAddressBookFilePath(addressBookFilePath);
    }

    //=========== AddressBook ================================================================================

    @Override
    public void setAddressBook(ReadOnlyAddressBook addressBook) {
        this.addressBook.resetData(addressBook);
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return addressBook;
    }

    @Override
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return addressBook.hasPerson(person);
    }

    @Override
    public void deletePerson(Person target) {
        addressBook.removePerson(target);
    }

    @Override
    public void addPerson(Person person) {
        addressBook.addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);

        addressBook.setPerson(target, editedPerson);
    }

    //=========== Events =============================================================
    @Override
    public void setEventsBook(ReadOnlyEventsBook eventsBook) {
        this.eventsBook.resetData(eventsBook);
    }

    @Override
    public ReadOnlyEventsBook getEventsBook() {
        return eventsBook;
    }

    @Override
    public void addEvent(Event event) {
        eventsBook.addEvent(event);
    }

    @Override
    public void deleteEvent(Event target) {
        eventsBook.removeEvent(target);
    }

    @Override
    public void setEvent(Event target, Event editedEvent) {
        requireAllNonNull(target, editedEvent);

        eventsBook.setEvent(target, editedEvent);
    }

    @Override
    public boolean hasEvent(Event event) {
        requireNonNull(event);
        return eventsBook.hasEvent(event);
    }

    @Override
    public boolean isValidClient(Person client) {
        requireAllNonNull(client);
        return addressBook.isValidClient(client);
    }

    @Override
    public Set<Person> getAllMatchedClients(Set<Person> clients) {
        return addressBook.getAllMatchedClients(clients);
    }

    @Override
    public Person getMatchedClient(Person client) {
        return addressBook.getMatchedClient(client);
    }

    @Override
    public ObservableList<Event> getEventList() {
        filteredEvents.setPredicate(PREDICATE_SHOW_ALL_EVENTS_AFTER_TODAY);
        return new SortedList<>(filteredEvents, EVENT_COMPARATOR);
    }

    @Override
    public ObservableList<Event> getFilteredEventList() {
        return new SortedList<>(filteredEvents, EVENT_COMPARATOR);
    }

    @Override
    public void updateFilteredEventList(Predicate<Event> predicate) {
        requireNonNull(predicate);
        filteredEvents.setPredicate(predicate);
    }

    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return filteredPersons;
    }

    @Override
    public void updateFilteredPersonList(Predicate<Person> predicate) {
        requireNonNull(predicate);
        filteredPersons.setPredicate(predicate);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ModelManager)) {
            return false;
        }

        ModelManager otherModelManager = (ModelManager) other;
        return addressBook.equals(otherModelManager.addressBook)
                && userPrefs.equals(otherModelManager.userPrefs)
                && filteredPersons.equals(otherModelManager.filteredPersons);
    }

    //===========  Finance =============================================================

    @Override
    public void setFinancesBook(ReadOnlyFinancesBook financesBook) {
        this.financesBook.resetData(financesBook);
    }

    @Override
    public ReadOnlyFinancesBook getFinancesBook() {
        return financesBook;
    }

    @Override
    public boolean hasFinance(Finance finance) {
        requireNonNull(finance);
        return financesBook.hasFinance(finance);
    }

    @Override
    public void setExpense(Expense target, Expense editedExpense) {
        requireAllNonNull(target, editedExpense);

        financesBook.setExpense(target, editedExpense);
    }

    @Override
    public void setCommission(Commission target, Commission editedCommission) {
        requireAllNonNull(target, editedCommission);

        financesBook.setCommission(target, editedCommission);
    }


    @Override
    public void addCommission(Commission commission) {
        financesBook.addFinance(commission);
    }
    public ObservableList<Finance> getFilteredFinanceList() {
        return filteredFinances;
    }

    @Override
    public void updateFilteredFinanceList(Predicate<Finance> predicate) {
        requireNonNull(predicate);
        filteredFinances.setPredicate(predicate);
    }

    @Override
    public void addExpense(Expense expense) {
        financesBook.addFinance(expense);
    }

    @Override
    public void deleteFinance(Finance target) {
        financesBook.removeFinance(target);
    }

    @Override
    public ObservableList<Finance> getFinanceList() {
        return financesBook.getFinanceList();
    }
}
