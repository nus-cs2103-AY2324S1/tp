package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.index.Index;
import seedu.address.model.event.Event;
import seedu.address.model.person.Person;
import seedu.address.model.statistics.ReadOnlySummaryStatistic;
import seedu.address.model.statistics.SummaryStatistic;
import seedu.address.model.tag.Tag;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AddressBook addressBook;
    private final SummaryStatistic summaryStatistic;
    private final EventBook eventBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Person> filteredPersons;
    private final FilteredList<Tag> filteredTags;
    private final FilteredList<Event> filteredEvents;

    private Index lastViewedPersonIndex;


    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyEventBook eventBook, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.addressBook = new AddressBook(addressBook);
        this.eventBook = new EventBook(eventBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPersons = new FilteredList<>(this.addressBook.getPersonList());
        filteredTags = new FilteredList<>(this.addressBook.getTagList());
        filteredEvents = new FilteredList<>(this.eventBook.getEventList());
        summaryStatistic = new SummaryStatistic(this.addressBook.getPersonList());
    }

    public ModelManager() {
        this(new AddressBook(), new EventBook(), new UserPrefs());
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
    public Path getEventBookFilePath() {
        return userPrefs.getEventBookFilePath();
    }

    @Override
    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setAddressBookFilePath(addressBookFilePath);
    }

    @Override
    public void setEventBookFilePath(Path eventBookFilePath) {
        requireNonNull(eventBookFilePath);
        userPrefs.setEventBookFilePath(eventBookFilePath);
    }

    //=========== AddressBook ================================================================================

    @Override
    public void setAddressBook(ReadOnlyAddressBook addressBook) {
        this.addressBook.resetData(addressBook);
    }

    @Override
    public void setEventBook(ReadOnlyEventBook eventBook) {
        this.eventBook.resetData(eventBook);
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return addressBook;
    }

    @Override
    public ReadOnlyEventBook getEventBook() {
        return eventBook;
    }

    @Override
    public ReadOnlySummaryStatistic getSummaryStatistic() {
        return summaryStatistic;
    }

    @Override
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return addressBook.hasPerson(person);
    }

    @Override
    public boolean hasEvent(Event event) {
        requireNonNull(event);
        return eventBook.hasEvent(event);
    }

    @Override
    public void deletePerson(Person target) {
        addressBook.removePerson(target);
    }

    @Override
    public void deleteEvent(Event target) {
        eventBook.removeEvent(target);
    }

    @Override
    public void addPerson(Person person) {
        addressBook.addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void addEvent(Event event) {
        eventBook.addEvent(event);
        updateFilteredEventList(PREDICATE_SHOW_ALL_EVENTS);
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);
        addressBook.setPerson(target, editedPerson);
    }

    @Override
    public void setEvent(Event target, Event editedEvent) {
        eventBook.setEvent(target, editedEvent);
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
    public void updateFilteredPersonList(List<Predicate<Person>> predicatesList) {
        requireNonNull(predicatesList);
        Predicate<Person> combinedPredicate = predicatesList.stream()
                .reduce(Predicate::and)
                .orElse(person -> true);
        filteredPersons.setPredicate(combinedPredicate);
    }

    @Override
    public ObservableList<Tag> getFilteredTagList() {
        return filteredTags;
    }
    @Override
    public void updateFilteredTagList(Predicate<Tag> predicate) {
        filteredTags.setPredicate(predicate);
    }

    @Override
    public ObservableList<Event> getFilteredEventList() {
        return filteredEvents;
    }

    @Override
    public void updateFilteredEventList(Predicate<Event> predicate) {
        requireNonNull(predicate);
        filteredEvents.setPredicate(predicate);
    }

    @Override
    public void updateFilteredEventList(List<Predicate<Event>> predicatesList) {
        requireNonNull(predicatesList);
        Predicate<Event> combinedPredicate = predicatesList.stream()
                .reduce(Predicate::and)
                .orElse(event -> true);
        filteredEvents.setPredicate(combinedPredicate);
    }

    @Override
    public void sortPersonList(Comparator<Person> comparator) {
        requireNonNull(comparator);
        addressBook.sortAddressBook(comparator);
    }

    @Override
    public void sortEventList(Comparator<Event> comparator) {
        requireNonNull(comparator);
        eventBook.sortEventBook(comparator);
    }

    @Override
    public void setLastViewedPersonIndex(Index index) {
        requireNonNull(index);
        lastViewedPersonIndex = index;
    }

    @Override
    public Index getLastViewedPersonIndex() {
        return lastViewedPersonIndex;
    }

    @Override
    public boolean hasTag(Tag tag) {
        requireNonNull(tag);
        return addressBook.hasTag(tag);
    }
    @Override
    public void addTag(Tag tag) {
        addressBook.addTag(tag);
    }

    public void loadSummaryStatistics() {
        summaryStatistic.updatePersonData(addressBook.getPersonList());
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
                && filteredPersons.equals(otherModelManager.filteredPersons)
                && filteredEvents.equals(otherModelManager.filteredEvents);
    }

}
