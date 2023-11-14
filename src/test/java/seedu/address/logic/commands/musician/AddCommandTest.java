package seedu.address.logic.commands.musician;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.typicalentities.TypicalMusicians.ALICE;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.band.Band;
import seedu.address.model.musician.Musician;
import seedu.address.testutil.MusicianBuilder;

public class AddCommandTest {

    @Test
    public void constructor_nullMusician_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(null));
    }

    @Test
    public void execute_musicianAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingMusicianAdded modelStub = new ModelStubAcceptingMusicianAdded();
        Musician validMusician = new MusicianBuilder().build();

        CommandResult commandResult = new AddCommand(validMusician).execute(modelStub);

        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, Messages.format(validMusician)),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validMusician), modelStub.musiciansAdded);
    }

    @Test
    public void execute_duplicateMusician_throwsCommandException() {
        Musician validMusician = new MusicianBuilder().build();
        AddCommand addCommand = new AddCommand(validMusician);
        ModelStub modelStub = new ModelStubWithMusician(validMusician);

        assertThrows(CommandException.class,
                AddCommand.MESSAGE_DUPLICATE_MUSICIAN, () -> addCommand.execute(modelStub));
    }

    @Test
    public void execute_duplicateName_throwsCommandException() {
        Musician existingMusician = ALICE;
        Musician musicianWithDuplicateName = new MusicianBuilder().withName(existingMusician.getName().toString())
                .build();

        // assert only name is duplicate
        assert existingMusician.getName().equals(musicianWithDuplicateName.getName())
                : "Both musicians should have same name";
        assert !existingMusician.getPhone().equals(musicianWithDuplicateName.getPhone())
                : "Both musicians should not have same phone";
        assert !existingMusician.getEmail().equals(musicianWithDuplicateName.getEmail())
                : "Both musicians should not have same email";

        AddCommand addCommand = new AddCommand(musicianWithDuplicateName);
        ModelStub modelStub = new ModelStubWithMusician(existingMusician);

        assertThrows(CommandException.class,
                AddCommand.MESSAGE_DUPLICATE_MUSICIAN, () -> addCommand.execute(modelStub));
    }

    @Test
    public void execute_duplicatePhone_throwsCommandException() {
        Musician existingMusician = ALICE;
        Musician musicianWithDuplicatePhone = new MusicianBuilder().withPhone(existingMusician.getPhone().toString())
                .build();

        // assert only phone is duplicate
        assert !existingMusician.getName().equals(musicianWithDuplicatePhone.getName())
                : "Both musicians should not have same name";
        assert existingMusician.getPhone().equals(musicianWithDuplicatePhone.getPhone())
                : "Both musicians should have same phone";
        assert !existingMusician.getEmail().equals(musicianWithDuplicatePhone.getEmail())
                : "Both musicians should not have same email";

        AddCommand addCommand = new AddCommand(musicianWithDuplicatePhone);
        ModelStub modelStub = new ModelStubWithMusician(existingMusician);

        assertThrows(CommandException.class,
                AddCommand.MESSAGE_DUPLICATE_INFO, () -> addCommand.execute(modelStub));
    }

    @Test
    public void execute_duplicateEmail_throwsCommandException() {
        Musician existingMusician = ALICE;
        Musician musicianWithDuplicateEmail = new MusicianBuilder().withEmail(existingMusician.getEmail().toString())
                .build();

        // assert only email is duplicate
        assert !existingMusician.getName().equals(musicianWithDuplicateEmail.getName())
                : "Both musicians should not have same name";
        assert !existingMusician.getPhone().equals(musicianWithDuplicateEmail.getPhone())
                : "Both musicians should not have same phone";
        assert existingMusician.getEmail().equals(musicianWithDuplicateEmail.getEmail())
                : "Both musicians should have same email";

        AddCommand addCommand = new AddCommand(musicianWithDuplicateEmail);
        ModelStub modelStub = new ModelStubWithMusician(existingMusician);

        assertThrows(CommandException.class,
                AddCommand.MESSAGE_DUPLICATE_INFO, () -> addCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Musician alice = new MusicianBuilder().withName("Alice").build();
        Musician bob = new MusicianBuilder().withName("Bob").build();
        AddCommand addAliceCommand = new AddCommand(alice);
        AddCommand addBobCommand = new AddCommand(bob);

        // same object -> returns true
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> returns true
        AddCommand addAliceCommandCopy = new AddCommand(alice);
        assertTrue(addAliceCommand.equals(addAliceCommandCopy));

        // different types -> returns false
        assertFalse(addAliceCommand.equals(1));

        // null -> returns false
        assertFalse(addAliceCommand.equals(null));

        // different musician -> returns false
        assertFalse(addAliceCommand.equals(addBobCommand));
    }

    @Test
    public void toStringMethod() {
        AddCommand addCommand = new AddCommand(ALICE);
        String expected = AddCommand.class.getCanonicalName() + "{toAdd=" + ALICE + "}";
        assertEquals(expected, addCommand.toString());
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
     * A Model stub that contains a single musician.
     */
    private class ModelStubWithMusician extends ModelStub {
        private final Musician musician;

        ModelStubWithMusician(Musician musician) {
            requireNonNull(musician);
            this.musician = musician;
        }

        @Override
        public boolean hasMusician(Musician musician) {
            requireNonNull(musician);
            return this.musician.isSameMusician(musician);
        }

        @Override
        public boolean hasDuplicateInfo(Musician toExclude, Musician musician) {
            requireNonNull(musician);
            return this.musician.hasSameInfo(musician);
        }
    }

    /**
     * A Model stub that always accept the musician being added.
     */
    private class ModelStubAcceptingMusicianAdded extends ModelStub {
        final ArrayList<Musician> musiciansAdded = new ArrayList<>();

        @Override
        public boolean hasMusician(Musician musician) {
            requireNonNull(musician);
            return musiciansAdded.stream().anyMatch(musician::isSameMusician);
        }

        @Override
        public void addMusician(Musician musician) {
            requireNonNull(musician);
            musiciansAdded.add(musician);
        }

        @Override
        public boolean hasDuplicateInfo(Musician toExclude, Musician musician) {
            requireNonNull(musician);
            return musiciansAdded.stream().anyMatch(musician::hasSameInfo);
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }

}
