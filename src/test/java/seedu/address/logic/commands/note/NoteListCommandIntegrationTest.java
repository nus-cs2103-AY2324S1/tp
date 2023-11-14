package seedu.address.logic.commands.note;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalObjects.getTypicalLocalCourseCatalogue;
import static seedu.address.testutil.TypicalObjects.getTypicalMappingCatalogue;
import static seedu.address.testutil.TypicalObjects.getTypicalNoteCatalogue;
import static seedu.address.testutil.TypicalObjects.getTypicalPartnerCourseCatalogue;
import static seedu.address.testutil.TypicalObjects.getTypicalUniversityCatalogue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.SeplendidModel;
import seedu.address.model.SeplendidModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.note.Note;
import seedu.address.testutil.NoteBuilder;

public class NoteListCommandIntegrationTest {
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
    public void execute_validNoteList_success() throws CommandException {
        // Set up sample notes in the model
        Note sampleNote1 = new NoteBuilder().withContent("Note 1").build();
        Note sampleNote2 = new NoteBuilder().withContent("Note 2").build();
        model.addNote(sampleNote1);
        model.addNote(sampleNote2);

        // Execute the NoteListCommand
        NoteListCommand noteListCommand = new NoteListCommand();
        CommandResult commandResult = noteListCommand.execute(model);

        // Verify the result message
        assertEquals(NoteListCommand.MESSAGE_SUCCESS, commandResult.getFeedbackToUser());

        // Verify that the filtered note list in the model is as expected
        assertTrue(model.getFilteredNoteList().contains(sampleNote1));
        assertTrue(model.getFilteredNoteList().contains(sampleNote2));
    }
}
