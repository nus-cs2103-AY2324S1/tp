package swe.context.model.contact;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import swe.context.testutil.TestData;

public class NoteTest {

    @Test
    public void equals() {
        Note amy = new Note(TestData.Valid.NOTE_AMY);

        // same values -> return true
        Note amyCopy = new Note(TestData.Valid.NOTE_AMY);
        assertTrue(amy.equals(amyCopy));

        // same objects -> return true
        assertTrue(amy.equals(amy));

        // different type -> return false
        assertFalse(amy.equals(1));

        // null -> return false
        assertFalse(amy.equals(null));

        // different note -> return false
        Note bob = new Note(TestData.Valid.NOTE_BOB);
        assertFalse(amy.equals(bob));
    }

}
