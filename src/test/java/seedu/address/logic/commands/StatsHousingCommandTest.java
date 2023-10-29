package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.testutil.TypicalPersons;

import java.util.List;
import java.util.Objects;

public class StatsHousingCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private StatsHousingCommand housingCommand = new StatsHousingCommand();

    @Test
    public void getHdbCount_unfilteredList() {
        int result = housingCommand.getHdbCount(model.getFilteredPersonList());
        model.updateFilteredPersonList(fosterer -> TypicalPersons.getHdbFosterers().contains(fosterer));
        assertEquals(model.getFilteredPersonList().size(), result);
    }

    @Test
    public void getHdbCount_filteredList_expectZero() {
        model.updateFilteredPersonList(fosterer -> !TypicalPersons.getHdbFosterers().contains(fosterer));
        int result = housingCommand.getHdbCount(model.getFilteredPersonList());
        assertEquals(0, result);
    }

    @Test
    public void getCondoCount_unfilteredList() {
        int result = housingCommand.getCondoCount(model.getFilteredPersonList());
        model.updateFilteredPersonList(fosterer -> TypicalPersons.getCondoFosterers().contains(fosterer));
        assertEquals(model.getFilteredPersonList().size(), result);
    }

    @Test
    public void getCondoCount_filteredList_expectZero() {
        model.updateFilteredPersonList(fosterer -> !TypicalPersons.getCondoFosterers().contains(fosterer));
        int result = housingCommand.getCondoCount(model.getFilteredPersonList());
        assertEquals(0, result);
    }

    @Test
    public void getLandedCount_unfilteredList() {
        int result = housingCommand.getLandedCount(model.getFilteredPersonList());
        model.updateFilteredPersonList(fosterer -> TypicalPersons.getLandedFosterers().contains(fosterer));
        assertEquals(model.getFilteredPersonList().size(), result);
    }

    @Test
    public void getLandedCount_filteredList_expectZero() {
        model.updateFilteredPersonList(fosterer -> !TypicalPersons.getLandedFosterers().contains(fosterer));
        int result = housingCommand.getLandedCount(model.getFilteredPersonList());
        assertEquals(0, result);
    }

    @Test
    public void execute_unfilteredList_success() throws CommandException {
        List<Person> fosterers = model.getFilteredPersonList();
        int total = fosterers.size();
        int hdbCount = housingCommand.getHdbCount(fosterers);
        int condoCount = housingCommand.getCondoCount(fosterers);
        int landedCount = housingCommand.getLandedCount(fosterers);
        int unknown = total - hdbCount - condoCount - landedCount;

        String expected = String.format(StatsHousingCommand.MESSAGE_HOUSING_SUCCESS, total,
                hdbCount, StatsCommand.calculatePercentage(hdbCount, total),
                condoCount, StatsCommand.calculatePercentage(condoCount, total),
                landedCount, StatsCommand.calculatePercentage(landedCount, total),
                unknown, StatsAvailCommand.calculatePercentage(unknown, total));
        assertEquals(expected, housingCommand.execute(model).getFeedbackToUser());
    }

    @Test
    public void execute_noFosterers_throwsCommandException() {
        model.updateFilteredPersonList(Objects::isNull);
        assertCommandFailure(housingCommand, model, StatsCommand.MESSAGE_NO_FOSTERERS);
    }

    @Test
    public void equals() {
        StatsHousingCommand housingSecondCommand = new StatsHousingCommand();
        // same object -> returns true
        assertEquals(housingCommand, housingCommand);

        // different object -> returns true
        assertEquals(housingCommand, housingSecondCommand);

        // different types -> returns false
        assertFalse(housingCommand.equals(1));
        assertFalse(housingCommand.equals(new StatsAvailCommand()));
        assertFalse(housingCommand.equals(new StatsCurrentCommand()));

        // null -> returns false
        assertFalse(housingCommand.equals(null));
    }
}
