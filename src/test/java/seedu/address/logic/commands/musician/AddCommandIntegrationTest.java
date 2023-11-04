package seedu.address.logic.commands.musician;

import static seedu.address.logic.commands.CommandTestUtil.VALID_GENRE_JAZZ;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GENRE_POP;
import static seedu.address.logic.commands.CommandTestUtil.VALID_INSTRUMENT_FLUTE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_INSTRUMENT_PIANO;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.typicalentities.TypicalAddressBook.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.musician.Musician;
import seedu.address.testutil.MusicianBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_newMusicianNoTags_success() {
        Musician validMusician = new MusicianBuilder().build();

        // assert new musician has no tags
        assert validMusician.getTags().isEmpty() : "New musician added should have no tag";
        assert validMusician.getInstruments().isEmpty() : "New musician added should have no instrument";
        assert validMusician.getGenres().isEmpty() : "New musician added should have no genre";

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.addMusician(validMusician);

        assertCommandSuccess(new AddCommand(validMusician), model,
                String.format(AddCommand.MESSAGE_SUCCESS, Messages.format(validMusician)),
                expectedModel);
    }

    @Test
    public void execute_newMusicianWithTags_success() {
        Musician validMusician = new MusicianBuilder()
                .withTags(VALID_TAG_FRIEND)
                .withInstruments(VALID_INSTRUMENT_PIANO, VALID_INSTRUMENT_FLUTE)
                .withGenres(VALID_GENRE_JAZZ, VALID_GENRE_POP)
                .build();

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.addMusician(validMusician);

        assertCommandSuccess(new AddCommand(validMusician), model,
                String.format(AddCommand.MESSAGE_SUCCESS, Messages.format(validMusician)),
                expectedModel);
    }

    @Test
    public void execute_duplicateMusician_throwsCommandException() {
        Musician musicianInList = model.getAddressBook().getMusicianList().get(0);
        assertCommandFailure(new AddCommand(musicianInList), model,
                AddCommand.MESSAGE_DUPLICATE_MUSICIAN);
    }

    // No more than one invalid input in a test case
    @Test
    public void execute_duplicateName_throwsCommandException() {
        Musician musicianInList = model.getAddressBook().getMusicianList().get(0);
        Musician newMusician = new MusicianBuilder().withName(musicianInList.getName().toString()).build();

        // assert only name is duplicate
        assert musicianInList.getName().equals(newMusician.getName()) : "Both musicians should have same name";
        assert !musicianInList.getPhone().equals(newMusician.getPhone())
                : "Both musicians should not have same phone";
        assert !musicianInList.getEmail().equals(newMusician.getEmail()) : "Both musicians should not have same email";

        assertCommandFailure(new AddCommand(newMusician), model,
                AddCommand.MESSAGE_DUPLICATE_MUSICIAN);
    }

    // No more than one invalid input in a test case
    @Test
    public void execute_duplicatePhone_throwsCommandException() {
        Musician musicianInList = model.getAddressBook().getMusicianList().get(0);
        Musician newMusician = new MusicianBuilder().withPhone(musicianInList.getPhone().toString()).build();

        // assert only name is duplicate
        assert !musicianInList.getName().equals(newMusician.getName()) : "Both musicians should not have same name";
        assert musicianInList.getPhone().equals(newMusician.getPhone())
                : "Both musicians should have same phone";
        assert !musicianInList.getEmail().equals(newMusician.getEmail()) : "Both musicians should not have same email";

        assertCommandFailure(new AddCommand(newMusician), model,
                AddCommand.MESSAGE_DUPLICATE_INFO);
    }

    // No more than one invalid input in a test case
    @Test
    public void execute_duplicateEmail_throwsCommandException() {
        Musician musicianInList = model.getAddressBook().getMusicianList().get(0);
        Musician newMusician = new MusicianBuilder().withEmail(musicianInList.getEmail().toString()).build();

        // assert only name is duplicate
        assert !musicianInList.getName().equals(newMusician.getName()) : "Both musicians should not have same name";
        assert !musicianInList.getPhone().equals(newMusician.getPhone())
                : "Both musicians should not have same phone";
        assert musicianInList.getEmail().equals(newMusician.getEmail()) : "Both musicians should have same email";

        assertCommandFailure(new AddCommand(newMusician), model, AddCommand.MESSAGE_DUPLICATE_INFO);
    }

}
