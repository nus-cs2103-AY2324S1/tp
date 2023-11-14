package seedu.address.testutil;

import static seedu.address.logic.commands.CommandAssignmentTestUtil.VALID_DEADLINE_ASSIGNMENT;
import static seedu.address.logic.commands.CommandAssignmentTestUtil.VALID_DESCRIPTION_ASSIGNMENT;
import static seedu.address.logic.commands.CommandAssignmentTestUtil.VALID_DESCRIPTION_PROJECT;
import static seedu.address.logic.commands.CommandAssignmentTestUtil.VALID_NAME_ASSIGNMENT;
import static seedu.address.logic.commands.CommandAssignmentTestUtil.VALID_NAME_PROJECT;
import static seedu.address.logic.commands.CommandAssignmentTestUtil.VALID_PLANNED_DATE;
import static seedu.address.logic.commands.CommandAssignmentTestUtil.VALID_STATUS_DONE;
import static seedu.address.logic.commands.CommandAssignmentTestUtil.VALID_STATUS_UNDONE;
import static seedu.address.logic.commands.CommandAssignmentTestUtil.VALID_TAG_ASSIGNMENT;
import static seedu.address.logic.commands.CommandAssignmentTestUtil.VALID_TAG_PROJECT;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.assignment.Assignment;
import seedu.address.model.fields.IsoDate;

/**
 * Typical Assignments
 */
public class TypicalAssignments {

    // Manually added - Assignment's details found in {@code CommandAssignmentTestUtil}
    public static final Assignment ASSIGNMENT5 = new AssignmentBuilder().withName(VALID_NAME_ASSIGNMENT)
            .withStatus(VALID_STATUS_DONE).withDescription(VALID_DESCRIPTION_ASSIGNMENT)
            .withDeadline(VALID_DEADLINE_ASSIGNMENT)
            .withPlannedDate(VALID_PLANNED_DATE)
            .withTags(VALID_TAG_ASSIGNMENT).build();
    public static final Assignment ASSIGNMENT6 = new AssignmentBuilder().withName(VALID_NAME_PROJECT)
            .withStatus(VALID_STATUS_UNDONE).withDescription(VALID_DESCRIPTION_PROJECT)
            .withDeadline(VALID_DEADLINE_ASSIGNMENT)
            .withPlannedDate(VALID_PLANNED_DATE)
            .withTags(VALID_TAG_PROJECT).build();

    private static final LocalDateTime exampleDate1 =
            LocalDateTime.parse("2023-11-29 18:00", DateTimeFormatter.ofPattern(IsoDate.DATE_FORMAT));

    private static final LocalDateTime exampleDate2 =
            LocalDateTime.parse("2023-12-12 21:30", DateTimeFormatter.ofPattern(IsoDate.DATE_FORMAT));

    private static final LocalDateTime exampleDate3 =
            LocalDateTime.parse("2023-12-16 22:30", DateTimeFormatter.ofPattern(IsoDate.DATE_FORMAT));
    private static final LocalDateTime exampleDate4 =
            LocalDateTime.parse("2023-12-15 21:30", DateTimeFormatter.ofPattern(IsoDate.DATE_FORMAT));

    public static final Assignment ASSIGNMENT1 = new AssignmentBuilder().withName("CS2100 Lab 7")
            .withStatus(false).withDescription("Draw diagrams")
            .withDeadline(exampleDate1)
            .withPlannedDate(exampleDate1)
            .withTags("CS2100").build();
    public static final Assignment ASSIGNMENT2 = new AssignmentBuilder().withName("CS2101 UG")
            .withStatus(false).withDescription("Check formatting")
            .withDeadline(exampleDate2)
            .withPlannedDate(exampleDate2)
            .withTags("CS2101", "Group").build();
    public static final Assignment ASSIGNMENT3 = new AssignmentBuilder().withName("CS2105 Assignment")
            .withStatus(false).withDescription("Do programming assignment")
            .withDeadline(exampleDate3)
            .withPlannedDate(exampleDate3)
            .withTags("Programming").build();
    public static final Assignment ASSIGNMENT4 = new AssignmentBuilder().withName("CS2105 Milestone 2")
            .withStatus(false).withDescription("Add test cases")
            .withDeadline(exampleDate4)
            .withPlannedDate(exampleDate4)
            .withTags("Milestone").build();

    //Manually added
    public static final Assignment ASSIGNMENT7 = new AssignmentBuilder().withName("MIH1101 OP")
            .withStatus(false).withDescription("Finish up slides")
            .withDeadline(exampleDate1)
            .withPlannedDate(exampleDate1)
            .withTags("Healthcare").build();

    private TypicalAssignments() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical assignments.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Assignment assignment : getTypicalAssignment()) {
            ab.addAssignment(assignment);
        }
        return ab;
    }

    public static List<Assignment> getTypicalAssignment() {
        return new ArrayList<>(Arrays.asList(ASSIGNMENT1, ASSIGNMENT2, ASSIGNMENT3,
                ASSIGNMENT4));
    }
}
