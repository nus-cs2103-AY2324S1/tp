package seedu.address.logic.commands.musician;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalMusicians.getTypicalAddressBook;

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
    public void execute_newMusician_success() {
        Musician validMusician = new MusicianBuilder().build();

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

}
