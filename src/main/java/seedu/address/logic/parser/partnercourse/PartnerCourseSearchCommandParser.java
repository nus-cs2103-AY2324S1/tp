package seedu.address.logic.parser.partnercourse;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PARAMETER_PARTNERATTRIBUTE;
import static seedu.address.logic.parser.CliSyntax.PARAMETER_QUERY;
import static seedu.address.logic.parser.ParserUtil.areValuesEnclosedAndNonEmpty;

import seedu.address.logic.commands.partnercourse.PartnerCourseSearchCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.SeplendidArgumentMap;
import seedu.address.logic.parser.SeplendidArgumentTokenizer;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.messages.ConstraintMessage;
import seedu.address.messages.UsageMessage;
import seedu.address.model.partnercourse.PartnerCode;
import seedu.address.model.partnercourse.PartnerCourseAttribute;
import seedu.address.model.partnercourse.PartnerCourseContainsKeywordsPredicate;
import seedu.address.model.partnercourse.PartnerName;

/**
 * Parses input arguments and creates a new PartnerCourseSearchCommand object.
 */
public class PartnerCourseSearchCommandParser implements Parser<PartnerCourseSearchCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the PartnerCourseSearchCommand
     * and returns a PartnerCourseSearchCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format.
     */
    public PartnerCourseSearchCommand parse(String args) throws ParseException {
        ParserUtil.AreValuesEnclosedAndNonEmptyResult areValuesEnclosedAndNonEmptyResult =
                areValuesEnclosedAndNonEmpty(args);
        if (areValuesEnclosedAndNonEmptyResult == ParserUtil.AreValuesEnclosedAndNonEmptyResult.FAILURE) {
            throw new ParseException(UsageMessage.PARTNERCOURSE_SEARCH.toString());
        } else if (areValuesEnclosedAndNonEmptyResult == ParserUtil.AreValuesEnclosedAndNonEmptyResult.EMPTY) {
            throw new ParseException(UsageMessage.PARTNERCOURSE_SEARCH.getValueWithEmptyArgs());
        }

        SeplendidArgumentMap parameterToArgMap =
                SeplendidArgumentTokenizer.tokenize(args,
                        PARAMETER_PARTNERATTRIBUTE, PARAMETER_QUERY);

        if (!ParserUtil.areArgumentsPresent(parameterToArgMap,
                PARAMETER_PARTNERATTRIBUTE, PARAMETER_QUERY)) {
            throw new ParseException(UsageMessage.PARTNERCOURSE_SEARCH.toString());
        }

        PartnerCourseAttribute partnerCourseAttribute = ParserUtil.parsePartnerCourseAttributeForSearch(
                parameterToArgMap.getValue(PARAMETER_PARTNERATTRIBUTE).get());
        String query = parseQuery(partnerCourseAttribute, parameterToArgMap.getValue(PARAMETER_QUERY).get());
        return new PartnerCourseSearchCommand(partnerCourseAttribute,
                new PartnerCourseContainsKeywordsPredicate(query, partnerCourseAttribute), query);
    }

    private String parseQuery(PartnerCourseAttribute partnerCourseAttribute, String query)
            throws ParseException {
        requireAllNonNull(partnerCourseAttribute, query);
        String trimmedQuery = query.trim();
        switch (partnerCourseAttribute) {
        case PARTNERCODE:
            if (!PartnerCode.isValidPartnerCode(trimmedQuery)) {
                throw new ParseException(ConstraintMessage.PARTNERCOURSE_CODE.toString());
            }
            break;
        case PARTNERNAME:
            if (!PartnerName.isValidPartnerName(trimmedQuery)) {
                throw new ParseException(ConstraintMessage.PARTNERCOURSE_NAME.toString());
            }
            break;
        default:
            //do nothing
        }
        return trimmedQuery;
    }
}
