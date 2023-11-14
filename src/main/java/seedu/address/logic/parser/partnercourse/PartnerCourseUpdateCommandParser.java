package seedu.address.logic.parser.partnercourse;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PARAMETER_PARTNERATTRIBUTE;
import static seedu.address.logic.parser.CliSyntax.PARAMETER_PARTNERCODE;
import static seedu.address.logic.parser.CliSyntax.PARAMETER_PARTNERUPDATEDVALUE;
import static seedu.address.logic.parser.CliSyntax.PARAMETER_UNIVERSITYNAME;
import static seedu.address.logic.parser.ParserUtil.areValuesEnclosedAndNonEmpty;

import seedu.address.logic.commands.partnercourse.PartnerCourseUpdateCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.SeplendidArgumentMap;
import seedu.address.logic.parser.SeplendidArgumentTokenizer;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.messages.ConstraintMessage;
import seedu.address.messages.UsageMessage;
import seedu.address.model.partnercourse.PartnerCode;
import seedu.address.model.partnercourse.PartnerCourseAttribute;
import seedu.address.model.partnercourse.PartnerDescription;
import seedu.address.model.partnercourse.PartnerName;
import seedu.address.model.partnercourse.PartnerUnit;
import seedu.address.model.university.UniversityName;

/**
 * Parses input arguments and creates a new PartnerCourseUpdateCommand object.
 */
public class PartnerCourseUpdateCommandParser implements Parser<PartnerCourseUpdateCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the PartnerCourseUpdateCommand
     * and returns a PartnerCourseUpdateCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format.
     */
    public PartnerCourseUpdateCommand parse(String args) throws ParseException {
        ParserUtil.AreValuesEnclosedAndNonEmptyResult areValuesEnclosedAndNonEmptyResult =
                areValuesEnclosedAndNonEmpty(args);
        if (areValuesEnclosedAndNonEmptyResult == ParserUtil.AreValuesEnclosedAndNonEmptyResult.FAILURE) {
            throw new ParseException(UsageMessage.PARTNERCOURSE_UPDATE.toString());
        } else if (areValuesEnclosedAndNonEmptyResult == ParserUtil.AreValuesEnclosedAndNonEmptyResult.EMPTY) {
            throw new ParseException(UsageMessage.PARTNERCOURSE_UPDATE.getValueWithEmptyArgs());
        }

        SeplendidArgumentMap parameterToArgMap = SeplendidArgumentTokenizer.tokenize(args,
                PARAMETER_UNIVERSITYNAME,
                PARAMETER_PARTNERCODE,
                PARAMETER_PARTNERATTRIBUTE,
                PARAMETER_PARTNERUPDATEDVALUE);

        if (!ParserUtil.areArgumentsPresent(parameterToArgMap,
                PARAMETER_UNIVERSITYNAME,
                PARAMETER_PARTNERCODE,
                PARAMETER_PARTNERATTRIBUTE,
                PARAMETER_PARTNERUPDATEDVALUE)) {
            throw new ParseException(UsageMessage.PARTNERCOURSE_UPDATE.toString());
        }

        UniversityName universityName =
                ParserUtil.parseUniversityName(parameterToArgMap.getValue(PARAMETER_UNIVERSITYNAME).get());
        PartnerCode partnerCode = ParserUtil.parsePartnerCode(parameterToArgMap.getValue(PARAMETER_PARTNERCODE).get());
        PartnerCourseAttribute partnerCourseAttribute = ParserUtil.parsePartnerCourseAttributeForUpdate(
                parameterToArgMap.getValue(PARAMETER_PARTNERATTRIBUTE).get());
        String updatedValue = parseUpdatedValue(partnerCourseAttribute,
                parameterToArgMap.getValue(PARAMETER_PARTNERUPDATEDVALUE).get());

        return new PartnerCourseUpdateCommand(universityName, partnerCode, partnerCourseAttribute, updatedValue);
    }

    private String parseUpdatedValue(PartnerCourseAttribute partnerCourseAttribute, String updatedValue)
            throws ParseException {
        requireAllNonNull(partnerCourseAttribute, updatedValue);
        String trimmedUpdatedValue = updatedValue.trim();
        switch (partnerCourseAttribute) {
        case PARTNERCODE:
            if (!PartnerCode.isValidPartnerCode(trimmedUpdatedValue)) {
                throw new ParseException(ConstraintMessage.PARTNERCOURSE_CODE.toString());
            }
            break;
        case PARTNERNAME:
            if (!PartnerName.isValidPartnerName(trimmedUpdatedValue)) {
                throw new ParseException(ConstraintMessage.PARTNERCOURSE_NAME.toString());
            }
            break;
        case PARTNERUNIT:
            if (!PartnerUnit.isValidPartnerUnit(trimmedUpdatedValue)) {
                throw new ParseException(ConstraintMessage.PARTNERCOURSE_UNIT.toString());
            }
            break;
        case PARTNERDESCRIPTION:
            if (!PartnerDescription.isValidPartnerDescription(trimmedUpdatedValue)) {
                throw new ParseException(ConstraintMessage.PARTNERCOURSE_DESCRIPTION.toString());
            }
            break;
        default:
            //do nothing
        }
        return trimmedUpdatedValue;
    }
}
