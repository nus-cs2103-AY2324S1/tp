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
import seedu.address.model.tag.Tag;
import seedu.address.testutil.NoteBuilder;

/**
 * Contains integration tests (interaction with the SeplendidModel) for {@code NoteTagCommand}.
 */
public class NoteTagCommandIntegrationTest {

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
        NoteTagCommand noteTagCommand = new NoteTagCommand(1, new Tag("test"));
        CommandResult commandResult = noteTagCommand.execute(model);
        Note updatedNote = model.getNoteCatalogue().getNoteList().get(0);

        assertEquals(String.format(NoteTagCommand.MESSAGE_SUCCESS, Messages.format(updatedNote)),
                commandResult.getFeedbackToUser());
    }

    @Test
    public void execute_updateNoteInvalidNegativeIndex_throwsCommandException() throws CommandException {
        Note validNote = new NoteBuilder().withContent("Sample Note").build();
        model.addNote(validNote);

        NoteTagCommand noteTagCommand = new NoteTagCommand(-1, new Tag("test"));

        assertThrows(CommandException.class, () -> noteTagCommand.execute(model));
    }

    @Test
    public void execute_updateNoteInvalidVeryLargeIndex_throwsCommandException() throws CommandException {
        Note validNote = new NoteBuilder().withContent("Sample Note").build();
        model.addNote(validNote);

        NoteTagCommand noteTagCommand = new NoteTagCommand(1234566799, new Tag("test"));

        assertThrows(CommandException.class, () -> noteTagCommand.execute(model));
    }

    @Test
    public void execute_duplicateTags_throwsCommandException() throws CommandException {
        Note validNote = new NoteBuilder().withContent("Sample Note").withTag(new Tag("test")).build();
        model.addNote(validNote);

        NoteTagCommand noteTagCommand = new NoteTagCommand(validNote.getIndex(), new Tag("test"));

        assertThrows(CommandException.class, () -> noteTagCommand.execute(model));
    }
}
