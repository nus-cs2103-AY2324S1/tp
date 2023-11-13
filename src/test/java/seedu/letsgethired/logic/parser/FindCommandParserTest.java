package seedu.letsgethired.logic.parser;

import static seedu.letsgethired.logic.parser.CliSyntax.PREFIX_COMPANY;
import static seedu.letsgethired.logic.parser.CliSyntax.PREFIX_CYCLE;
import static seedu.letsgethired.logic.parser.CliSyntax.PREFIX_NOTE_INSERT;
import static seedu.letsgethired.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.letsgethired.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.letsgethired.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.letsgethired.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import javafx.util.Pair;
import seedu.letsgethired.logic.commands.FindCommand;
import seedu.letsgethired.model.application.CompanyContainsFieldKeywordsPredicate;

public class FindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", FindCommand.NO_FIND_SPECIFIED + "\n" + FindCommand.MESSAGE_USAGE);
    }

    @Test
    public void parse_validArgCompany_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new CompanyContainsFieldKeywordsPredicate(Arrays.asList(
                        new Pair<>(PREFIX_COMPANY, "Jane Street"))));
        assertParseSuccess(parser, FindCommand.COMMAND_WORD + " " + PREFIX_COMPANY + "Jane Street",
                expectedFindCommand);
    }

    @Test
    public void parse_validArgStatus_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new CompanyContainsFieldKeywordsPredicate(Arrays.asList(
                        new Pair<>(PREFIX_STATUS, "Pending"))));
        assertParseSuccess(parser, FindCommand.COMMAND_WORD + " " + PREFIX_STATUS + "Pending",
                expectedFindCommand);
    }

    @Test
    public void parse_validArgRole_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new CompanyContainsFieldKeywordsPredicate(Arrays.asList(
                        new Pair<>(PREFIX_ROLE, "Software Engineer"))));
        assertParseSuccess(parser, FindCommand.COMMAND_WORD + " " + PREFIX_ROLE + "Software Engineer",
                expectedFindCommand);
    }

    @Test
    public void parse_validArgCycle_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new CompanyContainsFieldKeywordsPredicate(Arrays.asList(
                        new Pair<>(PREFIX_CYCLE, "Summer 2023"))));
        assertParseSuccess(parser, FindCommand.COMMAND_WORD + " " + PREFIX_CYCLE + "Summer 2023",
                expectedFindCommand);
    }

    @Test
    public void parse_validArgNote_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new CompanyContainsFieldKeywordsPredicate(Arrays.asList(
                        new Pair<>(PREFIX_NOTE_INSERT, "require MERN"))));
        assertParseSuccess(parser, FindCommand.COMMAND_WORD + " " + PREFIX_NOTE_INSERT + "require MERN",
                expectedFindCommand);
    }

    @Test
    public void parse_validArgMultipleWhitespaces_returnsFindCommand() {
        // multiple whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new CompanyContainsFieldKeywordsPredicate(Arrays.asList(
                        new Pair<>(PREFIX_COMPANY, "Jane Street"))));
        assertParseSuccess(parser, FindCommand.COMMAND_WORD + " " + PREFIX_COMPANY + "  Jane Street    ",
                expectedFindCommand);
    }

    @Test
    public void parse_multipleValidArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new CompanyContainsFieldKeywordsPredicate(Arrays.asList(
                        new Pair<>(PREFIX_COMPANY, "Jane Street"))));
        assertParseSuccess(parser, FindCommand.COMMAND_WORD + " " + PREFIX_COMPANY + "Jane Street",
                expectedFindCommand);
    }
}
