package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.FindCommand;
import seedu.address.model.person.Appointment;
import seedu.address.model.person.enums.InputSource;
import seedu.address.model.person.predicates.AppointmentOverlapsPredicate;
import seedu.address.model.person.predicates.CompositePredicate;
import seedu.address.model.person.predicates.IdContainsKeywordsPredicate;
import seedu.address.model.person.predicates.NameContainsKeywordsPredicate;

public class FindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_illegalFields_throwsParseException() {
        assertParseFailure(parser, " n/Alice p/91234567",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        assertParseFailure(parser, " n/Alice e/example@email.com",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        assertParseFailure(parser, " n/Alice a/Address Here",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        assertParseFailure(parser, " n/Alice m/On Medication XYZ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        assertParseFailure(parser, " n/Alice n/Benson",
                Messages.getErrorMessageForDuplicatePrefixes(CliSyntax.PREFIX_NAME));
        assertParseFailure(parser, " p/91234567",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void nameparse_validArgs_returnsFindCommand() {
        CompositePredicate findCommandPredicate = new CompositePredicate();
        findCommandPredicate.add(new NameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob")));
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand = new FindCommand(findCommandPredicate);
        assertParseSuccess(parser, " n/Alice Bob", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " n/Alice \n \t Bob  \t", expectedFindCommand);
    }

    @Test
    public void nricparse_validArgs_returnsFindCommand() {
        CompositePredicate findCommandPredicate = new CompositePredicate();
        findCommandPredicate.add(new IdContainsKeywordsPredicate(Arrays.asList("T0100606Z", "T0206006Z")));
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand = new FindCommand(findCommandPredicate);
        assertParseSuccess(parser, " id/T0100606Z T0206006Z", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " id/T0100606Z \n \t T0206006Z  \t", expectedFindCommand);
    }

    @Test
    public void apptparse_validArgs_returnsFindCommand() throws Exception {
        Appointment appointment = Appointment.of("1-1-2021, 9 11:30", InputSource.USER_INPUT);
        CompositePredicate findCommandPredicate = new CompositePredicate();
        findCommandPredicate.add(new AppointmentOverlapsPredicate(appointment));
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand = new FindCommand(findCommandPredicate);
        assertParseSuccess(parser, " ap/1-1-2021, 9 11:30", expectedFindCommand);
    }
}
