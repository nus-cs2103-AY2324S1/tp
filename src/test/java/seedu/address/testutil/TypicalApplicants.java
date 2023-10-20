package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.person.Applicant;

/**
 * A utility class containing a list of {@code Applicant} objects to be used in tests.
 */
public class TypicalApplicants {

    public static final Applicant ALICE_APPLICANT =
            new ApplicantBuilder().withName("Alice Pauline").withPhone("94351253").build();
    public static final Applicant BENSON_APPLICANT =
            new ApplicantBuilder().withName("Benson Meier").withPhone("98765432").build();
    public static final Applicant CARL_APPLICANT =
            new ApplicantBuilder().withName("Carl Kurz").withPhone("95352563").build();
    public static final Applicant DANIEL_APPLICANT =
            new ApplicantBuilder().withName("Daniel Meier").withPhone("87652533").build();
    public static final Applicant ELLE_APPLICANT =
            new ApplicantBuilder().withName("Elle Meyer").withPhone("9482224").build();
    public static final Applicant FIONA_APPLICANT =
            new ApplicantBuilder().withName("Fiona Kunz").withPhone("9482427").build();
    public static final Applicant GEORGE_APPLICANT =
            new ApplicantBuilder().withName("George Best").withPhone("9482442").build();

    // Manually added
    public static final Applicant HOON_APPLICANT =
            new ApplicantBuilder().withName("Hoon Meier").withPhone("8482424").build();

    // Manually added - Applicant's details found in {@code CommandTestUtil}
    public static final Applicant AMY_APPLICANT =
            new ApplicantBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY).build();
    public static final Applicant BOB_APPLICANT =
            new ApplicantBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB).build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalApplicants() {
    } // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical applicants.
     */
    public static AddressBook getTypicalAddressBookWithApplicants() {
        AddressBook ab = new AddressBook();
        for (Applicant applicant : getTypicalApplicants()) {
            ab.addApplicant(applicant);
        }
        return ab;
    }

    public static List<Applicant> getTypicalApplicants() {
        return new ArrayList<>(Arrays.asList(ALICE_APPLICANT, BENSON_APPLICANT, CARL_APPLICANT, DANIEL_APPLICANT,
                ELLE_APPLICANT, FIONA_APPLICANT, GEORGE_APPLICANT));
    }
}
