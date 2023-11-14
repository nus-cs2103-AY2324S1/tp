package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalVenues.LT27;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.displayable.DisplayableListViewItem;
import seedu.address.model.event.Event;
import seedu.address.model.person.Person;
import seedu.address.model.rsvp.Rsvp;
import seedu.address.model.rsvp.RsvpStatus;
import seedu.address.model.vendor.Vendor;
import seedu.address.model.venue.Venue;
import seedu.address.testutil.VenueBuilder;

public class CreateVenueCommandTest {

    @Test
    public void constructor_nullVenue_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new CreateEventCommand(null));
    }

    @Test
    public void execute_venueAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingVenueAdded modelStub = new ModelStubAcceptingVenueAdded();
        Venue validVenue = new VenueBuilder().build();

        CommandResult commandResult = new CreateVenueCommand(validVenue).execute(modelStub);

        assertEquals(String.format(CreateVenueCommand.MESSAGE_SUCCESS, Messages.format(validVenue)),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validVenue), modelStub.venuesAdded);
    }

    @Test
    public void execute_duplicateVenue_throwsCommandException() {
        Venue validVenue = new VenueBuilder().build();
        CreateVenueCommand createVenueCommand = new CreateVenueCommand(validVenue);
        ModelStub modelStub = new ModelStubWithVenue(validVenue);

        assertThrows(CommandException.class, CreateVenueCommand.MESSAGE_DUPLICATE_VENUE, () -> createVenueCommand
                .execute(modelStub));
    }

    @Test
    public void equals() {
        Venue lt27 = new VenueBuilder().withName("LT27").build();
        Venue clb = new VenueBuilder().withName("CLB").build();
        CreateVenueCommand createVenueLT27Command = new CreateVenueCommand(lt27);
        CreateVenueCommand createVenueClbCommand = new CreateVenueCommand(clb);

        // same object -> returns true
        assertTrue(createVenueLT27Command.equals(createVenueLT27Command));

        // same values -> returns true
        CreateVenueCommand createVenueLT27CommandCopy = new CreateVenueCommand(lt27);
        assertTrue(createVenueLT27Command.equals(createVenueLT27CommandCopy));

        // different types -> returns false
        assertFalse(createVenueLT27Command.equals(1));

        // null -> returns false
        assertFalse(createVenueLT27Command.equals(null));

        // different person -> returns false
        assertFalse(createVenueLT27Command.equals(createVenueClbCommand));
    }

    @Test
    public void toStringMethod() {
        CreateVenueCommand createVenueCommand = new CreateVenueCommand(LT27);
        String expected = CreateVenueCommand.class.getCanonicalName() + "{toAdd=" + LT27 + "}";
        assertEquals(expected, createVenueCommand.toString());
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
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
        public void setAddressBookFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Person getPerson(Index index) throws CommandException {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public List<Person> getPersons(Set<Index> indices) throws CommandException {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBook(ReadOnlyAddressBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
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
        public void setPerson(Person target, Person editedPerson) {
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
        public boolean hasEvent(Event event) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addEvent(Event event) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteEvent(Event target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setEvent(Event target, Event editedEvent) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Event createEditedEvent(Event eventToEdit, List<Person> personsToAdd,
                                       List<Vendor> vendorsToAdd, Venue venueToAdd) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void resetEvents() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void resetGuests() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void resetVenues() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void resetVendors() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void resetRsvps() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Event> getFilteredEventsList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredEventList(Predicate<Event> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasVenue(Venue venue) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setEventToView(Event event) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addVenue(Venue venue) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Venue getVenue(Index index) throws CommandException {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Event getEventToView() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteVenue(Venue venue) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setVenue(Venue target, Venue editedVenue) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Person> getFilteredEventAttendeesList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<DisplayableListViewItem> getFilteredDisplayableItemList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Venue> getFilteredVenuesList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addRsvp(Rsvp rsvp) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteRsvp(Rsvp rsvp) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setRsvps(List<Rsvp> rsvps) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Rsvp findRsvp(Event event, Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Rsvp createRsvp(Index eventIndex, Index personIndex, RsvpStatus rsvpStatus) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredVenueList(Predicate<Venue> predicate) {
            return;
        }

        @Override
        public boolean isValidRsvp(Rsvp rsvp) {
            throw new AssertionError("This method should not be called.");
        }

        public boolean hasVendor(Vendor vendor) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Rsvp> getFilteredRsvpList() {
            throw new AssertionError("This method should not be called.");
        }

        public void addVendor(Vendor vendor) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteVendor(Vendor target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setVendor(Vendor target, Vendor editedVendor) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Vendor> getFilteredVendorList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Vendor> getFilteredEventVendorsList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredVendorList(Predicate<Vendor> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Vendor getVendor(Index index) throws CommandException {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public List<Vendor> getVendors(Set<Index> indices) throws CommandException {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Rsvp> getFilteredEventRsvpList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredEventRsvpList(Predicate<Rsvp> predicate) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single person.
     */
    private class ModelStubWithVenue extends ModelStub {
        private final Venue venue;

        ModelStubWithVenue(Venue venue) {
            requireNonNull(venue);
            this.venue = venue;
        }

        @Override
        public boolean hasVenue(Venue venue) {
            requireNonNull(venue);
            return this.venue.isSameVenue(venue);
        }
    }

    /**
     * A Model stub that always accept the person being added.
     */
    private class ModelStubAcceptingVenueAdded extends ModelStub {
        final ArrayList<Venue> venuesAdded = new ArrayList<>();

        @Override
        public boolean hasVenue(Venue venue) {
            requireNonNull(venue);
            return venuesAdded.stream().anyMatch(venue::isSameVenue);
        }

        @Override
        public void addVenue(Venue venue) {
            requireNonNull(venue);
            venuesAdded.add(venue);
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }

}
