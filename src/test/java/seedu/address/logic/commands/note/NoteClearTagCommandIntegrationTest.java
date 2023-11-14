package seedu.address.logic.commands.note;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.testutil.TypicalObjects.getTypicalLocalCourseCatalogue;
import static seedu.address.testutil.TypicalObjects.getTypicalMappingCatalogue;
import static seedu.address.testutil.TypicalObjects.getTypicalNoteCatalogue;
import static seedu.address.testutil.TypicalObjects.getTypicalPartnerCourseCatalogue;
import static seedu.address.testutil.TypicalObjects.getTypicalUniversityCatalogue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.messages.Messages;
import seedu.address.model.SeplendidModel;
import seedu.address.model.SeplendidModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.note.Note;
import seedu.address.testutil.NoteBuilder;

/**
 * Contains integration tests (interaction with the SeplendidModel) for {@code NoteClearTagCommand}.
 */
public class NoteClearTagCommandIntegrationTest {

    private SeplendidModel model;

    @BeforeEach
    public void setUp() {
        model = new SeplendidModelManager(new UserPrefs(),
                getTypicalLocalCourseCatalogue(),
                getTypicalPartnerCourseCatalogue(),
                getTypicalUniversityCatalogue(),
                getTypicalMappingCatalogue(),
                getTypicalNoteCatalogue());
    }

    @Test
    public void execute_updateNote_success() throws CommandException {
        Note validNote = new NoteBuilder().withContent("Sample Note").build();

        model.addNote(validNote);
        NoteClearTagCommand noteClearTagCommand = new NoteClearTagCommand(1);
        CommandResult commandResult = noteClearTagCommand.execute(model);
        Note updatedNote = model.getNoteCatalogue().getNoteList().get(0);

        assertEquals(String.format(NoteClearTagCommand.MESSAGE_SUCCESS, Messages.format(updatedNote)),
                commandResult.getFeedbackToUser());
    }

    @Test
    public void execute_updateNoteInvalidNegativeIndex_throwsCommandException() throws CommandException {
        Note validNote = new NoteBuilder().withContent("Sample Note").build();
        model.addNote(validNote);

        NoteClearTagCommand noteClearTagCommand = new NoteClearTagCommand(-1);

        assertThrows(CommandException.class, () -> noteClearTagCommand.execute(model));
    }

    @Test
    public void execute_updateNoteInvalidVeryLargeIndex_throwsCommandException() throws CommandException {
        Note validNote = new NoteBuilder().withContent("Sample Note").build();
        model.addNote(validNote);

        NoteClearTagCommand noteClearTagCommand = new NoteClearTagCommand(123456789);

        assertThrows(CommandException.class, () -> noteClearTagCommand.execute(model));
    }
}
