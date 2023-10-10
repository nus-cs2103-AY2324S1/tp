package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalPersons.ALICE;

import org.junit.jupiter.api.Test;

public class ProjectManagerTest {

    @Test
    public void equals() {
        ProjectManager aliceManager =
                new ProjectManager(ALICE.getName(), ALICE.getPhone(), ALICE.getEmail(),
                        ALICE.getAddress(), ALICE.getRemark(), ALICE.getTags());

        // same object -> returns true
        assertTrue(aliceManager.equals(aliceManager));
    }

    // Other tests related to managing teams can be added here.
}
