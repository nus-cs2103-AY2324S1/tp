package seedu.address.logic.parser;


import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BUDGET;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FilterCustomerCommand;
import seedu.address.model.customer.Budget;
import seedu.address.model.customer.BudgetAndTagsInRangePredicate;
import seedu.address.model.tag.Tag;

public class FilterCustomerCommandParserTest {
    private FilterCustomerCommandParser parser = new FilterCustomerCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCustomerCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFilterCustomerCommand() {
        String budgetString = "10000";
        Budget budget = new Budget(budgetString);

        String tagString = "pink";
        Tag tag = new Tag(tagString);
        Set<Tag> tags = new HashSet<>();
        tags.add(tag);

        String userInput = " " + PREFIX_BUDGET + budgetString + " " + PREFIX_TAG + tagString;

        FilterCustomerCommand expectedFilterCustomerCommand =
                new FilterCustomerCommand(new BudgetAndTagsInRangePredicate(budget, tags));
        assertParseSuccess(parser, userInput, expectedFilterCustomerCommand);
    }
}
