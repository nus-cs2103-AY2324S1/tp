package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
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
import seedu.address.testutil.MusicianBuilder;

public class AddMusiciantoBandCommandTest {

    @Test
    public void constructor_nullMusician_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddMusiciantoBandCommand(1, null));
    }
    @Test
    public void constructor_nullBand_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddMusiciantoBandCommand(null, 1));
    }
    @Test
    public void constructor_nullBandAndMusician_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddMusiciantoBandCommand(null, null));
    }
    @Test
    public void execute_musicianAcceptedByModel_addSuccessful() throws Exception {
        AddMusiciantoBandCommandTest.ModelStubAcceptingMusicianAddedToBand modelBandStub =
                new AddMusiciantoBandCommandTest.ModelStubAcceptingMusicianAddedToBand();

        Musician validMusician = new MusicianBuilder().build();
        Band validBand = new BandBuilder().build();
        CommandResult addCommandResult = new AddCommand(validMusician).execute(modelBandStub);
        CommandResult addBandCommandResult = new AddBandCommand(validBand).execute(modelBandStub);
        CommandResult commandResult = new AddMusiciantoBandCommand(0, 0).execute(modelBandStub);

        assertEquals(String.format(AddMusiciantoBandCommand.MESSAGE_SUCCESS, Messages.format(0, 0)),
                commandResult.getFeedbackToUser());
        assertEquals(validBand, modelBandStub.bandsAdded.get(0));
        assertEquals(validBand.getMusicians(), modelBandStub.bandsAdded.get(0).getMusicians());
    }
    @Test
    public void execute_duplicateMusicianAddedtoBand_throwsCommandException() throws Exception {
        AddMusiciantoBandCommandTest.ModelStubAcceptingMusicianAddedToBand modelBandStub =
                new AddMusiciantoBandCommandTest.ModelStubAcceptingMusicianAddedToBand();

        Musician validMusician = new MusicianBuilder().build();
        Band validBand = new BandBuilder().build();
        CommandResult addCommandResult = new AddCommand(validMusician).execute(modelBandStub);
        CommandResult addBandCommandResult = new AddBandCommand(validBand).execute(modelBandStub);
        CommandResult firstAddMusiciancommandResult = new AddMusiciantoBandCommand(0, 0).execute(modelBandStub);

        assertThrows(CommandException.class,
                AddMusiciantoBandCommand.MESSAGE_DUPLICATE_MUSICIAN, () ->
                        new AddMusiciantoBandCommand(0, 0).execute(modelBandStub));
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
     * A Model stub that always accept the musician being added into the band.
     */
    private class ModelStubAcceptingMusicianAddedToBand extends AddMusiciantoBandCommandTest.ModelStub {
        final ArrayList<Band> bandsAdded = new ArrayList<>();
        final ArrayList<Musician> musiciansAdded = new ArrayList<>();
        @Override
        public boolean hasMusicianInBand(int bandToAddInto, int musicianToAdd) {
            requireNonNull(bandToAddInto);
            requireNonNull(musicianToAdd);
            return bandsAdded.get(bandToAddInto)
                    .hasMusician(musiciansAdded.get(musicianToAdd));
        }
        @Override
        public void addMusicianToBand(int bandToAddInto, int musicianToAdd) {
            bandsAdded.get(bandToAddInto).getModifiableMusicianList()
                    .add(musiciansAdded.get(musicianToAdd));
        }
        @Override
        public void addMusician(Musician musician) {
            requireNonNull(musician);
            musiciansAdded.add(musician);
        }
        @Override
        public void addBand(Band band) {
            requireNonNull(band);
            bandsAdded.add(band);
        }
        @Override
        public boolean hasBand(Band band) {
            requireNonNull(band);
            return bandsAdded.contains(band);
        }
        @Override
        public boolean hasMusician(Musician musician) {
            requireNonNull(musician);
            return musiciansAdded.contains(musician);
        }
        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }

}
