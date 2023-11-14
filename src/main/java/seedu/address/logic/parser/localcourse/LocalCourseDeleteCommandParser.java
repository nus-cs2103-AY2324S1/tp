package seedu.address.logic.parser.localcourse;


import static seedu.address.logic.parser.CliSyntax.PARAMETER_LOCALCODE;
import static seedu.address.logic.parser.ParserUtil.areValuesEnclosedAndNonEmpty;

import seedu.address.logic.commands.localcourse.LocalCourseDeleteCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.SeplendidArgumentMap;
import seedu.address.logic.parser.SeplendidArgumentTokenizer;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.messages.UsageMessage;
import seedu.address.model.localcourse.LocalCode;

/**
 * Parses input arguments and creates a new LocalCourse object.
 */
public class LocalCourseDeleteCommandParser implements Parser<LocalCourseDeleteCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the LocalCourseDeleteCommand
     * and returns an LocalCourseDeleteCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public LocalCourseDeleteCommand parse(String args) throws ParseException {
        ParserUtil.AreValuesEnclosedAndNonEmptyResult areValuesEnclosedAndNonEmptyResult =
                areValuesEnclosedAndNonEmpty(args);
        if (areValuesEnclosedAndNonEmptyResult == ParserUtil.AreValuesEnclosedAndNonEmptyResult.FAILURE) {
            throw new ParseException(UsageMessage.LOCALCOURSE_DELETE.toString());
        } else if (areValuesEnclosedAndNonEmptyResult == ParserUtil.AreValuesEnclosedAndNonEmptyResult.EMPTY) {
            throw new ParseException(UsageMessage.LOCALCOURSE_DELETE.getValueWithEmptyArgs());
        }

        assert areValuesEnclosedAndNonEmptyResult == ParserUtil.AreValuesEnclosedAndNonEmptyResult.SUCCESS;

        SeplendidArgumentMap parameterToArgMap =
                SeplendidArgumentTokenizer.tokenize(args, PARAMETER_LOCALCODE);

        if (!ParserUtil.areArgumentsPresent(parameterToArgMap, PARAMETER_LOCALCODE)) {
            throw new ParseException(UsageMessage.LOCALCOURSE_DELETE.toString());
        }

        // All arguments should be a non-empty {@code Optional}
        LocalCode localCode = ParserUtil.parseLocalCode(parameterToArgMap.getValue(PARAMETER_LOCALCODE).get());

        return new LocalCourseDeleteCommand(localCode);
    }

}
