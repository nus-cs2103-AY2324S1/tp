package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.List;
import java.util.Objects;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.AnimalType;
import seedu.address.model.person.Availability;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.TypicalPersons;

public class StatsCurrentCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private StatsCurrentCommand currentCommand = new StatsCurrentCommand();

    @Test
    public void getCurrentFosterers_unfilteredList() {
        List<Person> result = currentCommand.getCurrentFosterers(model.getFilteredPersonList());
        model.updateFilteredPersonList(fosterer -> TypicalPersons.getCurrentFosterers().contains(fosterer));
        List<Person> currentFosterers = model.getFilteredPersonList();
        assertEquals(currentFosterers, result);
    }

    @Test
    public void getCurrentFosterers_filteredList_notCurrent() {
        model.updateFilteredPersonList(fosterer -> !TypicalPersons.getCurrentFosterers().contains(fosterer));
        List<Person> result = currentCommand.getCurrentFosterers(model.getFilteredPersonList());
        assertTrue(result.isEmpty());
    }

    @Test
    public void getCurrentFosterers_filteredList_ableCat() {
        model.updateFilteredPersonList(fosterer -> TypicalPersons.getAbleCatFosterers().contains(fosterer));
        List<Person> result = currentCommand.getCurrentFosterers(model.getFilteredPersonList());
        assertTrue(result.isEmpty());
    }

    @Test
    public void getCurrentFosterers_filteredList_ableDog() {
        model.updateFilteredPersonList(fosterer -> TypicalPersons.getAbleDogFosterers().contains(fosterer));
        List<Person> result = currentCommand.getCurrentFosterers(model.getFilteredPersonList());
        assertTrue(result.isEmpty());
    }

    @Test
    public void getCurrentDogCount_unfilteredList() {
        int result = currentCommand.getCurrentDogCount(model.getFilteredPersonList());
        model.updateFilteredPersonList(fosterer -> TypicalPersons.getCurrentDogFosterers().contains(fosterer));
        assertEquals(model.getFilteredPersonList().size(), result);
    }

    @Test
    public void getCurrentDogCount_filteredList_notCurrent() {
        model.updateFilteredPersonList(fosterer -> !TypicalPersons.getCurrentFosterers().contains(fosterer));
        int result = currentCommand.getCurrentDogCount(model.getFilteredPersonList());
        assertEquals(0, result);
    }

    @Test
    public void getCurrentDogCount_filteredList_currentCat() {
        model.updateFilteredPersonList(fosterer -> TypicalPersons.getCurrentCatFosterers().contains(fosterer));
        int result = currentCommand.getCurrentDogCount(model.getFilteredPersonList());
        assertEquals(0, result);
    }

    @Test
    public void getCurrentCatCount_unfilteredList() {
        int result = currentCommand.getCurrentCatCount(model.getFilteredPersonList());
        model.updateFilteredPersonList(fosterer -> TypicalPersons.getCurrentCatFosterers().contains(fosterer));
        assertEquals(model.getFilteredPersonList().size(), result);
    }

    @Test
    public void getCurrentCatCount_filteredList_notCurrent() {
        model.updateFilteredPersonList(fosterer -> !TypicalPersons.getCurrentFosterers().contains(fosterer));
        int result = currentCommand.getCurrentCatCount(model.getFilteredPersonList());
        assertEquals(0, result);
    }

    @Test
    public void getCurrentCatCount_filteredList_currentDog() {
        //can only be current.Dog or current.Cat
        model.updateFilteredPersonList(fosterer -> TypicalPersons.getCurrentDogFosterers().contains(fosterer));
        int result = currentCommand.getCurrentCatCount(model.getFilteredPersonList());
        assertEquals(0, result);
    }

    @Test
    public void execute_unfilteredList_success() throws CommandException {
        List<Person> fosterers = model.getFilteredPersonList();
        int total = fosterers.size();
        int currCount = currentCommand.getCurrentFosterers(fosterers).size();
        int currDogCount = currentCommand.getCurrentDogCount(fosterers);
        int currCatCount = currentCommand.getCurrentCatCount(fosterers);
        int unknown = currCount - currCatCount - currDogCount;

        String summary = String.format(StatsCurrentCommand.MESSAGE_CURRENT_SUMMARY, currCount,
                total, StatsAvailCommand.calculatePercentage(currCatCount, total));

        String details = String.format(StatsCurrentCommand.MESSAGE_CURRENT_DETAILS, currDogCount,
                StatsCommand.calculatePercentage(currDogCount, currCount), currCatCount,
                StatsCommand.calculatePercentage(currCatCount, currCount), unknown,
                StatsCommand.calculatePercentage(unknown, currCount));
        assertEquals(summary + "\n" + details, currentCommand.execute(model).getFeedbackToUser());
    }

    @Test
    public void execute_noFosterers_throwsCommandException() {
        model.updateFilteredPersonList(Objects::isNull);
        assertCommandFailure(currentCommand, model, StatsCommand.MESSAGE_NO_FOSTERERS);
    }

    @Test
    public void execute_filteredList_expectZero() throws CommandException {
        model.updateFilteredPersonList(fosterer -> !TypicalPersons.getCurrentFosterers().contains(fosterer));
        List<Person> fosterers = model.getFilteredPersonList();
        int total = fosterers.size();
        int currCount = currentCommand.getCurrentFosterers(fosterers).size();

        String summary = String.format(StatsCurrentCommand.MESSAGE_CURRENT_SUMMARY, currCount,
                total, StatsCommand.calculatePercentage(currCount, total));
        assertEquals(summary, currentCommand.execute(model).getFeedbackToUser());
    }

    @Test
    public void equals() {
        StatsCurrentCommand currentSecondCommand = new StatsCurrentCommand();
        // same object -> returns true
        assertEquals(currentCommand, currentCommand);

        // different object -> returns true
        assertEquals(currentCommand, currentSecondCommand);

        // different types -> returns false
        assertFalse(currentCommand.equals(1));
        assertFalse(currentCommand.equals(new StatsAvailCommand()));
        assertFalse(currentCommand.equals(new StatsHousingCommand()));

        // null -> returns false
        assertFalse(currentCommand.equals(null));
    }
}
