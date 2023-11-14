package seedu.address.model.internshiprole;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalInternshipRoles.getTypicalInternshipRole1;
import static seedu.address.testutil.TypicalInternshipRoles.getTypicalInternshipRole2;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import seedu.address.model.fields.Outcome;
import seedu.address.model.internship.role.InternshipRole;
import seedu.address.testutil.InternshipRoleBuilder;

public class InternshipRoleTest {
    private InternshipRole role1 = getTypicalInternshipRole2();

    private InternshipRole role2 = getTypicalInternshipRole1();

    private final String obscureInput = "123";

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        InternshipRole role = new InternshipRoleBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> role.getTags().remove(0));
    }

    @Test
    public void isDuplicate() {
        // same object -> returns true
        assertTrue(role1.isDuplicate(role1));

        // null -> returns false
        assertFalse(role1.isDuplicate(null));

        // same name and role, all other attributes different -> returns true
        InternshipRole editedRole = new InternshipRoleBuilder(role1)
                .withOutcome(Outcome.REJECTED)
                .withLocation(obscureInput)
                .withPay(new BigDecimal("00.00"))
                .withDescription(obscureInput)
                .withTags("tag").build();

        assertTrue(role1.isDuplicate(editedRole));

        // different name & role, all other attributes same -> returns false
        editedRole = new InternshipRoleBuilder(role1).withName(obscureInput).withRole(obscureInput).build();
        assertFalse(role1.isDuplicate(editedRole));

        // different name , all other attributes same -> returns false
        editedRole = new InternshipRoleBuilder(role1).withName(obscureInput).build();
        assertFalse(role1.isDuplicate(editedRole));

        // different role , all other attributes same -> returns false
        editedRole = new InternshipRoleBuilder(role1).withRole(obscureInput).build();
        assertFalse(role1.isDuplicate(editedRole));

    }

    @Test
    public void equals() {
        // same values -> returns true
        InternshipRole internshipRoleCopy = getTypicalInternshipRole2();
        assertTrue(role1.equals(internshipRoleCopy));

        // same object -> returns true
        assertTrue(role1.equals(role1));

        // null -> returns false
        assertFalse(role1.equals(null));

        // different type -> returns false
        assertFalse(role1.equals(5));

        // different role -> returns false
        assertFalse(role1.equals(role2));

        // different name -> returns false
        InternshipRole editedRole = new InternshipRoleBuilder(role1).withName(obscureInput).build();
        assertFalse(role1.equals(editedRole));

        // different role -> returns false
        editedRole = new InternshipRoleBuilder(role1).withRole(obscureInput).build();
        assertFalse(role1.equals(editedRole));

        // different cycle -> returns false
        editedRole = new InternshipRoleBuilder(role1).withCycle(obscureInput).build();
        assertFalse(role1.equals(editedRole));

        // different outcome -> returns false
        editedRole = new InternshipRoleBuilder(role1).withOutcome(Outcome.REJECTED).build();
        assertFalse(role1.equals(editedRole));

        // different pay -> returns false
        editedRole = new InternshipRoleBuilder(role1).withPay(new BigDecimal("00.00")).build();
        assertFalse(role1.equals(editedRole));

        // different location -> returns false
        editedRole = new InternshipRoleBuilder(role1).withLocation(obscureInput).build();
        assertFalse(role1.equals(editedRole));

        // different description -> returns false
        editedRole = new InternshipRoleBuilder(role1).withDescription(obscureInput).build();
        assertFalse(role1.equals(editedRole));

        // different tags -> returns false
        editedRole = new InternshipRoleBuilder(role1).withTags("tag").build();
        assertFalse(role1.equals(editedRole));
    }

    @Test
    public void toStringMethod() {
        String expected = InternshipRole.class.getCanonicalName() + "{name=" + role1.getName() + ", role="
                + role1.getRole() + ", cycle="
                + role1.getCycle() + ", description="
                + role1.getDescription() + ", pay="
                + role1.getPay() + ", outcome="
                + role1.getApplicationOutcome() + ", location="
                + role1.getLocation() + ", tags="
                + role1.getTags() + "}";
        assertEquals(expected, role1.toString());
    }
}
