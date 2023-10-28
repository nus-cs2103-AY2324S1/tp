package seedu.ccacommander.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.ccacommander.model.enrolment.Enrolment;
/**
 * A utility class containing a list of {@code Enrolment} objects to be used in tests.
 */
public class TypicalEnrolments {

    public static final Enrolment ALICE_AURORA = new EnrolmentBuilder().withMemberName("Alice Pauline")
            .withEventName("Aurora Borealis").withHours("3").withRemark("Role: Photographer").build();

    public static final Enrolment BENSON_BOXING = new EnrolmentBuilder().withMemberName("Benson Meier")
            .withEventName("Boxing Day").withHours("7").withRemark("Bring boxing gloves").build();

    public static final Enrolment CARL_CNY = new EnrolmentBuilder().withMemberName("Carl Kurz")
            .withEventName("Chinese New Year").withHours("3").withRemark("Brought red packets").build();
    public static final Enrolment DANIEL_DOG_DAY = new EnrolmentBuilder().withMemberName("Daniel Meier")
            .withEventName("Dog Celebration Day").withHours("5").withRemark("Bringing dogs").build();

    public static final Enrolment ELLE_ECHO_DAY = new EnrolmentBuilder().withMemberName("Elle Meyer")
            .withEventName("Echo Day").withHours("0").withRemark("Absent without reason").build();

    public static final Enrolment FIONA_FESTIVAL = new EnrolmentBuilder().withMemberName("Fiona Kunz")
            .withEventName("Festival").withHours("2").withRemark("Usher").build();

    public static final Enrolment GEORGE_GRAVITY_DISCOVERY_DAY = new EnrolmentBuilder().withMemberName("George Best")
            .withEventName("Gravity Discovery Day").withHours("1").withRemark("Discovered gravity").build();

    private TypicalEnrolments() {} // prevents instantiation

    public static List<Enrolment> getTypicalEnrolment() {
        return new ArrayList<>(Arrays.asList(ALICE_AURORA, BENSON_BOXING, CARL_CNY, DANIEL_DOG_DAY, ELLE_ECHO_DAY,
                FIONA_FESTIVAL, GEORGE_GRAVITY_DISCOVERY_DAY));
    }
}
