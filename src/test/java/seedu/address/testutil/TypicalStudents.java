package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_RISK_LEVEL_HIGH;
import static seedu.address.logic.commands.CommandTestUtil.VALID_RISK_LEVEL_LOW;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.student.Student;

/**
 * A utility class containing a list of {@code Student} objects to be used in tests.
 */
public class TypicalStudents {

    public static final Student ALICE = new StudentBuilder().withName("Alice Pauline")
            .withAddress("123, Jurong West Ave 6, #08-111").withPhone("94351253")
            .withTags("high").withNote("course: computer science").build();
    public static final Student BENNY = new StudentBuilder().withName("Benny Dover")
            .withAddress("311, Clementi Ave 2, #02-25")
            .withPhone("98765432")
            .withTags("medium").withNote("Likes dogs").build();
    public static final Student CARL = new StudentBuilder().withName("Carl Kurz").withPhone("95352563")
            .withAddress("wall street").build();
    public static final Student DANIEL = new StudentBuilder().withName("Daniel Meier").withPhone("87652533")
            .withAddress("10th street").withTags("low").build();
    public static final Student ELLE = new StudentBuilder().withName("Elle Meyer").withPhone("94822240")
            .withAddress("michegan ave").build();
    public static final Student FIONA = new StudentBuilder().withName("Fiona Kunz").withPhone("94824270")
            .withAddress("little tokyo").build();
    public static final Student GEORGE = new StudentBuilder().withName("George Best").withPhone("94824420")
            .withAddress("4th street").build();

    // Manually added
    public static final Student HOON = new StudentBuilder().withName("Hoon Meier").withPhone("84824240")
            .withAddress("little india").build();
    public static final Student IDA = new StudentBuilder().withName("Ida Mueller").withPhone("84821310")
            .withAddress("chicago ave").build();

    // To be used in conjunction with default appointment builder to test in ScheduleCommandTest
    public static final Student JON_ANG = new StudentBuilder().withName("Jon Ang").withPhone("97980852")
            .withAddress("Blk 349 Woodlands Ave 3").build();

    // Manually added - Student's details found in {@code CommandTestUtil}
    public static final Student AMY = new StudentBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withAddress(VALID_ADDRESS_AMY).withTags(VALID_RISK_LEVEL_HIGH).build();
    public static final Student BOB = new StudentBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withAddress(VALID_ADDRESS_BOB).withTags(VALID_RISK_LEVEL_LOW).build();


    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalStudents() {} // prevents instantiation

    public static List<Student> getTypicalStudents() {
        return new ArrayList<>(Arrays.asList(ALICE, BENNY, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
