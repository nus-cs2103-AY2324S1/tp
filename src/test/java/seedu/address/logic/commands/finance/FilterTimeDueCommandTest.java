package seedu.address.logic.commands.finance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.parser.DateTimeParser.parseDateTimeDuration;
import static seedu.address.testutil.TypicalFinances.getTypicalCommissionOnlyBook;
import static seedu.address.testutil.TypicalFinances.getTypicalExpenseOnlyBook;
import static seedu.address.testutil.TypicalFinances.getTypicalFinancesBook;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.AddressBook;
import seedu.address.model.EventsBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.event.Duration;
import seedu.address.model.event.TimeEnd;
import seedu.address.model.event.TimeStart;
import seedu.address.model.finance.TimeDueBetweenPredicate;
class FilterTimeDueCommandTest {

    private Model model;
    private Model expectedAllModel;
    private Model expectedExpenseOnlyModel;
    private Model expectedCommissionOnlyModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(new AddressBook(), new EventsBook(), getTypicalFinancesBook(), new UserPrefs());
        expectedAllModel = new ModelManager(new AddressBook(), new EventsBook(), getTypicalFinancesBook(),
                new UserPrefs());
        expectedExpenseOnlyModel = new ModelManager(new AddressBook(), new EventsBook(), getTypicalExpenseOnlyBook(),
                new UserPrefs());
        expectedCommissionOnlyModel = new ModelManager(new AddressBook(), new EventsBook(),
                getTypicalCommissionOnlyBook(), new UserPrefs());
    }
    @Test
    public void execute_filterOverFinances_showsFullList() throws ParseException {
        TimeDueBetweenPredicate predicate = new TimeDueBetweenPredicate(
                new Duration(
                        new TimeStart(LocalDateTime.of(2023, 10, 22, 0, 0)),
                        new TimeEnd(LocalDateTime.of(2023, 10, 31, 0, 0))
                )
        );

        assertCommandSuccess(new FilterTimeDueCommand(predicate), model,
                String.format(
                        Messages.MESSAGE_FINANCE_LISTED_OVERVIEW,
                        expectedAllModel.getFilteredFinanceList().size()
                ),
                expectedAllModel);
        assertEquals(model.getFilteredFinanceList(), expectedAllModel.getFilteredFinanceList());
    }

    @Test
    public void execute_filterOutsideCommissions_showsExpenseList() throws ParseException {
        TimeDueBetweenPredicate predicate = new TimeDueBetweenPredicate(
                new Duration(
                        new TimeStart(LocalDateTime.of(2023, 10, 29, 0, 0)),
                        new TimeEnd(LocalDateTime.of(2023, 10, 31, 0, 0))
                )
        );

        assertCommandSuccess(new FilterTimeDueCommand(predicate), model,
                String.format(
                        Messages.MESSAGE_FINANCE_LISTED_OVERVIEW,
                        expectedExpenseOnlyModel.getFilteredFinanceList().size()
                ),
                expectedAllModel);
        assertEquals(model.getFilteredFinanceList(), expectedExpenseOnlyModel.getFilteredFinanceList());
    }

    @Test
    public void execute_filterOutsideExpenses_showsCommissionList() throws ParseException {
        TimeDueBetweenPredicate predicate = new TimeDueBetweenPredicate(
                new Duration(
                        new TimeStart(LocalDateTime.of(2023, 10, 22, 0, 0)),
                        new TimeEnd(LocalDateTime.of(2023, 10, 28, 0, 0))
                )
        );

        assertCommandSuccess(new FilterTimeDueCommand(predicate), model,
                String.format(
                        Messages.MESSAGE_FINANCE_LISTED_OVERVIEW,
                        expectedCommissionOnlyModel.getFilteredFinanceList().size()
                ),
                expectedAllModel);
        assertEquals(model.getFilteredFinanceList(), expectedCommissionOnlyModel.getFilteredFinanceList());
    }

    @Test
    public void equals() {
        TimeDueBetweenPredicate predicate1 = null;
        TimeDueBetweenPredicate predicate2 = null;
        try {
            predicate1 = new TimeDueBetweenPredicate(parseDateTimeDuration("23-01-2023", "25-01-2023"));
            predicate2 = new TimeDueBetweenPredicate(parseDateTimeDuration("24-01-2023", "25-01-2023"));
        } catch (ParseException e) {
            fail();
        }

        FilterTimeDueCommand command1 = new FilterTimeDueCommand(predicate1);
        FilterTimeDueCommand command2 = new FilterTimeDueCommand(predicate1);
        FilterTimeDueCommand command3 = new FilterTimeDueCommand(predicate2);

        // same object -> returns true
        assertTrue(command1.equals(command1));

        // same values -> returns true
        assertTrue(command1.equals(command2));

        // different types -> returns false
        assertFalse(command1.equals(1));

        // null -> returns false
        assertFalse(command1.equals(null));

        // different predicate -> returns false
        assertFalse(command1.equals(command3));
    }

    @Test
    public void toStringMethod() {
        TimeDueBetweenPredicate predicate = null;
        try {
            predicate = new TimeDueBetweenPredicate(parseDateTimeDuration("23-01-2023", "24-01-2023"));
        } catch (ParseException e) {
            fail();
        }
        FilterTimeDueCommand command = new FilterTimeDueCommand(predicate);
        String expected = FilterTimeDueCommand.class.getCanonicalName() + "{predicate=" + predicate + "}";
        assertEquals(expected, command.toString());
    }
}

