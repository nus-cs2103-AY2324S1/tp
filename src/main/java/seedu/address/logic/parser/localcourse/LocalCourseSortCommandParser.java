package seedu.address.logic.parser.localcourse;


import static seedu.address.logic.parser.CliSyntax.PARAMETER_LOCALATTRIBUTE;
import static seedu.address.logic.parser.ParserUtil.areValuesEnclosedAndNonEmpty;

import java.util.Comparator;

import seedu.address.logic.commands.localcourse.LocalCourseSortCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.SeplendidArgumentMap;
import seedu.address.logic.parser.SeplendidArgumentTokenizer;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.messages.ConstraintMessage;
import seedu.address.messages.UsageMessage;
import seedu.address.model.localcourse.LocalCourse;
import seedu.address.model.localcourse.LocalCourseAttribute;
import seedu.address.model.localcourse.comparator.LocalCourseComparatorByLocalCode;
import seedu.address.model.localcourse.comparator.LocalCourseComparatorByLocalName;

/**
 * Parses input arguments and creates a new LocalCourseSortCommand object.
 */
public class LocalCourseSortCommandParser implements Parser<LocalCourseSortCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the LocalCourseSortCommand
     * and returns a LocalCourseSortCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format.
     */
    public LocalCourseSortCommand parse(String args) throws ParseException {
        ParserUtil.AreValuesEnclosedAndNonEmptyResult areValuesEnclosedAndNonEmptyResult =
                areValuesEnclosedAndNonEmpty(args);
        if (areValuesEnclosedAndNonEmptyResult == ParserUtil.AreValuesEnclosedAndNonEmptyResult.FAILURE) {
            throw new ParseException(UsageMessage.LOCALCOURSE_SORT.toString());
        } else if (areValuesEnclosedAndNonEmptyResult == ParserUtil.AreValuesEnclosedAndNonEmptyResult.EMPTY) {
            throw new ParseException(UsageMessage.LOCALCOURSE_SORT.getValueWithEmptyArgs());
        }

        assert areValuesEnclosedAndNonEmptyResult == ParserUtil.AreValuesEnclosedAndNonEmptyResult.SUCCESS;

        SeplendidArgumentMap parameterToArgMap =
                SeplendidArgumentTokenizer.tokenize(args, PARAMETER_LOCALATTRIBUTE);

        if (!ParserUtil.areArgumentsPresent(parameterToArgMap, PARAMETER_LOCALATTRIBUTE)) {
            throw new ParseException(UsageMessage.LOCALCOURSE_SORT.toString());
        }

        Comparator<LocalCourse> localCourseComparator =
                parseLocalCourseComparator(parameterToArgMap.getValue(PARAMETER_LOCALATTRIBUTE).get());

        return new LocalCourseSortCommand(localCourseComparator);
    }

    private Comparator<LocalCourse> parseLocalCourseComparator(String args) throws ParseException {
        LocalCourseAttribute localCourseAttribute = ParserUtil.parseLocalCourseAttributeForSort(args);
        switch (localCourseAttribute) {
        case LOCALCODE:
            return new LocalCourseComparatorByLocalCode();
        case LOCALNAME:
            return new LocalCourseComparatorByLocalName();
        default:
            throw new ParseException(ConstraintMessage.LOCALCOURSE_ATTRIBUTE_SORT.toString());
        }
    }
}
