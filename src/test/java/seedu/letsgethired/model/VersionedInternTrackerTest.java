package seedu.letsgethired.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.letsgethired.testutil.TypicalInternApplications.JANE_STREET;
import static seedu.letsgethired.testutil.TypicalInternApplications.OPTIVER;

import org.junit.jupiter.api.Test;


public class VersionedInternTrackerTest {
    private final VersionedInternTracker internTracker = new VersionedInternTracker();
    @Test
    public void restorePreviousState_withSavedStates_returnsTrueAndRestoresState() {
        internTracker.commit();
        internTracker.addApplication(JANE_STREET);
        boolean isActionUndone = internTracker.undo();
        assertTrue(isActionUndone);
        assertTrue(internTracker.getApplicationList().size() == 0);
    }

    @Test
    public void restorePreviousState_withoutSavedStates_returnsFalse() {
        boolean isActionUndone = internTracker.undo();
        assertFalse(isActionUndone);
    }

    @Test
    public void restorePreviousState_multipleCalls_returnsTrueAndRestoresDifferentStates() {
        internTracker.commit();
        internTracker.addApplication(JANE_STREET);
        internTracker.commit();
        internTracker.addApplication(OPTIVER);
        boolean canUndoAddingJaneStreet = internTracker.undo();
        assertTrue(internTracker.getApplicationList().size() == 1);
        assertTrue(canUndoAddingJaneStreet);
        boolean canUndoAddingOptiver = internTracker.undo();
        assertTrue(canUndoAddingOptiver);
        assertTrue(internTracker.getApplicationList().size() == 0);
    }
}
