package seedu.address.logic.commands.clearcommands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON_INTERNSHIP_OR_COMPANY;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON_INTERNSHIP_OR_COMPANY;

import org.junit.jupiter.api.Test;

public class ClearCommandTest {

    @Test
    public void constructor_nullCompanyIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ClearInternshipCommand(null));
    }

    @Test
    public void equals() {
        ClearAllCommand clearAllCommand = new ClearAllCommand();
        ClearAllCommand anotherClearAllCommand = new ClearAllCommand();
        ClearInternshipCommand clearInternshipCommand1 =
                new ClearInternshipCommand(INDEX_FIRST_PERSON_INTERNSHIP_OR_COMPANY);
        ClearInternshipCommand clearInternshipCommand2 =
                new ClearInternshipCommand(INDEX_FIRST_PERSON_INTERNSHIP_OR_COMPANY);
        ClearInternshipCommand clearInternshipCommand3 =
                new ClearInternshipCommand(INDEX_SECOND_PERSON_INTERNSHIP_OR_COMPANY);

        // same object -> returns true
        assertTrue(clearAllCommand.equals(clearAllCommand));
        assertTrue(clearInternshipCommand1.equals(clearInternshipCommand1));

        // same values -> returns true
        assertTrue(clearInternshipCommand1.equals(clearInternshipCommand2));
        assertTrue(clearAllCommand.equals(anotherClearAllCommand));

        // different types -> returns false
        assertFalse(clearAllCommand.equals(1));
        assertFalse(clearInternshipCommand1.equals(1));

        // null -> returns false
        assertFalse(clearAllCommand.equals(null));
        assertFalse(clearInternshipCommand1.equals(null));

        // different company -> returns false
        assertFalse(clearInternshipCommand1.equals(clearInternshipCommand3));
    }

}
