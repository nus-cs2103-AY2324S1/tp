package seedu.address.model.company;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DEADLINE_TIKTOK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_TIKTOK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_TIKTOK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_TIKTOK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ROLE_TIKTOK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STATUS_TIKTOK;
import static seedu.address.testutil.TypicalCompanies.META;
import static seedu.address.testutil.TypicalCompanies.TIKTOK;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.CompanyBuilder;

public class CompanyTest {
    @Test
    public void isSameCompany() {
        // same object -> returns true
        assertTrue(META.isSameCompany(META));

        // null -> returns false
        assertFalse(META.isSameCompany(null));

        // same name, same role and deadline all other attributes different -> returns true
        Company editedMeta = new CompanyBuilder(META).withPhone(VALID_PHONE_TIKTOK).withEmail(VALID_EMAIL_TIKTOK)
                .withStatus(VALID_STATUS_TIKTOK)
                .build();
        assertTrue(META.isSameCompany(editedMeta));

        // different name, all other attributes same -> returns false
        editedMeta = new CompanyBuilder(META).withName(VALID_NAME_TIKTOK).withRole(VALID_ROLE_TIKTOK)
                .withDeadline(VALID_DEADLINE_TIKTOK).withStatus(VALID_STATUS_TIKTOK).build();
        assertFalse(META.isSameCompany(editedMeta));

        // name differs in case, all other attributes same -> returns true (case-insensitive)
        Company editedTikTok = new CompanyBuilder(TIKTOK).withName(VALID_NAME_TIKTOK.toLowerCase())
                .withRole(VALID_ROLE_TIKTOK).withDeadline(VALID_DEADLINE_TIKTOK)
                .withStatus(VALID_STATUS_TIKTOK).build();
        assertTrue(TIKTOK.isSameCompany(editedTikTok));

        // name has trailing spaces, all other attributes same -> returns true (trailing spaces ignored)
        String nameWithTrailingSpaces = VALID_NAME_TIKTOK + " ";
        editedTikTok = new CompanyBuilder(TIKTOK).withName(nameWithTrailingSpaces).withRole(VALID_ROLE_TIKTOK)
                .withDeadline(VALID_DEADLINE_TIKTOK).withStatus(VALID_STATUS_TIKTOK).build();
        assertTrue(TIKTOK.isSameCompany(editedTikTok));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Company metaCopy = new CompanyBuilder(META).build();
        assertTrue(META.equals(metaCopy));

        // same object -> returns true
        assertTrue(META.equals(META));

        // null -> returns false
        assertFalse(META.equals(null));

        // different type -> returns false
        assertFalse(META.equals(5));

        // different company -> returns false
        assertFalse(META.equals(TIKTOK));

        // different name -> returns false
        Company editedMeta = new CompanyBuilder(META).withName(VALID_NAME_TIKTOK).build();
        assertFalse(META.equals(editedMeta));

        // different phone -> returns false
        editedMeta = new CompanyBuilder(META).withPhone(VALID_PHONE_TIKTOK).build();
        assertFalse(META.equals(editedMeta));

        // different role -> returns false
        editedMeta = new CompanyBuilder(META).withRole(VALID_ROLE_TIKTOK).build();
        assertFalse(META.equals(editedMeta));

        //different deadline -> returns false
        editedMeta = new CompanyBuilder(META).withDeadline(VALID_DEADLINE_TIKTOK).build();
        assertFalse(META.equals(editedMeta));

        //different status -> returns false
        editedMeta = new CompanyBuilder(META).withStatus(VALID_STATUS_TIKTOK).build();
        assertFalse(META.equals(editedMeta));

        // different email -> returns false
        editedMeta = new CompanyBuilder(META).withEmail(VALID_EMAIL_TIKTOK).build();
        assertFalse(META.equals(editedMeta));
    }

    @Test
    public void toStringMethod() {
        String expected = Company.class.getCanonicalName() + "{name=" + META.getName() + ", role=" + META.getRole()
                + ", status=" + META.getStatus() + ", deadline=" + META.getDeadline()
                + ", recruiterName=" + META.getRecruiterName() + ", phone=" + META.getPhone()
                + ", email=" + META.getEmail()
                + ", priority=" + META.getPriority()
                + ", remark=" + META.getRemark()
                + "}";
        assertEquals(expected, META.toString());
    }
}
