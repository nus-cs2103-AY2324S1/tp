package seedu.address.logic.parser;

import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFindSuccess;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindPredicateMap;
import seedu.address.model.person.PersonType;
import seedu.address.model.person.predicates.NameContainsKeywordsPredicate;


public class FindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_validNameArgs_returnsFindCommand() {
        List<String> keywords = Arrays.asList("Alice", "Bob");
        FindPredicateMap findPredicateMap = new FindPredicateMap();
        findPredicateMap.put(PREFIX_NAME, new NameContainsKeywordsPredicate(keywords));
;
        // no leading and trailing whitespaces
        assertParseFindSuccess(parser, " " + PREFIX_NAME + " Alice Bob", findPredicateMap, PersonType.PATIENT);
        // multiple whitespaces between keywords
        assertParseFindSuccess(parser, " " + PREFIX_NAME + " \n Alice \n \t Bob  \t",
                findPredicateMap, PersonType.PATIENT);
    }
}
