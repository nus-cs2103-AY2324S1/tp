package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseComplexFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFindSuccess;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindCommand;
import seedu.address.model.person.Person;
import seedu.address.model.person.PersonType;
import seedu.address.model.person.predicates.NameContainsKeywordsPredicate;


public class FindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseComplexFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindCommand.MESSAGE_USAGE_PATIENT), PersonType.PATIENT);
        assertParseComplexFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindCommand.MESSAGE_USAGE_SPECIALIST), PersonType.SPECIALIST);
    }

    @Test
    public void parse_validNameArgs_returnsFindCommand() {

        List<String> keywords = Arrays.asList("Alice", "Bob");
        List<Predicate<Person>> predicateList = new ArrayList<>();
        predicateList.add(PersonType.PATIENT.getSearchPredicate());
        predicateList.add(new NameContainsKeywordsPredicate(keywords));
        Predicate<Person> expectedPredicate = person -> predicateList.stream().map(p -> p.test(person))
                .reduce(true, (x, y) -> x && y);
;
        // no leading and trailing whitespaces
        assertParseFindSuccess(parser, " " + PREFIX_NAME + " Alice Bob", expectedPredicate, PersonType.PATIENT);
        // multiple whitespaces between keywords
        assertParseFindSuccess(parser, " " + PREFIX_NAME + " \n Alice \n \t Bob  \t",
                expectedPredicate, PersonType.PATIENT);
    }
}
