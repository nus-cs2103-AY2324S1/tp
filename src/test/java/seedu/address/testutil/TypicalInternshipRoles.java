package seedu.address.testutil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.fields.Outcome;
import seedu.address.model.internship.role.InternshipRole;
import seedu.address.model.internship.task.InternshipTask;

/**
 * A class to get typical internship roles
 */
public class TypicalInternshipRoles {
    // Using static variables cause edit test cases to break if not careful
    public static final InternshipRole INTERNSHIP_ROLE_1 = new InternshipRoleBuilder()
            .withName("Gov Tech")
            .withCycle("Winter")
            .withRole("Software Engineer")
            .withDescription("Good Knowledge of React")
            .withOutcome(Outcome.OFFERED)
            .withLocation("Pasir Panjang")
            .withPay(new BigDecimal("1500.00"))
            .build();

    public static final InternshipRole INTERNSHIP_ROLE_2 = new InternshipRoleBuilder()
            .withName("TikTok")
            .withCycle("Summer 2023")
            .withRole("Software Engineer Backend")
            .withDescription("Good Knowledge of Python")
            .withOutcome(Outcome.AWAITING)
            .withLocation("Remote")
            .withPay(new BigDecimal("2500"))
            .build();

    public static final InternshipRole INTERNSHIP_ROLE_3 = new InternshipRoleBuilder()
            .withName("Google")
            .withCycle("Summer 2024")
            .withRole("Software Engineer")
            .withDescription("Very important")
            .withOutcome(Outcome.GHOSTED)
            .withLocation("Local")
            .withPay(new BigDecimal("4000"))
            .build();

    public static final InternshipRole INTERNSHIP_ROLE_4 = new InternshipRoleBuilder()
            .withName("Startup")
            .withCycle("Summer 2024")
            .withRole("Web Developer")
            .withDescription("Show portfolio")
            .withOutcome(Outcome.AWAITING)
            .withLocation("Local")
            .withPay(new BigDecimal("2000"))
            .build();

    public static InternshipRole getTypicalInternshipRole1() {
        return INTERNSHIP_ROLE_1;
    }

    public static InternshipRole getTypicalInternshipRole2() {
        return new InternshipRoleBuilder().build();
    }

    /**
     * Returns an {@code AddressBook} with all the typical internshipRoles.
     */
    public static AddressBook getTypicalAddressBook_internshipRoleOnly() {
        AddressBook ab = new AddressBook();
        for (InternshipRole internshipRole : getTypicalInternshipRole()) {
            ab.addInternshipRole(internshipRole);
        }
        return ab;
    }

    public static AddressBook getTypicalAddressBook_internshipRoleAndTask() {
        AddressBook ab = new AddressBook();
        for (InternshipRole internshipRole : getTypicalInternshipRole()) {
            ab.addInternshipRole(internshipRole);
        }
        for (InternshipTask internshipTask : TypicalInternshipTasks.getTypicalInternshipTasks()) {
            ab.addInternshipTask(internshipTask);
        }
        return ab;
    }

    public static List<InternshipRole> getTypicalInternshipRole() {
        return new ArrayList<>(Arrays.asList(INTERNSHIP_ROLE_1, INTERNSHIP_ROLE_2,
                INTERNSHIP_ROLE_3, INTERNSHIP_ROLE_4));
    }

}
