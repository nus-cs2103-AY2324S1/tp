package seedu.address.testutil;

import java.util.Comparator;

import seedu.address.logic.commands.partnercourse.PartnerCourseAddCommand;
import seedu.address.logic.commands.partnercourse.PartnerCourseDeleteCommand;
import seedu.address.logic.commands.partnercourse.PartnerCourseSortCommand;
import seedu.address.model.partnercourse.PartnerCourse;

/**
 * A utility class for PartnerCourse.
 */
public class PartnerCourseUtil {
    /**
     * Returns an add command string for adding the {@code PartnerCourse}.
     */
    public static String getPartnerCourseAddCommandFrom(PartnerCourse partnerCourse) {
        return String.format("%s %s %s",
            PartnerCourseAddCommand.COMMAND_WORD,
            PartnerCourseAddCommand.ACTION_WORD,
            getPartnerCourseArgumentsForAddCommand(partnerCourse));
    }

    /**
     * Returns the part of add command string for the given {@code PartnerCourse}'s details.
     */
    public static String getPartnerCourseArgumentsForAddCommand(PartnerCourse partnerCourse) {
        return String.format("[%s] [%s] [%s] [%s] [%s]",
            partnerCourse.getPartnerUniversity(),
            partnerCourse.getPartnerCode(),
            partnerCourse.getPartnerName(),
            partnerCourse.getPartnerUnit(),
            partnerCourse.getPartnerDescription());
    }

    /**
     * Returns a delete command string for deleting the {@code PartnerCourse}.
     */
    public static String getPartnerCourseDeleteCommandFrom(PartnerCourse partnerCourse) {
        return String.format("%s %s %s",
            PartnerCourseDeleteCommand.COMMAND_WORD,
            PartnerCourseDeleteCommand.ACTION_WORD,
            getPartnerCourseArgumentsForDeleteCommand(partnerCourse));
    }

    /**
     * Returns the part of delete command string for the given {@code PartnerCourse}'s details.
     */
    public static String getPartnerCourseArgumentsForDeleteCommand(PartnerCourse partnerCourse) {
        return String.format("[%s] [%s]",
                partnerCourse.getPartnerUniversity().getUniversityName(), partnerCourse.getPartnerCode());
    }

    /**
     * Returns a sort command string for sorting the partnercourse list.
     */
    public static String getPartnerCourseSortCommandFrom(Comparator<PartnerCourse> partnerCourseComparator) {
        return String.format("%s %s %s",
                PartnerCourseSortCommand.COMMAND_WORD,
                PartnerCourseSortCommand.ACTION_WORD,
                getPartnerCourseArgumentsForSortCommand(partnerCourseComparator));
    }

    /**
     * Returns the part of sort command string for the given comparator's details
     */
    public static String getPartnerCourseArgumentsForSortCommand(Comparator<PartnerCourse> comparator) {
        return String.format("[%s]", comparator.toString());
    }


}
