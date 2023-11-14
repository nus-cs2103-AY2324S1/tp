package seedu.address.logic.parser.localcourse;


import static seedu.address.logic.parser.CliSyntax.PARAMETER_LOCALCODE;
import static seedu.address.logic.parser.CliSyntax.PARAMETER_LOCALDESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PARAMETER_LOCALNAME;
import static seedu.address.logic.parser.CliSyntax.PARAMETER_LOCALUNIT;
import static seedu.address.logic.parser.ParserUtil.areValuesEnclosedAndNonEmpty;

import seedu.address.logic.commands.localcourse.LocalCourseAddCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.SeplendidArgumentMap;
import seedu.address.logic.parser.SeplendidArgumentTokenizer;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.messages.UsageMessage;
import seedu.address.model.localcourse.LocalCode;
import seedu.address.model.localcourse.LocalCourse;
import seedu.address.model.localcourse.LocalDescription;
import seedu.address.model.localcourse.LocalName;
import seedu.address.model.localcourse.LocalUnit;

/**
 * Parses input arguments and creates a new LocalCourseAddCommand object.
 */
public class LocalCourseAddCommandParser implements Parser<LocalCourseAddCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the LocalCourseAddCommand
     * and returns a LocalCourseAddCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public LocalCourseAddCommand parse(String args) throws ParseException {
        ParserUtil.AreValuesEnclosedAndNonEmptyResult areValuesEnclosedAndNonEmptyResult =
                areValuesEnclosedAndNonEmpty(args);
        if (areValuesEnclosedAndNonEmptyResult == ParserUtil.AreValuesEnclosedAndNonEmptyResult.FAILURE) {
            throw new ParseException(UsageMessage.LOCALCOURSE_ADD.toString());
        } else if (areValuesEnclosedAndNonEmptyResult == ParserUtil.AreValuesEnclosedAndNonEmptyResult.EMPTY) {
            throw new ParseException(UsageMessage.LOCALCOURSE_ADD.getValueWithEmptyArgs());
        }

        assert areValuesEnclosedAndNonEmptyResult == ParserUtil.AreValuesEnclosedAndNonEmptyResult.SUCCESS;

        SeplendidArgumentMap parameterToArgMap =
                SeplendidArgumentTokenizer.tokenize(args, PARAMETER_LOCALCODE, PARAMETER_LOCALNAME, PARAMETER_LOCALUNIT,
                        PARAMETER_LOCALDESCRIPTION);

        if (!ParserUtil.areArgumentsPresent(parameterToArgMap, PARAMETER_LOCALCODE, PARAMETER_LOCALNAME,
                PARAMETER_LOCALUNIT, PARAMETER_LOCALDESCRIPTION)) {
            throw new ParseException(UsageMessage.LOCALCOURSE_ADD.toString());
        }

        // All arguments should be a non-empty {@code Optional}
        LocalCode localCode = ParserUtil.parseLocalCode(parameterToArgMap.getValue(PARAMETER_LOCALCODE)
                .get());
        LocalName localName = ParserUtil.parseLocalName(parameterToArgMap.getValue(PARAMETER_LOCALNAME)
                .get());
        LocalUnit localUnit = ParserUtil.parseLocalUnit(parameterToArgMap.getValue(PARAMETER_LOCALUNIT)
                .get());
        LocalDescription localDescription =
                ParserUtil.parseLocalDescription(parameterToArgMap.getValue(PARAMETER_LOCALDESCRIPTION).get());

        LocalCourse localCourse = new LocalCourse(localCode, localName, localUnit, localDescription);

        return new LocalCourseAddCommand(localCourse);
    }

}
