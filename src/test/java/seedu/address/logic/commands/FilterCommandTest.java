package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.EventBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.statistics.StatisticMetric;
import seedu.address.model.tag.Tag;

public class FilterCommandTest {
    private static final Tag VALID_TAG = new Tag("Interview", "assessment");
    private static final Tag OTHER_VALID_TAG = new Tag("Fake", "Notassessment");
    private static final StatisticMetric VALID_METRIC = StatisticMetric.SCORE;
    private static final StatisticMetric OTHER_VALID_METRIC = StatisticMetric.MEAN;
    private static final int VALID_VALUE = 0;
    private static final int OTHER_VALID_VALUE = 76;
    private Model model = new ModelManager(getTypicalAddressBook(), new EventBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new EventBook(), new UserPrefs());

    @Test
    public void execute_validTagAndMetricAndValue_success() {
        //TODO: Fix this test
        FilterCommand filterCommand = new FilterCommand(VALID_TAG, VALID_METRIC, VALID_VALUE);
        String expectedMessage = filterCommand.successMessage(expectedModel.getFilteredPersonList().size());
    }


    @Test
    public void equals() {
        FilterCommand filterCommandTest = new FilterCommand(VALID_TAG, VALID_METRIC, VALID_VALUE);
        // same object -> returns true
        assertTrue(filterCommandTest.equals(filterCommandTest));

        // same values -> returns true
        FilterCommand filterCommandTestCopy = new FilterCommand(VALID_TAG, VALID_METRIC, VALID_VALUE);
        assertTrue(filterCommandTest.equals(filterCommandTestCopy));

        // different tag -> returns false
        FilterCommand filterCommandTestDiffTag = new FilterCommand(OTHER_VALID_TAG, VALID_METRIC, VALID_VALUE);
        assertFalse(filterCommandTest.equals(filterCommandTestDiffTag));

        // different metric -> returns false
        FilterCommand filterCommandTestDiffMetric = new FilterCommand(VALID_TAG, OTHER_VALID_METRIC, VALID_VALUE);
        assertFalse(filterCommandTest.equals(filterCommandTestDiffMetric));

        // different value -> returns false
        FilterCommand filterCommandTestDiffValue = new FilterCommand(VALID_TAG, VALID_METRIC, OTHER_VALID_VALUE);
        assertFalse(filterCommandTest.equals(filterCommandTestDiffValue));

    }


}
