package seedu.address.logic.parser.mapping;

import static seedu.address.logic.parser.CliSyntax.PARAMETER_MAPPINGATTRIBUTE;
import static seedu.address.logic.parser.CliSyntax.PARAMETER_QUERY;
import static seedu.address.logic.parser.ParserUtil.areValuesEnclosedAndNonEmpty;
import static seedu.address.logic.parser.ParserUtil.parseMappingAttributeForSearch;

import seedu.address.logic.commands.mapping.MappingSearchCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.SeplendidArgumentMap;
import seedu.address.logic.parser.SeplendidArgumentTokenizer;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.messages.UsageMessage;
import seedu.address.model.mapping.MappingAttribute;
import seedu.address.model.mapping.MappingAttributeContainsKeywordPredicate;


/**
 * Parses input arguments and creates a new MappingSearchCommandParser object.
 */
public class MappingSearchCommandParser implements Parser<MappingSearchCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the MappingSearchCommandParser
     * and returns a MappingSearchCommandParser object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public MappingSearchCommand parse(String args) throws ParseException {
        ParserUtil.AreValuesEnclosedAndNonEmptyResult areValuesEnclosedAndNonEmptyResult =
                areValuesEnclosedAndNonEmpty(args);
        if (areValuesEnclosedAndNonEmptyResult == ParserUtil.AreValuesEnclosedAndNonEmptyResult.FAILURE) {
            throw new ParseException(UsageMessage.MAPPING_SEARCH.toString());
        } else if (areValuesEnclosedAndNonEmptyResult == ParserUtil.AreValuesEnclosedAndNonEmptyResult.EMPTY) {
            throw new ParseException(UsageMessage.MAPPING_SEARCH.getValueWithEmptyArgs());
        }

        assert areValuesEnclosedAndNonEmptyResult == ParserUtil.AreValuesEnclosedAndNonEmptyResult.SUCCESS;

        SeplendidArgumentMap parameterToArgMap =
                SeplendidArgumentTokenizer.tokenize(args, PARAMETER_MAPPINGATTRIBUTE, PARAMETER_QUERY);


        if (!ParserUtil.areArgumentsPresent(parameterToArgMap, PARAMETER_MAPPINGATTRIBUTE, PARAMETER_QUERY)) {
            throw new ParseException(
                    UsageMessage.MAPPING_SEARCH.toString());
        }

        // All arguments should be a non-empty {@code Optional}
        MappingAttribute attributeEnumValue = parseMappingAttributeForSearch(parameterToArgMap.getValue(
                PARAMETER_MAPPINGATTRIBUTE).get().toLowerCase());
        // query is non-empty
        String query = parameterToArgMap.getValue(PARAMETER_QUERY).get();

        MappingAttributeContainsKeywordPredicate predicate =
                new MappingAttributeContainsKeywordPredicate(query, attributeEnumValue);

        return new MappingSearchCommand(predicate);
    }

}
