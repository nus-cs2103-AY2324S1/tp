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
import seedu.address.model.displayable.seller.SellerComparator;

public class SortSellerCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_nullSellerComparator_success() {
        SortSellerCommand nullSortSellerCommand = new SortSellerCommand(null);

        String expectedMessage = String.format(SortSellerCommand.MESSAGE_SUCCESS);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.updateFilteredSortedSellerList(null);

        assertCommandSuccess(nullSortSellerCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validSellerComparator_success() {
        String expectedMessage = String.format(SortSellerCommand.MESSAGE_SUCCESS);
        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());

        // 1. sort by name ascending
        SellerComparator ascendingNameComparator = SellerComparator.of(PREFIX_NAME, new SortOrder("a"));
        SortSellerCommand ascendingNameSortCommand = new SortSellerCommand(ascendingNameComparator);
        expectedModel.updateFilteredSortedSellerList(ascendingNameComparator);
        assertCommandSuccess(ascendingNameSortCommand, model, expectedMessage, expectedModel);

        // 2. sort by name descending
        SellerComparator descendingNameComparator = SellerComparator.of(PREFIX_NAME, new SortOrder("d"));
        SortSellerCommand descendingNameSortCommand = new SortSellerCommand(descendingNameComparator);
        expectedModel.updateFilteredSortedSellerList(descendingNameComparator);
        assertCommandSuccess(descendingNameSortCommand, model, expectedMessage, expectedModel);

        // 3. sort by priority ascending
        SellerComparator ascendingPriorityComparator = SellerComparator.of(PREFIX_PRIORITY, new SortOrder("a"));
        SortSellerCommand ascendingPriorityCommand = new SortSellerCommand(ascendingPriorityComparator);
        expectedModel.updateFilteredSortedSellerList(ascendingPriorityComparator);
        assertCommandSuccess(ascendingPriorityCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        SellerComparator ascendingNameComparator = SellerComparator.of(PREFIX_NAME, new SortOrder("a"));
        SellerComparator descendingNameComparator = SellerComparator.of(PREFIX_NAME, new SortOrder("d"));
        SellerComparator ascendingPriorityComparator = SellerComparator.of(PREFIX_PRIORITY, new SortOrder("a"));
        SortSellerCommand ascendingNameSortCommand = new SortSellerCommand(ascendingNameComparator);
        SortSellerCommand descendingNameSortCommand = new SortSellerCommand(descendingNameComparator);
        SortSellerCommand ascendingPriorityCommand = new SortSellerCommand(ascendingPriorityComparator);

        // same object -> returns true
        assertTrue(ascendingNameSortCommand.equals(ascendingNameSortCommand));

        // same values -> returns true
        SortSellerCommand ascendingNameSortCommandCopy = new SortSellerCommand(ascendingNameComparator);
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
