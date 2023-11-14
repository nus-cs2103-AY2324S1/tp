package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_CUSTOMERS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BUDGET;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.testutil.TypicalCustomers.ALICE;
import static seedu.address.testutil.TypicalCustomers.BENSON;
import static seedu.address.testutil.TypicalCustomers.getTypicalAddressBook;
import static seedu.address.testutil.TypicalProperties.getTypicalPropertyBook;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.FilterCustomerCommandParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.customer.Budget;
import seedu.address.model.customer.BudgetAndTagsInRangePredicate;
import seedu.address.model.tag.Tag;


public class FilterCustomerCommandTest {
    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setup() {
        model = new ModelManager(getTypicalAddressBook(), getTypicalPropertyBook(), new UserPrefs());
        expectedModel = new ModelManager(getTypicalAddressBook(), getTypicalPropertyBook(), new UserPrefs());
    }

    @Test
    public void equals() {
        String smallBudgetString = "100000";
        String bigBudgetString = "1000000000";

        String firstTagString = "sunny";
        String secondTagString = "bright";

        String filterFirstCustomerCommandString = FilterCustomerCommand.COMMAND_WORD + " "
                + PREFIX_BUDGET + smallBudgetString + " " + PREFIX_TAG + firstTagString;
        String filterSecondCustomerCommandString = FilterCustomerCommand.COMMAND_WORD + " "
                + PREFIX_BUDGET + bigBudgetString + " " + PREFIX_TAG + secondTagString;

        FilterCustomerCommand filterFirstCustomerCommand = preparePredicate(filterFirstCustomerCommandString);
        FilterCustomerCommand filterSecondCustomerCommand = preparePredicate(filterSecondCustomerCommandString);

        // same object -> returns true
        assertTrue(filterFirstCustomerCommand.equals(filterFirstCustomerCommand));

        // same values -> returns true
        FilterCustomerCommand filterFirstCustomerCommandCopy = preparePredicate(filterFirstCustomerCommandString);
        assertTrue(filterFirstCustomerCommand.equals(filterFirstCustomerCommandCopy));

        // different types -> returns false
        assertFalse(filterFirstCustomerCommand.equals(1));

        // null -> returns false
        assertFalse(filterFirstCustomerCommand.equals(null));

        // different customer -> returns false
        assertFalse(filterFirstCustomerCommand.equals(filterSecondCustomerCommand));
    }

    @Test
    public void execute_noBudgetSingleTag_noCustomerFound() {
        // no customer found
        String notFoundTagString = "notfound";
        Tag notFoundTag = new Tag(notFoundTagString);

        Set<Tag> notFoundTags = new HashSet<>();
        notFoundTags.add(notFoundTag);

        BudgetAndTagsInRangePredicate predicate = new BudgetAndTagsInRangePredicate(null, notFoundTags);
        String commandString = FilterCustomerCommand.COMMAND_WORD + " " + PREFIX_TAG + notFoundTagString;
        FilterCustomerCommand command = preparePredicate(commandString);

        String expectedMessage = String.format(MESSAGE_CUSTOMERS_LISTED_OVERVIEW, 0);
        expectedModel.updateFilteredCustomerList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredCustomerList());
    }

    @Test
    public void execute_noBudgetSingleTag_customerFound() {
        // customers found
        String foundTagString = "pink";
        Tag foundTag = new Tag(foundTagString);

        Set<Tag> foundTags = new HashSet<>();
        foundTags.add(foundTag);

        BudgetAndTagsInRangePredicate predicate = new BudgetAndTagsInRangePredicate(null, foundTags);
        String commandString = FilterCustomerCommand.COMMAND_WORD + " " + PREFIX_TAG + foundTagString;
        FilterCustomerCommand command = preparePredicate(commandString);

        expectedModel.updateFilteredCustomerList(predicate);
        String expectedMessage = String.format(MESSAGE_CUSTOMERS_LISTED_OVERVIEW, 1);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE), model.getFilteredCustomerList());
    }

    @Test
    public void execute_noBudgetSomeTags_noCustomerFound() {
        // no customer found
        String notFoundTagString = "notfound";
        String otherNotFoundString = "othernotfound";

        Tag notFoundTag = new Tag(notFoundTagString);
        Tag otherNotFoundTag = new Tag(otherNotFoundString);

        Set<Tag> notFoundTags = new HashSet<>();
        notFoundTags.add(notFoundTag);
        notFoundTags.add(otherNotFoundTag);

        BudgetAndTagsInRangePredicate predicate = new BudgetAndTagsInRangePredicate(null, notFoundTags);
        String commandString = FilterCustomerCommand.COMMAND_WORD + " " + PREFIX_TAG + notFoundTagString + " "
                + PREFIX_TAG + otherNotFoundString;
        FilterCustomerCommand command = preparePredicate(commandString);

        String expectedMessage = String.format(MESSAGE_CUSTOMERS_LISTED_OVERVIEW, 0);
        expectedModel.updateFilteredCustomerList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredCustomerList());
    }

    @Test
    public void execute_noBudgetSomeTags_customerFound() {
        // customers found
        String foundTagString = "square";
        String otherFoundTagString = "garden";

        Tag foundTag = new Tag(foundTagString);
        Tag otherFoundTag = new Tag(otherFoundTagString);

        Set<Tag> foundTags = new HashSet<>();
        foundTags.add(foundTag);
        foundTags.add(otherFoundTag);

        BudgetAndTagsInRangePredicate predicate = new BudgetAndTagsInRangePredicate(null, foundTags);
        String commandString = FilterCustomerCommand.COMMAND_WORD + " " + PREFIX_TAG + foundTagString + " "
                + PREFIX_TAG + otherFoundTagString;
        FilterCustomerCommand command = preparePredicate(commandString);

        expectedModel.updateFilteredCustomerList(predicate);
        String expectedMessage = String.format(MESSAGE_CUSTOMERS_LISTED_OVERVIEW, 1);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(BENSON), model.getFilteredCustomerList());
    }

    @Test
    public void execute_withBudgetSingleTag_noCustomerFound() {
        // no customer found
        String notFoundTagString = "notfound";
        Tag notFoundTag = new Tag(notFoundTagString);


        Set<Tag> notFoundTags = new HashSet<>();
        notFoundTags.add(notFoundTag);

        String budgetString = "100000";
        Budget budget = new Budget(budgetString);

        BudgetAndTagsInRangePredicate predicate = new BudgetAndTagsInRangePredicate(budget, notFoundTags);
        String commandString = FilterCustomerCommand.COMMAND_WORD + " " + PREFIX_BUDGET + budgetString + " "
                + PREFIX_TAG + notFoundTagString;
        FilterCustomerCommand command = preparePredicate(commandString);

        String expectedMessage = String.format(MESSAGE_CUSTOMERS_LISTED_OVERVIEW, 0);
        expectedModel.updateFilteredCustomerList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredCustomerList());
    }

    @Test
    public void execute_withBudgetSingleTag_customerFound() {
        // customers found
        String foundTagString = "pink";
        Tag foundTag = new Tag(foundTagString);

        Set<Tag> foundTags = new HashSet<>();
        foundTags.add(foundTag);

        String budgetString = "10000";
        Budget budget = new Budget(budgetString);

        BudgetAndTagsInRangePredicate predicate = new BudgetAndTagsInRangePredicate(budget, foundTags);
        String commandString = FilterCustomerCommand.COMMAND_WORD + " " + PREFIX_BUDGET + budgetString + " "
                + PREFIX_TAG + foundTagString;
        FilterCustomerCommand command = preparePredicate(commandString);

        expectedModel.updateFilteredCustomerList(predicate);
        String expectedMessage = String.format(MESSAGE_CUSTOMERS_LISTED_OVERVIEW, 1);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE), model.getFilteredCustomerList());
    }

    @Test
    public void execute_withBudgetSomeTags_noCustomerFound() {
        // no customer found
        String notFoundTagString = "notfound";
        String otherNotFoundString = "othernotfound";

        Tag notFoundTag = new Tag(notFoundTagString);
        Tag otherNotFoundTag = new Tag(otherNotFoundString);

        Set<Tag> notFoundTags = new HashSet<>();
        notFoundTags.add(notFoundTag);
        notFoundTags.add(otherNotFoundTag);

        String budgetString = "10000";
        Budget budget = new Budget(budgetString);

        BudgetAndTagsInRangePredicate predicate = new BudgetAndTagsInRangePredicate(budget, notFoundTags);
        String commandString = FilterCustomerCommand.COMMAND_WORD + " " + PREFIX_BUDGET + budgetString + " "
                + PREFIX_TAG + notFoundTagString + " " + PREFIX_TAG + otherNotFoundString;
        FilterCustomerCommand command = preparePredicate(commandString);

        String expectedMessage = String.format(MESSAGE_CUSTOMERS_LISTED_OVERVIEW, 0);
        expectedModel.updateFilteredCustomerList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredCustomerList());
    }

    @Test
    public void execute_withBudgetSomeTags_customerFound() {
        // customers found
        String foundTagString = "square";
        String otherFoundTagString = "garden";

        Tag foundTag = new Tag(foundTagString);
        Tag otherFoundTag = new Tag(otherFoundTagString);

        Set<Tag> foundTags = new HashSet<>();
        foundTags.add(foundTag);
        foundTags.add(otherFoundTag);

        String budgetString = "10000";
        Budget budget = new Budget(budgetString);

        BudgetAndTagsInRangePredicate predicate = new BudgetAndTagsInRangePredicate(budget, foundTags);
        String commandString = FilterCustomerCommand.COMMAND_WORD + " " + PREFIX_TAG + foundTagString + " "
                + PREFIX_TAG + otherFoundTagString;
        FilterCustomerCommand command = preparePredicate(commandString);

        expectedModel.updateFilteredCustomerList(predicate);
        String expectedMessage = String.format(MESSAGE_CUSTOMERS_LISTED_OVERVIEW, 1);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(BENSON), model.getFilteredCustomerList());
    }

    @Test
    public void toStringMethod() {
        String firstTagString = "square";
        String secondTagString = "garden";

        Tag firstTag = new Tag(firstTagString);
        Tag secondTag = new Tag(secondTagString);

        Set<Tag> tags = new HashSet<>();
        tags.add(firstTag);
        tags.add(secondTag);

        String budgetString = "100000000";
        Budget budget = new Budget(budgetString);

        BudgetAndTagsInRangePredicate predicate = new BudgetAndTagsInRangePredicate(budget, tags);
        FilterCustomerCommand filterCustomerCommand = new FilterCustomerCommand(predicate);
        String expected = FilterCustomerCommand.class.getCanonicalName() + "{predicate=" + predicate + "}";
        assertEquals(expected, filterCustomerCommand.toString());
    }

    private FilterCustomerCommand preparePredicate(String message) {
        try {
            return (new FilterCustomerCommandParser()).parse(message);
        } catch (ParseException e) {
            assert false;
            return null;
        }
    }
}
