package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.DEPARTMENT_DESC_INVESTMENT;
import static seedu.address.logic.commands.CommandTestUtil.DEPARTMENT_DESC_LOGISTIC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DEPARTMENT_INVESTMENT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DEPARTMENT_LOGISTIC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEPARTMENT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FilterCommand;
import seedu.address.model.employee.ContainsDepartmentPredicate;

public class FilterCommandParserTest {
    private FilterCommandParser parser = new FilterCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, " " + PREFIX_DEPARTMENT, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FilterCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFilterCommand() {
        String userInput = DEPARTMENT_DESC_INVESTMENT;
        FilterCommand expectedFilterCommand =
                new FilterCommand(new ContainsDepartmentPredicate(VALID_DEPARTMENT_INVESTMENT));
        assertParseSuccess(parser, userInput, expectedFilterCommand);

        userInput = DEPARTMENT_DESC_LOGISTIC;
        expectedFilterCommand = new FilterCommand(new ContainsDepartmentPredicate(VALID_DEPARTMENT_LOGISTIC));
        assertParseSuccess(parser, userInput, expectedFilterCommand);
    }

    @Test
    public void parse_moreThanOneArg_throwsParseException() {
        String userInput = DEPARTMENT_DESC_INVESTMENT + DEPARTMENT_DESC_LOGISTIC;
        assertParseFailure(parser, userInput, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FilterCommand.MESSAGE_USAGE));
    }
}
