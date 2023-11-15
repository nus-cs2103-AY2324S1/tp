package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.List;
import java.util.Objects;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.testutil.TypicalPersons;

public class StatsAvailCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private StatsAvailCommand availCommand = new StatsAvailCommand();


    @Test
    public void getAvailableFosterers_unfilteredList() {
        List<Person> result = availCommand.getAvailableFosterers(model.getFilteredPersonList());
        model.updateFilteredPersonList(fosterer -> TypicalPersons.getAvailableFosterers().contains(fosterer));
        List<Person> availableFosterers = model.getFilteredPersonList();
        assertEquals(availableFosterers, result);
    }

    @Test
    public void getAvailableFosterers_filteredList_notAvailable() {
        model.updateFilteredPersonList(fosterer -> !TypicalPersons.getAvailableFosterers().contains(fosterer));
        List<Person> result = availCommand.getAvailableFosterers(model.getFilteredPersonList());
        assertTrue(result.isEmpty());
    }

    @Test
    public void getAvailableFosterers_filteredList_currentCat() {
        model.updateFilteredPersonList(fosterer -> TypicalPersons.getCurrentCatFosterers().contains(fosterer));
        List<Person> result = availCommand.getAvailableFosterers(model.getFilteredPersonList());
        assertTrue(result.isEmpty());
    }

    @Test
    public void getAvailableFosterers_filteredList_currentDog() {
        model.updateFilteredPersonList(fosterer -> TypicalPersons.getCurrentDogFosterers().contains(fosterer));
        List<Person> result = availCommand.getAvailableFosterers(model.getFilteredPersonList());
        assertTrue(result.isEmpty());
    }

    @Test
    public void getAbleDogCount_unfilteredList() {
        int result = availCommand.getAbleDogCount(model.getFilteredPersonList());
        model.updateFilteredPersonList(fosterer -> TypicalPersons.getAbleDogFosterers().contains(fosterer));
        assertEquals(model.getFilteredPersonList().size(), result);
    }

    @Test
    public void getAbleDogCount_filteredList_current() {
        model.updateFilteredPersonList(fosterer -> TypicalPersons.getCurrentFosterers().contains(fosterer));
        int result = availCommand.getAbleDogCount(model.getFilteredPersonList());
        assertEquals(0, result);
    }

    @Test
    public void getAbleDogCount_filteredList_ableCat() {
        //can only be able.Dog or able.Cat
        model.updateFilteredPersonList(fosterer -> TypicalPersons.getAbleCatFosterers().contains(fosterer));
        int result = availCommand.getAbleDogCount(model.getFilteredPersonList());
        assertEquals(0, result);
    }

    @Test
    public void getAbleCatCount_unfilteredList() {
        int result = availCommand.getAbleCatCount(model.getFilteredPersonList());
        model.updateFilteredPersonList(fosterer -> TypicalPersons.getAbleCatFosterers().contains(fosterer));
        assertEquals(model.getFilteredPersonList().size(), result);
    }

    @Test
    public void getAbleCatCount_filteredList_current() {
        model.updateFilteredPersonList(fosterer -> TypicalPersons.getCurrentFosterers().contains(fosterer));
        int result = availCommand.getAbleCatCount(model.getFilteredPersonList());
        assertEquals(0, result);
    }

    @Test
    public void getAbleCatCount_filteredList_ableDog() {
        model.updateFilteredPersonList(fosterer ->TypicalPersons.getAbleDogFosterers().contains(fosterer));
        int result = availCommand.getAbleCatCount(model.getFilteredPersonList());
        assertEquals(0, result);
    }

    @Test
    public void execute_unfilteredList_success() throws CommandException {
        List<Person> fosterers = model.getFilteredPersonList();
        int total = fosterers.size();
        int availableCount = availCommand.getAvailableFosterers(fosterers).size();
        int ableDogCount = availCommand.getAbleDogCount(fosterers);
        int ableCatCount = availCommand.getAbleCatCount(fosterers);
        int unknown = availableCount - ableDogCount - ableCatCount;

        String summary = String.format(StatsAvailCommand.MESSAGE_AVAIL_SUMMARY, availableCount,
                total, StatsAvailCommand.calculatePercentage(availableCount, total));

        String details = String.format(StatsAvailCommand.MESSAGE_AVAIL_DETAILS, ableDogCount,
                StatsAvailCommand.calculatePercentage(ableDogCount, availableCount), ableCatCount,
                StatsAvailCommand.calculatePercentage(ableCatCount, availableCount), unknown,
                StatsAvailCommand.calculatePercentage(unknown, availableCount));
        assertEquals(summary + "\n" + details, availCommand.execute(model).getFeedbackToUser());
    }

    @Test
    public void execute_noFosterers_throwsCommandException() {
        model.updateFilteredPersonList(Objects::isNull);
        assertCommandFailure(availCommand, model, StatsCommand.MESSAGE_NO_FOSTERERS);
    }

    @Test
    public void execute_filteredList_expectZero() throws CommandException {
        model.updateFilteredPersonList(fosterer -> !TypicalPersons.getAvailableFosterers().contains(fosterer));
        List<Person> fosterers = model.getFilteredPersonList();
        int total = fosterers.size();
        int availableCount = availCommand.getAvailableFosterers(fosterers).size();

        String summary = String.format(StatsAvailCommand.MESSAGE_AVAIL_SUMMARY, availableCount,
                total, StatsAvailCommand.calculatePercentage(availableCount, total));
        assertEquals(summary, availCommand.execute(model).getFeedbackToUser());
    }

    @Test
    public void equals() {
        StatsAvailCommand availSecondCommand = new StatsAvailCommand();

        // same object -> returns true
        assertEquals(availCommand, availCommand);

        // different object -> returns true
        assertEquals(availCommand, availSecondCommand);

        // different types -> returns false
        assertFalse(availCommand.equals(1));
        assertFalse(availCommand.equals(new StatsCurrentCommand()));
        assertFalse(availCommand.equals(new StatsHousingCommand()));

        // null -> returns false
        assertFalse(availCommand.equals(null));
    }
}
