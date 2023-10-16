package seedu.address.model.musician;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalMusicians.ALICE;
import static seedu.address.testutil.TypicalMusicians.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.MusicianBuilder;

public class MusicianTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Musician musician = new MusicianBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> musician.getTags().remove(0));
    }

    @Test
    public void isSameMusician() {
        // same object -> returns true
        assertTrue(ALICE.isSameMusician(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSameMusician(null));

        // same name, all other attributes different -> returns true
        Musician editedAlice = new MusicianBuilder(ALICE).withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .withTags(VALID_TAG_HUSBAND).build();
        assertTrue(ALICE.isSameMusician(editedAlice));

        // different name, all other attributes same -> returns false
        editedAlice = new MusicianBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.isSameMusician(editedAlice));

        // name differs in case, all other attributes same -> returns false
        Musician editedBob = new MusicianBuilder(BOB).withName(VALID_NAME_BOB.toLowerCase()).build();
        assertFalse(BOB.isSameMusician(editedBob));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_BOB + " ";
        editedBob = new MusicianBuilder(BOB).withName(nameWithTrailingSpaces).build();
        assertFalse(BOB.isSameMusician(editedBob));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Musician aliceCopy = new MusicianBuilder(ALICE).build();
        assertTrue(ALICE.equals(aliceCopy));

        // same object -> returns true
        assertTrue(ALICE.equals(ALICE));

        // null -> returns false
        assertFalse(ALICE.equals(null));

        // different type -> returns false
        assertFalse(ALICE.equals(5));

        // different musician -> returns false
        assertFalse(ALICE.equals(BOB));

        // different name -> returns false
        Musician editedAlice = new MusicianBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different phone -> returns false
        editedAlice = new MusicianBuilder(ALICE).withPhone(VALID_PHONE_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different email -> returns false
        editedAlice = new MusicianBuilder(ALICE).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(ALICE.equals(editedAlice));


        // different tags -> returns false
        editedAlice = new MusicianBuilder(ALICE).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(ALICE.equals(editedAlice));
    }

    @Test
    public void toStringMethod() {
        String expected = Musician.class.getCanonicalName() + "{name=" + ALICE.getName() + ", phone=" + ALICE.getPhone()
                + ", email=" + ALICE.getEmail() + ", tags=" + ALICE.getTags()
                + ", instruments=" + ALICE.getInstruments() + ", genres=" + ALICE.getGenres() + "}";
        assertEquals(expected, ALICE.toString());
    }
}
