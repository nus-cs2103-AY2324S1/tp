package seedu.address.logic.parser.localcourse;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PARAMETER_LOCALATTRIBUTE;
import static seedu.address.logic.parser.CliSyntax.PARAMETER_QUERY;
import static seedu.address.logic.parser.ParserUtil.areValuesEnclosedAndNonEmpty;

import seedu.address.logic.commands.localcourse.LocalCourseSearchCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.SeplendidArgumentMap;
import seedu.address.logic.parser.SeplendidArgumentTokenizer;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.messages.ConstraintMessage;
import seedu.address.messages.UsageMessage;
import seedu.address.model.localcourse.LocalCode;
import seedu.address.model.localcourse.LocalCourseAttribute;
import seedu.address.model.localcourse.LocalCourseContainsKeywordsPredicate;
import seedu.address.model.localcourse.LocalDescription;
import seedu.address.model.localcourse.LocalName;

/**
 * Parses input arguments and creates a new LocalCourseSearchCommand object.
 */
public class LocalCourseSearchCommandParser implements Parser<LocalCourseSearchCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the LocalCourseSearchCommand
     * and returns a LocalCourseSearchCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format.
     */
    public LocalCourseSearchCommand parse(String args) throws ParseException {
        ParserUtil.AreValuesEnclosedAndNonEmptyResult areValuesEnclosedAndNonEmptyResult =
                areValuesEnclosedAndNonEmpty(args);
        if (areValuesEnclosedAndNonEmptyResult == ParserUtil.AreValuesEnclosedAndNonEmptyResult.FAILURE) {
            throw new ParseException(UsageMessage.LOCALCOURSE_SEARCH.toString());
        } else if (areValuesEnclosedAndNonEmptyResult == ParserUtil.AreValuesEnclosedAndNonEmptyResult.EMPTY) {
            throw new ParseException(UsageMessage.LOCALCOURSE_SEARCH.getValueWithEmptyArgs());
        }

        assert areValuesEnclosedAndNonEmptyResult == ParserUtil.AreValuesEnclosedAndNonEmptyResult.SUCCESS;

        SeplendidArgumentMap parameterToArgMap =
                SeplendidArgumentTokenizer.tokenize(args, PARAMETER_LOCALATTRIBUTE, PARAMETER_QUERY);

        if (!ParserUtil.areArgumentsPresent(parameterToArgMap,
                PARAMETER_LOCALATTRIBUTE, PARAMETER_QUERY)) {
            throw new ParseException(
                    UsageMessage.LOCALCOURSE_SEARCH.toString());
        }

        LocalCourseAttribute localCourseAttribute = ParserUtil.parseLocalCourseAttributeForSearch(
                parameterToArgMap.getValue(PARAMETER_LOCALATTRIBUTE).get());
        String query = parseQuery(localCourseAttribute, parameterToArgMap.getValue(PARAMETER_QUERY).get());
        return new LocalCourseSearchCommand(localCourseAttribute,
                new LocalCourseContainsKeywordsPredicate(query, localCourseAttribute), query);
    }

    private String parseQuery(LocalCourseAttribute localCourseAttribute, String query)
            throws ParseException {
        requireAllNonNull(localCourseAttribute, query);
        String trimmedQuery = query.trim();
        switch (localCourseAttribute) {
        case LOCALCODE:
            if (!LocalCode.isValidLocalCode(trimmedQuery)) {
                throw new ParseException(ConstraintMessage.LOCALCOURSE_CODE.toString());
            }
            break;
        case LOCALNAME:
            if (!LocalName.isValidLocalName(trimmedQuery)) {
                throw new ParseException(ConstraintMessage.LOCALCOURSE_NAME.toString());
            }
            break;
        case LOCALDESCRIPTION:
            if (!LocalDescription.isValidLocalDescription(trimmedQuery)) {
                throw new ParseException(ConstraintMessage.LOCALCOURSE_DESCRIPTION.toString());
            }
            break;
        default:
            //do nothing
        }
        return trimmedQuery;
    }
}






