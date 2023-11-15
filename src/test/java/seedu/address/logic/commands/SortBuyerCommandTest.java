package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRIORITY;
import static seedu.address.testutil.TypicalAddressBook.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.displayable.SortOrder;
import seedu.address.model.displayable.buyer.BuyerComparator;

public class SortBuyerCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_nullBuyerComparator_success() {
        SortBuyerCommand nullSortBuyerCommand = new SortBuyerCommand(null);

        String expectedMessage = String.format(SortBuyerCommand.MESSAGE_SUCCESS);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.updateFilteredSortedBuyerList(null);

        assertCommandSuccess(nullSortBuyerCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validBuyerComparator_success() {
        String expectedMessage = String.format(SortBuyerCommand.MESSAGE_SUCCESS);
        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());

        // 1. sort by name ascending
        BuyerComparator ascendingNameComparator = BuyerComparator.of(PREFIX_NAME, new SortOrder("a"));
        SortBuyerCommand ascendingNameSortCommand = new SortBuyerCommand(ascendingNameComparator);
        expectedModel.updateFilteredSortedBuyerList(ascendingNameComparator);
        assertCommandSuccess(ascendingNameSortCommand, model, expectedMessage, expectedModel);

        // 2. sort by name descending
        BuyerComparator descendingNameComparator = BuyerComparator.of(PREFIX_NAME, new SortOrder("d"));
        SortBuyerCommand descendingNameSortCommand = new SortBuyerCommand(descendingNameComparator);
        expectedModel.updateFilteredSortedBuyerList(descendingNameComparator);
        assertCommandSuccess(descendingNameSortCommand, model, expectedMessage, expectedModel);

        // 3. sort by priority ascending
        BuyerComparator ascendingPriorityComparator = BuyerComparator.of(PREFIX_PRIORITY, new SortOrder("a"));
        SortBuyerCommand ascendingPriorityCommand = new SortBuyerCommand(ascendingPriorityComparator);
        expectedModel.updateFilteredSortedBuyerList(ascendingPriorityComparator);
        assertCommandSuccess(ascendingPriorityCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        BuyerComparator ascendingNameComparator = BuyerComparator.of(PREFIX_NAME, new SortOrder("a"));
        BuyerComparator descendingNameComparator = BuyerComparator.of(PREFIX_NAME, new SortOrder("d"));
        BuyerComparator ascendingPriorityComparator = BuyerComparator.of(PREFIX_PRIORITY, new SortOrder("a"));
        SortBuyerCommand ascendingNameSortCommand = new SortBuyerCommand(ascendingNameComparator);
        SortBuyerCommand descendingNameSortCommand = new SortBuyerCommand(descendingNameComparator);
        SortBuyerCommand ascendingPriorityCommand = new SortBuyerCommand(ascendingPriorityComparator);

        // same object -> returns true
        assertTrue(ascendingNameSortCommand.equals(ascendingNameSortCommand));

        // same values -> returns true
        SortBuyerCommand ascendingNameSortCommandCopy = new SortBuyerCommand(ascendingNameComparator);
        assertTrue(ascendingNameSortCommand.equals(ascendingNameSortCommandCopy));

        // different types -> returns false
        assertFalse(ascendingNameSortCommand.equals(1));

        // null -> returns false
        assertFalse(ascendingNameSortCommand.equals(null));

        // different sort order comparator -> returns false
        assertFalse(ascendingNameSortCommand.equals(descendingNameSortCommand));

        // different prefix comparator -> returns false
        assertFalse(ascendingNameSortCommand.equals(ascendingPriorityCommand));
    }
}
