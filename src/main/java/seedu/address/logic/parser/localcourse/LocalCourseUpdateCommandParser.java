package seedu.address.logic.parser.localcourse;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PARAMETER_LOCALATTRIBUTE;
import static seedu.address.logic.parser.CliSyntax.PARAMETER_LOCALCODE;
import static seedu.address.logic.parser.CliSyntax.PARAMETER_LOCALUPDATEDVALUE;
import static seedu.address.logic.parser.ParserUtil.areValuesEnclosedAndNonEmpty;

import seedu.address.logic.commands.localcourse.LocalCourseUpdateCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.SeplendidArgumentMap;
import seedu.address.logic.parser.SeplendidArgumentTokenizer;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.messages.ConstraintMessage;
import seedu.address.messages.UsageMessage;
import seedu.address.model.localcourse.LocalCode;
import seedu.address.model.localcourse.LocalCourseAttribute;
import seedu.address.model.localcourse.LocalDescription;
import seedu.address.model.localcourse.LocalName;
import seedu.address.model.localcourse.LocalUnit;

/**
 * Parses input arguments and creates a new LocalCourseUpdateCommand object.
 */
public class LocalCourseUpdateCommandParser implements Parser<LocalCourseUpdateCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the LocalCourseUpdateCommand
     * and returns a LocalCourseUpdateCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format.
     */
    public LocalCourseUpdateCommand parse(String args) throws ParseException {
        ParserUtil.AreValuesEnclosedAndNonEmptyResult areValuesEnclosedAndNonEmptyResult =
                areValuesEnclosedAndNonEmpty(args);
        if (areValuesEnclosedAndNonEmptyResult == ParserUtil.AreValuesEnclosedAndNonEmptyResult.FAILURE) {
            throw new ParseException(UsageMessage.LOCALCOURSE_UPDATE.toString());
        } else if (areValuesEnclosedAndNonEmptyResult == ParserUtil.AreValuesEnclosedAndNonEmptyResult.EMPTY) {
            throw new ParseException(UsageMessage.LOCALCOURSE_UPDATE.getValueWithEmptyArgs());
        }

        assert areValuesEnclosedAndNonEmptyResult == ParserUtil.AreValuesEnclosedAndNonEmptyResult.SUCCESS;

        SeplendidArgumentMap parameterToArgMap = SeplendidArgumentTokenizer.tokenize(args,
                PARAMETER_LOCALCODE,
                PARAMETER_LOCALATTRIBUTE,
                PARAMETER_LOCALUPDATEDVALUE);

        if (!ParserUtil.areArgumentsPresent(parameterToArgMap,
                PARAMETER_LOCALCODE,
                PARAMETER_LOCALATTRIBUTE,
                PARAMETER_LOCALUPDATEDVALUE)) {
            throw new ParseException(UsageMessage.LOCALCOURSE_UPDATE.toString());
        }

        LocalCode localCode = ParserUtil.parseLocalCode(parameterToArgMap.getValue(PARAMETER_LOCALCODE).get());
        LocalCourseAttribute localCourseAttribute = ParserUtil.parseLocalCourseAttributeForUpdate(
                parameterToArgMap.getValue(PARAMETER_LOCALATTRIBUTE).get());
        String updatedValue = parseUpdatedValue(localCourseAttribute,
                parameterToArgMap.getValue(PARAMETER_LOCALUPDATEDVALUE).get());

        return new LocalCourseUpdateCommand(localCode, localCourseAttribute, updatedValue);
    }

    private String parseUpdatedValue(LocalCourseAttribute localCourseAttribute, String updatedValue)
            throws ParseException {
        requireAllNonNull(localCourseAttribute, updatedValue);
        String trimmedUpdatedValue = updatedValue.trim();
        switch (localCourseAttribute) {
        case LOCALCODE:
            if (!LocalCode.isValidLocalCode(trimmedUpdatedValue)) {
                throw new ParseException(ConstraintMessage.LOCALCOURSE_CODE.toString());
            }
            break;
        case LOCALNAME:
            if (!LocalName.isValidLocalName(trimmedUpdatedValue)) {
                throw new ParseException(ConstraintMessage.LOCALCOURSE_NAME.toString());
            }
            break;
        case LOCALUNIT:
            if (!LocalUnit.isValidLocalUnit(trimmedUpdatedValue)) {
                throw new ParseException(ConstraintMessage.LOCALCOURSE_UNIT.toString());
            }
            break;
        case LOCALDESCRIPTION:
            if (!LocalDescription.isValidLocalDescription(trimmedUpdatedValue)) {
                throw new ParseException(ConstraintMessage.LOCALCOURSE_DESCRIPTION.toString());
            }
            break;
        default:
            //do nothing
        }
        return trimmedUpdatedValue;
    }
}
