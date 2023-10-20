package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TELEGRAM_BOB;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalMembers.ALAN_MEMBER;
import static seedu.address.testutil.TypicalMembers.BOB_MEMBER;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.MemberBuilder;

public class MemberTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Member member = new MemberBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> member.getTags().remove(0));
    }

    @Test
    public void isSamePerson() {
        // same object -> returns true
        assertTrue(ALAN_MEMBER.isSamePerson(ALAN_MEMBER));

        // null -> returns false
        assertFalse(ALAN_MEMBER.isSamePerson(null));

        // same name, all other attributes different -> returns true
        Member editedAlan = new MemberBuilder(ALAN_MEMBER).withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .withTelegram(VALID_TELEGRAM_BOB).withTags(VALID_TAG_HUSBAND).build();
        assertTrue(ALAN_MEMBER.isSamePerson(editedAlan));

        // same phone, all other attributes different -> returns true
        editedAlan = new MemberBuilder(ALAN_MEMBER).withName(VALID_NAME_BOB).withEmail(VALID_EMAIL_BOB)
                .withTelegram(VALID_TELEGRAM_BOB).withTags(VALID_TAG_HUSBAND).build();
        assertTrue(ALAN_MEMBER.isSamePerson(editedAlan));

        // same email, all other attributes different -> returns true
        editedAlan = new MemberBuilder(ALAN_MEMBER).withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
                .withTelegram(VALID_TELEGRAM_BOB).withTags(VALID_TAG_HUSBAND).build();
        assertTrue(ALAN_MEMBER.isSamePerson(editedAlan));

        // same telegram, all other attributes different -> returns true
        editedAlan = new MemberBuilder(ALAN_MEMBER).withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
                .withEmail(VALID_EMAIL_BOB).withTags(VALID_TAG_HUSBAND).build();
        assertTrue(ALAN_MEMBER.isSamePerson(editedAlan));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Member alanCopy = new MemberBuilder(ALAN_MEMBER).build();
        assertTrue(ALAN_MEMBER.equals(alanCopy));

        // same object -> returns true
        assertTrue(ALAN_MEMBER.equals(ALAN_MEMBER));

        // null -> returns false
        assertFalse(ALAN_MEMBER.equals(null));

        // different type -> returns false
        assertFalse(ALAN_MEMBER.equals(5));

        // different member -> returns false
        assertFalse(ALAN_MEMBER.equals(BOB_MEMBER));

        // different name -> returns false
        Member editedAlan = new MemberBuilder(ALAN_MEMBER).withName(VALID_NAME_BOB).build();
        assertFalse(ALAN_MEMBER.equals(editedAlan));

        // different phone -> returns false
        editedAlan = new MemberBuilder(ALAN_MEMBER).withPhone(VALID_PHONE_BOB).build();
        assertFalse(ALAN_MEMBER.equals(editedAlan));

        // different email -> returns false
        editedAlan = new MemberBuilder(ALAN_MEMBER).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(ALAN_MEMBER.equals(editedAlan));

        // different telegram -> returns false
        editedAlan = new MemberBuilder(ALAN_MEMBER).withTelegram(VALID_TELEGRAM_BOB).build();
        assertFalse(ALAN_MEMBER.equals(editedAlan));

        // different tags -> returns false
        editedAlan = new MemberBuilder(ALAN_MEMBER).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(ALAN_MEMBER.equals(editedAlan));
    }

    @Test
    public void toStringMethod() {
        String expected = Member.class.getCanonicalName() + "{name=" + ALAN_MEMBER.getName() +
                ", phone=" + ALAN_MEMBER.getPhone() +
                ", email=" + ALAN_MEMBER.getEmail() +
                ", telegram=" + ALAN_MEMBER.getTelegram() +
                ", tags=" + ALAN_MEMBER.getTags() + "}";
        assertEquals(expected, ALAN_MEMBER.toString());
    }
}
