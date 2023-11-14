package seedu.address.testutil;

import seedu.address.model.mapping.Mapping;
import seedu.address.model.mapping.MappingComparatorByAttribute;

/**
 * A utility class for Mapping tests.
 */
public class MappingUtil {
    /**
     * Returns the part of the add command string for the given {@code mapping}'s details.
     */
    public static String getMappingArgumentsForAddCommand(Mapping mapping) {
        return String.format("[%s] [%s] [%s] [%s]",
                mapping.getLocalCode(),
                mapping.getUniversityName(),
                mapping.getPartnerCode(),
                mapping.getMappingMiscInformation());
    }

    /**
     * Returns the part of delete command string for the given {@code Mapping}'s details.
     */
    public static String getMappingArgumentsForDeleteCommand(Mapping mapping) {
        return String.format("[%s] [%s] [%s]",
                mapping.getLocalCode(),
                mapping.getUniversityName(),
                mapping.getPartnerCode());
    }

    /**
     * Returns the part of sort command string for the given comparator's details.
     */
    public static String getMappingArgumentsForSortCommand(MappingComparatorByAttribute mappingComparator) {
        return String.format("[%s]", mappingComparator.getAttribute().toString());
    }
}
