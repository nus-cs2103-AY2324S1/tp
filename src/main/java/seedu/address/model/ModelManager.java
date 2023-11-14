package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.displayable.DisplayableListViewItem;
import seedu.address.model.event.Event;
import seedu.address.model.person.Person;
import seedu.address.model.rsvp.Rsvp;
import seedu.address.model.rsvp.RsvpStatus;
import seedu.address.model.vendor.Vendor;
import seedu.address.model.venue.Venue;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AddressBook addressBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Person> filteredPersons;
    private final FilteredList<Event> filteredEvents;
    private final FilteredList<Venue> filteredVenues;
    private final FilteredList<Person> filteredEventAttendees;
    private final FilteredList<Vendor> filteredEventVendors;
    private final FilteredList<DisplayableListViewItem> filteredDisplayableItems;
    private final FilteredList<Rsvp> filteredRsvps;
    private final FilteredList<Rsvp> filteredEventRsvps;
    private final FilteredList<Vendor> filteredVendors;
    private Event eventToView;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.addressBook = new AddressBook(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPersons = new FilteredList<>(this.addressBook.getPersonList());
        filteredEvents = new FilteredList<>(this.addressBook.getEventList());
        filteredVenues = new FilteredList<>(this.addressBook.getVenueList());
        filteredVendors = new FilteredList<>(this.addressBook.getVendorList());
        filteredEventAttendees = new FilteredList<>(this.addressBook.getEventAttendeesList());
        filteredEventVendors = new FilteredList<>(this.addressBook.getEventVendorsList());
        filteredDisplayableItems = new FilteredList<>(this.addressBook.getDisplayableItemList());
        filteredRsvps = new FilteredList<>(this.addressBook.getRsvpList());
        filteredEventRsvps = new FilteredList<>(this.addressBook.getEventRsvpsList());
    }

    public ModelManager() {
        this(new AddressBook(), new UserPrefs());
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
    public void resetEvents() {
        this.addressBook.resetEvents();
    }

    @Override
    public void resetGuests() {
        this.addressBook.resetGuests();
        this.addressBook.clearGuestsFromEvents();
        if (getEventToView() != null) {
            Event updatedEvent = getEventToView();
            updatedEvent.clearGuests();
            setEventToView(updatedEvent);
        }
    }

    @Override
    public void resetVenues() {
        this.addressBook.resetVenues();
        this.addressBook.clearVenuesFromEvents();
        if (getEventToView() != null) {
            Event updatedEvent = getEventToView();
            updatedEvent.clearVenue();
            setEventToView(updatedEvent);
        }
    }

    @Override
    public void resetVendors() {
        this.addressBook.resetVendors();
        this.addressBook.clearVendorsFromEvents();
        if (getEventToView() != null) {
            Event updatedEvent = getEventToView();
            updatedEvent.clearVendors();
            setEventToView(updatedEvent);
        }
    }

    @Override
    public void resetRsvps() {
        this.addressBook.resetRsvps();
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
    public Person getPerson(Index index) throws CommandException {
        if (index.getZeroBased() >= addressBook.getPersonList().size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }
        filteredPersons.setPredicate(PREDICATE_SHOW_ALL_PERSONS);
        return filteredPersons.get(index.getZeroBased());
    }

    @Override
    public List<Person> getPersons(Set<Index> indices) throws CommandException {
        List<Person> personList = new ArrayList<>();

        for (Index index : indices) {
            personList.add(getPerson(index));
        }

        return personList;
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);

        addressBook.setPerson(target, editedPerson);
    }

    //=========== EventWise ================================================================================

    // Events
    @Override
    public boolean hasEvent(Event event) {
        requireNonNull(event);
        return addressBook.hasEvent(event);
    }

    @Override
    public void deleteEvent(Event target) {
        addressBook.removeEvent(target);
    }

    @Override
    public void addEvent(Event event) {
        addressBook.addEvent(event);
    }

    @Override
    public Venue getVenue(Index index) throws CommandException {
        if (index == null) {
            return null;
        }

        if (index.getZeroBased() >= addressBook.getVenueList().size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_VENUE_DISPLAYED_INDEX);
        }

        return filteredVenues.get(index.getZeroBased());
    }

    @Override
    public void setEvent(Event target, Event editedEvent) {
        requireAllNonNull(target, editedEvent);

        addressBook.setEvent(target, editedEvent);
    }

    public void setEventToView(Event event) {
        if (event == null) {
            addressBook.setEventAttendees(new ArrayList<>());
            addressBook.setEventVendors(new ArrayList<>());
            addressBook.setEventRsvps(new ArrayList<>());
        } else {
            addressBook.setEventAttendees(event.getPersons());
            addressBook.setEventVendors(event.getVendors());
            addressBook.setEventRsvps(getRsvps(event));
        }
        this.eventToView = event;
    }

    private List<Rsvp> getRsvps(Event event) {
        List<Rsvp> rsvps = new ArrayList<>();
        for (Rsvp rsvp : filteredRsvps) {
            if (rsvp.getEvent().isSameEvent(event)) {
                rsvps.add(rsvp);
            }
        }
        return rsvps;
    }

    @Override
    public Event createEditedEvent(Event eventToEdit, List<Person> personsToAdd,
                                   List<Vendor> vendorsToAdd, Venue venueToAdd) {

        List<Person> currentAttendees = new ArrayList<>(eventToEdit.getPersons());
        List<Vendor> currentVendors = new ArrayList<>(eventToEdit.getVendors());

        for (Person person: personsToAdd) {
            currentAttendees.add(person);
        }
        for (Vendor vendor: vendorsToAdd) {
            currentVendors.add(vendor);
        }

        Venue venueToStore = (venueToAdd == null) ? eventToEdit.getVenue() : venueToAdd;

        return new Event(eventToEdit.getName(), eventToEdit.getDescription(),
                eventToEdit.getFromDate(), eventToEdit.getToDate(),
                eventToEdit.getNote(), currentAttendees, currentVendors, venueToStore);
    }

    // Venues
    @Override
    public boolean hasVenue(Venue venue) {
        requireNonNull(venue);
        return addressBook.hasVenue(venue);
    }

    @Override
    public void deleteVenue(Venue target) {
        addressBook.removeVenue(target);
    }

    @Override
    public void addVenue(Venue venue) {
        addressBook.addVenue(venue);
    }

    @Override
    public void setVenue(Venue target, Venue editedVenue) {
        requireAllNonNull(target, editedVenue);

        addressBook.setVenue(target, editedVenue);
    }

    // Vendors

    @Override
    public boolean hasVendor(Vendor vendor) {
        requireNonNull(vendor);
        return addressBook.hasVendor(vendor);
    }

    @Override
    public void deleteVendor(Vendor target) {
        addressBook.removeVendor(target);
    }

    @Override
    public void addVendor(Vendor vendor) {
        addressBook.addVendor(vendor);
    }

    @Override
    public void setVendor(Vendor target, Vendor editedVendor) {
        requireAllNonNull(target, editedVendor);

        addressBook.setVendor(target, editedVendor);
    }

    @Override
    public Vendor getVendor(Index index) throws CommandException {
        if (index.getZeroBased() >= addressBook.getVendorList().size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_VENDOR_INDEX);
        }
        filteredVendors.setPredicate(PREDICATE_SHOW_ALL_VENDORS);
        return filteredVendors.get(index.getZeroBased());
    }

    @Override
    public List<Vendor> getVendors(Set<Index> indices) throws CommandException {
        List<Vendor> vendorList = new ArrayList<>();

        for (Index index : indices) {
            vendorList.add(getVendor(index));
        }

        return vendorList;
    }

    @Override
    public void addRsvp(Rsvp rsvp) {
        addressBook.addRsvp(rsvp);
    }

    @Override
    public void deleteRsvp(Rsvp rsvp) {
        addressBook.removeRsvp(rsvp);
    }

    @Override
    public Rsvp findRsvp(Event event, Person person) {
        for (Rsvp rsvp : filteredRsvps) {
            if (rsvp.getEvent().isSameEvent(event) && rsvp.getPerson().isSamePerson(person)) {
                return rsvp;
            }
        }

        return null;
    }

    @Override
    public void setRsvps(List<Rsvp> rsvps) {
        addressBook.setRsvps(rsvps);
    }

    /**
     * Creates a RSVP object given the index of the event, person and status.
     * @param eventIndex The index of the event provided by the user.
     * @param personIndex The index of the person provided by the user.
     * @param rsvpStatus The status of the RSVP
     * @return
     */
    @Override
    public Rsvp createRsvp(Index eventIndex, Index personIndex, RsvpStatus rsvpStatus) {
        if (eventIndex.getZeroBased() >= getFilteredEventsList().size()
                || personIndex.getZeroBased() >= getFilteredPersonList().size()) {
            return null;
        }
        Event event = getFilteredEventsList().get(eventIndex.getZeroBased());
        Person person = getFilteredPersonList().get(personIndex.getZeroBased());
        return new Rsvp(event, person, rsvpStatus);
    }

    @Override
    public boolean isValidRsvp(Rsvp rsvp) {
        Person person = rsvp.getPerson();
        return rsvp.getEventGuests().contains(person);
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
        addressBook.setDisplayableItems(filteredPersons);
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

    //=========== Filtered Event List Accessors ==============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Event} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Event> getFilteredEventsList() {
        return filteredEvents;
    }

    @Override
    public void updateFilteredEventList(Predicate<Event> predicate) {
        requireNonNull(predicate);
        filteredEvents.setPredicate(predicate);
        addressBook.setDisplayableItems(filteredEvents);
    }

    //=========== Filtered Venue List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Venue} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Venue> getFilteredVenuesList() {
        return filteredVenues;
    }

    @Override
    public void updateFilteredVenueList(Predicate<Venue> predicate) {
        requireNonNull(predicate);
        filteredVenues.setPredicate(predicate);
        addressBook.setDisplayableItems(filteredVenues);
    }

    //=========== Filtered Event Attendees List Accessors ====================================================
    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Person> getFilteredEventAttendeesList() {
        return filteredEventAttendees;
    }

    //=========== Filtered Event Vendors List Accessors ====================================================
    /**
     * Returns an unmodifiable view of the list of {@code Vendor} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Vendor> getFilteredEventVendorsList() {
        return filteredEventVendors;
    }

    //=========== RSVP List Accessors ====================================================
    /**
     * Returns an unmodifiable view of the list of {@code Rsvp} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Rsvp> getFilteredRsvpList() {
        return filteredRsvps;
    }

    //=========== Filtered Vendor List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Vendor} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Vendor> getFilteredVendorList() {
        return filteredVendors;
    }

    @Override
    public void updateFilteredVendorList(Predicate<Vendor> predicate) {
        requireNonNull(predicate);
        filteredVendors.setPredicate(predicate);
        addressBook.setDisplayableItems(filteredVendors);
    }

    //=========== Current Event Accessor =====================================================================

    /**
     * Returns the event that was requested by the user to view.
     */
    @Override
    public Event getEventToView() {
        return eventToView;
    }

    //=========== Filtered Displayable Items List Accessor ===================================================

    /**
     * Returns an unmodifiable view of the list of {@code DisplayableListViewItem} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<DisplayableListViewItem> getFilteredDisplayableItemList() {
        return filteredDisplayableItems;
    }

    @Override
    public ObservableList<Rsvp> getFilteredEventRsvpList() {
        return filteredEventRsvps;
    }

    @Override
    public void updateFilteredEventRsvpList(Predicate<Rsvp> predicate) {
        requireNonNull(predicate);
        filteredRsvps.setPredicate(predicate);
        addressBook.setEventRsvps(filteredRsvps);
    }
}
