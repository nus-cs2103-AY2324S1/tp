package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandPropertyTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalCustomers.getTypicalAddressBook;
import static seedu.address.testutil.TypicalProperties.getTypicalProperties;
import static seedu.address.testutil.TypicalProperties.getTypicalPropertyBook;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.parser.MatchPropertyCommandParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.customer.Budget;
import seedu.address.model.customer.BudgetAndTagsInRangePredicate;
import seedu.address.model.property.Price;
import seedu.address.model.property.Property;
import seedu.address.model.tag.Tag;

/**
 * Contains integration tests (interaction with the Model) and unit tests for MatchCustomerCommand.
 */
public class MatchPropertyCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), getTypicalPropertyBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), model.getPropertyBook(), new UserPrefs());
    }

    @Test
    public void equals() {
        MatchPropertyCommand matchFirstPropertyCommand = prepareIndex("1");
        MatchPropertyCommand matchSecondPropertyCommand = prepareIndex("2");

        //same object returns true.
        assertTrue(matchFirstPropertyCommand.equals(matchFirstPropertyCommand));

        //same index returns true.
        assertTrue(matchFirstPropertyCommand.equals(prepareIndex("1")));

        //different types returns false.
        assertFalse(matchFirstPropertyCommand.equals(1));
        assertFalse(matchFirstPropertyCommand.equals("1"));

        //null returns false.
        assertFalse(matchFirstPropertyCommand.equals(null));

        //different index return false
        assertFalse(matchFirstPropertyCommand.equals(matchSecondPropertyCommand));
        assertFalse(matchFirstPropertyCommand.equals(prepareIndex("2")));

    }

    @Test
    public void execute_propertyNotFound_showsError() {
        String index = "100";
        MatchPropertyCommand matchPropertyCommand = prepareIndex(index);
        assertCommandFailure(matchPropertyCommand, model, MatchPropertyCommand.MESSAGE_FAIL + index);
    }

    @Test
    public void execute_propertyFound_customerFiltered() {
        String index = "1";

        MatchPropertyCommand matchPropertyCommand = prepareIndex(index);

        Property property = getTypicalProperties().get(0);

        Price price = property.getPrice();
        Set<Tag> tags = property.getTags();

        Budget budget = price.convertToBudget();
        BudgetAndTagsInRangePredicate predicate = new BudgetAndTagsInRangePredicate(budget, tags);

        expectedModel.updateMatchedPropertyList(property, predicate);

        String expectedMessage = String.format(
                Messages.MESSAGE_PROPERTIES_MATCH_OVERVIEW + index,
                expectedModel.getFilteredCustomerList().size()
        );
        assertCommandSuccess(matchPropertyCommand, model, expectedMessage, expectedModel);
    }

    private MatchPropertyCommand prepareIndex(String message) {
        try {
            return (new MatchPropertyCommandParser()).parse(message);
        } catch (ParseException pe) {
            assert false;
            return null;
        }
    }
}
