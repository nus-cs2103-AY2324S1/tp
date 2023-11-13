package seedu.staffsnap.testutil;

import static seedu.staffsnap.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.staffsnap.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.staffsnap.logic.commands.CommandTestUtil.VALID_INTERVIEW_BEHAVIORAL;
import static seedu.staffsnap.logic.commands.CommandTestUtil.VALID_INTERVIEW_TECHNICAL;
import static seedu.staffsnap.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.staffsnap.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.staffsnap.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.staffsnap.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.staffsnap.logic.commands.CommandTestUtil.VALID_POSITION_AMY;
import static seedu.staffsnap.logic.commands.CommandTestUtil.VALID_POSITION_BOB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import seedu.staffsnap.model.ApplicantBook;
import seedu.staffsnap.model.applicant.Applicant;
import seedu.staffsnap.model.applicant.Score;

/**
 * A utility class containing a list of {@code Applicant} objects to be used in tests.
 */
public class TypicalApplicants {

    public static final Applicant ALICE = new ApplicantBuilder().withName("Alice Pauline")
            .withPosition("Software Engineer").withEmail("alice@example.com").withPhone("94351253")
            .build();
    public static final Applicant BENSON = new ApplicantBuilder().withName("Benson Meier")
            .withPosition("Frontend Engineer").withEmail("benson@example.com").withPhone("98765432")
            .withInterviews(VALID_INTERVIEW_TECHNICAL)
            .withScore(new Score(8.0, 8.0, 1))
            .withStatus("OFFERED").build();
    public static final Applicant CARL = new ApplicantBuilder().withName("Carl Kurz").withPhone("95352563")
            .withEmail("carl@example.com").withPosition("Backend Engineer").withInterviews(VALID_INTERVIEW_BEHAVIORAL)
            .withScore(new Score(8.5, 8.5, 1))
            .withStatus("REJECTED").build();
    public static final Applicant DANIEL = new ApplicantBuilder().withName("Daniel Meier").withPhone("87652533")
            .withEmail("daniel@example.com").withPosition("Testing Engineer").withInterviews(VALID_INTERVIEW_TECHNICAL)
            .build();
    public static final Applicant ELLE = new ApplicantBuilder().withName("Elle Meyer").withPhone("9482224")
            .withEmail("elle@example.com").withPosition("Frontend Engineer")
            .build();
    public static final Applicant FIONA = new ApplicantBuilder().withName("Fiona Kunz").withPhone("9482427")
            .withEmail("fiona@example.com").withPosition("Backend Engineer")
            .build();
    public static final Applicant GEORGE = new ApplicantBuilder().withName("George Best").withPhone("9482442")
            .withEmail("george@example.com").withPosition("Software Engineer")
            .build();
    public static final Applicant HOON = new ApplicantBuilder().withName("Hoon Meier").withPhone("8482424")
            .withEmail("hoon@example.com").withPosition("Staff Engineer")
            .build();
    public static final Applicant IDA = new ApplicantBuilder().withName("Ida Mueller").withPhone("8482131")
            .withEmail("ida@example.com").withPosition("Senior Software Engineer")
            .build();

    // References only for JsonSerializableApplicantBookTest
    public static final Applicant ALICE1 = new ApplicantBuilder().withName("Alice Pauline")
            .withPosition("Software Engineer").withEmail("alice@example.com").withPhone("94351253")
            .build();
    public static final Applicant BENSON1 = new ApplicantBuilder().withName("Benson Meier")
            .withPosition("Frontend Engineer").withEmail("benson@example.com").withPhone("98765432")
            .withInterviews(VALID_INTERVIEW_TECHNICAL)
            .withScore(new Score(8.0, 8.0, 1))
            .withStatus("OFFERED").build();
    public static final Applicant CARL1 = new ApplicantBuilder().withName("Carl Kurz").withPhone("95352563")
            .withEmail("carl@example.com").withPosition("Backend Engineer").withInterviews(VALID_INTERVIEW_BEHAVIORAL)
            .withScore(new Score(8.5, 8.5, 1))
            .withStatus("REJECTED").build();
    public static final Applicant DANIEL1 = new ApplicantBuilder().withName("Daniel Meier").withPhone("87652533")
            .withEmail("daniel@example.com").withPosition("Testing Engineer").withInterviews(VALID_INTERVIEW_TECHNICAL)
            .build();
    public static final Applicant ELLE1 = new ApplicantBuilder().withName("Elle Meyer").withPhone("9482224")
            .withEmail("elle@example.com").withPosition("Frontend Engineer")
            .build();
    public static final Applicant FIONA1 = new ApplicantBuilder().withName("Fiona Kunz").withPhone("9482427")
            .withEmail("fiona@example.com").withPosition("Backend Engineer")
            .build();
    public static final Applicant GEORGE1 = new ApplicantBuilder().withName("George Best").withPhone("9482442")
            .withEmail("george@example.com").withPosition("Software Engineer")
            .build();
    public static final Applicant HOON1 = new ApplicantBuilder().withName("Hoon Meier").withPhone("8482424")
            .withEmail("hoon@example.com").withPosition("Staff Engineer")
            .build();
    public static final Applicant IDA1 = new ApplicantBuilder().withName("Ida Mueller").withPhone("8482131")
            .withEmail("ida@example.com").withPosition("Senior Software Engineer")
            .build();

    public static final Applicant BOB1 = new ApplicantBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withPosition(VALID_POSITION_BOB)
            .withScore(new Score(8.7, 8.7, 1)).build();

    // Manually added - Applicant's details found in {@code CommandTestUtil}
    public static final Applicant AMY = new ApplicantBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY).withPosition(VALID_POSITION_AMY)
            .build();
    public static final Applicant BOB = new ApplicantBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withPosition(VALID_POSITION_BOB)
            .withScore(new Score(8.7, 8.7, 1)).build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalApplicants() {
    } // prevents instantiation

    /**
     * Returns an {@code ApplicantBook} with all the typical applicants.
     */
    public static ApplicantBook getTypicalApplicantBook() {
        ApplicantBook ab = new ApplicantBook();
        for (Applicant applicant : getTypicalApplicants()) {
            ab.addApplicant(applicant);
        }
        return ab;
    }

    /**
     * Returns an {@code ApplicantBook} with all the typical applicants in non-alphabetical order.
     */
    public static ApplicantBook getUnsortedApplicantBook() {
        ApplicantBook ab = new ApplicantBook();
        List<Applicant> unsorted = getTypicalApplicants();
        Collections.reverse(unsorted);
        for (Applicant applicant : unsorted) {
            ab.addApplicant(applicant);
        }
        return ab;
    }

    /**
     * Returns an {@code ApplicantBook} with all the typical applicants sorted by Name.
     */
    public static ApplicantBook getSortedByNameApplicantBook() {
        ApplicantBook ab = new ApplicantBook();
        for (Applicant applicant : getApplicantsSortedByName()) {
            ab.addApplicant(applicant);
        }
        return ab;
    }

    /**
     * Returns an {@code ApplicantBook} with all the typical applicants sorted by Phone.
     */
    public static ApplicantBook getSortedByPhoneApplicantBook() {
        ApplicantBook ab = new ApplicantBook();
        for (Applicant applicant : getApplicantsSortedByPhone()) {
            ab.addApplicant(applicant);
        }
        return ab;
    }

    public static List<Applicant> getTypicalApplicants() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, BOB, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }

    public static List<Applicant> getApplicantsSortedByName() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, BOB, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }

    public static List<Applicant> getApplicantsSortedByPhone() {
        return new ArrayList<>(Arrays.asList(BOB, DANIEL, ALICE, ELLE, FIONA, GEORGE, CARL, BENSON));
    }

    public static List<Applicant> getApplicantsSortedByStatus() {
        return new ArrayList<>(Arrays.asList(GEORGE, FIONA, ELLE, DANIEL, BOB, ALICE, BENSON, CARL));
    }

    public static List<Applicant> getApplicantsSortedByEmail() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, BOB, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }

    public static List<Applicant> getApplicantsSortedByPosition() {
        return new ArrayList<>(Arrays.asList(FIONA, CARL, ELLE, BENSON, GEORGE, BOB, ALICE, DANIEL));
    }

    public static List<Applicant> getApplicantsSortedByScore() {
        return new ArrayList<>(Arrays.asList(GEORGE, FIONA, ELLE, DANIEL, ALICE, BENSON, CARL, BOB));
    }

    public static List<Applicant> getApplicantsJsonTest() {
        return new ArrayList<>(Arrays.asList(ALICE1, BENSON1, BOB1, CARL1, DANIEL1, ELLE1, FIONA1, GEORGE1));
    }

    public static ApplicantBook getTypicalApplicantBookJsonTest() {
        ApplicantBook ab = new ApplicantBook();
        for (Applicant applicant : getApplicantsJsonTest()) {
            ab.addApplicant(applicant);
        }
        return ab;
    }
}
