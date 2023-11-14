package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandPropertyTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalCustomers.getTypicalAddressBook;
import static seedu.address.testutil.TypicalCustomers.getTypicalCustomers;
import static seedu.address.testutil.TypicalProperties.getTypicalPropertyBook;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.parser.MatchCustomerCommandParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.customer.Budget;
import seedu.address.model.customer.Customer;
import seedu.address.model.property.Price;
import seedu.address.model.property.PriceAndTagsInRangePredicate;
import seedu.address.model.tag.Tag;

/**
 * Contains integration tests (interaction with the Model) and unit tests for MatchCustomerCommand.
 */
public class MatchCustomerCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), getTypicalPropertyBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), model.getPropertyBook(), new UserPrefs());
    }

    @Test
    public void equals() {
        MatchCustomerCommand matchFirstCustomerCommand = prepareIndex("1");
        MatchCustomerCommand matchSecondCustomerCommand = prepareIndex("2");

        //same object returns true.
        assertTrue(matchFirstCustomerCommand.equals(matchFirstCustomerCommand));

        //same index returns true.
        assertTrue(matchFirstCustomerCommand.equals(prepareIndex("1")));

        //different types returns false.
        assertFalse(matchFirstCustomerCommand.equals(1));
        assertFalse(matchFirstCustomerCommand.equals("1"));

        //null returns false.
        assertFalse(matchFirstCustomerCommand.equals(null));

        //different index return false
        assertFalse(matchFirstCustomerCommand.equals(matchSecondCustomerCommand));
        assertFalse(matchFirstCustomerCommand.equals(prepareIndex("2")));

    }

    @Test
    public void execute_customerNotFound_showsError() {
        String index = "100";
        MatchCustomerCommand matchCustomerCommand = prepareIndex(index);
        assertCommandFailure(matchCustomerCommand, model, MatchCustomerCommand.MESSAGE_FAIL + index);
    }

    @Test
    public void execute_customerFound_propertyFiltered() {
        String index = "1";

        MatchCustomerCommand matchCustomerCommand = prepareIndex(index);

        Customer customer = getTypicalCustomers().get(0);

        Budget budget = customer.getBudget();
        Set<Tag> tags = customer.getTags();

        Price price = budget.convertToPrice();
        PriceAndTagsInRangePredicate predicate = new PriceAndTagsInRangePredicate(price, tags);

        expectedModel.updateMatchedCustomerList(customer, predicate);

        String expectedMessage = String.format(
                Messages.MESSAGE_CUSTOMERS_MATCH_OVERVIEW + index,
                expectedModel.getFilteredPropertyList().size()
        );
        assertCommandSuccess(matchCustomerCommand, model, expectedMessage, expectedModel);
    }

    private MatchCustomerCommand prepareIndex(String message) {
        try {
            return (new MatchCustomerCommandParser()).parse(message);
        } catch (ParseException pe) {
            assert false;
            return null;
        }
    }
}
