package seedu.address.logic.commands.band;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.musician.AddCommand;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.band.Band;
import seedu.address.model.band.UniqueBandList;
import seedu.address.model.musician.Musician;
import seedu.address.model.musician.UniqueMusicianList;
import seedu.address.testutil.BandBuilder;
import seedu.address.testutil.MusicianBuilder;

public class AddMusicianToBandCommandTest {

    @Test
    public void constructor_nullMusician_throwsNullPointerException() {
        Index bandIndex = Index.fromOneBased(1);
        assertThrows(NullPointerException.class, () -> new AddMusicianToBandCommand(bandIndex, null));
    }
    @Test
    public void constructor_nullBand_throwsNullPointerException() {
        List<Index> musicianIndices = List.of(Index.fromOneBased(1));
        assertThrows(NullPointerException.class, () -> new AddMusicianToBandCommand(null, musicianIndices));
    }
    @Test
    public void constructor_nullBandAndMusician_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddMusicianToBandCommand(null, null));
    }
    @Test
    public void execute_musicianAcceptedByModel_addSuccessful() throws Exception {
        AddMusicianToBandCommandTest.ModelStubAcceptingMusicianAddedToBand modelBandStub =
                new AddMusicianToBandCommandTest.ModelStubAcceptingMusicianAddedToBand();

        Musician validMusician = new MusicianBuilder().build();
        Band validBand = new BandBuilder().build();
        CommandResult addCommandResult = new AddCommand(validMusician).execute(modelBandStub);
        CommandResult addBandCommandResult = new AddBandCommand(validBand).execute(modelBandStub);
        Index bandIndex = Index.fromOneBased(1);
        List<Index> musicianIndices = List.of(Index.fromOneBased(1));
        CommandResult commandResult = new AddMusicianToBandCommand(bandIndex, musicianIndices).execute(modelBandStub);

        String expectedSuccessMessage = String.format(
                AddMusicianToBandCommand.MESSAGE_SUCCESS,
                Messages.format(modelBandStub.bandsAdded.get(bandIndex),
                        musicianIndices.stream().map(modelBandStub.musiciansAdded::get).collect(Collectors.toList()))
        );
        assertEquals(expectedSuccessMessage, commandResult.getFeedbackToUser());
        assertEquals(validBand, modelBandStub.bandsAdded.get(bandIndex));
        assertEquals(validBand.getMusicians(), modelBandStub.bandsAdded.get(bandIndex).getMusicians());
    }

    @Test
    public void execute_bandIndexOutOfBounds_throwsInvalidBandIndex() throws Exception {
        AddMusicianToBandCommandTest.ModelStubAcceptingMusicianAddedToBand modelBandStub =
                new AddMusicianToBandCommandTest.ModelStubAcceptingMusicianAddedToBand();

        Musician validMusician = new MusicianBuilder().build();
        Band validBand = new BandBuilder().build();
        // add musician to model
        CommandResult addCommandResult = new AddCommand(validMusician).execute(modelBandStub);
        // add band to model
        CommandResult addBandCommandResult = new AddBandCommand(validBand).execute(modelBandStub);
        Index invalidBandIndex = Index.fromOneBased(2);
        Index musicianIndex = Index.fromOneBased(1);
        List<Index> validMusicianIndexList = new ArrayList<>();
        validMusicianIndexList.add(musicianIndex);
        assertThrows(CommandException.class, Messages.MESSAGE_INVALID_BAND_DISPLAYED_INDEX, () ->
                new AddMusicianToBandCommand(invalidBandIndex, validMusicianIndexList).execute(modelBandStub));
    }
    @Test
    public void execute_musicianIndexOutOfBounds_throwsInvalidMusicianIndex() throws Exception {
        AddMusicianToBandCommandTest.ModelStubAcceptingMusicianAddedToBand modelBandStub =
                new AddMusicianToBandCommandTest.ModelStubAcceptingMusicianAddedToBand();

        Musician validMusician = new MusicianBuilder().build();
        Band validBand = new BandBuilder().build();
        // add musician to model
        CommandResult addCommandResult = new AddCommand(validMusician).execute(modelBandStub);
        // add band to model
        CommandResult addBandCommandResult = new AddBandCommand(validBand).execute(modelBandStub);
        Index bandIndex = Index.fromOneBased(1);
        Index invalidMusicianIndex = Index.fromOneBased(2);
        List<Index> invalidMusicianIndexList = new ArrayList<>();
        invalidMusicianIndexList.add(invalidMusicianIndex);
        assertThrows(CommandException.class, Messages.MESSAGE_INVALID_MUSICIAN_DISPLAYED_INDEX, () ->
                new AddMusicianToBandCommand(bandIndex, invalidMusicianIndexList).execute(modelBandStub));
    }
    @Test
    public void execute_duplicateMusicianAddedtoBand_throwsCommandException() throws Exception {
        AddMusicianToBandCommandTest.ModelStubAcceptingMusicianAddedToBand modelBandStub =
                new AddMusicianToBandCommandTest.ModelStubAcceptingMusicianAddedToBand();

        Musician validMusician = new MusicianBuilder().build();
        Band validBand = new BandBuilder().build();
        // add musician to model
        CommandResult addCommandResult = new AddCommand(validMusician).execute(modelBandStub);
        // add band to model
        CommandResult addBandCommandResult = new AddBandCommand(validBand).execute(modelBandStub);
        Index bandIndex = Index.fromOneBased(1);
        List<Index> musicianIndices = List.of(Index.fromOneBased(1));
        CommandResult firstAddMusiciancommandResult =
                new AddMusicianToBandCommand(bandIndex, musicianIndices).execute(modelBandStub);

        assertThrows(CommandException.class,
                AddMusicianToBandCommand.MESSAGE_DUPLICATE_MUSICIAN, () ->
                        new AddMusicianToBandCommand(bandIndex, musicianIndices).execute(modelBandStub));
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
        public boolean hasDuplicateInfo(Musician toExclude, Musician musician) {
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
        public void updateFilteredBandList(Predicate<Band> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredBandMusicianList(Predicate<Band> predicate) {
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
        public void setBand(Band band, Band toSet) {
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
        public void removeMusicianFromBand(int bandIndex, int musicianIndex) {
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
    private class ModelStubAcceptingMusicianAddedToBand extends AddMusicianToBandCommandTest.ModelStub {
        final UniqueBandList bandsAdded = new UniqueBandList();
        final UniqueMusicianList musiciansAdded = new UniqueMusicianList();
        @Override
        public boolean hasMusicianInBand(int bandToAddInto, int musicianToAdd) {
            requireNonNull(bandToAddInto);
            requireNonNull(musicianToAdd);
            Index bandIndex = Index.fromZeroBased(bandToAddInto);
            Index musicianIndex = Index.fromZeroBased(musicianToAdd);
            return bandsAdded.get(bandIndex)
                    .hasMusician(musiciansAdded.get(musicianIndex));
        }
        @Override
        public void addMusicianToBand(int bandToAddInto, int musicianToAdd) {
            Index bandIndex = Index.fromZeroBased(bandToAddInto);
            Index musicianIndex = Index.fromZeroBased(musicianToAdd);
            bandsAdded.get(bandIndex).getModifiableMusicianList()
                    .add(musiciansAdded.get(musicianIndex));
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
        public boolean hasDuplicateInfo(Musician toExclude, Musician musician) {
            requireNonNull(musician);
            return musiciansAdded.hasDuplicateInfo(null, musician);
        }
        @Override
        public ObservableList<Band> getFilteredBandList() {
            return new FilteredList<>(bandsAdded.asUnmodifiableObservableList());
        }
        @Override
        public void updateFilteredBandList(Predicate<Band> predicate) {
        }
        @Override
        public void updateFilteredBandMusicianList(Predicate<Band> predicate) {
        }
        @Override
        public ObservableList<Musician> getFilteredMusicianList() {
            return new FilteredList<>(musiciansAdded.asUnmodifiableObservableList());
        }
        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }

}
