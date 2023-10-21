package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalBands.ACE;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.band.Band;
import seedu.address.model.musician.Musician;
import seedu.address.testutil.BandBuilder;

public class AddBandCommandTest {

    @Test
    public void constructor_nullBand_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddBandCommand(null));
    }

    @Test
    public void execute_musicianAcceptedByModel_addSuccessful() throws Exception {
        AddBandCommandTest.ModelStubAcceptingBandAdded modelStub = new AddBandCommandTest.ModelStubAcceptingBandAdded();
        Band validBand = new BandBuilder().build();

        CommandResult commandResult = new AddBandCommand(validBand).execute(modelStub);

        assertEquals(String.format(AddBandCommand.MESSAGE_SUCCESS, Messages.format(validBand)),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validBand), modelStub.bandsAdded);
    }

    @Test
    public void execute_duplicateBand_throwsCommandException() {
        Band validBand = new BandBuilder().build();
        AddBandCommand addBandCommand = new AddBandCommand(validBand);
        AddBandCommandTest.ModelStub modelStub = new AddBandCommandTest.ModelStubWithBand(validBand);

        assertThrows(CommandException.class,
                AddBandCommand.MESSAGE_DUPLICATE_BAND, () -> addBandCommand.execute(modelStub));
    }
    @Test
    public void equals() {
        Band coldplay = new BandBuilder().withName("Coldplay").build();
        Band imagineDragons = new BandBuilder().withName("ImagineDragons").build();
        AddBandCommand addColdplayCommand = new AddBandCommand(coldplay);
        AddBandCommand addImagineDragonsCommand = new AddBandCommand(imagineDragons);

        // same object -> returns true
        assertTrue(addColdplayCommand.equals(addColdplayCommand));

        // same values -> returns true
        AddBandCommand addColdplayCommandCopy = new AddBandCommand(coldplay);
        assertTrue(addColdplayCommand.equals(addColdplayCommandCopy));

        // different types -> returns false
        assertFalse(addColdplayCommand.equals(1));

        // null -> returns false
        assertFalse(addColdplayCommand.equals(null));

        // different musician -> returns false
        assertFalse(addColdplayCommand.equals(addImagineDragonsCommand));
    }
    @Test
    public void toStringMethod() {
        AddBandCommand addBandCommand = new AddBandCommand(ACE);
        String expected = AddBandCommand.class.getCanonicalName() + "{toAdd=" + ACE + "}";
        assertEquals(expected, addBandCommand.toString());
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
        public void addMusician(Musician musician) {
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
        public boolean hasMusician(Musician musician) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteMusician(Musician target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setMusician(Musician target, Musician editedMusician) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Musician> getFilteredMusicianList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Band> getFilteredBandList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredMusicianList(Predicate<Musician> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredMusicianList(int bandIndex) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredBandList(Predicate<Band> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasBand(Band band) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addBand(Band band) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasMusicianInBand(int addInto, int toAdd) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addMusicianToBand(int addInto, int toAdd) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteBand(Band target) {
            throw new AssertionError("This method should not be called.");
        }
    }
    /**
     * A Model stub that contains a single band.
     */
    private class ModelStubWithBand extends AddBandCommandTest.ModelStub {
        private final Band band;
        ModelStubWithBand(Band band) {
            requireNonNull(band);
            this.band = band;
        }

        @Override
        public boolean hasBand(Band band) {
            requireNonNull(band);
            return this.band.isSameBand(band);
        }
    }
    /**
     * A Model stub that always accept the band being added.
     */
    private class ModelStubAcceptingBandAdded extends AddBandCommandTest.ModelStub {
        final ArrayList<Band> bandsAdded = new ArrayList<>();

        @Override
        public boolean hasBand(Band band) {
            requireNonNull(band);
            return bandsAdded.stream().anyMatch(band::isSameBand);
        }

        @Override
        public void addBand(Band band) {
            requireNonNull(band);
            bandsAdded.add(band);
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }
}
