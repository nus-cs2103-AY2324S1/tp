package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BOB;

import org.junit.jupiter.api.Test;


public class ProjectManagerTest {

    @Test
    public void constructor_validParameters_createsProjectManager() {
        ProjectManager aliceManager =
                new ProjectManager(ALICE.getName(), ALICE.getPhone(), ALICE.getEmail(),
                        ALICE.getAddress(), ALICE.getRemark(), ALICE.getTags());
        assertTrue(aliceManager != null);
    }

    @Test
    public void equals() {
        ProjectManager aliceManager =
                new ProjectManager(ALICE.getName(), ALICE.getPhone(), ALICE.getEmail(),
                        ALICE.getAddress(), ALICE.getRemark(), ALICE.getTags());

        // same object -> returns true
        assertTrue(aliceManager.equals(aliceManager));

        // different object -> returns false
        ProjectManager bobManager =
                new ProjectManager(BOB.getName(), BOB.getPhone(), BOB.getEmail(),
                        BOB.getAddress(), BOB.getRemark(), BOB.getTags());
        assertFalse(aliceManager.equals(bobManager));
    }

//    @Test
//    public void addTeam_addSingleTeam_successful() {
//        ProjectManager aliceManager =
//                new ProjectManager(ALICE.getName(), ALICE.getPhone(), ALICE.getEmail(),
//                        ALICE.getAddress(), ALICE.getRemark(), ALICE.getTags());
//
//        aliceManager.addTeam(aliceTeam);
//        assertTrue(aliceManager.getTeams().contains(aliceTeam));
//    }
}

