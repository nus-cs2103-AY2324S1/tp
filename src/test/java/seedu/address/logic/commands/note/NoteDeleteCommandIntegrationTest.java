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

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.messages.Messages;
import seedu.address.model.SeplendidModel;
import seedu.address.model.SeplendidModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.note.Note;
import seedu.address.model.note.exceptions.NoteNotFoundException;

/**
 * Contains integration tests (interaction with the SeplendidModel) for {@code NoteDeleteCommand}.
 */
public class NoteDeleteCommandIntegrationTest {

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
    public void execute_validNoteIndex_success() throws CommandException {
        int validNoteIndex = 1;

        SeplendidModel expectedModel = new SeplendidModelManager(new UserPrefs(),
                model.getLocalCourseCatalogue(),
                model.getPartnerCourseCatalogue(),
                model.getUniversityCatalogue(),
                model.getMappingCatalogue(),
                model.getNoteCatalogue());

        Note deletedNote = model.getNoteCatalogue().getNoteList().get(validNoteIndex - 1);
        expectedModel.deleteNote(validNoteIndex);

        NoteDeleteCommand noteDeleteCommand = new NoteDeleteCommand(validNoteIndex);
        assertEquals(String.format(NoteDeleteCommand.MESSAGE_SUCCESS, Messages.format(deletedNote)),
                noteDeleteCommand.execute(model).getFeedbackToUser());

        assertEquals(expectedModel, model);
    }

    @Test
    public void execute_invalidNoteIndex_throwsNoteNotFoundException() {
        int invalidNoteIndex = 0; // Invalid index

        NoteDeleteCommand noteDeleteCommand = new NoteDeleteCommand(invalidNoteIndex);
        assertThrows(NoteNotFoundException.class, () -> noteDeleteCommand.execute(model));
    }

    @Test
    public void execute_invalidNegativeNoteIndex_throwsNoteNotFoundException() {
        int invalidNoteIndex = -1; // Invalid index

        NoteDeleteCommand noteDeleteCommand = new NoteDeleteCommand(invalidNoteIndex);
        assertThrows(CommandException.class, () -> noteDeleteCommand.execute(model));
    }
}
