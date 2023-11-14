package seedu.address.testutil;

import java.util.Comparator;

import seedu.address.logic.commands.university.UniversitySearchCommand;
import seedu.address.logic.commands.university.UniversitySortCommand;
import seedu.address.model.university.University;


/**
 * A Utility Class for University.
 */
public class UniversityUtil {
    /**
     * Returns a sort command string for sorting the university list.
     */
    public static String getUniversitySortCommandFrom(Comparator<University> universityComparator) {
        return String.format("%s %s %s",
                UniversitySortCommand.COMMAND_WORD,
                UniversitySearchCommand.ACTION_WORD,
                getUniversitySortCommandFrom(universityComparator));
    }

    /**
     * Returns the part of sort command string for the given comparator's details
     */
    public static String getUniversityArgumentsForSortCommand(Comparator<University> comparator) {
        return String.format("[%s]", comparator.toString());
    }
}
