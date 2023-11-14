package seedu.address.logic.parser.partnercourse;


import static seedu.address.logic.parser.CliSyntax.PARAMETER_PARTNERATTRIBUTE;
import static seedu.address.logic.parser.ParserUtil.areValuesEnclosedAndNonEmpty;

import java.util.Comparator;

import seedu.address.logic.commands.partnercourse.PartnerCourseSortCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.SeplendidArgumentMap;
import seedu.address.logic.parser.SeplendidArgumentTokenizer;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.messages.ConstraintMessage;
import seedu.address.messages.UsageMessage;
import seedu.address.model.partnercourse.PartnerCourse;
import seedu.address.model.partnercourse.PartnerCourseAttribute;
import seedu.address.model.partnercourse.comparator.PartnerCourseComparatorByPartnerCode;
import seedu.address.model.partnercourse.comparator.PartnerCourseComparatorByPartnerName;
import seedu.address.model.partnercourse.comparator.PartnerCourseComparatorByUniversity;

/**
 * Parses input arguments and creates a new PartnerCourseSortCommand object.
 */
public class PartnerCourseSortCommandParser implements Parser<PartnerCourseSortCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the PartnerCourseSortCommand
     * and returns a PartnerCourseSortCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format.
     */
    public PartnerCourseSortCommand parse(String args) throws ParseException {
        ParserUtil.AreValuesEnclosedAndNonEmptyResult areValuesEnclosedAndNonEmptyResult =
                areValuesEnclosedAndNonEmpty(args);
        if (areValuesEnclosedAndNonEmptyResult == ParserUtil.AreValuesEnclosedAndNonEmptyResult.FAILURE) {
            throw new ParseException(UsageMessage.PARTNERCOURSE_SORT.toString());
        } else if (areValuesEnclosedAndNonEmptyResult == ParserUtil.AreValuesEnclosedAndNonEmptyResult.EMPTY) {
            throw new ParseException(UsageMessage.PARTNERCOURSE_SORT.getValueWithEmptyArgs());
        }

        SeplendidArgumentMap parameterToArgMap =
                SeplendidArgumentTokenizer.tokenize(args, PARAMETER_PARTNERATTRIBUTE);

        if (!ParserUtil.areArgumentsPresent(parameterToArgMap, PARAMETER_PARTNERATTRIBUTE)) {
            throw new ParseException(UsageMessage.PARTNERCOURSE_SORT.toString());
        }

        Comparator<PartnerCourse> partnerCourseComparator =
                parsePartnerCourseComparator(parameterToArgMap.getValue(PARAMETER_PARTNERATTRIBUTE).get());

        return new PartnerCourseSortCommand(partnerCourseComparator);
    }

    private Comparator<PartnerCourse> parsePartnerCourseComparator(String args) throws ParseException {
        PartnerCourseAttribute partnerCourseAttribute = ParserUtil.parsePartnerCourseAttributeForSort(args);
        switch (partnerCourseAttribute) {
        case PARTNERCODE:
            return new PartnerCourseComparatorByPartnerCode();
        case PARTNERNAME:
            return new PartnerCourseComparatorByPartnerName();
        case UNIVERSITY:
            return new PartnerCourseComparatorByUniversity();
        default:
            throw new ParseException(ConstraintMessage.PARTNERCOURSE_ATTRIBUTE_SORT.toString());
        }
    }
}
