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
 * Default model stub to use for tests.
 */
public class ModelStub implements Model {
    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public GuiSettings getGuiSettings() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public Path getAddressBookFilePath() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public Path getEventsFilePath() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public Path getFinanceFilePath() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setAddressBookFilePath(Path addressBookFilePath) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setAddressBook(ReadOnlyAddressBook addressBook) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setEventsBook(ReadOnlyEventsBook eventsBook) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ReadOnlyEventsBook getEventsBook() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setFinancesBook(ReadOnlyFinancesBook financesBook) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ReadOnlyFinancesBook getFinancesBook() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasPerson(Person person) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void deletePerson(Person target) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void addPerson(Person person) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasEvent(Event toAdd) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void addEvent(Event event) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setEvent(Event target, Event editedPerson) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean isValidClient(Person client) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public Set<Person> getAllMatchedClients(Set<Person> clients) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public Person getMatchedClient(Person client) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void deleteEvent(Event target) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<Event> getEventList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<Event> getFilteredEventList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void updateFilteredEventList(Predicate<Event> predicate) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<Person> getFilteredPersonList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void updateFilteredPersonList(Predicate<Person> predicate) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void addCommission(Commission commission) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<Finance> getFilteredFinanceList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void updateFilteredFinanceList(Predicate<Finance> predicate) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void addExpense(Expense expense) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void deleteFinance(Finance financeToDelete) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasFinance(Finance toAdd) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<Finance> getFinanceList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setExpense(Expense target, Expense editedExpense) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setCommission(Commission target, Commission editedCommission) {
        throw new AssertionError("This method should not be called.");
    }
}
