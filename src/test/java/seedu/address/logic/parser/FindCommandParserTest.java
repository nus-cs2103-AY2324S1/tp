package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;
import java.util.HashSet;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindCommand;
import seedu.address.model.musician.Musician;
import seedu.address.model.musician.NameContainsKeywordsPredicate;
import seedu.address.model.tag.GenreMatchesPredicate;
import seedu.address.model.tag.InstrumentMatchesPredicate;
import seedu.address.model.tag.TagMatchesPredicate;

public class FindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, " non-empty preamble",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgsWithNameKeywords_returnsFindCommand() {
        // no leading and trailing whitespaces
        HashSet<Predicate<Musician>> predicates = new HashSet<>(Arrays.asList(
                new NameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob"))
        ));
        FindCommand expectedFindCommand =
                new FindCommand(predicates);
        assertParseSuccess(parser, " n/Alice n/Bob", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n n/Alice \n \t n/Bob  \t", expectedFindCommand);
    }

    @Test
    public void parse_validArgsWithTagMatches_returnsFindCommand() {
        // no leading and trailing whitespaces
        HashSet<Predicate<Musician>> predicates = new HashSet<>(Arrays.asList(
                new TagMatchesPredicate(Arrays.asList("friends", "cool"))
        ));
        FindCommand expectedFindCommand =
                new FindCommand(predicates);
        assertParseSuccess(parser, " t/friends t/cool", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n t/friends \n \t t/cool  \t", expectedFindCommand);
    }

    @Test
    public void parse_validArgsWithMixedPredicates_returnsFindCommand() {
        HashSet<Predicate<Musician>> predicates = new HashSet<>(Arrays.asList(
                new NameContainsKeywordsPredicate(Arrays.asList("Alice")),
                new InstrumentMatchesPredicate(Arrays.asList("guitar", "drums")),
                new GenreMatchesPredicate(Arrays.asList("rock"))
        ));
        FindCommand expectedFindCommand =
                new FindCommand(predicates);
        assertParseSuccess(parser, " n/Alice g/rock i/guitar i/drums", expectedFindCommand);

        // multiple whitespaces between keywords and interchanged order
        assertParseSuccess(parser, " \n n/Alice \n \t i/guitar \t g/rock \t i/drums", expectedFindCommand);
    }
}
