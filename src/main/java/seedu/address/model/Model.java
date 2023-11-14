package seedu.address.model;

import java.nio.file.Path;
import java.util.Set;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.event.Event;
import seedu.address.model.finance.Commission;
import seedu.address.model.finance.Expense;
import seedu.address.model.finance.Finance;
import seedu.address.model.person.Person;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;

    /** {@code Predicate} that always evaluate to true */
    Predicate<Finance> PREDICATE_SHOW_ALL_FINANCES = unused -> true;

    /** {@code Predicate} that always evaluate to true */
    Predicate<Finance> PREDICATE_SHOW_ALL_EXPENSES = finance -> finance instanceof Expense;

    /** {@code Predicate} that always evaluate to true */
    Predicate<Finance> PREDICATE_SHOW_ALL_COMMISSIONS = finance -> finance instanceof Commission;

    /** {@code Predicate} that always evaluate to true */
    Predicate<Event> PREDICATE_SHOW_ALL_EVENTS = unused -> true;

    /** {@code Predicate} that always evaluate to true */
    Predicate<Event> PREDICATE_SHOW_ALL_EVENTS_AFTER_TODAY = Event::isAfterToday;


    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' address book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Returns the user prefs' events file path.
     */
    Path getEventsFilePath();

    /**
     * Returns the user prefs' address book file path.
     */
    Path getFinanceFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setAddressBookFilePath(Path addressBookFilePath);

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setAddressBook(ReadOnlyAddressBook addressBook);

    /** Returns the AddressBook */
    ReadOnlyAddressBook getAddressBook();

    /**
     * Replaces events book data with the data in {@code eventsBook}.
     */
    void setEventsBook(ReadOnlyEventsBook eventsBook);

    /** Returns the EventsBook */
    ReadOnlyEventsBook getEventsBook();


    /**
     * Replaces finance book data with the data in {@code financesBook}.
     */
    void setFinancesBook(ReadOnlyFinancesBook financesBook);

    /** Returns the AddressBook */
    ReadOnlyFinancesBook getFinancesBook();

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    boolean hasPerson(Person person);

    /**
     * Deletes the given person.
     * The person must exist in the address book.
     */
    void deletePerson(Person target);

    /**
     * Adds the given person.
     * {@code person} must not already exist in the address book.
     */
    void addPerson(Person person);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    void setPerson(Person target, Person editedPerson);


    /**
     * Returns true if an event with the same identity as {@code event} exists in the events book.
     */
    boolean hasEvent(Event toAdd);

    /**
     * Adds the given {@code event}.
     */
    void addEvent(Event event);

    /**
     * Replaces the given event {@code target} with {@code editedEvent}.
     * {@code target} must exist in the events book.
     * The event identity of {@code editedEvent} must not be the same as another existing event in the events book.
     */
    void setEvent(Event target, Event editedPerson);

    /**
     * Checks if client tagged exists.
     * @return true if client exist false if client does not.
     */
    boolean isValidClient(Person client);

    Set<Person> getAllMatchedClients(Set<Person> clients);

    Person getMatchedClient(Person client);

    /**
     * Deletes the given event.
     * The event must exist in the address book.
     */
    void deleteEvent(Event target);

    /**
     * Returns an unmodifiable view of events
     */
    ObservableList<Event> getEventList();

    ObservableList<Event> getFilteredEventList();

    void updateFilteredEventList(Predicate<Event> predicate);


    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Person> getFilteredPersonList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);

    //=========== Finance ==================================================================================

    /**
     * Adds the given commission.
     * {@code person} must already exist in the address book.
     */
    void addCommission(Commission commission);

    /** Returns an unmodifiable view of the filtered finance list */
    ObservableList<Finance> getFilteredFinanceList();

    /**
     * Updates the filter of the filtered finance list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredFinanceList(Predicate<Finance> predicate);

    /**
     * Adds the given expense.
     * If included, {@code person} must already exist in the address book.
     */
    void addExpense(Expense expense);

    void deleteFinance(Finance financeToDelete);

    /**
     * Returns true if a finance with the same identity as {@code finance} exists in the finance book.
     */
    boolean hasFinance(Finance toAdd);

    ObservableList<Finance> getFinanceList();

    /**
     * Replaces the given expense {@code target} with {@code editedExpense}.
     * {@code target} must exist in the finance book.
     * The expense identity of {@code editedExpense} must not be the same as
     * another existing expense in the finance book.
     */
    void setExpense(Expense target, Expense editedExpense);

    /**
     * Replaces the given commission {@code target} with {@code editedCommission}.
     * {@code target} must exist in the finance book.
     * The commission identity of {@code editedCommission} must not be the same as
     * another existing commission in the finance book.
     */
    void setCommission(Commission target, Commission editedCommission);

}
