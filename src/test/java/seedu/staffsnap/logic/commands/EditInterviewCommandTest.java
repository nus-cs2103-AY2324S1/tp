package seedu.staffsnap.logic.commands;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.staffsnap.testutil.TypicalIndexes.INDEX_FIRST_APPLICANT;
import static seedu.staffsnap.testutil.TypicalIndexes.INDEX_FIRST_INTERVIEW;

import org.junit.jupiter.api.Test;

class EditInterviewCommandTest {
    @Test
    public void equalsMethod() {
        EditInterviewCommand editInterviewCommand = new EditInterviewCommand(
                INDEX_FIRST_APPLICANT, INDEX_FIRST_INTERVIEW, new EditInterviewCommand.EditInterviewDescriptor());
        assertEquals(editInterviewCommand, editInterviewCommand);
        assertEquals(editInterviewCommand,
                new EditInterviewCommand(
                        INDEX_FIRST_APPLICANT,
                        INDEX_FIRST_INTERVIEW,
                        new EditInterviewCommand.EditInterviewDescriptor()));
    }
}
