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
import seedu.address.testutil.NoteBuilder;

/**
 * Contains integration tests (interaction with the SeplendidModel) for {@code NoteAddCommand}.
 */
public class NoteAddCommandIntegrationTest {

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
    public void execute_newNote_success() throws CommandException {
        Note validNote = new NoteBuilder().withContent("Sample Note").build();

        SeplendidModel expectedModel = new SeplendidModelManager(new UserPrefs(),
                model.getLocalCourseCatalogue(),
                model.getPartnerCourseCatalogue(),
                model.getUniversityCatalogue(),
                model.getMappingCatalogue(),
                model.getNoteCatalogue());
        expectedModel.addNote(validNote);

        NoteAddCommand noteAddCommand = new NoteAddCommand(validNote);
        assertEquals(String.format(NoteAddCommand.MESSAGE_SUCCESS, Messages.format(validNote)),
                noteAddCommand.execute(model).getFeedbackToUser());

        assertEquals(expectedModel, model);
    }

    @Test
    public void execute_duplicateNote_throwsDuplicateNoteException() {
        Note noteInList = model.getNoteCatalogue().getNoteList().get(0);
        NoteAddCommand noteAddCommand = new NoteAddCommand(noteInList);
        assertThrows(CommandException.class, () -> noteAddCommand.execute(model));
    }
}
